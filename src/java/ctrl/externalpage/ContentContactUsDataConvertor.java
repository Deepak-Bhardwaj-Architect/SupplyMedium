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
public class ContentContactUsDataConvertor
{
	public int convert( HttpServletRequest request, TemplateFillContentContactUsData data )
	{
		try
		{
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			if( reqMap.containsKey( "country_1" ) )
			{
				data.country_ = request.getParameter( "country_1" );
			}

			if( reqMap.containsKey( "address_1" ) )
			{
				data.adddress_ = request.getParameter( "address_1" );
			}

			if( reqMap.containsKey( "city_1" ) )
			{
				data.city_ = request.getParameter( "city_1" );
			}

			if( reqMap.containsKey( "state_1" ) )
			{
				data.state_ = request.getParameter( "state_1" );
			}

			if( reqMap.containsKey( "zipcode_1" ) )
			{
				data.zipcode_ = request.getParameter( "zipcode_1" );
			}

			if( reqMap.containsKey( "inquiry_1" ) )
			{
				data.inquiry_ = request.getParameter( "inquiry_1" );
			}

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: ContentContactUsDataConvertor : convert - " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

	}
}
