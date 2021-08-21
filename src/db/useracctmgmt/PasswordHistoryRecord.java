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

package db.useracctmgmt;

import java.sql.Timestamp;

/**
 * File:  PasswordHistoryRecord.java 
 *
 * Created on Apr 22, 2013 2:15:38 PM
 */

//This DB record class is related to password_history table in db

public class PasswordHistoryRecord
{
	public long	  passwordHistoryId_;
	
	public String regnRelKey_;
	public String userRelKey_;
	
	public String password_;
	public Timestamp timeVal_;
	
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
		System.out.println( "passwordHistoryId_	= " + passwordHistoryId_ );
		
		System.out.println( "regnRelKey_	= " + regnRelKey_ );
		System.out.println( "userRelKey_	= " + userRelKey_ );

		System.out.println( "password_		= " + password_ );
		System.out.println( "timeVal_		= " + timeVal_ );
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
		passwordHistoryId_ = 0;
		
		regnRelKey_ = "";
		userRelKey_ = "";
		
		password_	= "";
		timeVal_	= null;
	}
}
