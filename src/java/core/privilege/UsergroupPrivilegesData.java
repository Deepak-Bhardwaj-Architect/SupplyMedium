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
import utils.ErrorMaster;

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
		errorMaster_.insert( "groupKey_		= " + groupKey_ );
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
		errorMaster_.insert( "applyThemes_		= " + applyThemes_ );
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
