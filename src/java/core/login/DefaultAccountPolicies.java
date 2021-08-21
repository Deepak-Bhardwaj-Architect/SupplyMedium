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

/**
 * File:  DefaultAccountPolicies.java 
 *
 * Created on Mar 9, 2013 11:04:56 AM
 */


/*
 * Class  : DefaultAccountPolicies
 *
 * Purpose: This class is used to set and get default password policies,
 * password login policies and lockout policies
 * 
 */

public class DefaultAccountPolicies
{
	
	/*
	 * Method 	: setDefaults( ) 
	 * Input 	: AccountPolicyData object 
	 * Return 	: int value specifies the result of the operation (Success/failed)
	 * 
	 * Purpose	: This method gets AccountPolicyData object as parameter. This object
	 * contains PasswordPolicyData, LockoutPolicyData and PasswordLoginPolicyData.
	 * 
	 * By using these values from these objects, the default policies are set by making
	 * corresponding calls to DefaultPasswordLoginPolicies, DefaultPasswordPolicies and
	 * DefaultLockoutPolicies classes.
	 */
	
	public int setDefaults( AccountPolicyData accPolicyData ) 
    {
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		DefaultPasswordLoginPolicies defaultPassLoginPolicies = new DefaultPasswordLoginPolicies( );
		
		int passLoginPolicyResult = 0;
		
		passLoginPolicyResult = defaultPassLoginPolicies.setDefaults( accPolicyData.passLoginPolicyData_ );    
        	
		if( passLoginPolicyResult != 0 ) // get the default account policies
		                                 // failed
		{
			errLogger.logMsg( "DefaultAccountPolicies :: set-defaultPassLoginPolicies :: Error" );

			defaultPassLoginPolicies = null;

			return 105;
		}
		
		int passPolicyResult = 0;
		
		DefaultPasswordPolicies defaultPassPolicies	= new DefaultPasswordPolicies( );		
		
		passPolicyResult = defaultPassPolicies.setDefaults( accPolicyData.passPolicyData_ ); 

		if( passPolicyResult != 0 ) // get the default account policies
		                                 // failed
		{
			errLogger.logMsg( "DefaultAccountPolicies :: set-defaultPassPolicies :: Error " );
			
			defaultPassPolicies = null;

			return 105;
		}
		
		int lockoutPolicyResult = 0;
		
		DefaultLockoutPolicies defaultLockoutPolicies = new DefaultLockoutPolicies( );
		
		lockoutPolicyResult = defaultLockoutPolicies.setDefaults( accPolicyData.lockoutPolicyData_ );
		
		if( lockoutPolicyResult != 0 ) // get the default account policies
		                                 // failed
		{
			errLogger.logMsg( "DefaultAccountPolicies :: set-defaultPassLockoutPolicies :: Error "  );
		
			defaultLockoutPolicies = null;

			return 105;
		}
		
		return 0; //Fetched successfully
    }
	
	/*
	 * Method 	: getDefaults( ) 
	 * Input 	: AccountPolicyData object 
	 * Return 	: int value specifies the result of the operation (Success/failed)
	 * 
	 * Purpose	: This method gets AccountPolicyData object as parameter. This object
	 * contains PasswordPolicyData, LockoutPolicyData and PasswordLoginPolicyData.
	 * 
	 * By using these values from these objects, the default policies are retrieved by making
	 * corresponding calls to DefaultPasswordLoginPolicies, DefaultPasswordPolicies and
	 * DefaultLockoutPolicies classes.
	 */
	
	public int getDefaults( AccountPolicyData accPolicyData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		DefaultPasswordLoginPolicies defaultPassLoginPolicies = new DefaultPasswordLoginPolicies( );
		
		int passLoginPolicyResult = 0;
		
		PasswordLoginPolicyData data = new PasswordLoginPolicyData( );
			
		data.regnRelKey_ = accPolicyData.regnRelKey_;
			
		accPolicyData.passLoginPolicyData_ =data ;
			
		passLoginPolicyResult = defaultPassLoginPolicies.getDefaults( accPolicyData.passLoginPolicyData_ );    

		if( passLoginPolicyResult != 0 ) // get the default account policies
		                                 // failed
		{
			errLogger.logMsg( "DefaultAccountPolicies :: get-defaultPassLoginPolicies :: Error" );
		        
		    defaultPassLoginPolicies = null;
		        
		    return 105;
		}
		
		int passPolicyResult = 0;
		
		DefaultPasswordPolicies defaultPassPolicies	= new DefaultPasswordPolicies( );
		
		PasswordPolicyData passPolicy = new PasswordPolicyData( );
			
		passPolicy.regnRelKey_ = accPolicyData.regnRelKey_;
			
		accPolicyData.passPolicyData_ =	passPolicy ;
			
		passPolicyResult = defaultPassPolicies.getDefaults( accPolicyData.passPolicyData_ );     
        
		if( passPolicyResult != 0 ) // get the default account policies
		                                 // failed
		{
			errLogger.logMsg( "DefaultAccountPolicies :: get-defaultPassPolicies :: Error" );
        	
        	defaultPassPolicies = null;
        	
        	return 105;
		}
		
		int lockoutPolicyResult = 0;
		
		DefaultLockoutPolicies defaultLockoutPolicies = new DefaultLockoutPolicies( );
		
		LockoutPolicyData lockoutPolicy = new LockoutPolicyData( );
			
		lockoutPolicy.regnRelKey_ = accPolicyData.regnRelKey_;
			
		accPolicyData.lockoutPolicyData_ =	lockoutPolicy ;
			
		lockoutPolicyResult = defaultLockoutPolicies.getDefaults( accPolicyData.lockoutPolicyData_ );   
        
		if( lockoutPolicyResult != 0 ) // get the default account policies
		                                 // failed
		{
			errLogger.logMsg( "DefaultAccountPolicies :: get-defaultLockoutPolicies :: Error" );
	       	 
       	 	defaultLockoutPolicies = null;
       	 
       	 	return 105;
		}
		
		return 0;
	}
}
