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
package timers;


import core.chat.ChatUserCache;
import core.feed.UserFeedCache;

/**
 * File:  SocialTimer.java 
 *
 * Created on 04-May-2013 2:52:48 PM
 * 
 * Purpose : This Timer fire at every minutes. It is used to clean up the 
 * Social part datas. It implements the Runnable Interface
 * 1. It Remove the idle user feeds from UserFeedCache
 * 2. It refresh the ChatUserCache 
 */
public class SocialTimer implements Runnable
{

	/*
	 * Method : SocialTimer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public SocialTimer()
	{
		
	}
	
	
	/*
	 * Method : run
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: We implements the Runnable interface for this class. So we override this
	 * method in this class.
	 * We define the timer operation here.
	 * 1.It remove the idle user feeds from UserFeedCache
	 * 2. Refresh the ChatUserCache
	 */
	 public void run( )
	 {
		 
		 // It remove the idle user feeds from UserFeedCache
		 
		UserFeedCache userFeedCache = UserFeedCache.instance( );

		userFeedCache.cleanCache( );
		
		

		// Refresh the ChatUserCache
		
		ChatUserCache chatUserCache = ChatUserCache.instance( );

		chatUserCache.refresh( );
		
	 }
    

}
