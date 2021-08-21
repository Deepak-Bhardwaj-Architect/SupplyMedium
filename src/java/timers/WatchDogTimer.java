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


/**
 * File:  WatchDogTimer.java 
 *
 * Created on 04-May-2013 3:06:43 PM
 * 
 * Purpose : This Timer fire at every minutes. It is used to clean up 
 * unwanted data's and data reset operation. It implements the Runnable 
 * Interface. The following operation started here.
 * 
 * 1. Send the password expire remainder mail to users.
 * 2. Block the user account when User Account expire date 
 * 		and current date are same.
 */
public class WatchDogTimer implements Runnable
{
	final int ACC_TIMER_TASK_FIRE_INTERVAL  = 3*60 ;  // 3 Hours
	
	final int PASS_TIMER_TASK_FIRE_INTERVAL = 24*60 ;  // 24 Hours
	
	
	AccountTimerTask accountTimerTask_;
	
	PasswordTimerTask passTimerTask_;
	

	int accTimerTaskCounter_;
	
	int passTimerTaskCounter_;
	
	
	
	/*
	 * Method : WatchDogTimer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WatchDogTimer()
	{
		accountTimerTask_ = new AccountTimerTask( );
		
		passTimerTask_ = new PasswordTimerTask( );
		
		accTimerTaskCounter_ = 0;
		
		passTimerTaskCounter_ = 0;
	}
	
	/*
	 * Method : run
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: We implements the Runnable interface for this class. So we override this
	 * method in this class. This method is called every minute.
	 * We define the timer operation here.
	 * 
	 * 1. Send the password expire remainder mail to users.
	 * 2. Block the user account when User Account expire date 
	 * 		and current date are same.
	 */
	 public void run( )
	 {
		//errorMaster_.insert( " One minute watch dog timer " );
		
		accTimerTaskCounter_++;
		
		passTimerTaskCounter_++;
		
		 if( accTimerTaskCounter_ == ACC_TIMER_TASK_FIRE_INTERVAL )
		 {
			 accTimerTaskCounter_ = 0;
			 
			 Thread accThread = new Thread( new AccountTimerTask( ) );
			 
			 accThread.start( );
		 }
		 
		 if( passTimerTaskCounter_ == PASS_TIMER_TASK_FIRE_INTERVAL )
		 {
			 passTimerTaskCounter_ = 0;
			 
			 Thread passThread = new Thread( new PasswordTimerTask( ) );
			 
			 passThread.start( );
		 }
	 }

}
