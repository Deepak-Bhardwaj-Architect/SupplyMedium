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

package core.managefolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import core.dept.DeptData;
import core.dept.DeptFolderData;
import core.dept.DeptFolderKey;
import core.dept.DeptFolderMappingData;
import core.dept.DeptKey;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import core.usermgmt.GroupData;
import core.usermgmt.GroupDeptMappingData;
import core.usermgmt.GroupKey;
import core.usermgmt.UserGroupMappingData;
import db.dept.DeptFolderMappingTable;
import db.dept.DeptTable;
import db.dept.UserDeptMappingTable;
import db.managefolder.GroupFolderAccessTable;
import db.managefolder.UserFolderAccessTable;
import db.regn.UserProfileTable;
import db.usermgmt.GroupsDeptMapTable;
import db.usermgmt.GroupsTable;
import db.usermgmt.UserGroupMapTable;
import utils.ErrorLogger;


/**
 * File:  ManageFolder.java 
 *
 * Created on May 10, 2013 4:33:16 PM
 */

/*
 * Class: ManageFolder
 * 
 * Purpose: This class manages the manage folder operations
 */

public class ManageFolder
{

	ErrorLogger errLogger_;
	
	/*Constructor*/
	
	public ManageFolder( )
	{
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method: getGroups
	 * 
	 * Input: CompanyRegnKey obj, List<GroupData> groupDataList ( As ref )
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches the list of GroupData and list of UserProfileData
	 * for each groups, then adds the GroupData into groupDataList for given
	 * CompanyRegnKey 
	 * 
	 */
	
	public int getGroups( CompanyRegnKey regnKey, List<GroupData> groupDataList )
	{
		GroupsTable groupsTable = new GroupsTable( );
		
		UserProfileTable profileTable = new UserProfileTable( );
		
        UserGroupMapTable mapTable = new UserGroupMapTable( );
		
		int result = groupsTable.getAllGroups( regnKey, groupDataList );
	
		groupsTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::ManageFolder.getGroups() - Failed to get all groups" );
	
			profileTable = null;
			
			mapTable = null;
			
			return 7801; //Failed to get all groups
		}
		
		for( GroupData groupData : groupDataList )
        {
	        List<UserGroupMappingData> mappingDataList = new ArrayList<UserGroupMappingData>( );
	        
	        result = mapTable.getGroupUsers( groupData.groupKey_, mappingDataList );
	        
	        if( result != 0)
	        {
	        	errLogger_.logMsg( "Error::ManageFolder.getGroups() - Failed to get users for a group" );
	        	
	        	mappingDataList = null;
	        	
				profileTable = null;
				
				mapTable = null;
	        	
				return 7802; //Failed to fetch users for a group
	        }
	        
	        List<UserProfileData> profileDataList = new ArrayList<UserProfileData>( );
	        
	        for( UserGroupMappingData userGroupMappingData : mappingDataList )
            {   
	            UserProfileData profileData = new UserProfileData( );
	            
	            result = profileTable.getUserProfile( userGroupMappingData.userRelKey_, profileData );
	            
	            if( result != 0 )
	            {
	            	errLogger_.logMsg( "Error::ManageFolder.getGroups() - Failed to get user profile data" );
		        	
	            	profileData = null;
	            	
	            	mappingDataList = null;
	            	
	            	profileDataList = null;
	            	
	    			profileTable = null;
	    			
	    			mapTable = null;
	            	
					return 7803; // Failed to get user profile details for given user key
	            }
	            
	            profileDataList.add( profileData );
	            
	            profileData = null;
	        }
	        
	        groupData.profileDataList_ = profileDataList;
	        
	        profileDataList = null;
	        
	        mappingDataList = null;
        }
		
		profileTable = null;
		
		mapTable = null;
		
		return 7800; //Successfully fetched all groups and users of each group
	}
	
	/*
	 * Method: getUsers
	 * 
	 * Input: CompanyRegnKey obj, List<UserProfileData> profileDataList ( As ref )
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches the list of UserProfileData for given CompanyRegnKey.
	 * Then for each userkey in profileDataList, the UserGroupMapTable is checked whether,
	 * the userkey is associated with any groups. 
	 * 
	 * If it is associated, then the userkey is removed from the profileDataList. Otherwise,
	 * the loop will be continued.
	 * 
	 */
	
