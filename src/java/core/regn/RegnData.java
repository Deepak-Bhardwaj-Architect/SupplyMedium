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
import utils.ErrorMaster;


/**
 * File:  RegnData.java 
 *
 * Created on Jan 22, 2013 11:27:28 AM
 */

//This core data class is contain the all the company registeration details

public class RegnData
{

	public String                   logo_;
        public String                   logoPath_;
	public String                   companyName_;
	public String                   businessCategory_;
        public String 				country_;
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
        
        private static ErrorMaster errorMaster_ = null;


	
	
	public RegnData( )
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
			
		logo_ 				= null ;
                logoPath_=null;
		companyName_ 		= null ; 
		businessCategory_ 	= null ;
                country_=null;

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
                this.logoPath_=null;
		this.companyName_ 		= null;
		this.businessCategory_ 	= null;
                this.country_=null;

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
		errorMaster_.insert( "logo_				= " + logo_ );
                errorMaster_.insert("logo_Path              =   "+logoPath_);
		errorMaster_.insert( "companyName_		= " + companyName_ );
		errorMaster_.insert( "businessCategory_	= " + businessCategory_ );

		errorMaster_.insert( "branch_			= " + branch_ );
		errorMaster_.insert( "mailingAddressArr_	= " + mailingAddressArr_ );

		errorMaster_.insert( "signUpAs_			= " + signUpAs_ );
		errorMaster_.insert( "firstName_			= " + firstName_ );

		errorMaster_.insert( "lastName_			= " + lastName_ );
		errorMaster_.insert( "title_				= " + title_ );
		errorMaster_.insert( "department_		= " + department_ );

		errorMaster_.insert( "managerSupervisor_	= " + managerSupervisor_ );
		errorMaster_.insert( "phoneNo_			= " + phoneNo_ );
		errorMaster_.insert( "cellNo_			= " + cellNo_ );

		errorMaster_.insert( "faxNo_				= " + faxNo_ );
		errorMaster_.insert( "emailId_			= " + emailId_ );
		errorMaster_.insert( "password_			= " + password_ );

		errorMaster_.insert( "uuid_					= " + uuid_ );
		errorMaster_.insert( "segmentDivisionName_	= " + segmentDivisionName_ );

		errorMaster_.insert( "businessUnitName_	= " + businessUnitName_ );
		errorMaster_.insert( "pricingOption_		= " + pricingOption_ );
		errorMaster_.insert( "companyregnKey		= " + companyRegnKey_ );

		errorMaster_.insert( "createdDate_		= " + createdDate_ );
		errorMaster_.insert( "maxEmployee_		= " + maxEmployee_ );
		errorMaster_.insert( "maxTransaction_	= " + maxTransaction_ );
		
		errorMaster_.insert( "companyType_	= " + companyType_ );
		
	}
}
