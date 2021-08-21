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
import core.trans.RFQItemData;
import utils.ErrorMaster;

/**
 * File:  RFQItemTableUtils.java 
 *
 * Created on 21-Jun-2013 12:05:19 PM
 */
public class RFQItemTableUtils
{
	private String tableName_;
        private static ErrorMaster errorMaster_ = null;


	
	/*
	 * Method : RFQItemTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public RFQItemTableUtils()
	{
		this.tableName_ = "rfq_item";
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : RFQItemData obj, RFQItemRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the RFQItemData object to
	 * RFQItemRecord object. And add to RFQItemRecord parameter so it is copied
	 * to caller method.
	 */
	
	public int dataToRecord( RFQItemData rfqItemData, RFQItemRecord rfqItemRecord )
	{
		rfqItemRecord.createdTimestamp_ = rfqItemData.createdTimestamp_;
		rfqItemRecord.itemDesc_			= rfqItemData.itemDesc_;
		rfqItemRecord.partNo_			= rfqItemData.partNo_;
		
		rfqItemRecord.quantity_			= rfqItemData.quantity_;
		rfqItemRecord.quantityUnit_		= rfqItemData.quantityUnit_;
		rfqItemRecord.RFQId_			= rfqItemData.RFQId_;
		
		rfqItemRecord.shipDate_			= rfqItemData.shipDate_;
		rfqItemRecord.transId_			= rfqItemData.transId_;
		
		return 0;
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : RFQItemRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using RFQItemRecord and returns as
	 * string
	 */

	public String formInsertQuery( RFQItemRecord rfqItemRecord )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "trans_id, rfq_id, item_desc, part_no, ";
		query		 = query  + "quantity, ship_date, quantity_unit)";
		
		query		 = query + " VALUES";
		query		 = query + "(";
		
		query		 = query + "'" + rfqItemRecord.transId_ + ", ";
		query		 = query + "'" + rfqItemRecord.RFQId_  + ", ";
		query		 = query + "'" + rfqItemRecord.itemDesc_ + "', ";
		
		query		 = query + "'" + rfqItemRecord.partNo_ + "', ";
		query   	 = query + "'" + rfqItemRecord.quantity_ + ", ";
		query		 = query + "'" + rfqItemRecord.shipDate_ + "', ";
		
		query 		 = query + "'" + rfqItemRecord.quantityUnit_ + "'";
		
		query 	  	 = query + ")";
 		
		errorMaster_.insert( "Item Query = " + query );
		
		return query;
	}
	
	/*
	 * Method : getFilterString( )
	 * 
	 * Input : RFQItemRecord obj, StringHolder filterQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the rfqItemRecord it form the filter query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getFilterString( RFQItemRecord filter, StringHolder query )
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

			query.value += filter.RFQId_ != -1 ? 
							" rfq_id='" + filter.RFQId_ + "' AND" : "";

			query.value += filter.shipDate_ != null ?
							" ship_date='" + filter.shipDate_ + "' AND" : "";

			query.value += filter.transId_ != -1 ?
							" trans_id='" + filter.transId_ + "' AND" : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );

			
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::RFQItemTableUtils.getFilterString() - " + ex );
			return -1;
		}
	}
	
	/*
	 * Method : getUpdateString( )
	 * 
	 * Input : RFQItemRecord obj, StringHolder updateQuery
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the rfqItemRecord it form the update query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getUpdateString( RFQItemRecord filter, StringHolder query )
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
        
        	query.value += filter.RFQId_ != -1 ? 
        					" rfq_id='" + filter.RFQId_ + "' ," : "";
        
        	query.value += filter.shipDate_ != null ?
        					" ship_date='" + filter.shipDate_ + "' ," : "";
        
        	query.value += filter.transId_ != -1 ?
        					" trans_id='" + filter.transId_ + "' ," : "";


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
			errLogger_.logMsg( "Exception::RFQItemTableUtils.getUpdateString() - " + ex );
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
	 * Purpose: It is used to convert the ResultSet object to List<RFQItemData> object.
	 * And add to rfqItemList parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<RFQItemData> RFQItemList )
	{
		/*trans_id, rfq_id, item_desc, part_no, quantity, ship_date,
		 * quantity_unit, created_timestamp*/
		
