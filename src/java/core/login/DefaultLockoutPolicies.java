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
import db.login.LockoutPoliciesTable;
import db.regn.AccountPoliciesDefaultsTable;

/**
 * File:  DefaultLockoutPolicies.java 
 *
 * Created on Mar 9, 2013 11:05:06 AM
 */

/*
 * Class  : DefaultLockoutPolicies
 *
 * Purpose: This class is used to get and set default lockout policies
 * 
 */

public class DefaultLockoutPolicies
{
	/*
	 * Method : getDefaults( )
	 * 
	 * Input : lockout policy data 
	 * 
	 * Return: int
	 * 
	 * Purpose: It gets the default lockout policies data and assign to
	 * lockout policy data parameter. So that caller class access the data from
	 * lockout policy data object.
	 */

	public int getDefaults( LockoutPolicyData lockoutPolicyData )
	{	
		AccountPoliciesDefaultsTable policyDefaults = new AccountPoliciesDefaultsTable( );

		int policyDefaultsResult = policyDefaults.getLockoutPoliciesDefaults( lockoutPolicyData );
		
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
	 * Purpose: It sets the default lockout policies data 
	 */

	public int setDefaults( LockoutPolicyData lockoutPolicyData )
	{
		if( lockoutPolicyData == null )
		{
			ErrorLogger errLogger = ErrorLogger.instance();
			
			errLogger.logMsg( "DefaultLockoutPolicies : setDefaults :: lockoutPolicyData null " );
			
			return -1;
		}
		LockoutPoliciesTable lockoutPolicies = new LockoutPoliciesTable( );

		int policyResult = lockoutPolicies.insert( lockoutPolicyData );
		
		lockoutPolicies = null;

		return policyResult;
	}
	
}
