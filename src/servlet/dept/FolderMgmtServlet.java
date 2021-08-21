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

import core.dept.DeptFolderKey;
import core.login.SessionData;

import ctrl.dept.DeptMgmtController;

@WebServlet("/FolderMgmtServlet")
public class FolderMgmtServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String folderOldName_ ;
	private String folderNewName_;
	
	public FolderMgmtServlet()
	{
		super( );
		
		folderNewName_ = new String( );
		folderOldName_ = new String( );
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
	 * HttpServletRequest and it send the folder management (New department creation,
	 * Rename department and department delete) result to jsp using core classes. 
	 * 
	 */
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
		
			String msg = "Info :: FolderMgmtServlet : doPost -  Request for "
						 + request.getParameter( "RequestType" )+ " for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			parseRequest( request );
			
			DeptMgmtController deptMgmtController = new DeptMgmtController( );
			
			int responseCode = deptMgmtController.manageDeptFolder( request );
			
			deptMgmtController = null;

			String jsonStr = composeResponseJSON( responseCode );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::FolderMgmtServlet.doPost() - "+e;

			errLogger.logMsg( msg );
			
			Map<String, String> jsonMap = new HashMap<String, String>( );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			jsonMap = null;
			
			response.getWriter( ).write( jsonStr );
			
        }
	}
	
	/*
	 * Method : composeResponseJSON( ) 
	 * 
	 * Input : int responseCode
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the response in to json
	 * string
	 */

	private String composeResponseJSON(
	        int responseCode )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String jsonStr = "";
		
		String responseString = "";
		
		Map<String, String> jsonMap = new HashMap<String, String>( );

		/*
		 *  success numbers for folder creation, update folder and delete 
		 *  folder. Other wise failed.
		 */
		System.out.print( "Response code="+responseCode );
		
		if( responseCode == 2000 || responseCode == 2010 || responseCode == 2020 )
		{
			jsonMap.put( "result", "success" );
			
			String msg = "Info::FolderMgmtServlet.doPost - Request successful \r\n\n";
		
			errLogger.logMsg( msg );
			
			if( responseCode == 2000 || responseCode == 2003 )
			{	
				String[] valueArr = {folderNewName_};
				
				System.out.println( "folder new name="+folderNewName_ );
				
				responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				System.out.println( responseString );
			}
			
			else if( responseCode == 2020 )
			{	
				String[] valueArr = {folderOldName_};
				
				responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				System.out.println( responseString );
			}
			
			else 
			{
				
				responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			}
			
		}
		
		
		else 
		{
			jsonMap.put( "result", "failed" );
			
			String msg = "Info::FolderMgmtServlet.doPost - Request failed - Error code - "+responseCode+"\r\n\n";
			
			errLogger.logMsg( msg );
			
			if( responseCode == 2003  )
			{	
				String[] valueArr = {folderNewName_};
				
				responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				System.out.println( responseString );
			}
			else if( responseCode == 2021 ) 
			{
				String[] valueArr = {folderOldName_};
				
				responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
			}
			else
			{
				responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			}
		}
		
		jsonMap.put( "message", responseString );
		
		jsonStr = new Gson( ).  toJson( jsonMap );
		
		jsonMap = null;
		
		System.out.println( "json str="+jsonStr );

		return jsonStr;
	}
	
	/*To get department old name and new name*/
	private void parseRequest( HttpServletRequest request )
	{
		if( request.getParameter( "RequestType" ).equals( "AddNewFolder" ) || 
			 request.getParameter( "RequestType" ).equals( "DPAddNewFolder" ) )
		{
			folderNewName_ = request.getParameter( "FolderName" );
		}
		
		if( request.getParameter( "RequestType" ).equals( "RemoveFolder" ) || 
			request.getParameter( "RequestType" ).equals( "DPRemoveFolderToRB" ) )
		{
			DeptFolderKey folderKey = new DeptFolderKey( );
			
			String folderKeyStr = request.getParameter( "FolderKey" );
			
			if( folderKeyStr != null )
			{
				 String [] strArr = folderKeyStr.split( ":" );
	 	            
	 	            if( strArr.length > 1)
	 	            {
	 	            	String folderName = strArr[1];
	 	            	
	 	            	folderKey.folderName_ = folderName;
	 	            }
			}
			System.out.println( "Working 7" );
			folderOldName_ = folderKey.folderName_;

		}
	}

}
