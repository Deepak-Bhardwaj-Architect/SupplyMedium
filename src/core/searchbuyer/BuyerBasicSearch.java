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

import utils.ErrorLogger;

import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.trans.RFQData;
import core.userratings.RatingSummaryData;
import db.regn.CompanyRegnTable;
import db.trans.RFQItemTable;
import db.trans.RFQTable;
import db.userratings.RatingsSummaryTable;
import db.vendorregn.RegnVendorMapTable;

/**
 * File:  BuyerBasicSearch.java 
 *
 * Created on Jul 19, 2013 7:09:16 PM
 */
public class BuyerBasicSearch implements BasicBuyerSearch
{

	private ErrorLogger	errLogger_;

	/**
	 * Method : BuyerBasicSearch( ) - constructor Input : Return :
	 * 
	 * Purpose: It is used initialization
	 */
	public BuyerBasicSearch( )
	{
		errLogger_ = ErrorLogger.instance( );
	}

	
	
	/**
	 * This is the prime interface of the buyer basic search option, implementing the BasicBuyerSearch
	 * interface.
	 * 
	 * Input:  BuyerSearchOption that contains the option specified by the user.
	 * 
	 * Output: The list of search results containing the companies matching the search option.
	 * 
	 * 
	 */
    
    public int search( BuyerSearchOption searchOptionObj,
            List<BuyerSearchResult> searchResultList )
    {
    	CompanyRegnTable companyRegnTblObj = new CompanyRegnTable( );

		List<RegnData> companyList 		   = new ArrayList<RegnData>( );

		
		RegnData queryFilter 			   = new RegnData( );
		
		// Create the queryFilter based on the search options, set in the BuyerSearchOption object.
		createFilter( searchOptionObj, queryFilter );

		Set<CompanyRegnKey> buyerCompanyKeys   = new HashSet<CompanyRegnKey>( );
		
		// Retrieve the company keys for the Filter.
		companyRegnTblObj.searchKeys( queryFilter, buyerCompanyKeys );

		// If there are no keys for the given filter, just return as there is nothing to process.
		if( buyerCompanyKeys.isEmpty( ) )
			return 3201; //
		
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
			
			companyRegnTblObj.getCompanyList( buyerCompanyKeys, companyList );
			formSearchResult( companyList, searchResultList );
			
			// Setting registered vendor flag
			setRegnVendorFlag( searchOptionObj.supplierKey_, searchResultList );

			return 3200; // Result found for given company keys without keyword.
			
		} 
		
		/** 
		 * If companyKeys is not empty and keyword is present in the search. 
		 * 
		 */
		else if(  ( searchOptionObj.keyword_ != null ) &&
				 !( searchOptionObj.keyword_.trim( ).equalsIgnoreCase( "" ) ) )
		{
			 
			Set<CompanyRegnKey> buyersByKeyword = new HashSet<CompanyRegnKey>( );
	//		ProductCatalogTable productCatalogTblObj = new ProductCatalogTable( );
			
			RFQItemTable itemTable = new RFQItemTable( );

			// Pass the keyword in the search option, to the RFQItem table.
			//productCatalogTblObj.getCompanyKeys( searchOptionObj.keyword_, buyersByKeyword );
			
			List<RFQData> rfqDataList = new ArrayList<RFQData>( );
			
			int result = itemTable.getRfqs( searchOptionObj.keyword_, rfqDataList );
			
			if( result != 0 )
				return 3202; //Unable to fetch rfqs from RFQItemTable
			
			if( rfqDataList.isEmpty( ) )
				return 3202; //No items to search
			
			RFQTable rfqTable = new RFQTable( );
			
			result = rfqTable.getCompanyKeys( rfqDataList, buyersByKeyword );
			
			if( buyersByKeyword.isEmpty( ) )
			{
				// There is nothing in common, since this Set<> is empty, so return now.
				System.out.println( "BuyersByKeyword is Empty" );
				return 3203;  
			}
			
			/*
			 * Since the search results fetch all the companies matching the search criteria
			 * the company requesting for the search will be present in the search results
			 * So, from the search results, this company should be removed
			 */
			buyersByKeyword.remove( searchOptionObj.supplierKey_ );

			// intersection of two Set<>s , supplier Keys by keyword and supplierKeys in total.
			buyerCompanyKeys.retainAll( buyersByKeyword );
			
			if( !buyerCompanyKeys.isEmpty( ) ) // Intersection set is not empty.
			{
				// Now get the companyList for the given keys.
				companyRegnTblObj.getCompanyList( buyerCompanyKeys, companyList );
				
				// Based on the companyList, form the searchResultList of companies.
				formSearchResult( companyList, searchResultList );
				
				
				// Setting registered vendor flag
				setRegnVendorFlag( searchOptionObj.supplierKey_, searchResultList );
				
				return 3200; // intersection set List

			}
			else
			{
				// Intersection is empty
				return 3204;
			}
				
		}
		else
		{	
			return 3205; // Should not come here !
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
            	System.out.println( "companyregnkey="+companyRegnKey.toString( )+" buyersearchkey="+buyerSearchRes.regnKey_.toString( ) );
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
	 * This method facilitates in creating a RegnData Filter object from the SearchOption object.
	 * 
	 */
	private void createFilter( BuyerSearchOption searchOptionObj, RegnData queryFilter )
	{
		queryFilter.signUpAs_ = "Buyer";

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
	 * Output: List<> of BuyerSearchResult, which is the SearchResult object that needs to be
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

}
