/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any.
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */

package core.privilege;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.omg.CORBA.LongHolder;

import utils.ErrorLogger;
import utils.IntHolder;
import utils.UserType;

import core.dept.DeptData;
import core.dept.DeptKey;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import core.usermgmt.GroupKey;
import core.usermgmt.UserGroupMapper;
import core.usermgmt.UserGroupMappingData;
import db.dept.DeptTable;
import db.dept.UserDeptMappingTable;
import db.managefolder.UserFolderAccessTable;
import db.privileges.DeptPrivilegesTable;
import db.privileges.UserPrivilegesTable;
import db.regn.UserProfileTable;
import db.usermgmt.GroupPrivilegesTable;
import db.usermgmt.UserGroupMapTable;

/**
 * File:  PrivilegesComposer.java 
 *
 * Created on Feb 25, 2013 11:27:28 AM
 */

/*
 * This class is used to compose the privileges for given user.
 */
public class PrivilegesComposer
{
	
	/*
	 * Method : getAllPrivileges() 
	 * 
	 * Input : user profile key and AllPrivilegesData object 
	 * 
	 * Return : int specify the result success or
	 * failed.
	 * 
	 * Purpose: It get the input as the user profile key, and get the all user
	 * privileges using private method of this class and compose the
	 * AllPrivilegesData object then assign to allPrivilegesData object
	 * parameter so it will be copied in caller class.
	 */

