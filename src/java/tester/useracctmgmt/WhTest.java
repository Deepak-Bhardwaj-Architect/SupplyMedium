package tester.useracctmgmt;

import java.sql.Time;

import core.useracctmgmt.WorkingHoursData;
import ctrl.useracctmgmt.UserAcctMgmtController;

public class WhTest
{

	WorkingHoursData whData_;
	String userEmail_;
	String companyPhoneNo_;
	
	
	public WhTest( )
	{
		userEmail_ = "";
		companyPhoneNo_ = "4044081234";
		
		whData_ = new WorkingHoursData( );
		whData_.userProfileKey_.email_ = userEmail_;
		whData_.workingDaysData_.userProfileKey_.email_ = userEmail_; 
		
		whData_.workingDaysData_.sunWorkingFlag_ = true;
		whData_.workingDaysData_.monWorkingFlag_ = true;
		whData_.workingDaysData_.tueWorkingFlag_ = true;
		whData_.workingDaysData_.wedWorkingFlag_ = true;
		whData_.workingDaysData_.thuWorkingFlag_ = true;
		whData_.workingDaysData_.friWorkingFlag_ = true;
		whData_.workingDaysData_.satWorkingFlag_ = true;
		
		whData_.sunFromTime_ = Time.valueOf( "10:00:00" );
		whData_.monFromTime_ = Time.valueOf( "10:00:00" );
		whData_.tueFromTime_ = Time.valueOf( "10:00:00" );
		whData_.wedFromTime_ = Time.valueOf( "10:00:00" );
		whData_.thuFromTime_ = Time.valueOf( "10:00:00" );
		whData_.friFromTime_ = Time.valueOf( "10:00:00" );
		whData_.satFromTime_ = Time.valueOf( "10:00:00" );
		
		whData_.sunToTime_ = Time.valueOf( "18:00:00" );
		whData_.monToTime_ = Time.valueOf( "18:00:00" );
		whData_.tueToTime_ = Time.valueOf( "18:00:00" );
		whData_.wedToTime_ = Time.valueOf( "18:00:00" );
		whData_.thuToTime_ = Time.valueOf( "18:00:00" );
		whData_.friToTime_ = Time.valueOf( "18:00:00" );
		whData_.satToTime_ = Time.valueOf( "18:00:00" );
	}
	
	public void testAll( )
	{
		updateWhSettings( );
		
		getWhSettings( );
	}
	
	private void updateWhSettings( )
	{
		UserAcctMgmtController userAcctMgmtController = new UserAcctMgmtController( );
		
		userAcctMgmtController.updateWh( whData_ );
		
		userAcctMgmtController = null;
	}
	
	private void getWhSettings( )
	{
		UserAcctMgmtController userAcctMgmtController = new UserAcctMgmtController( );
		
		userAcctMgmtController.getWhData( whData_ );
		
		userAcctMgmtController = null;
	}
}
