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

package core.dept;

import core.regn.CompanyRegnKey;
import utils.ErrorMaster;

/**
 * File:  DeptFolderKey.java 
 *
 * Created on Mar 5, 2013 4:28:01 PM
 */

/*
 * This class contain the department folder key.
 * Concatenation (with :) of the all class variable create the department folder key.
 */

public class DeptFolderKey
{
	public CompanyRegnKey companyRegnKey_;
	public String  folderName_;
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "deptKey_		= " + companyRegnKey_ );
		errorMaster_.insert( "folderName_	= " + folderName_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		companyRegnKey_ 	= null;
		folderName_ 		= null;
	}
	
	 @Override
	 public String toString( ) // still not final, but will work now.
	 {
	     return companyRegnKey_.toString( )+":"+folderName_;
	 }
	    
}
