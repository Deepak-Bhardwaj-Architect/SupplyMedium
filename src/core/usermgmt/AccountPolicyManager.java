package core.usermgmt;

import utils.ErrorLogger;
import core.login.AccountPoliciesEnforcer;
import core.login.AccountPolicyData;
import core.login.DefaultAccountPolicies;
import core.login.LockoutPolicyData;
import core.login.PolicyEnforcerInitializer;

import core.regn.AccountPolicyTblBuffer;
import core.regn.CompanyRegnKey;

import db.login.LockoutPoliciesTable;
import db.login.PasswordLoginPoliciesTable;
import db.login.PasswordPoliciesTable;

/*
 * This class is used to manage operations like insert, update, delete
 * and find account policies of a company
 * 
 */

public class AccountPolicyManager
{

	/*
	 * Method : newPolicy( )
	 * 
	 * Input : CompanyRegnKey obj
	 * 
	 * Return : int value specifies the result of the operation (Success/failed)
	 * 
	 * Purpose : Get the default account policies from supply medium then insert
	 * thae default account policies value to given company
	 */

	public int insertPolicy( CompanyRegnKey regnKey )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );

		AccountPolicyData accountPolicyData = new AccountPolicyData( );

		accountPolicyData.regnRelKey_ = regnKey.companyPhoneNo_;

		DefaultAccountPolicies policies = new DefaultAccountPolicies( );

		int getResult = policies.getDefaults( accountPolicyData );

		if( getResult == 0 )
		{
			int setResult = policies.setDefaults( accountPolicyData );

			if( setResult != 0 )
			{
				errLogger.logMsg( "Error::AccountPolicyManager:insertPolicy - failed to set defaultAccountPolicies" );

				policies = null;
				
				accountPolicyData = null;

				return 651;
			}

			policies = null;

			accountPolicyData = null;

			AccountPolicyTblBuffer tblBuff = AccountPolicyTblBuffer.instance( );

			PolicyEnforcerInitializer enforcerInit = new PolicyEnforcerInitializer( );

			AccountPoliciesEnforcer enforcer = new AccountPoliciesEnforcer( );

			int result = enforcerInit.createEnforcer( regnKey, enforcer );

			if( result == 0 )
			{
				tblBuff.insert( enforcer );
				
				enforcer = null;
				
				enforcerInit = null;
				
				return 650; //Success
			}
			else 
			{
				errLogger.logMsg( "Error::AccountPolicyManager:insertPolicy - failed to update policy enforcer map" );
				
				enforcer = null;
				
				enforcerInit = null;
				
				return 653; 
			}
		} 
		
		else
		{
			errLogger.logMsg( "Error::AccountPolicyManager:insertPolicy - failed to get defaultAccountPolicies" );

			policies = null;
			
			accountPolicyData = null;

			return 652; //Failed to get default account policies
		}
	}

	/*
	 * Method : update( )
	 * 
	 * Input : AccountPolicyData object, CompanyRegnKey obj
	 * 
	 * Return : int value specifies the result of the operation (Success/failed)
	 * 
	 * Purpose : This method gets AccountPolicyData object as parameter. This
	 * object contains PasswordPolicyData, LockoutPolicyData and
	 * PasswordLoginPolicyData.
	 * 
	 * By using these values from these objects, the account policies are
	 * updated by making corresponding calls to PasswordPoliciesTable,
	 * PasswordLoginPoliciesTable and LockoutPolicyTable
	 */

	public int update( CompanyRegnKey regnKey, AccountPolicyData accPolicyData )
	{

		ErrorLogger errorLogger = ErrorLogger.instance( );

		PasswordPoliciesTable passTbl = new PasswordPoliciesTable( );

		int result = passTbl.update( accPolicyData.passPolicyData_ );

		if( result != 0 )
		{
			errorLogger.logMsg( "Error::AccountPolicyManager:update - Error occurred while updating password policies" );
		}
		
		passTbl = null;

		PasswordLoginPoliciesTable passLoginTbl = new PasswordLoginPoliciesTable( );

		result = passLoginTbl.update( accPolicyData.passLoginPolicyData_ );

		if( result != 0 )
		{
			errorLogger.logMsg( "Error::AccountPolicyManager:update - Error occurred while updating password login policies" );
		}
		
		passLoginTbl = null;

		LockoutPoliciesTable lockoutTbl = new LockoutPoliciesTable( );

		result = lockoutTbl.update( accPolicyData.lockoutPolicyData_ );

		if( result != 0 )
		{
			errorLogger.logMsg( "Error::AccountPolicyManager:update - Error occurred while updating lockout policies" );
		}
		
		lockoutTbl = null;

		AccountPolicyTblBuffer tblBuff = AccountPolicyTblBuffer.instance( );

		PolicyEnforcerInitializer enforcerInit = new PolicyEnforcerInitializer( );

		AccountPoliciesEnforcer enforcer = new AccountPoliciesEnforcer( );

		result = enforcerInit.createEnforcer( regnKey, enforcer );

		if( result == 0 )
		{
			tblBuff.update( enforcer );
			
			enforcer = null;
			
			enforcerInit = null;
			
			return 660; //Success
		}
		else 
		{
			errorLogger.logMsg( "Error::AccountPolicyManager:update - Failed to update policy enforcer map" );
			
			enforcer = null;
			
			enforcerInit = null;
			
			return 661; 
		}
	}

	/*
	 * Method : find( )
	 * 
	 * Input : AccountPolicyData object (as ref), CompanyRegnKey obj
	 * 
	 * Return : int value specifies the result of the operation (Success/failed)
	 * 
	 * Purpose : This method gets company regn key as parameter and gets the
	 * filled account policy data (Passed as reference parameter)
	 */

	public int find( CompanyRegnKey regnKey, AccountPolicyData accountPolicyData )
	{
		
		ErrorLogger errorLogger = ErrorLogger.instance( );

		AccountPolicyTblBuffer tblBuff = AccountPolicyTblBuffer.instance( );

		AccountPoliciesEnforcer enforcerObj = new AccountPoliciesEnforcer( );
		

		int result = tblBuff.find( regnKey, enforcerObj );

		if( result == 0 )
		{
			AccountPolicyData localAcPolicyData = enforcerObj.accPolicyData_;
			
			accountPolicyData.regnRelKey_ = localAcPolicyData.regnRelKey_;
			
			accountPolicyData.lockoutPolicyData_ = localAcPolicyData.lockoutPolicyData_;
			
			accountPolicyData.passLoginPolicyData_ = localAcPolicyData.passLoginPolicyData_;
			
			accountPolicyData.passPolicyData_ = localAcPolicyData.passPolicyData_;
			
			
			enforcerObj = null;
			
			return 670; //Success
		}
		else 
		{
			enforcerObj = null;
			
			errorLogger.logMsg( "Error::AccountPolicyManager:find - Failed to find policy enforcer map" );
			
			return 671;
		}
	}

}
