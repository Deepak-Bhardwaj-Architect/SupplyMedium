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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;
import core.regn.RegnData;
import core.regn.UserProfileKey;
import core.trans.TransData;
import core.trans.states.RFQCreated;
import db.history.TransactionHistoryTable;
import db.history.TransactionRatingTable;
import db.history.TransactionRemainderTable;
import db.regn.CompanyRegnTable;
import db.regn.MailingAddressTable;
import db.trans.TransTable;
import db.vendorregn.RegnVendorMapTable;
import utils.ErrorMaster;

/**
 * File:  CustomerHistory.java 
 *
 * Created on Sep 26, 2013 6:07:18 PM
 */
public class CustomerHistory
{
	

    private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : CustomerHistory -- constructor
	 * 
	 * Input  : None
	 * 
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public CustomerHistory( )
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	

	/*
	 * Method: getCustomersHistory
	 * 
	 * Input: CompanyRegnKey obj, List<CustomerHistoryData> listObj
	 * 
	 * Return: int
	 * 
	 * Purpose: To fetch all the customer list 
	 */
	
    public int getCustomersHistory( CompanyRegnKey regnKey, List<CustomerHistoryData> historyDataList)
    {
    	errorMaster_.insert("Get CustomerHistory Entry" );
    	
    	int result = 0;
    	
    	// Fetching vendors regn keys for the given company
    	
    	RegnVendorMapTable regnVendorMapTable = new RegnVendorMapTable( );
    	
    	Set<CompanyRegnKey> vendorsKeys = new HashSet<CompanyRegnKey>( );
    	
    	result = regnVendorMapTable.find( regnKey, vendorsKeys );
    	
    	regnVendorMapTable = null;
    	
    	
    	if( result != 0)
    		return 16001; // Can't fetch the customers
    	
    	// Fetching all the company 
    	
    	Map<CompanyRegnKey, RegnData> companyDetailsMap = new HashMap<CompanyRegnKey, RegnData>( );
    	
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		
		result = regnTable.getAllCompanyData( companyDetailsMap );
		
		regnTable = null;
		
		if( result != 0 ) 
			return 16002;  // Cant get the all company details
		
		// Fetching all the remainders for this company
		
		List<TransReminderData> remainders = new ArrayList<TransReminderData>( );
		
		TransactionRemainderTable transRemainderTbl = new TransactionRemainderTable( );
		
		TransReminderData transReminderData = new TransReminderData( );
		
		transReminderData.regnKey_ = regnKey;
		
		result = transRemainderTbl.find( transReminderData , remainders );
		
		transReminderData = null;
		
		transRemainderTbl = null;
		
		if( result != 0 )
			return 16003;
		
		
		// Fetching all the customers ratings for this company
		
		List<TransRatingsData> ratings = new ArrayList<TransRatingsData>( );
		
		TransactionRatingTable ratingsTbl = new TransactionRatingTable( );
		
		TransRatingsData transRatingsData = new TransRatingsData( );
		
		transRatingsData.regnKey_ = regnKey;
		
		result = ratingsTbl.find( transRatingsData, ratings );
		
		transRatingsData = null;
		
		ratingsTbl = null;
		
		if( result != 0 )
			return 16004;
		
		
		
		for( CompanyRegnKey vendorKey : vendorsKeys )
        {
		CustomerHistoryData customerHistoryData = new CustomerHistoryData( );
			
			//code start bala
					
			UserProfileKey key=new UserProfileKey( );
			
			MailingAddressData mailingAddressData=new MailingAddressData( );
			
		
			
			MailingAddressTable mailingAddressTable=new MailingAddressTable( );
			
			CompanyRegnTable companyRegnTable=new CompanyRegnTable( );
			
			//mailingAddressData.address_=address;
			
			RegnData regnData1=new RegnData( );
			
			int resultemail=companyRegnTable.find( vendorKey, regnData1 );
			
			errorMaster_.insert( "resultmail:"+resultemail);
					
			key.email_=regnData1.emailId_;
			
			int resultkey=mailingAddressTable.find( key, mailingAddressData );
			
			errorMaster_.insert( "resultkey:"+resultkey);
			
			customerHistoryData.address_=mailingAddressData.address_.trim( );
			
			
			
			
			
			//code End
			
			
			
			// Setting customer company details
			
			if( companyDetailsMap.containsKey( vendorKey ) )
			{
				RegnData regnData = companyDetailsMap.get( vendorKey );
				
				customerHistoryData.customerKey_ = regnData.companyRegnKey_;
				
				customerHistoryData.companyName_ = regnData.companyName_;
				
				customerHistoryData.email_		 = regnData.emailId_;
				
			}
			
			
			// Setting customer related remiander details
			
			List<TransReminderData> vendorRemainders = new ArrayList<TransReminderData>( );
			
			for( TransReminderData remainderData: remainders )
            {
				if( remainderData.customerKey_.equals( vendorKey ))
				{
					vendorRemainders.add( remainderData );
				}
            }
			
			customerHistoryData.remainders_ = vendorRemainders;
			
			vendorRemainders = null;
			
			
			// Setting latest transactio id
			
			TransTable transTable = new TransTable( );
			
			TransData transData = new TransData( );
			
			int transResult = transTable.find( regnKey, vendorKey, transData );
			
			if( transResult == 0 )
				customerHistoryData.recentTransId_ = transData.transId_;
			
			transData = null;
			
			transTable = null;
			
			
			// Setting customer ratings details
			
			int totalStars = 0;
			
			int ratingsCount = 0;
			
		
			for( TransRatingsData ratingsData : ratings )
            {
	           if( ratingsData.customerKey_.equals( vendorKey ))
	           {
	        	   totalStars += ratingsData.starCount_;
	        	   
	        	   ratingsCount ++;   
	           }
            }
			
			if( ratingsCount != 0 )
			{
				float starCount =(float) totalStars / (float)ratingsCount;
							
				customerHistoryData.starCount_=Math.round( starCount*2 );
			}
			else
				customerHistoryData.starCount_ = 0;
			
			historyDataList.add( customerHistoryData );
			
			customerHistoryData = null;
			
        }
    	errorMaster_.insert( "Get Customer  History End ");
		return 16000;
	    
   }
    
