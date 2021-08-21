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
import core.trans.POItemData;

/**
 * File:  POItemTableUtils.java 
 *
 * Created on Jul 4, 2013 2:22:57 PM
 */

public class POItemTableUtils
{
	private String tableName_;
	
	/*Table fields
 
  	po_id,		 	 	 	 	 	 	
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
	 * Method : POItemTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public POItemTableUtils()
	{
		this.tableName_ = "po_item";
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : POItemData obj, POItemRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the POItemData object to
	 * POItemRecord object. And add to POItemRecord parameter so it is copied
	 * to caller method.
	 */
	
	public int dataToRecord( POItemData poItemData, POItemRecord poItemRecord )
	{
		poItemRecord.createdTimestamp_ 	= poItemData.createdTimestamp_;
		poItemRecord.itemDesc_			= poItemData.itemDesc_;
		poItemRecord.partNo_				= poItemData.partNo_;
		
		poItemRecord.quantity_			= poItemData.quantity_;
		poItemRecord.quantityUnit_		= poItemData.quantityUnit_;
		poItemRecord.poId_				= poItemData.poId_;
		
		poItemRecord.shipDate_			= poItemData.shipDate_;
		poItemRecord.transId_				= poItemData.transId_;
		
		poItemRecord.price_				= poItemData.price_;
		poItemRecord.currency_			= poItemData.currency_;
		poItemRecord.multiplier_			= poItemData.multiplier_;
		
		
		return 0;
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : POItemRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using POItemRecord and returns as
	 * string
	 */

	public String formInsertQuery( POItemRecord poItemRecord )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "po_id, trans_id, item_desc, part_no, ";
		query		 = query  + "quantity, ship_date, quantity_unit, ";
		query		 = query  + "price, currency, multiplier)";
		
		query		 = query + " VALUES";
		query		 = query + "(";
		
		query		 = query + "'" + poItemRecord.poId_ + ", ";
		query		 = query + "'" + poItemRecord.transId_  + ", ";
		query		 = query + "'" + poItemRecord.itemDesc_ + "', ";
		
		query		 = query + "'" + poItemRecord.partNo_ + "', ";
		query   	 = query + "'" + poItemRecord.quantity_ + ", ";
		query		 = query + "'" + poItemRecord.shipDate_ + "', ";
		
		query 		 = query + "'" + poItemRecord.quantityUnit_ + "', ";
		query 		 = query + "'" + poItemRecord.price_ + "', ";
		
		query 		 = query + "'" + poItemRecord.currency_ + "', ";
		query 		 = query + "'" + poItemRecord.multiplier_ + "'";
		
		query 	  	 = query + ")";
 		
		System.out.println( "Item Query = " + query );
		
		return query;
	}
	
	/*
	 * Method : getFilterString( )
	 * 
	 * Input : POItemRecord obj, StringHolder filterQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the poItemRecord it form the filter query and add to string holder
	 * parameter so it is copied to caller class.
	 */

	public int getFilterString( POItemRecord filter, StringHolder query )
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

