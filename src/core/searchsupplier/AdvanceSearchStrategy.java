/**
 * 
 */

package core.searchsupplier;

public class AdvanceSearchStrategy 
{
	private static AdvSupplierSearch ass_ = null;
	
	/**
	 * Method : AdvanceSearchStrategy( ) - constructor 
	 * Input  :  
	 * Return :  
	 * 
	 * Purpose: It is used for initialization
	 */
	private AdvanceSearchStrategy()
	{
	}
	
	/**
	 * 
	 */
	public static AdvSupplierSearch createObject( AdvSearchOption searchOptionObj )
	{		
		
		// search only in Registered buyers
		if( searchOptionObj.searchRegd_ )
		{
			ass_ = new AdvRegnSearch( );
			return ass_;
			
		}
		// search in All Buyers 
		else
		{
			ass_ = new AdvSearch( );
			return ass_;
			
		}
		
	}
	
	
}
