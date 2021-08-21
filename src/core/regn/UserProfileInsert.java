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


package core.regn;


import org.omg.CORBA.LongHolder;

import core.privilege.UserPrivilegesData;
import core.useracctmgmt.NotificationSettingsData;
import core.useracctmgmt.UserNotificationSettings;
import core.useracctmgmt.UserWorkingHoursSettings;
import core.useracctmgmt.WorkingHoursData;
import utils.ErrorLogger;
import utils.HTMLMailComposer;

import utils.Mailer;
import db.login.ActivationPendingTable;
import db.privileges.PrivilegesDefaultsTable;
import db.privileges.UserPrivilegesTable;
import db.regn.MailingAddressTable;
import db.regn.UserProfileTable;
import db.usermgmt.UserAccountDefaultsTable;
import db.usermgmt.UserAccountPoliciesTable;

/*
 * This is one of the implementation class for the interface UserSignupProcess.
 * This class is used to create a User from given
 * user signup details.
 */

public class UserProfileInsert implements UserSignupProcess
{
	/*
	 * Method : process( ) Input : User Profile details Return : int value
	 * specify the User profile insert result (Success/failed)
	 * 
	 * Purpose: Get the user profile details as parameter. Split this details
	 * according to database storage. And store to DB one by one. And finally
	 * send the user signup confirmation mail to given mail address while user
	 * signup.
	 */

	public int process( UserProfileData userProfileData )
	{

		int addrVal = createUserAddress( userProfileData );

		if( addrVal != 0 )
		{
			// Log error
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Error::RegnInsert.process() - Failed to insert address" +
								"for user " +"<" + userProfileData.firstName_+ ">, " +
								"<" + userProfileData.userProfileKey_.email_ + ">" );

			return 301;
			// Roll back code to be added here
		}

		System.out.println( "Address Details successfully inserted" );

		int profileVal = createUserProfile( userProfileData );

