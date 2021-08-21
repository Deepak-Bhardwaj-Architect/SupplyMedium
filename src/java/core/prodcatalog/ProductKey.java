package core.prodcatalog;

import utils.ErrorMaster;

/**
 * This is the core class for the product key which 
 * used for define a key for a product catalog (company registration key and part number)
 */
public class ProductKey 
{
	public String productKey_;
        private static ErrorMaster errorMaster_ = null;


	
	/**
	 * Method 	: clear( ) 
	 * Input 	: None 
	 * Return 	: None
	 * 
	 * Purpose	: To reset the class variables.
	 */
	public void clear( )
	{
		productKey_ = null;
	}
	
	/**
	 * Method : show( ) 
	 * Input  : None 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */
	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "productKey_	= " + productKey_ );
	}

}
