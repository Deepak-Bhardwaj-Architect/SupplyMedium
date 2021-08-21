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
import core.trans.InvoiceData;
import utils.ErrorMaster;

/**
 * File:  InvoiceTableUtils.java 
 *
 * Created on Jul 6, 2013 2:28:43 PM
 */

public class InvoiceTableUtils
{
	private String tableName_;
	
	private ErrorLogger errLogger_;
	
	/*Table fields
 
  	invoice_id	 	 	 	 	 	 	
	trans_id,		 	 	 	 	 	 	
	from_regn_key,		 	 	 	 	 	 	 
	to_regn_key,		 	 	 	 	 	 	 
	user_from,		 	 	 	 	 	 	 
	user_to,		 	 	 	 	 	 	 
	status,		 	 	 	 	 	 	 
	action,		 	 	 	 	 	 	 
	is_outside_buyer,		 	 	 	 	 	 	
	outside_buyer_email,		 	 	 	 	 	 	 
	recurring,		 	 	 	 	 	 	 
	invoice_date,		 	 	 	 	 	 	
	total_list_price,		 	 	 	 	 	 	
	tax_percentage,		 	 	 	 	 	 	
	total_price,		 	
	freight_handling, 	 	 	 	 	
	discount,		 	 	 	 	 	 	
	invoice_billing_period,		 	 	 	 	 	 	 
	invoice_due_date,		 	 	 	 	 	 	
	invoice_no,		 
	freight_carrier,
	bill_of_landing,
	freight_weight,
	freight_weight_unit,
	shipped_date,	 	 	 	 
	is_nonpo_invoice,		 	 	 	 	 	 	
	po_num,		 	 	 	 	 	 	 
	is_diff_address,	 	 
	created_timestamp
  
 */
        
        private static ErrorMaster errorMaster_ = null;


	/*
	 * Method : InvoiceTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public InvoiceTableUtils( )
	{
		this.tableName_ = "invoice";
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : InvoiceData obj, InvoiceRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the InvoiceData object to
	 * InvoiceRecord object. And add to InvoiceRecord parameter so it is copied
	 * to caller method.
	 */
	
