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
package core.dashboard;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.regn.CompanyRegnKey;
import core.regn.RegnData;

import db.history.TransactionHistoryTable;
import db.regn.CompanyRegnTable;


/**
 * File:  CustomBasedReporter.java 
 *
 * Created on Oct 28, 2013 11:37:33 AM
 */

public class CustomerBasedReporter
{

	
	/*
	 * Method : getDailyReport
	 * 
	 * Input  : CustomerBasedData object, list of CustomerBasedData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It get the customer based report for one month in day wise. and
	 * add to customerBasedList parameter so it is copied to caller classes.
	 */
	
    public int getDailyReport( CustomerBasedData customerBasedData,
            List<CustomerBasedData> customerBasedList )
    {
    	
    	int result=0;
    	
    	Calendar cal=Calendar.getInstance();
		
		DateFormat format=new SimpleDateFormat( "dd-MMM-yyyy" );
		
		format.format( customerBasedData.filterDate_ );
		
		cal=format.getCalendar();
		
		
		// Creating from date
		
		cal.set( Calendar.DAY_OF_MONTH,1 );
		
		java.util.Date fromDate=cal.getTime( );
		
		java.sql.Date sqlDate = new java.sql.Date(fromDate.getTime());
		
		customerBasedData.fromDate_=sqlDate;
		
		System.out.println( "from date="+fromDate );
		
		
		// Creating end date
		int noOfDaysMonth=cal.getActualMaximum( Calendar.DAY_OF_MONTH );
		
		cal.add( Calendar.DAY_OF_MONTH,noOfDaysMonth-1 );
		
		java.util.Date toDate=cal.getTime( );
		
		java.sql.Date sqlDate1 = new java.sql.Date(toDate.getTime());
		
		customerBasedData.toDate_=sqlDate1;
		
		System.out.println( "to date="+toDate );
		
    	
    	// Fetching all the records related users and customers
    	List< CustomerBasedData > tempList = new ArrayList<CustomerBasedData>( );
	    
    	TransactionHistoryTable transHistoryTable = new TransactionHistoryTable( );
    	
    	result = transHistoryTable.find( customerBasedData, tempList );
    	
    	transHistoryTable = null;
    	
    	if( result != 0 )
    		return 10441;
    	
    	result = generateCustomerReport( customerBasedData.regnKey_, customerBasedList, tempList );
    	
		
		if( result == 0 )
			return 10440;
		else 
			return 10441;
    }
    
    
    /*
	 * Method : getMonthlyReport
	 * 
	 * Input  : CustomerBasedData object, list of CustomerBasedData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It get the customer based report for 1 year in month wise. and
	 * add to customerBasedList parameter so it is copied to caller classes.
	 */
	
    public int getMonthlyReport( CustomerBasedData customerBasedData,
            List<CustomerBasedData> customerBasedList )
    {
    	
    	int result=0;
    	
    	Calendar cal=Calendar.getInstance();
   		
   		DateFormat format=new SimpleDateFormat("dd-MMM-yyyy");
   		
   		format.format(customerBasedData.filterDate_);
   		
   		cal=format.getCalendar();
   		
   		
   		cal.set( Calendar.DAY_OF_YEAR,1 );
   		
   		java.util.Date fromDate=cal.getTime( );
   		
   		
   		java.sql.Date sqlDate = new java.sql.Date(fromDate.getTime());
   		
   		customerBasedData.fromDate_=sqlDate;
   		
   		
   		
   		//current date
   		java.util.Date date=new java.util.Date( );
   						
   		java.util.Date toDate=date;
   		
   		java.sql.Date sqlDate1 = new java.sql.Date(toDate.getTime());
   		
   		customerBasedData.toDate_=sqlDate1;
   		
    	
    	// Fetching all the records related users and customers
    	List< CustomerBasedData > tempList = new ArrayList<CustomerBasedData>( );
	    
    	TransactionHistoryTable transHistoryTable = new TransactionHistoryTable( );
    	
    	result = transHistoryTable.find( customerBasedData, tempList );
    	
    	transHistoryTable = null;
    	
    	if( result != 0 )
    		return 10451;
    	
    	result = generateCustomerReport( customerBasedData.regnKey_, customerBasedList, tempList );
    	
		
		if( result == 0 )
			return 10450;
		else 
			return 10451;
    }
    

    
    
