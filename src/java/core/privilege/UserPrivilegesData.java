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
import utils.ErrorMaster;

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
        
        private static ErrorMaster errorMaster_ = null;



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
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "userKey_		= " + userKey_ );
		errorMaster_.insert( "addNewUser_	= " + addNewUser_ );
		errorMaster_.insert( "deleteUser_	= " + deleteUser_ );

		errorMaster_.insert( "uploadDoc_		= " + uploadDoc_ );
		errorMaster_.insert( "deleteDoc_		= " + deleteDoc_ );

		errorMaster_.insert( "addBuySupplier_	= " + addBuySupplier_ );
		errorMaster_.insert( "deleteBuySupplier_	= " + deleteBuySupplier_ );

		errorMaster_.insert( "accessUserMgmt_	= " + accessUserMgmt_ );
		errorMaster_.insert( "postAnnouncement_	= " + postAnnouncement_ );
		errorMaster_.insert( "deleteAnnouncement_= " + deleteAnnouncement_ );

		errorMaster_.insert( "rateBuySupplier_	= " + rateBuySupplier_ );
		errorMaster_.insert( "createUserGroup_	= " + createUserGroup_ );
		errorMaster_.insert( "deleteUserGroup_	= " + deleteUserGroup_ );

		errorMaster_.insert( "manageFolder_		= " + manageFolder_ );
		errorMaster_.insert( "privilegesValue_	= " + privilegesValue_ );
		errorMaster_.insert( "userType_			= " + userType_ );

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
