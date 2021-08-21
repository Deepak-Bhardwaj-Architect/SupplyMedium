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

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import utils.JSONEncode;

import com.google.gson.Gson;

import core.dashboard.CustomerBasedData;
import core.dashboard.TimeBasedData;
import utils.ErrorMaster;


/**
 * File:  CustomerBasedReportComposer.java 
 *
 * Created on Oct 29, 2013 6:02:51 PM
 */
public class CustomerBasedReportComposer
{
	
	private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : CustomerBasedReportComposer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public CustomerBasedReportComposer()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method: composeCustomerBasedReportJSON
	 * 
	 * Input: int response, List of customerBasedData Data
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the timeBasedList list object to json string
	 */
	
    public String composeCustomerBasedReportJSON( int responseCode,
            List<CustomerBasedData> customerBasedList )
    {
    	String jsonString = "";

		JSONEncode jsonEncode = new JSONEncode( );

		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );

		if( responseCode == 10440 || responseCode == 10450 || responseCode == 10460 || responseCode == 10470)
		{
			String servletMsg = "Info::CustomerBasedReportServlet.doPost() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			// errorMaster_.insert( "response code="+responseCode );

			int timeBasedCount = customerBasedList.size( );

			jsonString = "{ \"result\" : \"success\",  ";

			jsonString = jsonString + "\"records\" : [";

			for( int i = 0; i < timeBasedCount; i++ )
			{
				CustomerBasedData cusBasedData = customerBasedList.get( i );

				if( i != 0 ) jsonString = jsonString + ",";


				jsonString = jsonString + "{ \"transactionAmount\" : \""
				        + cusBasedData.transactionAmount_ + "\", ";
				
				jsonString = jsonString + " \"reportType" +
						"\" : \"" + cusBasedData.reportType_
				        + "\", ";

				jsonString = jsonString + " \"label\" : \"" + cusBasedData.vendorCompanyName_
				        + "\", ";

				jsonString = jsonString + " \"currencyType\" : \""
				        + cusBasedData.currencyType_ + "\"";


				

				jsonString = jsonString + "}";

			}

			jsonString = jsonString + "]}";

			jsonEncode = null;

			errorMaster_.insert( "json str=" + jsonString );

			return jsonString;
		}
		else
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::CustomerBasedReportServlet.doPost() - "
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
