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
package core.vendorregn;

import java.sql.Timestamp;

import core.regn.CompanyRegnKey;
import utils.ErrorMaster;


/**
 * File:  VendorInquireData.java 
 *
 * Created on May 25, 2013 4:22:13 PM
 */

/*
*
* Class: VendorInquireData
* 
* Purpose: This class contains the domain variables
* for vendor registration
*/

public class VendorInquireData
{
	public long vendorRegnId_;
	
	public CompanyRegnKey regnKey_;
	public CompanyRegnKey vendorRegnKey_;
	
	public String inquireDetails_;
	public Timestamp createdTs_;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Constructor
	 */
	
	public VendorInquireData( )
	{
		vendorRegnId_	= 0;
		
		vendorRegnKey_ 	= new CompanyRegnKey( );
		regnKey_		= new CompanyRegnKey( );
		createdTs_		= new Timestamp( System.currentTimeMillis( ) );	
		
		inquireDetails_	= "";
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
		errorMaster_.insert( "vendorRegnId_		= " + vendorRegnId_ );
		errorMaster_.insert( "vendorRegnKey_		= " + vendorRegnKey_.toString( ) );
		errorMaster_.insert( "regnKey_			= " + regnKey_.toString( ) );
		
		errorMaster_.insert( "createdTs_	  		= " + createdTs_ );
		
		errorMaster_.insert( "inquireDetails_	= " + inquireDetails_ );	
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
		vendorRegnId_	= 0;
		
		vendorRegnKey_	= null;
		regnKey_		= null;
		
		inquireDetails_	= null;
		createdTs_		= null;
	}
}
