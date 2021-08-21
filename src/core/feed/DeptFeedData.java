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
package core.feed;

import java.io.StringWriter;
import java.sql.Timestamp;

import core.dept.DeptKey;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;

/**
 * File:  DeptFeedData.java 
 *
 * Created on 15-Jun-2013 11:45:26 AM
 */

/*
 * Class: DeptFeedData
 * 
 * Purpose: This is the domain class for performing dept page view's feed operations.
 */

public class DeptFeedData
{
	public long deptFeedId_;
	
	public CompanyRegnKey regnKey_;

	public UserProfileKey userKey_;

	public DeptKey        deptKey_;

	/* Title of the department feed */

	public String         feedTitle_;

	/* Actual feed text */

	public String         deptFeed_;

	/*
	 * This variable is used as boolean. If '0', then the feed will be shown
	 * only within the department. If '1' then the feed will be shown to the
	 * entire company (through company internal page)
	 */

	public int            companyFeedFlag_;

	/* Feed created timestamp */

	public Timestamp      createdTs_;
	
	/* Name of the user who is posting the feed to department */
	public String userName_;
	
	/* Feed publisher picture url */
	public String userPictureUrl_;

	
	/* Constructor */

	public DeptFeedData( )
	{
		deptFeedId_ = -1;
		
		regnKey_ = new CompanyRegnKey( );
		userKey_ = new UserProfileKey( );
		deptKey_ = new DeptKey( );

		feedTitle_ = "";
		deptFeed_ = "";
		companyFeedFlag_ = 0;
		
		userName_ = "";
		userPictureUrl_ = "";
		createdTs_ = null;
	}

	/*
	 * Method: show
	 * 
	 * Input: None
	 * 
	 * Return: None
	 * 
	 * Purpose: To display the class variables
	 */

	public void show( )
	{
		System.out.println( "feedId_	= " + deptFeedId_ );
		
		System.out.println( "regnKey_	= " + regnKey_.toString( ) );
		System.out.println( "userKey_	= " + userKey_.email_ );
		System.out.println( "deptKey_	= " + deptKey_.companyRegnKey_ + ":"
		        + deptKey_.deptName_ );

		System.out.println( "feedTitle_	= " + feedTitle_ );
		System.out.println( "deptFeed_		= " + deptFeed_ );
		System.out.println( "companyFeedFlag_	= " + companyFeedFlag_ );

		System.out.println( "userName_  = "+userName_);
		System.out.println( "userPictureUrl_  = "+userPictureUrl_);
		System.out.println( "createdTs_		= " + createdTs_ );
	}

	/*
	 * Method: clear
	 * 
	 * Input: None
	 * 
	 * Return: None
	 * 
	 * Purpose: To clear the class variables
	 */

	public void clear( )
	{
		deptFeedId_ = -1;
		
		regnKey_ = null;
		userKey_ = null;
		deptKey_ = null;

		feedTitle_ = null;
		feedTitle_ = null;
		deptFeed_ = null;

		userName_ = null;
		userPictureUrl_ = null;
		companyFeedFlag_ = 0;
		createdTs_ = null;
	}

}
