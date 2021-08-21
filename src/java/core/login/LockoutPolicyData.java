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
 * File:  LockoutPolicyData.java 
 *
 * Created on Mar 9, 2013 11:03:54 AM
 */

/*
 * Class  : LockoutPolicyData
 *
 * Purpose: 
 */


public class LockoutPolicyData
{
	public String regnRelKey_;
	public int    invalidLoginAttempts_; 
	public int    lockoutDurationMin_;

	public int    resetLockoutDurationMin_;
	public int    adminUnlockFlag_;
	public int    expireSessionMin_;
        
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
		errorMaster_.insert( "invalidLoginAttempts_	= " + invalidLoginAttempts_ );
		errorMaster_.insert( "lockoutDurationMin_	= " + lockoutDurationMin_ );

		errorMaster_.insert( "resetLockoutDurationMin_	= " + resetLockoutDurationMin_ );
		errorMaster_.insert( "adminUnlockFlag_		= " + adminUnlockFlag_ );
		errorMaster_.insert( "expireSessionMin_		= " + expireSessionMin_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: It is used to reset all class variable values
	 */

	public void clear( )
	{
		this.regnRelKey_ = null;
		this.invalidLoginAttempts_ = 0;
		this.lockoutDurationMin_ = 0;

		this.resetLockoutDurationMin_ = 0;
		this.adminUnlockFlag_ = 0;
		this.expireSessionMin_ = 0;
	}
}


