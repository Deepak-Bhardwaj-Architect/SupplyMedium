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

import core.dashboard.CustomerBasedData;
import core.dashboard.TimeBasedData;
import core.regn.CompanyRegnKey;

import ctrl.dashboard.CustomerBasedReportController;

/**
 * File:  CustomerBasedReportTest.java 
 *
 * Created on Oct 29, 2013 7:01:22 PM
 */
public class CustomerBasedReportTest
{
	/*
	 * Method : CustomerBasedReportTest -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public CustomerBasedReportTest()
	{
		
	}
	
	
	public void execute()
	{
		
		fetchTestDaily();
	}


	/*
	 * Method : fetchTestDaily()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to fetch customerBasedReport . 
	 */	
    public void fetchTestDaily()
    {
    	CustomerBasedReportController controller=new CustomerBasedReportController( );
    	
    	List<CustomerBasedData>customerBasedList=new ArrayList<CustomerBasedData>( );
    	
    	CompanyRegnKey fromRegnKey=new CompanyRegnKey( );
    	
		fromRegnKey.companyPhoneNo_="9003361885";
		
		CustomerBasedData customerBasedData=new CustomerBasedData( );
		
		customerBasedData.regnKey_=fromRegnKey;
		
		customerBasedData.reportType_ = "Daily";
		
		try 
	    {  
	      
			String datestr="23-Sep-2013  ";
	      
			DateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy" ); 
			
			java.util.Date shipDate = ( java.util.Date )formatter.parse( datestr );
			
			customerBasedData.filterDate_ = new java.sql.Date(shipDate.getTime());
			   
		} 
	   
		catch (Exception e)
	    {
			e.printStackTrace( );
	    }
		
		
		int result = controller.getDailyReport( customerBasedData, customerBasedList );
		
		if( result == 10220  )
		{
			System.out.println( "CustomerBasedReport fetchDaily test successfully completed" );
			
			for( CustomerBasedData customerBasedData1 : customerBasedList )
	        {
				customerBasedData1.show( );
	        }
			
		}
		
		else
		{
			System.out.println( "TimeBasedReport fetchDaily test failed" );
		}
    }

}
