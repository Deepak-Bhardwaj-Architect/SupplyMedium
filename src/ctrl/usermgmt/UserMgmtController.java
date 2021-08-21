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

package ctrl.usermgmt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import core.login.SessionData;
import core.privilege.PrivilegesComposer;
import core.privilege.UsergroupPrivilegesData;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileData;
import core.usermgmt.GroupData;
import core.usermgmt.GroupManager;
import core.usermgmt.UserGroupMapper;

import utils.ErrorLogger;

/**
 * File:  UserMgmtController.java 
 *
 * Created on Mar 1, 2013 6:28:57 PM
 */


/*
 * This is the controller class for user management. It get the 
 * call from servlet and forward that call to corresponding core classes.
 * 
 */

public class UserMgmtController
{
	/*
	 * Method : manageGroup( ) Input : HttpServletRequest object Return :
	 * int
	 * 
	 * Purpose:
	 * 
	 * The request types are 
	 * 
	 * 1. "NewUsergroup" request
	 * 2. "UpdateUsergroup" request
	 * 3. "DeleteUsergroup" request 
	 */
	
	public int manageGroup( HttpServletRequest request  )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );	
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		UserMgmtDataConverter converter = new UserMgmtDataConverter( );
		
		GroupData groupData	= new GroupData( );
		
		converter.getGroupData( request, groupData );
		
		converter = null;
		
		GroupManager groupMgr = new GroupManager( );
		
		int responseCode = 0;
		
		if( request.getParameter( "RequestType" ).equals( "NewUserGroup" ))
		{			
			String errMsg = "Info::UserMgmtController.manageGroup() - Add New Group - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errorLogger.logMsg( errMsg );
			
			responseCode = groupMgr.add( groupData );
		}
		else if( request.getParameter( "RequestType" ).equals( "UpdateUserGroup" ) )
		{
			String errMsg = "Info::UserMgmtController.manageGroup() - Update User Group - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errorLogger.logMsg( errMsg );
			
			responseCode = groupMgr.update( groupData );
		}
		else if( request.getParameter( "RequestType" ).equals( "DeleteUserGroup" ) )
		{
			String errMsg = "Info::UserMgmtController.manageGroup() - Delete User Group - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errorLogger.logMsg( errMsg );
			
			responseCode = groupMgr.remove( groupData );
		}
		else
		{
			errorLogger.logMsg( "UserMgmtController::manageGroup() - Error occurred while processing " +
					request.getParameter( "RequestType" ) + " request"   +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">");
			
			responseCode = 1002; // Error while processing request
		}
		
		groupMgr = null;

		groupData = null;
		
		System.gc( );

		return responseCode;
	}

	/*
	 * Method : mapUserGroup( ) Input : HttpServletRequest object Return :
	 * int
	 * 
	 * Purpose:
	 * 
	 * The request types are 
	 * 
	 * 1. "AddUser" request
	 * 2. "RemoveUser" request
	 * 3. "FindUser" request 
	 * 4. "NonGroupUsers" request
	 */
	
	public int mapUserGroup( HttpServletRequest request, List< UserProfileData > userProfileDataList )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );	
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		UserGroupMapper mapper = new UserGroupMapper( );
		
		UserMgmtDataConverter converter = new UserMgmtDataConverter( );
		
		GroupData groupData = new GroupData( );
		
		int code = converter.getGroupData( request, groupData );
		
		System.out.println( "GroupData: " +groupData );
		
		int result	= converter.getUsersData( request, userProfileDataList );
		
		System.out.println( "UserProfileDataList: " + userProfileDataList );
		
		int responseCode = 0;
		
		converter = null;
		
		if( request.getParameter( "RequestType" ).equals( "AddUser" ))
		{	
			String errMsg = "Info::UserMgmtController.mapUserGroup() - Add user to group - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errorLogger.logMsg( errMsg );
			
			responseCode = mapper.add( groupData.groupKey_, userProfileDataList );
		}
		else if( request.getParameter( "RequestType" ).equals( "RemoveUser" ) )
		{
			String errMsg = "Info::UserMgmtController.mapUserGroup() - Remove user from group - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errorLogger.logMsg( errMsg );
			
			responseCode = mapper.remove( groupData.groupKey_, userProfileDataList );
		}
		else if( request.getParameter( "RequestType" ).equals( "FindUser" ) )
		{
			String errMsg = "Info::UserMgmtController.mapUserGroup() - Find users of a group - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errorLogger.logMsg( errMsg );
			
			responseCode = mapper.find( groupData.groupKey_, userProfileDataList );
		}
		else if( request.getParameter( "RequestType" ).equals( "NonGroupUsers" ) )
		{
			String errMsg = "Info::UserMgmtController.mapUserGroup() - Find non group users - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errorLogger.logMsg( errMsg );
			
			responseCode = mapper.getUsers( groupData.groupKey_, userProfileDataList );
		}
		
		else
		{
			errorLogger.logMsg( "Error::UserMgmtController::manageGroup() - Error while processing " +
					request.getParameter( "RequestType" ) + " request"  +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">");
			
			responseCode = 1002;
		}	
		
		mapper = null;
		
		groupData = null;

		return responseCode; 
	}
	
	/*
	 * Method : groupPrivileges( ) Input : HttpServletRequest object Return :
	 * int
	 * 
	 * Purpose:
	 * 
	 * The request types are 
	 * 
	 * 1. "UpdatePrivileges" request
	 * 2. "FindGroupPrivileges" request
	 *  
	 */
	
	public int groupPrivileges( HttpServletRequest request, UsergroupPrivilegesData privilegesData )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );	
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		PrivilegesComposer composer = new PrivilegesComposer( );
		
		UserMgmtDataConverter converter = new UserMgmtDataConverter( );
		
		GroupData groupData	= new GroupData( );
		
		int result = converter.getGroupData( request, groupData );
		
		System.out.println( "Result: " + result );
		
		//converter = null;
		
		int responseCode = 0;
		
		if( request.getParameter( "RequestType" ).equals( "UpdatePrivileges" ) )
		{
			String errMsg = "Info::UserMgmtController.groupPrivileges() - Update privileges - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errorLogger.logMsg( errMsg );
			
			privilegesData = converter.getGroupPrivilegesData( request ); 
			privilegesData.groupKey_ = groupData.groupKey_;
			
			responseCode = composer.setGroupPrivileges( groupData.groupKey_, privilegesData );
		}
		else if( request.getParameter( "RequestType" ).equals( "FindGroupPrivileges" ) )
		{
			String errMsg = "Info::UserMgmtController.groupPrivileges() - Find Group Privileges - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errorLogger.logMsg( errMsg );
			
			responseCode = composer.getGroupPrivileges( groupData.groupKey_, privilegesData );
		}
		else
		{
			errorLogger.logMsg( "UserMgmtController::groupPrivileges - Error while processing " +
					request.getParameter( "RequestType" ) + " request " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">");
			responseCode = 1002;
		}
		
		composer = null;
		
		groupData = null;

		return responseCode; 
	}
	
	/*
	 * Method : getAllUserGroups( ) Input : HttpServletRequest object Return :
	 * int
	 * 
	 * Purpose:
	 * 
	 * The request types are 
	 * 
	 * 1. "GetAllUserGroups" request
	 * 
	 */
	
	public int getAllUserGroups( HttpServletRequest request, List< GroupData > dataArr )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );	
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		GroupManager manager = new GroupManager( );
		
		int responseCode = 0;
		
		CompanyRegnKey key = new CompanyRegnKey( );
		
		key.companyPhoneNo_ = request.getParameter( "RegnKey" );
		
		System.out.println( "Company key got successfully.. : "+ key.companyPhoneNo_ );
		
		if( request.getParameter( "RequestType" ).equals( "GetAllUserGroups" ) )
		{
			String errMsg = "Info::UserMgmtController.getAllUserGroups() - Get all user groups - Request In Process " +
	                "for company <" + request.getParameter( "RegnKey" )+ ">, <" + sessionData.companyName_ + ">"
	                + " by user <" + sessionData.username_ + ">";
			
			errorLogger.logMsg( errMsg );
			
			responseCode = manager.getAllUserGroups( key, dataArr );
		}
		
		System.out.println( "Response code in UsermgmtCtrl: "+responseCode );

		return responseCode;
	}

}
