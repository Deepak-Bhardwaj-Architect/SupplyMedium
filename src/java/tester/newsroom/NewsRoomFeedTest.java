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
import utils.ErrorMaster;

/**
 * File:  NewsRoomFeedTest.java 
 *
 * Created on 19-Sep-2013 5:14:52 PM
 */
public class NewsRoomFeedTest
{

    private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : NewsRoomFeedTest.java() -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public NewsRoomFeedTest()
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		NewsFeedController newsFeedController = new NewsFeedController( );
		
		UserProfileKey userKey = new UserProfileKey( );
		
		userKey.email_ = "smadmn1@gmail.com";
		
		List<UserFeedData> userFeedList = new ArrayList<UserFeedData>( );
		
		newsFeedController.get( userKey, userFeedList, 0, 10, 0 );
		
		errorMaster_.insert( "feed list count="+userFeedList.size( ) );
		
		for( UserFeedData userFeedData : userFeedList )
        {
	        errorMaster_.insert("userfeeedid="+userFeedData.userFeedId_);
        }
	}

}
