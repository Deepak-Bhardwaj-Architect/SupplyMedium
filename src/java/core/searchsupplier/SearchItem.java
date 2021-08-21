/**
 * 
 */

package core.searchsupplier;

import utils.ErrorMaster;

public class SearchItem 
{
	public String partno_;
	public String name_;
        private static ErrorMaster errorMaster_ = null;


	
	/**
	 * 
	 */
	public void clear( )
	{
		partno_ = null;
		name_ = null;
	}
	
	/**
	 * 
	 */
	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "partno_	= " + partno_ );
		errorMaster_.insert( "name_	= " + name_ );
	}

}
