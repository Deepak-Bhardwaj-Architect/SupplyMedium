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
 * @FileName : RatingsSummaryRecord.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Aug 28, 2013
 * 
 * @Purpose :
 * 
 */
public class RatingsSummaryRecord
{
	public String	 _regn_rel_key;
	public Timestamp	_first_rating_timestamp;
	public Timestamp	_last_rating_timestamp;
	public int	     _avg_ratings;
	public int	     _no_of_ratings;

	/**
	 * @Date : Aug 28, 2013 12:41:47 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Show the Class Information
	 * 
	 * 
	 */
	public void Show( )
	{
             ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "_regn_rel_key =" + _regn_rel_key );
		errorMaster_.insert( "_first_rating_timestamp =" + _first_rating_timestamp );
		errorMaster_.insert( "_last_rating_timestamp =" + _last_rating_timestamp );
		errorMaster_.insert( "_avg_ratings =" + _avg_ratings );
		errorMaster_.insert( "no_of_ratings =" + _no_of_ratings );
	}

	/**
	 * @Date : Aug 28, 2013 12:42:22 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Clear the Data's from the class
	 * 
	 * 
	 */
	public void Clear( )
	{
		_regn_rel_key = "";
		_first_rating_timestamp = null;
		_last_rating_timestamp = null;
		_avg_ratings = 0;
		_no_of_ratings = 0;

	}

}
