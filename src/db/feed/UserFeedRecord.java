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
		System.out.println( "userFeedId_		= " + userFeedId_ );
		System.out.println( "userKey_			= " + userKey_ );
		System.out.println( "regnKey_			= " + regnKey_ );
		
		System.out.println( "feedTitle_			= " + feedTitle_ );
		System.out.println( "feed_				= " + feed_ );
		
		System.out.println( "relativePath_      = "+ relativePath_);
		System.out.println( "localPath_         = "+ localPath_);
		System.out.println( "webURL_            = "+webURL_);
		
		
		
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
