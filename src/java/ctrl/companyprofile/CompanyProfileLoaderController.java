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

import core.companyprofile.CompanyProfileData;
import core.companyprofile.CompanyProfileDetails;
import core.regn.CompanyRegnKey;

/**
 * @FileName	: CompanyProfileLoaderController.java
 *
 * @author 		: Anbazhagan
 *
 * @Date 		: Mar 3, 2013
 *
 * Purpose 		: Load the Company Profile Information
 *
 */
public class CompanyProfileLoaderController
{
	
	/**
	 * @Date	: Mar 3, 2013 9:35:05 AM
	 *
	 * @Return 	: int
	 *
	 * @Purpose	: Getting the Company Info Using the CompProfileGetInfo Core
	 *
	 * @param ComanyRegKey
	 *
	 */
	public int getCompanyInfo( CompanyRegnKey cmpkey,CompanyProfileData cmpdata )
	{
		int result;
		
		CompanyProfileDetails cmpinfo = new CompanyProfileDetails( );

		result = cmpinfo.getCompanyInfo(cmpkey,cmpdata );
		cmpinfo=null;
		
		if(result==0)
		{
			cmpdata.Show( );
			return 1400;
		}
		else 
			return 1401;
	}
	
	/*
	 * Method: getFullProfile
	 * 
	 * Input: CompanyRegnKey obj, CompanyProfileData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to company info and admin profile details
	 */
	
	public int getFullProfile( HttpServletRequest request, CompanyProfileData profileData)
	{
		CompanyRegnKey regnKey = new CompanyRegnKey( );
		
		regnKey.companyPhoneNo_ = request.getParameter( "RegnKey" );
		
		CompanyProfileDetails cmpInfo = new CompanyProfileDetails( );
		
		int result = cmpInfo.getFullProfile( regnKey, profileData );
		
		cmpInfo = null;
		
		if( result == 0 )
		{
			return 1410;
		}
		
		return 1411;
	}
}
