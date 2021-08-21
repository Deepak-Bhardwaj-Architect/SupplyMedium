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

import core.dept.DeptFolderData;
import core.dept.DeptKey;
import core.login.SessionData;
import ctrl.dept.DeptMgmtController;
import utils.ErrorMaster;

/**
 * File: FolderDeptMapServlet.java
 * 
 * Created on Mar 20, 2013 03:17:28 PM
 */

@WebServlet("/FolderDeptMapServlet")
public class FolderDeptMapServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String            currentFolderName_;
	private String            currentDeptName_;
        private static ErrorMaster errorMaster_ = null;




	public FolderDeptMapServlet()
	{
		super( );

		currentDeptName_ = new String( );
		currentFolderName_ = new String( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );

		// TODO Auto-generated constructor stub
	}

	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It get the request from browser. Then parse the
	 * HttpServletRequest and it do the following operation 1.Assign folders to
	 * department (AddFolder) 2.Remove folders from department (RemoveFodler)
	 * 3.Fetch the department folders (FindFolder) 4.Fetch the folders which are
	 * not added in department (ExceptFolder)
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
			String msg = "Info::FolderDeptMapServlet.doPost() - Request for :"
			        + request.getParameter( "RequestType" ) + " for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";

			errLogger.logMsg( msg );
			
			parseRequest( request );

			DeptMgmtController deptMgmtController = new DeptMgmtController( );

			List<DeptFolderData> folderDataArr = new ArrayList<DeptFolderData>( );

			int responseCode = deptMgmtController.mapFolderDept( request, folderDataArr );

			deptMgmtController = null;

			errorMaster_.insert( "Response Code=" + responseCode );
			
			String responseString = "";

			if( responseCode == 2200 || responseCode == 2210 || responseCode == 2220
			        || responseCode == 2230 )
			{
				jsonMap.put( "result", "success" );
			
				msg = "Info::FolderDeptMapServlet.doPost() - Request Successful - Response code - "+responseCode+"\r\n\n";

				errLogger.logMsg( msg );
				
				if( responseCode == 2200 )
				{	
					String[] value = {currentFolderName_, currentDeptName_};
					
					responseString = ErrorCodeConfigReader.instance( ).get( responseCode, value );

					jsonMap.put( "message", responseString );
				}
				
				else if ( responseCode == 2210 )
				{
					String[] value = {currentFolderName_, currentDeptName_};
					
					responseString = ErrorCodeConfigReader.instance( ).get( responseCode, value );

					jsonMap.put( "message", responseString );
				} 
				
				else
				{	
					for( DeptFolderData deptFolderData : folderDataArr )
					{
						String folderKey = deptFolderData.deptFolderKey_.companyRegnKey_.companyPhoneNo_
						        + ":" + deptFolderData.deptFolderKey_.folderName_;

						String folderName = deptFolderData.folderName_;

						jsonMap.put( folderKey, folderName );

					}
				}

			}
			else
			{
				jsonMap.put( "result", "failed" );

				msg = "Info::FolderDeptMapServlet.doPost() - Failed Error code - "+ responseCode+"\r\n\n";

				errLogger.logMsg( msg );
				
				if( responseCode == 2202 || responseCode == 2211 )
				{
					String[] value = {currentFolderName_, currentDeptName_};
					
					responseString = ErrorCodeConfigReader.instance( ).get( responseCode, value );

					jsonMap.put( "message", responseString );
				}
				
				else 
				{
					responseString = ErrorCodeConfigReader.instance( ).get(
					        "" + responseCode );
				}

				jsonMap.put( "message", responseString );
			}

			String jsonStr = new Gson( ).toJson( jsonMap );

			errorMaster_.insert( "json str=" + jsonStr );

			response.getWriter( ).write( jsonStr );
		}
		catch( Exception e )
		{
			String msg = "Exception::FolderDeptMapServlet.doPost() - " + e;

			errLogger.logMsg( msg );

			jsonMap.put( "result", "failed" );

			jsonMap.put( "message", "Request failed. Try again." );

			String jsonStr = new Gson( ).toJson( jsonMap );

			response.getWriter( ).write( jsonStr );

		}
	}

	/* To get folder name and department name */
	private void parseRequest( HttpServletRequest request )
	{
		String [ ] foldersKeyStrArr = request.getParameterValues( "FolderKeys[]" );

		if( foldersKeyStrArr == null )
		{
			return;
		}

		String folderKeyStr = foldersKeyStrArr[0];

		String [ ] strArr = folderKeyStr.split( ":" );

		if( strArr.length > 1 )
		{
			currentFolderName_ = strArr[1];
		}
		
		//To get department name

		DeptKey deptKey = new DeptKey( );
		
		String deptKeyStr = request.getParameter( "DeptKey" );
		
		if( deptKeyStr != null )
		{
			 String [] deptStrArr = deptKeyStr.split( ":" );
 	            
 	            if( deptStrArr.length > 1)
 	            {
 	            	String deptName = deptStrArr[1];
 	            	
 	            	deptKey.deptName_ = deptName;
 	            }
		}
		
		currentDeptName_ = deptKey.deptName_;
	}
}
