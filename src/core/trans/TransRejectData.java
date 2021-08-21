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
		System.out.println( "transType_				= " + transType_ );

		System.out.println( "transTypeId_			= " + transTypeId_ );
		System.out.println( "from					= " + from_.toString( ) );

		System.out.println( "to						= " + to_.toString( ) );
		System.out.println( "userFrom				= " + userFrom_.toString( ) );

		System.out.println( "userTo					= " + userTo_.toString( ) );
		System.out.println( "Action					= " + action_ );
		System.out.println( "Status					= " + status_ );
		
		System.out.println( "rejectReason_			= " + rejectReason_ );
		System.out.println( "createdTimestamp_		= " + createdTimestamp_ );
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
