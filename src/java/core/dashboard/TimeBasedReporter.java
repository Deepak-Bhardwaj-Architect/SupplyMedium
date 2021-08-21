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
import java.util.Calendar;
import java.util.List;



import db.history.TransactionHistoryTable;

/**
 * File:  TimeBasedReporter.java 
 *
 * Created on Oct 28, 2013 11:37:09 AM
 */
public class TimeBasedReporter
{
	
	/*
	 * Method : TimeBasedReporter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TimeBasedReporter()
	{
		
	}

	
	
	/*
	 * Method : getDaiyReport
	 * 
	 * Input  : TimeBasedData object, list of TimeBasedData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It get the time based report for one month in day wise. and
	 * add to customerBasedList parameter so it is copied to caller classes.
	 */
	
    public int getDailyReport( TimeBasedData timeBasedData, List<TimeBasedData> timeBasedList )
    {
		
		Calendar cal=Calendar.getInstance();
		
		DateFormat format=new SimpleDateFormat( "dd-MMM-yyyy" );
		
		format.format( timeBasedData.filterDate_ );
		
		cal=format.getCalendar();
		
		
		// Creating from date
		
		cal.set( Calendar.DAY_OF_MONTH,1 );
		
		java.util.Date fromDate=cal.getTime( );
		
		java.sql.Date sqlDate = new java.sql.Date(fromDate.getTime());
		
		timeBasedData.fromDate_=sqlDate;
		
		
		
		
		// Creating end date
		/*int noOfDaysMonth=cal.getActualMaximum( Calendar.DAY_OF_MONTH );
		
		cal.add( Calendar.DAY_OF_MONTH,noOfDaysMonth-1 );*/
	
		
		cal.add(  Calendar.MONTH,1 );
		
		java.util.Date toDate=cal.getTime( );
		
		java.sql.Date sqlDate1 = new java.sql.Date(toDate.getTime());
		
		timeBasedData.toDate_=sqlDate1;
		
		
		
	
		// Fetch the result from table
    	TransactionHistoryTable transactionHistoryTable = new TransactionHistoryTable( );

		int result = transactionHistoryTable.find( timeBasedData, timeBasedList );
		
		transactionHistoryTable = null;
		
		if( result == 0 )
			return 10400;
		else 
			return 10401;
		
    }


    
    /*
   	 * Method : getMonthlyReport
   	 * 
   	 * Input  : TimeBasedData object, list of TimeBasedData object
   	 * 
   	 * Return : int success/fail
   	 * 
   	 * Purpose:  It get the time based report for 1 year in month wise. and
	 * add to customerBasedList parameter so it is copied to caller classes.
   	 */
   	
       public int getMonthlyReport( TimeBasedData timeBasedData,
               List<TimeBasedData> timeBasedList )
       {
   		
   		Calendar cal=Calendar.getInstance();
   		
   		DateFormat format=new SimpleDateFormat("dd-MMM-yyyy");
   		
   		format.format(timeBasedData.filterDate_);
   		
   		cal=format.getCalendar();
   		
   		
   		cal.set( Calendar.MONTH,0 );
   		
   		java.util.Date fromDate=cal.getTime( );
   		
   		
   		java.sql.Date sqlDate = new java.sql.Date(fromDate.getTime());
   		
   		timeBasedData.fromDate_=sqlDate;
   		
  
   		//current date
   	/*	java.util.Date date=new java.util.Date( );
   						
   		java.util.Date toDate=date;
   		
   		java.sql.Date sqlDate1 = new java.sql.Date(toDate.getTime());
   		
   		timeBasedData.toDate_=sqlDate1;*/
   		
   	 // Creating end date
   		
   		cal.add( Calendar.YEAR,1 );
   			
   		java.util.Date toDate=cal.getTime( );
   			
   		java.sql.Date sqlDate1 = new java.sql.Date(toDate.getTime());
   			
   		timeBasedData.toDate_=sqlDate1;
   		
   	    
       	TransactionHistoryTable transactionHistoryTable = new TransactionHistoryTable( );

   		int result = transactionHistoryTable.find( timeBasedData, timeBasedList );
   		
   		transactionHistoryTable = null;
   		
   		if( result == 0 )
   			return 10410;
   		else 
   			return 10411;
   		
       	
       	
       }

    
    /*
	 * Method : getQuarterlyReport
	 * 
	 * Input  : TimeBasedData object, list of TimeBasedData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It get the time based report for 1 year data in quarterly wise. and
	 * add to customerBasedList parameter so it is copied to caller classes.
	 */
	
