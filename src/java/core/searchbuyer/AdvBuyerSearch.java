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
 * File:  BuyerAdvSupplierSearch.java 
 *
 * Created on Jul 19, 2013 7:08:47 PM
 */

/**
 * 
 * 
 * All buyer advanced search strategy objects performing search should implement this interface.
 *
 */

public interface AdvBuyerSearch
{
	
	/**
	 * This is the primary interface of Advanced Buyer Search, that any adv search strategy object
	 * must implement. 
	 * 
	 * Input : Takes in BuyerAdvSearchOoption 
	 * Output: Returns a List of AdvanceSearchResult objects.
	 * 
	 */

	public int search(  BuyerAdvSearchOption searchOptionObj, 
			List<BuyerSearchResult> searchResultList );
	
}