    /*
	 * Method: addTransHistory
	 * 
	 * Input: TransData object
	 * 
	 * Return: int
	 * 
	 * Purpose: It is used to add the transaction history for completed or rejected transaction 
	 */
    
    public int addTransHistory( TransactionHistoryData transHistoryData )
    {
    	TransactionHistoryTable transHistoryTable = new TransactionHistoryTable( );
    	
    	int result = transHistoryTable.insert( transHistoryData );
    	
    	transHistoryTable = null;
    	
    	return result;
    	
    }


	
    /*
	 * Method: getTransactionHistory
	 * 
	 * Input: CompanyRegnKey obj,CompanyRegnKey obj, List<TransactionHistoryData> listObj
	 * 
	 * Return: int
	 * 
	 * Purpose: To fetch all the transactions for selected customer 
	 */
     
    public int getTransactionHistory( CompanyRegnKey regnKey, CompanyRegnKey customerKey,
    		CustomerTransactionData cusTransData )
    {
    	
    	int result = 0;
    	
    	// Filling the customer company details
    	RegnData regnData = new RegnData( );
    	
    	CompanyRegnTable regnTable = new CompanyRegnTable( );
    	
    	result = regnTable.getCompany( customerKey, regnData );
    	
    	regnTable = null;
    	
    	if( result !=0 )
    		return 16011;
    	
    	
    	cusTransData.companyname_ = regnData.companyName_;
    	
    	cusTransData.regnKey_ = customerKey;
    	
    	
    	
    	// Filling the customer company address details
    	
    	MailingAddressData mailingAddressData = new MailingAddressData( );
    	
    	MailingAddressTable mailingAddressTable = new MailingAddressTable( );
    	
    	UserProfileKey userProfileKey = new UserProfileKey( );
    	
    	userProfileKey.email_ = regnData.emailId_;
    	
    	result = mailingAddressTable.find( userProfileKey, mailingAddressData );
    	
    	if( result !=0 )
    		return 16012;
    	
    	cusTransData.address_ = mailingAddressData.address_.trim( );
    	
    	cusTransData.city_ 	  = mailingAddressData.city_;
    	
    	cusTransData.state_  = mailingAddressData.state_;
    	
    	cusTransData.addressType_ = mailingAddressData.addressType_;
    	
    	
    	// Filling the transaction details
    	TransactionHistoryTable transactionHistoryTable=new TransactionHistoryTable( );
    	
	    result=transactionHistoryTable.find(regnKey,customerKey,cusTransData.transHistoryDataList_);
	    
	    transactionHistoryTable=null;
	    
	    // Setting transaction states
	    
	    TransTable transTable = new TransTable( );
	    
	    for( TransactionHistoryData transHistoryData : cusTransData.transHistoryDataList_ )
        {
	        List<TransData> transactions = new ArrayList<TransData>( );
	        
	        int transResult = transTable.find( transHistoryData.transId_, transactions );
	        
	        if( transResult != 0)
	        	continue;
	        
	        String transState = "";
	        
	        for( TransData transData : transactions )
            {
	            if( transData.status_.equals("RFQCreated" ))
	            	transState += "RFQ,";
	            else if( transData.status_.equals( "QuoteCreated" ))
	            	transState += "Quote,";
	            else if( transData.status_ .equals("POCreated" ))
	            	transState += "PO,";
	            else if( transData.status_.equals("InvoiceCreated" ))
	            	transState += "Invoice,";
	            
	            errorMaster_.insert( "transState ="+ transState );
	           
	            
	          
	            
            }
	        // Removing , from end
	        
	        if( transState != "")
            	transState =  transState.substring( 0, transState.length( ) - 1 );
	        
	        transHistoryData.transStates_ = transState;
	        
        }
	    
	    if( result == 0 )
	    	
			return 16010;
		else
			return 16013;
	    
    }


	

}
