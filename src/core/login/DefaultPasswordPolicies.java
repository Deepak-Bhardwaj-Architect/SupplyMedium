/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any.
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */
package core.login;

import utils.ErrorLogger;
import db.login.PasswordPoliciesTable;
import db.regn.AccountPoliciesDefaultsTable;

/**
 * File:  DefaultPasswordPolicies.java 
 *
 * Created on Mar 9, 2013 11:05:16 AM
 */

/*
 * Class  : DefaultPasswordPolicies
 *
 * Purpose: This is used to get and set password policies
 * 
 */

public class DefaultPasswordPolicies
{
	/*
	 * Method : getDefaults( )
	 * 
	 * Input : password policy data 
	 * 
	 * Return: int
	 * 
	 * Purpose: It gets the default password policies data and assign to
	 * password policy data parameter. So that caller class access the data from
	 * password policy data object.
	 */

	public int getDefaults( PasswordPolicyData passPolicyData )
	{	
		AccountPoliciesDefaultsTable policyDefaults = new AccountPoliciesDefaultsTable( );

		int policyDefaultsResult = policyDefaults.getPasswordPoliciesDefaults( passPolicyData );
		
		//System.out.println( "PasswordPolicyData-passwordLength: "+passPolicyData.pass )
		
		policyDefaults = null;

		return policyDefaultsResult;
	}
	
	/*
	 * Method : setDefaults( )
	 * 
	 * Input : lockout policy data 
	 * 
	 * Return: int
	 * 
	 * Purpose: It sets the default password policies data 
	 */

	public int setDefaults( PasswordPolicyData passPolicyData )
	{
		if( passPolicyData == null )
		{
			ErrorLogger errLogger = ErrorLogger.instance();
			
			errLogger.logMsg( "DefaultPasswordPolicies : setDefaults :: passPolicyData null " );
			
			return -1;
		}
		PasswordPoliciesTable passPoliciesTbl = new PasswordPoliciesTable( );

		int policyResult = passPoliciesTbl.insert( passPolicyData );
		
		passPoliciesTbl = null;

		return policyResult;
	}
}