			query.value += filter.poId_ != -1 ? 
							" po_id='" + filter.poId_ + "' AND" : "";

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
			errLogger_.logMsg( "Exception::POItemTableUtils.getFilterString() - " + ex );
			return -1;
		}
	}
	
	/*
	 * Method : getUpdateString( )
	 * 
	 * Input : POItemRecord obj, StringHolder updateQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the POItemRecord it form the update query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getUpdateString( POItemRecord filter, StringHolder query )
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
        
        	query.value += filter.poId_ != -1 ? 
        					" po_id='" + filter.poId_ + "' ," : "";
        
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
			errLogger_.logMsg( "Exception::POItemTableUtils.getUpdateString() - " + ex );
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
	 * Purpose: It is used to convert the ResultSet object to List<POItemData> object.
	 * And add to POItemList parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<POItemData> poItemList )
	{
		
		/* Table fields: po_id, trans_id, item_desc, part_no, quantity,	 	 	 	 	 	
		ship_date, quantity_unit, price, currency, multiplier, created_timestamp */
		
		try
        {
			while( rs.next( ) )
			{
    			POItemData poItemData = new POItemData( );
    			
    			poItemData.createdTimestamp_ 	= rs.getTimestamp( "created_timestamp" );
    			poItemData.itemDesc_			= rs.getString( "item_desc" );
    			poItemData.partNo_				= rs.getString( "part_no" );
    			poItemData.quantity_			= rs.getDouble( "quantity" );
    			poItemData.quantityUnit_		= rs.getString( "quantity_unit" );
    			poItemData.poId_				= rs.getLong( "po_id" );
    			poItemData.shipDate_			= rs.getDate( "ship_date" );
    			poItemData.transId_			= rs.getLong( "trans_id" );
    			poItemData.price_			= rs.getDouble( "price" );
    			poItemData.currency_			= rs.getString( "currency" );
    			poItemData.multiplier_		= rs.getInt( "multiplier" );
    			
    			poItemList.add( poItemData );
    			
    			poItemData = null;
			}
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::POItemTableUtils.rsToDataList() - " + e );
			return -1;
        }
		
		return 0;
	}

	/*
	 * Method : dataListToRecordList( )
	 * 
	 * Input : List<POItemData> obj, List<POItemRecord> object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the List<POItemData>  object to
	 * List<POItemRecord>  object. And add to List<POItemRecord>  parameter so it is copied
	 * to caller method.
	 */
	
	public int dataListToRecordList( List<POItemData> poItemDataList, List<POItemRecord> poItemRecordList )
	{
		try
        {	
			for( POItemData poItemData : poItemDataList )
	        {
				POItemRecord poItemRecord = new POItemRecord( );
				
				poItemRecord.createdTimestamp_ 	= poItemData.createdTimestamp_;
				poItemRecord.itemDesc_			= poItemData.itemDesc_;
				poItemRecord.partNo_			= poItemData.partNo_;
				
				poItemRecord.quantity_			= poItemData.quantity_;
				poItemRecord.quantityUnit_		= poItemData.quantityUnit_;
				poItemRecord.poId_				= poItemData.poId_;
				
				poItemRecord.shipDate_			= poItemData.shipDate_;
				poItemRecord.transId_			= poItemData.transId_;   
				poItemRecord.price_				= poItemData.price_;
				
				poItemRecord.currency_			= poItemData.currency_;
				poItemRecord.multiplier_			= poItemData.multiplier_;
				
				poItemRecordList.add( poItemRecord );
				
				poItemRecord = null;
	        }
			
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
        	
			errLogger_.logMsg( "Exception::POItemTableUtils.dataListToRecordList() - " + e );
			
			return -1;
        }
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : POIItemRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using POItemRecord and returns as
	 * string
	 */

	public String formInsertQuery( List<POItemRecord> poItemRecordList )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "po_id, trans_id, item_desc, part_no, ";
		query		 = query  + "quantity, ship_date, quantity_unit, ";
		query		 = query  + "price, currency, multiplier)";
		
		query		 = query + " VALUES";
		
		int iterator = 0;

		
		for( POItemRecord poItemRecord : poItemRecordList )
        {
			query		 = query + "(";
			
			query		 = query + "'" + poItemRecord.poId_ + "', ";
			query		 = query + "'" + poItemRecord.transId_  + "', ";
			query		 = query + "'" + poItemRecord.itemDesc_ + "', ";
			
			query		 = query + "'" + poItemRecord.partNo_ + "', ";
			query   	 = query + "'" + poItemRecord.quantity_ + "', ";
			query		 = query + "'" + poItemRecord.shipDate_ + "', ";
			
			query 		 = query + "'" + poItemRecord.quantityUnit_ + "', ";
			query 		 = query + "'" + poItemRecord.price_ + "', ";
			
			query 		 = query + "'" + poItemRecord.currency_ + "', ";
			query 		 = query + "'" + poItemRecord.multiplier_ + "'";
			
			query 	  	 = query + ")";
			
			iterator = iterator + 1;
			
			if( poItemRecordList.size( ) > iterator )
			{
				query = query + ", ";
			}
			
        }
		
		System.out.println( "Compound Query = " + query );
		
		return query;
	}

}
