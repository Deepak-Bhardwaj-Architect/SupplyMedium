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

import utils.ErrorMaster;

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
        private static ErrorMaster errorMaster_ = null;



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
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( "vendorRegnId_		= " + vendorRegnId_ );
		errorMaster_.insert( "regnKey_			= " + regnKey_.toString( ) );
		errorMaster_.insert( "regnMapKey_		= " + regnMapKey_.toString( ) );
		
		errorMaster_.insert( "userKey_			= " + userKey_.toString( ) );
		errorMaster_.insert( "requestSenderType_	= " + requestSenderType_ );
		
		errorMaster_.insert( "companyLevel_		= " + companyLevel_ );
		errorMaster_.insert( "businessContact_	= " + businessContact_ );
		errorMaster_.insert( "businessTaxId_		= " + businessTaxId_ );
		
		errorMaster_.insert( "NAICSCode_			= " + NAICSCode_ );
		errorMaster_.insert( "w9TaxFormPath_		= " + w9TaxFormPath_ );
		errorMaster_.insert( "businessSize_		= " + businessSize_ );
		
		errorMaster_.insert( "businessClassification_= " + businessClassification_);
		errorMaster_.insert( "additionalDetails_ 	= " + additionalDetails_);
		errorMaster_.insert( "regnStatus_	  		= " + regnStatus_ );		
		
		errorMaster_.insert( "w9TaxFormFlag_	  		= " + w9TaxFormFlag_ );
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
