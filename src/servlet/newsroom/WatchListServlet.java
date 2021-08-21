package servlet.newsroom;

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
import core.newsroom.WatchListData;
import ctrl.newsroom.WatchListController;

/**
 * Servlet implementation class WatchListServlet
 */
@WebServlet("/WatchListServlet")
public class WatchListServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public WatchListServlet()
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
	 * Purpose: It get the request from News Room under the Network Menu from GUI. This
	 * servlet get the following request from view and send the response to
	 * View. It do the following 
	 * 
	 * 1.Create the new watch list
	 * 2.Remove the watch list
	 * 3.Get all watch list for given user key 
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
			String msg = "Info::WatchListServlet.doPost() -  Request : "
						  + request.getParameter( "RequestType" ) + 
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			
			// Call to controller
			
			WatchListController watchListController = new WatchListController( );
			
			List<WatchListData> watchLists= new ArrayList<WatchListData>( );
			
			int responseCode = watchListController.processWL( request, watchLists );
			
			watchListController = null;
			
			
			// Compose the result 
			
			WatchListComposer composer = new WatchListComposer( );
			
			String jsonStr = composer.composeWatchListJSON( responseCode, watchLists );

			response.getWriter( ).write( jsonStr );
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::WatchListServlet.doPost() - "+e+"\r\n\n";

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
