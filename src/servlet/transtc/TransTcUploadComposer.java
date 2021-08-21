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
package servlet.transtc;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import utils.JSONEncode;

import com.google.gson.Gson;

import core.transtc.TransTcUploadData;

/**
 * File:  TransTcUploadComposer.java 
 *
 * Created on Oct 19, 2013 11:37:49 AM
 */
public class TransTcUploadComposer
{
	
	
	/*
	 * Method : TransTcUploadComposer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public TransTcUploadComposer()
	{
		
	}
	
	
	/*
	 * Method: composeTransTcJSON
	 * 
	 * Input: int response, List of TransTcUploadData
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the trans list object to json string
	 */
    public String composeTransTcJSON( int responseCode, List<TransTcUploadData> translists )
    {
    	String jsonString = "";

		JSONEncode jsonEncode = new JSONEncode( );

		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );

		if( responseCode == 17000 || responseCode == 17010  )
		{
			String servletMsg = "Info::TransTcUploadServlet.doPost() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			// System.out.println( "response code="+responseCode );

			if( responseCode == 17000 )
			{
				jsonMap.put( "result", "success" );

				String responseString = ErrorCodeConfigReader.instance( ).get(
				        Integer.toString( responseCode ) );

				jsonMap.put( "message", responseString );

				jsonString = new Gson( ).toJson( jsonMap );

				jsonMap = null;
				
				return jsonString;

			}
			
			else
			{
				// System.out.println( "response code="+responseCode );

				int transTcCount = translists.size( );

				jsonString = "{ \"result\" : \"success\",  ";

				jsonString = jsonString + "\"TransTc\" : [";

				for( int i = 0; i < transTcCount; i++ )
				{
					TransTcUploadData transTcUploadData = translists.get( i );

					if( i != 0 ) jsonString = jsonString + ",";

					jsonString = jsonString + "{ \"transTcId\" : \""
					        + transTcUploadData.transTcId_ + "\", ";

					jsonString = jsonString + " \"companyKey\" : \""
					       + jsonEncode.encode( transTcUploadData.regnKey_.companyPhoneNo_ ) + "\", ";
					
					
					jsonString = jsonString + " \"transType\" : \""
					        + jsonEncode.encode( transTcUploadData.transType_ ) + "\", ";
					
					jsonString = jsonString + " \"tc\" : \""
					        + jsonEncode.encode( transTcUploadData.tc_ ) + "\" ";
					
					
					
					

						jsonString = jsonString + "}";

				

				}

				jsonString = jsonString + "]}";

				jsonEncode = null;
				
				System.out.println( "json str=" + jsonString );

				return jsonString;

			}

		}
		else
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::TransTcUploadComposer.doPost() - "
			        + "Request failed - Error code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );

			jsonMap.put( "message", responseString );

			jsonString = new Gson( ).toJson( jsonMap );

			jsonMap = null;

			System.out.println( "json str=" + jsonString );

			return jsonString;

		}
	}

	    
    

}
