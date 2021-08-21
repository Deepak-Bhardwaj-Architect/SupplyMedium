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
package servlet.newsroom;

import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import utils.JSONEncode;

import core.newsroom.WatchListData;

/**
 * File:  WatchListComposer.java 
 *
 * Created on 30-Aug-2013 6:35:37 PM
 */
public class WatchListComposer
{

	/*
	 * Method : WatchListComposer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WatchListComposer()
	{
		
	}
	
	
	/*
	 * Method: composeWatchListJSON
	 * 
	 * Input: int response, List of WatchListData
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the watchLists list object to json string
	 */
	
	public String composeWatchListJSON( int responseCode, List<WatchListData> watchLists )
	{
		String jsonString = "";

		JSONEncode jsonEncode = new JSONEncode( );

		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );

		if( responseCode == 10100 || responseCode == 10110 || responseCode == 10120 )
		{
			String servletMsg = "Info::WatchListServlet.doPost() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			// System.out.println( "response code="+responseCode );

			if( responseCode == 10100 || responseCode == 10110)
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

				int watchListCount = watchLists.size( );

				jsonString = "{ \"result\" : \"success\",  ";

				jsonString = jsonString + "\"watchLists\" : [";

				for( int i = 0; i < watchListCount; i++ )
				{
					WatchListData watchListData = watchLists.get( i );

					if( i != 0 ) jsonString = jsonString + ",";

					jsonString = jsonString + "{ \"watchListId\" : \""
					        + watchListData.watchListId_ + "\", ";

					jsonString = jsonString + " \"watchListname\" : \""
					        + jsonEncode.encode( watchListData.watchListName_ ) + "\", ";

					SimpleDateFormat dateFormat = new SimpleDateFormat(
					        "dd MMM yyyy ',' hh:mm aaa" );

					String dateStr = dateFormat.format( watchListData.createdTimestamp_ );

					jsonString = jsonString + " \"createdTime\" : \"" + dateStr + "\" ";

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

			String servletMsg = "Info::WatchListServlet.doPost() - "
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
