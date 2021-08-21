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

/**
 * File:  QuoteServlet.java 
 *
 * Created on Jun 20, 2013 02:45:28 PM
 */

package servlet.trans;

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

import core.login.SessionData;
import core.trans.QuoteData;
import ctrl.trans.QuoteController;

/**
 * Servlet implementation class QuoteServlet
 */
@WebServlet("/QuoteServlet")
public class QuoteServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

	
	public QuoteServlet()
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
	 * Purpose: It get the request from Quote View under the transaction. This
	 * servlet get the following request from view and send the response to
	 * View.
	 * 1.Quote creation (RequestType - QuoteCreation)
	 * 2.Get all the Quote's for given company and regn key (RequestType - FetchQuote)
	 * 3.Update the Quote form - (RequestType - UpdateQuote)
	 * 4.Change the Quote status (RequestType - ChangeStatus)
	 * 
	 */
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );
		
		String jsonStr = "";
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
		
			String msg = "Info::QuoteServlet.doPost() - Request to fetch all " 
						+ request.getParameter( "RequestType" ) +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";

			errLogger.logMsg( msg );
			
			//DeptMgmtController deptMgmtController = new DeptMgmtController( );
			
			QuoteController quoteCtrl	= new QuoteController( );
			
			List<QuoteData> quoteDataList = new ArrayList<QuoteData>( );
			
			int responseCode = quoteCtrl.processRequest( request, quoteDataList );
			
			quoteCtrl = null;

			if( responseCode == 8100 || responseCode == 8110 ||
				responseCode == 8120 || responseCode == 8130 )
			{
				//jsonMap.put( "result", "success" );
				
				msg = "Info::QuoteServlet.doPost() -  Request Successful - Response code - "+responseCode+"\r\n\n\n";
				
				errLogger.logMsg( msg );
				
				String responseString = ErrorCodeConfigReader.instance( ).get(
				        "" + responseCode );
				
				jsonStr = "{ \"result\" : \"success\",  ";
				jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
				
				if( responseCode == 8120 )
				{
					QuoteJSONComposer composer = new QuoteJSONComposer( );
					
					jsonStr = composer.composeQuoteListJSON( responseCode, quoteDataList );
				}
			}
			else 
			{
				jsonMap.put( "result", "failed" );
				
				msg = "Info::QuoteServlet.doPost() -  Request Failed - Error code - "+responseCode+"\r\n\n";
				
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
				
				jsonMap.put( "message", responseString );
				
				jsonStr = new Gson( ).  toJson( jsonMap );
			}
			
			System.out.println( "json str="+jsonStr );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::QuoteServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }
	}
}
