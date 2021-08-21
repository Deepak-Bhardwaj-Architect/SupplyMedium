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

import java.util.List;

import utils.ErrorLogger;
import utils.IntHolder;

import core.privilege.DeptPrivilegesData;
import core.regn.CompanyRegnKey;
import core.usermgmt.GroupKey;

import db.dept.DeptFolderMappingTable;
import db.dept.DeptTable;
import db.dept.UserDeptMappingTable;
import db.privileges.DeptPrivilegesTable;
import db.privileges.PrivilegesDefaultsTable;
import db.usermgmt.GroupsDeptMapTable;
import utils.ErrorMaster;

/**
 * File:  DeptManager.java 
 *
 * Created on Mar 6, 2013 4:28:27 PM
 */

/*
 * This class is used manage the department operation like new department creation,
 * Rename department, Remove the department from SM and getting all departments.
 */

public class DeptManager
{

    private static ErrorMaster errorMaster_ = null;


	/*
	 * Method : add( ) 
	 * 
	 * Input  : DeptData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to create the new department in SM. It return the int value 
	 * based on department creation result (Success/failed).
	 */
	public DeptManager()
        {
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
        }
        
	public int add( DeptData deptData )
	{
		int responseCode = 0;
		
		DeptTable deptTable = new DeptTable( );
		
		// Check department already exist
		int isExist = deptTable.isExist( deptData );
	
					
		if( isExist != 1 ) // Department not exist create new department
		{
			responseCode = deptTable.insert( deptData );
		}
		else 
		{
			
			return 1903; // Department already exist
		}
		
		deptTable = null;
		
		if( responseCode == 0 )
		{
			setDefaultPrivileges( deptData );
			
			return 1900;  // success
		}
		else
		{
			return 1902; // Failed 
		}
	}
	
	
	/*
	 * Method : update( ) 
	 * 
	 * Input  : DeptData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to update the department name in SM. It return the int value 
	 * based on department updated result (Success/failed).
	 */
	
	public int update( DeptData deptData )
	{
		int responseCode = 0;
		
		DeptTable deptTable = new DeptTable( );
		
		// Check department already exist
		int isExist = deptTable.isExist( deptData );

		if( isExist != 1 ) // Department not exist update department
		{
			responseCode = deptTable.update( deptData );
		}
		else
		{
			deptTable = null;
			return 1903; // Department already exist
		}
		
		if( responseCode == 0 )
		{
			//To update key
			DeptKey oldKey = new DeptKey( );
			DeptKey updatedKey = new DeptKey( );
			
			CompanyRegnKey key = deptData.deptKey_.companyRegnKey_;
			
			oldKey.deptName_ = deptData.deptKey_.deptName_; 
			oldKey.companyRegnKey_ = key;
			
			updatedKey.deptName_ = deptData.deptName_;
			updatedKey.companyRegnKey_ = key;
			
			//To update dept key in departments table
			deptTable.updateKey( updatedKey, oldKey );
			
			//To update dept key in dept_folder_mapping table
			DeptFolderMappingTable mapTbl = new DeptFolderMappingTable( );
			mapTbl.updateKey( updatedKey, oldKey );
			
			//To update dept key in dept_privileges_mapping table
			DeptPrivilegesTable priTbl = new DeptPrivilegesTable( );
			priTbl.updateKey( updatedKey, oldKey );
			
			//To update dept key in group_dept_mapping table
			GroupsDeptMapTable groupMapTbl = new GroupsDeptMapTable( );
			groupMapTbl.updateKey( updatedKey, oldKey );
			
			//To update dept key in user_dept_mapping table
			UserDeptMappingTable deptUserMapTbl = new UserDeptMappingTable( );
			deptUserMapTbl.updateKey( updatedKey, oldKey );
			
			oldKey = null;
			updatedKey = null;
			mapTbl = null;
			priTbl = null;
			groupMapTbl = null;
			deptUserMapTbl = null;
			deptTable = null;
			return 1910;  // success
		}
		else
		{
			deptTable = null;
			return 1911; // Failed 
		}
	}
	

