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

package ctrl.history;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import core.history.TransRatingsData;
import core.history.TransReminderData;
import core.regn.CompanyRegnKey;
import utils.ErrorMaster;

/**
 * File: HistoryDataConverter.java
 * 
 * Created on September 3, 2013 8:16:34 AM
 */

public class HistoryDataConverter
{
    private static ErrorMaster errorMaster_ = null;
    public HistoryDataConverter()
    {
    if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
    }
	/* To convert request into TransReminderData */

	public int getTransReminderData( HttpServletRequest request,
			
			
	        TransReminderData transReminderData )
	{
		try
		{
			String requestType = request.getParameter( "RequestType" );

			if( requestType.equals( "AddRemainder" ) )
			{
				errorMaster_.insert( "Enters" );
				String transRemindJSON = request.getParameter( "TransReminder" )
				        .toString( );

				JsonParser jsonParser = new JsonParser( );

				JsonObject transRemindJSONData = (JsonObject)jsonParser
				        .parse( transRemindJSON );

				// Setting company regn key

				errorMaster_.insert( "Setting regn key" );
				String regnKeyStr = transRemindJSONData.get( "regnKey" )
				        .getAsString( );

				CompanyRegnKey regnKey = new CompanyRegnKey( );

				regnKey.companyPhoneNo_ = regnKeyStr;

				transReminderData.regnKey_ = regnKey;

				regnKey = null;

				errorMaster_.insert( "regnKey_=" + transReminderData.regnKey_ );

				// Setting customer regn key

				errorMaster_.insert( "Setting customer key" );
				
				String cusRegnKeyStr = transRemindJSONData.get( "customerKey" )
				        .getAsString( );

				CompanyRegnKey cusRegnKey = new CompanyRegnKey( );

				cusRegnKey.companyPhoneNo_ = cusRegnKeyStr;

				transReminderData.customerKey_ = cusRegnKey;

				cusRegnKey = null;

				errorMaster_.insert( "cusRegnKey="
				        + transReminderData.customerKey_ );

				// Setting Reminder

				String reminder = transRemindJSONData.get( "reminder" )
				        .getAsString( );

				transReminderData.remainder_ = reminder;

				errorMaster_.insert( "remainder_="
				        + transReminderData.remainder_ );

				// Setting transId

				long transId = transRemindJSONData.get( "transId" ).getAsLong( );

				transReminderData.transId_ = transId;

				errorMaster_.insert( "transId_=" + transReminderData.transId_ );

				// Setting dueDate

				DateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy hh:mm" );

				String dueDate = transRemindJSONData.get( "dueDate" )
				        .getAsString( );

				java.util.Date shippedDate = (java.util.Date)formatter
				        .parse( dueDate );

				transReminderData.dueDate_ = new java.sql.Date(
				        shippedDate.getTime( ) );

				errorMaster_.insert( "dueDate_=" + transReminderData.dueDate_ );

			}
			
			return 0;
		} 
		
		catch( Exception ex )
		{
			ErrorLogger.instance( ).logMsg(
			        "Exception::HistoryDataConverter.getTransReminderData() "
			                + "- Unable to parse request - " + ex );
			ex.printStackTrace( );
			return -1;
		}

	}
	
	/* To convert request into TransRatingsData */
	
	public int getTransRatingsData( HttpServletRequest request,
			TransRatingsData transRatingsData )
	{
		try
		{
			String requestType = request.getParameter( "RequestType" );

			if( requestType.equals( "AddRatings" ) )
			{
				errorMaster_.insert( "line 1");
				String transRatingsJSON = request.getParameter( "TransRatings" )
				        .toString( );

				JsonParser jsonParser = new JsonParser( );

				JsonObject transRatingsJSONData = (JsonObject)jsonParser
				        .parse( transRatingsJSON );

				// Setting company regn key
				errorMaster_.insert( "line 2 ");
				String regnKeyStr = transRatingsJSONData.get( "regnKey" )
				        .getAsString( );

				CompanyRegnKey regnKey = new CompanyRegnKey( );

				regnKey.companyPhoneNo_ = regnKeyStr;

				transRatingsData.regnKey_ = regnKey;

				regnKey = null;

				errorMaster_.insert( "regnKey_=" + transRatingsData.regnKey_ );

				// Setting customer regn key
				errorMaster_.insert( "line 3");
				String cusRegnKeyStr = transRatingsJSONData.get( "customerKey" )
				        .getAsString( );

				CompanyRegnKey cusRegnKey = new CompanyRegnKey( );

				cusRegnKey.companyPhoneNo_ = cusRegnKeyStr;

				transRatingsData.customerKey_ = cusRegnKey;

				cusRegnKey = null;

				errorMaster_.insert( "cusRegnKey="
				        + transRatingsData.customerKey_ );

				// Setting Star count
				errorMaster_.insert( "line 4");
				int starCount = transRatingsJSONData.get( "starCount" ).getAsInt( );

				transRatingsData.starCount_ = starCount;

				errorMaster_.insert( "starCount_="
				        + transRatingsData.starCount_ );

				// Setting transId
				errorMaster_.insert( "line 5");
				long transId = transRatingsJSONData.get( "transId" ).getAsLong( );

				transRatingsData.transId_ = transId;

				errorMaster_.insert( "transId_=" + transRatingsData.transId_ );

			}
			
			return 0;
		}
		
		catch( Exception ex )
		{
			ErrorLogger.instance( ).logMsg(
			        "Exception::HistoryDataConverter.getTransRatingsData() "
			                + "- Unable to parse request - " + ex );
			errorMaster_.insert( "converter End");
			return -1;
		}
	}
}
