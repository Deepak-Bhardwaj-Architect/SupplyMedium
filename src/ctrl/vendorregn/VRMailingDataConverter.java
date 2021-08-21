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
package ctrl.vendorregn;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;


/**
 * File:  VRMailingDataConverter.java 
 *
 * Created on Oct 30, 2013 12:33:17 PM
 */
public class VRMailingDataConverter
{
	ErrorLogger errorLogger_;
	
	/*
	 * Method : VRMailingDataConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public VRMailingDataConverter()
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	
	/*
	 * Method : convert 
	 * 
	 * Input  : HttpServletRequest object, MailingAddressData object
	 * 
	 * Return : int - success/fail
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to MailingAddressData object.
	 * And copied to MailingAddressData parameter so it available in caller classes.
	 * 
	 */

    public int convert( HttpServletRequest request, MailingAddressData mailingAddressData )
    {
    	int result = 0;
		
		try
        {
			
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			/* Notification details */
	
			if( reqMap.containsKey( "addrid" ) )
			{
				mailingAddressData.addrid_ = Integer.parseInt( request.getParameter( "addrid" ) );
			}
			
			
			if( reqMap.containsKey( "companyRegnKey" ) )
			{
				CompanyRegnKey regnKey = new CompanyRegnKey( );
				
				regnKey.companyPhoneNo_ = request.getParameter( "companyRegnKey" );
				
				mailingAddressData.companyRegnKey_ = regnKey;
				
				
			}
			
			
			if( reqMap.containsKey( "emailid" ) )
			{
				mailingAddressData.emailid_=    request.getParameter( "emailid"  );
				
			}
			
			if( reqMap.containsKey( "address" ) )
			{
				mailingAddressData.address_= request.getParameter( "address" );
			}
			
			if( reqMap.containsKey( "city" ) )
			{
				mailingAddressData.city_ = request.getParameter( "city" );
			}
			
			if( reqMap.containsKey( "state" ) )
			{
				mailingAddressData.state_ = request.getParameter( "state" );
			}
			
			if( reqMap.containsKey( "zipcode" ) )
			{
				mailingAddressData.zipcode_= request.getParameter( "zipcode" );
			}
			
			if( reqMap.containsKey( "countryRegion" ) )
			{
				mailingAddressData.countryRegion_= request.getParameter( "countryRegion" );
			}
			
			
			if( reqMap.containsKey( "addressType" ) )
			{
				mailingAddressData.addressType_= request.getParameter( "addressType" );
			}
			
			
			
        }
		catch( Exception e )
	    {
			errorLogger_.logMsg( "Exception::VRMailingDataConverter.convert() - " + e );
	        	
	        return -1;
	    }
		
		return result;
    }

}
