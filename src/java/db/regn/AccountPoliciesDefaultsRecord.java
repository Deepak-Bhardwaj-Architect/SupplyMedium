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

import utils.ErrorMaster;

/**
 * File: AccountPoliciesDefaultsRecord.java
 * 
 * Created on Feb 5, 2013 3:29:51 PM
 */

//This DB record class is related to account_policies_defaults table in db

public class AccountPoliciesDefaultsRecord
{
	public int passwordHistoryDays_;
	public int passwordAgeMaxDays_;

	public int passwordAgeMinDays_;
	public int passwordLength_;
	public int notificationRemainderNday_;

	public int dailyRemainderFlag_;
	public int invalidLoginAttempts_;
	public int lockoutDurationMin_;

	public int resetLockoutDurationMin_;
	public int adminUnlockFlag_;
	public int expireSessionMin_;

	public int passwordComplexityFlag_;
	public int upperLowerFlag_;
	public int numericFlag_;

	public int specialCharactersFlag_;
	public int changePasswordFlag_;

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
		errorMaster_.insert( "passwordHistoryDays_	= " + passwordHistoryDays_ );
		errorMaster_.insert( "passwordAgeMaxDays_	= " + passwordAgeMaxDays_ );

		errorMaster_.insert( "passwordAgeMinDays_	= " + passwordAgeMinDays_ );
		errorMaster_.insert( "passwordLength_		= " + passwordLength_ );
		errorMaster_.insert( "notificationRemainderNday_	= " + notificationRemainderNday_ );

		errorMaster_.insert( "dailyRemainderFlag_	= " + dailyRemainderFlag_ );
		errorMaster_.insert( "invalidLoginAttempts_	= " + invalidLoginAttempts_ );
		errorMaster_.insert( "lockoutDurationMin_	= " + lockoutDurationMin_ );

		errorMaster_.insert( "resetLockoutDurationMin_	= " + resetLockoutDurationMin_ );
		errorMaster_.insert( "adminUnlockFlag_		= " + adminUnlockFlag_ );
		errorMaster_.insert( "expireSessionMin_		= " + expireSessionMin_ );

		errorMaster_.insert( "passwordComplexityFlag_ 	= " + passwordComplexityFlag_ );
		errorMaster_.insert( "upperLowerFlag_		= " + upperLowerFlag_ );
		errorMaster_.insert( "numericFlag_		= " + numericFlag_ );

		errorMaster_.insert( "specialCharactersFlag_	= " + specialCharactersFlag_ );
		errorMaster_.insert( "changePasswordFlag_	= " + changePasswordFlag_ );
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
		this.passwordHistoryDays_ = 0;
		this.passwordAgeMaxDays_ = 0;

		this.passwordAgeMinDays_ = 0;
		this.passwordLength_ = 0;
		this.notificationRemainderNday_ = 0;

		this.dailyRemainderFlag_ = 0;
		this.invalidLoginAttempts_ = 0;
		this.lockoutDurationMin_ = 0;

		this.resetLockoutDurationMin_ = 0;
		this.adminUnlockFlag_ = 0;
		this.expireSessionMin_ = 0;

		this.passwordComplexityFlag_ = 0;
		this.upperLowerFlag_ = 0;
		this.numericFlag_ = 0;

		this.specialCharactersFlag_ = 0;
		this.changePasswordFlag_ = 0;
	}

}
