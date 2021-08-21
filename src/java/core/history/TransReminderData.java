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

import core.regn.CompanyRegnKey;

import java.sql.Date;
import java.sql.Timestamp;
import utils.ErrorMaster;

/**
 * File:  TransReminderData.java 
 *
 * Created on September 2, 2013 9:25:33 AM
 */


public class TransReminderData
{
	public long 			transRemainderId_;
	
	public long 			transId_;
	
	public CompanyRegnKey 	regnKey_;
	
	public CompanyRegnKey 	customerKey_;
	
	public String 			remainder_;
	
	public Date 			dueDate_;
        
        private static ErrorMaster errorMaster_ = null;


	
	/*Constructor - To Initialize the class variables*/
	public TransReminderData( )
	{
		transRemainderId_	= -1;
		
		transId_ 			= -1;
		
		regnKey_ 			= new CompanyRegnKey( );
		
		customerKey_ 		= new CompanyRegnKey( );
		
		remainder_ 			= null;
		
		dueDate_ 			= null;
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
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "transRemainderId_ 	= "+ transRemainderId_);
		errorMaster_.insert( "transId_			= " + transId_ );
		errorMaster_.insert( "regnKey_			= " + regnKey_ );
		errorMaster_.insert( "customerKey_		= " + customerKey_ );
		errorMaster_.insert( "remainder_			= " + remainder_ );
		errorMaster_.insert( "dueDate_			= " + dueDate_ );
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
		transRemainderId_ 	= -1;
		transId_ 			= -1;
		regnKey_ 			= null;
		customerKey_ 		= null;
		remainder_ 			= null;
		dueDate_ 			= null;
	}
	
}

