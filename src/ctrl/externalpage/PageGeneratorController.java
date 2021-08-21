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

import javax.servlet.http.HttpServletRequest;

import core.externalpage.ExternalPageData;
import core.externalpage.PageGenerator;
import core.externalpage.PageGeneratorData;

/**
 * @FileName : PageGeneratorController.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Jun 1, 2013
 * 
 * @Purpose :
 * 
 */
public class PageGeneratorController
{

	/**
	 * @Date : Jun 1, 2013 12:44:22 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public int processRequest( HttpServletRequest request )
	{
		int result = 0;
		ExternalPageDataConvertor pageConvertor = new ExternalPageDataConvertor( );
		
		ExternalPageData basicdata = new ExternalPageData( );
		
		result = pageConvertor.convert( request, basicdata );
		
		if( result == 0 )
		{
			PageGeneratorData pagedata = new PageGeneratorData( );
			
			PageGeneratorDataConvertor dataConvert = new PageGeneratorDataConvertor( );
			
			dataConvert.convert( request, pagedata );

			PageGenerator generate = new PageGenerator( );
			
			result = generate.generator( basicdata, pagedata );

			if( result == 0 )
				return 4800;
			else
				return 4801;
		}
		
		return result;
	}
	
	public int processRequestExternalWebsite( HttpServletRequest request )
	{
		int result = 0;
		ExternalPageDataConvertor pageConvertor = new ExternalPageDataConvertor( );
		ExternalPageData basicdata = new ExternalPageData( );
		result = pageConvertor.convert( request, basicdata );
		if( result == 0 )
		{
			PageGenerator generate = new PageGenerator( );
			result = generate.generateExternalWebSite(basicdata);

			if( result == 0 )
				return 6100;
			else
				return 6101;
		}
		
		return result;
	}
}
