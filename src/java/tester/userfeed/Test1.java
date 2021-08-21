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

import core.feed.UserFeedData;
import core.feed.UserFeedManager;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File:  Test1.java 
 *
 * Created on 02-May-2013 9:21:13 AM
 */
public class Test1
{
    private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : Test1 -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public Test1()
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	
	public void singleFeedInsertTest()
	{
		UserFeedData userFeedData = new UserFeedData( );
		
		userFeedData.feed_ = "This is the user feed description";
		
		userFeedData.feedTitle_ = "This is the user feed title";
		
		CompanyRegnKey regnKey = new CompanyRegnKey( );
		
		regnKey.companyPhoneNo_ = "408-408-6333";
		
		userFeedData.regnKey_ = regnKey;
		
		UserProfileKey userProfileKey = new UserProfileKey( );
		
		userProfileKey.email_ = "";
		
		userFeedData.userKey_ = userProfileKey;
		
		userFeedData.relativePath_ = "url";
		
		userFeedData.userFeedId_ = 1;
		
		
		UserFeedManager userfeedManager = new UserFeedManager( );
		
		int result = userfeedManager.add(userFeedData );
		
		if( result == 1800)
			errorMaster_.insert( "Single feed inserted test successfully completed" );
		else 
			errorMaster_.insert( "Single feed inserted test failed." );	
		
	}
	
	public void multipleFeedInsertTest()
    {

		for( int i = 100; i < 300; i++ )
        {
			try
            {
				Thread.sleep( 1000 );
            }
            catch( Exception e )
            {
	            // TODO: handle exception
            }
			
			
			UserFeedData userFeedData = new UserFeedData( );
			
			userFeedData.feed_ = "This is the user feed description"+i;
			
			userFeedData.feedTitle_ = "This is the user feed title"+i;
			
			CompanyRegnKey regnKey = new CompanyRegnKey( );
			
			regnKey.companyPhoneNo_ = "408-408-6333";
			
			userFeedData.regnKey_ = regnKey;
			
			UserProfileKey userProfileKey = new UserProfileKey( );
			
			userProfileKey.email_ = "";
			
			userFeedData.userKey_ = userProfileKey;
			
			userFeedData.relativePath_ = "url";
			
			userFeedData.userFeedId_ = 1;
			
			
			UserFeedManager userfeedManager = new UserFeedManager( );
			
			int result = userfeedManager.add(userFeedData );
			
			if( result == 1800)
				errorMaster_.insert( " Feed inserted test successfully completed" );
			else 
				errorMaster_.insert( " Feed inserted test failed." );	
			
			
        }
    }
	
	public void deleteFeedTest()
	{
		UserFeedData userFeedData = new UserFeedData( );
		
		UserProfileKey userProfileKey = new UserProfileKey( );
		
		userProfileKey.email_ = "";
		
		userFeedData.userKey_ = userProfileKey;
		
		userFeedData.userFeedId_ = 1;
		
		
		UserFeedManager userfeedManager = new UserFeedManager( );
		
		int result = userfeedManager.remove( userFeedData.userFeedId_, userFeedData.userKey_ );
		
		//int result = userfeedManager.update( userFeedData );
		
		//int result = userfeedManager.remove( userFeedData.userFeedId_, userFeedData.userKey_ );
		
		if( result == 1810)
			errorMaster_.insert( " Feed deleted test successfully completed" );
		else 
			errorMaster_.insert( " Feed deleted test failed." );	
	}

}
