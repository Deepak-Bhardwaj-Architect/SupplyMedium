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

import core.dept.FileData;
import core.login.SessionData;
import ctrl.dept.DeptMgmtController;
import ctrl.dept.DeptPageController;
import utils.ErrorMaster;

/**
 * File:  DeptFetchServlet.java 
 *
 * Created on May 11, 2013 11:17:28 PM
 */

@WebServlet("/DeptFilesMgmtServlet")
public class DeptFilesMgmtServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
        private static ErrorMaster errorMaster_ = null;




	public DeptFilesMgmtServlet()
	{
		super( );
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
	 * Purpose: It get the request from browser.And it do the following operation using 
	 * core classes.
	 * 1.Add File
	 * 2.Delete File
	 * 
	 */
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger  = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {		
			String msg = "Info::DeptFilesMgmtServlet.doPost() -  Request for "
						  + request.getParameter( "RequestType" ) + 
						  " for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			DeptPageController deptPageCtrl = new DeptPageController( );
			
			FileData fileData = new FileData( );
			
			int responseCode = deptPageCtrl.manageFiles( request,fileData );
			
			deptPageCtrl = null;

			String jsonStr = composeResponseJSON( responseCode,fileData );

			response.getWriter( ).write( jsonStr );
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::DeptFilesMgmtServlet.doPost() - "+e+"\r\n\n";

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

	private String composeResponseJSON( int responseCode,FileData fileData )
	{
		String jsonStr = "";
		
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );

		String responseString = "";
		
		/*
		 *  success numbers for folder creation, update folder and delete 
		 *  folder. Other wise failed.
		 */
		
		if( responseCode == 1700 || responseCode == 1705 || responseCode == 1710 )
		{
			jsonMap.put( "result", "success" );
			
			String msg = "Info::DeptFilesMgmtServlet.doPost() - Request Successful - Response code - "+responseCode+"\r\n\n";
			
			errorLogger.logMsg( msg );
			
			if( responseCode == 1700 )  // File uploaded successfully.
			{	
				/*String[] valueArr = {deptNewName_};
				
				responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				errorMaster_.insert( responseString );*/
				
				responseString = ErrorCodeConfigReader.instance( ).get( "" + responseCode );
			}
			
			if( responseCode == 1705 ) // Remove the file from folder and store to recycle bin
			{
				/*String[] valueArr = {deptOldName_, deptNewName_};
				
				responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				errorMaster_.insert( responseString );*/
				
				responseString = ErrorCodeConfigReader.instance( ).get( "" + responseCode );
			}
			
			if( responseCode == 1710 ) // Restore the file from recycle bin
			{
				/*String[] valueArr = {deptOldName_};
				
				responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				errorMaster_.insert( responseString );*/
				
				responseString = ErrorCodeConfigReader.instance( ).get( "" + responseCode );
			}
			
		}
		else 
		{
			jsonMap.put( "result", "failed" );
			
			String msg = "Info::DeptMgmtServlet.doPost() - Request Failed - Error code - "+responseCode+"\r\n\n";
			
			errorLogger.logMsg( msg );
			
			/*if( responseCode == 1903 )
			{
				
				String[] valueArr = {deptNewName_};
				
				responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				//errorMaster_.insert( responseString );
			}
			else 
			{
				responseString = ErrorCodeConfigReader.instance( ).get( "" + responseCode );
			}*/
			
			responseString = ErrorCodeConfigReader.instance( ).get( "" + responseCode );
		}
		
		jsonMap.put( "message", responseString );
		
		jsonStr = new Gson( ).  toJson( jsonMap );
		
		jsonMap = null;
		
		errorMaster_.insert( "json str="+jsonStr );

		return jsonStr;
	}

}
