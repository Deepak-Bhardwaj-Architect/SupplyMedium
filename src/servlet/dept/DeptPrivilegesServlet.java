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
import java.util.HashMap;
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
import core.privilege.DeptPrivilegesData;
import ctrl.dept.DeptMgmtController;


/**
 * File:  DeptPrivilegesServlet.java 
 *
 * Created on Mar 21, 2013 12:39:28 PM
 */


@WebServlet("/DeptPrivilegesServlet")
public class DeptPrivilegesServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String deptCurrentName_;
	
	public DeptPrivilegesServlet()
	{
		super( );
		
		deptCurrentName_ = new String( );
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
	 * HttpServletRequest and it send the department privilege management 
	 * (Fetch department privileges,update department privileges) result to jsp 
	 * using core classes. 
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
			String msg = "Info::DeptPrivilegesServlet.doPost() - Request for :"
						 +request.getParameter( "RequestType" )+" for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
													
			errLogger.logMsg( msg );
			
			parseRequest( request );
			
			DeptMgmtController deptMgmtController = new DeptMgmtController( );
			
			DeptPrivilegesData deptPrivilegesData = new DeptPrivilegesData( );
			
			int responseCode = deptMgmtController.deptPrivileges( request, deptPrivilegesData );
			
			deptMgmtController = null;

			String jsonStr = composeResponseJSON( responseCode, deptPrivilegesData );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::DeptPrivilegesServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }
		
	}
	
	/*
	 * Method : composeResponseJSON( ) 
	 * 
	 * Input : int responseCode, deptPrivilegesdata objetc
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the response in to json
	 * string
	 */

	private String composeResponseJSON(
	        int responseCode, DeptPrivilegesData deptPrivilegesData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String jsonStr = "";
		
		System.out.println( "Response Code="+responseCode );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 2400 || responseCode == 2410  )
		{
			jsonMap.put( "result", "success" );
			
			String msg = "Info::DeptPrivilegesServlet.doPost() - Request Successful - Response code - "+responseCode+"\r\n\n";
															
			errLogger.logMsg( msg );
			
			if( responseCode == 2400 )
			{					
				String[] valueArr = {deptCurrentName_};
				
				String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				jsonMap.put( "message", responseString );
				//System.out.println( responseString );
			}
			
			else 
			{	
				if( deptPrivilegesData.addFolder_)
				{
					jsonMap.put("AddFolder","1" );
				}
				else 
				{
					jsonMap.put("AddFolder","0" );
				}
					
				if( deptPrivilegesData.deleteFolder_)
				{
					jsonMap.put("DeleteFolder","1" );
				}
				else 
				{
					jsonMap.put("DeleteFolder","0" );
				}
				
				if( deptPrivilegesData.addFile_)
				{
					jsonMap.put("AddFile","1" );
				}
				else 
				{
					jsonMap.put("AddFile","0" );
				}
				
				if( deptPrivilegesData.deleteFile_)
				{
					jsonMap.put("DeleteFile","1" );
				}
				else 
				{
					jsonMap.put("DeleteFile","0" );
				}
				
				if( deptPrivilegesData.deleteFile_)
				{
					jsonMap.put("DeleteFile","1" );
				}
				else 
				{
					jsonMap.put("DeleteFile","0" );
				}
				
				if( deptPrivilegesData.postAnnouncement_)
				{
					jsonMap.put("PostAnnouncement","1" );
				}
				else 
				{
					jsonMap.put("PostAnnouncement","0" );
				}
				
				if( deptPrivilegesData.manageFolder_)
				{
					jsonMap.put("ManageFolder","1" );
				}
				else 
				{
					jsonMap.put("ManageFolder","0" );
				}
			}
			
		}
		else 
		{
			jsonMap.put( "result", "failed" );
			
			String msg = "Info::DeptPrivilegesServlet.doPost() - Request Failed - Error code - "+responseCode+"\r\n\n";
														
			errLogger.logMsg( msg );
			
			if( responseCode == 2402 )
			{	
				String[] valueArr = {deptCurrentName_};
				
				String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				jsonMap.put( "message", responseString );
				
				//System.out.println( responseString );
			}
			
			else if( responseCode == 2411 )
			{	
				String[] valueArr = {deptCurrentName_};
				
				String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				jsonMap.put( "message", responseString );
				//System.out.println( responseString );
			}
			
			else 
			{	
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
				
				jsonMap.put( "message", responseString );
			}
			
		}
		
		jsonStr = new Gson( ).  toJson( jsonMap );
		
		System.out.println( "json str="+jsonStr );

		return jsonStr;
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

		deptCurrentName_ = deptKey.deptName_;

		deptKey = null;

	}

}


