package db.dept;

//This DB record class is related to departments table in db

import utils.ErrorMaster;


public class DeptRecord
{
	public String deptKey_;
	public String deptName_;
	public String regnKey_;

	/*
	 * Method : show( ) 
	 * Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "deptKey_		= " + deptKey_ );
		errorMaster_.insert( "deptName_		= " + deptName_ );
		errorMaster_.insert( "regnKey_		= " + regnKey_ );
	}

	/*
	 * Method : clear( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		deptKey_  	= null;
		deptName_  	= null;
		regnKey_  	= null;
	}
}
