package tester.useracctmgmt;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.useracctmgmt.PasswordResetData;
import ctrl.useracctmgmt.UserAcctMgmtController;

public class PasswordResetTest
{
	PasswordResetData resetData_;
	String userEmail_;
	String companyPhoneNo_;
	
	public PasswordResetTest( )
	{
		resetData_ = new PasswordResetData( );
		
		userEmail_ = "";
		companyPhoneNo_ = "";
		
		resetData_.oldPassword_ = "atpTennis_007";
		resetData_.newPassword_ = "atpTennis_008";
		resetData_.regnKey_ = new CompanyRegnKey( );
		resetData_.regnKey_.companyPhoneNo_ = companyPhoneNo_;
		resetData_.userKey_ = new UserProfileKey( );
		resetData_.userKey_.email_ = userEmail_;
	}
	
	public void testAll( )
	{
		updatePassword( );
	}
	
	private void updatePassword( )
	{
		UserAcctMgmtController userAcctMgmtController = new UserAcctMgmtController( );
		
		userAcctMgmtController.passwordReset( resetData_, false );
		
		userAcctMgmtController = null;
		
	}
	
}
