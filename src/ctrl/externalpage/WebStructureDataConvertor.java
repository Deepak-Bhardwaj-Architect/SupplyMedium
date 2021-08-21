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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.externalpage.TemplateWebStructureData;

/**
 * @FileName : WebStructureDataConvertor.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 11, 2013
 * 
 * @Purpose :
 * 
 */
public class WebStructureDataConvertor
{
	public int convert( HttpServletRequest request, TemplateWebStructureData data )
	{
		try
		{
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			if( reqMap.containsKey( "Home" ) )
			{
				data.home_ = Boolean.valueOf( request.getParameter( "Home" ) );
			}

			if( reqMap.containsKey( "Products" ) )
			{
				data.products_ = Boolean.valueOf( request.getParameter( "Products" ) );
			}

			if( reqMap.containsKey( "Solutions" ) )
			{
				data.solutions_ = Boolean.valueOf( request.getParameter( "Solutions" ) );
			}

			if( reqMap.containsKey( "Service" ) )
			{
				data.serviceSupport_ = Boolean.valueOf( request.getParameter( "Service" ) );
			}

			if( reqMap.containsKey( "About_US" ) )
			{
				data.aboutUS_ = Boolean.valueOf( request.getParameter( "About_US" ) );
			}

			if( reqMap.containsKey( "ContactUS" ) )
			{
				data.contactUS_ = Boolean.valueOf( request.getParameter( "ContactUS" ) );
			}
			
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: WebStructureDataConvertor : convert - " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

	}
}
