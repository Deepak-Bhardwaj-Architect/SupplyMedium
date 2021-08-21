package tester.useracctmgmt;

public class UserAcctMgmtTest
{

	NotifySettingsTest notifySettingsTest_;
	PasswordResetTest passwordResetTest_;
	WhTest whTest_;
	
	public UserAcctMgmtTest()
	{
		notifySettingsTest_ = new NotifySettingsTest( );
		passwordResetTest_  = new PasswordResetTest( );
		whTest_				= new WhTest( );
	}
	
	public void executeTest( )
	{
		notifySettingsTest_.testAll( );
		passwordResetTest_.testAll( );
		whTest_.testAll( );
	}

}
