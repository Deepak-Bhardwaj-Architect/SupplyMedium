package dataimporter;

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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

import utils.AppStartupInitializer;
import utils.ErrorLogger;
import utils.UserStatus;
import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import ctrl.regn.UserSignupController;
import db.login.UserkeyUuidMapperTable;
import utils.ErrorMaster;

/**
 * File:  CompanyRegnDataGen.java 
 *
 * Created on Jun 8, 2013 12:47:52 PM
 */

public class UserProfileDataImporter
{
    private static ErrorMaster errorMaster_ = null;


public UserProfileDataImporter()
{
if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
}

	public static void main( String [ ] args )
	{
		// TODO Auto-generated method stub
	
		ErrorLogger errorLogger = ErrorLogger.instance( );
	
		String msg = "Info::StartupUtil:contextInitialized-Application started";
		
		errorMaster_.insert( "Startup" );

		AppStartupInitializer appInitializer = new AppStartupInitializer( );

		appInitializer.initAppConfigProperty( );

		appInitializer.initDBConfigProperty( );

		appInitializer.initErrorCodeConfigProperty( );

		appInitializer.initErrorLogger( );
		
		appInitializer.initAccountPolicies( );
		
		errorLogger.logMsg( msg );
		
		importUsers( );
		
		
	}
	
	/* To import companies from CSV */
	private static void importUsers( )
	{
		ArrayList<String []> csvDataList = get( "/Users/Desktop/user_details.csv" );
		
		
		
		errorMaster_.insert( "Total List Size - " + csvDataList.size( ) );
		
		int i=1;
		
		for( String [ ] strings : csvDataList )
        {
			if( i == 1 || i == 2 )
			{
				i++;
				continue;
			}
			
			//RegnData regnData = new RegnData( );
			
			UserProfileData profileData = new UserProfileData( );
			
			profileData.companyRegnKey_ = new CompanyRegnKey( );
			
			profileData.companyRegnKey_.companyPhoneNo_ = strings[1];
			
			profileData.mailingAddr_ = new MailingAddressData( );
			profileData.mailingAddr_.countryRegion_ = strings[2];
			
			profileData.title_	= strings[3];
			profileData.firstName_ = strings[4];
			profileData.lastName_ = strings[5];
			
			profileData.mailingAddr_.address_ = strings[6];
			profileData.mailingAddr_.city_ = strings[7];
			profileData.mailingAddr_.state_ = strings[8];
			profileData.mailingAddr_.zipcode_ = strings[9];
			profileData.mailingAddr_.companyRegnKey_ = new CompanyRegnKey( );
			profileData.mailingAddr_.companyRegnKey_.companyPhoneNo_ = strings[1];
			
			profileData.userType_	= strings[10];
			profileData.emailId_ = strings[11];
			profileData.password_ = strings[12];
			profileData.department_ = strings[13];
			profileData.managerSupervisor_ = strings[14];
			profileData.phoneNo_ = strings[15];
			profileData.cellNo_	= strings[16];
			profileData.faxNo_ = strings[17];
			
			profileData.userStatus_ = UserStatus.status.PENDING.getValue( );
			profileData.profileUuid_ = UUID.randomUUID( ).toString( );
			profileData.userProfileKey_ = new UserProfileKey( );
			profileData.userProfileKey_.email_ = profileData.emailId_;
			
			UserSignupController profileCtrl = new UserSignupController( );
			//NewRegistration RegistrationActivate
					
			int responseCode = profileCtrl.processUserSignupReq( profileData, "NewSignup" );
			
	        errorMaster_.insert( "Response code for company " + (i-2) + ": " + responseCode );
	        i++;
	        
	        if( responseCode == 300 )
	        {
	        	activateCompany( profileData, "UserStatusUpdate" );
	        }
	        	
	        profileData = null;
	        profileCtrl = null;
        }
	}
	
	/* Activate Company */
	
	private static void activateCompany( UserProfileData profileData, String reqType )
	{		
		UserSignupController signupController = new UserSignupController( );
		
		profileData.userStatus_ = UserStatus.status.ACTIVE.getValue( );
		
		int responseCode = signupController.processUserSignupReq( profileData, reqType );
		
		errorMaster_.insert( "User Activation result: " + responseCode );
		
		signupController = null;
	}
	
	/* CSV to ArrayList<String []> converter */
	
	private static ArrayList<String []> get(String filename)
	{		
		ArrayList<String []> arrayList = new ArrayList<String []>();

		 try{
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;	  

			  while ((strLine = br.readLine()) != null)   {
				  arrayList.add(strLine.split(","));
			  }

		 }catch(Exception e){
			 errorMaster_.insert(e.getMessage());
		 }

		return arrayList;
	}
	
}
