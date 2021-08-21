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

package core.privilege;

import core.dept.DeptData;

/**
 * File: DeptPrivilegesData.java
 * 
 * Created on Feb 26, 2013 11:27:28 AM
 */

/*
 * This data class contain department privileges is related dept_privileges
 * table in database. If variable have true value, user have the that privilege.
 * If variable have false vale, user don't have that privilege.
 */

public class DeptPrivilegesData
{
	public DeptData deptData_;

	public boolean  addFolder_;
	public boolean  deleteFolder_;

	public boolean  addFile_;
	public boolean  deleteFile_;

	public boolean  postAnnouncement_;
	public boolean  manageFolder_;

	public int      privileges_;

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
		System.out.println( "deptKey_		= " + deptData_ );

		System.out.println( "addFolder_		= " + addFolder_ );
		System.out.println( "deleteFolder_	= " + deleteFolder_ );

		System.out.println( "addFile_		= " + addFile_ );
		System.out.println( "deleteFile_	= " + deleteFile_ );

		System.out.println( "manageFolder_	= " + manageFolder_ );
		System.out.println( "postAnnouncement_	= " + postAnnouncement_ );

		System.out.println( "postAnnouncement_	= " + privileges_ );

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
		deptData_ = null;

		addFolder_ = false;
		deleteFolder_ = false;

		addFile_ = false;
		deleteFile_ = false;

		manageFolder_ = false;
		postAnnouncement_ = false;

		privileges_ = 0;
	}

}
