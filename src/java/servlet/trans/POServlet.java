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
 * File:  POServlet.java 
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
import core.trans.POData;
import ctrl.trans.POController;
import utils.ErrorMaster;

/**
 * Servlet implementation class POServlet
 */
@WebServlet("/POServlet")
public class POServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
private static ErrorMaster errorMaster_ = null;




	
	public POServlet()
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
	 * Purpose: It get the request from PO View under the transaction. This
	 * servlet get the following request from view and send the response to
	 * View.
	 * 1.Purchase Order creation (RequestType - POCreation)
	 * 2.Get all the Purchase Order's for given company and regn key (RequestType - FetchPO)
	 * 3.Update the PurchaseOrder form - (RequestType - UpdatePO)
	 * 4.Change the PurchaseOrder  status (RequestType - ChangeStatus)
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
		
			String msg = "Info::POServlet.doPost() - Request to fetch all " 
						+ request.getParameter( "RequestType" ) +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";

			errLogger.logMsg( msg );
			
			//DeptMgmtController deptMgmtController = new DeptMgmtController( );
			
			POController poCtrl	= new POController( );
			
			List<POData> poDataList = new ArrayList<POData>( );
			
			int responseCode = poCtrl.processRequest( request, poDataList );
			
			poCtrl = null;

			if( responseCode == 8200 || responseCode == 8210 ||
				responseCode == 8220 || responseCode == 8230 )
			{
				//jsonMap.put( "result", "success" );
				
				msg = "Info::POServlet.doPost() -  Request Successful - Response code - "+responseCode+"\r\n\n\n";
				
				errLogger.logMsg( msg );
				
				String responseString = ErrorCodeConfigReader.instance( ).get(
				        "" + responseCode );
				
				jsonStr = "{ \"result\" : \"success\",  ";
				jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
				
				if( responseCode == 8220 )
				{
					POJSONComposer composer = new POJSONComposer( );
					
					jsonStr = composer.composePoListJSON( responseCode, poDataList );
				}
			}
			else 
			{
				jsonMap.put( "result", "failed" );
				
				msg = "Info::POServlet.doPost() -  Request Failed - Error code - "+responseCode+"\r\n\n";
				
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
				
				jsonMap.put( "message", responseString );
				
				jsonStr = new Gson( ).  toJson( jsonMap );
			}
			
			errorMaster_.insert( "json str="+jsonStr );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::POServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }
	}

}
