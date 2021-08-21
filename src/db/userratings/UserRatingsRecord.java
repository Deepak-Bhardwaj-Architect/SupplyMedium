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
		System.out.println( " _rattings_key =" + _rattings_key );
		System.out.println( " _regn_rel_key =" + _regn_rel_key );
		System.out.println( " _user_profile_key =" + _user_profile_key );
		System.out.println( " _reviewer_name =" + _reviewer_name );
		System.out.println( " _company_regn_key =" + _company_regn_key );
		System.out.println( " _reviewtitle =" + _review_title );
		System.out.println( " _reviewdate =" + _reviewdate );
		System.out.println( " _ratings =" + _ratings );
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
