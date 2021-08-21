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
package core.newsroom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import core.feed.UserFeedCache;
import core.feed.UserFeedData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.feed.UserFeedTable;
import db.regn.UserProfileTable;

/**
 * File:  WatchListFeed.java 
 *
 * Created on 21-Sep-2013 2:49:19 PM
 */
public class WatchListFeed
{

	/*
	 * Method : WatchListFeed -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WatchListFeed()
	{
	}
	
	/*
	 * Method : find()
	 * 
	 * Input  : watchListId, list of user feeds, feed starts from and how many feeds
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the watchlist users feeds. Using from and count parameters we filter the 
	 * feeds. Feed list add to userFeedList so it is copied to caller classes.
	 */
	public int find( long watchListId, List<UserFeedData> userFeedList,int from,int count,int lastFeedId )
	{
		int responseCode = 0;
		
		WatchListFeedCache newsFeedCache = WatchListFeedCache.instance( );
		
		responseCode = newsFeedCache.find( watchListId, userFeedList, lastFeedId, count );
		
		if( responseCode == 0 ) // Needed watchlist user's feeds available in cache
		{
			return 10170;
		}
		else // Needed watchlist user's feeds not available in cache.
		{
			
			// Fetching watchlist users
			
			WLMember wlMember = new WLMember( );
			
			List<WLMemberData> members = new ArrayList<WLMemberData>( );
			
			int result = wlMember.get( watchListId, members );
			
			wlMember = null;
			
			if( result != 10150 )
				return 10181;
			
			List<UserProfileKey> userKeys = new ArrayList<UserProfileKey>( );
			
			
			for( WLMemberData wlMemberData : members )
            {
	            userKeys.add( wlMemberData.memberKey_ );
	        }
			
			
			
			UserFeedTable userFeedTbl = new UserFeedTable( );
			
			// So fetch the 10 user feeds for each user from database table
			responseCode = userFeedTbl.find( userKeys, userFeedList, from, 100 );
			
			userFeedTbl = null;
			
			if( responseCode == 0 )  // Feeds fetched successfully from DB
			{
				System.out.println( "fetched feeds count from database ="+userFeedList.size( ) );
				
				UserProfileTable profileTable = new UserProfileTable( );
				// This needs to be optimized
				for( UserFeedData userFeedData : userFeedList )
	            {
					UserProfileData userProfileData = new UserProfileData( );
					
		            profileTable.getUserProfile( userFeedData.userKey_, userProfileData );
		            
		            userFeedData.userName_ = userProfileData.firstName_+" "+userProfileData.lastName_;
		            
		            userFeedData.userProfilePicPath_ = userProfileData.profilePicture_;
	            }
				
				profileTable = null;
				
				newsFeedCache.remove( watchListId );
				
				// Create the sorted map for storing the user feed values
				SortedMap<Long, UserFeedData> feedMap = new TreeMap<Long, UserFeedData>(Collections.reverseOrder());
				
				// Add the values to sorted map
				for( UserFeedData userFeedData : userFeedList )
                {
					System.out.println( "Fetched feed id = "+userFeedData.userFeedId_ );
					
	                feedMap.put( new Long( userFeedData.userFeedId_ )  , userFeedData );
                }
				
				// Add the sorted map to cache
				newsFeedCache.put( watchListId
						, feedMap );
				
				return 10180;
			} 
			else  // Failed
			{
				return 10181;
			}
		}
		
	}
	
	
	/*
	 * Method : find()
	 * 
	 * Input  : watch list id, list of user feeds, and latest feed id
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the latest user feeds from the given watchlist.
	 * Feed list add to userFeedList so it is copied to caller classes.
	 */
	public int find( long watchListId, List<UserFeedData> userFeedList,int latestFeedId )
	{
		int responseCode = 0;
		
		// Fetching watchlist users
		
		WLMember wlMember = new WLMember( );
					
		List<WLMemberData> members = new ArrayList<WLMemberData>( );
					
		int result = wlMember.get( watchListId, members );
					
		wlMember = null;
					
		if( result != 10150 )
				return 10191;
					
		List<UserProfileKey> userKeys = new ArrayList<UserProfileKey>( );
					
					
		for( WLMemberData wlMemberData : members )
		{
		      userKeys.add( wlMemberData.memberKey_ );
		}
					
		UserFeedTable userFeedTbl = new UserFeedTable( );

		// So fetch the 100 user feeds for each user from database table
		responseCode = userFeedTbl.find( userKeys, userFeedList, latestFeedId );

		userFeedTbl = null;
					
		
		if( responseCode == 0 ) // Needed user feeds available in cache
		{
			UserProfileTable profileTable = new UserProfileTable( );
			// This needs to be optimized
			for( UserFeedData userFeedData : userFeedList )
            {
				UserProfileData userProfileData = new UserProfileData( );
				
	            profileTable.getUserProfile( userFeedData.userKey_, userProfileData );
	            
	            userFeedData.userName_ = userProfileData.firstName_+" "+userProfileData.lastName_;
	            
	            userFeedData.userProfilePicPath_ = userProfileData.profilePicture_;
            }
			
			profileTable = null;
			
			return 10190;
		}
		else // Needed user feeds not available in cache.
		{
			
			return 10191;
		}
		
	}
	
	
	/*
	 * Method : update()
	 * 
	 * Input  : UserFeedData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update user feed to supply medium for
	 * Particular feed id.
	 */
	public int update( UserFeedData userFeedData )
	{
		int responseCode = 0;
		
		UserFeedTable userFeedTbl = new UserFeedTable( );
		
		responseCode = userFeedTbl.update( userFeedData );
		
		userFeedTbl = null;
		
		if( responseCode == 0 ) // User feed updated successfully in SupplyMedium
		{
			UserFeedCache userFeedCache = UserFeedCache.instance( );
			
			// Adding user feed in user feed cache
			userFeedCache.put( userFeedData.userKey_, userFeedData );
			
			return 1830;
			
		}
		else // failed
		{
			return 1831;
		}
		
	}

}
