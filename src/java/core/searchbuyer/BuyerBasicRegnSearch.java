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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.trans.RFQData;
import db.regn.CompanyRegnTable;
import db.trans.RFQItemTable;
import db.trans.RFQTable;
import db.vendorregn.RegnVendorMapTable;

import utils.ErrorLogger;
import utils.ErrorMaster;

/**
 * File:  BuyerBasicRegnSearch.java 
 *
 * Created on Jul 19, 2013 7:09:02 PM
 */
public class BuyerBasicRegnSearch implements BasicBuyerSearch
{

	private ErrorLogger errLogger_;
        private static ErrorMaster errorMaster_ = null;


	
	/**
	 * Method : BuyerBasicRegnSearch( ) - constructor 
	 * Input  :  
	 * Return :  
	 * 
	 * Purpose: It is used initialization
	 */
	public BuyerBasicRegnSearch( )
	{
		this.errLogger_ = ErrorLogger.instance( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	
	/**
	 * This is the prime interface of the basic search option, implementing the BasicBuyerSearch
	 * interface.
	 * 
	 * Input:  SearchOption that contains the option specified by the user.
	 * 
	 * Output: The list of search results containing the companies matching the search option.
	 * 
	 * 
	 */
	
    public int search( BuyerSearchOption searchOptionObj,
            List<BuyerSearchResult> searchResultList )
    {
    	Set<CompanyRegnKey> regnCompanyKeys = new HashSet<CompanyRegnKey>( );
		
    	//RegnCompanyMapperTable regnMapper = new RegnCompanyMapperTable();
    	
    	RegnVendorMapTable regnMapper = new RegnVendorMapTable( );
		
		
		regnMapper.find( searchOptionObj.supplierKey_, regnCompanyKeys );
		
		errorMaster_.insert(  "regnCompanyKeys size:" + regnCompanyKeys.size( ) );

		
		
		/** If there are no registered vendors for this supplier(regnKey), 
		 *  just return as there is nothing to process.
		 */
		if( regnCompanyKeys.isEmpty( ) )
			return 3251;

	
		List<RegnData> companyList 		   = new ArrayList<RegnData>( );
		RegnData queryFilter 			   = new RegnData( );

		// Create the queryFilter based on the search options, set in the SearchOption object.
		createFilter( searchOptionObj, queryFilter );

		Set<CompanyRegnKey> buyerCompanyKeys   = new HashSet<CompanyRegnKey>( );
		
		CompanyRegnTable companyRegnTblObj 		  = new CompanyRegnTable( );
		
		// Lets get the buyers for this filter(category)
		companyRegnTblObj.searchKeys( queryFilter, buyerCompanyKeys );

		errorMaster_.insert(  "Buyer CompanyKeys before retainAll size:" + buyerCompanyKeys.size( ) );

		/** 
		 * If there are no buyers for this queryFilter, return with error.
		 */		
		if( buyerCompanyKeys.isEmpty( ) )
			return 3252;
		
		
		// Find the intersection of query Filtered buyers and regd buyers.
		buyerCompanyKeys.retainAll( regnCompanyKeys );
		
		
		/** If there are no registered buyers for this queryFilter, return with error.
		 * 
		 */			
		if( buyerCompanyKeys.isEmpty( ) ) return  3253;
		
		
		
		/** 
		 * If the keyword is either null or empty string "",  i.e. no keyword present in the search.
		 * Just get the search result list for the given buyerCompanyKeys and return success! 
		 */
		
		if(  ( searchOptionObj.keyword_ == null ) ||
			 ( ( searchOptionObj.keyword_ != null ) && 
			   ( searchOptionObj.keyword_.trim( ).equalsIgnoreCase( "" ) ) )
			 
		   )
		{
	
			companyRegnTblObj.getCompanyList( buyerCompanyKeys, companyList );
			formSearchResult( companyList, searchResultList );

			return 3250; // Result found for given company keys without keyword.
			
		}
		/** 
		 * If keyword is present in the search. 
		 * 
		 */
		else if(  ( searchOptionObj.keyword_ != null ) &&
				 !( searchOptionObj.keyword_.trim( ).equalsIgnoreCase( "" ) ) )
		{
			
			Set<CompanyRegnKey> buyersByKeyword = new HashSet<CompanyRegnKey>( );
			//ProductCatalogTable productCatalogTblObj = new ProductCatalogTable( );

			RFQItemTable itemTable = new RFQItemTable( );

			// Pass the keyword in the search option, to the RFQItem table.
			//productCatalogTblObj.getCompanyKeys( searchOptionObj.keyword_, buyersByKeyword );
			
			List<RFQData> rfqDataList = new ArrayList<RFQData>( );
			
			int result = itemTable.getRfqs( searchOptionObj.keyword_, rfqDataList );
			
			if( result != 0 )
				return 3254; //Unable to fetch rfqs from RFQItemTable
			
			if( rfqDataList.isEmpty( ) )
				return 3254; //No items in this search
			
			RFQTable rfqTable = new RFQTable( );
			
			result = rfqTable.getCompanyKeys( rfqDataList, buyersByKeyword );
			
			
			errorMaster_.insert(  "BuyersByKeyword size:" + buyersByKeyword.size( ) );
			errorMaster_.insert(  "BuyersCompanyKeys size:" + buyerCompanyKeys.size( ) );
			

			if( buyersByKeyword.isEmpty( ) )
			{
				// There is nothing in common, since this Set<> is empty, so return now.
				return 3255; 
			}
			
			/*
			 * Since the search results fetch all the companies matching the search criteria
			 * the company requesting for the search will be present in the search results
			 * So, from the search results, this company should be removed
			 */
			buyersByKeyword.remove( searchOptionObj.supplierKey_ );

			// intersection of two Set<>s , buyer Keys by keyword and buyerkeys in total.
			buyerCompanyKeys.retainAll( buyersByKeyword );
			
			if( buyerCompanyKeys.isEmpty( ) ) return 3256;
			
			// Intersection set is not empty. So we are good.
			// Now get the companyList for the given keys.
			companyRegnTblObj.getCompanyList( buyerCompanyKeys, companyList );
			
			// Based on the companyList, form the searchResultList of companies.
			formSearchResult( companyList, searchResultList );
			
			// Setting registered vendor flag
			setRegnVendorFlag( searchOptionObj.supplierKey_, searchResultList );
			
			return 3250; 
			
		}
		else
		{	
			return 3257; // Should not come here !
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
   		
   		for( BuyerSearchResult buyerSearchRes : searchResultList )
           {
               for( CompanyRegnKey companyRegnKey : vendorRegnKeyList )
               {
                   if( companyRegnKey.equals( buyerSearchRes.regnKey_ ))
                   {
                   	buyerSearchRes.isRegn_ = true;
                   }
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
			
			searchResult.regnKey_ 	  = regnDataObj.companyRegnKey_;

			searchResult.companyName_ = regnDataObj.companyName_;
			
			searchResult.isRegn_      = regnDataObj.isRegnVendor_;
			
			searchResultList.add( searchResult );
			
		}
	
	}
	
}
