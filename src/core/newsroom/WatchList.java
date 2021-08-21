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

import java.util.List;

import core.regn.UserProfileKey;
import db.newsroom.WatchListTable;

/**
 * File:  WatchList.java 
 *
 * Created on 30-Aug-2013 5:12:09 PM
 */
public class WatchList
{

	/*
	 * Method : WatchList -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WatchList()
	{
		
	}
	
	/*
	 * Method : add
	 * 
	 * Input  : WatchListData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: Used to add the new watchlist for given user key. 
	 */
	
	public int add( WatchListData watchListData)
    {
		WatchListTable watchListTable = new WatchListTable( );
		
		int result = watchListTable.isWatchListExist( watchListData );
		
		if( result == 1 )
			return 10103;  // WatchList already exist
		
		result = watchListTable.insert( watchListData );
		
		watchListTable = null;
		
		if( result == 0 )
			return 10100;  // WatchList successfully created
		else
			return 10104;  // Watch list creation failed 
		
    }

	/*
	 * Method : delete
	 * 
	 * Input  : watchlist id
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It delete the watchlist using given watchlist id. Also it remove the 
	 * watchlist member relationship
	 */
	
	public int remove( long watchListId )
    {
		WatchListTable watchListTable = new WatchListTable( );
		
		int result = watchListTable.delete( watchListId );
		
		watchListTable = null;
		
		if( result == 0 )
		{
			// delete the watch list members from watchlist member mapper table
			
			return 10110;
		}
		
		return 0;
	    
    }
	
	/*
	 * Method : get
	 * 
	 * Input  : UserProfileKey object, list of WatchListData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It get all the watchlist for given userprofilekey. And copied to watchlists parameter.
	 * So it is available for caller classes
	 */
	
	public int get( UserProfileKey userKey, List<WatchListData> watchLists )
	{
		
		WatchListTable watchListTable = new WatchListTable( );
		
		WatchListData watchListData = new WatchListData( );
		
		watchListData.ownerKey_ = userKey;
		
		int result = watchListTable.find( watchListData, watchLists );
		
		watchListData = null;
		
		watchListTable = null;
		
		if( result == 0 )
			return 10120;
		else 
			return 10121;
		
		
	}
}
