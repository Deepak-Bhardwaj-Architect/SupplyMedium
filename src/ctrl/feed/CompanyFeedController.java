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

package ctrl.feed;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;
import core.feed.CompanyFeedData;
import core.feed.CompanyFeedManager;

/**
 * File:  CompanyFeedController.java 
 *
 * Created on 09 May, 2013
 */

public class CompanyFeedController
{

	/*
	 * Method : CompanyFeedController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public CompanyFeedController()
	{
		
	}
	
	
	/*
	 * Method : processRequest()
	 * 
	 * Input  : HTTPServletRequest and list of CompamyFeedData objects 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to process the company feed request. It gets the request from servlet
	 * and do the appropriate action according to RequestType, which is available in request.
	 * With the use CompanyFeedDataCoverter it convert request object to CompanyFeeddata object.
	 */
	
	public int processRequest( HttpServletRequest request, List<CompanyFeedData> companyFeedList )
	{
		int result = 0;
		
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		CompanyFeedDataConverter converter = new CompanyFeedDataConverter( );
		
		CompanyFeedData companyFeedData = new CompanyFeedData( );
		
		// Parse the request object to CompanyFeedData object
		int parseRes = converter.convert( request, companyFeedData );
		
		converter = null;
		
		//companyFeedData.show( );
		
		if( parseRes != 0 )
		{
			String errMsg = "Error::CompanyFeedController.processRequest() - Can't parse the " +
					"request ";
			
			errorLogger.logMsg(errMsg);
			
			return 1601;  // Failed request because of invalid data
		}
		
		String requestType = request.getParameter( "RequestType" );
		
		CompanyFeedManager companyFeedManager = new CompanyFeedManager( );
		
		if( requestType.equals( "NewFeed" ))  // Process the New feed request
		{
			result = companyFeedManager.add( companyFeedData );
			
			companyFeedList.add( companyFeedData );
		}
		else if( requestType.equals( "DeleteFeed" )) // Process the delete feed request
		{
			result = companyFeedManager.remove( companyFeedData.companyFeedId_, companyFeedData.regnKey_ );
		}
		else if( requestType.equals( "FetchFeed" ) ) // Process the fetch feed request
		{
			System.out.println( "----------------------------------------------------------------------------------------" );
			
			
			int from = Integer.parseInt( request.getParameter( "StartsFrom" ) );
			
			int count = Integer.parseInt( request.getParameter( "Count" ) );
			
			int lastFeedId = Integer.parseInt( request.getParameter( "LastFeedId" ) );
			
			System.out.println( "Fetch company feeds from "+from+" to "+from+count+" lastFeedId="+lastFeedId );
					
			result = companyFeedManager.find( companyFeedData.regnKey_, companyFeedList, from, count, lastFeedId );
			
			System.out.println( "----------------------------------------------------------------------------------------" );
			
		}
		else if( requestType.equals( "LatestFetchFeed" ) ) // Process the latest fetch feed request
		{
			System.out.println( "----------------------------------------------------------------------------------------" );
			
			int latestFeedId = Integer.parseInt( request.getParameter( "LatestFeedId" ) );
			
			System.out.println( "latest feed id="+latestFeedId );
					
			result = companyFeedManager.find( companyFeedData.regnKey_, companyFeedList, latestFeedId );
			
			System.out.println( "----------------------------------------------------------------------------------------" );
			
			
		}
		
		System.gc( );
		
		return result;
	}
	
}
