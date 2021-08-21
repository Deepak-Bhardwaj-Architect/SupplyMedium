package core.usermgmt;

import java.sql.Timestamp;

import utils.ErrorLogger;
import utils.HTMLMailComposer;
import utils.Mailer;
import utils.UserStatus;
import core.login.LoginAttemptsData;
import core.regn.LoginData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import core.regn.UserSignupProcess;
import core.useracctmgmt.PasswordHistory;
import core.useracctmgmt.PasswordResetData;
import db.login.ActivationPendingTable;
import db.login.LoginAttemptsTable;
import db.regn.UserLoginTable;
import db.regn.UserProfileTable;
import db.usermgmt.UserAccountPoliciesTable;
import utils.ErrorMaster;

public class UserStatusUpdate implements UserSignupProcess
{
    
    private static ErrorMaster errorMaster_ = null;

    public UserStatusUpdate()
    {
        if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
    }
    
	/*
	 * Method : process( )
	 * 
	 * Input : User Profile details
	 * 
	 * Return : int value specifies the User status update result
	 * (Success/failed)
	 * 
	 * Purpose: This method is used to process the updating user status to ,
	 * activate user from 1. Pending to Active - (activation tbl record are
	 * shifted to login tbl) 2. Active to Blocked 3. Blocked to Active
	 */

	@Override
	public int process( UserProfileData signupDataObj )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		int updateStatusResult = 0;

		UserProfileTable userProfileTable = new UserProfileTable( );
		
		
		if( isUserActivation( signupDataObj ) == false )
		{
			errorMaster_.insert( "Update status only" );
		
			updateStatusResult = userProfileTable.updateStatus(
			        signupDataObj.userProfileKey_, signupDataObj.userStatus_ );
			
			// Sending mail for account blocked by admin
			if( signupDataObj.userStatus_.equals( UserStatus.status.BLOCKED.getValue( ) ))
			{
				sendUserStatusChangeMail( signupDataObj.emailId_,signupDataObj.firstName_, signupDataObj.userStatus_ );
			}
			else if( signupDataObj.userStatus_.equals( "Reject" ) )  // Sending mail for signup request rejected by admin
			{
				sendUserStatusChangeMail( signupDataObj.emailId_,signupDataObj.firstName_, signupDataObj.userStatus_ );
			}
			
			StringBuilder currentStatus = new StringBuilder( );

			if( userProfileTable.getStatus( signupDataObj.userProfileKey_, currentStatus ) == 0 )
			{
				
				if( currentStatus.equals( UserStatus.status.ACTIVE.getValue( ) ) )
				{
					LoginData loginData = new LoginData( );
					loginData.emailid_ = signupDataObj.userProfileKey_.email_;
					resetLoginAttempts( loginData );
				}

				UserAccountPoliciesTable userPoliciesTable = new UserAccountPoliciesTable( );

				if( signupDataObj.userStatus_.equals( UserStatus.status.ACTIVE.getValue( ) ) )
				{
					signupDataObj.disableAccountFlag_ = 0;
					userPoliciesTable.updatePolicies( signupDataObj );
				}
				if( signupDataObj.userStatus_.equals( UserStatus.status.BLOCKED.getValue( ) ) )
				{
					signupDataObj.disableAccountFlag_ = 1;
					userPoliciesTable.updatePolicies( signupDataObj );
				}

			}

			return 720;
		}

		updateStatusResult = userProfileTable.updateStatus(
		        signupDataObj.userProfileKey_, signupDataObj.userStatus_ );
		
		if( updateStatusResult == 0 )
		{
			errorMaster_.insert( "Update and activate user" );

			ActivationPendingTable pendingTbl = new ActivationPendingTable( );

			int emailResult = pendingTbl.isEmailExists( signupDataObj.userProfileKey_ );

			LoginData loginData = new LoginData( );

			if( emailResult == 0 )
			{
				int loginDataRes = pendingTbl.getRecord( signupDataObj.emailId_, loginData );

				errorMaster_.insert( "Change activation record to login record - progress 1" );

				if( loginDataRes == 0 )
				{
					int loginVal = 0;

					UserLoginTable userLoginTblObj = new UserLoginTable( );

					loginVal = userLoginTblObj.insert( loginData );

					userLoginTblObj = null;

					System.out
					        .println( "Change activation record to login record - progress 2" );

					if( loginVal == 0 )
					{
						
						PasswordResetData data = new PasswordResetData( );
						
						data.newPassword_ = loginData.password_;
						
						if( signupDataObj.companyRegnKey_ == null )
						{
							userProfileTable.find(signupDataObj.userProfileKey_, signupDataObj);
						}
						
						data.regnKey_ = signupDataObj.companyRegnKey_;
						
						data.userKey_ = signupDataObj.userProfileKey_;
						
						PasswordHistory passwordHistory = new PasswordHistory( );
						
						int result = passwordHistory.add( data );
						
						passwordHistory = null;
						
						if( result == 2520 )
						{						
    						UserProfileKey userKey = new UserProfileKey( );
    
    						userKey.email_ = loginData.emailid_;
    
    						errorMaster_.insert( "Activation table deleting  - key value : "
    						        + userKey.email_ );
    
    						pendingTbl.delete( userKey );
    
    						userKey = null;
    
    						pendingTbl = null;
    
    						errorMaster_.insert( "Change activation record to login record - progress 3" );
    						
    						sendUserStatusChangeMail( signupDataObj.emailId_,signupDataObj.firstName_, signupDataObj.userStatus_ );
    						
    						return 720; // Success status
						}
						else 
						{
							return 721;
						}
					}
					else
					{
						return 721; // Error updating user status
					}
				}
				else
				{
					return 721; // Error updating user status
				}
			}
			else
			{
				return 721; // Error updating user status
			}
		}

