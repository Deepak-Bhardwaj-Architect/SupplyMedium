package core.login;

import java.util.HashMap;
import java.util.Map;

/**
 * File: RegnFactory.java
 * 
 * Created on Jan 9, 2013 11:27:28 AM
 */

/*
 * Class : AccountPolicyFactory Purpose: This factory method create and return
 * the AccountPolicy object for given request type. It is related to Company
 * Account Policies and Login
 */

public class AccountPolicyFactory
{
	Map<String, AccountPolicy> accountPolicyObjHashMap_;

	/*
	 * Method : createAccPolicyObj( )
	 * 
	 * Input : String
	 * 
	 * Return : AccountPolicy
	 * 
	 * Purpose: It is used to create account policy object based on the request
	 * type. The request types are
	 * 
	 * 1. CompanyLockoutPolicy 2. CompanyPasswordPolicy
	 */

	public AccountPolicy createAccPolicyObj( String requestType )
	{
		return this.accountPolicyObjHashMap_.get( requestType );
	}

	/*
	 * Method : AccountPolicyFactory( ) -- Constructor 
	 * 
	 * Purpose: It is used to
	 * initialize and fill accountPolicyObjHashMap_. The hash map contains
	 * request type and AccountPolicy Object
	 */

	public AccountPolicyFactory()
	{
		accountPolicyObjHashMap_ = new HashMap<String, AccountPolicy>( );

		// This is to enforce LockoutPolicy while login

		accountPolicyObjHashMap_.put( "CompanyLockoutPolicy", new LockoutPolicy( ) );

		// This is to enforce PasswordPolicy while login

		accountPolicyObjHashMap_.put( "CompanyPasswordPolicy", new PasswordPolicy( ) );
	}
}
