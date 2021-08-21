/**
 * 
 */
package core.searchsupplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import core.prodcatalog.ProductCatalogData;
import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.userratings.RatingSummaryData;
import db.prodcatalog.ProductCatalogTable;
import db.regn.CompanyRegnTable;
import db.userratings.RatingsSummaryTable;
import db.vendorregn.RegnVendorMapTable;

import utils.ErrorLogger;


public class AdvSearch implements AdvSupplierSearch
{
	
	private ErrorLogger errLogger_;
	
	/**
	 * Method : AdvSearchAll( ) - constructor Input : Return :
	 * 
	 * Purpose: It is used initialization
	 */
	public AdvSearch()
	{
		this.errLogger_ = ErrorLogger.instance( );
	}
	

	
	/**
	 * 
	 */
	public int search( AdvSearchOption searchOptionObj, 
		               List<SupplierSearchResult> searchResultList )
	{
		
		CompanyRegnTable companyRegnTbl    = new CompanyRegnTable( );
		List<RegnData> companyList 		   = new ArrayList<RegnData>( );
		
		RegnData queryFilter = new RegnData( );
		
		// Create the queryFilter based on the search options, set in the
		// SearchOption object. ? In queryFilter the Category "All" case should be
		// considered.
		createFilter( searchOptionObj, queryFilter );
	      	
		Set<CompanyRegnKey> supplierCompanyKeys = new HashSet<CompanyRegnKey>( );
		
		// Retrieve the company keys for the filter.
		companyRegnTbl.searchKeys( queryFilter, supplierCompanyKeys );
	
		// If there are no keys for the given filter, just return as there is nothing to process.
		if( supplierCompanyKeys.isEmpty( ) )
		{
			companyRegnTbl = null;
			return -1;
		}
		
		List<SearchItem> searchItemList = searchOptionObj.searchItemList_;
		if( searchItemList == null ) // Invalid error !!
			return -2;
		
		
		
		/**
		 * If there are no search items in the advanced search, then just return the
		 * search results based on the supplier keys, got as a result of the queryFilter alone.
		 */
		if( searchItemList.isEmpty( ) )
		{
			
			companyRegnTbl.getCompanyList( supplierCompanyKeys, companyList );
			
			formSearchResult( companyList, searchResultList );
			
			return 0; 
			
		} 
		else // the list is not empty, i.e. there EXISTS item names/part numbers to search from.
		{
			
			Set<CompanyRegnKey> prodCatalogCompanyKeys = null;
			
			ProductCatalogData pcData = new ProductCatalogData( );
			ProductCatalogTable pcTable = new ProductCatalogTable( );
			
			boolean first = true;
			Set<CompanyRegnKey> suppliersByKeyword = new HashSet<CompanyRegnKey>( );

			for( SearchItem searchItemObj : searchItemList )
			{
				pcData = null;
				pcData = new ProductCatalogData( );
				
				
				convertToPcData( searchItemObj, pcData );
				pcTable.getCompanyKeys( pcData, suppliersByKeyword );
				
				// keep going until we get a non-empty list for any product item search.
				if( suppliersByKeyword.isEmpty( ) && first )
					continue;
				
				// if first time, create a new product catalog company keys initially, based on
				// the first set of company keys got by above query.
				if( !suppliersByKeyword.isEmpty( ) && first )
				{
					prodCatalogCompanyKeys = new HashSet<CompanyRegnKey>( suppliersByKeyword );
					first = false;
				}
				else 
				{
					
					/**
					 * Only if all products mentioned in the search are found in the same company, 
					 * add that company to the result. This is achieved using the intersection of 
					 * productCatalogCompanyKeys in multiple queries for various search items.
					 * 
					 */
					
					if( searchOptionObj.checkAll_ )
					{
						prodCatalogCompanyKeys.retainAll( suppliersByKeyword );
					}
					/**
					 * Else it is the union of all sets of companies with each query based on the
					 * product item.
					 * 
					 */
					else
					{
						prodCatalogCompanyKeys.addAll( suppliersByKeyword );
					}
					
				}
				
				suppliersByKeyword.clear( );
			
			} // end for
			
			if( prodCatalogCompanyKeys == null )
			{
				System.out.println( "pcCompanyKeys is null !!" );
				return -3;
				
			}
			
			// After complete search with all options, if we still have a non-empty set, go ahead
			// OR else return as no items found !
			if( prodCatalogCompanyKeys.isEmpty( ) )
			{
				companyRegnTbl = null;
				return -4;
			}
			
			/*
			 * Since the search results fetch all the companies matching the search criteria
			 * the company requesting for the search will be present in the search results
			 * So, from the search results, this company should be removed
			 */
			prodCatalogCompanyKeys.remove( searchOptionObj.buyerKey_ );
			
			// returning companies only that match the queryFilter as well as the 
			// product catalog items, as per the search options.
			supplierCompanyKeys.retainAll( prodCatalogCompanyKeys );
			if( supplierCompanyKeys.size( ) == 0 )
				return 0; // No supplier found
			
			companyRegnTbl.getCompanyList( supplierCompanyKeys, companyList );
			formSearchResult( companyList, searchResultList );
			
			
			// This method is used to set the vendor regn flag
			
			setRegnVendorFlag( searchOptionObj.buyerKey_, searchResultList );
			
			companyRegnTbl = null;
			queryFilter = null;
			companyList = null;
			pcData = null;
			pcTable = null;
			
			return 0; // intersection set
			
		}
		
		
	}
	
