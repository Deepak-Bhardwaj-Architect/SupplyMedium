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

import utils.ErrorLogger;
import core.login.AccountPoliciesEnforcer;
import core.login.PasswordPolicyData;
import core.regn.AccountPolicyTblBuffer;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.usermgmt.UserAccountPoliciesTable;

/**
 * File:  UserAcctManager.java 
 *
 * Created on Apr 22, 2013 10:36:06 AM
 */

/*
 * Class: UserAcctManager
 * 
 * Purpose: This class manages the user account operations like
 * Reset Password history, Save/update user notification settings,
 * Save/update user working hours settings
 * 
 */

public class UserAcctManager
{
	
	ErrorLogger errorLogger_;	
	
	/*
	 * Method: UserAcctManager - constructor
	 * 
	 * Input: none
	 * 
	 * Return: none
	 * 
	 * Purpose: To initialize the error logger
	 * 
	 */
	
	public UserAcctManager( )
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	
	/*
	 * Method: udpate 
	 * 
	 * Input: PasswordResetData object
	 * 
	 * Return: int
	 * 
	 * Purpose: It is used to implement the reset the password, set
	 * by the user by applying password history.
	 * 
	 */
	
	public int update( PasswordResetData passResetData, Boolean passMinAgeOverrideFlag )
    {
		PasswordHistory passHistory = new PasswordHistory( );
		
		AccountPolicyTblBuffer buffer = AccountPolicyTblBuffer.instance( );
		
		AccountPoliciesEnforcer enforcerObj = new AccountPoliciesEnforcer( );
		
		int buffResult = buffer.find( passResetData.regnKey_, enforcerObj );
		
		if( buffResult != 0 )
		{
			errorLogger_.logMsg( "Error::UserAcctManger.update( PasswordResetData ) - " +
								 "Failed to get enforcer object from Map for key " +
								 "<" + passResetData.regnKey_ + ">" );
			
			passHistory = null;
			
			enforcerObj = null;
			
			return 2501; // Unable to fetch enforcer object
		}
		
		PasswordPolicyData policyData = new PasswordPolicyData( );
		
		policyData.passwordHistoryDays_ = enforcerObj.accPolicyData_.passPolicyData_.passwordHistoryDays_;
		
		if( passMinAgeOverrideFlag )
		{
			policyData.passwordAgeMinDays_ = 0;
		}
		else 
		{
			policyData.passwordAgeMinDays_ = enforcerObj.accPolicyData_.passPolicyData_.passwordAgeMinDays_;
		}
		
		int resetResult = passHistory.reset( passResetData , policyData);
		
		if( resetResult == 2500 )
		{
			UserAccountPoliciesTable policiesTable = new UserAccountPoliciesTable( );
			
			UserProfileData profileData = new UserProfileData( );
			
			profileData.changePasswordFlag_ = 0;
			
			profileData.userProfileKey_ = new UserProfileKey( );
			
			profileData.userProfileKey_.email_ = passResetData.userKey_.email_;
			
			int result = policiesTable.update( profileData );
			
			if( result != 0 )
			{
				errorLogger_.logMsg( "Error::UserAcctManger.update( PasswordResetData ) - " +
						 "Failed to udpate user account policy's changePasswordFlag_" );
			}
		}
		
		passHistory = null;
		
		enforcerObj = null;
		
		policyData = null;
		
		return resetResult;
    }
	
	/*
	 * Method: udpate 
	 * 
	 * Input: NotificationSettingsData object
	 * 
	 * Return: int
	 * 
	 * Purpose: It is used to update the notifications settings, set
	 * by the user to start/stop receiving email/sms notifications
	 */
	
	public int update( NotificationSettingsData notificationSettingsData )
	{
		UserNotificationSettings notifySettings = new UserNotificationSettings( );
		
		int result = notifySettings.update( notificationSettingsData );
		
		notifySettings = null;
		
		return result; //2610 or 2611
	}
	
	/*
	 * Method: udpate 
	 * 
	 * Input: WorkingHoursData object
	 * 
	 * Return: int
	 * 
	 * Purpose: It is used to update the working hours settings, set
	 * by the user to receive sms/email for the specified working hours 
	 *  
	 */
	
	public int update( WorkingHoursData workingHoursData )
	{
		UserWorkingHoursSettings workingHrSettings = new UserWorkingHoursSettings( );
		
		int result = workingHrSettings.update( workingHoursData );
		
		workingHrSettings = null;
		
		return result;
	}
	
	/*
	 * Method: udpate 
	 * 
	 * Input: UserProfileData object
	 * 
	 * Return: int
	 * 
	 * Purpose: It is used to update user profile details.
	 *  
	 */
	
	public int update( UserProfileData userProfileData )
	{
		System.out.println( "At UserAcctManager" );
		
		UserProfileSettings profileUpdate = new UserProfileSettings( );
		
		int result = profileUpdate.update( userProfileData );
		
		return result;
	}
	
	/*
	 * Method: get
	 * 
	 * Input: NotificationsSettingsData object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to fetch the notifications data
	 */
	
	public int get( NotificationSettingsData notifyData )
	{
		UserNotificationSettings notifySettings = new UserNotificationSettings( );
		
		int result = notifySettings.get( notifyData );
				
		return result;
	}
	
	/*
	 * Method: get
	 * 
	 * Input: WorkingHoursData object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to fetch the working hours data
	 */
	
	public int get( WorkingHoursData workingHrsData )
	{
		UserWorkingHoursSettings workingHrsSettings = new UserWorkingHoursSettings( );
		
		int result = workingHrsSettings.get( workingHrsData );
		
		return result;
	}
	
	/*
	 * Method: get
	 * 
	 * Input: UserProfileData object
	 * 
	 * Return: int
	 * 
	 * Purpose: To fetch user profile pic path
	 */
	
	public int get( UserProfileData profileData )
	{
		UserProfileSettings findProfile = new UserProfileSettings( );
		
		int result = findProfile.get( profileData );
		
		return result;
	}
}
