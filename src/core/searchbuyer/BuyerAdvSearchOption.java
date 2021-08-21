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

/**
 * File:  BuyerAdvSearchOption.java 
 *
 * Created on Jul 19, 2013 7:08:27 PM
 */
public class BuyerAdvSearchOption
{
	public String 				category_;
	public String 				keyword_;
	public List <BuyerSearchItem> 	buyerSearchItemList_;
	public Boolean 			checkAll_;
	public Boolean 			searchRegd_;
	public CompanyRegnKey 		supplierKey_;
	
	public BuyerAdvSearchOption( )
	{
		buyerSearchItemList_ = new ArrayList<BuyerSearchItem>( );
		category_ = null;
		keyword_ = null;
		checkAll_ = false;
		searchRegd_ = false;
		supplierKey_ = null;
		
	}
	/**
	 * 
	 */
	public void clear( )
	{
		
		category_ = null;
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
		
		System.out.println( "category_						= " + category_ );
		System.out.println( "keyword_						= " + keyword_ );
		System.out.println( "buyerSearchItemList_ Size	= "  + buyerSearchItemList_.size( ) );
		System.out.println( "checkAll_						= " + checkAll_ );
		System.out.println( "searchRegd_				= " + searchRegd_ );
		System.out.println( "supplierKey_				= " + supplierKey_ );
		
	}
}
