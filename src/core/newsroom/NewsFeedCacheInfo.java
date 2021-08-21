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

import core.regn.UserProfileKey;

/**
 * File:  NewsFeedCacheInfo.java 
 *
 * Created on 19-Sep-2013 12:22:57 PM
 */

/*
 * This class contain news feed info details. That is it contain the all the
 * users last accessed cache timestamp. This is the singleton class.
 */


public class NewsFeedCacheInfo
{
	private Map<UserProfileKey, Timestamp> newsFeedInfoMap_;
	
	private Map<UserProfileKey, Long> newsFeedLimitCountMap_;

	private static NewsFeedCacheInfo       newsFeedCacheInfo_ = null;
	

	/*
	 * Method : NewsFeedCacheInfo -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public NewsFeedCacheInfo()
	{
		// This map contain the all the users last accessed timestamp of cache.
			this.newsFeedInfoMap_ = new HashMap<UserProfileKey, Timestamp>( );
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

	public static NewsFeedCacheInfo instance( )
	{
		if( newsFeedCacheInfo_ == null )
		{
			newsFeedCacheInfo_ = new NewsFeedCacheInfo( );
		}
		return newsFeedCacheInfo_;

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

	public void put( UserProfileKey key )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			Date date = new Date( );

			Timestamp curTimestamp = new Timestamp( date.getTime( ) );

			newsFeedInfoMap_.put( key, curTimestamp );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( Exception ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.add() - " + ex;

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

	public void remove( UserProfileKey key )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			newsFeedInfoMap_.remove( key );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( Exception ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}

	}
	
	
	/*
	 * Method : put()
	 * 
	 * Input : UserProfileKey, query limit count
	 * 
	 * Return : void
	 * 
	 * Purpose: It is used to add the user's friends feed fetch query limit count
	 */

	public void put( UserProfileKey key, Long count )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			newsFeedLimitCountMap_.put( key, count );

		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( Exception ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}

	}
	

	/*
	 * Method : remove()
	 * 
	 * Input : UserProfileKey, count
	 * 
	 * Return : void
	 * 
	 * Purpose: It is used to remove user's friends feed fetch query limit count
	 */

	public void remove( UserProfileKey key , Long count )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			newsFeedInfoMap_.remove( key );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( Exception ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.delete() - " + ex;

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

	public int getIdleUsers( List<UserProfileKey> userKeys )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		Date cdate = new Date( );

		UserProfileKey userKey;

		Timestamp lastTimestamp;

		int TDIV = 1000 * 60;

		long diffInMins = 0;

		try
		{

			for( Map.Entry<UserProfileKey, Timestamp> entry : newsFeedInfoMap_.entrySet( ) )
			{
				userKey = entry.getKey( );

				lastTimestamp = entry.getValue( );

				diffInMins = ( cdate.getTime( ) - lastTimestamp.getTime( ) ) / TDIV;

				if( diffInMins >= 1 )
				{
					userKeys.add( userKey );
				}

			}

			return 0;

		}

		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::NewsFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
	}

}
