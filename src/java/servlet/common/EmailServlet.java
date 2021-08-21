package servlet.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import core.login.SessionData;
import ctrl.common.EmailController;
import utils.ErrorMaster;


/**
 * Servlet implementation class EmailServlet
 */
@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EmailServlet() {
        super();
           }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	
	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It get the request from History under the Network Menu from GUI. This
	 * servlet get the following request from view and send the response to
	 * View. It do the following 
	 * 
	 * 1.send the mail to particular customer
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		String jsonStr =  "";
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
			String requestType = request.getParameter( "RequestType" );
			
			
			EmailController emailCtrl = new EmailController( );
			
			errLogger.logMsg( "Info::EmailServlet.doPost() - Request for " + requestType + " for company " +
					"<" + sessionData.companyRegnKeyStr_ + ">, " +
					"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
			
			
			int responseCode = 0;
	
			responseCode = emailCtrl.sendMail( request );
                        ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
			
			errorMaster_.insert( "ResponseCode - "+responseCode );
			
			if( responseCode == 16050 ) 
			{
				jsonStr = "{ \"result\" : \"success\",  ";		
			
			}	
			else
			{
				jsonStr = "{ \"result\" : \"failed\",  ";		
    			
			}	
			
			String responseString = ErrorCodeConfigReader.instance( ).get( Integer.toString( responseCode ) );
			
			jsonStr = jsonStr + "\"message\" : \" "+responseString+"\" }";
			
			response.getWriter( ).write( jsonStr );
        }
		
		catch( Exception e )
        {
			String msg = "Exception::EmailServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
						
			jsonStr = "{ \"result\" : \"failed\",  ";		
			
			jsonStr = jsonStr + "\"message\" : \" Request failed. Try again. \" }";
				
			response.getWriter( ).write( jsonStr );
        }
	}
}
