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
package ctrl.dashboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import core.dashboard.CustomerBasedData;
import core.dashboard.CustomerBasedReporter;

/**
 * File:  CustomerBasedReportController.java 
 *
 * Created on Oct 28, 2013 11:35:31 AM
 */
public class CustomerBasedReportController
{
	

	/*
	 * Method : processCB
	 * 
	 * Input  : HttpServletRequest object, list of CustomerBased object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: This method is used to control the CustomerBaseReport. It do the following operation in TimeBaseReport
	 *
	 *1. Get  the TimeBaseReport for given user key
	 * 
	 * 
	 */
    public int processCB( HttpServletRequest request,
            List<CustomerBasedData> customerBasedList )
    {
    	int result = 0;
      	 // Converting request object to CustomerBaseData object
      	
      	CustomerBasedDataConverter converter = new CustomerBasedDataConverter( );
      	
      	CustomerBasedData customerBasedData = new CustomerBasedData( );
      	
      	result = converter.convert( request, customerBasedData );
         
      	
      	converter = null;
      	
      	if( result != 0 )
      		return 10441;  // Parser error.
      	
      	String requestType = request.getParameter( "RequestType" );
      	
       if( requestType.equals( "Daily" ))
      	{
      		result = getDailyReport( customerBasedData, customerBasedList );
      	}
       else if (requestType.equals( "Monthly" )) 
       {
       		result=getMonthlyReport( customerBasedData, customerBasedList );
       }
       
       else if (requestType.equals( "Period" ))
       {
   			result=getPeriodReport(  customerBasedData, customerBasedList );
   		}
       
      	else
       	{
       		result = 10442;  // can't find appropriate request type
       	}
       	
       	return result;
	    
    }

	

	/*
	 * Method : getDailyReport
	 * 
	 * Input : CustomerBasedData object, list of CustomerBasedData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It get the customer based report for one month in day wise. and
	 * add to customerBasedList parameter so it is copied to caller classes.
	 * 
	 * 
	 */
	public int getDailyReport( CustomerBasedData customerBasedData,
	        List<CustomerBasedData> customerBasedList )
	{
		int result = 0;

		CustomerBasedReporter customerBasedReporter = new CustomerBasedReporter( );

		result = customerBasedReporter
		        .getDailyReport( customerBasedData, customerBasedList );
		
		customerBasedReporter = null;

		return result;

	}
	
	/*
	 * Method : getMonthlyReport
	 * 
	 * Input : TimeBasedData object, list of TimeBasedData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose:  It get the customer based report for 1 year in month wise. and
	 * add to customerBasedList parameter so it is copied to caller classes.
	 */

	public int getMonthlyReport( CustomerBasedData customerBasedData,
	        List<CustomerBasedData> customerBasedList )
	{

		int result = 0;

		CustomerBasedReporter customerBasedReporter = new CustomerBasedReporter( );

		result = customerBasedReporter.getMonthlyReport( customerBasedData, customerBasedList );
		
		customerBasedReporter = null;

		return result;
	}



	/*
	 * Method : getPeriodReport
	 * 
	 * Input : TimeBasedData object, list of TimeBasedData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose:It get the customer based report for all his transaction in between two dates. and
	 * add to customerBasedList parameter so it is copied to caller classes.
	 */

	public int getPeriodReport( CustomerBasedData customerBasedData,
	        List<CustomerBasedData> customerBasedList )
	{
		int result = 0;

		CustomerBasedReporter customerBasedReporter = new CustomerBasedReporter( );

		result = customerBasedReporter.getPeroidReport( customerBasedData, customerBasedList );
		
		customerBasedReporter = null;

		return result;
	}

	
}
