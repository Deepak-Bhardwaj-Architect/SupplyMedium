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

import core.common.FileUploadPath;
import db.login.ActivationPendingTable;
import db.regn.CCMapperTable;
import db.regn.CompanyRegnTable;
import db.regn.MailingAddressTable;
import db.regn.UserProfileTable;
import java.io.File;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import utils.ErrorLogger;
import utils.FileStore;
import utils.HTMLMailComposer;
import utils.Mailer;
import utils.PathBuilder;
import utils.StringHolder;
import utils.UserStatus;

/**
 * File:  RegnInsert.java 
 *
 * Created on Feb 11, 2013 11:27:28 AM
 */

/*
 * This is the helper class for the NewRegn. It is used to create the new company 
 * in supply medium
 */
public class RegnInsert
{

	/*
	 * Method 	: createCompanyRegn( ) 
	 * Input 	: company registration details 
	 * Return 	: int
	 * 
	 * Purpose	: Insert the new company details. And return the 0 if company
	 * successfully inserted in supply medium other wise not inserted.
	 */

	public int createCompanyRegn( RegnData regnDataObj )
	{
		
		if( regnDataObj == null )
		{
			ErrorLogger errLogger = ErrorLogger.instance();
			
			errLogger.logMsg( "Error::RegnInsert.createCompanyRegn() - RegnDataobj is null" );
			
			return -1;
		}
		
		CompanyRegnTable companyRegnTblObj = new CompanyRegnTable( );

		int compVal = 0;

		compVal = companyRegnTblObj.insert( regnDataObj );

		companyRegnTblObj = null;

		return compVal;

	}

	/*
	 * Method 	: createUserAddress( ) 
	 * Input 	: company registration details 
	 * Return	: int
	 * 
	 * Purpose	: Insert the company address. And return the 0 if address details
	 * successfully inserted in supply medium other wise not inserted. If
	 * address successfully inserted, insert company and country details.
	 */

	public int createUserAddress( RegnData regnDataObj )
	{
		ErrorLogger errLogger = ErrorLogger.instance();
		
		if( regnDataObj == null )
		{
			errLogger.logMsg( "Error::RegnInsert.createUserAddress() - RegnDataobj is null" );
			
			return -1;
		}
		
		if( regnDataObj.mailingAddressArr_ == null )
		{
			errLogger.logMsg( "Error::RegnInsert.createUserAddress() - mailingAddressArr_ is null" );
			
			return -1;
		}

		MailingAddressTable mailingAddressTblObj = new MailingAddressTable( );

		int addrVal = 0;
		
		for( MailingAddressData mailingAddressData : regnDataObj.mailingAddressArr_ )
		{
			addrVal = mailingAddressTblObj.insertAddress( mailingAddressData );

			if( addrVal == 0 )  // address successfully inserted and insert country company relation
			{
				// Convert CCMapperData object from MailingAddressData object and companyname
				CCMapperData ccMapperData = createCCMapperdata( mailingAddressData,
				        regnDataObj.companyName_ );
				
				CCMapperTable ccMapperTable = new CCMapperTable( );

				ccMapperTable.insertCCMapper( ccMapperData );
				
				ccMapperTable = null;
			}

			else
			{
				continue;
			}
		}

		mailingAddressTblObj = null;

		return addrVal;

	}

	/*
	 * Method : createUserProfile( ) Input : company registration details Return
	 * : int
	 * 
	 * Purpose: Insert the new company admin profile details. And return the 0
	 * if profile details successfully inserted in supply medium other wise not
	 * inserted.
	 */

	public int createUserProfile( RegnData regnDataObj )
	{
		if( regnDataObj == null )
		{
			ErrorLogger errLogger = ErrorLogger.instance();
			
			errLogger.logMsg( "Error::RegnInsert.createUserProfile() - RegnDataobj is null" );
			
			return -1;
		}
		
		int profileVal = 0;

		// Convert RegnData object to UserProfileData object
		UserProfileData userProfileData = createUserProfileData( regnDataObj );
		
		UserProfileTable userProfileTblObj = new UserProfileTable( );
		
		profileVal = userProfileTblObj.insertUserProfile( userProfileData );
		
		userProfileTblObj = null;

		return profileVal;
	}

	/*
	 * Method : createUserLogin( ) Input : company registration details Return :
	 * int
	 * 
	 * Purpose: Insert the new company admin login details. And return the 0 if
	 * login details successfully inserted in supply medium other wise not
	 * inserted.
	 */

	public int createUserLogin( RegnData regnDataObj )
	{

		/*
		 * To insert login details in activation_pending table. Once the link is
		 * activated, the login details will be added to user_login table.
		 */

		if( regnDataObj == null )
		{
			ErrorLogger errLogger = ErrorLogger.instance();
			
			errLogger.logMsg( "Error::RegnInsert.createUserLogin() - RegnDataobj is null" );
			
			return -1;
		}
		
		int loginVal = 0;

		// Convert RegnData object to LoginData object
		LoginData loginData = createLoginData( regnDataObj );
		
		ActivationPendingTable pendingTable = new ActivationPendingTable( );
		
		loginVal = pendingTable.insert( loginData );
		
		pendingTable = null;

		return loginVal;

	}
	
	

