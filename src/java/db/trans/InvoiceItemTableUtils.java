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
import core.trans.InvoiceItemData;
import utils.ErrorMaster;

/**
 * File:  InvoiceItemTableUtils.java 
 *
 * Created on Jul 6, 2013 2:29:17 PM
 */

public class InvoiceItemTableUtils
{
	private String tableName_;
        private static ErrorMaster errorMaster_ = null;


	
	/*Table fields
 
 	invoice_item_id
  	invoice_id,		 	 	 	 	 	 	
	trans_id,		 	 	 	 	 	 	
	item_desc,		 	 	 				 
	part_no,		 	 	 	 	 	 	 
	quantity_ordered,		 	 	 	 	 	 	
	ship_date,		 	
	quantity_ordered_unit, 	 	 	 	 			 	 	 	 	 	 	 
	price,		 	 	 	 	 	 	
	currency,		 	 	 	 	 	 	 
	multiplier,		 
	quantity_shipped,
	quantity_shipped_unit,	 	 	 	 	 	
	created_timestamp
  
 */
	/*
	 * Method : InvoiceItemTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public InvoiceItemTableUtils()
	{
		this.tableName_ = "invoice_item";
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : InvoiceItemData obj, InvoiceItemRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the InvoiceItemData object to
	 * InvoiceItemRecord object. And add to InvoiceItemRecord parameter so it is copied
	 * to caller method.
	 */
	
	public int dataToRecord( InvoiceItemData invoiceItemData, InvoiceItemRecord invoiceItemRecord )
	{
		invoiceItemRecord.createdTimestamp_ 	= invoiceItemData.createdTimestamp_;
		invoiceItemRecord.itemDesc_				= invoiceItemData.itemDesc_;
		invoiceItemRecord.partNo_				= invoiceItemData.partNo_;
		
		invoiceItemRecord.quantityOrdered_		= invoiceItemData.quantityOrdered_;
		invoiceItemRecord.quantityOrderedUnit_	= invoiceItemData.quantityOrderedUnit_;
		invoiceItemRecord.invoiceId_			= invoiceItemData.invoiceId_;
		
		invoiceItemRecord.shipDate_				= invoiceItemData.shipDate_;
		invoiceItemRecord.transId_				= invoiceItemData.transId_;
		invoiceItemRecord.quantityShipped_		= invoiceItemData.quantityShipped_;
		
		invoiceItemRecord.quantityShippedUnit_	= invoiceItemData.quantityShippedUnit_;
		
		invoiceItemRecord.price_				= invoiceItemData.price_;
		invoiceItemRecord.currency_				= invoiceItemData.currency_;
		invoiceItemRecord.multiplier_			= invoiceItemData.multiplier_;
		
		return 0;
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : InvoiceItemRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using InvoiceItemRecord and returns as
	 * string
	 */

	public String formInsertQuery( InvoiceItemRecord invoiceItemRecord )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "invoice_id, trans_id, item_desc, part_no, ";
		query		 = query  + "quantity_ordered, quantity_ordered_unit, ";
		query		 = query  + "price, currency, quantity_shipped, quantity_shipped_unit, multiplier)";
		
		query		 = query + " VALUES";
		query		 = query + "(";
		
		query		 = query + "'" + invoiceItemRecord.invoiceId_ + "', ";
		query		 = query + "'" + invoiceItemRecord.transId_  + "', ";
		query		 = query + "'" + invoiceItemRecord.itemDesc_ + "', ";
		
		query		 = query + "'" + invoiceItemRecord.partNo_ + "', ";
		query   	 = query + "'" + invoiceItemRecord.quantityOrdered_ + ", ";
		
		query 		 = query + "'" + invoiceItemRecord.quantityOrderedUnit_ + "', ";
		query 		 = query + "'" + invoiceItemRecord.price_ + "', ";
		
		query 		 = query + "'" + invoiceItemRecord.currency_ + "', ";
		query   	 = query + "'" + invoiceItemRecord.quantityShipped_ + ", ";
		
		query   	 = query + "'" + invoiceItemRecord.quantityShippedUnit_ + ", ";
		query 		 = query + "'" + invoiceItemRecord.multiplier_ + "'";
		
		query 	  	 = query + ")";
 		
		errorMaster_.insert( "Item Query = " + query );
		
		return query;
	}
	
	/*
	 * Method : getFilterString( )
	 * 
	 * Input : InvoiceItemRecord obj, StringHolder filterQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the invoiceItemRecord it form the filter query and add to string holder
	 * parameter so it is copied to caller class.
	 */

