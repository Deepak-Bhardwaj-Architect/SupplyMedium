package servlet.usermgmt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

import com.google.gson.Gson;

import core.login.AccountPolicyData;
import core.login.SessionData;


import ctrl.usermgmt.AccountPoliciesController;
import utils.ErrorMaster;


/**
 * Servlet implementation class AccountPolicyServlet
 */
@WebServlet("/AccountPolicyServlet")
public class AccountPolicyServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
        private static ErrorMaster errorMaster_ = null;




	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountPolicyServlet()
	{
		super( );if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet( HttpServletRequest request,
	        HttpServletResponse response ) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	/*Request type strings
	 * 
	 *  1. RevisePolicies
	 *  2. GetPolicies
	 */
	
	
	protected void doPost( HttpServletRequest request,
	        HttpServletResponse response ) throws ServletException, IOException
	{
		// TODO Auto-generated method stub	
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
			String msg = "Info::AccountPolicyServlet.doPost() -  Request for "
						  + request.getParameter( "RequestType" ) + " for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			AccountPoliciesController accPolicyCtrl = new AccountPoliciesController( );
			
			AccountPolicyData accPolicyData = new AccountPolicyData( );
			
			int responseCode = accPolicyCtrl.processAccPolicies( request, accPolicyData );
			
			String jsonStr = composeResponseJSON( responseCode,accPolicyData );

			response.getWriter( ).write( jsonStr );
			
			accPolicyCtrl = null;
        }
        catch( Exception e )
        {
        	String msg = "Exception::AccountPolicyServlet.doPost() - "+e+" \r\n\n\n";

			errLogger.logMsg( msg );
			
			Map<String, String> jsonMap = new HashMap<String, String>( );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			jsonMap = null;
			
			response.getWriter( ).write( jsonStr );
        }
	}
	
	/*
	 * Method : composeResponseJSON( ) 
	 * 
	 * Input : int responseCode, accountpolicydata object
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the response in to json
	 * string
	 */

	private String composeResponseJSON(
	        int responseCode, AccountPolicyData accountPolicyData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String jsonStr = "";
		
		Map<String, String> jsonMap = new HashMap<String, String>( );
		
		//660 - Update success
		
		//670 - Fetch policies success
		
		if( responseCode == 660 || responseCode == 670  )
		{
			
			jsonMap.put( "result", "success" );
			
			String msg = "Info::AccountPolicyServlet.doPost() -  Request successful - Response code - "+responseCode+"\r\n\n\n";
			errLogger.logMsg( msg );
			
			if( responseCode == 660 ) 
			{
				
				
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
				
				jsonMap.put( "message", responseString );
			}
			else 
			{	
				jsonMap.put( "numeric", String.valueOf( accountPolicyData.passLoginPolicyData_.numericFlag_ ));
				jsonMap.put( "pass_comp", String.valueOf( accountPolicyData.passLoginPolicyData_.passwordComplexityFlag_ ));
				jsonMap.put( "pass_length", String.valueOf( accountPolicyData.passPolicyData_.passwordLength_ ));
				jsonMap.put( "non_alpha", String.valueOf( accountPolicyData.passLoginPolicyData_.specialCharactersFlag_ ));
				jsonMap.put( "upperlower", String.valueOf( accountPolicyData.passLoginPolicyData_.upperLowerFlag_ ));
				
				jsonMap.put( "daily_rem", String.valueOf( accountPolicyData.passPolicyData_.dailyRemainderFlag_ ));
				jsonMap.put( "email_noti", String.valueOf( accountPolicyData.passPolicyData_.notificationRemainderNday_ ));
				jsonMap.put( "max_pass", String.valueOf( accountPolicyData.passPolicyData_.passwordAgeMaxDays_ ));
				jsonMap.put( "min_pass", String.valueOf( accountPolicyData.passPolicyData_.passwordAgeMinDays_ ));
				jsonMap.put( "pass_his", String.valueOf( accountPolicyData.passPolicyData_.passwordHistoryDays_ ));
				
				jsonMap.put( "unlock_by_admin", String.valueOf( accountPolicyData.lockoutPolicyData_.adminUnlockFlag_ ));
				jsonMap.put( "session", String.valueOf( accountPolicyData.lockoutPolicyData_.expireSessionMin_ ));
				jsonMap.put( "lockout", String.valueOf( accountPolicyData.lockoutPolicyData_.invalidLoginAttempts_ ));
				jsonMap.put( "lockout_dur", String.valueOf( accountPolicyData.lockoutPolicyData_.lockoutDurationMin_ ));
				jsonMap.put( "reset_counter", String.valueOf( accountPolicyData.lockoutPolicyData_.resetLockoutDurationMin_ ));
			}
		}
		else 
		{
			jsonMap.put( "result", "failed" );
			
			
			String msg = "Info::AccountPolicyServlet.doPost() -  Request Failed - Error code - "+responseCode+"\r\n\n\n";
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			
			jsonMap.put( "message", responseString );
		}
		String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
		
		jsonMap.put( "message", responseString );
		
		jsonStr = new Gson( ).  toJson( jsonMap );
		
		jsonMap = null;
		
		errorMaster_.insert( "json str="+jsonStr );

		return jsonStr;
	}

}