    public int getQuarterlyReport( TimeBasedData timeBasedData,
            List<TimeBasedData> timeBasedList )
    {
		
    	Calendar cal=Calendar.getInstance();
   		
   		DateFormat format=new SimpleDateFormat("dd-MMM-yyyy");
   		
   		format.format(timeBasedData.filterDate_);
   		
   		cal=format.getCalendar();
   		
   		
   		cal.set( Calendar.MONTH,0 );
   		
   		java.util.Date fromDate=cal.getTime( );
   		
   		
   		java.sql.Date sqlDate = new java.sql.Date(fromDate.getTime());
   		
   		timeBasedData.fromDate_=sqlDate;
   		
  
   		//current date
   		/*java.util.Date date=new java.util.Date( );
   						
   		java.util.Date toDate=date;
   		
   		java.sql.Date sqlDate1 = new java.sql.Date(toDate.getTime());
   		
   		timeBasedData.toDate_=sqlDate1;*/
   		
   		cal.add( Calendar.YEAR,1 );
			
   		java.util.Date toDate=cal.getTime( );
   			
   		java.sql.Date sqlDate1 = new java.sql.Date(toDate.getTime());
   			
   		timeBasedData.toDate_=sqlDate1;
   		
   	    
       	TransactionHistoryTable transactionHistoryTable = new TransactionHistoryTable( );

   		int result = transactionHistoryTable.find( timeBasedData, timeBasedList );
   		
   		transactionHistoryTable = null;
   		
   		for( TimeBasedData timeBasedData2 : timeBasedList )
        {
	        if( (timeBasedData2.timeLabel_.equals( "1" )))
	        {
	        	
	        }
        }
   		
		if( result == 0 )
			return 10420;
		else 
			return 10421;
      
    }

    
    
    /*
	 * Method : getYearlyReport
	 * 
	 * Input  : TimeBasedData object, list of TimeBasedData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It get the time based report for all his transaction in year wise. and
	 * add to customerBasedList parameter so it is copied to caller classes.
	 */
	
    public int getYearlyReport( TimeBasedData timeBasedData,
            List<TimeBasedData> timeBasedList )
    {
		
		/*Calendar cal=Calendar.getInstance();
		
		DateFormat format=new SimpleDateFormat("dd-MMM-yyyy");
		
		format.format(timeBasedData.filterDate_);
		
		
		
		cal=format.getCalendar();
		
		cal.get( Calendar.DAY_OF_YEAR );
		
		cal.get( Calendar.MONTH );
	
		int noOfDaysMonth=cal.getActualMaximum( Calendar.DAY_OF_YEAR );
		
		
		// Setting from date
		
		cal.set( Calendar.DAY_OF_YEAR,1 );
		
		java.util.Date fromDate=cal.getTime( );
		
		java.sql.Date sqlDate = new java.sql.Date(fromDate.getTime());
		
		timeBasedData.fromDate_=sqlDate;
		
		
		
		// Setting to date
		
		cal.add( Calendar.DAY_OF_YEAR,noOfDaysMonth-1 );
		
		java.util.Date toDate=cal.getTime( );
		
		java.sql.Date sqlDate1 = new java.sql.Date(toDate.getTime());
		
		timeBasedData.toDate_=sqlDate1;*/
		
		
	    
    	TransactionHistoryTable transactionHistoryTable = new TransactionHistoryTable( );

		int result = transactionHistoryTable.find( timeBasedData, timeBasedList );
		
		transactionHistoryTable = null;
		
		if( result == 0 )
			return 10430;
		else 
			return 10431;
    	
    }


   

}
