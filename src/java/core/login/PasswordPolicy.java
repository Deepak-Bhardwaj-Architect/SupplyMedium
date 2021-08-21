package core.login;

import core.regn.LoginData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.regn.UserLoginTable;
import db.usermgmt.UserAccountPoliciesTable;

/*
 * Class  : PasswordPolicy
 * 
 * Purpose: It is used to check the user login credentials with company's password policies 
 * 
 */

public class PasswordPolicy implements AccountPolicy
{

	/*
	 * Method : enforce( )
	 * 
	 * Input : AccountPolicyData obj, LoginData obj, LoginError obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to enforce all the the password policies for the
	 * entered login credentials and return appropriate response code. Based on
	 * the response code the credentials will be validated
	 */

	public int enforce( AccountPolicyData accPolicyData, LoginData loginData )
	{
		UserLoginTable userLoginTbl = new UserLoginTable( );

		LoginData refLoginData = new LoginData( );
		
		int loginResult = userLoginTbl.getPasswordTimestampForEmail( loginData.emailid_,
		        refLoginData );

		if( loginResult == 0 )
		{
			//To check whether the password reaches is expired
			
			int diffInDays = (int)( ( ( new java.util.Date( ) ).getTime( ) - refLoginData.createdTimestamp_
			        .getTime( ) ) / ( 1000 * 60 * 60 * 24 ) );

			// If password created date is greater than Maximum password age

			PasswordPolicyData passPolicyData = new PasswordPolicyData( );
			
			passPolicyData	= accPolicyData.passPolicyData_;
			
			if( diffInDays > passPolicyData.passwordAgeMaxDays_ )
			{
				userLoginTbl = null;
				refLoginData = null;
				passPolicyData = null;
				
			   /* password expired. User must be redirected to 
				*reset password screen on next login*/
				return 403; 
			}
			else
			{
				userLoginTbl = null;
				refLoginData = null;
				passPolicyData = null;
				
				loginResult = checkResetFlag( loginData );
				
				return loginResult; // Success
			}
		}
		
		//Entered email is not valid
		else
		{
			userLoginTbl = null;
			refLoginData = null;
			
			return 407; // Invalid email
		}
	}
	
	/*
	 * Method: checkResetFlag
	 * 
	 * Input: LoginData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to check whether the currently logging in user's 
	 * need to reset is password at next login or not
	 */
	
	private int checkResetFlag( LoginData loginData )
	{
		UserAccountPoliciesTable userAccPolicyTbl = new UserAccountPoliciesTable( );
		
		UserProfileKey profileKey = new UserProfileKey( );
		
		profileKey.email_ = loginData.emailid_;
		
		UserProfileData profileData = new UserProfileData( );
		
		int result = userAccPolicyTbl.getUserPolicies( profileKey, profileData );
		
		userAccPolicyTbl = null;
		
		if( result == -2 || result == -3 )
		{
			profileKey = null;
			
			profileData = null;
			
			return 415;  // Unable to fetch user account policies
		}
		if( result == -1 )
		{
			profileKey = null;
			
			profileData = null;
			
			return 400; //No record exists. No need to check for admin
		}
		
	   /* If admin forces a user to reset his password at next login, then this flag
		* will be set. Based on this flag, the user will be redirected to successful
		* logged in screen or changepassword screen
		*/
		if( profileData.changePasswordFlag_ == 0 )
		{	
			profileKey = null;
			
			profileData = null;
			
			return 400; // Success
		}
		else 
		{
			profileKey = null;
			
			profileData = null;
			
			return 414; // User must change password at next login
		}
	}
}
