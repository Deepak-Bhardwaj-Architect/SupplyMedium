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
package core.useracctmgmt;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;

/**
 * File: PasswordResetData.java
 * 
 * Created on Apr 22, 2013 12:41:00 PM
 */

/*
 * Class: PasswordResetData
 * 
 * Purpose: This class contains the domain variables for resetting password.
 * All the variables are initialized here.
 * 
 */

public class PasswordResetData
{
	public long			  passwordHistoryId_;
	
	public CompanyRegnKey regnKey_;
	public UserProfileKey userKey_;

	public String         oldPassword_;
	public String         newPassword_;

	/*
	 * Method: PasswordResetData()  -- Constructor
	 * 
	 * Input: none
	 * 
	 * Return: none
	 * 
	 * Purpose: This method intializes the CompanyRegnKey object and
	 * UserProfileKey object
	 */
	
	public PasswordResetData( )
	{
		regnKey_ = new CompanyRegnKey( );
		userKey_ = new UserProfileKey( );
	}

	/*
	 * Method: show()
	 * 
	 * Input: none
	 *  
	 * Return: void
	 *  
	 * Purpose: This method is used to print all 
	 * the domain variables in console
	 * 
	 */
	
	public void show( )
	{
		System.out.println( "passwordHistoryId_ = " + passwordHistoryId_ );
		
		System.out.println( "regnKey_ = " + regnKey_.companyPhoneNo_ );
		System.out.println( "userKey_ = " + userKey_.email_ );
		
		System.out.println( "oldPassword_ = " + oldPassword_ );
		System.out.println( "newPassword_ = " + newPassword_ );
	}
	
	/*
	 * Method: clear()
	 * 
	 * Input: none
	 * 
	 * Return: void
	 * 
	 * Purpose: This method is used to clear/null all
	 * domain variables
	 * 
	 */

	public void clear( )
	{
		passwordHistoryId_ = 0;
		
		regnKey_ = null;
		userKey_ = null;
		
		oldPassword_ = "";
		newPassword_ = "";
	}

}
