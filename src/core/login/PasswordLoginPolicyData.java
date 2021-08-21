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
package core.login;

/**
 * File:  PasswordLoginPolicyData.java 
 *
 * Created on Mar 9, 2013 11:04:15 AM
 */

/*
 * Class  : PasswordLoginPolicyData
 *
 * Purpose: 
 */

public class PasswordLoginPolicyData
{
	public String regnRelKey_;
	
	//public int    passwordAgeMaxDays_; 
	public int    passwordLength_;  
	public int    passwordComplexityFlag_;
	
	public int    upperLowerFlag_;
	public int    numericFlag_;
	public int    specialCharactersFlag_;
	
	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "regnRelKey_			= " + regnRelKey_ );
		
		//System.out.println( "passwordAgeMaxDays_	= " + passwordAgeMaxDays_ );
		System.out.println( "passwordLength_		= " + passwordLength_ );

		System.out.println( "passwordComplexityFlag_ 	= " + passwordComplexityFlag_ );
		System.out.println( "upperLowerFlag_		= " + upperLowerFlag_ );
		
		System.out.println( "numericFlag_		= " + numericFlag_ );
		System.out.println( "specialCharactersFlag_	= " + specialCharactersFlag_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: It is used to reset all class variable values
	 */

	public void clear( )
	{
		this.regnRelKey_ = null;
		
		//this.passwordAgeMaxDays_ = 0;
		this.passwordLength_ = 0;
	
		this.passwordComplexityFlag_ = 0;
		this.upperLowerFlag_ = 0;
	
		this.numericFlag_ = 0;
		this.specialCharactersFlag_ = 0;
	}
}
