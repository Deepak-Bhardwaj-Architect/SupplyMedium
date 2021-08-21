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
 * File:  BuyerSearchResult.java 
 *
 * Created on Jul 19, 2013 7:12:15 PM
 */
public class BuyerSearchResult
{
	public CompanyRegnKey 	regnKey_;
	
	public String 			companyName_;
	
	public Boolean 			isRegn_;
	
	public float 			avg_ratings_;
	
	public long	            no_of_ratings_;
        private static ErrorMaster errorMaster_ = null;


	
	
	/**
	 * Method 	: clear( ) 
	 * Input 	: None 
	 * Return 	: None
	 * 
	 * Purpose	: To reset the class variables.
	 */
	public  	BuyerSearchResult( )
	{
		regnKey_ 			= null;
		companyName_ 		= null;
		isRegn_				= false;
		avg_ratings_ 		= 0.0f;
		no_of_ratings_      = 0;
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	
	
	/**
	 * Method : show( ) 
	 * Input  : None 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */
	public void show( )
	{
		regnKey_.show();
		errorMaster_.insert( "Company Name	= " + companyName_ );
		errorMaster_.insert( "Is Registered ? : " + isRegn_.toString( ) );
		errorMaster_.insert( "avg_ratings_ ="+  avg_ratings_);
		errorMaster_.insert( "no_of_ratings_ ="+  no_of_ratings_);
		
	}
	
	
	/**
	 * Method 	: clear( ) 
	 * Input 	: None 
	 * Return 	: None
	 * 
	 * Purpose	: To reset the class variables.
	 */
	public void clear( )
	{
		regnKey_ 		= null;
		companyName_ 	= null;
		isRegn_			= true;
		avg_ratings_ 		= 0.0f;
		no_of_ratings_     = 0;
	}
}
