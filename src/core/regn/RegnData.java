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

import java.sql.Timestamp;
import java.util.List;


/**
 * File:  RegnData.java 
 *
 * Created on Jan 22, 2013 11:27:28 AM
 */

//This core data class is contain the all the company registeration details

public class RegnData
{

	public String                   logo_;
	public String                   companyName_;
	public String                   businessCategory_;
	public String 					companyId_;
	
	public Boolean					 isRegnVendor_ ;

	public String                   branch_;
	public List<MailingAddressData> mailingAddressArr_;
	public String                   signUpAs_;
	public String                   firstName_;

	public String                   lastName_;
	public String                   title_;
	public String                   department_;

	public String                   managerSupervisor_;
	public String                   phoneNo_;
	public String                   cellNo_;

	public String                   faxNo_;
	public String                   emailId_;
	public String                   password_;

	public String                   uuid_;
	public String                   segmentDivisionName_;

	public String                   businessUnitName_;
	public String                   pricingOption_;

	public String                   theme_;
	public String                   userType_;
	public String                   companyStatus_;

	public CompanyRegnKey           companyRegnKey_;
	public Timestamp                createdDate_;

	public long                     maxEmployee_;
	public long                     maxTransaction_;
	
	public String 					companyType_;
	
	public Object					logoImage_;
	
	
	public RegnData( )
	{
		
			
		logo_ 				= null ;
		companyName_ 		= null ; 
		businessCategory_ 	= null ;

		isRegnVendor_ 		= false;
		branch_ 			= null;
		mailingAddressArr_ 	= null;

		signUpAs_ 			= null;
		firstName_ 			= null;

		lastName_ 			= null;
		title_ 				= null;
		department_ 		= null;

		managerSupervisor_ 	= null;
		phoneNo_ 			= null;
		cellNo_ 			= null;

		faxNo_ 				= null;
		emailId_ 			= null;
		password_ 			= null;

		uuid_ 				= null;
		segmentDivisionName_ = null;

		businessUnitName_	= null;
		pricingOption_ 		= null;

		companyRegnKey_ 	= null;
		createdDate_ 		= null;
		
		maxEmployee_		= -1;
		maxTransaction_		= -1;
				
		
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
		this.logo_ 				= null;
		this.companyName_ 		= null;
		this.businessCategory_ 	= null;

		this.branch_ 			= null;
		this.mailingAddressArr_ = null;

		this.signUpAs_ 			= null;
		this.firstName_ 		= null;

		this.lastName_ 			= null;
		this.title_ 			= null;
		this.department_ 		= null;

		this.managerSupervisor_ = null;
		this.phoneNo_ 			= null;
		this.cellNo_ 			= null;

		this.faxNo_ 			= null;
		this.emailId_ 			= null;
		this.password_ 			= null;

		this.uuid_ 				= null;
		this.segmentDivisionName_ = null;

		this.businessUnitName_	= null;
		this.pricingOption_ 	= null;

		this.companyRegnKey_ 	= null;
		this.createdDate_ 		= null;
		
		this.companyType_		= null;
		this.logoImage_ 		= null;
	}

	/*
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "logo_				= " + logo_ );
		System.out.println( "companyName_		= " + companyName_ );
		System.out.println( "businessCategory_	= " + businessCategory_ );

		System.out.println( "branch_			= " + branch_ );
		System.out.println( "mailingAddressArr_	= " + mailingAddressArr_ );

		System.out.println( "signUpAs_			= " + signUpAs_ );
		System.out.println( "firstName_			= " + firstName_ );

		System.out.println( "lastName_			= " + lastName_ );
		System.out.println( "title_				= " + title_ );
		System.out.println( "department_		= " + department_ );

		System.out.println( "managerSupervisor_	= " + managerSupervisor_ );
		System.out.println( "phoneNo_			= " + phoneNo_ );
		System.out.println( "cellNo_			= " + cellNo_ );

		System.out.println( "faxNo_				= " + faxNo_ );
		System.out.println( "emailId_			= " + emailId_ );
		System.out.println( "password_			= " + password_ );

		System.out.println( "uuid_					= " + uuid_ );
		System.out.println( "segmentDivisionName_	= " + segmentDivisionName_ );

		System.out.println( "businessUnitName_	= " + businessUnitName_ );
		System.out.println( "pricingOption_		= " + pricingOption_ );
		System.out.println( "companyregnKey		= " + companyRegnKey_ );

		System.out.println( "createdDate_		= " + createdDate_ );
		System.out.println( "maxEmployee_		= " + maxEmployee_ );
		System.out.println( "maxTransaction_	= " + maxTransaction_ );
		
		System.out.println( "companyType_	= " + companyType_ );
		
	}
}
