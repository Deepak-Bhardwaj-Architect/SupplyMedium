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

import utils.ErrorMaster;

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
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "mailKey_			= " + mailKey_ );
		
		errorMaster_.insert( "addressId_			= " + addressId_ );
		errorMaster_.insert( "userId_			= " + userId_ );
		errorMaster_.insert( "addressType_		= " + addressType_ );

		errorMaster_.insert( "address_			= " + address_ );
		errorMaster_.insert( "cityName_			= " + cityName_ );
		errorMaster_.insert( "stateId_			= " + stateName_ );

		errorMaster_.insert( "zipcode_			= " + zipcode_ );
		errorMaster_.insert( "countryId_			= " + countryName_ );
		errorMaster_.insert( "companyPhoneNo_	= " + regnKey_ );

		errorMaster_.insert( "email_				= " + email_ );
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
