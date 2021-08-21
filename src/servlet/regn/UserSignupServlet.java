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

package servlet.regn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;



import utils.AppConfigReader;
import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import ctrl.regn.UserSignupController;

/**
 * File: UserSignupServlet.java
 * 
 * Created on Jan 21, 2013 2:39:16 PM
 */

/*
 * Servlet implementation class CompanyRegnServlet. This servlet get the user
 * signup request from jsp and parse the request and do the corresponding
 * operation then send the response to that jsp.
 */

@WebServlet("/UserSignupServlet")
public class UserSignupServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public UserSignupServlet()
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
	 * Purpose: It get the request from the usersignup.jsp. Then parse the
	 * HttpServletRequest and using core class it add the user to company in
	 * SupplyMedium. Then send the back the result to browser.
	 */

	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{

		System.out.println( "do post method called" );

		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String servletMsg = "Info::UserSignupServlet.doPost() - "
		        + request.getParameter( "RequestType" ) 
		        + " request for the company <" +request.getParameter( "companyname" )+">";
						

		errLogger.logMsg( servletMsg );

		UserSignupController userSignupCtrl = new UserSignupController( );

		int result = 0;

		String resultType = null;

		String page = null;

		String resultStr = null;

		try
		{
			result = userSignupCtrl.processRequest( request );

			if( request.getParameter( "RequestType" ).equals( "NewSignup" ) )
			{
				
				if( result == 300 ) // Success
				{
					servletMsg = "Info::UserSignupServlet.doPost() - " +
							"Request successful - Response code - " + result + "\r\n\n\n";

					errLogger.logMsg( servletMsg );
					
					resultType = "Confirmation";

					resultStr = ErrorCodeConfigReader.instance( ).get( "" + result );
				}
				else
				{
					servletMsg = "Info::UserSignupServlet.doPost() - " +
							"Request failed - Error code - " + result + "\r\n\n\n";

					errLogger.logMsg( servletMsg );
					
					resultType = "Failed";

					String jspRelativePath = AppConfigReader.instance( ).get( "JSP_PATH" );

					String jspAbsolutePath = jspRelativePath
					        + "/Views/Registration/jsp/usersignup.jsp";

					String link = "<a href='" + jspAbsolutePath + "'>here</a>";

					String [ ] valueArr = { link };

					resultStr = ErrorCodeConfigReader.instance( ).get( result, valueArr );
				}

				page = "Views/Utils/jsp/successresponse.jsp";

				response.sendRedirect( "CompanyRegnResultServlet?page=" + page
				        + "&resulttype=" + resultType + "&result=" + resultStr );
			}
			
			else 
			{
				String jsonStr = composeResponseJSON( result, request );
				
				response.setContentType( "application/json" );

				response.setCharacterEncoding( "UTF-8" );

				response.getWriter( ).write( jsonStr );

			}

		}

		catch( Exception ex )
		{
			servletMsg = "Exception :: UserSignupServlet : doPost - " + ex;

			errLogger.logMsg( servletMsg );
			
			if( request.getParameter( "RequestType" ).equals( "NewSignup" ) )
			{

				resultStr = "Error occur while registering with SupplyMedium. Please try agin later.";

				resultType = "Failed";

				page = "Views/Utils/jsp/successresponse.jsp";

				response.sendRedirect( "CompanyRegnResultServlet?page=" + page
				        + "&resulttype=" + resultType + "&result=" + resultStr );
			}
			else 
			{
				Map<String, String> jsonMap = new HashMap<String, String>( );
				
				jsonMap.put( "result", "failed" );
				
				jsonMap.put( "message", "Request failed. Try again." );
				
				String jsonStr = new Gson( ).toJson( jsonMap );
				
				jsonMap = null;
				
				response.getWriter( ).write( jsonStr );
			}
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

	private String composeResponseJSON( int responseCode, HttpServletRequest request )
	{
		String jsonStr = "";
		
		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );
		
		if( responseCode == 700 || responseCode == 750 || responseCode == 720 || responseCode == 800 )
		{
			String servletMsg = "Info::UserSignupServlet.doPost() - " +
					"Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );
			
			jsonMap.put( "result", "success" );
			
			if( responseCode == 700 || responseCode == 750 || responseCode == 800 )
			{
				String[] valueArr = { request.getParameter( "firstname" ) };
				
				String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				jsonMap.put( "message", responseString );
				
			}
			
			else if( responseCode == 720 )
			{
				String[] valueArr = { request.getParameter( "firstname" ), request.getParameter( "status" ) };
				
				String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				jsonMap.put( "message", responseString );
			}
			
			else
			{
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
				
				jsonMap.put( "message", responseString );
			}
			
		}
		else 
		{
			jsonMap.put( "result", "failed" );
			
			String servletMsg = "Info::UserSignupServlet.doPost() - " +
					"Request failed - Error code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			
			if( responseCode == 701 || responseCode == 751 || responseCode == 801 )
			{	
				String[] valueArr = { request.getParameter( "firstname" ) };
				
				String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				jsonMap.put( "message", responseString );
				
			}
			
			else 
			{
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
				
				jsonMap.put( "message", responseString );
			}
		}
	
		jsonStr = new Gson( ).  toJson( jsonMap );
		
		jsonMap = null;
		
		System.out.println( "json str="+jsonStr );

		return jsonStr;
	}
}
