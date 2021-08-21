package db.login;

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
		System.out.println( "regnRelKey_		= " + regnRelKey_ );
		System.out.println( "passwordComplexityFlag_ 	= " + passwordComplexityFlag_ );
		System.out.println( "upperLowerFlag_		= " + upperLowerFlag_ );

		System.out.println( "numericFlag_		= " + numericFlag_ );
		System.out.println( "specialCharactersFlag_	= " + specialCharactersFlag_ );
		System.out.println( "changePasswordFlag_	= " + changePasswordFlag_ );
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
