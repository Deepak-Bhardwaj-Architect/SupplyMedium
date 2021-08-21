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

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.feed.CompanyFeedData;;


/**
 * File:  CompanyFeedDataConverter.java 
 *
 * Created on 09, May, 2013
 */
public class CompanyFeedDataConverter
{

	/*
	 * Method : CompanyFeedDataConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public CompanyFeedDataConverter()
	{
		
	}
	
	
	/*
	 * Method : convert()
	 * 
	 * Input  : HTTPServletRequest and CompanyFeedData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: It it used to convert the HTTPServletRequest to CommpanyFeeddata object.
	 * So CompanyFeedData object copied to caller classes.
	 */
	public int convert( HttpServletRequest request, CompanyFeedData feedData )
	{
		try
        {
			String feedIdStr = request.getParameter( "FeedId" );
			
			if( feedIdStr !=null)
			{
				feedData.companyFeedId_ 	= Integer.parseInt( feedIdStr );
			}
			
			feedData.feed_			= request.getParameter( "Feed" );
			
			feedData.feedTitle_ 	= request.getParameter( "FeedTitle" );
	
			
			feedData.regnKey_.companyPhoneNo_ = request.getParameter( "RegnKey" );
			
			feedData.userKey_.email_ 			= request.getParameter( "UserKey" );
			
        }
        catch( Exception ex )
        {
        	ErrorLogger errorLogger = ErrorLogger.instance( );
        	
        	String msg = "Exception::CompanyFeedDataConverter.convert() - " + ex;

			errorLogger.logMsg( msg );
			
			return -1;
        }
		
		return 0;
	}
	
}
