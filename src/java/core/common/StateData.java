package core.common;

//This core data class is related to state table in db

import utils.ErrorMaster;


public class StateData
{
	public String state_;
        
        private static ErrorMaster errorMaster_ = null;



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
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "state_	= " + state_ );
	}
}
