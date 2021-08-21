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
 * File:  VendorRegnRecord.java 
 *
 * Created on May 21, 2013 3:55:41 PM
 */

/*
 * Class: VendorRegnRecord
 * 
 * Purpose: This class is maps the vendor_registration table
 */

public class VendorRegnRecord
{
	public long vendorRegnId_;
	public String regnKey_;
	public String regnMapKey_;
	
	public String userKey_;
	
	public String requestSenderType_;
	
	public String companyLevel_;
	public String businessContact_;

	public String businessTaxId_;
	public String NAICSCode_;
	public String w9TaxFormPath_;
	
	public int w9TaxFormFlag_;
	
	public String businessSize_;
	public String businessClassification_;
	public String additionalDetails_;

	public String regnStatus_;

	/*
	 * Constructor
	 */
	
	public VendorRegnRecord( )
	{
		vendorRegnId_ 		= 0;
		regnKey_			= "";
		regnMapKey_			= "";
		
		userKey_			= "";
		requestSenderType_  = "";
		
		companyLevel_ 		= "";
		businessContact_ 	= "";
		businessTaxId_		= "";
		
		NAICSCode_			= "";
		w9TaxFormPath_		= "";
		businessSize_		= "";
		
		businessClassification_	= "";
		additionalDetails_		= "";
		regnStatus_				= "";	
		
		w9TaxFormFlag_			= 0;
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
		System.out.println( "vendorRegnId_		= " + vendorRegnId_ );
		System.out.println( "regnKey_			= " + regnKey_.toString( ) );
		System.out.println( "regnMapKey_		= " + regnMapKey_.toString( ) );
		
		System.out.println( "userKey_			= " + userKey_.toString( ) );
		System.out.println( "requestSenderType_	= " + requestSenderType_ );
		
		System.out.println( "companyLevel_		= " + companyLevel_ );
		System.out.println( "businessContact_	= " + businessContact_ );
		System.out.println( "businessTaxId_		= " + businessTaxId_ );
		
		System.out.println( "NAICSCode_			= " + NAICSCode_ );
		System.out.println( "w9TaxFormPath_		= " + w9TaxFormPath_ );
		System.out.println( "businessSize_		= " + businessSize_ );
		
		System.out.println( "businessClassification_= " + businessClassification_);
		System.out.println( "additionalDetails_ 	= " + additionalDetails_);
		System.out.println( "regnStatus_	  		= " + regnStatus_ );		
		
		System.out.println( "w9TaxFormFlag_	  		= " + w9TaxFormFlag_ );
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
		vendorRegnId_  	= 0;
		regnKey_		= null;
		regnMapKey_		= null;
		
		userKey_		= null;
		requestSenderType_ = null;
		
		companyLevel_	= null;
		businessContact_= null;
		
		businessTaxId_	= null;
		NAICSCode_		= null;
		
		w9TaxFormPath_	= null;
		businessSize_	= null;
		businessClassification_	= null;
		
		regnStatus_ 		= null;
		additionalDetails_ 	= null;
		
		w9TaxFormFlag_		= 0;
	}
}
