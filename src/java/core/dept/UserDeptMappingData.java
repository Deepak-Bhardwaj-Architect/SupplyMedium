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

import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File:  UserDeptMappingData.java 
 *
 * Created on Mar 12, 2013 2:36:29 PM
 */
public class UserDeptMappingData
{
	public DeptKey       deptKey_;
	public UserProfileKey userKey_;
        
        private static ErrorMaster errorMaster_ = null;


	
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
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "deptKey_		= " + deptKey_ );
		errorMaster_.insert( "userKey		= " + userKey_ );
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
		deptKey_ 		= null;
		userKey_ 		= null;
	}
}
