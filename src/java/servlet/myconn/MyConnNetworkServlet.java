package servlet.myconn;

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
import core.myconn.MyConnectionData;
import ctrl.myconn.MyConnNetworkController;

/**
 * Servlet implementation class UserConnFetchServlet
 */
@WebServlet("/MyConnNetworkServlet")

public class MyConnNetworkServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	
	public MyConnNetworkServlet()
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
	 * 1.Fetch all user connection
	 * 2.Fetch all the pending request connection 
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
			String msg = "Info::MyConnNetworkServlet.doPost() -  Request : "
						  +" for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			
			// Call to controller
			
			MyConnNetworkController myConnStatusController = new MyConnNetworkController( );
			
			List<MyConnectionData> connections = new ArrayList<MyConnectionData>( );
			
			int responseCode = myConnStatusController.processNetwork( request, connections );
			
			myConnStatusController = null;
			
			
			// Compose the result 
			
			MyConnNetworkComposer composer = new MyConnNetworkComposer( );
			
			String jsonStr = composer.composeConnectionsJSON( responseCode, connections );

			response.getWriter( ).write( jsonStr );
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::MyConnNetworkServlet.doPost() - "+e+"\r\n\n";

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
