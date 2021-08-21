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
package ctrl.companyprofile;

import javax.servlet.http.HttpServletRequest;

import utils.IntHolder;
import core.companyprofile.CompanyProfileData;
import core.companyprofile.CompanyProfileOperation;
import core.regn.MailingAddressData;
import core.regn.MailingAddressKey;

/**
 * @FileName : CompanyProfileController.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Mar 13, 2013
 * 
 * @Purpose : This Controller will handle the Add/Remove Mail Address & Update
 *          the Company Profile
 * 
 */

public class CompanyProfileController
{

	/**
	 * @Date : Mar 21, 2013 7:52:12 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Adding the Mailing Address to the Database
	 * 
	 * @param request
	 * @param mailingAdd_No
	 * @return
	 * 
	 */
	public int addMailingAddress( HttpServletRequest request, MailingAddressKey mailingAdd_No )
	{

		int result = 0;

		MailingAddressData maildata = new MailingAddressData( );
		MailAddressDataConvertor covertobj = new MailAddressDataConvertor( );
		result = covertobj.convert( request, maildata );
		covertobj = null;

		if( result == 0 )
		{
			CompanyProfileOperation process = new CompanyProfileOperation( );
			result = process.add(maildata, mailingAdd_No );

			if( result == 0 )
			{
				return 2100; // Sucess
			}
		}

		return 2101; // For Error
	}

	/**
	 * @Date : Mar 13, 2013 6:53:50 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Calling the Remove Maill Address Core
	 * 
	 * @param addressID
	 * @return
	 * 
	 */
	public int removeMailingAddress( MailingAddressKey addressID )
	{
		int result = 0;

		CompanyProfileOperation process = new CompanyProfileOperation( );
		result = process.remove(addressID );

		if( result == 0 )
		{
			return 2200;
		}

		return 2201;
	}

	/**
	 * @Date : Mar 14, 2013 11:38:09 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Update the Comapny Profile information in the Table
	 * 
	 * @param profileData
	 * @return
	 * 
	 */
	public int updateCompanyProfile( HttpServletRequest request )
	{
		int result = 0;

		CompanyProfileData profiledata = new CompanyProfileData( );
		CompanyProfileDataConvertor profileConvert = new CompanyProfileDataConvertor( );
		result = profileConvert.convert( request, profiledata );
		profileConvert = null;

		if( result == 0 )
		{
			CompanyProfileOperation process = new CompanyProfileOperation( );
			result = process.update( profiledata);
			process=null;
			
			if( result == 0 ) return 2300; // Failed

		}

		return 2301; // Error
	}

}
