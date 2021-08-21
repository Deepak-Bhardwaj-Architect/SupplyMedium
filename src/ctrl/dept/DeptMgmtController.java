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

package ctrl.dept;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import utils.ErrorLogger;

import core.dept.DeptData;
import core.dept.DeptFolderData;
import core.dept.DeptFolderManager;
import core.dept.DeptKey;
import core.dept.DeptManager;
import core.dept.FolderDeptMapper;
import core.dept.GroupDeptMapper;
import core.dept.UserDeptMapper;
import core.login.SessionData;
import core.privilege.DeptPrivilegesData;
import core.privilege.PrivilegesComposer;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileData;
import core.usermgmt.GroupData;

/**
 * File:  DeptMgmtController.java 
 *
 * Created on Mar 6, 2013 2:39:16 PM
 */

/*
 * This is the controller for department management. It is the intermediator between 
 * servlet and core classes.
 * 
 */

public class DeptMgmtController
{
	private ErrorLogger errLogger_ ;
	
	/*
	 * Method : DeptMgmtController( ) - constructor
	 * 
	 * Input :none
	 * 
	 * Return :none
	 * 
	 * Purpose: It is used intialize the ErrorLogger object
	 */
	
	public DeptMgmtController()
	{
		
		errLogger_  = ErrorLogger.instance();
	}
	/*
	 * Method : manageDept( ) 
	 * 
	 * Input  : HttpServletRequest request
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose:It is used to do the one of following operation for the departments.
	 * New department creation, Rename the department and remove the department
	 * from the Company. Request type is decide the which operation need to be done here.
	 */
	
	public int manageDept( HttpServletRequest request )
	{
		String requestType = request.getParameter( "RequestType" );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		int responseCode = 0;
		
		DeptData deptData = new DeptData( );
		
		DeptManager deptManager = new DeptManager( );
		
		// Convert HttpServletRequest to DeptData object
		DeptMgmtDataConverter dataConverter = new DeptMgmtDataConverter( );
		
		responseCode = dataConverter.getDeptData( request, deptData );
		
		dataConverter = null;
		
		if( responseCode != 0)
		{
			return 1901;
		}
		
		if( requestType.equals( "AddNewDept" ))  // New department creation
		{
			String errMsg = "Info::DeptMgmtController.manageDept() - Add New Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = deptManager.add( deptData );
		}
		else if( requestType.equals( "UpdateDept" ))  // Rename the department
		{
			String errMsg = "Info::DeptMgmtController.manageDept() - Update Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = deptManager.update( deptData );
		}
		else if( requestType.equals( "RemoveDept" )) // delete the department from SM
		{
			String errMsg = "Info::DeptMgmtController.manageDept() - Remove Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = deptManager.remove( deptData );
		}
		else  //Response type not match with any of this
		{
			
			String errMsg = "Error::DeptMgmtController.manageDept() - Error while processing " +
					requestType + " request";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = 1901;
		}
		
		deptManager = null;
		
		deptData = null;
		
		System.gc( );
		
		return responseCode;
		
		
	}
	
	/*
	 * Method : manageDeptFolder( ) 
	 * 
	 * Input  : HttpServletRequest request
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose:It is used to do the one of following operation for the folders.
	 * New folder creation, Rename the folder and remove the folder
	 * from the Company. Request type is decide the which operation need to be done here.
	 */
	
