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

package core.usermgmt;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.LongHolder;

import core.privilege.UsergroupPrivilegesData;
import core.regn.CompanyRegnKey;

import utils.ErrorLogger;

import db.privileges.PrivilegesDefaultsTable;
import db.usermgmt.GroupPrivilegesTable;

import db.usermgmt.GroupsDeptMapTable;
import db.usermgmt.GroupsTable;
import db.usermgmt.UserGroupMapTable;

/**
 * File: GroupManager.java
 * 
 * Created on Mar 6, 2013 2:35:58 PM
 */


/*
 * Class  : GroupManager
 * 
 * Purpose: This class is used to manage add, update, remove, find user groups operations
 * 
 */


public class GroupManager
{
	
	/*
	 * Method : add( )
	 * 
	 * Input : GroupData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to create new user group to the company
	 * 
	 */
	
	public int add( GroupData groupData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		GroupsTable groupsTable = new GroupsTable( );
		
		int result= 0;
		
		// Check group already exist
		int isExist = groupsTable.isExist( groupData );
		
		if( isExist != 1 ) // group not exist create new group
		{
			result = groupsTable.insert( groupData );
		}
		else 
		{
			
			return 1002; // Group already exist
		}
		
		
		
		if( result != 0 )
		{			
			groupsTable = null;
			return 1001; // Error while creating group
		}
		else
		{
			int priVal = setDefaultPrivileges( groupData );
			if( priVal == 0)
			{
				errLogger.logMsg( "Info::GroupManager:add:Group default privileges - Added" );
			}
			groupsTable = null;
			return 1000; // Success
		}
	}

	/*
	 * Method : update( )
	 * 
	 * Input : GroupData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update name of existing user group
	 * 
	 */
	
	
	public int update( GroupData groupData )
	{
		GroupsTable groupsTable = new GroupsTable( );
		
		// Check group already exist
		
		int result=0;
		
		int isExist = groupsTable.isExist( groupData );
		
		if( isExist != 1 ) // group not exist create new group
		{
			 result = groupsTable.update( groupData );
		}
		else 
		{
			groupsTable = null;
			return 1002;
		}
	
		if( result != 0 )
		{			
			groupsTable = null;
			return 1051; // Error while updating group
		}
		else
		{
			//To update key
			GroupKey oldKey = new GroupKey( );
			GroupKey updatedKey = new GroupKey( );
			
			CompanyRegnKey key = groupData.groupKey_.companyRegnKey_;
			
			oldKey.groupName_ = groupData.groupKey_.groupName_; 
			oldKey.companyRegnKey_ = key;
			
			updatedKey.groupName_ = groupData.groupName_;
			updatedKey.companyRegnKey_ = key;

			//To update key in Groups table
			groupsTable.updateKey( updatedKey, oldKey );
			
			//To update key in Group privileges table
			GroupPrivilegesTable priTbl = new GroupPrivilegesTable( );
			priTbl.updateKey( updatedKey, oldKey );
			
			//To update key in user group map table
			UserGroupMapTable userGroupMap = new UserGroupMapTable( );
			userGroupMap.updateKey( updatedKey, oldKey );
			
			//To update key in Group dept map table
			GroupsDeptMapTable groupDeptMap = new GroupsDeptMapTable( );
			groupDeptMap.updateKey( updatedKey, oldKey );
			
			oldKey = null;
			updatedKey = null;
			priTbl = null;
			userGroupMap = null;
			groupDeptMap = null;
			groupsTable = null;
			
			return 1050; // Success
		}
	}
	
	/*
	 * Method : remove( )
	 * 
	 * Input : GroupData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to remove existing group from the company, 
	 * To remove user group,
	 * To remove users from user group mapping,
	 * To update users privilege
	 */
	
