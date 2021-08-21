/**
 * 
 */

package core.searchsupplier;


import java.util.List;


/**
 * This interface is the Basic Search interface for the "Search Suppliers" feature. 
 * This takes in the SearchOption object and gives the result back as a List< > of 
 * SupplierSearchResult objects.
 * 
 * All BasicSearch Strategy objects must implement this interface.
 *
 */

public interface BasicSupplierSearch
{
	
	/**
	 * This is the primary interface of Basic Search.
	 * 
	 * Input : Takes in BasicSearchOoption 
	 * Output: Returns a List of SupplierSearchResult objects.
	 * 
	 */
	
	public int search(  SearchOption searchOptionObj, 
						List<SupplierSearchResult> searchResultList );
	
	
}