		if( profileVal != 0 )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Error::RegnInsert.process() - Failed to insert profile" +
								"for user " +"<" + userProfileData.firstName_+ ">, " +
								"<" + userProfileData.userProfileKey_.email_ + ">" );

			return 302;
			// Roll back code to be added here
		}

		System.out.println( "Profile Details successfully inserted" );

		int loginVal = createUserLogin( userProfileData );

		if( loginVal != 0 )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Error::RegnInsert.process() - Failed to insert login details" +
								" for user " +"<" + userProfileData.firstName_+ ">, " +
								"<" + userProfileData.userProfileKey_.email_ + ">" );

			return 303;
			// Roll back code to be added here
		}
		
		System.out.println( "Login Details successfully inserted" );
		
		int accountDefaultsVal = setAccountDefaults( userProfileData );
		
		if( accountDefaultsVal != 0 )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger.logMsg( "Error::UserProfileInsert.process() - Failed to set account defaults " +
								"for user " +"<" + userProfileData.firstName_+ ">, " +
								"<" + userProfileData.userProfileKey_.email_ + ">" );
			
			return 304;
		}

		
		int privilegesVal = setDefaultPrivileges( userProfileData );
		
		if( privilegesVal != 0 )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger.logMsg( "Error::UserProfileInsert.process() - Failed to set default privileges " +
								"for user " +"<" + userProfileData.firstName_+ ">, " +
								"<" + userProfileData.userProfileKey_.email_ + ">" );
			
			return 305;
		}
		
		setNotifyDefaults( userProfileData );
		
		setWhDefaults( userProfileData );

		sendUserSignupMail( userProfileData.emailId_,userProfileData.firstName_ );

		return 300;

	}

	/*
	 * Method : createUserAddress( ) Input : user profile details Return : int
	 * 
	 * Purpose: Insert the user address. And return the 0 if address details
	 * successfully inserted in supply medium other wise not inserted.
	 */

	public int createUserAddress( UserProfileData userProfileData )
	{
		System.out.println( "createuseraddress" + userProfileData );

		MailingAddressTable mailingAddressTblObj = new MailingAddressTable( );

		int addrVal = 0;

		addrVal = mailingAddressTblObj.insertAddress( userProfileData.mailingAddr_ );

		mailingAddressTblObj = null;

		return addrVal;

	}

	/*
	 * Method : createUserProfile( ) Input : user profile details Return : int
	 * 
	 * Purpose: Insert the new user profile details. And return the 0 if profile
	 * details successfully inserted in supply medium other wise not inserted.
	 */

	public int createUserProfile( UserProfileData userProfileData )
	{

		UserProfileTable userProfileTblObj = new UserProfileTable( );

		int profileVal = 0;

		profileVal = userProfileTblObj.insertUserProfile( userProfileData );
		userProfileTblObj = null;

		return profileVal;

	}

	/*
	 * Method : createUserLogin( ) Input : user signup details Return : int
	 * 
	 * Purpose: Insert the new user login details. And return the 0 if login
	 * details successfully inserted in supply medium other wise not inserted.
	 */

	public int createUserLogin( UserProfileData userProfileData )
	{

		// UserLoginTable userLoginTblObj = new UserLoginTable( );
		//
		// int loginVal = 0;
		//
		// LoginData loginData = createLoginData( userProfileData );
		//
		// loginVal = userLoginTblObj.insertLoginDetails( loginData );
		// userLoginTblObj = null;
		//
		// return loginVal;

		ActivationPendingTable pendingTable = new ActivationPendingTable( );

		int loginVal = 0;

		LoginData loginData = createLoginData( userProfileData );

		loginVal = pendingTable.insert( loginData );

		pendingTable = null;

		return loginVal;

	}

	/*
	 * Method : createLoginData( ) Input : user profile details Return : user
	 * login details
	 * 
	 * Purpose: Filter the user login details from user signup details. Add the
	 * filter details to login data object then return it.
	 */

	private LoginData createLoginData( UserProfileData userProfileData )
	{
		LoginData loginData = new LoginData( );

		loginData.emailid_ = userProfileData.emailId_;
		loginData.password_ = userProfileData.password_;

		return loginData;
	}

	/*
	 * Method : sendUserSignupMail( ) 
	 * 
	 * Input : user email address and first name of the user
	 * 
	 * Return : none
	 * 
	 * Purpose: Send the user signup success mail to user mail id.
	 */

	private void sendUserSignupMail( String emailId, String firstName )
	{

		Mailer mailerObj = new Mailer( );

		String [ ] to = { emailId };

		String sub = "SupplyMedium User signup";
		
		HTMLMailComposer composer = new HTMLMailComposer( );

		String message = composer.composeUserSignupMail( emailId, firstName );
		
		composer = null;

		mailerObj.composeAndSendMail( to, sub, message );

	}
	
	/*
	 * Method : setAccountDefaults( ) 
	 * 
	 * Input : userProfileDataq object 
	 * 
	 * Return : none
	 * 
	 * Purpose: Set default account policies for new user
	 */

	private int setAccountDefaults( UserProfileData profileData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		UserAccountDefaultsTable defaults = new UserAccountDefaultsTable( );
		
		int defaultResult = defaults.getDefaults( profileData );
		
		if(defaultResult == 0)
		{
			errLogger.logMsg( "info::UserProfileInsert:UserAccountDefaultsTable.getDefaults:Success" );
		}
		
		defaults = null;
		
		UserAccountPoliciesTable policiesDefaults = new UserAccountPoliciesTable( );
		
		int policiesResult = policiesDefaults.insertPolicies( profileData );
		
		if( policiesResult == 0 )
		{
//			errLogger.logMsg( "info::UserProfileInsert:UserAccountPoliciesTable.insertPolicies:Success" );
		}
		
		policiesDefaults = null;
		return policiesResult;
	}
	
	/*
	 * Method : setDefaultPrivileges( ) 
	 * 
	 * Input : userProfileDataq object 
	 * 
	 * Return : none
	 * 
	 * Purpose: Set default user privileges for new user
	 */

	private int setDefaultPrivileges( UserProfileData profileData )
	{
//		ErrorLogger errLogger = ErrorLogger.instance( );
		
		PrivilegesDefaultsTable defaults = new PrivilegesDefaultsTable( );
		
		LongHolder privilegesValue = new LongHolder( );
		
		int defaultResult = defaults.getUserDefaults( privilegesValue );
		
		if(defaultResult == 0)
		{
//			errLogger.logMsg( "info::UserProfileInsert:setDefaultPrivileges:PrivilegesDefaultsTable.getUserDefaults:Success" );
		}
		
		defaults = null;
		
		UserPrivilegesTable privilegesTable = new UserPrivilegesTable( );
		
		UserPrivilegesData privilegesData = new UserPrivilegesData();
		
		UserProfileKey key = new UserProfileKey( );
		
		key.email_	= profileData.emailId_;
		
		privilegesData.userKey_ = key;
		
		privilegesData.privilegesValue_ = privilegesValue.value;
		
		int privVal = privilegesTable.insert( privilegesData );
		
		if( privVal != 0 )
		{
//			errLogger.logMsg( "info::UserProfileInsert:setDefaultPrivileges:UserPrivilegesTable.setDefaultPrivileges:Failed" );
		}
		
		key = null;
		privilegesTable = null;
		return privVal;
	}

	/*
	 * Method : setNotifyDefaults( ) 
	 * 
	 * Input : userProfileData object 
	 * 
	 * Return : none
	 * 
	 * Purpose: Set user notification defaults for new user
	 */
	
	private void setNotifyDefaults( UserProfileData userProfileData )
	{	
		UserNotificationSettings notifySettings = new UserNotificationSettings( );
		
		NotificationSettingsData notifySettingsData = new NotificationSettingsData( );
		
		notifySettingsData.userProfileKey_.email_ = userProfileData.emailId_;
		
		notifySettings.create( notifySettingsData );
		
		notifySettings = null;
		
		notifySettingsData = null;
	}
	
	/*
	 * Method : setWhDefaults( ) 
	 * 
	 * Input : userProfileData object 
	 * 
	 * Return : none
	 * 
	 * Purpose: Set user working hours defaults for new user
	 */
	
	private void setWhDefaults( UserProfileData userProfileData )
	{	
		UserWorkingHoursSettings whSettings = new UserWorkingHoursSettings( );
		
		WorkingHoursData whData = new WorkingHoursData( );
		
		whData.userProfileKey_.email_ = userProfileData.emailId_;
		
		whSettings.create( whData );
		
		whSettings = null;
		
		whData = null;

	}
	
}
