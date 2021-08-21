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
import core.externalpage.TemplateFillContentAboutUsData;
import core.externalpage.TemplateFillContentServiceData;
import core.externalpage.TemplateFillContentSolutionsData;

/**
 * @FileName : ContentServiceDataConvertor.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 12, 2013
 * 
 * @Purpose :
 * 
 */
public class ContentAboutUsDataConvertor
{
	public int convert( HttpServletRequest request, TemplateFillContentAboutUsData data )
	{

		try
		{

			data.image_1_data_ = request.getAttribute( "image_1_data" );
			data.image_1_hasimage_ = Boolean.valueOf( request.getParameter( "image_1_hasimage_" ) );
			
			data.image_1_Title_ = request.getParameter( "image_1_title" );
			data.image_1_content = request.getParameter( "image_1_content" );

			if( data.image_1_data_ != null || data.image_1_hasimage_ )
			{
				if( !request.getParameter( "image_1_imgName_" ).equalsIgnoreCase( "" ) )
				{
					data.image_1_imgName_ = request.getParameter( "image_1_imgName_" );
				}

				data.image_1_hasimage_ = true;
			}
			else
			{
				data.image_1_hasimage_ = false;
			}

			return 0;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: ContentAboutUsDataConvertor : convert - " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

	}

}
