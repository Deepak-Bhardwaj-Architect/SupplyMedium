/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any.
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */

package servlet.searchsupplier;

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

import com.google.gson.Gson;

import core.searchsupplier.SupplierSearchResult;

import ctrl.searchsupplier.SearchSupplierReqController;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

/**
 * File: SearchSupplierServlet.java
 * 
 * Created on May 10, 2013 10:39:16 AM
 */

@WebServlet("/SearchSupplierServlet")
public class SearchSupplierServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    
    public SearchSupplierServlet() {
        super();
        
    }

	
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
	 * Purpose: It get the request from the Supplier.jsp. Then parse
	 * the HttpServletRequest and using core class it find the supplier
	 * for given search parameter. Then send the company list to jsp.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ErrorLogger errLogger = ErrorLogger.instance( );

		String servletMsg = "Info::SearchSupplierServlet.doPost() "
		        + "Search Supplier request";
		
		errLogger.logMsg( servletMsg );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		List<SupplierSearchResult> searchResultList = new ArrayList<SupplierSearchResult>( );
		
		try
        {
			SearchSupplierReqController searchRegCtrl = new SearchSupplierReqController( );
			
	        int result = searchRegCtrl.processRequest( request, searchResultList );
	        
	        String jsonStr = composeResponseJSON( result, request, searchResultList );

			response.getWriter( ).write( jsonStr );
        }
        catch(  Exception e )
        {
        	Map<String, String> jsonMap = new HashMap<String, String>( );
        	
        	String msg = "Exception::SearchSupplierServlet.doPost() - "+e+"\r\n\n";

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

	private String composeResponseJSON( int responseCode, HttpServletRequest request,List<SupplierSearchResult> searchResultList  )
	{
		String jsonString = "";
		
		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );
		
		if( responseCode == 0  ||
			responseCode == -1 ||
			responseCode == -2 || 
			responseCode == -3 || 
			responseCode == -4 || 
			responseCode == -5 || 
			responseCode == -6 ||
			responseCode == -7 )
		{
			String servletMsg = "Info::SearchSupplierServlet.composeResponseJSON() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			jsonString = "{ \"result\" : \"success\",  ";

			jsonString = jsonString + "\"vendors\" : [";
			
			int i=0;

			for( SupplierSearchResult supplierSearchResult : searchResultList )
			{

				if( i != 0 ) jsonString = jsonString + ",";
				
				jsonString = jsonString + "{ \"regnKey\" : \""
				        + supplierSearchResult.regnKey_.toString( ) + "\", ";
				
				jsonString = jsonString + " \"isRegn\" : \""
				        + supplierSearchResult.isRegn_+ "\", ";
				
				jsonString = jsonString + " \"avgRatings\" : \""
				        + supplierSearchResult.avg_ratings_+ "\", ";
				
				jsonString = jsonString + " \"noOfRatings\" : \""
				        + supplierSearchResult.no_of_ratings_+ "\", ";

				jsonString = jsonString + " \"companyName\" : \""
				        + supplierSearchResult.companyName_ + "\" ";

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

			String servletMsg = "Info::SearchSupplierServlet.composeResponseJSON() - "
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
