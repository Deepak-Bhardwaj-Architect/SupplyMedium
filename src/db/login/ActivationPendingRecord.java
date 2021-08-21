package db.login;

import java.sql.Timestamp;

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
		System.out.println( "emailAddress_		= " + emailAddress_ );
		System.out.println( "encryptedPassword_		= " + encryptedPassword_ );
		System.out.println( "createdTimestamp_		= " + createdTimestamp_ );
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
