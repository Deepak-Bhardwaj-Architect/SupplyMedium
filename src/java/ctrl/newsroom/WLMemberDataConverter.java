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
package ctrl.newsroom;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import core.newsroom.WLMemberData;

/**
 * File:  WLMemberDataConverter.java 
 *
 * Created on 30-Aug-2013 6:10:06 PM
 */
public class WLMemberDataConverter
{

	/*
	 * Method : WLMemberDataConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WLMemberDataConverter()
	{
		
	}
	
	/*
	 * Method : convert 
	 * 
	 * Input  : HttpServletRequest object, list of WLMemberData object
	 * 
	 * Return : int - success/fail
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to list of WLMemberdata
	 * And copied to members parameter so it available in caller classes.
	 * 
	 */
	public int convert( HttpServletRequest request, List<WLMemberData> members )
    {
		Map<String, String [ ]> reqMap = request.getParameterMap( );

		/* WatchList details */
		
		if( reqMap.containsKey( "MembersJSON" ) )
		{
			String wlMembersJson = request.getParameter("MembersJSON").toString();
			
			JsonParser jsonParser = new JsonParser( );

			JsonObject wlMembersJSONData = (JsonObject)jsonParser.parse( wlMembersJson );


			JsonArray wlMembersArr = wlMembersJSONData.get( "members" ).getAsJsonArray( );
		
			for( JsonElement jsonele : wlMembersArr )
			{

				JsonObject wlMemberJSONData = (JsonObject)jsonele;
				
				
				// Converting JSON Data to strings
				String userKeyStr = wlMemberJSONData.get( "userKey" ).getAsString( );

				String watchListId = wlMemberJSONData.get( "watchListId" ).getAsString( );

			
				// Converting strings to WatchListData object
				WLMemberData wlMemberData = new WLMemberData( );
				
				wlMemberData.memberKey_.email_ = userKeyStr;
				
				wlMemberData.watchListId_ = Integer.parseInt( watchListId );
				
				members.add( wlMemberData );
				
				wlMemberData = null;
				
			}
			
		}
		
		 return 0;
		
    }

}
