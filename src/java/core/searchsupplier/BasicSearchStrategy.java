/**
 * 
 */

package core.searchsupplier;


public class BasicSearchStrategy
{
	private static BasicSupplierSearch	bss_	= null;

	/**
	 * Method : BasicSearch( ) - constructor Input : Return :
	 * 
	 * Purpose: It is used for initialization
	 */
	private BasicSearchStrategy( )
	{
	}

	/**
	 * 
	 */
	public static BasicSupplierSearch createObject( SearchOption searchOptionObj )
	{

		// search only in Registered suppliers
		if( searchOptionObj.searchRegd_ )
		{
			bss_ = new BasicRegnSearch( );
			return bss_;
			
		}
		// search in All suppliers 
		else
		{
			bss_ = new BasicSearch( );
			return bss_;
			
		}
		
	}
	
}
