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
package db.usermgmt;

import utils.ErrorMaster;

/**
 * File:  UserGroupMapRecord.java 
 *
 * Created on Mar 4, 2013 5:22:34 PM
 */
public class UserGroupMapRecord
{
    private static ErrorMaster errorMaster_ = null;


	public String	userRelKey_;
	public String	groupRelKey_;
	public String	userGroupMappingKey_;


	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "userRelKey_			= " + userRelKey_ );
		errorMaster_.insert( "groupRelKey_			= " + groupRelKey_ );
		errorMaster_.insert( "userGroupMappingKey_	= " + userGroupMappingKey_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: It is used to reset all class variable values
	 */

	public void clear( )
	{
		this.userRelKey_ = null;
		this.groupRelKey_ = null;
		this.userGroupMappingKey_ = null;
	}
}
