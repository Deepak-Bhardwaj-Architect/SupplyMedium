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

import utils.ErrorMaster;

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
        
        private static ErrorMaster errorMaster_ = null;


	
	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "regnRelKey_			= " + regnRelKey_ );
		
		//errorMaster_.insert( "passwordAgeMaxDays_	= " + passwordAgeMaxDays_ );
		errorMaster_.insert( "passwordLength_		= " + passwordLength_ );

		errorMaster_.insert( "passwordComplexityFlag_ 	= " + passwordComplexityFlag_ );
		errorMaster_.insert( "upperLowerFlag_		= " + upperLowerFlag_ );
		
		errorMaster_.insert( "numericFlag_		= " + numericFlag_ );
		errorMaster_.insert( "specialCharactersFlag_	= " + specialCharactersFlag_ );
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
