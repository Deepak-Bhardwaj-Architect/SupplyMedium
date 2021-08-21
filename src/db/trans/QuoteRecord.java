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
 * File:  QuoteRecord.java 
 *
 * Created on Jul 3, 2013 10:07:10 AM
 */

public class QuoteRecord
{
	// This is the transaction id
	public long     transId_;

	// This is the unique id of this Quote
	public long     quoteId_;
	
	// This is the quote reference number entered by user
	public String quoteRef_;

	// This is the companyRegnKey who sent the RFQ request
	public String   from_;

	// This is the companyRegnKey who receive the RFQ request
	public String   to_;

	// This is the userProfileKey who sent the RFQ request
	public String   userFrom_;

	// This is the userProfileKey who receive the RFQ request
	public String  	userTo_;

	// Current status of the RFQ request
	public String	status_;

	/*
	 * This is the current action of the transaction.It should be Accept or
	 * Reject or Inquire
	 */
	public String 	action_;

	/*
	 * This flag is set to true if RFQ send to outside supplier who is not
	 * registered with SupplyMedium. Flag set to false RFQ send to registered
	 * supplier
	 */
	public int      isOutsideSupplier_;

	/* Email id for outside supplier */
	public String   outsideSupplierEmail_;

	// This string indicate the recurring type
	public String   recurring_;

	// RFQ Date
	public Date     quoteDate_;
	
	// Total amount of the Quotation with out tax amount
	public double 	totalListPrice_;  
	
	// Percentage of tax for quotaion
	public double 	taxPercentage_;
	
	// Total amount of the Quotation with tax amount
	public double 	totalPrice_;
	
	// This is the RFQ form added in SM date
	public Timestamp	createdTimestamp_;
	
	// It is indicate that purchase order create or not for this Quote. If this value 1
    // Purchase Order created otherwise not at all
    public int isPOCreated_;

	/*
	 * Method : QuoteRecord -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public QuoteRecord( )
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
		System.out.println( "quoteId_				= " + quoteId_ );
		System.out.println( "QuoteRef               = " + quoteRef_);
		
		System.out.println( "from					= " + from_.toString( ) );
		System.out.println( "to						= " + to_.toString( ) );
		
		System.out.println( "userFrom				= " + userFrom_.toString( ) );
		System.out.println( "userTo					= " + userTo_.toString( ) );
		
		System.out.println( "status_				= " + status_ );
		
		System.out.println( "isOutsideSupplier_		= " + isOutsideSupplier_ );
		System.out.println( "outsideSupplierEmail_	= " + outsideSupplierEmail_ );
		
		System.out.println( "recurring_				= " + recurring_ );
		System.out.println( "RFQDate_				= " + quoteDate_ );
		System.out.println( "createdTimestamp_		= " + createdTimestamp_ );
		
		System.out.println( "totalListPrice_		= " + totalListPrice_ );
		System.out.println( "taxPercentage_			= " + taxPercentage_ );
		System.out.println( "totalPrice_			= " + totalPrice_ );
		
		System.out.println( "isPOCreated_			= " + isPOCreated_ );
		
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
		transId_ 				= -1;
		quoteId_ 				= -1;
		quoteRef_ 				= null;
		
		from_ 					= null;
		to_ 					= null;
		
		userFrom_ 				= null;
		userTo_					= null;
		
		status_ 				= null;
		
		isOutsideSupplier_ 		= -1;
		outsideSupplierEmail_ 	= null;
		
		recurring_ 				= null;
		quoteDate_ 				= null;
		createdTimestamp_ 		= null;
		
		totalListPrice_ 		= 0.0f;
		taxPercentage_ 			= 0.0f;
		totalPrice_ 			= 0.0f;
		
		isPOCreated_ = 0;
	}
	
}
