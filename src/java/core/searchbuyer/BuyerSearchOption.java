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
import utils.ErrorMaster;

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
        
        private static ErrorMaster errorMaster_ = null;


	
	
	
	
	/**
	 * 
	 */
    public BuyerSearchOption()
    {
	    // TODO Auto-generated constructor stub
    	searchRegd_ = false;
        if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( "category_		= " + category_ );
		errorMaster_.insert( "keyword_		= " + keyword_  );
		errorMaster_.insert( "searchRegd_	= " + searchRegd_ );
		errorMaster_.insert( "supplierKey_		= " + supplierKey_ );
	}
}
