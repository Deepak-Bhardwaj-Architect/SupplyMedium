package servlet.chat;

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
import ctrl.chat.ChatMessageController;

/**
 * Servlet implementation class ChatMessageServlet
 */
@WebServlet("/ChatMessageServlet")
public class ChatMessageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	
	public ChatMessageServlet()
	{
		super( );
		
	}

	
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		
	}

	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It get the request from chat under the Network Menu from GUI. This
	 * servlet get the following request from view and send the response to
	 * View. It do the following 
	 * 
	 * 1.Get the chat message from user
	 */
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
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
			
			ChatMessageController controller = new ChatMessageController( );
			
			errLogger.logMsg( "Info::ChatMessageServlet.doPost() - Request for " + requestType + " for company " +
					"<" + sessionData.companyRegnKeyStr_ + ">, " +
					"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
			
			int responseCode = controller.processMessage( request );
			
			controller = null;
		
			//Based on the responseCode, the response JSON message to be generated here
			
			if( responseCode == 10620 ) 
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
			String msg = "Exception::ChatMessageServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			jsonStr = "{ \"result\" : \"failed\",  ";		
			
			jsonStr = jsonStr + "\"message\" : \" Request failed. Try again. \" }";
			
			response.getWriter( ).write( jsonStr );
        }
		
	}

}
