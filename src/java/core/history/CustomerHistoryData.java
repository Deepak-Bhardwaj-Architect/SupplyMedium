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

import core.regn.CompanyRegnKey;
import utils.ErrorMaster;

/**
 * File:  CustomerHistoryData.java 
 *
 * Created on September 2, 2013 9:24:55 AM
 */


public class CustomerHistoryData
{
	public long 			customerId_;
	public CompanyRegnKey 	customerKey_;
	public String 			companyName_;
	public String 			address_;
	public String 			email_;
	
	public List<TransReminderData> remainders_;
	
	public long 			recentTransId_;
	public int 				starCount_;
        private static ErrorMaster errorMaster_ = null;


	

	
	/*Constructor - To Initialize the class variables*/
	public CustomerHistoryData( )
	{
		customerId_ 		= -1;
		companyName_ 		= null;
		address_ 			= null;
		email_ 				= null;
		customerKey_ 		= new CompanyRegnKey( );
		
		remainders_			= new ArrayList<TransReminderData>( );
		
		recentTransId_		= -1;
		starCount_			= 0;
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
		errorMaster_.insert( "customerId_		= " + customerId_ );
		errorMaster_.insert( "customerKey_		= " + customerKey_.toString( ) );
		errorMaster_.insert( "companyName_		= " + companyName_ );
		errorMaster_.insert( "address_			= " + address_ );
		errorMaster_.insert( "email_				= " + email_ );
		errorMaster_.insert( "remainders_		= " + remainders_.size( ) );
		errorMaster_.insert( "recentTransDocLink_= " + recentTransId_ );
		errorMaster_.insert( "starCount_			= " + starCount_ );
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
		customerId_ 		= -1;
		customerKey_		= null;
		companyName_ 		= null;
		address_ 			= null;
		email_ 				= null;
		remainders_			= null;
		recentTransId_		= -1;
		starCount_ 			= 0;
	}
}
