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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ErrorLogger;
import core.regn.UserProfileKey;

/**
 * File:  ChatMessageCache.java 
 *
 * Created on 15-Oct-2013 3:29:04 PM
 */
public class ChatMessageCache
{
	private Map<UserProfileKey, List<ChatMessageData>> chatMsgMap_   = null;

	private static ChatMessageCache chatMessageCache_ = null;

	/*
	 * Method : ChatMessageCache -- constructor
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to initialize the chatMsgMap_ object 
	 * map.
	 */

	private ChatMessageCache()
	{
		

		chatMsgMap_ = new HashMap<UserProfileKey, List<ChatMessageData>>( );
	}

	/*
	 * Method : instance()
	 * 
	 * Input : none
	 * 
	 * Return : ChatMessageCache obj
	 * 
	 * Purpose: It is used to implement the single-ton for ChatMessageCachee class
	 */

	public static ChatMessageCache instance( )
	{
		if( chatMessageCache_ == null )
		{
			chatMessageCache_ = new ChatMessageCache( );
		}
		return chatMessageCache_;

	}

	

	/*
	 * Method : put()
	 * 
	 * Input : UserProfileKey object and ChatMessageData object
	 * 
	 * Return : int
	 * 
	 * Purpose: chatMsgMap_ contain the particular users message, 
	 */

	public int put( UserProfileKey userKey, ChatMessageData chatMessageData )
	{
		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( userKey == null || chatMessageData == null )
		{
			return -1;
		}

		try
		{
			List<ChatMessageData> messages = chatMsgMap_.get( userKey );

			// Message map not availble for this user so create new list and add this message to that list
			if( messages == null )
			{
				messages = new ArrayList<ChatMessageData>( );
				
				messages.add( chatMessageData );
				
			}
			else
			{
				// Add the newly added feed into user feed map
				messages.add( chatMessageData );

			}
			
			// Update the map with latest changes
			chatMsgMap_.put( userKey, messages );

			
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::ChatMessageCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::ChatMessageCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::ChatMessageCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::ChatMessageCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( Exception ex )
		{
			String errMsg = "Exception::ChatMessageCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		return result;
	}

	/*
	 * Method : remove()
	 * 
	 * Input : UserProfileKey object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to remove the all the messages for particular user's  from cache.
	 */

	public int remove( UserProfileKey userKey )
	{
		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( userKey == null ) return -1;

		try
		{
			chatMsgMap_.remove( userKey );

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::ChatMessageCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::ChatMessageCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::ChatMessageCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::ChatMessageCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::ChatMessageCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		return result;
	}


	/*
	 * Method : get()
	 * 
	 * Input : user key, list of message for user
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the recent messages for the user using timestamp parameter.
	 * After getting the result, copied to messages parameter, so it available in caller classes.
	 */

	public int find( UserProfileKey userKey, List<ChatMessageData> messages )
	{
		

		if( userKey == null || messages == null ) return -1;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			
			List<ChatMessageData> _messages = new ArrayList<ChatMessageData>( );
			
			_messages = chatMsgMap_.get( userKey );
			
			if( _messages == null )
				return 0; // No messages available
			
			for( ChatMessageData chatMessageData : _messages )
            {
	            messages.add( chatMessageData );
            }
			
			remove( userKey );
			
			return 0;

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::ChatMessageCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::ChatMessageCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::ChatMessageCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::ChatMessageCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::ChatMessageCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

	}

}
