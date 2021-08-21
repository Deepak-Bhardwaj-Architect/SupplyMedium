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
package core.trans;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import core.companyprofile.CompanyProfileData;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File:  RFQData.java 
 *
 * Created on 20-Jun-2013 3:06:36 PM
 */

public class RFQData
{ 
	
	// This is the transaction id 
	public long transId_;
	
	// This is the unique id of this RFQ
	public long RFQId_;
	
	// This is the quote reference number entered by user
	public String quoteRef_;
	
	// This is the companyRegnKey who sent the RFQ request
	public CompanyRegnKey from_;
	
	// This is the companyRegnKey who receive the RFQ request
	public CompanyRegnKey to_;
	
	
	// This is the userProfileKey who sent the RFQ request 
	public UserProfileKey userFrom_;
		
	// This is the userProfileKey who receive the RFQ request
	public UserProfileKey userTo_;
		
	
	// Current status of the RFQ request
	public String status_;
	
	/* This is the current action of the transaction.It should be
	 * Accept or Reject or Inquire*/
	public String action_;
	
	
	/* This flag is set to true if RFQ send to outside supplier who is not 
	 * registered with SupplyMedium.
	 * Flag set to false RFQ send to registered supplier */
	public int isOutsideSupplier_;
	
	/* Email id for outside supplier */
	
        public String outsideSupplierName_;
        
        public String outsideSupplierEmail_;
        
        public String outsideSuppliercountry;
        
        public String outsideSupplierstate;
        
        public String outsideSuppliercity;
        
        public String outsideSupplieraddress;
        
        public String outsideSupplierzipcode;
	
	/* It shows we need to send the invitation to supplier if it set to 1 otherwise no need */
	public int outsideSupplierEmailFlag_;
	
	// This string indicate the recurring type
	public String recurring_;
	
	// RFQ Date
	public Date RFQDate_;
	
	// This is the RFQ form added in SM date
	public Timestamp createdTimestamp_;
	
	// list of RFQ items
    public List<RFQItemData> RFQItems_;
    
    // list of transaction occured in RFQ Request
    public List<TransData> transList_;
	
	// list of inquire data for RFQ 
    public List<TransInquireData> transInquireList_; 
    
    // From company profile data
    public CompanyProfileData fromCompanyProfileData_;
    
    // To company profile data
    public CompanyProfileData toCompanyProfileData_;
    
    // It is indicate that quote create or not for this RFQ. If this value 1
    // Quote created otherwise not at all
    public int isQuoteCreated_;
    
    private static ErrorMaster errorMaster_ = null;


    
    
	/*
	 * Method : RFQData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
    
	public RFQData()
	{
		from_ = new CompanyRegnKey( );
		
		to_  = new CompanyRegnKey( );
		
		userFrom_ = new UserProfileKey( );
		
		userTo_ = new UserProfileKey( );
		
		RFQItems_ = new ArrayList<RFQItemData>( );
		
		transList_ = new ArrayList<TransData>( );
		
		transInquireList_ = new ArrayList<TransInquireData>( );
		
		fromCompanyProfileData_	= new CompanyProfileData( );
		
		toCompanyProfileData_	= new CompanyProfileData( );
		
		transId_ 				= -1;
		
		RFQId_ 					= -1;
		
		isOutsideSupplier_ 		= -1;
		
		isQuoteCreated_         = 0;
		
		outsideSupplierEmailFlag_ = 0;
                
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
		errorMaster_.insert( "transId_				= " + transId_ );
		errorMaster_.insert( "RFQId_					= " + RFQId_ );
		errorMaster_.insert( "QuoteRef               = " + quoteRef_);
		
		errorMaster_.insert( "from					= " + from_.toString( ) );
		errorMaster_.insert( "to						= " + to_.toString( ) );
		
		errorMaster_.insert( "userFrom				= " + userFrom_.toString( ) );
		errorMaster_.insert( "userTo					= " + userTo_.toString( ) );
		
		errorMaster_.insert( "status_				= " + status_ );
		
		errorMaster_.insert( "isOutsideSupplier_		= " + isOutsideSupplier_ );
		errorMaster_.insert( "outsideSupplierEmail_	= " + outsideSupplierEmail_ );
		errorMaster_.insert( "outsideSupplierEmailFlag_	= " + outsideSupplierEmailFlag_ );
		
		errorMaster_.insert( "recurring_				= " + recurring_ );
		errorMaster_.insert( "RFQDate_				= " + RFQDate_ );
		errorMaster_.insert( "createdTimestamp_		= " + createdTimestamp_ );
		
		errorMaster_.insert( "isQuoteCreated_        = " + isQuoteCreated_);
		
		for( RFQItemData rfqItem : RFQItems_ )
        {
	        rfqItem.show( );
        }
		
		for( TransData transData : transList_ )
        {
	        transData.show( );
        }
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
		transId_ 				= -1;
		RFQId_ 					= -1;
		quoteRef_ 				= null;
		
		from_ 					= null;
		to_ 					= null;
		
		userFrom_ 				= null;
		userTo_					= null;
		
		status_ 				= null;
		
		isOutsideSupplier_ 		= -1;
                outsideSupplierName_ 	= null;
		outsideSupplierEmail_ 	= null;
                outsideSuppliercountry 	= null;
                outsideSupplierstate 	= null;
                outsideSuppliercity 	= null;
                outsideSupplieraddress 	= null;
                outsideSupplierzipcode 	= null;
		outsideSupplierEmailFlag_ = 0;
		
		recurring_ 				= null;
		RFQDate_ 				= null;
		createdTimestamp_ 		= null;
		
		RFQItems_				= null;
		transList_				= null;
		
		fromCompanyProfileData_	= null;
		toCompanyProfileData_	= null;
		isQuoteCreated_			= 0;
		
	}

}
