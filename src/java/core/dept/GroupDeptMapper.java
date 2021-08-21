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
import core.usermgmt.GroupData;
import core.usermgmt.GroupDeptMappingData;
import db.usermgmt.GroupsDeptMapTable;
import db.usermgmt.GroupsTable;


import utils.ErrorLogger;
import utils.ErrorMaster;

/**
 * File:  GroupDeptMapper.java 
 *
 * Created on Mar 6, 2013 4:49:17 PM
 */

/*
 * This class is used to manage the relationship between groups and department.
 */

public class GroupDeptMapper
{
    
    private static ErrorMaster errorMaster_ = null;


	/*
	 * Method : add( ) 
	 * 
	 * Input  : DeptKey object, list of GroupData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to create the relationship between the groups and department.
	 */
	public GroupDeptMapper()
        {
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );    
        }
        
	public int add( DeptKey deptKey,List<GroupData> groupDataArr )
	{
		int responseCode = 0;
		
		GroupsDeptMapTable groupDeptMapTbl = new GroupsDeptMapTable();
		
		for( GroupData groupData : groupDataArr )
		{
			GroupDeptMappingData groupDeptMappingData = new GroupDeptMappingData( );
			
			groupDeptMappingData.deptRelKey_ = deptKey;
			
			groupDeptMappingData.groupRelKey_ = groupData.groupKey_;
			
			// Check relation already exist
			int isExist = groupDeptMapTbl.isExist( groupDeptMappingData );
						
			if( isExist != 1 ) // Relation not exist, insert relation
			{
				responseCode = groupDeptMapTbl.insert( groupDeptMappingData );
							
			}
			
			
		}
		
		groupDeptMapTbl = null;
		
		if( responseCode == 0 )
		{
			return 2100;  // Success
		}
		else 
		{
			return 2102; // Failed
		}
	}
	
	
	/*
	 * Method : remove( ) 
	 * 
	 * Input  : DeptKey object, list of GroupData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to remove the relationship between the groups and department.
	 */
	
	
	public int remove( DeptKey deptKey,List<GroupData> groupDataArr )
	{
		int responseCode = 0;
		
		GroupsDeptMapTable groupsDeptMapTbl = new GroupsDeptMapTable( );
		
		errorMaster_.insert( "remove group from dept" +groupDataArr.size( ) );
		
		
		
		for( GroupData groupData : groupDataArr )
		{
			GroupDeptMappingData groupDeptMappingData = new GroupDeptMappingData( );
			
			groupDeptMappingData.deptRelKey_ = deptKey;
			
			groupDeptMappingData.groupRelKey_ = groupData.groupKey_;
			
			responseCode = groupsDeptMapTbl.delete( groupDeptMappingData );
		}
		
		groupsDeptMapTbl = null;
		
		if( responseCode == 0 )
		{
			return 2110;  // Success
		}
		else 
		{
			return 2111; // Failed
		}
		
	}
	
	
	/*
	 * Method : find( ) 
	 * 
	 * Input  : DeptKey object, list of GroupData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is fetch the all the groups, related to given department key.
	 * Assign this to groupDataArr parameter so it will be copied to the caller method.
	 */
	
	public int find( DeptKey deptKey,List<GroupData> groupDataArr )
	{
		int responseCode = 0;
		
		List<GroupDeptMappingData> groupDeptMappingDataArr = 
									new ArrayList<GroupDeptMappingData>( );
		
		GroupsDeptMapTable groupDeptMapTbl = new GroupsDeptMapTable( );
		
		// Used to fetch all the groups key for given dept key
		responseCode = groupDeptMapTbl.getGroupsKey( deptKey, groupDeptMappingDataArr );
		
		groupDeptMapTbl = null;
		
		if( responseCode != 0 )  // failed
		{
			return 2121;
		}
		
		GroupsTable groupsTable = new GroupsTable( );
		
		for( GroupDeptMappingData groupDeptMappingData : groupDeptMappingDataArr )
        {
			GroupData groupData = new GroupData( );
			
			// Fetch group data from group key
			responseCode = groupsTable.getGroup( groupDeptMappingData.groupRelKey_, 
													groupData );
			
			if( responseCode == 0)  // Success
			{
				groupDataArr.add( groupData );
				
				groupData = null;
			}
			else // Failed
			{
				groupData = null;
				
				continue;
			}
        }
		
		groupsTable = null;
		
		return 2120;  // Success
	}
	
	
	/*
	 * Method : getGroups( ) 
	 * 
	 * Input  : DeptKey object, list of GroupData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is fetch the all the groups except the groups which is already added 
	 * in given department key.
	 * Assign this to groupDataArr parameter so it will be copied to the caller method.
	 */
	
	public int getGroups( DeptKey deptKey,List<GroupData> groupDataArr )
	{
		int responseCode = 0;
		
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		List<GroupData> deptGroups = new ArrayList<GroupData>( );
		
		List<GroupData> allGroups  = new ArrayList<GroupData>( );
		
		responseCode = find( deptKey, deptGroups );
		
		if( responseCode == 2121 )
		{
			return 2131;
		}
		
		GroupsTable groupsTable = new GroupsTable( );
		
		responseCode = groupsTable.getAllGroups( deptKey.companyRegnKey_, allGroups );
		
		errorMaster_.insert( "allgroups count="+allGroups.size( )
				+"deptcount="+deptGroups.size( ));
		
		groupsTable = null;
		
		if( responseCode !=0 )
		{
			deptGroups = null;
			
			allGroups  = null;
			
			return 2131;
		}
		
		String errMsg = "";
				
		try
        {
			// Minus the group data from all groups to department groups
			/*for( GroupData groupData : allGroups )
	        {
		        if( !deptGroups.contains( groupData ))
		        {
		        	groupDataArr.add( groupData );
		        	
		        	errorMaster_.insert( "if part" );
		        }
		        else 
		        {
		        	errorMaster_.insert( "else part" );
				}
	        }*/
			
			boolean isExist = false;
			
			for( GroupData groupData : allGroups )
	        {
				for( GroupData groupData2 :deptGroups)
				{
					//errorMaster_.insert( "all groupname="+ groupData.groupName_+"group groupname="+ groupData2.groupName_);
					
					 if( (groupData.groupName_.equals( groupData2.groupName_)))
					 {
						 isExist = true;
					 }
				}
				
				if( !isExist )
				{
					groupDataArr.add( groupData );
				}
				
				isExist = false;
		       
	        }
			
			
        }
        catch( ClassCastException e )
        {
        	errMsg = "Exception::GroupDeptMapper:getGroups - "+e;
			
			errorLogger.logMsg( errMsg );
			
			deptGroups = null;
			
			allGroups  = null;
			
			return 2131;
			
        }
		catch( NullPointerException e )
        {
        	errMsg = "Exception::GroupDeptMapper:getGroups - "+e;
			
			errorLogger.logMsg( errMsg );
			
			deptGroups = null;
			
			allGroups  = null;
			
			return 2131;
			
        }
		catch( Exception e )
        {
        	errMsg = "Exception::GroupDeptMapper:getGroups - "+e;
			
			errorLogger.logMsg( errMsg );
			
			deptGroups = null;
			
			allGroups  = null;
			
			return 2131;
			
        }
		
		deptGroups = null;
		
		allGroups  = null;
		
		return 2130;
	}

	
}
