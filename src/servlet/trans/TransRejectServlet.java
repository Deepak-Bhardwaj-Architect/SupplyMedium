package servlet.trans;

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

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

import com.google.gson.Gson;

import core.login.SessionData;
import core.trans.TransRejectData;
import ctrl.trans.TransRejectController;

/**
 * Servlet implementation class TransRejectServlet
 */

@WebServlet("/TransRejectServlet")

public class TransRejectServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransRejectServlet()
	{
		super( );
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It get the request for RFQ,Quote,PO and Invoice reject under the transaction. This
	 * servlet get the following request from view and send the response to
	 * View.
	 * 1.Reject the transaction (RequestType - Reject)
	 */
	
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );
		
		String jsonStr = "";
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
		
			String msg = "Info::TransRejectServlet.doPost() - Request to fetch all " 
						+ request.getParameter( "RequestType" ) +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";

			errLogger.logMsg( msg );
			
			TransRejectController transRejectCtrl = new TransRejectController( );
			
			List<TransRejectData> rejectDataList = new ArrayList<TransRejectData>( );
			
			int responseCode = transRejectCtrl.processRequest( request, rejectDataList );
			
			transRejectCtrl = null;

			if( responseCode == 8050 || responseCode == 8150 ||
				responseCode == 8250 || responseCode == 8350 )
			{
				
				msg = "Info::TransRejectServlet.doPost() -  Request Successful - Response code - "+responseCode+"\r\n\n\n";
				
				errLogger.logMsg( msg );
				
				String responseString = ErrorCodeConfigReader.instance( ).get(
				        "" + responseCode );
				
				jsonStr = "{ \"result\" : \"success\",  ";
				jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
			}
			else 
			{
				jsonMap.put( "result", "failed" );
				
				msg = "Info::TransRejectServlet.doPost() -  Request Failed - Error code - "+responseCode+"\r\n\n";
				
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
				
				jsonMap.put( "message", responseString );
				
			    jsonStr = new Gson( ).  toJson( jsonMap );
			}
			
			System.out.println( "json str="+jsonStr );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::TransRejectServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			 jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
        }
	}
}
