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

import utils.BinaryDecimalConverter;
import utils.ErrorLogger;

import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.privileges.PrivilegesDefaultsTable;
import db.privileges.UserPrivilegesTable;
import db.regn.UserProfileTable;
import db.usermgmt.GroupPrivilegesTable;
import db.usermgmt.UserGroupMapTable;


/**
 * File:  UserGroupMapper.java 
 *
 * Created on Mar 6, 2013 2:43:13 PM
 */


/*
 * Class  : UserGroupMapper
 * 
 * Purpose: This class is used to map users to specific user groups.
 * 
 */

public class UserGroupMapper
{

	/*
	 * Method : add( )
	 * 
	 * Input : GroupKey key, List< GroupData > (as ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to add new list of users to the
	 * given GroupKey
	 * To update the user privileges.
	 * 
	 */
	
	public int add( GroupKey key, List< UserProfileData > userProfileDataList )
	{
		UserGroupMapTable mapTable = new UserGroupMapTable( );
		
		int result = 0;
		
		for( UserProfileData userProfileData :  userProfileDataList )
		{
			UserGroupMappingData data = new UserGroupMappingData( );
			
			data.groupRelKey_ = key;
			data.userRelKey_ = userProfileData.userProfileKey_;
			
			//System.out.println( "Add user to group: GroupRelKey = "+key.groupName_+ ","+key.companyRegnKey_ );
			
			
			result = mapTable.insert( data );
		
			//To udpate user privileges with group privileges.
			
			if( result == 0 )
			{
				updatePrivileges( data.userRelKey_ );
			}
			
			data = null;
		}
		return 1150; //Success
	}

	/*
	 * Method : remove( )
	 * 
	 * Input : GroupKey key, List< GroupData > (as ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to remove list of users from existing
	 * user group using group key
	 * 
	 */
	
	public int remove( GroupKey key, List< UserProfileData > userProfileDataList )
	{
		UserGroupMapTable mapTable = new UserGroupMapTable( );
		
		int result = 0;
		
		for( UserProfileData userProfileData :  userProfileDataList )
		{
			UserGroupMappingData data = new UserGroupMappingData( );
			
			data.groupRelKey_ 	= key;
			data.userRelKey_	=	userProfileData.userProfileKey_;
			
			result = mapTable.delete( data );

			//To udpate user privileges with group privileges.
			
			if( result == 0 )
			{
				updatePrivileges( data.userRelKey_ );
			}
			
			data = null;
		}
		
		return 1170;
	}
	
	
	/*
	 * Method : find( )
	 * 
	 * Input : GroupKey key, List< GroupData > (as ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get list of users of a usergroup
	 * using given group key
	 * 
	 */
	
	public int find( GroupKey key, List< UserProfileData > userProfileDataList )
	{
		UserGroupMapTable groupMapTbl = new UserGroupMapTable( );
		
		List<UserGroupMappingData> dataList = new ArrayList<UserGroupMappingData>( );
		
		int result = groupMapTbl.getGroupUsers( key, dataList );
		
		if( result != 0 )
		{
			groupMapTbl = null;
			dataList = null;
			return 1251;
		}
		
		
		for ( UserGroupMappingData data : dataList )
		{
			UserProfileTable profileTbl = new UserProfileTable( );
			
			UserProfileData profileData = new UserProfileData( );
			
			int profileResult = profileTbl.getUserProfile( data.userRelKey_, profileData );
			
			if( profileResult == 0 )
			{
				userProfileDataList.add( profileData );
			}
			
			profileTbl = null;
			profileData = null;
		}
		
		groupMapTbl = null;
		dataList = null;
		return 1250;
	}
	
	/*
	 * Method : getUsers( ) 
	 * 
	 * Input  : GroupKey object, list of UserProfile objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is fetch the all the users except the users who are already added 
	 * in given group key.
	 * Assign this to userProfileDataArr parameter so it will be copied to the caller method.
	 */
	
