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
		System.out.println( "regnKey_	= " + regnKey_ );
		System.out.println( "groupKey_	= " + groupKey_ );
		System.out.println( "folderKey_	= " + folderKey_ );
		
		System.out.println( "deptKey_	= " + deptKey_ );
		
		System.out.println( "readFlag_	= " + readFlag_ );
		System.out.println( "readWriteFlag_ = " + readWriteFlag_ );
		System.out.println( "groupFolderAccessId_ = " + groupFolderAccessId_ );
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
