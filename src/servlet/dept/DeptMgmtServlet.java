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

import com.google.gson.Gson;

import core.dept.DeptKey;
import core.login.SessionData;

import ctrl.dept.DeptMgmtController;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

/**
 * File:  DeptMgmtServlet.java 
 *
 * Created on Mar 13, 2013 03:17:28 PM
 */

@WebServlet("/DeptMgmtServlet")
public class DeptMgmtServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private String deptOldName_;
	private String deptNewName_;

	public DeptMgmtServlet()
	{
		super( );

		deptOldName_ = new String( );
		deptNewName_ = new String( );
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
	 * HttpServletRequest and it send the department management (New department creation,
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
			String msg = "Info::DeptMgmtServlet.doPost() -  Request for "
						  + request.getParameter( "RequestType" ) + 
						  " for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			parseRequest( request );
			
			DeptMgmtController deptMgmtController = new DeptMgmtController( );
			
			int responseCode = deptMgmtController.manageDept( request );
			
			deptMgmtController = null;

			String jsonStr = composeResponseJSON( responseCode );

			response.getWriter( ).write( jsonStr );
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::DeptMgmtServlet.doPost() - "+e+"\r\n\n";

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

	private String composeResponseJSON( int responseCode )
	{
		String jsonStr = "";
		
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );

		String responseString = "";
		
		/*
		 *  success numbers for folder creation, update folder and delete 
		 *  folder. Other wise failed.
		 */
		
		if( responseCode == 1900 || responseCode == 1910 || responseCode == 1920 )
		{
			jsonMap.put( "result", "success" );
			
			String msg = "Info::DeptMgmtServlet.doPost() - Request Successful - Response code - "+responseCode+"\r\n\n";
			
			errorLogger.logMsg( msg );
			
			if( responseCode == 1900 )
			{	
				String[] valueArr = {deptNewName_};
				
				responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				System.out.println( responseString );
			}
			
			if( responseCode == 1910 )
			{
				String[] valueArr = {deptOldName_, deptNewName_};
				
				responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				System.out.println( responseString );
			}
			
			if( responseCode == 1920 )
			{
				String[] valueArr = {deptOldName_};
				
				responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				System.out.println( responseString );
			}
			
		}
		else 
		{
			jsonMap.put( "result", "failed" );
			
			String msg = "Info::DeptMgmtServlet.doPost() - Request Failed - Error code - "+responseCode+"\r\n\n";
			
			errorLogger.logMsg( msg );
			
			if( responseCode == 1903 )
			{
				
				String[] valueArr = {deptNewName_};
				
				responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				//System.out.println( responseString );
			}
			else 
			{
				responseString = ErrorCodeConfigReader.instance( ).get( "" + responseCode );
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
		if( request.getParameter( "RequestType" ).equals( "AddNewDept" ) )
		{	
			deptNewName_ = request.getParameter( "DeptName" );
		}
		
		if( request.getParameter( "RequestType" ).equals( "UpdateDept" ))
		{
			DeptKey deptKey = new DeptKey( );
			
			String deptKeyStr = request.getParameter( "DeptKey" );
			
			if( deptKeyStr != null )
			{
				 String [] strArr = deptKeyStr.split( ":" );
	 	            
	 	            if( strArr.length > 1)
	 	            {
	 	            	String deptName = strArr[1];
	 	            	
	 	            	deptKey.deptName_ = deptName;
	 	            }
			}
			
			deptOldName_ = deptKey.deptName_;
			deptNewName_ = request.getParameter( "DeptName" );
			
			deptKey = null;
		}
		
		if( request.getParameter( "RequestType" ).equals( "RemoveDept" ))
		{
			DeptKey deptKey = new DeptKey( );
			
			String deptKeyStr = request.getParameter( "DeptKey" );
			
			if( deptKeyStr != null )
			{
				 String [] strArr = deptKeyStr.split( ":" );
	 	            
	 	            if( strArr.length > 1)
	 	            {
	 	            	String deptName = strArr[1];
	 	            	
	 	            	deptKey.deptName_ = deptName;
	 	            }
			}
			deptOldName_ = deptKey.deptName_;
		}
	}

}
