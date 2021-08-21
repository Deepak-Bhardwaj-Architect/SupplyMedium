package servlet.searchbuyer;

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

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

import com.google.gson.Gson;

import core.searchbuyer.BuyerSearchResult;
import ctrl.searchbuyer.SearchBuyerReqController;

/**
 * Servlet implementation class SearchBuyerServlet
 */
@WebServlet("/SearchBuyerServlet")
public class SearchBuyerServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchBuyerServlet()
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
	

	 /* Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It get the request from the Buyer.jsp. Then parse
	 * the HttpServletRequest and using core class it find the buyer
	 * for given search parameter. Then send the company list to jsp.
	 */
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );

		String servletMsg = "Info::SearchBuyerServlet.doPost() "
		        + "Search Buyer request";
		
		errLogger.logMsg( servletMsg );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		List<BuyerSearchResult> searchResultList = new ArrayList<BuyerSearchResult>( );
		
		try
        {
			SearchBuyerReqController searchRegCtrl = new SearchBuyerReqController( );
			
	        int result = searchRegCtrl.processRequest( request, searchResultList );
	        
	        String jsonStr = composeResponseJSON( result, request, searchResultList );

			response.getWriter( ).write( jsonStr );
        }
        catch(  Exception e )
        {
        	Map<String, String> jsonMap = new HashMap<String, String>( );
        	
        	String msg = "Exception::SearchBuyerServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
        }
	}
	
	/*
	 * Method : composeResponseJSON( ) 
	 * 
	 * Input : int responseCode,request object and searchresult
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the response in to json
	 * string
	 */

	private String composeResponseJSON( int responseCode, HttpServletRequest request,List<BuyerSearchResult> searchResultList  )
	{
		String jsonString = "";
		
		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );
		
		if( responseCode == 3200 || responseCode == 3300 || responseCode == 3201 || 
			responseCode == 3202 || responseCode == 3204 ||
			responseCode == 3250 || responseCode == 3251|| responseCode == 3252 ||
			responseCode == 3253 || responseCode == 3254||
			responseCode == 3255 || responseCode == 3256 || 
			responseCode == 3300 || responseCode == 3301 || responseCode == 3302 || 
			responseCode == 3303 || responseCode == 3304 ||
			responseCode == 3350 || responseCode == 3351 || responseCode == 3352 ||
			responseCode == 3353 || responseCode == 3354 )
		{
			String servletMsg = "Info::SearchBuyerServlet.composeResponseJSON() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			jsonString = "{ \"result\" : \"success\",  ";

			jsonString = jsonString + "\"vendors\" : [";
			
			int i=0;

			for( BuyerSearchResult buyerSearchResult : searchResultList )
			{

				if( i != 0 ) jsonString = jsonString + ",";
				
				jsonString = jsonString + "{ \"regnKey\" : \""
				        + buyerSearchResult.regnKey_.toString( ) + "\", ";
				
				jsonString = jsonString + " \"isRegn\" : \""
				        + buyerSearchResult.isRegn_+ "\", ";
				
				jsonString = jsonString + " \"avgRatings\" : \""
				        + buyerSearchResult.avg_ratings_+ "\", ";
				
				jsonString = jsonString + " \"noOfRatings\" : \""
				        + buyerSearchResult.no_of_ratings_+ "\", ";

				jsonString = jsonString + " \"companyName\" : \""
				        + buyerSearchResult.companyName_ + "\" ";

				jsonString = jsonString + "}";
				
				i++;

			}

			jsonString = jsonString + "]}";
			
			System.out.println( "result="+jsonString );

			return jsonString;
		}
		else 
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::SearchBuyerServlet.composeResponseJSON() - "
			        + "Request failed - Error code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );

			jsonMap.put( "message", responseString );
			
			jsonString = new Gson( ).  toJson( jsonMap );
			
			jsonMap = null;
			
			System.out.println( "json str="+jsonString );

			return jsonString;
		}
	}

}