	public int manageDeptFolder( HttpServletRequest request )
	{
		String requestType = request.getParameter( "RequestType" );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		int responseCode = 0;
		
		DeptFolderData deptFolderData = new DeptFolderData( );
		
		DeptFolderManager deptFolderManager = new DeptFolderManager( );
		
		// Convert HttpServletRequest object to DeptFolderData object
		DeptMgmtDataConverter deptConverter = new DeptMgmtDataConverter( );
		
		DeptKey deptKey = new DeptKey( );
		
		responseCode = deptConverter.getDeptFolderData( request, deptFolderData, deptKey );
		
		deptConverter = null;
		
		if( responseCode != 0)
		{
			return 2001;
		}
		
		if( requestType.equals( "AddNewFolder" ))  // New Folder creation
		{
			String errMsg = "Info::DeptMgmtController.manageDeptFolder() - Add new folder to Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = deptFolderManager.add( deptFolderData );
		}
		else if( requestType.equals( "UpdateFolder" )) // Rename the folder
		{
			String errMsg = "Info::DeptMgmtController.manageDeptFolder() - Update folder in Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = deptFolderManager.update( deptFolderData );
		}
		else if( requestType.equals( "RemoveFolder" )) // delete the folder from SM
		{
			String errMsg = "Info::DeptMgmtController.manageDeptFolder() - Remove folder from Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = deptFolderManager.remove( deptFolderData );
		}
		else if( requestType.equals( "DPAddNewFolder" ) )
		{
			String errMsg = "Info::DeptMgmtController.manageDeptFolder() - Add New Folder from " +
					"Department Page - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = deptFolderManager.add( deptFolderData, deptKey );
		}
		else if( requestType.equals( "DPFullRemoveFolder" ) )
		{
			String errMsg = "Info::DeptMgmtController.manageDeptFolder() - Remove folder from " +
					"Department Page - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = deptFolderManager.remove( deptFolderData, deptKey );
		}
		else if( requestType.equals( "DPRestoreFolderFromRB" ) )
        {
			String errMsg = "Info::DeptMgmtController.manageDeptFolder() - Restore folder from recycle bin in " +
					"Department Page - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode =  deptFolderManager.restore( deptFolderData, deptKey );
		}
		else if( requestType.equals( "DPRemoveFolderToRB" ))
		{
			String errMsg = "Info::DeptMgmtController.manageDeptFolder() - Move folder to recycle bin in " +
					"Department Page - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode =  deptFolderManager.moveToRecyle( deptFolderData, deptKey );
		}
		else  // Response type not match with any of this
		{
			String errMsg = "Error::DeptMgmtController.manageDeptFolder() - Error while processing " +
					requestType + " request";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = 2001;
		}
		
		deptFolderManager = null;
		
		deptFolderData = null;

		return responseCode;
	}
	
	
	/*
	 * Method : mapGroupDept( ) 
	 * 
	 * Input  : HttpServletRequest request, list of GroupData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose:It is used to do the one of following operation for mapping between groups 
	 * and department. Assign groups to department, remove the groups from department
	 * and find all the groups for department. 
	 * Request type is decide the which operation need to be done here.
	 */
	
	public int mapGroupDept( HttpServletRequest request,List<GroupData> groupDataArr )
	{
		String requestType = request.getParameter( "RequestType" );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		int responseCode = 0;
		
		GroupDeptMapper groupDeptMapper = new GroupDeptMapper( );
		
		DeptKey deptKey = new DeptKey( );
		
		DeptMgmtDataConverter deptConverter = new DeptMgmtDataConverter( );
		
		// Convert HttpServletRequest object to DeptKey object
		responseCode = deptConverter.getDeptKey( request, deptKey );
		
		if( responseCode != 0)
		{
			deptConverter = null;
			
			return 2101;
		}
		
		// Convert HttpServletRequest object to list of GroupData object
		responseCode = deptConverter.getGroupsData( request, groupDataArr );
		
		deptConverter = null;
		
		if( responseCode != 0)
		{
			return 2101;
		}
		
	
		if( requestType.equals( "AddGroup" ))  // Assign groups to department
		{
			String errMsg = "Info::DeptMgmtController.mapGroupDept() - Add Group to Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = groupDeptMapper.add( deptKey, groupDataArr );
		}
		else if( requestType.equals( "RemoveGroup" )) // Remove groups from department
		{
			String errMsg = "Info::DeptMgmtController.mapGroupDept() - Remove Group from Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = groupDeptMapper.remove( deptKey, groupDataArr );
		}
		else if( requestType.equals( "FindGroup" )) // Fetch all groups for department
		{
			String errMsg = "Info::DeptMgmtController.mapGroupDept() - Find Groups for Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = groupDeptMapper.find( deptKey, groupDataArr );
			
		} // Fetch all groups that not present in department
		else if( requestType.equals( "ExceptGroup" )) 
		{
			String errMsg = "Info::DeptMgmtController.mapGroupDept() - Find Non Dept Groups for Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = groupDeptMapper.getGroups( deptKey, groupDataArr );
		}
		else
		{
			
			String errMsg = "Error::DeptMgmtController.mapGroupDept() - Error while processing " +
					requestType + " request";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = 2101;
		}
		
		deptKey = null;
		
		groupDeptMapper = null;

		return responseCode;
		
	}
	
