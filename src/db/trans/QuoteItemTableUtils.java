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
import core.trans.QuoteItemData;

/**
 * File:  QuoteItemUtils.java 
 *
 * Created on Jul 3, 2013 10:30:18 AM
 */
public class QuoteItemTableUtils
{
	private String tableName_;
	
	/*Table fields
 
  	quote_id,		 	 	 	 	 	 	
	trans_id,		 	 	 	 	 	 	
	item_desc,		 	 	 				 
	part_no,		 	 	 	 	 	 	 
	quantity,		 	 	 	 	 	 	
	ship_date,		 	 	 	 	 	 	
	quantity_unit,		 	 	 	 	 	 	 
	price,		 	 	 	 	 	 	
	currency,		 	 	 	 	 	 	 
	multiplier,		 	 	 	 	 	 	
	created_timestamp
  
 */
	/*
	 * Method : QuoteItemUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public QuoteItemTableUtils()
	{
		this.tableName_ = "quote_item";
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : QuoteItemData obj, QuoteItemRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the QuoteItemData object to
	 * QuoteItemRecord object. And add to QuoteItemRecord parameter so it is copied
	 * to caller method.
	 */
	
	public int dataToRecord( QuoteItemData quoteItemData, QuoteItemRecord quoteItemRecord )
	{
		quoteItemRecord.createdTimestamp_ 	= quoteItemData.createdTimestamp_;
		quoteItemRecord.itemDesc_			= quoteItemData.itemDesc_;
		quoteItemRecord.partNo_				= quoteItemData.partNo_;
		
		quoteItemRecord.quantity_			= quoteItemData.quantity_;
		quoteItemRecord.quantityUnit_		= quoteItemData.quantityUnit_;
		quoteItemRecord.quoteId_			= quoteItemData.quoteId_;
		
		quoteItemRecord.shipDate_			= quoteItemData.shipDate_;
		quoteItemRecord.transId_			= quoteItemData.transId_;
		
		quoteItemRecord.price_				= quoteItemData.price_;
		quoteItemRecord.currency_			= quoteItemData.currency_;
		quoteItemRecord.multiplier_			= quoteItemData.multiplier_;
		
		return 0;
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : QuoteItemRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using QuoteItemRecord and returns as
	 * string
	 */

	public String formInsertQuery( QuoteItemRecord quoteItemRecord )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "quote_id, trans_id, item_desc, part_no, ";
		query		 = query  + "quantity, ship_date, quantity_unit, ";
		query		 = query  + "price, currency, multiplier)";
		
		query		 = query + " VALUES";
		query		 = query + "(";
		
		query		 = query + "'" + quoteItemRecord.quoteId_ + "', ";
		query		 = query + "'" + quoteItemRecord.transId_  + "', ";
		query		 = query + "'" + quoteItemRecord.itemDesc_ + "', ";
		
		query		 = query + "'" + quoteItemRecord.partNo_ + "', ";
		query   	 = query + "'" + quoteItemRecord.quantity_ + ", ";
		query		 = query + "'" + quoteItemRecord.shipDate_ + "', ";
		
		query 		 = query + "'" + quoteItemRecord.quantityUnit_ + "', ";
		query 		 = query + "'" + quoteItemRecord.price_ + "', ";
		
		query 		 = query + "'" + quoteItemRecord.currency_ + "', ";
		query 		 = query + "'" + quoteItemRecord.multiplier_ + "'";
		
		query 	  	 = query + ")";
 		
		System.out.println( "Item Query = " + query );
		
		return query;
	}
	
	/*
	 * Method : getFilterString( )
	 * 
	 * Input : QuoteItemRecord obj, StringHolder filterQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the quoteItemRecord it form the filter query and add to string holder
	 * parameter so it is copied to caller class.
	 */

	public int getFilterString( QuoteItemRecord filter, StringHolder query )
	{
		try
		{
			query.value = filter.createdTimestamp_ != null ?  
							" created_timestamp='" + filter.createdTimestamp_ + "' AND" : "";

			query.value += filter.partNo_ != null ? 
							" part_no = '" + filter.partNo_ + "' AND" : "";

			query.value += filter.quantityUnit_ != null ? 
							" quantity_unit='" + filter.quantityUnit_ + "' AND" : "";

			query.value += filter.itemDesc_ != null ? 
							" item_desc='" + filter.itemDesc_ + "' AND" : "";

			query.value += filter.quantity_ != 0.0f ? 
							" quantity='" + filter.quantity_ + "' AND" : "";

			query.value += filter.quoteId_ != -1 ? 
							" quote_id='" + filter.quoteId_ + "' AND" : "";

			query.value += filter.shipDate_ != null ?
							" ship_date='" + filter.shipDate_ + "' AND" : "";

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
			errLogger_.logMsg( "Exception::QuoteItemTableUtils.getFilterString() - " + ex );
			return -1;
		}
	}
	
