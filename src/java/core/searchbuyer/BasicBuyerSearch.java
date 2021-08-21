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
package core.searchbuyer;

import java.util.List;


/**
 * File:  BuyerBasicSupplierSearch.java 
 *
 * Created on Jul 19, 2013 7:09:59 PM
 */

/**
 * This interface is the Basic Search interface for the "Search Buyers" feature. 
 * This takes in the BuyerSearchOption object and gives the result back as a List< > of 
 * BuyerSearchResult objects.
 * 
 * All BuyerBasicSearch Strategy objects must implement this interface.
 *
 */

public interface BasicBuyerSearch 
{
	
	/**
	 * This is the primary interface of Buyer Basic Search.
	 * 
	 * Input : Takes in BuyerSearchOption 
	 * Output: Returns a List of BuyerSearchResult objects.
	 * 
	 */
	
	public int search( BuyerSearchOption searchOptionObj, 
			List<BuyerSearchResult> searchResultList );

}
