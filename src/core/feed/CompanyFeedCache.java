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
import core.regn.CompanyRegnKey;


/**
 * File: CompanyFeedCache.java
 * 
 * Created on 15-Apr-2013 11:42:53 AM
 */

/*
 * This class act as cache for company internal feeds. It having all the internal page(currently
 * accessing department feeds) feeds in map. It having latest  100 feeds in memory.
 * Timer cleanup the cache frequently. It remove the internal feeds from cache who are
 * not accessing the cache of last 1 minute.This is the singleton class.
 */

public class CompanyFeedCache
{
	private Map<CompanyRegnKey, SortedMap<Long, CompanyFeedData>> compFeedMap_   = null;

	private static CompanyFeedCache                         compFeedCache_ = null;

	/*
	 * Method : CompanyFeedCache -- constructor
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to initialize the compFeedMap_ object 
	 * map.
	 */

	private CompanyFeedCache()
	{
		// this map contain the all the company internal(which are currently accessing 
		// the feeds by their users) feeds.

		compFeedMap_ = new HashMap<CompanyRegnKey, SortedMap<Long, CompanyFeedData>>( );
	}

	/*
	 * Method : instance()
	 * 
	 * Input : none
	 * 
	 * Return : CompanyFeedCache obj
	 * 
	 * Purpose: It is used to implement the single-ton for CompanyFeedCache class
	 */

	public static CompanyFeedCache instance( )
	{
		if( compFeedCache_ == null )
		{
			compFeedCache_ = new CompanyFeedCache( );
		}
		return compFeedCache_;

	}

	/*
	 * Method : put()
	 * 
	 * Input : CompanyRegnKey object and compFeedMap object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to add the company feeds to compFeedMap_ object. And add the
	 * Timestamp details in companyFeedcacheinfo map.
	 * This method called when company feeds not available in companyFeedMap_, at the company user 
	 * send the internal feeds request, so we get the latest 100 company feeds from database and 
	 * store this feeds in compFeedMap_ using this function. 
	 */

