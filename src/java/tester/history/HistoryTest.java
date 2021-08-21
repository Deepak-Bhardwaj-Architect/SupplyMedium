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
package tester.history;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import core.history.CustomerTransactionData;
import core.history.TransRatingsData;
import core.history.TransReminderData;
import core.history.TransactionHistoryData;
import core.history.TransactionRating;
import core.history.TransactionRemainder;
import core.regn.CompanyRegnKey;
import ctrl.history.HistoryController;
import utils.ErrorMaster;

/**
 * File:  HistoryTest.java 
 *
 * Created on Sep 27, 2013 6:03:39 PM
 */
public class HistoryTest
{
	private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : HistoryTest -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public HistoryTest()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	public void execute() 
	{
		//addTest( );
		//addTestRating( );
		//deleteTest( );
		//fetchTest( );
		fetchTransactionHistoryTest( );
	}
	
	
	//Remainder---------------
	
	/*
	 * Method : addTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to add the Remainder. 
	 */
	public void addTest()
			
	{
		HistoryController historyCtrl=new HistoryController( );
		
		TransReminderData reminderData=new TransReminderData( );
		
		reminderData.regnKey_.companyPhoneNo_="63963";
		
		reminderData.customerKey_.companyPhoneNo_="62585";
		
		reminderData.remainder_="second remainder";
		
		try 
	    {  
	      
			String datestr="2013-09-29 23:59:59 ";
	      
			DateFormat formatter; 
	   
			Date date; 
		
			formatter = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
	     
			date = (Date)formatter.parse(datestr); 
	      
	      
			reminderData.dueDate_=date;
	    
	    } 
	   
		catch (Exception e)
	    {
			
	    }
		
		int result = historyCtrl.addReminder( reminderData );
		
		errorMaster_.insert( "Remainder result id="+result );
		
		if( result == 10300  )
		{
			errorMaster_.insert( "Remainder add test successfully completed" );
			
			errorMaster_.insert( "Remainder added successfully" );
		}
		
		else
		{
			errorMaster_.insert( "Remainder add test failed" );
		}
			
		
		
	}
	
	
	
	/*
	 * Method : deleteTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to delete the Remainder. 
	 */
	
	public void deleteTest()
	{
		HistoryController historyCtrl=new HistoryController( );
		long transId=7;
		int result = historyCtrl.remove( transId );
		
		if( result == 10310  )
		{
			errorMaster_.insert( "Remainder delete test successfully completed" );
			
		}
		
		else
		{
			errorMaster_.insert( "Remainder delete test failed" );
		}
	}
	
	/*
	 * Method : fetchTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to delete the Remainder. 
	 */	
	public void fetchTest()
	{
		HistoryController historyCtrl=new HistoryController( );
		
		List<TransReminderData> remainders =new ArrayList<TransReminderData>( );
		
			
		CompanyRegnKey customerKey=new CompanyRegnKey( );
		
		customerKey.companyPhoneNo_="69315441215";
		
		TransReminderData ncData=new TransReminderData( );
		
		ncData.customerKey_=customerKey;
		
		int result = historyCtrl.get( ncData, remainders );
		
		if( result == 10320  )
		{
			errorMaster_.insert( "Remainder fetch test successfully completed" );
			
			for( TransReminderData reminderData : remainders )
            {
				reminderData.show( );
            }
			
		}
		
		else
		{
			errorMaster_.insert( "Remainder fetch test failed" );
		}
			
	}
	
	
	//Ratings---------------------
	
	/*
	 * Method : addTestRating()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to add the Ratings
	 */
		
	public void addTestRating()
	{
		HistoryController historyCtrl=new HistoryController( );
		
		TransRatingsData ratingsData=new TransRatingsData( );
		
		ratingsData.regnKey_.companyPhoneNo_="9003361885";
		
		ratingsData.customerKey_.companyPhoneNo_="2563987411";
		
		ratingsData.starCount_=5;
		
		int result = historyCtrl.addRatings( ratingsData );
		
		errorMaster_.insert( "Ratings result id="+result );
		
		if( result == 16020  )
		{
			errorMaster_.insert( "Ratings add test successfully completed" );
			
			errorMaster_.insert( "Ratings added successfully" );
		}
		
		else
		{
			errorMaster_.insert( "Ratings add test failed" );
		}
			
	}
	
	
	//TransactionHistory----------------
	
	/*
	 * Method : fetchTransactionHistoryTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to fetch the TransactionHistory. 
	 */	
	
	public void fetchTransactionHistoryTest()
	{
		HistoryController historyCtrl=new HistoryController( );
		
		List<TransactionHistoryData> transHistoryDataList=new ArrayList<TransactionHistoryData>( );
		
		CompanyRegnKey customerKey=new CompanyRegnKey( );
		
		customerKey.companyPhoneNo_="9751856585";
		
		CompanyRegnKey regnKey=new CompanyRegnKey( );
		
		regnKey.companyPhoneNo_="9003361885";
		
		
		
		CustomerTransactionData custransData=new CustomerTransactionData( );
		//custransData.regnKey_=regnKey;
		
		
		int result = historyCtrl.getTransactionHistory(customerKey,regnKey, custransData );
		
		
		if( result == 10320  )
		{
			errorMaster_.insert( "TransactionHistory fetch test successfully completed" );
			
			for( TransactionHistoryData transactionHistoryData : transHistoryDataList )
            {
				transactionHistoryData.show( );
            }
			
		}
		
		else
		{
			errorMaster_.insert( "TransactionHistory fetch test failed" );
		}
			
	}
}
	


