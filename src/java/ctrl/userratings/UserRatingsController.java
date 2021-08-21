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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import utils.IntHolder;

import core.login.SessionData;
import core.regn.CompanyRegnKey;
import core.userratings.CompanyTotalRatingsData;
import core.userratings.RatingSummaryData;
import core.userratings.UserRatingsData;
import core.userratings.UserRatingsManager;

/**
 * @FileName : UserRatingsController.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Aug 28, 2013
 * 
 * @Purpose : It will handle all the request from the servelet
 * 
 */
public class UserRatingsController
{
	private int	               _result;
	private UserRatingsManager	_ratingManager;

	/**
	 * @Date : Aug 28, 2013 8:05:07 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Saving the User Post
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public int save( HttpServletRequest request,SessionData sessiondata,RatingSummaryData ratingSummaryData )
	{
		UserRatingsData data = new UserRatingsData( );
		UserRatingsConvertor convertor = new UserRatingsConvertor( );
		_result = convertor.Convert( request, data , sessiondata );
		data.Show( );
		if( _result == 0 )
		{
			_ratingManager = new UserRatingsManager( );
			_result = _ratingManager.save( data,ratingSummaryData );
		}
		else
		{
			_result = 10511;
		}

		return _result;
	}

	/**
	 * @Date : Aug 28, 2013 8:09:26 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : get Ratings List for the Company
	 * 
	 * @return
	 * 
	 */
	public int getRatingsList( HttpServletRequest request, List<UserRatingsData> ratingsdata )
	{
		CompanyRegnKey key = new CompanyRegnKey( );
		UserRatingsConvertor convertor = new UserRatingsConvertor( );
		_result = convertor.Convert( request, key );

		if( _result == 0 )
		{
			_ratingManager = new UserRatingsManager( );
			_result = _ratingManager.getRatingsList( key, ratingsdata );
		}
		else
		{
			_result = 10501;
		}

		return _result;
	}
	
	/**
	 * @Date	: Aug 28, 2013 8:17:40 PM
	 *
	 * @Return 	: int
	 *
	 * @Purpose	: get the Company Ratings List
	 *
	 * @param request
	 * @param ratingsdata
	 * @return
	 *
	 */
	public int getCompanyRatingList( HttpServletRequest request, List<CompanyTotalRatingsData> ratingsdata )
	{
		CompanyRegnKey key = new CompanyRegnKey( );
		UserRatingsConvertor convertor = new UserRatingsConvertor( );
		_result = convertor.Convert( request, key );

		if( _result == 0 )
		{
			_ratingManager = new UserRatingsManager( );
			_result = _ratingManager.getCompanyRatingList( key, ratingsdata );
		}
		else
		{
			_result = 10521;
		}

		return _result;
	}
}
