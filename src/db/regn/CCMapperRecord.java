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
 * File: CCMapperRecord.java
 * 
 * Created on Jan 5, 2013 3:29:51 PM
 */


//This DB record class is related to country_company_mapper table in db

public class CCMapperRecord
{
	public String regnKey_;
	public String countryname_;
	public String companyname_;

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
		regnKey_ = null;
		countryname_ = null;
		companyname_ = null;
	}

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
		System.out.println( "regnKey_					= " + regnKey_ );
		System.out.println( "countryname_				= " + countryname_ );
		System.out.println( "companyname_				= " + companyname_ );
	}
}
