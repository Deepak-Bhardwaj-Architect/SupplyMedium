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

import java.util.List;

import db.history.TransactionRemainderTable;


/**
 * File:  TransactionRemainder.java 
 *
 * Created on Sep 26, 2013 3:19:56 PM
 */
public class TransactionRemainder
{
	/*
	 * Method : TransactionRemainder -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransactionRemainder( )
	{
		
	}
	
	/*
	 * Method : add
	 * 
	 * Input  : TransReminderData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: Used to add the new Remainder 
	 */
	
	public int add( TransReminderData reminderData )
	{
		TransactionRemainderTable transactionRemainderTable=new TransactionRemainderTable( );
		
		int result = transactionRemainderTable.insert( reminderData );
		
		transactionRemainderTable = null;
		
		if( result == 0 )
			return 16030;  // Remainder successfully created
		else
			return 16032;  // Remainder creation failed 
		
    }
	
	/*
	 * Method : remove
	 * 
	 * Input  : transId
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It delete the Remainder using given transId. Also it remove the 
	 * Remainder member relationship
	 */
	
	public int remove( long transRemainderId )
	{
		TransactionRemainderTable transactionRemainderTable=new TransactionRemainderTable( );
		
		int result = transactionRemainderTable.delete( transRemainderId );
		
		transactionRemainderTable=null;
		
		if( result == 0 )
		{
			// delete the Remainder members from TransactionRemainder table
			
			return 16040;
		}
		else 
		{
			return 16042;
		}
	}
	
	/*
	 * Method : get
	 * 
	 * Input  : TransReminderData obj, list of TransReminderData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It get all the Remainder for given customerKey. And copied to remains parameter.
	 * So it is available for caller classes
	 */
	
	public int get( TransReminderData ncData,
			List<TransReminderData> remainders )
	{

		TransactionRemainderTable transactionRemainderTable = new TransactionRemainderTable( );

		int result = transactionRemainderTable.find( ncData, remainders );

		transactionRemainderTable = null;

		if( result == 0 )
			return 10320;
		else
			return 10321;
	}
}
	


