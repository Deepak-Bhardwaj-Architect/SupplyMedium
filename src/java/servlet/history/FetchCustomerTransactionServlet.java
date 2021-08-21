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
import java.text.SimpleDateFormat;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import core.history.CustomerTransactionData;
import core.history.TransactionHistoryData;
import core.login.SessionData;
import ctrl.history.HistoryController;
import utils.ErrorMaster;

/**
 * File:  FetchCustomerTransactionServlet.java 
 *
 * Created on September 4, 2013 9:30:11 AM
 */

/**
 * Servlet implementation class FetchCustomerTransactionServlet
 */

@WebServlet("/FetchCustomerTransactionServlet")
public class FetchCustomerTransactionServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
        private static ErrorMaster errorMaster_ = null;




	
	public FetchCustomerTransactionServlet()
	{
		super( );
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
	 * 1.FetchCustomerTransaction
	 *  
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
			
			CustomerTransactionData cusTransData=new CustomerTransactionData( );
			
			errLogger.logMsg( "Info::FetchCustomerTransactionServlet.doPost() - Request for " + requestType + " for company " +
					"<" + sessionData.companyRegnKeyStr_ + ">, " +
					"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
			
			int responseCode = historyCtrl.getTransactionHistory( request,cusTransData  );
			
			jsonStr = parseJSON( responseCode, cusTransData );
			
			response.getWriter( ).write( jsonStr );
        }
		
		catch( Exception e )
        {
			String msg = "Exception::FetchCustomerTransactionServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			//jsonMap.put( "result", "failed" );
			
			jsonStr = "{ \"result\" : \"failed\",  ";		
			
			//jsonMap.put( "message", "Request failed. Try again." );
			
			jsonStr = jsonStr + "\"message\" : \" Request failed. Try again. \" }";
			
			//String jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
        }
		
	}
	
	private String parseJSON( int responseCode, CustomerTransactionData cusTransData)
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		errorMaster_.insert( "Response Code="+responseCode );
		
		
		
		if( responseCode == 16010 )
		{
		
			
			String msg = "Info::FetchCustomerTransactionServlet.doPost() - Request successful - Response code - "
			        + responseCode + "\r\n\n\n";

			errLogger.logMsg( msg );

			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );

			jsonStr = "{ \"result\" : \"success\",  ";
			jsonStr = jsonStr + "\"message\" : \"" + responseString + "\", ";
			jsonStr = jsonStr + "\"customers\" : [";

			int profileIter = 0;

			SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy " );

			jsonStr = jsonStr + "{ \"companyName\" : \"" + cusTransData.companyname_
			        + "\", ";
			jsonStr = jsonStr + " \"customerId\" : \"" + cusTransData.customerId_ + "\", ";
			jsonStr = jsonStr + " \"address\" : \"" + cusTransData.address_ + "\", ";

			jsonStr = jsonStr + "\"city\" : \"" + cusTransData.city_ + "\", ";

			jsonStr = jsonStr + " \"state\" : \"" + cusTransData.state_ + "\", ";
			jsonStr = jsonStr + " \"addressType\" : \"" + cusTransData.addressType_
			        + "\", ";

			jsonStr = jsonStr + " \"transactions\" : [";

			int iter = 0;

			for( TransactionHistoryData historyData : cusTransData.transHistoryDataList_ )
			{
				jsonStr = jsonStr + "{ \"transId\" : \"" + historyData.transId_ + "\", ";

				String dateStr = dateFormat.format( historyData.createdTimestamp_ );

				jsonStr = jsonStr + " \"date\" : \"" + dateStr + "\", ";
				jsonStr = jsonStr + " \"amount\" : \"" + historyData.amount_ + "\", ";
				jsonStr = jsonStr + " \"transStates\" : \"" + historyData.transStates_ + "\", ";
				jsonStr = jsonStr + " \"status\" : \"" + historyData.status_ + "\"";

				jsonStr = jsonStr + "}";
				iter = iter + 1;

				if( cusTransData.transHistoryDataList_.size( ) > iter )
				{
					jsonStr = jsonStr + ",";
				}
			}

			jsonStr = jsonStr + "]";

			jsonStr = jsonStr + "}";
			profileIter = profileIter + 1;

			jsonStr = jsonStr + "]}";

		}
		else 
		{
			jsonStr = "{ \"result\" : \"failed\",  ";
			
			String msg = "Info::FetchCustomerTransactionServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			jsonStr = jsonStr + "\"message\" : \"Failed\" }";
		}
		
		errorMaster_.insert( "Json = " + jsonStr );
		
		return jsonStr;
	}

}
