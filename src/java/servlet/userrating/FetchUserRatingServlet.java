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

package servlet.userrating;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ErrorLogger;

import com.google.gson.Gson;
import core.userratings.UserRatingsData;

import ctrl.userratings.UserRatingsController;
import utils.ErrorMaster;

/**
 * @FileName : FetchUserRatingServlet.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Aug 28, 2013
 * 
 * @Purpose : Getting the User Rating Servlet
 * 
 */

@WebServlet("/FetchUserRatingServlet")
public class FetchUserRatingServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;
        private static ErrorMaster errorMaster_ = null;




	public FetchUserRatingServlet()
	{
		super( );if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}

	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );

		UserRatingsController controller = new UserRatingsController( );
		int _result;
		String responseJson = "";

		//try
	//	{
			List<UserRatingsData> ratingsList = new ArrayList<UserRatingsData>( );
			_result = controller.getRatingsList( request, ratingsList );
			if( _result == 10500 )
			{
				responseJson = "{ \"result\" : \"success\",  ";

				Gson gson = new Gson( );
				String value = gson.toJson( ratingsList );
				responseJson += "\"UserRatingList\" :" + value;
				responseJson += "}";
			}
			else
			{
				responseJson = "{ \"result\" : \"failed\",  ";
			}

		/*}
		catch( Exception e )
		{
			String msg = "Exception::FetchUserRatingServlet.doPost() - " + e + "\r\n\n\n";
			errLogger.logMsg( msg );
			responseJson = "{ \"result\" : \"failed\",  ";
		}*/

		errorMaster_.insert( "json str=" + responseJson );
		response.setContentType( "application/json" );
		response.setCharacterEncoding( "UTF-8" );
		response.getWriter( ).write( responseJson.toString( ) );
	}

}
