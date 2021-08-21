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

package core.managefolder;

import core.dept.DeptFolderKey;
import core.dept.DeptKey;
import core.regn.CompanyRegnKey;
import core.usermgmt.GroupKey;
import utils.ErrorMaster;

/**
 * File:  GroupFolderData.java 
 *
 * Created on May 10, 2013 5:49:28 PM
 */

/*
 * Class: GroupFolderData
 * 
 * Purpose: This class contains the domain object for assigning groups 
 * through manage folders view
 */

public class GroupFolderData
{
	public long 			groupFolderAccessId_;

	public CompanyRegnKey 	regnKey_;
	public GroupKey 		groupKey_;
	public DeptFolderKey 	folderKey_;
	
	public DeptKey			deptKey_;
	
	public int 				readFlag_;
	public int 				readWriteFlag_;
        
        private static ErrorMaster errorMaster_ = null;


	
	/*Constructor*/
	
	public GroupFolderData( )
	{
		groupFolderAccessId_ = 0;
		
		readFlag_ 		= 0;
		readWriteFlag_ 	= 0;
		
		regnKey_ 	= new CompanyRegnKey( );
		groupKey_ 	= new GroupKey( );
		
		deptKey_	= new DeptKey( );
		folderKey_ 	= new DeptFolderKey( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( "groupFolderAccessId_	= " + groupFolderAccessId_ );
		
		errorMaster_.insert( "regnKey_	= " + regnKey_.toString( ) );
		errorMaster_.insert( "groupKey_	= " + groupKey_.companyRegnKey_.toString( ) + ":" + groupKey_.groupName_ );
		errorMaster_.insert( "folderKey_	= " + folderKey_.companyRegnKey_.toString( ) + ":" + folderKey_.folderName_ );
		
		errorMaster_.insert( "deptKey_	= " + deptKey_.toString( ) );
		
		errorMaster_.insert( "readFlag_		= " + readFlag_ );
		errorMaster_.insert( "readWriteFlag_ = " + readWriteFlag_ );
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
		groupFolderAccessId_ = 0;
		
		regnKey_ 	= null;
		groupKey_ 	= null;
		folderKey_ 	= null;
		
		deptKey_	= null;
		
		readFlag_ 		= 0;
		readWriteFlag_ 	= 0;
	}
}
