package core.regn;

import java.util.List;

import java.util.HashMap;
import java.util.Map;

import utils.ErrorLogger;

import core.login.AccountPoliciesEnforcer;
import core.login.AccountPolicy;
import core.login.AccountPolicyData;

import core.login.PolicyEnforcerInitializer;

public class AccountPolicyTblBuffer
{

	// private map contain all companies account policies
	public Map<CompanyRegnKey, AccountPoliciesEnforcer> policyMap_ ;
	
	private static AccountPolicyTblBuffer tblBuff_  = null;

	// This is the singleton class. This class initialize at application startup
	public static AccountPolicyTblBuffer instance( )
	{
		if( tblBuff_  == null )
		{
			tblBuff_  = new AccountPolicyTblBuffer( );
		}

		return tblBuff_ ;
	}

	// constructor call the private init method to load the all company account policies
	public AccountPolicyTblBuffer ()
	{
		init( );
	}

	// this method is used  to load the all companies account policies to map
	private void init()
	{
		PolicyEnforcerInitializer policyInit = new PolicyEnforcerInitializer( );
		
		policyMap_  = new HashMap<CompanyRegnKey, AccountPoliciesEnforcer>( );

		policyInit.init( );
		
		policyInit.createPolicyEnforcerMap( policyMap_  );
	}

	// Insert new company account policies enforcer object to map
	public int insert( AccountPoliciesEnforcer enforcerObj )
	{	
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		CompanyRegnKey regnKey = new CompanyRegnKey( );
		
		regnKey.companyPhoneNo_ = enforcerObj.accPolicyData_.regnRelKey_;
		
		if( regnKey.companyPhoneNo_ == null)
		{
			errorLogger.logMsg( "Error::AccountPolicyTblBuffer.insert() - Failed to insert policy map" );
			
			regnKey = null;
			
			return 0;
		}
		
		policyMap_ .put( regnKey, enforcerObj );
		
		regnKey = null;
		
		return 0;
	}

	// update the existing company account policies enforcer object  in map
	public int update( AccountPoliciesEnforcer enforcerObj )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		List<AccountPolicy> accPoliciesList = enforcerObj.accPolicyList_;
		
		AccountPolicyData data = enforcerObj.accPolicyData_;
		
		
		CompanyRegnKey regnKey = new CompanyRegnKey( );
		
		regnKey.companyPhoneNo_ = data.regnRelKey_;
		
		if( accPoliciesList != null && data != null )
		{	
			policyMap_ .put( regnKey, enforcerObj );
		}
		else
		{
			errorLogger.logMsg( "Error::AccountPolicyTblBuffer.update() - Failed to update policy map" );
		}
		
		regnKey = null;
		
		return 0;
	}

	// get the company account policy enforcer object from map for given company regn key
	public int find(  CompanyRegnKey key, AccountPoliciesEnforcer enforcerObj )
	{		
		AccountPoliciesEnforcer accountPoliciesEnforcer = policyMap_ .get( key );
		
		if( accountPoliciesEnforcer == null )
		{
			//Object not exists for given key
			
			return -1;
		}
		
		
		enforcerObj.accPolicyData_ = accountPoliciesEnforcer.accPolicyData_;
		
		enforcerObj.accPolicyList_ = accountPoliciesEnforcer.accPolicyList_;
	
		return 0;
	
	}

	// remove the company account polices enforcer object from map for given company regn key
	public int remove( CompanyRegnKey key )
	{
		AccountPoliciesEnforcer enforcer = new AccountPoliciesEnforcer( );
		
		int result = find( key, enforcer );
		
		if ( result == 0 )
		{
			policyMap_ .remove( key );
		}
		
		return 0;
	}
	
}
