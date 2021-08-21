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
package ctrl.searchbuyer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import core.searchbuyer.BuyerAdvSearchOption;
import core.searchbuyer.BuyerSearchOption;
import core.searchbuyer.BuyerSearchResult;

/**
 * File:  SearchBuyerReqController.java 
 *
 * Created on Jul 22, 2013 10:21:54 AM
 */
public class SearchBuyerReqController
{
	/*
	 * Method : SearchBuyerReqController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public SearchBuyerReqController( )
	{
		
	}
	
	
	/*
	 * Method : processRequest()
	 * 
	 * Input  : HttpServletRequest and List of searchResultList
	 * 
	 * Return : int 
	 * 
	 * Purpose: It is convert the request data object to BuyerSearchOption object
	 * and using the SearchBuyerController it get back the buyer list
	 * according to given search condition 
	 */
	
	public int processRequest( HttpServletRequest request, 
			List<BuyerSearchResult> searchResultList )
	{
		SearchBuyerDataConverter dataConverter = new SearchBuyerDataConverter( );

		SearchBuyerController searchBuyerCtrl = new SearchBuyerController( );
		
		int result = 0;
		
		// This is for the basic search
		if( request.getParameter( "SearchType" ).equals( "BasicSearch" ))
		{
			BuyerSearchOption searchOption = new BuyerSearchOption( );
			
			result = dataConverter.convert( request, searchOption );
			
			if( result == 0 )
			{
				result = searchBuyerCtrl.basicSearch( searchOption, searchResultList );
			}
		}
		else // Advance search
		{
			BuyerAdvSearchOption advSearchOption = new BuyerAdvSearchOption( );
			
			result = dataConverter.convert( request, advSearchOption );

			if( result == 0 )
			{
				result =searchBuyerCtrl.advancedSearch( advSearchOption, searchResultList );
			}
		}
			
		searchBuyerCtrl = null;
			
		dataConverter = null;
		
		System.gc( );
		
		return result;
	}
}
