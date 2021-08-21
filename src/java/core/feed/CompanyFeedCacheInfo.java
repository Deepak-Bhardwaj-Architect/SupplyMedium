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
import core.regn.CompanyRegnKey;


/**
 * File: CompanyFeedCacheInfo.java
 * 
 * Created on 15-Jun-2013 11:42:53 AM
 */

/*
 * This class contain company feed info details. That is it contain the all the
 * company user last accessed cache timestamp. This is the singleton class.
 */

public class CompanyFeedCacheInfo
{
	private Map<CompanyRegnKey, Timestamp> compFeedInfoMap_;

	private static CompanyFeedCacheInfo       compFeedCacheInfo_ = null;

	/*
	 * Method : CompanyFeedCacheInfo -- constructor
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to initialize the dept feed info map.
	 */

	private CompanyFeedCacheInfo()
	{
		// This map contain the all the compfeed last accessed timestamp of cache.
		this.compFeedInfoMap_ = new HashMap<CompanyRegnKey, Timestamp>( );

	}

	/*
	 * Method : instance()
	 * 
	 * Input : none
	 * 
	 * Return : CompanyFeedCacheInfo obj
	 * 
	 * Purpose: It is used to implement the single-ton for CompanyFeedCacheInfo
	 */

	public static CompanyFeedCacheInfo instance( )
	{
		if( compFeedCacheInfo_ == null )
		{
			compFeedCacheInfo_ = new CompanyFeedCacheInfo( );
		}
		return compFeedCacheInfo_;

	}

	/*
	 * Method : put()
	 * 
	 * Input : none
	 * 
	 * Return : void
	 * 
	 * Purpose: It is used to add the company cache updated time stamp in company feed
	 * info map.
	 */

	public void put( CompanyRegnKey key )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			Date date = new Date( );

			Timestamp curTimestamp = new Timestamp( date.getTime( ) );

			compFeedInfoMap_.put( key, curTimestamp );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::CompanyFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::CompanyFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::CompanyFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::CompanyFeedCacheInfo.add() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( Exception ex )
		{
			String errMsg = "Exception::CompanyFeedCacheInfo.add() - " + ex;

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
	 * Purpose: It is used to remove the company cache updated time stamp from company
	 * feed info map.
	 */

	public void remove( CompanyRegnKey key )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		try
		{
			compFeedInfoMap_.remove( key );
		}
		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::CompanyFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::CompanyFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::CompanyFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}

		catch( Exception ex )
		{
			String errMsg = "Exception::CompanyFeedCacheInfo.delete() - " + ex;

			errorLogger.logMsg( errMsg );

		}

	}

	/*
	 * Method : getIdleCompanies()
	 * 
	 * Input : none
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to collect the all the copanies which are not accessing
	 * the feed cache for last 1 minute.
	 */

	public int getIdleCompanies( List<CompanyRegnKey> regnKeys )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		Date cdate = new Date( );

		CompanyRegnKey regnKey;

		Timestamp lastTimestamp;

		int TDIV = 1000 * 60;

		long diffInMins = 0;

		try
		{

			for( Map.Entry<CompanyRegnKey, Timestamp> entry : compFeedInfoMap_.entrySet( ) )
			{
				regnKey = entry.getKey( );

				lastTimestamp = entry.getValue( );

				diffInMins = ( cdate.getTime( ) - lastTimestamp.getTime( ) ) / TDIV;

				if( diffInMins >= 1 )
				{
					regnKeys.add( regnKey );
				}

			}

			return 0;

		}

		catch( UnsupportedOperationException ex )
		{
			String errMsg = "Exception::CompanyFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( ClassCastException ex )
		{
			String errMsg = "Exception::CompanyFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
		catch( NullPointerException ex )
		{
			String errMsg = "Exception::CompanyFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( IllegalArgumentException ex )
		{
			String errMsg = "Exception::CompanyFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}

		catch( Exception ex )
		{
			String errMsg = "Exception::CompanyFeedCacheInfo.getIdealUsers() - " + ex;

			errorLogger.logMsg( errMsg );

			return -1;
		}
	}
}
