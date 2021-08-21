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
import utils.ErrorMaster;

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
             ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "passwordHistoryId_	= " + passwordHistoryId_ );
		
		errorMaster_.insert( "regnRelKey_	= " + regnRelKey_ );
		errorMaster_.insert( "userRelKey_	= " + userRelKey_ );

		errorMaster_.insert( "password_		= " + password_ );
		errorMaster_.insert( "timeVal_		= " + timeVal_ );
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
