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

import core.privilege.UserPrivilegesData;
import core.useracctmgmt.NotificationSettingsData;
import core.useracctmgmt.UserNotificationSettings;
import core.useracctmgmt.UserWorkingHoursSettings;
import core.useracctmgmt.WorkingHoursData;
import core.usermgmt.AccountPolicyManager;
import db.privileges.UserPrivilegesTable;
import utils.ErrorLogger;
import utils.ErrorMaster;

/**
 * File:  NewRegn.java 
 *
 * Created on Feb 20, 2013 11:27:28 AM
 */

/*
 * This is the implementation class for the interface RegnProcess.
 * This class is used to create a new company registration from given
 * registration details.
 */

public class NewRegn implements RegnProcess
{
    
    private static ErrorMaster errorMaster_ = null;

    public NewRegn()
    {
        if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
    }
	/*
	 * Method 	: process( ) 
	 * Input 	: Company registration details 
	 * Return 	: int value specify the company registration result (Success/failed)
	 * 
	 * Purpose	: Get the company registration details as parameter. Add the company details 
	 * in supply medium. 
	 * Finally send the registration activation mail to given mail address while
	 * registration.
	 */

	public int process( RegnData regnDataObj )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		RegnInsert regnInsert = new RegnInsert( );
		
		/*****************Stroing logo image ****************************/
		
		int logoVal = regnInsert.storeLogoImage( regnDataObj );
		
		if( logoVal != 0 ) // insert company details failed
		{
			errLogger.logMsg( "Error::NewRegn.process() - Failed to insert Logo image, " +
							   "<" + regnDataObj.companyName_ + ">, " +
							   "<" + regnDataObj.companyRegnKey_.toString( )+ ">" );

			regnInsert = null;

			return 101;
			// Roll back code to be added here
		}
		
		errorMaster_.insert( "**************LogoImage successfully stored**********************" );
		
		/***************** Adding company details ************************/
		
		int compVal = regnInsert.createCompanyRegn( regnDataObj );

		if( compVal != 0 ) // insert company details failed
		{
			errLogger.logMsg( "Error::NewRegn.process() - Failed to insert company, " +
							   "<" + regnDataObj.companyName_ + ">, " +
							   "<" + regnDataObj.companyRegnKey_.toString( )+ ">" );

			regnInsert = null;

			return 101;
			// Roll back code to be added here
		}
		
		
		errorMaster_.insert( "*************Adding company details**********************" );
		
		/***************** Adding company address details ************************/

		int addrVal = regnInsert.createUserAddress( regnDataObj );

		if( addrVal != 0 ) // Insert user address details failed
		{
			// Log error

			errLogger.logMsg( "Error::NewRegn.process() - Failed to insert address details for company, " +
							   "<" + regnDataObj.companyName_ + ">, " +
							   "<" + regnDataObj.companyRegnKey_.toString( )+ ">" );

			regnInsert = null;

			return 102;
			// Roll back code to be added here
		}

		errorMaster_.insert( "*************Adding company address details**********************" );
		
		/***************** Adding Admin profile details ************************/

		int profileVal = regnInsert.createUserProfile( regnDataObj );
		
		if( profileVal != 0 ) // Insert user details failed
		{

			errLogger.logMsg( "Error::NewRegn.process() - Failed to insert profile details for company " +
							   "<" + regnDataObj.companyName_ + ">, " +
							   "<" + regnDataObj.companyRegnKey_.toString( )+ ">" );

			regnInsert = null;

			return 103;
			// Roll back code to be added here
		}

		errorMaster_.insert( "*************Adding Admin profile details*********************" );
		
		/***************** Adding Admin login details ************************/

		int loginVal = regnInsert.createUserLogin( regnDataObj );

		if( loginVal != 0 ) // Insert user login details failed
		{

			errLogger.logMsg( "Error::NewRegn.process() - Failed to insert login details for company " +
							   "<" + regnDataObj.companyName_ + ">, " +
							   "<" + regnDataObj.companyRegnKey_.toString( )+ ">" );

			regnInsert = null;

			return 104;
			// Roll back code to be added here
		}
		
		errorMaster_.insert( "************* Adding Admin login details*********************" );
		
		/***************** Get and set default account policies details ************************/

		AccountPolicyManager policyMgr = new AccountPolicyManager( );
		
		int policyResult = 0;
		
		CompanyRegnKey key = new CompanyRegnKey( );
		
		key.companyPhoneNo_ = regnDataObj.phoneNo_;
		
		policyResult = policyMgr.insertPolicy( key );
		
		if( policyResult != 650 )
		{
			errLogger.logMsg( "Error::NewRegn.process() - failed to insert default account policies for company " +
							   "<" + regnDataObj.companyName_ + ">, " +
							   "<" + regnDataObj.companyRegnKey_.toString( )+ ">" );
			
			return 105;
		}
		
		errorMaster_.insert( "************* Get and set default account policies details *********************" );
		
		/***************** Set default privileges for admin ************************/
		
		UserPrivilegesTable privilegesTable = new UserPrivilegesTable( );
		
		UserPrivilegesData privilegesData = new UserPrivilegesData( );
		
		UserProfileKey userProfileKey = new UserProfileKey( );
		
		userProfileKey.email_ = regnDataObj.emailId_;
		
		privilegesData.userKey_ = userProfileKey;
		
		userProfileKey = null;
		
		privilegesData.privilegesValue_ = 11111111111111L; //Since admin has all privileges
		
		privilegesTable.insert( privilegesData );
		
		privilegesData = null; privilegesTable = null;
		
		errorMaster_.insert( "************* Set default privileges for admin *********************" );
		
		/***************** Get and set default notification settings ************************/
		
		UserNotificationSettings notifySettings = new UserNotificationSettings( );
		
		NotificationSettingsData notifySettingsData = new NotificationSettingsData( );
		
		notifySettingsData.userProfileKey_.email_ = regnDataObj.emailId_;
		
		int result = notifySettings.create( notifySettingsData );
		
		errorMaster_.insert( "Notification settings insertion result: "+result );
		
		notifySettings = null;
		
		notifySettingsData = null;
		
		errorMaster_.insert( "************* Get and set default wh settings *********************" );
		
		/***************** Get and set default wh settings ************************/

		UserWorkingHoursSettings whSettings = new UserWorkingHoursSettings( );
		
		WorkingHoursData whData = new WorkingHoursData( );
		
		whData.userProfileKey_.email_ = regnDataObj.emailId_;
		
		whSettings.create( whData );
		
		errorMaster_.insert( "WH settings insertion result: "+result );
		
		whSettings = null;
		
		whData = null;
		
		errorMaster_.insert( "************* Company details added successfully now send email *********************" );
		
		
		/***************** Company details added successfully now send email ************************/

		regnInsert.sendRegActivationMail( regnDataObj.uuid_, regnDataObj.emailId_, regnDataObj.firstName_,
											regnDataObj.companyName_ );

		
		regnInsert = null;
		
		//errorMaster_.insert( "Success - New registration" );

		return 100;   // New company successfully added in supply medium

	}

}
