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

import java.sql.Timestamp;

/**
 * File:  TransRejectRecord.java 
 *
 * Created on Jul 10, 2013 12:06:20 PM
 */

public class TransRejectRecord
{
	// This is the transaction reject id
	public long 		  transRejectId_;
	
	// This is the transaction id
	public long           transId_;

	// This is the transaction type (RFQ,Quote,PO and Invoice)
	public String         transType_;

	// This is the transaction type id ( RFQId,QuoteId,POId and InvoiceId )
	public long           transTypeId_;

	// This is the companyRegnKey who sent the inquire request
	public String from_;

	// This is the companyRegnKey who receive the inquire request
	public String to_;

	// This is the userProfileKey who sent the inquire request
	public String userFrom_;

	// This is the userProfileKey who receive the inquire request
	public String userTo_;

	// This is the reject reason
	public String         rejectReason_;

	// Created TimeStamp
	public Timestamp      createdTimestamp_;

	/*
	 * Method : TransInquireRecord -- constructor
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransRejectRecord( )
	{
		transRejectId_ = -1;
		transId_ = -1;
		
		transType_ = null;
		transTypeId_ = -1;

		from_ = null;
		to_ = null;

		userFrom_ = null;
		userTo_ = null;

		rejectReason_ = null;
		createdTimestamp_ = null;
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
		System.out.println( "transRejectId_			= " + transRejectId_ );
		System.out.println( "transId_				= " + transId_ );
		System.out.println( "transType_				= " + transType_ );
		System.out.println( "transTypeId_			= " + transTypeId_ );

		System.out.println( "from					= " + from_.toString( ) );
		System.out.println( "to						= " + to_.toString( ) );

		System.out.println( "userFrom				= " + userFrom_.toString( ) );
		System.out.println( "userTo					= " + userTo_.toString( ) );

		System.out.println( "details_				= " + rejectReason_ );
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
		transId_ = -1;
		transType_ = null;
		transTypeId_ = -1;

		from_ = null;
		to_ = null;

		userFrom_ = null;
		userTo_ = null;

		rejectReason_ = null;
		createdTimestamp_ = null;

	}
}
