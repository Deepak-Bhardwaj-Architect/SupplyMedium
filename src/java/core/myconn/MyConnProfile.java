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
package core.myconn;

import utils.ErrorLogger;


import core.regn.MailingAddressData;
import core.regn.RegnData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.regn.CompanyRegnTable;
import db.regn.MailingAddressTable;
import db.regn.UserProfileTable;

/**
 * File:  MyConnProfile.java 
 *
 * Created on 21-Aug-2013 2:43:34 PM
 */
public class MyConnProfile
{

	/*
	 * Method : MyConnProfile -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnProfile()
	{
		
	}
	
	/*
	 * Method : get
	 * 
	 * Input  : UserProfileKey object, MyConnProfileData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the profile details for given profile key.
	 * Copied to MyConnProfile object then only caller classes can access the 
	 * myconnprofile object.
	 * 
	 */
	
	public int get( UserProfileKey userKey, MyConnProfileData connProfileData )
	{
		int result = 0;
		
		// Get the user profile details from user_profile table
		
		UserProfileData userProfileData = new UserProfileData( );
		
		UserProfileTable profileTbl = new UserProfileTable( );
		
		result = profileTbl.getUserProfile( userKey, userProfileData );
		
		profileTbl = null;
		
		if( result != 0 )
			return 10061;   // User profile details fetched failed
		
		convert( userProfileData, connProfileData );

		
		// Get the user address details from mailling_address table
		
		MailingAddressData maillAddressData = new MailingAddressData( );
		
		MailingAddressTable mailingAddressTable = new MailingAddressTable( );
		
		result = mailingAddressTable.find( userKey, maillAddressData );
		
		if( result !=0 )
			return 10062;  // User address fetch failed
		
		convert( maillAddressData, connProfileData );
		
		maillAddressData = null;
		
		
		// Gte the user's company details from company_registration table
		
		RegnData regnData = new RegnData( );
		
		CompanyRegnTable regnTbl = new CompanyRegnTable( );
		
		result = regnTbl.find( userProfileData.companyRegnKey_, regnData );
		 
		regnTbl = null;
		
		if( result != 0 )
			return 10063;
		
		convert( regnData, connProfileData );
		
		userProfileData = null;
		
		regnData = null;
		
		return 10060;
	}
	
	
	/*
	 * Method : get
	 * 
	 * Input : UserProfileData object, MyConnProfileData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the userprofiledata to MyConnProfileData
	 */

	private int convert( UserProfileData userProfileData,MyConnProfileData myConnProfileData )
	{
		try
        {
			myConnProfileData.name_ 		= userProfileData.firstName_+" "+userProfileData.lastName_;
			
			myConnProfileData.email_ 		= userProfileData.emailId_;
			
			myConnProfileData.department_ 	= userProfileData.department_;
			
			myConnProfileData.role_ 		= userProfileData.managerSupervisor_;
			
			myConnProfileData.phoneNo_ 		= userProfileData.phoneNo_;
			
			myConnProfileData.cell_ 		= userProfileData.cellNo_;
			
			myConnProfileData.fax_ 			= userProfileData.faxNo_;
			
			myConnProfileData.regnKey_		= userProfileData.companyRegnKey_;
			
			myConnProfileData.profileImagePath_ = userProfileData.profilePicture_;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
        	
			String errorMessage = "Exception :: MyConnProfile.convert( userProfileData, myConnProfileData ) - "
			        + e;

			errLogger_.logMsg( errorMessage );

			return -1; 

        }
		
		
		return 0;
	}

	/*
	 * Method : get
	 * 
	 * Input : UserProfileData object, MyConnProfileData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the maillingAddrData to MyConnProfileData
	 */

	private int convert( MailingAddressData maillingAddrData,MyConnProfileData myConnProfileData )
	{
		try
        {
			String address = "";
			
			if( maillingAddrData.address_ != null )
				address = maillingAddrData.address_ + ",";
			
			if( maillingAddrData.city_ != null )
				address = maillingAddrData.city_+",";
			
			if( maillingAddrData.state_ != null )
				address = maillingAddrData.state_+",";
			
			if( maillingAddrData.countryRegion_ != null )
				address = maillingAddrData.countryRegion_+",";
			
			if( !address.equalsIgnoreCase( "" ) )
				address =  address.substring( 0, address.length( ) - 1 );
			
			myConnProfileData.address_ = address;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
        	
			String errorMessage = "Exception :: MyConnProfile.convert( maillingAddrData, myConnProfileData ) - "
			        + e;

			errLogger_.logMsg( errorMessage );

			return -1; 

        }
		return 0;
	}

	/*
	 * Method : get
	 * 
	 * Input : UserProfileData object, MyConnProfileData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the regnData to MyConnProfileData
	 */

	private int convert( RegnData regnData, MyConnProfileData myConnProfileData )
	{
		try
        {
			myConnProfileData.companyName_ 		= regnData.companyName_;
			
			myConnProfileData.companyType_ 		= regnData.companyType_;
			
			myConnProfileData.businessCategory_ = regnData.businessCategory_;
			
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
        	
			String errorMessage = "Exception :: MyConnProfile.convert( regnData, myConnProfileData ) - "
			        + e;

			errLogger_.logMsg( errorMessage );

			return -1; 

        }
		return 0;
	}


}
