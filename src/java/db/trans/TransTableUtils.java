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
import core.trans.TransData;
import utils.ErrorMaster;

/**
 * File:  TransTableUtils.java 
 *
 * Created on 21-Jun-2013 11:11:21 AM
 */
public class TransTableUtils
{
	private String tableName_;
	
	private ErrorLogger errLogger_;
        private static ErrorMaster errorMaster_ = null;


	
	/*
	 * Method : TransTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransTableUtils()
	{
		tableName_ = "transaction";
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : TransData obj, TransRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the TransData object to
	 * TransRecord object. And add to transRecord parameter so it is copied
	 * to caller method.
	 */
	
	public int dataToRecord( TransData transData, TransRecord transRecord )
	{
		try
        {
			transRecord.action_	= transData.action_;
			transRecord.createdTimestamp_ = transData.createdTimestamp_;
			transRecord.from_	= transData.from_.toString( );
			transRecord.status_ = transData.status_;
			
			errorMaster_.insert("trans status"+transRecord.status_ );
			
			transRecord.to_			= transData.to_.toString( );
			transRecord.transId_	= transData.transId_;
			transRecord.transType_	= transData.transType_;
			
			transRecord.transTypeId_= transData.transTypeId_;
			transRecord.userFrom_	= transData.userFrom_.toString( );
			transRecord.userTo_		= transData.userTo_.toString( );
			
			return 0;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransTableUtils.dataToRecord() - " + e );
        	
        	return -1;
        }
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : TransRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using TransRecord and returns as
	 * string
	 */

	public String formInsertQuery( TransRecord transRecord )
	{	
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "trans_id, trans_type, trans_type_id, action, ";
		query		 = query  + "from_regn_key, to_regn_key,  user_from, ";
		query		 = query + "user_to, status)";
		
		query		 = query + " VALUES";
		query		 = query + "(";
		
		query		 = query + "'" + transRecord.transId_ + "', ";
		query		 = query + "'" + transRecord.transType_  + "', ";
		query		 = query + "'" + transRecord.transTypeId_ + "', ";
		
		query		 = query + "'" + transRecord.action_ + "', ";
		query   	 = query + "'" + transRecord.from_ + "', ";
		query		 = query + "'" + transRecord.to_ + "', ";
	
		query 		 = query + "'" + transRecord.userFrom_ + "', ";
		query 		 = query + "'" + transRecord.userTo_ + "', ";
		
		query 		 = query + "'" + transRecord.status_+ "' ";
		
		query 	  	 = query + ")";
 		
		errorMaster_.insert( "Query = " + query );
		
		return query;
	}
	
	/*
	 * Method : getFilterString( )
	 * 
	 * Input : TransRecord transRecord, StringHolder filterQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the transRecord it form the filter query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getFilterString( TransRecord filter, StringHolder query )
	{
		try
		{
			query.value = filter.transId_ != -1 ?  
							" trans_id='" + filter.transId_ + "' AND" : "";

			query.value += filter.transType_ != null ? 
							" trans_type= '" + filter.transType_ + "' AND" : "";

			query.value += filter.transTypeId_ != -1 ? 
							" trans_type_id='" + filter.transTypeId_ + "' AND" : "";
			

			query.value += filter.action_ != null ? 
							" action='" + filter.action_ + "' AND" : "";

			query.value += filter.from_ != null ? 
							" from='" + filter.from_ + "' AND" : "";

			query.value += filter.to_ != null ? 
							" to='" + filter.to_ + "' AND" : "";

			query.value += filter.userFrom_ != null ?
							" user_from='" + filter.userFrom_ + "' AND" : "";

			query.value += filter.userTo_ != null ?
							" user_to='" + filter.userTo_ + "' AND" : "";
			
			query.value += filter.status_ != null ? 
							" status='" + filter.status_ + "' AND" : "";

			query.value += filter.createdTimestamp_ != null ?
							" created_timestamp='" + filter.createdTimestamp_ + "' AND" : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::TransTableUtils.getFilterString() - " + ex );
			return -1;
		}
	}
	
	/*
	 * Method : getUpdateString( )
	 * 
	 * Input : TransRecord transRecord, StringHolder updateQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the transRecord it form the update query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getUpdateString( TransRecord filter, StringHolder query )
	{
		try
		{
			query.value = filter.transId_ != -1 ?  
							" trans_id='" + filter.transId_ + "' ," : "";

        	query.value += filter.transType_ != null ? 
        					" trans_type= '" + filter.transType_ + "' ," : "";
        
        	query.value += filter.transTypeId_ != -1 ? 
        					" trans_type_id='" + filter.transTypeId_ + "' ," : "";
        	
        
        	query.value += filter.action_ != null ? 
        					" action='" + filter.action_ + "' ," : "";
        
        	query.value += filter.from_ != null ? 
        					" from='" + filter.from_ + "' ," : "";
        
        	query.value += filter.to_ != null ? 
        					" to='" + filter.to_ + "' ," : "";
        
        	query.value += filter.userFrom_ != null ?
        					" user_from='" + filter.userFrom_ + "' ," : "";
        
        	query.value += filter.userTo_ != null ?
        					" user_to='" + filter.userTo_ + "' ," : "";
        	
        	query.value += filter.status_ != null ? 
        					" status='" + filter.status_ + "' ," : "";
        
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
			errLogger_.logMsg( "Exception::TransTableUtils.getUpdateString() - " + ex );
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
	 * Purpose: It is used to convert the ResultSet object to List<TransData> object.
	 * And add to transList parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<TransData> transList )
	{
		try
        {
			while( rs.next( ) )
			{
    			TransData transData = new TransData( );
    			
    			transData.transId_				= rs.getLong( "trans_Id" );
    			transData.transType_			= rs.getString( "trans_type" );
    			transData.transTypeId_			= rs.getLong( "trans_type_id" );
    			transData.action_					= rs.getString( "action" );
    			transData.from_.companyPhoneNo_	= rs.getString( "from_regn_key" );
    			transData.to_.companyPhoneNo_		= rs.getString( "to_regn_key" );
    			transData.userFrom_.email_		= rs.getString( "user_from" );
    			transData.userTo_.email_			= rs.getString( "user_to" );
    			transData.status_					= rs.getString( "status" );
    			transData.createdTimestamp_ 		= rs.getTimestamp( "created_timestamp" );
    			
    			transList.add( transData );
    			
    			transData = null;
			}
			
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception:TransTableUtils.rsToDataList() - " + e );
			return -1;
        }
	}
}
