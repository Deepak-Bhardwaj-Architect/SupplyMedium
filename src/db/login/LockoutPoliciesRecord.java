package db.login;

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
		System.out.println( "regnRelKey_		= " + regnRelKey_ );
		System.out.println( "invalidLoginAttempts_	= " + invalidLoginAttempts_ );
		System.out.println( "lockoutDurationMin_	= " + lockoutDurationMin_ );

		System.out.println( "resetLockoutDurationMin_	= " + resetLockoutDurationMin_ );
		System.out.println( "adminUnlockFlag_		= " + adminUnlockFlag_ );
		System.out.println( "expireSessionMin_		= " + expireSessionMin_ );
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
