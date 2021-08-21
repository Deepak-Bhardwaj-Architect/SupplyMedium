package core.login;

import java.sql.Timestamp;

import java.util.UUID;

import utils.ErrorLogger;
import utils.HTMLMailComposer;
import utils.LoginStatus;
import utils.Mailer;
import utils.PasswordStore;
import utils.PathBuilder;
import utils.StringHolder;

import core.privilege.AllPrivilegesData;
import core.privilege.PrivilegesComposer;
import core.regn.CompanyRegnKey;
import core.regn.LoginData;
import core.regn.RegnData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.login.LoginStatusTable;
import db.login.UserkeyUuidMapperTable;
import db.regn.CompanyRegnTable;
import db.regn.UserLoginTable;
import db.regn.UserProfileTable;
import utils.ErrorMaster;

/*
 * Class  : LoginManager
 * 
 * Purpose: It manages operations like user login, forget password, reset password, etc.
 * 
 */

public class LoginManager
{

	//Map<String, AccountPoliciesEnforcer> policyEnforcerMap_;

	private static LoginManager          loginManager_ = null;
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : instance()
	 * 
	 * Input : none
	 * 
	 * Return : LoginManager obj
	 * 
	 * Purpose: It is used to implement the single-ton for LoginManager
	 */

	public static LoginManager instance( )
	{
		if( loginManager_ == null )
		{
			loginManager_ = new LoginManager( );
		}
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		return loginManager_;
	}

	/*
	 * Method : enforce( ) -- constructor
	 * 
	 * Input : None
	 * 
	 * Return :
	 * 
	 * Purpose: It is used to initialize the PolicyEnforcerIntializer and
	 * creates PolicyEnforcerMap. The PolicyEnforcerMap consists of
	 * AccountPolicyData and list<AccountPolicy> of a company in which the
	 * user's login credentials belongs to.
	 */

	private LoginManager()
	{
//		PolicyEnforcerInitializer policyEnforceInitializer = new PolicyEnforcerInitializer( );
//
//		policyEnforcerMap_ = new HashMap<String, AccountPoliciesEnforcer>( );
//
//		policyEnforceInitializer.init( );
//		
//		policyEnforceInitializer.createPolicyEnforcerMap( policyEnforcerMap_ );
	}

	/*
	 * Method : isValidLogin( )
	 * 
	 * Input : LoginData obj, SessionData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check whether the login is valid or not. If valid
	 * login, then the SessionData will be set. Otherwise, the appropriate
	 * response code will be logged in the LoginError obj.
	 */

	public int isValidLogin( LoginData loginData, SessionData sessionData )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		

		UserProfileKey userKey = new UserProfileKey( );
		
		userKey.email_ = loginData.emailid_;
		
		UserLoginTable userLoginTbl = new UserLoginTable( );

		//int result = userLoginTbl.checkLoginCredentials( loginData );
		
		int result = userLoginTbl.isEmailExists( userKey );

		if( result != 0 )
		{
			userLoginTbl = null;

			return 411; // Login credentials are not valid
		}

		UserProfileTable userProfileTbl = new UserProfileTable( );
		
		CompanyRegnKey regnKey = new CompanyRegnKey( );

		int regn = userProfileTbl.getRegnKey( userKey, regnKey );

		if( regn != 0 )
		{
			userProfileTbl = null;
			userKey = null;
			regnKey = null;
			userLoginTbl = null;

			return 410; // invalid email
		}

		UserProfileData profileData = new UserProfileData( );

		int profileResult = userProfileTbl.getUserProfile( userKey, profileData );

		int response = 0;
		
		PolicyEnforcerInitializer policyEnforcer = new PolicyEnforcerInitializer( );
		
		AccountPoliciesEnforcer enforcer = new AccountPoliciesEnforcer( );

		int resultVal = policyEnforcer.createEnforcer( regnKey, enforcer );
		
		if( resultVal == -1)
		{
			userProfileTbl = null;
			userKey = null;
			regnKey = null;
			profileData = null;
			userLoginTbl = null;
			
			errorLogger.logMsg( "Error::LoginManager:isValidLogin - Failed to get enforcer object" );
			
			return 412;
		}
		
		for( AccountPolicy accPolicy : enforcer.accPolicyList_ )
		{
			response = accPolicy.enforce( enforcer.accPolicyData_, loginData );

			if( response != 400 ) break;
		}

