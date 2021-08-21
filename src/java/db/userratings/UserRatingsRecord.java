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
package db.userratings;

import java.sql.Timestamp;
import utils.ErrorMaster;

/**
 * @FileName : RatingsRecord.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Aug 24, 2013
 * 
 * @Purpose : Ratings Table Class
 * 
 */
public class UserRatingsRecord
{
	int	      _rattings_key;
	String	  _regn_rel_key;
	String	  _user_profile_key;
	String	  _reviewer_name;
	String	  _company_regn_key;
	String	  _review_title;
	String	  _comments;
	Timestamp	_reviewdate;
	int	      _ratings;

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
             ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( " _rattings_key =" + _rattings_key );
		errorMaster_.insert( " _regn_rel_key =" + _regn_rel_key );
		errorMaster_.insert( " _user_profile_key =" + _user_profile_key );
		errorMaster_.insert( " _reviewer_name =" + _reviewer_name );
		errorMaster_.insert( " _company_regn_key =" + _company_regn_key );
		errorMaster_.insert( " _reviewtitle =" + _review_title );
		errorMaster_.insert( " _reviewdate =" + _reviewdate );
		errorMaster_.insert( " _ratings =" + _ratings );
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
		_regn_rel_key = "";
		_user_profile_key = "";
		_reviewer_name = "";
		_company_regn_key = "";
		_review_title = "";
		_reviewdate = null;
		_ratings = 0;
	}

}
