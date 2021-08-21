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
package servlet.dashboard;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

import com.google.gson.Gson;

import core.dashboard.TimeBasedData;


/**
 * File:  TimeBasedReportComposer.java 
 *
 * Created on Oct 28, 2013 2:37:58 PM
 */
public class TimeBasedReportComposer
{
	
	
	/*
	 * Method : TimeBasedReportComposer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public TimeBasedReportComposer()
	{
		
	}
	
	

	/*
	 * Method: composeTimeBasedReportJSON
	 * 
	 * Input: int response, List of TimeBasedData Data
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the timeBasedList list object to json string
	 */
	
    public String composeTimeBasedReportJSON( int responseCode,
            List<TimeBasedData> timeBasedList )
    {
    	String jsonString = "";

		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );

		if( responseCode == 10400 || responseCode == 10410 || responseCode == 10420 || responseCode == 10430 )
		{
			String servletMsg = "Info::TimeBasedReportServlet.doPost() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			// System.out.println( "response code="+responseCode );

			int timeBasedCount = timeBasedList.size( );

			jsonString = "{ \"result\" : \"success\",  ";

			
			jsonString = jsonString + "\"records\" : [";

			for( int i = 0; i < timeBasedCount; i++ )
			{
				TimeBasedData timeBasedData = timeBasedList.get( i );

				if( i != 0 ) jsonString = jsonString + ",";


				jsonString = jsonString + "{ \"transactionAmount\" : \""
				        + timeBasedData.transactionAmount_ + "\", ";
				
				
				
				jsonString = jsonString + " \"reportType" +
						"\" : \"" + timeBasedData.reportType_
				        + "\", ";

				jsonString = jsonString + " \"label\" : \"" + timeBasedData.timeLabel_
				        + "\" ,";

				jsonString = jsonString + " \"currencyType\" : \""
				        +  timeBasedData.currencyType_ + "\"";


				

				jsonString = jsonString + "}";

			}

			jsonString = jsonString + "]}";

			System.out.println( "json str=" + jsonString );

			return jsonString;

		}
		else
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::TimeBasedReportServlet.doPost() - "
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
