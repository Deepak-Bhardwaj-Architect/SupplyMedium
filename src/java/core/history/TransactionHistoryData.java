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
import utils.ErrorMaster;

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
        
        private static ErrorMaster errorMaster_ = null;



	
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
		errorMaster_.insert( "fromRegnKey_	= " + fromRegnKey_.toString( ) );
		
		errorMaster_.insert( "toRegnKey_		= " + toRegnKey_.toString( ) );
		
		errorMaster_.insert( "transId_		= " + transId_ );
		
		errorMaster_.insert( "amount_		= " + amount_ );
		
		errorMaster_.insert( "status_		= " + status_ );
		
		errorMaster_.insert( "transStates_	= " + transStates_ );
		
		errorMaster_.insert( "createdTimestamp_		= " + createdTimestamp_ );
		
		errorMaster_.insert( "currency_="+currency_ );
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