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
	public CompanyRegnKey	_company_regn_key;
	public String	      _review_title;
	public Timestamp	  _reviewdate;
	public String	      _reviewdateFormated;
	public int	          _ratings;
	public String	      _comments;

	public UserRatingsData()
	{
		this._user_profile_key = new UserProfileKey( );
		this._regn_rel_key = new CompanyRegnKey( );
		this._company_regn_key = new CompanyRegnKey( );
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
		System.out.println( " _rattings_key =" + _rattings_key );
		System.out.println( " _regn_rel_key =" + _regn_rel_key.companyPhoneNo_ );
		System.out.println( " _user_profile_key =" + _user_profile_key.email_ );
		System.out.println( " _reviewer_name =" + _reviewer_name );
		System.out.println( " _company_regn_key =" + _company_regn_key.companyPhoneNo_ );
		System.out.println( " _review_title =" + _review_title );
		System.out.println( " _reviewdate =" + _reviewdate );
		System.out.println( " _ratings =" + _ratings );
		System.out.println( " _comments =" + _comments );
		System.out.println( " _reviewdateFormated =" + _reviewdateFormated );
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
		_company_regn_key = null;
		_review_title = "";
		_reviewdate = null;
		_ratings = 0;
		_comments = "";
		_reviewdateFormated = "";
	}

}
