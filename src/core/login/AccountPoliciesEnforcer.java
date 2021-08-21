package core.login;

import java.util.List;

/*
 * Class  : AccountPoliciesEnforcer
 * Method : None
 * Input  : None
 * Return : None
 *
 * Purpose: This holds AccountPolicyData and List<AccountPolicy> where these are used to 
 * enforce account policies
 * 
 */

public class AccountPoliciesEnforcer
{
	/*
	 * This member variable contains the account policy data object,
	 * which includes PasswordPolicyData, LockoutPolicyData and
	 * PasswordLoginPolicyData 
	 */
	public AccountPolicyData   accPolicyData_;

	/*
	 * This member variable contains the AccountPolicy object list.
	 * The list contains PasswordPolicy object and LockoutPolicy
	 * object
	 * 
	 */
	public List<AccountPolicy> accPolicyList_;
}
