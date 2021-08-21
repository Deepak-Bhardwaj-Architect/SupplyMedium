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

import utils.ErrorMaster;

/**
 * File: DeptFolderMappingData.java
 * 
 * Created on Mar 5, 2013 4:26:01 PM
 */

/* 
 * This data class contain department folder relationship data is related 
 * dept_folder_mapping table in database.
 */

public class DeptFolderMappingData
{
	public DeptKey       deptKey_;
	public DeptFolderKey deptFolderKey_;
	public int			 recyleFlag_;
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "deptKey_		= " + deptKey_ );
		errorMaster_.insert( "DeptFolderKey_	= " + deptFolderKey_ );
		errorMaster_.insert( "recyleFlag_	= " + recyleFlag_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		deptKey_ 		= null;
		deptFolderKey_ 	= null;
		recyleFlag_		= 0;
	}
}
