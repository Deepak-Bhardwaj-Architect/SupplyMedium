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

package ctrl.useracctmgmt;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.regn.UserProfileData;
import core.useracctmgmt.NotificationSettingsData;
import core.useracctmgmt.PasswordResetData;
import core.useracctmgmt.UserAcctManager;
import core.useracctmgmt.UserAcctMgmtData;
import core.useracctmgmt.WorkingHoursData;

/**
 * File: UserAcctMgmtController.java
 * 
 * Created on Apr 22, 2013 10:20:58 AM
 */

/*
 * Class: UserAcctMgmtController
 * 
 * Purpose: This class controls the user acct mgmt operations like 1. Reset
 * password (Through password history), 2. Update user profile, 3. Controls User
 * Notification settings, 4. Controls User Working hours settings,
 * 
 * based on the following request types
 * 
 * 1. ResetPassword 2. UpdateProfilePic 3. UpdateNotificationSettings 
 * 4. UpdateWorkingHours 5. GetWorkingHours 6. GetNotificationSetttings
 * 7. GetProfilePic 
 */

public class UserAcctMgmtController
{

	/*
	 * Method: manageUserAccount
	 * 
	 * Input: HttpServletRequest object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method parses the request object and fills user account
	 * management object, which contains, PasswordResetData,
	 * NotificationsSettingsData, WorkingHoursData, UserProfileData.
	 * 
	 * Based on the request types, the calls to various classes will be made.
	 */

	public int manageUserAccount( HttpServletRequest request,
	        UserAcctMgmtData userAcctMgmtData )
	{
		// RequestType is "ResetPassword

		ErrorLogger errLogger_ = ErrorLogger.instance( );

		UserAcctMgmtDataConverter converter = new UserAcctMgmtDataConverter( );

		int result = converter.parseRequest( request, userAcctMgmtData );

		if( result != 0 )
		{
			errLogger_.logMsg( "Error::UserAcctMgmtController.manageUserAccount( ) - Failed to parse request "
			                + "for user <" + request.getParameter( "UserKey" ) + ">" );

			return 2699; // Failed to parse request object
		}

		String requestType = request.getParameter( "RequestType" );

		if( requestType.equals( "ResetPassword" ) )
		{
			errLogger_.logMsg( "Info::UserAcctMgmtController.manageUserAccount( ) - Reset Password Request in Process "
			                + "for user <" + request.getParameter( "UserKey" + ">" ) );

			return passwordReset( userAcctMgmtData.passResetData_, false );
		}
		else if( requestType.equals( "ChangePassword" ) ) 
		{
			errLogger_.logMsg( "Info::UserAcctMgmtController.manageUserAccount( ) - Change Password Request in Process "
	                + "for user <" + request.getParameter( "UserKey" + ">" ) );

			return passwordReset( userAcctMgmtData.passResetData_, true );
		}
		
		else if( requestType.equals( "UpdateProfilePic" ) )
		{
			errLogger_.logMsg( "Info::UserAcctMgmtController.manageUserAccount( ) - Update Profile Settings"
			                + "Request in Process for user <"
			                + request.getParameter( "UserKey" )
			                + ">" );

			return updateProfilePic( userAcctMgmtData.userProfileData_ );
		}
		else if( requestType.equals( "UpdateNotificationSettings" ) )
		{
			errLogger_.logMsg( "Info::UserAcctMgmtController.manageUserAccount( ) - Update Notification Settings"
			                + "Request in Process for user <"
			                + request.getParameter( "UserKey" )
			                + ">" );

			return updateNotifySettings( userAcctMgmtData.notifySettingsData_ );
		}
		else if( requestType.equals( "UpdateWorkingHours" ) )
		{
			errLogger_.logMsg( "Info::UserAcctMgmtController.manageUserAccount( ) - Update Working Hours Request " +
					"in Process for user <" + request.getParameter( "Userkey" ) + ">" );
			
			return updateWh( userAcctMgmtData.workingHrsData_ );
		}
		else if( requestType.equals( "GetProfilePicture" ) )
		{
			errLogger_.logMsg( "Info::UserAcctMgmtController.manageUserAccount( ) - Fetch Profile Pic Request " +
					"in Process for user <" + request.getParameter( "Userkey" ) + ">" );
			
			return getProfile( userAcctMgmtData.userProfileData_ );
		}
		else if( requestType.equals( "GetNotificationSettings" ) )
		{
			errLogger_.logMsg( "Info::UserAcctMgmtController.manageUserAccount( ) - Fetch Notification Settings" +
					" Request in Process for user <" + request.getParameter( "Userkey" ) + ">" );
			
			return getNotifyData( userAcctMgmtData.notifySettingsData_ );
		}
		else if( requestType.equals( "GetWorkingHours" ) )
		{
			errLogger_.logMsg( "Info::UserAcctMgmtController.manageUserAccount( ) - Fetch Working Hours Request " +
					"in Process for user <" + request.getParameter( "Userkey" ) + ">" );
			
			return getWhData( userAcctMgmtData.workingHrsData_ );
		}
		else 
		{
			errLogger_.logMsg( "Info::UserAcctMgmtController.manageUserAccount( ) - " +
					"Error Occurred While Processing Request " +
					"for user <" + request.getParameter( "Userkey" ) + ">" );
			
			return 2698; //Error occurred while processing request
		}
	}
	
	
	
