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
package ctrl.newsroom;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import core.newsroom.WatchList;
import core.newsroom.WatchListData;
import core.regn.UserProfileKey;

/**
 * File:  WatchListController.java 
 *
 * Created on 30-Aug-2013 5:54:31 PM
 */
public class WatchListController
{

	/*
	 * Method : WatchListController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WatchListController()
	{
		
		
	}
	
	/*
	 * Method : processWL
	 * 
	 * Input  : HttpServletRequest object, list of watchlistData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: This method is used to control the watchlist. It do the following operation in watchlist
	 * 
	 * 1. Create the new watchlist
	 * 2. Delete the watchlist
	 * 3. Get all the watchlist for given user key
	 * 
	 * 
	 */
	public int processWL( HttpServletRequest request, List<WatchListData> watchLists )
    {
		int result = 0;
		 
		 // Converting request object to WatchListData object
		 
		WatchListDataConverter converter = new WatchListDataConverter( );
		
		WatchListData watchListData = new WatchListData( );
		
		result = converter.convert( request, watchListData );
	   
		converter = null;
		
		if( result != 0 )
			return 10101;  // Parser error.
		
		String requestType = request.getParameter( "RequestType" );
		
		if( requestType.equals( "AddWatchLists" ))
		{
			result = add( watchListData );
		}
		else if( requestType.equals( "DeleteWatchLists" ))
		{
			result = remove( watchListData.watchListId_ );
		}
		else if( requestType.equals( "FetchWatchLists" ))
		{
			result = get( watchListData.ownerKey_, watchLists );
		}
		else
		{
			result = 10102;  // can't find appropriate request type
		}
	    
	    return result;
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
		int result = 0;
		
		WatchList watchList = new WatchList( );
		
		result = watchList.add( watchListData );
		
		watchList = null;
		
		return result;
	    
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
		int result = 0;
		
		WatchList watchList = new WatchList( );
		
		result = watchList.remove( watchListId );
		
		return result;
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
		int result = 0;
		
		WatchList watchList = new WatchList( );
		
		result = watchList.get( userKey, watchLists );
		
		return result;
	}

}
