package servlet.chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorLogger;

import com.google.gson.Gson;

import core.chat.ChatEventData;
import core.login.SessionData;
import ctrl.chat.ChatEventController;

/**
 * Servlet implementation class ChatEventServlet
 */
@WebServlet("/ChatEventServlet")
public class ChatEventServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	
	public ChatEventServlet()
	{
		super( );
		
	}

	
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		//HttpSession session = request.getSession( );
		
		//SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {		
			/*String msg = "Info::ChatEventServlet.doPost() -  Request : "
						  + request.getParameter( "RequestType" ) + 
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );*/
			
			
			// Call to controller
			
			ChatEventController chatEventController = new ChatEventController( );
			
			ChatEventData chatEventData = new ChatEventData( );
			
			int responseCode = chatEventController.processEvent( request, chatEventData );
			
			chatEventController = null;
			
			
			// Compose the result 
			
			ChatEventComposer composer = new ChatEventComposer( );
		
			String jsonStr = composer.composeChatEventJSON( responseCode, chatEventData );
			
			composer = null;

			response.getWriter( ).write( jsonStr );
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::ChatEventServlet.doPost() - "+e+"\r\n\n";

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
	 * 1.Fetch the connection list and currently received message for the user
	 */
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		

	}

}
