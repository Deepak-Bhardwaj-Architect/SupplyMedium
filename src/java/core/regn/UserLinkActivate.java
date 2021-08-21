package core.regn;


import utils.PasswordStore;
import utils.UserStatus;
import core.config.SMConfigData;
import core.login.UserkeyUuidMapperData;
import core.useracctmgmt.PasswordHistory;
import core.useracctmgmt.PasswordResetData;

import db.config.SMConfigTable;
import db.login.ActivationPendingTable;
import db.login.UserkeyUuidMapperTable;

import db.regn.UserLoginTable;
import db.regn.UserProfileTable;

/*
 * This is the implementation class for the interface SignupProcess.
 * This class is used to activate the user after getting password from him 
 * when he clicks the user activation link
 */

public class UserLinkActivate implements UserSignupProcess
{
	/*
	 * Method : process( ) Input : user profile details
	 * 
	 * Return : int value specify the user profile activation result
	 * (Success/failed)
	 * 
	 * Purpose: Get the uuid from user profile details. Check whether the uuid
	 * is available or not. If available, the userkey will be get from uuid
	 * mapper table.
	 * 
	 * Then, with the user key, the login table will be updated,
	 * 
	 * The user status will be changed and return error code according to
	 * errors.
	 */

	@Override
	public int process( UserProfileData signupDataObj )
	{
		// CompanyRegnTable companyRegnTblObj = new CompanyRegnTable( );

		int emailExistResult = 0;

		UserkeyUuidMapperTable mapperTable = new UserkeyUuidMapperTable( );

		UserkeyUuidMapperData mapperData = new UserkeyUuidMapperData( );

		emailExistResult = mapperTable.getEmailId( signupDataObj.profileUuid_, mapperData );

		if( emailExistResult == 0 )
		{
			LoginData loginData = new LoginData( );

			loginData.emailid_ = mapperData.userRelKey_;

			UserProfileKey profileKey = new UserProfileKey( );

			profileKey.email_ = mapperData.userRelKey_;

			signupDataObj.userProfileKey_ = profileKey;

			signupDataObj.emailId_ = mapperData.userRelKey_;

			PasswordStore pStore = new PasswordStore( );

			loginData.password_ = pStore.getEncryptedValue( signupDataObj.password_ );

			pStore = null;

			UserProfileTable profileTable = new UserProfileTable( );

			int dateResult = 0;

			dateResult = profileTable.getCreatedDate( signupDataObj );

			if( dateResult == 0 )
			{

				int diffInDays = (int)( ( ( new java.util.Date( ) ).getTime( ) - signupDataObj.createdDate_
				        .getTime( ) ) / ( 1000 * 60 * 60 * 24 ) );

				SMConfigData smConfigData = new SMConfigData( );

				SMConfigTable smConfigTbl = new SMConfigTable( );

				smConfigTbl.getSMConfig( smConfigData );

				smConfigTbl = null;

				if( diffInDays > smConfigData.regLinkExpireDays_ ) // Checking
																   // in
				                                                   // days
				{
					return 712; // link expired
				}

				else
				{
					int result = profileTable.updateStatus( signupDataObj.userProfileKey_,
					        UserStatus.status.ACTIVE.getValue( ) );

					if( result == 0 )
					{
						int getResult = 0;

						ActivationPendingTable actTbl = new ActivationPendingTable( );

						LoginData actPendingData = new LoginData( );

						getResult = actTbl.getRecord( signupDataObj.emailId_,
						        actPendingData );

						int loginVal = 0;

						UserLoginTable userLoginTblObj = new UserLoginTable( );

						if( getResult == 0 )
						{
							loginVal = userLoginTblObj.insert( loginData );

							userLoginTblObj = null;
						}
						else
						{

							loginVal = userLoginTblObj.resetPassword( loginData );

							userLoginTblObj = null;
						}

						if( loginVal == 0 )
						{
							
							PasswordResetData data = new PasswordResetData( );
    						
    						data.newPassword_ = loginData.password_;
    						
    						
    						
    						if( signupDataObj.companyRegnKey_ == null )
    						{
    							profileTable.find(signupDataObj.userProfileKey_, signupDataObj);
    						}
    						
    						data.regnKey_ 	  = signupDataObj.companyRegnKey_;
    						
    						data.userKey_     = profileKey;
    						
    						PasswordHistory passwordHistory = new PasswordHistory( );
    						
    						result = passwordHistory.add( data );
    						
    						passwordHistory = null;
    						
    						if( result == 2520 )
    
    						{
    							ActivationPendingTable pendingTbl = new ActivationPendingTable( );

    							pendingTbl.delete( profileKey );

    							profileKey = null;

    							pendingTbl = null;

    							return 710; //Success
    						}
    						else 
    						{
    							data = null;
    							return 710; //Success
							}
						}
						else
						{
							return 715;
						}

					}

					else
					{
						return 711; // result=1 activation failed,
					}
				}
			}

			else
			{
				return 717; // Fetching created date error
			}

		}
		else
		{
			mapperTable = null;
			mapperData = null;

			return 713; // The link is not valid
		}

	}

}
