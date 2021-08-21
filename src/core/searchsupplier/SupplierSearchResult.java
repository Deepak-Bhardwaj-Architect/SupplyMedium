
package core.searchsupplier;

import core.regn.CompanyRegnKey;


/**
 * This is the core class for the search supplier result data structure.
 */
public class SupplierSearchResult 
{
	
	public CompanyRegnKey 	regnKey_;
	
	public String 			companyName_;
	
	public Boolean 			isRegn_;
	
	public float 			avg_ratings_;
	
	public long	            no_of_ratings_;
	
	
	/**
	 * Method 	: clear( ) 
	 * Input 	: None 
	 * Return 	: None
	 * 
	 * Purpose	: To reset the class variables.
	 */
	public  	SupplierSearchResult( )
	{
		regnKey_ 		= null;
		companyName_ 	= null;
		isRegn_			= false;
		avg_ratings_ 		= 0.0f;
		no_of_ratings_      = 0;
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
		regnKey_.show();
		System.out.println( "Company Name	= " + companyName_ );
		System.out.println( "Is Registered ? : " + isRegn_.toString( ) );
		System.out.println( "avg_ratings_ ="+  avg_ratings_);
		System.out.println( "no_of_ratings_ ="+  no_of_ratings_);
		
	}
	
	
	/**
	 * Method 	: clear( ) 
	 * Input 	: None 
	 * Return 	: None
	 * 
	 * Purpose	: To reset the class variables.
	 */
	public void clear( )
	{
		regnKey_ 		= null;
		companyName_ 	= null;
		isRegn_			= true;
		avg_ratings_ 		= 0.0f;
		no_of_ratings_     = 0;
	}
	
	
	
}


