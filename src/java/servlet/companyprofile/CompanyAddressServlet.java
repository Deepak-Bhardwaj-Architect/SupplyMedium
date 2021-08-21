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

package servlet.companyprofile;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;

import core.regn.MailingAddressKey;
import utils.ErrorLogger;
import ctrl.companyprofile.CompanyProfileController;
import utils.ErrorMaster;

/**
 * @FileName : CompanyAddressServlet.java
 * 
 * @author : Anbazhagan P
 * 
 * @Date : Mar 13, 2013
 * 
 * @Purpose : It Will Handle the Company Profile Add/Remove Mail Address
 *          Requests
 * 
 * @Note : Adding Mail Address to the Company
 * 
 **/

@WebServlet("/CompanyAddressServlet")
public class CompanyAddressServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;
        private static ErrorMaster errorMaster_ = null;




	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
	        IOException
	{

		String mailOperation = request.getParameter( "operation" );
if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( " Info::CompanyAddressServlet :: CompanyAddressServlet Received Request" );
		errorMaster_.insert( " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " );

		JsonObject responseJson = new JsonObject( );
		CompanyProfileController profileController = new CompanyProfileController( );
		ErrorLogger errLogger_ = ErrorLogger.instance( );
		int result = 0;
		MailingAddressKey mailKey = new MailingAddressKey( );
		
		
		if( mailOperation.equalsIgnoreCase( "AddMail" ) )
		{
			

			errorMaster_.insert( "Info::CompanyAddressServlet :: AddMaillAddress ::"
			        + "Maill Data Conversion Successfull" );
			result = profileController.addMailingAddress( request, mailKey );
			if( result == 2100 )
			{
				System.out
				        .println( "Info::CompanyAddressServlet :: AddMaillAddress ::Mail Address Inserted Successfully" );

				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "insertAddrId", mailKey.mailngKey_ );
			}
			else if( result == 2101 )
			{

				errLogger_
				        .logMsg( "Error::CompanyAddressServlet :: AddMaillAddress :: Error Occured while Inserting the mail Address " );
				responseJson.addProperty( "result", "failed" );
			}

		}

		else if( mailOperation.equalsIgnoreCase( "RemoveMail" ) )
		{
			if( request.getParameterMap( ).containsKey( "addressId" ) )
			{
				mailKey.mailngKey_ = Long.parseLong(request.getParameter( "addressId" ) );

				result = profileController.removeMailingAddress( mailKey );
				if( result == 2200 )
				{
					System.out
					        .println( "Info::CompanyAddressServlet :: RemoveMaillAddress :: Mail Address Removed Successfully" );
					responseJson.addProperty( "result", "success" );
				}
				else if( result == 2201 )
				{
					errLogger_.logMsg( "Error::CompanyAddressServlet :: RemoveMaillAddress ::  Error Occured while Removing the mail Address " );
					responseJson.addProperty( "result", "failed" );
				}
			}
			else
			{
				errorMaster_.insert( "Error::CompanyAddressServlet :: RemoveMaillAddress ::Invalid Call" );
				responseJson.addProperty( "result", "failed" );
			}

		}

		errorMaster_.insert( "jsonStr=" + responseJson.toString( ) );

		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );

		response.getWriter( ).write( responseJson.toString( ) );

	}

}
