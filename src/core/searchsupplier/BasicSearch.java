/**
 * 
 * 
 * 
 */

package core.searchsupplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utils.ErrorLogger;

import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.userratings.RatingSummaryData;
import db.prodcatalog.ProductCatalogTable;
import db.regn.CompanyRegnTable;
import db.userratings.RatingsSummaryTable;
import db.vendorregn.RegnVendorMapTable;


public class BasicSearch implements BasicSupplierSearch
{
	private ErrorLogger	errLogger_;

	/**
	 * Method : BasicSearchAll( ) - constructor Input : Return :
	 * 
	 * Purpose: It is used initialization
	 */
	
	public BasicSearch( )
	{
		errLogger_ = ErrorLogger.instance( );
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

		CompanyRegnTable companyRegnTblObj = new CompanyRegnTable( );

		List<RegnData> companyList 		   = new ArrayList<RegnData>( );

		
		RegnData queryFilter 			   = new RegnData( );
		// Create the queryFilter based on the search options, set in the SearchOption object.
		createFilter( searchOptionObj, queryFilter );

		
		Set<CompanyRegnKey> supplierCompanyKeys   = new HashSet<CompanyRegnKey>( );
		// Retrieve the company keys for the Filter.
		companyRegnTblObj.searchKeys( queryFilter, supplierCompanyKeys );

		// If there are no keys for the given filter, just return as there is nothing to process.
		if( supplierCompanyKeys.isEmpty( ) )
			return -1;
		
		/** 
		 * If companyKeys is not empty and keyword is either null or empty string "", 
		 *  i.e. keyword is NOT present in the search.
		 *  
		 */
		if(  (   searchOptionObj.keyword_ == null ) ||
			 ( ( searchOptionObj.keyword_ != null ) && 
			   ( searchOptionObj.keyword_.trim( ).equalsIgnoreCase( "" ) ) )
			 
		   )
		{
			
			companyRegnTblObj.getCompanyList( supplierCompanyKeys, companyList );
			formSearchResult( companyList, searchResultList );
			
			// Setting registered vendor flag
			setRegnVendorFlag( searchOptionObj.buyerKey_, searchResultList );

			return 0; // Result found for given company keys without keyword.
			
		} 
		
		/** 
		 * If companyKeys is not empty and keyword is present in the search. 
		 * 
		 */
		else if(  ( searchOptionObj.keyword_ != null ) &&
				 !( searchOptionObj.keyword_.trim( ).equalsIgnoreCase( "" ) ) )
		{
			 
			Set<CompanyRegnKey> suppliersByKeyword = new HashSet<CompanyRegnKey>( );
			ProductCatalogTable productCatalogTblObj = new ProductCatalogTable( );

			// Pass the keyword in the search option, to the ProductCatalog table.
			productCatalogTblObj.getCompanyKeys( searchOptionObj.keyword_, suppliersByKeyword );
			
			if( suppliersByKeyword.isEmpty( ) )
			{
				// There is nothing in common, since this Set<> is empty, so return now.
				System.out.println( "SuppliersByKeyword is Empty" );
				return -2; 
			}
			
			/*
			 * Since the search results fetch all the companies matching the search criteria
			 * the company requesting for the search will be present in the search results
			 * So, from the search results, this company should be removed
			 */
			suppliersByKeyword.remove( searchOptionObj.buyerKey_ );

			// intersection of two Set<>s , supplier Keys by keyword and supplierKeys in total.
			supplierCompanyKeys.retainAll( suppliersByKeyword );
			
			if( !supplierCompanyKeys.isEmpty( ) ) // Intersection set is not empty.
			{
				// Now get the companyList for the given keys.
				companyRegnTblObj.getCompanyList( supplierCompanyKeys, companyList );
				
				// Based on the companyList, form the searchResultList of companies.
				formSearchResult( companyList, searchResultList );
				
				// Setting registered vendor flag
				setRegnVendorFlag( searchOptionObj.buyerKey_, searchResultList );
				
				return 0; // intersection set List

			}
			else
			{
				// Intersection is empty
				return -3;
			}
				
		}
		else
		{	
			return -4; // Should not come here !
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
		
		 Map<CompanyRegnKey, RatingSummaryData> ratingMap = new HashMap<CompanyRegnKey, RatingSummaryData>();
		 
		RatingsSummaryTable ratingsSummaryTable = new RatingsSummaryTable( );
			
		result = ratingsSummaryTable.getAllRatingsSummaryList( ratingMap );
			
		if( result != 0 )
	    {
		       return;
	    }
			
		
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
			
			searchResult.regnKey_ 		= regnDataObj.companyRegnKey_;

			searchResult.companyName_ 	= regnDataObj.companyName_;
			
			searchResult.isRegn_      	= regnDataObj.isRegnVendor_;
			
			searchResultList.add( searchResult );
			
		}
	
	}
	
	
}
