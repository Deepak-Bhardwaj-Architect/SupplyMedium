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
package servlet.notification;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import utils.JSONEncode;
import utils.PathBuilder;
import utils.StringHolder;

import com.google.gson.Gson;

import core.notification.NotificationCenterData;
import utils.ErrorMaster;


/**
 * File:  NotificationComposer.java 
 *
 * Created on Sep 24, 2013 12:46:44 PM
 */
public class NotificationComposer
{
	
	/*
	 * Method : NotificationComposer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public NotificationComposer()
	{
		
	}
	
	
	/*
	 * Method: composeNotificationJSON
	 * 
	 * Input: int response, List of NotificationCenterData
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the notifications list object to json string
	 */
	
	
    public String composeNotificationJSON( int responseCode,
            List<NotificationCenterData> notifications )
    {
    	String jsonString = "";

		JSONEncode jsonEncode = new JSONEncode( );

		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );

		if( responseCode == 10300 || responseCode == 10310 || responseCode == 10320 || responseCode == 10330 )
		{
			/*String servletMsg = "Info::NotificationServlet.doPost() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );*/

			// errorMaster_.insert( "response code="+responseCode );

			if( responseCode == 10300 || responseCode == 10310)
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

				int notificationCount = notifications.size( );

				jsonString = "{ \"result\" : \"success\",  ";

				jsonString = jsonString + "\"notifications\" : [";

				for( int i = 0; i < notificationCount; i++ )
				{
					NotificationCenterData notificationCenterData = notifications.get( i );

					if( i != 0 ) jsonString = jsonString + ",";

					jsonString = jsonString + "{ \"notificationId\" : \""
					        + notificationCenterData.notificationId_ + "\", ";

					jsonString = jsonString + " \"senderName\" : \""
					        + jsonEncode.encode( notificationCenterData.senderName_ ) + "\", ";
					
					jsonString = jsonString + " \"senderKey\" : \""
					        + jsonEncode.encode( notificationCenterData.senderKey_.toString( ) ) + "\", ";
					
					jsonString = jsonString + " \"senderCompName\" : \""
					        + jsonEncode.encode( notificationCenterData.senderCompName_ ) + "\", ";
					
					String profileImagPath = notificationCenterData.senderprofilePicPath_;
					
					if( profileImagPath != null )
					{
						profileImagPath = profileImagPath.replace( "\\", "\\\\" );
						
						PathBuilder pathBuilder = new PathBuilder( );
						
						StringHolder webURL = new StringHolder( );
						
						pathBuilder.getWebURLFromRelativepath( profileImagPath, webURL );
						
						profileImagPath = webURL.value;
						
						pathBuilder = null;
						
						webURL = null;
					}
					
						
					
					jsonString = jsonString + " \"senderPhotoPath\" : \""
					        +  profileImagPath  + "\", ";
					
					jsonString = jsonString + " \"message\" : \""
					        + jsonEncode.encode( notificationCenterData.notificationMessage_ ) + "\", ";
					
					jsonString = jsonString + " \"notificationType\" : \""
					        + jsonEncode.encode( notificationCenterData.notificationType_ ) + "\", ";
					
					jsonString = jsonString + " \"notificationTypeId\" : \""
					        + jsonEncode.encode( notificationCenterData.notificationTypeId_ ) + "\", ";

					SimpleDateFormat dateFormat = new SimpleDateFormat(
					       "dd MMM yyyy ',' hh:mm aaa" );
					
					String dateStr = dateFormat.format( notificationCenterData.createdTimestamp_ );

					jsonString = jsonString + " \"createdDate\" : \"" + dateStr + "\", ";
					
					SimpleDateFormat dateFormat1 = new SimpleDateFormat(
						       "dd MMM yyyy ',' HH:mm:ss" );
						
						String dateStr1 = dateFormat1.format( notificationCenterData.createdTimestamp_ );

						jsonString = jsonString + " \"createdTimestamp\" : \"" + dateStr1 + "\" ";

					jsonString = jsonString + "}";

				}

				jsonString = jsonString + "]}";

				jsonEncode = null;
				
				//errorMaster_.insert( "json str=" + jsonString );

				return jsonString;

			}

		}
		else
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::NotificationComposer.doPost() - "
			        + "Request failed - Error code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );

			jsonMap.put( "message", responseString );

			jsonString = new Gson( ).toJson( jsonMap );

			jsonMap = null;

                        ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
			errorMaster_.insert( "json str=" + jsonString );

			return jsonString;

		}
	}

}
