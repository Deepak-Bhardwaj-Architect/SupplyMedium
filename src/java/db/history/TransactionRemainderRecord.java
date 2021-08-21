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
package db.history;

import java.sql.Date;
import utils.ErrorMaster;

/**
 * File:  TransactionRemainderRecord.java 
 *
 * Created on Sep 26, 2013 4:05:49 PM
 */
public class TransactionRemainderRecord
{

	public long transRemainderId_;
	
	public long transId_;
	
	public String regnKey_;
	
	public String customerKey_;
	
	public String remainder_;
	
	public Date dueDate_;
	
	
	public TransactionRemainderRecord( )
	{
		transRemainderId_ 	= -1;
		
		transId_ 			= -1;
	}
	
	
	/* Method: show
	 * 
	 * Input: none
	 *
	 * Return: void
	 *
	 * Purpose: To print the class variables in the console
	 */
	
	public void show( )
	{
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "transRemainderId_	= " + transRemainderId_ );
		errorMaster_.insert( "transId_			= " + transId_ );
		errorMaster_.insert( "regnKey_			= " + regnKey_ );
		errorMaster_.insert( "customerKey_		= " + customerKey_ );
		errorMaster_.insert( "remainder_			= " + remainder_ );
		errorMaster_.insert( "dueDate_			= " + dueDate_ );
	}
	
	/* Method: clear
	 * 
	 * Input: none
	 *
	 * Return: void
	 *
	 * Purpose: To release the class variables from memory
	 */
	
	public void clear( )
	{
		transRemainderId_ 	= -1;
		transId_ 			= -1;
		regnKey_ 			= null;
		customerKey_ 		= null;
		remainder_ 			= null;
		dueDate_ 			= null;
	}

}
