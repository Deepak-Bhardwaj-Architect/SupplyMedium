/**
 * 
 */
package core.searchsupplier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import core.prodcatalog.ProductCatalogData;
import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import db.RegnCompanyMapperTable;
import db.prodcatalog.ProductCatalogTable;
import db.regn.CompanyRegnTable;
import db.vendorregn.RegnVendorMapTable;

import utils.ErrorLogger;


public class AdvRegnSearch implements AdvSupplierSearch
{
	
	private ErrorLogger errLogger_;
	
	/**
	 * Method : AdvRegnSearchAll( ) - constructor 
	 * Input  :  
	 * Return :  
	 * 
	 * Purpose: It is used initialization
	 */
	public AdvRegnSearch()
	{
		this.errLogger_ = ErrorLogger.instance( );
	}
	
	
	/**
	 * 
	 */
	public int search(  AdvSearchOption searchOptionObj, 
						List<SupplierSearchResult> searchResultList )
	{
	
		
		Set<CompanyRegnKey> regnCompanyKeys = new HashSet<CompanyRegnKey>( );
		RegnCompanyMapperTable regnMapper = new RegnCompanyMapperTable();
	
		// Get all registered vendors only for this Buyer key
		regnMapper.getRegnCompanyKeys( searchOptionObj.buyerKey_, regnCompanyKeys );

		/** If there are no registered vendors for this buyer(regnKey), 
		 *  just return as there is nothing to process.
		 */
		if( regnCompanyKeys.isEmpty( ) )
			return -2;

	
		List<RegnData> companyList 		   = new ArrayList<RegnData>( );
		RegnData queryFilter 			   = new RegnData( );

		// Create the queryFilter based on the search options, set in the SearchOption object.
		createFilter( searchOptionObj, queryFilter );
		
		Set<CompanyRegnKey> supplierCompanyKeys = new HashSet<CompanyRegnKey>();
		CompanyRegnTable companyRegnTblObj 		  = new CompanyRegnTable( );
		
		// Lets get the suppliers for this filter(category)
		companyRegnTblObj.searchKeys( queryFilter, supplierCompanyKeys );

		 
		// If there are no suppliers for this queryFilter, return with error.
		 	
		if( supplierCompanyKeys.isEmpty( ) )
			return -3;
		
		// Find the intersection of query Filtered suppliers and regn suppliers.
		supplierCompanyKeys.retainAll( regnCompanyKeys );
		
		
		// If there are no remaining suppliers for this queryFilter, return with error.
		  
		if( supplierCompanyKeys.isEmpty( ) )
		{
			companyRegnTblObj = null;
			return  -4;
		}
		
		
		List<SearchItem> searchItemList = searchOptionObj.searchItemList_;
		if( searchItemList == null ) // Invalid error !!
			return -5;
		
		/**
		 * If there are no search items in the advanced search, then just return the
		 * search results based on the supplier keys, got as a result of the queryFilter alone.
		 */
		if( searchItemList.isEmpty( ) )
		{
			
			companyRegnTblObj.getCompanyList( supplierCompanyKeys, companyList );
			
			formSearchResult( companyList, searchResultList );
			
			return 0; 
			
		} 
		
		// The list is not empty, i.e. there are item names/part no. to search from.
		Set<CompanyRegnKey> suppliersByProducts = new HashSet<CompanyRegnKey>( );

		
		ProductCatalogData pcatData = new ProductCatalogData();
		ProductCatalogTable pcatTable = new ProductCatalogTable();
		
		boolean first = true;
				
		
		Set<CompanyRegnKey> prodCatalogCompanyKeys = null;
		
		for( SearchItem searchItemObj : searchItemList )
		{
			
			pcatData = null;
			pcatData = new ProductCatalogData();
			
			createproductCatalogData( searchItemObj, pcatData );
			pcatTable.getCompanyKeys( pcatData,  suppliersByProducts );
			
			// keep going until we get a non-empty supplier list for any product item search.
			if( suppliersByProducts.isEmpty( ) && first )
				continue;
			
			// if first time, create a new product catalog company keys initially, based on
			// the first set of company keys got by above query.
			if( !suppliersByProducts.isEmpty( ) && first )
			{
				prodCatalogCompanyKeys = new HashSet<CompanyRegnKey>( suppliersByProducts );
				first = false;
			}
			else
			{
				
				/**
				 * Only if ALL the products mentioned in the search are found in the same company, 
				 * add that company to the result. This is achieved using the intersection of 
				 * productCatalogCompanyKeys in multiple queries for various search items.
				 * 
				 */
						
    			if( searchOptionObj.checkAll_ )
    			{
    				prodCatalogCompanyKeys.retainAll( suppliersByProducts );
    			}
    			/**
				 * Else if any ONE of the product is found in the search for the company,
				 * it is the union of all sets of companies with each query based on the
				 * product item.
				 * 
				 */
    			else
    			{
    				prodCatalogCompanyKeys.addAll( suppliersByProducts );
    			}
			
			}
			
			suppliersByProducts.clear( );
			
		}
	
		// No suppliers by products found, so return
		if( prodCatalogCompanyKeys == null )
		{
			System.out.println( "regd: pcCompanyKeys is null !!" );
			return -6;
			
		}
		
		// After complete search with all options, if we still have a non-empty set, go ahead
		// OR else return as no items found !
		if( prodCatalogCompanyKeys.isEmpty( ) )
		{
			companyRegnTblObj = null;
			return -7;
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
		
		companyRegnTblObj.getCompanyList( supplierCompanyKeys, companyList );
		
		formSearchResult( companyList, searchResultList );

		// Setting registered vendor flag
		setRegnVendorFlag( searchOptionObj.buyerKey_, searchResultList );

		return 0;
		
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
		
		
		// Need to optimized this code, because of it process two loops
		
		if( result != 0 ) return; // Error code
		
		for( SupplierSearchResult supplierSearchRes : searchResultList )
        {
            for( CompanyRegnKey companyRegnKey : vendorRegnKeyList )
            {
                if( companyRegnKey.equals( supplierSearchRes.regnKey_ ))
                {
                	supplierSearchRes.isRegn_ = true;
                }
            }
        }
	}
	
	
	/**
	 * 
	 */
	private void createFilter( AdvSearchOption searchOptionObj, RegnData queryFilter )
	{
		queryFilter.signUpAs_ = "Supplier";
		
		if(searchOptionObj.category_ != "" && searchOptionObj.category_ != null)
		{
			queryFilter.businessCategory_ = searchOptionObj.category_;
		}
		else
		{
			queryFilter.businessCategory_ = null;
		}
		
	}
	
	
	/**
	 * 
	 */
	private void createproductCatalogData( SearchItem searchItemObj, ProductCatalogData productCatalogDataObj )
	{
		if(searchItemObj.name_ != "" && searchItemObj.name_ != null)
		{
			productCatalogDataObj.itemName_ = searchItemObj.name_;
		}
		else
		{
			productCatalogDataObj.itemName_ = null;
		}
		if(searchItemObj.partno_ != "" && searchItemObj.partno_ != null)
		{
			productCatalogDataObj.itemPartNo_ = searchItemObj.partno_;
		}
		else
		{
			productCatalogDataObj.itemPartNo_ = null;
		}
		
	}
	
	

	
	
	
	/**
	 * 
	 */
//	private void formSearchResult( List<RegnData> companyList, 
//										 List<SupplierSearchResult> supplierSearchResultList )
//	{
//		
//		SupplierSearchResult supplierSearchResultObj  = null;
//		for( RegnData regnDataObj : companyList )
//		{
//			supplierSearchResultObj  = null;
//			supplierSearchResultObj = new SupplierSearchResult();
//			
//			if(regnDataObj.companyRegnKey_.companyPhoneNo_ != "" && regnDataObj.companyRegnKey_.companyPhoneNo_ != null)
//			{
//				supplierSearchResultObj.regnKey_.companyPhoneNo_ = regnDataObj.companyRegnKey_.companyPhoneNo_;		
//			}
//			else
//			{
//				supplierSearchResultObj.regnKey_.companyPhoneNo_ = null;
//			}
//			
//			if(regnDataObj.companyName_ != "" && regnDataObj.companyName_ != null)
//			{
//				supplierSearchResultObj.companyName_ = regnDataObj.companyName_;		
//			}
//			else
//			{
//				supplierSearchResultObj.companyName_ = null;
//			}
//			supplierSearchResultList.add(supplierSearchResultObj);
//		}
//		
//		
//	}
	
	
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
			
			searchResult.regnKey_ 	  = regnDataObj.companyRegnKey_;

			searchResult.companyName_ = regnDataObj.companyName_;
			
			searchResult.isRegn_      = regnDataObj.isRegnVendor_;
			
			searchResultList.add( searchResult );
			
		}
	
	}
	
	
	
}
