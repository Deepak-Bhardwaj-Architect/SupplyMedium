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
 * File:  QuoteData.java 
 *
 * Created on 21-Jun-2013 4:30:36 PM
 */
public class QuoteData
{
	// This is the transaction id
	public long              transId_;

	// This is the unique id of this Quote
	public long              quoteId_;
	
	// This is the quote reference number entered by user
	public String quoteRef_;

	// This is the companyRegnKey who sent the Quote request
	public CompanyRegnKey    from_;

	// This is the companyRegnKey who receive the Quote request
	public CompanyRegnKey    to_;

	// This is the userProfileKey who sent the Quote request
	public UserProfileKey    userFrom_;

	// This is the userProfileKey who receive the Quote request
	public UserProfileKey    userTo_;

	// Current status of the Quote request
	public String            status_;

	/*
	 * This is the current action of the transaction.It should be Accept or
	 * Reject or Inquire
	 */
	public String            action_;

	/*
	 * This flag is set to true if Quote send to outside supplier who is not
	 * registered with SupplyMedium. Flag set to false Quote send to registered
	 * supplier
	 */
	public int           isOutsideSupplier_;

	/* Email id for outside supplier */
        public String outsideSupplierName_;
        
	public String            outsideSupplierEmail_;
        
        public String            outsideSuppliercountry;
        
        public String            outsideSupplierstate;
        
        public String            outsideSuppliercity;
        
        public String            outsideSupplieraddress;
        
        public String            outsideSupplierzipcode;
	
	/* It shows we need to send the invitation to supplier if it set to 1 otherwise no need */
	public int outsideSupplierEmailFlag_;

	// This string indicate the recurring type
	public String            recurring_;

	// Quote Date
	public Date              quoteDate_;
	
	// Total amount of the Quotation with out tax amount
	public double totalListPrice_;  
	
	// Percentage of tax for quotaion
	public double taxPercentage_;
	
	// Total amount of the Quotation with tax amount
	public double totalPrice_;
	
	// This is the Quote form added in SM date
	public Timestamp         createdTimestamp_;

	// list of Quote items
	public List<QuoteItemData> quoteItems_;

	// list of transaction occured in Quote Request
	public List<TransData>   transList_;

	// list of transaction occured in Quote Request
	public List<TransInquireData> transInquireList_; 

    // From company profile data
    public CompanyProfileData fromCompanyProfileData_;
    
    // To company profile data
    public CompanyProfileData toCompanyProfileData_;
    
    // It is indicate that purchase order create or not for this Quote. If this value 1
    // Purchase Order created otherwise not at all
    public int isPOCreated_;
    
    private static ErrorMaster errorMaster_ = null;


	
	/*
	 * Method : QuoteData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public QuoteData()
	{
		from_ = new CompanyRegnKey( );
		
		to_  = new CompanyRegnKey( );
		
		userFrom_ = new UserProfileKey( );
		
		userTo_ = new UserProfileKey( );
		
		quoteItems_ = new ArrayList<QuoteItemData>( );
		
		transList_ = new ArrayList<TransData>( );
		
		transInquireList_ = new ArrayList<TransInquireData>( );
		
		isOutsideSupplier_ = -1;
		
		fromCompanyProfileData_ = new CompanyProfileData( );
		
		toCompanyProfileData_	= new CompanyProfileData( );
		
		quoteId_ = -1;
		
		transId_ = -1;
		
		isPOCreated_ = 0;
                
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
		errorMaster_.insert( "QuoteId_				= " + quoteId_ );
		errorMaster_.insert( "QuoteRef               = " + quoteRef_);
		
		errorMaster_.insert( "from					= " + from_.toString( ) );
		errorMaster_.insert( "to						= " + to_.toString( ) );
		
		errorMaster_.insert( "userFrom				= " + userFrom_.toString( ) );
		errorMaster_.insert( "userTo					= " + userTo_.toString( ) );
		
		errorMaster_.insert( "status_				= " + status_ );
		
		errorMaster_.insert( "isOutsideSupplier_		= " + isOutsideSupplier_ );
		errorMaster_.insert( "outsideSupplierEmail_	= " + outsideSupplierEmail_ );
		
		errorMaster_.insert( "recurring_				= " + recurring_ );
		errorMaster_.insert( "QuoteDate_				= " + quoteDate_ );
		errorMaster_.insert( "createdTimestamp_		= " + createdTimestamp_ );
		
		errorMaster_.insert( "totalListPrice_		= " + totalListPrice_ );
		errorMaster_.insert( "taxPercentage_			= " + taxPercentage_ );
		errorMaster_.insert( "totalPrice_			= " + totalPrice_ );
		
		errorMaster_.insert( "isPOCreated_			= " + isPOCreated_ );
		
		
		for( QuoteItemData quoteItemData : quoteItems_ )
        {
			quoteItemData.show( );
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
		quoteId_ 				= -1;
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
		
		recurring_ 				= null;
		quoteDate_ 				= null;
		createdTimestamp_ 		= null;
		
		quoteItems_				= null;
		transList_				= null;
		
		totalListPrice_ 		= 0.0f;
		taxPercentage_ 			= 0.0f;
		totalPrice_ 			= 0.0f;
		
		transInquireList_ 		= null;
		fromCompanyProfileData_	= null;
		toCompanyProfileData_	= null;
		
		isPOCreated_			= 0;
	}
}
