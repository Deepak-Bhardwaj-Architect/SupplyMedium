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

import javax.servlet.http.HttpServletRequest;

import core.regn.CompanyRegnKey;
import core.vendorregn.NRVendorSearchData;
import core.vendorregn.VendorRegnData;
import core.vendorregn.VendorRegnSM;

import utils.ErrorLogger;
import utils.VendorRegnStatus;

/**
 * File:  VendorRegnDataConverter.java 
 *
 * Created on May 23, 2013 2:54:55 PM
 */

/*
 * Class: VendorRegnDataConverter
 * 
 * Purpose: This method is used to convert HttpServletRequest object
 * into various domain objects
 */

public class VendorRegnDataConverter
{
	ErrorLogger errLogger_;
	
	/*Constructor*/
	public VendorRegnDataConverter( )
	{
		
	}
	
	/*
	 * Method: getRegnKey
	 * 
	 * Input: HttpServletRequest object, CompanyRegnKey regnKey
	 * 
	 * Return: int
	 * 
	 * Purpose: This method parse the HttpServletRequest and gets the
	 * CompanyRegnKey
	 */
	
	public int getRegnKey( HttpServletRequest request, CompanyRegnKey regnKey )
	{
		try
        {
			String companyPhoneNo = request.getParameter( "RegnKey" );
			if( companyPhoneNo != null)
			{
				regnKey.companyPhoneNo_ = companyPhoneNo;
				return 0;
			}
			return -1;
        }
        catch( Exception e )
        {
	        errLogger_.logMsg( "Exception::VendorRegnDataConverter.getRegnKey() - " + e );
	        return -2;
        }
	}
	
	/*
	 * Method: getNRVendorRegData
	 * 
	 * Input: HttpServletRequest object, CompanyRegnKey regnKey
	 * 
	 * Return: int
	 * 
	 * Purpose: This method parse the HttpServletRequest and gets the
	 * CompanyRegnKey
	 */
	
	public int getNRVendorSearchData( HttpServletRequest request, NRVendorSearchData nrVendorSrchData )
	{
		try
        {
			String companyPhoneNo = request.getParameter( "RegnKey" );
			
			nrVendorSrchData.regnKey_.companyPhoneNo_ 	= companyPhoneNo;
			nrVendorSrchData.requestSendorType_			= request.getParameter( "RequestSenderType" );
			nrVendorSrchData.searchStr_					= request.getParameter( "SearchStr" );
			
			return 0;
        }
        catch( Exception e )
        {
	        errLogger_.logMsg( "Exception::VendorRegnDataConverter.getRegnKey() - " + e );
	        return -2;
        }
	}
	
	/*
	 * Method: getRegnKey
	 * 
	 * Input: HttpServletRequest object, CompanyRegnKey regnKey
	 * 
	 * Return: int
	 * 
	 * Purpose: This method parse the HttpServletRequest and gets the
	 * CompanyRegnKey
	 */
	
	public int getVendorRegnKey( HttpServletRequest request, CompanyRegnKey vendorRegnKey )
	{
		try
        {
			String companyPhoneNo = request.getParameter( "VendorRegnKey" );
			if( companyPhoneNo != null)
			{
				vendorRegnKey.companyPhoneNo_ = companyPhoneNo;
				return 0;
			}
			return -1;
        }
        catch( Exception e )
        {
	        errLogger_.logMsg( "Exception::VendorRegnDataConverter.getVendorRegnKey() - " + e );
	        return -2;
        }
	}
	
	
	/*
	 * Method: getVendorRegnData
	 * 
	 * Input: HttpServletRequest object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method parse the HttpServletRequest and gets the
	 * VendorRegnData 
	 */
	
