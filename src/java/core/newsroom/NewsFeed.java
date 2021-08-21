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
import core.myconn.MyConnNetwork;
import core.myconn.MyConnectionData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.feed.UserFeedTable;
import db.regn.UserProfileTable;
import utils.ErrorMaster;

/**
 * File:  NewsFeed.java 
 *
 * Created on 19-Sep-2013 12:22:21 PM
 */




public class NewsFeed
{

    private static ErrorMaster errorMaster_ = null;


	/*
	 * Method : NewsFeed -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public NewsFeed()
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	

	
	/*
	 * Method : find()
	 * 
	 * Input  : user key, list of user feeds, feed starts from and how many feeds
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the user feeds. Using from and count parameters we filter the 
	 * feeds. Feed list add to userFeedList so it is copied to caller classes.
	 */
	public int find( UserProfileKey userKey, List<UserFeedData> userFeedList,int from,int count,int lastFeedId )
	{
		int responseCode = 0;
		
		NewsFeedCache newsFeedCache = NewsFeedCache.instance( );
		
		responseCode = newsFeedCache.find( userKey, userFeedList, lastFeedId, count );
		
		if( responseCode == 0 ) // Needed user feeds available in cache
		{
			return 10160;
		}
		else // Needed user feeds not available in cache.
		{
			
			// Fetching user's connections
			
			MyConnNetwork myConnNetwork = new MyConnNetwork( );
			
			List<MyConnectionData> connections = new ArrayList<MyConnectionData>( );
			
			int result = myConnNetwork.getConnection( userKey, connections );
			
			myConnNetwork = null;
			
			if( result != 10030 )
				return 10161;
			
			List<UserProfileKey> userKeys = new ArrayList<UserProfileKey>( );
			
			// including user's feed also.
			userKeys.add( userKey );
			
			for( MyConnectionData myConnectionData : connections )
                        {
                                userKeys.add( myConnectionData.toUserKey_ );
                        }
			
			
			
			UserFeedTable userFeedTbl = new UserFeedTable( );
			
			// So fetch the 100 user feeds for each user from database table
			responseCode = userFeedTbl.find( userKeys, userFeedList, from, 100 );
			
			userFeedTbl = null;
			
			if( responseCode == 0 )  // Feeds fetched successfully from DB
			{
				errorMaster_.insert( "fetched feeds count from database ="+userFeedList.size( ) );
				
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
				
				newsFeedCache.remove( userKey );
				
				// Create the sorted map for storing the user feed values
				SortedMap<Long, UserFeedData> feedMap = new TreeMap<Long, UserFeedData>(Collections.reverseOrder());
				
				// Add the values to sorted map
				for( UserFeedData userFeedData : userFeedList )
                {
					errorMaster_.insert( "Fetched feed id = "+userFeedData.userFeedId_ );
					
	                feedMap.put( new Long( userFeedData.userFeedId_ )  , userFeedData );
                }
				
				// Add the sorted map to cache
				newsFeedCache.put( userKey, feedMap );
				
				return 10160;
			} 
			else  // Failed
			{
				return 10161;
			}
		}
		
	}
	
	
	/*
	 * Method : find()
	 * 
	 * Input  : user key, list of user feeds, and latest feed id
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the latest user feeds.
	 * Feed list add to userFeedList so it is copied to caller classes.
	 */
	public int find( UserProfileKey userKey, List<UserFeedData> userFeedList,int latestFeedId )
	{
		int responseCode = 0;
		// Fetching user's connections

		MyConnNetwork myConnNetwork = new MyConnNetwork( );

		List<MyConnectionData> connections = new ArrayList<MyConnectionData>( );

		int result = myConnNetwork.getConnection( userKey, connections );

		myConnNetwork = null;

		if( result != 10030 ) return 10171;

		List<UserProfileKey> userKeys = new ArrayList<UserProfileKey>( );

		// including user's feed also.
		userKeys.add( userKey );

		for( MyConnectionData myConnectionData : connections )
		{
			userKeys.add( myConnectionData.toUserKey_ );
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
			
			return 10170;
		}
		else // Needed user feeds not available in cache.
		{
			
			return 10171;
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
