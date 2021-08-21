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
import core.trans.QuoteData;
import utils.ErrorMaster;

/**
 * File:  QuoteTableUtils.java 
 *
 * Created on Jul 3, 2013 10:06:44 AM
 */
public class QuoteTableUtils
{
	private String tableName_;
	
	private ErrorLogger errLogger_;
	
	/* Table fields:
	
    	quote_id,
    	trans_Id,
    	quote_ref,		 	 	 	 	 	 	
    	from_regn_key,		 	 	 	 	 	 	 
    	to_regn_key,		 	 	 	 	 	 	 
    	user_from,		 	 	 	 	 	 	 
    	user_to,		 	 	 	 	 	 	 
    	status,		 	 	 	 	 	 	 
    	action,		 	 	 	 	 	 	 
    	is_outside_supplier,		 	 	 	 	 	 	
    	outside_supplier_email,		 	 	 	 	 	 	 
    	recurring,		 	 	 	 	 	 	 
    	quote_date,
    	total_list_price,
    	tax_percentage,
    	total_price,		 	 	 	 	 	 	
    	created_timestamp 
	
	*/
        private static ErrorMaster errorMaster_ = null;


	
	/*
	 * Method : QuoteTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public QuoteTableUtils( )
	{
		this.tableName_ = "quote";
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : RFQData obj, RFQRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the QuoteData object to
	 * QuoteRecord object. And add to QuoteRecord parameter so it is copied
	 * to caller method.
	 */
	
	public int dataToRecord( QuoteData quoteData, QuoteRecord quoteRecord )
	{
		try
        {
			quoteRecord.action_ 	= quoteData.action_;
			quoteRecord.recurring_	= quoteData.recurring_;
			
			quoteRecord.createdTimestamp_		= quoteData.createdTimestamp_;
			quoteRecord.isOutsideSupplier_		= quoteData.isOutsideSupplier_;
			quoteRecord.outsideSupplierEmail_	= quoteData.outsideSupplierEmail_;
                        
                        quoteRecord.outsideSuppliercountry	= quoteData.outsideSuppliercountry;
                        quoteRecord.outsideSupplierstate	= quoteData.outsideSupplierstate;
                        quoteRecord.outsideSuppliercity	= quoteData.outsideSuppliercity;
                        quoteRecord.outsideSupplieraddress	= quoteData.outsideSupplieraddress;
                        quoteRecord.outsideSupplierzipcode	= quoteData.outsideSupplierzipcode;
			
			quoteRecord.quoteDate_	= quoteData.quoteDate_;
			quoteRecord.quoteId_	= quoteData.quoteId_;
			quoteRecord.quoteRef_	= quoteData.quoteRef_;
			quoteRecord.status_		= quoteData.status_;
			quoteRecord.from_		= quoteData.from_.toString( );
			quoteRecord.to_			= quoteData.to_.toString( );
			quoteRecord.transId_	= quoteData.transId_;
			quoteRecord.userFrom_	= quoteData.userFrom_.toString( );
			quoteRecord.userTo_		= quoteData.userTo_.toString( );
			
			quoteRecord.taxPercentage_ 	= quoteData.taxPercentage_;
			quoteRecord.totalListPrice_ = quoteData.totalListPrice_;
			quoteRecord.totalPrice_		= quoteData.totalPrice_;
			
			quoteRecord.isPOCreated_	= quoteData.isPOCreated_;
			
			return 0;
			
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::QuoteTableUtils.dataToRecord() - " + e );
        	
        	return -1;
        }
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : QuoteRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using QuoteRecord and returns as
	 * string
	 */

	public String formInsertQuery( QuoteRecord quoteRecord )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "trans_id,quote_ref, from_regn_key, to_regn_key, ";
		query		 = query + "user_from, user_to, status, action, ";
		query		 = query + "is_outside_supplier, outside_supplier_email, recurring,is_po_created, ";
		query		 = query + "quote_date, total_list_price, tax_percentage, total_price,outside_supplier_address)";
		
		query		 = query + " VALUES";
		query		 = query + "(";
		
		query		 = query + "'" + quoteRecord.transId_  + "', ";
                //System.out.println(" quoteRecord.transId_------------------------->"+ quoteRecord.transId_);
		query		 = query + "'" + quoteRecord.quoteRef_  + "', ";
		query		 = query + "'" + quoteRecord.from_ + "', ";
		query		 = query + "'" + quoteRecord.to_ + "', ";

		query   	 = query + "'" + quoteRecord.userFrom_ + "', ";
		query		 = query + "'" + quoteRecord.userTo_ + "', ";
		query 		 = query + "'" + quoteRecord.status_ + "', ";

		query 		 = query + "'" + quoteRecord.action_ + "', ";
		query 		 = query + "'" + quoteRecord.isOutsideSupplier_ + "', ";
		query 		 = query + "'" + quoteRecord.outsideSupplierEmail_ + "', ";

		query 		 = query + "'" + quoteRecord.recurring_ + "', ";
		query 		 = query + "'" + quoteRecord.isPOCreated_ + "', ";
		query 		 = query + "'" + quoteRecord.quoteDate_ + "', ";
		query 		 = query + "'" + quoteRecord.totalListPrice_ + "', ";
		
		query 		 = query + "'" + quoteRecord.taxPercentage_ + "', ";
		query 		 = query + "'" + quoteRecord.totalPrice_ + "',";
                query 		 = query + "'" + quoteRecord.outsideSupplieraddress+", "+quoteRecord.outsideSuppliercity+", "+quoteRecord.outsideSupplierstate+", "+quoteRecord.outsideSuppliercountry+", "+quoteRecord.outsideSupplierzipcode+ "'";
		
		
		query 	  	 = query + ")";
 		
		errorMaster_.insert( "Query = " + query );
		
		return query;
	}
	
