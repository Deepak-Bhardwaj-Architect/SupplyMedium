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
package servlet.myconn;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

import com.google.gson.Gson;



/**
 * File:  MyConnStatusComposer.java 
 *
 * Created on 19-Aug-2013 3:27:24 PM
 */
public class MyConnStatusComposer
{

	/*
	 * Method : MyConnStatusComposer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnStatusComposer()
	{
		
	}
	
	/*
	 * Method: composeResultJSON
	 * 
	 * Input: int response, Request object
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the response code and compose JSON string
	 */
	
	public String composeResultJSON( int responseCode, HttpServletRequest request )
	{
		String jsonStr = "";
		
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );

		String responseString = "";
		
		/*
		 *  success numbers for add connection, accept connection and reject 
		 *  connection. Other wise failed.
		 */
		
		if( responseCode == 10000 || responseCode == 10010 || responseCode == 10020 || responseCode == 10090 )
		{
			jsonMap.put( "result", "success" );
			
			String msg = "Info::MyConnStatusServletServlet.doPost() - Request Successful - Response code - "+responseCode+"\r\n\n";
			
			errorLogger.logMsg( msg );
			
			responseString = ErrorCodeConfigReader.instance( ).get( "" + responseCode );
			
		}
		else 
		{
			jsonMap.put( "result", "failed" );
			
			String msg = "Info::DeptMgmtServlet.doPost() - Request Failed - Error code - "+responseCode+"\r\n\n";
			
			errorLogger.logMsg( msg );
			
			responseString = ErrorCodeConfigReader.instance( ).get( "" + responseCode );
			
		}
		
		jsonMap.put( "message", responseString );
		
		jsonStr = new Gson( ).  toJson( jsonMap );
		
		jsonMap = null;
		
		System.out.println( "json str="+jsonStr );

		return jsonStr;
	}

}
