package servlet.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import core.common.SearchVendor;
import core.login.SessionData;
import core.regn.RegnData;
import ctrl.common.SearchVendorController;
import utils.ErrorMaster;

/**
 * Servlet implementation class SearchVendor
 */
@WebServlet("/SearchVendorServlet")
public class SearchVendorServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public SearchVendorServlet()
	{
		super( );
		// TODO Auto-generated constructor stub
	}

	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: This method is used to search vendor for the company.
	 * This servlet get the two type of request
	 * 1.Search Registered vendors
	 * 2.Search Non-Registered vendors
	 */
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		String jsonStr =  "";
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		String requestType = request.getParameter( "RequestType" );
		
		try
        {
			errLogger.logMsg( "Info::VendorRegnServlet.doPost() - Request for " + requestType + " for company " +
					"<" + sessionData.companyRegnKeyStr_ + ">, " +
					"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
			
			int responseCode = 0;
			
			List<RegnData> vendorRegnDataList = new ArrayList<RegnData>( );
			
			SearchVendorController searchVenCtrl = new SearchVendorController( );
			
			responseCode = searchVenCtrl.search( request, vendorRegnDataList );
			
			jsonStr = composeVendorListJSON( responseCode, vendorRegnDataList );
	
			searchVenCtrl = null;
			
			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::SearchVendorServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			//jsonMap.put( "result", "failed" );
			
			jsonStr = "{ \"result\" : \"failed\",  ";		
			
			//jsonMap.put( "message", "Request failed. Try again." );
			
			jsonStr = jsonStr + "\"message\" : \" Request failed. Try again. \" }";
			
			//String jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }
        
	}
	
	
	/*
	 * Method: composeNRListJSON
	 * 
	 * Input: int response, List<RegnData> regnDataList
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the regnDataList and compose JSON string
	 */
	
	public String composeVendorListJSON( int responseCode, List<RegnData> regnDataList )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
                ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		
		errorMaster_.insert( "Response Code="+responseCode );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 3640 || responseCode == 3645 )
		{
			//jsonMap.put( "result", "success" );
			
			String msg = "Info::SearchVendorServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );
			
			jsonStr = "{ \"result\" : \"success\",  ";
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\", ";
			
			if( responseCode == 3640 )   // Non registered vendors
			{
				jsonStr = jsonStr + "\"NRvendors\" : [";
			}
				
			else   // Registered vendors
			{
				jsonStr = jsonStr + "\"Rvendors\" : [";
			}
			
			int iterator = 0;
			
			for( RegnData regnData : regnDataList )
            {
				/*Vendor regn data*/
				jsonStr = jsonStr + "{ \"companykey\" : \"" + regnData.companyRegnKey_.toString( ) + "\", ";
                                jsonStr = jsonStr + " \"companyid\" : \"" + regnData.companyId_ + "\", ";
				jsonStr = jsonStr + "\"companyname\" : \"" + regnData.companyName_ + "\"} ";
				
				iterator = iterator + 1;

				if( regnDataList.size( ) > iterator )
				{
					jsonStr = jsonStr + ",";
				}
			}
				
			jsonStr = jsonStr + "]}";
		}
		else 
		{
			//jsonMap.put( "result", "failed" );
			
			jsonStr = "{ \"result\" : \"failed\",  ";
			
			String msg = "Info::SearchVendorServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
			
			//jsonMap.put( "message", responseString );	
		
		}
		
		//jsonStr = new Gson( ).toJson( jsonMap );
		
		errorMaster_.insert( "json str="+jsonStr );

		return jsonStr;
	}

}
