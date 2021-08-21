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
package servlet.privatemsg;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import utils.JSONEncode;

import com.google.gson.Gson;

import core.privatemsg.PrivateMessageData;
import utils.ErrorMaster;


/**
 * File:  PrivateMessageComposer.java 
 *
 * Created on Sep 20, 2013 11:52:16 AM
 */
public class PrivateMessageComposer
{

private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : PrivateMessageComposer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public PrivateMessageComposer()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	
	/*
	 * Method: composePrivateMessageJSON
	 * 
	 * Input: int response, List of PrivateMessageData
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the messages list object to json string
	 */
	
	
    public String composePrivateMessageJSON( int responseCode,
            List<PrivateMessageData> messages )
    {
    	String jsonString = "";

		JSONEncode jsonEncode = new JSONEncode( );

		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );

		if( responseCode == 10200 || responseCode == 10210 || responseCode == 10220 )
		{
			String servletMsg = "Info::PrivateMessageServlet.doPost() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			// errorMaster_.insert( "response code="+responseCode );

			if( responseCode == 10200 || responseCode == 10210)
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

				int privateMessageCount = messages.size( );

				jsonString = "{ \"result\" : \"success\",  ";

				jsonString = jsonString + "\"messages\" : [";

				for( int i = 0; i < privateMessageCount; i++ )
				{
					PrivateMessageData privateMessageData = messages.get( i );

					if( i != 0 ) jsonString = jsonString + ",";

					jsonString = jsonString + "{ \"messageId\" : \""
					        + privateMessageData.messageId_ + "\", ";
					
					jsonString = jsonString + " \"fromUserKey\" : \""
					        + privateMessageData.fromUserKey_.toString( ) + "\", ";
					
					jsonString = jsonString + " \"toUserKey\" : \""
					        + privateMessageData.toUserKey_.toString( ) + "\", ";
					
					jsonString = jsonString + " \"fromUserProfilePic\" : \""
					        + privateMessageData.fromUserProfilePicPath_ + "\", ";
					
					jsonString = jsonString + " \"toUserProfilePic\" : \""
					        + privateMessageData.toUserProfilePicPath_
					        + "\", ";

					jsonString = jsonString + " \"message\" : \""
					        + jsonEncode.encode( privateMessageData.message ) + "\", ";

					SimpleDateFormat dateFormat = new SimpleDateFormat(
					        "MMM dd,yyyy" );

					String dateStr = dateFormat.format( privateMessageData.createdTimestamp_ );

					jsonString = jsonString + " \"createdTime\" : \"" + dateStr + "\" ";

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

			String servletMsg = "Info::PrivateMessageServlet.doPost() - "
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


