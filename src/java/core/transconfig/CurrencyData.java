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
 * File:  CurrencyData.java 
 *
 * Created on Jul 11, 2013 5:10:18 PM
 */
public class CurrencyData
{
	public String currencyKey_;
	public String currencyName_;
        private static ErrorMaster errorMaster_ = null;



	public CurrencyData( )
	{
		currencyKey_  	= null;
		currencyName_ 	= null;
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		currencyKey_ 		= null;
		currencyName_	= null;
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
		errorMaster_.insert( "carrierName_				= " + currencyKey_ );
		errorMaster_.insert( "currencyName_			= " + currencyName_ );
	}
}
