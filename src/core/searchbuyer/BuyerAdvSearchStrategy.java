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


/**
 * File:  BuyerAdvanceSearchStrategy.java 
 *
 * Created on Jul 19, 2013 7:06:52 PM
 */
public class BuyerAdvSearchStrategy
{
	private static AdvBuyerSearch asb_ = null;
	
	/**
	 * Method : BuyerAdvSearchStrategy( ) - constructor 
	 * Input  :  
	 * Return :  
	 * 
	 * Purpose: It is used for initialization
	 */
	private BuyerAdvSearchStrategy()
	{
	}
	
	/**
	 * 
	 */
	public static AdvBuyerSearch createObject( BuyerAdvSearchOption searchOptionObj )
	{		
		
		// search only in Registered buyers
		if( searchOptionObj.searchRegd_ )
		{
			asb_ = new BuyerAdvRegnSearch( );
			return asb_;
			
		}
		// search in All Buyers 
		else
		{
			asb_ = new BuyerAdvSearch( );
			return asb_;
			
		}
		
	}
	
}
