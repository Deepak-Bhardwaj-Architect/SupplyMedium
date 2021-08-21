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
import core.trans.POData;
import utils.ErrorMaster;

/**
 * File:  POTableUtils.java 
 *
 * Created on Jul 4, 2013 2:23:37 PM
 */

public class POTableUtils
{
	private String tableName_;
	
	private ErrorLogger errLogger_;
	
	/* Table fields:

    	po_id,
    	trans_Id,
    	po_num,		 	 	 	 	 	 	
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
	 * Method : POTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public POTableUtils( )
	{
		this.tableName_ = "po";
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : POData obj, PORecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the POData object to
	 * PORecord object. And add to PORecord parameter so it is copied
	 * to caller method.
	 */
	
	public int dataToRecord( POData poData, PORecord poRecord )
	{
		try
        {
			poRecord.action_ 	= poData.action_;
			poRecord.recurring_	= poData.recurring_;
			poRecord.poNum_		= poData.poNum_;
			
			poRecord.createdTimestamp_		= poData.createdTimestamp_;
			poRecord.isOutsideSupplier_		= poData.isOutsideSupplier_;
			poRecord.outsideSupplierNmae_	= poData.outsideSupplierName_;
                        poRecord.outsideSupplierEmail_	= poData.outsideSupplierEmail_;
                        
                        poRecord.outsideSuppliercountry	= poData.outsideSuppliercountry;
                        poRecord.outsideSupplierstate	= poData.outsideSupplierstate;
                        poRecord.outsideSuppliercity	= poData.outsideSuppliercity;
                        poRecord.outsideSupplieraddress	= poData.outsideSupplieraddress;
                        poRecord.outsideSupplierzipcode	= poData.outsideSupplierzipcode;
			
			poRecord.poDate_	= poData.poDate_;
			poRecord.poId_		= poData.poId_;
			poRecord.status_	= poData.status_;
			poRecord.from_		= poData.from_.toString( );
			poRecord.to_		= poData.to_.toString( );
			poRecord.transId_	= poData.transId_;
			poRecord.userFrom_	= poData.userFrom_.toString( );
			poRecord.userTo_	= poData.userTo_.toString( );
			
			poRecord.taxPercentage_ 	= poData.taxPercentage_;
			poRecord.totalListPrice_	= poData.totalListPrice_;
			poRecord.totalPrice_		= poData.totalPrice_;
			
			poRecord.isInvoiceCreated_		= poData.isInvoiceCreated_;
			
			return 0;
			
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::POTableUtils.dataToRecord() - " + e );
        	
        	return -1;
        }
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : PORecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using PORecord and returns as
	 * string
	 */

	public String formInsertQuery( PORecord poRecord )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "trans_id,po_num, from_regn_key, to_regn_key, ";
		query		 = query + "user_from, user_to, status, action, ";
		query		 = query + "is_outside_supplier, outside_supplier_email, recurring,is_invoice_created,";
		query		 = query + "po_date, total_list_price, tax_percentage, total_price,outside_supplier_address)";
		
		query		 = query + " VALUES";
		query		 = query + "(";
		
		query		 = query + "'" + poRecord.transId_  + "', ";
		query		 = query + "'" + poRecord.poNum_  + "', ";
		
		query		 = query + "'" + poRecord.from_ + "', ";
		query		 = query + "'" + poRecord.to_ + "', ";

		query   	 = query + "'" + poRecord.userFrom_ + "', ";
		query		 = query + "'" + poRecord.userTo_ + "', ";
		query 		 = query + "'" + poRecord.status_ + "', ";

		query 		 = query + "'" + poRecord.action_ + "', ";
		query 		 = query + "'" + poRecord.isOutsideSupplier_ + "', ";
		query 		 = query + "'" + poRecord.outsideSupplierEmail_ + "', ";

		query 		 = query + "'" + poRecord.recurring_ + "', ";
		query 		 = query + "'" + poRecord.isInvoiceCreated_ + "', ";
		query 		 = query + "'" + poRecord.poDate_ + "', ";
		query 		 = query + "'" + poRecord.totalListPrice_ + "', ";
		
		query 		 = query + "'" + poRecord.taxPercentage_ + "', ";
		query 		 = query + "'" + poRecord.totalPrice_ + "',";
		query 		 = query + "'" + poRecord.outsideSupplierNmae_+", "+poRecord.outsideSupplieraddress+", "+poRecord.outsideSuppliercity+", "+poRecord.outsideSupplierstate+", "+poRecord.outsideSuppliercountry+", "+poRecord.outsideSupplierzipcode+ "'";
		
		query 	  	 = query + ")";
 		
		errorMaster_.insert( "Query = " + query );
		          System.out.println("query......."+query);
		return query;
	}
	
