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

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ErrorLogger;

import core.regn.UserProfileKey;

/**
 * File: UserFeedCacheInfo.java
 * 
 * Created on 25-Apr-2013 11:42:53 PM
 */

/*
 * This class contain user feed info details. That is it contain the all the
 * users last accessed cache timestamp. This is the singleton class.
 */

public class UserFeedCacheInfo
{
	private Map<UserProfileKey, Timestamp> userFeedInfoMap_;

	private static UserFeedCacheInfo       userFeedCacheInfo_ = null;

	/*
	 * Method : UserFeedCache -- constructor
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to initialize the user feed info map.
	 */

	private UserFeedCacheInfo()
	{
		// This map contain the all the users last accessed timestamp of cache.
		this.userFeedInfoMap_ = new HashMap<UserProfileKey, Timestamp>( );

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

	public static UserFeedCacheInfo instance( )
	{
		if( userFeedCacheInfo_ == null )
		{
			userFeedCacheInfo_ = new UserFeedCacheInfo( );
		}
		return userFeedCacheInfo_;

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

			userFeedInfoMap_.put( key, curTimestamp );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::UserFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::UserFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::UserFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::UserFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( Exception ex )
		{
			String errMsg = "Exception::UserFeedCacheInfo.add() - " + ex;

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
			userFeedInfoMap_.remove( key );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::UserFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::UserFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::UserFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( Exception ex )
		{
			String errMsg = "Exception::UserFeedCacheInfo.delete() - " + ex;

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

			for( Map.Entry<UserProfileKey, Timestamp> entry : userFeedInfoMap_.entrySet( ) )
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
			String errMsg = "Exception::UserFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::UserFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::UserFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::UserFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::UserFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
	}
}
