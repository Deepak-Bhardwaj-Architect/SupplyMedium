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
package core.transconfig;

import utils.ErrorMaster;

/**
 * File:  QuantityTypeData.java 
 *
 * Created on Jul 11, 2013 5:10:28 PM
 */
public class QuantityTypeData
{
	public String quantityTypeKey_;
	public String quantityType_;
        private static ErrorMaster errorMaster_ = null;



	public QuantityTypeData( )
	{
		quantityTypeKey_  	= null;
		quantityType_ 	= null;
	}
	
	/*
	 * Method : clear( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		quantityTypeKey_ 		= null;
		quantityType_	= null;
	}

	/*
	 * Method : show( ) 
	 * 
	 * Input  : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "quantityTypeKey_			= " + quantityTypeKey_ );
		errorMaster_.insert( "quantityType_					= " + quantityType_ );
	}
}
