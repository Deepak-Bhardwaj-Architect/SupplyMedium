package ctrl.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import utils.ErrorLogger;
import utils.PasswordStore;

import core.login.LoginManager;
import core.login.PasswordLoginPolicyData;
import core.login.SessionData;
import core.regn.CompanyRegnKey;
import core.regn.LoginData;

/*
 * Class  : LoginController
 * 
 * Purpose: It is used to check for login credentials, request new password and reset password
 * 
 */

public class LoginController
{

	/*
	 * Method : isValidLogin( )
	 * 
	 * Input : HttpServletRequest request
	 * 
	 * Return : String
	 * 
	 * Purpose: It gets the HttpServletRequest and sends the LoginData object to
	 * LoginManager. It response, it gets LoginError object. Through this object
	 * the JSON string is generated for success and failed cases.
	 */

	public int isValidLogin( HttpServletRequest request, SessionData sessionData )
	{

		// SessionData sessionData = new SessionData( );
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errMsg = "Info::LoginController.isValidLogin() - Request in process for user <"
						+request.getParameter( "email" ) + ">";
		
		errLogger.logMsg(errMsg);
		
		return LoginManager.instance( ).isValidLogin( parseLoginData( request ),
		        sessionData );

	}

	/*
	 * Method : passwordResetRequest( )
	 * 
	 * Input : HttpServletRequest request
	 * 
	 * Return : int
	 * 
	 * Purpose: It gets the HttpServletRequest and sends password reset request
	 * to LoginManagert
	 */

	public int passwordResetRequest( HttpServletRequest request )
	{
		LoginData loginData = new LoginData( );

		loginData.emailid_ = request.getParameter( "email" );

		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		String errMsg = "Info::LoginController.passwordResetRequest() - Forgort password request in process for user <"
				+ loginData.emailid_ + ">";
		
		errorLogger.logMsg( errMsg );
		
		
		int passwordResetResult = LoginManager.instance( )
		        .sendPasswordResetLink( loginData );

		return passwordResetResult;
	}

	/*
	 * Method : resetPassword( )
	 * 
	 * Input : HttpServletRequest request
	 * 
	 * Return : int
	 * 
	 * Purpose: It gets the HttpServletRequest and sends the password and uuid
	 * to login manager inorder to update the new password
	 */

	public int resetPassword( HttpServletRequest request )
	{
		String password = request.getParameter( "password" );

		String uuid = request.getParameter( "uuid" );

		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		String errMsg = "Info::LoginController.resetPassword() - reset password request in process ";
		
		errorLogger.logMsg( errMsg );
		
		int resetPasswordResult = LoginManager.instance( ).resetPassword( password, uuid );

		return resetPasswordResult;
	}

	/*
	 * Method : getPasswordPolicies( )
	 * 
	 * Input : HttpServletRequest request
	 * 
	 * Return : String
	 * 
	 * Purpose: It is used to get password policies from login manager
	 */

	public String getPassswordPolicies( HttpServletRequest request )
	{

		//AccountPolicyData accPolicyData = new AccountPolicyData( );
		PasswordLoginPolicyData passLoginPolicyData = new PasswordLoginPolicyData( );
		
		String requestType =request.getParameter( "RequestType" );
		
		Map<String, String> passwordPolicyMap = new HashMap<String, String>( );

		String jsonStr = "";
		
		int policyResult = 0;
		
		// Fetch password policies for reset password
		if( requestType.equals( "ResetPassword" ) )  
		{
			String uuid = request.getParameter( "uuid" );
			
			policyResult = LoginManager.instance( )
			        .getPasswordPolicies( uuid, passLoginPolicyData );
		}
		// Fetch password policies for user signup
		else if( requestType.equals( "UserSignup" ) )
		{
			String companyKeyStr =  request.getParameter( "CompanyKey" );
			
			CompanyRegnKey companyRegnKey = new CompanyRegnKey( );
			
			companyRegnKey.companyPhoneNo_ = companyKeyStr;
			
			policyResult = LoginManager.instance( )
			        .getPasswordPolicies( companyRegnKey, passLoginPolicyData );
		}

		if( policyResult == 0 )
		{
			passwordPolicyMap.put( "result", "success" );

			passwordPolicyMap.put( "upperLowerCharFlag",
			        String.valueOf( passLoginPolicyData.upperLowerFlag_ ) );

			passwordPolicyMap.put( "numericFlag",
			        String.valueOf( passLoginPolicyData.numericFlag_ ) );

			passwordPolicyMap.put( "splCharFlag",
			        String.valueOf( passLoginPolicyData.specialCharactersFlag_ ) );

			passwordPolicyMap.put( "minChar",
			        String.valueOf( passLoginPolicyData.passwordLength_ ) );

			jsonStr = new Gson( ).toJson( passwordPolicyMap );

			return jsonStr;
		}
		else
		{
			passwordPolicyMap.put( "result", "failed" );

			jsonStr = new Gson( ).toJson( passwordPolicyMap );

			return jsonStr;
		}
	}

	/*
	 * Method : parseLoginData( ) : private
	 * 
	 * Input : HttpServletRequest request
	 * 
	 * Return : LoginData
	 * 
	 * Purpose: It parses the HttpServletRequest to LoginData
	 */

	private LoginData parseLoginData( HttpServletRequest request )
	{
		LoginData loginData = new LoginData( );

		loginData.emailid_ = request.getParameter( "email" );

		PasswordStore passStore = new PasswordStore( );

		loginData.password_ = passStore.getEncryptedValue( request
		        .getParameter( "password" ) );

		return loginData;
	}
	
	/**/
	
	public int updateStatus( LoginData loginData )
    {
		LoginManager loginMgr = LoginManager.instance( );
		
		return loginMgr.updateStatus( loginData );
		
    }

}
