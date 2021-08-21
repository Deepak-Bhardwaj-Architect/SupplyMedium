package servlet.usermgmt;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;


import core.login.SessionData;
import core.regn.MailingAddressData;
import core.regn.UserProfileData;
import ctrl.regn.UserSignupController;
import utils.ErrorMaster;

/**
 * Servlet implementation class UserFetchServlet
 */
@WebServlet("/UserFetchServlet")
public class UserFetchServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
        private static ErrorMaster errorMaster_ = null;




	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserFetchServlet()
	{
		super( );if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		// Map<String, String> jsonMap = new HashMap<String, String>( );

		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );

		String jsonString = "";
		
		try
		{
			String msg = "Info::UserFetchServlet.doPost() - Request to Fetch Users"
						 + " for the company " +"<" + sessionData.companyRegnKeyStr_ + ">, " +
						 "<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";			        

			errLogger.logMsg( msg );
			
			UserSignupController signUpCtrl = new UserSignupController( );
			
			List<UserProfileData> profileDataArr = new ArrayList<UserProfileData>( );

			int responseCode = signUpCtrl.getAllUsers( request, profileDataArr );
			
			//int responseCode = signUpCtrl.getAllUsers( request, profileDataArr );

			signUpCtrl = null;
			
			

			if( responseCode == 900 )
			{
				// jsonMap.put( "result", "success" );
				msg = "Info::UserFetchServlet.doPost() - Request successful - Response code - "+responseCode+"\r\n\n\n";
				
				errLogger.logMsg( msg );

				String responseString = ErrorCodeConfigReader.instance( ).get(
				        "" + responseCode );

				jsonString = "{ \"result\" : \"success\",  ";
				jsonString = jsonString + "\"msg\" : \""+responseString+"\", ";
				jsonString = jsonString + "\"users\" : [";
				
				int iterator = 0;
				for( UserProfileData userProfileData : profileDataArr )
                {
	                jsonString = jsonString + "{ \"firstname\" : \""+userProfileData.firstName_+" "+userProfileData.lastName_+"\", ";
	                
	                              
	                if( userProfileData.mailingAddr_ != null )
	                {
	                	MailingAddressData data = userProfileData.mailingAddr_;
	                	                
	                	if( data.city_ != null )
	                	{
	                		jsonString = jsonString + " \"city\" : \""+data.city_+"\", ";
	                	}
	                	else 
	                	{
	                		jsonString = jsonString + " \"city\" : \"\", ";
	                	}
	                }	

	                else 
                	{
                		jsonString = jsonString + " \"city\" : \"\", ";
                	}
	               
	                jsonString = jsonString + " \"email\" : \""+userProfileData.emailId_+"\", ";
	                
	                jsonString = jsonString + " \"status\" : \""+userProfileData.userStatus_+"\", ";
	                

	                if(userProfileData.loginData_ == null)
	                {
	                	 jsonString = jsonString + " \"state\" : \"Not Connected\", ";
	                }
	                else
	                {
	                	 jsonString = jsonString + " \"state\" : \""+userProfileData.loginData_.loginStatus_+"\", ";
	                }
	               
	                
	                
	                jsonString = jsonString + " \"changepassword\" : \""+userProfileData.changePasswordFlag_+"\", ";
	               
	                jsonString = jsonString + " \"disableaccount\" : \""+userProfileData.disableAccountFlag_+"\", ";

	                jsonString = jsonString + " \"expiretime\" : \""+userProfileData.accountExpireDays_+"\"";

	                
	                jsonString = jsonString + "}";
	                iterator = iterator + 1;

	                
	                if( profileDataArr.size( ) > iterator )
	                {
	                	jsonString = jsonString + ",";
	                }

                }
				
				jsonString = jsonString + "]}";
			}
			else
			{
				String responseString = ErrorCodeConfigReader.instance( ).get(
				        "" + responseCode );

				jsonString = "{ \"result\" : \"failed\"  ";

				jsonString = jsonString + "\"msg\" : "+responseString+" }";
				
				msg = "Info::UserFetchServlet.doPost() - Request Failed - Error code - "+responseCode+"\r\n\n\n";
				
				errLogger.logMsg( msg );
				
			}

			errorMaster_.insert( "json str=" + jsonString );

			response.getWriter( ).write( jsonString );
		}
		catch( Exception e )
		{
			String msg = "Exception::UserFetchServletServlet.doPost() - " + e+"\r\n\n\n";

			errLogger.logMsg( msg );
			
			jsonString = "{ \"result\" : \"failed\"  ";

			jsonString = jsonString + "\"msg\" : \" Request failed.  Pls try again later \" }";

		}
	}

}