		if( response == 400 )
		{
			//To Update login status
			loginData.loginStatus_ = LoginStatus.loginStatus.CONNECTED.getValue( );
			updateStatus( loginData );
			
			// set session
			if( profileResult == 0 )
			{
				RegnData regnData = new RegnData( );
				CompanyRegnTable regnTbl = new CompanyRegnTable( );
				int value=regnTbl.getCompany( regnKey, regnData );
				
				if(value == 0) // Company details fetched successfully.
				{
					sessionData.companyName_ = regnData.companyName_;
				}
				
				sessionData.username_ = userKey.email_;
				sessionData.password_ = loginData.password_;
				sessionData.firstName_ = profileData.firstName_;

				sessionData.lastName_ = profileData.lastName_;
				sessionData.userType_ = profileData.userType_;
				sessionData.companyRegnKeyStr_ = regnKey.companyPhoneNo_;
				
				sessionData.companyType_ = regnData.companyType_;
				
				PathBuilder pathBuilder = new PathBuilder( );
				
				errorMaster_.insert( "regnLogo Path ********************************************="+regnData.logo_ );
				                        //System.out.println("regnData.logo_============"+regnData.logo_);
				if( regnData.logo_ != null && !regnData.logo_.equals( "" ) )
				{
					
					StringHolder companyLogoUrl = new StringHolder( );
					
					pathBuilder.getWebURLFromRelativepath( regnData.logo_, companyLogoUrl );
					
					sessionData.companyLogoUrl_= regnData.logo_;//companyLogoUrl.value;
				}
				else
				{
					sessionData.companyLogoUrl_ = regnData.logo_;
				}
				
				
				errorMaster_.insert( "profilePath Path ********************************************="+profileData.profilePicture_+"*****************" );
				
				if( profileData.profilePicture_ != null && !profileData.profilePicture_.equals( "" ) )
				{
					StringHolder userImageURL = new StringHolder( );
					
					pathBuilder.getWebURLFromRelativepath( profileData.profilePicture_, userImageURL );
					
					sessionData.userImageURL_ = profileData.profilePicture_;//userImageURL.value;
				}
				else
				{
					sessionData.userImageURL_ = profileData.profilePicture_;
				}
				
				pathBuilder = null;
				
				

				sessionData.loginTimeStamp_ = new Timestamp( System.currentTimeMillis( ) );
				
				LockoutPolicyData lockoutPolicyData = new LockoutPolicyData( );
				lockoutPolicyData = enforcer.accPolicyData_.lockoutPolicyData_;
				
				sessionData.sessionExpireMin_ = lockoutPolicyData.expireSessionMin_;
				
				
				
				
				AllPrivilegesData allPrivilegesData = new AllPrivilegesData( );
				
				sessionData.allPrivilegesData_ = allPrivilegesData;
				
				allPrivilegesData = null;
				
				PrivilegesComposer privilegesComposer =new PrivilegesComposer( );
				
				privilegesComposer.getAllPrivileges( userKey, sessionData.allPrivilegesData_ );
				
				
				
				lockoutPolicyData = null;
			}
		}
		else if( response == 403 || response == 414 )
		{
			if( profileResult == 0 )
			{
				sessionData.username_ = userKey.email_;
				sessionData.companyRegnKeyStr_ = regnKey.companyPhoneNo_;

				sessionData.loginTimeStamp_ = new Timestamp( System.currentTimeMillis( ) );
				
				LockoutPolicyData lockoutPolicyData = new LockoutPolicyData( );
				lockoutPolicyData = enforcer.accPolicyData_.lockoutPolicyData_;
				
				sessionData.sessionExpireMin_ = lockoutPolicyData.expireSessionMin_;
				lockoutPolicyData = null;
			}
		}

		userProfileTbl = null;
		userKey = null;
		regnKey = null;
		profileData = null;
		userLoginTbl = null;

