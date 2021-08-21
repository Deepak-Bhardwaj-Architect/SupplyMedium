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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * File:  SMTimers.java 
 *
 * Created on 04-May-2013 2:44:46 PM
 * 
 * Purpose : This class is used to initiate the all the timers
 * which are used in SupplyMedium
 */

public class SMTimers
{

	/*
	 * Method : SMTimers -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public SMTimers()
	{
		
	}
	
	
	/*
	 * Method : executeSMTimers
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: This method is used to create the all the timers which are 
	 * used in SupplyMedium using Executor services.
	 * 
	 */
	
	public void executeSMTimers()
	{
		
		final ScheduledExecutorService socialScheduler  =
				 Executors.newScheduledThreadPool(1);

		socialScheduler.scheduleAtFixedRate( new SocialTimer( ), 1, 1, TimeUnit.MINUTES );
		 
		 
		 final ScheduledExecutorService watchDogScheduler  =
				 Executors.newScheduledThreadPool(1);

		 watchDogScheduler.scheduleAtFixedRate( new WatchDogTimer( ), 1, 1, TimeUnit.MINUTES );
	}

}
