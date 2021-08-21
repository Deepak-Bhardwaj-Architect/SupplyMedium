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
package core.myconn;

import core.regn.CompanyRegnKey;

/**
 * File:  MyConnProfileData.java 
 *
 * Created on 21-Aug-2013 2:43:45 PM
 */
public class MyConnProfileData
{
	public String name_;
	
	public String email_;
	
	public String department_;
	
	public String role_;
	
	public String companyName_;
	
	public String companyType_;
	
	public String businessCategory_;
	
	public String address_;
	
	public String phoneNo_;
	
	public String cell_;
	
	public String fax_;
	
	public String profileImagePath_;
	
	public CompanyRegnKey regnKey_;

	/*
	 * Method : MyConnProfileData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnProfileData()
	{
		
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
		System.out.println( " name_				=" + name_ );
		System.out.println( " email_			=" + email_ );
		System.out.println( " department_		=" + department_ );
		
		System.out.println( " role_				=" + role_ );
		System.out.println( " businessCategory_	=" + businessCategory_ );
		System.out.println( " address_			=" + address_ );
		
		System.out.println( " CompanyName_      =" + companyName_);
		System.out.println( " CompanyType_      =" + companyType_);
		
		System.out.println( " phoneNo_			=" + phoneNo_ );
		System.out.println( " cell_				=" + cell_ );
		System.out.println( " fax_				=" + fax_ );
		
		System.out.println( " profileImagePath_	=" + profileImagePath_ );
		System.out.println( " regnkey_          =" + regnKey_.companyPhoneNo_);
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
		name_ 				= null;
		email_ 				= null;
		department_ 		= null;
		
		role_ 				= null;
		businessCategory_ 	= null;
		address_ 			= null;
		
		companyName_ 		= null;
		companyType_		= null;
		
		phoneNo_ 			= null;
		cell_ 				= null;
		fax_ 				= null;
		
		profileImagePath_ 	= null;
		regnKey_ 			= null;
	}

}
