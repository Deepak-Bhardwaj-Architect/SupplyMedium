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
	public int	          _avgRating;

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
		System.out.println( " _regn_rel_key = " + _regn_rel_key );
		System.out.println( " _logoPath = " + _logoPath );
		System.out.println( " _rating = " + _rating );
		System.out.println( " _company_name = " + _company_name );
		System.out.println( " _avgRating = " + _avgRating );
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
		_avgRating = 0;
	}
}
