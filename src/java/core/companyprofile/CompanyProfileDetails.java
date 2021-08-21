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

package core.companyprofile;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;
import core.regn.RegnData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;

import utils.DiskSpaceChecker;
import utils.ErrorLogger;
import utils.IntHolder;

import db.regn.CompanyRegnTable;
import db.regn.MailingAddressTable;
import db.config.PricingOptionTable;
import db.history.TransactionHistoryTable;
import db.regn.UserProfileTable;
import utils.ErrorMaster;

/**
 * @FileName : CompanyProfileDetails.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Mar 5, 2013
 * 
 * Purpose : It is Core Class for get the Company Profile information from
 *       the Database
 * 
 */

public class CompanyProfileDetails
{

	ErrorLogger	               errLogger_	= ErrorLogger.instance( );

	// This Property will returned to the calling function

	private CompanyProfileData	cmpProfiledata;
        private static ErrorMaster errorMaster_ = null;



	
	/**
	 * @Date : Mar 5, 2013 9:47:46 AM
	 * 
	 * @Return : int - // 0- No Data. -1 for Error . 0< for Success
	 * 
	 * @Purpose : Get the Company Profile Information
	 * 
	 * @param cmpKey
	 * @param data
	 * 
	 */
	public int getCompanyInfo( CompanyRegnKey cmpKey, CompanyProfileData data )
	{
		cmpProfiledata = data;

		int result = getCompanyProfile( cmpKey );

		if( result == 0 )
		{
			getCompanyPricing( );
			
			getDiskQuotaInfo( cmpKey );

			getNoOfRegistertedUser( cmpKey );

			getMailingAddress( cmpKey,data.email_ );
			
			getTransaction(cmpKey);

		}
		
		return result;
	}

	/*
	 * Method: getFullProfile
	 * 
	 * Input: CompanyRegnKey obj, CompanyProfileData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to fetch the company info with admin details
	 */
	
	public int getFullProfile( CompanyRegnKey regnKey, CompanyProfileData cmpProData )
	{
		int result = getProfile( regnKey, cmpProData );

		if( result == 0 )
		{
			getMailAdd( regnKey, cmpProData );
		}
		return result;
	}
	
	/*
	 * Method: getProfile
	 * 
	 * Input: CompanyRegnKey
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is the helper method for getFullProfile(..)
	 */
	
	private int getProfile( CompanyRegnKey regnKey, CompanyProfileData cmpProData )
	{
		int result = 0;

		// Getting the List of Records
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		RegnData regndata=new RegnData( );
		result = regnTable.searchKey( regnKey, regndata );
		
		convert( regndata, cmpProData );
		
		if(result==0)
		{
			UserProfileTable profileTable = new UserProfileTable( );
			
			UserProfileKey profileKey = new UserProfileKey( );
			
			UserProfileData profileData = new UserProfileData( );
			
			profileKey.email_ = regndata.emailId_;
			
			result = profileTable.find( profileKey, profileData );		
		
			cmpProData.businessContactName_ = profileData.firstName_ + " " + profileData.lastName_;
			
			cmpProData.email_ 		= profileData.emailId_;
			cmpProData.department_ 	= profileData.department_;
			cmpProData.phone_		= profileData.phoneNo_;
			cmpProData.cell_		= profileData.cellNo_;
			cmpProData.fax_			= profileData.faxNo_;
			
			
			profileData = null; profileKey = null; profileTable = null;
		}
		regnTable = null;

		return result;
	}
	
	/*
	 * Method: getMailAdd
	 * 
	 * Input: CompanyRegnKey obj
	 * 
	 * Return: void
	 * 
	 * Purpose: 
	 */
	private void getMailAdd( CompanyRegnKey regnkey, CompanyProfileData cmProData )
	{
		MailingAddressTable mailTable = new MailingAddressTable( );

		cmProData.mailingAddressArr_ = new ArrayList<MailingAddressData>( );
		
		MailingAddressData maildata=new MailingAddressData( );
		
		maildata.companyRegnKey_=regnkey;
		
		maildata.emailid_ = cmProData.email_;
		
		int result = mailTable.searchFilter(maildata,cmProData.mailingAddressArr_);
if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		if( cmProData.mailingAddressArr_.size( ) == 0 )
		{
			System.out.println( "getMailingAddress: No Mailing Address Found for the given Company" );
		}
		else if( result == -1 )
		{
			System.out.println( "getMailingAddress:  Error While Gettng the maill Address" );
		}

	}
	
	/**
	 * @Date : Mar 13, 2013 9:50:58 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Process the CompanyRegnRecord to get the Company info from
	 *          CompanyRegnTable
	 * 
	 * @param cmpKey
	 * @return
	 * 
	 */
	private int getCompanyProfile( CompanyRegnKey cmpKey )
	{
		int result = 0;

		// Getting the List of Records
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		RegnData regndata=new RegnData( );
		result = regnTable.searchKey( cmpKey, regndata );
		
		if(result==0)
		{
			convert( regndata, this.cmpProfiledata );			
		}
		
		regnTable = null;

		return result;
	}

	/*
	 * Purpose : Get the Pricing Option Parameter : Pricing Option
	 */

	/**
	 * @Date : Mar 13, 2013 9:52:13 AM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Getting the Max Member & Max Transaction information from the
	 *          PricingOptionTable
	 * 
	 */
	private void getCompanyPricing( )
	{
		if( this.cmpProfiledata.pricingOption_ != null )
		{
			PricingOptionTable priceTable = new PricingOptionTable( );

			IntHolder maxTans = new IntHolder( );

			this.cmpProfiledata.maxEmployee_ = priceTable.getMaxMembers( this.cmpProfiledata.pricingOption_ );
if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
			int Result = priceTable.getMaxTransaction( this.cmpProfiledata.pricingOption_, maxTans );
			if( Result == 0 )
			{
				this.cmpProfiledata.maxTransaction_ = maxTans.value;
			}
			else
			{
				System.out.println( "getCompanyPricing : Error while geting the "
				        + "Company Max Transaction for the Pricing Option" + this.cmpProfiledata.pricingOption_ );
			}

			priceTable = null;
		}
	}

