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
import core.myconn.MyConnProfileData;
import ctrl.myconn.MyConnProfileController;

/**
 * Servlet implementation class MyConnProfileServlet
 */
@WebServlet("/MyConnProfileServlet")
public class MyConnProfileServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	
	public MyConnProfileServlet()
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
	 * Purpose: It is used to fetch the user profile details and convert the details into
	 * json format. Then send it to client.
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
			String msg = "Info::MyConnProfileServlet.doPost() -   "+
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			
			// Call to controller
			
			MyConnProfileController controller = new MyConnProfileController( );
			
			MyConnProfileData myConnProfileData  = new MyConnProfileData( );
			
			int responseCode = controller.processRequest( request, myConnProfileData );
			
			controller = null;
			
			
			// Compose the result 
			
			MyConnProfileComposer composer = new MyConnProfileComposer( );
			
			String jsonStr = composer.composeProfileJSON( responseCode, myConnProfileData );

			response.getWriter( ).write( jsonStr );
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::MyConnProfileServlet.doPost() - "+e+"\r\n\n";

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
