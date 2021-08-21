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
 * File:  AccountTimerTask.java 
 *
 * Created on 04-May-2013 3:19:47 PM
 * 
 * Purpose :This timer task called at every 3 hours from
 * WatchDogTimer. 
 */
public class AccountTimerTask implements Runnable
{

	/*
	 * Method : AccountTimerTask -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 * 
	 */
	
	public AccountTimerTask()
	{
	}
	
	
	/*
	 * Method : run
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: This method is used to check whether the account is
	 * expired.  This will check user's account expiration config value
	 * and compares with the current date.  If the condition satisfies,
	 * then the account's status will be changed to block.
	 * 
	 */
	
	 public void run( )
	 {
		 //System.out.println( " Account timer task timer " );
	 }

}
