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

import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File: UserAcctMgmtData.java
 * 
 * Created on Apr 22, 2013 12:48:13 PM
 */
public class UserAcctMgmtData
{
	/*
	 * Class: UserAcctMgmtData
	 * 
	 * Purpose: This class contains the domain variables for user account mgmt.
	 * This contains, NotificationsSettingsData, PasswordResetData,
	 * WorkingHoursData and UserProfileData. All the variables are initialized
	 * here.
	 */

	public UserProfileKey			userProfileKey_;
	public UserProfileData          userProfileData_;
	public PasswordResetData        passResetData_;
	
	public NotificationSettingsData notifySettingsData_;
	public WorkingHoursData         workingHrsData_;
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method: UserAcctMgmtData() -- Constructor
	 * 
	 * Input: none
	 * 
	 * Return: none
	 * 
	 * Purpose: This method intializes the UserProfileData object,
	 * PasswordResetData object, NotificationsSettingsData object and
	 * WorkingHoursData object
	 * 
	 */

	public UserAcctMgmtData()
	{
		userProfileKey_		= new UserProfileKey( ); 
		userProfileData_ 	= new UserProfileData( );
		passResetData_ 		= new PasswordResetData( );
		
		notifySettingsData_ = new NotificationSettingsData( );
		workingHrsData_ 	= new WorkingHoursData( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method: show()
	 * 
	 * Input: none
	 *  
	 * Return: void
	 *  
	 * Purpose: This method is used to print all 
	 * the domain variables in console
	 * 
	 */
	
	public void show( )
	{
		errorMaster_.insert( "-----Printing User Acct Mgmt Data - Starts-----" );
		
		errorMaster_.insert( "userProfileKey_ = " + userProfileKey_.email_ );
		
		userProfileData_.show( );
		passResetData_.show( );
		notifySettingsData_.show( );
		//workingHrsData_.
		
		errorMaster_.insert( "-----Printing User Acct Mgmt Data - Ends-----" );
	}
	
	/*
	 * Method: clear()
	 * 
	 * Input: none
	 * 
	 * Return: void
	 * 
	 * Purpose: This method is used to clear/null all
	 * domain variables
	 * 
	 */

	public void clear( )
	{
		userProfileKey_ = null;
		userProfileData_ = null;
		passResetData_ = null;
		
		notifySettingsData_ = null;
		workingHrsData_ = null;
	}


}
