/**
 * 
 */
package core.common;

import utils.ErrorMaster;

public class QuanTypeKey 
{
	public String quanTypeKey_;
        
        private static ErrorMaster errorMaster_ = null;


	
	/**
	 * 
	 */
	public void clear( )
	{
		quanTypeKey_ = null;
	}
	
	/**
	 * 
	 */
	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "quanTypeKey_	= " + quanTypeKey_ );
	}
}