	/*
	 * Method : remove( ) 
	 * 
	 * Input  : DeptData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to remove the department from SM. It return the int value 
	 * based on department delete result (Success/failed).
	 * And also it remove the all the department data from other tables.
	 */
	
	public int remove( DeptData deptData )
	{
		int responseCode = 0;
		
		DeptTable deptTable = new DeptTable( );
		
		responseCode = deptTable.delete( deptData.deptKey_ );
		
		deptTable = null;
		
		if( responseCode == 0 )
		{
			int result = 0;
			
			// Removing Department privileges 
			
			DeptPrivilegesTable deptPrivilegesTable = new DeptPrivilegesTable( );
			
			result = deptPrivilegesTable.delete( deptData.deptKey_ );
			
			deptPrivilegesTable = null;
			
			
			// Removing Department and folders relationship
			DeptFolderMappingTable deptFolMapTbl = new DeptFolderMappingTable( );
			
			result = deptFolMapTbl.delete( deptData.deptKey_ );
			
			deptFolMapTbl = null;
			

			// Removing Department and users relationship
			UserDeptMappingTable userDeptMapTbl = new UserDeptMappingTable( );
			
			result = userDeptMapTbl.delete( deptData.deptKey_ );
			
			userDeptMapTbl = null;

			
			// Removing Department and Groups relationship
			GroupsDeptMapTable groupDeptMapTbl = new GroupsDeptMapTable( );

			result = groupDeptMapTbl.delete( deptData.deptKey_ );

			groupDeptMapTbl = null;

			
			return 1920;  // success
		}
		else
		{
			return 1921; // Failed 
		}
	}
	
	
	/*
	 * Method : getAllDepts( ) 
	 * 
	 * Input  : CompanyRegnKey object,list of DeptData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to get the all departments which are available in SM for 
	 * given company.Assign this to deptDataArr parameter so it will be copied 
	 * to the caller method.
	 * 
	 */
	
	public int getAllDepts( CompanyRegnKey regnKey, List<DeptData> deptDataArr )
	{
		int responseCode = 0;
		
		DeptTable deptTable = new DeptTable( );
		
		responseCode = deptTable.getAllDepartments( regnKey, deptDataArr );
		
		deptTable = null;
		
		if( responseCode == 0 )
		{	
			return 1930;  // success
		}
		else
		{
			return 1931; // Failed 
		}
	}
	

	/*
	 * Method : setDefaultPrivileges( ) 
	 * 
	 * Input  : DeptData deptData
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to get the department default privileges from 
	 * privileges_default table and set the privileges to given department
	 * in dept_privileges table.
	 * 
	 */
	
	private int setDefaultPrivileges( DeptData deptData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		PrivilegesDefaultsTable defaults = new PrivilegesDefaultsTable( );
		
		IntHolder privilegesValue = new IntHolder( );
		
		int defaultResult = defaults.getDeptDefaults( privilegesValue );
		
		errorMaster_.insert( "default privileges="+privilegesValue.value );
		
		if(defaultResult == 0)
		{
			errLogger.logMsg( "info::DeptManager:setDefaultPrivileges-" +
					"PrivilegesDefaultsTable.getDeptDefaults-Success" );
		}
		else 
		{
			errLogger.logMsg( "info::DeptManager:setDefaultPrivileges-" +
					"PrivilegesDefaultsTable.getDeptDefaults-Failed" );
			
			return -1;
		}
		
		defaults = null;
		
		DeptPrivilegesTable groupPriTbl = new DeptPrivilegesTable( );
		
		DeptPrivilegesData deptPriData = new DeptPrivilegesData( );
		
		deptPriData.privileges_ = privilegesValue.value;
		
		deptPriData.deptData_	= deptData;
		
		int priVal = groupPriTbl.insert( deptPriData );
		
		if( priVal != 0 )
		{
			errLogger.logMsg( "Error::DeptManager:setDefaultPrivileges-PrivilegesTable.insert-Failed" );
			groupPriTbl = null;
			return -1;
		}
		
		groupPriTbl = null;
		return 0;
	}
}
