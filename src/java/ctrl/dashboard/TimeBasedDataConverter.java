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


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.dashboard.TimeBasedData;

/**
 * File:  TimeBasedDataConverter.java 
 *
 * Created on Oct 28, 2013 3:49:18 PM
 */
public class TimeBasedDataConverter
{
	
	ErrorLogger errorLogger_;
	
	/*
	 * Method : TimeBasedDataConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	
	public TimeBasedDataConverter()
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	
	/*
	 * Method : convert 
	 * 
	 * Input  : HttpServletRequest object, TimeBasedData object
	 * 
	 * Return : int - success/fail
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to TimeBasedData object.
	 * And copied to TimeBasedData parameter so it available in caller classes.
	 * 
	 */
	public int convert( HttpServletRequest request, TimeBasedData timeBasedData )
    {
		int result = 0;
		
		try
        {
			
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			/* TimeBasedReport details */
			
			if( reqMap.containsKey( "RegnKey" ) )
			{
				timeBasedData.regnKey_.companyPhoneNo_ = request.getParameter( "RegnKey" );
			}
			
			if( reqMap.containsKey( "RequestType" ) )
			{
				timeBasedData.reportType_ = request.getParameter( "RequestType" );
			}
			
			if( reqMap.containsKey( "FilterDate" ) )
			{
				DateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy" ); 
				
				String invoiceDueDateStr = request.getParameter( "FilterDate" );;
				
				java.util.Date invoiceDueDate = ( java.util.Date )formatter.parse( invoiceDueDateStr );
				
				timeBasedData.filterDate_ = new java.sql.Date(invoiceDueDate.getTime());
				
			}

			
        }
		catch( Exception e )
	    {
			errorLogger_.logMsg( "Exception::TimeBasedDataConverter.convert() - " + e );
	        	
	        return -1;
	    }
		
		return result;
    
    }

}
