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
package db.newsroom;

import java.sql.ResultSet;
import java.util.List;


import core.newsroom.WatchListData;
import utils.ErrorLogger;
import utils.StringHolder;

/**
 * File:  WatchListTableUtils.java 
 *
 * Created on 30-Aug-2013 4:04:16 PM
 */
public class WatchListTableUtils
{
	private String tableName_;
	
	private ErrorLogger errLogger_;
	
	/*
	 * watchlist_id
	 * watchlist_name
	 * user_rel_key
	 * regn_rel_key
	 * created_timestamp
	 */
	

	/*
	 * Method : WatchListTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WatchListTableUtils()
	{
		this.tableName_ = "watchlist";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : WatchListData object to WatchListRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the WatchListData object to
	 * WatchListRecord object. And add to watchListRecord parameter so it is copied
	 * to caller method.
	 */
	
	public int dataToRecord( WatchListData watchListData, WatchListRecord watchListRecord )
	{
		try
        {
			watchListRecord.watchListId_ 	= watchListData.watchListId_;
			
			watchListRecord.watchListName_ 	= watchListData.watchListName_;
			
			watchListRecord.userKey_ 		= watchListData.ownerKey_.toString( );
			
			watchListRecord.regnKey_ 		= watchListData.regnKey_.toString( );
			
			watchListData.createdTimestamp_ = watchListData.createdTimestamp_;
			
			return 0;
			
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::WatchListTableUtils.dataToRecord() - " + e );
        	
        	return -1;
        }
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input :WatchListRecord object
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using WatchListRecord and returns as
	 * string
	 */

	public String formInsertQuery( WatchListRecord watchListRecord )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + " watchlist_name,user_rel_key,regn_rel_key) ";
		query		 = query + " VALUES";
		query		 = query + "(";
		
		query		 = query + "'" + watchListRecord.watchListName_  + "', ";
		query		 = query + "'" + watchListRecord.userKey_ +"',";
		query 		 = query + "'" + watchListRecord.regnKey_ + "' ";
		
		query 	  	 = query + ")";
 		
		//System.out.println( "Query = " + query );
		
		return query;
	}
	
	/*
	 * Method : getFilterString( )
	 * 
	 * Input :	WatchListRecord filter, StringHolder query
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the WatchListRecord it form the filter query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getFilterString( WatchListRecord filter, StringHolder query )
	{
		try
		{
			
			
			query.value += filter.watchListId_ != -1 ? 
							" watchlist_id= '" + filter.watchListId_ + "' AND" : "";

			query.value += filter.watchListName_ != null ? 
							" watchlist_name='" + filter.watchListName_ + "' AND" : "";
			

			query.value += filter.userKey_ != null ? 
							" user_rel_key='" + filter.userKey_ + "' AND" : "";

			query.value += filter.regnKey_ != null ? 
							" regn_rel_key='" + filter.regnKey_ + "' AND" : "";

			query.value += filter.createdTimestamp_ != null ? 
							" created_timestamp='" + filter.createdTimestamp_ + "' AND" : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );
			
		
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::WatchListTableUtils.getFilterString() - " + ex );
			return -1;
		}
	}
	
	/*
	 * Method : rsToDataList( )
	 * 
	 * Input :  ResultSet,WatchLists
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the ResultSet object to List<WatchListData> object.
	 * And add to watchLists parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<WatchListData> watchLists )
	{
		try
        {
			while( rs.next( ) )
			{
				WatchListData watchListData = new WatchListData( );

				watchListData.watchListId_ = rs.getInt( "watchlist_id" );
				watchListData.watchListName_ = rs.getString( "watchlist_name" );
				watchListData.ownerKey_.email_ = rs.getString( "user_rel_key" );
				watchListData.regnKey_.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				watchListData.createdTimestamp_ = rs.getTimestamp( "created_timestamp" );

				watchLists.add( watchListData );

				watchListData = null;
			}
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::WatchListTableUtils.rsToDataList() - " + e );
			return -1;
        }
	}
	

}
