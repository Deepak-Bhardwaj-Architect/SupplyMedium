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
package core.chat;

import core.regn.UserProfileKey;

/**
 * File:  ChatEvent.java 
 *
 * Created on 15-Oct-2013 3:29:20 PM
 */

public class ChatEvent
{

	/*
	 * Method : ChatEvent -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public ChatEvent()
	{
		
	}
	
	/*
	 * Method : get
	 * 
	 * Input  : userkey and ChatEventData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch user latest event in cache for the requested user . And assign to chatEventData 
	 * parameter it is copied to caller classes.
	 */
	
	public int get ( UserProfileKey userKey, ChatEventData chatEventData )
	{
		
		int result = 0;
		
		chatEventData.userKey_ = userKey;
		
		// Fteching all the users connections from ChatUserCache
		ChatUserCache chatUserCache = ChatUserCache.instance( );
		
		result = chatUserCache.find( userKey, chatEventData.chatUsersStatus_ );
		
		if( result != 0)
			return 10631;
		
		/*if( result == 0 )
		{
			System.out.println( "userlist available in cache: Count="+chatEventData.chatUsersStatus_.size( ) );
			
			return 10630;  // UserList available in cache
		}
		else if( result == 1 )  // UserList not available in cache
		{
			// Fetch the userlist from db
			ChatUser chatUser = new ChatUser( );
			
			result = chatUser.get( userKey, chatEventData.chatUsersStatus_ );
			
			chatUser = null;
			
			// Put the userlist to cache
			chatUserCache.put( userKey, chatEventData.chatUsersStatus_ );
			
			if( result != 10600 )
				return 10631;
		}
		else
		{
			return 10632;  // Exception occurred
		}*/
		
		
		
		// Fetch the all the messages for user, from ChatMessageCache
		ChatMessageCache chatMessageCache = ChatMessageCache.instance( );
		
		result = chatMessageCache.find( userKey, chatEventData.chatMessages_ );
		
		if( result != 0 )
			return 10633;
		
		return 10630;
		
	}


}
