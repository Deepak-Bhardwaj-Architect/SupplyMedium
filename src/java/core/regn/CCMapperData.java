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

package core.regn;

import utils.ErrorMaster;

/**
 * File:  CCMapperData.java 
 *
 * Created on Jan 18, 2013 11:27:28 AM
 */


//This core data class is related to country_company_mapper table in db

public class CCMapperData
{

	public CompanyRegnKey companyRegnKey_;
	public String         countryname_;
	public String         companyname_;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method 	: clear( ) 
	 * Input 	: None 
	 * Return 	: None
	 * 
	 * Purpose	: To reset the class variables.
	 */

	public void clear( )
	{
		companyRegnKey_ 	= null;
		countryname_ 		= null;
		companyname_ 		= null;
	}

	/*
	 * Method 	: show( ) 
	 * Input 	: None 
	 * Return 	: None
	 * 
	 * Purpose	: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "companyRegnKey_		= " + companyRegnKey_ );
		errorMaster_.insert( "countryname_			= " + countryname_ );
		errorMaster_.insert( "companyname_			= " + companyname_ );
	}
}
