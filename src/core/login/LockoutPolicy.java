package core.login;

import java.sql.Timestamp;

import utils.CompanyStatus;
import utils.ErrorLogger;
import utils.UserStatus;
import core.regn.CompanyRegnKey;
import core.regn.LoginData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.login.LoginAttemptsTable;
import db.regn.CompanyRegnTable;
import db.regn.UserLoginTable;
import db.regn.UserProfileTable;
import db.usermgmt.UserAccountPoliciesTable;

/*
 * Class  : LockoutPolicy
 * 
 * Purpose: It is used to check whether the login credentials are active, lock free, valid, 
 * active, etc. If invalid attempts made, then the attempt will be logged
 */

public class LockoutPolicy implements AccountPolicy
{
	/*
	 * Method : enforce( )
	 * 
	 * Input : AccountPolicyData obj, LoginData obj, LoginError obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to enforce all the the lockout policies for the
	 * entered login credentials and return appropriate response code. Based on
	 * the response code the credentials will be validated
	 */

	public int enforce( AccountPolicyData accPolicyData, LoginData loginData )
	{
		UserLoginTable userLogin = new UserLoginTable( );

		LockoutPolicyData lockoutPolicyData = new LockoutPolicyData( );
		
		lockoutPolicyData = accPolicyData.lockoutPolicyData_;
		
		//The default failed attempt reset config value
		
		int resetSec = lockoutPolicyData.resetLockoutDurationMin_ * 60 * 10 * 100;

		
		//The default lockout duration config value
		
		int lockoutSec = lockoutPolicyData.lockoutDurationMin_ * 60 * 10 * 100;
		

		//Admin should unlock the user - Flag value
		
		int adminUnlockFlag = lockoutPolicyData.adminUnlockFlag_;
		
		
		UserProfileData profileData = new UserProfileData( );

		int loginResult = userLogin.checkLoginCredentials( loginData );

		if( loginResult == 0 ) // Login credentials are correct
		{
			//To check whether account is expired or not. If expired, 
			//the error code will be returned
			int responseCode = checkAccountExpire( loginData );
			
			if( responseCode != 400 )
			{
				return responseCode; 
			}
			
			// If account lock exists after entering correct credentials

			if( isLockExists( accPolicyData, loginData ) )
			{
				userLogin = null;
				profileData = null;

				return 406; // Account locked
			}

			UserProfileTable userProfileTbl = new UserProfileTable( );
			int profileResult = userProfileTbl.getUserprofile
    													( 
    														loginData.emailid_,
    														profileData 
    													);
			if( profileResult == 0 )
			{
				// To check whether the company is active or not
				if( !isCompanyActive( accPolicyData, loginData, profileData ) )
				{
					userLogin = null;
					profileData = null;
					userProfileTbl = null;

					return 409; // Company Status not exists
				}

				else
				{
					// To check whether the user status is active or note

					responseCode = checkUserStatus( accPolicyData, loginData, profileData );

					if (responseCode == 400 )
					{
						resetLoginAttempts( loginData );
					}
					
					userLogin = null;
					profileData = null;
					userProfileTbl = null;

					// The response code will be one from 400, 401, 402 and 408
					
					return responseCode;
				}
			}
			else
			{
				userLogin = null;
				profileData = null;
				userProfileTbl = null;

				return 404; // Unknown error
			}
		}
		
		// To log the invalid login attempts
		else
		{
			LoginAttemptsTable loginAttemptsTbl = new LoginAttemptsTable( );
			LoginAttemptsData loginAttemptsData = new LoginAttemptsData( );

			int result = loginAttemptsTbl.getLoginAttempts( loginData.emailid_,
			        loginAttemptsData );

			//There already exists login attempts for logged in user.  Need to update
			//the existing record.
			
			if( result == 0 )
			{
				Timestamp lockoutTs = loginAttemptsData.lockoutTimestamp_;
				Timestamp attemptTs = loginAttemptsData.attemptedTimestamp_;

				int invalidAttemptCount = loginAttemptsData.invalidAttemptsCount_;
				System.out.println( "Invalid Attempts Count: " + invalidAttemptCount);

				Timestamp currentTimestamp = null;
				if( loginAttemptsData.lockoutTimestamp_
				        .equals( loginAttemptsData.attemptedTimestamp_ ) ) 
				// No lock
				{
					if( System.currentTimeMillis( ) > ( attemptTs.getTime( ) + resetSec ) )
					{
						// Reset time exceeds. No need to count attempts;
						// invalid attempts is reset to 1;

						currentTimestamp = new Timestamp( System.currentTimeMillis( ) );
						updateLoginAttempts( loginData, 1, currentTimestamp,
						        currentTimestamp );

						userLogin = null;
						profileData = null;
						loginAttemptsData = null;
						loginAttemptsTbl = null;

						return 405; // Invalid password
					}
					else
					{
						// Reset time does not exceed. Invalid attempts need to
						// be incremented.

						if( invalidAttemptCount >= ( lockoutPolicyData.invalidLoginAttempts_ - 2 ) )
						{
							System.out.println( "Invalid attempts ( " +invalidAttemptCount+ " ) is greater than conf.exists" +
									" ( "+lockoutPolicyData.invalidLoginAttempts_ );
							currentTimestamp = new Timestamp( System.currentTimeMillis( ) );
							updateLoginAttempts(
							        loginData,
							        invalidAttemptCount + 1,
							        new Timestamp( currentTimestamp.getTime( ) + lockoutSec ),
							        currentTimestamp );
							
							//To check whether the account need to be blocked or not
							//If the flag is checked, the account will be blocked.
							//Admin only can unlock the account
							if( adminUnlockFlag == 1 )
							{
								blockAccount( loginData );
							}
						}
						else
						{
							currentTimestamp = new Timestamp( System.currentTimeMillis( ) );
							updateLoginAttempts( loginData, invalidAttemptCount + 1,
							        currentTimestamp, currentTimestamp );
						}

						userLogin = null;
						profileData = null;
						loginAttemptsData = null;
						loginAttemptsTbl = null;

						return 405; // Invalid password
					}
				}

				// Lock out time exceeds;
				else if( System.currentTimeMillis( ) > lockoutTs.getTime( ) + lockoutSec )
				{
					currentTimestamp = new Timestamp( System.currentTimeMillis( ) );
					updateLoginAttempts( loginData, 1, currentTimestamp, currentTimestamp );

					userLogin = null;
					profileData = null;
					loginAttemptsData = null;
					loginAttemptsTbl = null;

					return 405; // invalid password
				}

				//Account is locked
				else
				{
					userLogin = null;
					profileData = null;
					loginAttemptsData = null;
					loginAttemptsTbl = null;

					return 406; // Account locked
				}
			}
			
			//No record for the user logged in exists.  Need to insert new record.
			else
			{
				insertLoginAttempts( loginData );

				userLogin = null;
				profileData = null;
				loginAttemptsData = null;
				loginAttemptsTbl = null;

				return 405; // invalid password
			}
		}
	}

