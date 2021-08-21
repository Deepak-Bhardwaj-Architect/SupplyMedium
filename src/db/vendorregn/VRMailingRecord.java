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
package db.vendorregn;

import core.regn.CompanyRegnKey;
import core.regn.MailingAddressKey;

/**
 * File:  VRMailingRecord.java 
 *
 * Created on Oct 30, 2013 3:28:09 PM
 */
public class VRMailingRecord
{

	public String         address_;
	public String         city_;
	public String         state_;

	public String         zipcode_;
	public String         countryRegion_;
	public String         addressType_;

	public String companyRegnKey_;
	public String         emailid_;

	public long	             userID_;
	public long	             addrid_;
	public MailingAddressKey	mailkey_;
	
	
	public VRMailingRecord()
	{
		
		addrid_ = -1;
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
		System.out.println( "address_		= " + address_ );
		System.out.println( "city_			= " + city_ );
		System.out.println( "state_			= " + state_ );

		System.out.println( "zipcode_		= " + zipcode_ );
		System.out.println( "countryRegion_	= " + countryRegion_ );
		System.out.println( "addressType_	= " + addressType_ );

		System.out.println( "companyRegnKey_= " + companyRegnKey_ );
		System.out.println( "emailid_		= " + emailid_ );

		System.out.println( "userID_		= " + userID_ );

		System.out.println( "addrid_		= " + addrid_ );


		System.out.println( "mailkey_		= " + mailkey_ );

	}

}