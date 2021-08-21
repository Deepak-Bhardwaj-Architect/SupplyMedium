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
import utils.ErrorMaster;

/**
 * File: UserNotificationDefaultsRecord.java
 * 
 * Created on Apr 22, 2013 2:19:38 PM
 */

// This DB record class is related to user_notifications_defaults table in db

public class UserNotificationDefaultsRecord
{
	public int       notifyWorkingHoursFlag_;
	public int       notifyNonWorkingHoursFlag_;

	public String    notifyWorkingHoursMode_;
	public String    notifyNonWorkingHoursMode_;

	public int       notifyStopFlag_;

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
           ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "notifyWorkingHoursFlag_	= " + notifyWorkingHoursFlag_ );
		errorMaster_.insert( "notifyNonWorkingHoursFlag_	= " + notifyNonWorkingHoursFlag_ );

		errorMaster_.insert( "notifyWorkingHoursMode_	= " + notifyWorkingHoursMode_ );
		errorMaster_.insert( "notifyNonWorkingHoursMode_	= " + notifyNonWorkingHoursMode_ );

		errorMaster_.insert( "notifyStopFlag_	= " + notifyStopFlag_ );

		errorMaster_.insert( "notifyStopFromTime_	= " + notifyStopFromTime_ );
		errorMaster_.insert( "notifyStopToTime_		= " + notifyStopToTime_ );
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
		notifyNonWorkingHoursFlag_ = 0;
		notifyNonWorkingHoursFlag_ = 0;

		notifyWorkingHoursMode_ 	= "";
		notifyNonWorkingHoursMode_ 	= "";

		notifyStopFlag_ = 0;

		notifyStopFromTime_ = null;
		notifyStopToTime_ 	= null;
	}
}
