package db.login;

import utils.ErrorMaster;

/*
 * Class  : LockoutPoliciesRecord
 *
 * Purpose: This is the mapper class for LockoutPoliciesTable
 * 
 */

public class LockoutPoliciesRecord
{
	public String regnRelKey_;
	public int    invalidLoginAttempts_;
	public int    lockoutDurationMin_;

	public int    resetLockoutDurationMin_;
	public int    adminUnlockFlag_;
	public int    expireSessionMin_;

	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "regnRelKey_		= " + regnRelKey_ );
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
