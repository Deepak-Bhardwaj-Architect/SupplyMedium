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

import core.dashboard.CustomerBasedData;

/**
 * File:  CustomerBasedDataConverter.java 
 *
 * Created on Oct 29, 2013 6:29:05 PM
 */
public class CustomerBasedDataConverter
{
	
	ErrorLogger errorLogger_;
	
	/*
	 * Method : CustomerBasedDataConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	
	public CustomerBasedDataConverter()
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	
	
	/*
	 * Method : convert 
	 * 
	 * Input  : HttpServletRequest object, CustomerBasedData object
	 * 
	 * Return : int - success/fail
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to CustomerBasedData object.
	 * And copied to CustomerBasedData parameter so it available in caller classes.
	 * 
	 */
    public int convert( HttpServletRequest request, CustomerBasedData customerBasedData )
    {
    	int result = 0;
		
		try
        {
			
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			/* TimeBasedReport details */
			
			if( reqMap.containsKey( "RegnKey" ) )
			{
				customerBasedData.regnKey_.companyPhoneNo_ = request.getParameter( "RegnKey" );
			}
			
			if( reqMap.containsKey( "RequestType" ) )
			{
				customerBasedData.reportType_ = request.getParameter( "RequestType" );
				
				if( customerBasedData.reportType_.equals( "Period" ))
				{
					if( reqMap.containsKey( "FromDate" ) )
					{
						
						DateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy" ); 
						
						String invoiceDueDateStr = request.getParameter( "FromDate" );;
						
						java.util.Date invoiceDueDate = ( java.util.Date )formatter.parse( invoiceDueDateStr );
						
						customerBasedData.fromDate_ = new java.sql.Date(invoiceDueDate.getTime());
						
					}
					if( reqMap.containsKey( "ToDate" ) )
					{
						DateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy" ); 
						
						String invoiceDueDateStr = request.getParameter( "ToDate" );;
						
						java.util.Date invoiceDueDate = ( java.util.Date )formatter.parse( invoiceDueDateStr );
						
						customerBasedData.toDate_ = new java.sql.Date(invoiceDueDate.getTime());
						
					}
				}
			}
			
			if( reqMap.containsKey( "FilterDate" ) )
			{
				DateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy" ); 
				
				String invoiceDueDateStr = request.getParameter( "FilterDate" );;
				
				java.util.Date invoiceDueDate = ( java.util.Date )formatter.parse( invoiceDueDateStr );
				
				customerBasedData.filterDate_ = new java.sql.Date(invoiceDueDate.getTime());
				
			}
			
			
			
			
			
        }
		catch( Exception e )
	    {
			errorLogger_.logMsg( "Exception::customerBasedDataConverter.convert() - " + e );
	        	
	        return -1;
	    }
		
		return result;
    
    }
	    
}


