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
import core.newsroom.WLMemberData;
import ctrl.newsroom.WLMemberController;


/**
 * Servlet implementation class WLMemberServlet
 */
@WebServlet("/WLMemberServlet")
public class WLMemberServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public WLMemberServlet()
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
	 * 1.Add the members to Watch list
	 * 2.Remove the members from watch list
	 * 3.Get all the members from given watchlist
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
			String msg = "Info::WLMemberServlet.doPost() -  Request : "
						  + request.getParameter( "RequestType" ) + 
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			
			// Call to controller
			
			WLMemberController wlMemberController = new WLMemberController( );
			
			List<WLMemberData> members= new ArrayList<WLMemberData>( );
			
			int responseCode = wlMemberController.processWLMembers( request, members );
			
			wlMemberController = null;
			
			
			// Compose the result 
			
			WLMemberComposer composer = new WLMemberComposer( );
			
			String jsonStr = composer.composeMembersJSON( responseCode, members );

			response.getWriter( ).write( jsonStr );
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::WLMemberServlet.doPost() - "+e+"\r\n\n";

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
