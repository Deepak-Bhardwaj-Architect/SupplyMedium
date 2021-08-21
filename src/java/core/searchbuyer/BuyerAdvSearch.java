/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any.
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */
package core.searchbuyer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.trans.RFQData;
import core.trans.RFQItemData;
import core.userratings.RatingSummaryData;
import db.regn.CompanyRegnTable;
import db.trans.RFQItemTable;
import db.trans.RFQTable;
import db.userratings.RatingsSummaryTable;
import db.vendorregn.RegnVendorMapTable;

import utils.ErrorLogger;
import utils.ErrorMaster;

/**
 * File:  BuyerAdvSearch.java 
 *
 * Created on Jul 19, 2013 7:08:10 PM
 */
public class BuyerAdvSearch implements AdvBuyerSearch
{
	private ErrorLogger errLogger_;
        private static ErrorMaster errorMaster_ = null;


	
	/**
	 * Method : BuyerAdvSearch( ) - constructor Input : Return :
	 * 
	 * Purpose: It is used initialization
	 */
	
	public BuyerAdvSearch( )
	{
		this.errLogger_ = ErrorLogger.instance( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
    public int search( BuyerAdvSearchOption searchOptionObj,
            List<BuyerSearchResult> searchResultList )
    {
    	CompanyRegnTable companyRegnTbl    = new CompanyRegnTable( );
		List<RegnData> companyList 		   = new ArrayList<RegnData>( );
		
		RegnData queryFilter = new RegnData( );
		
		// Create the queryFilter based on the search options, set in the
		// SearchOption object. ? In queryFilter the Category "All" case should be
		// considered.
		createFilter( searchOptionObj, queryFilter );
	      	
		Set<CompanyRegnKey> buyerCompanyKeys = new HashSet<CompanyRegnKey>( );
		
		// Retrieve the company keys for the filter.
		companyRegnTbl.searchKeys( queryFilter, buyerCompanyKeys );
	
		// If there are no keys for the given filter, just return as there is nothing to process.
		if( buyerCompanyKeys.isEmpty( ) )
		{
			companyRegnTbl = null;
			return 3351;
		}
		
		List<BuyerSearchItem> searchItemList = searchOptionObj.buyerSearchItemList_;
		if( searchItemList == null ) // Invalid error !!
			return 3352;
		
		/**
		 * If there are no search items in the advanced search, then just return the
		 * search results based on the buyer keys, got as a result of the queryFilter alone.
		 */
		if( searchItemList.isEmpty( ) )
		{
			companyRegnTbl.getCompanyList( buyerCompanyKeys, companyList );
			
			formSearchResult( companyList, searchResultList );
			
			return 3350; 
		} 
		else // the list is not empty, i.e. there EXISTS item names/part numbers to search from.
		{
//			//Set<CompanyRegnKey> prodCatalogCompanyKeys = null;
//			
//			Set<CompanyRegnKey> rfqItemCompanyKeys = null;
//			
//			//ProductCatalogData pcData = new ProductCatalogData( );
//			//ProductCatalogTable pcTable = new ProductCatalogTable( );
//			
//			RFQItemData itemData = new RFQItemData( );
//			RFQItemTable itemTable = new RFQItemTable( );
//			RFQTable rfqTable = new RFQTable( );
//			
//			List<RFQData> rfqDataList = new ArrayList<RFQData>( );
//			
//			boolean first = true;
//			Set<CompanyRegnKey> buyersByKeyword = new HashSet<CompanyRegnKey>( );
//
//			for( BuyerSearchItem searchItemObj : searchItemList )
//			{
//				itemData = null;
//				itemData = new RFQItemData( );
//				
//				rfqDataList = null;
//				rfqDataList = new ArrayList<RFQData>( );
//				
//				//
//				convertToRfqItemData( searchItemObj, itemData );
//				int result = itemTable.getRfqs( itemData, rfqDataList );
//				
//				if( result != 0 )
//				{
//					continue;
//				}
//				
//				if( rfqDataList.isEmpty( ) )
//					continue;
//				
//				result = rfqTable.getCompanyKeys( rfqDataList, buyersByKeyword ); 
//				
//				// keep going until we get a non-empty list for any product item search.
//				if( buyersByKeyword.isEmpty( ) && first )
//					continue;
//				
//				// if first time, create a new product catalog company keys initially, based on
//				// the first set of company keys got by above query.
//				if( !buyersByKeyword.isEmpty( ) && first )
//				{
//					rfqItemCompanyKeys = new HashSet<CompanyRegnKey>( buyersByKeyword );
//					first = false;
//				}
//				else 
//				{
//					/**
//					 * Only if all items mentioned in the search are found in the same company, 
//					 * add that company to the result. This is achieved using the intersection of 
//					 * rfqItemCompanyKeys in multiple queries for various search items.
//					 */
//					
//					if( searchOptionObj.checkAll_ )
//					{
//						rfqItemCompanyKeys.retainAll( buyersByKeyword );
//					}
//					/**
//					 * Else it is the union of all sets of companies with each query based on the
//					 * product item.
//					 * 
//					 */
//					else
//					{
//						rfqItemCompanyKeys.addAll( buyersByKeyword );
//					}
//					
//				}
//				
//				buyersByKeyword.clear( );
//			
//			} // end for
//			
//			if( rfqItemCompanyKeys == null )
//			{
//				errorMaster_.insert( "rfqItemCompanyKeys is null !!" );
//				return 3353;
//				
//			}
//			
//			// After complete search with all options, if we still have a non-empty set, go ahead
//			// OR else return as no items found !
//			if( rfqItemCompanyKeys.isEmpty( ) )
//			{
//				companyRegnTbl = null;
//				return 3354;
//			}
//			
//			/*
//			 * Since the search results fetch all the companies matching the search criteria
//			 * the company requesting for the search will be present in the search results
//			 * So, from the search results, this company should be removed
//			 */
//			rfqItemCompanyKeys.remove( searchOptionObj.supplierKey_ );
//			
//			// returning companies only that match the queryFilter as well as the 
//			// rfq items, as per the search options.
//			buyerCompanyKeys.retainAll( rfqItemCompanyKeys );
			if( buyerCompanyKeys.size( ) == 0 )
				return 0; // No supplier found
			
			companyRegnTbl.getCompanyList( buyerCompanyKeys, companyList );
			formSearchResult( companyList, searchResultList );
			
			
			// This method is used to set the vendor regn flag
			
			setRegnVendorFlag( searchOptionObj.supplierKey_, searchResultList );
			
			companyRegnTbl = null;
			queryFilter = null;
			companyList = null;
//			itemData = null;
//			itemTable = null;
			
			return 3300; // intersection set
			
		}
	}
	
	/**
	 * This method decide the result vendor type. That is registered vendor or non registered vendor.
	 * If registered vendor it set the isRegn_ flag as true
	 * 
	 */
	private void setRegnVendorFlag( CompanyRegnKey buyerKey, List<BuyerSearchResult> searchResultList )
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
		
		
		
		for( BuyerSearchResult buyerSearchRes : searchResultList )
        {
			
            for( CompanyRegnKey companyRegnKey : vendorRegnKeyList )
            {
            	errorMaster_.insert( "companyregnkey="+companyRegnKey.toString( )+" buyersearchkey="+buyerSearchRes.regnKey_.toString( ) );
                if( companyRegnKey.equals( buyerSearchRes.regnKey_ ))
                {
                	buyerSearchRes.isRegn_ = true;
                }
            }
            
            RatingSummaryData ratingSummaryData = ratingMap.get( buyerSearchRes.regnKey_ );
            
            if( ratingSummaryData != null )
            {
            	buyerSearchRes.avg_ratings_ = ratingSummaryData._avg_ratings;
                
                buyerSearchRes.no_of_ratings_ = ratingSummaryData._no_of_ratings;
            }
            
            
        }
		
	}
	
	/**
	 * Creates the queryFilter based on the Search Options in the Advanced search option object.
	 */
	private void createFilter( BuyerAdvSearchOption searchOptionObj, RegnData queryFilter )
	{
		queryFilter.signUpAs_ = "Buyer";
		
		if( searchOptionObj.category_ != null && !(searchOptionObj.category_.equalsIgnoreCase( "" )) )
		{
			queryFilter.businessCategory_ = searchOptionObj.category_;
		}
		else
		{
			queryFilter.businessCategory_ = null;
		}
                if( searchOptionObj.country_ != null && !(searchOptionObj.country_.equalsIgnoreCase( "" )) )
		{
			queryFilter.country_ = searchOptionObj.country_;
		}
		else
		{
			queryFilter.country_ = null;
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
	        					   List<BuyerSearchResult> searchResultList )
	{
		BuyerSearchResult searchResult = null;
		
		for( RegnData regnDataObj : companyList )
		{
			searchResult = new BuyerSearchResult( );
			
			searchResult.regnKey_ = regnDataObj.companyRegnKey_;

			searchResult.companyName_ = regnDataObj.companyName_;
			
			searchResult.isRegn_      = regnDataObj.isRegnVendor_;
			
			searchResultList.add( searchResult );
			
		}
	}
	
	/**
	 * 
	 */
	private void convertToRfqItemData( BuyerSearchItem searchItemObj, RFQItemData itemData )
	{
		
		if( searchItemObj.name_ != null && !searchItemObj.name_.equalsIgnoreCase( "" )  )
		{
			itemData.itemDesc_ = searchItemObj.name_;
		}
		else
		{
			itemData.itemDesc_ = null;
		}
		if( searchItemObj.partno_ != null && !searchItemObj.partno_.equalsIgnoreCase( "" )  )
		{
			itemData.partNo_ = searchItemObj.partno_;
		}
		else
		{
			itemData.partNo_ = null;
		}
	}

}
