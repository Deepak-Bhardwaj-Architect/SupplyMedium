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
 * File:  InvoiceData.java 
 *
 * Created on 22-Jun-2013 9:32:03 AM
 */
public class InvoiceData
{
	// This is the transaction id
	public long              transId_;

	// This is the unique id of this invoice record
	public long              invoiceId_;
	
	// Invoice Number
	public String invoiceNo_;

	// This is the companyRegnKey who sent the invoice request
	public CompanyRegnKey    from_;

	// This is the companyRegnKey who receive the invoice request
	public CompanyRegnKey    to_;

	// This is the userProfileKey who sent the invoice request
	public UserProfileKey    userFrom_;

	// This is the userProfileKey who receive the invoice request
	public UserProfileKey    userTo_;

	// Current status of the invoice request
	public String            status_;

	/*
	 * This is the current action of the transaction.It should be Accept or
	 * Reject or Inquire
	 */
	public String            action_;

	/*
	 * This flag is set to true if Quote send to outside buyer who is not
	 * registered with SupplyMedium. Flag set to false Invoice send to registered
	 * supplier
	 */
	public int           isOutsideBuyer_;

	/* Email id for outside buyer */
	public String            outsideBuyerEmail_;
        
        public String outsideSuppliercountry;
        
        public String outsideSupplierstate;
        
        public String outsideSuppliercity;
        
        public String outsideSupplieraddress;
        
        public String outsideSupplierzipcode;
	
	/* It shows we need to send the invitation to supplier if it set to 1 otherwise no need */
	public int outsideSupplierEmailFlag_;

	// This string indicate the recurring type
	public String            recurring_;

	// invoice Date
	public Date              invoiceDate_;
	
	// Total amount of the invoice with out tax amount
	public double totalListPrice_;  
	
	// Percentage of tax for invoice
	public double taxPercentage_;
	
	// Freight handling charges
	public double freightHandling_;
	
	// Total amount of the invoice with tax amount
	public double totalPrice_;
	
	// Invoice discount amount
	public double discount_;
	
	// Invoice billing period
	public String invoiceBillingPeriod_;
		
	// Invoice Due Date
	public Date dueDate_;
	
	// Freight carrier
	public String freightCarrier_;
	
	// Bill of landing
	public long billOfLanding_;
	
	//Freight weight
	public double freightWeight_;
	
	//Freight weight unit
	public String freightWeightUnit_;
	
	//Date shipped
	public Date dateShipped_;

	// is Non po invoice
	public int isNonPOInvoice_;
	
	// PO Number
	public String poNo_;
		
	// is diff address
	public int isDiffAdd_;
	
	// if address different, email
	public String diffEmail_;
 	
         public String selected_address;
        
	// This is the invoice form added in SM date
	public Timestamp         createdTimestamp_;

	// list of invoice items
	public List<InvoiceItemData> invoiceItems_;

	// list of transaction occured in invoice Request
	public List<TransData>   transList_;

	// list of transaction occured in invoice Request
	public List<TransInquireData> transInquireList_; 

    // From company profile data
    public CompanyProfileData fromCompanyProfileData_;
    
    // To company profile data
    public CompanyProfileData toCompanyProfileData_;
    
