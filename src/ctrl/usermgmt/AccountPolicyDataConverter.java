package ctrl.usermgmt;

import javax.servlet.http.HttpServletRequest;

import core.login.AccountPolicyData;
import core.login.LockoutPolicyData;
import core.login.PasswordLoginPolicyData;
import core.login.PasswordPolicyData;



public class AccountPolicyDataConverter
{

	public int getAccountPolicyData( HttpServletRequest request, AccountPolicyData accountPolicyData )
	{
		
		accountPolicyData.regnRelKey_ = request.getParameter( "CompanyRegnKey" );
		
		
		if( request.getParameter( "RequestType" ).equals( "RevisePolicies" ) )
		{
			
			System.out.println( "Password length---------"+  Integer.parseInt( request
			        .getParameter( "pass_length" ) ) );
			PasswordLoginPolicyData passLoginPolicyData = new PasswordLoginPolicyData( );

			PasswordPolicyData passPolicyData = new PasswordPolicyData( );

			LockoutPolicyData lockoutPolicyData = new LockoutPolicyData( );

			passLoginPolicyData.numericFlag_ = Integer.parseInt( request
			        .getParameter( "numeric" ) );

			passLoginPolicyData.passwordComplexityFlag_ = Integer.parseInt( request
			        .getParameter( "pass_comp" ) );

			passLoginPolicyData.passwordLength_ = Integer.parseInt( request
			        .getParameter( "pass_length" ) );

			passLoginPolicyData.specialCharactersFlag_ = Integer.parseInt( request
			        .getParameter( "non_alpha" ) );

			passLoginPolicyData.upperLowerFlag_ = Integer.parseInt( request
			        .getParameter( "upper&lower" ) );

			passLoginPolicyData.regnRelKey_ = accountPolicyData.regnRelKey_;

			accountPolicyData.passLoginPolicyData_ = passLoginPolicyData;

			//

			passPolicyData.dailyRemainderFlag_ = Integer.parseInt( request
			        .getParameter( "daily_rem" ) );

			passPolicyData.notificationRemainderNday_ = Integer.parseInt( request
			        .getParameter( "email_noti" ) );

			passPolicyData.passwordAgeMaxDays_ = Integer.parseInt( request
			        .getParameter( "max_pass" ) );

			passPolicyData.passwordAgeMinDays_ = Integer.parseInt( request
			        .getParameter( "min_pass" ) );

			passPolicyData.passwordHistoryDays_ = Integer.parseInt( request
			        .getParameter( "pass_his" ) );

			passPolicyData.passwordLength_ = Integer.parseInt( request
			        .getParameter( "pass_length" ) );
			;

			passPolicyData.regnRelKey_ = accountPolicyData.regnRelKey_;

			accountPolicyData.passPolicyData_ = passPolicyData;

			//

			lockoutPolicyData.adminUnlockFlag_ = Integer.parseInt( request
			        .getParameter( "unlock_by_admin" ) );

			lockoutPolicyData.expireSessionMin_ = Integer.parseInt( request
			        .getParameter( "session" ) );

			lockoutPolicyData.invalidLoginAttempts_ = Integer.parseInt( request
			        .getParameter( "lockout" ) );

			lockoutPolicyData.lockoutDurationMin_ = Integer.parseInt( request
			        .getParameter( "lockout_dur" ) );

			lockoutPolicyData.resetLockoutDurationMin_ = Integer.parseInt( request
			        .getParameter( "reset_counter" ) );

			lockoutPolicyData.regnRelKey_ = accountPolicyData.regnRelKey_;

			accountPolicyData.lockoutPolicyData_ = lockoutPolicyData;
		}
		
		return 0;
	}
	
}
