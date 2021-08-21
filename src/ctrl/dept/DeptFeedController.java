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

package ctrl.dept;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.feed.DeptFeedManager;
import core.feed.DeptFeedData;


/**
 * File: DeptFeedController.java
 * 
 * Created on May 6, 2013 2:51:11 PM
 */

/*
 * Class: DeptFeedController
 * 
 * Purpose: This class is used to store and display the feed on the department
 * page, posted by the member of the department
 * 
 * The request types processed for the following requests
 * 
 * 1. PostFeed - To post a new feed to the department page
 * 
 * 2. RemoveFeed - To delete an existing feed from the department page
 * 
 * 3. GetFeeds - To fetch all feeds of the department
 */

public class DeptFeedController
{
	ErrorLogger errLogger_;

	/* Constructor */
	public DeptFeedController()
	{
		errLogger_ = ErrorLogger.instance( );
	}
	
	
	/*
	 * Method : processRequest()
	 * 
	 * Input  : HTTPServletRequest and list of DeptFeedData objects 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to process the dept feed request. It get the request from servlet
	 * and do the appropriate action according to RequestType, which is available in request.
	 * With the use DeptFeedDataCoverter it convert request object to DeptFeeddata object.
	 */
	public int processRequest( HttpServletRequest request, List<DeptFeedData> deptFeedList )
	{
		int result = 0;
		
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		DeptFeedDataConverter converter = new DeptFeedDataConverter( );
		
		DeptFeedData deptFeedData = new DeptFeedData( );
		
		// Parse the request object to DeptFeedData object
		int parseRes = converter.convert( request, deptFeedData );
		
		converter = null;
		
		//deptFeedData.show( );
		
		if( parseRes != 0 )
		{
			String errMsg = "Error::DeptFeedController.processRequest() - Can't parse the " +
					"request ";
			
			errorLogger.logMsg(errMsg);
			
			return 1731;  // Failed request because of invalid data
		}
		
		String requestType = request.getParameter( "RequestType" );
		
		DeptFeedManager deptFeedManager = new DeptFeedManager( );
		
		if( requestType.equals( "NewFeed" ))  // Process the New feed request
		{
			result = deptFeedManager.add( deptFeedData );
			
			deptFeedList.add( deptFeedData );
		}
		else if( requestType.equals( "DeleteFeed" )) // Process the delete feed request
		{
			result = deptFeedManager.remove( deptFeedData.deptFeedId_, deptFeedData.deptKey_ );
		}
		else if( requestType.equals( "FetchFeed" ) ) // Process the fetch feed request
		{
			System.out.println( "----------------------------------------------------------------------------------------" );
			
			
			int from = Integer.parseInt( request.getParameter( "StartsFrom" ) );
			
			int count = Integer.parseInt( request.getParameter( "Count" ) );
			
			int lastFeedId = Integer.parseInt( request.getParameter( "LastFeedId" ) );
			
			System.out.println( "Fetch user feeds from "+from+" to "+from+count+" lastFeedId="+lastFeedId );
					
			result = deptFeedManager.find( deptFeedData.deptKey_, deptFeedList, from, count, lastFeedId );
			
			System.out.println( "----------------------------------------------------------------------------------------" );
			
			
		}
		else if( requestType.equals( "LatestFetchFeed" ) ) // Process the latest fetch feed request
		{
			System.out.println( "----------------------------------------------------------------------------------------" );
			
			int latestFeedId = Integer.parseInt( request.getParameter( "LatestFeedId" ) );
			
			System.out.println( "latest feed id="+latestFeedId );
					
			result = deptFeedManager.find( deptFeedData.deptKey_, deptFeedList, latestFeedId );
			
			System.out.println( "----------------------------------------------------------------------------------------" );
			
			
		}
		return result;
	}
}
