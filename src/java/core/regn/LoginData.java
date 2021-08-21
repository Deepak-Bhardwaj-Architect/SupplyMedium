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

package core.regn;

import java.sql.Timestamp;
import utils.ErrorMaster;

/**
 * File:  LoginData.java 
 *
 * Created on Jan 22, 2013 11:27:28 AM
 */

//This core data class is related to user_login table in db

public class LoginData
{

	public String    emailid_;
	public String    password_;
	public Timestamp createdTimestamp_;

	public String    uuid_;
	public String	 loginStatus_;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method 	: clear( ) 
	 * Input 	: None 
	 * Return 	: None
	 * 
	 * Purpose	: To reset the class variables.
	 */

	public void clear( )
	{
		emailid_ 			= null;
		password_ 			= null;
		createdTimestamp_ 	= null;

		uuid_ 				= null;
		loginStatus_		= null;
	}

	/*
	 * Method 	: show( ) 
	 * Input 	: None 
	 * Return 	: None
	 * 
	 * Purpose	: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "emailid_		= " + emailid_ );
		errorMaster_.insert( "password_		= " + password_ );
		errorMaster_.insert( "createdTimestamp_ 	= " + createdTimestamp_ );

		errorMaster_.insert( "uuid_ 				= " + uuid_ );
		errorMaster_.insert( "loginStatus_  		= " + loginStatus_ );
	}
}
