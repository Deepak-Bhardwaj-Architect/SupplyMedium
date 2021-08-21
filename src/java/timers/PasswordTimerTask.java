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

import core.login.PasswordExpireRemainder;

/**
 * File:  PasswordTimerTask.java 
 *
 * Created on 04-May-2013 3:19:27 PM
 * 
 * Purpose : This timer task called at every 12 hours from
 * WatchDogTimer. 
 */
public class PasswordTimerTask implements Runnable
{

	/*
	 * Method : PasswordTimerTask -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 * 
	 */
	
	public PasswordTimerTask()
	{
	}
	
	/*
	 * Method : run
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: This method will be called for every 12 hours and checks
	 * whether the user's password is going to expire in 'n' days.
	 * 
	 * If password expires in 'n' days (as configured in account policies),
	 * then mail will be sent to the user on the 'n'th day
	 * 
	 * If daily remainder is checked, then email will be sent for everyday
	 * from the nth day, including nth day
	 * 
	 */
	
	 public void run( )
	 {
		// errorMaster_.insert( " password timer task timer " );
		 
		 PasswordExpireRemainder obj = new PasswordExpireRemainder( );
		 
		 obj.remaind( );
		 
		// errorMaster_.insert( "Task complete" );
	 }

}
