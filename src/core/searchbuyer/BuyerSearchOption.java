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

import core.regn.CompanyRegnKey;

/**
 * File:  BuyerSearchOption.java 
 *
 * Created on Jul 19, 2013 7:10:25 PM
 */
public class BuyerSearchOption
{
	public String 				category_;
	public String 				keyword_;
	public Boolean 				searchRegd_;
	public CompanyRegnKey 		supplierKey_;
	
	
	
	
	/**
	 * 
	 */
    public BuyerSearchOption()
    {
	    // TODO Auto-generated constructor stub
    	searchRegd_ = false;
    }


	/**
	 * 
	 */
	public void clear( )
	{
		category_ 	= null;
		keyword_ 	= null;
		searchRegd_ = null;
		supplierKey_ 	= null;
	}
	
	
	/**
	 * 
	 */
	public void show( )
	{
		System.out.println( "category_		= " + category_ );
		System.out.println( "keyword_		= " + keyword_  );
		System.out.println( "searchRegd_	= " + searchRegd_ );
		System.out.println( "supplierKey_		= " + supplierKey_ );
	}
}
