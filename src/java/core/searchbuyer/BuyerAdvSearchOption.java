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
import java.util.List;

import core.regn.CompanyRegnKey;
import utils.ErrorMaster;

/**
 * File:  BuyerAdvSearchOption.java 
 *
 * Created on Jul 19, 2013 7:08:27 PM
 */
public class BuyerAdvSearchOption
{
	public String 				category_;
        public String 				country_;
	public String 				keyword_;
	public List <BuyerSearchItem> 	buyerSearchItemList_;
	public Boolean 			checkAll_;
	public Boolean 			searchRegd_;
	public CompanyRegnKey 		supplierKey_;
        private static ErrorMaster errorMaster_ = null;


	
	public BuyerAdvSearchOption( )
	{
		buyerSearchItemList_ = new ArrayList<BuyerSearchItem>( );
		category_ = null;
                country_=null;
		keyword_ = null;
		checkAll_ = false;
		searchRegd_ = false;
		supplierKey_ = null;
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
		checkAll_ = false;
		searchRegd_ = false;
		supplierKey_ = null;
		buyerSearchItemList_ = null;
	}
	
	/**
	 * 
	 */
	public void show( )
	{
		
		errorMaster_.insert( "category_						= " + category_ );
		errorMaster_.insert( "keyword_						= " + keyword_ );
		errorMaster_.insert( "buyerSearchItemList_ Size	= "  + buyerSearchItemList_.size( ) );
		errorMaster_.insert( "checkAll_						= " + checkAll_ );
		errorMaster_.insert( "searchRegd_				= " + searchRegd_ );
		errorMaster_.insert( "supplierKey_				= " + supplierKey_ );
		
	}
}
