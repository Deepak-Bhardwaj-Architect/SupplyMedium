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
package core.userratings;

import java.sql.Timestamp;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * @FileName : RatingsData.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Aug 24, 2013
 * 
 * @Purpose :
 * 
 */
public class UserRatingsData
{
	public int	          _rattings_key;
	public CompanyRegnKey	_regn_rel_key;
	public UserProfileKey	_user_profile_key;
	public String	      _reviewer_name;
        public String	      _reviewer_id;
	public CompanyRegnKey	_company_regn_key;
	public String	      _review_title;
	public Timestamp	  _reviewdate;
	public String	      _reviewdateFormated;
	public int	          _ratings;
	public String	      _comments;
        private static ErrorMaster errorMaster_ = null;



	public UserRatingsData()
	{
		this._user_profile_key = new UserProfileKey( );
		this._regn_rel_key = new CompanyRegnKey( );
		this._company_regn_key = new CompanyRegnKey( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}

	/**
	 * @Date : Aug 24, 2013 1:57:28 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Show All the Properties of this Class
	 * 
	 * 
	 */
	public void Show( )
	{
		errorMaster_.insert( " _rattings_key =" + _rattings_key );
		errorMaster_.insert( " _regn_rel_key =" + _regn_rel_key.companyPhoneNo_ );
		errorMaster_.insert( " _user_profile_key =" + _user_profile_key.email_ );
		errorMaster_.insert( " _reviewer_name =" + _reviewer_name );
		errorMaster_.insert( " _company_regn_key =" + _company_regn_key.companyPhoneNo_ );
		errorMaster_.insert( " _review_title =" + _review_title );
		errorMaster_.insert( " _reviewdate =" + _reviewdate );
		errorMaster_.insert( " _ratings =" + _ratings );
		errorMaster_.insert( " _comments =" + _comments );
		errorMaster_.insert( " _reviewdateFormated =" + _reviewdateFormated );
	}

	/**
	 * @Date : Aug 24, 2013 1:57:52 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Clear All the Properties of this Class
	 * 
	 * 
	 */
	public void Clear( )
	{
		_rattings_key = 0;
		_regn_rel_key = null;
		_user_profile_key = null;
		_reviewer_name = "";
                _reviewer_id = "";
		_company_regn_key = null;
		_review_title = "";
		_reviewdate = null;
		_ratings = 0;
		_comments = "";
		_reviewdateFormated = "";
	}

}
