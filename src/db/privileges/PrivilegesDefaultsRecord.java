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
package db.privileges;

/**
 * File: PrivilegesDefaultsRecord.java
 * 
 * Created on Mar 20, 2013 2:29:41 PM
 */

// This DB record class is related to privileges_defaults table in db

public class PrivilegesDefaultsRecord
{

	public int	userPrivileges_;
	public int  groupPrivileges_;
	public int	deptPrivileges_;

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
		System.out.println( "userPrivileges_		= " + userPrivileges_ );
		System.out.println( "groupPrivileges_		= " + groupPrivileges_ );
		System.out.println( "deptPrivileges_		= " + deptPrivileges_ );
		
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
		userPrivileges_ 	= 0;
		groupPrivileges_ 	= 0;
		deptPrivileges_		= 0;
	}
}
