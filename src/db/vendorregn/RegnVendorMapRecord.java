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

package db.vendorregn;

/**
 * File:  RegnVendorMapRecord.java 
 *
 * Created on May 24, 2013 6:28:24 PM
 */

/*
 * Class: RegnVendorMapRecord
 * 
 * Purpose: This is wrapper class for regn_vendor_mapper table of supply medium db
 */

public class RegnVendorMapRecord
{
	public String regnRelKey_;
	public String regnRelKeyMap_;
	
	/*Constructor*/
	
	public RegnVendorMapRecord( )
	{
		regnRelKey_ 	= "";
		regnRelKeyMap_ 	= "";
	}
	
	/*
	 * Method: show
	 * 
	 * Input: none
	 * 
	 * return: void
	 * 
	 * Purpose: This method prints the class variables into console
	 */
	
	public void show( )
	{
		System.out.println( "regnRelKey_	= " + regnRelKey_ );
		System.out.println( "regnRelKeyMap_ = " + regnRelKeyMap_ );
	}
	
	/*
	 * Method: clear
	 * 
	 * Input: none
	 * 
	 * return: void
	 * 
	 * Purpose; This method clears all the class variables
	 */
	
	public void clear( )
	{
		regnRelKey_ 	= null;
		regnRelKeyMap_ 	= null;
	}
}