	/*
	 * Method : getUpdateString( )
	 * 
	 * Input : QuoteItemRecord obj, StringHolder updateQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the QuoteItemRecord it form the update query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getUpdateString( QuoteItemRecord filter, StringHolder query )
	{
		try
		{
			query.value = filter.createdTimestamp_ != null ?  
							" created_timestamp='" + filter.createdTimestamp_ + "' ," : "";

        	query.value += filter.partNo_ != null ? 
        					" part_no = '" + filter.partNo_ + "' ," : "";
        
        	query.value += filter.quantityUnit_ != null ? 
        					" quantity_unit='" + filter.quantityUnit_ + "' ," : "";
        	
        
        	query.value += filter.itemDesc_ != null ? 
        					" item_desc='" + filter.itemDesc_ + "' ," : "";
        
        	query.value += filter.quantity_ != 0.0f ? 
        					" quantity='" + filter.quantity_ + "' ," : "";
        
        	query.value += filter.quoteId_ != -1 ? 
        					" quote_id='" + filter.quoteId_ + "' ," : "";
        
        	query.value += filter.shipDate_ != null ?
        					" ship_date='" + filter.shipDate_ + "' ," : "";
        
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
			
			System.out.println( "Filter String = " + query.value );

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::QuoteItemTableUtils.getUpdateString() - " + ex );
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
	 * Purpose: It is used to convert the ResultSet object to List<QuoteItemData> object.
	 * And add to QuoteItemList parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<QuoteItemData> quoteItemList )
	{
		
		/* Table fields: quote_id, trans_id, item_desc, part_no, quantity,	 	 	 	 	 	
		ship_date, quantity_unit, price, currency, multiplier, created_timestamp */
		
		try
        {
			while( rs.next( ) )
			{
    			QuoteItemData quoteItemData = new QuoteItemData( );
    			
    			quoteItemData.createdTimestamp_ 	= rs.getTimestamp( "created_timestamp" );
    			quoteItemData.itemDesc_			= rs.getString( "item_desc" );
    			quoteItemData.partNo_				= rs.getString( "part_no" );
    			quoteItemData.quantity_			= rs.getDouble( "quantity" );
    			quoteItemData.quantityUnit_		= rs.getString( "quantity_unit" );
    			quoteItemData.quoteId_				= rs.getLong( "quote_id" );
    			quoteItemData.shipDate_			= rs.getDate( "ship_date" );
    			quoteItemData.transId_			= rs.getLong( "trans_id" );
    			quoteItemData.price_			= rs.getDouble( "price" );
    			quoteItemData.currency_			= rs.getString( "currency" );
    			quoteItemData.multiplier_		= rs.getInt( "multiplier" );
    			
    			quoteItemList.add( quoteItemData );
    			
    			quoteItemData = null;
			}
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::QuoteItemTableUtils.rsToDataList() - " + e );
			return -1;
        }
		
		return 0;
	}

	/*
	 * Method : dataListToRecordList( )
	 * 
	 * Input : List<QuoteItemData> obj, List<QuoteItemRecord> object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the List<QuoteItemData>  object to
	 * List<QuoteItemRecord>  object. And add to List<QuoteItemRecord>  parameter so it is copied
	 * to caller method.
	 */
	
	public int dataListToRecordList( List<QuoteItemData> quoteItemDataList, List<QuoteItemRecord> quoteItemRecordList )
	{
		try
        {	
			for( QuoteItemData quoteItemData : quoteItemDataList )
	        {
				QuoteItemRecord quoteItemRecord = new QuoteItemRecord( );
				
				quoteItemRecord.createdTimestamp_ = quoteItemData.createdTimestamp_;
				quoteItemRecord.itemDesc_			= quoteItemData.itemDesc_;
				quoteItemRecord.partNo_			= quoteItemData.partNo_;
				
				quoteItemRecord.quantity_			= quoteItemData.quantity_;
				quoteItemRecord.quantityUnit_		= quoteItemData.quantityUnit_;
				quoteItemRecord.quoteId_			= quoteItemData.quoteId_;
				
				quoteItemRecord.shipDate_			= quoteItemData.shipDate_;
				quoteItemRecord.transId_			= quoteItemData.transId_;   
				quoteItemRecord.price_				= quoteItemData.price_;
				
				quoteItemRecord.currency_			= quoteItemData.currency_;
				quoteItemRecord.multiplier_			= quoteItemData.multiplier_;
				
				quoteItemRecordList.add( quoteItemRecord );
				
				quoteItemRecord = null;
	        }
			
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
        	
			errLogger_.logMsg( "Exception::QuoteItemTableUtils.dataListToRecordList() - " + e );
			
			return -1;
        }
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : QuoteItemRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using QuoteItemRecord and returns as
	 * string
	 */

	public String formInsertQuery( List<QuoteItemRecord> quoteItemRecordList )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "quote_id, trans_id, item_desc, part_no, ";
		query		 = query  + "quantity, ship_date, quantity_unit, ";
		query		 = query  + "price, currency, multiplier)";
		
		query		 = query + " VALUES";
		
		int iterator = 0;
		
		for( QuoteItemRecord quoteItemRecord : quoteItemRecordList )
        {
			query		 = query + "(";
			
			query		 = query + "'" + quoteItemRecord.quoteId_ + "', ";
			query		 = query + "'" + quoteItemRecord.transId_  + "', ";
			query		 = query + "'" + quoteItemRecord.itemDesc_ + "', ";
			
			query		 = query + "'" + quoteItemRecord.partNo_ + "', ";
			query   	 = query + "'" + quoteItemRecord.quantity_ + "', ";
			query		 = query + "'" + quoteItemRecord.shipDate_ + "', ";
			
			query 		 = query + "'" + quoteItemRecord.quantityUnit_ + "', ";
			query 		 = query + "'" + quoteItemRecord.price_ + "', ";
			
			query 		 = query + "'" + quoteItemRecord.currency_ + "', ";
			query 		 = query + "'" + quoteItemRecord.multiplier_ + "'";
			
			query 	  	 = query + ")";
			
			iterator = iterator + 1;
			
			if( quoteItemRecordList.size( ) > iterator )
			{
				query = query + ", ";
			}
			
        }
		
		System.out.println( "Compound Query = " + query );
		
		return query;
	}

}
