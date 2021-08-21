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

package db.config;

/**
 * File: CompanyEmailDomainRecord.java
 * 
 * Created on Mar 25, 2013 3:29:51 PM
 */

//This DB record class is related to company_email_domain table in db

public class CompanyEmailDomainRecord
{

	public String companyName_;
	
	public String emailDomain_;

	/*
	 * Method : clear( ) 
	 * 
	 * Input  : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		companyName_ = null;
		
		emailDomain_ = null;
	}

	/*
	 * Method : show( ) 
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "companyName_	= " + companyName_ );
		
		System.out.println( "emailDomain_	= " + emailDomain_ );
	}


}
