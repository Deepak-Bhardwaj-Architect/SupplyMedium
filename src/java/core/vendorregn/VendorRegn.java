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

package core.vendorregn;


import java.util.ArrayList;
import java.util.List;


import core.notification.NotificationCenter;
import core.notification.NotificationCenterData;
import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;
import core.regn.RegnData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import core.useracctmgmt.UserNotificationSettings;
import db.regn.CompanyRegnTable;
import db.regn.MailingAddressTable;
import db.regn.UserProfileTable;
import db.vendorregn.RegnVendorMapTable;
import db.vendorregn.VendorInquiryTable;
import db.vendorregn.VendorRegnTable;
import utils.ErrorLogger;
import utils.ErrorMaster;
import utils.FileStore;
import utils.NotificationType;
import utils.PathBuilder;
import utils.StringHolder;
import utils.VRMailType;
import utils.VendorRegnStatus;

/**
 * File:  VendorRegn.java 
 *
 * Created on May 21, 2013 3:55:07 PM
 */

/*
 * Class: VendorRegn
 * 
 * Purpose: This class performs vendor registration operations like
 * load all vendor registration request, create for new vendor registration
 * request, update registration status, delete registration
 */

public class VendorRegn
{
	ErrorLogger errLogger_;
        private static ErrorMaster errorMaster_ = null;


	
	/*Constructor*/
	public VendorRegn( )
	{
		errLogger_ = ErrorLogger.instance( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method: add
	 * 
	 * Input: VendorRegnData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to create a new vendor registration
	 * request
	 */
	
	public int add( VendorRegnData vendorRegnData )
	{	
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		
		UserProfileKey profileKey = new UserProfileKey( );
		
		int result = regnTable.find( vendorRegnData.regnKey_, profileKey );
				
		if( result != 0)
		{
			errLogger_.logMsg( "Error::VendorRegn.add() - Failed to fetch profile key" );
			
			profileKey = null;
			
			return  3602; //Failed to fetch user profile key
		}
		
		UserProfileTable profileTable = new UserProfileTable( );
		
		StringHolder contactName = new StringHolder( );
		
		result = profileTable.find( profileKey, contactName );
		
		profileTable = null;
		
		profileKey = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::VendorRegn.add() - Failed to fetch contact name" );
			
			contactName = null;
			
			return  3603; //Failed to fetch contact name
		}
		
		vendorRegnData.businessContact_ = contactName.value;
		
		contactName = null;
		
		VendorRegnTable vendorRegnTable = new VendorRegnTable( );
		
		result = vendorRegnTable.isRelationExist( vendorRegnData );
		
		if( result == 1 )
			return 3604;  // Vendor registration request already sent 
		
		result = vendorRegnTable.insert( vendorRegnData );
		
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::VendorRegn.add() - Failed to add new vendor request" );
			
			vendorRegnTable = null;
			
			return  3601; //Failed to insert into vendor registration table
		}
		
		if( vendorRegnData.w9TaxForm_ != null)
		{
			result = storew9Form( vendorRegnData );
			
			if( result != 0 )
			{
				errLogger_.logMsg( "Error::VendorRegn.add() - Failed to add new vendor request" );
				
				vendorRegnTable = null;
				
				return  3601; //Failed to insert into vendor registration table
			}
		}
		
				
		result = vendorRegnTable.update( vendorRegnData );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::VendorRegn.add() - Failed to add new vendor request" );
			
			vendorRegnTable = null;
			
			return  3601; //Failed to insert into vendor registration table
		}
			
		vendorRegnTable = null;
		
		addNotification( vendorRegnData );
		
		
		
		//To send vendor registraiton email
		
		UserNotificationSettings notificationSettings=new UserNotificationSettings( );
	
		RegnData regnData = new RegnData( );
		
		int res	= regnTable.find(vendorRegnData.vendorRegnKey_, regnData );
		
		UserProfileKey userKey = new UserProfileKey( );
		
		userKey.email_ = regnData.emailId_;
				
		boolean canSendMail = notificationSettings.canSendNotification(userKey);
		
		if( canSendMail )
		{
			VendorRegnMail vrMail = new VendorRegnMail( VRMailType.vrMailType.NEW_REQ.getValue( ), "" );
			
			vrMail.send( vendorRegnData.regnKey_, vendorRegnData.vendorRegnKey_ );
			
			vrMail = null;
			

		}
		
