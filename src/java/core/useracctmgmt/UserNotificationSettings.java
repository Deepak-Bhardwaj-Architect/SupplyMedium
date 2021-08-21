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

import java.sql.Date;
import java.sql.Timestamp;

//import com.sun.tools.javac.resources.javac;

import core.regn.UserProfileKey;
import db.useracctmgmt.UserNotificationDefaultsTable;
import db.useracctmgmt.UserNotificationTable;

/**
 * File:  UserNotificationSettings.java 
 *
 * Created on Apr 22, 2013 10:36:58 AM
 */

/*
 * Class: UserNotificationSettings
 * 
 * Purpose: This is used to create new user notification
 * settings and update the existing notification settings
 * 
 */

public class UserNotificationSettings
{

	/*Constructor*/
	
	public UserNotificationSettings( )
	{
		
	}
	
	/*
	 * Method: create( )
	 * 
	 * Input: NotificationSettingsData
	 * 
	 * Return: int
	 * 
	 * Purpose: To get notification defaults from notification
	 * defaults and create new notificatons settings for user
	 */
	
	public int create( NotificationSettingsData notifyData )
    {
		//notifyData.show( );
		UserNotificationDefaultsTable notifyDefaultsTbl = new UserNotificationDefaultsTable( );
		
		int result = notifyDefaultsTbl.getNotificationDefaults( notifyData );
		
		if( result != 0 )
		{
			notifyDefaultsTbl = null;
			
			return 2601; //error occurred while fetching notification defaults
		}
		
		notifyDefaultsTbl = null;
		
		UserNotificationTable notifyTbl = new UserNotificationTable( );
		
		result = notifyTbl.insert( notifyData );
		
		if( result != 0)
		{
			notifyTbl = null;
			
			return 2602; //error occurred while inserting new notification
		}
		
		notifyTbl = null;
		
	    return 2600; //Inserting new notification - success
    }
	
	/*
	 * Method: update()
	 * 
	 * Input: NotificationSettingsData object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to update the existing user notification
	 * settings
	 */
	
	public int update( NotificationSettingsData notifyData )
	{
		UserNotificationTable notifyTbl = new UserNotificationTable( );
		
		int result = notifyTbl.update( notifyData );
		
		if( result != 0)
		{
			notifyTbl = null;
			
			return 2611; //Error occurred while updating existing notification
		}
		
		notifyTbl = null;
		
		return 2610; //Updating existing notification settings - success
	}
	
	
public boolean canSendNotification(UserProfileKey userKey)
{
	
	UserNotificationTable notifyTbl=new UserNotificationTable( );
	
	NotificationSettingsData notifyData=new NotificationSettingsData( );
	
	notifyData.userProfileKey_=userKey;
	
	int result=notifyTbl.find( notifyData );
	
	
	if(notifyData.notifyStopFlag_ == 1)
	{
		java.util.Date date= new java.util.Date();
		
        Timestamp currentTimestamp= new Timestamp(date.getTime());
       
		if(currentTimestamp.after( notifyData.notifyStopFromTime_) && currentTimestamp.before( notifyData.notifyStopToTime_))
		{
			return false;
		}
	}
	
	
	return true;
	
}
	
	/*
	 * Method: get
	 * 
	 * Input: NotificationSettingsData object
	 *  
	 * Return: int
	 * 
	 *  Purpose: To fetch the user notification settings data
	 */
	
	public int get( NotificationSettingsData notifySettingsData )
	{
		UserNotificationTable notifyTbl = new UserNotificationTable( );
		
		int result = notifyTbl.find( notifySettingsData );
		
		if( result != 0)
		{
			notifyTbl = null;
			
			return 2621; //Error occurred while fetching notification settings data
		}
		
		notifyTbl = null;
		
		return 2620; //Fetch - Success
	}
	
}