	/*
	 * Method : mapFolderDept( ) 
	 * 
	 * Input  : HttpServletRequest request, list of DeptFolderData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose:It is used to do the one of following operation for mapping between folders 
	 * and department. Assign folders to department, remove the folders from department
	 * and find all the folders for department. 
	 * Request type is decide the which operation need to be done here.
	 */
	
	public int mapFolderDept( HttpServletRequest request,List<DeptFolderData> deptFolderDataArr )
	{
		String requestType = request.getParameter( "RequestType" );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		int responseCode = 0;
		
		DeptKey deptKey = new DeptKey( );
		
		FolderDeptMapper folderDeptMapper = new FolderDeptMapper( );
		
		DeptMgmtDataConverter deptConverter = new DeptMgmtDataConverter( );
		
		// Convert HttpServletRequest object to DeptKey object
		deptConverter.getDeptKey( request, deptKey );
		
		// Convert HttpServletRequest object to list of DeptFolderData object
		deptConverter.getDeptFoldersData( request, deptFolderDataArr );
		
		deptConverter = null;
		
		
		if( requestType.equals( "AddFolder" )) // Assign folders to department
		{
			String errMsg = "Info::DeptMgmtController.mapFolderDept() - Add folder to Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			
			responseCode = folderDeptMapper.add( deptKey, deptFolderDataArr );
		}
		else if( requestType.equals( "RemoveFolder" )) // Remove folders from department
		{
			String errMsg = "Info::DeptMgmtController.mapFolderDept() - Remove folder from Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = folderDeptMapper.remove( deptKey, deptFolderDataArr );
		}
		else if( requestType.equals( "FindFolder" )) // Fetch all folders for department
		{
			String errMsg = "Info::DeptMgmtController.mapFolderDept() - Find folders for Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = folderDeptMapper.find( deptKey, deptFolderDataArr );
		}
		else if( requestType.equals( "ExceptFolder" )) 
		{
			String errMsg = "Info::DeptMgmtController.mapFolderDept() - Find Non Dept folders for Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = folderDeptMapper.getFolders( deptKey, deptFolderDataArr );
		}
		else
		{
			
			String errMsg = "Error::DeptMgmtController:mapFolderDept() - Error while processing " +
					requestType + " request";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = 2201;
		}
		
		folderDeptMapper = null;
		
		deptKey = null;

		return responseCode;
		
	}
	
	/*
	 * Method : mapUserDept( ) 
	 * 
	 * Input  : HttpServletRequest request, list of UserProfileData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose:It is used to do the one of following operation for mapping between folders 
	 * and department. Assign folders to department, remove the folders from department
	 * and find all the folders for department. 
	 * Request type is decide the which operation need to be done here.
	 */
	
	public int mapUserDept( HttpServletRequest request,List<UserProfileData> userProfileDataArr )
	{
		String requestType = request.getParameter( "RequestType" );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		int responseCode = 0;
		
		DeptKey deptKey = new DeptKey( );
		
		UserDeptMapper userDeptMapper = new UserDeptMapper( );
		
		DeptMgmtDataConverter deptMgmtConverter = new DeptMgmtDataConverter( );
		
		// Convert HttpServletRequest object to DeptKey object
		deptMgmtConverter.getDeptKey( request, deptKey );
		
		// Convert HttpServletRequest object to list of users data
		deptMgmtConverter.getUsersData( request, userProfileDataArr );
		
		
		deptMgmtConverter = null;
		
		if( requestType.equals( "AddUser" ))  // Assign users to department
		{
			String errMsg = "Info::DeptMgmtController.mapUserDept() - Add User to Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = userDeptMapper.add( deptKey, userProfileDataArr );
		}
		else if( requestType.equals( "RemoveUser" ))  // Remove users from department
		{
			String errMsg = "Info::DeptMgmtController.mapUserDept() - Remove User from Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = userDeptMapper.remove( deptKey, userProfileDataArr );
		}
		else if( requestType.equals( "FindUser" ))   // Fetch all users for the department
		{
			String errMsg = "Info::DeptMgmtController.mapUserDept() - Find Users for Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = userDeptMapper.find( deptKey, userProfileDataArr );
		}
		else if( requestType.equals( "ExceptUser" )) 
		{
			String errMsg = "Info::DeptMgmtController.mapUserDept() - Find Non Dept Users for Dept - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = userDeptMapper.getUsers( deptKey, userProfileDataArr );
		}
		else
		{
			
			String errMsg = "Error::DeptMgmtController.mapUserDept() - Error while processing " +
					requestType + " request";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = 2301;
		}
		
		userDeptMapper = null;
		
		deptKey = null;

		return responseCode;
		
	}
	
	
	/*
	 * Method : deptPrivileges( ) 
	 * 
	 * Input  : HttpServletRequest request,  UserProfileData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose:It is used to do the one of following operation for department privileges.
	 * Update the privileges and get the privileges for department.
	 * Request type is decide the which operation need to be done here.
	 */
	
