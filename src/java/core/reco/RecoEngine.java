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

package core.reco;

import java.util.ArrayList;
import java.util.List;

import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;
import core.regn.RegnData;
import core.regn.UserProfileKey;
import db.regn.CompanyRegnTable;
import db.regn.MailingAddressTable;
import utils.ErrorMaster;

/**
 * File:  RecoController.java 
 *
 * Created on May 6, 2013 3:24:11 PM
 */


/*
 * Class: RecoManager
 * 
 * Purpose: This is used to fetch the list of vendors (Suppliers and buyers) inorder
 * to show recommended buyers/suppliers
 * 
 */

public class RecoEngine
{
	private static ErrorMaster errorMaster_ = null;


	/*Constructor*/
	
	public RecoEngine( )
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method: getVendors
	 * 
	 * Input: CompanyRegnKey object,  List<RegnData> regnDataList
	 * 
	 * Return: int
	 * 
	 * Purpose: This method will fetch list of vendors. For a supplier, list of buyer based
	 * on his business category will be fetched.  For a buyer, list of suppliers based on
	 * his business category will be fetched.  For both supplier and buyers, list of buyers
	 * and suppliers will be fetched based on business category
	 * 
	 */
	
	public int getVendors( CompanyRegnKey regnKey, List<RegnData> regnDataList )
	{
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		
		RegnData regnData = new RegnData( );
		
		regnData.companyRegnKey_ = new CompanyRegnKey( );
		
		regnData.companyRegnKey_.companyPhoneNo_ = regnKey.companyPhoneNo_;
		
		int result = regnTable.find( regnKey, regnData );
		
		if( result != 0 )
		{
			regnData = null;
			
			regnTable = null;
			
			return 1799; //Failed to fetch company type and business category from company regn table
		}
		
		result = regnTable.find( regnData, regnDataList );
		
		if( result != 0)
		{
			regnData = null;
			
			regnTable = null;
			
			return 1798; //Failed to get the vendor list for the given company
		}
		
		regnTable = null;
		
		MailingAddressTable addressTable = new  MailingAddressTable( );
		
		for( RegnData data : regnDataList )
        {
			MailingAddressData addressData = new MailingAddressData( );
			
			addressData.companyRegnKey_ = new CompanyRegnKey( );
			
			addressData.companyRegnKey_.companyPhoneNo_ = data.phoneNo_;
			
			UserProfileKey key = new UserProfileKey( );
			
			key.email_ = data.emailId_;
			
			result = addressTable.find( key, addressData );
			
			errorMaster_.insert( "address result="+result );
			
			if( result != 0)
			{
				addressTable = null;
				
				addressData = null;
				
				key = null;
				
				return 1797; //Failed to fetch address details
			}
			
			//List<MailingAddressData> mailAddressList = new ArrayList<MailingAddressData>( );
			
			//mailAddressList.add( addressData );
			
			addressData.show( );
			
			data.mailingAddressArr_.add( addressData );
			
			addressData = null;
			
			key = null;
        }
		
		return 1795; //Sucess
		
	}
}
