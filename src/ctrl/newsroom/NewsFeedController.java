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

import javax.servlet.http.HttpServletRequest;

import core.feed.UserFeedData;
import core.newsroom.NewsFeed;
import core.regn.UserProfileKey;


/**
 * File:  NewsFeedController.java 
 *
 * Created on 19-Sep-2013 12:22:00 PM
 */
public class NewsFeedController
{

	/*
	 * Method : NewsFeedController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public NewsFeedController()
	{
	}
	

	/*
	 * Method : processRequest()
	 * 
	 * Input  : HTTPServletRequest and list of UserFeedData objects 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to process the news feed request. It get the request from servlet
	 * and do the appropriate action.
	 */
	public int processRequest( HttpServletRequest request, List<UserFeedData> userFeedList )
	{
		int result = 0;
		
		String requestType = request.getParameter( "RequestType" );
		
		UserProfileKey userKey = new UserProfileKey( );
		
		 if( requestType.equals( "FetchFeed" ) ) // Process the fetch feed request
		{
			userKey.email_  =  request.getParameter( "UserKey" );
			 
			int from = Integer.parseInt( request.getParameter( "StartsFrom" ) );
			
			int count = Integer.parseInt( request.getParameter( "Count" ) );
			
			int lastFeedId = Integer.parseInt( request.getParameter( "LastFeedId" ) );
			
			System.out.println( "Fetch user feeds from "+from+" to "+from+count+" lastFeedId="+lastFeedId );
					
			result = get( userKey, userFeedList, from, count, lastFeedId );
			
			
		}
		else if( requestType.equals( "LatestFetchFeed" ) ) // Process the latest fetch feed request
		{

			userKey.email_  =  request.getParameter( "UserKey" );
			
			int latestFeedId = Integer.parseInt( request.getParameter( "LatestFeedId" ) );
					
			result = get( userKey, userFeedList, latestFeedId );
			
		}
		
				
		
			
		return result;
	}
	
	
	/*
	 * Method : get
	 * 
	 * Input  : user key, list of user feeds, feed starts from and how many feeds
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the user feeds. Using from and count parameters we filter the 
	 * feeds. Feed list add to userFeedList so it is copied to caller classes.
	 */
	
	public int get( UserProfileKey userKey, List<UserFeedData> userFeedList,int from,int count,int lastFeedId )
	{
		int result = 0;
		
		NewsFeed newsFeed = new NewsFeed( );
		
		result = newsFeed.find( userKey, userFeedList, from, count, lastFeedId );
		
		newsFeed	= null;
		
		return result;
	}
	
	
	/*
	 * Method : get()
	 * 
	 * Input  : user key, list of user feeds, and latest feed id
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the latest user feeds.
	 * Feed list add to userFeedList so it is copied to caller classes.
	 */
	public int get( UserProfileKey userKey, List<UserFeedData> userFeedList,int latestFeedId )
	{
		int result = 0;
		
		NewsFeed newsFeed = new NewsFeed( );
		
		result = newsFeed.find( userKey, userFeedList,latestFeedId );
		
		newsFeed	= null;
		
		return result;
	}
}
