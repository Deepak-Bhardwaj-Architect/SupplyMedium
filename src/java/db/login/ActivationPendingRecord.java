package db.login;

import java.sql.Timestamp;
import utils.ErrorMaster;

//This DB record class is related to activation_pending table in db

public class ActivationPendingRecord
{
	public String    emailAddress_;
	public String    encryptedPassword_;

	public Timestamp createdTimestamp_;

	/*
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "emailAddress_		= " + emailAddress_ );
		errorMaster_.insert( "encryptedPassword_		= " + encryptedPassword_ );
		errorMaster_.insert( "createdTimestamp_		= " + createdTimestamp_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		this.emailAddress_ = null;
		this.encryptedPassword_ = null;
		this.createdTimestamp_ = null;
	}
}