	public int dataToRecord( InvoiceData invoiceData, InvoiceRecord invoiceRecord )
	{
		try
        {
			invoiceRecord.status_	= invoiceData.status_;
			invoiceRecord.from_		= invoiceData.from_.toString( );
			invoiceRecord.to_		= invoiceData.to_.toString( );
			invoiceRecord.transId_	= invoiceData.transId_;
			invoiceRecord.userFrom_	= invoiceData.userFrom_.toString( );
			invoiceRecord.userTo_	= invoiceData.userTo_.toString( );

			invoiceRecord.invoiceNo_	= invoiceData.invoiceNo_;
			invoiceRecord.invoiceDate_	= invoiceData.invoiceDate_;
			invoiceRecord.invoiceId_	= invoiceData.invoiceId_;
			invoiceRecord.action_ 		= invoiceData.action_;
			invoiceRecord.recurring_	= invoiceData.recurring_;
			
			invoiceRecord.taxPercentage_ 	= invoiceData.taxPercentage_;
			invoiceRecord.totalListPrice_	= invoiceData.totalListPrice_;
			invoiceRecord.totalPrice_		= invoiceData.totalPrice_;
			invoiceRecord.freightHandling_	= invoiceData.freightHandling_;
			
			invoiceRecord.createdTimestamp_		= invoiceData.createdTimestamp_;
			invoiceRecord.isOutsideBuyer_		= invoiceData.isOutsideBuyer_;
			invoiceRecord.outsideBuyerEmail_	= invoiceData.outsideBuyerEmail_;
                        
                        invoiceRecord.outsideSuppliercountry	= invoiceData.outsideSuppliercountry;
                        invoiceRecord.outsideSupplierstate	= invoiceData.outsideSupplierstate;
                        invoiceRecord.outsideSuppliercity	= invoiceData.outsideSuppliercity;
                        invoiceRecord.outsideSupplieraddress	= invoiceData.outsideSupplieraddress;
                        invoiceRecord.outsideSupplierzipcode	= invoiceData.outsideSupplierzipcode;
                        
			invoiceRecord.discount_				= invoiceData.discount_;
			invoiceRecord.invoiceBillingPeriod_	= invoiceData.invoiceBillingPeriod_;
			invoiceRecord.invoiceDueDate_		= invoiceData.dueDate_;
			
			invoiceRecord.freightCarrier_		= invoiceData.freightCarrier_;
			invoiceRecord.billOfLanding_		= invoiceData.billOfLanding_;
			invoiceRecord.freightWeight_		= invoiceData.freightWeight_;
			invoiceRecord.freightWeightUnit_	= invoiceData.freightWeightUnit_;
			invoiceRecord.dateShipped_			= invoiceData.dateShipped_;
			
			invoiceRecord.isNonPOInvoice_		= invoiceData.isNonPOInvoice_;
			invoiceRecord.isDiffAdd_			= invoiceData.isDiffAdd_;
			invoiceRecord.poNo_					= invoiceData.poNo_;
			
			invoiceRecord.diffEmail_			= invoiceData.diffEmail_;
                        invoiceRecord.selected_address			= invoiceData.selected_address;
			
			return 0;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::InvoiceTableUtils.dataToRecord() - " + e );
        	
        	return -1;
        }
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : InvoiceRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using InvoiceRecord and returns as
	 * string
	 */

	public String formInsertQuery( InvoiceRecord invoiceRecord )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		
		query		 = query + "trans_id, from_regn_key, to_regn_key, ";
		query		 = query + "user_from, user_to, status, action, ";
		query		 = query + "is_outside_buyer, outside_buyer_email, recurring, ";
		query		 = query + "invoice_date, total_list_price, tax_percentage, total_price, ";
		query 	     = query + "freight_handling, discount, invoice_billing_period, invoice_due_date, ";
		query		 = query + "invoice_no, freight_carrier, bill_of_landing, freight_weight, ";
		query		 = query + "freight_weight_unit, shipped_date, is_nonpo_invoice, po_num, diff_email,is_diff_address,diff_address,outside_supplier_address)";
		
		query		 = query + " VALUES";
		
		query		 = query + "(";
		
		query		 = query + "'" + invoiceRecord.transId_  + "', ";
		query		 = query + "'" + invoiceRecord.from_ + "', ";
		query		 = query + "'" + invoiceRecord.to_ + "', ";

		query   	 = query + "'" + invoiceRecord.userFrom_ + "', ";
		query		 = query + "'" + invoiceRecord.userTo_ + "', ";
		query 		 = query + "'" + invoiceRecord.status_ + "', ";

		query 		 = query + "'" + invoiceRecord.action_ + "', ";
		query 		 = query + "'" + invoiceRecord.isOutsideBuyer_ + "', ";
		query 		 = query + "'" + invoiceRecord.outsideBuyerEmail_ + "', ";

		query 		 = query + "'" + invoiceRecord.recurring_ + "', ";
		query 		 = query + "'" + invoiceRecord.invoiceDate_ + "', ";
		query 		 = query + "'" + invoiceRecord.totalListPrice_ + "', ";
		
		query 		 = query + "'" + invoiceRecord.taxPercentage_ + "', ";
		query 		 = query + "'" + invoiceRecord.totalPrice_ + "', ";
		query		 = query + "'" + invoiceRecord.freightHandling_ + "', ";
		
		query 		 = query + "'" + invoiceRecord.discount_ + "', ";
		query 		 = query + "'" + invoiceRecord.invoiceBillingPeriod_ + "', ";
		query 		 = query + "'" + invoiceRecord.invoiceDueDate_ + "', ";

		query 		 = query + "'" + invoiceRecord.invoiceNo_ + "', ";
		query 		 = query + "'" + invoiceRecord.freightCarrier_ + "', ";
		query 		 = query + "'" + invoiceRecord.billOfLanding_ + "', ";

		query 		 = query + "'" + invoiceRecord.freightWeight_ + "', ";
		query 		 = query + "'" + invoiceRecord.freightWeightUnit_ + "', ";
		query 		 = query + "'" + invoiceRecord.dateShipped_+ "', ";
		
		query 		 = query + "'" + invoiceRecord.isNonPOInvoice_+ "', ";
		query 		 = query + "'" + invoiceRecord.poNo_+ "', ";
		query 		 = query + "'" + invoiceRecord.diffEmail_+ "', ";
		query 		 = query + "'" + invoiceRecord.isDiffAdd_+ "',";
		query 		 = query + "'" + invoiceRecord.selected_address+ "',";
                query 		 = query + "'" + invoiceRecord.outsideSupplieraddress+", "+invoiceRecord.outsideSuppliercity+", "+invoiceRecord.outsideSupplierstate+", "+invoiceRecord.outsideSuppliercountry+", "+invoiceRecord.outsideSupplierzipcode+ "'";
		query 	  	 = query + ")";
 		
		errorMaster_.insert( "Query = " + query );
		
		return query;
	}
	
