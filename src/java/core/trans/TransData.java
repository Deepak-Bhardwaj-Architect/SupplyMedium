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
package core.trans;

import java.sql.Timestamp;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File:  TransData.java 
 *
 * Created on 20-Jun-2013 4:36:36 PM
 */
public class TransData
{
	// This is the transaction id 
	public long transId_;
	
	/* This indicate the type of the transaction.
	 * It should be RFQ or Quote or PO or Invoice*/
	public String transType_;
	
	/* This is the id of the transaction type.It should be
	 * RFQId or QuoteId or POId or InvoiceId*/
	public long transTypeId_;
	
	/* This is the current action of the transaction.It should be
	 * Accept or Reject or Inquire*/
	public String action_;
	
	// This is the companyRegnKey who sent the RFQ request
	public CompanyRegnKey from_;
		
	// This is the companyRegnKey who receive the RFQ request
	public CompanyRegnKey to_;
	
	// This is the userProfileKey who sent the RFQ request 
	public UserProfileKey userFrom_;
			
	// This is the userProfileKey who receive the RFQ request
	public UserProfileKey userTo_;
	
	// Current state of the transaction
	public String status_;
	
	// created time stamp
	public Timestamp createdTimestamp_;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : TransData.java() -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransData()
	{
		from_ = new CompanyRegnKey( );
		
		to_  = new CompanyRegnKey( );
		
		userFrom_ = new UserProfileKey( );
		
		userTo_ = new UserProfileKey( );
		
		transId_ = -1;
		
		transTypeId_ = -1;
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
		errorMaster_.insert( "transType_				= " + transType_ );
		
		errorMaster_.insert( "transTypeId_			= " + transTypeId_ );
		errorMaster_.insert( "action_				= " + action_ );
		
		errorMaster_.insert( "from_					= " + from_ );
		errorMaster_.insert( "to_					= " + to_ );
		
		errorMaster_.insert( "userFrom_				= " + userFrom_ );
		errorMaster_.insert( "userTo_				= " + userTo_ );
		
		
		errorMaster_.insert( "status_				= " + status_ );
		errorMaster_.insert( "createdTimestamp_		= " + createdTimestamp_ );
		
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
		transId_ 			= -1;
		transType_ 			= null;
		
		transTypeId_ 		= -1;
		action_ 			= null;
		
		from_ 			    = null;
		to_ 				= null;
		
		userFrom_ 			= null;
		userTo_ 			= null;
		
		status_ 			= null;
		createdTimestamp_	= null;
	}


}
