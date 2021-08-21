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

import core.externalpage.TemplateFillContentHomeData;

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
public class ContentHomeDataConvertor
{
	public int convert( HttpServletRequest request, TemplateFillContentHomeData data )
	{
		try
		{
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			if( reqMap.containsKey( "Video_URL" ) )
			{
				data.videoURL_ = request.getParameter( "Video_URL" );
			}

			if( reqMap.containsKey( "Video_Title" ) )
			{
				data.videoTitle_ = request.getParameter( "Video_Title" );
			}

			if( reqMap.containsKey( "Content" ) )
			{
				data.content_ = request.getParameter( "Content" );
			}
			
			System.out.println( "data parsed partially" );
			
			data.image_1_data_ = request.getAttribute( "image_1_data" );
			
			data.image_1_hasimage_ = Boolean.valueOf( request.getParameter( "image_1_hasimage" ) );

			if( data.image_1_data_ != null || data.image_1_hasimage_ )
			{
				data.image_1_Title_ = request.getParameter( "image_1_title" );
				
				data.image_1_content = request.getParameter( "image_1_content" );

				if( !request.getParameter( "image_1_imgName" ).equalsIgnoreCase( "" ) )
				{
					data.image_1_imgName_ = request.getParameter( "image_1_imgName" );
				}

				data.image_1_hasimage_ = true;
			}
			else
			{
				data.image_1_hasimage_ = false;
			}
			
			System.out.println( "parsing completed successfully" );

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: ContentHomeDataConvertor : convert - " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

	}
}