	public int getUsers( GroupKey groupKey,List<UserProfileData> userProfileDataArr )
	{
		int responseCode = 0;
		
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		List<UserProfileData> groupUsers = new ArrayList<UserProfileData>( );
		
		List<UserProfileData> allUsers  = new ArrayList<UserProfileData>( );
		
		System.out.println( "GroupKey: "+groupKey.companyRegnKey_+":"+groupKey.groupName_);
		
		responseCode = find( groupKey, groupUsers );
		
		System.out.println( "Mapper - Response code = "+responseCode  );
		
		if( responseCode == 1251 )
		{
			return 1261;
		}
		
		UserProfileTable  userTable= new UserProfileTable(  );
		
		responseCode = userTable.getAllUsers( groupKey.companyRegnKey_, allUsers );
		
		System.out.println( "allUsers count="+allUsers.size( )
				+"groupCount="+groupUsers.size( ));
		
		userTable = null;
		
		if( responseCode !=0 )
		{
			groupUsers = null;
			
			allUsers  = null;
			
			return 1261;
		}
		
		String errMsg = "";
				
		try
        {
			// Minus the user data from all users to group users
			/*for( UserProfileData userProfileData : allUsers )
	        {
		        if( !groupUsers.contains( userProfileData ))
		        {
		        	userProfileDataArr.add( userProfileData );
		        }
		        else 
		        {
		        	System.out.println( "else part" );
				}
	        }*/
			System.out.println( "allusers="+allUsers.size( )+"groupuser="+groupUsers );
			
			boolean isExist = false;
			
			for( UserProfileData userProfileData : allUsers )
	        {
				for( UserProfileData userProfileData2 : groupUsers)
				{
					
					 if( (userProfileData.emailId_.equals( userProfileData2.emailId_)))
					 {
						 isExist = true;
					 }
				}
				
				if( !isExist )
				{
					userProfileDataArr.add( userProfileData );
				}
		       
				isExist = false;
				
	        }
        }
        catch( ClassCastException e )
        {
        	errMsg = "Exception::UserDeptMapper:getUsers - "+e;
			
			errorLogger.logMsg( errMsg );
			
			groupUsers = null;
			
			allUsers  = null;
			
			return 1261;
			
        }
		catch( NullPointerException e )
        {
			errMsg = "Exception::UserDeptMapper:getUsers - "+e;
			
			errorLogger.logMsg( errMsg );
			
			groupUsers = null;
			
			allUsers  = null;
			
			return 1261;
			
        }
		catch( Exception e )
        {
			errMsg = "Exception::UserDeptMapper:getUsers - "+e;
			
			errorLogger.logMsg( errMsg );
			
			groupUsers = null;
			
			allUsers  = null;
			
			return 1261;
			
        }
		
		groupUsers = null;
		
		allUsers  = null;
		
		return 1260;
	}
	
	/*
	 * Method : updatePrivileges( )
	 * 
	 * Input : UserProfileKey obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used update user privileges
	 * 
	 */

	public int updatePrivileges( UserProfileKey profileKey )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		UserGroupMapTable mapTable = new UserGroupMapTable( );
		
		List<UserGroupMappingData> groupList = new ArrayList<UserGroupMappingData>( );
		
		int result = mapTable.getGroups( profileKey, groupList );
		
		if( result != 0 )
		{
			errLogger.logMsg( "Error::GroupManager:updatePrivileges - getGroups failed" );
		}
		
		LongHolder userPrivilege = new LongHolder( );
		
		PrivilegesDefaultsTable priTbl = new PrivilegesDefaultsTable( );
		
		int priVal = priTbl.getUserDefaults( userPrivilege );
		
		priTbl = null;
		
		if( priVal != 0 )
		{
			errLogger.logMsg( "Error::GroupManager:updatePrivileges - getUserDefaults failed" );
		}
		
		BinaryDecimalConverter bdc = new BinaryDecimalConverter( );
		
		long existPrivilege = bdc.binToDec( userPrivilege.value );
		
		for( UserGroupMappingData userGroupMappingData : groupList )
        {
			LongHolder userPriVal = new LongHolder( );
			
			GroupPrivilegesTable tbl = new GroupPrivilegesTable( );
			
			int priResult = tbl.getGroupPrivileges( userGroupMappingData.groupRelKey_, userPriVal );

			System.out.println( "priResult = " + priResult );
			
			if( priResult == 0 )
			{
				long applyPrivilege = bdc.binToDec( userPriVal.value );
				
				existPrivilege = existPrivilege | applyPrivilege;
			}
			
			userPriVal = null;
			
			tbl = null;
        }
		
		userPrivilege.value = bdc.decToBin( existPrivilege );
		
		bdc = null;
		
		UserPrivilegesTable userPriTbl = new UserPrivilegesTable( );
		
		result = userPriTbl.updatePrivileges( profileKey, userPrivilege );
		
		userPriTbl = null;
		
		if( result != 0 )
		{
			errLogger.logMsg( "Error::UserGroupMapper:updatePrivileges - Unable to update user privileges" );
		}
		
		return 0;
	}
	
}
