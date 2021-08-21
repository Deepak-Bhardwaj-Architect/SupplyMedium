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
package servlet.chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.VariableElement;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import utils.PathBuilder;
import utils.StringHolder;


import com.google.gson.Gson;
import core.chat.ChatUserStatusData;

/**
 * File:  ChatUserComposer.java 
 *
 * Created on 16-Oct-2013 9:21:23 AM
 */
public class ChatUserComposer
{

	/*
	 * Method : ChatUserComposer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public ChatUserComposer()
	{
	}
	
	
	/*
	 * Method: composeChatUserJSON
	 * 
	 * Input: int response, List of ChatUserStatusData
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the ad list object to json string
	 */

    public String composeChatUserJSON( int responseCode, List< ChatUserStatusData > chatUserStatusList )
    {
    	
    	String jsonString = "";

		//JSONEncode jsonEncode = new JSONEncode( );

		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );

		if( responseCode == 10600  )
		{
			String servletMsg = "Info::ChatUserComposer.doPost() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			
			int userCount = chatUserStatusList.size( );
			
			System.out.println( "User count="+userCount );

			//jsonString = "{ \"result\" : \"success\",  ";

			jsonString = jsonString + "{ \"Users\" : [";

			for( int i = 0; i < userCount; i++ )
			{
				ChatUserStatusData chatUserStatusData = chatUserStatusList.get( i );

				if( i != 0 ) jsonString = jsonString + ",";

				jsonString = jsonString + "{ \"Id\" : \"" + chatUserStatusData.userKey_.toString( ) + "\", ";
				
				jsonString = jsonString + " \"Name\" : \"" + chatUserStatusData.name_ + "\", ";
				
				jsonString = jsonString + " \"url\" : null, ";
				
				String profileImagPath = chatUserStatusData.profilePicturePath_;
				
				if(  profileImagPath != null )
				{
					profileImagPath = profileImagPath.replace( "\\", "\\\\" );
					
					PathBuilder pathBuilder = new PathBuilder( );
						
					StringHolder webURL = new StringHolder( );
						
					pathBuilder.getWebURLFromRelativepath( profileImagPath, webURL );
						
					profileImagPath = webURL.value;
					
					jsonString = jsonString + " \"ProfilePictureUrl\" : \"" + profileImagPath + "\", ";
					
					pathBuilder = null;
					
					webURL = null;
					
				}
				else 
				{
					
					jsonString = jsonString + " \"ProfilePictureUrl\" :null, ";
				}
				
				jsonString = jsonString + " \"Status\" : \"" + chatUserStatusData.status_ + "\", ";
				
				jsonString = jsonString + " \"Email\" : \"" + chatUserStatusData.email_ + "\", ";
				
				jsonString = jsonString + " \"RoomId\" : \" chatjs-room \" ";

				jsonString = jsonString + "}";

			}

			jsonString = jsonString + "]}";

			//jsonEncode = null;

			System.out.println( "json str=" + jsonString );

			return jsonString;

		}
		else
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::ChatUserComposer.doPost() - "
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
