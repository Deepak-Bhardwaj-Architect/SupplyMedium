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

import core.trans.RFQData;
import utils.ErrorLogger;
import utils.ErrorMaster;
import utils.StringHolder;


/**
 * File:  RFQTableUtils.java 
 *
 * Created on 21-Jun-2013 9:49:04 AM
 * 
 * Purpose : This is the helper class for RFQTable class. It contain the 
 * create query and converter methods.
 */
public class RFQTableUtils
{
	private String tableName_;
	
	private ErrorLogger errLogger_;
	
	/* Table fields:
	
    	rfq_Id,
    	trans_Id,	
    	quote_ref,	 	 	 	 	 	 	
    	from,		 	 	 	 	 	 	 
    	to,		 	 	 	 	 	 	 
    	user_from,		 	 	 	 	 	 	 
    	user_to,		 	 	 	 	 	 	 
    	status,		 	 	 	 	 	 	 
    	action,		 	 	 	 	 	 	 
    	is_outside_supplier,		 	 	 	 	 	 	
    	outside_supplier_email,		 	 	 	 	 	 	 
    	recurring,		 	 	 	 	 	 	 
    	rfq_date,		 	 	 	 	 	 	
    	created_timestamp 
	
	*/
        
        private static ErrorMaster errorMaster_ = null;


	
	/*
	 * Method : RFQTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public RFQTableUtils()
	{
		this.tableName_ = "rfq";
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
	 * Purpose: It is used to convert the RFQData object to
	 * RFQRecord object. And add to rfqRecord parameter so it is copied
	 * to caller method.
	 */
	