	/*
	 * Method : isLockExistsForCorrectCredentials( ) : private
	 * 
	 * Input : AccountPolicyData obj, LoginData obj, LoginError obj
	 * 
	 * Return : Boolean
	 * 
	 * Purpose: It is used to check whether there is an account lock after
	 * entering the correct login credentials. If lock exists, it returns true.
	 */

	private Boolean isLockExists( AccountPolicyData accPolicyData, LoginData loginData )
	{
		LoginAttemptsTable loginAttemptsTbl = new LoginAttemptsTable( );
		LoginAttemptsData loginAttemptsData = new LoginAttemptsData( );

		//LockoutPolicyData lockoutPolicyData = new LockoutPolicyData( );
		//lockoutPolicyData = accPolicyData.lockoutPolicyData_;
		
		//int lockoutSec = lockoutPolicyData.lockoutDurationMin_ * 60 * 10 * 100;

		int result = loginAttemptsTbl.getLoginAttempts( loginData.emailid_,
		        loginAttemptsData );

		//To check whether lock exists or not
		if( result == 0 )
		{
			if( System.currentTimeMillis( ) < ( loginAttemptsData.lockoutTimestamp_
			        .getTime( ) ) )
			{
				loginAttemptsData = null;
				loginAttemptsTbl = null;

				return true; // Lock exists
			}
			else
			{
				loginAttemptsData = null;
				loginAttemptsTbl = null;

				return false; // No lock
			}
		}

		else
		{
			loginAttemptsData = null;
			loginAttemptsTbl = null;

			return false; // First attempt
		}
	}

	/*
	 * Method : checkUserStatus( ) : private
	 * 
	 * Input : AccountPolicyData obj, LoginData obj, LoginError obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check whether the status of the user is active. It
	 * returns appropriate response codes for various user status
	 */

	private int checkUserStatus( AccountPolicyData accPolicyData, LoginData loginData,
	        UserProfileData profileData )
	{

		if( profileData.userStatus_.equals( UserStatus.status.ACTIVE.getValue( ) ) )
		{
			return 400; // Success
		}

		else if( profileData.userStatus_.equals( UserStatus.status.PENDING.getValue( ) ) )
		{
			return 401; // Activation pending
		}

		else
		{
			return 402; // Account blocked
		}

	}

	/*
	 * Method : isCompanyActive( ) : private
	 * 
	 * Input : AccountPolicyData obj, LoginData obj, LoginError obj
	 * 
	 * Return : Boolean
	 * 
	 * Purpose: It is used to check whether the status of the company is active.
	 */

	private boolean isCompanyActive( AccountPolicyData accPolicyData, LoginData loginData,
	        UserProfileData profileData )
	{
		CompanyRegnTable companyRegnTable = new CompanyRegnTable( );

		CompanyRegnKey companyKey = new CompanyRegnKey( );

		companyKey = profileData.companyRegnKey_;

		StringBuilder userStatus = new StringBuilder( );

		int regnStatusResult = companyRegnTable.getCompanyStatus( companyKey, userStatus );

		if( regnStatusResult == 0 )
		{
			if( userStatus.toString( ).equals( CompanyStatus.status.ACTIVE.getValue( ) ) )
			{
				return true;
			}
			else
			{
				return false; // Company Status is not active.
			}
		}

		else
		{
			return false; // Company status not exists.
		}
	}

