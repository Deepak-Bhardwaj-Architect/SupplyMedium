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

package core.privilege;

import java.util.List;

import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File: AllPrivilegesData.java
 * 
 * Created on Feb 25, 2013 11:27:28 AM
 */

/*
 * This is class contain the all the privilege for the user. This class is
 * decide the user menu and user access controls in front end.
 */
public class AllPrivilegesData
{
	public UserProfileKey                   userProfileKey_;

	// Contain the user privileges
	public UserPrivilegesData               userPrivilegesdata_;

	// Contain the user related departments details and that departments
	// privileges
	public List<DeptPrivilegesData>         deptPrivilegesDataArr_;

	// Contain the user related manage folder privileges
	public List<ManageFolderPrivilegesData> manageFolderPrivilegesDataArr_;
        
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
                
		errorMaster_.insert( "userProfileKey_		= " + userProfileKey_ );

		errorMaster_.insert( "userPrivilegesdata_	= " + userPrivilegesdata_ );

		errorMaster_.insert( "deptPrivilegesDataArr_	= " + deptPrivilegesDataArr_ );

		errorMaster_.insert( "manageFolderPrivilegesDataArr_	= "
		        + manageFolderPrivilegesDataArr_ );
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
		userProfileKey_ = null;

		userPrivilegesdata_ = null;

		deptPrivilegesDataArr_ = null;

		manageFolderPrivilegesDataArr_ = null;
	}

}
