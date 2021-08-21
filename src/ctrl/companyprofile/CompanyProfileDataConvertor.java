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

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import utils.ErrorLogger;
import core.companyprofile.CompanyProfileData;
import core.login.SessionData;
import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;

/**
 * @FileName	: CompanyProfileDataConvertor.java
 *
 * @author 		: Anbazhagan
 *
 * @Date 		: Mar 14, 2013
 *
 * @Purpose 		: Convert the HTTPServlet Request to Company Profile Data
 *
 */
public class CompanyProfileDataConvertor
{
	public int convert(HttpServletRequest request,CompanyProfileData profiledata)
	{
		try
		{
			Map<String,String[]> reqMap=request.getParameterMap( );
			
			
			
			if(reqMap.containsKey( "companyname" ))
			{
				profiledata.companyName_=request.getParameter("companyname");
			}
			
			if(reqMap.containsKey( "signupas" ))
			{
				profiledata.signupas_=request.getParameter( "signupas" );
				System.out.println( profiledata.signupas_);
			}
			
			if(reqMap.containsKey( "businesscategory" ))
			{
				profiledata.businessCategory_=request.getParameter("businesscategory");
			}			
			
			if(reqMap.containsKey( "companyid" ))
			{
				profiledata.companyID_=request.getParameter("companyid");
			}
			
			//CompanyRegnKey regnKey = new CompanyRegnKey( );
			
			HttpSession session = request.getSession( );
			
			SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
			
			System.out.println( "session data="+sessionData );
			
			//regnKey.companyPhoneNo_ = sessionData.companyRegnKeyStr_;
			
			profiledata.companyRegnKey_=sessionData.companyRegnKeyStr_;
			
			if(reqMap.containsKey( "plan" ))
			{
				profiledata.pricingOption_=request.getParameter("plan");
			}
			
			if(reqMap.containsKey( "themeSelect" ))
			{
				profiledata.theme_=request.getParameter("themeSelect");
			}			
			
			
			// Mail Address Converter
			int Result;
			MailingAddressData maildata=new MailingAddressData( );
			MailAddressDataConvertor convert=new MailAddressDataConvertor();
			
			Result=convert.convert(request, maildata );
			
			maildata.show( );
			//System.out.println(Result );
			if(Result==0)
			{
				profiledata.mailingAddressArr_=new ArrayList<MailingAddressData>();
				profiledata.mailingAddressArr_.add( maildata );
			}
			
			return 0;
		}
		catch(Exception ex)
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::CompanyProfileDataConvertor::Convert:: " + ex.getMessage( ) );			
			return -1;
		}
	}
}