	/* This is Called to update profile picture from UserAcctMgmtController's manageUserAccount method */
	
	public int updateProfilePic( UserProfileData profileData )
	{
		UserAcctManager userAcctManager = new UserAcctManager( );
		
		int result = userAcctManager.update( profileData );
		
		userAcctManager = null;
		
		return result;
	}
		
	/* This is Called to reset password from UserAcctMgmtController's manageUserAccount method 
	 * Here, the passMinAgeOverrideFlag decides whether to override the pass min age
	 * attribute or not.  
	 * 
	 * If it is set, then passMinAge will be set to 0
	 * else, the passMinAge will be the value from company account defaults
	 */
	
	public int passwordReset( PasswordResetData passwordResetData, Boolean passMinAgeOverrideFlag )
	{
		UserAcctManager userAcctManager = new UserAcctManager( );
		
		int result = userAcctManager.update( passwordResetData, passMinAgeOverrideFlag );
		
		userAcctManager = null;
		
		return result;
	}
	
	/* This is Called to update notification settings from UserAcctMgmtController's manageUserAccount method */
	
	public int updateNotifySettings( NotificationSettingsData notifySettingsData )
	{
		UserAcctManager userAcctManager = new UserAcctManager( );
		
		int result = userAcctManager.update( notifySettingsData );
		
		userAcctManager = null;
		
		return result;
	}
	
	/* This is Called to update working hours data from UserAcctMgmtController's manageUserAccount method */
	
	public int updateWh( WorkingHoursData whData )
	{
		UserAcctManager userAcctManager = new UserAcctManager( );
		
		int result = userAcctManager.update( whData );
		
		userAcctManager = null;
		
		return result;
	}
	
	/* This is Called to get profile pic data from UserAcctMgmtController's manageUserAccount method */
	
	public int getProfile( UserProfileData profileData )
	{
		UserAcctManager userAcctManager = new UserAcctManager( );
		
		int result = userAcctManager.get( profileData );
		
		userAcctManager = null;
		
		return result;
	}
	
	/* This is Called to fetch notify settings  data from UserAcctMgmtController's manageUserAccount method */
	
	public int getNotifyData( NotificationSettingsData notifySettingsData )
	{
		UserAcctManager userAcctManager = new UserAcctManager( );
		
		int result = userAcctManager.get( notifySettingsData );
		
		userAcctManager = null;
		
		return result;
	}
	
	/* This is Called to fetch working hours data from UserAcctMgmtController's manageUserAccount method */
	
	public int getWhData( WorkingHoursData whData )
	{
		UserAcctManager userAcctManager = new UserAcctManager( );
		
		int result = userAcctManager.get( whData );
		
		userAcctManager = null;
		
		return result;
	}
	
}