	/*
	 * Method : sendRegActivationMail( ) 
	 * 
	 * Input : company admin email address,company name and uuid 
	 * 
	 * Return : none
	 * 
	 * Purpose: Send the company registration link to given mail address. If
	 * user click the link to activate the company. Otherwise it is in pending
	 * state. It get the mailing config details from appconfig file.
	 */

	public void sendRegActivationMail( String uuid, String emailId,String firstName, String companyName )
	{

		String [ ] to = { emailId };

		String sub = "SupplyMedium login activation link";

		HTMLMailComposer composer = new HTMLMailComposer( );
		
		String message = composer.composeRegActivationMail( uuid, emailId, firstName, companyName );
		
		composer = null;
				  
		Mailer mailerObj = new Mailer( );
		
		mailerObj.composeAndSendMail( to, sub, message.toString( ),"SM Registration" );
	}


	/*
	 * Method : createUserProfileData( ) Input : company registration details
	 * Return : admin profile details
	 * 
	 * Purpose: Filter the admin profile details from company registration
	 * details. Add the filter details to user profile data object then return
	 * it.
	 */

	private UserProfileData createUserProfileData( RegnData regnData )
	{
		
		UserProfileData userProfileData = new UserProfileData( );

		CompanyRegnKey companyRegnKey 	= new CompanyRegnKey( );
		companyRegnKey.companyPhoneNo_ 	= regnData.phoneNo_;
		userProfileData.companyRegnKey_ = companyRegnKey;

		UserProfileKey userProfileKey 	= new UserProfileKey( );
		userProfileKey.email_ 			= regnData.emailId_;
		userProfileData.userProfileKey_ = userProfileKey;

		userProfileData.firstName_		= regnData.firstName_;
		userProfileData.lastName_ 		= regnData.lastName_;
		userProfileData.title_ 			= regnData.title_;

		userProfileData.department_ 	= regnData.department_;
		userProfileData.managerSupervisor_ = regnData.managerSupervisor_;

		userProfileData.phoneNo_ 		= regnData.phoneNo_;
		userProfileData.cellNo_ 		= regnData.cellNo_;
		userProfileData.faxNo_ 			= regnData.faxNo_;
		userProfileData.emailId_ 		= regnData.emailId_;

		userProfileData.password_ 		= regnData.password_;
		userProfileData.userType_ 		= regnData.userType_;
		userProfileData.userStatus_ 	= UserStatus.status.ACTIVE.getValue( );

		return userProfileData;
	}

	/*
	 * Method : createLoginData( ) Input : company registration details Return :
	 * admin login details
	 * 
	 * Purpose: Filter the admin login details from company registration
	 * details. Add the filter details to login data object then return it.
	 */

	private LoginData createLoginData( RegnData regnData )
	{
		LoginData loginData 	= new LoginData( );

		loginData.emailid_ 		= regnData.emailId_;
		loginData.password_ 	= regnData.password_;

		return loginData;
	}

	/*
	 * Method : createCCMapperdata( ) Input : company registration details
	 * Return : company country mapping data object
	 * 
	 * Purpose: Filter the country company mapping details from company
	 * registration details. Add the filter details to ccmapperdata object then
	 * return it.
	 */
	private CCMapperData createCCMapperdata( MailingAddressData mailingAddrData,
	        String companyname )
	{
		CCMapperData ccMapperdata 		= new CCMapperData( );

		ccMapperdata.companyRegnKey_ 	= mailingAddrData.companyRegnKey_;
		ccMapperdata.countryname_ 		= mailingAddrData.countryRegion_;
		ccMapperdata.companyname_		= companyname;

		return ccMapperdata;
	}
	
	/*
	 * Method : storeLogoImage( ) 
	 * 
	 * Input :RegnData object
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to store the company logo in local directory
	 */
	public int storeLogoImage(RegnData regnData)
	{
		try
        {
			if(regnData.logoImage_ == null)
				return 0;
			
			String companyname = regnData.companyName_;
			
			String phoneNumber = regnData.companyRegnKey_.toString( );
			
			StringHolder dirPath = new StringHolder( );
			
			PathBuilder pathBuilder = new PathBuilder( );
			
			// Getting the user feed image directory path
                        //dirPath.value=FileUploadPath.getFileUploadPath(request);
			int dirPathRes = pathBuilder.getCompanyLogoPath( companyname, phoneNumber, dirPath );
			
			if( dirPathRes == 0 ) // logo directory path fetched successfully
			{
				FileStore fileStoreObj = new FileStore( );
				
				StringHolder storedPath = new StringHolder( );
				
			
				// Used to store logo image 
				int storeResult =0;// fileStoreObj.storeFile( regnData.logoImage_, dirPath.value, "logo",storedPath );
				
				if( storeResult == 0 )  // logo image stored in folder successfully
				{
					
					StringHolder relativePath = new StringHolder( );
					
					int result = 0;
					
					
					// Converting local path to relative url
					
					result = pathBuilder.getRelativePath( storedPath.value, relativePath );
					
					if( result == 0 )
					{
						regnData.logo_ = storedPath.value;
					}
					
					
					
				}
			}
        }
        catch( Exception ex )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::RegnInsert.storeLogoImage() - " + ex );
			
			return -1;
        }
		
		return 0;
	}
	

}
