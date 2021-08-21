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

package ctrl.regn;

import utils.ErrorLogger;


import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.regn.RegnProcess;
import core.regn.RegnValidation;
import javax.servlet.http.HttpServletRequest;

import utils.StringHolder;

import core.regn.RegnFactory;


/**
 * File:  RegnController.java 
 *
 * Created on Feb 6, 2013 2:39:16 PM
 */

/*
 * This is the controller class for company registration process. It get the 
 * call from servlet and forward that call to corresponding core classes.
 */

public class RegnController
{
	private ErrorLogger errLogger_ ;
	
	/*
	 * Method : EntityLoaderController( ) - constructor
	 * 
	 * Input :none
	 * 
	 * Return :none
	 * 
	 * Purpose: It is used intialize the ErrorLogger object
	 */
	
	public RegnController()
	{
		
		errLogger_  = ErrorLogger.instance();
	}
	

	/*
	 * Method : processRequest( ) Input : HttpServletRequest object Return :
	 * String - success or failed message
	 * 
	 * Purpose: It is used to get the HttpServletrequest object as a parameter.
	 * Then parse the request object to company registration data object. From
	 * the requesttype parameter it decide what type of request is. And do the
	 * cooresponding operation using RegnFactory and RegnProcess classes.
	 */

	public int processRequest( HttpServletRequest request )
	{
		RegnData regnDataObj = new RegnData();
		
		RegnDataConverter regnDataConverter = new RegnDataConverter();
		
		// Convert to RegnData object from HttpServletRequest
		int parseResponse = regnDataConverter.convert(request, regnDataObj);
		
		if( parseResponse != 0 )
		{
			String errMsg = "Error::RegnController.processRequest() - Can't parse the " +
					"Company Registration request ";
			
			errLogger_.logMsg(errMsg);
			
			regnDataObj  = null;
			
			return 101;  // Company registration failed.
		}

		//regnDataObj.show( );
		
		regnDataConverter = null;
		
		String requestType = request.getParameter( "RequestType" );
				
		return processRegnReq( regnDataObj, requestType );

	}

	/*
	 * Method: processRegnReq
	 * 
	 * Input:
	 * 
	 * Return:
	 * 
	 * Purpose: This is the helper for processRequest method
	 * 
	 */
	
	public int processRegnReq( RegnData regnDataObj, String requestType )
	{
		RegnFactory regnFactoryObj = new RegnFactory( );

		RegnProcess regnProcessObj = regnFactoryObj.createRegnObj( requestType );
		
		regnFactoryObj = null;
		
		if( regnProcessObj == null)
		{
			String errMsg = "Error::RegnController.processRequest( ) null RegnProcessObj for company <" + 
                    regnDataObj.companyName_ +  ", " + regnDataObj.companyRegnKey_ + ">";
			
			errLogger_.logMsg(errMsg);
			
			return 101;  // Company registration failed
		}

		
		try
		{
			int regnResult = 0;
			
			//regnDataObj.show( );

			String errMsg = "Info::RegnController.processRequest() - Request In Process " +
	                "<" + regnDataObj.companyName_ + ">, <" + regnDataObj.companyRegnKey_.toString( ) + ">";
			
			errLogger_.logMsg( errMsg );
			
			regnResult = regnProcessObj.process( regnDataObj );
			
			regnProcessObj = null;
			
			regnDataObj = null;

			return regnResult;
		}
		catch( Exception ex )
		{

			errLogger_.logMsg( "Exception::RegnController.processRequest() - " + ex );
			
			regnProcessObj = null;
			
			regnDataObj = null;

			return 101;  // Company registration failed
		}
	}

	/*
	 * Method : validate( ) 
	 * 
	 * Input : email address and company name
	 * 
	 * Return : int
	 * 
	 * Purpose: It check whether email address is valid or not.
	 */