	public int getVendorRegnData( HttpServletRequest request, VendorRegnData vendorRegnData )
	{
		String requestType = request.getParameter( "RequestType" );
		try
        {	
			vendorRegnData.regnKey_.companyPhoneNo_	= request.getParameter( "RegnKey" );
			
			vendorRegnData.requestSenderType_ = request.getParameter( "RequestSenderType" );
			
			if( requestType.equals( "AddVendor" ) )
			{
				vendorRegnData.vendorRegnKey_.companyPhoneNo_ = request.getParameter( "VendorKey" );
				
				vendorRegnData.additionalDetails_ 		= request.getParameter( "AdditionalDetails" );
				vendorRegnData.businessClassification_ 	= request.getParameter( "BusinessClassification" );

				vendorRegnData.businessContact_ = request.getParameter( "BusinessContact" );

				vendorRegnData.businessSize_ 	= request.getParameter( "BusinessSize" );
				vendorRegnData.businessTaxId_ 	= request.getParameter( "BusinessTaxid" );
				vendorRegnData.companyLevel_ 	= request.getParameter( "CompanyLevel" );
				
				vendorRegnData.NAICSCode_ 		= request.getParameter( "NAICSCode" );
				
				VendorRegnSM sm = new VendorRegnSM( );
				
				vendorRegnData.regnStatus_ 		= sm.getNextState( request.getParameter( "Action" ) );

				vendorRegnData.w9TaxFormFlag_	= Integer.valueOf( request.getParameter( "W9TaxFormFlag" ) );
				vendorRegnData.w9TaxFormPath_ 	= request.getParameter( "W9TaxFormPath" );
				
				vendorRegnData.w9TaxForm_       = request.getAttribute( "w9Form" );
			}
			else if( requestType.equals( "UpdateStatus" ))
			{
				VendorRegnSM sm = new VendorRegnSM( );
				
				//Here, action will be 'pending', 'accept', 'reject', 'inquire'
				vendorRegnData.regnStatus_	= sm.getNextState( request.getParameter( "Action" ) );
				
				vendorRegnData.vendorRegnKey_.companyPhoneNo_ = request.getParameter( "VendorKey" );
				
				sm = null;
			}
			else if( requestType.equals( "Update" ))
			{
				
				vendorRegnData.vendorRegnKey_.companyPhoneNo_ = request.getParameter( "VendorKey" );
				
				vendorRegnData.additionalDetails_ 		= request.getParameter( "AdditionalDetails" );
				
				vendorRegnData.businessClassification_ 	= request.getParameter( "BusinessClassification" );
				
				vendorRegnData.businessContact_ = request.getParameter( "BusinessContact" );
				
				vendorRegnData.businessSize_ 	= request.getParameter( "BusinessSize" );
				
				vendorRegnData.businessTaxId_ 	= request.getParameter( "BusinessTaxid" );
				
				vendorRegnData.companyLevel_ 	= request.getParameter( "CompanyLevel" );
				
				vendorRegnData.NAICSCode_ 		= request.getParameter( "NAICSCode" );
				
				//vendorRegnData.regnStatus_ 		= VendorRegnStatus.VRStatus.PENDING.getValue( );
				
				VendorRegnSM sm = new VendorRegnSM( );
				
				//Here, action will be 'pending', 'accept', 'reject', 'inquire'
				vendorRegnData.regnStatus_	= sm.getNextState( request.getParameter( "Action" ) );
				
				vendorRegnData.w9TaxFormFlag_	= Integer.valueOf( request.getParameter( "W9TaxFormFlag" ) );
				
				vendorRegnData.w9TaxFormPath_ 	= request.getParameter( "W9TaxFormPath" );
				
				vendorRegnData.w9TaxForm_       = request.getAttribute( "w9Form" );
				
				System.out.println( "w9form="+vendorRegnData.w9TaxForm_ );
				
				vendorRegnData.vendorRegnId_ = Integer.parseInt( request.getParameter( "VendorRegnId" ) );
				
			}
			else if( requestType.equals( "AddInquiry" ) )
			{
				vendorRegnData.vendorRegnKey_.companyPhoneNo_ = request.getParameter( "VendorKey" );
				//vendorRegnData.vendorRegnId_ = Long.valueOf( request.getParameter( "VendorRegnId" ) );
				vendorRegnData.vendorInquireData.regnKey_.companyPhoneNo_		= request.getParameter( "RegnKey" );
				vendorRegnData.vendorInquireData.vendorRegnKey_.companyPhoneNo_	= request.getParameter( "VendorKey" );
				vendorRegnData.vendorInquireData.inquireDetails_	= request.getParameter( "InquireDetails" );
				vendorRegnData.vendorInquireData.vendorRegnId_	= Long.valueOf( request.getParameter( "VendorRegnId" ) );
				
				VendorRegnSM sm = new VendorRegnSM( );
				vendorRegnData.regnStatus_	= sm.getNextState( request.getParameter( "Action" ) );
				sm = null;
			}
			
			return 0;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::VendorRegnDataConverter.getVendorRegnData() - " + e );
   	        return -2;
        }
	}
		
}