	/*
	 * Method : getFilterString( )
	 * 
	 * Input :	PORecord filter, StringHolder query
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the poRecord it form the filter query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getFilterString( PORecord filter, StringHolder query )
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
			
			query.value += filter.poNum_ != null ? 
					" po_num='" + filter.poNum_ + "' AND" : "";

			query.value += filter.to_ != null ?
							" to='" + filter.to_ + "' AND" : "";

			query.value += filter.userFrom_ != null ?
							" user_from='" + filter.userFrom_ + "' AND" : "";
			
			query.value += filter.userTo_ != null ? 
							" user_to='" + filter.userTo_ + "' AND" : "";

			query.value += filter.isOutsideSupplier_ != -1 ?
							" is_outside_supplier='" + filter.isOutsideSupplier_ + "' AND" : "";

			query.value += filter.poDate_ != null ?
							" po_date='" + filter.poDate_ + "' AND" : "";
			
			query.value += filter.transId_ != -1 ?
							" trans_Id='" + filter.transId_ + "' AND" : "";
			
			query.value += filter.taxPercentage_ != 0.00f ?
							"tax_percentage='" + filter.taxPercentage_ + "' AND" : "";
			
			query.value += filter.totalListPrice_ != 0.00f ?
							"total_list_price='" + filter.totalListPrice_ + "' AND" : ""; 

			query.value += filter.totalPrice_ != 0.00f ? 
							"total_price='" + filter.totalPrice_ + "' AND" : "";
			
			query.value += filter.poId_ != -1 ?
							"po_id='" + filter.poId_ + "' AND" : "";
			
			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );
			
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::POTableUtils.getFilterString() - " + ex );
			return -1;
		}
	}
	
	/*
	 * Method : getUpdateString( )
	 * 
	 * Input : PORecord filter, StringHolder query
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the poRecord it form the update query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getUpdateString( PORecord filter, StringHolder query )
	{ 
		try
		{
			
			
			query.value = filter.transId_ !=-1 ?
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
			
			query.value += filter.poNum_ != null ? 
					" po_num='" + filter.poNum_ + "' ," : "";

			query.value += filter.to_ != null ?
							" to_regn_key='" + filter.to_ + "' ," : "";

			query.value += filter.userFrom_ != null ?
							" user_from='" + filter.userFrom_ + "' ," : "";
			
			query.value += filter.userTo_ != null ? 
							" user_to='" + filter.userTo_ + "' ," : "";

			query.value += filter.isOutsideSupplier_ != -1 ?
							" is_outside_supplier='" + filter.isOutsideSupplier_ + "' ," : "";

			query.value += filter.poDate_ != null ?
							" po_date='" + filter.poDate_ + "' ," : "";
			
			query.value += filter.taxPercentage_ != 0.00f ?
							" tax_percentage='" + filter.taxPercentage_ + "' ," : "";
			
			query.value += filter.totalListPrice_ != 0.00f ?
					" total_list_price='" + filter.totalListPrice_ + "' ," : "";
			
			query.value += filter.totalPrice_ != 0.00f ?
					" total_price='" + filter.totalPrice_ + "' ," : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value  =   query.value.substring( 0, query.value.length( ) - 1  ) +
			    				" WHERE po_id='"+filter.poId_ +"'";

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::POTableUtils.getUpdateString() - " + ex );
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
	 * Purpose: It is used to convert the ResultSet object to List<POData> object.
	 * And add to poList parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<POData> poList )
	{
		try
        {
			while( rs.next( ) )
			{			
    			POData poData = new POData( );
    			
    			poData.poId_	 				= rs.getLong( "po_id" );
    			poData.transId_					= rs.getLong( "trans_Id" );
    			poData.poNum_					= rs.getString( "po_num" );
    			poData.from_.companyPhoneNo_	= rs.getString( "from_regn_key" );
    			poData.to_.companyPhoneNo_		= rs.getString( "to_regn_key" );
    			poData.userFrom_.email_			= rs.getString( "user_from" );
    			poData.userTo_.email_			= rs.getString( "user_to" );
    			poData.status_					= rs.getString( "status" );
    			poData.action_					= rs.getString( "action" );
    			poData.isOutsideSupplier_ 		= rs.getInt( "is_outside_supplier" );
    			poData.outsideSupplierEmail_ 	= rs.getString( "outside_supplier_email" );
    			poData.recurring_	 			= rs.getString( "recurring" );
    			poData.poDate_	 				= rs.getDate( "po_date" );
    			poData.totalListPrice_			= rs.getDouble( "total_list_price" );
    			poData.taxPercentage_			= rs.getDouble( "tax_percentage" );
    			poData.totalPrice_				= rs.getDouble( "total_price" );
    			poData.isInvoiceCreated_		= rs.getInt( "is_invoice_created" );
    			poData.outsideSupplieraddress 		= rs.getString("outside_supplier_address" );
    			
    			poList.add( poData );
    			
    			poData = null;
			}
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::POTableUtils.rsToDataList() - " + e );
			return -1;
        }
	}
}
