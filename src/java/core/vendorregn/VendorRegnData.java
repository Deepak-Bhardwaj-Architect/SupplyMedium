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

package core.vendorregn;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.regn.UserProfileData;
import utils.ErrorMaster;

/**
 * File:  VendorRegnData.java 
 *
 * Created on May 21, 2013 3:55:50 PM
 */

/*
 * Class: VendorRegnData
 * 
 * Purpose: This class contains the domain variables
 * for vendor registration
 */

public class VendorRegnData
{
	public long vendorRegnId_;
	
	public RegnData regnData_;
	public UserProfileData profileData_;
	
	public RegnData vendorRegnData_;
	public UserProfileData vendorProfileData_;
	
	//These two are used for inserting/updating vendor regn data
	public CompanyRegnKey regnKey_;
	public CompanyRegnKey vendorRegnKey_;
	
	public String requestSenderType_;
	
	public VendorInquireData	vendorInquireData;
	public List<VendorInquireData> vendorInquireDataList_;
	/*
	 * If this flag is set, then w9 form tax is mandatory
	 * Here, int is used as flag, such that, if 0, then
	 * the w9 tax form is not mandatory; else, its 
	 * mandatory
	 */
	
	public int w9TaxFormFlag_; 
	
	public String companyLevel_;
	public String businessContact_;
	
	public String vendorContact_;

	public String businessTaxId_;
	public String NAICSCode_;
	public String w9TaxFormPath_;
	
	public String businessSize_;
	public String businessClassification_;
	public String additionalDetails_;

	public String regnStatus_;
	
	public Object w9TaxForm_;
	
	public Timestamp createdTimestamp_;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Constructor
	 */
	
	public VendorRegnData( )
	{
		vendorRegnId_	= 0;
		regnData_ 		= new RegnData( );
		profileData_ 	= new UserProfileData( );
		
		vendorRegnData_ = new RegnData( );
		vendorProfileData_ = new UserProfileData( );
		
		vendorRegnKey_ 	= new CompanyRegnKey( );
		regnKey_		= new CompanyRegnKey( );
		
		vendorInquireDataList_	= new ArrayList<VendorInquireData>( );
		vendorInquireData		= new VendorInquireData( );
		
		w9TaxFormFlag_		= 0;
		
		requestSenderType_ 	= "";
		vendorContact_		= "";
		
		companyLevel_ 		= "";
		businessContact_ 	= "";
		businessTaxId_		= "";
		
		NAICSCode_			= "";
		w9TaxFormPath_		= "";
		businessSize_		= "";
		
		businessClassification_	= "";
		additionalDetails_		= "";
		regnStatus_				= "";	
		
		createdTimestamp_ 		= null;
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		
		errorMaster_.insert( "regnData_			= " + regnData_ );
		errorMaster_.insert( "vendorRegnKey_		= " + vendorRegnKey_.toString( ) );
		errorMaster_.insert( "regnKey_			= " + regnKey_.toString( ) );
		
		errorMaster_.insert( "requestSenderType_	= " + requestSenderType_ );
		errorMaster_.insert( "vendorContact_	  	= " + vendorContact_ );
		
		errorMaster_.insert( "w9TaxFormFlag_		= " + w9TaxFormFlag_ );
		
		errorMaster_.insert( "companyLevel_		= " + companyLevel_ );
		errorMaster_.insert( "businessContact_	= " + businessContact_ );
		
		errorMaster_.insert( "businessTaxId_		= " + businessTaxId_ );
		
		errorMaster_.insert( "NAICSCode_			= " + NAICSCode_ );
		errorMaster_.insert( "w9TaxFormPath_		= " + w9TaxFormPath_ );
		errorMaster_.insert( "businessSize_		= " + businessSize_ );
		
		errorMaster_.insert( "businessClassification_	= " + businessClassification_);
		errorMaster_.insert( "additionalDetails_         = " + additionalDetails_);
		errorMaster_.insert( "regnStatus_	  	= " + regnStatus_ );
		
		
		errorMaster_.insert( "createdTimeStamp   = " + createdTimestamp_);
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
		vendorRegnId_	= 0;
		
		regnData_  		= null;
		vendorRegnKey_	= null;
		regnKey_		= null;
		
		w9TaxFormFlag_	= 0;
		
		requestSenderType_ = null;
		vendorContact_	   = null;
		
		vendorInquireData 		= null;
		vendorInquireDataList_	= null;
		
		companyLevel_	= null;
		businessContact_= null;
		
		businessTaxId_	= null;
		NAICSCode_		= null;
		
		w9TaxFormPath_	= null;
		businessSize_	= null;
		businessClassification_	= null;
		
		regnStatus_ 		= null;
		additionalDetails_ 	= null;
		
		createdTimestamp_   = null;
	}
}
