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

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import core.managefolder.UserFolderData;
import core.privilege.DeptPrivilegesData;
import core.privilege.PrivilegesComposer;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;

import db.dept.DeptFilesTable;
import db.dept.DeptFolderMappingTable;
import db.dept.DeptFolderTable;
import db.dept.UserDeptMappingTable;
import db.managefolder.UserFolderAccessTable;
import db.regn.CompanyRegnTable;
import db.regn.UserProfileTable;

import utils.DiskSpaceChecker;
import utils.ErrorLogger;
import utils.FileStore;
import utils.PathBuilder;
import utils.StringHolder;
import utils.UserType;

/**
 * File:  DeptPage.java 
 *
 * Created on May 8, 2013 11:22:18 AM
 */

/*
 * Class: DeptPage
 * 
 * Purpose: To perform department page operations like, add new file, remove file, restore
 * file, delete file and list files and folders
 */

public class DeptPage
{

	ErrorLogger errLogger_;
	
	
	/*Constructor*/
	public DeptPage( )
	{
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method: add
	 * 
	 * Input: FileData object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method adds new file to the folder for a department through
	 * the dept key and dept folder key which available in the FilesData
	 */
	
	public int add( FileData fileData )
	{
		int result = 0;
		
		Object fileObject  = fileData.file_;
		
		if( fileObject != null )
		{
			FileItem file = (FileItem)fileObject;
			
			double fileSize = file.getSize( );
			
			fileSize = fileSize / (1024 * 1024);
			
			DiskSpaceChecker diskSpaceChecker = new DiskSpaceChecker( fileData.attrData_.regnKey_ );
			
			ErrorLogger.instance( ).logMsg( "SpaceRemaining - " + diskSpaceChecker.spaceRemaining_ + ";" + "ImageSize - " + fileSize );
			
			if( diskSpaceChecker.spaceRemaining_ < fileSize && diskSpaceChecker.spaceRemaining_ != -1 )
			{
				//This will not allow to post image as image size exceeds the disk quota for that company
				
				return 1703; //Disk quota for the company exceeds the limit
			}
		}
		
		DeptFilesTable filesTable = new DeptFilesTable( );
		
		result = filesTable.insert( fileData );
		
		if( result == 0 )
		{
			result = storeDeptDoc( fileData );
			
			if( result == 0)
			{
				DeptFilesTable deptFilesTbl = new DeptFilesTable( );
				
				deptFilesTbl.update( fileData );
			}
		}
		else 
		{
			filesTable = null;
			
			return 1702;  //Failed to insert the stored file attributes to DB
		}
		
		filesTable = null;
		
		return 1700; //Successfully saved the file into location and inserts the file attr to DB
	}
	
	/*
	 * Method: remove
	 * 
	 * Input: FileData object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method removes a file from a folder and move it to 
	 * recycle bin 
	 */
	
	public int remove( FileData fileData )
	{
		DeptFilesTable filesTable = new DeptFilesTable( );
		
		fileData.attrData_.recycleFlag_ = 1;
		
		int result = filesTable.update( fileData );
		
		if( result != 0 )
		{
			filesTable = null;
			
			return 1706; //Failed to move to recycle bin
		}
	
		filesTable = null;
		
		return 1705; //Successfully moved to recycle bin
	}
	
	/*
	 * Method: restore
	 * 
	 * Input: FileData object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method restores a file from recycle bin to its original
	 * folder
	 */
	
	public int restore( FileData fileData )
	{
		
		DeptFilesTable filesTable = new DeptFilesTable( );
		
		int result = filesTable.update( fileData );
		
		if( result != 0 )
		{
			filesTable = null;
			
			return 1711; //Failed to restore file from recycle bin
		}
		
		filesTable = null;
		
		return 1710;  //Successfully restored file from recycle bin
	}
	
	/*
	 * Method: get
	 * 
	 * Input: DeptKey, List<DeptFolderData> deptFolderDataList (As Ref)
	 * 
	 * return: int
	 * 
	 * Purpose: This method fetches all the folders for a department and 
	 * other departments folders in which user is privileged through Manage Folder
	 */

	public int get ( DeptKey deptKey, UserProfileKey userKey, List<DeptFolderData> folderDataList )
	{
		UserProfileTable profileTable = new UserProfileTable( );
		
		UserProfileData profileData = new UserProfileData( );
		
		int result = profileTable.getUserProfile( userKey, profileData );
		
		if( result != 0 )
		{
			ErrorLogger.instance( ).logMsg( "Error::DeptPage.get( ) - Unable to fetch user type" );
			
			return 1714;
		}
		
		if( profileData.userType_.equals( UserType.type.ADMIN.getValue( ) ))
		{
			return getAdminFolders( deptKey, userKey, folderDataList );
		}
		
		UserDeptMappingTable mapTable = new UserDeptMappingTable( );
		
		UserDeptMappingData mapData = new UserDeptMappingData( );
		
		mapData.deptKey_ = new DeptKey( );
		
		mapData.deptKey_ = deptKey;
		
		mapData.userKey_ = new UserProfileKey( );
		
		mapData.userKey_ = userKey;
		
		result = mapTable.isExist( mapData );
		
		mapTable = null;
		
		if( result < 0 )
		{
			ErrorLogger.instance( ).logMsg( "Error::DeptPage.get( ) - Unable to check with UserDeptMappingTable" );
			
			return 1714;
		}
		
		if( result == 1 )
		{
			return getUserDeptFolders( deptKey, userKey, folderDataList );
		}
		else 
		{
			return getMFFolders( deptKey, userKey, folderDataList );
		}		
	}
	
	/*
	 * Method: getAdminFolders
	 * 
	 * Input: DeptKey, List<DeptFolderData> deptFolderDataList (As Ref)
	 * 
	 * return: int
	 * 
	 * Purpose: This method fetches all the folders for the company admin user
	 * 
	 */
	
	private int getAdminFolders( DeptKey deptKey, UserProfileKey userKey, List<DeptFolderData> folderDataList )
	{
		DeptFolderMappingTable mapTable = new DeptFolderMappingTable( );
		
		List<DeptFolderMappingData> mapDataArr = new ArrayList<DeptFolderMappingData>( );
		
		int result = mapTable.getFolders( deptKey, mapDataArr );
		
		mapTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::DeptPage.getAdminFolders( DeptKey, userKey, folderDataList ) - " +
							   "Failed to fetch folders from DeptFolderMappingTable" );
		
			mapDataArr = null;
			
			return 1716; //Failed to fetch folders from DeptFolderMappingTable
		}
		
