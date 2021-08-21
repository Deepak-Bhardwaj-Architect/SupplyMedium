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

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.dept.DeptFolderKey;
import core.managefolder.GroupFolderData;
import core.managefolder.UserFolderData;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.usermgmt.GroupKey;
import utils.ErrorMaster;

/**
 * File:  ManageFolderDataConverter.java 
 *
 * Created on May 10, 2013 4:34:09 PM
 */

/*
 * Class: ManageFolderDataConverter
 * 
 * Purpose: This is the helper class for ManageFolderController that parses
 * the HttpServletRequest object and returns required Domain objects for
 * the controller
 */

public class ManageFolderDataConverter
{

	/*Constructor*/
	
	public ManageFolderDataConverter( )
	{
		
	}
	
	/*To parse CompanyRegnKey from the request*/
	
	public int getRegnKey( HttpServletRequest request, CompanyRegnKey regnKey )
	{
		if( request.getParameter( "RegnKey" ) != null )
		{
			regnKey.companyPhoneNo_ = request.getParameter( "RegnKey" );
			return 0;
		}
		else 
		{
			return -1;
		}
		
	}
	
	/*To parse GroupKey from the request*/
	
	public int getGroupKey( HttpServletRequest request, GroupKey key )
	{
                ErrorMaster errorMaster_ = null;
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		if( request.getParameter( "GroupKey" ) != null )
		{
			String[] keyArr = request.getParameter( "GroupKey" ).split( ":" );
			
			errorMaster_.insert( request.getParameter( "GroupKey" ) );
			
			key.companyRegnKey_ = new CompanyRegnKey( );
			key.companyRegnKey_.companyPhoneNo_ = keyArr[0];
			key.groupName_	= keyArr[1];
			
			return 0;
		}
		else 
		{
			return -1;
		}
	}
	
	
	/*To parse UserProfileKey from the request*/
	
	public int getUserKey( HttpServletRequest request, UserProfileKey userKey )
	{
		if( request.getParameter( "UserKey" ) != null )
		{
			userKey.email_ = request.getParameter( "UserKey" );
			
			return 0;
		}
		else 
		{
			return -1;
		}
	}
	
	
	/*To parse FolderKey from the request*/
	
	public int getFolderKey( HttpServletRequest request, DeptFolderKey folderKey )
	{
		if( request.getParameter( "FolderKey" ) != null )
		{
			String[] keyArr = request.getParameter( "FolderKey" ).split( ":" );
			
			folderKey.companyRegnKey_	= new CompanyRegnKey( );
			folderKey.companyRegnKey_.companyPhoneNo_	= keyArr[0];
			folderKey.folderName_	= keyArr[1];
			
			return 0;
		}
		else
		{
			return -1;
		}
	}
	
	/*To parse GroupFolderData from the request*/
	
	public int getGroupFolderData( HttpServletRequest request, GroupFolderData groupFolderData )
	{
		try
        {
			String regnKeyStr = request.getParameter( "RegnKey" );
			
			String[] folderStrArr = request.getParameter( "FolderKey" ).split( ":" );
			
			String folderName = folderStrArr[1];
			
			String[] groupStrArr = request.getParameter( "GroupKey" ).split( ":" );
			
			String groupName	= groupStrArr[1];
			
			groupFolderData.folderKey_.companyRegnKey_ = new CompanyRegnKey( );
			groupFolderData.folderKey_.companyRegnKey_.companyPhoneNo_ = regnKeyStr;
						
			groupFolderData.folderKey_.folderName_	= folderName;
			
			groupFolderData.regnKey_.companyPhoneNo_ = regnKeyStr;
			
			groupFolderData.groupKey_.companyRegnKey_ = new CompanyRegnKey( );
			groupFolderData.groupKey_.companyRegnKey_.companyPhoneNo_ = regnKeyStr;
			
			groupFolderData.groupKey_.groupName_	= groupName;
			
			groupFolderData.deptKey_.companyRegnKey_ = new CompanyRegnKey( );
			groupFolderData.deptKey_.companyRegnKey_.companyPhoneNo_ = regnKeyStr;
			
			groupFolderData.deptKey_.deptName_	= request.getParameter( "DeptName" );
			
			if( request.getParameter( "RequestType" ).equals( "UpdateGroupPrivileges" ))
			{
				groupFolderData.readFlag_	= Integer.valueOf( request.getParameter( "ReadFlag" ) );
				groupFolderData.readWriteFlag_ = Integer.valueOf( request.getParameter( "ReadWriteFlag" ) );
			}
			
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger.instance( ).logMsg( "Exception::ManageFolderDataConverter.getGroupFolderData - " + e );
	        
        	return -1;
        }
				
	}
	
	/*To parse UserFolderData from the request*/
	
	public int getUserFolderData( HttpServletRequest request, UserFolderData userFolderData )
	{
		try
        {
			String regnKeyStr = request.getParameter( "RegnKey" );
			
			String[] folderStrArr = request.getParameter( "FolderKey" ).split( ":" );
			
			String folderName = folderStrArr[1];
			
			userFolderData.folderKey_.companyRegnKey_ = new CompanyRegnKey( );
			userFolderData.folderKey_.companyRegnKey_.companyPhoneNo_ = regnKeyStr;
			
			userFolderData.folderKey_.folderName_	= folderName;
			
			if( request.getParameter( "RequestType" ).equals( "UpdateUserPrivileges" ))
			{
				userFolderData.readFlag_	= Integer.valueOf( request.getParameter( "ReadFlag" ) );
				userFolderData.readWriteFlag_ = Integer.valueOf( request.getParameter( "ReadWriteFlag" ) );
			}
			userFolderData.regnKey_.companyPhoneNo_ = regnKeyStr;
			userFolderData.userKey_.email_ = request.getParameter( "UserKey" );
			
			userFolderData.deptKey_.companyRegnKey_ = new CompanyRegnKey( );
			userFolderData.deptKey_.companyRegnKey_.companyPhoneNo_ = regnKeyStr;
			
			userFolderData.deptKey_.deptName_	= request.getParameter( "DeptName" );
			
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger.instance( ).logMsg( "Exception::ManageFolderDataConverter.getUserFolderData - " + e );
	        
        	return -1;
        }
	}
}
