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
package servlet.advertisement;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import utils.JSONEncode;

import com.google.gson.Gson;

import core.advertisement.AdData;
import utils.ErrorMaster;


/**
 * File:  AdComposer.java 
 *
 * Created on Oct 3, 2013 11:33:52 AM
 */
public class AdComposer
{
	
	/*
	 * Method : AdComposer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public AdComposer()
	{
		
	}
	
	/*
	 * Method: composeAdJSON
	 * 
	 * Input: int response, List of AdData
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the ad list object to json string
	 */

    public String composeAdJSON( int responseCode, List<AdData> adlists )
    {
    	ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
    	String jsonString = "";

		JSONEncode jsonEncode = new JSONEncode( );

		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );

		if( responseCode == 10700 || responseCode == 10710 || responseCode == 10720  )
		{
			String servletMsg = "Info::AdServlet.doPost() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			// errorMaster_.insert( "response code="+responseCode );

			if( responseCode == 10700 || responseCode == 10710)
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
				// errorMaster_.insert( "response code="+responseCode );

				int adCount = adlists.size( );

				jsonString = "{ \"result\" : \"success\",  ";

				jsonString = jsonString + "\"ads\" : [";

				for( int i = 0; i < adCount; i++ )
				{
					AdData adData = adlists.get( i );

					if( i != 0 ) jsonString = jsonString + ",";

					jsonString = jsonString + "{ \"adId\" : \""
					        + adData.adId_ + "\", ";

					jsonString = jsonString + " \"companyKey\" : \""
					        + jsonEncode.encode( adData.regnKey_.companyPhoneNo_ ) + "\", ";
					
					jsonString = jsonString + " \"customerKey\" : \""
					        + jsonEncode.encode( adData.userProfileKey_.toString( ) ) + "\", ";
					
					jsonString = jsonString + " \"alternateText\" : \""
					        + jsonEncode.encode( adData.alternateText_ ) + "\", ";
					
					jsonString = jsonString + " \"link\" : \""
					        + jsonEncode.encode( adData.link_ ) + "\", ";
									
					jsonString = jsonString + " \"adImagePath\" : \""
					        + jsonEncode.encode( adData.adImagePath_ ) + "\" ";
					
					jsonString = jsonString + "}";

				}

				jsonString = jsonString + "]}";

				jsonEncode = null;
				
				errorMaster_.insert( "json str=" + jsonString );

				return jsonString;

			}

		}
		else
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::AdServlet.doPost() - "
			        + "Request failed - Error code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );

			jsonMap.put( "message", responseString );

			jsonString = new Gson( ).toJson( jsonMap );

			jsonMap = null;

			errorMaster_.insert( "json str=" + jsonString );

			return jsonString;

		}
	}

}

