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
package core.feed;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import utils.ErrorLogger;

import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File: UserFeedCache.java
 * 
 * Created on 22-Apr-2013 12:42:53 PM
 */

/*
 * This class act as cache for users feeds. It having all the users(currently
 * accessing user feeds) feeds in map. It having users 100 feeds in memory.
 * Timer cleanup the cache frequently. It remove the Users from cache who are
 * not accessing the cache of last 1 minute.This is the singleton class.
 */

public class UserFeedCache
{
	private Map<UserProfileKey, SortedMap<Long, UserFeedData>> userFeedMap_   = null;

	private static UserFeedCache                                 UserFeedCache_ = null;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : UserFeedCache -- constructor
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to initialize the userFeedMap_ object 
	 * map.
	 */

	private UserFeedCache()
	{
		// this map contain the all the users(who are currently accessing the
		// feeds) feeds.

		userFeedMap_ = new HashMap<UserProfileKey, SortedMap<Long, UserFeedData>>( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}

	/*
	 * Method : instance()
	 * 
	 * Input : none
	 * 
	 * Return : UserFeedCache obj
	 * 
	 * Purpose: It is used to implement the single-ton for UserFeedCache class
	 */

	public static UserFeedCache instance( )
	{
		if( UserFeedCache_ == null )
		{
			UserFeedCache_ = new UserFeedCache( );
		}
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		return UserFeedCache_;

	}

	/*
	 * Method : put()
	 * 
	 * Input : UserProfileKey object and userFeedmap object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to add the user feeds to userFeedMap_ object. And add the
	 * Timestamp details in userFeed.
	 * This method called when user feeds not available in userFeedMap_ at the user 
	 * send the user feeds request, so we get the 100 user feeds from database and 
	 * store this feeds in userFeedMap_ using this function. 
	 */

	public int put( UserProfileKey userKey, SortedMap<Long, UserFeedData> feedMap )
	{

		errorMaster_.insert( "Put the feeds to cahce fetched from database. count = "+feedMap.size( ) );

		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( userKey == null || feedMap == null )
		{
			return -1;
		}

		try
		{
			// Add the feed map to user feed map
			userFeedMap_.put( userKey, feedMap );

			// Add the timestamp details in cache info map

			UserFeedCacheInfo cacheInfo = UserFeedCacheInfo.instance( );

			cacheInfo.put( userKey );

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::UserFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::UserFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::UserFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::UserFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( Exception ex )
		{
			String errMsg = "Exception::UserFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		return result;
	}

	/*
	 * Method : put()
	 * 
	 * Input : UserProfileKey object and UserFeedData object
	 * 
	 * Return : int
	 * 
	 * Purpose: userFeedMap_ contain the particular users feeds, at the time
	 * user post the new feeds, so we store the  posted feed in database after that
	 * we add that feed in userFeedmap_ using this method.
	 */

	public int put( UserProfileKey userKey, UserFeedData userFeedData )
	{
		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( userKey == null || userFeedData == null )
		{
			return -1;
		}

		try
		{
			SortedMap<Long, UserFeedData> feedMap = userFeedMap_.get( userKey );

			if( feedMap == null )
			{
				return -1;
			}

			// Add the newly added feed into user feed map
			feedMap.put( new Long( userFeedData.userFeedId_ ), userFeedData );

			// Update the map with latest changes
			userFeedMap_.put( userKey, feedMap );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::UserFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::UserFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::UserFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::UserFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( Exception ex )
		{
			String errMsg = "Exception::UserFeedCache.add() - " + ex;

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
	 * Purpose: It is used to remove the all the feeds for particular user. And
	 * also delete the timestamp details for this user from user feed info map.
	 */

	public int remove( UserProfileKey userKey )
	{
		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( userKey == null ) return -1;

		try
		{
			userFeedMap_.remove( userKey );

			UserFeedCacheInfo cacheInfo = UserFeedCacheInfo.instance( );

			cacheInfo.remove( userKey );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::UserFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::UserFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::UserFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::UserFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::UserFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		return result;
	}

	/*
	 * Method : remove()
	 * 
	 * Input : UserProfileKey object and feed id
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the particular feed from user feeds list
	 * that is already available in user feed cache.
	 */

	public int remove( UserProfileKey userKey, int feedId )
	{
		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( userKey == null ) return -1;

		try
		{
			// Get the user feeds
			SortedMap<Long, UserFeedData> feedMap = userFeedMap_.get( userKey );

			if( feedMap == null )
			{
				return -1;
			}

			// remove feed from user feed map
			feedMap.remove( Integer.toString( feedId ) );

			// Update the map with latest changes
			userFeedMap_.put( userKey, feedMap );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::UserFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::UserFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::UserFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::UserFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		return result;
	}
	
/*	public static class CityNameComparator implements Comparator<String>
    {
        public int compare (String c1, String c2) {
            return c1.compareTo(c2);
        }
    }*/

	/*
	 * Method : get()
	 * 
	 * Input : user key, list of user feeds, feed starts from and how many feeds
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the user feeds from cache. Using from and
	 * count parameters, we filter the feeds. Filter feeds add to userFeedList
	 * parameter so it is copied to caller classes.
	 */

	public int find( UserProfileKey userKey, List<UserFeedData> userFeedList, int lastFeedId,
	        int count )
	{
		

		if( userKey == null || userFeedList == null ) return -1;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			SortedMap<Long, UserFeedData> feedMap = userFeedMap_.get( userKey );

			if( feedMap == null )
			{
				errorMaster_.insert( "User Feeds not available in cache." );
				
				return -1;
			}


			SortedMap<Long, UserFeedData> filtermap = feedMap.tailMap( new Long( lastFeedId ) );

			if( filtermap == null )
			{
				return -1;
			}

			int addedFeedCount = 0;

			for( Map.Entry<Long, UserFeedData> entry : filtermap.entrySet( ) )
			{
				if( addedFeedCount >= count ) break; // This is because we need
													 // only <count> feeds only
				
				if( lastFeedId ==entry.getValue( ).userFeedId_ )
					continue;
				
				errorMaster_.insert( "user feed id="+entry.getValue( ).userFeedId_ );
				
				userFeedList.add( entry.getValue( ) );

				addedFeedCount++;

			}
			
			errorMaster_.insert( "feedlist count="+userFeedList.size( ) );

			if( userFeedList.size( ) != count )
			{
				userFeedList.clear( ); // Remove the elements if added from
									   // cache.
				
				errorMaster_.insert( "All the feeds not available from user feed cache." );

				return 1; // all feeds are not available in cache.(Some of them
						  // exist)
			}

			UserFeedCacheInfo cacheInfo = UserFeedCacheInfo.instance( );

			cacheInfo.put( userKey );
			
			errorMaster_.insert( "All the feeds fetched from user feed cache. Feeds count="+userFeedList.size( ) );

			return 0;

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::UserFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::UserFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::UserFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::UserFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::UserFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

	}
	
	/*
	 * Method : get()
	 * 
	 * Input : user key, list of user feeds, feed starts from and how many feeds
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the user feeds from cache. Using from and
	 * count parameters, we filter the feeds. Filter feeds add to userFeedList
	 * parameter so it is copied to caller classes.
	 */

	public int find( UserProfileKey userKey, List<UserFeedData> userFeedList, int latestFeedId )
	{
		

		if( userKey == null || userFeedList == null ) return -1;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			SortedMap<Long, UserFeedData> feedMap = userFeedMap_.get( userKey );

			if( feedMap == null )
			{
				errorMaster_.insert( "User Feeds not available in cache." );
				
				return -1;
			}


			SortedMap<Long, UserFeedData> filtermap = feedMap.headMap( new Long( latestFeedId ) );

			if( filtermap == null )
			{
				return -1;
			}

			int addedFeedCount = 0;

			for( Map.Entry<Long, UserFeedData> entry : filtermap.entrySet( ) )
			{
				
				
				if( latestFeedId ==entry.getValue( ).userFeedId_ )
					continue;
				
				errorMaster_.insert( "user feed id="+entry.getValue( ).userFeedId_ );
				
				userFeedList.add( entry.getValue( ) );

				addedFeedCount++;

			}
			
			errorMaster_.insert( "feedlist count="+userFeedList.size( ) );

			UserFeedCacheInfo cacheInfo = UserFeedCacheInfo.instance( );

			cacheInfo.put( userKey );
			
			errorMaster_.insert( "All the feeds fetched from user feed cache. Feeds count="+userFeedList.size( ) );

			return 0;

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::UserFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::UserFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::UserFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::UserFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::UserFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

	}

	/*
	 * Method : cleanCache()
	 * 
	 * Input : none
	 * 
	 * Return : int
	 * 
	 * Purpose: It is the timer call it is called frequently. It is used to
	 * clean up the unwanted (User who are not accessing the cache for last 1
	 * minute) user feeds from cache.
	 */

	public int cleanCache( )
	{
		
		//errorMaster_.insert( "Cleaning cache" );
		
		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			List<UserProfileKey> idealUsers = new ArrayList<UserProfileKey>( );

			UserFeedCacheInfo cacheInfo = UserFeedCacheInfo.instance( );

			int result = cacheInfo.getIdleUsers( idealUsers );

			if( result == 0 )
			{
				for( UserProfileKey userKey : idealUsers )
				{
					remove( userKey );
				}

				return 0;
			}
			else
			{
				return -1;
			}

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::UserFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::UserFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::UserFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::UserFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::UserFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
	}

}
