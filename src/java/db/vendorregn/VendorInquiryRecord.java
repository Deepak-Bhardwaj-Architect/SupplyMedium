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
import utils.ErrorMaster;

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
        private static ErrorMaster errorMaster_ = null;


	
	/*Constructor*/
	
	public VendorInquiryRecord( )
	{
		vendorRegnId_	= 0;
		regnRelKey_ 	= "";
		regnRelKeyMap_ 	= "";
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( "vendorRegnId_	= " + vendorRegnId_ );
		errorMaster_.insert( "regnRelKey_	= " + regnRelKey_ );
		errorMaster_.insert( "regnRelKeyMap_ = " + regnRelKeyMap_ );
		
		errorMaster_.insert( "inquireDetails_= " + inquireDetails_ );
		errorMaster_.insert( "createdTs_		= " + createdTs_ );
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