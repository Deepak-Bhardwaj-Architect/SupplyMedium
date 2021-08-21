package servlet.notification;

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


import utils.ErrorLogger;
import com.google.gson.Gson;

import core.notification.NotificationCenterData;
import ctrl.notification.NotificationController;


/**
 * Servlet implementation class NotificationServlet
 */
@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public NotificationServlet()
    {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
	}
	
	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It get the request from Notification under the Network Menu from GUI. This
	 * servlet get the following request from view and send the response to
	 * View. It do the following 
	 * 
	 * 1.Create the new Notification
	 * 2.Remove the Notification
	 * 3.Get all Notification for given user key 
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		ErrorLogger errLogger = ErrorLogger.instance( );
		
		//HttpSession session = request.getSession( );
		
		//SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {		
			/*String msg = "Info::NotificationServlet.doPost() -  Request : "
						  + request.getParameter( "RequestType" ) + 
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );*/
			
			
			// Call to controller
			
			NotificationController notificationController = new NotificationController( );
			
			List<NotificationCenterData> notifications= new ArrayList<NotificationCenterData>( );
			
			int responseCode = notificationController.processNM( request, notifications );
			
			notificationController = null;
			
			
			// Compose the result 
			
			NotificationComposer composer = new NotificationComposer( );
			
			String jsonStr = composer.composeNotificationJSON( responseCode, notifications );

			response.getWriter( ).write( jsonStr );
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::NotificationServlet.doPost() - "+e+"\r\n\n";

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