	/**
	 * @Date : Mar 13, 2013 9:53:11 AM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Get the Number of Registered User for this UserProfileTable
	 *          table
	 * 
	 * @param regnkey
	 * 
	 */
	private int getNoOfRegistertedUser( CompanyRegnKey regnkey )
	{
		UserProfileTable userprofileTable = new UserProfileTable( );

		regnkey.companyPhoneNo_ = this.cmpProfiledata.companyRegnKey_;

		int result = userprofileTable.getNoOfRegisteredUserForCompany( regnkey );
if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		if( result > -1 )
		{
			this.cmpProfiledata.registeredEmployee_ = result;
		}
		else
		{
			System.out.println( " getNoOfRegistertedUser :Error while geting the No of Registerted User for the CompanyKey : "
			                + regnkey.companyPhoneNo_ );
		}

		return result;

	}

	/**
	 * @Date : Mar 13, 2013 9:53:49 AM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Get Mailing Address from MailingAddressTable
	 * 
	 * @param regnkey,email
	 * 
	 */
	private void getMailingAddress( CompanyRegnKey regnkey, String comEmail )
	{
		MailingAddressTable mailTable = new MailingAddressTable( );

		this.cmpProfiledata.mailingAddressArr_ = new ArrayList<MailingAddressData>( );
		
		MailingAddressData maildata=new MailingAddressData( );
		
		maildata.companyRegnKey_=regnkey;
		
		maildata.emailid_ = comEmail;
		
		int result = mailTable.searchFilter(maildata,this.cmpProfiledata.mailingAddressArr_);
if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		if( this.cmpProfiledata.mailingAddressArr_.size( ) == 0 )
		{
			System.out.println( "getMailingAddress: No Mailing Address Found for the given Company" );
		}
		else if( result == -1 )
		{
			System.out.println( "getMailingAddress:  Error While Gettng the maill Address" );
		}

	}
	
	
	/**
	 * @Date : Mar 13, 2013 9:53:49 AM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Get Mailing Address from MailingAddressTable
	 * 
	 * @param regnkey,email
	 * 
	 */
	private void getTransaction( CompanyRegnKey regnkey )
	{
		Calendar cal=Calendar.getInstance();
   		
   		DateFormat format=new SimpleDateFormat("dd-MMM-yyyy");
   		
   		format.format(cal.getTime());
   		
   		cal=format.getCalendar();
   		
   		
   		cal.set( Calendar.DAY_OF_MONTH,1 );
   		
   		java.util.Date fromDate=cal.getTime( );
   		
   		java.sql.Date sqlDate = new java.sql.Date(fromDate.getTime());
   		
   		
   		
   		//current date
   		
   		/*cal.add(  Calendar.MONTH,1 );
   		
   		java.util.Date date=new java.util.Date( );
   						
   		java.util.Date toDate=date;
   		
   		java.sql.Date sqlDate1 = new java.sql.Date(toDate.getTime());*/
   		
   		cal.add(  Calendar.MONTH,1 );
		
		java.util.Date toDate=cal.getTime( );
		
		java.sql.Date sqlDate1 = new java.sql.Date(toDate.getTime());
		
		//timeBasedData.toDate_=sqlDate1;
   		
 
   		
   		
   		TransactionHistoryTable  transHisTable = new TransactionHistoryTable();
   		
   		double amount = transHisTable.find(regnkey, sqlDate, sqlDate1);
   		
   		this.cmpProfiledata.thisMonthTrasaction_ = (long) amount;
   		
   		transHisTable = null;
	}

	
	/**
	 * @Date	: Mar 23, 2013 2:19:11 PM
	 *
	 * @Return 	: void
	 *
	 * @Purpose	: Convert RegnData to CompanyProfileData
	 *
	 * @param regndata
	 * @param profilData
	 */
	
	private void convert(RegnData regndata,CompanyProfileData profilData)
	{		
		profilData.businessCategory_	=regndata.businessCategory_;
		profilData.companyID_			=regndata.companyId_;
		profilData.companyName_			=regndata.companyName_;
		profilData.companyRegnKey_		=regndata.companyRegnKey_.companyPhoneNo_;
		profilData.pricingOption_		=regndata.pricingOption_;
		profilData.theme_				=regndata.theme_;	
		profilData.email_ 				= regndata.emailId_;
		profilData.businessType_ 		= regndata.signUpAs_;
                //System.out.print("profilData.businessType_==========================>"+profilData.businessType_);
                
	}
	
	/*
	 * Purpose : Get the company disk quota information
	 */

	/**
	 * @Date : Jul 18, 2013 9:52:13 AM
	 * 
	 * @Return : void
	 * 
	 * @Purpose :Get the company disk quota information
	 * 
	 */
	private void getDiskQuotaInfo( CompanyRegnKey regnKey )
	{
		DiskSpaceChecker diskSpaceChecker 	= new DiskSpaceChecker( regnKey );
		
		this.cmpProfiledata.totalSpace_ 	= diskSpaceChecker.totalSpace_;
		
		this.cmpProfiledata.spaceOccupied_ 	= diskSpaceChecker.spaceOccupied_;
		
		this.cmpProfiledata.spaceRemaining_ = diskSpaceChecker.spaceRemaining_;
	}


}
