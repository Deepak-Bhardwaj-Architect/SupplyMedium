/**
 * 
 */
package core.searchsupplier;

import core.regn.CompanyRegnKey;
import utils.ErrorMaster;

public class SearchOption 
{
	
	public String 				category_;
	public String 				keyword_;
	public Boolean 				searchRegd_;
	public CompanyRegnKey 		buyerKey_;
        
        private static ErrorMaster errorMaster_ = null;


	
	
	
	
	/**
	 * 
	 */
    public SearchOption()
    {
	    // TODO Auto-generated constructor stub
    	searchRegd_ = false;
        if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( "category_		= " + category_ );
		errorMaster_.insert( "keyword_		= " + keyword_  );
		errorMaster_.insert( "searchRegd_	= " + searchRegd_ );
		errorMaster_.insert( "buyerKey_		= " + buyerKey_ );
	}
	
	
}
