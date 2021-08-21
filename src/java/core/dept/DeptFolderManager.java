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

import db.dept.DeptFolderMappingTable;
import db.dept.DeptFolderTable;


/**
 * File:  DeptFolderManager.java 
 *
 * Created on Mar 6, 2013 4:43:55 PM
 */

/*
 * This class is used manage the department folder operation like new folder creation,
 * Rename folder, Remove the folder from SM.
 */

public class DeptFolderManager
{
	/*
	 * Method : add( ) 
	 * 
	 * Input  : DeptFolderData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to create the new folder in SM. It return the int value 
	 * based on folder creation result (Success/failed).
	 */
	
	public int add( DeptFolderData deptFolderData )
	{
		int responseCode = 0;
		
		DeptFolderTable deptFolderTable = new DeptFolderTable( );
		
		// Check department already exist
		int isExist = deptFolderTable.isExist( deptFolderData );
			
							
		if( isExist != 1 ) // Department not exist create new department
		{
		
			responseCode = deptFolderTable.insert( deptFolderData );
		}
		else
		{
			return 2003;
		}
		
	
		deptFolderTable = null;
		
		if( responseCode == 0 )
		{
			return 2000;  // success
		}
		else
		{
			return 2002; // Failed 
		}
	}
	
	/*
	 * Method : add( ) 
	 * 
	 * Input  : DeptFolderData object, DeptKey key
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to create the new folder in SM and the created
	 * folder will be assigned to the department
	 */
	
	public int add( DeptFolderData deptFolderData, DeptKey deptKey )
	{
		int responseCode = 0;
		
		DeptFolderTable deptFolderTable = new DeptFolderTable( );
		
		// Check department already exist
		int isExist = deptFolderTable.isExist( deptFolderData );
							
		if( isExist != 1 ) // Department not exist create new department
		{
			responseCode = deptFolderTable.insert( deptFolderData );
		}
		else
		{
			return 2003;
		}
		
		deptFolderTable = null;
		
		if( responseCode == 0 )
		{
			DeptFolderMappingTable deptFolMapTbl = new DeptFolderMappingTable( );

			DeptFolderMappingData mapData = new DeptFolderMappingData( );
			
			mapData.deptFolderKey_ = deptFolderData.deptFolderKey_;
			
			mapData.deptKey_	= deptKey;
			
			responseCode = deptFolMapTbl.insert( mapData );

			mapData = null;
			
			deptFolMapTbl = null;
			
			return 2000;  // success
		}
		else
		{
			return 2002; // Failed 
		}
	}
	
	/*
	 * Method : update( ) 
	 * 
	 * Input  : DeptFolderData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to update the folder name in SM. It return the int value 
	 * based on folder updated result (Success/failed).
	 */
	
	public int update( DeptFolderData deptFolderData )
	{
		int responseCode = 0;
		
		DeptFolderTable deptFolderTable = new DeptFolderTable( );
		
		responseCode = deptFolderTable.update( deptFolderData );
		
		deptFolderTable = null;
		
		if( responseCode == 0 )
		{
			return 2010;  // success
		}
		else
		{
			return 2011; // Failed 
		}
	}
	

	/*
	 * Method : remove( ) 
	 * 
	 * Input  : DeptFolderData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to remove the folder from SM. It return the int value 
	 * based on folder delete result (Success/failed).
	 */
	
	public int remove( DeptFolderData deptFolderData )
	{
		int responseCode = 0;
		
		DeptFolderTable deptFolderTable = new DeptFolderTable( );
		
		responseCode = deptFolderTable.delete( deptFolderData.deptFolderKey_ );
		
		deptFolderTable = null;
		
		if( responseCode == 0 )
		{
			
			// Removing Department and folder relationship
			DeptFolderMappingTable deptFolMapTbl = new DeptFolderMappingTable( );

			responseCode = deptFolMapTbl.delete( deptFolderData.deptFolderKey_ );

			deptFolMapTbl = null;
			
			return 2020;  // success
		}
		else
		{
			return 2021; // Failed 
		}
	}
	
	/*
	 * Method : remove( ) 
	 * 
	 * Input  : DeptFolderData object, DeptKey deptKey
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to unassign the folder from given department
	 */
	
	public int remove( DeptFolderData deptFolderData, DeptKey deptKey )
	{
		int responseCode = 0;
		
		// Removing Department and folder relationship
		DeptFolderMappingTable deptFolMapTbl = new DeptFolderMappingTable( );

		responseCode = deptFolMapTbl.delete( deptKey, deptFolderData.deptFolderKey_ );

		deptFolMapTbl = null;
	
		if( responseCode == 0)
		{
			return 2020;  // success
		}
    	else
    	{
    		return 2021; // Failed 
    	}
	}
	
	/*
	 * Method : moveToRecyle( ) 
	 * 
	 * Input  : DeptFolderData object, DeptKey deptKey
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to move the folder to recycle bin
	 */
	
	public int moveToRecyle( DeptFolderData deptFolderData, DeptKey deptKey )
	{
		int responseCode = 0;
		
		// Removing Department and folder relationship
		DeptFolderMappingTable deptFolMapTbl = new DeptFolderMappingTable( );

		DeptFolderMappingData mapData = new DeptFolderMappingData( );
		
		mapData.deptFolderKey_ = deptFolderData.deptFolderKey_;
		
		mapData.deptKey_	= deptKey;
		
		mapData.recyleFlag_	= 1;
		
		deptKey.show( );
		
		deptFolderData.show( );
		
		
		
		responseCode = deptFolMapTbl.update( mapData );

		deptFolMapTbl = null;
	
		if( responseCode == 0)
		{
			return 2020;  // success
		}
    	else
    	{
    		return 2021; // Failed 
    	}
	}
	
	/*
	 * Method : restore( ) 
	 * 
	 * Input  : DeptFolderData object, DeptKey deptKey
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to restore the folder from recyle bin
	 */
	
	public int restore( DeptFolderData deptFolderData, DeptKey deptKey )
	{
		int responseCode = 0;
		
		deptKey.show( );
		
		// Removing Department and folder relationship
		DeptFolderMappingTable deptFolMapTbl = new DeptFolderMappingTable( );

		DeptFolderMappingData mapData = new DeptFolderMappingData( );
		
		mapData.deptFolderKey_ = deptFolderData.deptFolderKey_;
		
		mapData.deptKey_	= deptKey;
		
		mapData.recyleFlag_	= 0;
		
		responseCode = deptFolMapTbl.update( mapData );

		deptFolMapTbl = null;
	
		if( responseCode == 0)
		{
			return 2020;  // success
		}
    	else
    	{
    		return 2021; // Failed 
    	}
	}
}
