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

package servlet.regn.validation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.StringHolder;


import ctrl.regn.RegnController;

/*
 *Servlet implementation class RegnValidationServlet. This servlet get the company 
 *registration validation request from jsp and parse the request and do the corresponding
 *operation then send the response to that jsp.
 * 
 */

@WebServlet("/RegnValidationServlet")
public class RegnValidationServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public RegnValidationServlet()
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
	 * Purpose: It get the request from the browser. Then parse the
	 * HttpServletRequest and using core class it check the given field is
	 * already available in supplymedium or not.
	 */

	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		String fieldName = request.getParameter( "fieldname" );

		//ErrorLogger errLogger = ErrorLogger.instance( );

		/*String servletMsg = "Info :: RegnValidationServlet : doPost - "
		        + "Registration validation request: " + fieldName;

		errLogger.logMsg( servletMsg );*/

		RegnController regnCtrlObj = new RegnController( );

		boolean result = false;

		String resultStr = "";

		if( fieldName.equals( "email" ) ) // Check email is valid or not 
		{
			String email = request.getParameter( "email" );
			
			String companyName = request.getParameter( "companyname" );

			int emailValRes = regnCtrlObj.validate( email,companyName );
			
			resultStr = resultStr + emailValRes;

		}
		else if( fieldName.equals( "phoneno" ) ) // Check phone number already
												 // exist
		{
			String phoneNo = request.getParameter( "phoneno" );

			result = regnCtrlObj.isPhoneNoExist( phoneNo );
		}
		else if( fieldName.equals( "member" ) ) // Check company can add members
		{
			String companyKey = request.getParameter( "compkey" );

			result = regnCtrlObj.isMemberAllowed( companyKey );
		}
		else if( fieldName.equals( "companytype" ) ) // Fetch Company type
		{
			String companyKey = request.getParameter( "compkey" );

			int comType = regnCtrlObj.getCompanyType( companyKey );

			resultStr = resultStr + comType;
		}
		else if( fieldName.equals( "countrycode" ) ) // Fetch Country code for given country
		{
			String country = request.getParameter( "countryname" );
			
			StringHolder strHolder = new StringHolder( );

			int resultVal = regnCtrlObj.getCountryCode( country, strHolder );
			
			if( resultVal == 0 )
			{
				resultStr = resultStr + strHolder.value;  // Country code available
			}
			else 
			{ 
				resultStr = resultStr + "-1";  // country code not available
			}

			
		}

		if( fieldName.equals( "phoneno" ) || fieldName.equals( "member" )  )
		{
			if( result )
			{
				resultStr = resultStr + "0"; // Checking field data already
				// exists in DB.So it is invaild
			}
			else
			{
				resultStr = resultStr + "1"; // Checking field data not exists
				// in DB.So it is vaild
			}
		}

		response.setContentType( "text/html" );

		response.setCharacterEncoding( "UTF-8" );

		response.getWriter( ).write( resultStr );

	}

}
