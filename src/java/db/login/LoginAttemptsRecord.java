package db.login;

import java.sql.Timestamp;
import utils.ErrorMaster;

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
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "email_			= " + email_ );
		errorMaster_.insert( "invalieAttemptsCount_	= " + invalidAttemptsCount_ );
		errorMaster_.insert( "lockoutTimestamp_		= " + lockoutTimestamp_ );

		errorMaster_.insert( "accountLockFlag_		= " + accountLockFlag_ );
		errorMaster_.insert( "attemptedTimestamp_	= " + attemptedTimestamp_ );
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
