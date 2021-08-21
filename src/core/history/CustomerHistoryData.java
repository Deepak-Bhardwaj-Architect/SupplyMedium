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
		System.out.println( "customerId_		= " + customerId_ );
		System.out.println( "customerKey_		= " + customerKey_.toString( ) );
		System.out.println( "companyName_		= " + companyName_ );
		System.out.println( "address_			= " + address_ );
		System.out.println( "email_				= " + email_ );
		System.out.println( "remainders_		= " + remainders_.size( ) );
		System.out.println( "recentTransDocLink_= " + recentTransId_ );
		System.out.println( "starCount_			= " + starCount_ );
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
