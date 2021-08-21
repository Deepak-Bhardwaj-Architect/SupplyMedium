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

import utils.ErrorLogger;

import core.dept.DeptData;
import core.dept.DeptFolderData;
import core.dept.DeptFolderKey;
import core.dept.DeptKey;
import core.privilege.DeptPrivilegesData;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import core.usermgmt.GroupData;
import core.usermgmt.GroupKey;


/**
 * File:  DeptMgmtDataConverter.java 
 *
 * Created on Mar 6, 2013 6:48:39 PM
 */

/*
 * This class is convert the HttpServletRequest request to data classes of the 
 * Department.
 */

public class DeptMgmtDataConverter
{
	/*
	 * Method : getDeptData( ) 
	 * 
	 * Input  : HttpServletRequest object, DeptData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to DeptData object.
	 */
	
	public int getDeptData( HttpServletRequest request,DeptData deptData )
	{
		try
		{

			DeptKey deptKey = new DeptKey( );

			int responseCode = getDeptKey( request, deptKey );

			if( responseCode == 0 )
			{
				deptData.deptKey_ = deptKey;
				deptData.deptName_ =  request.getParameter( "DeptName" );
			}
			
			return responseCode;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errMsg = "Exception :: DeptMgmtDataConvert : getDeptKey - " + ex;

			errLogger.logMsg( errMsg );

			return -1;  // failed
		}
	}
	
	/*
	 * Method : getDeptKey( ) 
	 * 
	 * Input  : HttpServletRequest object, DeptKey object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to DeptKey object.
	 */
	
