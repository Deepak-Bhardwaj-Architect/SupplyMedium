package core.login;

import java.sql.Timestamp;
import utils.ErrorMaster;

/*
 * Class  : LoginAttemptsData
 *
 * Purpose: It contains the data variables related to LoginAttemptsTable
 */

public class LoginAttemptsData
{
	public String    email_;
	public int       invalidAttemptsCount_;

	public Timestamp lockoutTimestamp_;
	public Timestamp attemptedTimestamp_;
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "email_			= " + email_ );
		errorMaster_.insert( "invalieAttemptsCount_	= " + invalidAttemptsCount_ );

		errorMaster_.insert( "lockoutTimestamp_		= " + lockoutTimestamp_ );
		errorMaster_.insert( "attemptedTimestamp_	= " + attemptedTimestamp_ );
	}

	/*
	 * Method : clear( ) Input : None Return : void
	 * 
	 * Purpose: It is used to reset all class variable values
	 */

	public void clear( )
	{
		this.email_ = null;
		this.invalidAttemptsCount_ = 0;

		this.lockoutTimestamp_ = null;
		this.attemptedTimestamp_ = null;
	}
}
