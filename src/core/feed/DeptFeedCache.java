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

import core.dept.DeptKey;


/**
 * File: DeptFeedCache.java
 * 
 * Created on 15-Apr-2013 11:42:53 AM
 */

/*
 * This class act as cache for department feeds. It having all the department(currently
 * accessing department feeds) feeds in map. It having latest  100 feeds in memory.
 * Timer cleanup the cache frequently. It remove the department feeds from cache who are
 * not accessing the cache of last 1 minute.This is the singleton class.
 */

public class DeptFeedCache
{
	private Map<DeptKey, SortedMap<Long, DeptFeedData>> deptFeedMap_   = null;

	private static DeptFeedCache                         deptFeedCache_ = null;

	/*
	 * Method : DeptFeedCache -- constructor
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to initialize the deptFeedMap_ object 
	 * map.
	 */

	private DeptFeedCache()
	{
		// this map contain the all the departments(which are currently accessing 
		// the feeds by their users) feeds.

		deptFeedMap_ = new HashMap<DeptKey, SortedMap<Long, DeptFeedData>>( );
	}

	/*
	 * Method : instance()
	 * 
	 * Input : none
	 * 
	 * Return : DeptFeedCache obj
	 * 
	 * Purpose: It is used to implement the single-ton for DeptFeedCache class
	 */

	public static DeptFeedCache instance( )
	{
		if( deptFeedCache_ == null )
		{
			deptFeedCache_ = new DeptFeedCache( );
		}
		return deptFeedCache_;

	}

	/*
	 * Method : put()
	 * 
	 * Input : deptkey object and deptFeedMap object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to add the dept feeds to deptFeedMap_ object. And add the
	 * Timestamp details in deptFeedcacheinfo map.
	 * This method called when dept feeds not available in deptFeedMap_, at the dept user 
	 * send the dept feeds request, so we get the latest 100 dept feeds from database and 
	 * store this feeds in deptFeedMap_ using this function. 
	 */