	public int dataToRecord( RFQData rfqData, RFQRecord rfqRecord )
	{
		try
        {
			rfqRecord.action_ 		= rfqData.action_;
			rfqRecord.recurring_	= rfqData.recurring_;
			
			rfqRecord.createdTimestamp_	 	= rfqData.createdTimestamp_;
			rfqRecord.isOutsideSupplier_	= rfqData.isOutsideSupplier_;
			rfqRecord.outsideSupplierEmail_	= rfqData.outsideSupplierEmail_;
                        
                        rfqRecord.outsideSuppliercountry	= rfqData.outsideSuppliercountry;
                        rfqRecord.outsideSupplierstate	= rfqData.outsideSupplierstate;
                        rfqRecord.outsideSuppliercity	= rfqData.outsideSuppliercity;
                        rfqRecord.outsideSupplieraddress	= rfqData.outsideSupplieraddress;
                        rfqRecord.outsideSupplierzipcode	= rfqData.outsideSupplierzipcode;
			
			rfqRecord.RFQDate_			= rfqData.RFQDate_;
			rfqRecord.RFQId_			= rfqData.RFQId_;
			rfqRecord.status_			= rfqData.status_;
			rfqRecord.from_				= rfqData.from_.toString( );
			rfqRecord.to_				= rfqData.to_.toString( );
                        //System.out.print("rfqData.to_-------------"+rfqData.to_);
			rfqRecord.transId_			= rfqData.transId_;
			rfqRecord.userFrom_			= rfqData.userFrom_.toString( );
			rfqRecord.userTo_			= rfqData.userTo_.toString( );
                        //System.out.print("rfqData.userTo_-------------"+rfqData.userTo_);
			rfqRecord.quoteRef_			= rfqData.quoteRef_;
			rfqRecord.isQuoteCreated_ = rfqData.isQuoteCreated_;
			
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
	 * Input : RFQRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using RFQRecord and returns as
	 * string
	 */

	public String formInsertQuery( RFQRecord rfqRecord )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + " trans_id,quote_ref, from_regn_key, to_regn_key, ";
		query		 = query  + "user_from, user_to, status, action, ";
		query		 = query + "is_outside_supplier, outside_supplier_email, recurring,is_quote_created, ";
		query		 = query  + "rfq_date,outside_supplier_address)";
		
		query		 = query + " VALUES";
		query		 = query + "(";
		
		query		 = query + "'" + rfqRecord.transId_  + "', ";
                //System.out.println(" rfqRecord.transId_------------------------->"+ rfqRecord.transId_);
		query		 = query + "'" + rfqRecord.quoteRef_ +"',";
		query		 = query + "'" + rfqRecord.from_ + "', ";
		
		query		 = query + "'" + rfqRecord.to_ + "', ";
		query   	 = query + "'" + rfqRecord.userFrom_ + "', ";
		query		 = query + "'" + rfqRecord.userTo_ + "', ";
		
		query 		 = query + "'" + rfqRecord.status_ + "', ";
		query 		 = query + "'" + rfqRecord.action_ + "', ";
		query 		 = query + "'" + rfqRecord.isOutsideSupplier_ + "', ";
		
		query 		 = query + "'" + rfqRecord.outsideSupplierEmail_ + "', ";
		query 		 = query + "'" + rfqRecord.recurring_ + "', ";
		query 		 = query + "'" + rfqRecord.isQuoteCreated_ + "', ";
		query 		 = query + "'" + rfqRecord.RFQDate_ + "',";
                query 		 = query + "'" + rfqRecord.outsideSupplieraddress+", "+rfqRecord.outsideSuppliercity+", "+rfqRecord.outsideSupplierstate+", "+rfqRecord.outsideSuppliercountry+", "+rfqRecord.outsideSupplierzipcode+ "'";
		
		
		
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
	
	public int getFilterString( RFQRecord filter, StringHolder query )
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

			query.value += filter.to_ != null ?
							" to='" + filter.to_ + "' AND" : "";

			query.value += filter.userFrom_ != null ?
							" user_from='" + filter.userFrom_ + "' AND" : "";
			
			query.value += filter.userTo_ != null ? 
							" user_to='" + filter.userTo_ + "' AND" : "";

			query.value += filter.isOutsideSupplier_ != -1 ?
							" is_outside_supplier='" + filter.isOutsideSupplier_ + "' AND" : "";

			query.value += filter.RFQDate_ != null ?
							" rfq_date='" + filter.RFQDate_ + "' AND" : "";
			
			query.value += filter.quoteRef_ != null ?
					" quote_ref='" + filter.quoteRef_ + "' AND" : "";
			
			query.value += filter.RFQId_ != -1 ?
							" rfq_Id='" + filter.RFQId_ + "' AND" : "";

			query.value += filter.transId_ != -1 ?
							" trans_Id='" + filter.transId_ + "' AND" : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );

			
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::RFQTableUtils.getFilterString() - " + ex );
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
	
	public int getUpdateString( RFQRecord filter, StringHolder query )
	{ 
		try
		{
			query.value = filter.createdTimestamp_ != null ?  
							" created_timestamp='" + filter.createdTimestamp_ + "' ," : "";

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

			query.value += filter.RFQDate_ != null ?
							" rfq_date='" + filter.RFQDate_ + "' ," : "";
			

			query.value += filter.transId_ != -1 ?
							" trans_Id='" + filter.transId_ + "' ," : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value  =   query.value.substring( 0, query.value.length( ) - 1  )+" Where rfq_id='"+filter.RFQId_+"'";


			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::RFQTableUtils.getUpdateString() - " + ex );
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
	 * Purpose: It is used to convert the ResultSet object to List<RFQData> object.
	 * And add to RFQList parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<RFQData> RFQList )
	{
		try
        {
			while( rs.next( ) )
			{
    			RFQData rfqData = new RFQData( );
    			
    			rfqData.RFQId_ 					= rs.getLong( "rfq_id" );
    			rfqData.transId_				= rs.getLong( "trans_Id" );
    			rfqData.quoteRef_				= rs.getString( "quote_ref" );
    			rfqData.from_.companyPhoneNo_	= rs.getString( "from_regn_key" );
    			rfqData.to_.companyPhoneNo_		= rs.getString( "to_regn_key" );
    			rfqData.userFrom_.email_		= rs.getString( "user_from" );
    			rfqData.userTo_.email_			= rs.getString( "user_to" );
    			rfqData.status_					= rs.getString( "status" );
    			rfqData.action_					= rs.getString( "action" );
    			rfqData.isOutsideSupplier_ 		= rs.getInt( "is_outside_supplier" );
    			rfqData.outsideSupplierEmail_ 	= rs.getString( "outside_supplier_email" );
    			rfqData.recurring_	 			= rs.getString( "recurring" );
    			rfqData.isQuoteCreated_			= rs.getInt( "is_quote_created" );
    			
    			rfqData.RFQDate_ 				= rs.getDate( "rfq_date" );
    			rfqData.createdTimestamp_ 		= rs.getTimestamp( "created_timestamp" );
    			
    			RFQList.add( rfqData );
    			
    			rfqData = null;
			}
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::RFQTableUtils.rsToDataList() - " + e );
			return -1;
        }
	}
}
