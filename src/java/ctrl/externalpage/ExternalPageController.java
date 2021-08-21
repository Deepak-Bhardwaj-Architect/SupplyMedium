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
package ctrl.externalpage;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import core.externalpage.ExternalPageData;
import core.externalpage.ExternalPageManager;
import utils.ErrorMaster;

import utils.IntHolder;
import utils.StringHolder;

/**
 * @FileName : ExternalPageController.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 5, 2013
 * 
 * @Purpose :
 * 
 */
public class ExternalPageController
{
	public int procrssRequestInsert( HttpServletRequest request, IntHolder externalPageKey )
	{
		int result = 0;
		ExternalPageData pagedata = new ExternalPageData( );
		ExternalPageDataConvertor convert = new ExternalPageDataConvertor( );
		result = convert.convert( request, pagedata );
		ExternalPageManager manage;

		if( result == 0 )
		{
			if( pagedata.pageType_.equalsIgnoreCase( "ExternalLink" ) )
			{
				
				if( isURLValid( pagedata.externalPageURL_ ) )
				{
					manage = new ExternalPageManager( );
					
					result = manage.insert( pagedata, externalPageKey );
				}
				else
				{
					result = 4003; // URL Not Found
				}
			}
			else
			{
				manage = new ExternalPageManager( );
				
				result = manage.insert( pagedata, externalPageKey );
			}
		}

		return result;
	}

	/**
	 * @Date : May 11, 2013 9:56:34 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @param key
	 * @return
	 * 
	 */
	public int procrssRequestLoad( HttpServletRequest request, ExternalPageData pagedata )
	{

		int result = 0;

		ExternalPageDataConvertor convert = new ExternalPageDataConvertor( );
		result = convert.convert( request, pagedata );

		if( result == 0 )
		{
			ExternalPageManager externalMgr = new ExternalPageManager( );
			result = externalMgr.getInformation( pagedata.compnayRegnKey_, pagedata );
		}

		return result;
	}

	/**
	 * @Date : May 11, 2013 11:36:01 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public int procrssRequestUpdate( HttpServletRequest request )
	{
		int result = 0;

		ExternalPageDataConvertor convert = new ExternalPageDataConvertor( );
		ExternalPageData pagedata = new ExternalPageData( );
		result = convert.convert( request, pagedata );
		ExternalPageManager externalMgr = null;

		if( result == 0 )
		{
			if( pagedata.pageType_.equalsIgnoreCase( "ExternalLink" ) )
			{
				if( isURLValid( pagedata.externalPageURL_ ) )
				{
					externalMgr = new ExternalPageManager( );
					result = externalMgr.update( pagedata );
				}
				else
				{
					result = 4003; // URL Not Found
				}
			}
			else
			{
				externalMgr = new ExternalPageManager( );
				result = externalMgr.update( pagedata );
			}
		}

		return result;
	}

	/**
	 * @Date : May 11, 2013 9:16:37 AM
	 * 
	 * @Return : boolean
	 * 
	 * @Purpose : This function Will Check Whether the Given URL is valid or not
	 * 
	 * @param URL
	 * @return
	 * 
	 */
	public boolean isURLValid( String URL )
	{
		try
		{
                    ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
			errorMaster_.insert( URL );
			URL u = new URL( URL );
			HttpURLConnection huc = (HttpURLConnection)u.openConnection( );
			huc.setRequestMethod( "GET" ); // OR huc.setRequestMethod ("HEAD");
			huc.connect( );
			int code = huc.getResponseCode( );
			errorMaster_.insert( ""+code );

			return true;
		}
		catch( Exception e )
		{
			return false;
		}

	}
	
	
	/**
	 * @Date : May 11, 2013 11:36:01 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public int procrssRequestGetLink( HttpServletRequest request, StringHolder websiteURL )
	{
		int result = 0;

		ExternalPageDataConvertor convert = new ExternalPageDataConvertor( );
		
		ExternalPageData pagedata = new ExternalPageData( );
		
		result = convert.convert( request, pagedata );
		
		ExternalPageManager externalMgr = null;

		if( result == 0 )
		{
			externalMgr = new ExternalPageManager( );
			
			result = externalMgr.getWebSiteLink( pagedata, websiteURL );
			
		}

		return result;
	}

}
