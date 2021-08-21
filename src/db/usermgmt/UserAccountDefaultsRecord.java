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

import java.sql.Timestamp;

/**
 * File:  UserAccountDefaultsRecord.java 
 *
 * Created on Mar 20, 2013 2:30:34 PM
 */

//This DB record class is related to user_account_defaults table in db

public class UserAccountDefaultsRecord
{
	public int	changePasswordFlag_;
	public int  disableAccountFlag_;
	public int	accountExpirationDays_;

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
		System.out.println( "changePasswordFlag_		= " + changePasswordFlag_ );
		System.out.println( "disableAccountFlag_		= " + disableAccountFlag_ );
		System.out.println( "accountExpirationDays_		= " + accountExpirationDays_ );
		
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
		changePasswordFlag_ 	= 0;
		disableAccountFlag_ 	= 0;
		accountExpirationDays_	= 0;
	}
}
