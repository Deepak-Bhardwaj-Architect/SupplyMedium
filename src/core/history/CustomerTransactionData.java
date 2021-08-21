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
		System.out.println( "customerId_			= " + customerId_ );
		System.out.println( "transId_				= " + transId_ );
		System.out.println( "regnKey_				= " + regnKey_ );
		System.out.println( "companyname_			= " + companyname_ );
		System.out.println( "address_				= " + address_ );
		System.out.println( "city_					= " + city_ );
		System.out.println( "state_					= " + state_ );
		System.out.println( "addressType_			= " + addressType_ );
		System.out.println( "transHistoryDataList_	= " + transHistoryDataList_.size( ) );
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
