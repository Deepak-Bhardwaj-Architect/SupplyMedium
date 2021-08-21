package db.dept;

//This DB record class is related to departments table in db

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
		System.out.println( "deptKey_		= " + deptKey_ );
		System.out.println( "deptName_		= " + deptName_ );
		System.out.println( "regnKey_		= " + regnKey_ );
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
