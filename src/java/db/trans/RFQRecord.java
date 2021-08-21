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
import utils.ErrorMaster;


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
                public String outsideSupplierNmae_;
                
		public String outsideSupplierEmail_;
                
                public String outsideSuppliercountry;
                
                public String outsideSupplierstate;
                
                public String outsideSuppliercity;
                
                public String outsideSupplieraddress;
                
                public String outsideSupplierzipcode;
		
		// This string indicate the recurring type
		public String recurring_;
		
		// RFQ Date
		public Date RFQDate_;
		
		// This is the RFQ form added in SM date
		public Timestamp createdTimestamp_;
		
		// It is indicate that quote create or not for this RFQ. If this value 1
	    // Quote created otherwise not at all
	    public int isQuoteCreated_;
            private static ErrorMaster errorMaster_ = null;


		
	    
	    
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
		errorMaster_.insert( "RFQId_					= " + RFQId_ );
		errorMaster_.insert( "QuoteRef               = " + quoteRef_);
		
		errorMaster_.insert( "from					= " + from_.toString( ) );
		errorMaster_.insert( "to						= " + to_.toString( ) );
		
		errorMaster_.insert( "status_				= " + status_ );
		
		errorMaster_.insert( "isOutsideSupplier_		= " + isOutsideSupplier_ );
		errorMaster_.insert( "outsideSupplierEmail_	= " + outsideSupplierEmail_ );
		
		errorMaster_.insert( "recurring_				= " + recurring_ );
		errorMaster_.insert( "RFQDate_				= " + RFQDate_ );
		errorMaster_.insert( "createdTimestamp_		= " + createdTimestamp_ );
		
		errorMaster_.insert( "isQuoteCreated_        = " + isQuoteCreated_);
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
                outsideSupplierNmae_=null;
		outsideSupplierEmail_ 	= null;
                
                outsideSuppliercountry 	= null;
                
                outsideSupplierstate 	= null;
                
                outsideSuppliercity 	= null;
                
                outsideSupplieraddress 	= null;
                
                outsideSupplierzipcode 	= null;
		
		recurring_ 				= null;
		RFQDate_ 				= null;
		createdTimestamp_ 		= null;
		
		isQuoteCreated_         = 0;
		
	}


}
