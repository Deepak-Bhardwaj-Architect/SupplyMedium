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

package core.common;

import utils.ErrorMaster;

/**
 * File:  BusinessCategoryData.java 
 *
 * Created on Jan 18, 2013 11:27:28 AM
 */

// This core data class is related to business_categories table in db

public class BusinessCategoryData
{

	public String businessCategory_;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : clear( ) 
	 * 
	 * Input  : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		businessCategory_ = null;
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( "businessCategory_	= " + businessCategory_ );
	}

}
