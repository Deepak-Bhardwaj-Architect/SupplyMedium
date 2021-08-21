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
import db.login.PasswordLoginPoliciesTable;
import db.regn.AccountPoliciesDefaultsTable;

/**
 * File:  DefaultPasswordLoginPoliciies.java 
 *
 * Created on Mar 9, 2013 11:05:31 AM
 */

/*
 * Class  : DefaultPasswordLoginPoliciies
 *
 * Purpose: This is used to get and set password login policies
 * 
 */

public class DefaultPasswordLoginPolicies
{
	/*
	 * Method : getDefaults( )
	 * 
	 * Input : password login policy data 
	 * 
	 * Return: int
	 * 
	 * Purpose: It gets the default password login policies data and assign to
	 * password login policy data parameter. So that caller class access the data from
	 * password login policy data object.
	 */

	public int getDefaults( PasswordLoginPolicyData passLoginPolicyData )
	{	
		AccountPoliciesDefaultsTable policyDefaults = new AccountPoliciesDefaultsTable( );
		
		passLoginPolicyData.show( );
		
		int policyDefaultsResult = policyDefaults.getPasswordLoginPoliciesDefaults( passLoginPolicyData );
		
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
	 * Purpose: It sets the default password login policies data 
	 */

	public int setDefaults( PasswordLoginPolicyData passLoginPolicyData )
	{
		if( passLoginPolicyData == null )
		{
			ErrorLogger errLogger = ErrorLogger.instance();
			
			errLogger.logMsg( "DefaultPasswordLoginPolicies : setDefaults :: passLoginPolicyData null " );
			
			return -1;
		}
		PasswordLoginPoliciesTable passLoginPoliciesTbl = new PasswordLoginPoliciesTable( );

		int policyResult = passLoginPoliciesTbl.insert( passLoginPolicyData );
		
		passLoginPoliciesTbl = null;

		return policyResult;
	}
}
