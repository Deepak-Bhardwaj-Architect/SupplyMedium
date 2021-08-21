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

package db.managefolder;

import utils.ErrorMaster;

/**
 * File:  GroupFolderAccessRecord.java 
 *
 * Created on May 10, 2013 4:16:24 PM
 */

/*
 * Class: GroupFolderAccessRecord 
 * 
 * Purpose: This class contains the variables that wraps group_folder_access table
 */

public class GroupFolderAccessRecord
{
	public long groupFolderAccessId_;
	public String regnKey_;
	public String groupKey_;
	public String folderKey_;
	
	public String deptKey_;
	
	public int readFlag_;
	public int readWriteFlag_;
	
	/*Constructor*/
	
	public GroupFolderAccessRecord( )
	{
		groupFolderAccessId_ = 0;
		readFlag_ = 0;
		readWriteFlag_ = 0;
		
		deptKey_ = "";
		
		regnKey_ = "";
		groupKey_ = "";
		folderKey_ = "";
	}
	
	/*
	 * Method: show
	 * 
	 * Input: none
	 * 
	 * Return: void
	 * 
	 * Purpose: This method is used to show the class variables in the console
	 * 
	 */
	
	public void show( )
	{
           ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "regnKey_	= " + regnKey_ );
		errorMaster_.insert( "groupKey_	= " + groupKey_ );
		errorMaster_.insert( "folderKey_	= " + folderKey_ );
		
		errorMaster_.insert( "deptKey_	= " + deptKey_ );
		
		errorMaster_.insert( "readFlag_	= " + readFlag_ );
		errorMaster_.insert( "readWriteFlag_ = " + readWriteFlag_ );
		errorMaster_.insert( "groupFolderAccessId_ = " + groupFolderAccessId_ );
	}
	
	/*
	 * Method: clear
	 * 
	 * Input: none
	 * 
	 * Return: void
	 * 
	 * Purpose: This method is used to clear the instance variables
	 */
	
	public void clear( )
	{
		regnKey_ 	= null;
		groupKey_ 	= null;
		folderKey_ 	= null;
		
		deptKey_	= null;
		
		readFlag_ 		= 0;
		readWriteFlag_ 	= 0;
		
		groupFolderAccessId_ = 0;
	}
}
