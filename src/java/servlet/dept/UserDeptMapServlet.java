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
import core.regn.UserProfileData;

import ctrl.dept.DeptMgmtController;
import utils.ErrorMaster;


/**
 * File: UserDeptMapServlet.java
 * 
 * Created on Mar 20, 2013 03:17:28 PM
 */

@WebServlet("/UserDeptMapServlet")
public class UserDeptMapServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String currentDeptName_;
	private String currentUserName_;
	private String toasterDisAuxVerb_;
        private static ErrorMaster errorMaster_ = null;



	
	public UserDeptMapServlet()
	{
		super( );
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		currentDeptName_ = new String( );
		currentUserName_ = new String( );
		toasterDisAuxVerb_ = new String( );
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
	 *	1.Assign users to department (AddUser)
	 *	2.Remove users from department (RemoveUser)
	 *	3.Fetch the department users (FindUser)
	 *	4.Fetch the users which are not added in department (ExceptUser)
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
			String msg = "Info::UserDeptMapServlet:doPost - Request for :"
						 +request.getParameter( "RequestType" ) +" for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
													
			errLogger.logMsg( msg );
			
			parseRequest( request );
			
			DeptMgmtController deptMgmtController = new DeptMgmtController( );
			
			List<UserProfileData> userDataArr = new ArrayList<UserProfileData>( );
			
			int responseCode = deptMgmtController.mapUserDept( request, userDataArr );
			
			deptMgmtController = null;
			
			errorMaster_.insert( "userdataarr count="+userDataArr.size( ) );

			
			if( responseCode == 2300 || responseCode == 2310 || responseCode == 2320
					||responseCode == 2330  )
			{
				jsonMap.put( "result", "success" );
				
				msg = "Info::UserDeptMapServlet.doPost() - Request successful - Response code - "+responseCode+"\r\n\n\n";
													
				errLogger.logMsg( msg );
				
				if( responseCode == 2300 || responseCode == 2310) 
				{
					String[] valArr = { currentUserName_, toasterDisAuxVerb_, currentDeptName_ };
					
					String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valArr );
					
					jsonMap.put( "message", responseString );
				}
				else 
				{
					for( UserProfileData userProfileData : userDataArr )
					{
						String userKey = userProfileData.userProfileKey_.email_;
								
						String userName = userProfileData.firstName_+" "+userProfileData.lastName_;
						
						jsonMap.put(userKey,userName );
						
					}
				}
				
			}
			else 
			{
				jsonMap.put( "result", "failed" );
				
				msg = "Info::UserDeptMapServlet.doPost() - Request Failed - Error code - "+responseCode+"\r\n\n\n";
				
				errLogger.logMsg( msg );
				
				if( responseCode == 2302 || responseCode == 2311 )
				{
					String[] valArr = { currentUserName_, currentDeptName_ };
					
					String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valArr );
					
					jsonMap.put( "message", responseString );
				} 
				
				else
				{
					String responseString = ErrorCodeConfigReader.instance( ).get( "" + responseCode );

					jsonMap.put( "message", responseString );	
				}
			}
			
			String jsonStr = new Gson( ).  toJson( jsonMap );
			
			errorMaster_.insert( "json str="+jsonStr );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::UserDeptMapServlet.doPost() - "+e +"\r\n\n\n";

			errLogger.logMsg( msg );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }
	}
	
	/*To get department current name and user name name*/
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
		
		if ( !request.getParameterMap().containsKey("UserNames[]") ) 
		{
			return;
		}
		
		String [] usersNameStrArr = request.getParameterValues( "UserNames[]" );
		
		if( usersNameStrArr == null )
		{
			return;
		}
		

		//This is configured like this inorder to show  that if one user is added,
		//user first name will be shown in toaster else, the below text will be shown
		
		if( usersNameStrArr.length > 1 )
		{
			currentUserName_ = "users";
			toasterDisAuxVerb_ = "have";
		}
		else 
		{
			currentUserName_ = "user '"+ usersNameStrArr[0]+ "'";
			toasterDisAuxVerb_ = "has";
		}
		
	}


}
