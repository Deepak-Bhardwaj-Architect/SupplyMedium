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
package db.useracctmgmt;

import java.sql.Timestamp;

/**
 * File:  UserNotificationRecord.java 
 *
 * Created on Apr 22, 2013 2:17:50 PM
 */

//This DB record class is related to user_notifications_defaults table in db

public class UserNotificationRecord
{
	public String userRelKey_;
	public String notifyEmail_;
	public String notifyMobile_;
	
	public int notifyWorkingHoursFlag_;
	public int notifyNonWorkingHoursFlag_;
	
	public String notifyWorkingHoursMode_;
	public String notifyNonWorkingHoursMode_;
	
	public int notifyStopFlag_;
	
	public Timestamp notifyStopFromTime_;
	public Timestamp notifyStopToTime_;
	

	/*
	 * Method : show( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */
	
	public void show( )
	{
		System.out.println( "userRelKey_	= " + userRelKey_ );
		System.out.println( "notifyEmail_	= " + notifyEmail_ );
		System.out.println( "notifyMobile_	= " + notifyMobile_ );
		
		System.out.println( "notifyWorkingHoursFlag_	= " + notifyWorkingHoursFlag_ );
		System.out.println( "notifyNonWorkingHoursFlag_	= " + notifyNonWorkingHoursFlag_ );

		System.out.println( "notifyWorkingHoursMode_	= " + notifyWorkingHoursMode_ );
		System.out.println( "notifyNonWorkingHoursMode_	= " + notifyNonWorkingHoursMode_ );

		System.out.println( "notifyStopFlag_	= " + notifyStopFlag_ );

		System.out.println( "notifyStopFromTime_	= " + notifyStopFromTime_ );
		System.out.println( "notifyStopToTime_		= " + notifyStopToTime_ );
	}
	
	/*
	 * Method : clear( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		userRelKey_	= "";
		notifyEmail_ = "";
		notifyMobile_ = "";
		
		notifyNonWorkingHoursFlag_ = 0;
		notifyNonWorkingHoursFlag_ = 0;

		notifyWorkingHoursMode_ 	= "";
		notifyNonWorkingHoursMode_ 	= "";

		notifyStopFlag_ = 0;

		notifyStopFromTime_ = null;
		notifyStopToTime_ 	= null;
	}
}
