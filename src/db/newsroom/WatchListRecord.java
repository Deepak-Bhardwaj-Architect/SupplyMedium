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
package db.newsroom;

import java.sql.Timestamp;

import core.regn.UserProfileKey;

/**
 * File:  WatchListRecord.java 
 *
 * Created on 30-Aug-2013 4:04:36 PM
 */
public class WatchListRecord
{
	// Unique id of the watch list. It is auto incremented.
	public long watchListId_;
	
	// Watchlist owner user key
	public String userKey_;
	
	// Company regn key of the watchlist owner
	public String regnKey_;
	
	// Name of the watchlist
	public String watchListName_;
	
	// created time stamp of the watchlist
	public Timestamp createdTimestamp_;
	

	/*
	 * Method : WatchListRecord -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WatchListRecord()
	{
		watchListId_ = -1;
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
		System.out.println( "watchListId 		=" +watchListId_);
		
		System.out.println( "userKey     		=" +userKey_);
		
		System.out.println( "regnKey     		=" +regnKey_);
		
		System.out.println( "watchListName 		=" +watchListName_);
			
		System.out.println( "CreatedTimestamp 	=" +createdTimestamp_);
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
		watchListId_ = -1;
		
		userKey_ = null;
		
		regnKey_ = null;
		
		watchListName_ = null;
		
		createdTimestamp_ = null;
	}
}
