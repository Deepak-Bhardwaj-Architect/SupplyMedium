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


/**
 * File: CompanyFeedData.java
 *
 * Created on May 9, 2013
 */
package core.feed;

import java.sql.Timestamp;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;


/* 
 * This data class contain company feed data is related 
 * company_feed table in database.
 */

public class CompanyFeedData
{
	
	public long companyFeedId_;
	
	public UserProfileKey userKey_;
	public CompanyRegnKey regnKey_;
	
	public String userName_;
	public String userPictureUrl_;
	
	public String feedTitle_;
	public String feed_;
	
	public String relativePath_;
	public String localPath_;
	public String webURL_;
	
	public Timestamp createdTimestamp_;
	

	/*
	 * Method : CompanyFeedData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: Initialize the company registration key and user profile key
	 * 
	 */
	
	public CompanyFeedData( )
	{
		this.regnKey_   = new CompanyRegnKey( );
		this.userKey_   = new UserProfileKey( );
		
		companyFeedId_  	= -1;
		
		userName_ 		= null;
		userPictureUrl_ = null;
		
		
		feedTitle_		= null;
		feed_			= null;
		
		relativePath_	= null;
		localPath_ 		= null;
		webURL_			= null;
		
		
		createdTimestamp_ = null;
	}

	

	/*
	 * Method : show( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "userKey_			= " + userKey_.toString( ) );
		System.out.println( "regnKey_			= " + regnKey_.toString( ) );
		
		System.out.println( "userFeedId_		= " + companyFeedId_ );
		
		System.out.println( "userName           = "+ userName_);
		System.out.println( "userPictureUrl_    = "+ userPictureUrl_);
		
		System.out.println( "feedTitle_			= " + feedTitle_ );
		System.out.println( "feed_				= " + feed_ );
		
		System.out.println( "relative path		= " + relativePath_ );
		System.out.println( "local path         = " + localPath_);
		System.out.println( "web url_           = " +webURL_);
		
		System.out.println( "created TimeStamp  = " +createdTimestamp_ );		
		
	}

	/*
	 * Method : clear( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		companyFeedId_  	= 0;
		userKey_		= null;
		regnKey_		= null;
		
		userName_ 		= null;
		userPictureUrl_ = null;
		
		
		feedTitle_		= null;
		feed_			= null;
		
		relativePath_	= null;
		localPath_		= null;
		webURL_			= null;
		
		
		createdTimestamp_ = null;
	}
}