		return 3600; //Success
		
	}
	
	/*
	 * Method: addInquiry
	 * 
	 * Input: VendorRegnData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to add new inquiry detail for vendor registration
	 * request
	 */
	
	public int addInquiry( VendorRegnData vendorRegnData )
	{
		int result = updateStatus( vendorRegnData );
		
		if( result == 3620)
		{
			VendorInquiryTable inquiryTable = new VendorInquiryTable( );
			
			 result = inquiryTable.insert( vendorRegnData.vendorInquireData );
			
			inquiryTable = null;
			
			if( result != 0 )
			{
				errLogger_.logMsg( "Error::VendorRegn.add() - Failed to add new inquiry detail" );
				
				return  3651; //Failed to add new inquiry detail
			}
			return 3650; //Success
		}
		else
		{
			return 3621;
		}
	}
	
	/*
	 * Method: update
	 * 
	 * Input: VendorRegnData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to update the vendor
	 * registration details. This scenario will occur when
	 * buyer initiates the registration request
	 */
	
	public int update( VendorRegnData vendorRegnData )
	{
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		
		UserProfileKey profileKey = new UserProfileKey( );
		
		int result = regnTable.find( vendorRegnData.regnKey_, profileKey );
		
		regnTable = null;
		
		if( result != 0)
		{
			errLogger_.logMsg( "Error::VendorRegn.update() - Failed to fetch profile key" );
			
			profileKey = null;
			
			return  3612; //Failed to fetch user profile key
		}
		
		UserProfileTable profileTable = new UserProfileTable( );
		
		StringHolder contactName = new StringHolder( );
		
		result = profileTable.find( profileKey, contactName );
		
		profileTable = null;
		
		profileKey = null;
		
		if( result != 0)
		{
			errLogger_.logMsg( "Error::VendorRegn.update() - Failed to fetch contact name" );
			
			contactName = null;
			
			return  3613; //Failed to fetch contact name
		}
		
		vendorRegnData.businessContact_ = contactName.value;
		
		contactName = null;
		
		VendorRegnTable vendorRegnTable = new VendorRegnTable( );
		
		if( vendorRegnData.w9TaxForm_ != null )
		{
			result = storew9Form( vendorRegnData );
			
			if( result != 0 )
			{
				errLogger_.logMsg( "Error::VendorRegn.add() - Failed to add new vendor request" );
				
				vendorRegnTable = null;
				
				return  3611; //Failed to insert into vendor registration table
			}
		}
		
	
		
		result = vendorRegnTable.update( vendorRegnData );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::VendorRegn.update() - Failed to update vendor details" );
			
			return  3611; //Failed to insert into vendor registration table
		}
		
		
		
	
		VendorRegnSM sm = new VendorRegnSM( );
		
		vendorRegnData.regnStatus_ = sm.getNextState( "formsent" );
		
		vendorRegnTable.updateStatus( vendorRegnData );
		
		vendorRegnTable = null;
		
		addNotification( vendorRegnData );
		
		
		//To send vendor registraiton email
		
		UserNotificationSettings notificationSettings=new UserNotificationSettings( );
	
		RegnData regnData = new RegnData( );
		
		int res	= regnTable.find(vendorRegnData.regnKey_, regnData );
		
		UserProfileKey userKey = new UserProfileKey( );
		
		userKey.email_ = regnData.emailId_;
				
		boolean canSendMail = notificationSettings.canSendNotification(userKey);
		
		if( canSendMail )
		{
			
			VendorRegnMail vrMail = new VendorRegnMail( VRMailType.vrMailType.UPDATE_REQ.getValue( ), "" );
			
			vrMail.send( vendorRegnData.regnKey_, vendorRegnData.vendorRegnKey_ );
			
			vrMail = null;
			

		}
		
		
		return 3610; //Successfully updated
	}
	
	
	/*
	 * Method: updateStatus
	 * 
	 * Input: VendorRegnData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to update the status of the vendor
	 * registration request
	 */
	
	public int updateStatus( VendorRegnData vendorRegnData )
	{
		VendorRegnTable vendorRegnTable = new VendorRegnTable( );
		
		int result = vendorRegnTable.updateStatus( vendorRegnData );
		
		vendorRegnTable = null;
		
		addNotification( vendorRegnData );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::VendorRegn.updateStatus() - Failed to update vendor registration status" );
			
			return  3621; //Failed to update status
		}
		
		if( vendorRegnData.regnStatus_.equals( VendorRegnStatus.VRStatus.ACCEPTED.getValue( ) ) )
		{
			RegnVendorMapTable mapTable = new RegnVendorMapTable( );
			
			result = mapTable.insert( vendorRegnData );
			
			if( result != 0 )
			{
				errLogger_.logMsg( "Error::VendorRegn.updateStatus() - Failed to add vendor into mapper table" );
				
				return  3622; //Failed to add vendor into mapper table
			}
		}
		
		//To send vendor registraiton email
		
		UserNotificationSettings notificationSettings=new UserNotificationSettings( );
			
		RegnData regnData = new RegnData( );
		
		CompanyRegnTable regnTable = new CompanyRegnTable();
				
		int res	= regnTable.find(vendorRegnData.vendorRegnKey_, regnData );
				
		UserProfileKey userKey = new UserProfileKey( );
				
		userKey.email_ = regnData.emailId_;
						
		boolean canSendMail = notificationSettings.canSendNotification(userKey);
				
		if( canSendMail )
		{
					
			if( vendorRegnData.regnStatus_.equals( VendorRegnStatus.VRStatus.ACCEPTED.getValue( ) ))
			{
				VendorRegnMail vrMail = new VendorRegnMail( VRMailType.vrMailType.CONFIRMATION.getValue( ), "" );
				
				vrMail.send( vendorRegnData.regnKey_, vendorRegnData.vendorRegnKey_ );
				
				vrMail = null;
			}
			else if( vendorRegnData.regnStatus_.equals( VendorRegnStatus.VRStatus.REJECTED.getValue( ) ) )
			{
				VendorRegnMail vrMail = new VendorRegnMail( VRMailType.vrMailType.REJECTION.getValue( ), "" );
				
				vrMail.send( vendorRegnData.regnKey_, vendorRegnData.vendorRegnKey_ );

				vrMail = null;
			}
			
			else if( vendorRegnData.regnStatus_.equals( VendorRegnStatus.VRStatus.INQUIRE.getValue( ) ) )
			{
				VendorRegnMail vrMail = new VendorRegnMail(
																								VRMailType.vrMailType.INQUIRY.getValue( ), 
																								vendorRegnData.vendorInquireData.inquireDetails_ 
																							);
				
				vrMail.send( vendorRegnData.regnKey_, vendorRegnData.vendorRegnKey_ );
				
				vrMail = null;
			}
			else if( vendorRegnData.regnStatus_.equals( VendorRegnStatus.VRStatus.INQUIRYANSWERED.getValue( ) ) )
			{
				VendorRegnMail vrMail = new VendorRegnMail( 
																								VRMailType.vrMailType.INQUIRY_RESPONSE.getValue( ), 
																								vendorRegnData.vendorInquireData.inquireDetails_ 
																							);
				
				vrMail.send( vendorRegnData.regnKey_, vendorRegnData.vendorRegnKey_ );
				
				vrMail = null;
			}
					

		}
		
		
		
		return 3620;
	}
	
	/*
	 * Method: getMyVendorReq
	 * 
	 * Input: VendorRegnData obj, List<VendorRegnData> obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches the vendor registration request record list for
	 * given company regn key. This contains the list of vendor request of whom this
	 * company requests for vendor registration.  
	 */
	
	public int getMyVendorReq( VendorRegnData myVendorData, List<VendorRegnData> vendorRegnDataList )
	{
		VendorRegnTable vendorRegnTable = new VendorRegnTable( );
		
		int result = vendorRegnTable.findMyVendors( myVendorData, vendorRegnDataList );
	        //System.out.print("result =================>"+result);
		vendorRegnTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::VendorRegn.getMyVendorReq() - Failed to fetch vendor regn data list" );
		
			return  3631; //Failed to fetch vendor regn data list
		}
		
		/*To fill the vendorRegnDataList object with company's (who requests the vendor queue)
		 * regnData, profileData and vendorInquireData
		 */
		result = getMyCompanyDetails( myVendorData.regnKey_, vendorRegnDataList );
		//System.out.print("result =================>"+result);
		/*To fill the vendorRegnDataList object with company vendor's regnData
		 * and proileData*/
		if( result == 3630 )
		{
			result = getVendorCompanyDetails( vendorRegnDataList );
		}
		return result;
	}
	
	/*
	 * Method: getOtherVendorReq
	 * 
	 * Input: CompanyRegnKey obj, List<VendorRegnData> obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches the vendor registration request record list for
	 * given company regn key. This contains the list of vendor request of whom this
	 * company is being requested for vendor registration.  
	 */
	
	public int getOtherVendorReq( VendorRegnData myVendorData, List<VendorRegnData> vendorRegnDataList )
	{
		VendorRegnTable vendorRegnTable = new VendorRegnTable( );
		
		int result = vendorRegnTable.findOthVendors( myVendorData, vendorRegnDataList );
		
		vendorRegnTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::VendorRegn.getOtherVendorReq() - Failed to fetch vendor regn data list" );
		
			return  3631; //Failed to fetch vendor regn data list
		}
		
		/*To fill the vendorRegnDataList object with company's (who requests the vendor queue)
		 * regnData, profileData and vendorInquireData
		 */
		result = getMyCompanyDetails( myVendorData.regnKey_, vendorRegnDataList );
		
		/*To fill the vendorRegnDataList object with company vendor's regnData
		 * and proileData*/
		if( result == 3630 )
		{
			result = getVendorCompanyDetails( vendorRegnDataList );
		}
		return result;
	}
	
	/*
	 * Method: getNonRegList
	 * 
	 * Input: CompanyRegnKey obj, List<VendorRegnData> obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches the list of company who are not 
	 * the registered vendors for the given CompanyRegnKey 
	 */
	
	public int getNonRegList( NRVendorSearchData nrVendorSrchData, List<RegnData> NRVendorRegnDataList )
	{			
		RegnVendorMapTable mapTable = new RegnVendorMapTable( );
		
		List<CompanyRegnKey> vendorRegnKeyList = new ArrayList<CompanyRegnKey>( );
 		
		int result = mapTable.find( nrVendorSrchData.regnKey_, vendorRegnKeyList );
		
		mapTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::VendorRegn.getNonRegList() - Failed to fetch regn keys" );
			
			vendorRegnKeyList = null;
			
			return  3641; //Failed to fetch regn keys
		}
		
		//This is added to filter the current user's company
		vendorRegnKeyList.add( nrVendorSrchData.regnKey_ );
		
		//To filter the companies who are all in the process of vendor registration 
		//to get non registered vendors
		VendorRegnTable vendorRegnTable = new VendorRegnTable( );
		
		List<CompanyRegnKey> filterList = new ArrayList<CompanyRegnKey>( );
		
		result = vendorRegnTable.filter( nrVendorSrchData.regnKey_, filterList );
		
		vendorRegnTable = null;
		
		if( result != 0)
		{
			errLogger_.logMsg( "Error::VendorRegn.getNonRegList() - Failed to fetch regn keys" );
			
			vendorRegnKeyList = null;
			
			return  3641; //Failed to fetch regn keys			
		}
		
		for( CompanyRegnKey key : filterList )
        {
	        vendorRegnKeyList.add( key );
        }
		
		//This will fetch 10 companies in the list
		
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		
		result = regnTable.find( vendorRegnKeyList, NRVendorRegnDataList, nrVendorSrchData );
		
		vendorRegnKeyList = null;
		
		if( result != 0)
		{
			errLogger_.logMsg( "Error::VendorRegn.getNonRegList() - Failed to fetch 10 NR Company list" );
			
			vendorRegnKeyList = null; regnTable = null;
			
			return  3642; //Failed to 10 NR Company list
		}
		
		 regnTable = null;
		
		return 3640;
	}
	
	/*To fill the vendorRegnDataList object with company's (who requests the vendor queue)
	 * regnData, profileData and vendorInquireData
	 */
	private int getMyCompanyDetails( CompanyRegnKey regnKey, List<VendorRegnData> vendorRegnDataList )
	{
		//request sender type
		
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		
		UserProfileTable profileTable = new UserProfileTable( );
		
		//To fill company regn details of company
		
		RegnData regnData = new RegnData( );
		
		errorMaster_.insert( "My company Key - " + regnKey.toString( ) );
		
        int result = regnTable.find( regnKey, regnData );
        
        if( result != 0)
        {
        	errLogger_.logMsg( "Error::VendorRegn.getMyCompanyDetails() - Failed to fetch regn data" );
    		
        	regnData = null; regnTable = null; profileTable = null;
        	
			return  3632; //Failed to fetch regn data list
        }
		
		for( VendorRegnData vendorRegnData : vendorRegnDataList )
        {   
	        vendorRegnData.regnData_.businessCategory_ 	= regnData.businessCategory_;
	        vendorRegnData.regnData_.companyName_	 	= regnData.companyName_;
	        vendorRegnData.regnData_.companyType_		= regnData.companyType_;
	        
	        UserProfileKey key = new UserProfileKey( );
	        
	        key.email_ = regnData.emailId_;
	        
	        MailingAddressTable mailTbl = new MailingAddressTable( );
	        
	        MailingAddressData addData = new MailingAddressData( );
	        
	        result = mailTbl.find( key, addData );
	        
	        mailTbl = null;
	        
	        if( result != 0 )
	        {
	        	errLogger_.logMsg( "Error::VendorRegn.getMyCompanyDetails() - Failed to fetch mailing address data" );
	    		
	        	regnData = null; regnTable = null; profileTable = null; key = null;
	        	
				return  3632; //Failed to fetch regn data list
	        }
	        
	        vendorRegnData.profileData_.mailingAddr_ = new MailingAddressData( );
	        
	        vendorRegnData.profileData_.mailingAddr_.address_ 		= addData.address_;
	        vendorRegnData.profileData_.mailingAddr_.addressType_ 	= addData.addressType_;
	        vendorRegnData.profileData_.mailingAddr_.city_			= addData.city_;
	        vendorRegnData.profileData_.mailingAddr_.countryRegion_	= addData.countryRegion_;
	        vendorRegnData.profileData_.mailingAddr_.state_			= addData.state_;
	        vendorRegnData.profileData_.mailingAddr_.zipcode_		= addData.zipcode_;
	        
	        addData = null;
	        
	        //To fill user details of the company
	        
	        UserProfileData profileData = new UserProfileData( );
	        
	        result = profileTable.find( key, profileData );
	        
	        key = null;
	        
	        if( result != 0 )
	        {
	        	errLogger_.logMsg( "Error::VendorRegn.getMyCompanyDetails() - Failed to fetch UserProfileData" );
				
				profileData = null; regnTable = null; profileTable = null;
				
				return  3633; //Failed to fetch user profile data
	        }
	        
	        vendorRegnData.profileData_.department_ = profileData.department_;
	        vendorRegnData.profileData_.phoneNo_	= profileData.phoneNo_;
	        vendorRegnData.profileData_.cellNo_		= profileData.cellNo_;
	        vendorRegnData.profileData_.faxNo_		= profileData.faxNo_;
	        vendorRegnData.profileData_.emailId_	= profileData.emailId_;
	        
	        profileData = null; 
	        
	      //To fill inquire details of the registration process
			
			VendorInquiryTable inquiryTable = new VendorInquiryTable( );
			
			List<VendorInquireData> inquireDataList = new ArrayList<VendorInquireData>( );
			
			result = inquiryTable.find( vendorRegnData.vendorRegnId_, inquireDataList );
			
			inquiryTable = null;
			
			if( result != 0 )
			{
				errLogger_.logMsg( "Error::VendorRegn.getMyCompanyDetails() - Failed to fetch" +
								   " vendor regn inquire details" );
				
				profileData = null; regnTable = null; 
				
				profileTable = null; inquireDataList = null;
				
				return  3634; //Failed to fetch vendor regn inquire details
			}
			
			vendorRegnData.vendorInquireDataList_ = inquireDataList;
			
			inquireDataList = null;
        }
		
		regnTable = null; profileTable = null; regnData = null;
		
		return 3630;
	}
	
	/*To fill the vendorRegnDataList object with company vendor's regnData
	 * and proileData
	 */
	private int getVendorCompanyDetails( List<VendorRegnData> vendorRegnDataList )
	{
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		
		UserProfileTable profileTable = new UserProfileTable( );
		
		for( VendorRegnData vendorRegnData : vendorRegnDataList )
        {   
			//To fill company regn details of company
			RegnData regnData = new RegnData( );
			
			//errorMaster_.insert( "VendorRegnKey - " + vendorRegnData.vendorRegnKey_.toString( ) );
			
	        int result = regnTable.find( vendorRegnData.vendorRegnKey_, regnData );
	        
	        if( result != 0)
	        {
	        	errLogger_.logMsg( "Error::VendorRegn.getVendorCompanyDetails() - Failed to fetch regn data" );
	    		
	        	regnData = null; regnTable = null; profileTable = null;
	        	
				return  3632; //Failed to fetch regn data list
	        }
	        
	        vendorRegnData.vendorRegnData_.businessCategory_= regnData.businessCategory_;
	        vendorRegnData.vendorRegnData_.companyName_	 	= regnData.companyName_;
	        vendorRegnData.vendorRegnData_.companyType_		= regnData.companyType_;
	        
	        UserProfileKey key = new UserProfileKey( );
	        
	        key.email_ = regnData.emailId_;
	        
	        MailingAddressTable mailTbl = new MailingAddressTable( );
	        
	        MailingAddressData addData = new MailingAddressData( );
	        
	        result = mailTbl.find( key, addData );
	        
	        mailTbl = null;
	        
	        if( result != 0 )
	        {
	        	errLogger_.logMsg( "Error::VendorRegn.getVendorCompanyDetails() - Failed to fetch mailing address data" );
	    		
	        	regnData = null; regnTable = null; profileTable = null; key = null;
	        	
				return  3632; //Failed to fetch regn data list
	        }
	        
	        regnData = null;
	        
	        vendorRegnData.vendorProfileData_.mailingAddr_ = new MailingAddressData( );
	        
	        vendorRegnData.vendorProfileData_.mailingAddr_.address_ 		= addData.address_;
	        vendorRegnData.vendorProfileData_.mailingAddr_.addressType_ 	= addData.addressType_;
	        vendorRegnData.vendorProfileData_.mailingAddr_.city_			= addData.city_;
	        vendorRegnData.vendorProfileData_.mailingAddr_.countryRegion_	= addData.countryRegion_;
	        vendorRegnData.vendorProfileData_.mailingAddr_.state_			= addData.state_;
	        vendorRegnData.vendorProfileData_.mailingAddr_.zipcode_		= addData.zipcode_;
	        
	        addData = null;
	        
	        //To fill user details of the company

	        UserProfileData profileData = new UserProfileData( );
	        
	        result = profileTable.find( key, profileData );
	        key = null;
	        
	        if( result != 0 )
	        {
	        	errLogger_.logMsg( "Error::VendorRegn.getVendorCompanyDetails() - Failed to fetch UserProfileData" );
				
				profileData = null; regnTable = null; profileTable = null;
				
				return  3633; //Failed to fetch user profile data
	        }
	        
	        vendorRegnData.vendorProfileData_.department_ = profileData.department_;
	        vendorRegnData.vendorProfileData_.phoneNo_	= profileData.phoneNo_;
	        vendorRegnData.vendorProfileData_.cellNo_		= profileData.cellNo_;
	        vendorRegnData.vendorProfileData_.faxNo_		= profileData.faxNo_;
	        vendorRegnData.vendorProfileData_.emailId_	= profileData.emailId_;
	        vendorRegnData.vendorContact_ = profileData.firstName_ + " " + profileData.lastName_;
	        
	        profileData = null;
        }
		
		regnTable = null; profileTable = null;
		
		return 3630;
	}
	
	/*
	 * Method: storew9Form
	 * 
	 * Input: VendorRegnData object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to store the uploaded w9form  to server. If it
	 * return 0 for successfully upload or otherwise
	 */
	public int storew9Form( VendorRegnData vendorRegnData )
	{
		try
        {
			errorMaster_.insert( "id="+vendorRegnData.vendorRegnId_ );
			
			errorMaster_.insert( "key="+vendorRegnData.regnKey_ );
			
			errorMaster_.insert( "object="+vendorRegnData.w9TaxForm_ );
			
			CompanyRegnTable regnTable = new CompanyRegnTable( );
			
			String companyname = regnTable.find( vendorRegnData.regnKey_ );
			
			regnTable = null;
			
			String phoneNumber = vendorRegnData.regnKey_.toString( );
			
			StringHolder dirPath = new StringHolder( );
			
			PathBuilder pathBuilder = new PathBuilder( );
			
			// Getting the w9form directory path
			int dirPathRes = pathBuilder.getW9FormDirPath( companyname, phoneNumber, dirPath );
			
			
			if( dirPathRes == 0 ) // w9 form directory path fetched successfully
			{
				FileStore fileStoreObj = new FileStore( );
				
				StringHolder storedPath = new StringHolder( );
				
				// Used to store feed image 
				int storeResult = fileStoreObj.storeFile( vendorRegnData.w9TaxForm_, dirPath.value, Long.toString( vendorRegnData.vendorRegnId_ ),storedPath );
				
				if( storeResult == 0 )  // user feed image stored in folder successfully
				{
					//int result = 0;
					
					//StringHolder webURL = new StringHolder( );
					
					// Converting local path to web url
					
					//result = pathBuilder.getWebURL( storedPath.value, webURL );
					
					//if( result == 0 )
					//{
						vendorRegnData.w9TaxFormPath_= storedPath.value;
						errorMaster_.insert( "Stored path - " + vendorRegnData.w9TaxFormPath_ );
						vendorRegnData.w9TaxFormPath_ = vendorRegnData.w9TaxFormPath_.replace( "\\", "/" );
						
					//}
				}
			}
        }
        catch( Exception ex )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::UserFeedController.storeFeedImage() - " + ex );
			
			return -1;
        }
		
		return 0;
	}
	
	/*Method: get
	 * 
	 * Input: CompanyRegnKey vendorKey
	 * 
	 * Return: String
	 * 
	 * Purpose: This is used to get the company name of the vendor
	 * to display in the toaster message
	 */
	
	public String get( CompanyRegnKey vendorRegnKey )
	{
		String companyName = "";
		
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		
		companyName = regnTable.find( vendorRegnKey );
		
		return  companyName;
	}
	
	
	/* Method: addNotification
	 * 
	 * Input: VendorRegnData object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to convert the VendorRegnData to NotificationCenterData. Then add new
	 * notification in notification center.
	 */
	
	public int addNotification( VendorRegnData vendorRegnData )
	{
		
		int result = 0;
		
		NotificationCenterData notificationCenterData = new NotificationCenterData( );
		
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		
		notificationCenterData.receiverRegnKey_ = vendorRegnData.vendorRegnKey_;
		
		RegnData regnData = new RegnData( );
		
		result = regnTable.getCompany( vendorRegnData.vendorRegnKey_, regnData );
		
		if( result != 0 )
			return -1;
		
		
		notificationCenterData.receiverKey_.email_ = regnData.emailId_;
		
		notificationCenterData.senderRegnKey_ = vendorRegnData.regnKey_;
		
		RegnData regnData1 = new RegnData( );
		
		result = regnTable.getCompany( vendorRegnData.regnKey_, regnData1 );
		
		if( result != 0 )
			return -1;
		
		
		notificationCenterData.senderKey_.email_ = regnData1.emailId_;
		
		regnTable = null;
		
		notificationCenterData.notificationType_ = NotificationType.type.VENREG.getValue( );
		
		notificationCenterData.notificationTypeId_ = ""+vendorRegnData.vendorRegnId_;
		
		if( vendorRegnData.regnStatus_.equals( VendorRegnStatus.VRStatus.NEWREQUEST.getValue( )) )
		{
			notificationCenterData.notificationMessage_ = "New Vendor registration request received from "+ 
					regnData1.companyName_+"";
		}
		else if( vendorRegnData.regnStatus_.equals(VendorRegnStatus.VRStatus.FORMSENT.getValue( )) )
		{
			notificationCenterData.notificationMessage_ = ""+regnData1.companyName_+
					" sent Vendor registration form";
		}
		else if( vendorRegnData.regnStatus_.equals( VendorRegnStatus.VRStatus.ACCEPTED.getValue( )) )
		{
			notificationCenterData.notificationMessage_ = "Vendor registration request accepted by " +
					""+regnData1.companyName_+"";
		}
		else if( vendorRegnData.regnStatus_.equals(VendorRegnStatus.VRStatus.REJECTED.getValue( )) )
		{
			notificationCenterData.notificationMessage_ = "Vendor registration request rejected by "+ 
					regnData1.companyName_+"";
		}
		else if( vendorRegnData.regnStatus_.equals(VendorRegnStatus.VRStatus.INQUIRE.getValue( )) )
		{
			notificationCenterData.notificationMessage_ = "Vendor registration inquire details asked by "+ 
					regnData1.companyName_+"";
		}
		else if( vendorRegnData.regnStatus_.equals(VendorRegnStatus.VRStatus.INQUIRYANSWERED.getValue( )) )
		{
			notificationCenterData.notificationMessage_ = "Vendor registration inquire answered by "+ 
					regnData1.companyName_+"";
		}
			
			
		regnData1 = null;
		
		regnData = null;
		
		NotificationCenter notificationCenter = new NotificationCenter( );
		
		result = notificationCenter.add( notificationCenterData );
		
		notificationCenter = null;
		
		
		return result;
	}
	
}
