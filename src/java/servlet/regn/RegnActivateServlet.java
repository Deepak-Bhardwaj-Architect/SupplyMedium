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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.AppConfigReader;
import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import ctrl.regn.RegnController;
import utils.ErrorMaster;

/**
 * File:  RegnActivateServlet.java 
 *
 * Created on Jan 21, 2013 2:39:16 PM
 */


@WebServlet("/RegnActivateServlet")
public class RegnActivateServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
        private static ErrorMaster errorMaster_ = null;




	
	public RegnActivateServlet()
	{
		super( );
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}

	/*
	 * Method : doPost( ) 
	 * 
	 * Input  : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: User clicking the Company registration link from mail,
	 * this method called. It get the regnkey from request and using core
	 * classes it activate the company if regnkey is valid. If not company
	 * activation failed.
	 */
	
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{

		errorMaster_.insert( "Regn activate servlet" );
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		String servletMsg = "Info::CompanyRegnServlet.doPost() -" +
				" Company activation request for company <" + request.getParameter( "name" ) + ">";
		
		errLogger.logMsg( servletMsg );

		//request.setAttribute( "RequestType", "RegistrationActivate" );
		
		String companyName = "'"+request.getParameter( "name" )+"'";

		RegnController regnCtrlObj = new RegnController( );

		int result = 0;

		String resultStr = null;

		String resultType = null;

		String page = null;

		try
		{
			result = regnCtrlObj.processRequest( request );
			
			regnCtrlObj = null;

			if( result == 200 ) // Success
			{
				servletMsg = "Info::CompanyRegnServlet.doPost() -" +
						" Company activation success - Response code - " + result + "\r\n\n\n";
				
				errLogger.logMsg( servletMsg );
				
				resultType = "Confirmation";
				
				String jspRelativePath = AppConfigReader.instance( ).get( "JSP_PATH" );

				String jspAbsolutePath = jspRelativePath
				        + "/index.jsp";
				
				String link = "<a href='" + jspAbsolutePath + "'>here</a>";

				String [ ] valueArr = { companyName,link };

				resultStr = ErrorCodeConfigReader.instance( ).get( result, valueArr );
			}
			else if( result == 204 )           // failed.
			{
				servletMsg = "Error::CompanyRegnServlet.doPost() -" +
						" Company activation failed - Error code - " + result + "\r\n\n\n";
				
				errLogger.logMsg( servletMsg );
				
				resultType = "Already Registered";
				
				String jspRelativePath = AppConfigReader.instance( ).get( "JSP_PATH" );

				String jspAbsolutePath = jspRelativePath
				        + "/index.jsp";

				String link = "<a href='" + jspAbsolutePath + "'>here</a>";

				String [ ] valueArr = { link };

				resultStr = ErrorCodeConfigReader.instance( ).get( result, valueArr );

				//resultStr = ErrorCodeConfigReader.instance( ).get( "" + result );
			}

			page = "Views/Utils/jsp/successresponse.jsp";

			response.sendRedirect( "CompanyRegnResultServlet?page=" + page + "&resulttype="
			        + resultType + "&result=" + resultStr );

		}

		catch( Exception ex )
		{
			regnCtrlObj = null;
			
			servletMsg = "Exception::RegnActivationServlet.doPost() -" +ex;
			
			errLogger.logMsg( servletMsg );
			
			resultStr = "Company activation failed. try again.";

			resultType = "Failed";

			page = "Views/Utils/jsp/successresponse.jsp";

			response.sendRedirect( "CompanyRegnResultServlet?page=" + page + "&resulttype="
			        + resultType + "&result=" + resultStr );
		}
	}

	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{

	}

}
