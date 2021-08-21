package db.config;

//This DB record class is related to states table in db

public class StateRecord
{
	public String state_;
	public String country_;

	/*
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "state_ = " + state_ );
		System.out.println( "country_=" + country_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		this.state_ = null;
		this.country_ = null;
	}
}
