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
 * File:  ChatUserCache.java 
 *
 * Created on 15-Oct-2013 3:28:31 PM
 */
public class ChatUserCache
{

	private Map< UserProfileKey, List< ChatUserStatusData> > chatUserMap_   = null;

	private static ChatUserCache chatUserCache_ = null;

	/*
	 * Method :  ChatUserCache -- constructor
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to initialize the chatUserMap_ object .
	 */

	private ChatUserCache()
	{
		
		chatUserMap_ = new HashMap<UserProfileKey, List< ChatUserStatusData > >( );
		
	}

	/*
	 * Method : instance()
	 * 
	 * Input : none
	 * 
	 * Return : ChatUserCache obj
	 * 
	 * Purpose: It is used to implement the single-ton for ChatUserCache class
	 */

	public static ChatUserCache instance( )
	{
		if( chatUserCache_ == null )
		{
			chatUserCache_ = new ChatUserCache( );
		}
		return chatUserCache_;

	}

	

	/*
	 * Method : put()
	 * 
	 * Input : UserProfileKey object and list of ChatUserStatusData object
	 * 
	 * Return : int
	 * 
	 * Purpose: chatUsergMap_ contain the all the connection of the user, 
	 */

	public int put( UserProfileKey userKey, List< ChatUserStatusData > chatUsers )
	{
		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( userKey == null || chatUsers == null )
		{
			return -1;
		}

		try
		{
			
			// Update the map with latest changes
			chatUserMap_.put( userKey, chatUsers );

			
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::ChatUserCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::ChatUserCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::ChatUserCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::ChatUserCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( Exception ex )
		{
			String errMsg = "Exception::ChatUserCache.add() - " + ex;

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
	 * Purpose: It is used to remove the all the users for particular user's from cache.
	 */

	public int remove( UserProfileKey userKey )
	{
		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( userKey == null ) return -1;

		try
		{
			chatUserMap_.remove( userKey );

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::ChatUserCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::ChatUserCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::ChatUserCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::ChatUserCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::ChatUserCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		return result;
	}


	/*
	 * Method : get()
	 * 
	 * Input : user key, list of user feeds, feed starts from and how many feeds
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the recent messages for the user using timestamp parameter.
	 * After getting the result, copied to messages parameter, so it available in caller classes.
	 */

	public int find( UserProfileKey userKey, List< ChatUserStatusData > chatUsers )
	{
		if( userKey == null || chatUsers == null ) return -1;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			if( chatUserMap_.get( userKey ) == null ) 
				return 0;
			
			List< ChatUserStatusData > _chatUsers = new ArrayList<ChatUserStatusData>( );
			
			_chatUsers = chatUserMap_.get( userKey );
			
			
			for( ChatUserStatusData chatUserStatusData : _chatUsers )
            {
	            chatUsers.add( chatUserStatusData );
            }
			
			_chatUsers.clear( );
			
			put( userKey, _chatUsers );
			
			_chatUsers = null;
			
			return 0;

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::ChatUserCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::ChatUserCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::ChatUserCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::ChatUserCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::ChatUserCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

	}
	
	/*
	 * Method : refresh
	 * 
	 * Input : none
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to refresh the map data.
	 */

	public int refresh()
	{
		int result = 0;
		
		System.out.println( "Refresh ChatUserCache" );
		
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		try
        {
			// Fetching all the userkeys
			
			for (Map.Entry< UserProfileKey, List< ChatUserStatusData> > entry : chatUserMap_.entrySet()) 
			{
				UserProfileKey key = entry.getKey();
				
				System.out.println( "key="+key.toString( ) );
				
				// Get the latest details about user using userkey
				ChatUser chatUser = new ChatUser( );
				
				List< ChatUserStatusData > chatUserList = new ArrayList< ChatUserStatusData >( );
				
				result = chatUser.get( key, chatUserList );
				
				chatUser = null;
				
				if( result != 10600 )
					continue;
				
				// Setting the latest details in map. so old details updated by this
				put( key, chatUserList );
				
				System.out.println( "friends list="+chatUserList.size( ) );
				
				chatUserList = null;
			}
	        return result;
        }
        catch( Exception e )
        {
        	String errMsg = "Exception::ChatUserCache.refresh - " + e;

			errorLogger.logMsg( errMsg );

			return -1;
        }
	}

}
