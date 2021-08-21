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

import utils.ErrorMaster;

/**
 * File:  MailingAddressData.java 
 *
 * Created on Jan 15, 2013 11:27:28 AM
 */

//This core data class is related to mailing_address table in db

public class MailingAddressData
{

	public String         address_;
	public String         city_;
	public String         state_;

	public String         zipcode_;
	public String         countryRegion_;
	public String         addressType_;

	public CompanyRegnKey companyRegnKey_;
	public String         emailid_;

	public long	             userID_;
	public long	             addrid_;
	public MailingAddressKey	mailkey_;
        private static ErrorMaster errorMaster_ = null;


	
	public MailingAddressData()
	{
		
		addrid_ = -1;
		
		companyRegnKey_ = new CompanyRegnKey( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}

	/*
	 * Method 	: clear( ) 
	 * Input 	: None 
	 * Return 	: None
	 * 
	 * Purpose	: To reset the class variables.
	 */

	public void clear( )
	{
		address_ 		= null;
		city_ 			= null;
		state_ 			= null;

		zipcode_ 		= null;
		countryRegion_	= null;
		addressType_ 	= null;

		companyRegnKey_ = null;
		emailid_ 		= null;

		userID_= 0;
		addrid_= -1;
		mailkey_=null;
	}

	/*
	 * Method 	: show( ) 
	 * Input 	: None 
	 * Return 	: None
	 * 
	 * Purpose	: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		errorMaster_.insert( "address_		= " + address_ );
		errorMaster_.insert( "city_			= " + city_ );
		errorMaster_.insert( "state_			= " + state_ );

		errorMaster_.insert( "zipcode_		= " + zipcode_ );
		errorMaster_.insert( "countryRegion_	= " + countryRegion_ );
		errorMaster_.insert( "addressType_	= " + addressType_ );

		errorMaster_.insert( "companyRegnKey_= " + companyRegnKey_ );
		errorMaster_.insert( "emailid_		= " + emailid_ );

		errorMaster_.insert( "userID_		= " + userID_ );

		errorMaster_.insert( "addrid_		= " + addrid_ );


		errorMaster_.insert( "mailkey_		= " + mailkey_ );

	}

}