	/*
	 * Method : updateLoginAttempts( ) : private
	 * 
	 * Input : LoginData obj, int, Timestamp, Timestamp
	 * 
	 * Return : void
	 * 
	 * Purpose: It is used to update the login attempts data for given inputs
	 */

	private void updateLoginAttempts( LoginData loginData, int invalidAttempts,
	        Timestamp lockoutTimestamp, Timestamp attemptedTimestamp )
	{
		LoginAttemptsData loginAttemptsData = new LoginAttemptsData( );

		LoginAttemptsTable loginAttemptsTbl = new LoginAttemptsTable( );

		loginAttemptsData.email_ = loginData.emailid_;

		loginAttemptsData.invalidAttemptsCount_ = invalidAttempts;

		loginAttemptsData.attemptedTimestamp_ = attemptedTimestamp;

		loginAttemptsData.lockoutTimestamp_ = lockoutTimestamp;

		loginAttemptsTbl.updateLoginAttempts( loginAttemptsData );

		loginAttemptsData = null;

		loginAttemptsTbl = null;

	}

	/*
	 * Method : insertLoginAttempts( )
	 * 
	 * Input : LoginData obj
	 * 
	 * Return : void
	 * 
	 * Purpose: It is used to insert the login attempts data for given inputs
	 */

	private void insertLoginAttempts( LoginData loginData )
	{
		LoginAttemptsData loginAttemptsData = new LoginAttemptsData( );

		LoginAttemptsTable loginAttemptsTbl = new LoginAttemptsTable( );

		loginAttemptsData.email_ = loginData.emailid_;

		loginAttemptsData.invalidAttemptsCount_ = 1;

		loginAttemptsData.attemptedTimestamp_ = new Timestamp( System.currentTimeMillis( ) );

		loginAttemptsData.lockoutTimestamp_ = loginAttemptsData.attemptedTimestamp_;

		loginAttemptsTbl.insertLoginAttempts( loginAttemptsData );

		loginAttemptsData = null;

		loginAttemptsTbl = null;
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
	
	/*
	 * Method: blockAccount
	 * 
	 * Input: LoginData
	 * 
	 * Return: void
	 * 
	 * Purpose: This method blocks the user if 'Admin should unlock' 
	 * option is set in the Account policies.
	 * 
	 */
	
	private void blockAccount( LoginData loginData )
	{
		UserProfileTable profileTable = new UserProfileTable( );
		
		UserProfileKey profileKey = new UserProfileKey( );
		
		profileKey.email_ = loginData.emailid_;
		
		int result = profileTable.updateStatus( profileKey, UserStatus.status.BLOCKED.getValue( ) );
		
		if( result != 0 )
		{
			ErrorLogger.instance( ).logMsg( "Error::LockoutPolicy.blockAccount() - Unable to change user status to Blocked" );
		}
		
		profileTable = null; profileKey = null;
	}
	
	/*
	 * Method: checkAccountExpire
	 * 
	 * Input: Timestamp accountCreatedTs, LoginData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to check whether the currently logging in user's 
	 * account is valid or expires
	 */
	
	private int checkAccountExpire( LoginData loginData )
	{	
		UserProfileTable profileTable = new UserProfileTable( );
		
		UserProfileData profileData = new UserProfileData( );
		
		profileData.userProfileKey_ = new UserProfileKey( );
		
		profileData.userProfileKey_.email_ = loginData.emailid_;
		
		profileData.emailId_	= loginData.emailid_;
		
		int loginResult = profileTable.getCreatedDate( profileData );
		
		profileTable = null;
		
		if( loginResult == 0 )
		{
    		UserAccountPoliciesTable userAccPolicyTbl = new UserAccountPoliciesTable( );
    	    		
    		int result = userAccPolicyTbl.getUserPolicies( profileData.userProfileKey_, profileData );
    		
    		userAccPolicyTbl = null;
    		
    		if( result == -2 || result == -3)
    		{
    			profileData = null;
    			
    			return 415;  // Unable to fetch user account policies
    		}
    		
    		if( result == -1 )
    		{
    			profileData = null;
    			
    			return 400; //No record exists. No need to check for admin
    		}
    		
    		int diffInDays = (int)( ( ( new java.util.Date( ) ).getTime( ) 
    						- profileData.createdDate_.getTime( ) ) / ( 1000 * 60 * 60 * 24 ) );
    		
    		if( profileData.accountExpireDays_ < diffInDays )
    		{	
    			profileData = null;
    			
    			return 413; // Account expires
    		}
    		else 
    		{
    			profileData = null;
    			
    			return 400; // Account is active
			}
		}
		else 
		{
			profileData = null;
			
			return 416; // Unable to fetch the profile created date from user profile table
		}

	}

}
