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
import core.trans.TransInquireData;
import db.utils.DBDecode;
import db.utils.DBEncode;

/**
 * File:  TransInquireTableUtils.java 
 *
 * Created on 21-Jun-2013 3:07:33 PM
 */

public class TransInquireTableUtils
{

	/*	Table fields
	
	trans_id	 	 	 	 	 	 	
	trans_type,		 	 	 	 	 	 	 
	trans_type_id,		 	 	 	 	 	 	
	from,		 	 	 	 	 	 	 
	to,		 	 	 	 	 	 	 
	user_from,		 	 	 	 	 	 	 
	user_to,		 	 	 	 	 	 	 
	details,		 	 	 				 
	created_timestamp	*/
	
	private String tableName_;
	private ErrorLogger errLogger_;
	
	/*
	 * Method : TransInquireTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransInquireTableUtils()
	{
		tableName_ = "trans_inquire";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : TransInquireData obj, TransInquireRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the TransInquireData object to
	 * TransInquireRecord object. And add to transInquireRecord parameter so it is copied
	 * to caller method.
	 */
	
	public int dataToRecord( TransInquireData transInquireData, TransInquireRecord transInquireRecord )
	{
		try
        {
			DBEncode dbEncode = new DBEncode();
			
			transInquireRecord.createdTimestamp_ 	= transInquireData.createdTimestamp_;
			transInquireRecord.details_			 	= dbEncode.encode( transInquireData.details_ );
			
			transInquireRecord.from_			 	= transInquireData.from_.toString( );
			transInquireRecord.to_					= transInquireData.to_.toString( );
			transInquireRecord.transId_				= transInquireData.transId_;
			
			transInquireRecord.transType_			= transInquireData.transType_;
			transInquireRecord.transType_			= transInquireData.transType_;
			transInquireRecord.transTypeId_			= transInquireData.transTypeId_;
			transInquireRecord.userFrom_			= transInquireData.userFrom_.toString( );
			transInquireRecord.userTo_				= transInquireData.userTo_.toString( );
			
			dbEncode = null;
			
			return 0;
			
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::RFQTableUtils.dataToRecord() - " + e );
        	
        	return -1;
        }
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : TransInquireRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using TransInquireRecord and returns as
	 * string
	 */

	public String formInsertQuery( TransInquireRecord transInquireRecord )
	{	
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "trans_id, trans_type, trans_type_id, from_regn_key, ";
		query		 = query  + "to_regn_key, user_from, user_to, details) ";
		
		
		query		 = query + " VALUES";
		query		 = query + "(";
		
		query		 = query + "'" + transInquireRecord.transId_  + "', ";
		query		 = query + "'" + transInquireRecord.transType_  + "', ";
		query		 = query + "'" + transInquireRecord.transTypeId_  + "', ";
		
		query		 = query + "'" + transInquireRecord.from_ + "', ";
		query		 = query + "'" + transInquireRecord.to_ + "', ";
		query   	 = query + "'" + transInquireRecord.userFrom_ + "', ";
		
		query		 = query + "'" + transInquireRecord.userTo_ + "', ";
		query 		 = query + "'" + transInquireRecord.details_ + "' ";
		
		query 	  	 = query + ")";
 		
		System.out.println( "Query = " + query );
		
		return query;
	}
	
	/*
	 * Method : getFilterString( )
	 * 
	 * Input : TransInquireRecord transInquireRecord, StringHolder filterQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the transInquireRecord it form the filter query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getFilterString( TransInquireRecord filter, StringHolder query )
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

			query.value += filter.details_ != null ?
							" details='" + filter.details_ + "' AND" : "";
			
			query.value += filter.createdTimestamp_ != null ?
							" created_timestamp='" + filter.createdTimestamp_ + "' AND" : "";
			
			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );

			
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::TransInquireTableUtils.getFilterString() - " + ex );
			return -1;
		}
	}
	
	/*
	 * Method : getUpdateString( )
	 * 
	 * Input : TransInquireRecord transInquireRecord, StringHolder updateQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the transInquireRecord it form the update query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getUpdateString( TransInquireRecord filter, StringHolder query )
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
        
        	query.value += filter.details_ != null ?
        					" details='" + filter.details_ + "' ," : "";
        	
        	query.value += filter.createdTimestamp_ != null ?
        					" created_timestamp='" + filter.createdTimestamp_ + "' ," : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 1 );

			System.out.println( "Filter String = " + query.value );
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::TransInquireableUtils.getUpdateString() - " + ex );
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
	 * Purpose: It is used to convert the ResultSet object to List<TransInquireData> object.
	 * And add to transInquireList parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<TransInquireData> transInquireList )
	{
		try
        {
			while( rs.next( ) )
			{
				TransInquireData transInquireData = new TransInquireData( );
				
				DBDecode dbDecode = new DBDecode();
    			
    			transInquireData.transId_				= rs.getLong( "trans_id" );
    			transInquireData.transType_				= rs.getString( "trans_type" );
    			transInquireData.transTypeId_			= rs.getLong( "trans_type_id" );
    			transInquireData.from_.companyPhoneNo_	= rs.getString( "from_regn_key" );
    			transInquireData.to_.companyPhoneNo_	= rs.getString( "to_regn_key" );
    			transInquireData.userFrom_.email_		= rs.getString( "user_from" );
    			transInquireData.userTo_.email_			= rs.getString( "user_to" );
    			transInquireData.details_				= dbDecode.decode( rs.getString( "details" ) );
    			transInquireData.createdTimestamp_ 		= rs.getTimestamp( "created_timestamp" );
    			
    			transInquireList.add( transInquireData );
    			
    			dbDecode = null;
    			
    			transInquireData = null;
			}
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::TransInquireTableUtils.rsToDataList() - " + e );
			return -1;
        }
	}
}
