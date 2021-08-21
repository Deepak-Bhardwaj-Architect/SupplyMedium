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

import core.privilege.DeptPrivilegesData;
import core.privilege.PrivilegesComposer;
import core.regn.UserProfileKey;

import utils.ErrorLogger;

/**
 * File:  DPFetchDeptController.java 
 *
 * Created on Jul 30, 2013 3:10:00 PM
 */

/*
 * Class: DPFetchDeptController
 * 
 * Purpose: This is used to fetch departments that includes
 * other departments through Manage Folder privilege
 */

public class DPFetchDeptController
{

	private ErrorLogger errLogger_;
	
	/*Constructor*/
	
	public DPFetchDeptController( )
	{
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method: get
	 * 
	 *  Input: HttpServletRequest obj, List<DeptPrivilegesData> deptPrivilegesDataArr
	 *  
	 *  Return: int
	 *  
	 *  Purpose: This method fetches the all the departments for the given dept key
	 *  that includes the user key's non departments which are accessible
	 *  through Manage Folders Privileges
	 */
	
	public int get( HttpServletRequest request, List<DeptPrivilegesData> deptPrivilegesDataArr  )
	{		 
		PrivilegesComposer privilegesComposer = new PrivilegesComposer( );
		
		UserProfileKey profileKey = new UserProfileKey( );
		
		if( request.getParameter( "UserKey" ) == null )
		{
			errLogger_.logMsg( "Error::DPFetchDeptController.get( ) - Unable to parse the request" );
			
			return 1752;
		}
		
		profileKey.email_ = request.getParameter( "UserKey" );
		
		int result = privilegesComposer.getAllDeptAndPri( profileKey, deptPrivilegesDataArr );
				
		return result;
	}
	
	/*Unit Test*/
	public int fetch( List<DeptPrivilegesData> deptPrivilegesDataArr, UserProfileKey profileKey )
	{
		PrivilegesComposer com = new PrivilegesComposer( );
		
		int result = com.getAllDeptAndPri( profileKey, deptPrivilegesDataArr );
		
		return result;
	}
}
