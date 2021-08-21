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

package core.usermgmt;

import java.util.ArrayList;
import java.util.List;

import core.regn.UserProfileData;

/**
 * File: GroupData.java
 * 
 * Created on Mar 4, 2013 5:41:04 PM
 */

public class GroupData
{
	public GroupKey groupKey_;
	public String   groupName_;
	public List<UserProfileData> profileDataList_;

	/*Constructor*/
	
	public GroupData( )
	{
		profileDataList_ = new ArrayList<UserProfileData>( );
	}
	
	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "groupKey_		= " + groupKey_ );
		System.out.println( "groupName_		= " + groupName_ );
		System.out.println( "profileDataList_.size = " + profileDataList_.size( ) );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: It is used to reset all class variable values
	 */

	public void clear( )
	{
		this.groupKey_ = null;
		this.groupName_ = null;
		profileDataList_ = null;
	}
}
