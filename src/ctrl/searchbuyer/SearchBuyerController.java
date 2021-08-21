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

import core.searchbuyer.AdvBuyerSearch;
import core.searchbuyer.BasicBuyerSearch;
import core.searchbuyer.BuyerAdvSearchOption;
import core.searchbuyer.BuyerAdvSearchStrategy;
import core.searchbuyer.BuyerBasicSearchStrategy;
import core.searchbuyer.BuyerSearchOption;
import core.searchbuyer.BuyerSearchResult;

/**
 * File:  SearchBuyerController.java 
 *
 * Created on Jul 22, 2013 10:21:29 AM
 */
public class SearchBuyerController
{
	/**
	 * Method : SearchBuyerController( ) - constructor 
	 *
	 */
	public SearchBuyerController()
	{
		
	}
	
	/**
	 *  This is the entry point called from the Servlet class
	 */
	public int basicSearch( BuyerSearchOption options, 
							List<BuyerSearchResult> searchResultList )
	{
		
		// This will create the right strategy object.
		BasicBuyerSearch bs  =  BuyerBasicSearchStrategy.createObject( options ); 
		
		// createObject does not return null
		int val = bs.search( options, searchResultList ); 
		
		bs = null;
		
		return val;
	}
	
	
	
	/**
	 * 
	 */
	public int advancedSearch( BuyerAdvSearchOption advOptions, 
							   List<BuyerSearchResult> searchResultList )
	{
		// This will create the right strategy object.
		AdvBuyerSearch as  =  BuyerAdvSearchStrategy.createObject( advOptions ); 
		
		int val =  as.search( advOptions, searchResultList );
		
		as = null;
		
		return val;
		
	}
}
