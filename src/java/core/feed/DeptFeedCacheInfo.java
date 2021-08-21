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

import core.dept.DeptKey;


/**
 * File: DeptFeedCacheInfo.java
 * 
 * Created on 15-Jun-2013 11:42:53 AM
 */

/*
 * This class contain dept feed info details. That is it contain the all the
 * department user last accessed cache timestamp. This is the singleton class.
 */

public class DeptFeedCacheInfo
{
	private Map<DeptKey, Timestamp> deptFeedInfoMap_;

	private static DeptFeedCacheInfo       deptFeedCacheInfo_ = null;

	/*
	 * Method : DeptFeedCacheInfo -- constructor
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to initialize the dept feed info map.
	 */

	private DeptFeedCacheInfo()
	{
		// This map contain the all the deptfeed last accessed timestamp of cache.
		this.deptFeedInfoMap_ = new HashMap<DeptKey, Timestamp>( );

	}

	/*
	 * Method : instance()
	 * 
	 * Input : none
	 * 
	 * Return : DeptFeedCacheInfo obj
	 * 
	 * Purpose: It is used to implement the single-ton for DeptFeedCacheInfo
	 */

	public static DeptFeedCacheInfo instance( )
	{
		if( deptFeedCacheInfo_ == null )
		{
			deptFeedCacheInfo_ = new DeptFeedCacheInfo( );
		}
		return deptFeedCacheInfo_;

	}

	/*
	 * Method : put()
	 * 
	 * Input : none
	 * 
	 * Return : void
	 * 
	 * Purpose: It is used to add the dept cache updated time stamp in dept feed
	 * info map.
	 */

	public void put( DeptKey key )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			Date date = new Date( );

			Timestamp curTimestamp = new Timestamp( date.getTime( ) );

			deptFeedInfoMap_.put( key, curTimestamp );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::DeptFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::DeptFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::DeptFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::DeptFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( Exception ex )
		{
			String errMsg = "Exception::DeptFeedCacheInfo.add() - " + ex;

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
	 * Purpose: It is used to remove the dept cache updated time stamp from dept
	 * feed info map.
	 */

	public void remove( DeptKey key )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			deptFeedInfoMap_.remove( key );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::DeptFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::DeptFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::DeptFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( Exception ex )
		{
			String errMsg = "Exception::DeptFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}

	}

	/*
	 * Method : getIdleDept()
	 * 
	 * Input : none
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to collect the all the dept which are not accessing
	 * the feed cache for last 1 minute.
	 */

	public int getIdleDepts( List<DeptKey> deptKeys )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		Date cdate = new Date( );

		DeptKey deptKey;

		Timestamp lastTimestamp;

		int TDIV = 1000 * 60;

		long diffInMins = 0;

		try
		{

			for( Map.Entry<DeptKey, Timestamp> entry : deptFeedInfoMap_.entrySet( ) )
			{
				deptKey = entry.getKey( );

				lastTimestamp = entry.getValue( );

				diffInMins = ( cdate.getTime( ) - lastTimestamp.getTime( ) ) / TDIV;

				if( diffInMins >= 1 )
				{
					deptKeys.add( deptKey );
				}

			}

			return 0;

		}

		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::DeptFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::DeptFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::DeptFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::DeptFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::DeptFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
	}
}
