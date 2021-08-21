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
import utils.ErrorMaster;

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
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "deptFileId_	= " + deptFileId_ );
		errorMaster_.insert( "regnRelKey_	= " + regnRelKey_ );
		errorMaster_.insert( "deptRelKey_	= " + deptRelKey_ );
		
		errorMaster_.insert( "deptFolderRelKey_	= " + deptFolderRelKey_ );
		errorMaster_.insert( "relativePath_		= " + relativePath_ );
		
		errorMaster_.insert( "localPath_			= " + localPath_ );
		errorMaster_.insert( "webUrl_			= " + webUrl_ );
		errorMaster_.insert( "fileName_			= " + fileName_ );
		
		errorMaster_.insert( "fileType_			= " + fileType_ );
		errorMaster_.insert( "fileSize_			= " + fileSize_ );
		
		errorMaster_.insert( "recycleFlag_	= " + recycleFlag_ );
		errorMaster_.insert( "createdTs_		= " + createdTs_ );
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
