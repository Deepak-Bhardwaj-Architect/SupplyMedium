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

package db.dept;

/**
 * File:  DeptFoldersRecord.java 
 *
 * Created on Mar 5, 2013 3:28:39 PM
 */

//This DB record class is related to dept_folders table in db

public class DeptFolderRecord
{

	public String deptFolderKey_;
	
	public String regnKey_;

	public String folderName_;

	public String folderPath_;

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
		System.out.println( "deptFolderKey_		= " + deptFolderKey_ );
		System.out.println( "regnKey_			= " + regnKey_ );
		System.out.println( "folderName_		= " + folderName_ );
		System.out.println( "folderPath_		= " + folderPath_ );
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
		deptFolderKey_ 	= null;
		regnKey_		= null;
		folderName_ 	= null;
		folderPath_ 	= null;
	}

}
