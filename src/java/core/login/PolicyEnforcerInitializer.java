package core.login;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import utils.ErrorLogger;

import core.regn.CompanyRegnKey;
import db.login.PasswordPoliciesTable;
import db.login.LockoutPoliciesTable;
import db.login.PasswordLoginPoliciesTable;
import db.regn.CompanyRegnTable;
import utils.ErrorMaster;

/*
 * Class  : PolicyEnforcerInitializer
 * 
 * Purpose: It is used to initialize the AccountPolicyFactory and store List<AccountPolicy>
 * 		and AccountPolicyData into the PolicyEnforcerMap.
 */

public class PolicyEnforcerInitializer
{
	public List<AccountPolicy> accPoliciesObjList_;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : init( )
	 * 
	 * Input : None
	 * 
	 * Return : void
	 * 
	 * Purpose: It is used to fill the List<AccountPolicy> using
	 * AccountPolicyFactory
	 */

	public void init( )
	{
		AccountPolicyFactory accPolicyFactory = new AccountPolicyFactory( );

		accPoliciesObjList_ = new ArrayList<AccountPolicy>( );

		accPoliciesObjList_.add( accPolicyFactory
		        .createAccPolicyObj( "CompanyLockoutPolicy" ) );

		accPoliciesObjList_.add( accPolicyFactory
		        .createAccPolicyObj( "CompanyPasswordPolicy" ) );
                
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );

	}

	/*
	 * Method : createPolicyEnforcerMap( )
	 * 
	 * Input : Map<String, AccountPoliciesEnforcer>
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used get all company regn key and fills the
	 * AccountPoliciesEnforcer with AccountPolicyData and List<AccountPolicy>
	 */

	public int createPolicyEnforcerMap(
	        Map<CompanyRegnKey, AccountPoliciesEnforcer> policyEnforcerMap )
	{
		List<CompanyRegnKey> companyRegnKeyList = new ArrayList<CompanyRegnKey>( );

		CompanyRegnTable companyRegnTbl = new CompanyRegnTable( );

		int result = companyRegnTbl.getAllCompanyRegnKey( companyRegnKeyList );

		if( result != 0 )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "PolicyEnforcerInitilizer : createPolicyEnforcerMap :: Error"
			        + "occured while getting all company regn keys to fill"
			        + " policy enforcer map";

			errLogger_.logMsg( errorMessage );

			return -1;
		}
		

		for( CompanyRegnKey regnKey : companyRegnKeyList )
		{
			AccountPoliciesEnforcer policyEnforcer = new AccountPoliciesEnforcer( );

			AccountPolicyData accPolicyData = new AccountPolicyData( );
			
			//errorMaster_.insert( "companyregn key="+regnKey.companyPhoneNo_ );

			AccountPolicyData obj = getAccountPoliciesForCompany( regnKey, accPolicyData );
			
			policyEnforcer.accPolicyData_ = obj;

			policyEnforcer.accPolicyList_ = accPoliciesObjList_;

			policyEnforcerMap.put( regnKey, policyEnforcer );
			
			policyEnforcer = null;
			
			accPolicyData = null;
		}

		return 0;
	}

	/*
	 * Method : getAccountPoliciesForCompany( ) : private
	 * 
	 * Input : CompanyRegnKey obj, AccountPolicyData obj
	 * 
	 * Return : AccountPolicyData
	 * 
	 * Purpose: It is used to account policies, password policies and lockout
	 * policies for the given inputs
	 */

	private AccountPolicyData getAccountPoliciesForCompany( CompanyRegnKey regnKey,
	        AccountPolicyData accPolicyData )
	{
		PasswordPoliciesTable passPolicyTbl = new PasswordPoliciesTable( );

		PasswordLoginPoliciesTable passLoginPolicyTbl = new PasswordLoginPoliciesTable( );

		LockoutPoliciesTable lockoutPolicyTbl = new LockoutPoliciesTable( );

		
		PasswordPolicyData passPolicyData = new PasswordPolicyData( );
		
		PasswordLoginPolicyData passLoginPolicyData = new PasswordLoginPolicyData( );
		
		LockoutPolicyData lockoutPolicyData = new LockoutPolicyData( );
		
		
		int passPolicyResult = passPolicyTbl.get( regnKey.companyPhoneNo_,
		        passPolicyData );

		errorMaster_.insert( "AccountPolicyResult =" + passPolicyResult );

		int passLoginPolicyResult = passLoginPolicyTbl.get(
		        regnKey.companyPhoneNo_, passLoginPolicyData );

		errorMaster_.insert( "passwordPolicyResult =" + passLoginPolicyResult );

		int lockoutPolicyResult = lockoutPolicyTbl.get(
		        regnKey.companyPhoneNo_, lockoutPolicyData );

		errorMaster_.insert( "lockoutPolicyResult =" + lockoutPolicyResult );
		
		accPolicyData.regnRelKey_	=	passLoginPolicyData.regnRelKey_;
		accPolicyData.lockoutPolicyData_ = lockoutPolicyData;
		accPolicyData.passLoginPolicyData_ = passLoginPolicyData;
		accPolicyData.passPolicyData_ = passPolicyData;

		return accPolicyData;
	}
	
	/*
	 * Method : createEnforcer( )
	 * 
	 * Input :  CompanyRegnKey regnKey, AccountPoliciesEnforcer enforcer
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used get enforcer object for given company regn key
	 * 
	 */

	public int createEnforcer( CompanyRegnKey regnKey,
	        AccountPoliciesEnforcer enforcer )
	{

		init( );
		
		AccountPolicyData accPolicyData = new AccountPolicyData( );

		errorMaster_.insert( "At Policy Enforcer Initilizer - Company key - "+regnKey.companyPhoneNo_+", enforcer - "+enforcer );
		
		AccountPolicyData obj = getAccountPoliciesForCompany( regnKey,
		        accPolicyData );

		obj.show( );
		
		enforcer.accPolicyData_ = obj;

		enforcer.accPolicyList_ = accPoliciesObjList_;
		
		//enforcer.accPolicyData_.lockoutPolicyData_.show( );
		//enforcer.accPolicyData_.passLoginPolicyData_.show( );
		//enforcer.accPolicyData_.passPolicyData_.show( );
		
		if( enforcer.accPolicyData_ != null && enforcer.accPolicyList_ != null)
		{
			accPolicyData = null;
			return 0;
		}
		else 
		{
			accPolicyData = null;
			return -1;
		}
	}

}
