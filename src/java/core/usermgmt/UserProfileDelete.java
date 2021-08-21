package core.usermgmt;

import java.util.List;
import java.util.ArrayList;

import utils.ErrorLogger;
import core.dept.DeptKey;
import core.dept.UserDeptMappingData;
import core.regn.UserProfileData;
import core.regn.UserSignupProcess;
import db.dept.UserDeptMappingTable;
import db.login.ActivationPendingTable;
import db.privileges.UserPrivilegesTable;
import db.regn.MailingAddressTable;
import db.regn.UserLoginTable;
import db.regn.UserProfileTable;
import db.usermgmt.UserAccountPoliciesTable;
import db.usermgmt.UserGroupMapTable;

/*
 * This is one of the implementation class for the interface
 * UserSignupProcess. This class is used to delete a User 
 * from user_profile, mailing_address, user_login, user_privileges tables
 */

public class UserProfileDelete implements UserSignupProcess
{
	
	private ErrorLogger errorLogger_ = ErrorLogger.instance( );
	
	/*
	 * Method : process( ) Input : User Profile details Return : int value
	 * specify the User profile insert result (Success/failed)
	 * 
	 * Purpose: This method is used to process the deleting user profile details,
	 * user addresses, user login details, user account policies, user privileges
	 * from supply medium system. The info for processing this operation is obtained
	 * from UserProfileData
	 */
	
	@Override
	public int process( UserProfileData signupDataObj )
	{
		
		UserProfileTable userProfileTable = new UserProfileTable( );
		
		int profileDelete = 0;
		
		// To delete user details from user_profile table
		
		profileDelete = userProfileTable.deleteUserProfile( signupDataObj.emailId_ );
		
		if( profileDelete != 0 )
		{
			String errorMsg = "UserProfileDelete	::	process-deleteUserProfile	::	Error";
			
			errorLogger_.logMsg( errorMsg );
			
			userProfileTable = null;
			
			return 751;  //delete user profile error
		}
		
		userProfileTable = null;
		
		// To delete user addresses from mailing_address table if deleting user_profile
		// table is successfully accomplished
		
		MailingAddressTable addressTable = new MailingAddressTable( );
		
		int addressDelete = 0;
		
		addressDelete = addressTable.deleteAddress( signupDataObj.emailId_ );
		
		if( addressDelete != 0 )
		{
			String errorMsg = "UserProfileDelete	::	process-deleteAddress	::	Error";
			
			errorLogger_.logMsg( errorMsg );
			
			addressTable	= null;
			
			return 752; // delete address error
		}
		
		addressTable	= null;
		
		
		// To delete user login details from user_login table if deleting mailing_addresses
		// table is successfully accomplished
		
		int loginDelete = 0;
		
		UserLoginTable loginTable = new UserLoginTable( );
		
		loginDelete	= loginTable.delete( signupDataObj.userProfileKey_ );
		
		if( loginDelete != 0 )
		{
			String errorMsg = "UserProfileDelete	::	process-deleteFromLogin	::	Error";
			
			errorLogger_.logMsg( errorMsg );
			
			loginTable	= null;
			
			return 753; // delete login details error
		}
		
		loginTable	= null;
		
		// To delete activation pending details from activation_pending table if deleting login
		// _table is successfully accomplished
		
		int activationVal = 0;
		
		ActivationPendingTable pendTbl = new ActivationPendingTable( );
		
		activationVal = pendTbl.isEmailExists( signupDataObj.userProfileKey_ );
		
		if( activationVal == 0 )
		{
			activationVal = pendTbl.delete( signupDataObj.userProfileKey_ );
			
			if( activationVal != 0 )
			{
				String errorMsg = "Error::UserProfileDelete:process - deleting from activation pending tbl failed";
				
				errorLogger_.logMsg( errorMsg );
				
				pendTbl	= null;
			}
		}
		pendTbl = null;
		
		// To delete user privileges details from user_privileges table if deleting user_login
		// table is successfully accomplished
		
		int privilegesDelete = 0;
		
		UserPrivilegesTable privilegesTable = new UserPrivilegesTable( );
		
		privilegesDelete	= privilegesTable.deletePrivileges( signupDataObj.userProfileKey_ );
		
		if( privilegesDelete != 0 )
		{
			String errorMsg = "UserProfileDelete	::	process-deletePrivileges	::	Error";
			
			errorLogger_.logMsg( errorMsg );
			
			privilegesTable	= null;
			
			return 754; // delete privileges error
		}
		
		privilegesTable	= null;
		
		
		// To delete user account policies details from user_account_policies table 
		// if deleting user_privileges table is successfully accomplished
		
		int userAccPoliciesDelete = 0;
		
		UserAccountPoliciesTable policiesTable = new UserAccountPoliciesTable( );
		
		userAccPoliciesDelete = policiesTable.deletePolicies( signupDataObj.userProfileKey_ );
		
		if( userAccPoliciesDelete != 0 )
		{
			String errorMsg = "UserProfileDelete	::	process-deleteUserProfile	::	Error";
			
			errorLogger_.logMsg( errorMsg );
			
			policiesTable = null;
			
			return 755; // delete user account policies error
		}
		
		policiesTable = null;
		
		// To delete users from various groups
		// if the deleting users are in any of the groups
		
		int groupRes = 0;
		
		UserGroupMapTable mapTbl = new UserGroupMapTable( );
		
		List<UserGroupMappingData> dataList = new ArrayList<UserGroupMappingData>();
		
		groupRes = mapTbl.getGroups( signupDataObj.userProfileKey_, dataList );
		
		if( groupRes == 0 )
		{
			for( UserGroupMappingData userGroupMappingData : dataList )
            {	
	            //mapTbl.delete( userGroupMappingData.groupRelKey_ );
				mapTbl.delete( userGroupMappingData );
            }
		}
		else if( groupRes == -1 )
		{
			String errorMsg = "Error::UserProfileDelete:process- No users to delete from user_group_mapping table";
			
			errorLogger_.logMsg( errorMsg );
		}
		
		else
		{
			String errorMsg = "Error::UserProfileDelete:process- Failed to delete user from user_group_mapping table";
			
			errorLogger_.logMsg( errorMsg );
		}		
		mapTbl = null;
		dataList = null;
		
		// To delete users from various departments
		// if the deleting users are in any of the departments
		
		int depRes = 0;
		
		UserDeptMappingTable deptMap = new UserDeptMappingTable( );
		
		List<DeptKey> deptKeyList = new ArrayList<DeptKey>( );
		
		depRes = deptMap.getDepartmentKeys( signupDataObj.userProfileKey_, deptKeyList );
		
		if( depRes == 0 )
		{
			for( DeptKey deptKey : deptKeyList )
            {
				UserDeptMappingData mapData = new UserDeptMappingData( );
				
				mapData.deptKey_ = deptKey;
				
				mapData.userKey_ = signupDataObj.userProfileKey_;
				
	            deptMap.delete( mapData );
	            
	            mapData = null;
            }
		}
		
		else if( depRes == -1 )
		{
			String errorMsg = "Error::UserProfileDelete:process- No users to delete from user_dept_mapping table";
			
			errorLogger_.logMsg( errorMsg );
		}
		
		else
		{
			String errorMsg = "Error::UserProfileDelete:process- Failed to delete user from user_dept_mapping table";
			
			errorLogger_.logMsg( errorMsg );
		}
		
		deptMap = null;
		deptKeyList = null;
		
		return 750; // Successfully Deleted
	}
}
