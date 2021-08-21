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

import core.regn.CompanyRegnKey;
import utils.ErrorMaster;

/**
 * @FileName : CompanyTotalRatingsData.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Aug 24, 2013
 * 
 * @Purpose :
 * 
 */
public class CompanyTotalRatingsData
{
	public CompanyRegnKey	_regn_rel_key;
	public String	      _logoPath;
	public int	          _rating;
	public String	      _company_name;
        public String	      _company_id;
	public int	          _avgRating;
        private static ErrorMaster errorMaster_ = null;



	/**
	 * @Date : Aug 24, 2013 3:13:47 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Show the Domain Data
	 * 
	 * 
	 */
	public void Show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( " _regn_rel_key = " + _regn_rel_key );
		errorMaster_.insert( " _logoPath = " + _logoPath );
		errorMaster_.insert( " _rating = " + _rating );
		errorMaster_.insert( " _company_name = " + _company_name );
		errorMaster_.insert( " _avgRating = " + _avgRating );
	}

	/**
	 * @Date : Aug 24, 2013 3:13:57 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Clear the Domain Data
	 * 
	 * 
	 */
	public void Clear( )
	{
		_regn_rel_key = null;
		_logoPath = "";
		_rating = 0;
		_company_name = "";
                _company_id = "";
		_avgRating = 0;
	}
}
