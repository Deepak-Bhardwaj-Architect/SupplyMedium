package core.regn;

import java.util.List;

import core.config.SMConfigData;
import core.useracctmgmt.PasswordHistory;
import core.useracctmgmt.PasswordResetData;

import utils.ErrorLogger;

import db.config.SMConfigTable;
import db.login.ActivationPendingTable;
import db.regn.CompanyRegnTable;
import db.regn.UserLoginTable;
import utils.ErrorMaster;

/*
 * This is the implementation class for the interface RegnProcess.
 * This class is used to activate the company once user click the company activation link
 */

public class RegnLinkActivate implements RegnProcess
{
    
    private static ErrorMaster errorMaster_ = null;

    public RegnLinkActivate()
    {
        if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
    }
	/*
	 * Method : process( ) Input : Company registration details Return : int
	 * value specify the company activation result (Success/failed)
	 * 
	 * Purpose: Get the uuid from company registration details. Check whether it
	 * is valid uuid. If valid uuid activate the corresponding company.otherwise
	 * return error code according to errors.
	 */

	public int process( RegnData regnDataObj )
	{

		CompanyRegnTable companyRegnTblObj = new CompanyRegnTable( );

		List<RegnData> regnDataList = null;

		int companyListSize = 0;

		try
		{
			regnDataList = companyRegnTblObj.getCompany( regnDataObj.uuid_ );

			companyListSize = regnDataList.size( );
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			errLogger_.logMsg( "Exception::RegnActivate.process() - "
			        + ex );
		}
		
		errorMaster_.insert( "companylistsize=" + companyListSize );

		if( companyListSize > 0 )
		{
			RegnData regnData = regnDataList.get( 0 );

			// float diffInDays = (int)( ( ( new java.util.Date( ) ).getTime( )
			// - regnData.createdDate_
			// .getTime( ) ) / ( 1000 * 60 * 60 * 24 ) );

			int diffInDays = (int)( ( ( new java.util.Date( ) ).getTime( ) - regnData.createdDate_
			        .getTime( ) ) / ( 1000 * 60 * 60 * 24) );

			SMConfigData smConfigData = new SMConfigData( );

			SMConfigTable smConfigTbl = new SMConfigTable( );

			smConfigTbl.getSMConfig( smConfigData );

			smConfigTbl = null;

			if( diffInDays > smConfigData.regLinkExpireDays_ ) // Checking in
			                                                   // days
			{
				return 201; // link expired
			}

			else
			{
				int result = companyRegnTblObj.activateCompany( regnData.companyRegnKey_ );

				if( result == 0 )
				{

					int getResult	= 0;
					
					ActivationPendingTable actTbl = new ActivationPendingTable( );
					
					LoginData loginData = new LoginData( );
					
					getResult	= actTbl.getRecord( regnData.emailId_, loginData );
					
					if( getResult == 0 )
					{
					
    					UserLoginTable userLoginTblObj = new UserLoginTable( );
    
    					int loginVal = 0;
    
    					//LoginData loginData = createLoginData( regnDataObj );
    
    					loginVal = userLoginTblObj.insert( loginData );
    					
    					userLoginTblObj = null;
    
    					if( loginVal == 0 )
    					{
    						UserProfileKey userKey = new UserProfileKey( );
    
    						userKey.email_ = loginData.emailid_;
    						
    						PasswordResetData data = new PasswordResetData( );
    						
    						data.newPassword_ = loginData.password_;
    						
    						data.regnKey_ = regnData.companyRegnKey_;
    						
    						data.userKey_ = userKey;
    						
    						PasswordHistory passwordHistory = new PasswordHistory( );
    						
    						result = passwordHistory.add( data );
    						
    						passwordHistory = null;
    						
    						if( result == 2520)
    
    						{
    							ActivationPendingTable pendingTbl = new ActivationPendingTable( );
    
    							pendingTbl.delete( userKey );
    
    							userKey = null;
    
    							pendingTbl = null;
    						}
    						else 
    						{
    							data = null;
							}
    
    					}
    					else
    					{
    						return 205;
						}
    					// return loginVal;
    					
					}
					else 
					{
						return 204;
					}

					return 200; // result = 0 company successfully activated.
				}

				else
				{
					return 202; // result=1 activation failed,
				}

			}
		}

		else
		{
			return 203; // company not found
		}

	}
}
