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
import utils.ErrorMaster;


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
        private static ErrorMaster errorMaster_ = null;


	

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
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( "userKey_			= " + userKey_.toString( ) );
		errorMaster_.insert( "regnKey_			= " + regnKey_.toString( ) );
		
		errorMaster_.insert( "userFeedId_		= " + companyFeedId_ );
		
		errorMaster_.insert( "userName           = "+ userName_);
		errorMaster_.insert( "userPictureUrl_    = "+ userPictureUrl_);
		
		errorMaster_.insert( "feedTitle_			= " + feedTitle_ );
		errorMaster_.insert( "feed_				= " + feed_ );
		
		errorMaster_.insert( "relative path		= " + relativePath_ );
		errorMaster_.insert( "local path         = " + localPath_);
		errorMaster_.insert( "web url_           = " +webURL_);
		
		errorMaster_.insert( "created TimeStamp  = " +createdTimestamp_ );		
		
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
