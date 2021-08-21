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

import java.sql.Timestamp;

import core.regn.CompanyRegnKey;

/**
 * File: FilesData.java
 * 
 * Created on May 7, 2013 11:00:56 AM
 */

/*
 * Class: FilesData
 * 
 * Purpose: This is the domain class for performing dept page view's files operations.
 */

public class FileAttributesData
{
	public long 		  deptFileId_;
	
	public CompanyRegnKey regnKey_;

	public DeptKey        deptKey_;

	public DeptFolderKey  deptFolderKey_;

	/*
	 * This is the file path where the actual uploaded file will be located.
	 * There will be one folder for dept. No separate folder for recycle bin. If
	 * recyleFlag_ flag is set, then the file will be shown in recycle bin pop
	 * up.
	 * 
	 * Else, the file will be shown in the folder explorer of department page
	 */

	public String         relativePath_;
	
	public String		  localPath_;
	
	public String		  webUrl_;
	
	public String		  fileName_;
	
	/*
	 * This int is used as boolean such
	 * that 0 or 1 will be stored in DB
	 */
	
	public int            recycleFlag_;  
	
	

	public String         fileType_;
	
	public double         fileSize_;

	
	
	/*
	 * Based on this timestamp, the files are removed from recycle bin if the
	 * file is older than 30 days. This timestamp will be updated whenever the
	 * file is sent to recycle bin or restored to folder from recycle bin
	 */

	public Timestamp      createdTs_;

	/*
	 * Constructor
	 */

	public FileAttributesData()
	{
		deptFileId_		= 0;
		
		regnKey_ 		= new CompanyRegnKey( );
		deptKey_ 		= new DeptKey( );
		deptFolderKey_ 	= new DeptFolderKey( );

		relativePath_	= null;
		localPath_		= null;
		webUrl_			= null;
		fileName_		= null;
		
		recycleFlag_ 	= 0;
		fileType_ 		= null;
		fileSize_ 		= 0.0f;

		createdTs_ = new Timestamp( System.currentTimeMillis( ) );
	}

	/*
	 * Method: show
	 * 
	 * Input: None
	 * 
	 * Return: None
	 * 
	 * Purpose: To display the class variables
	 */

	public void show( )
	{
		System.out.println( "deptFileId_	= " + deptFileId_ );
		System.out.println( "regnKey_		= " + regnKey_.toString( ) );
		System.out.println( "deptKey_		= " + deptKey_.companyRegnKey_ + ":"
		        								+ deptKey_.deptName_ );
		System.out.println( "deptFolderKey_	= " + deptFolderKey_.companyRegnKey_ + ":"
		        								+ deptFolderKey_.folderName_ );

		System.out.println( "relativePath_	= " + relativePath_ );
		System.out.println( "localPath_		= " + localPath_ );
		System.out.println( "webUrl_		= " + webUrl_ );
		System.out.println( "fileName_		= " + fileName_ );
		
		System.out.println( "recycleFlag_	= " + recycleFlag_ );
		
		System.out.println( "fileType_		= " + fileType_ );
		System.out.println( "fileSize_		= " + fileSize_ );

		System.out.println( "createdTs_		= " + createdTs_ );

	}

	/*
	 * Method: clear
	 * 
	 * Input: None
	 * 
	 * Return: None
	 * 
	 * Purpose: To clear the class variables
	 */

	public void clear( )
	{
		deptFileId_		= 0;
		
		regnKey_ 		= null;
		deptKey_ 		= null;
		deptFolderKey_ 	= null;

		relativePath_	= null;
		localPath_		= null;
		webUrl_			= null;
		fileName_		= null;
		
		recycleFlag_ 	= 0;
		fileType_ 		= null;
		fileSize_ 		= 0.0f;
		
		createdTs_ 		= null;
	}

}
