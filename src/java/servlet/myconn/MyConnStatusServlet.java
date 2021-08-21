package servlet.myconn;

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

import core.login.SessionData;
import ctrl.myconn.MyConnStatusController;

/**
 * Servlet implementation class UserConnServlet
 */
@WebServlet("/MyConnStatusServlet")
public class MyConnStatusServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	
	public MyConnStatusServlet()
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
	 * Purpose: It get the request from My Connection under the Network. This
	 * servlet get the following request from view and send the response to
	 * View.
	 * 1.Add new connection request
	 * 2.Connection accept request
	 * 3.Connection reject request 
	 */
	
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {		
			String msg = "Info::MyConnStatusServlet.doPost() -  Request for "
						  + request.getParameter( "RequestType" ) + 
						  " for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			
			// Call to controller
			
			MyConnStatusController myConnStatusController = new MyConnStatusController( );
			
			int responseCode = myConnStatusController.processStatus( request );
			
			myConnStatusController = null;
			
			
			// Compose the result 
			
			MyConnStatusComposer composer = new MyConnStatusComposer( );
			
			String jsonStr = composer.composeResultJSON( responseCode, request );

			response.getWriter( ).write( jsonStr );
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::MyConnStatusServlet.doPost() - "+e+"\r\n\n";

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
