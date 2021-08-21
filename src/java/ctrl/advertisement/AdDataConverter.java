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
package ctrl.advertisement;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.advertisement.AdData;

/**
 * File:  AdvertisementDataConverter.java 
 *
 * Created on Oct 3, 2013 12:25:43 PM
 */
public class AdDataConverter
{

	ErrorLogger errorLogger_;
	
	/*
	 * Method : AdvertisementDataConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public AdDataConverter()
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : convert 
	 * 
	 * Input  : HttpServletRequest object, AdvertisementData object
	 * 
	 * Return : int - success/fail
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to AdvertisementData object.
	 * And copied to adData parameter so it available in caller classes.
	 * 
	 */
    public int convert( HttpServletRequest request, AdData adData )
    {
    	int result = 0;
		
		try
        {
			
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			/* Advertisement details */
			
			if( reqMap.containsKey( "AdId" ) )
			{
				adData.adId_ = Integer.parseInt( request.getParameter( "AdId" ) );
			}
			
			if( reqMap.containsKey( "RegnKey" ) )
			{
				adData.regnKey_ .companyPhoneNo_= request.getParameter( "RegnKey" );
			}
			
			if( reqMap.containsKey( "UserKey" ) )
			{
				adData.userProfileKey_.email_ = request.getParameter( "UserKey" );
			}
			
			if( reqMap.containsKey( "AlternateText" ) )
			{
				adData.alternateText_=request.getParameter( "AlternateText" );
			}
			
			if( reqMap.containsKey( "Link" ) )
			{
				adData.link_=request.getParameter( "Link" );
			}
			
			if( reqMap.containsKey( "Timestamp" ) )
			{
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy ',' HH:mm:ss");
				java.util.Date date = sdf.parse(request.getParameter( "Timestamp" ));
				java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
				
				adData.createdTimestamp_ = timestamp;
			}
			
			
			if( reqMap.containsKey( "NoOfAds" ) )
			{
				adData.noOfAds_=Integer.parseInt( request.getParameter( "NoOfAds" ) ) ;
			}


			
        }
		catch( Exception e )
	    {
			errorLogger_.logMsg( "Exception::AdvertisementDataConverter.convert() - " + e );
	        	
	        return -1;
	    }
		
		return result;
    }
			

}
