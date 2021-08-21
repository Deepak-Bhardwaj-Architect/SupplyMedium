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
 * File:  TransInquireServlet.java 
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

import com.google.gson.Gson;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import core.login.SessionData;
import core.trans.RFQData;
import core.trans.TransInquireData;
import ctrl.trans.RFQController;
import ctrl.trans.TransInquireController;
import utils.ErrorMaster;

/**
 * Servlet implementation class TransInquireServlet
 */
@WebServlet("/TransInquireServlet")
public class TransInquireServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
private static ErrorMaster errorMaster_ = null;




	
	public TransInquireServlet()
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
	 * Purpose: It get the request for RFQ,Quote,PO and Invoice inquire under the transaction. This
	 * servlet get the following request from view and send the response to
	 * View.
	 * 1.Add the Inquire details (RequestType - AddInquire)
	 * 2.Get all the inquire details for TransactionTypeId ( It should be RFQId or QuoteId
	 *  or PurchaseOrderId or InvoiceId) (RequestType - FetchInquire)
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
		
			String msg = "Info::TransInquireServlet.doPost() - Request to fetch all " 
						+ request.getParameter( "RequestType" ) +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";

			errLogger.logMsg( msg );
			
			
			
			TransInquireController transInquireCtrl = new TransInquireController( );
			
			List<TransInquireData> rfqDataList = new ArrayList<TransInquireData>( );
			
			int responseCode = transInquireCtrl.processRequest( request, rfqDataList );
			
			transInquireCtrl = null;

			if( responseCode == 8040 || responseCode == 8050 )
			{
				
				msg = "Info::TransInquireServlet.doPost() -  Request Successful - Response code - "+responseCode+"\r\n\n\n";
				
				errLogger.logMsg( msg );
				
				String responseString = ErrorCodeConfigReader.instance( ).get(
				        "" + responseCode );
				
				jsonStr = "{ \"result\" : \"success\",  ";
				jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
				
				
			}
			else 
			{
				jsonMap.put( "result", "failed" );
				
				msg = "Info::TransInquireServlet.doPost() -  Request Failed - Error code - "+responseCode+"\r\n\n";
				
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
				
				jsonMap.put( "message", responseString );
				
			    jsonStr = new Gson( ).  toJson( jsonMap );
			}
			
			
			
			errorMaster_.insert( "json str="+jsonStr );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::TransInquireServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			 jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }
	}

}
