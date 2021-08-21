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

import utils.ErrorLogger;
import utils.StringHolder;
import core.newsroom.WLMemberData;

/**
 * File:  WLMemberMapperTableUtils.java 
 *
 * Created on 30-Aug-2013 4:05:17 PM
 */
public class WLMemberMapperTableUtils
{
	
	/*
	 * watchlist_rel_id
	 * user_rel_key
	 * created_timestamp
	 */
	
	private String tableName_;

	/*
	 * Method : WLMemberMapperTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WLMemberMapperTableUtils()
	{
		this.tableName_ = "watchlist_member_mapper";
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : WLMemberData object, WLMemberMapperRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the WLMemberData object to
	 * WLMemberMapperRecord object. And add to WLMemberMapperRecord parameter so it is copied
	 * to caller method.
	 */
	
	public int dataToRecord( WLMemberData wlMemberData, WLMemberMapperRecord wlMemberMapperRecord )
	{
		wlMemberMapperRecord.userKey_ 		= wlMemberData.memberKey_.toString( );
		wlMemberMapperRecord.watchListId_	= wlMemberData.watchListId_;
	
		return 0;
	}
	
	/*
	 * Method : getFilterString( )
	 * 
	 * Input :  WLMemberMapperRecord object, StringHolder filterQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the WLMemberMapperRecord it form the filter query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getFilterString( WLMemberMapperRecord filter, StringHolder query )
	{
		try
		{
			query.value = filter.watchListId_ != -1 ?  
					" watchlist_rel_id='" + filter.watchListId_ + "' AND" : "";

			
			query.value += filter.userKey_ != null ? 
							" user_rel_key = '" + filter.userKey_ + "' AND" : "";
			
			query.value += filter.createdTimestamp_ != null ?  
					" created_timestamp='" + filter.createdTimestamp_ + "' AND" : "";


			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );

			
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::WLMemberMapperTableUtils.getFilterString() - " + ex );
			return -1;
		}
	}
	
	/*
	 * Method : rsToData( )
	 * 
	 * Input :  ResultSet
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the ResultSet object to List<WLMemberData> object.
	 * And add to members parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<WLMemberData> members )
	{
		
		try
        {
			while( rs.next( ) )
			{
    			WLMemberData memberData = new WLMemberData( );
    		
    			memberData.watchListId_				= rs.getInt( "watchlist_rel_id" );
    			memberData.memberKey_.email_		= rs.getString( "user_rel_key" );
    			
    			members.add( memberData );
    			memberData = null;
			}
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::WLMemberMapperTableUtils.rsToDataList() - " + e );
			return -1;
        }
		
		return 0;
	}

	/*
	 * Method : dataListToRecordList( )
	 * 
	 * Input : List<WLMemberData> obj, List<WLMemberMapperRecord> object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the List<WLMemberData>  object to
	 * List<WLMemberMapperRecord>  object. And add to List<WLMemberMapperRecord>  parameter so it is copied
	 * to caller method.
	 */
	
	public int dataListToRecordList( List<WLMemberData> membersData, List<WLMemberMapperRecord> membersRecord )
	{
		try
        {	
			for( WLMemberData data : membersData )
	        {
				WLMemberMapperRecord record = new WLMemberMapperRecord( );
				
				record.watchListId_ = data.watchListId_;
				
				record.userKey_ 	= data.memberKey_.toString( );
				
				membersRecord.add( record );
				
				record = null;
	        }
			
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
        	
			errLogger_.logMsg( "Exception::WLMemberMapperTableUtils.dataListToRecordList() - " + e );
			
			return -1;
        }
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : list of WLMemberMapperRecord
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using WLMemberMapperRecord and returns as
	 * string
	 */

	public String formInsertQuery( List<WLMemberMapperRecord> recordList )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query  + "watchlist_rel_id,user_rel_key)";
		
		query		 = query + " VALUES";
		
		int iterator = 0;
		
		for( WLMemberMapperRecord record : recordList )
        {
			query		 = query + "(";
			
			query		 = query + "'" + record.watchListId_ + "', ";
			
			query 		 = query + "'" + record.userKey_ + "'";
			
			query 	  	 = query + ")";
			
			iterator = iterator + 1;
			
			if( recordList.size( ) > iterator )
			{
				query = query + ", ";
			}
			
        }
		
		System.out.println( "Compound Query = " + query );
		
		return query;
	}
	

}
