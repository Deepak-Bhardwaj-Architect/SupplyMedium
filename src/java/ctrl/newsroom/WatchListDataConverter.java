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


import java.util.Map;

import javax.servlet.http.HttpServletRequest;



import utils.ErrorLogger;

import core.newsroom.WatchListData;

/**
 * File:  WatchListDataConverter.java 
 *
 * Created on 30-Aug-2013 6:09:35 PM
 */
public class WatchListDataConverter
{

	ErrorLogger errorLogger_;
	
	/*
	 * Method : WatchListDataConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WatchListDataConverter()
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : convert 
	 * 
	 * Input  : HttpServletRequest object, WatchListData object
	 * 
	 * Return : int - success/fail
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to watchlistdata object.
	 * And copied to watchListData parameter so it available in caller classes.
	 * 
	 */
	public int convert( HttpServletRequest request, WatchListData  watchListData )
    {
		int result = 0;
		
		try
        {
			
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			/* WatchList details */
			
			if( reqMap.containsKey( "WatchListName" ) )
			{
				watchListData.watchListName_ = request.getParameter( "WatchListName" );
			}
			
			if( reqMap.containsKey( "WatchListId" ) )
			{
				watchListData.watchListId_ = Integer.parseInt( request.getParameter( "WatchListId" ) );
			}
			
			if( reqMap.containsKey( "UserKey" ) )
			{
				watchListData.ownerKey_.email_ = request.getParameter( "UserKey" );
			}
			if( reqMap.containsKey( "RegnKey" ) )
			{
				watchListData.regnKey_.companyPhoneNo_ = request.getParameter( "RegnKey" );
			}
						
			
			
        }
		catch( Exception e )
	    {
			errorLogger_.logMsg( "Exception::WatchListDataConverter.convert() - " + e );
	        	
	        return -1;
	    }
		
		return result;
    }

}
