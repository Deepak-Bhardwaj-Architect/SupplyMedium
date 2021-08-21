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
package core.newsroom;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ErrorLogger;

/**
 * File:  WatchListFeedCacheInfo.java 
 *
 * Created on 21-Sep-2013 2:49:49 PM
 */
public class WatchListFeedCacheInfo
{

	private Map<Long, Timestamp> watchLisFeedInfoMap_;

	private static WatchListFeedCacheInfo       watchListFeedCacheInfo_ = null;
	

	/*
	 * Method : NewsFeedCacheInfo -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WatchListFeedCacheInfo()
	{
		// This map contain the all the users last accessed timestamp of cache.
			this.watchLisFeedInfoMap_ = new HashMap<Long, Timestamp>( );
	}
	
	/*
	 * Method : instance()
	 * 
	 * Input : none
	 * 
	 * Return : UserFeedCacheInfo obj
	 * 
	 * Purpose: It is used to implement the single-ton for UserFeedCacheInfo
	 */

	public static WatchListFeedCacheInfo instance( )
	{
		if( watchListFeedCacheInfo_ == null )
		{
			watchListFeedCacheInfo_ = new WatchListFeedCacheInfo( );
		}
		return watchListFeedCacheInfo_;

	}

	/*
	 * Method : put()
	 * 
	 * Input : none
	 * 
	 * Return : void
	 * 
	 * Purpose: It is used to add the user cache updated time stamp in user feed
	 * info map.
	 */

	public void put( Long watchListId )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			Date date = new Date( );

			Timestamp curTimestamp = new Timestamp( date.getTime( ) );

			watchLisFeedInfoMap_.put( watchListId, curTimestamp );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::WatchListFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::WatchListFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::WatchListFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::WatchListFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( Exception ex )
		{
			String errMsg = "Exception::WatchListFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}

	}

	/*
	 * Method : remove()
	 * 
	 * Input : none
	 * 
	 * Return : void
	 * 
	 * Purpose: It is used to remove the user cache updated time stamp from user
	 * feed info map.
	 */

	public void remove( Long watchListId )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			watchLisFeedInfoMap_.remove( watchListId );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::WatchListFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::WatchListFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::WatchListFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( Exception ex )
		{
			String errMsg = "Exception::WatchListFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}

	}

	/*
	 * Method : getIdleUsers()
	 * 
	 * Input : none
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to collect the all the users who are not accessing
	 * the feed cache for last 1 minute.
	 */

	public int getIdleUsers( List<Long> watchListIds )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		Date cdate = new Date( );
		
		Long watchListId;

		Timestamp lastTimestamp;

		int TDIV = 1000 * 60;

		long diffInMins = 0;

		try
		{

			for( Map.Entry<Long, Timestamp> entry : watchLisFeedInfoMap_.entrySet( ) )
			{
				watchListId = entry.getKey( );

				lastTimestamp = entry.getValue( );

				diffInMins = ( cdate.getTime( ) - lastTimestamp.getTime( ) ) / TDIV;

				if( diffInMins >= 1 )
				{
					watchListIds.add( watchListId );
				}

			}

			return 0;

		}

		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::WatchListFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::WatchListFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::watchListFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::WatchListFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::WatchListFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
	}

}
