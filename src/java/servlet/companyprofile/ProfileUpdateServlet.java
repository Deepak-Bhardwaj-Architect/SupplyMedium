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

import utils.ErrorLogger;

import com.google.gson.JsonObject;

import ctrl.companyprofile.CompanyProfileController;
import utils.ErrorMaster;

/**
 * @FileName : ProfileUpdateServlet.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Mar 14, 2013
 * 
 * @Purpose : Update the Company Profile Information
 * 
 */
@WebServlet("/ProfileUpdateServlet")
public class ProfileUpdateServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;
        private static ErrorMaster errorMaster_ = null;



	
	protected void doPost( HttpServletRequest request,
	        HttpServletResponse response ) throws ServletException, IOException
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( " ProfileUpdateServlet " );
		errorMaster_.insert( " ~~~~~~~~~~~~~~~~~~~~ " );

		
		JsonObject insertAddrId=new JsonObject( );
		int result;

		
			errorMaster_.insert("ProfileUpdateServlet :: Update Profile Received");
			
			CompanyProfileController profileController=new CompanyProfileController( );
			result=profileController.updateCompanyProfile( request );
			profileController=null;
			if(result==2300)
			{
				errorMaster_.insert("Info::ProfileUpdateServlet :: Company Profile Updated Successfully"  );
				insertAddrId.addProperty( "result", "success" );
			}
			else if(result==2301)
			{
				ErrorLogger errLogger_ = ErrorLogger.instance( );
				errLogger_.logMsg( "Error::ProfileUpdateServlet :: Error Occured while Updating the Comapny Profile" );
				insertAddrId.addProperty( "result", "failed" );				
			}

		errorMaster_.insert( "jsonStr=" + insertAddrId.toString( ) );

		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );

		response.getWriter( ).write( insertAddrId.toString( ) );
		
		errorMaster_.insert( " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " );
		
	}

}
