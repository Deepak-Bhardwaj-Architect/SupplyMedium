/**
 * 
 */
package core.common;

import utils.ErrorMaster;

public class CurrencyKey 
{
	public String currencyKey_;
        
        private static ErrorMaster errorMaster_ = null;
	
	/**
	 * 
	 */
	public void clear( )
	{
		currencyKey_ = null;
	}
	
	/**
	 * 
	 */
	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "currencyKey_	= " + currencyKey_ );
	}
}
