/**
 * 
 */
package core.searchsupplier;

import java.util.ArrayList;
import java.util.List;

import core.regn.CompanyRegnKey;

public class AdvSearchOption 
{
	
	public String 				category_;
	public String 				keyword_;
	public List <SearchItem> 	searchItemList_;
	public Boolean 				checkAll_;
	public Boolean 				searchRegd_;
	public CompanyRegnKey 		buyerKey_;
	
	
	
	
	public AdvSearchOption()
	{
		searchItemList_ = new ArrayList<SearchItem>( );
		category_ = null;
		keyword_ = null;
		keyword_ = null;
		checkAll_ = false;
		searchRegd_ = false;
		buyerKey_ = null;
		
	}
	/**
	 * 
	 */
	public void clear( )
	{
		
		category_ = null;
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
		
		System.out.println( "category_	= " 		+ category_ );
		System.out.println( "keyword_	= " 		+ keyword_ );
		System.out.println( "searchItemList_	= " + searchItemList_ );
		System.out.println( "checkAll_	= " 		+ checkAll_ );
		System.out.println( "searchRegd_	= " 	+ searchRegd_ );
		System.out.println( "buyerKey_	= " 		+ buyerKey_ );
		
	}
	
	
}
