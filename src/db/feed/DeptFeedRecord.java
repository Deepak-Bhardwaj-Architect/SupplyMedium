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
		System.out.println( "deptFeedId_	= " + deptFeedId_ );
		System.out.println( "regnRelKey_	= " + regnRelKey_ );
		System.out.println( "deptRelKey_	= " + deptRelKey_ );

		System.out.println( "userRelKey_	= " + userRelKey_ );
		System.out.println( "feedTitle_			= " + feedTitle_ );
		System.out.println( "feed_			= " + feed_ );

		System.out.println( "companyFeedFlag_	= " + companyFeedFlag_ );
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
		regnRelKey_ = null;
		deptRelKey_ = null;

		userRelKey_ = null;
		feedTitle_ = null;
		feed_ = null;

		companyFeedFlag_ = 0;
		createdTs_ = null;
	}

}
