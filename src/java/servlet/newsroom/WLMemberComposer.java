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
package servlet.newsroom;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import utils.JSONEncode;

import com.google.gson.Gson;

import core.newsroom.WLMemberData;
import core.newsroom.WatchListData;
import utils.ErrorMaster;

/**
 * File:  WLMemberComposer.java 
 *
 * Created on 30-Aug-2013 6:35:52 PM
 */
public class WLMemberComposer
{

	/*
	 * Method : WLMemberComposer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WLMemberComposer()
	{
	}
	
	/*
	 * Method: composeMembersJSON
	 * 
	 * Input: int response, List of WLMemberData
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the members list object to json string
	 */
	
	public String composeMembersJSON( int responseCode, List<WLMemberData> members )
	{
		String jsonString = "";

		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );
                ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );

		if( responseCode == 10130 || responseCode == 10140 || responseCode == 10150  )
		{
			String servletMsg = "Info::WLMemberServlet.doPost() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			// errorMaster_.insert( "response code="+responseCode );

			if( responseCode == 10130 || responseCode == 10140)
			{
				jsonMap.put( "result", "success" );

				String responseString = ErrorCodeConfigReader.instance( ).get(
				        Integer.toString( responseCode ) );

				jsonMap.put( "message", responseString );

				jsonString = new Gson( ).toJson( jsonMap );

				jsonMap = null;

				errorMaster_.insert( "json str=" + jsonString );

				return jsonString;

			}

			else
			{
				// errorMaster_.insert( "response code="+responseCode );

				int membersCount = members.size( );

				jsonString = "{ \"result\" : \"success\",  ";

				jsonString = jsonString + "\"members\" : [";

				for( int i = 0; i < membersCount; i++ )
				{
					WLMemberData memberData = members.get( i );

					if( i != 0 ) jsonString = jsonString + ",";

					jsonString = jsonString + "{ \"memberName\" : \""
					        + memberData.memberName_ + "\", ";
					
					jsonString = jsonString + " \"memberKey\" : \""
					        + memberData.memberKey_ + "\", ";
					
					jsonString = jsonString + " \"photoPath\" : \""
					        + memberData.photoPath_ + "\", ";
					
					jsonString = jsonString + " \"companyName\" : \""
					        + memberData.companyName_ + "\", ";

					jsonString = jsonString + " \"regnKey\" : \""
					        + memberData.regnKey_+ "\", ";

					jsonString = jsonString + " \"watchListId\" : \""
					        + memberData.watchListId_+ "\" ";


					jsonString = jsonString + "}";

				}

				jsonString = jsonString + "]}";
				
				errorMaster_.insert( "json str=" + jsonString );

				return jsonString;

			}

		}
		else
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::WLMemberServlet.doPost() - "
			        + "Request failed - Error code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );

			jsonMap.put( "message", responseString );

			jsonString = new Gson( ).toJson( jsonMap );

			jsonMap = null;

			errorMaster_.insert( "json str=" + jsonString );

			return jsonString;

		}
	}

}
