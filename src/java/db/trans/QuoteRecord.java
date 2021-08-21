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
import utils.ErrorMaster;

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
        public String outsideSupplierNmae_;
	public String   outsideSupplierEmail_;
        
        public String            outsideSuppliercountry;
        
        public String            outsideSupplierstate;
        
        public String            outsideSuppliercity;
        
        public String            outsideSupplieraddress;
        
        public String            outsideSupplierzipcode;

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
    private static ErrorMaster errorMaster_ = null;



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
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( "transId_				= " + transId_ );
		errorMaster_.insert( "quoteId_				= " + quoteId_ );
		errorMaster_.insert( "QuoteRef               = " + quoteRef_);
		
		errorMaster_.insert( "from					= " + from_.toString( ) );
		errorMaster_.insert( "to						= " + to_.toString( ) );
		
		errorMaster_.insert( "userFrom				= " + userFrom_.toString( ) );
		errorMaster_.insert( "userTo					= " + userTo_.toString( ) );
		
		errorMaster_.insert( "status_				= " + status_ );
		
		errorMaster_.insert( "isOutsideSupplier_		= " + isOutsideSupplier_ );
		errorMaster_.insert( "outsideSupplierEmail_	= " + outsideSupplierEmail_ );
		
		errorMaster_.insert( "recurring_				= " + recurring_ );
		errorMaster_.insert( "RFQDate_				= " + quoteDate_ );
		errorMaster_.insert( "createdTimestamp_		= " + createdTimestamp_ );
		
		errorMaster_.insert( "totalListPrice_		= " + totalListPrice_ );
		errorMaster_.insert( "taxPercentage_			= " + taxPercentage_ );
		errorMaster_.insert( "totalPrice_			= " + totalPrice_ );
		
		errorMaster_.insert( "isPOCreated_			= " + isPOCreated_ );
		
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
                outsideSupplierNmae_=null;
		outsideSupplierEmail_ 	= null;
                outsideSuppliercountry 	= null;
                outsideSupplierstate 	= null;
                outsideSuppliercity 	= null;
                outsideSupplieraddress 	= null;
                outsideSupplierzipcode 	= null;
		
		recurring_ 				= null;
		quoteDate_ 				= null;
		createdTimestamp_ 		= null;
		
		totalListPrice_ 		= 0.0f;
		taxPercentage_ 			= 0.0f;
		totalPrice_ 			= 0.0f;
		
		isPOCreated_ = 0;
	}
	
}
