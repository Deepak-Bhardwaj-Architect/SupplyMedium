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
import core.externalpage.PageGeneratorData;
import core.externalpage.enumTemplateMainType;
import core.externalpage.enumTemplateSubType;

/**
 * @FileName : PageGeneratorDataConvertor.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Jun 1, 2013
 * 
 * @Purpose :
 * 
 */
public class PageGeneratorDataConvertor
{
	public int convert( HttpServletRequest request, PageGeneratorData data )
	{
		try
		{
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			if( reqMap.containsKey( "templateMainType" ) )
			{
				data.templateMainType_ = enumTemplateMainType.valueOf( request.getParameter( "templateMainType" ) );
			}

			if( reqMap.containsKey( "templateSubType" ) )
			{
				data.templateSubType_ = enumTemplateSubType.valueOf( request.getParameter( "templateSubType" ) );
			}

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: PageGeneratorDataConvertor : convert - " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

	}
}
