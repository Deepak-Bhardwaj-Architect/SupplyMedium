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

package db.config;

import utils.ErrorMaster;

/**
 * File: PricingOptionRecord.java
 * 
 * Created on Feb 5, 2013 3:29:51 PM
 */

//This DB record class is related to pricing_option table in db

public class PricingOptionRecord
{
	String pricingOptionKey_;
	String plan_;
	long   maxEmployee_;
	long   maxTransaction_;

	/*
	 * Method : show( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "pricingOptionKey_		= " + pricingOptionKey_ );
		errorMaster_.insert( "plan_					= " + plan_ );
		errorMaster_.insert( "maxEmployee_			= " + maxEmployee_ );
		errorMaster_.insert( "maxTransaction_		= " + maxTransaction_ );
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
		this.pricingOptionKey_ = null;
		this.plan_ = null;
		this.maxEmployee_ = 0;
		this.maxTransaction_ = 0;
	}

}
