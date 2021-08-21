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

import core.dept.DeptData;
import core.login.SessionData;

import ctrl.dept.DeptMgmtController;

/**
 * File:  DeptFetchServlet.java 
 *
 * Created on Mar 14, 2013 03:17:28 PM
 */

@WebServlet("/DeptFetchServlet")
public class DeptFetchServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public DeptFetchServlet()
	{
		super( );
		
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
	 * Purpose: It get the request from browser. Fetch the all the department list
	 * associated with company. Then send the result to jsp.
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
		
			String msg = "Info::DeptFetchServlet.doPost() -  Request to fetch all " +
					"departments for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
													

			errLogger.logMsg( msg );
			
			DeptMgmtController deptMgmtController = new DeptMgmtController( );
			
			List<DeptData> deptDataArr = new ArrayList<DeptData>( );
			
			int responseCode = deptMgmtController.getAllDepartments( request, deptDataArr );
			
			deptMgmtController = null;

			System.out.println( "Response code= "+deptDataArr.size( ) );
			
			if( responseCode == 1930  )
			{
				jsonMap.put( "result", "success" );
				
				msg = "Info::DeptFetchServlet.doPost() -  Request Successful - Response code - "+responseCode+"\r\n\n";
				
				errLogger.logMsg( msg );
				
				for( DeptData deptData : deptDataArr )
				{
					String deptKey = deptData.deptKey_.companyRegnKey_.companyPhoneNo_+":"
							+deptData.deptName_;
							
					String deptName = deptData.deptName_;
					
					System.out.println( "deptkey="+deptKey +",deptname="+deptName );
					
					jsonMap.put(deptKey,deptName );
					
					System.out.println( "iteration" +jsonMap.size( ));
				}
			}
			else 
			{
				jsonMap.put( "result", "failed" );
				
				msg = "Info::DeptFetchServlet.doPost() -  Request Failed - Error code - "+responseCode+"\r\n\n";
				
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
				
				jsonMap.put( "message", responseString );
			}
			
			String jsonStr = new Gson( ).  toJson( jsonMap );
			
			System.out.println( "json str="+jsonStr );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::DeptFetchServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }
	}

}
