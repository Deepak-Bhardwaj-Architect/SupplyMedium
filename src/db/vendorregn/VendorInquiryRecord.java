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

import java.sql.Timestamp;

/**
 * File:  VendorInquiryRecord.java 
 *
 * Created on May 25, 2013 3:17:17 PM
 */

/*
 * Class: VendorInquiryRecord
 * 
 * Purpose: This is wrapper class for vendor_inquire_details table of supply medium db
 */

public class VendorInquiryRecord
{
	public long 		vendorRegnId_;
	public String 		regnRelKey_;
	public String 		regnRelKeyMap_;
	
	public String 		inquireDetails_;
	public Timestamp 	createdTs_;
	
	/*Constructor*/
	
	public VendorInquiryRecord( )
	{
		vendorRegnId_	= 0;
		regnRelKey_ 	= "";
		regnRelKeyMap_ 	= "";
		
		inquireDetails_	= null;
		createdTs_		= null;
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
		System.out.println( "vendorRegnId_	= " + vendorRegnId_ );
		System.out.println( "regnRelKey_	= " + regnRelKey_ );
		System.out.println( "regnRelKeyMap_ = " + regnRelKeyMap_ );
		
		System.out.println( "inquireDetails_= " + inquireDetails_ );
		System.out.println( "createdTs_		= " + createdTs_ );
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
		vendorRegnId_	= 0;
		regnRelKey_ 	= null;
		regnRelKeyMap_ 	= null;
		
		inquireDetails_	= null;
		createdTs_		= null;
	}
	
}