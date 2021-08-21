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

import utils.ErrorMaster;

/**
 * File:  TransactionRatingRecord.java 
 *
 * Created on Sep 26, 2013 5:51:55 PM
 */
public class TransactionRatingRecord
{

	public long transRatingId_;
	
	public long transId_;
	
	public String regnKey_;
	
	public String customerKey_;
	
	public int starCount_;
	
	public TransactionRatingRecord( )
	{
		transRatingId_	= -1;
		
		transId_ 		= -1;
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
		errorMaster_.insert( "transRatingId_	= " + transRatingId_ );
		
		errorMaster_.insert( "transId_		= " + transId_ );
		
		errorMaster_.insert( "regnKey_		= " + regnKey_ );
		
		errorMaster_.insert( "customerKey_	= " + customerKey_ );
		
		errorMaster_.insert( "starCount_		= " + starCount_ );
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
		transRatingId_  = -1;
		
		transId_ 		= -1;
		
		regnKey_ 		= null;
		
		customerKey_ 	= null;
		
		starCount_ 		= 0;
	}

}
