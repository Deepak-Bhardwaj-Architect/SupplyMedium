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

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import utils.PathBuilder;
import utils.StringHolder;

import com.google.gson.Gson;

import core.chat.ChatEventData;
import core.chat.ChatMessageData;
import core.chat.ChatUserStatusData;
import utils.ErrorMaster;

/**
 * File:  ChatEventComposer.java 
 *
 * Created on 16-Oct-2013 9:21:56 AM
 */
public class ChatEventComposer
{

	/*
	 * Method : ChatEventComposer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public ChatEventComposer()
	{
	}

	/*
	 * Method: composeChatEventJSON
	 * 
	 * Input: int response, ChatEventData
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the ad list object to json string
	 */
	
	 public String composeChatEventJSON( int responseCode, ChatEventData chatEventData )
	 {
		String jsonSt = "";

		// JSONEncode jsonEncode = new JSONEncode( );

		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );

		if( responseCode == 10630 )
		{
			/*String servletMsg = "Info::ChatEventComposer.doPost() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );*/
			
			List< ChatUserStatusData > chatUserStatusList = chatEventData.chatUsersStatus_;
			
			List< ChatMessageData > chatMessages = chatEventData.chatMessages_;
			
			int usersCount = chatUserStatusList.size( );
			
			int messagesCount = chatMessages.size( );
			
			
			jsonSt += "{";
			jsonSt += "\"Events\":[";
			
			//errorMaster_.insert( "Chat User count="+usersCount );
			
			
			if( usersCount != 0 )
			{
				jsonSt +="{\"EventKey\":\"user_list\",";
				jsonSt +="\"ProviderName\":\"chat\",";
				jsonSt += "\"Data\":[";
				
				
				for( int i = 0; i < usersCount; i++ )
				{
					ChatUserStatusData chatUserStatusData = chatUserStatusList.get( i );

					if( i != 0 ) jsonSt = jsonSt + ",";
					
					jsonSt = jsonSt + "{ \"Id\" : \"" + chatUserStatusData.userKey_.toString( ) + "\", ";
					
					jsonSt = jsonSt + " \"Name\" : \"" + chatUserStatusData.name_ + "\", ";
					
					jsonSt = jsonSt + " \"url\" : null, ";
					
					String profileImagPath = chatUserStatusData.profilePicturePath_;
					
					if(  profileImagPath != null )
					{
						profileImagPath = profileImagPath.replace( "\\", "\\\\" );
						
						PathBuilder pathBuilder = new PathBuilder( );
							
						StringHolder webURL = new StringHolder( );
							
						pathBuilder.getWebURLFromRelativepath( profileImagPath, webURL );
							
						profileImagPath = webURL.value;
						
						jsonSt = jsonSt + " \"ProfilePictureUrl\" : \"" + profileImagPath + "\", ";
						
						pathBuilder = null;
						
						webURL = null;
						
					}
					else 
					{
						
						jsonSt = jsonSt + " \"ProfilePictureUrl\" :null, ";
					}
					
					jsonSt = jsonSt + " \"Status\" : \"" + chatUserStatusData.status_ + "\", ";
					
					jsonSt = jsonSt + " \"Email\" : \"" + chatUserStatusData.email_ + "\", ";
					
					jsonSt = jsonSt + " \"RoomId\" : \" chatjs-room \" ";

					jsonSt = jsonSt + "}";
				}
				
				
				jsonSt +="]},";
			}
			
			jsonSt +="{\"EventKey\":\"new-messages\",";
			jsonSt +="\"ProviderName\":\"chat\",";
			jsonSt += "\"Data\":[";
			
			for( int i = 0; i < messagesCount; i++ )
			{
				ChatMessageData chatMessageData = chatMessages.get( i );

				if( i != 0 ) jsonSt = jsonSt + ",";
				
				jsonSt = jsonSt + "{ \"UserFromId\" : \"" + chatMessageData.fromUserKey_.toString( ) + "\", ";
				
				jsonSt = jsonSt + " \"UserToId\" : \"" + chatMessageData.toUserKey_.toString( ) + "\", ";
				
				jsonSt = jsonSt + " \"Timestamp\" : null, ";
				
				jsonSt = jsonSt + " \"Message\" : \"" + chatMessageData.message_ + "\", ";
				
				jsonSt = jsonSt + " \"ClientGuid\" : \"10\", ";
				
				jsonSt = jsonSt + " \"DateTime\" : \"Date\" ";

				jsonSt = jsonSt + "}";
			}
			
			jsonSt +="]";
		
			
			jsonSt +="}";
			jsonSt +="]";
			jsonSt +="}";
			

			return jsonSt;

		}
		else
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::ChatEventComposer.doPost() - "
			        + "Request failed - Error code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );

			jsonMap.put( "message", responseString );

			jsonSt = new Gson( ).toJson( jsonMap );

			jsonMap = null;
ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
			errorMaster_.insert( "json str=" + jsonSt );

			return jsonSt;

		}
	}
}
