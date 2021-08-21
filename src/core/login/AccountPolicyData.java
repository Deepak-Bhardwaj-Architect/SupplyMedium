package core.login;

/*
 * Class  : AccountPolicyData
 *
 * Purpose: This class holds the LockoutPolicyData object, PasswordPolicyObject and
 * PasswordLoginPolicyData object
 * 
 */

public class AccountPolicyData
{
	public String 				regnRelKey_;
	public LockoutPolicyData 	lockoutPolicyData_;
	
	public PasswordLoginPolicyData 	passLoginPolicyData_;
	public PasswordPolicyData		passPolicyData_;

	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "regnRelKey_		= " + regnRelKey_ );
		System.out.println( "lockoutPolicyData_	= " + lockoutPolicyData_ );
		
		System.out.println( "passLoginPolicyData_	= " + passLoginPolicyData_ );
		System.out.println( "passPolicyData_	= " + passPolicyData_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: It is used to reset all class variable values
	 */

	public void clear( )
	{
		this.regnRelKey_ = null;
		this.lockoutPolicyData_ = null;
		
		this.passLoginPolicyData_ = null;
		this.passPolicyData_ = null;
	}

}
