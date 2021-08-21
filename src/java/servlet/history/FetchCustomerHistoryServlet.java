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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import core.history.CustomerHistoryData;
import core.history.TransReminderData;
import core.login.SessionData;
import ctrl.history.HistoryController;
import utils.ErrorMaster;

/**
 * File:  FetchCustomerHistoryServlet.java 
 *
 * Created on September 4, 2013 9:21:37 AM
 */

/**
 * Servlet implementation class FetchCustomerHistoryServlet
 */
@WebServlet("/FetchCustomerHistoryServlet")
public class FetchCustomerHistoryServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
        private static ErrorMaster errorMaster_ = null;




	public FetchCustomerHistoryServlet()
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
	 * 1.FetchCustomerHistory
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
			
			List<CustomerHistoryData> historyDataList = new ArrayList<CustomerHistoryData>( );
			
			errLogger.logMsg( "Info::FetchCustomerHistoryServlet.doPost() - Request for " + requestType + " for company " +
					"<" + sessionData.companyRegnKeyStr_ + ">, " +
					"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
			
			int responseCode = historyCtrl.getCustomersHistory( request, historyDataList );
			
			jsonStr = parseJSON( responseCode, historyDataList );
			
			response.getWriter( ).write( jsonStr );
        }
		
		catch( Exception e )
        {
			String msg = "Exception::FetchCustomerHistoryServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			//jsonMap.put( "result", "failed" );
			
			jsonStr = "{ \"result\" : \"failed\",  ";		
			
			//jsonMap.put( "message", "Request failed. Try again." );
			
			jsonStr = jsonStr + "\"message\" : \" Request failed. Try again. \" }";
			
			//String jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
        }
		
	}
	
	private String parseJSON( int responseCode, List<CustomerHistoryData> historyDataList )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		errorMaster_.insert( "Response Code="+responseCode );
		
		
		
		if( responseCode == 16000 )
		{
		
			String msg = "Info::FetchCustomerHistoryServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );
			
			jsonStr = "{ \"result\" : \"success\",  ";
			jsonStr = jsonStr + "\""+responseString+"\" : \"working\", ";
			jsonStr = jsonStr + "\"customers\" : [";

			int profileIter = 0;

			for( CustomerHistoryData customerHistoryData : historyDataList )
			{
				jsonStr = jsonStr + "{ \"companyId\" : \"" + customerHistoryData.customerId_ + "\", ";
				jsonStr = jsonStr + " \"companyPhoneNo\" : \"" + customerHistoryData.customerKey_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"companyName\" : \"" + customerHistoryData.companyName_ + "\", ";
				jsonStr = jsonStr + " \"address\" : \"" + customerHistoryData.address_ + "\", ";
				
				jsonStr = jsonStr + "\"email\" : \"" + customerHistoryData.email_ + "\", ";
				
				jsonStr = jsonStr + " \"recentTransId\" : \"" + customerHistoryData.recentTransId_ + "\", ";
				jsonStr = jsonStr + " \"ratingsCount\" : \"" + customerHistoryData.starCount_ + "\", ";
				
				jsonStr = jsonStr + " \"reminders\" : [";
				
				int iter = 0;
				
				for( TransReminderData reminderData : customerHistoryData.remainders_ )
				{
					jsonStr = jsonStr + "{ \"transId\" : \"" + reminderData.transId_ + "\", ";
					jsonStr = jsonStr + " \"transRemainderId\" : \"" + reminderData.transRemainderId_ + "\", ";
					jsonStr = jsonStr + " \"regnKey\" : \"" + reminderData.regnKey_.toString( ) + "\", ";
					jsonStr = jsonStr + " \"customerKey\" : \"" + reminderData.customerKey_.toString( ) + "\", ";
					jsonStr = jsonStr + " \"remainderStr\" : \"" + reminderData.remainder_ + "\", ";
					jsonStr = jsonStr + " \"dueDate\" : \"" + reminderData.dueDate_ + "\"";
					
					jsonStr = jsonStr + "}";
					iter = iter + 1;

					if( customerHistoryData.remainders_.size( ) > iter )
					{
						jsonStr = jsonStr + ",";
					}
				}
				
				jsonStr = jsonStr + "]";
				
				jsonStr = jsonStr + "}";
				profileIter = profileIter + 1;
				
				if( historyDataList.size( ) > profileIter )
				{
					jsonStr = jsonStr + ",";
				}

				errorMaster_.insert( "Profile Arr size: " + historyDataList.size( )
				        + ", profileIter: " + profileIter );
			}

			jsonStr = jsonStr + "]}";

		}
		else 
		{
			jsonStr = "{ \"result\" : \"failed\",  ";
			
			
			String msg = "Info::FetchCustomerHistoryServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			
			jsonStr = jsonStr + "\"message\" : \"Failed\" }";
		}
		
		errorMaster_.insert( "Json = " + jsonStr );
		
		return jsonStr;
	}

}
