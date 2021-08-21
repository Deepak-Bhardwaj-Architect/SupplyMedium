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

import core.dept.DeptFolderData;
import core.dept.DeptKey;
import core.dept.DeptPage;
import core.dept.FileData;
import core.regn.UserProfileKey;

/**
 * File: DeptPageController.java
 * 
 * Created on May 6, 2013 2:24:49 PM
 */

/*
 * Class: DeptPageController
 * 
 * Purpose: This class is used to process the following following Request Types
 * 
 * 1. GetFolders - To fetch list of folders for the given dept key 2. AddFiles -
 * To add the a files to the folder 3. RestoreFiles - To restore the files from
 * recycle bin 4. RemoveFiles - To delete files from the folder to recycle bin
 */

public class DeptPageController
{

	ErrorLogger errLogger_;

	/* Constructor */
	public DeptPageController()
	{
		errLogger_ = ErrorLogger.instance( );
	}

	/*
	 * Method: getAllFiles
	 * 
	 * Input: HttpServletRequest requestObject, List<DeptFolderData>
	 * deptFolderDataList_ (As ref)
	 * 
	 * Return: int
	 * 
	 * Purpose: This method gets all the files and folders for the department
	 */

	public int getAllFiles( HttpServletRequest request,
	        List<DeptFolderData> folderDataList )
	{
		DeptPage deptPage = new DeptPage( );

		DeptPageDataConverter converter = new DeptPageDataConverter( );

		DeptKey deptKey = new DeptKey( );

		int result = converter.getDeptkey( request, deptKey );

		if( result != 0 )
		{
			errLogger_
			        .logMsg( "Error::DeptPageController.getAllFiles() - Error occurred while parsing request" );

			return 1791; // Parse error
		}
		
		if( request.getParameter( "UserKey" ) == null )
		{
			errLogger_
	        .logMsg( "Error::DeptPageController.getAllFiles() - Error occurred while parsing request" );

			return 1791; // Parse error
		}

		UserProfileKey userKey = new UserProfileKey( );
		
		userKey.email_ = request.getParameter( "UserKey" );
		
		errLogger_
		        .logMsg( "Info::DeptPageController.getAllFiles() - Request in process" );

		result = deptPage.get( deptKey, userKey, folderDataList );

		return result;
	}

	/*For unit test*/
	
	public int fetch(  List<DeptFolderData> folderDataList, DeptKey deptKey, UserProfileKey userKey )
	{
		DeptPage deptPage = new DeptPage( );
		
		int result = deptPage.get( deptKey, userKey, folderDataList );
		
		return result;
	}
	
	/*
	 * Method: manageFiles
	 * 
	 * Input: HttpServletRequest requestObject
	 * 
	 * Return: int
	 * 
	 * Purpose: This method adds list of files to the folder
	 */

	public int manageFiles( HttpServletRequest request, FileData fileData )
	{
		DeptPage deptPage = new DeptPage( );

		DeptPageDataConverter converter = new DeptPageDataConverter( );

		int result = converter.getFilesData( request, fileData );

		if( result != 0 )
		{
			return 1791; // Parse error
		}

		String requestType = request.getParameter( "RequestType" );

		if( requestType.equals( "AddFiles" ) )
		{
			errLogger_.logMsg( "Info::DeptPageController.manageFiles() - AddFiles Request in process" );

			return deptPage.add( fileData );
		}

		else if( requestType.equals( "RestoreFiles" ) )
		{
			errLogger_
			        .logMsg( "Info::DeptPageController.manageFiles() - RestoreFiles Request in process" );
			return deptPage.restore( fileData );
		}

		else if( requestType.equals( "RemoveFiles" ) )
		{
			errLogger_
			        .logMsg( "Info::DeptPageController.manageFiles() - RemoveFiles Request in process" );
			return deptPage.remove( fileData );
		}
		else if( requestType.equals( "DownloadFile" ) )
		{
			errLogger_
	        .logMsg( "Info::DeptPageController.manageFiles() - DownloadFiles Request in process" );
			return deptPage.get( fileData );
		}
		 else if( requestType.equals( "EmptyRecycleBin" ))
		 {
    		 errLogger_.logMsg(
    		 "Info::DeptPageController.manageFiles() - EmptyRecycleBin Request in process" );
    		 
    		 DeptKey deptKey = new DeptKey( );
    		 
    		 converter.getDeptkey( request, deptKey );
    		 
    		 return deptPage.remove( deptKey );
		 }
		

		else
		{
			return 1791; // Parse error
		}

	}

}
