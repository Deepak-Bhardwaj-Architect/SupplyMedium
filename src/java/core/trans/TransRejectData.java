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
 * File:  TransRejectData.java 
 *
 * Created on Jul 10, 2013 12:05:10 PM
 */

public class TransRejectData
{
	// This is the transaction reject id
	public long 		transRejectId_;
	
	// This is the transaction id
	public long              transId_;
	
	// This is the transaction type (RFQ,Quote,PO and Invoice)
	public String              transType_;

	// This is the transaction type id ( RFQId,QuoteId,POId and InvoiceId )
	public long              transTypeId_;

	// This is the companyRegnKey who sent the inquire request
	public CompanyRegnKey    from_;

	// This is the companyRegnKey who receive the inquire request
	public CompanyRegnKey    to_;

	// This is the userProfileKey who sent the inquire request
	public UserProfileKey    userFrom_;

	// This is the userProfileKey who receive the inquire request
	public UserProfileKey    userTo_;

	// This is the reject reason details
	public String rejectReason_;

	
	// This is the current state of the transaction
	public String status_;
	
	
	// This is the action of the transaction
	public String action_;
	
	// Created TimeStamp
	public Timestamp createdTimestamp_;
        
        private static ErrorMaster errorMaster_ = null;


	

	/*
	 * Method : TransInquireData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransRejectData()
	{
		from_ = new CompanyRegnKey( );
		
		to_  = new CompanyRegnKey( );
		
		userFrom_ = new UserProfileKey( );
		
		userTo_ = new UserProfileKey( );
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
		errorMaster_.insert( "from					= " + from_.toString( ) );

		errorMaster_.insert( "to						= " + to_.toString( ) );
		errorMaster_.insert( "userFrom				= " + userFrom_.toString( ) );

		errorMaster_.insert( "userTo					= " + userTo_.toString( ) );
		errorMaster_.insert( "Action					= " + action_ );
		errorMaster_.insert( "Status					= " + status_ );
		
		errorMaster_.insert( "rejectReason_			= " + rejectReason_ );
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
		transRejectId_			= -1;
		transId_ 				= -1;

		transType_				= null;
		transTypeId_ 			= -1;
		
		from_ 					= null;
		to_ 					= null;
		
		userFrom_ 				= null;
		userTo_					= null;
		
		action_					= null;
		rejectReason_			= null;

		status_					= null;
		createdTimestamp_ 		= null;
	}
}
