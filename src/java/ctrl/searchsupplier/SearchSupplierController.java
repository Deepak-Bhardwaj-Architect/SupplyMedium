/**
 * 
 */
package ctrl.searchsupplier;


import core.searchsupplier.SearchOption;
import core.searchsupplier.SupplierSearchResult;
import core.searchsupplier.AdvSearchOption;
import core.searchsupplier.BasicSearchStrategy;
import core.searchsupplier.BasicSupplierSearch;
import core.searchsupplier.AdvanceSearchStrategy;
import core.searchsupplier.AdvSupplierSearch;

import java.util.List;

public class SearchSupplierController 
{	
	
	/**
	 * Method : SearchSupplierController( ) - constructor 
	 *
	 */
	public SearchSupplierController()
	{
		
	}
	
	
	
	/**
	 *  This is the entry point called from the Servlet class
	 */
	public int basicSearch( SearchOption options, 
							List<SupplierSearchResult> searchResultList )
	{
		
		// This will create the right strategy object.
		BasicSupplierSearch bs  =  BasicSearchStrategy.createObject( options ); 
		
		// createObject does not return null
		int val = bs.search( options, searchResultList ); 
		
		bs = null;
		
		return val;
	}
	
	
	
	/**
	 * 
	 */
	public int advancedSearch( AdvSearchOption advOptions, 
							   List<SupplierSearchResult> searchResultList )
	{
		// This will create the right strategy object.
		AdvSupplierSearch as  =  AdvanceSearchStrategy.createObject( advOptions ); 
		
		int val =  as.search( advOptions, searchResultList );
		
		as = null;
		
		return val;
		
	}

}
