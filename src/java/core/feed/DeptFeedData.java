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
import utils.ErrorMaster;

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
        
        private static ErrorMaster errorMaster_ = null;



	
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
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( "feedId_	= " + deptFeedId_ );
		
		errorMaster_.insert( "regnKey_	= " + regnKey_.toString( ) );
		errorMaster_.insert( "userKey_	= " + userKey_.email_ );
		errorMaster_.insert( "deptKey_	= " + deptKey_.companyRegnKey_ + ":"
		        + deptKey_.deptName_ );

		errorMaster_.insert( "feedTitle_	= " + feedTitle_ );
		errorMaster_.insert( "deptFeed_		= " + deptFeed_ );
		errorMaster_.insert( "companyFeedFlag_	= " + companyFeedFlag_ );

		errorMaster_.insert( "userName_  = "+userName_);
		errorMaster_.insert( "userPictureUrl_  = "+userPictureUrl_);
		errorMaster_.insert( "createdTs_		= " + createdTs_ );
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
