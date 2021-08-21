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
package ctrl.transtc;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.transtc.TransTcUploadData;

/**
 * File:  TransTcUploadConverter.java 
 *
 * Created on Oct 19, 2013 12:23:47 PM
 */
public class TransTcUploadConverter
{
	
ErrorLogger errorLogger_;
	
	/*
	 * Method : TransTcUploadConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public TransTcUploadConverter()
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	
	
	
	
	/*
	 * Method : convert 
	 * 
	 * Input  : HttpServletRequest object, TransTcUploadData object
	 * 
	 * Return : int - success/fail
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to TransTcUploadData object.
	 * And copied to transTcUploadData parameter so it available in caller classes.
	 * 
	 */
	

    public int convert( HttpServletRequest request, TransTcUploadData transTcUploadData )
    {
	   int result=0;
	   
	   try
       {
			
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			/* Notification details */
			
			
			if( reqMap.containsKey( "TransTcId" ) )
			{
				transTcUploadData.transTcId_ = Integer.parseInt( request.getParameter( "TransTcId" ) );
			
			}
			
			
			
			if( reqMap.containsKey( "CompanyKey" ) )
			{
				transTcUploadData.regnKey_.companyPhoneNo_= request.getParameter( "CompanyKey" );
			}
			
			
			if( reqMap.containsKey( "TransType" ) )
			{
				transTcUploadData.transType_ = request.getParameter( "TransType" );
			}
			
			
			if( reqMap.containsKey( "Tc" ) )
			{
				transTcUploadData.tc_=  request.getParameter( "Tc" );
			}
			
			
			if( reqMap.containsKey( "Timestamp" ) )
			{
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy ',' HH:mm:ss");
				java.util.Date date = sdf.parse(request.getParameter( "Timestamp" ));
				java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
				
				transTcUploadData.createdTimestamp_ = timestamp;
			}
			

			
			
       }
		catch( Exception e )
	    {
			errorLogger_.logMsg( "Exception::TransTcUploadConverter.convert() - " + e );
	        	
	        return -1;
	    }
		
		return result;
   }
}
