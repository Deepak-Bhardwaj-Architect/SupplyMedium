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

import java.sql.Timestamp;

/**
 * File:  DeptFilesRecord.java 
 *
 * Created on May 7, 2013 10:24:06 AM
 */

/*
 * Class: DeptFilesRecord
 * 
 * Purpose: This class maps the table 'dept_files' of supply medium db
 * 
 */

public class DeptFilesRecord
{
	public long deptFileId_;
	
	public String regnRelKey_;
	public String deptRelKey_;
	public String deptFolderRelKey_;
	
	public String relativePath_;
	public String localPath_;
	public String webUrl_;
	public String fileName_;
	
	public String fileType_;
	public double fileSize_;
	
	public int recycleFlag_;
	public Timestamp createdTs_;
	
	/* Constructor */
	
	public DeptFilesRecord( )
	{
		deptFileId_ = 0;
		
		regnRelKey_ = null;
		deptRelKey_ = null;
		deptFolderRelKey_ = null;
		
		relativePath_	= null;
		localPath_		= null;
		webUrl_			= null;
		fileName_		= null;
		
		fileType_	= null;
		fileSize_   = 0.0f;
		
		recycleFlag_	= 0;
		createdTs_	= null;
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
		System.out.println( "regnRelKey_	= " + regnRelKey_ );
		System.out.println( "deptRelKey_	= " + deptRelKey_ );
		
		System.out.println( "deptFolderRelKey_	= " + deptFolderRelKey_ );
		System.out.println( "relativePath_		= " + relativePath_ );
		
		System.out.println( "localPath_			= " + localPath_ );
		System.out.println( "webUrl_			= " + webUrl_ );
		System.out.println( "fileName_			= " + fileName_ );
		
		System.out.println( "fileType_			= " + fileType_ );
		System.out.println( "fileSize_			= " + fileSize_ );
		
		System.out.println( "recycleFlag_	= " + recycleFlag_ );
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
		deptFileId_ = 0;
		regnRelKey_ = null;
		deptRelKey_ = null;
		
		deptFolderRelKey_ = null;
		relativePath_	= null;
		localPath_		= null;
		webUrl_			= null;
		fileName_		= null;
		
		fileType_	= null;
		fileSize_   = 0.0f;
		recycleFlag_	= 0;
		createdTs_	= null;
	}
	
}
