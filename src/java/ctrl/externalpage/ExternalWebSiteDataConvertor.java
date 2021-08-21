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

import core.externalpage.ExternalWebSiteData;
import core.externalpage.TemplateFillContentContactUsData;

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
public class ExternalWebSiteDataConvertor
{
	public int convert( HttpServletRequest request, ExternalWebSiteData data )
	{
		try
		{
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			if( reqMap.containsKey( "webURL" ) )
			{
				data.webURL_ = request.getParameter( "webURL" );
			}

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: ExternalWebSiteDataConvertor : convert - " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

	}
}