	/*
	 * Method : getFilterString( )
	 * 
	 * Input : RFQRecord filter, StringHolder query
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the rfqRecord it form the filter query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getFilterString( QuoteRecord filter, StringHolder query )
	{
		try
		{
			query.value = filter.createdTimestamp_ != null ?  
							" created_timestamp='" + filter.createdTimestamp_ + "' AND" : "";

			query.value += filter.action_ != null ? 
							" action= '" + filter.action_ + "' AND" : "";

			query.value += filter.from_ != null ? 
							" from='" + filter.from_ + "' AND" : "";
			
			query.value += filter.outsideSupplierEmail_ != null ? 
							" outside_supplier_email='" + filter.outsideSupplierEmail_ + "' AND" : "";

			query.value += filter.recurring_ != null ? 
							" recurring='" + filter.recurring_ + "' AND" : "";

			query.value += filter.status_ != null ? 
							" status='" + filter.status_ + "' AND" : "";
			
			query.value += filter.quoteRef_ != null ? 
					" quote_ref='" + filter.quoteRef_ + "' AND" : "";

			query.value += filter.to_ != null ?
							" to='" + filter.to_ + "' AND" : "";

			query.value += filter.userFrom_ != null ?
							" user_from='" + filter.userFrom_ + "' AND" : "";
			
			query.value += filter.userTo_ != null ? 
							" user_to='" + filter.userTo_ + "' AND" : "";

			query.value += filter.isOutsideSupplier_ != -1 ?
							" is_outside_supplier='" + filter.isOutsideSupplier_ + "' AND" : "";

			query.value += filter.quoteDate_ != null ?
							" quote_date='" + filter.quoteDate_ + "' AND" : "";
			
			query.value += filter.transId_ != -1 ?
							" trans_Id='" + filter.transId_ + "' AND" : "";
			
			query.value += filter.taxPercentage_ != 0.00f ?
							"tax_percentage='" + filter.taxPercentage_ + "' AND" : "";
			
			query.value += filter.totalListPrice_ != 0.00f ?
							"total_list_price='" + filter.totalListPrice_ + "' AND" : ""; 

			query.value += filter.totalPrice_ != 0.00f ? 
							"total_price='" + filter.totalPrice_ + "' AND" : "";
			
			query.value += filter.quoteId_ != -1 ?
							"quote_id='" + filter.quoteId_ + "' AND" : "";
			
			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );
			
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::QuoteTableUtils.getFilterString() - " + ex );
			return -1;
		}
	}
	
	/*
	 * Method : getUpdateString( )
	 * 
	 * Input : RFQRecord filter, StringHolder query
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the rfqRecord it form the update query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getUpdateString( QuoteRecord filter, StringHolder query )
	{ 
		
		
		try
		{
			query.value = filter.transId_ != -1 ?
					" trans_Id='" + filter.transId_ + "' ," : "";
			
			query.value += filter.action_ != null ? 
							" action= '" + filter.action_ + "' ," : "";

			query.value += filter.from_ != null ? 
							" from_regn_key='" + filter.from_ + "' ," : "";
			

			query.value += filter.outsideSupplierEmail_ != null ? 
							" outside_supplier_email='" + filter.outsideSupplierEmail_ + "' ," : "";

			query.value += filter.recurring_ != null ? 
							" recurring='" + filter.recurring_ + "' ," : "";

			query.value += filter.status_ != null ? 
							" status='" + filter.status_ + "' ," : "";
			
			query.value += filter.quoteRef_ != null ? 
					" quote_ref='" + filter.quoteRef_ + "' ," : "";

			query.value += filter.to_ != null ?
							" to_regn_key='" + filter.to_ + "' ," : "";

			query.value += filter.userFrom_ != null ?
							" user_from='" + filter.userFrom_ + "' ," : "";
			
			query.value += filter.userTo_ != null ? 
							" user_to='" + filter.userTo_ + "' ," : "";

			query.value += filter.isOutsideSupplier_ != -1 ?
							" is_outside_supplier='" + filter.isOutsideSupplier_ + "' ," : "";

			query.value += filter.quoteDate_ != null ?
							" quote_date='" + filter.quoteDate_ + "' ," : "";
			
			query.value += filter.taxPercentage_ != 0.00f ?
							" tax_percentage='" + filter.taxPercentage_ + "' ," : "";
			
			query.value += filter.totalListPrice_ != 0.00f ?
					" total_list_price='" + filter.totalListPrice_ + "' ," : "";
			
			query.value += filter.totalPrice_ != 0.00f ?
					" total_price='" + filter.totalPrice_ + "' ," : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value  =   query.value.substring( 0, query.value.length( ) - 1  ) +
			    				" WHERE quote_id='"+filter.quoteId_+"'";

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::QuoteTableUtils.getUpdateString() - " + ex );
			return -1;
		}
	}
	
	
	/*
	 * Method : rsToDataList( )
	 * 
	 * Input :  ResultSet
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the ResultSet object to List<QuoteData> object.
	 * And add to quoteList parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<QuoteData> quoteList )
	{
		try
        {
			while( rs.next( ) )
			{			
    			QuoteData quoteData = new QuoteData( );
    			
    			quoteData.quoteId_ 					= rs.getLong( "quote_id" );
    			quoteData.transId_					= rs.getLong( "trans_Id" );
    			quoteData.quoteRef_					= rs.getString( "quote_ref" );
    			quoteData.from_.companyPhoneNo_		= rs.getString( "from_regn_key" );
    			quoteData.to_.companyPhoneNo_		= rs.getString( "to_regn_key" );
    			quoteData.userFrom_.email_			= rs.getString( "user_from" );
    			quoteData.userTo_.email_			= rs.getString( "user_to" );
    			quoteData.status_					= rs.getString( "status" );
    			quoteData.action_					= rs.getString( "action" );
    			quoteData.isOutsideSupplier_ 		= rs.getInt( "is_outside_supplier" );
    			quoteData.outsideSupplierEmail_ 	= rs.getString( "outside_supplier_email" );
    			quoteData.recurring_	 			= rs.getString( "recurring" );
    			quoteData.quoteDate_ 				= rs.getDate( "quote_date" );
    			quoteData.totalListPrice_			= rs.getDouble( "total_list_price" );
    			quoteData.taxPercentage_			= rs.getDouble( "tax_percentage" );
    			quoteData.totalPrice_				= rs.getDouble( "total_price" );
    			quoteData.createdTimestamp_ 		= rs.getTimestamp( "created_timestamp" );
    			quoteData.isPOCreated_		 		= rs.getInt( "is_po_created" );
    			
    			
    			quoteList.add( quoteData );
    			
    			quoteData = null;
			}
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::QuoteTableUtils.rsToDataList() - " + e );
			return -1;
        }
	}
}
