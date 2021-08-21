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

package servlet.history;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import core.login.SessionData;
import ctrl.history.HistoryController;

/**
 * File:  RatingsServlet.java 
 *
 * Created on September 4, 2013 9:35:53 AM
 */

/**
 * Servlet implementation class RatingsServlet
 */

@WebServlet("/RatingsServlet")
public class RatingsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public RatingsServlet()
	{
		super( );
	
	}

	
	protected void doGet( HttpServletRequest request,
	        HttpServletResponse response ) throws ServletException, IOException
	{
		
	}
	
	
	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It get the request from History under the Network Menu from GUI. This
	 * servlet get the following request from view and send the response to
	 * View. It do the following 
	 * 
	 * 1.add the Rating
	 */
	
	protected void doPost( HttpServletRequest request,
	        HttpServletResponse response ) throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		String jsonStr =  "";
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
			String requestType = request.getParameter( "RequestType" );
			
			HistoryController historyCtrl = new HistoryController( );
			
			errLogger.logMsg( "Info::RatingsServlet.doPost() - Request for " + requestType + " for company " +
					"<" + sessionData.companyRegnKeyStr_ + ">, " +
					"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
			
			int responseCode = historyCtrl.addRatings( request );
			
			System.out.println( "ResponseCode - "+responseCode );
			
			//Based on the responseCode, the response JSON message to be generated here
			
			if( responseCode == 16020 ) 
			{
				jsonStr = "{ \"result\" : \"success\",  ";		
				
			}	
			else
			{
				jsonStr = "{ \"result\" : \"failed\",  ";		
		
			}		
			String responseString = ErrorCodeConfigReader.instance( ).get( Integer.toString( responseCode ) );
			
			jsonStr = jsonStr + "\"message\" : \" "+responseString+"\" }";
			
			response.getWriter( ).write( jsonStr );
        }
		
		catch( Exception e )
        {
			String msg = "Exception::RatingsServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			jsonStr = "{ \"result\" : \"failed\",  ";		
			
			jsonStr = jsonStr + "\"message\" : \" Request failed. Try again. \" }";
			
			response.getWriter( ).write( jsonStr );
        }
	}

}
