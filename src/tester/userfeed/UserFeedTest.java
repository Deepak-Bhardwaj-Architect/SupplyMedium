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

/**
 * File:  MainTest.java 
 *
 * Created on 02-May-2013 9:20:51 AM
 */
public class UserFeedTest
{

	/*
	 * Method : MainTest -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public UserFeedTest()
	{
		
	}
	
	public void execute()
	{
		//Test1 test1 = new Test1( );
		
		//test1.singleFeedInsertTest( );
		
		//test1.multipleFeedInsertTest( );
		
		//test1.deleteFeedTest( );
		
		
		
		//Test2 test2 = new Test2( );
		
		//test2.fetchFeedTest( );
		

		(new Thread(new UserFeedTesterThread1())).start();
		(new Thread(new UserFeedTesterThread2())).start();
		(new Thread(new UserFeedTesterThread3())).start();
		(new Thread(new UserFeedTesterThread4())).start();
		
	}

}
