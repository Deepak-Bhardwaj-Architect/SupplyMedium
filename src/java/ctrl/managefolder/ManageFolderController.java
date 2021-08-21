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

package ctrl.managefolder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.dept.DeptData;
import core.managefolder.GroupFolderData;
import core.managefolder.ManageFolder;
import core.managefolder.UserFolderData;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import core.usermgmt.GroupData;
import core.usermgmt.GroupKey;
import utils.ErrorMaster;

/**
 * File:  ManageFolderController.java 
 *
 * Created on May 10, 2013 4:33:58 PM
 */

/*
 * Class: ManageFolderController
 * 
 * Purpose: This class is used to control the manage folder operations like
 * assigning department folders ( department other than selected groups/users 
 * parent departments ) and updating the access permissions.
 * 
 * The request types are,
 * 
 * 1. ListAllGroups - To list all user groups and its users
 * 
 * 2. ListNonGroupUsers - To list all non group users ( to list users who
 * are all not in any groups )
 * 
 * 3. ListNonGroupDepts	- To list all departments which are not related to given
 * group key
 * 
 * 4. ListNonUserDepts - To list all departments which are not related to given
 * user key
 * 
 * 5. GetGroupPrivileges - To fetch manage folder access permissions for the given
 * group key
 * 
 * 6. GetUserPrivileges	- To fetch manage folder access permissions for the given
 * user key
 * 
 * 7. UpdateGroupPrivileges	- To update manage folder access permissions for the given
 * group key
 * 
 * 8. UpdateUserPrivileges - To update manage folder access permissions for the given
 * user key
 * 
 */

public class ManageFolderController
{
	
	ErrorLogger errLogger_;
	
	public ManageFolderController( )
	{
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method: getDepts 
	 * 
	 * input: HttpServletRequest request, List<DeptData> deptDataList
	 * 
	 * Return: int
	 * 
	 * Purpose: This method parses the request and processes
	 * 1. ListNonGroupDepts request
	 * 2. ListNonUserDepts request
	 */
	
	public int getDepts( HttpServletRequest request, List<DeptData> deptDataList )
	{	
                ErrorMaster errorMaster_ = null;
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );			
		ManageFolderDataConverter converter = new ManageFolderDataConverter( );
		
		String requestType = request.getParameter( "RequestType" );
		
		CompanyRegnKey regnKey = new CompanyRegnKey( );

		int result = converter.getRegnKey( request, regnKey );
		
		if( result != 0)
		{
			errLogger_.logMsg( "Error::ManageFolderController.getDepts( ) - Failed to parse regnKey");
			
			regnKey = null;
			
			converter = null;
			
			return 7999; // Parse request error
		}		
		
		if( requestType.equals( "ListNonGroupDepts" ))
		{
			GroupKey groupKey = new GroupKey( );
			
			result = converter.getGroupKey( request, groupKey );
			
			if( result != 0)
			{
				errLogger_.logMsg( "Error::ManageFolderController.getDepts( ) - Failed to parse groupKey");
				
				groupKey = null;
				
				converter = null;
				
				return 7999; // Parse request error
			}
			
			errLogger_.logMsg( "Info::ManageFolderController.getDepts( ) - ListNonGroupDepts Request in process");
			
			result = listNonGroupDepts( regnKey, groupKey, deptDataList );
			
			errorMaster_.insert( "MF Ctrl List NG Depts - Response result = " + result );
			
			regnKey = null;
			groupKey = null;
			converter = null;
		}
		else if( requestType.equals( "ListNonUserDepts" ))
		{
			UserProfileKey userKey = new UserProfileKey( );
			
			result = converter.getUserKey( request, userKey );
			
			if( result != 0)
			{
				errLogger_.logMsg( "Error::ManageFolderController.getDepts( ) - Failed to parse userKey");
				
				userKey = null;
				
				converter = null;
				
				return 7999; // Parse request error
			}
			
			errLogger_.logMsg( "Info::ManageFolderController.getDepts( ) - ListNonUserDepts Request in process" );
			
			result = listNonUserDepts( regnKey, userKey, deptDataList );
			
			errorMaster_.insert( "MF Ctrl - List NU Depts - Response result = " + result );
			
			regnKey = null;
			userKey = null;
			converter = null;
		}
		else 
		{
			errLogger_.logMsg( "Error::ManageFolderController.getDepts( ) - Failed to parse request type" );
			
			result = 7999;
		}
		
		return result;
	}
	
	/*
	 * Method:  getGroups
	 * 
	 * input: HttpServletRequest request, List<GroupData> groupDataList 
	 * 
	 * Return: int
	 * 
	 * Purpose: This method parses the request and processes
	 * 1. ListAllGroups request
	 */
	
