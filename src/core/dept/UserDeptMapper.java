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

package core.dept;

import java.util.ArrayList;
import java.util.List;

import utils.ErrorLogger;

import core.regn.UserProfileData;
import core.usermgmt.GroupData;
import db.dept.UserDeptMappingTable;
import db.regn.UserProfileTable;

/**
 * File:  UserDeptMapper.java 
 *
 * Created on Mar 6, 2013 6:14:18 PM
 */

/*
 * This class is used to manage the relationship between users and department.
 */

public class UserDeptMapper
{
	/*
	 * Method : add( ) 
	 * 
	 * Input  : DeptKey object, list of UserProfileData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to create the relationship between the users and department.
	 */
	
	public int add( DeptKey deptKey,List<UserProfileData> userProfileDataArr )
	{
		int responseCode = 0;
		
		UserDeptMappingTable userDeptMappingTbl = new UserDeptMappingTable();
		
		
		
		for( UserProfileData userProfiledata : userProfileDataArr )
		{
			
			
			UserDeptMappingData userDeptMappingData = new UserDeptMappingData( );
			
			userDeptMappingData.deptKey_ = deptKey;
			
			userDeptMappingData.userKey_ = userProfiledata.userProfileKey_;
			
			
			// Check relation already exist
			int isExist = userDeptMappingTbl.isExist( userDeptMappingData );
			
						
			if( isExist != 1 ) // Relation not exist, insert relation
			{
				responseCode = userDeptMappingTbl.insert( userDeptMappingData );
							
			}
			
			
		}
		
		userDeptMappingTbl = null;
		
		if( responseCode == 0 )
		{
			return 2300;  // Success
		}
		else 
		{
			return 2302; // Failed
		}
	}
	
	
	/*
	 * Method : remove( ) 
	 * 
	 * Input  : DeptKey object, list of UserProfileData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to remove the relationship between the users and department.
	 */
	
	
	public int remove( DeptKey deptKey,List<UserProfileData> userProfileDataArr )
	{
		UserDeptMappingTable userDeptMappingTbl = new UserDeptMappingTable();
		
		int responseCode = 0;
		
		for( UserProfileData userProfiledata : userProfileDataArr )
		{
			UserDeptMappingData userDeptMappingData = new UserDeptMappingData( );
			
			userDeptMappingData.deptKey_ = deptKey;
			
			userDeptMappingData.userKey_ = userProfiledata.userProfileKey_;
		
			responseCode = userDeptMappingTbl.delete( userDeptMappingData );
		}
		
		userDeptMappingTbl = null;
		
		if( responseCode == 0 )
		{
			return 2310;  // Success
		}
		else 
		{
			return 2311; // Failed
		}
	}
	
	
	/*
	 * Method : find( ) 
	 * 
	 * Input  : DeptKey object, list of userProfileDataArr objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is fetch the all the users, related to given department key.
	 * Assign this to userProfileDataArr parameter so it will be copied 
	 * to the caller method.
	 */
	
	public int find( DeptKey deptKey,List<UserProfileData> userProfileDataArr )
	{
		int responseCode = 0;
		
		List<UserDeptMappingData> userDeptMappingDataArr = 
									new ArrayList<UserDeptMappingData>( );
		
		UserDeptMappingTable userDeptMappingTable = new UserDeptMappingTable( );
		
		// Used to fetch all the users key for given dept key
		responseCode = userDeptMappingTable.getUsersKey( deptKey, userDeptMappingDataArr );
		
		userDeptMappingTable = null;
		
		if( responseCode != 0 )  // failed
		{
			return 2321;
		}
		
		UserProfileTable userProfileTbl = new UserProfileTable( );
		
		for( UserDeptMappingData userDeptMappingData : userDeptMappingDataArr )
        {
			UserProfileData userProfileData = new UserProfileData( );
			
			// Fetch group data from group key
			responseCode = userProfileTbl.getUserProfile( userDeptMappingData.userKey_, 
															userProfileData );
			if( responseCode == 0)  // Success
			{
				userProfileDataArr.add( userProfileData );
				
				userProfileData = null;
			}
			else // Failed
			{
				userProfileData = null;
				
				continue;
			}
        }
		
		userProfileTbl = null;
		
		return 2320;  // Success
	}
	

	/*
	 * Method : getUsers( ) 
	 * 
	 * Input  : DeptKey object, list of UserProfile objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is fetch the all the users except the users who are already added 
	 * in given department key.
	 * Assign this to userProfileDataArr parameter so it will be copied to the caller method.
	 */
	
	public int getUsers( DeptKey deptKey,List<UserProfileData> userProfileDataArr )
	{
		int responseCode = 0;
		
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		List<UserProfileData> deptUsers = new ArrayList<UserProfileData>( );
		List<UserProfileData> allUsers  = new ArrayList<UserProfileData>( );
		
		responseCode = find( deptKey, deptUsers );
		
		if( responseCode == 2321 )
		{
			return 2331;
		}
		
		UserProfileTable  userTable= new UserProfileTable(  );
		
		responseCode = userTable.getAllUsers( deptKey.companyRegnKey_, allUsers );
		
		System.out.println( "allUsers count="+allUsers.size( )
				+"deptcount="+deptUsers.size( ));
		
		userTable = null;
		
		if( responseCode !=0 )
		{
			deptUsers = null;
			
			allUsers  = null;
			
			return 2331;
		}
		
		String errMsg = "";
				
		try
        {
			// Minus the user data from all users to department users
			/*for( UserProfileData userProfileData : allUsers )
	        {
		        if( !deptUsers.contains( userProfileData ))
		        {
		        	userProfileDataArr.add( userProfileData );
		        }
		        else 
		        {
		        	System.out.println( "else part" );
				}
	        }*/
			
			boolean isExist = false;
			
			for( UserProfileData userProfileData : allUsers )
	        {
				for( UserProfileData userProfileData2 : deptUsers)
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
			
			deptUsers = null;
			
			allUsers  = null;
			
			return 2331;
			
        }
		catch( NullPointerException e )
        {
			errMsg = "Exception::UserDeptMapper:getUsers - "+e;
			
			errorLogger.logMsg( errMsg );
			
			deptUsers = null;
			
			allUsers  = null;
			
			return 2331;
			
        }
		catch( Exception e )
        {
			errMsg = "Exception::UserDeptMapper:getUsers - "+e;
			
			errorLogger.logMsg( errMsg );
			
			deptUsers = null;
			
			allUsers  = null;
			
			return 2331;
			
        }
		
		deptUsers = null;
		
		allUsers  = null;
		
		return 2330;
	}
}
