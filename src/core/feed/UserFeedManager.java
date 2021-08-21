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
package core.feed;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import utils.NotificationType;

import core.newsroom.WLMember;
import core.newsroom.WLMemberData;
import core.newsroom.WatchListData;
import core.notification.NotificationCenterData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.feed.UserFeedTable;
import db.newsroom.WLMemberMapperTable;
import db.newsroom.WatchListTable;
import db.notification.NotificationTable;
import db.regn.UserProfileTable;

/**
 * File:  UserFeedManager.java 
 *
 * Created on 22-Apr-2013 11:30:47 AM
 */
public class UserFeedManager
{

	/*
	 * Method : UserFeedManager -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public UserFeedManager()
	{
		
	}
	

	/*
	 * Method : add()
	 * 
	 * Input  : UserFeedData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to add the new user feed to supply medium for
	 * Particular user.
	 */
	public int add( UserFeedData userFeedData )
	{
		int responseCode = 0;
		
		UserFeedTable userFeedTbl = new UserFeedTable( );
		
		responseCode = userFeedTbl.insert( userFeedData );
		
		userFeedTbl = null;
		
		if( responseCode == 0 ) // User feed added successfully in SupplyMedium
		{
			UserFeedCache userFeedCache = UserFeedCache.instance( );
			
			// Adding user feed in user feed cache
			userFeedCache.put( userFeedData.userKey_, userFeedData );
			
			userFeedCache = null;
			
			addNotification( userFeedData );
			
			return 1800;
			
		}
		else // failed
		{
			return 1802;
		}
		
	}
	
	
	/*
	 * Method : remove()
	 * 
	 * Input  : user feed id and user key
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to remove the user feed from supply medium.
	 */
	public int remove( int userFeedId, UserProfileKey userKey )
	{
		int responseCode = 0;
		
		UserFeedTable userFeedTbl = new UserFeedTable( );
		
		responseCode = userFeedTbl.delete( userFeedId );
		
		userFeedTbl = null;
		
		if( responseCode == 0 )  // User Feed successfully removed from SupplyMedium
		{
			UserFeedCache userFeedCache = UserFeedCache.instance( );
			
			userFeedCache.remove( userKey, userFeedId );
			
			userFeedCache = null;
			
			return 1810;
		}
		else // failed
		{
			return 1811;
		}
		
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
		
		UserFeedCache userFeedCache = UserFeedCache.instance( );
		
		responseCode = userFeedCache.find( userKey, userFeedList, lastFeedId, count );
		
		if( responseCode == 0 ) // Needed user feeds available in cache
		{
			return 1820;
		}
		else // Needed user feeds not available in cache.
		{
			UserFeedTable userFeedTbl = new UserFeedTable( );
			
			// So fetch the 100 user feeds from database table
			responseCode = userFeedTbl.find( userKey, userFeedList, from, 100 );
			
			userFeedTbl = null;
			
			if( responseCode == 0 )  // Feeds fetched successfully from DB
			{
				System.out.println( "fetched feeds count from database ="+userFeedList.size( ) );
				
				userFeedCache.remove( userKey );
				
				// Create the sorted map for storing the user feed values
				SortedMap<Long, UserFeedData> feedMap = new TreeMap<Long, UserFeedData>(Collections.reverseOrder());
				
				// Add the values to sorted map
				for( UserFeedData userFeedData : userFeedList )
                {
					System.out.println( "Fetched feed id = "+userFeedData.userFeedId_ );
					
	                feedMap.put( new Long( userFeedData.userFeedId_ )  , userFeedData );
                }
				
				// Add the sorted map to cache
				userFeedCache.put( userKey, feedMap );
				
				return 1820;
			} 
			else  // Failed
			{
				return 1821;
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
	 * Purpose: It is used to get the lates user feeds.
	 * Feed list add to userFeedList so it is copied to caller classes.
	 */
	public int find( UserProfileKey userKey, List<UserFeedData> userFeedList,int latestFeedId )
	{
		int responseCode = 0;
		
		UserFeedCache userFeedCache = UserFeedCache.instance( );
		
		responseCode = userFeedCache.find( userKey, userFeedList,latestFeedId );
		
		if( responseCode == 0 ) // Needed user feeds available in cache
		{
			return 1820;
		}
		else // Needed user feeds not available in cache.
		{
			
			return 1821;
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
	
	
	/*
	 * Method : addNotification
	 * 
	 * Input  : UserFeedData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to add the notification for this feed. Users( who are added this user in watchlist )
	 * get the notification when this user add feed successfully
	 */
	private int addNotification( UserFeedData userFeedData )
	{
		int response = 0;
		
		WLMemberData memberData = new WLMemberData( );
		
		memberData.memberKey_ = userFeedData.userKey_;
		
		// Finding all the watchlists which user added in that
		
		List< WLMemberData > wlMembersList = new ArrayList<WLMemberData>( );
		
		WLMemberMapperTable memberMapperTable = new WLMemberMapperTable( );
		
		response = memberMapperTable.find( memberData, wlMembersList );
		
		memberMapperTable = null;
		
		NotificationTable notificationTable = new NotificationTable( );
		
		for( WLMemberData memberDataNew : wlMembersList )
        {
			
			// Finding watchlist owner details 
	        WatchListData watchListData = new WatchListData( );
	        
	        watchListData.watchListId_ = memberDataNew.watchListId_;
	        
	        WatchListTable watchListTable = new WatchListTable( );
	        
	        List<WatchListData> watchLists = new ArrayList<WatchListData>( );
	        
	        response = watchListTable.find( watchListData, watchLists );
	        
	        if( watchLists.size( ) > 0 )
	        {
	        	// Getting user profile details
	        	
	        	UserProfileData userProfileData = new UserProfileData( );
	        	
	        	UserProfileTable userProfileTable = new UserProfileTable( );
	        	
	        	userProfileTable.find( userFeedData.userKey_, userProfileData );
	        	
	        	userProfileTable = null;
	        	
	        	// Create the notification object
	        	WatchListData watchListData2 = watchLists.get( 0 );
	        	
	        	NotificationCenterData notiCenterData = new NotificationCenterData( );
	        	
	        	notiCenterData.receiverKey_ = watchListData2.ownerKey_;
	        	
	        	notiCenterData.receiverRegnKey_ = watchListData2.regnKey_;
	        	
	        	notiCenterData.senderKey_ = userFeedData.userKey_;
	        	
	        	notiCenterData.senderRegnKey_ = userFeedData.regnKey_;
	        	
	        	notiCenterData.notificationType_ = NotificationType.type.NEWSFEED.getValue( );
	        	
	        	notiCenterData.notificationTypeId_ = ""+userFeedData.userFeedId_;
	        	
	        	notiCenterData.notificationMessage_ = userProfileData.firstName_+" "+
	        	userProfileData.lastName_+" posted in Newsroom :<b>"+userFeedData.feedTitle_+"</b>";
	        	
	        	// Insert new notification
	        	notificationTable.insert( notiCenterData );
	        	
	        	notiCenterData = null;
	        	
	        	userProfileData = null;
	        }
        }
		
		notificationTable = null;
		
		return response;
		
		
	}
	
	
	
	

}
