/**
 * 
 */
package core.searchsupplier;

import core.regn.CompanyRegnKey;

public class SearchOption 
{
	
	public String 				category_;
	public String 				keyword_;
	public Boolean 				searchRegd_;
	public CompanyRegnKey 		buyerKey_;
	
	
	
	
	/**
	 * 
	 */
    public SearchOption()
    {
	    // TODO Auto-generated constructor stub
    	searchRegd_ = false;
    }


	/**
	 * 
	 */
	public void clear( )
	{
		category_ 	= null;
		keyword_ 	= null;
		searchRegd_ = null;
		buyerKey_ 	= null;
	}
	
	
	/**
	 * 
	 */
	public void show( )
	{
		System.out.println( "category_		= " + category_ );
		System.out.println( "keyword_		= " + keyword_  );
		System.out.println( "searchRegd_	= " + searchRegd_ );
		System.out.println( "buyerKey_		= " + buyerKey_ );
	}
	
	
}
