/**
 * 
 */
package core.searchsupplier;

import java.util.List;

/**
 * 
 * 
 * All advanced search strategy objects performing search should implement this interface.
 *
 */

public interface AdvSupplierSearch 
{
	
	/**
	 * This is the primary interface of Advanced Search, that any adv search strategy object
	 * must implement. 
	 * 
	 * Input : Takes in AdvSearchOoption 
	 * Output: Returns a List of SupplierSearchResult objects.
	 * 
	 */
	public int search(  AdvSearchOption searchOptionObj, 
						List<SupplierSearchResult> supplierSearchResultList );

}