		return response;
	}

	/*
	 * Method : sendPasswordResetLink( )
	 * 
	 * Input : LoginData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to send password reset link to the user who requests
	 * new password. This checks whether the email is valid. If valid, the
	 * activation link will be sent through email; and userkey_uuid_mapper table
	 * will be updated with newly generated uuid. Else, valid error code will be
	 * returned.
	 */

	public int sendPasswordResetLink( LoginData loginData )
	{
		int emailValidResult = 0;

		UserLoginTable userLoginTbl = new UserLoginTable( );

		UserProfileKey userKey = new UserProfileKey( );

		userKey.email_ = loginData.emailid_;

		emailValidResult = userLoginTbl.isEmailExists( userKey );

		if( emailValidResult == 0 ) // email exists
		{
			int insertUuidResult = 0;

			UserkeyUuidMapperData mapper = new UserkeyUuidMapperData( );

			mapper.userRelKey_ = loginData.emailid_;

			/*
			 * This call creates the random UUID number which will be unique
			 * everytime when it is called. This number is used to store as uuid
			 * for mapper table
			 */

			mapper.uuid_ = UUID.randomUUID( ).toString( );

			UserkeyUuidMapperTable mapperTable = new UserkeyUuidMapperTable( );

			if( mapperTable.isMapperRecExists( userKey ) == -1 )
			{
				insertUuidResult = mapperTable.insert( mapper );
			}
			else
			{
				insertUuidResult = mapperTable.update( mapper );
			}

			if( insertUuidResult == 0 ) // insert/update success
			{
				UserProfileData userProfileData = new UserProfileData( );
				
				UserProfileTable userProfileTable = new UserProfileTable( );
				
				userProfileTable.getUserProfile( userKey, userProfileData );
				
				userProfileTable = null;
				
				emailResetPasswordLink( mapper.uuid_, mapper.userRelKey_,userProfileData.firstName_ );

				userLoginTbl = null;
				userKey = null;
				mapper = null;
				mapperTable = null;

				return 500; // Success
			}
			else
			{
				userLoginTbl = null;
				userKey = null;
				mapper = null;
				mapperTable = null;

				return 502; // password reset error
			}

		}
		else
		{
			userLoginTbl = null;
			userKey = null;

			return 501; // invalid email
		}
	}

	/*
	 * Method : resetPassword( )
	 * 
	 * Input : String password, String uuid
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the old password with new password, only if
	 * the email for the given uuid exists in the DB. Else, the activation link
	 * is not valid. The corresponding error code will be returned.
	 */

	public int resetPassword( String password, String uuid )
	{

		int emailExistResult = 0;

		UserkeyUuidMapperTable mapperTable = new UserkeyUuidMapperTable( );

		UserkeyUuidMapperData mapperData = new UserkeyUuidMapperData( );

		emailExistResult = mapperTable.getEmailId( uuid, mapperData );

		if( emailExistResult == 0 )
		{
			LoginData loginData = new LoginData( );

			loginData.emailid_ = mapperData.userRelKey_;

			PasswordStore pStore = new PasswordStore( );

			loginData.password_ = pStore.getEncryptedValue( password );

			UserLoginTable userLoginTbl = new UserLoginTable( );

			int updatePasswordResult = userLoginTbl.resetPassword( loginData );

			if( updatePasswordResult == 0 )
			{
				mapperTable = null;
				mapperData = null;
				loginData = null;
				userLoginTbl = null;

				return 600; // Success
			}

			else
			{
				mapperTable = null;
				mapperData = null;
				loginData = null;
				userLoginTbl = null;

				return 601; // The link is not valid
			}

		}
		else
		{
			mapperTable = null;
			mapperData = null;

			return 601; // The link is not valid
		}

	}

	/*
	 * Method : getPasswordPolicies( )
	 * 
	 * Input : String uuid, AccountPolicyData accPolicyData
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get password policies for given uuid, to implement
	 * password policies on forgot password view, registration view such that
	 * user enters password according to these policies
	 */

	public int getPasswordPolicies( String uuid, PasswordLoginPolicyData passLoginPolicyData )
	{
		UserkeyUuidMapperTable mapperTable = new UserkeyUuidMapperTable( );
		UserkeyUuidMapperData mapperData = new UserkeyUuidMapperData( );

		int emailExistResult = 0;

		emailExistResult = mapperTable.getEmailId( uuid, mapperData );

		errorMaster_.insert( "Getting AccountPolicies.." );
		
		if( emailExistResult == 0 )
		{
			UserProfileTable userProfileTbl = new UserProfileTable( );

			UserProfileKey userKey = new UserProfileKey( );

			CompanyRegnKey regnKey = new CompanyRegnKey( );

			/*
			 * Inorder to get account policy data from enforcer object, the
			 * company key is required. So the company key is need to be
			 * obtained first using user key. Here, mapperdata contains
			 * userrelkey through which the user key is obtained
			 */
			userKey.email_ = mapperData.userRelKey_;

			int val = userProfileTbl.getRegnKey( userKey, regnKey );

			if( val == 0 )
			{
				
				int result = getPasswordPolicies( regnKey, passLoginPolicyData );
				
				mapperData = null;
				mapperTable = null;
				
				return result;
				
			}
			else
			{
				mapperData = null;
				mapperTable = null;
				userProfileTbl = null;
				userKey = null;
				regnKey = null;

				return -2; // No company exists for given user key
			}
		}
		else
		{
			mapperData = null;
			mapperTable = null;

			return -1; // No email id exists
		}

	}
	
	/*
	 * Method : getPasswordPolicies( )
	 * 
	 * Input : regnKey, AccountPolicyData accPolicyData
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get password policies for given regnkey, to implement
	 * password policies on registration view such that
	 * user enters password according to these policies
	 */

	public int getPasswordPolicies( CompanyRegnKey regnKey, PasswordLoginPolicyData passLoginPolicyData )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		PolicyEnforcerInitializer policyEnforcer = new PolicyEnforcerInitializer( );
		
		AccountPoliciesEnforcer enforcer = new AccountPoliciesEnforcer( );
		
		int resultVal = policyEnforcer.createEnforcer( regnKey, enforcer );
		
		if( resultVal == -1 )
		{
			errorLogger.logMsg( "Error::LoginManager:getPasswordPolicies - Failed to get enforcer object " );
			return -3; // enforcer object null for regnkey
		}

		PasswordLoginPolicyData data = new PasswordLoginPolicyData( );
		data = enforcer.accPolicyData_.passLoginPolicyData_;
		
		PasswordPolicyData passPolicyData = new PasswordPolicyData( );
		passPolicyData = enforcer.accPolicyData_.passPolicyData_;
		
		errorMaster_.insert( "PasswordLoginPolicyData..."+data );
		
		if( data.passwordComplexityFlag_ == 1 )
		{
			passLoginPolicyData.upperLowerFlag_ = data.upperLowerFlag_;
			passLoginPolicyData.numericFlag_ = data.numericFlag_;
			passLoginPolicyData.specialCharactersFlag_ = data.specialCharactersFlag_;
			
			passLoginPolicyData.passwordLength_ = passPolicyData.passwordLength_;
		}
		else
		{
			passLoginPolicyData.upperLowerFlag_ = 0;
			passLoginPolicyData.numericFlag_ = 0;
			passLoginPolicyData.specialCharactersFlag_ = 0;
			
			passLoginPolicyData.passwordLength_ = passPolicyData.passwordLength_;
		}
		
		return 0;
	}

	/*
	 * Method : emailResetPasswordLink( ) : private
	 * 
	 * Input : String uuid, String emailId and firstname
	 * 
	 * Return : void
	 * 
	 * Purpose: It is used to generate password activation link and send email
	 * to the user who requests new password.
	 */

	private void emailResetPasswordLink( String uuid, String emailId,String firstName )
	{

		Mailer mailerObj = new Mailer( );

		String [ ] to = { emailId };

		String sub = "SupplyMedium Password Reset Link";

		//String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );
		
		HTMLMailComposer composer = new HTMLMailComposer( );
		
		String message = composer.composeResetPasswordtMail( uuid, emailId, firstName );
		
		composer = null;

		//String message = path + "/Views/Registration/jsp/passwordreset.jsp?key=" + uuid;

		mailerObj.composeAndSendMail( to, sub, message );

		mailerObj = null;

		errorMaster_.insert( "Mail send successfully" );
	}

	/*
	 * Method : updateLoginStatus( ) : private
	 * 
	 * Input : LoginData
	 * 
	 * Return : void
	 * 
	 * Purpose: It is used to update the login status of the user
	 * 
	 */
	
	private int updateLoginStatus( LoginData loginData )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		LoginStatusTable statusTbl = new LoginStatusTable( );
		
		UserProfileKey key = new UserProfileKey( );
		
		key.email_	= loginData.emailid_;
		
		int statusResult = 0;
		
		statusResult = statusTbl.isEmailExists( key );
		
		if( statusResult == 0 )
		{
			//Email exists already, Need to update the status
			
			int updateStatus = 0;
			
			updateStatus = statusTbl.update( loginData );
			
			if( updateStatus != 0 )
			{
				errorLogger.logMsg( "Error::LoginManager:updateLoginStatus - Failed to update existing login status" );
				statusTbl = null;
				key = null;
				return -2;
			}
		}
		else
		{
			//Email does not exist already, need to insert the new status record
			int insertStatus = 0;
			
			insertStatus = statusTbl.insert( loginData );
			
			if( insertStatus != 0 )
			{
				errorLogger.logMsg( "Error::LoginManager:updateLoginStatus - Failed to insert login status" );
				statusTbl = null;
				key = null;
				return -1;
			}
		}
		statusTbl = null;
		key = null;
		return 0;
	}
	
	/**/
	
	public int updateStatus( LoginData loginData)
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		int loginStatusVal = updateLoginStatus( loginData );
		if( loginStatusVal != 0)
		{
			errLogger.logMsg( "Error::LoginManager:updateStatus - Failed change login status" );
		}
		return loginStatusVal;
	}
	
}
