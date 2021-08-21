package tester.useracctmgmt;

import java.sql.Timestamp;

import core.regn.UserProfileKey;
import core.useracctmgmt.NotificationSettingsData;
import ctrl.useracctmgmt.UserAcctMgmtController;

public class NotifySettingsTest
{

	NotificationSettingsData notifyData_;
	String userEmail_;
	String companyPhoneNo_;
	
	public NotifySettingsTest( )
	{
		notifyData_ = new NotificationSettingsData( );
		
		userEmail_ = "";
		companyPhoneNo_ = "";
;		
		notifyData_.nonWorkingHoursFlag_ = 1;
		notifyData_.notifyEmail_ = "";
		notifyData_.notifyMobile_ = "";
		notifyData_.notifyNonWhMode_ = "Email";
		notifyData_.notifyStopFlag_ = 1;
		notifyData_.notifyStopFromTime_ = new Timestamp( System.currentTimeMillis( ) );
		notifyData_.notifyStopToTime_ = new Timestamp( System.currentTimeMillis( ) + 20 );
		notifyData_.notifyWhMode_ = "Both";
		notifyData_.userProfileKey_ = new UserProfileKey( );
		notifyData_.userProfileKey_.email_ = userEmail_;
		notifyData_.workingHoursFlag_ = 1;
	}
	
	public void testAll( )
	{
		updateNotifySettings( );
		getNotifySettings( );
	}
	
	private void updateNotifySettings( )
	{
		UserAcctMgmtController userAcctMgmtController = new UserAcctMgmtController( );
		
		userAcctMgmtController.updateNotifySettings( notifyData_ );
		
		userAcctMgmtController = null;
		
	}
	
	private void getNotifySettings( )
	{
		UserAcctMgmtController userAcctMgmtController = new UserAcctMgmtController( );
		
		userAcctMgmtController.getNotifyData( notifyData_ );
		
		userAcctMgmtController = null;
	}
	
}
