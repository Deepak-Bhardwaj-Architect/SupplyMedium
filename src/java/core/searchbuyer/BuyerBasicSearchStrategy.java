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
 * File:  BuyerBasicSearchStrategy.java 
 *
 * Created on Jul 19, 2013 7:09:34 PM
 */
public class BuyerBasicSearchStrategy
{
	private static BasicBuyerSearch	bsb_	= null;

	/**
	 * Method : BasicSearch( ) - constructor Input : Return :
	 * 
	 * Purpose: It is used for initialization
	 */
	private BuyerBasicSearchStrategy( )
	{
	}

	/**
	 * 
	 */
	public static BasicBuyerSearch createObject( BuyerSearchOption searchOptionObj )
	{

		// search only in Registered nuyers
		if( searchOptionObj.searchRegd_ )
		{
			bsb_ = new BuyerBasicRegnSearch( );
			return bsb_;
			
		}
		// search in All suppliers 
		else
		{
			bsb_ = new BuyerBasicSearch( );
			return bsb_;
			
		}
		
	}
}
