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

package core.policies;

import core.regn.CompanyRegnKey;
import db.config.PricingOptionTable;
import db.regn.CompanyRegnTable;
import db.regn.UserProfileTable;
import utils.ErrorLogger;
import utils.ErrorMaster;
import utils.PricingOption;

/**
 * File: MemberLimiter.java
 * 
 * Created on Feb 20, 2013 11:27:28 AM
 */

public class MemberLimiter implements AccountLimiter

{
    
    private static ErrorMaster errorMaster_ = null;


	/*
	 * Method : isAllowed( ) 
	 * 
	 * Input : company registeration key 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check company can add the user. It return the result
	 * according to company pricing option and number of users already available.
	 */

	public int isAllowed( CompanyRegnKey companyRegnKey )
	{

		long memberCount = 0;

		long maxMemberCount = 0;

		String pricingOptionKey = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		try
		{
			CompanyRegnTable companyRegnTbl = new CompanyRegnTable( );

			pricingOptionKey = companyRegnTbl.getCompanyPricingOption( companyRegnKey );
			
			companyRegnTbl = null;

			if( PricingOption.option.PREMIUM.getValue( ).equals( pricingOptionKey ) )
			{
				return 0; // Because premium company can add unlimited members.
			}
		}
		catch( Exception ex )
		{
			String errorMessage = "Exception::MemberLimiter.isAllowed() - " + ex;

			errLogger.logMsg( errorMessage );

			return -1; // SqlException
		}

		try
		{
			PricingOptionTable pricingOptionTbl = new PricingOptionTable( );

			maxMemberCount = pricingOptionTbl.getMaxMembers( pricingOptionKey );

			pricingOptionTbl = null;

		}
		catch( Exception ex )
		{
			String errorMessage = "Exception :: MemberLimiter : isAllowed - " + ex;

			errLogger.logMsg( errorMessage );

			return -1; // SqlException
		}

		try
		{
			UserProfileTable userProfileTbl = new UserProfileTable( );

			memberCount = userProfileTbl.getNoOfRegisteredUserForCompany( companyRegnKey );

			userProfileTbl = null;
		}
		catch( Exception ex )
		{
			String errorMessage = "Exception :: MemberLimiter : isAllowed - " + ex;

			errLogger.logMsg( errorMessage );

			return -1; // SqlException
		}

                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "membercount=" + memberCount + ",maxcount=" + maxMemberCount );

		if( memberCount >= maxMemberCount )
		{
			return -2;  // Maximum member limit reached, Not allowed more users.
		}

		return 0;   // Company can add users
	}

}
