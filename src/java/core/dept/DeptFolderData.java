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
import utils.ErrorMaster;


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
        private static ErrorMaster errorMaster_ = null;


	
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
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( "deptFolderKey_	= " + deptFolderKey_ );
		//errorMaster_.insert( "companyRegnKey_ = "+companyRegnKey_);
		errorMaster_.insert( "filesDataArr_ size = " + filesArr_.size( ) );
		errorMaster_.insert( "folderName_	= " + folderName_ );
		errorMaster_.insert( "folderPath_	= " + folderPath_ );
		errorMaster_.insert( "recycleFlag_	= " + recycleFlag_ );
		errorMaster_.insert( "addFileFlag_	= " + addFileFlag_ );
		errorMaster_.insert( "deleteFileFlag_	= " + deleteFileFlag_ );
		errorMaster_.insert( "isFromMF_	= " + isFromMF_ );
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
