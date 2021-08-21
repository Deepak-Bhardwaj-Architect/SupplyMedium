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
package core.dashboard;

import java.sql.Date;
import java.sql.Timestamp;

import core.regn.CompanyRegnKey;

/**
 * File:  CustomerBasedData.java 
 *
 * Created on Oct 29, 2013 6:17:58 PM
 */
public class CustomerBasedData
{
	public CompanyRegnKey 	regnKey_;
	
	public CompanyRegnKey 	toRegnKey_;
	
	public Date 			fromDate_;
	
	public Date 			toDate_;
	
	public String 			currencyType_;
	
	public Date 			filterDate_;
	
	public double 			transactionAmount_;
	
	public String 			vendorCompanyName_;

	public Timestamp 		createdTimestamp_;
	
	public String 			reportType_;
	
	
	/*
	 * Method : CustomerBasedData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public CustomerBasedData()
	{
		regnKey_		= new CompanyRegnKey( );	
		
		toRegnKey_ 		= new CompanyRegnKey( );
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
	
	public void show()
	{
		System.out.println( " from_regn_key     	= "+ regnKey_.toString( ));
		
		System.out.println( " to_regn_key     		= "+ toRegnKey_.toString( ));
		
		System.out.println(  " created_time_stamp 	= "+ createdTimestamp_);
		
		System.out.println( " currency_type			= "+ currencyType_);
		
		System.out.println( " amount 				= "+ transactionAmount_);
		
		System.out.println( " reportType_ 			= "+ reportType_);
		
		System.out.println( " vendorCompanyName_    = "+ vendorCompanyName_);
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
	
	public void clear()
	{
		regnKey_			= null;
		
		toRegnKey_			= null;
		
		createdTimestamp_	= null;
		 
		currencyType_		= null;
		
		fromDate_			= null;
		
		toDate_				= null;
		
		vendorCompanyName_	= null;
		
		transactionAmount_	= 0.00;
		
		filterDate_			= null;
		
		reportType_			= null;
		 
	}
	
	
}
