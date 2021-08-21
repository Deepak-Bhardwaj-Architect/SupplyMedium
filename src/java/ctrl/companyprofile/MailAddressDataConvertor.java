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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import utils.ErrorLogger;

import core.login.SessionData;
import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;
import core.regn.MailingAddressKey;
import utils.ErrorMaster;

/**
 * @FileName : MaillAddressDataConvertor.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Mar 13, 2013
 * 
 * @Purpose : Converting the User Request to MaillAddress Data
 * 
 */
public class MailAddressDataConvertor
{
	/**
	 * @Date	: Mar 13, 2013 2:05:41 PM
	 *
	 * @Return 	: int
	 *
	 * @Purpose	: Convert the Request Data to Mail Address Data for Further Process
	 *
	 * @param request
	 * @param maildata
	 * @return
	 *
	 */
	public int convert(HttpServletRequest request,MailingAddressData maildata)
	{
		try
		{
			Map<String, String [ ]> reqMap=request.getParameterMap( );
			
                        ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );

			if(reqMap.containsKey( "address" ))
			{
				maildata.address_=request.getParameter("address");
			}
			
			if(reqMap.containsKey( "branch" ))
			{
				maildata.addressType_=request.getParameter("branch");
			}			
			
			if(reqMap.containsKey( "city" ))
			{
				maildata.city_=request.getParameter("city");
			}

			CompanyRegnKey companyKey = new CompanyRegnKey( );
			
			HttpSession session = request.getSession( );
			
			SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
			
			errorMaster_.insert( "session data="+sessionData );
			
			companyKey.companyPhoneNo_ = sessionData.companyRegnKeyStr_;

			
			maildata.companyRegnKey_= companyKey;
			
			maildata.emailid_ = sessionData.username_;
			
			if(reqMap.containsKey( "country" ))
			{
				maildata.countryRegion_=request.getParameter("country");
			}
			
			if( reqMap.containsKey( "state" ) )
			{
				maildata.state_ = request.getParameter( "state" );

			}
				
			
			if(reqMap.containsKey( "zipcode" ))
			{
				maildata.zipcode_=request.getParameter("zipcode");
				
			}		
			
			if(reqMap.containsKey( "address_id" ))
			{
				maildata.mailkey_=new MailingAddressKey(Long.parseLong(request.getParameter("address_id")));
			}
			
					
			return 0;
		}
		catch(Exception ex)
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception :: MaillAddressDataConvertor::convert " + ex.getMessage( ));
			
			return -1;
		}
	}
}
