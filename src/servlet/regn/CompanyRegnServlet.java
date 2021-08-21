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

/**
 * File: CompanyRegnServlet.java
 * 
 * Created on Jan 21, 2013 2:39:16 PM
 */

/*
 * Servlet implementation class CompanyRegnServlet. This servlet get the company
 * registration request from jsp and parse the request and do the corresponding
 * operation then send the response to that jsp.
 */

@WebServlet("/CompanyRegnServlet")
public class CompanyRegnServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public CompanyRegnServlet()
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
	 * Purpose: It get the request from the companyregistration.jsp. Then parse
	 * the HttpServletRequest and using core class it add the company in
	 * SupplyMedium. Then send the back the result to browser.
	 */

	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{

		String companyName = request.getParameter( "companyname" );

		ErrorLogger errLogger = ErrorLogger.instance( );

		String servletMsg = "Info::CompanyRegnServlet.doPost() "
		        + "New company registration request <" + companyName + ">";

		errLogger.logMsg( servletMsg );

		RegnController regnCtrlObj = new RegnController( );

		int result = 0;

		String resultType = null;

		String resultStr = null;

		String page = null;

		try
		{
			result = regnCtrlObj.processRequest( request );

			if( result == 100 )
			{
				String errMsg = "Info::CompanyRegnServlet.doPost() - New company "
				        + "successfully added <" + companyName +">\r\n\n\n";
				
				errLogger.logMsg( errMsg );

				/*
				 * Result type string, This string decide in jsp page which css
				 * apply for response page. Confirmation - Company registration
				 * success css
				 */
				resultType = "Confirmation";

				resultStr  = ErrorCodeConfigReader.instance( ).get( "" + result );
			}
			else
			{	
				String errMsg = "Error::CompanyRegnServlet.doPost() - New company "
				        + " registration failed with error code <" + result + "> for Company <" + companyName +">\r\n\n\n";
				
				errLogger.logMsg( errMsg );
				
				resultType = "Failed"; // Result type string
				
				String jspRelativePath = AppConfigReader.instance( ).get( "JSP_PATH" );
				
				String jspAbsolutePath = jspRelativePath + "/Views/Registration/jsp/companyregistration.jsp";
				
				String link = "<a href='"+jspAbsolutePath+"'>here</a>";
				
				String [] valueArr = {link};

				resultStr = ErrorCodeConfigReader.instance( ).get( result, valueArr );
			}

			page = "Views/Utils/jsp/successresponse.jsp";

			// Send redirect to CompanyRegnResultServlet with request parameters

			response.sendRedirect( "CompanyRegnResultServlet?page=" + page + "&resulttype="
			        + resultType + "&result=" + resultStr );

		}

		catch( Exception ex )
		{

			String errMsg = "Exception::CompanyRegnServlet.doPost() - " + ex + "\r\n\n\n";

			errLogger.logMsg( errMsg );

			resultStr = "An error occurred while registering with SupplyMedium. Please try agin later.";

			/*
			 * Result type string, This string decide in jsp page which css
			 * apply for response page. Failed - Company registration failed css
			 */
			
			resultType = "Failed";

			page = "Views/Utils/jsp/successresponse.jsp";

			response.sendRedirect( "CompanyRegnResultServlet?page=" + page + "&resulttype="
			        + resultType + "&result=" + resultStr );
		}

	}

}
