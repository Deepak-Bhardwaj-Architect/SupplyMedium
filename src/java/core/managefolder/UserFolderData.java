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
import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File:  UserFolderData.java 
 *
 * Created on May 10, 2013 5:51:11 PM
 */

/*
 * Class: UserFolderData
 * 
 * Purpose: This class contains the domain object for assigning users 
 * through manage folders view
 */

public class UserFolderData
{
	public long userFolderAccessId_;
	
	public CompanyRegnKey regnKey_;
	public UserProfileKey userKey_;
	public DeptFolderKey folderKey_;
	
	public DeptKey	deptKey_;
	
	public int readFlag_;
	public int readWriteFlag_;
        
        private static ErrorMaster errorMaster_ = null;


	
	/*Constructor*/
	
	public UserFolderData( )
	{
		userFolderAccessId_ = 0;
		
		readFlag_ = 0;
		readWriteFlag_ = 0;
		
		regnKey_ = new CompanyRegnKey( );
		userKey_ = new UserProfileKey( );
		folderKey_ = new DeptFolderKey( );
		
		deptKey_	= new DeptKey( );
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
		errorMaster_.insert( "userFolderAccessId_	= " + userFolderAccessId_ );
		
		errorMaster_.insert( "regnKey_	= " + regnKey_.toString( ) );
		errorMaster_.insert( "userKey_	= " + userKey_.email_ );
		errorMaster_.insert( "folderKey_	= " + folderKey_.companyRegnKey_.toString( ) + ":" + folderKey_.folderName_ );
		
		errorMaster_.insert( "deptKey_	= " + deptKey_.toString( ) );
		
		errorMaster_.insert( "readFlag_	= " + readFlag_ );
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
		userFolderAccessId_ = 0;
		
		regnKey_ 	= null;
		userKey_ 	= null;
		folderKey_ 	= null;
		
		deptKey_	= null;
		
		readFlag_ 		= 0;
		readWriteFlag_ 	= 0;
	}
}
