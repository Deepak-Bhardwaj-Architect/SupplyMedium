package core.prodcatalog;
/**
 * This is the core class for the product key which 
 * used for define a key for a product catalog (company registration key and part number)
 */
public class ProductKey 
{
	public String productKey_;
	
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
		System.out.println( "productKey_	= " + productKey_ );
	}

}
