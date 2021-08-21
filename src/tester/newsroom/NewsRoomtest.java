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

/**
 * File:  NewsFeedTest.java 
 *
 * Created on 02-Sep-2013 3:59:39 PM
 */
public class NewsRoomtest
{

	/*
	 * Method : NewsFeedTest -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public NewsRoomtest()
	{
		
	}
	
	public void execute()
    {
		// Watch list management
		WatchListTest watchListTest = new WatchListTest( );
		
		//watchListTest.addTest( );
		
		//watchListTest.deleteTest( );
		
		//watchListTest.fetchTest( );
		
		watchListTest = null;
		
		
		
		// Watchlist members management
		WLMemberTest wlMemberTest = new WLMemberTest( );
		
		//wlMemberTest.addMembersTest( );
		
		//wlMemberTest.deleteMembersTest( );
		
		//wlMemberTest.getMembersTest( );
		
		wlMemberTest = null;
		
		
		
		
		// News room feed test
		
		NewsRoomFeedTest newsRoomFeedTest = new NewsRoomFeedTest( );
		
		//newsRoomFeedTest.fetchFeedTest( );
		
		newsRoomFeedTest = null;
		
		
		

		// WatchList feed test
		
		WatchListFeedTest watchListFeedTest = new WatchListFeedTest( );
		
		watchListFeedTest.fetchFeedTest( );
		
		watchListFeedTest = null;
		
		
	    
    }

}