	public int put( CompanyRegnKey regnKey, SortedMap<Long, CompanyFeedData> feedMap )
	{

		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( regnKey == null || feedMap == null )
		{
			return -1;
		}

		try
		{
			// Add the feed map to company feed map
			compFeedMap_.put( regnKey, feedMap );
			
			System.out.println( "Department feed map count(1) ="+compFeedMap_.size( ) );

			// Add the timestamp details in cache info map

			CompanyFeedCacheInfo cacheInfo = CompanyFeedCacheInfo.instance( );

			cacheInfo.put( regnKey );
			
			System.out.println( "Put the feeds to cahce fetched from database. count = "+feedMap.size( ) +"Regnkey="+regnKey);

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( Exception ex )
		{
			String errMsg = "Exception::CompanyFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		return result;
	}

	/*
	 * Method : put()
	 * 
	 * Input : RegnKey object and CompanyFeedData object
	 * 
	 * Return : int
	 * 
	 * Purpose: compFeedMap_ contain the latest company feeds, at the time
	 * company user post the new feeds, so we store the  posted feed in database after that
	 * we add that feed in compFeedmap_ using this method. and also we delete the oldest feed for
	 * maintaining the latest feed map.
	 */

	public int put( CompanyRegnKey regnKey, CompanyFeedData compFeedData )
	{
		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( regnKey == null || compFeedData == null )
		{
			return -1;
		}

		try
		{
			SortedMap<Long, CompanyFeedData> feedMap = compFeedMap_.get( regnKey );

			if( feedMap == null )
			{
				return -1;
			}

			// Add the newly added feed into company feed map
			feedMap.put(  compFeedData.companyFeedId_ , compFeedData );
			
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
			compFeedMap_.put( regnKey, feedMap );
			
			System.out.println( "Department feed map count(2) ="+compFeedMap_.size( ) );
			
			
				
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( Exception ex )
		{
			String errMsg = "Exception::CompanyFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		return result;
	}

	/*
	 * Method : remove()
	 * 
	 * Input : RegnKey object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to remove the all the feeds for particular company. And
	 * also delete the timestamp details for this company from company feed info map.
	 */

	public int remove( CompanyRegnKey regnKey )
	{
		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( regnKey == null ) return -1;

		try
		{
			compFeedMap_.remove( regnKey );

			CompanyFeedCacheInfo cacheInfo = CompanyFeedCacheInfo.instance( );

			cacheInfo.remove( regnKey );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.add() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::CompanyFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		return result;
	}

	/*
	 * Method : remove()
	 * 
	 * Input : RegnKey object and feed id
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the particular feed from company feeds list
	 * that is already available in company feed cache.
	 */

	public int remove( CompanyRegnKey regnKey, long feedId )
	{
		int result = 0;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		if( regnKey == null ) return -1;

		try
		{
			// Get the company feeds
			SortedMap<Long, CompanyFeedData> feedMap = compFeedMap_.get( regnKey );

			if( feedMap == null )
			{
				return -1;
			}

			// remove feed from company feed map
			feedMap.remove(  feedId  );

			// Update the map with latest changes
			compFeedMap_.put( regnKey, feedMap );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::CompanyFeedCache.remove() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		return result;
	}
	

	/*
	 * Method : find()
	 * 
	 * Input : regnkey, list of company feeds, feed starts from and how many feeds
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the company feeds from cache. Using from and
	 * count parameters, we filter the feeds. Filter feeds add to compFeedList
	 * parameter so it is copied to caller classes.
	 */

	public int find( CompanyRegnKey regnKey, List<CompanyFeedData> compFeedList, int lastFeedId,
	        int count )
	{
		

		if( regnKey == null || compFeedList == null ) return -1;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			SortedMap<Long, CompanyFeedData> feedMap = compFeedMap_.get( regnKey );
			
			// Company feeds available in company feed cache
			if( feedMap == null )
			{
				System.out.println( "Company Feeds not available in cache." );
				
				return 3;
			}


			SortedMap<Long, CompanyFeedData> filtermap = feedMap.tailMap( new Long( lastFeedId ) );

			// There is no needed feeds available in company feed cache
			if( filtermap == null )
			{
				return 2;
			}

			int addedFeedCount = 0;

			for( Map.Entry<Long, CompanyFeedData> entry : filtermap.entrySet( ) )
			{
				if( addedFeedCount >= count ) break; // This is because we need
													 // only <count> feeds only
				
				if( lastFeedId ==entry.getValue( ).companyFeedId_ )
					continue;
				
				System.out.println( "company feed id="+entry.getValue( ).companyFeedId_ );
				
				compFeedList.add( entry.getValue( ) );

				addedFeedCount++;

			}
			
			System.out.println( "feedlist count="+compFeedList.size( ) );

			if( compFeedList.size( ) != count )
			{
				compFeedList.clear( ); // Remove the elements if added from
									   // cache.
				
				System.out.println( "All the feeds not available from company feed cache." );

				return 1; // all feeds are not available in cache.(Some of them
						  // exist)
			}

			CompanyFeedCacheInfo cacheInfo = CompanyFeedCacheInfo.instance( );

			cacheInfo.put( regnKey );
			
			System.out.println( "All the feeds fetched from company feed cache. Feeds count="+compFeedList.size( ) );

			return 0;

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::CompanyFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

	}
	
	
	/*
	 * Method : find()
	 * 
	 * Input : regnkey, list of company feeds, latest feed id 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the latest feeds from timer.
	 */

	public int find( CompanyRegnKey regnKey, List<CompanyFeedData> compFeedList, long latestFeedId )
	{
		if( regnKey == null || compFeedList == null ) return -1;

		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			System.out.println( "deptkey="+regnKey );
			
			System.out.println( "Dept map count="+compFeedMap_.size( ));
			
			SortedMap<Long, CompanyFeedData> feedMap = compFeedMap_.get( regnKey );
			
			
			
			// Company feeds available in company feed cache
			if( feedMap == null )
			{
				System.out.println( "Company Feeds not available in cache." );
				
				return 1;
			}

			System.out.println( "Company feeds count="+feedMap.size( ) );
			
			SortedMap<Long, CompanyFeedData> filtermap = feedMap.headMap( latestFeedId );
			
			System.out.println( "filter map="+ filtermap);

			// There is no latest feeds available in comp feed cache
			
			if( filtermap == null )
			{
				return 1;
			}


			for( Map.Entry<Long, CompanyFeedData> entry : filtermap.entrySet( ) )
			{
				
				if( latestFeedId ==entry.getValue( ).companyFeedId_ )
					continue;
				
				System.out.println( "user feed id="+entry.getValue( ).companyFeedId_ );
				
				compFeedList.add( entry.getValue( ) );

			}
			
			System.out.println( "feedlist count="+compFeedList.size( ) );
			
			// update the dept cache info time stamp

			CompanyFeedCacheInfo cacheInfo = CompanyFeedCacheInfo.instance( );

			cacheInfo.put( regnKey );

			return 0;

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.find() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::CompanyFeedCache.find() - " + ex;

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
	 * minute) Company feeds from cache.
	 */

	public int cleanCache( )
	{
		
		System.out.println( "Cleaning cache" );
		
		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			List<CompanyRegnKey> idealDepts = new ArrayList<CompanyRegnKey>( );

			CompanyFeedCacheInfo cacheInfo = CompanyFeedCacheInfo.instance( );

			int result = cacheInfo.getIdleCompanies( idealDepts );

			if( result == 0 )
			{
				for( CompanyRegnKey regnKey : idealDepts )
				{
					remove( regnKey );
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
			String errMsg = "Exception::CompanyFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::CompanyFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::CompanyFeedCache.cleanCache() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
	}

}
