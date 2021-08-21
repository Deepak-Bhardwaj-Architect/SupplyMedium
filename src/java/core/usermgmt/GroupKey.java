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

package core.usermgmt;

import core.regn.CompanyRegnKey;
import utils.ErrorMaster;

/**
 * File:  DeptKey.java 
 *
 * Created on Feb 27, 2013 11:27:28 AM
 */

/*
 * This class contain the group key.
 * Concatenation of the all class variable create the group key.
 * 
 * Concatenated key format: companyRegnKey_:groupName_
 */

public class GroupKey
{
	public CompanyRegnKey companyRegnKey_;

	public String         groupName_;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "email_		= " + groupName_ );

		errorMaster_.insert( "companyRegnKey_	= " + companyRegnKey_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		groupName_ = null;

		companyRegnKey_ = null;
	}
	

	 @Override
	 public String toString( ) //
	 {
	     return companyRegnKey_.toString( )+":"+groupName_;
	 }
	    
}
