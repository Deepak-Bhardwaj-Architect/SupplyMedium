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

package db.regn;

import java.sql.Timestamp;

/**
 * File:  UserLoginRecord.java 
 *
 * Created on Jan 5, 2013 3:28:59 PM
 */

//This DB record class is related to user_login table in db

public class UserLoginRecord
{
	public long      userId_;
	public String    emailAddress_;
	public String    encryptedPassword_;

	public Timestamp createdTimestamp_;
	public String    uuid_;

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
		System.out.println( "userId_			= " + userId_ );
		System.out.println( "emailAddress_		= " + emailAddress_ );
		System.out.println( "encryptedPassword_		= " + encryptedPassword_ );

		System.out.println( "createdTimestamp_		= " + createdTimestamp_ );
		System.out.println( "uuid_			= " + uuid_ );
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
		this.userId_ = 0;
		this.emailAddress_ = null;
		this.encryptedPassword_ = null;

		this.createdTimestamp_ = null;
		this.uuid_ = null;
	}
}