	public int getFilterString( InvoiceItemRecord filter, StringHolder query )
	{
		try
		{
			query.value = filter.createdTimestamp_ != null ?  
							" created_timestamp='" + filter.createdTimestamp_ + "' AND" : "";

			query.value += filter.partNo_ != null ? 
							" part_no = '" + filter.partNo_ + "' AND" : "";

			query.value += filter.quantityOrderedUnit_ != null ? 
							" quantity_ordered_unit='" + filter.quantityOrderedUnit_ + "' AND" : "";

			query.value += filter.itemDesc_ != null ? 
							" item_desc='" + filter.itemDesc_ + "' AND" : "";

			query.value += filter.quantityOrdered_ != 0.0f ? 
							" quantity_ordered='" + filter.quantityOrdered_ + "' AND" : "";
			
			query.value += filter.quantityShipped_ != 0.0f ? 
					" quantity_shipped='" + filter.quantityShipped_ + "' AND" : "";
			
			query.value += filter.quantityShippedUnit_ != null ? 
					" quantity_shipped_unit='" + filter.quantityShippedUnit_ + "' AND" : "";

			query.value += filter.invoiceId_ != -1 ? 
							" invoice_id='" + filter.invoiceId_ + "' AND" : "";

			query.value += filter.transId_ != -1 ?
							" trans_id='" + filter.transId_ + "' AND" : "";
			
			query.value += filter.price_ != 0.00f ?
							" price='" + filter.price_ + "' AND" : "";
			
			query.value += filter.currency_ != null ?
							" currency='" + filter.currency_ + "' AND" : "";
			
			query.value += filter.multiplier_ != -1 ?
							" multiplier='" + filter.multiplier_ + "' AND" : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );

			
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::InvoiceItemTableUtils.getFilterString() - " + ex );
			return -1;
		}
	}
	
	/*
	 * Method : getUpdateString( )
	 * 
	 * Input : InvoiceItemRecord obj, StringHolder updateQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the InvoiceItemRecord it form the update query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getUpdateString( InvoiceItemRecord filter, StringHolder query )
	{
		try
		{
			query.value = filter.createdTimestamp_ != null ?  
							" created_timestamp='" + filter.createdTimestamp_ + "' ," : "";

        	query.value += filter.partNo_ != null ? 
        					" part_no = '" + filter.partNo_ + "' ," : "";
        
        	query.value += filter.quantityOrderedUnit_ != null ? 
        					" quantity_ordered_unit='" + filter.quantityOrderedUnit_ + "' ," : "";
        	
        
        	query.value += filter.itemDesc_ != null ? 
        					" item_desc='" + filter.itemDesc_ + "' ," : "";
        
        	query.value += filter.quantityOrdered_ != 0.0f ? 
        					" quantity_ordered='" + filter.quantityOrdered_ + "' ," : "";
        	
        	query.value += filter.quantityShipped_ != 0.0f ? 
					" quantity_shipped='" + filter.quantityShipped_ + "' ," : "";
        	
        	query.value += filter.quantityShippedUnit_ != null ? 
					" quantity_shipped_unit='" + filter.quantityShippedUnit_ + "' ," : "";
        
        	query.value += filter.invoiceId_ != -1 ? 
        					" invoice_id='" + filter.invoiceId_ + "' ," : "";
        
        
        	query.value += filter.transId_ != -1 ?
        					" trans_id='" + filter.transId_ + "' ," : "";
        	
        	query.value += filter.price_ != 0.00f ?
        					" price='" + filter.price_ + "' ," : "";
        	
        	query.value += filter.currency_ != null ?
        					" currency='" + filter.currency_ + "' ," : "";
        	
        	query.value += filter.multiplier_ != -1 ?
        					" multiplier='" + filter.multiplier_ + "' ," : "";


			if( !query.value.equalsIgnoreCase( "" ) )
			{
				query.value = query.value.substring( 0, query.value.length( ) - 1 );
			}
			
			errorMaster_.insert( "Filter String = " + query.value );

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::InvoiceItemTableUtils.getUpdateString() - " + ex );
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
	 * Purpose: It is used to convert the ResultSet object to List<InvoiceItemData> object.
	 * And add to InvoiceItemList parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<InvoiceItemData> invoiceItemList )
	{
		
		/* Table fields: invoice_item_id, invoice_id, trans_id, item_desc, part_no, quantity,	 	 	 	 	 	
		ship_date, quantity_unit, price, currency, multiplier, created_timestamp */
		
		try
        {
			while( rs.next( ) )
			{
    			InvoiceItemData invoiceItemData = new InvoiceItemData( );
    			
    			invoiceItemData.createdTimestamp_ 		= rs.getTimestamp( "created_timestamp" );
    			invoiceItemData.itemDesc_				= rs.getString( "item_desc" );
    			invoiceItemData.partNo_					= rs.getString( "part_no" );
    			invoiceItemData.quantityOrdered_		= rs.getDouble( "quantity_ordered" );
    			invoiceItemData.quantityOrderedUnit_	= rs.getString( "quantity_ordered_unit" );
    			invoiceItemData.invoiceId_				= rs.getLong( "invoice_id" );
    			invoiceItemData.transId_				= rs.getLong( "trans_id" );
    			invoiceItemData.price_					= rs.getDouble( "price" );
    			invoiceItemData.currency_				= rs.getString( "currency" );
    			invoiceItemData.quantityShipped_		= rs.getDouble( "quantity_shipped" );
    			invoiceItemData.quantityShippedUnit_	= rs.getString( "quantity_shipped_unit" );
    			invoiceItemData.multiplier_				= rs.getInt( "multiplier" );
                        invoiceItemData.brcd_					= rs.getString( "barcode_id" );
    			
    			invoiceItemList.add( invoiceItemData );
    			
    			invoiceItemData = null;
			}
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::InvoiceItemTableUtils.rsToDataList() - " + e );
			return -1;
        }
		
		return 0;
	}

	/*
	 * Method : dataListToRecordList( )
	 * 
	 * Input : List<InvoiceItemData> obj, List<InvoiceItemRecord> object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the List<InvoiceItemData>  object to
	 * List<InvoiceItemRecord>  object. And add to List<InvoiceItemRecord>  parameter so it is copied
	 * to caller method.
	 */
	
	public int dataListToRecordList( List<InvoiceItemData> invoiceItemDataList, List<InvoiceItemRecord> invoiceItemRecordList )
	{
		try
        {	
			for( InvoiceItemData invoiceItemData : invoiceItemDataList )
	        {
				InvoiceItemRecord invoiceItemRecord = new InvoiceItemRecord( );
				
				invoiceItemRecord.createdTimestamp_ 	= invoiceItemData.createdTimestamp_;
				invoiceItemRecord.itemDesc_				= invoiceItemData.itemDesc_;
				invoiceItemRecord.partNo_				= invoiceItemData.partNo_;
				
				invoiceItemRecord.quantityOrdered_		= invoiceItemData.quantityOrdered_;
				invoiceItemRecord.quantityOrderedUnit_	= invoiceItemData.quantityOrderedUnit_;
				invoiceItemRecord.invoiceId_			= invoiceItemData.invoiceId_;
				
				invoiceItemRecord.transId_				= invoiceItemData.transId_;   
				invoiceItemRecord.price_				= invoiceItemData.price_;
				
				invoiceItemRecord.currency_				= invoiceItemData.currency_;
				invoiceItemRecord.quantityShipped_		= invoiceItemData.quantityShipped_;
				
				invoiceItemRecord.quantityShippedUnit_ 	= invoiceItemData.quantityShippedUnit_;
				invoiceItemRecord.multiplier_			= invoiceItemData.multiplier_;
                                invoiceItemRecord.brcd_			= invoiceItemData.brcd_;
				
				invoiceItemRecordList.add( invoiceItemRecord );
				
				invoiceItemRecord = null;
	        }
			
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
        	
			errLogger_.logMsg( "Exception::InvoiceItemTableUtils.dataListToRecordList() - " + e );
			
			return -1;
        }
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : InvoiceIItemRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using InvoiceItemRecord and returns as
	 * string
	 */

	public String formInsertQuery( List<InvoiceItemRecord> invoiceItemRecordList )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "invoice_id, trans_id, item_desc, part_no, ";
		query		 = query  + "quantity_ordered, quantity_ordered_unit, ";
		query		 = query  + "price, currency, quantity_shipped, quantity_shipped_unit, multiplier,barcode_id)";
		
		query		 = query + " VALUES";
		
		int iterator = 0;
		
		for( InvoiceItemRecord invoiceItemRecord : invoiceItemRecordList )
        {
			query		 = query + "(";
			
			query		 = query + "'" + invoiceItemRecord.invoiceId_ + "', ";
			query		 = query + "'" + invoiceItemRecord.transId_  + "', ";
			query		 = query + "'" + invoiceItemRecord.itemDesc_ + "', ";
			
			query		 = query + "'" + invoiceItemRecord.partNo_ + "', ";
			query   	 = query + "'" + invoiceItemRecord.quantityOrdered_ + "', ";
			
			query 		 = query + "'" + invoiceItemRecord.quantityOrderedUnit_ + "', ";
			query 		 = query + "'" + invoiceItemRecord.price_ + "', ";
			
			query 		 = query + "'" + invoiceItemRecord.currency_ + "', ";
			query   	 = query + "'" + invoiceItemRecord.quantityShipped_ + "', ";
			
			query   	 = query + "'" + invoiceItemRecord.quantityShippedUnit_ + "', ";
			query 		 = query + "'" + invoiceItemRecord.multiplier_ + "',";
                        query 		 = query + "'" + invoiceItemRecord.brcd_ + "'";
			
			query 	  	 = query + ")";
			
			iterator = iterator + 1;
			
			if( invoiceItemRecordList.size( ) > iterator )
			{
				query = query + ", ";
			}
			
        }
		
		errorMaster_.insert( "Compound Query = " + query );
		
		return query;
	}
}
