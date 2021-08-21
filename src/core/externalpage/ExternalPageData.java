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
package core.externalpage;

import core.regn.CompanyRegnKey;

/**
 * @FileName : ExternalPageRecord.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 5, 2013
 * 
 * @Purpose : This class used for storing the External Page Basic information
 *          Storing The data
 * 
 */
public class ExternalPageData
{
	public int	          externalpageid_;
	public CompanyRegnKey	compnayRegnKey_;
	public String	      companyURLName_;
	public String	      pageType_;
	public String	      externalPageURL_;

	public ExternalPageData()
	{
		this.compnayRegnKey_ = new CompanyRegnKey( );
	}

	public void show( )
	{
		System.out.println( "External Page ID :" + this.externalpageid_ );
		System.out.println( "Comapny Regn Key :" + this.compnayRegnKey_ );
		System.out.println( "Company URL Name :" + this.companyURLName_ );
		System.out.println( "Company Page Type  :" + this.pageType_ );
		System.out.println( "External Page URL  :" + this.externalPageURL_ );
	}

	public void clear( )
	{
		this.externalpageid_ = 0;
		this.companyURLName_ = null;
		this.compnayRegnKey_ = null;
		this.pageType_ = null;
		this.externalPageURL_ = null;
	}
}