	/**
	 * This method decide the result vendor type. That is registered vendor or non registered vendor.
	 * If registered vendor it set the isRegn_ flag as true
	 * 
	 */
	private void setRegnVendorFlag( CompanyRegnKey buyerKey, List<SupplierSearchResult> searchResultList )
	{
		RegnVendorMapTable mapTable = new RegnVendorMapTable( );
		
		List<CompanyRegnKey> vendorRegnKeyList = new ArrayList<CompanyRegnKey>( );
 		
		int result = mapTable.find( buyerKey, vendorRegnKeyList );
		
		if( result != 0 )
			return;
		
		Map<CompanyRegnKey, RatingSummaryData> ratingMap = new HashMap<CompanyRegnKey, RatingSummaryData>();
		 
		RatingsSummaryTable ratingsSummaryTable = new RatingsSummaryTable( );
		
		result = ratingsSummaryTable.getAllRatingsSummaryList( ratingMap );
		
		if( result != 0 )
        {
	        return;
        }
		
		
		// Need to optimized this code, because of it process two loops
		
			for( SupplierSearchResult supplierSearchRes : searchResultList )
            {
                for( CompanyRegnKey companyRegnKey : vendorRegnKeyList )
                {
                    if( companyRegnKey.equals( supplierSearchRes.regnKey_ ))
                    {
                    	supplierSearchRes.isRegn_ = true;
                    }
                }
                
                RatingSummaryData ratingSummaryData = ratingMap.get( supplierSearchRes.regnKey_ );
                
                if( ratingSummaryData != null )
                {
                	supplierSearchRes.avg_ratings_ = ratingSummaryData._avg_ratings;
                    
                	supplierSearchRes.no_of_ratings_ = ratingSummaryData._no_of_ratings;
                }
            }
		
		
	}
	
	/**
	 * Creates the queryFilter based on the Search Options in the Advanced search option object.
	 */
	private void createFilter( AdvSearchOption searchOptionObj, RegnData queryFilter )
	{
		queryFilter.signUpAs_ = "Supplier";
		
		if( searchOptionObj.category_ != null && !(searchOptionObj.category_.equalsIgnoreCase( "" )) )
		{
			queryFilter.businessCategory_ = searchOptionObj.category_;
		}
		else
		{
			queryFilter.businessCategory_ = null;
		}
		
	}
	
	
	
	/**
	 * This method forms the searchResultList, from the given companyList. This is a helper
	 * method for it.
	 * 
	 * Input: List<> of RegnData which is the companyList.
	 * 
	 * Output: List<> of SupplierSearchResult, which is the SearchResult object that needs to be
	 * sent back to the view.
	 * 
	 */
	private void formSearchResult( List<RegnData> companyList,
	        					   List<SupplierSearchResult> searchResultList )
	{
		
		SupplierSearchResult searchResult = null;
		
		for( RegnData regnDataObj : companyList )
		{
			searchResult = new SupplierSearchResult( );
			
			searchResult.regnKey_ = regnDataObj.companyRegnKey_;

			searchResult.companyName_ = regnDataObj.companyName_;
			
			searchResult.isRegn_      = regnDataObj.isRegnVendor_;
			
			searchResultList.add( searchResult );
			
		}
		
	}
	
	
	/**
	 * 
	 */
	private void convertToPcData( SearchItem searchItemObj, ProductCatalogData pcData )
	{
		
		if( searchItemObj.name_ != null && !searchItemObj.name_.equalsIgnoreCase( "" )  )
		{
			pcData.itemName_ = searchItemObj.name_;
		}
		else
		{
			pcData.itemName_ = null;
		}
		if( searchItemObj.partno_ != null && !searchItemObj.partno_.equalsIgnoreCase( "" )  )
		{
			pcData.itemPartNo_ = searchItemObj.partno_;
		}
		else
		{
			pcData.itemPartNo_ = null;
		}
		
	}
	
	
}