		try
        {
			while( rs.next( ) )
			{
    			RFQItemData rfqItemData = new RFQItemData( );
    			
    			rfqItemData.createdTimestamp_ 	= rs.getTimestamp( "created_timestamp" );
    			rfqItemData.itemDesc_			= rs.getString( "item_desc" );
    			rfqItemData.partNo_				= rs.getString( "part_no" );
    			rfqItemData.quantity_			= rs.getDouble( "quantity" );
    			rfqItemData.quantityUnit_		= rs.getString( "quantity_unit" );
    			rfqItemData.RFQId_				= rs.getLong( "rfq_id" );
    			rfqItemData.shipDate_			= rs.getDate( "ship_date" );
    			rfqItemData.transId_			= rs.getLong( "trans_id" );
    			rfqItemData.brcdNo_			= rs.getString( "barcode_id" );
                        
    			RFQItemList.add( rfqItemData );
    			
    			rfqItemData = null;
			}
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::RFQItemTableUtils.rsToDataList() - " + e );
			return -1;
        }
		
		return 0;
	}

	/*
	 * Method : dataListToRecordList( )
	 * 
	 * Input : List<RFQItemData> obj, List<RFQItemRecord> object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the List<RFQItemData>  object to
	 * List<RFQItemRecord>  object. And add to List<RFQItemRecord>  parameter so it is copied
	 * to caller method.
	 */
	
	public int dataListToRecordList( List<RFQItemData> rfqItemDataList, List<RFQItemRecord> rfqItemRecordList )
	{
		try
        {	
			for( RFQItemData rfqItemData : rfqItemDataList )
	        {
				RFQItemRecord rfqItemRecord = new RFQItemRecord( );
				
				rfqItemRecord.createdTimestamp_ = rfqItemData.createdTimestamp_;
				rfqItemRecord.itemDesc_			= rfqItemData.itemDesc_;
				rfqItemRecord.partNo_			= rfqItemData.partNo_;
				
				rfqItemRecord.quantity_			= rfqItemData.quantity_;
				rfqItemRecord.quantityUnit_		= rfqItemData.quantityUnit_;
				rfqItemRecord.RFQId_			= rfqItemData.RFQId_;
				
				rfqItemRecord.shipDate_			= rfqItemData.shipDate_;
				rfqItemRecord.transId_			= rfqItemData.transId_;  
                                rfqItemRecord.brcdNo_=rfqItemData.brcdNo_;
				
				rfqItemRecordList.add( rfqItemRecord );
				
				rfqItemRecord = null;
	        }
			
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
        	
			errLogger_.logMsg( "Exception::RFQItemTableUtils.dataListToRecordList() - " + e );
			
			return -1;
        }
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : RFQItemRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using RFQItemRecord and returns as
	 * string
	 */

	public String formInsertQuery( List<RFQItemRecord> rfqItemRecordList )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "trans_id, rfq_id, item_desc, part_no, ";
		query		 = query  + "quantity, ship_date, quantity_unit,barcode_id)";
		
		query		 = query + " VALUES";
		
		int iterator = 0;
		
		for( RFQItemRecord rfqItemRecord : rfqItemRecordList )
        {
			query		 = query + "(";
			
			query		 = query + "'" + rfqItemRecord.transId_ + "', ";
			query		 = query + "'" + rfqItemRecord.RFQId_  + "', ";
			query		 = query + "'" + rfqItemRecord.itemDesc_ + "', ";
			
			query		 = query + "'" + rfqItemRecord.partNo_ + "', ";
			query   	 = query + "'" + rfqItemRecord.quantity_ + "', ";
			query		 = query + "'" + rfqItemRecord.shipDate_ + "', ";
			
			query 		 = query + "'" + rfqItemRecord.quantityUnit_ + "',";
                        
                        query 		 = query + "'" + rfqItemRecord.brcdNo_ + "'";
			
			query 	  	 = query + ")";
			
			iterator = iterator + 1;
			
			if( rfqItemRecordList.size( ) > iterator )
			{
				query = query + ", ";
			}
			
        }
		
		errorMaster_.insert( "Compound Query = " + query );
		
		return query;
	}

}
