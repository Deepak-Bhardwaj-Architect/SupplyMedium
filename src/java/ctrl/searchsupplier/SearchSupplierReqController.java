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
package ctrl.searchsupplier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import core.searchsupplier.AdvSearchOption;
import core.searchsupplier.SearchOption;
import core.searchsupplier.SupplierSearchResult;

/**
 * File:  SearchSupplierReqController.java 
 *
 * Created on 10-May-2013 11:20:55 AM
 */
public class SearchSupplierReqController
{

	/*
	 * Method : SearchSupplierReqController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public SearchSupplierReqController()
	{
		
	}
	
	
	/*
	 * Method : processRequest()
	 * 
	 * Input  : HttpServletRequest and List of searchResultList
	 * 
	 * Return : int 
	 * 
	 * Purpose: It is convert the request data object to SearchOption object
	 * and using the SearchSupplierController it get back the supplier list
	 * according to given search condition 
	 */
	
	public int processRequest( HttpServletRequest request, 
			List<SupplierSearchResult> searchResultList )
	{
		SearchSupplierDataConverter dataConverter = new SearchSupplierDataConverter( );

		SearchSupplierController searchSupplierCtrl = new SearchSupplierController( );
		
		int result = 0;
		
		// This is for the basic search
		if( request.getParameter( "SearchType" ).equals( "BasicSearch" ))
		{
			SearchOption searchOption = new SearchOption( );
			
			result = dataConverter.convert( request, searchOption );
			
			if( result == 0 )
			{
				result = searchSupplierCtrl.basicSearch( searchOption, searchResultList );
			}
		}
		else // Advance search
		{
			AdvSearchOption advSearchOption = new AdvSearchOption( );
			
			result = dataConverter.convert( request, advSearchOption );

			if( result == 0 )
			{
				result =searchSupplierCtrl.advancedSearch( advSearchOption, searchResultList );
			}
		}
			
		searchSupplierCtrl = null;
			
		dataConverter = null;
		
		System.gc( );
		
		return result;
	}
}
