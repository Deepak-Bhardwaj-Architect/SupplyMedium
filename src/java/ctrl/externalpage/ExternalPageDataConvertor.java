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

import core.externalpage.ExternalPageData;
import core.regn.CompanyRegnKey;

/**
 * @FileName	: ExternalPageDataConveror.java
 *
 * @author 		: Anbazhagan
 *
 * @Date 		: May 5, 2013
 *
 * @Purpose 		: 
 *
 */
public class ExternalPageDataConvertor
{
	public int convert(HttpServletRequest request, ExternalPageData data)
	{
		try
		{
			Map<String, String [ ]> reqMap=request.getParameterMap( );
			
			if(reqMap.containsKey( "cmpRegnKey" ))
			{
				data.compnayRegnKey_=new CompanyRegnKey( request.getParameter("cmpRegnKey"));
			}
			
			if(reqMap.containsKey( "compnayURLName" ))
			{
				data.companyURLName_=request.getParameter("compnayURLName");
			}
			
			if(reqMap.containsKey( "pageType" ))
			{
				data.pageType_=request.getParameter("pageType");
			}
			
			if(reqMap.containsKey( "externalPageID" ))
			{
				data.externalpageid_=Integer.parseInt(request.getParameter("externalPageID"));
			}
			
			if(reqMap.containsKey( "externalPageURL" ))
			{
				data.externalPageURL_=request.getParameter("externalPageURL");
			}
			
			return 0;
			
		}
		catch(Exception ex)
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: ExternalPageDataConveror : convert - " + ex;
			errLogger.logMsg( errMsg );

			return 4002;  // failed
		}
	}

}