	public int getUsers( CompanyRegnKey regnKey, List<UserProfileData> profileDataList )
	{
		UserProfileTable profileTable = new UserProfileTable( );
		
		UserGroupMapTable mapTable = new UserGroupMapTable( );
		
		int result = profileTable.find( regnKey, profileDataList );
		
		profileTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::ManageFolder.getUsers() - Failed to get all users" );
			
			mapTable = null;
			
			return 7811;  //Failed to get all users for given regn key
		}
			
		Iterator<UserProfileData> iterator = profileDataList.iterator( );
		
		while( iterator.hasNext( ) )
        {   
			UserProfileData profileData = iterator.next( );
			
			result = mapTable.isExists( profileData.userProfileKey_ );
	        
			if( result == 0 )
	        {
	        	//profileDataList.remove( userProfileData );	
	        	iterator.remove( );
	        }
        }
		
		return 7810; //Successfully fetched non group users list
	}
	
	/*
	 * Method: getDepts
	 * 
	 * Input: CompanyRegnKey obj, GroupKey obj, List<DeptData> deptDataList
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches the list of DeptData for given CompanyRegnKey, then
	 * list of DeptData for given GroupKey from DeptGroupMap. 
	 * 
	 * Then two list are compared. The result list will contain department list such
	 * that each department is not associated with any group
	 */
	
	public int getDepts( CompanyRegnKey regnKey, GroupKey groupKey, List<DeptData> deptDataList )
	{
		DeptTable deptTable = new DeptTable( );
		
		int result = deptTable.getAllDepartments( regnKey, deptDataList );
		
		deptTable = null;
		
		if( result != 0)
		{
			errLogger_.logMsg( "Error::ManageFolder.getDepts() - Failed to get all departments" );
			
			return 7821;  // Failed to fetch dept for given regn key
		}
		List<GroupDeptMappingData> tempList = new ArrayList<GroupDeptMappingData>( );
		
		GroupsDeptMapTable mapTable = new GroupsDeptMapTable( );
		
		result = mapTable.get( groupKey, tempList );
		
		mapTable = null;
		
		if( result != 0)
		{
			errLogger_.logMsg( "Error::ManageFolder.getDepts( regnKey, groupKey, deptDataList ) " +
							   "- Failed to get dept mapping for given group key" );
			
			tempList = null;
			
			return 7822;  // Failed to fetch dept mapping for given group key
		}
		Iterator<DeptData> deptListIterator = deptDataList.iterator( );
		
		while( deptListIterator.hasNext( ) )
        {
			DeptData deptData = deptListIterator.next( );

			for( GroupDeptMappingData groupDeptMappingData : tempList )
            {
	         	if( deptData == null)
	         		continue;
	         	
	         	if( deptData.deptKey_.toString( ).equals( groupDeptMappingData.deptRelKey_.toString( ) ) )
	         	{
	         		deptListIterator.remove( );
	         	}
            }
        }
		
		tempList = null;
		
		DeptFolderMappingTable mapTbl = new DeptFolderMappingTable( );
		
		
		
		for( DeptData deptData : deptDataList )
        {
			List<DeptFolderMappingData> deptFolderMappingDataArr = new ArrayList<DeptFolderMappingData>( );
			
			result = mapTbl.getFoldersKey( deptData.deptKey_, deptFolderMappingDataArr );
			
			if( result != 0 )
			{
				errLogger_.logMsg( "Error::ManageFolder.getDepts( regnKey, groupKey, deptDataList ) " +
						   "- Failed to get folder keys for given group key" );
		
				mapTbl = null;
				
				return 7823; //Failed to get folder keys for given dept key
			}
			
			//List<DeptFolderData> folderDataList = new ArrayList<DeptFolderData>( );
			
			deptData.deptFolderDataList_.clear( );
			
			System.out.println( "Folder count="+deptFolderMappingDataArr.size( ) );
			
			for( DeptFolderMappingData deptFolderMappingData : deptFolderMappingDataArr )
            {
				DeptFolderData folderData = new DeptFolderData( );
				
				folderData.deptFolderKey_ = new DeptFolderKey( );
				
				folderData.deptFolderKey_ = deptFolderMappingData.deptFolderKey_;
				
				folderData.folderName_ = deptFolderMappingData.deptFolderKey_.folderName_;
				
				deptData.deptFolderDataList_.add( folderData );
				
				folderData = null;
            }
			
			deptFolderMappingDataArr = null;
			
			//deptData.deptFolderDataList_ = folderDataList;
			
			//folderDataList.clear( );
			
			//folderDataList = null;
        }
		
		mapTbl = null;
		
		return 7820; //Success
	}
	
	/*
	 * Method: getUsers
	 * 
	 * Input: CompanyRegnKey obj, UserProfileKey obj, List<DeptData> deptDataList
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches the list of DeptData for given CompanyRegnKey, then
	 * list of DeptData for given UserProfileKey from UserDeptMap. 
	 * 
	 * Then two list are compared. The result list will contain department list such
	 * that each department is not associated with any user
	 */
	
	public int getDepts( CompanyRegnKey regnKey, UserProfileKey userKey, List<DeptData> deptDataList )
	{
		DeptTable deptTable = new DeptTable( );
		
		int result = deptTable.getAllDepartments( regnKey, deptDataList );
		
		deptTable = null;
		
		if( result != 0)
		{
			errLogger_.logMsg( "Error::ManageFolder.getDepts(regnKey, userKey, deptDataList ) " +
							   "- Failed to get all departments" );
			
			return 7831;  // Failed to fetch dept for given regn key
		}
		
		List<DeptKey> tempList = new ArrayList<DeptKey>( );
		
		UserDeptMappingTable mapTable = new UserDeptMappingTable( );
		
		result = mapTable.getDepartmentKeys( userKey, tempList );
		
		mapTable = null;
		
		if( result != 0)
		{
			errLogger_.logMsg( "Error::ManageFolder.getDepts() - Failed to get dept mapping for given user key" );
			
			tempList = null;
			
			return 7832;  // Failed to fetch dept user mapping for given userkey key
		}
		
		Iterator<DeptData> deptListIterator = deptDataList.iterator( );
		
		while( deptListIterator.hasNext( ) )
        {
			DeptData deptData = deptListIterator.next( );
	        for( DeptKey deptKey : tempList )
	        {	
	         	if( deptData.deptKey_.toString( ).equals( deptKey.toString( ) ) )
	         	{
	         		deptListIterator.remove( );
	         	}
            }
        }
		
		tempList = null;
		
		DeptFolderMappingTable mapTbl = new DeptFolderMappingTable( );
		
		
		
		for( DeptData deptData : deptDataList )
        {
			List<DeptFolderMappingData> deptFolderMappingDataArr = new ArrayList<DeptFolderMappingData>( );
			
			result = mapTbl.getFoldersKey( deptData.deptKey_, deptFolderMappingDataArr );
			
			if( result != 0 )
			{
				errLogger_.logMsg( "Error::ManageFolder.getDepts( regnKey, groupKey, deptDataList ) " +
						   "- Failed to get folder keys for given group key" );
		
				mapTbl = null;
				
				return 7833; //Failed to get folder keys for given dept key
			}
			
			List<DeptFolderData> folderDataList = new ArrayList<DeptFolderData>( );
			
			for( DeptFolderMappingData deptFolderMappingData : deptFolderMappingDataArr )
            {
				DeptFolderData folderData = new DeptFolderData( );
				
				folderData.deptFolderKey_ = new DeptFolderKey( );
				
				folderData.deptFolderKey_ = deptFolderMappingData.deptFolderKey_;
				
				folderData.folderName_ = deptFolderMappingData.deptFolderKey_.folderName_;
				
				folderDataList.add( folderData );
				
				folderData = null;
            }
			
			deptData.deptFolderDataList_ = folderDataList;
			
			deptFolderMappingDataArr = null;
        }
		
		mapTbl = null;
		
		
		return 7830; //Success
	}
	
	/*
	 * Method: getPrivileges
	 * 
	 * Input: GroupFolderData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches the GroupFolderData for given GroupKey and
	 * FolderKey object
	 */
	
	public int getPrivileges( GroupFolderData groupFolderData )
	{	
		GroupFolderAccessTable folderAccessTbl = new GroupFolderAccessTable( );
		
		int result = folderAccessTbl.find( groupFolderData );
		
		folderAccessTbl = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::ManageFolder.getPrivileges( groupFolderData ) - " +
								"Failed to get group folder access privileges" );
			
			return 7841;  // Failed to fetch group folder access privileges
		}
		
		return 7840; //Successfully fetched group's folder access privileges
	}
	
	/*
	 * Method: getPrivileges
	 * 
	 * Input: UserFolderData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches the UserFolderData for given UserProfileKey and
	 * FolderKey object
	 */
	
	public int getPrivileges( UserFolderData userFolderData )
	{
		UserFolderAccessTable folderAccessTbl = new UserFolderAccessTable( );
		
		int result = folderAccessTbl.find( userFolderData );
		
		folderAccessTbl = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::ManageFolder.getPrivileges( userFolderData ) - " +
							   "Failed to get user's folder access privileges" );
			
			return 7851;  // Failed to fetch user's folder access privileges
		}
		
		return 7850; //Successfully fetched user's folder access privileges
	}
	
	/*
	 * Method: update
	 * 
	 * Input: GroupFolderData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method updates the access permissions (GroupFolderAccessTable)
	 * for given GroupKey and DeptFolderKey with the data available from GroupFolderData
	 * 
	 * After successful update, the list of users will be fetched from the UserGroupMapTable. Then
	 * for each user, the UserFolderData will be formed.  This UserFolderData will be inserted/updated
	 * into the UserFolderAccessTable
	 */
	
	public int update( GroupFolderData groupFolderData )
	{
		GroupFolderAccessTable folderAccessTbl = new GroupFolderAccessTable( );
		
		int result = folderAccessTbl.isExist( groupFolderData );
		
		if( result == 0 )
		{
			result = folderAccessTbl.update( groupFolderData );
		}
		else if( result == -1) 
		{
			result = folderAccessTbl.insert( groupFolderData );
		}
		else 
		{
			errLogger_.logMsg( "Error::ManageFolder.update( groupFolderData ) - " +
					   "Failed to check whether group_folder_access record exists" );
	
			return 7862;  // Failed to check whether record exists
		}
		
		folderAccessTbl = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::ManageFolder.update( groupFolderData ) - " +
					   "Failed to get group's folder access privileges" );
	
			return 7861;  // Failed to update group's folder access privileges
		}
		
		UserGroupMapTable mapTable = new UserGroupMapTable( );
		
		List<UserGroupMappingData> dataList = new ArrayList<UserGroupMappingData>( );
		
		result = mapTable.getGroupUsers( groupFolderData.groupKey_, dataList );
		
		for( UserGroupMappingData userGroupMappingData : dataList )
        {
			UserFolderData userFolderData = new UserFolderData( );
			
			userFolderData.regnKey_ = groupFolderData.regnKey_;
			
			userFolderData.userKey_ = userGroupMappingData.userRelKey_;
			
			userFolderData.folderKey_ = groupFolderData.folderKey_;
			
			userFolderData.deptKey_	= groupFolderData.deptKey_;
			
			userFolderData.readFlag_ = groupFolderData.readFlag_;
			
			userFolderData.readWriteFlag_ = groupFolderData.readWriteFlag_;
			
			result = update( userFolderData );
			
			if( result != 7870 )
			{
				errLogger_.logMsg( "Error::ManageFolder.update( groupFolderData ) - " +
						   "Failed to update user's folder privileges while updating group's folder privileges" );
			}
			
			userFolderData = null;
        }
		
		return 7860; // Successfully updated group's folder access privileges
	}
	
	/*
	 * Method: update
	 * 
	 * Input: UserProfileKey obj, DeptFolderKey folderKey, UserFolderData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method updates the access permissions (UserFolderAccessTable)
	 * for given UserProfileKey and DeptFolderKey with the data available from UserFolderData
	 * 
	 */
	
	public int update( UserFolderData userFolderData )
	{
		UserFolderAccessTable folderAccessTbl = new UserFolderAccessTable( );
		
		int result = folderAccessTbl.isExist( userFolderData );
		
		if( result == 0 )
		{
			result = folderAccessTbl.update( userFolderData );
		}
		else if( result == -1) 
		{
			result = folderAccessTbl.insert( userFolderData );
		}
		else 
		{
			errLogger_.logMsg( "Error::ManageFolder.update( groupFolderData ) - " +
					   "Failed to check whether user_folder_access record exists" );
	
			return 7872;  // Failed to check whether record exists
		}
		
		result = folderAccessTbl.update( userFolderData );
		
		folderAccessTbl = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::ManageFolder.update( userFolderData ) - " +
					   "Failed to get user's folder access privileges" );
	
			return 7871;  // Failed to update user's folder access privileges
		}
		return 7870;
	}
}
