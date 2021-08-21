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

import core.dashboard.TimeBasedData;
import core.dashboard.TimeBasedReporter;

/**
 * File:  TimeBasedReportController.java 
 *
 * Created on Oct 28, 2013 11:35:02 AM
 */
public class TimeBasedReportController
{

	
	public TimeBasedReportController()
	{
		
	}

	/*
	 * Method : processPM
	 * 
	 * Input  : HttpServletRequest object, list of TimeBasedData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: This method is used to control the TimeBaseReport. It do the following operation in TimeBaseReport
	 *
	 *1. Get  the TimeBaseReport for given user key
	 * 
	 * 
	 */



    public int processTB( HttpServletRequest request, List<TimeBasedData> timeBasedList )
    {
    	
    	int result = 0;
   	 // Converting request object to TimeBasedData object
   	
   	TimeBasedDataConverter converter = new TimeBasedDataConverter( );
   	
   	TimeBasedData timeBasedData = new TimeBasedData( );
   	
   	result = converter.convert( request, timeBasedData );
      
   	converter = null;
   	
   	if( result != 0 )
   		return 10401;  // Parser error.
   	
   	String requestType = request.getParameter( "RequestType" );
   	
    if( requestType.equals( "Daily" ))
   	{
   		result = getDailyReport( timeBasedData, timeBasedList);
   	}
    
    else if (requestType.equals( "Monthly" )) 
    {
    	result=getMonthlyReport(timeBasedData,timeBasedList);
		
	}
    
    else if (requestType.equals( "Yearly" ))
    {
		result=getYearlyReport(  timeBasedData,timeBasedList);
	}
    
    else if (requestType.equals( "Quarterly" ))
    {
    	result=getQuarterlyReport( timeBasedData,timeBasedList );
	}
   	else
   	{
   		result = 10402;  // can't find appropriate request type
   	}
   	
   	return result;
   }

    
    
    /*
     * Method : getDailyReport
     * 
     * Input  : TimeBasedData object, list of TimeBasedData object
     * 
     * Return : int success/fail
     * 
     * Purpose: It get the time based report for one month in day wise. and
	 * add to customerBasedList parameter so it is copied to caller classes.
     */
    
   
	public  int getDailyReport( TimeBasedData timeBasedData, List<TimeBasedData> timeBasedList )
    {
    	int result = 0;
    	
    	TimeBasedReporter timeBasedReporter = new TimeBasedReporter( );
    	
    	result = timeBasedReporter.getDailyReport( timeBasedData, timeBasedList );
    	
    	return result;
	    
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
    	
    	int result = 0;
    	
    	TimeBasedReporter timeBasedReporter = new TimeBasedReporter( );
    	
    	result = timeBasedReporter.getMonthlyReport( timeBasedData, timeBasedList );
    	
    	return result;
    }

	/*
	 * Method : getQuarterlyReport
	 * 
	 * Input : TimeBasedData object, list of TimeBasedData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose:It get the time based report for 1 year data in quarterly wise. and
	 * add to customerBasedList parameter so it is copied to caller classes.
	 */

	public int getQuarterlyReport( TimeBasedData timeBasedData,
	        List<TimeBasedData> timeBasedList )
	{
		int result = 0;

		TimeBasedReporter timeBasedReporter = new TimeBasedReporter( );

		result = timeBasedReporter.getQuarterlyReport( timeBasedData, timeBasedList );

		return result;

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
    	int result = 0;
    	
    	TimeBasedReporter timeBasedReporter = new TimeBasedReporter( );
    	
    	result = timeBasedReporter.getYearlyReport( timeBasedData, timeBasedList );
    	
    	return result;
    }
	   
}


