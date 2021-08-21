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
package db.feed;

import java.sql.Timestamp;
import utils.ErrorMaster;

/**
 * File: DeptFeedRecord.java
 * 
 * Created on May 7, 2013 10:23:58 AM
 */

/*
 * Class: DeptFeedRecord
 * 
 * Purpose: This class maps the table 'dept_feed' of supply medium db
 */

public class DeptFeedRecord
{
	public long      deptFeedId_; 
	public String    regnRelKey_;
	public String    deptRelKey_;

	public String    userRelKey_;
	public String    feedTitle_;
	public String    feed_;

	public int       companyFeedFlag_;
	public Timestamp createdTs_;

	/* Constructor */

	public DeptFeedRecord()
	{
		deptFeedId_ = 0;
		regnRelKey_ = "";
		deptRelKey_ = "";

		userRelKey_ = "";
		feedTitle_ = "";
		feed_ = "";

		companyFeedFlag_ = 0;
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
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "deptFeedId_	= " + deptFeedId_ );
		errorMaster_.insert( "regnRelKey_	= " + regnRelKey_ );
		errorMaster_.insert( "deptRelKey_	= " + deptRelKey_ );

		errorMaster_.insert( "userRelKey_	= " + userRelKey_ );
		errorMaster_.insert( "feedTitle_			= " + feedTitle_ );
		errorMaster_.insert( "feed_			= " + feed_ );

		errorMaster_.insert( "companyFeedFlag_	= " + companyFeedFlag_ );
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
		regnRelKey_ = null;
		deptRelKey_ = null;

		userRelKey_ = null;
		feedTitle_ = null;
		feed_ = null;

		companyFeedFlag_ = 0;
		createdTs_ = null;
	}

}
