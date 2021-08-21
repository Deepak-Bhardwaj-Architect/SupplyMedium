package db.login;

import java.sql.Timestamp;

/*
 * Class  : LoginAttemptsRecord
 *
 * Purpose: This is the mapper class for LoginAttemptsTable
 * 
 */

public class LoginAttemptsRecord
{
	public String    email_;
	public int       invalidAttemptsCount_;
	public Timestamp lockoutTimestamp_;

	public int       accountLockFlag_;
	public Timestamp attemptedTimestamp_;

	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "email_			= " + email_ );
		System.out.println( "invalieAttemptsCount_	= " + invalidAttemptsCount_ );
		System.out.println( "lockoutTimestamp_		= " + lockoutTimestamp_ );

		System.out.println( "accountLockFlag_		= " + accountLockFlag_ );
		System.out.println( "attemptedTimestamp_	= " + attemptedTimestamp_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: It is used to reset all class variable values
	 */

	public void clear( )
	{
		this.email_ = null;
		this.invalidAttemptsCount_ = 0;
		this.lockoutTimestamp_ = null;

		this.accountLockFlag_ = 0;
		this.attemptedTimestamp_ = null;
	}
}
