package core.login;

import utils.ErrorMaster;

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
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "regnRelKey_		= " + regnRelKey_ );
		errorMaster_.insert( "lockoutPolicyData_	= " + lockoutPolicyData_ );
		
		errorMaster_.insert( "passLoginPolicyData_	= " + passLoginPolicyData_ );
		errorMaster_.insert( "passPolicyData_	= " + passPolicyData_ );
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
