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

package core.history;

import core.regn.CompanyRegnKey;
import utils.ErrorMaster;

/**
 * File:  TransRatingsData.java 
 *
 * Created on September 2, 2013 9:25:18 AM
 */


public class TransRatingsData
{
	public long             transRatingId_;
	public long 			transId_;
	public CompanyRegnKey 	regnKey_;
	public CompanyRegnKey 	customerKey_;
	public int 				starCount_;
        private static ErrorMaster errorMaster_ = null;


	
	/*Constructor - To Initialize the class variables*/
	public TransRatingsData( )
	{
		transRatingId_  = -1;
		transId_ 		= -1;
		regnKey_ 		= new CompanyRegnKey( );
		customerKey_ 	= new CompanyRegnKey( );
		starCount_ 		= 0;
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