	public int put( DeptKey deptKey, SortedMap<Long, DeptFeedData> feedMap )
	{

		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( deptKey == null || feedMap == null )
		{
			return -1;
		}

		try
		{
			// Add the feed map to user feed map
			deptFeedMap_.put( deptKey, feedMap );
			
			System.out.println( "Department feed map count(1) ="+deptFeedMap_.size( ) );

			// Add the timestamp details in cache info map

			DeptFeedCacheInfo cacheInfo = DeptFeedCacheInfo.instance( );

			cacheInfo.put( deptKey );
			
			System.out.println( "Put the feeds to cahce fetched from database. count = "+feedMap.size( ) +"Deptkey="+deptKey);

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::DeptFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::DeptFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::DeptFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::DeptFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( Exception ex )
		{
			String errMsg = "Exception::DeptFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		return result;
	}

	/*
	 * Method : put()
	 * 
	 * Input : DeptKey object and DeptFeedData object
	 * 
	 * Return : int
	 * 
	 * Purpose: deptFeedMap_ contain the latest department feeds, at the time
	 * department user post the new feeds, so we store the  posted feed in database after that
	 * we add that feed in deptFeedmap_ using this method. and also we delete the oldest feed for
	 * maintaining the leatest feed map.
	 */

	public int put( DeptKey deptKey, DeptFeedData deptFeedData )
	{
		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( deptKey == null || deptFeedData == null )
		{
			return -1;
		}

		try
		{
			SortedMap<Long, DeptFeedData> feedMap = deptFeedMap_.get( deptKey );

			if( feedMap == null )
			{
				return -1;
			}

			// Add the newly added feed into user feed map
			feedMap.put(  deptFeedData.deptFeedId_ , deptFeedData );
			
			/*if( feedMap.size( ) >100 )
			{
				// delete the oldest one feed from map
				long oldestKey = feedMap.lastKey( );
				
				System.out.println( "olderset key="+oldestKey );
				
				feedMap.remove(oldestKey);
			}*/
			
			// delete the oldest one feed from map
						long oldestKey = feedMap.lastKey( );
						
						System.out.println( "olderset key="+oldestKey );
						
						feedMap.remove(oldestKey);
			

			// Update the map with latest changes
			deptFeedMap_.put( deptKey, feedMap );
			
			System.out.println( "Department feed map count(2) ="+deptFeedMap_.size( ) );
			
			
				
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::DeptFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::DeptFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::DeptFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::DeptFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( Exception ex )
		{
			String errMsg = "Exception::DeptFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		return result;
	}

	/*
	 * Method : remove()
	 * 
	 * Input : DeptKey object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to remove the all the feeds for particular department. And
	 * also delete the timestamp details for this department from department feed info map.
	 */

	public int remove( DeptKey deptKey )
	{
		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( deptKey == null ) return -1;

		try
		{
			deptFeedMap_.remove( deptKey );

			DeptFeedCacheInfo cacheInfo = DeptFeedCacheInfo.instance( );

			cacheInfo.remove( deptKey );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::DeptFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::DeptFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::DeptFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::DeptFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::DeptFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		return result;
	}

	/*
	 * Method : remove()
	 * 
	 * Input : DeptKey object and feed id
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the particular feed from department feeds list
	 * that is already available in department feed cache.
	 */

	public int remove( DeptKey deptKey, long feedId )
	{
		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( deptKey == null ) return -1;

		try
		{
			// Get the department feeds
			SortedMap<Long, DeptFeedData> feedMap = deptFeedMap_.get( deptKey );

			if( feedMap == null )
			{
				return -1;
			}

			// remove feed from department feed map
			feedMap.remove(  feedId  );

			// Update the map with latest changes
			deptFeedMap_.put( deptKey, feedMap );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::DeptFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::DeptFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::DeptFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::DeptFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		return result;
	}
	

	/*
	 * Method : find()
	 * 
	 * Input : deptkey, list of dept feeds, feed starts from and how many feeds
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the department feeds from cache. Using from and
	 * count parameters, we filter the feeds. Filter feeds add to deptFeedList
	 * parameter so it is copied to caller classes.
	 */

	public int find( DeptKey deptKey, List<DeptFeedData> deptFeedList, int lastFeedId,
	        int count )
	{
		

		if( deptKey == null || deptFeedList == null ) return -1;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			SortedMap<Long, DeptFeedData> feedMap = deptFeedMap_.get( deptKey );
			
			// Department feeds available in dept feed cache
			if( feedMap == null )
			{
				System.out.println( "Department Feeds not available in cache." );
				
				return 3;
			}


			SortedMap<Long, DeptFeedData> filtermap = feedMap.tailMap( new Long( lastFeedId ) );

			// There is no needed feeds available in dept feed cache
			if( filtermap == null )
			{
				return 2;
			}

			int addedFeedCount = 0;

			for( Map.Entry<Long, DeptFeedData> entry : filtermap.entrySet( ) )
			{
				if( addedFeedCount >= count ) break; // This is because we need
													 // only <count> feeds only
				
				if( lastFeedId ==entry.getValue( ).deptFeedId_ )
					continue;
				
				System.out.println( "user feed id="+entry.getValue( ).deptFeedId_ );
				
				deptFeedList.add( entry.getValue( ) );

				addedFeedCount++;

			}
			
			System.out.println( "feedlist count="+deptFeedList.size( ) );

			if( deptFeedList.size( ) != count )
			{
				deptFeedList.clear( ); // Remove the elements if added from
									   // cache.
				
				System.out.println( "All the feeds not available from department feed cache." );

				return 1; // all feeds are not available in cache.(Some of them
						  // exist)
			}

			DeptFeedCacheInfo cacheInfo = DeptFeedCacheInfo.instance( );

			cacheInfo.put( deptKey );
			
			System.out.println( "All the feeds fetched from dept feed cache. Feeds count="+deptFeedList.size( ) );

			return 0;

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::DeptFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::DeptFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::DeptFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::DeptFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::DeptFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

	}
	
	
	/*
	 * Method : find()
	 * 
	 * Input : deptkey, list of dept feeds, latest feed id 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the latest feeds from timer.
	 */

	public int find( DeptKey deptKey, List<DeptFeedData> deptFeedList, long latestFeedId )
	{
		if( deptKey == null || deptFeedList == null ) return -1;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			System.out.println( "deptkey="+deptKey );
			
			System.out.println( "Dept map count="+deptFeedMap_.size( ));
			
			SortedMap<Long, DeptFeedData> feedMap = deptFeedMap_.get( deptKey );
			
			
			
			// Department feeds available in dept feed cache
			if( feedMap == null )
			{
				System.out.println( "Department Feeds not available in cache." );
				
				return 1;
			}

			System.out.println( "department feeds count="+feedMap.size( ) );
			
			SortedMap<Long, DeptFeedData> filtermap = feedMap.headMap( latestFeedId );
			
			System.out.println( "filter map="+ filtermap);

			// There is no latest feeds available in dept feed cache
			
			if( filtermap == null )
			{
				return 1;
			}


			for( Map.Entry<Long, DeptFeedData> entry : filtermap.entrySet( ) )
			{
				
				if( latestFeedId ==entry.getValue( ).deptFeedId_ )
					continue;
				
				System.out.println( "user feed id="+entry.getValue( ).deptFeedId_ );
				
				deptFeedList.add( entry.getValue( ) );

			}
			
			System.out.println( "feedlist count="+deptFeedList.size( ) );
			
			// update the dept cache info time stamp

			DeptFeedCacheInfo cacheInfo = DeptFeedCacheInfo.instance( );

			cacheInfo.put( deptKey );

			return 0;

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::DeptFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::DeptFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::DeptFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::DeptFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::DeptFeedCache.find() - " + ex;

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
	 * minute) department feeds from cache.
	 */

	public int cleanCache( )
	{
		
		System.out.println( "Cleaning cache" );
		
		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			List<DeptKey> idealDepts = new ArrayList<DeptKey>( );

			DeptFeedCacheInfo cacheInfo = DeptFeedCacheInfo.instance( );

			int result = cacheInfo.getIdleDepts( idealDepts );

			if( result == 0 )
			{
				for( DeptKey deptKey : idealDepts )
				{
					remove( deptKey );
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
			String errMsg = "Exception::DeptFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::DeptFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::DeptFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::DeptFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::DeptFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
	}

}
