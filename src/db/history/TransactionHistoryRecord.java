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
package db.history;




/**
 * File:  TransactionHistoryRecord.java 
 *
 * Created on Sep 27, 2013 9:58:46 AM
 */
public class TransactionHistoryRecord
{
	public String fromRegnKey_;
	
	public String toRegnKey_;
	
	public long transId_;
	
	public double amount_;
	
	public String status_;
	
	public String currency_;
	
	/*Constructor - To Initialize the class variables*/
	public TransactionHistoryRecord( )
	{
		fromRegnKey_ = null;
		
		toRegnKey_   = null;
		
		transId_ 	= -1;
		
		amount_ 	= 0.00;
		
		status_ 	= null;
		
		currency_   = null;
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
		
		System.out.println( "currency_		= " + currency_ );
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
		fromRegnKey_ 	= null;
		
		toRegnKey_  	= null;
		
		transId_ 		= -1;
		
		amount_ 		= 0.00;
		
		status_ 		= null;
		
		currency_ 		= null;
	}


}