	public int remove( GroupData groupData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		GroupsTable groupsTable = new GroupsTable( );
		
		UserGroupMapTable mapTbl  = new UserGroupMapTable( );
		
		List<UserGroupMappingData> usersList = new ArrayList<UserGroupMappingData>( );
		
		int getResult = mapTbl.getGroupUsers( groupData.groupKey_, usersList );
		
		if( getResult != 0 )
		{
			errLogger.logMsg( "Error::GroupManager:remove - getGroupUsers failed" );
		}
		
		int result = groupsTable.delete( groupData.groupKey_ );
		
		if( result != 0 )
		{			
			groupsTable = null;
			mapTbl = null;
			usersList = null;
			
			return 1101; // Error while deleting group
		}
		else
		{	
			//To delete group from user-group mapping table
			
			int delVal = mapTbl.delete( groupData.groupKey_ );
			if( delVal == 0 )
			{
				errLogger.logMsg( "Info::GroupManager:remove - Group-users removed Successfully" );
				
				//To update user privileges
				
				UserGroupMapper map = new UserGroupMapper( );
				
				for( UserGroupMappingData userGroupMappingData : usersList )
                {
					map.updatePrivileges( userGroupMappingData.userRelKey_ );    
                }
				
				map = null;
			}
			
			//To delete group privileges 
			
			GroupPrivilegesTable tbl = new GroupPrivilegesTable( );
			
			delVal = tbl.delete( groupData.groupKey_ );
			
			if( delVal != 0)
			{
				errLogger.logMsg( "Error::GroupManager:remove - Failed to remove group privileges" );
			}
			
			
			//To delete group from group_dept mapping table
			
			GroupsDeptMapTable groupsDeptMapTable = new GroupsDeptMapTable( );
			
			List<GroupDeptMappingData> mapDataList = new ArrayList<GroupDeptMappingData>( );
			
			delVal = groupsDeptMapTable.get( groupData.groupKey_, mapDataList );
			
			if( delVal == 0 )
			{
				for( GroupDeptMappingData groupDeptMappingData : mapDataList )
                {
	                groupsDeptMapTable.delete( groupDeptMappingData );
                }
			} 
			
			else 
			{
				errLogger.logMsg( "Error::GroupManager:remove - Failed to remove group_dept_mapping" );
			}
			
			groupsDeptMapTable = null;
			
			mapDataList = null;
			
			groupsTable = null;
			mapTbl = null;
			usersList = null;
			
			return 1100; // Success
		}
	}
	
	/*
	 * Method : getAllUserGroups( )
	 * 
	 * Input : GroupData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all user groups of a company
	 * 
	 */
	
	
	public int getAllUserGroups( CompanyRegnKey key, List< GroupData > groupDataArr )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		GroupsTable groupsTable = new GroupsTable( );
		
		int result = groupsTable.getAllGroups( key, groupDataArr );
		
		if( result != 0 )
		{
			errLogger.logMsg( "Error::GroupManager:getAllUserGroups - Error while getting user groups" );
			
			return 1201; 
		}
		
		return 1200;
	}
	
	/*
	 * Method : setDefaultPrivileges( ) 
	 * 
	 * Input : userProfileDataq object 
	 * 
	 * Return : none
	 * 
	 * Purpose: Set default user privileges for new user
	 */
	
	private int setDefaultPrivileges( GroupData groupData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		PrivilegesDefaultsTable defaults = new PrivilegesDefaultsTable( );
		
		LongHolder privilegesValue = new LongHolder( );
		
		int defaultResult = defaults.getGroupDefaults( privilegesValue );
		
		if(defaultResult == 0)
		{
			errLogger.logMsg( "info::GroupManager:setDefaultPrivileges:PrivilegesDefaultsTable.getGroupDefaults:Success" );
		}
		
		defaults = null;
		
		GroupPrivilegesTable groupPriTbl = new GroupPrivilegesTable( );
		
		UsergroupPrivilegesData groupPriData = new UsergroupPrivilegesData( );
		
		groupPriData.privilegesValue_ = privilegesValue.value;
		
		groupPriData.groupKey_	= groupData.groupKey_;
		
		int priVal = groupPriTbl.insert( groupPriData );
		
		if( priVal != 0 )
		{
			errLogger.logMsg( "Error::GroupManager:setDefaultPrivileges:GroupPrivilegesTable.insert:Failed" );
			groupPriTbl = null;
			return -1;
		}
		
		groupPriTbl = null;
		return 0;
	}
	
}
