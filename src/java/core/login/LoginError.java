package core.login;

import utils.ErrorMaster;

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
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "errorCode_		= " + errorCode_ );
		errorMaster_.insert( "errorString	= " + errorString_ );
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
