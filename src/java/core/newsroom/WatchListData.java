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
package core.newsroom;

import java.sql.Timestamp;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File:  WatchListData.java 
 *
 * Created on 30-Aug-2013 5:11:57 PM
 */
public class WatchListData
{
	// Id of the watchlist. This is the auto incremented id
	public long watchListId_;
	
	// Owner key of the watchlist. Owner who is creater of the watchlist
	public UserProfileKey ownerKey_;
	
	// Owner company company registration key
	public CompanyRegnKey regnKey_;
	
	// Name of the watchlist
	public String watchListName_;
	
	// Watchlist created time
	public Timestamp createdTimestamp_;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : WatchListData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WatchListData()
	{
		ownerKey_ = new UserProfileKey( );
		
		regnKey_ = new CompanyRegnKey( );
		
		watchListId_ = -1;
                
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
		errorMaster_.insert( "watchListId    		= "+ watchListId_ );
		
		errorMaster_.insert( "ownerKey_    			= "+ ownerKey_.toString( ) );
		
		errorMaster_.insert( "regnKey_    			= "+ regnKey_.toString( ) );
		
		errorMaster_.insert( "watchListName_    		= "+ watchListName_ );
		
		errorMaster_.insert( "createdTimestamp_    	= "+ createdTimestamp_ );
		
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
		watchListId_ 		= -1;
		
		ownerKey_ 			= null;
		
		regnKey_ 			= null;
		
		watchListName_ 		= null;
		
		createdTimestamp_ 	= null;
		
	}

}
