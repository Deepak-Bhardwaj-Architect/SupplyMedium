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


import java.sql.Timestamp;

import core.regn.CompanyRegnKey;

/**
 * File:  TransactionHistoryData.java 
 *
 * Created on September 2, 2013 9:24:29 AM
 */

public class TransactionHistoryData
{
	public CompanyRegnKey fromRegnKey_;
	
	public CompanyRegnKey toRegnKey_;
	
	public long 	transId_;
	
	public double 	amount_;
	
	public String 	status_;
	
	public String transStates_;
	
	public String currency_;
	
	public Timestamp createdTimestamp_;

	
	/*Constructor - To Initialize the class variables*/
	public TransactionHistoryData( )
	{
		fromRegnKey_ = new CompanyRegnKey( );
		
		toRegnKey_   = new CompanyRegnKey( );
		
		transId_ 	= -1;
		
		amount_ 	= 0.00;
		
		status_ 	= null;
		
		transStates_ = null;
		
		currency_=null;
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
		System.out.println( "fromRegnKey_	= " + fromRegnKey_.toString( ) );
		
		System.out.println( "toRegnKey_		= " + toRegnKey_.toString( ) );
		
		System.out.println( "transId_		= " + transId_ );
		
		System.out.println( "amount_		= " + amount_ );
		
		System.out.println( "status_		= " + status_ );
		
		System.out.println( "transStates_	= " + transStates_ );
		
		System.out.println( "createdTimestamp_		= " + createdTimestamp_ );
		
		System.out.println( "currency_="+currency_ );
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
		fromRegnKey_ = null;
		
		toRegnKey_  = null;
		
		transId_ 	= -1;
		
		amount_ 	= 0.00;
		
		status_ 	= null;
		
		createdTimestamp_ = null;
		
		transStates_ = null;
		
		currency_=null;
	}
}