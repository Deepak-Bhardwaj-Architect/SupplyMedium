package core.login;

public class LoginError
{

	/*
	 * Class : LoginError
	 * 
	 * Purpose: It contains the data variables which are used to map login error
	 * / login result
	 */

	public int    errorCode_;
	public String errorString_;

	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "errorCode_		= " + errorCode_ );
		System.out.println( "errorString	= " + errorString_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: It is used to reset all class variable values
	 */

	public void clear( )
	{
		errorCode_ = 0;
		errorString_ = null;
	}
}
