
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
 * File:  RFQServlet.java 
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
import core.trans.RFQData;
import ctrl.trans.RFQController;
import utils.ErrorMaster;

/**
 * Servlet implementation class RFQServlet
 */
@WebServlet("/RFQServlet")
public class RFQServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
        private static ErrorMaster errorMaster_ = null;




	
	public RFQServlet()
	{
		super( );
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
	 * Purpose: It get the request from RFQ View under the transaction. This
	 * servlet get the following request from view and send the response to
	 * View.
	 * 1.RFQ creation (RequestType - REQCreation)
	 * 2.Get all the RFQ's for given company and regn key (RequestType - FetchRFQ)
	 * 3.Update the RFQ form - (RequestType - UpdateRFQ)
	 * 4.Change the RFQ status (RequestType - ChangeStatus)
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
		
			String msg = "Info::RFQServlet.doPost() - Request to fetch all " 
						+ request.getParameter( "RequestType" ) +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";

			errLogger.logMsg( msg );
			
			//DeptMgmtController deptMgmtController = new DeptMgmtController( );
			
			RFQController rfqCtrl	= new RFQController( );
			
			List<RFQData> rfqDataList = new ArrayList<RFQData>( );
			
			int responseCode = rfqCtrl.processRequest( request, rfqDataList );
			
			rfqCtrl = null;

			if( responseCode == 8000 || responseCode == 8010 ||
				responseCode == 8020 || responseCode == 8030 )
			{
				//jsonMap.put( "result", "success" );
				
				msg = "Info::RFQServlet.doPost() -  Request Successful - Response code - "+responseCode+"\r\n\n\n";
				
				errLogger.logMsg( msg );
				
				String responseString = ErrorCodeConfigReader.instance( ).get(
				        "" + responseCode );
				
				jsonStr = "{ \"result\" : \"success\",  ";
				jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
				
				if( responseCode == 8020 )
				{
					RFQJSONComposer composer = new RFQJSONComposer( );
					
					jsonStr = composer.composeRFQListJSON( responseCode, rfqDataList );
				}
			}
			else 
			{
				jsonMap.put( "result", "failed" );
				
				msg = "Info::DeptFetchServlet.doPost() -  Request Failed - Error code - "+responseCode+"\r\n\n";
				
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
				
				jsonMap.put( "message", responseString );
				
				jsonStr = new Gson( ).  toJson( jsonMap );
			}
			
			errorMaster_.insert( "json str="+jsonStr );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::DeptFetchServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }
	}
}
