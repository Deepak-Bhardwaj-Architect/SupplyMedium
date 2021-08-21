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

import core.regn.UserProfileKey;

/**
 * File:  UserPrivilegesData.java 
 *
 * Created on Feb 26, 2013 11:27:28 AM
 */

/* 
 * This data class contain user privileges is related 
 * user_privileges table in database. 
 * If variable have true value, user have the that privilege.
 * If variable have false vale, user don't have that privilege.
 */

public class UserPrivilegesData
{
	public UserProfileKey userKey_;
	public boolean        addNewUser_;
	public boolean        deleteUser_;

	public boolean        uploadDoc_;
	public boolean        deleteDoc_;

	public boolean        addBuySupplier_;
	public boolean        deleteBuySupplier_;

	public boolean        accessUserMgmt_;
	public boolean        postAnnouncement_;
	public boolean        deleteAnnouncement_;

	public boolean        rateBuySupplier_;
	public boolean        createUserGroup_;
	public boolean        deleteUserGroup_;

	public boolean        manageFolder_;
	public boolean		   applyThemes_;
	
	public long	   privilegesValue_;
	public String         userType_;

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
		System.out.println( "userKey_		= " + userKey_ );
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
		System.out.println( "privilegesValue_	= " + privilegesValue_ );
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
		userKey_ = null;
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
		privilegesValue_  = 0;
		userType_ = null;
	}

}
