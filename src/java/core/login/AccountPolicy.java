package core.login;

import core.regn.LoginData;

/*
 * Class  : AccountPolicy (Interface)
 * Purpose: This interface enforces the PasswordPolicies and LockoutPolicies for login 
 */

public interface AccountPolicy
{

	/*
	 * Method : enforce( ) Input : AccountPolicyData obj, LoginData obj,
	 * LoginError obj Return : int
	 * 
	 * Purpose: It is used to enforce PasswordPolicies and LockoutPolicies for
	 * login
	 */

	public int enforce( AccountPolicyData accPolicyData, LoginData loginData );
}