	public int deptPrivileges( HttpServletRequest request,DeptPrivilegesData deptPrivilegesData )
	{
		
		String requestType = request.getParameter( "RequestType" );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		int responseCode = 0;
		
		DeptKey deptKey = new DeptKey( );
		
		PrivilegesComposer privilegesComp = new PrivilegesComposer( );
		
		DeptMgmtDataConverter deptMgmtConverter = new DeptMgmtDataConverter( );
		
		// Convert HttpServletRequest object to DeptKey object
		deptMgmtConverter.getDeptKey( request, deptKey );
		
		// Convert HttpServletRequest object to DeptPrivileges Object
		deptMgmtConverter.getDeptPrivileges( request, deptPrivilegesData );
		
		deptMgmtConverter = null;
		
		if( requestType.equals( "UpdatePrivileges" ))  // Update the department privileges
		{
			String errMsg = "Info::DeptMgmtController.deptPrivileges() - Update Dept Privileges - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = privilegesComp.setDeptPrivileges( deptKey, deptPrivilegesData );
		}
		else if( requestType.equals( "FindPrivileges" ))  // Fetch the department privileges
		{
			String errMsg = "Info::DeptMgmtController.deptPrivileges() - Fetch Dept Privileges - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = privilegesComp.getDeptPrivileges( deptKey, deptPrivilegesData );
		}
		else
		{
			
			String errMsg = "Error::DeptMgmtController.deptPrivileges() - Error while processing " +
					requestType + " request";
			
			errLogger_.logMsg( errMsg );
			
			responseCode = 2401;
		}
		
		deptKey = null;
		
		privilegesComp = null;

		return responseCode;
		
	}
	
	/*
	 * Method : getAllDepartments( ) 
	 * 
	 * Input  : HttpServletRequest request,  list of deptData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose:It is used to fetch the all the departments of the supply medium.
	 * Assign to deptDataArr parameter so it will be copied to the caller method.
	 */
	
	public int getAllDepartments( HttpServletRequest request,List<DeptData> deptDataArr )
	{	
		int responseCode = 0;
		
		DeptMgmtDataConverter deptMgmtConverter = new DeptMgmtDataConverter( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		CompanyRegnKey regnKey = new CompanyRegnKey( );
		
		// Convert HttpServletRequest object to CompanyRegnKey object
		deptMgmtConverter.getRegnKey( request, regnKey );
		
		deptMgmtConverter = null;
		
		DeptManager deptmanager = new DeptManager( );
		
		String errMsg = "Info::DeptMgmtController.getAllDepartments() - Get All Departments - Request In Process " +
                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
		
		errLogger_.logMsg( errMsg );
		
		responseCode = deptmanager.getAllDepts( regnKey,deptDataArr );
		
		deptmanager = null;

		return responseCode;
	}
	
	/*
	 * Method : mapGroupDept( ) 
	 * 
	 * Input  : HttpServletRequest request, list of GroupData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose:It is used to fetch the all the user groups except the groups which is
	 * already added in given department key.
	 */
	
	public int getGroups( HttpServletRequest request,List<GroupData> groupDataArr )
	{
		int responseCode = 0;
		
		GroupDeptMapper groupDeptMapper = new GroupDeptMapper( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		DeptKey deptKey = new DeptKey( );
		
		DeptMgmtDataConverter deptConverter = new DeptMgmtDataConverter( );
		
		// Convert HttpServletRequest object to DeptKey object
		deptConverter.getDeptKey( request, deptKey );
		
		// Convert HttpServletRequest object to list of GroupData object
		deptConverter.getGroupsData( request, groupDataArr );
		
		deptConverter = null;
		
		String errMsg = "Info::DeptMgmtController.getGroups() - Get All Non Dept Groups - Request In Process " +
                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
		
		errLogger_.logMsg( errMsg );
		
		responseCode = groupDeptMapper.find( deptKey, groupDataArr );
		
		deptKey = null;
		
		groupDeptMapper = null;

		return responseCode;
	}
}
