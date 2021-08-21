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

package db.regn;

/**
 * File: MailingAddressRecord.java
 * 
 * Created on Jan 5, 2013 3:29:51 PM
 */

//This DB record class is related to mailing_address table in db

public class MailingAddressRecord
{

	public long	  mailKey_;

	public long   addressId_;
	public long   userId_;
	public String addressType_;

	public String address_;
	public String cityName_;
	public String stateName_;

	public String zipcode_;
	public String countryName_;
	public String regnKey_;

	public String email_;

	/*
	 * Method : show( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "mailKey_			= " + mailKey_ );
		
		System.out.println( "addressId_			= " + addressId_ );
		System.out.println( "userId_			= " + userId_ );
		System.out.println( "addressType_		= " + addressType_ );

		System.out.println( "address_			= " + address_ );
		System.out.println( "cityName_			= " + cityName_ );
		System.out.println( "stateId_			= " + stateName_ );

		System.out.println( "zipcode_			= " + zipcode_ );
		System.out.println( "countryId_			= " + countryName_ );
		System.out.println( "companyPhoneNo_	= " + regnKey_ );

		System.out.println( "email_				= " + email_ );
	}

	/*
	 * Method : clear( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		this.mailKey_ = 0;
		this.addressId_ = 0;
		this.userId_ = 0;
		this.address_ = null;
		this.address_ = null;
		this.stateName_ = null;
		this.zipcode_ = null;
		this.countryName_ = null;
		this.regnKey_ = null;
		this.email_ = null;
	}

}
