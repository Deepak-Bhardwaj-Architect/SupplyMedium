package ctrl.usermgmt;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.login.AccountPolicyData;
import core.regn.CompanyRegnKey;
import core.usermgmt.AccountPolicyManager;
import utils.ErrorMaster;


/*
 * This class is receives http servlet request object from AccountPolicy servlet,
 * parse the request and make call to AccountPolicyManager based on request Type 
 * 
 */
public class AccountPoliciesController
{
	/*
	 * Method : accountPolicies( )
	 * 
	 * Input : HttpServletRequest obj, AccountPolicyData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: This method gets HttpServletRequest obj from servlet, parse the
	 * request and gets the company regn key, account policy data, etc.
	 * 
	 * Using these, it revise the company's account policies through
	 * AccountPolicyManager
	 */
	
	public int processAccPolicies( HttpServletRequest request, AccountPolicyData accPolicyData )
	{
                ErrorMaster errorMaster_ = null;
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		ErrorLogger errorLogger = ErrorLogger.instance( );	
		
		int responseCode = 0;
		
		String requestType = request.getParameter( "RequestType" );
		
		AccountPolicyManager policyMgr = new AccountPolicyManager( );
		
		AccountPolicyDataConverter dataConverter = new AccountPolicyDataConverter( );

		dataConverter.getAccountPolicyData( request, accPolicyData );

		accPolicyData.show( );
		
		CompanyRegnKey key = new CompanyRegnKey( );

		key.companyPhoneNo_ = request.getParameter( "CompanyRegnKey" );
		
		
		if( requestType.equals( "RevisePolicies" ) )
		{

			String errMsg = "Info::AccountPoliciesController.processAccPolicies() - Update Policies Request In Process " +
	                "for company <" + accPolicyData.regnRelKey_.toString( ) + ">";
			
			errorLogger.logMsg( errMsg );
			
			responseCode = policyMgr.update( key, accPolicyData );

			return responseCode;
			
		}
		
		else if( requestType.equals( "GetPolicies" ) )
		{
			String errMsg = "Info::AccountPoliciesController.processAccPolicies() - Fetch Policies Request In Process " +
	                "for company <" + accPolicyData.regnRelKey_.toString( ) + ">";
			
			errorLogger.logMsg( errMsg );
			
			responseCode = policyMgr.find( key, accPolicyData );
			
			errorMaster_.insert( "accPolicyData_"+accPolicyData.lockoutPolicyData_ );
			
			return responseCode;
		}
		
		else
		{
			errorLogger.logMsg( "Error::AccountPoliciesController.processAccPolicies() -  Error while processing " +
					request.getParameter( "RequestType" ) + " request" );
			return 672;
		}

	}

}
