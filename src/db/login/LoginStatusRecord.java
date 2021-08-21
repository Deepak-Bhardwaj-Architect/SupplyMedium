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
package db.login;

import java.sql.Timestamp;

/**
 * File:  LoginStatusRecord.java 
 *
 * Created on Mar 21, 2013 2:40:07 PM
 */
public class LoginStatusRecord
{
	public String    userKey_;
	public Timestamp loggedInTimestamp_;
	public String	 loginStatus_;

	/*
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "userKey_			= " + userKey_ );
		System.out.println( "loggedInTimestamp_	= " + loggedInTimestamp_ );
		System.out.println( "loginStatus_		= " + loginStatus_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		this.userKey_ = null;
		this.loggedInTimestamp_ = null;
		this.loginStatus_ = null;
	}
}
