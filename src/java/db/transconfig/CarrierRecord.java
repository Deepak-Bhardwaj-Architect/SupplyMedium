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
package db.transconfig;

import utils.ErrorMaster;

/**
 * File:  CarrierRecord.java 
 *
 * Created on Jul 11, 2013 5:08:59 PM
 */
public class CarrierRecord
{
	public String carrierName_;

	public CarrierRecord( )
	{
		carrierName_  = null;
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
		carrierName_ = null;
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
             ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "carrierName_			= " + carrierName_ );
	}
}
