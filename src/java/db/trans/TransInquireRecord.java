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
import utils.ErrorMaster;


/**
 * File: TransInquireRecord.java
 * 
 * Created on 21-Jun-2013 3:07:50 PM
 */
public class TransInquireRecord
{

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

	// This is the Inquire details
	public String         details_;

	// Created TimeStamp
	public Timestamp      createdTimestamp_;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : TransInquireRecord -- constructor
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransInquireRecord( )
	{
		transId_ = -1;
		transType_ = null;
		transTypeId_ = -1;

		from_ = null;
		to_ = null;

		userFrom_ = null;
		userTo_ = null;
if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		details_ = null;
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
		errorMaster_.insert( "transId_				= " + transId_ );
		errorMaster_.insert( "transType_				= " + transType_ );
		errorMaster_.insert( "transTypeId_			= " + transTypeId_ );

		errorMaster_.insert( "from					= " + from_.toString( ) );
		errorMaster_.insert( "to						= " + to_.toString( ) );

		errorMaster_.insert( "userFrom				= " + userFrom_.toString( ) );
		errorMaster_.insert( "userTo					= " + userTo_.toString( ) );

		errorMaster_.insert( "details_				= " + details_ );
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
		transId_ = -1;
		transType_ = null;
		transTypeId_ = -1;

		from_ = null;
		to_ = null;

		userFrom_ = null;
		userTo_ = null;

		details_ = null;
		createdTimestamp_ = null;

	}

}