	/*
	 * Method : getFilterString( )
	 * 
	 * Input :	InvoiceRecord filter, StringHolder query
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the invoiceRecord it form the filter query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getFilterString( InvoiceRecord filter, StringHolder query )
	{
		try
		{
			query.value = filter.createdTimestamp_ != null ?  
							" created_timestamp='" + filter.createdTimestamp_ + "' AND" : "";

			query.value += filter.action_ != null ? 
							" action= '" + filter.action_ + "' AND" : "";

			query.value += filter.from_ != null ? 
							" from_regn_key='" + filter.from_ + "' AND" : "";
			
			query.value += filter.outsideBuyerEmail_ != null ? 
							" outside_buyer_email='" + filter.outsideBuyerEmail_ + "' AND" : "";

			query.value += filter.recurring_ != null ? 
							" recurring='" + filter.recurring_ + "' AND" : "";

			query.value += filter.status_ != null ? 
							" status='" + filter.status_ + "' AND" : "";

			query.value += filter.to_ != null ?
							" to_regn_key='" + filter.to_ + "' AND" : "";

			query.value += filter.userFrom_ != null ?
							" user_from='" + filter.userFrom_ + "' AND" : "";
			
			query.value += filter.userTo_ != null ? 
							" user_to='" + filter.userTo_ + "' AND" : "";

			query.value += filter.isOutsideBuyer_ != -1 ?
							" is_outside_buyer='" + filter.isOutsideBuyer_ + "' AND" : "";

			query.value += filter.invoiceDate_ != null ?
							" invoice_date='" + filter.invoiceDate_ + "' AND" : "";
			
			query.value += filter.transId_ != -1 ?
							" trans_Id='" + filter.transId_ + "' AND" : "";
			
			query.value += filter.taxPercentage_ != 0.00f ?
							"tax_percentage='" + filter.taxPercentage_ + "' AND" : "";
			
			query.value += filter.totalListPrice_ != 0.00f ?
							"total_list_price='" + filter.totalListPrice_ + "' AND" : ""; 

			query.value += filter.totalPrice_ != 0.00f ? 
							"total_price='" + filter.totalPrice_ + "' AND" : "";
			
			query.value += filter.freightHandling_ != 0.00f ? 
					"freight_handling='" + filter.freightHandling_ + "' AND" : "";
			
			query.value += filter.invoiceId_ != -1 ?
							"invoice_id='" + filter.invoiceId_ + "' AND" : "";
			
			query.value += filter.discount_ != 0.00f ?
					"discount='" + filter.discount_ + "' AND" : "";
			
			query.value += filter.invoiceBillingPeriod_ != null ?
					"invoice_billing_period='" + filter.invoiceBillingPeriod_ + "' AND" : "";
			
			query.value += filter.invoiceNo_ != null ?
					"invoice_no='" + filter.invoiceNo_ + "' AND" : "";
			
			query.value += filter.invoiceDueDate_ != null ?
					"invoice_due_date='" + filter.invoiceDueDate_ + "' AND" : "";
			
			query.value += filter.freightCarrier_ != null ?
					"freight_carrier='" + filter.freightCarrier_ + "' AND" : "";
			
			query.value += filter.billOfLanding_ != -1 ?
					"bill_of_landing='" + filter.billOfLanding_ + "' AND" : "";
			
			query.value += filter.freightWeight_ != 0.0f ?
					"freight_weight='" + filter.freightWeight_ + "' AND" : "";
			
			query.value += filter.freightWeightUnit_ != null ?
					"freight_weight_unit='" + filter.freightWeightUnit_ + "' AND" : "";
			
			query.value += filter.dateShipped_ != null ?
					"shipped_date='" + filter.dateShipped_ + "' AND" : "";
			
			query.value += filter.isDiffAdd_ != -1 ?
					"is_diff_address='" + filter.isDiffAdd_ + "' AND" : "";
			
			query.value += filter.diffEmail_ != null ?
					"diff_email='" + filter.diffEmail_ + "' AND" : "";
			
			query.value += filter.isNonPOInvoice_ != -1 ?
					"is_nonpo_invoice='" + filter.isNonPOInvoice_ + "' AND" : "";
			
			query.value += filter.poNo_ != null ?
					"po_num='" + filter.poNo_ + "' AND" : "";
			
			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );
			
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::InvoiceTableUtils.getFilterString() - " + ex );
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
	
	public int getUpdateString( InvoiceRecord filter, StringHolder query )
	{ 
		try
		{
			query.value = filter.createdTimestamp_ != null ?  
					" created_timestamp='" + filter.createdTimestamp_ + "' ," : "";

        	query.value += filter.action_ != null ? 
        					" action= '" + filter.action_ + "' ," : "";
        
        	query.value += filter.from_ != null ? 
        					" from_regn_key='" + filter.from_ + "' ," : "";
        	
        	query.value += filter.outsideBuyerEmail_ != null ? 
        					" outside_buyer_email='" + filter.outsideBuyerEmail_ + "' ," : "";
        
        	query.value += filter.recurring_ != null ? 
        					" recurring='" + filter.recurring_ + "' ," : "";
        
        	query.value += filter.status_ != null ? 
        					" status='" + filter.status_ + "' ," : "";
        
        	query.value += filter.to_ != null ?
        					" to_regn_key='" + filter.to_ + "' ," : "";
        
        	query.value += filter.userFrom_ != null ?
        					" user_from='" + filter.userFrom_ + "' ," : "";
        	
        	query.value += filter.userTo_ != null ? 
        					" user_to='" + filter.userTo_ + "' ," : "";
        
        	query.value += filter.isOutsideBuyer_ != -1 ?
        					" is_outside_buyer='" + filter.isOutsideBuyer_ + "' ," : "";
        
        	query.value += filter.invoiceDate_ != null ?
        					" invoice_date='" + filter.invoiceDate_ + "' ," : "";
        	
        	query.value += filter.transId_ != -1 ?
        					" trans_Id='" + filter.transId_ + "' ," : "";
        	
        	query.value += filter.taxPercentage_ != 0.00f ?
        					"tax_percentage='" + filter.taxPercentage_ + "' ," : "";
        	
        	query.value += filter.totalListPrice_ != 0.00f ?
        					"total_list_price='" + filter.totalListPrice_ + "' ," : ""; 
        
        	query.value += filter.totalPrice_ != 0.00f ? 
        					"total_price='" + filter.totalPrice_ + "' ," : "";
        	
        	query.value += filter.freightHandling_ != 0.00f ? 
					"freight_handling='" + filter.freightHandling_ + "' ," : "";
        	
        	query.value += filter.discount_ != 0.00f ?
        			"discount='" + filter.discount_ + "' ," : "";
        	
        	query.value += filter.invoiceBillingPeriod_ != null ?
        			"invoice_billing_period='" + filter.invoiceBillingPeriod_ + "' ," : "";
        	
        	query.value += filter.invoiceNo_ != null ?
        			"invoice_no='" + filter.invoiceNo_ + "' ," : "";
        	
        	query.value += filter.invoiceDueDate_ != null ?
        			"invoice_due_date='" + filter.invoiceDueDate_ + "' ," : "";

        	query.value += filter.freightCarrier_ != null ?
					"freight_carrier='" + filter.freightCarrier_ + "' ," : "";
			
			query.value += filter.billOfLanding_ != -1 ?
					"bill_of_landing='" + filter.billOfLanding_ + "' ," : "";
			
			query.value += filter.freightWeight_ != 0.0f ?
					"freight_weight='" + filter.freightWeight_ + "' ," : "";
			
			query.value += filter.freightWeightUnit_ != null ?
					"freight_weight_unit='" + filter.freightWeightUnit_ + "' ," : "";
			
			query.value += filter.dateShipped_ != null ?
					"shipped_date='" + filter.dateShipped_ + "' ," : "";
			
			query.value += filter.isDiffAdd_ != -1 ?
					"is_diff_address='" + filter.isDiffAdd_ + "' ," : "";
			
			query.value += filter.diffEmail_ != null ?
					"diff_email='" + filter.diffEmail_ + "' ," : "";
			
			
			query.value += filter.isNonPOInvoice_ != -1 ?
					"is_nonpo_invoice='" + filter.isNonPOInvoice_ + "' ," : "";
			
			query.value += filter.poNo_ != null ?
					"po_num='" + filter.poNo_ + "' ," : "";
        	
			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value  =   query.value.substring( 0, query.value.length( ) - 1  ) +
			    				" WHERE invoice_id='"+filter.invoiceId_ +"'";

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::InvoiceTableUtils.getUpdateString() - " + ex );
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
	 * Purpose: It is used to convert the ResultSet object to List<InvoiceData> object.
	 * And add to invoiceList parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<InvoiceData> invoiceList )
	{
		try
        {
			while( rs.next( ) )
			{			
    			InvoiceData invoiceData = new InvoiceData( );
    			
    			invoiceData.invoiceId_	 			= rs.getLong( "invoice_id" );
    			invoiceData.transId_				= rs.getLong( "trans_Id" );
    			invoiceData.from_.companyPhoneNo_	= rs.getString( "from_regn_key" );
    			invoiceData.to_.companyPhoneNo_		= rs.getString( "to_regn_key" );
    			invoiceData.userFrom_.email_		= rs.getString( "user_from" );
    			invoiceData.userTo_.email_			= rs.getString( "user_to" );
    			invoiceData.status_					= rs.getString( "status" );
    			invoiceData.action_					= rs.getString( "action" );
    			invoiceData.isOutsideBuyer_ 		= rs.getInt( "is_outside_buyer" );
    			invoiceData.outsideBuyerEmail_ 		= rs.getString( "outside_buyer_email" );
    			invoiceData.recurring_	 			= rs.getString( "recurring" );
    			invoiceData.invoiceDate_	 		= rs.getDate( "invoice_date" );
    			invoiceData.totalListPrice_			= rs.getDouble( "total_list_price" );
    			invoiceData.taxPercentage_			= rs.getDouble( "tax_percentage" );
    			invoiceData.totalPrice_				= rs.getDouble( "total_price" );
    			invoiceData.freightHandling_		= rs.getDouble( "freight_handling" );
    			invoiceData.discount_				= rs.getDouble( "discount" );
    			invoiceData.invoiceBillingPeriod_	= rs.getString( "invoice_billing_period" );
    			invoiceData.dueDate_				= rs.getDate( "invoice_due_date" );
    			invoiceData.invoiceNo_				= rs.getString( "invoice_no" );
    			invoiceData.freightCarrier_			= rs.getString( "freight_carrier" );
    			invoiceData.freightWeight_			= rs.getDouble( "freight_weight" );
    			invoiceData.freightWeightUnit_		= rs.getString( "freight_weight_unit" );
    			invoiceData.billOfLanding_			= rs.getInt( "bill_of_landing" );
    			invoiceData.dateShipped_			= rs.getDate( "shipped_date" );
    			invoiceData.isDiffAdd_				= rs.getInt( "is_diff_address" );
    			invoiceData.diffEmail_				= rs.getString( "diff_email" );
    			invoiceData.isNonPOInvoice_			= rs.getInt( "is_nonpo_invoice" );
    			invoiceData.poNo_					= rs.getString( "po_num" );
    			invoiceData.createdTimestamp_ 		= rs.getTimestamp( "created_timestamp" );
    			
    			invoiceList.add( invoiceData );
    			
    			invoiceData = null;
			}
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::InvoiceTableUtils.rsToDataList() - " + e );
			return -1;
        }
	}
}
