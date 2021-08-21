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
import core.externalpage.TemplateFillContentProductsData;

/**
 * @FileName : ContentProductDataConvertor.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 12, 2013
 * 
 * @Purpose :
 * 
 */
public class ContentProductDataConvertor
{
	public int convert( HttpServletRequest request, TemplateFillContentProductsData data )
	{

		try
		{

			Map<String, String [ ]> reqMap = request.getParameterMap( );

			if( reqMap.containsKey( "videoURL_1_" ) )
			{
				data.videoURL_1_ = request.getParameter( "videoURL_1_" );
			}

			if( reqMap.containsKey( "videoURL_1_content_" ) )
			{
				data.videoURL_1_content_ = request.getParameter( "videoURL_1_content_" );
			}

			if( reqMap.containsKey( "videoURL_2_" ) )
			{
				data.videoURL_2_ = request.getParameter( "videoURL_2_" );
			}

			if( reqMap.containsKey( "videoURL_2_content_" ) )
			{
				data.videoURL_2_content_ = request.getParameter( "videoURL_2_content_" );
			}
			
			// Image1 data
			
			data.image_1_data_ = request.getAttribute( "image_1_data" );
			data.image_1_hasimage_ = Boolean.valueOf( request.getParameter( "image_1_hasimage_" ) );

			if( data.image_1_data_ != null || data.image_1_hasimage_ )
			{
				data.image_1_Title_ = request.getParameter( "image_1_title" );
				data.image_1_content = request.getParameter( "image_1_content" );

				if( !request.getParameter( "image_1_imgName_" ).equalsIgnoreCase( "" ) )
				{
					data.image_1_imgName_ = request.getParameter( "image_1_imgName_" );
				}

				data.image_1_hasimage_ = true;
			}
			else
			{
				data.image_1_hasimage_ = false;
				
				data.image_1_imgName_ = "";
				
				if( !request.getParameter( "image_1_title" ).equalsIgnoreCase( "" ) && 
						 !request.getParameter( "image_1_content" ).equalsIgnoreCase( "" ))
				{
					data.image_1_Title_ = request.getParameter( "image_1_title" );
					data.image_1_content = request.getParameter( "image_1_content" );
				}
				
			}
			
			// Image 2 data

			data.image_2_data_ = request.getAttribute( "image_2_data" );
			data.image_2_hasimage_ = Boolean.valueOf( request.getParameter( "image_2_hasimage_" ) );

			if( data.image_2_data_ != null || data.image_2_hasimage_ )
			{
				data.image_2_Title_ = request.getParameter( "image_2_title" );
				data.image_2_content = request.getParameter( "image_2_content" );
				if( !request.getParameter( "image_2_imgName_" ).equalsIgnoreCase( "" ) )
				{
					data.image_2_imgName_ = request.getParameter( "image_2_imgName_" );
				}
				data.image_2_hasimage_ = true;
			}
			else
			{
				data.image_2_hasimage_ = false;
				
				if( !request.getParameter( "image_2_title" ).equalsIgnoreCase( "" ) && 
						 !request.getParameter( "image_2_content" ).equalsIgnoreCase( "" ))
				{
					data.image_2_Title_ = request.getParameter( "image_2_title" );
					data.image_2_content = request.getParameter( "image_2_content" );
				}
			}

			// Image 3 data
			
			data.image_3_data_ = request.getAttribute( "image_3_data" );
			data.image_3_hasimage_ = Boolean.valueOf( request.getParameter( "image_3_hasimage_" ) );

			if( data.image_3_data_ != null || data.image_3_hasimage_ )
			{
				data.image_3_Title_ = request.getParameter( "image_3_title" );
				data.image_3_content = request.getParameter( "image_3_content" );
				if( !request.getParameter( "image_3_imgName_" ).equalsIgnoreCase( "" ) )
				{
					data.image_3_imgName_ = request.getParameter( "image_3_imgName_" );
				}
				data.image_3_hasimage_ = true;
			}
			else
			{
				data.image_3_hasimage_ = false;
				
				if( !request.getParameter( "image_3_title" ).equalsIgnoreCase( "" ) && 
						 !request.getParameter( "image_3_content" ).equalsIgnoreCase( "" ))
				{
					data.image_3_Title_ = request.getParameter( "image_3_title" );
					data.image_3_content = request.getParameter( "image_3_content" );
				}
			}

			
			// Image 4 data
			
			data.image_4_data_ = request.getAttribute( "image_4_data" );
			data.image_4_hasimage_ = Boolean.valueOf( request.getParameter( "image_4_hasimage_" ) );

			if( data.image_4_data_ != null || data.image_4_hasimage_ )
			{
				data.image_4_Title_ = request.getParameter( "image_4_title" );
				data.image_4_content = request.getParameter( "image_4_content" );
				if( !request.getParameter( "image_4_imgName_" ).equalsIgnoreCase( "" ) )
				{
					data.image_4_imgName_ = request.getParameter( "image_4_imgName_" );
				}
				data.image_4_hasimage_ = true;
			}
			else
			{
				data.image_4_hasimage_ = false;
				
				if( !request.getParameter( "image_4_title" ).equalsIgnoreCase( "" ) && 
						 !request.getParameter( "image_4_content" ).equalsIgnoreCase( "" ))
				{
					data.image_4_Title_ = request.getParameter( "image_4_title" );
					data.image_4_content = request.getParameter( "image_4_content" );
				}
			}

			return 0;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: ContentProductDataConvertor : convert - " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

	}

}
