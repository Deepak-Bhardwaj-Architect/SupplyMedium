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

import utils.ErrorMaster;

/**
 * File:  BuyerSearchItem.java 
 *
 * Created on Jul 19, 2013 7:10:09 PM
 */
public class BuyerSearchItem
{
	public String partno_;
	public String name_;
        private static ErrorMaster errorMaster_ = null;


	
	/**
	 * 
	 */
	public void clear( )
	{
		partno_ = null;
		name_ = null;
	}
	
	/**
	 * 
	 */
	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		System.out.println( "partno_	= " + partno_ );
		System.out.println( "name_	= " + name_ );
	}
}
