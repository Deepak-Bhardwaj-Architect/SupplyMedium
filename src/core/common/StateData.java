package core.common;

//This core data class is related to state table in db

public class StateData
{
	public String state_;

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		this.state_ = null;
	}

	/*
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "state_	= " + state_ );
	}
}
