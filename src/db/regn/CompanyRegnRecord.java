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

import java.sql.Timestamp;

/**
 * File: CompanyRegnRecord.java
 * 
 * Created on Jan 5, 2013 3:29:51 PM
 */

//This DB record class is related to company_registration table in db

public class CompanyRegnRecord
{

	public String    companyId_;
	public String    companyName_;
	public String    companyLogoPath_;

	public String    businessCategoryName_;
	public String    companyType_;
	public String    companyTheme_;

	public String    pricingOption_;
	public String    companyPhoneNo_;

	public String    uuid_;
	public String    companyStatus_;
	public Timestamp createdDate_;

	public String    segmentDivisionName_;
	public String    businessUnitName_;
	public String    regnKey_;
	public String    email_;

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
		System.out.println( "CompanyId_				= " + companyId_ );
		System.out.println( "companyName_			= " + companyName_ );
		System.out.println( "companyLogoPath_		= " + companyLogoPath_ );

		System.out.println( "businessCategoryId_	= " + businessCategoryName_ );
		System.out.println( "companyType_			= " + companyType_ );
		System.out.println( "companyTheme_			= " + companyTheme_ );

		System.out.println( "pricingOption_			= " + pricingOption_ );
		System.out.println( "companyPhoneNo_		= " + companyPhoneNo_ );
		System.out.println( "uuid_					= " + uuid_ );

		System.out.println( "Compnay Status_		= " + companyStatus_ );
		System.out.println( "Created Date			= " + createdDate_ );
		System.out.println( "segmentDivisionName_	= " + segmentDivisionName_ );

		System.out.println( "regnKey_				= " + regnKey_ );
		System.out.println( "email_					= " + email_ );
		System.out.println( "businessUnitName_		= " + businessUnitName_ );
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
		this.companyId_ = null;
		this.companyName_ = null;
		this.companyLogoPath_ = null;

		this.businessCategoryName_ = null;
		this.companyType_ = null;
		this.companyTheme_ = null;

		this.pricingOption_ = null;
		this.companyPhoneNo_ = null;

		this.uuid_ = null;
		this.companyStatus_ = null;
		this.createdDate_ = null;

		this.segmentDivisionName_ = null;
		this.regnKey_ = null;
		this.email_ = null;

		this.businessUnitName_ = null;
	}
}