	public int validate( String email, String companyName )
	{
		
		int result = 0;

		try
		{
			RegnValidation regnValidationObj = new RegnValidation( );
			
			result = regnValidationObj.validate( email,companyName );
			
			regnValidationObj  = null;

			return result;
		}
		catch( Exception ex )
		{
			errLogger_.logMsg( "Exception::RegnController.validate() - " + ex );

			return result;
		}
	}
	
	/*
	 * Method : isPhoneNoExist( ) 
	 * 
	 * Input : phone number 
	 * 
	 * Return : boolean
	 * 
	 * Purpose: It check whether phone numbers already exist in supply medium or
	 * not. If exist it return true otherwise return false
	 */

	public boolean isPhoneNoExist( String phoneNo )
	{

		boolean result = false;

		try
		{
			RegnValidation regnValidationObj = new RegnValidation( );
			
			result = regnValidationObj.isPhoneNoExist( phoneNo );
			
			regnValidationObj = null;

			return result;
		}
		catch( Exception ex )
		{
			errLogger_.logMsg( "Exception::RegnController.isPhoneNoExist() - " + ex );

			return result;
		}

	}

	/*
	 * Method : isMemberAllowed( ) 
	 * 
	 * Input : string company registration key
	 * 
	 * 
	 * Return : boolean
	 * 
	 * Purpose: To check whether max user for this company exists. This max user
	 * limit depending to company pricing option. If not return true. (user can
	 * add new members for this company) Otherwise return false.
	 */

	public boolean isMemberAllowed( String companyKey )
	{
		try
		{

			CompanyRegnKey companyRegnKey = new CompanyRegnKey( );

			companyRegnKey.companyPhoneNo_ = companyKey;

			int result = 0;

			RegnValidation regnValidationObj = new RegnValidation( );
			
			result = regnValidationObj.isMemberAllowed( companyRegnKey );
			
			regnValidationObj = null;
			

			if( result != 0 ) // company can add users
			{
				companyRegnKey = null;
				
				return true;
			}

			return false;  // company cant add any more user
		}
		catch( Exception ex )
		{

			errLogger_.logMsg( "Exception::RegnController.isMemberAllowed() - " + ex );

			return false;
		}

	}

	/*
	 * Method : getCompanyType( ) 
	 * 
	 * Input : string company registration key 
	 * 
	 * Return : int value specified company type
	 * 
	 * Purpose: It get the company type for given company registration key. If
	 * company is buyer return 1 If company is Supplier return 2 If company is
	 * both return 3 Other wise return -1 for error
	 */

	public int getCompanyType( String companyKey )
	{
		
		try
		{
			RegnValidation regnValidationObj = new RegnValidation( );

			CompanyRegnKey companyRegnKey = new CompanyRegnKey( );

			companyRegnKey.companyPhoneNo_ = companyKey;

			int result = 0;

			result = regnValidationObj.getCompanyType( companyRegnKey );
			
			regnValidationObj = null;
			
			companyKey = null;

			return result;

		}
		catch( Exception ex )
		{
			
			errLogger_.logMsg( "Exception::RegnController.getCompanyType() - " + ex );

			return -1;
		}

	}
	
	
	/*
	 * Method : getCountryCode( ) 
	 * 
	 * Input : string countryName , countryCode as StringHolder obj
	 * 
	 * Return: int value result success or failed
	 * 
	 * Purpose: It get the country code for given country name. Then
	 * assign the country code to string holder object. So it is 
	 * copied to caller class.
	 */

	public int getCountryCode( String countryName,StringHolder countryCode )
	{
		
		try
		{
			RegnValidation regnValidation = new RegnValidation( );

			int result = 0;

			result = regnValidation.getCountryCode( countryName, countryCode );
			
			regnValidation = null;

			return result;

		}
		catch( Exception ex )
		{
			
			errLogger_.logMsg( "Exception::RegnController.getCountryCode() - " + ex );

			return -1;
		}

	}

}
