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

import utils.ErrorMaster;



/**
 * File:  UserFeedRecord.java 
 *
 * Created on 22-Apr-2013 4:31:49 PM
 */

/*
 * Class  : UserFeedRecord
 * 
 * Purpose: This DB record class is related to activation_pending table in db.
 * 
 */
public class UserFeedRecord
{

	public int userFeedId_;
	public String userKey_;
	public String regnKey_;
	
	public String feedTitle_;
	public String feed_;
	
	public String relativePath_;
	public String localPath_;
	public String webURL_;
	
	
	
	
	/*
	 * Method : UserFeedRecord -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public UserFeedRecord()
	{
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
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "userFeedId_		= " + userFeedId_ );
		errorMaster_.insert( "userKey_			= " + userKey_ );
		errorMaster_.insert( "regnKey_			= " + regnKey_ );
		
		errorMaster_.insert( "feedTitle_			= " + feedTitle_ );
		errorMaster_.insert( "feed_				= " + feed_ );
		
		errorMaster_.insert( "relativePath_      = "+ relativePath_);
		errorMaster_.insert( "localPath_         = "+ localPath_);
		errorMaster_.insert( "webURL_            = "+webURL_);
		
		
		
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
		userFeedId_  	= 0;
		userKey_		= null;
		regnKey_		= null;
		
		feedTitle_		= null;
		feed_			= null;
		
		relativePath_	= null;
		localPath_		= null;
		webURL_			= null;
		
	}

}
