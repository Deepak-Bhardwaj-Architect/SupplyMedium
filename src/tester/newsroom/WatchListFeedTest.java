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
package tester.newsroom;

import java.util.ArrayList;
import java.util.List;

import core.feed.UserFeedData;
import core.regn.UserProfileKey;
import ctrl.newsroom.NewsFeedController;
import ctrl.newsroom.WatchListFeedController;

/**
 * File:  WatchListFeedTest.java 
 *
 * Created on 21-Sep-2013 4:27:29 PM
 */
public class WatchListFeedTest
{

	/*
	 * Method : WatchListFeedTest -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WatchListFeedTest()
	{
	}
	
	/*
	 * Method : fetchFeedTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to fetch the news room feeds
	 */
	public void fetchFeedTest()
	{
		WatchListFeedController newsFeedController = new WatchListFeedController( );
		
		Long watchListId = Long.parseLong( "5" );
		
		List<UserFeedData> userFeedList = new ArrayList<UserFeedData>( );
		
		newsFeedController.get( watchListId, userFeedList, 0, 10, 0 );
		
		System.out.println( "feed list count="+userFeedList.size( ) );
		
		for( UserFeedData userFeedData : userFeedList )
        {
	        System.out.println("userfeeedid="+userFeedData.userFeedId_);
        }
	}

}
