/**
 * 
 */
package core.searchsupplier;

import java.util.*;

import core.regn.CompanyRegnKey;
import core.regn.RegnData;

import utils.ErrorLogger;
import db.regn.CompanyRegnTable;
import db.vendorregn.RegnVendorMapTable;
import db.RegnCompanyMapperTable;
import db.prodcatalog.ProductCatalogTable;

public class BasicRegnSearch implements BasicSupplierSearch
{
	
	private ErrorLogger errLogger_;
	
	/**
	 * Method : BasicRegnSearchAll( ) - constructor 
	 * Input  :  
	 * Return :  
	 * 
	 * Purpose: It is used initialization
	 */
	public BasicRegnSearch()
	{
		this.errLogger_ = ErrorLogger.instance( );
	}
	
	
	/**
	 * This is the prime interface of the basic search option, implementing the BasicSupplierSearch
	 * interface.
	 * 
	 * Input:  SearchOption that contains the option specified by the user.
	 * 
	 * Output: The list of search results containing the companies matching the search option.
	 * 
	 * 
	 */
	
	public int search( SearchOption searchOptionObj, List<SupplierSearchResult> searchResultList )
	{
		
		Set<CompanyRegnKey> regnCompanyKeys = new HashSet<CompanyRegnKey>( );
		RegnCompanyMapperTable regnMapper = new RegnCompanyMapperTable();
		
		
		regnMapper.getRegnCompanyKeys( searchOptionObj.buyerKey_, regnCompanyKeys );
		
		System.out.println(  "regnCompanyKeys size:" + regnCompanyKeys.size( ) );

		
		
		/** If there are no registered vendors for this buyer(regnKey), 
		 *  just return as there is nothing to process.
		 */
		if( regnCompanyKeys.isEmpty( ) )
			return -2;

	
		List<RegnData> companyList 		   = new ArrayList<RegnData>( );
		RegnData queryFilter 			   = new RegnData( );

		// Create the queryFilter based on the search options, set in the SearchOption object.
		createFilter( searchOptionObj, queryFilter );

		Set<CompanyRegnKey> supplierCompanyKeys   = new HashSet<CompanyRegnKey>( );
		
		CompanyRegnTable companyRegnTblObj 		  = new CompanyRegnTable( );
		
		// Lets get the suppliers for this filter(category)
		companyRegnTblObj.searchKeys( queryFilter, supplierCompanyKeys );

		System.out.println(  "supplierCompanyKeys before retainAll size:" + supplierCompanyKeys.size( ) );

		/** 
		 * If there are no suppliers for this queryFilter, return with error.
		 */		
		if( supplierCompanyKeys.isEmpty( ) )
			return -3;
		
		
		// Find the intersection of query Filtered suppliers and regd suppliers.
		supplierCompanyKeys.retainAll( regnCompanyKeys );
		
		
		/** If there are no registered suppliers for this queryFilter, return with error.
		 * 
		 */			
		if( supplierCompanyKeys.isEmpty( ) ) return  -4;
		
		
		
		/** 
		 * If the keyword is either null or empty string "",  i.e. no keyword present in the search.
		 * Just get the search result list for the given supplierCompanyKeys and return success! 
		 */
		if(  ( searchOptionObj.keyword_ == null ) ||
			 ( ( searchOptionObj.keyword_ != null ) && 
			   ( searchOptionObj.keyword_.trim( ).equalsIgnoreCase( "" ) ) )
			 
		   )
		{
	
			companyRegnTblObj.getCompanyList( supplierCompanyKeys, companyList );
			formSearchResult( companyList, searchResultList );

			return 0; // Result found for given company keys without keyword.
			
		} 
		/** 
		 * If keyword is present in the search. 
		 * 
		 */
		else if(  ( searchOptionObj.keyword_ != null ) &&
				 !( searchOptionObj.keyword_.trim( ).equalsIgnoreCase( "" ) ) )
		{
			
			Set<CompanyRegnKey> suppliersByKeyword = new HashSet<CompanyRegnKey>( );
			ProductCatalogTable productCatalogTblObj = new ProductCatalogTable( );

			// Pass the keyword in the search option, to the ProductCatalog table.
			productCatalogTblObj.getCompanyKeys( searchOptionObj.keyword_, suppliersByKeyword );
			
			System.out.println(  "SuppliersByKeyword size:" + suppliersByKeyword.size( ) );
			System.out.println(  "supplierCompanyKeys size:" + supplierCompanyKeys.size( ) );


			if( suppliersByKeyword.isEmpty( ) )
			{
				// There is nothing in common, since this Set<> is empty, so return now.
				return -5; 
			}
			
			/*
			 * Since the search results fetch all the companies matching the search criteria
			 * the company requesting for the search will be present in the search results
			 * So, from the search results, this company should be removed
			 */
			suppliersByKeyword.remove( searchOptionObj.buyerKey_ );

			// intersection of two Set<>s , supplier Keys by keyword and supplierKeys in total.
			supplierCompanyKeys.retainAll( suppliersByKeyword );
			
			if( supplierCompanyKeys.isEmpty( ) ) return -6;
			
			// Intersection set is not empty. So we are good.
			// Now get the companyList for the given keys.
			companyRegnTblObj.getCompanyList( supplierCompanyKeys, companyList );
			
			// Based on the companyList, form the searchResultList of companies.
			formSearchResult( companyList, searchResultList );
			
			// Setting registered vendor flag
			setRegnVendorFlag( searchOptionObj.buyerKey_, searchResultList );
			
			return 0; 
			
		}
		else
		{	
			return -7; // Should not come here !
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
	 * This method facilitates in creating a RegnData Filter object from the SearchOption object.
	 * 
	 */
	private void createFilter( SearchOption searchOptionObj, RegnData queryFilter )
	{
		queryFilter.signUpAs_ = "Supplier";

		if(  searchOptionObj.category_ != null && 
			!searchOptionObj.category_.trim( ).equalsIgnoreCase( "" )  )
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
			
			searchResult.regnKey_ 	  = regnDataObj.companyRegnKey_;

			searchResult.companyName_ = regnDataObj.companyName_;
			
			searchResult.isRegn_      = regnDataObj.isRegnVendor_;
			
			searchResultList.add( searchResult );
			
		}
	
	}
	
}
