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

import java.sql.Date;
import java.sql.Timestamp;

/**
 * File:  InvoiceRecord.java 
 *
 * Created on Jul 6, 2013 2:28:27 PM
 */
public class InvoiceRecord
{
	// This is the transaction id
	public long     transId_;

	// This is the unique id of this invoice
	public long     invoiceId_;

	// This is the companyRegnKey who sent the invoice request
	public String   from_;

	// This is the companyRegnKey who receive the invoice request
	public String   to_;

	// This is the userProfileKey who sent the invoice request
	public String   userFrom_;

	// This is the userProfileKey who receive the invoice request
	public String  	userTo_;

	// Current status of the invoice request
	public String	status_;

	/*
	 * This is the current action of the transaction.It should be Accept or
	 * Reject or Inquire
	 */
	public String 	action_;

	/*
	 * This flag is set to true if invoice send to outside buyer who is not
	 * registered with SupplyMedium. Flag set to false invoice send to registered
	 * buyer
	 */
	public int      isOutsideBuyer_;

	/* Email id for outside buyer */
	public String   outsideBuyerEmail_;

	// This string indicate the recurring type
	public String   recurring_;

	// Invoice Date
	public Date     invoiceDate_;
	
	// Total amount of the Invoice with out tax amount
	public double 	totalListPrice_;  
	
	// Percentage of tax for Invoice
	public double 	taxPercentage_;
	
	// Freight handling charges
	public double freightHandling_;
	
	// Total amount of the invoice with tax amount
	public double 	totalPrice_;
	
	// Invoice discount
	public double 	discount_;
	
	// Invoice billing period
	public String invoiceBillingPeriod_;
	
	//Invoice due date
	public Date invoiceDueDate_;
	
	//Invoice no.
	public String invoiceNo_;
	
	// Freight carrier
	public String freightCarrier_;
		
	// Bill of landing
	public long billOfLanding_;
		
	//Freight weight
	public double freightWeight_;
		
	//Freight weight unit
	public String freightWeightUnit_;
		
	//Date shipped
	public Date dateShipped_;
	
	// is Non po invoice
	public int isNonPOInvoice_;
		
	// PO Number
	public String poNo_;
			
	// is diff address
	public int isDiffAdd_;
	
	// if address different, email
	public String diffEmail_;
	
	// This is the Invoice form added in SM date
	public Timestamp	createdTimestamp_;

	/*
	 * Method : PORecord -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public InvoiceRecord( )
	{
		
	}
	
	/*
	 * Method : show( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "transId_				= " + transId_ );
		System.out.println( "invoiceId_				= " + invoiceId_ );
		System.out.println( "from					= " + from_.toString( ) );

		System.out.println( "to						= " + to_.toString( ) );
		System.out.println( "userFrom				= " + userFrom_.toString( ) );
		System.out.println( "userTo					= " + userTo_.toString( ) );
		
		System.out.println( "status_				= " + status_ );
		System.out.println( "isOutsideBuyer_		= " + isOutsideBuyer_ );
		System.out.println( "outsideBuyerEmail_		= " + outsideBuyerEmail_ );
		
		System.out.println( "recurring_				= " + recurring_ );
		System.out.println( "invoiceDate_			= " + invoiceDate_ );
		System.out.println( "createdTimestamp_		= " + createdTimestamp_ );
		
		System.out.println( "totalListPrice_		= " + totalListPrice_ );
		System.out.println( "taxPercentage_			= " + taxPercentage_ );
		
		System.out.println( "freightHandling_		= " + freightHandling_ );
		System.out.println( "totalPrice_			= " + totalPrice_ );
		
		System.out.println( "discount_				= " + discount_ );
		System.out.println( "invoiceBillingPeriod_	= " + invoiceBillingPeriod_ );
		System.out.println( "invoiceDueDate_		= " + invoiceDueDate_ );
		
		System.out.println( "invoiceNo_				= " + invoiceNo_ );
		System.out.println( "freightCarrier_		= " + freightCarrier_ );
		
		System.out.println( "billOfLanding_			= " + billOfLanding_ );
		System.out.println( "freightWeight_			= " + freightWeight_ );

		System.out.println( "freightWeightUnit_		= " + freightWeightUnit_ );
		System.out.println( "dateShipped_			= " + dateShipped_ );
		
		System.out.println( "isNonPOInvoice_		= " + isNonPOInvoice_ );
		System.out.println( "poNo_					= " + poNo_ );
		System.out.println( "isDiffAdd_				= " + isDiffAdd_ );
		
		System.out.println( "diffEmail				= " + diffEmail_ );
	}
	
	
	/*
	 * Method : clear( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */
	
	public void clear( )
	{
		transId_				= -1;
		invoiceId_				= -1;
		
		from_ 					= null;
		to_ 					= null;
		
		userFrom_ 				= null;
		userTo_					= null;
		
		status_ 				= null;
		
		isOutsideBuyer_ 		= -1;
		outsideBuyerEmail_ 		= null;
		
		recurring_ 				= null;
		invoiceDate_ 			= null;
		createdTimestamp_ 		= null;
		
		totalListPrice_ 		= 0.0f;
		taxPercentage_ 			= 0.0f;
		totalPrice_ 			= 0.0f;
		
		freightHandling_		= 0.0f;
		freightCarrier_ 		= null;
		billOfLanding_ 			= -1;
		
		freightWeight_ 			= 0.0f;
		freightWeightUnit_ 		= null;
		dateShipped_			= null;
		
		isNonPOInvoice_			= 0;
		poNo_					= null;
		isDiffAdd_				= 0;	
		
		diffEmail_ = null;
	}
}
