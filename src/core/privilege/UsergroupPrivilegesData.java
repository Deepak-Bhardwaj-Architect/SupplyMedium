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

import core.usermgmt.GroupKey;

/**
 * File: UsergroupPrivileges.java
 * 
 * Created on Mar 5, 2013 1:10:51 PM
 */
public class UsergroupPrivilegesData
{

	public GroupKey groupKey_;
	public boolean  addNewUser_;
	public boolean  deleteUser_;

	public boolean  uploadDoc_;
	public boolean  deleteDoc_;

	public boolean  addBuySupplier_;
	public boolean  deleteBuySupplier_;

	public boolean  accessUserMgmt_;
	public boolean  postAnnouncement_;
	public boolean  deleteAnnouncement_;

	public boolean  rateBuySupplier_;
	public boolean  createUserGroup_;
	public boolean  deleteUserGroup_;

	public boolean  manageFolder_;
	public boolean 	applyThemes_;
	
	public String   userType_;
	public long	  	privilegesValue_;

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
		System.out.println( "groupKey_		= " + groupKey_ );
		System.out.println( "addNewUser_	= " + addNewUser_ );
		System.out.println( "deleteUser_	= " + deleteUser_ );

		System.out.println( "uploadDoc_		= " + uploadDoc_ );
		System.out.println( "deleteDoc_		= " + deleteDoc_ );

		System.out.println( "addBuySupplier_	= " + addBuySupplier_ );
		System.out.println( "deleteBuySupplier_	= " + deleteBuySupplier_ );

		System.out.println( "accessUserMgmt_	= " + accessUserMgmt_ );
		System.out.println( "postAnnouncement_	= " + postAnnouncement_ );
		System.out.println( "deleteAnnouncement_= " + deleteAnnouncement_ );

		System.out.println( "rateBuySupplier_	= " + rateBuySupplier_ );
		System.out.println( "createUserGroup_	= " + createUserGroup_ );
		System.out.println( "deleteUserGroup_	= " + deleteUserGroup_ );

		System.out.println( "manageFolder_		= " + manageFolder_ );
		System.out.println( "applyThemes_		= " + applyThemes_ );
		System.out.println( "userType_			= " + userType_ );

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
		groupKey_ = null;
		addNewUser_ = false;
		deleteUser_ = false;

		uploadDoc_ = false;
		deleteDoc_ = false;

		addBuySupplier_ = false;
		deleteBuySupplier_ = false;

		accessUserMgmt_ = false;
		postAnnouncement_ = false;
		deleteAnnouncement_ = false;

		rateBuySupplier_ = false;
		createUserGroup_ = false;
		deleteUserGroup_ = false;

		manageFolder_ = false;
		applyThemes_	= false;
		userType_ = null;
	}

}
