/**
 * 
 */
package core.searchsupplier;

import java.util.ArrayList;
import java.util.List;

import core.regn.CompanyRegnKey;
import utils.ErrorMaster;

public class AdvSearchOption 
{
	
	public String 				category_;
        public String 				country_;
	public String 				keyword_;
	public List <SearchItem> 	searchItemList_;
	public Boolean 				checkAll_;
	public Boolean 				searchRegd_;
	public CompanyRegnKey 		buyerKey_;
        
        private static ErrorMaster errorMaster_ = null;


	
	
	
	
	public AdvSearchOption()
	{
		searchItemList_ = new ArrayList<SearchItem>( );
		category_ = null;
                country_=null;
		keyword_ = null;
		keyword_ = null;
		checkAll_ = false;
		searchRegd_ = false;
		buyerKey_ = null;
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		
	}
	/**
	 * 
	 */
	public void clear( )
	{
		
		category_ = null;
                country_=null;
		keyword_ = null;
		keyword_ = null;
		checkAll_ = false;
		searchRegd_ = false;
		buyerKey_ = null;
		
	}
	
	
	/**
	 * 
	 */
	public void show( )
	{
		
		errorMaster_.insert( "category_	= " 		+ category_ );
		errorMaster_.insert( "keyword_	= " 		+ keyword_ );
		errorMaster_.insert( "searchItemList_	= " + searchItemList_ );
		errorMaster_.insert( "checkAll_	= " 		+ checkAll_ );
		errorMaster_.insert( "searchRegd_	= " 	+ searchRegd_ );
		errorMaster_.insert( "buyerKey_	= " 		+ buyerKey_ );
		
	}
	
	
}
