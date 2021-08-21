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

package ctrl.history;


import java.util.List;

import javax.servlet.http.HttpServletRequest;


import utils.ErrorLogger;

import core.history.CustomerHistory;
import core.history.CustomerHistoryData;
import core.history.CustomerTransactionData;
import core.history.TransRatingsData;
import core.history.TransReminderData;
import core.history.TransactionRating;
import core.history.TransactionRemainder;
import core.regn.CompanyRegnKey;

/**
 * File:  HistoryController.java 
 *
 * Created on September 3, 2013 8:14:21 AM
 */

/*
 * Class: HistoryController
 * 
 * Purpose: This is used to control the following Customer transaction history operations
 * 
 * 1. Fetch all the customer list
 * 
 * 2. Fetch all the transactions for selected customer
 * 
 * 3.Add new remainder
 * 
 * 4.Add ratings for a transaction
 */

public class HistoryController
{
	
	/*
	 * Method : HistoryController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public HistoryController()
	{
	
	}
	
	
	
	
	/*
	 * Method: getCustomersHistory
	 * 
	 * Input: HttpServletRequest obj, List<CustomerHistoryData> listObj
	 * 
	 * Return: int
	 * 
	 * Purpose: To fetch all the customer list 
	 */
	
	public int getCustomersHistory( HttpServletRequest request, List<CustomerHistoryData> historyDataList )
	{
		CompanyRegnKey regnKey = new CompanyRegnKey( );
		
		regnKey.companyPhoneNo_ = request.getParameter( "CompanyRegnKey" );
		
		return getCustomersHistory( regnKey, historyDataList );
	}
	
	/*Method:getCustomerHistory
	 * 
	 * Input:CompanyRegnKey obj,List<CustomerHistoryData> listObj
	 * 
	 * purpose:To fetch all the Customer list
	 * 
	 */
	
	public int getCustomersHistory( CompanyRegnKey regnKey, List<CustomerHistoryData> historyDataList )
	{
		int result=0;
		
		CustomerHistory customerHistory=new CustomerHistory( );
		
		result=customerHistory.getCustomersHistory(regnKey,historyDataList);
		
		return result;
		
		
		
	}
	
	/*
	 * Method: getTransactionHistory
	 * 
	 * Input: HttpServletRequest obj, List<TransactionHistoryData> listObj
	 * 
	 * Return: int
	 * 
	 * Purpose: To fetch all the transactions for selected customer 
	 */
	

	public int getTransactionHistory( HttpServletRequest request,
			CustomerTransactionData cusTransData )
	{
		
		CompanyRegnKey regnKey = new CompanyRegnKey( );
		
		CompanyRegnKey customerKey = new CompanyRegnKey( );
		
		regnKey.companyPhoneNo_ = request.getParameter( "CompanyRegnKey" );
		
		customerKey.companyPhoneNo_ = request.getParameter( "CustomerKey" );
		
		return getTransactionHistory( regnKey, customerKey, cusTransData );
	}

	
	
	public int getTransactionHistory( CompanyRegnKey regnKey, CompanyRegnKey customerKey,
			CustomerTransactionData cusTransData )
	{
		int result=0;
		
		CustomerHistory customerHistory=new CustomerHistory( );
		
		result=customerHistory.getTransactionHistory(regnKey,customerKey,cusTransData);
		
		return result;
	}

	/*
	 * Method: addRemainder
	 * 
	 * Input: HttpServletRequest obj, TransReminderData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: To Add new remainder
	 */
	
	public int addReminder( HttpServletRequest request )
	{
		System.out.println("add remainder controller start");
		
		TransReminderData reminderData = new TransReminderData( );
		
		HistoryDataConverter converter = new HistoryDataConverter( );
		
		int result = converter.getTransReminderData( request, reminderData );
		
		if( result != 0 )
		{
			ErrorLogger.instance( ).logMsg( "Error::HistoryController.addReminder() - Unable to parse data" );
			
			return 16031;
		}
		
		System.out.println("add remainder controller end");
		
		return addReminder( reminderData );
	}

	/*Helper method for above method which can be directly called for unit testing*/
	
	public int addReminder( TransReminderData reminderData )
	{
		int result=0;
		
		TransactionRemainder transactionRemainder=new TransactionRemainder( );
		
		result =transactionRemainder.add( reminderData );
		
		transactionRemainder=null;
		
		return result;
		
		
	}
	
	
	/*
	 * Method: remove
	 * 
	 * Input: long transId
	 * 
	 * Return: int
	 * 
	 * Purpose: To delete the remainder
	 */
	
	public int remove( long transRemainderId)
	{
		int result = 0;
		
		TransactionRemainder transactionRemainder = new TransactionRemainder( );
		
		result = transactionRemainder.remove( transRemainderId );
		
		transactionRemainder = null;
		
		return result;
	}
	
	
	
	/*
	 * Method: get
	 * 
	 * Input: TransRemainderData obj, list of TransReminderData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: fetch all the TransactionRemainder
 */
	
	public int get( TransReminderData ncData, List<TransReminderData> remainders )
	{

		int result = 0;
		
		TransactionRemainder transactionRemainder = new TransactionRemainder( );
		
		result = transactionRemainder.get( ncData, remainders );
		
		transactionRemainder = null;
		
		return result;
	}

	
	/*
	 * Method: addRatings
	 * 
	 * Input: HttpServletRequest obj
	 * 
	 * Return: int
	 * 
	 * Purpose: To Add new ratings
	 */
	
	public int addRatings( HttpServletRequest request )
	{
		
		
		TransRatingsData ratingsData = new TransRatingsData( );
		
		HistoryDataConverter converter = new HistoryDataConverter( );
		
		int result = converter.getTransRatingsData( request, ratingsData );
		
		if( result != 0 )
		{
			ErrorLogger.instance( ).logMsg( "Error::HistoryController.addRatings() - Unable to parse data" );
			
			return 16021;
		}
		
		result = addRatings( ratingsData );
		
		return result;
	}
	
	
	
	public int addRatings( TransRatingsData ratingsData )
	{
		
		int result=0;
		
		TransactionRating transactionRating=new TransactionRating( );
		
		result =transactionRating.add( ratingsData );
		
		transactionRating=null;
		
		return result;
		
	}
	
}
