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
package tester.dashboard;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



import core.dashboard.TimeBasedData;

import core.regn.CompanyRegnKey;

import ctrl.dashboard.TimeBasedReportController;


/**
 * File:  TimeBasedReportTest.java 
 *
 * Created on Oct 28, 2013 6:35:40 PM
 */
public class TimeBasedReportTest
{
	
	/*
	 * Method : TimeBasedReportTest -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TimeBasedReportTest()
	{
		
	}
	
	
	public void execute()
	{
		
		//fetchTestDaily( );
		//fetchTestMonthly();
		fetchTestYearly();
		 //fetchTestQuarterly();
	}
	
	

    /*
	 * Method : fetchTestDaily()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to fetch the TimebasedReportDaily. 
	 */	
    

	public void fetchTestDaily()
	{
	
	TimeBasedReportController controller = new TimeBasedReportController( );
	
	List<TimeBasedData> timebasedList = new ArrayList<TimeBasedData>( );

	CompanyRegnKey fromRegnKey=new CompanyRegnKey( );
	
	fromRegnKey.companyPhoneNo_="9751856585";
	
		
	TimeBasedData timeBasedData = new TimeBasedData( );
	
	timeBasedData.regnKey_=fromRegnKey;
	
	timeBasedData.reportType_ = "Daily";
	
		
	//timeBasedData.createdTimestamp_=ts;
	
	
	try 
    {  
      
		String datestr="23-Sep-2013  ";
      
		DateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy" ); 
		
		java.util.Date shipDate = ( java.util.Date )formatter.parse( datestr );
		
		timeBasedData.filterDate_ = new java.sql.Date(shipDate.getTime());
		   
	} 
   
	catch (Exception e)
    {
		e.printStackTrace( );
    }
		
	int result = controller.getDailyReport( timeBasedData, timebasedList );
	
	if( result == 10220  )
	{
		System.out.println( "TimeBasedReport fetchDaily test successfully completed" );
		
		for( TimeBasedData timeBasedData1 : timebasedList )
        {
			timeBasedData1.show( );
        }
		
	}
	
	else
	{
		System.out.println( "TimeBasedReport fetchDaily test failed" );
	}
		
}
	
	
	/*
	 * Method : fetchTestMonthly()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to fetch the TimebasedReportMonthly. 
	 */	
	
	public void fetchTestMonthly()
	{
		
		TimeBasedReportController controller = new TimeBasedReportController( );
		
		List<TimeBasedData> timebasedList = new ArrayList<TimeBasedData>( );

		CompanyRegnKey fromRegnKey=new CompanyRegnKey( );
		fromRegnKey.companyPhoneNo_="9003361885";
		
			
		TimeBasedData timeBasedData = new TimeBasedData( );
		
		timeBasedData.regnKey_=fromRegnKey;
			
		timeBasedData.reportType_ = "Monthly";
		
		
		try 
	    {  
	      
			String datestr="29-Feb-2013  ";
	      
			DateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy" ); 
			
			java.util.Date shipDate = ( java.util.Date )formatter.parse( datestr );
			
			timeBasedData.filterDate_ = new java.sql.Date(shipDate.getTime());
			
			
			System.out.println( "date");
			   
		} 
	   
		catch (Exception e)
	    {
			e.printStackTrace( );
	    }
		
		int result = controller.getMonthlyReport( timeBasedData, timebasedList );
		
		if( result == 10220  )
		{
			System.out.println( "TimeBasedReport fetchMonthly test successfully completed" );
			
			for( TimeBasedData timeBasedData1 : timebasedList )
	        {
				timeBasedData1.show( );
	        }
			
		}
		
		else
		{
			System.out.println( "TimeBasedReport fetchMonthly test failed" );
		}
		
	}
	

	/*
	 * Method : fetchTestYearly()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to fetch the TimebasedReportYearly. 
	 */	
    public void fetchTestYearly( )
    {
    	TimeBasedReportController controller = new TimeBasedReportController( );
		
		List<TimeBasedData> timebasedList = new ArrayList<TimeBasedData>( );

		CompanyRegnKey fromRegnKey=new CompanyRegnKey( );
		
		fromRegnKey.companyPhoneNo_="9751856585";
		
			
		TimeBasedData timeBasedData = new TimeBasedData( );
		
		timeBasedData.regnKey_=fromRegnKey;
		
		timeBasedData.reportType_ = "Yearly";
		
			
		//timeBasedData.createdTimestamp_=ts;
		
		
		try 
	    {  
	      
			String datestr="23-Sep-2013  ";
	      
			DateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy" ); 
			
			java.util.Date shipDate = ( java.util.Date )formatter.parse( datestr );
			
			timeBasedData.filterDate_ = new java.sql.Date(shipDate.getTime());
			
			
			System.out.println( "date" +datestr);
			   
		} 
	   
		catch (Exception e)
	    {
			e.printStackTrace( );
	    }	
		
		int result = controller.getYearlyReport( timeBasedData, timebasedList );
		
		if( result == 10220  )
		{
			System.out.println( "TimeBasedReport fetchYear test successfully completed" );
			
			for( TimeBasedData timeBasedData1 : timebasedList )
	        {
				timeBasedData1.show( );
	        }
			
		}
		
		else
		{
			System.out.println( "TimeBasedReport fetchYear test failed" );
		}
	    
    }

    /*
	 * Method : fetchTestQuarterly()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to fetch the TimebasedReportQuarterly. 
	 */	
    
    public void fetchTestQuarterly( )
    {
    	TimeBasedReportController controller = new TimeBasedReportController( );
		
		List<TimeBasedData> timebasedList = new ArrayList<TimeBasedData>( );

		CompanyRegnKey fromRegnKey=new CompanyRegnKey( );
		fromRegnKey.companyPhoneNo_="9751856585";
		
			
		TimeBasedData timeBasedData = new TimeBasedData( );
		
		timeBasedData.regnKey_=fromRegnKey;
		
			
		//timeBasedData.createdTimestamp_=ts;
		
		
		try 
	    {  
	      
			String datestr="23-AUG-2013  ";
	      
			DateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy" ); 
			
			java.util.Date shipDate = ( java.util.Date )formatter.parse( datestr );
			
			timeBasedData.filterDate_ = new java.sql.Date(shipDate.getTime());
			
			
			//System.out.println( "date");
			   
		} 
	   
		catch (Exception e)
	    {
			e.printStackTrace( );
	    }
			
		int result = controller.getQuarterlyReport( timeBasedData, timebasedList );
		
		if( result == 10220  )
		{
			System.out.println( "TimeBasedReport fetchQuarterly test successfully completed" );
			
			for( TimeBasedData timeBasedData1 : timebasedList )
	        {
				timeBasedData1.show( );
	        }
			
		}
		
		else
		{
			System.out.println( "TimeBasedReport fetchQuarterly test failed" );
		}
		
	}
	
}
