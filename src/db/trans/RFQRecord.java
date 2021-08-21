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

import java.util.Date;
import java.sql.Timestamp;


/**
 * File:  RFQRecord.java 
 *
 * Created on 21-Jun-2013 9:50:56 AM
 */

public class RFQRecord
{

	// This is the transaction id 
		public long transId_;
		
		// This is the unique id of this RFQ
		public long RFQId_;
		
		// This is the quote reference number entered by user
		public String quoteRef_;
		
		// This is the companyRegnKey string who sent the RFQ request
		public String from_;
		
		// This is the companyRegnKey string who receive the RFQ request
		public String to_;
		
		// This is the userProfileKey string who sent the RFQ request 
		public String userFrom_;
			
		// This is the userProfileKey string who receive the RFQ request
		public String userTo_;
			
		
		// Current status of the RFQ request
		public String status_;
		
		/* This is the current action of the transaction.It should be
		 * Accept or Reject or Inquire*/
		public String action_;
		
		
		/* This flag is set to true if RFQ send to outside supplier who is not 
		 * registered with SupplyMedium.
		 * Flag set to false RFQ send to registered supplier */
		public int isOutsideSupplier_;
		
		/* Email id for outside supplier */
		public String outsideSupplierEmail_;
		
		// This string indicate the recurring type
		public String recurring_;
		
		// RFQ Date
		public Date RFQDate_;
		
		// This is the RFQ form added in SM date
		public Timestamp createdTimestamp_;
		
		// It is indicate that quote create or not for this RFQ. If this value 1
	    // Quote created otherwise not at all
	    public int isQuoteCreated_;
		
	    
	    
	/*
	 * Method : RFQRecord -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public RFQRecord()
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
		System.out.println( "RFQId_					= " + RFQId_ );
		System.out.println( "QuoteRef               = " + quoteRef_);
		
		System.out.println( "from					= " + from_.toString( ) );
		System.out.println( "to						= " + to_.toString( ) );
		
		System.out.println( "status_				= " + status_ );
		
		System.out.println( "isOutsideSupplier_		= " + isOutsideSupplier_ );
		System.out.println( "outsideSupplierEmail_	= " + outsideSupplierEmail_ );
		
		System.out.println( "recurring_				= " + recurring_ );
		System.out.println( "RFQDate_				= " + RFQDate_ );
		System.out.println( "createdTimestamp_		= " + createdTimestamp_ );
		
		System.out.println( "isQuoteCreated_        = " + isQuoteCreated_);
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
		RFQId_ 					= -1;
		quoteRef_ 				= null;
		
		from_ 					= null;
		to_ 					= null;
		
		status_ 				= null;
		
		isOutsideSupplier_ 		= -1;
		outsideSupplierEmail_ 	= null;
		
		recurring_ 				= null;
		RFQDate_ 				= null;
		createdTimestamp_ 		= null;
		
		isQuoteCreated_         = 0;
		
	}


}
