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
import java.util.List;
import java.util.Set;

import core.regn.CompanyRegnKey;
import utils.ErrorMaster;

/**
 * File:  CustomerTransactionData.java 
 *
 * Created on September 2, 2013 9:23:49 AM
 */

public class CustomerTransactionData
{	
	public long customerId_; 	//This is the vendor registration Id (the auto incremented id.)
	
	public long transId_;	 	//This is the transactionId from transaction table
	
	public CompanyRegnKey regnKey_;
	
	public String companyname_;
	
	public String address_;
	
	public String city_;
	
	public String state_;
	
	public String addressType_;
	
	public List<TransactionHistoryData> transHistoryDataList_;
        private static ErrorMaster errorMaster_ = null;


	

	/*Constructor - To Initialize the class variables*/
	
	public CustomerTransactionData( )
	{
		customerId_ 		= -1;
		transId_			= -1;
		regnKey_ 			= null;
		companyname_		= null;
		address_ 			= null;
		city_				= null;
		state_				= null;
		addressType_		= null;
		transHistoryDataList_	= new ArrayList<TransactionHistoryData>( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/* Method: show
	 * 
	 * Input: none
	 *
	 * Return: void
	 *
	 * Purpose: To print the class variables in the console
	 */
	
	public void show( )
	{
		errorMaster_.insert( "customerId_			= " + customerId_ );
		errorMaster_.insert( "transId_				= " + transId_ );
		errorMaster_.insert( "regnKey_				= " + regnKey_ );
		errorMaster_.insert( "companyname_			= " + companyname_ );
		errorMaster_.insert( "address_				= " + address_ );
		errorMaster_.insert( "city_					= " + city_ );
		errorMaster_.insert( "state_					= " + state_ );
		errorMaster_.insert( "addressType_			= " + addressType_ );
		errorMaster_.insert( "transHistoryDataList_	= " + transHistoryDataList_.size( ) );
	}
	
	/* Method: clear
	 * 
	 * Input: none
	 *
	 * Return: void
	 *
	 * Purpose: To release the class variables from memory
	 */
	
	public void clear( )
	{
		customerId_ 			= -1;
		transId_ 				= -1;
		regnKey_ 				= null;
		companyname_ 			= null;
		address_				= null;
		city_ 					= null;
		state_					= null;
		addressType_ 			= null;
		transHistoryDataList_ 	= null;
	}
}
