package db.login;

import utils.ErrorMaster;

/*
 * Class  : PasswordPoliciesRecord
 *
 * Purpose: This is the mapper class for PasswordPoliciesTable
 * 
 */

public class PasswordLoginPoliciesRecord
{
	public String regnRelKey_;
	public int    passwordComplexityFlag_;
	public int    upperLowerFlag_;

	public int    numericFlag_;
	public int    specialCharactersFlag_;
	public int    changePasswordFlag_;

	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "regnRelKey_		= " + regnRelKey_ );
		errorMaster_.insert( "passwordComplexityFlag_ 	= " + passwordComplexityFlag_ );
		errorMaster_.insert( "upperLowerFlag_		= " + upperLowerFlag_ );

		errorMaster_.insert( "numericFlag_		= " + numericFlag_ );
		errorMaster_.insert( "specialCharactersFlag_	= " + specialCharactersFlag_ );
		errorMaster_.insert( "changePasswordFlag_	= " + changePasswordFlag_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: It is used to reset all class variable values
	 */

	public void clear( )
	{
		this.regnRelKey_ = null;
		this.passwordComplexityFlag_ = 0;
		this.upperLowerFlag_ = 0;

		this.numericFlag_ = 0;
		this.specialCharactersFlag_ = 0;
		this.changePasswordFlag_ = 0;
	}

}
