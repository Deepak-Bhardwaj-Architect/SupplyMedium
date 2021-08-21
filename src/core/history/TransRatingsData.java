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
	
	/*Constructor - To Initialize the class variables*/
	public TransRatingsData( )
	{
		transRatingId_  = -1;
		transId_ 		= -1;
		regnKey_ 		= new CompanyRegnKey( );
		customerKey_ 	= new CompanyRegnKey( );
		starCount_ 		= 0;
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
		System.out.println( "transRatingId_	= " + transRatingId_ );
		System.out.println( "transId_		= " + transId_ );
		System.out.println( "regnKey_		= " + regnKey_ );
		System.out.println( "customerKey_	= " + customerKey_ );
		System.out.println( "starCount_		= " + starCount_ );
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
