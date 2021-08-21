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

package db.trans;

import java.sql.ResultSet;
import java.util.List;

import utils.ErrorLogger;
import utils.StringHolder;
import core.trans.TransRejectData;
import utils.ErrorMaster;

/**
 * File:  TransRejectTableUtils.java 
 *
 * Created on Jul 10, 2013 12:06:44 PM
 */
public class TransRejectTableUtils
{
/*	Table fields

	trans_reject_id	 	 	 	 	 	 	
	trans_id		 	 	 	 	 	 	
	trans_type_id		 	 	 	 	 	 	
	trans_type		 	 	 	 	 	 	 
	from_regn_key		 	 	 	 	 	 	 
	to_regn_key		 	 	 	 	 	 	 
	user_from		 	 	 	 	 	 	 
	user_to		 	 	 	 	 	 	 
	reject_reason		 	 	 	 	 	 	 
	created_timestamp	
*/
	
	private String tableName_;
	private ErrorLogger errLogger_;
        private static ErrorMaster errorMaster_ = null;


	
	/*
	 * Method : TransRejectTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransRejectTableUtils()
	{
		tableName_ = "trans_reject";
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : TransRejectData obj, TransRejectRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the TransRejectData object to
	 * TransRejectRecord object. And add to TransRejectRecord parameter so it is copied
	 * to caller method.
	 */
	
	public int dataToRecord( TransRejectData transRejectData, TransRejectRecord transRejectRecord )
	{
		try
        {
			transRejectRecord.createdTimestamp_ 	= transRejectData.createdTimestamp_;
			transRejectRecord.rejectReason_		 	= transRejectData.rejectReason_;
			
			transRejectRecord.from_			 		= transRejectData.from_.toString( );
			transRejectRecord.to_					= transRejectData.to_.toString( );
			transRejectRecord.transId_				= transRejectData.transId_;
			
			transRejectRecord.transType_			= transRejectData.transType_;
			transRejectRecord.transType_			= transRejectData.transType_;
			transRejectRecord.transTypeId_			= transRejectData.transTypeId_;
			transRejectRecord.userFrom_				= transRejectData.userFrom_.toString( );
			transRejectRecord.userTo_				= transRejectData.userTo_.toString( );
			
			return 0;
			
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransRejectTableUtils.dataToRecord() - " + e );
        	
        	return -1;
        }
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : TransRejectRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using TransRejectRecord and returns as
	 * string
	 */

	public String formInsertQuery( TransRejectRecord transRejectRecord )
	{	
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "trans_id, trans_type, trans_type_id, from_regn_key, ";
		query		 = query  + "to_regn_key, user_from, user_to, reject_reason) ";
		
		
		query		 = query + " VALUES";
		query		 = query + "(";
		
		query		 = query + "'" + transRejectRecord.transId_  + "', ";
		query		 = query + "'" + transRejectRecord.transType_  + "', ";
		query		 = query + "'" + transRejectRecord.transTypeId_  + "', ";
		
		query		 = query + "'" + transRejectRecord.from_ + "', ";
		query		 = query + "'" + transRejectRecord.to_ + "', ";
		query   	 = query + "'" + transRejectRecord.userFrom_ + "', ";
		
		query		 = query + "'" + transRejectRecord.userTo_ + "', ";
		query 		 = query + "'" + transRejectRecord.rejectReason_ + "' ";
		
		query 	  	 = query + ")";
 		
		errorMaster_.insert( "Query = " + query );
		
		return query;
	}
	
	/*
	 * Method : getFilterString( )
	 * 
	 * Input : TransRejectRecord transRejectRecord, StringHolder filterQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the transRejectRecord it form the filter query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getFilterString( TransRejectRecord filter, StringHolder query )
	{
		try
		{
			query.value = filter.transId_ != -1 ?  
							" trans_id='" + filter.transId_ + "' AND" : "";

			query.value += filter.transType_ != null ? 
							" trans_type= '" + filter.transType_ + "' AND" : "";

			query.value += filter.transTypeId_ != -1 ? 
							" trans_type_id='" + filter.transTypeId_ + "' AND" : "";

			query.value += filter.from_ != null ? 
							" from='" + filter.from_ + "' AND" : "";

			query.value += filter.to_ != null ? 
							" to='" + filter.to_ + "' AND" : "";

			query.value += filter.userFrom_ != null ? 
							" user_from='" + filter.userFrom_ + "' AND" : "";

			query.value += filter.userTo_ != null ?
							" user_to='" + filter.userTo_ + "' AND" : "";

			query.value += filter.rejectReason_ != null ?
							" reject_reason='" + filter.rejectReason_ + "' AND" : "";
			
			query.value += filter.createdTimestamp_ != null ?
							" created_timestamp='" + filter.createdTimestamp_ + "' AND" : "";
			
			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );

			
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::TransRejectTableUtils.getFilterString() - " + ex );
			return -1;
		}
	}
	
	/*
	 * Method : getUpdateString( )
	 * 
	 * Input : TransRejectRecord transRejectRecord, StringHolder updateQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the transRejectRecord it form the update query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getUpdateString( TransRejectRecord filter, StringHolder query )
	{
		try
		{
			query.value = filter.transId_ != -1 ?  
							" trans_id='" + filter.transId_ + "' ," : "";

        	query.value += filter.transType_ != null ? 
        					" trans_type= '" + filter.transType_ + "' ," : "";
        
        	query.value += filter.transTypeId_ != -1 ? 
        					" trans_type_id='" + filter.transTypeId_ + "' ," : "";
        
        	query.value += filter.from_ != null ? 
        					" from='" + filter.from_ + "' ," : "";
        
        	query.value += filter.to_ != null ? 
        					" to='" + filter.to_ + "' ," : "";
        
        	query.value += filter.userFrom_ != null ? 
        					" user_from='" + filter.userFrom_ + "' ," : "";
        
        	query.value += filter.userTo_ != null ?
        					" user_to='" + filter.userTo_ + "' ," : "";
        
        	query.value += filter.rejectReason_ != null ?
        					" reject_reason='" + filter.rejectReason_ + "' ," : "";
        	
        	query.value += filter.createdTimestamp_ != null ?
        					" created_timestamp='" + filter.createdTimestamp_ + "' ," : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 1 );

			errorMaster_.insert( "Filter String = " + query.value );
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::TransRejectTableUtils.getUpdateString() - " + ex );
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
	 * Purpose: It is used to convert the ResultSet object to List<TransRejectData> object.
	 * And add to transRejectList parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<TransRejectData> transRejectList )
	{
		try
        {
			while( rs.next( ) )
			{
				TransRejectData transRejectData = new TransRejectData( );
    			
    			transRejectData.transId_				= rs.getLong( "trans_id" );
    			transRejectData.transType_				= rs.getString( "trans_type" );
    			transRejectData.transTypeId_			= rs.getLong( "trans_type_id" );
    			transRejectData.from_.companyPhoneNo_	= rs.getString( "from_regn_key" );
    			transRejectData.to_.companyPhoneNo_		= rs.getString( "to_regn_key" );
    			transRejectData.userFrom_.email_		= rs.getString( "user_from" );
    			transRejectData.userTo_.email_			= rs.getString( "user_to" );
    			transRejectData.rejectReason_			= rs.getString( "reject_reason" );
    			transRejectData.createdTimestamp_ 		= rs.getTimestamp( "created_timestamp" );
    			
    			transRejectList.add( transRejectData );
    			
    			transRejectData = null;
			}
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::TransRejectTableUtils.rsToDataList() - " + e );
			return -1;
        }
	}
}