	public int getDeptKey( HttpServletRequest request,DeptKey deptKey )
	{
		try
        {
			int responseCode = 0;
			
	        CompanyRegnKey regnKey = new CompanyRegnKey( );
	        
	        responseCode = getRegnKey( request, regnKey );
	        
	        if( responseCode == 0 )
	        {
	        	String requestType = request.getParameter( "RequestType" );
	        	
	        	deptKey.companyRegnKey_ = regnKey;
	        	 //For new department creation
	        	if( requestType.equals( "AddNewDept" ) ) 
	        	{
	        		deptKey.deptName_ = request.getParameter( "DeptName" );
	        	}
	        	else  // Other than all operation having the deptkey
	        	{
	        		String deptKeyStr = request.getParameter( "DeptKey" );
	        		
	        		if( deptKeyStr != null )
	        		{
	        			 String [] strArr = deptKeyStr.split( ":" );
	 	 	            
	 	 	            if( strArr.length > 1)
	 	 	            {
	 	 	            	String deptName = strArr[1];
	 	 	            	
	 	 	            	deptKey.deptName_ = deptName;
	 	 	            }
	        		}
	        		
	        		
				}
	        }
	        
	        return responseCode;
        }
        catch( Exception ex )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
        	String errMsg = "Exception :: DeptMgmtDataConvert : getDeptKey - "+ex;
			
			errLogger.logMsg( errMsg );
			
	        return -1;  // failed
        }
	}
	
	/*
	 * Method : getDeptPrivileges( ) 
	 * 
	 * Input  : HttpServletRequest object, DeptPrivilegesData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to DeptPrivilegesData 
	 * object.
	 */
	
	public int getDeptPrivileges( HttpServletRequest request,
									DeptPrivilegesData deptPrivilegesData )
	{
		
		String addFolder = request.getParameter( "AddFolder" );
		
		String deleteFodler = request.getParameter( "DeleteFolder" );
		
		String addFile = request.getParameter( "AddFile" );
		
		String deleteFile = request.getParameter( "DeleteFile" );
		
		String postAnnouncement = request.getParameter( "PostAnnouncement" );
		
		String manageFolder = request.getParameter( "ManageFolder" );
		
		if( addFolder != null )
		{
			if(addFolder.equals( "1" ))
			{
				deptPrivilegesData.addFolder_ = true;
			}
			else 
			{
				deptPrivilegesData.addFolder_ = false;
			}
		}
		
		if( deleteFodler != null )
		{
			if(deleteFodler.equals( "1" ))
			{
				deptPrivilegesData.deleteFolder_ = true;
			}
			else 
			{
				deptPrivilegesData.deleteFolder_ = false;
			}
		}
		
		if( addFile != null )
		{
			if(addFile.equals( "1" ))
			{
				deptPrivilegesData.addFile_ = true;
			}
			else 
			{
				deptPrivilegesData.addFile_ = false;
			}
		}
		
		if( deleteFile != null )
		{
			if(deleteFile.equals( "1" ))
			{
				deptPrivilegesData.deleteFile_ = true;
			}
			else 
			{
				deptPrivilegesData.deleteFile_ = false;
			}
		}
		
		if( postAnnouncement != null )
		{
			if(postAnnouncement.equals( "1" ))
			{
				deptPrivilegesData.postAnnouncement_ = true;
			}
			else 
			{
				deptPrivilegesData.postAnnouncement_ = false;
			}
		}
		
		if( manageFolder != null )
		{
			if(manageFolder.equals( "1" ))
			{
				deptPrivilegesData.manageFolder_ = true;
			}
			else 
			{
				deptPrivilegesData.manageFolder_ = false;
			}
		}
		
		
		return 0;
	}
	
	/*
	 * Method : getDeptFolderData( ) 
	 * 
	 * Input  : HttpServletRequest object, DeptFolderData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to DeptFolderData 
	 * object.
	 */
	
	public int getDeptFolderData( HttpServletRequest request,DeptFolderData deptFolderData, DeptKey deptKey )
	{
		try
		{
			
			DeptFolderKey deptFolderKey = new DeptFolderKey( );

			int responseCode = getDeptFolderKey( request, deptFolderKey );

			if( responseCode == 0 )
			{
				deptFolderData.deptFolderKey_ = deptFolderKey;
				
				deptFolderData.folderName_    = request.getParameter( "FolderName" );
			}
			
			if( request.getParameter( "RequestType" ).equals( "DPAddNewFolder" ) ||
				request.getParameter( "RequestType" ).equals( "DPRemoveFolder" ) || 
				request.getParameter( "RequestType" ).equals( "DPRemoveFolderToRB")||
				request.getParameter( "RequestType" ).equals( "DPRestoreFolderFromRB"))
			{
				String [] deptKeyArr = request.getParameter( "DeptKey" ).split( ":" );
				
				deptKey.companyRegnKey_ = new CompanyRegnKey( );
				
				deptKey.companyRegnKey_.companyPhoneNo_ = deptKeyArr[0];
				
				deptKey.deptName_ = deptKeyArr[1];
			}
			
			
			return responseCode;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errMsg = "Exception :: DeptMgmtDataConvert : getDeptKey - " + ex;

			errLogger.logMsg( errMsg );

			return -1;  // failed
		}
	}
	
	/*
	 * Method : getFolderKey( ) 
	 * 
	 * Input  : HttpServletRequest object, DeptFolderKey object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to DeptFolderKey object.
	 */
	
	public int getDeptFolderKey( HttpServletRequest request,DeptFolderKey deptFolderKey )
	{
		
		try
        {
			int responseCode = 0;
			
	        CompanyRegnKey regnKey = new CompanyRegnKey( );
	        
	        responseCode = getRegnKey( request, regnKey );
	        
	        if( responseCode == 0 )
	        {
	        	
	        	String requestType = request.getParameter( "RequestType" );
	        	
	        	deptFolderKey.companyRegnKey_ = regnKey;
	        	 //For new folder creation
	        	if( requestType.equals( "AddNewFolder" ) || requestType.equals( "DPAddNewFolder" ) ) 
	        	{
	        		deptFolderKey.folderName_ = request.getParameter( "FolderName" );
	        	}
	        	else  // Other than all operation having the deptkey
	        	{
	        		String deptKeyStr = request.getParameter( "FolderKey" );
	        		
	        		if( deptKeyStr != null )
	        		{
	        			 String [] strArr = deptKeyStr.split( ":" );
	 	 	            
	 	 	            if( strArr.length > 1)
	 	 	            {
	 	 	            	String folderName = strArr[1];
	 	 	            	
	 	 	            	deptFolderKey.folderName_ = folderName;
	 	 	            }
	        		}
				}
	        }
	       
	        
	        return responseCode;
        }
        catch( Exception ex )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
        	String errMsg = "Exception :: DeptMgmtDataConvert : getDeptFolderKey - "+ex;
			
			errLogger.logMsg( errMsg );
			
	        return -1;  // failed
        }
	}
	
	/*
	 * Method : getGroupsData( ) 
	 * 
	 * Input  : HttpServletRequest object, list of GroupData objects.
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to list of GroupData 
	 * objects.
	 */
	
	public int getGroupsData( HttpServletRequest request,List<GroupData> groupDataArr )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		try
        {
			int responseCode = 0;
			
			if (!request.getParameterMap().containsKey("GroupKeys[]")) 
			{
				return responseCode;
			}
			
			String [] groupsKeyStrArr = request.getParameterValues( "GroupKeys[]" );
			
			if( groupsKeyStrArr == null )
			{
				
	        	String errMsg = "Exception::DeptMgmtDataConvert:getGroupsData - " +
	        			"Parser error ";
				
				errLogger.logMsg( errMsg );
				
				return -1;
			}
			
			for( String groupStr : groupsKeyStrArr )
            {
	            String [] strArr = groupStr.split( ":" );
	            
	            if( strArr.length > 1)
	            {
	            	String groupName = strArr[1];
	            	
	            	String companyPhoneNo = strArr[0];
	            	
	            	GroupData groupData = new GroupData( );
	            	
	            	GroupKey groupKey = new GroupKey( );
	            	
	            	CompanyRegnKey companyRegnKey = new CompanyRegnKey( );
	            	
	            	companyRegnKey.companyPhoneNo_ = companyPhoneNo;
	            	
	            	groupKey.companyRegnKey_ = companyRegnKey;
	            	
	            	companyRegnKey = null;
	            	
	            	groupKey.groupName_ = groupName;
	            	
	            	groupData.groupKey_ = groupKey;
	            	
	            	groupKey = null;
	            	
	            	groupData.groupName_ = groupName;
	            	
	            	groupDataArr.add( groupData );
	            	
	            	groupData = null;
	            	
	            }
            }
			
	        return responseCode;
        }
        catch( Exception ex )
        {
        	
        	String errMsg = "Exception::DeptMgmtDataConvert:getGroupsData - "+ex;
			
			errLogger.logMsg( errMsg );
			
	        return -1;  // failed
        }
	}
	
	/*
	 * Method : getDeptFoldersData( ) 
	 * 
	 * Input  : HttpServletRequest object, list of DeptFolderData objects.
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to list 
	 * of DeptFolderData objects.
	 */
	
	public int getDeptFoldersData( HttpServletRequest request,
									List<DeptFolderData> deptFolderDataArr )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		try
        {
			int responseCode = 0;
			
			if (!request.getParameterMap().containsKey("FolderKeys[]")) 
			{
				return responseCode;
			}
			
			String [] foldersKeyStrArr = request.getParameterValues( "FolderKeys[]" );
			
			if( foldersKeyStrArr == null )
			{
				
	        	String errMsg = "Exception::DeptMgmtDataConvert:getDeptFoldersData - " +
	        			"Parser error ";
				
				errLogger.logMsg( errMsg );
				
				return -1;
			}
			
			for( String folderKeyStr : foldersKeyStrArr )
            {
	            String [] strArr = folderKeyStr.split( ":" );
	            
	            if( strArr.length > 1)
	            {
	            	String folderName = strArr[1];
	            	
	            	String companyPhoneNo = strArr[0];
	            	
	            	DeptFolderData deptFolderData = new DeptFolderData( );
	            	
	            	DeptFolderKey deptFolderKey = new DeptFolderKey( );
	            	
	            	CompanyRegnKey companyRegnKey = new CompanyRegnKey( );
	            	
	            	companyRegnKey.companyPhoneNo_ = companyPhoneNo;
	            	
	            	deptFolderKey.companyRegnKey_ = companyRegnKey;
	            	
	            	companyRegnKey = null;
	            	
	            	deptFolderKey.folderName_ = folderName;
	            	
	            	deptFolderData.deptFolderKey_ = deptFolderKey;
	            	
	            	deptFolderKey = null;
	            	
	            	deptFolderData.folderName_ = folderName;
	            	
	            	deptFolderDataArr.add( deptFolderData );
	            	
	            	deptFolderData = null;
	            	
	            }
            }
			
	        return responseCode;
        }
        catch( Exception ex )
        {
        	
        	String errMsg = "Exception::DeptMgmtDataConvert:getDeptFolderData - "+ex;
			
			errLogger.logMsg( errMsg );
			
	        return -1;  // failed
        }
	}
	
	
	/*
	 * Method : getUsersData( ) 
	 * 
	 * Input  : HttpServletRequest object, list of UserProfileData objects.
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to list 
	 * of UserProfileData objects.
	 */
	
	public int getUsersData( HttpServletRequest request,
									List<UserProfileData> userProfileDataArr )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		try
        {
			int responseCode = 0;
			
			if (!request.getParameterMap().containsKey("UserKeys[]")) 
			{
				return responseCode;
			}
			
			String [] usersKeyStrArr = request.getParameterValues( "UserKeys[]" );
			
			if( usersKeyStrArr == null )
			{
				
	        	String errMsg = "Exception::DeptMgmtDataConvert:getUsersData - " +
	        			"Parser error ";
				
				errLogger.logMsg( errMsg );
				
				return -1;
			}
			
			for( String userKeyStr : usersKeyStrArr )
            {
				
				UserProfileData userProfileData = new UserProfileData( );

				UserProfileKey userProfileKey = new UserProfileKey( );

				userProfileKey.email_ = userKeyStr;

				userProfileData.userProfileKey_ = userProfileKey;

				userProfileKey = null;

				userProfileData.emailId_ = userKeyStr;

				userProfileDataArr.add( userProfileData );

				userProfileData = null;

            }
			
	        return responseCode;
        }
        catch( Exception ex )
        {
        	
        	String errMsg = "Exception::DeptMgmtDataConvert:getUsersData - "+ex;
			
			errLogger.logMsg( errMsg );
			
	        return -1;  // failed
        }
	}
	
	/*
	 * Method : getRegnKey( ) 
	 * 
	 * Input  : HttpServletRequest object, CompanyRegnKey object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to CompanyRegnKey
	 * object.
	 */
	
	public int getRegnKey( HttpServletRequest request,CompanyRegnKey regnKey )
	{
		try
        {
	       regnKey.companyPhoneNo_ = request.getParameter( "RegnKey" );
	       
	       return 0;
	        
        }
        catch( Exception ex )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
        	String errMsg = "Exception :: DeptMgmtDataConvert : getDeptKey - "+ex;
			
			errLogger.logMsg( errMsg );
			
	        return -1; // failed
        }
	}
	
	
}
