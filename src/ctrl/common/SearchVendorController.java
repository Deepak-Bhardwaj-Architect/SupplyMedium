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
package ctrl.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.common.BusinessCategoryData;
import core.common.EntityLoader;
import core.common.RVendorSearchData;
import core.common.SearchVendor;
import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.vendorregn.NRVendorSearchData;

/**
 * File:  SearchVendorController.java 
 *
 * Created on 29-Jun-2013 11:36:10 AM
 */
public class SearchVendorController
{

	/*
	 * Method : SearchVendorController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public SearchVendorController()
	{
		
	}
	
	
	/*
	 * Method : search( ) 
	 * 
	 * Input : HttpServletRequest request, List<RegnData> regnDataList 
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to get the registered/non-registered vendor 
	 * for given company.
	 */

	public int search( HttpServletRequest request, List<RegnData> regnDataList  )
	{
		int result = 0;

		try
		{
			String requestType = request.getParameter( "RequestType" );
			
			String companyPhoneNo = request.getParameter( "RegnKey" );
			
			CompanyRegnKey regnKey = new CompanyRegnKey( );
			
			regnKey.companyPhoneNo_ = companyPhoneNo;
			 
			String searchStr = request.getParameter( "SearchStr" );
			
			String serderType =  request.getParameter( "RequestSenderType");
			
			if( requestType.equals( "ListRegVendors" ))
			{
				RVendorSearchData rVendorSearchData = new RVendorSearchData( );
				
				rVendorSearchData.regnKey_ = regnKey;
				
				rVendorSearchData.searchStr_ = searchStr;
				
				result = searchRegnVendor( rVendorSearchData, regnDataList );
			}
			else 
			{
				NRVendorSearchData nrVendorSearchData = new NRVendorSearchData( );
				
				nrVendorSearchData.regnKey_ = regnKey;
				
				nrVendorSearchData.searchStr_ = searchStr;
				
				nrVendorSearchData.requestSendorType_ = serderType;
				
				result = searchNonRegnVendor( nrVendorSearchData, regnDataList );
			}
			
			return result;

		}

		catch( Exception ex )
		{
			ErrorLogger errorLogger = ErrorLogger.instance( );
			
			String msg = "Exception :: SearchVendorController : search - "+ex;
			
			errorLogger.logMsg(msg);

			return 3642;  // failed
		}

	}
	
	
	/*
	 * Method : searchRegnVendor( ) 
	 * 
	 * Input : CompanyRegnKey object, List<RegnData> regnDataList 
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to get the registered vendor 
	 * for given company.
	 */

	private int searchRegnVendor( RVendorSearchData rVendorSearchData,List<RegnData> regnDataList )
	{
		int result = 0;
		
		SearchVendor searchVendor = new SearchVendor( );
		
		result = searchVendor.getRegnVendorList( rVendorSearchData, regnDataList );
		
		searchVendor = null;
	
		return result;
	}
	
	/*
	 * Method : searchNonRegnVendor( ) 
	 * 
	 * Input : CompanyRegnKey object, List<RegnData> regnDataList 
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to get the non-registered vendor 
	 * for given company.
	 */

	private int searchNonRegnVendor( NRVendorSearchData nrVendorSearchData,List<RegnData> regnDataList )
	{
		int result = 0;
		
		SearchVendor searchVendor = new SearchVendor( );
		
		result = searchVendor.getNonRegVendorList( nrVendorSearchData, regnDataList );
		
		searchVendor = null;
	
		return result;
	}
	
}
