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

package servlet.dept;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

import com.google.gson.Gson;

import core.dept.DeptKey;
import core.login.SessionData;

import core.usermgmt.GroupData;

import ctrl.dept.DeptMgmtController;

/**
 * File:  GroupDeptMapServlet.java 
 *
 * Created on Mar 15, 2013 03:17:28 PM
 */
@WebServlet("/GroupDeptMapServlet")
public class GroupDeptMapServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private String currentDeptName_;
	private String currentGroupName_;

	public GroupDeptMapServlet()
	{
		super( );
		
		currentGroupName_ = new String( );
		currentDeptName_ = new String( );
	}

	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		
	}

	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It get the request from browser. Then parse the
	 * HttpServletRequest and it do the following operation
	 *	1.Assign groups to department (AddGroup)
	 *	2.Remove groups from department (RemoveGroup)
	 *	3.Fetch the department groups (FindGroup)
	 *	4.Fetch the groups which are not added in department (ExceptGroup)
	 * 
	 */
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
			String msg = "Info::GroupDeptMapServlet:doPost - Request for :"
						+request.getParameter( "RequestType" ) +" for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
													
			errLogger.logMsg( msg );
			
			parseRequest( request );
			
			DeptMgmtController deptMgmtController = new DeptMgmtController( );
			
			List<GroupData> groupDataArr = new ArrayList<GroupData>( );
			
			int responseCode = deptMgmtController.mapGroupDept( request, groupDataArr );
			
			deptMgmtController = null;

			
			if( responseCode == 2100 || responseCode == 2110 || responseCode == 2120
					||responseCode == 2130  )
			{
				jsonMap.put( "result", "success" );
				
				msg = "Info::GroupDeptMapServlet.doPost() - Request successful - Response code - " + responseCode + " \r\n\n\n";
													
				errLogger.logMsg( msg );
				
				if( responseCode == 2100 || responseCode == 2110) 
				{
					String[] valArr = { currentGroupName_, currentDeptName_ };
					
					String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valArr );
					
					jsonMap.put( "message", responseString );
				}
				else 
				{
					for( GroupData groupData : groupDataArr )
					{
						System.out.println( "for loop"+  groupData.groupKey_.companyRegnKey_.companyPhoneNo_ );
						
						String groupKey = groupData.groupKey_.companyRegnKey_.companyPhoneNo_+":"
								+groupData.groupName_;
								
						String groupName = groupData.groupName_;
						
						jsonMap.put(groupKey,groupName );
						
					}
				}
				
			}
			else 
			{
				jsonMap.put( "result", "failed" );
				
				msg = "Info::GroupDeptMapServlet.doPost() - Request failed - Error code - "+responseCode+"\r\n\n\n";
				
				errLogger.logMsg( msg );
				
				if( responseCode == 2102 || responseCode == 2111)
				{

					
					String[] valArr = {currentGroupName_, currentDeptName_ };
					
					String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valArr );
					
					jsonMap.put( "message", responseString );
		
				}
				else 
				{
					String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
					
					jsonMap.put( "message", responseString );
				}
			}
			
			String jsonStr = new Gson( ).  toJson( jsonMap );
			
			System.out.println( "json str="+jsonStr );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::GroupDeptMapServlet.doPost() - "+e+"\r\n\n\n";

			errLogger.logMsg( msg );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }
	}
	
	/*To get department old name and new name*/
	private void parseRequest( HttpServletRequest request )
	{
		DeptKey deptKey = new DeptKey( );

		String deptKeyStr = request.getParameter( "DeptKey" );

		if( deptKeyStr != null )
		{
			String [ ] strArr = deptKeyStr.split( ":" );

			if( strArr.length > 1 )
			{
				String deptName = strArr[1];

				deptKey.deptName_ = deptName;
			}
		}

		currentDeptName_ = deptKey.deptName_;

		deptKey = null;
		
		if (!request.getParameterMap().containsKey("GroupKeys[]")) 
		{
			return;
		}
		
		String [] groupsKeyStrArr = request.getParameterValues( "GroupKeys[]" );
		
		if( groupsKeyStrArr == null )
		{
			return;
		}
		
		String groupStr = groupsKeyStrArr[0];
		
		String[] strArr = groupStr.split( ":" );
		
		currentGroupName_ = strArr[1];	
	}
}