    private static ErrorMaster errorMaster_ = null;


	
	/*
	 * Method : InvoiceData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public InvoiceData( )
	{
		from_ = new CompanyRegnKey( );
		
		to_  = new CompanyRegnKey( );
		
		userFrom_ = new UserProfileKey( );
		
		userTo_ = new UserProfileKey( );
		
		invoiceItems_ = new ArrayList<InvoiceItemData>( );
		
		transList_ = new ArrayList<TransData>( );
		
		transInquireList_ = new ArrayList<TransInquireData>( );
		
		isOutsideBuyer_ = -1;
		
		transId_ = -1;
		
		fromCompanyProfileData_ = new CompanyProfileData( );
		
		toCompanyProfileData_	= new CompanyProfileData( );
		
		freightHandling_ = 0.0f;
		
		discount_ = 0.00f;
		
		invoiceBillingPeriod_ = null;
			
		dueDate_ = null;
		
		invoiceNo_ = null;
		
		freightCarrier_ = null;
		
		billOfLanding_ = -1;
		
		freightWeight_ = 0.00f;;
		
		freightWeightUnit_ = null;
		
		dateShipped_ = null;
		
		isNonPOInvoice_ = 0;
		
		poNo_ = null;
			
		isDiffAdd_ = 0;
		
		diffEmail_ = null;
                
                selected_address=null;
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
		errorMaster_.insert( "invoiceId_				= " + invoiceId_ );
		
		errorMaster_.insert( "from					= " + from_.toString( ) );
		errorMaster_.insert( "to						= " + to_.toString( ) );
		
		errorMaster_.insert( "userFrom				= " + userFrom_.toString( ) );
		errorMaster_.insert( "userTo					= " + userTo_.toString( ) );
		
		errorMaster_.insert( "status_				= " + status_ );
		
		errorMaster_.insert( "isOutsideBuyer_		= " + isOutsideBuyer_ );
		errorMaster_.insert( "outsideBuyerEmail_		= " + outsideBuyerEmail_ );
		
		errorMaster_.insert( "recurring_				= " + recurring_ );
		errorMaster_.insert( "InvoiceDate_			= " + invoiceDate_ );
		errorMaster_.insert( "createdTimestamp_		= " + createdTimestamp_ );
		
		errorMaster_.insert( "totalListPrice_		= " + totalListPrice_ );
		errorMaster_.insert( "taxPercentage_			= " + taxPercentage_ );
		
		errorMaster_.insert( "freightHandling_		= " + freightHandling_ );
		errorMaster_.insert( "totalPrice_			= " + totalPrice_ );
		
		errorMaster_.insert( "discount_				= " + discount_ );
		errorMaster_.insert( "invoiceBillingPeriod_	= " + invoiceBillingPeriod_ );
		errorMaster_.insert( "dueDate_				= " + dueDate_ );
		
		errorMaster_.insert( "invoiceNo_				= " + invoiceNo_ );
		errorMaster_.insert( "freightCarrier_		= " + freightCarrier_ );
		
		errorMaster_.insert( "billOfLanding_			= " + billOfLanding_ );
		errorMaster_.insert( "freightWeight_			= " + freightWeight_ );

		errorMaster_.insert( "freightWeightUnit_		= " + freightWeightUnit_ );
		errorMaster_.insert( "dateShipped_			= " + dateShipped_ );
		
		errorMaster_.insert( "isNonPOInvoice_		= " + isNonPOInvoice_ );
		errorMaster_.insert( "poNo_					= " + poNo_ );
		errorMaster_.insert( "isDiffAdd_				= " + isDiffAdd_ );
		
		errorMaster_.insert( "diffEmail				= " + diffEmail_ );
		
		for( InvoiceItemData invoiceItemData : invoiceItems_ )
        {
			invoiceItemData.show( );
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
		invoiceId_	 			= -1;
		from_ 					= null;

		to_ 					= null;
		userFrom_ 				= null;
		userTo_					= null;
		
		status_ 				= null;
		isOutsideBuyer_ 		= -1;
		outsideBuyerEmail_	 	= null;
                
                outsideSuppliercountry 	= null;
                outsideSupplierstate 	= null;
                outsideSuppliercity 	= null;
                outsideSupplieraddress 	= null;
                outsideSupplierzipcode 	= null;
		
		recurring_ 				= null;
		invoiceDate_ 			= null;
		createdTimestamp_ 		= null;
		
		invoiceItems_			= null;
		transList_				= null;
		
		totalListPrice_ 		= 0.0f;
		taxPercentage_ 			= 0.0f;
		totalPrice_ 			= 0.0f;
		
		transInquireList_ 		= null;
		fromCompanyProfileData_	= null;
		toCompanyProfileData_	= null;
		
		discount_ 				= 0.00f;
		invoiceBillingPeriod_ 	= null;
		dueDate_ 				= null;
			
		freightHandling_		= 0.0f;
		freightCarrier_ 		= null;
		billOfLanding_ 			= -1;
		
		freightWeight_ 			= 0.00f;
		freightWeightUnit_ 		= null;
		dateShipped_			= null;
	
		isNonPOInvoice_			= 0;
		poNo_					= null;
		isDiffAdd_				= 0;	
		
		invoiceNo_ 				= null;
		diffEmail_ 				= null;
                selected_address=null;
	}
}
