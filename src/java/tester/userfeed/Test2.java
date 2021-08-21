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
package tester.userfeed;

import java.util.ArrayList;
import java.util.List;

import core.feed.UserFeedData;
import core.feed.UserFeedManager;
import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File:  Test2.java 
 *
 * Created on 02-May-2013 9:21:25 AM
 */
public class Test2
{
    private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : Test2 -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public Test2()
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	
	public void fetchFeedTest()
	{
		List<UserFeedData> userFeedList = new ArrayList<UserFeedData>( );
		
		UserFeedManager userfeedManager = new UserFeedManager( );
		
		UserProfileKey userProfileKey = new UserProfileKey( );
		
		userProfileKey.email_ = "";
		
		int result = userfeedManager.find( userProfileKey, userFeedList, 10, 10,10 );
		
		//int result = userfeedManager.update( userFeedData );
		
		//int result = userfeedManager.remove( userFeedData.userFeedId_, userFeedData.userKey_ );

		if( result == 1820)
			errorMaster_.insert( " Feed Fetched test successfully completed" );
		else 
			errorMaster_.insert( " Feed Fetched test failed." );	
	}

}
