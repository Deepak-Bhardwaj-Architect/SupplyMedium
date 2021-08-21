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

/**
 * File:  CompanyRegnDataGen.java 
 *
 * Created on Jun 7, 2013 12:47:52 PM
 */



import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import utils.AppStartupInitializer;
import utils.CompanyStatus;
import utils.ErrorLogger;
import utils.UserType;
import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.regn.MailingAddressData;
import ctrl.regn.RegnController;
import db.regn.CompanyRegnTable;
import utils.ErrorMaster;

public class CompanyRegnDataImporter
{
private static ErrorMaster errorMaster_ = null;

	/**
	 * @param args
	 */
public CompanyRegnDataImporter()
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
		
		importCompanies( );
		
		
	}
	
	/* To import companies from CSV */
	private static void importCompanies( )
	{
		ArrayList<String []> csvDataList = get( "/Users/Desktop/company_details.csv" );
		
		
		errorMaster_.insert( "Total List Size - " + csvDataList.size( ) );
		
		int i=1;
		
		for( String [ ] strings : csvDataList )
        {
			if( i == 1 || i == 2 )
			{
				i++;
				continue;
			}
			
			RegnData regnData = new RegnData( );
			
			regnData.branch_ 			= strings[8];
			regnData.businessCategory_ 	= strings[5];
			regnData.businessUnitName_ 	= strings[7];
			regnData.cellNo_ 			= strings[20];
			regnData.companyId_ 		= strings[4];
			regnData.companyName_ 		= strings[3];
			regnData.companyRegnKey_ 	= new CompanyRegnKey( );
			
			regnData.companyRegnKey_.companyPhoneNo_ = strings[19];
			
			regnData.companyType_ 		= strings[1];
			//regnData.createdDate_ = "";
			regnData.department_ 		= strings[17];
			regnData.emailId_ 			= strings[22];
			regnData.faxNo_ 			= strings[21];
			regnData.firstName_ 		= strings[14];
			regnData.lastName_ 			= strings[15];
			regnData.logo_ 				= strings[2];
			
			regnData.mailingAddressArr_ = new ArrayList<MailingAddressData>( );
			
			MailingAddressData addData 	= new MailingAddressData( );
			
			addData.address_ 			= strings[10];
			addData.addressType_ 		= strings[8];
			addData.addrid_ 			= 0;
			addData.city_ 				= strings[11];
			addData.companyRegnKey_ 	= new CompanyRegnKey( );
			
			addData.companyRegnKey_.companyPhoneNo_ = strings[19];
			
			addData.countryRegion_ 		= strings[9];
			addData.emailid_ 			= strings[22];;
			//addData.mailkey_ = new MailingAddressKey( );
			addData.state_ 				= strings[12];
			addData.userID_ 			= 0;
			addData.zipcode_ 			= strings[13];
			
			regnData.mailingAddressArr_.add( addData );
			
			addData = null;
			
			//regnData.mailingAddressArr_ = "";
			regnData.managerSupervisor_ = strings[18];
			regnData.maxEmployee_ 		= 0;
			regnData.maxEmployee_ 		= 0;
			regnData.maxTransaction_ 	= 0;
			regnData.password_ 			= strings[23];
			regnData.phoneNo_ 			= strings[19];
			regnData.pricingOption_ 	= strings[24];
			
			regnData.segmentDivisionName_ = "";
			
			regnData.signUpAs_ 			= strings[1];
			regnData.theme_ 			= "default";
			regnData.title_ 			= strings[16];
			regnData.userType_ 			= UserType.type.ADMIN.getValue( );
			regnData.uuid_ 				= UUID.randomUUID( ).toString( );
			
			regnData.companyStatus_		= CompanyStatus.status.PENDING.getValue( );

			RegnController regnCtrl = new RegnController( );
			//NewRegistration RegistrationActivate
					
			int responseCode = regnCtrl.processRegnReq( regnData, "NewRegistration" );
			
	        errorMaster_.insert( "Response code for company " + (i-2) + ": " + responseCode );
	        i++;
	        
	        if( responseCode == 100 )
	        {
	        	activateCompany( regnData, "RegistrationActivate" );
	        }
	        	
	        regnData = null;
	        regnCtrl = null;
        }
	}
	
	/* Activate Company */
	
	private static void activateCompany( RegnData regnData, String reqType )
	{
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		
		//regnData.companyStatus_		= CompanyStatus.status.ACTIVE.getValue( );
		
		regnData.uuid_ = regnTable.getUuid( regnData.companyRegnKey_ );
		
		regnTable = null;
		
		RegnController regnCtrl = new RegnController( );
		
		int responseCode = regnCtrl.processRegnReq( regnData, reqType );
		
		errorMaster_.insert( "Company Activation result: " + responseCode );
		
		regnCtrl = null;
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

