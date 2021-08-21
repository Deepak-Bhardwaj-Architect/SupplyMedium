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
package ctrl.userratings;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.login.SessionData;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.userratings.UserRatingsData;

/**
 * @FileName : UserRatingsConvertor.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Aug 28, 2013
 * 
 * @Purpose : This Class will Convert the User Request to UserRatingsData Class
 * 
 */
public class UserRatingsConvertor
{

	/**
	 * @Date : Aug 28, 2013 7:48:08 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : This function will perform the conversion operation
	 * 
	 * @param request
	 * @param data
	 * @return
	 * 
	 */
	public int Convert( HttpServletRequest request, UserRatingsData data, SessionData sessiondata )
	{
		try
		{
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			if( reqMap.containsKey( "rattings_key" ) )
			{
				data._rattings_key = Integer.parseInt( request.getParameter( "rattings_key" ) );
			}

			if( reqMap.containsKey( "regn_rel_key" ) )
			{
				data._regn_rel_key = new CompanyRegnKey( request.getParameter( "regn_rel_key" ) );
			}

			if( reqMap.containsKey( "user_profile_key" ) )
			{
				data._user_profile_key = new UserProfileKey( request.getParameter( "user_profile_key" ) );
			}

			data._reviewer_name = sessiondata.firstName_ + " " + sessiondata.lastName_;

			data._company_regn_key.companyPhoneNo_ = sessiondata.companyRegnKeyStr_;

			if( reqMap.containsKey( "review_title" ) )
			{
				data._review_title = request.getParameter( "review_title" );
				data._review_title = data._review_title.trim( );
			}

			if( reqMap.containsKey( "comments" ) )
			{
				data._comments = request.getParameter( "comments" );
				data._comments = data._comments.trim( );
			}

			Date currentDate = new Date( );
			data._reviewdate = new Timestamp( currentDate.getTime( ) );

			if( reqMap.containsKey( "ratings" ) )
			{
				data._ratings = Integer.parseInt( request.getParameter( "ratings" ) );
			}

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: UserRatingsConvertor : convert - " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

		return 0;
	}

	/**
	 * @Date : Aug 28, 2013 8:10:55 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : It will get the CompanyRegnKey form the Request
	 * 
	 * @param request
	 * @param key
	 * @return
	 * 
	 */
	public int Convert( HttpServletRequest request, CompanyRegnKey key )
	{
		try
		{
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			if( reqMap.containsKey( "regn_rel_key" ) )
			{
				key.companyPhoneNo_ = request.getParameter( "regn_rel_key" );
			}

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: UserRatingsConvertor : convert - " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

		return 0;
	}

}