		else
		{
			String errorMessage = "Error::UserStatusUpdate:process - update user status failed";

			errorLogger.logMsg( errorMessage );

			userProfileTable = null;

			return 721; // Error updating user status
		}
	}

	/*
	 * Method : isUserActivation( )
	 * 
	 * Input : User Profile details
	 * 
	 * Return : Boolean value specifies the request is for user activation or
	 * status update
	 * 
	 * Purpose: This method is used to find whether the request is for user
	 * activation i.e changing user status from Pending to Active or not.
	 */

	private Boolean isUserActivation( UserProfileData signupDataObj )
	{
		UserProfileTable userProfileTable = new UserProfileTable( );

		StringBuilder currentStatus = new StringBuilder( );

		Boolean actStatus = false;

		if( userProfileTable.getStatus( signupDataObj.userProfileKey_, currentStatus ) == 0 )
		{
			if( currentStatus.toString( ).equals( UserStatus.status.PENDING.getValue( ) )
			        && signupDataObj.userStatus_.equals( UserStatus.status.ACTIVE
			                .getValue( ) ) )
			{
				
				actStatus = true;
			}
		}

		return actStatus;
	}
	
	/*
	 * Method : sendUserStatusChangeMail( )
	 * 
	 * Input : String emailId, String firstName
	 * 
	 * Return : void
	 * 
	 * Purpose: It is used to send the mail while admin change the user status
	 */

	private void sendUserStatusChangeMail( String emailId, String firstName, String status )
	{
		Mailer mailerObj = new Mailer( );

		String [ ] to = { emailId };
		
		String sub = "";
		
		if( status.equals( UserStatus.status.ACTIVE.getValue( ) ))  // Mail content for account activated successfully
		{
			sub = "SupplyMedium Account Activated By Admin";
		}
		else if( status.equals( UserStatus.status.BLOCKED.getValue( ) ) ) // Mail content for account blocked
		{
		
			sub = "SupplyMedium Account Blocked By Admin";
		}
		else if( status.equals( "Reject" )) // Mail content for user signup rejected by admin
		{
			sub = "SupplyMedium Account Rejected By Admin";
		}


		//String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );

		//String message = path + "/Views/Registration/jsp/useractivate.jsp?key=" + uuid;
		
		HTMLMailComposer composer = new HTMLMailComposer( );
		
		String message = composer.sendUserStatusChangeMail( emailId, firstName, status );
		
		composer = null;

		mailerObj.composeAndSendMail( to, sub, message );

		mailerObj = null;

		errorMaster_.insert( "Mail send successfully" );
	}
	
	/*
	 * Method: resetLoginAttempts
	 * 
	 * Input: LoginData obj
	 * 
	 * Return: void
	 * 
	 * Purpose: This method fetches the record from login attempts and if
	 * exists, resets the login attempts count to 0;
	 */

	private void resetLoginAttempts( LoginData loginData )
	{
		LoginAttemptsData loginAttemptsData = new LoginAttemptsData( );

		LoginAttemptsTable loginAttemptsTbl = new LoginAttemptsTable( );

		int result = loginAttemptsTbl.getLoginAttempts( loginData.emailid_, loginAttemptsData );
		
		if( result == -2 )
			return;
		
		loginAttemptsData.email_ = loginData.emailid_;

		loginAttemptsData.invalidAttemptsCount_ = 1;

		loginAttemptsData.attemptedTimestamp_ = new Timestamp( System.currentTimeMillis( ) );

		loginAttemptsData.lockoutTimestamp_ = loginAttemptsData.attemptedTimestamp_;

		
		loginAttemptsTbl.updateLoginAttempts( loginAttemptsData );

		loginAttemptsData = null;

		loginAttemptsTbl = null;
	}
}