	public int getGroups( HttpServletRequest request, List<GroupData> groupDataList )
	{
		ManageFolderDataConverter converter = new ManageFolderDataConverter( );
		
		String requestType = request.getParameter( "RequestType" );
		
		CompanyRegnKey regnKey = new CompanyRegnKey( );
		
		int result = converter.getRegnKey( request, regnKey );
		
		converter = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::ManageFolderController.getGroups( ) - Failed to parse regnKey");
			
			regnKey = null;
			
			return 7999; // Parse request error
		}
		
		if( requestType.equals( "ListAllGroups" ) )
		{
    		errLogger_.logMsg( "Info::ManageFolderController.getGroups( ) - ListAllGroups Request in process" );
    		
    		result = listAllGroups( regnKey, groupDataList );
    		
    		regnKey = null;
		}
		else 
		{
			errLogger_.logMsg( "Error::ManageFolderController.getGroups( ) - Failed to parse request type" );
			
			result = 7999;
		}
		
		return result;
	}
	
	/*
	 * Method: getUsers
	 * 
	 * Input: HttpServletRequest request, List<UserProfileData> profileDataList
	 * 
	 * Return: int
	 * 
	 * Purpose: This method parses the request and processes
	 * 1. ListNonGroupUsers request
	 */
	
	public int getUsers( HttpServletRequest request, List<UserProfileData> profileDataList )
	{
		ManageFolderDataConverter converter = new ManageFolderDataConverter( );
		
		String requestType = request.getParameter( "RequestType" );
		
		CompanyRegnKey regnKey = new CompanyRegnKey( );
		
		int result = converter.getRegnKey( request, regnKey );
		
		converter = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::ManageFolderController.getUsers( ) - Failed to parse regnKey");
			
			regnKey = null;
			
			return 7999; // Parse request error
		}
		
		if( requestType.equals( "ListNonGroupUsers" ) )
		{
    		errLogger_.logMsg( "Info::ManageFolderController.getUsers( ) - ListNonGroupUsers Request in process" );
    		
    		result = listNonGroupUsers( regnKey, profileDataList );
    		
    		regnKey = null;
		}
		else 
		{
			errLogger_.logMsg( "Error::ManageFolderController.getUsers( ) - Failed to parse request type" );
			
			result = 7999;
		}
		
		return result;
	}
	
	/*
	 * Method: getUserPri
	 * 
	 * Input: HttpServletRequest request, UserFolderData userFolderData
	 * 
	 * Return: int
	 * 
	 * Purpose: This method parses the request and processes
	 * 1. GetUserPrivileges request
	 */
	
	public int getUserPri( HttpServletRequest request, UserFolderData userFolderData )
	{
		ManageFolderDataConverter converter = new ManageFolderDataConverter( );
		
		String requestType = request.getParameter( "RequestType" );
		
		int result = converter.getUserFolderData( request, userFolderData );
		
		converter = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::ManageFolderController.getUserPri( ) - Failed to parse userFolderData");
			
			return 7999; // Parse request error
		}
		
		if( requestType.equals( "GetUserPrivileges" ))
		{
			errLogger_.logMsg( "Info::ManageFolderController.getUserPri( ) - GetUserPrivileges Request in process" );
			
			result = fetchUserPri( userFolderData );
		}
		else 
		{
			errLogger_.logMsg( "Error::ManageFolderController.getUserPri( ) - Failed to parse request type");
			
			result = 7999;
		}
		
		return result;
	}
	
	/*
	 * Method: getGroupPri 
	 * 
	 * Input: HttpServletRequest request, UserFolderData userFolderData
	 * 
	 * Return: int
	 * 
	 * Purpose: This method parses the request and processes
	 * 1. GetGroupPrivileges request
	 */
	
	public int getGroupPri( HttpServletRequest request, GroupFolderData groupFolderData )
	{
		ManageFolderDataConverter converter = new ManageFolderDataConverter( );
		
		String requestType = request.getParameter( "RequestType" );
		
		int result = converter.getGroupFolderData( request, groupFolderData );
		
		converter = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::ManageFolderController.getGroupPri( ) - Failed to parse groupFolderData");
			
			return 7999; // Parse request error
		}
		
		if( requestType.equals( "GetGroupPrivileges" ))
		{
			errLogger_.logMsg( "Info::ManageFolderController.getGroupPri( ) - GetGroupPrivileges Request in process" );
			
			result = fetchGroupPri( groupFolderData );
		}
		else 
		{
			errLogger_.logMsg( "Error::ManageFolderController.getGroupPri( ) - Failed to parse request type");
			
			result = 7999;
		}
		
		return result;
	}
	
	/*
	 * Method: updatePrivileges 
	 * 
	 * Input: HttpServletRequest request
	 * 
	 * Return: int
	 * 
	 * Purpose: This method parses the request an processes
	 * 1. UpdateGroupPrivileges request
	 * 2. UpdateUserPrivileges request
	 */

	public int updatePrivileges( HttpServletRequest request )
	{
		ManageFolderDataConverter converter = new ManageFolderDataConverter( );
		
		String requestType = request.getParameter( "RequestType" );
		
		int result = 0;
		
		if( requestType.equals( "UpdateGroupPrivileges" ))
		{
			GroupFolderData groupFolderData = new GroupFolderData( );
			
			result = converter.getGroupFolderData( request, groupFolderData );
			
			converter = null;
			
			if( result != 0)
			{
				errLogger_.logMsg( "Error::ManageFolderController.updatePrivileges( ) - Failed to parse groupFolderData");
				
				groupFolderData = null;
				
				return 7999; // Parse request error
			}
			
			errLogger_.logMsg( "Info::ManageFolderController.updatePrivileges( ) - UpdateGroupPrivileges Request in process" );
			
			result =  updateGroupPrivileges( groupFolderData );
			
			groupFolderData = null;
		}
		else if( requestType.equals( "UpdateUserPrivileges" ))
		{
			UserFolderData userFolderData = new UserFolderData( );
			
			result = converter.getUserFolderData( request, userFolderData );
			
			converter = null;
			
			if( result != 0)
			{
				errLogger_.logMsg( "Error::ManageFolderController.updatePrivileges( ) - Failed to parse userFolderData" );
				
				userFolderData = null;
				
				return 7999; // Parse request error
			}
			
			errLogger_.logMsg( "Info::ManageFolderController.updatePrivileges( ) - UpdateUserPrivileges Request in process" );
			
			result =  updateUserPrivileges( userFolderData );
			
			userFolderData = null;
		}
		else 
		{
			errLogger_.logMsg( "Error::ManageFolderController.updatePrivileges( ) - Failed to parse request type");
			
			result = 7999;
		}
		
		return result;
	}
	
	
	/*This is called to fetch non group depts from ManageFolderController's getDepts method*/
	
	public int listNonGroupDepts( 
									CompanyRegnKey regnKey, 
									GroupKey groupKey, 
									List<DeptData> deptDataList 
								)
	{
		ManageFolder manageFolder = new ManageFolder( );
		
		int result = manageFolder.getDepts( regnKey, groupKey, deptDataList );
		
		manageFolder = null;
		
		return result;
	}
	
	/*This is called to fetch non user depts from ManageFolderController's getDepts method*/
	
	public int listNonUserDepts( 
									CompanyRegnKey regnKey,
								 	UserProfileKey userKey,
								 	List<DeptData> deptDataList
								 )
	{
		ManageFolder manageFolder = new ManageFolder( );
		
		int result = manageFolder.getDepts( regnKey, userKey, deptDataList );
		
		manageFolder = null;
		
		return result;
	}
	
	/*This is called to fetch all groups from ManageFolderController's getGroups method*/
	
	public int listAllGroups( CompanyRegnKey regnKey, List<GroupData> groupDataList )
	{
		ManageFolder manageFolder = new ManageFolder( );
		
		int result = manageFolder.getGroups( regnKey, groupDataList );
		
		manageFolder = null;
		
		return result;
	}
	
	/*This is called to fetch all non group users from ManageFolderController's getUsers method*/
	
	public int listNonGroupUsers( CompanyRegnKey regnKey, List<UserProfileData> profileDataList )
	{
		ManageFolder manageFolder = new ManageFolder( );
		
		int result = manageFolder.getUsers( regnKey, profileDataList );
		
		manageFolder = null;
		
		return result;
	}
	
	/*This is called to fetch group folder's access data from 
	 * ManageFolderController's getGroupPri method*/
	
	public int fetchGroupPri( GroupFolderData groupFolderData )
	{
		ManageFolder manageFolder = new ManageFolder( );
		
		int result = manageFolder.getPrivileges( groupFolderData );
		
		manageFolder = null;
		
		return result;
	}
	
	/*This is called to fetch user folder's access data from 
	 * ManageFolderController's getUserPri method*/
	
	public int fetchUserPri( UserFolderData userFolderData )
	{
		ManageFolder manageFolder = new ManageFolder( );
		
		int result = manageFolder.getPrivileges( userFolderData );
		
		manageFolder = null;
		
		return result;
	}
	
	/*This is called to update user folder's access data from
	 * ManageFolderController's updatePrivileges method*/
	
	public int updateUserPrivileges( UserFolderData userFolderData )
	{
		ManageFolder manageFolder = new ManageFolder( );
		
		int result = manageFolder.update( userFolderData );
		
		manageFolder = null;
		
		return result;
	}
	
	/*This is called to update group folder's access data from
	 * ManageFolderController's updatePrivileges method*/
	
	public int updateGroupPrivileges( GroupFolderData groupFolderData )
	{
		ManageFolder manageFolder = new ManageFolder( );
		
		int result = manageFolder.update( groupFolderData );
		
		manageFolder = null;
		
		return result;
	}
}