	public int getAllPrivileges( UserProfileKey userProfileKey,
	        AllPrivilegesData allPrivilegesData )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		try
        {
			allPrivilegesData.userProfileKey_ = userProfileKey;

			int result = 0;
			
			System.out.println( "get dept privileges start" );

			List<DeptPrivilegesData> deptPrivilegesDataArr = new ArrayList<DeptPrivilegesData>( );

			result = getDeptPrivileges( userProfileKey, deptPrivilegesDataArr );
			
			

			if( result == 0 )
			{
				allPrivilegesData.deptPrivilegesDataArr_ = deptPrivilegesDataArr;
				
				System.out.println( "get dept privileges end : dept privileges data arr count="+deptPrivilegesDataArr.size( ) );
			}
			else
			{
				String errorMsg = "Error::PrivilegesComposer.getAllPrivileges() - " +
						"Failed to fetch Department privileges of the user, <"
							+ userProfileKey.email_ + ">";
				
				errorLogger.logMsg( errorMsg );
			}

			System.out.println( "get user privileges start" );
			
			UserPrivilegesData userPrivilegesData = new UserPrivilegesData( );

			result = getUserPrivileges( userProfileKey, userPrivilegesData );
			
			System.out.println( "get user privileges end" );

			if( result == 0 )
			{
				allPrivilegesData.userPrivilegesdata_ = userPrivilegesData;
			}
			else
			{
				String errorMsg = "Error::PrivilegesComposer.getAllPrivileges() - " +
									"Failed to fetch User privileges of the  user, <"
										+ userProfileKey.email_ + ">";

				errorLogger.logMsg( errorMsg );
			}

			return 0;
        }
        catch( Exception e )
        {
        	
    		String errMsg = "Exception::Privileges.getAllPrivileges() - " + e;

    		errorLogger.logMsg( errMsg );

    		return -1;
    		
        }
		
	}

	/*
	 * Method : getDeptPrivileges() 
	 * 
	 * Input : user profile key,deptPrivilegesDataArr 
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get the all the user related department
	 * with department privileges. Assign to deptPrivilegesDataArr so it will be
	 * copied to the caller method.
	 */

	public int getDeptPrivileges( UserProfileKey userProfileKey,
	        List<DeptPrivilegesData> deptPrivilegesDataArr )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		int result = 0;
		
		UserProfileTable userProfileTbl = new UserProfileTable( );
		
		UserProfileData profileData = new UserProfileData( );

		int profileResult = userProfileTbl.getUserProfile( userProfileKey, profileData );
		
		
		if( profileResult != 0 ) // Not getting user type
		{
			
			String errorMsg = "Error::PrivilegesComposer.getDeptPrivileges() - " +
					"Failed to fetch user type, <"
						+ userProfileKey.email_ + ">";

			errorLogger.logMsg( errorMsg );

			return result;
		}
		
		//System.out.println( "*******************************************************************************"+profileData.userType_.equals( UserType.type.ADMIN.getValue( )));
		
		//System.out.println( "usertype="+profileData.userType_+"admin="+UserType.type.ADMIN.getValue( )  );
		
		if( (profileData.userType_.equals( UserType.type.ADMIN.getValue( ) )))  // This is for the admin user
		{
			result = getAdminDeptPrivileges( profileData.companyRegnKey_,deptPrivilegesDataArr );
		}
		else 
		{
			result = getUserDeptPrivileges( userProfileKey,deptPrivilegesDataArr );
		}
		
		return result;
		
	}
	
	/*
	 * Method : getMFDeptPri( ) 
	 * 
	 * Input : user profile key,deptPrivilegesDataArr 
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get the all the user related department
	 * with department privileges. Assign to deptPrivilegesDataArr so it will be
	 * copied to the caller method.
	 */

	public int getAllDeptAndPri( UserProfileKey userProfileKey,
	        List<DeptPrivilegesData> deptPrivilegesDataArr )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		int result = 0;
		
		result = getDeptPrivileges( userProfileKey, deptPrivilegesDataArr );
		
		System.out.println( "Size of GetDept = " + deptPrivilegesDataArr.size( ) );
		
		if( result != 0 )
		{
			errorLogger.logMsg( "Error::PrivilegesComposer.getMFDeptPri( ) - Unable to fetch Departments for Dept Page and its privileges" );
			
			return 1751;
		}
		
		UserFolderAccessTable mfPriTbl = new UserFolderAccessTable( );
		
		List<DeptKey> deptKeyArr = new ArrayList<DeptKey>( );
		
		result = mfPriTbl.find( userProfileKey, deptKeyArr );
		
		mfPriTbl = null;
		
		if( result != 0 )
		{
			errorLogger.logMsg( "Error::PrivilegesComposer.getMFDeptPri( ) - Unable to fetch MF Departments for Dept Page and its privileges" );
			
			deptKeyArr = null;
			
			return 1751;
		}
		
		Iterator<DeptKey> deptKeyIterator = deptKeyArr.iterator( );

		while( deptKeyIterator.hasNext( ) )
        {
			DeptKey deptKey = deptKeyIterator.next( );
        
	        for( DeptPrivilegesData privilegesData : deptPrivilegesDataArr )
            {
	        	if( privilegesData.deptData_.deptKey_.toString( ).equals( deptKey.toString( ) ) )
	        	{
	        		deptKeyIterator.remove( );
	        	}
            }
        }

		for( DeptKey key : deptKeyArr )
        {
	        DeptPrivilegesData priData = new DeptPrivilegesData( );
	        
	        priData.deptData_ = new DeptData( );
    		priData.deptData_.deptKey_ = new DeptKey( );
    		
    		priData.deptData_.deptKey_ = key;
    		deptPrivilegesDataArr.add( priData );
    		
    		priData = null;
        }
				
		return 1750;
	}

	/*
	 * Method : getAdminDeptPrivileges() 
	 * 
	 * Input : user profile key,deptPrivilegesDataArr 
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get the all the admin related department
	 * with department privileges. Assign to deptPrivilegesDataArr so it will be
	 * copied to the caller method.
	 */
	
	private int getAdminDeptPrivileges( CompanyRegnKey regnKey,
	        List<DeptPrivilegesData> deptPrivilegesDataArr )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		int result = 0;
		
		DeptTable deptTable = new DeptTable( );
		
		List<DeptData> deptDataArr = new ArrayList<DeptData>( );
		
		result = deptTable.getAllDepartments( regnKey, deptDataArr );
		
		deptTable = null;
		
		if( result != 0 ) // Not getting user type
		{
			
			String errorMsg = "Error::PrivilegesComposer.getAdminDeptPrivileges() - " +
					"Failed to fetch departments for admin user";

			errorLogger.logMsg( errorMsg );

			return result;
		}
		
		for( DeptData deptData : deptDataArr )
        {
			DeptPrivilegesData deptPrivilegesData = new DeptPrivilegesData( );

			deptPrivilegesData.addFile_ =true;
			
			deptPrivilegesData.addFolder_ = true;
			
			deptPrivilegesData.deleteFile_ = true;
			
			deptPrivilegesData.deleteFolder_ = true;
			
			deptPrivilegesData.manageFolder_ = true;
			
			deptPrivilegesData.postAnnouncement_ = true;
			
			deptPrivilegesData.deptData_ = deptData;

			deptPrivilegesDataArr.add( deptPrivilegesData );
			
			deptPrivilegesData = null;
        }
		
		return result;
	}
	
	
	/*
	 * Method : getUserDeptPrivileges() 
	 * 
	 * Input : user profile key,deptPrivilegesDataArr 
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get the all the user related department
	 * with department privileges. Assign to deptPrivilegesDataArr so it will be
	 * copied to the caller method.
	 */
	
	private int getUserDeptPrivileges( UserProfileKey userProfileKey,
	        List<DeptPrivilegesData> deptPrivilegesDataArr )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		int result = 0;
		
		List<DeptKey> deptKeyArr = new ArrayList<DeptKey>( );

		// get all the user related departments keys

		UserDeptMappingTable userDeptMapperTbl = new UserDeptMappingTable( );

		result = userDeptMapperTbl.getDepartmentKeys( userProfileKey, deptKeyArr );

		userDeptMapperTbl = null;

		if( result != 0 ) // getting all departments key for user failed
		{

			String errorMsg = "Error::PrivilegesComposer.getUserDeptPrivileges() - "
			        + "Failed to fetch all departments realted to user, <"
			        + userProfileKey.email_ + ">";

			errorLogger.logMsg( errorMsg );

			return result;
		}

		for( DeptKey deptKey : deptKeyArr )
		{
			// get department details from dept key

			DeptTable deptTbl = new DeptTable( );

			DeptData deptData = new DeptData( );

			result = deptTbl.getDepartment( deptKey, deptData );

			deptTbl = null;

			if( result != 0 ) // getting department details for given department
			                  // id -failed
			{
				String errorMsg = "Error::PrivilegesComposer.getUserDeptPrivileges() - "
				        + "Failed to fetch user related department details for user, <"
				        + userProfileKey.email_ + ">";

				errorLogger.logMsg( errorMsg );

				continue;
			}

			// get department privileges from dept key

			DeptPrivilegesTable deptPrivilegesTbl = new DeptPrivilegesTable( );

			IntHolder privileges = new IntHolder( );

			result = deptPrivilegesTbl.getDeptPrivileges( deptKey, privileges );

			deptPrivilegesTbl = null;

			if( result != 0 ) // Getting department privileges - failed
			{

				String errorMsg = "Error::PrivilegesComposer.getUserDeptPrivileges() - "
				        + "Failed to fetch department privileges for the user, <"
				        + userProfileKey.email_ + ">";

				errorLogger.logMsg( errorMsg );

				continue;
			}

			// decode and assign the department privileges value to arraylist

			DeptPrivilegesConverter deptPrivilegesConverter = new DeptPrivilegesConverter( );

			DeptPrivilegesData deptPrivilegesData = new DeptPrivilegesData( );

			result = deptPrivilegesConverter.decode( privileges.value, deptPrivilegesData );

			deptPrivilegesConverter = null;

			if( result != 0 )
			{

				String errorMsg = "Error::PrivilegesComposer.getUserDeptPrivileges() - "
				        + "Cant decode the department privileges for user, <"
				        + userProfileKey.email_ + ">";

				errorLogger.logMsg( errorMsg );

				continue;
			}

			deptPrivilegesData.deptData_ = deptData;

			deptPrivilegesDataArr.add( deptPrivilegesData );
		}

		return result;
	}
	


	/*
	 * Method : getUserPrivileges() Input : user profile key, userPrivilegesData
	 * object Return : int
	 * 
	 * Purpose: It get the input as the user profile key, and get the user
	 * privileges. Assign to userPrivilegesData parameter so it will be copied
	 * to the caller method.
	 */

	public int getUserPrivileges( UserProfileKey userProfileKey,
	        UserPrivilegesData userPrivilegesData )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		LongHolder privileges = new LongHolder( );

		UserPrivilegesTable userPrivilegesTbl = new UserPrivilegesTable( );

		// getting user privileges

		int result = userPrivilegesTbl.getUserPrivileges( userProfileKey, privileges );
		
		userPrivilegesTbl = null;

		if( result != 0 ) // getting user privileges failed
		{
			
			String errorMsg = "Error::PrivilegesComposer.getUserPrivileges() - " +
					"Failed to fetch user privileges for the user, <"
						+ userProfileKey.email_ + ">";

			errorLogger.logMsg( errorMsg );

			return result;
		}

		UserPrivilegesConverter userPrivilegesConverter = new UserPrivilegesConverter( );

		// convert user privileges int value to object value
		result = userPrivilegesConverter.decode( privileges.value,userPrivilegesData );
		
		userPrivilegesConverter = null;
		
		if( result != 0 )
		{
			
			String errorMsg = "Error::PrivilegesComposer.getUserPrivileges() - " +
					"Cant decode the user privileges for user, <"
						+ userProfileKey.email_ + ">";
			
			errorLogger.logMsg( errorMsg );

			return result;
		}

		return 0;
	}
	
	/*
	 * Method : setUserPrivileges() 
	 * 
	 * Input : GroupKey, groupPrivilegesData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: 
	 * 
	 */
	
	public int setUserPrivileges( UserProfileKey key,
	        UserPrivilegesData userPrivilegesData )
	{
		//ErrorLogger errorLogger = ErrorLogger.instance( );
		
		UserPrivilegesConverter converter = new UserPrivilegesConverter( );
		
		long privileges = 0;
		
		privileges = converter.encode( userPrivilegesData );
		
		converter = null;
		
		userPrivilegesData.privilegesValue_ = privileges;
			
		UserPrivilegesTable userPrivilegesTable = new UserPrivilegesTable( );
		
		int response = userPrivilegesTable.updatePrivileges( key, userPrivilegesData );

		System.out.println( "Response: "+response );
		
		return 0;
	}

	/*
	 * Method : getGroupPrivileges() Input : GroupKey, groupPrivilegesData
	 * object Return : int
	 * 
	 * Purpose: It get the input as the group key, and get the group
	 * privileges. Assign to groupPrivilegesData parameter so it will be copied
	 * to the caller method.
	 */

	public int getGroupPrivileges( GroupKey key,
	        UsergroupPrivilegesData groupPrivilegesData )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		LongHolder privileges = new LongHolder( );

		//UserPrivilegesTable userPrivilegesTbl = new UserPrivilegesTable( );
		
		GroupPrivilegesTable groupPrivilegesTbl = new GroupPrivilegesTable( );

		// getting user privileges

		int result = groupPrivilegesTbl.getGroupPrivileges( key, privileges );
		
		groupPrivilegesTbl = null;

		if( result != 0 ) // getting user privileges failed
		{
			
			String errorMsg = "Error::PrivilegesComposer.getGroupPrivileges() - " +
					"Failed to fetch group privileges for the group, <" + key.groupName_ + ">";

			errorLogger.logMsg( errorMsg );

			return 951; //Failed
		}
		
		GroupPrivilegesConverter groupPrivilegesConverter = new GroupPrivilegesConverter( );

		// convert user privileges int value to object value
		result = groupPrivilegesConverter.decode( privileges.value,groupPrivilegesData );
		
		groupPrivilegesConverter = null;
		
		if( result != 0 )
		{
			String errorMsg = "Error::PrivilegesComposer.getGroupPrivileges() - " +
					"Cant decode the user privileges for group, <" + key.groupName_ + ">";
	
			errorLogger.logMsg( errorMsg );

			return 951; //Failed
		}

		
		return 950; //Success
	}
	
	/*
	 * Method : setGroupPrivileges() 
	 * 
	 * Input : GroupKey, groupPrivilegesData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: 
	 * 
	 */
	
	public int setGroupPrivileges( GroupKey key,
	        UsergroupPrivilegesData groupPrivilegesData )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		GroupPrivilegesConverter converter = new GroupPrivilegesConverter( );
		
		long privileges = 0;
		
		privileges = converter.encode( groupPrivilegesData );
		
		System.out.println( "Encoded Privileges: "+privileges );
		
		converter = null;
		
		groupPrivilegesData.privilegesValue_ = privileges;

		groupPrivilegesData.groupKey_ = key;
		//
			
		GroupPrivilegesTable groupPrivilegesTbl = new GroupPrivilegesTable( );
		
		int response = groupPrivilegesTbl.update( groupPrivilegesData );
		
		if( response != 0 )
		{
			String errorMsg = "Error::PrivilegesComposer.setGroupPrivileges() - " +
					"Cant update the group privileges for group, <" + key.groupName_ + ">";

			errorLogger.logMsg( errorMsg );
			
			return 961; //Failed
		}
				
		UserGroupMapTable mapTbl = new UserGroupMapTable( );
		
		List<UserGroupMappingData> dataList = new ArrayList<UserGroupMappingData>( );
		
		mapTbl.getGroupUsers( key, dataList );
		
		mapTbl = null;
		
		UserGroupMapper mapper = new UserGroupMapper( );
		
		for( UserGroupMappingData userGroupMappingData : dataList )
        {
			mapper.updatePrivileges( userGroupMappingData.userRelKey_ );
        }
		
		mapper = null;
		
		return 960; //Success
	}
	
	/*
	 * Method : getDeptPrivileges() 
	 * 
	 * Input : DeptKey, DeptPrivilegesData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: It get the input as the dept key, and get the dept
	 * privileges. Assign to deptPrivilegesData parameter so it will be copied
	 * to the caller method.
	 */

	public int getDeptPrivileges( DeptKey key,
	        DeptPrivilegesData deptPrivilegesData )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		IntHolder privileges = new IntHolder( );
		
		DeptPrivilegesTable deptPrivilegesTbl = new DeptPrivilegesTable( );

		// getting department privileges

		int result = deptPrivilegesTbl.getDeptPrivileges( key, privileges );
		
		deptPrivilegesTbl = null;

		if( result != 0 ) // getting department privileges failed
		{
			
			String errorMsg = "Error::PrivilegesComposer.getDeptPrivileges() - " +
					"Failed to fetch group privileges for dept, <" + key.deptName_ + ">";

			errorLogger.logMsg( errorMsg );

			return 2411;
		}
		
		DeptPrivilegesConverter deptPrivilegesConverter = new DeptPrivilegesConverter( );

		// convert user privileges int value to object value
		result = deptPrivilegesConverter.decode( privileges.value,deptPrivilegesData );
		
		deptPrivilegesConverter = null;
		
		if( result != 0 )
		{
			String errorMsg = "Error::PrivilegesComposer.getDeptPrivileges() - " +
					"Cant decode the user privileges for department, <" + key.deptName_ + ">"; 
	
			errorLogger.logMsg( errorMsg );

			return 2411;
		}

		return 2410;
	}
	
	/*
	 * Method : setDeptPrivileges() 
	 * 
	 * Input : DeptKey, groupPrivilegesData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the department privilges.
	 * 
	 */
	
	public int setDeptPrivileges( DeptKey key,
	        DeptPrivilegesData deptPrivilegesData )
	{
		
		DeptPrivilegesConverter converter = new DeptPrivilegesConverter( );
		
		int privileges = 0;
		
		privileges = converter.encode( deptPrivilegesData );
		
		converter = null;
		
		deptPrivilegesData.privileges_ = privileges;
		DeptData deptData = new DeptData( );
		deptData.deptKey_ = key;
		deptPrivilegesData.deptData_ = deptData;
	
		DeptPrivilegesTable deptPrivilegesTable = new DeptPrivilegesTable( );
		
		int response = deptPrivilegesTable.update( deptPrivilegesData );

		System.out.println( "Response: "+response );
		
		return 2400;
	}

}
