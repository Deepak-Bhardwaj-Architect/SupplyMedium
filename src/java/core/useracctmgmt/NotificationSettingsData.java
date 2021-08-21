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
package core.useracctmgmt;

import java.sql.Timestamp;

import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File: NotificationSettingsData.java
 * 
 * Created on Apr 22, 2013 12:47:41 PM
 */

/*
 * Class: NotificationSettingsData
 * 
 * Purpose: This class contains the domain variables for notification settings.
 * All the variables are initialized here.
 */

public class NotificationSettingsData
{
	public UserProfileKey userProfileKey_;
	
	/* This is the email address where the notifications will be sent to user.
	 * User will have an option 'Use default email address for notifications'
	 * If this option is checked, then the default email will be the notifyEmail_.
	 * Else, the user entered email address will be the notifyEmail_
	 */
	public String         notifyEmail_;
	
	/*
	 * This is the mobile number through which all the sms notifications will
	 * be sent to the user
	 */
	public String         notifyMobile_;

	/*
	 * This is a flag. Instead of saving it as boolean, this is used a tiny int 
	 * If this flag is set, then all the notifications will be sent through
	 * email / sms (as specified by user) during working hours, as set by
	 * the user using working hour settings. 
	 */
	public int            workingHoursFlag_;
	
	
	/*
	 * This is a flag. Instead of saving it as boolean, this is used a tiny int
	 * If this flag is set, then all the notificaitons will be sent through
	 * email / sms (as specified by user)  other than the working hours specified
	 * by the user in working hour settings.
	 */
	public int            nonWorkingHoursFlag_;
	
	/*
	 * The values of this string are "EMAIL", "SMS", "BOTH"
	 * If "EMAIL" is set, then notifications during working hours 
	 * will be sent to email
	 * 
	 * If "SMS" is set, then notifications during working hours
	 * will be sent to SMS
	 * 
	 * IF "BOTH" is set, then notifications during working hours
	 * will be sent to both email and sms
	 */
	public String         notifyWhMode_;

	/*
	 * The values of this string are "EMAIL", "SMS", "BOTH"
	 * If "EMAIL" is set, then notifications during non-working hours 
	 * will be sent to email
	 * 
	 * If "SMS" is set, then notifications during non-working hours
	 * will be sent to SMS
	 * 
	 * IF "BOTH" is set, then notifications during non-working hours
	 * will be sent to both email and sms
	 */
	public String         notifyNonWhMode_;
	
	/*
	 * This is a flag. Instead of saving it as boolean, this is used a tiny int
	 * If this flag is set, then all notificaitons will be stopped from sending
	 * to email and sms
	 */
	public int            notifyStopFlag_;

	/*
	 * If notifyStopFlag_ is set, then the notifications through sms / email will be
	 * stopped as specified between notifyStopFromTime_ and notifyStopToTime_ 
	 */
	public Timestamp      notifyStopFromTime_;
	public Timestamp      notifyStopToTime_;
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method: NotificagtionSettingsData() -- Constructor
	 * 
	 * Input: none
	 * 
	 * Return: none
	 * 
	 * Purpose: This method intializes the UserProfileKey object
	 */

	public NotificationSettingsData()
	{
		userProfileKey_ = new UserProfileKey( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}

	/*
	 * Method: show()
	 * 
	 * Input: none
	 * 
	 * Return: void
	 * 
	 * Purpose: This method is used to print all the domain variables in console
	 */

	public void show( )
	{
		errorMaster_.insert( "userProfileKey_ 	= " + userProfileKey_.email_ );
		errorMaster_.insert( "notifyEmail_ 		= " + notifyEmail_ );
		errorMaster_.insert( "notifyMobile_ 		= " + notifyMobile_ );
		
		errorMaster_.insert( "workingHoursFlag_ 	= " + workingHoursFlag_ );
		errorMaster_.insert( "nonWorkingHoursFlag_ 	= " + nonWorkingHoursFlag_ );
		errorMaster_.insert( "notifyWhMode_ 			= " + notifyWhMode_ );

		errorMaster_.insert( "notifyNonWhMode_ 	= " + notifyNonWhMode_ );
		errorMaster_.insert( "notifyStopFlag_ 	= " + notifyStopFlag_ );
		errorMaster_.insert( "notifyStopFromTime_ 	= " + notifyStopFromTime_ );
		
		errorMaster_.insert( "notifyStopToTime_ 		= " + notifyStopToTime_ );
	}

	/*
	 * Method: clear()
	 * 
	 * Input: none
	 * 
	 * Return: void
	 * 
	 * Purpose: This method is used to clear/null all domain variables
	 */

	public void clear( )
	{
		userProfileKey_ = null;
		notifyEmail_ 	= "";
		notifyMobile_ 	= "";

		workingHoursFlag_ 	= 0;
		nonWorkingHoursFlag_= 0;
		notifyWhMode_ 		= "";

		notifyNonWhMode_ 	= "";
		notifyStopFlag_ 	= 0;

		notifyStopFromTime_ = null;
		notifyStopToTime_ 	= null;
	}

}
