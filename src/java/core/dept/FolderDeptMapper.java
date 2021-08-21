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

import core.regn.UserProfileData;

import utils.ErrorLogger;

import db.dept.DeptFolderMappingTable;
import db.dept.DeptFolderTable;
import utils.ErrorMaster;



/**
 * File:  FolderDeptMapping.java 
 *
 * Created on Mar 6, 2013 5:29:39 PM
 */

/*
 * This class is used to manage the relationship between folders and department.
 */


public class FolderDeptMapper
{
	
    private static ErrorMaster errorMaster_ = null;

    public FolderDeptMapper()
    {
        if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
    }
	/*
	 * Method : add( ) 
	 * 
	 * Input  : DeptKey object, list of DeptFolderData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to create the relationship between the folders and department.
	 */
	
	public int add( DeptKey deptKey,List<DeptFolderData> deptFolderDataArr )
	{
		DeptFolderMappingTable deptFolderMappingTbl = new DeptFolderMappingTable( );
		
		int responseCode = 0;
		
		errorMaster_.insert( "Deptfolder arr count="+deptFolderDataArr.size( ) );
		
		for( DeptFolderData deptFolderData : deptFolderDataArr )
		{
			DeptFolderMappingData deptFolderMappingData = new DeptFolderMappingData( );
			
			deptFolderMappingData.deptKey_ = deptKey;
			
			deptFolderMappingData.deptFolderKey_ = deptFolderData.deptFolderKey_;
				
			// Check relation already exist
			int isExist = deptFolderMappingTbl.isExist( deptFolderMappingData );
			
			if( isExist != 1 ) // Relation not exist, insert relation
			{
				responseCode = deptFolderMappingTbl.insert( deptFolderMappingData );
				
			}
		}
		
		deptFolderMappingTbl = null;
		
		if( responseCode == 0 )
		{
			return 2200;  // Success
		}
		else 
		{
			return 2202; // Failed
		}
	}
	
	
	/*
	 * Method : remove( ) 
	 * 
	 * Input  : DeptKey object, list of DeptFolderData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to remove the relationship between the folders and department.
	 */
	
	
	public int remove( DeptKey deptKey,List<DeptFolderData> deptFolderDataArr )
	{
		DeptFolderMappingTable deptFolderMappingTbl = new DeptFolderMappingTable( );
		
		int responseCode = 0;
		
		errorMaster_.insert( "folder data count="+deptFolderDataArr.size( ) );
		
		for( DeptFolderData deptFolderData : deptFolderDataArr )
		{
			DeptFolderMappingData deptFolderMappingData = new DeptFolderMappingData( );
			
			deptFolderMappingData.deptKey_ = deptKey;
			
			deptFolderMappingData.deptFolderKey_ = deptFolderData.deptFolderKey_;
			
			responseCode = deptFolderMappingTbl.delete( deptFolderMappingData );
		}
		
		deptFolderMappingTbl = null;
		
		if( responseCode == 0 )
		{
			return 2210;  // Success
		}
		else 
		{
			return 2211; // Failed
		}
	}
	
	
	/*
	 * Method : find( ) 
	 * 
	 * Input  : DeptKey object, list of DeptFolderData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is fetch the all the folders, related to given department key.
	 * Assign this to deptFolderDataArr parameter so it will be copied 
	 * to the caller method.
	 */
	
	public int find( DeptKey deptKey,List<DeptFolderData> deptFolderDataArr )
	{
		int responseCode = 0;
		
		List<DeptFolderMappingData> deptFolderMappingDataArr = 
									new ArrayList<DeptFolderMappingData>( );
		
		DeptFolderMappingTable deptFolderMappingTbl = new DeptFolderMappingTable( );
		
		// Used to fetch all the folders key for given dept key
		responseCode = deptFolderMappingTbl.getFoldersKey( deptKey, deptFolderMappingDataArr );
		
		deptFolderMappingTbl = null;
		
		if( responseCode != 0 )  // failed
		{
			return 2221;
		}
		
		DeptFolderTable deptFolderTable = new DeptFolderTable( );
		
		for( DeptFolderMappingData deptFolderMappingData : deptFolderMappingDataArr )
        {
			DeptFolderData deptFolderData = new DeptFolderData( );
			
			// Fetch group data from group key
			responseCode = deptFolderTable.getDeptFolder( deptFolderMappingData.deptFolderKey_, 
					deptFolderData );
			if( responseCode == 0)  // Success
			{
				deptFolderDataArr.add( deptFolderData );
				
				deptFolderData = null;
			}
			else // Failed
			{
				deptFolderData = null;
				
				continue;
			}
        }
		
		deptFolderTable = null;
		
		return 2220;  // Success
	}
	
	/*
	 * Method : getFolders( ) 
	 * 
	 * Input  : DeptKey object, list of DeptFolderData objects
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is fetch the all the folders except the folders which are already added 
	 * in given department key.
	 * Assign this to deptFodlerData parameter so it will be copied to the caller method.
	 */
	
	public int getFolders( DeptKey deptKey,List<DeptFolderData> deptFodlerDataArr )
	{
		int responseCode = 0;
		
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		List<DeptFolderData> deptFolders = new ArrayList<DeptFolderData>( );
		List<DeptFolderData> allFolders  = new ArrayList<DeptFolderData>( );
		
		responseCode = find( deptKey, deptFolders );
		
		if( responseCode == 2221 )
		{
			return 2231;
		}
		
		DeptFolderTable  folderTable= new DeptFolderTable(  );
		
		responseCode = folderTable.getAllDeptFolders( deptKey.companyRegnKey_, allFolders );
		
		errorMaster_.insert( "allUsers count="+allFolders.size( )
				+"deptcount="+deptFolders.size( ));
		
		folderTable = null;
		
		if( responseCode !=0 )
		{
			deptFolders = null;
			
			allFolders  = null;
			
			return 2231;
		}
		
		String errMsg = "";
				
		try
        {
			// Minus the folders data from all folders to department folders
			/*for( DeptFolderData deptFolderData : allFolders )
	        {
		        if( !deptFolders.contains( deptFolderData ))
		        {
		        	deptFodlerData.add( deptFolderData );
		        	
		        	errorMaster_.insert( "if part" );
		        }
		        else 
		        {
		        	errorMaster_.insert( "else part" );
				}
	        }*/
			
			boolean isExist = false;
			
			for( DeptFolderData deptFolderData : allFolders )
	        {
				for( DeptFolderData deptFolderData2 : deptFolders)
				{
					 if( (deptFolderData.folderName_.equals( deptFolderData2.folderName_)))
					 {
						 isExist = true;
					 }
				}
				
				if( !isExist )
				{
					deptFodlerDataArr.add( deptFolderData );
				}
				
				isExist = false;
		       
	        }
        }
        catch( ClassCastException e )
        {
        	errMsg = "Exception::FolderDeptMapper:getUsers - "+e;
			
			errorLogger.logMsg( errMsg );
			
			deptFolders = null;
			
			allFolders  = null;
			
			return 2231;
			
        }
		catch( NullPointerException e )
        {
			errMsg = "Exception::FolderDeptMapper:getUsers - "+e;
			
			errorLogger.logMsg( errMsg );
			
			deptFolders = null;
			
			allFolders  = null;
			
			return 2231;
			
        }
		catch( Exception e )
        {
			errMsg = "Exception::FolderDeptMapper:getUsers - "+e;
			
			errorLogger.logMsg( errMsg );
			
			deptFolders = null;
			
			allFolders  = null;
			
			return 2231;
			
        }
		
		deptFolders = null;
		
		allFolders  = null;
		
		return 2230;
	}
	
}
