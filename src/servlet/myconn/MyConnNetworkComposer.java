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

import java.text.SimpleDateFormat;
import java.util.List;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

import core.myconn.MyConnectionData;


/**
 * File:  UserConnComposer.java 
 *
 * Created on 13-Aug-2013 5:52:41 PM
 */
public class MyConnNetworkComposer
{

	/*
	 * Method : MyConnComposer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnNetworkComposer()
	{
	}
	
	/*
	 * Method: composeConnectionsJSON
	 * 
	 * Input: int response, List<UserConnectionData> connections
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the connections list and compose JSON string
	 */
	
	public String composeConnectionsJSON( int responseCode, List<MyConnectionData> connections )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		System.out.println( "Response Code="+responseCode );
		
		if( responseCode == 10030 || responseCode == 10040 || responseCode == 10050 || responseCode == 10070 || responseCode == 10080 )
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy ");
			
			jsonStr = jsonStr+ "{ \"result\" : \"success\",  ";
			
			String msg = "Info::MyConnNetworkServletServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			jsonStr = jsonStr + "\"connections\" : [";
			
			int iterator = 0;
			
			for( MyConnectionData myConnData : connections )
            {
				jsonStr = jsonStr + "{ \"fromUserName\" : \"" + myConnData.fromUserName_ + "\", ";
				jsonStr = jsonStr + " \"fromCompName\" : \"" + myConnData.fromCompName_ + "\", ";
				
				if( myConnData.fromUserKey_ != null )
					jsonStr = jsonStr + " \"fromUserKey\" : \"" + myConnData.fromUserKey_.toString( ) + "\", ";
				
				if( myConnData.fromRegnKey_ != null )
					jsonStr = jsonStr + " \"fromRegnKey\" : \"" + myConnData.fromRegnKey_.toString( ) + "\", ";
				
				
				
				jsonStr = jsonStr + " \"toUserName\" : \"" + myConnData.toUserName_ + "\", ";
				jsonStr = jsonStr + " \"toCompName\" : \"" + myConnData.toCompName_ + "\", ";
				jsonStr = jsonStr + " \"toUserKey\" : \"" + myConnData.toUserKey_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"toRegnKey\" : \"" + myConnData.toRegnKey_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"photoPath\" : \"" + myConnData.userProfileImagePath_+ "\", ";
				
				
				
				if( myConnData.createdTimestamp_!= null )
				{
					String createdTimeStr =	dateFormat.format( myConnData.createdTimestamp_  );
					jsonStr = jsonStr + " \"createdTimeStamp\" : \"" + createdTimeStr + "\", ";
				}
					
				jsonStr = jsonStr + " \"status\" : \"" + myConnData.status_ + "\" ";
				
				jsonStr = jsonStr + "}";
				
				iterator++ ;
	

				if( connections.size( ) > iterator )
				{
					jsonStr = jsonStr + ",";
				}

			}
				
			jsonStr = jsonStr + "]}";
		}
		else 
		{
			
			jsonStr = "{ \"result\" : \"failed\",  ";
			
			String msg = "Info::MyConnNetworkServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
		
		}
		
		System.out.println( "json = "+jsonStr);
		return jsonStr;
	}

}
