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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import core.login.SessionData;
import core.userratings.RatingSummaryData;

import ctrl.userratings.UserRatingsController;

import utils.ErrorLogger;

/**
 * @FileName : UserRatingsController.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Aug 28, 2013
 * 
 * @Purpose : It will handle all the request from the servelet
 * 
 */

@WebServlet("/UserRatingsServlet")
public class UserRatingsServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	public UserRatingsServlet()
	{
		super( );
	}

	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		JsonObject responseJson = new JsonObject( );

		UserRatingsController controller = new UserRatingsController( );
		int _result;

		HttpSession session = request.getSession( );
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );

		try
		{
			RatingSummaryData ratingSummaryData = new RatingSummaryData( );

			_result = controller.save( request, sessionData, ratingSummaryData );
			if( _result == 10505 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Review Posted Successfully" );
				if( ratingSummaryData != null )
				{
					responseJson.addProperty( "avg_ratings", ratingSummaryData._avg_ratings );
					responseJson.addProperty( "no_of_ratings", ratingSummaryData._no_of_ratings );
				}
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Error Occured while Posting Review" );
			}

		}
		catch( Exception e )
		{
			String msg = "Exception::UserRatingsServlet.doPost() - " + e + "\r\n\n\n";
			errLogger.logMsg( msg );
			responseJson.addProperty( "result", "failed" );
		}

		System.out.println( "json str=" + responseJson );
		response.setContentType( "application/json" );
		response.setCharacterEncoding( "UTF-8" );
		response.getWriter( ).write( responseJson.toString( ) );

	}

}