		if( mapDataArr.size( ) <= 0 )
		{
			errLogger_.logMsg( "Error::DeptPage.getAdminFolders( DeptKey, userKey, folderDataList ) - " +
					   "No record is available to fetch" );
			
			mapDataArr = null;
					
			return 1717; //No folders exists for the department
		}
		
		for( DeptFolderMappingData deptFolderMappingData : mapDataArr )
        {	
			DeptFolderData folderData = new DeptFolderData( );
			
			folderData.isFromMF_ = 0;
			
			DeptFolderTable folderTable = new DeptFolderTable( );
			
			result = folderTable.getDeptFolder( deptFolderMappingData.deptFolderKey_, folderData );
			
			folderTable = null;
			
			if( result == 0)
			{
				//To apply dept privileges to the department folders
				
				folderData.addFileFlag_ = 1;
				
				folderData.deleteFileFlag_	= 1;
				
				DeptFilesTable filesTable = new DeptFilesTable( );
				
				List<FileData> fileDataArr = new ArrayList<FileData>( );
				
				result = filesTable.find( folderData.deptFolderKey_, fileDataArr );
				
				filesTable = null;
				
				if( result == 0)
				{
    				folderData.filesArr_ = fileDataArr;
    				
    				folderData.recycleFlag_ = deptFolderMappingData.recyleFlag_;
    				
    				folderDataList.add( folderData );
    				
    				fileDataArr = null;
    				
    				folderData = null;
    				
				}
				else 
				{
					errLogger_.logMsg( "Error::DeptPage.getAdminFolders( DeptKey, userKey, folderDataList ) - " +
							   "Failed to fetch files list for given folder key" );
					
					fileDataArr = null;
    				
    				folderData = null;
					
    				return 1719; //Failed to fetch files list for given folder key
				}
			}
			else 
			{
				errLogger_.logMsg( "Error::DeptPage.getAdminFolders( DeptKey, userKey, folderDataList ) - " +
						   "Failed to fetch folder data fpr given folder key" );
				
				folderData = null;
				
				folderTable = null;
				
				mapDataArr = null;
				
				return 1718; // failed to fetch folder data for folder key
			}
        }
		return 1715; //Successfully listed all folders and files for each folder for given dept key
	}
	
	/*
	 * Method: getUserDeptFolders
	 * 
	 * Input: DeptKey, List<DeptFolderData> deptFolderDataList (As Ref)
	 * 
	 * return: int
	 * 
	 * Purpose: This method fetches all the folders for a department and 
	 * all the files for each folder, each folders privileges and fills the deptFolderDataList.
	 * 
	 */
	private int getUserDeptFolders( DeptKey deptKey, UserProfileKey userKey, List<DeptFolderData> folderDataList )
	{	
		DeptFolderMappingTable mapTable = new DeptFolderMappingTable( );
		
		List<DeptFolderMappingData> mapDataArr = new ArrayList<DeptFolderMappingData>( );
		
		int result = mapTable.getFolders( deptKey, mapDataArr );
		
		mapTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::DeptPage.getUserDeptFolders( DeptKey, userKey, folderDataList ) - " +
							   "Failed to fetch folders from DeptFolderMappingTable" );
		
			mapDataArr = null;
			
			return 1716; //Failed to fetch folders from DeptFolderMappingTable
		}
		
		if( mapDataArr.size( ) <= 0 )
		{
			errLogger_.logMsg( "Error::DeptPage.getUserDeptFolders( DeptKey, userKey, folderDataList ) - " +
					   "No record is available to fetch" );
			
			mapDataArr = null;
					
			return 1717; //No folders exists for the department
		}
		
		DeptPrivilegesData priData = new DeptPrivilegesData( );
		
		PrivilegesComposer composer = new PrivilegesComposer( );
		
		result = composer.getDeptPrivileges( deptKey, priData );
		
		composer = null;
		
		//if( result != 0 )
		//{
		//	errLogger_.logMsg( "Error::DeptPage.getUserDeptFolders( DeptKey, userKey, folderDataList ) - " +
		//			   "Failed to fetch dept privileges" );

		//	mapDataArr = null; priData = null;
	
		//	return 1713; //Failed to fetch folders from DeptFolderMappingTable
		//}
		
		for( DeptFolderMappingData deptFolderMappingData : mapDataArr )
        {	
			DeptFolderData folderData = new DeptFolderData( );
			
			folderData.isFromMF_ = 0;
			
			DeptFolderTable folderTable = new DeptFolderTable( );
			
			result = folderTable.getDeptFolder( deptFolderMappingData.deptFolderKey_, folderData );
			
			folderTable = null;
			
			if( result == 0)
			{
				//To apply dept privileges to the department folders
				
				folderData.addFileFlag_ = priData.addFile_ ? 1:0;
				
				folderData.deleteFileFlag_	= priData.deleteFile_ ? 1:0;
				
				DeptFilesTable filesTable = new DeptFilesTable( );
				
				List<FileData> fileDataArr = new ArrayList<FileData>( );
				
				result = filesTable.find( folderData.deptFolderKey_, fileDataArr );
				
				filesTable = null;
				
				if( result == 0)
				{
    				folderData.filesArr_ = fileDataArr;
    				
    				folderData.recycleFlag_ = deptFolderMappingData.recyleFlag_;
    				
    				folderDataList.add( folderData );
    				
    				fileDataArr = null;
    				
    				folderData = null;
    				
				}
				else 
				{
					errLogger_.logMsg( "Error::DeptPage.getUserDeptFolders( DeptKey, userKey, folderDataList ) - " +
							   "Failed to fetch files list for given folder key" );
					
					fileDataArr = null;
    				
    				folderData = null;
					
    				return 1719; //Failed to fetch files list for given folder key
				}
			}
			else 
			{
				errLogger_.logMsg( "Error::DeptPage.getUserDeptFolders( DeptKey, userKey, folderDataList ) - " +
						   "Failed to fetch folder data fpr given folder key" );
				
				folderData = null;
				
				folderTable = null;
				
				mapDataArr = null;
				
				return 1718; // failed to fetch folder data for folder key
			}
        }
		return 1715; //Successfully listed all folders and files for each folder for given dept key
	}
	
	/*Helper method for get*/
	private int getMFFolders(  DeptKey deptKey, UserProfileKey userKey, List<DeptFolderData> folderDataList  )
	{
		UserFolderAccessTable accessTable = new UserFolderAccessTable( );
		
		List<UserFolderData> userFolderDataList = new ArrayList<UserFolderData>( );
		
		int result = accessTable.find( userKey, deptKey, userFolderDataList );
		
		if( result != 0 )
		{
			ErrorLogger.instance( ).logMsg( "Error::DeptPage.getMFFolders - Failed to fetch MF privileged folders from User Folder access table." );
			
			return 1712;
		}
		
		for( UserFolderData userFolderData : userFolderDataList )
        {
			DeptFolderData folderData = new DeptFolderData( );

			folderData.isFromMF_ = 1;
			
			//To apply dept privileges to the department folders
			
			if( userFolderData.readFlag_  == 0 && userFolderData.readWriteFlag_ == 0 )
				continue;
			
			if( userFolderData.readWriteFlag_ == 1 )
			{
				folderData.addFileFlag_ = 1;
				folderData.deleteFileFlag_ = 1;
			}
			else 
			{
				folderData.addFileFlag_ = 0;
				folderData.deleteFileFlag_ = 0;
			}
				
			DeptFilesTable filesTable = new DeptFilesTable( );
			
			List<FileData> fileDataArr = new ArrayList<FileData>( );
			
			result = filesTable.find( deptKey, fileDataArr, 0 );
			
			filesTable = null;
			
			if( result == 0)
			{
				folderData.filesArr_ = fileDataArr;
				
				folderData.recycleFlag_ = 0;
				
				folderDataList.add( folderData );
				
				fileDataArr = null;
				
				folderData = null;
				
			}
			else 
			{
				errLogger_.logMsg( "Error::DeptPage.get( DeptKey, userKey, folderDataList ) - " +
						   "Failed to fetch files list for given folder key" );
				
				fileDataArr = null;
				
				folderData = null;
				
				return 1719; //Failed to fetch files list for given folder key
			}

        }
		return 1715; //Successfully listed all folders and files for each folder for given dept key
	}
	
	
	/*
	 * Method: get
	 * 
	 * Input: FileData object
	 * 
	 * return: int
	 * 
	 * Purpose: This method fetches the filedata value for given fileid.
	 * 
	 */
	
	public int get( FileData fileData )
	{	
		DeptFilesTable deptFilesTable = new DeptFilesTable( );
		
		int result = deptFilesTable.find( fileData );
		
		deptFilesTable = null;
		
		if( result == 0)
		{
			return 1730;
		}
		else if( result == -1 )
		{
			 return 1731;
		}
		else
		{
			return 1732;
		}
	}
	
	
	/*
	 * Method: emptyRecycleBin
	 * 
	 * Input: DeptKey obj, FileData obje
	 * 
	 * Return: int
	 * 
	 * Purpose: This method permenantly deletes the files that are present
	 * in recycle bin
	 */
	
	public int remove( DeptKey deptKey )
	{
		DeptFolderMappingTable mappingTable = new DeptFolderMappingTable( );
		
		List<DeptFolderMappingData> mapDataArr = new ArrayList<DeptFolderMappingData>( );
		
		int result = mappingTable.find( deptKey, mapDataArr );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::DeptPage.remove( DeptKey, FileData ) - " +
					   "Failed to fetch deleted folder records from DeptFolderMappingTable" );
			
			mappingTable = null; mapDataArr = null;
			
			return 1724; //Failed to fetch deleted folder records from DeptFolderMappingTable
		}
		
		//To remove the relations of the folder from the mapper table
		
		for( DeptFolderMappingData deptFolderMappingData : mapDataArr )
        {
			result = mappingTable.delete( deptFolderMappingData );
			
			if( result  != 0 )
			{
				errLogger_.logMsg( "Error::DeptPage.remove( DeptKey, FileData ) - " +
						   "Failed to remove dept-folder relation from DeptFolderMappingTable" );
			}
        }
		
		mappingTable = null; mapDataArr = null;
		
		
		/*To remove the file record from DB. If success, then the file will be removed
		 * from actual local path*/
		
		FileStore fileStore = new FileStore( );
		
		DeptFilesTable filesTable = new DeptFilesTable( );
		
		List<FileData> fileDataArr = new ArrayList<FileData>( );
		
		result = filesTable.find( deptKey, fileDataArr );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::DeptPage.remove( DeptKey, FileData ) - " +
					   "Failed to fetch deleted file records from DeptFilesTable" );
			
			fileStore = null; filesTable = null; fileDataArr = null;
			
			return 1723; //Failed to remove file from the folder directory
		}

		for( FileData fileData : fileDataArr )
        {
	        result = filesTable.delete( fileData.attrData_.deptFileId_ );
	        
	        if( result != 0 )
	        {
	        	errLogger_.logMsg( "Error::DeptPage.remove( DeptKey, FileData ) - " +
						   "Failed to remove " + fileData.attrData_.fileName_ + " from DeptFilesTable" );
				
				continue; //Failed to remove file from the folder directory
	        }
	        
	        result = fileStore.deleteFile( fileData.attrData_.localPath_, fileData.attrData_.fileName_ );
	        
	        if( result != 0)
	        {
	        	errLogger_.logMsg( "Error::DeptPage.remove( DeptKey, FileData ) - " +
						   "Failed to remove " + fileData.attrData_.fileName_ + " from directory" );
				
	        }   	
        }
		
		fileStore = null; filesTable = null; fileDataArr = null;
		
		return 1720; //Successfully emptied recycle bin
	}
	
	/*
	 * Method: remove
	 * 
	 * Input: DeptKey object, Timestamp criticalTimestamp
	 * 
	 * Return: int
	 * 
	 * Purpose: This method will be called from the timer, to delete
	 * the files from recycle bin which is older than 30 days
	 */
	
	public int remove( DeptKey deptKey, Timestamp currentTs )
	{
		
		return 0;
	}
	
	
	/*
	 * Method: storeDeptDoc
	 * 
	 * Input: filedata object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to store the uploaded file to server. If it
	 * return 0 for successfully upload or otherwise
	 */
	public int storeDeptDoc( FileData fileData)
	{
		try
        {
			CompanyRegnTable regnTable = new CompanyRegnTable( );
			
			String companyname = regnTable.find( fileData.attrData_.regnKey_ );
			
			regnTable = null;
			
			String phoneNumber = fileData.attrData_.regnKey_.companyPhoneNo_;
			
			StringHolder dirPath = new StringHolder( );
			
			PathBuilder pathBuilder = new PathBuilder( );
			
			// Getting the user feed image directory path
			int dirPathRes = pathBuilder.getDeptFilesDirPath( companyname, phoneNumber, dirPath );
			
			if( dirPathRes == 0 ) // User feed directory path fetched successfully
			{
				FileStore fileStoreObj = new FileStore( );
				
				StringHolder storedPath = new StringHolder( );
				
				System.out.println( "file object="+fileData.file_ );
				
				// Used to store feed image 
				int storeResult = fileStoreObj.storeFile( fileData.file_, dirPath.value, Long.toString( fileData.attrData_.deptFileId_ ),storedPath );
				
				if( storeResult == 0 )  // user feed image stored in folder successfully
				{
					fileData.attrData_.localPath_ = storedPath.value;
					
					fileData.attrData_.localPath_ = fileData.attrData_.localPath_.replace( "\\", "/" );
					
					StringHolder relativePath = new StringHolder( );
					
					StringHolder webURL = new StringHolder( );
					
					// Converting local path to relative path
					
					int result = pathBuilder.getRelativePath( fileData.attrData_.localPath_, relativePath );
					
					if( result == 0 )
					{
						fileData.attrData_.relativePath_ = relativePath.value;
					}
					
					
					// Converting local path to web url
					
					result = pathBuilder.getWebURL( fileData.attrData_.localPath_, webURL );
					
					if( result == 0 )
					{
						fileData.attrData_.webUrl_ = webURL.value;
					}
					
					FileItem file = (FileItem)fileData.file_;
					
					double sizeInBytes = file.getSize( );
					
					double sizeInKB =  sizeInBytes/1024;
					
					fileData.attrData_.fileSize_ = sizeInKB;
					
					String filename = new File( file.getName( ) ).getName( );
					
					filename = filename.replace( ".", ":" );
					
					String[] filenameArr = filename.split( ":" );
					
						
					if( filenameArr.length>1 )
					{
						fileData.attrData_.fileName_ = filenameArr[0];
						
						fileData.attrData_.fileType_ = filenameArr[1];
						
						System.out.println(fileData.attrData_.fileName_+fileData.attrData_.fileType_);
					}
					
				}
			}
        }
        catch( Exception ex )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::UserFeedController.storeFeedImage() - " + ex );
			
			return -1;
        }
		
		return 0;
	}
	
}
