package servlet.privatemsg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import utils.ErrorLogger;

import com.google.gson.Gson;
import core.login.SessionData;
import core.privatemsg.PrivateMessageData;
import ctrl.privatemsg.PrivateMessageController;


/**
 * Servlet implementation class PrinvateMessageServlet
 */
@WebServlet("/PrivateMessageServlet")
public class PrivateMessageServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
      public PrivateMessageServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
			{
		
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It get the request from Messages under the Network Menu from GUI. This
	 * servlet get the following request from view and send the response to
	 * View. It do the following 
	 * 
	 * 1.Create the new PrivateMessage
	 * 2.Remove the PrivateMessage
	 * 3.Get all PrivateMessage for given user key 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
			{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {		
			String msg = "Info::PrivateMessageServlet.doPost() -  Request : "
						  + request.getParameter( "RequestType" ) + 
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			
			// Call to controller
			
			PrivateMessageController privateMessageController = new PrivateMessageController( );
			
			List<PrivateMessageData> messages= new ArrayList<PrivateMessageData>( );
			
			int responseCode = privateMessageController.processPM( request, messages );
			
			privateMessageController = null;
			
			
			// Compose the result 
			
			PrivateMessageComposer composer = new PrivateMessageComposer( );
			
			String jsonStr = composer.composePrivateMessageJSON( responseCode, messages );

			response.getWriter( ).write( jsonStr );
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::PrivateMessageServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			Map<String, String> jsonMap = new HashMap<String, String>( );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			jsonMap = null;
			
			response.getWriter( ).write( jsonStr );
			
        }

	}
}
