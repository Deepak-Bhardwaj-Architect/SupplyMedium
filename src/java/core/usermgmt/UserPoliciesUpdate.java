package core.usermgmt;

import utils.ErrorLogger;
import utils.UserStatus;
import core.regn.UserProfileData;
import core.regn.UserSignupProcess;
import db.regn.UserProfileTable;
import db.usermgmt.UserAccountPoliciesTable;

public class UserPoliciesUpdate implements UserSignupProcess
{
	
	/*
	 * This is one of the implementation class for the interface
	 * UserSignupProcess. This class is used to process the update user operation.
	 * The details like user profile, 
	 * 
	 */
	
	@Override
	public int process( UserProfileData signupDataObj )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		int userAccUpdateResult = 0;
		
		UserAccountPoliciesTable userPoliciesTable = new UserAccountPoliciesTable( );
		
		userAccUpdateResult	= userPoliciesTable.updatePolicies( signupDataObj );
		
		if( userAccUpdateResult != 0 )
		{
			String errorMessage = "Error::UserPoliciesUpdate.process() - updatePolicies";
			
			errorLogger.logMsg( errorMessage );
			
			userPoliciesTable	= null;
			
			return 801; // Error updating user policies
		}
		
		/// To update status when user opts to disable account
		
		UserProfileTable profileTable = new UserProfileTable( );
		
		if( signupDataObj.disableAccountFlag_ == 1 && signupDataObj.userStatus_.equals( UserStatus.status.ACTIVE.getValue( ) ))
		{
			profileTable.updateStatus( signupDataObj.userProfileKey_, UserStatus.status.BLOCKED.getValue( ) );
		}
		if( signupDataObj.disableAccountFlag_ == 0 && signupDataObj.userStatus_.equals( UserStatus.status.BLOCKED.getValue( ) ) )
		{
			profileTable.updateStatus( signupDataObj.userProfileKey_, UserStatus.status.ACTIVE.getValue( ) );
		}
		
		profileTable = null;
		userPoliciesTable	= null;
		
		return 800;
	}

	// user profile update

}