    /*
	 * Method : getPeroidReport
	 * 
	 * Input  : CustomerBasedData object, list of CustomerBasedData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose:  It get the customer based report for all his transaction in between two dates. and
	 * add to customerBasedList parameter so it is copied to caller classes.
	 */
	
    public int getPeroidReport( CustomerBasedData customerBasedData,
            List< CustomerBasedData > cusBasedList )
    {
		int result = 0;
		
		// Fetching all the records related users and customers
    	List< CustomerBasedData > tempList = new ArrayList<CustomerBasedData>( );
	    
    	TransactionHistoryTable transHistoryTable = new TransactionHistoryTable( );
    	
    	result = transHistoryTable.find( customerBasedData, tempList );
    	
    	transHistoryTable = null;
    	
    	if( result != 0 )
    		return 10471;
    	
    	result = generateCustomerReport( customerBasedData.regnKey_, cusBasedList, tempList );
    	
		
		if( result == 0 )
			return 10470;
		else 
			return 10471;
    	
    }

    
    // 
    
    private int generateCustomerReport( CompanyRegnKey regnKey, List< CustomerBasedData > customerBasedList, 
    									List< CustomerBasedData > tempList )
    {
    	int result = 0;
    	
    	String currency = "";
    	
    	Map<CompanyRegnKey, Double> customerTransMap = new HashMap<CompanyRegnKey, Double>();
    	
    	// adding unique customer key and customers transaction amount in map
    	for( CustomerBasedData customerBasedData : tempList )
        {
    		
    		currency = customerBasedData.currencyType_;
    		
    		// check customer regnkey and result regnkey is equal if yes, to regnkey is the customer key
	        if( customerBasedData.regnKey_.equals( regnKey ))
	        {
	        	Double amount = customerTransMap.get( customerBasedData.toRegnKey_ );
	        	
	        	// For this customer key already exist in map.So get that old amount and add with new amount and set
	        	if (amount != null) 
	        	{
	        	    amount = amount + customerBasedData.transactionAmount_;
	        	    
	        	    customerTransMap.put( customerBasedData.toRegnKey_, amount );
	        	} 
	        	else // For this customer key not exist, so add newly with amount
	        	{
	        		customerTransMap.put( customerBasedData.toRegnKey_, customerBasedData.transactionAmount_ );

	        	}
	        }
	        else
	        {
	        	Double amount = customerTransMap.get( customerBasedData.regnKey_ );
	        	
	        	if (amount != null) 
	        	{
	        	    amount = amount + customerBasedData.transactionAmount_;
	        	    
	        	    customerTransMap.put( customerBasedData.regnKey_, amount );
	        	} 
	        	else
	        	{
	        		customerTransMap.put( customerBasedData.regnKey_, customerBasedData.transactionAmount_ );

	        	}
			}
        }
    	
    	// Get the values from map and set to final list
    	
    	CompanyRegnTable regnTable = new CompanyRegnTable( );
    	
    	for (CompanyRegnKey  key: customerTransMap.keySet())
    	{

    	   Double amount = customerTransMap.get(key);
    	   
    	   RegnData regnData = new RegnData( );
    	   
    	   regnTable.getCompany( key, regnData );
    	   
    	   
    	   CustomerBasedData customerBasedData = new CustomerBasedData( );
    	   
    	   customerBasedData.regnKey_ = regnKey;
    	   
    	   customerBasedData.toRegnKey_ = key;
    	   
    	   customerBasedData.transactionAmount_ = amount;
    	   
    	   customerBasedData.vendorCompanyName_ = regnData.companyName_;
    	   
    	   customerBasedData.currencyType_ = currency;
    	   
    	   customerBasedList.add( customerBasedData );
    	   
    	   customerBasedData = null;
    	   
    	   regnData = null;
    	   
    	}
    	
    	regnTable = null;
    	
    	
    	
    	return result;
    	
    }

}
