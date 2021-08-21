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
package core.companyprofile;

import core.regn.CCMapperData;
import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;
import core.regn.MailingAddressKey;
import core.regn.RegnData;
import core.regn.RegnInsert;
import db.regn.CCMapperTable;
import db.regn.CompanyRegnTable;
import db.regn.MailingAddressTable;

/**
 * @FileName : CompanyProfileOperation.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Mar 13, 2013
 * 
 * @Purpose : Adding the maill Address to the Database
 * 
 */
public class CompanyProfileOperation
{
	/**
	 * @Date : Mar 13, 2013 6:47:54 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Adding Maill Address to the mailing Address Table and return
	 *          the Insert ID
	 * 
	 * @param mailData
	 * @param addrNo
	 * @return
	 * 
	 */
	public int add( MailingAddressData mailData, MailingAddressKey mailkey )
	{
		int result;
		MailingAddressTable mailTableObj = new MailingAddressTable( );
		result = mailTableObj.insert( mailData,mailkey);
		// For Updating the Information
		MailingAddressData mailaddrData=new MailingAddressData( );
		mailaddrData.mailkey_=mailkey;				
		mailTableObj.update( mailkey, mailaddrData );
		return result;
	}

	/**
	 * @Date : Mar 13, 2013 6:48:31 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Delete the Mail Address Based on the Address Id
	 * 
	 * @param MailAddressID
	 * @return
	 * 
	 */
	public int remove( MailingAddressKey mailKey)
	{
		MailingAddressTable mailTableObj = new MailingAddressTable( );
		int result = mailTableObj.delete( mailKey );

		return result;
	}

	/**
	 * @Date : Mar 14, 2013 12:26:47
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Update the Company Profile information in the Database
	 * 
	 * @param profiledata
	 * @return
	 * 
	 */
	public int update( CompanyProfileData profiledata )
	{
		int result = 0;

		CompanyRegnTable regnTable = new CompanyRegnTable( );
		RegnData regnData=new RegnData( );
		convert( profiledata, regnData );
		CompanyRegnKey key=new CompanyRegnKey( profiledata.companyRegnKey_);
		result = regnTable.update(key,regnData);
                
                RegnInsert regnInsert = new RegnInsert( );
		
		/*****************Stroing logo image ****************************/
		
		int logoVal = regnInsert.storeLogoImage( regnData );
		
		CCMapperData ccMapperData = new CCMapperData();
		ccMapperData.companyname_ = profiledata.companyName_;
		
		CompanyRegnKey regnKey = new CompanyRegnKey();
		regnKey.companyPhoneNo_ = profiledata.companyRegnKey_;
		ccMapperData.companyRegnKey_ = regnKey;
		
		if( profiledata.mailingAddressArr_ != null )
			ccMapperData.countryname_ = profiledata.mailingAddressArr_.get(0).countryRegion_;
		
		CCMapperTable ccMapperTbl = new CCMapperTable();
		ccMapperTbl.update(ccMapperData);
		

		if(profiledata.mailingAddressArr_!=null)
		{
			MailingAddressTable mailTable = new MailingAddressTable( );
			mailTable.update(profiledata.mailingAddressArr_.get( 0 ).mailkey_,profiledata.mailingAddressArr_.get( 0 ) );
		}

		return result;
	}
	
	/**
	 * @Date	: Mar 23, 2013 2:52:52 PM
	 *
	 * @Return 	: void
	 *
	 * @Purpose	: Convert CompanyProfileData to RegnData
	 *
	 * @param profilData
	 * @param regndata
	 *
	 */
	private void convert(CompanyProfileData profilData,RegnData regndata)
	{
		regndata.signUpAs_=profilData.signupas_;
		regndata.businessCategory_=profilData.businessCategory_;
		regndata.companyId_=profilData.companyID_;
		regndata.companyName_=profilData.companyName_;
                regndata.logoPath_=profilData.logoPath_;
		regndata.companyRegnKey_=new CompanyRegnKey(profilData.companyRegnKey_ );;
		regndata.pricingOption_=profilData.pricingOption_;
		regndata.theme_=profilData.theme_;	
	}
}
