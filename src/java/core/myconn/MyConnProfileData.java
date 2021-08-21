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
import utils.ErrorMaster;

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
        
        private static ErrorMaster errorMaster_ = null;



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
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( " name_				=" + name_ );
		errorMaster_.insert( " email_			=" + email_ );
		errorMaster_.insert( " department_		=" + department_ );
		
		errorMaster_.insert( " role_				=" + role_ );
		errorMaster_.insert( " businessCategory_	=" + businessCategory_ );
		errorMaster_.insert( " address_			=" + address_ );
		
		errorMaster_.insert( " CompanyName_      =" + companyName_);
		errorMaster_.insert( " CompanyType_      =" + companyType_);
		
		errorMaster_.insert( " phoneNo_			=" + phoneNo_ );
		errorMaster_.insert( " cell_				=" + cell_ );
		errorMaster_.insert( " fax_				=" + fax_ );
		
		errorMaster_.insert( " profileImagePath_	=" + profileImagePath_ );
		errorMaster_.insert( " regnkey_          =" + regnKey_.companyPhoneNo_);
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
