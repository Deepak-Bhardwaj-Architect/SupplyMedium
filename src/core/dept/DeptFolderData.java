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

import java.util.ArrayList;
import java.util.List;


/**
 * File: DeptFolderData.java
 * 
 * Created on Mar 5, 2013 4:24:22 PM
 */

/* 
 * This data class contain department folder data is related 
 * dept_folders table in database.
 */

public class DeptFolderData
{
	public DeptFolderKey deptFolderKey_;
	//public CompanyRegnKey companyRegnKey_;
	
	public List<FileData> filesArr_;
	
	public String        folderName_;
	public String        folderPath_;
	public int			 recycleFlag_;

	public int		addFileFlag_;
	public int		deleteFileFlag_;
	
	public int isFromMF_;
	
	/* Constructor */
	
	public DeptFolderData( )
	{
		filesArr_ = new ArrayList<FileData>( );
		folderName_ = null;
		folderPath_ = null;
		recycleFlag_ = 0;
		addFileFlag_ = 0;
		deleteFileFlag_ = 0;
		isFromMF_ = 0;
	}
	
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
		System.out.println( "deptFolderKey_	= " + deptFolderKey_ );
		//System.out.println( "companyRegnKey_ = "+companyRegnKey_);
		System.out.println( "filesDataArr_ size = " + filesArr_.size( ) );
		System.out.println( "folderName_	= " + folderName_ );
		System.out.println( "folderPath_	= " + folderPath_ );
		System.out.println( "recycleFlag_	= " + recycleFlag_ );
		System.out.println( "addFileFlag_	= " + addFileFlag_ );
		System.out.println( "deleteFileFlag_	= " + deleteFileFlag_ );
		System.out.println( "isFromMF_	= " + isFromMF_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		deptFolderKey_ 	= null;
		//companyRegnKey_ = null;
		filesArr_	= null;
		folderName_ 	= null;
		folderPath_ 	= null;
		recycleFlag_ 	= 0;
		addFileFlag_		= 0;
		deleteFileFlag_ = 0;
		isFromMF_  = 0;
	}
}
