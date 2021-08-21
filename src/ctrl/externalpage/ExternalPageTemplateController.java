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
import core.externalpage.ExternalWebSiteData;
import core.externalpage.TemplateCommonData;
import core.externalpage.TemplateDataManager;
import core.externalpage.TemplateFillContentAboutUsData;
import core.externalpage.TemplateFillContentContactUsData;
import core.externalpage.TemplateFillContentHomeData;
import core.externalpage.TemplateFillContentProductsData;
import core.externalpage.TemplateFillContentServiceData;
import core.externalpage.TemplateFillContentSolutionsData;
import core.externalpage.TemplateWebStructureData;

/**
 * @FileName : ExternalPageTemplateController.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 11, 2013
 * 
 * @Purpose :
 * 
 */
public class ExternalPageTemplateController
{
	public int processWebStructureSave( HttpServletRequest request )
	{
		int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path
			TemplateWebStructureData webStructData = new TemplateWebStructureData( );

			WebStructureDataConvertor webConvert = new WebStructureDataConvertor( );
			result = webConvert.convert( request, webStructData );

			if( result == 0 )
			{
				TemplateDataManager manager = new TemplateDataManager( );
				result = manager.webStructureSave( webStructData );
			}
		}
		return result;
	}

	/**
	 * @Date : May 11, 2013 7:33:23 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public int processWebStructureRead( HttpServletRequest request, TemplateWebStructureData webStructData )
	{
		int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path

			TemplateDataManager manager = new TemplateDataManager( );
			result = manager.webStructureRead( webStructData );

		}
		return result;
	}

	/**
	 * @Date : May 12, 2013 10:15:44 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public int processContentHomeSave( HttpServletRequest request )
	{
		int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path
			TemplateFillContentHomeData webStructData = new TemplateFillContentHomeData( );

			ContentHomeDataConvertor webConvert = new ContentHomeDataConvertor( );
			result = webConvert.convert( request, webStructData );

			if( result == 0 )
			{
				TemplateDataManager manager = new TemplateDataManager( );
				result = manager.contentHomeSave( webStructData );
			}
		}
		return result;
	}

	/**
	 * @Date : May 12, 2013 10:28:06 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @param homeData
	 * @return
	 * 
	 */
	public int processContentHomeRead( HttpServletRequest request, TemplateFillContentHomeData homeData )
	{
		int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path

			TemplateDataManager manager = new TemplateDataManager( );
			result = manager.contentHomeRead( homeData );

		}
		return result;
	}

	/**
	 * @Date : May 12, 2013 9:44:37 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public int processContentProductSave( HttpServletRequest request )
	{
		int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path
			TemplateFillContentProductsData productdata = new TemplateFillContentProductsData( );

			ContentProductDataConvertor productConvert = new ContentProductDataConvertor( );
			result = productConvert.convert( request, productdata );

			if( result == 0 )
			{
				TemplateDataManager manager = new TemplateDataManager( );
				result = manager.contentProductSave( productdata );
			}
		}

		return result;
	}

	/**
	 * @Date : May 12, 2013 10:00:40 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @param productdata
	 * @return
	 * 
	 */
	public int processContentProductRead( HttpServletRequest request, TemplateFillContentProductsData productdata )
	{
		int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path

			TemplateDataManager manager = new TemplateDataManager( );
			result = manager.contentProductRead( productdata );

		}
		return result;
	}

	/**
	 * @Date : May 28, 2013 8:14:13 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public int processContentServicesSave( HttpServletRequest request )
	{
		int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path
			TemplateFillContentServiceData servicedata = new TemplateFillContentServiceData( );

			ContentServiceDataConvertor serviceConvert = new ContentServiceDataConvertor( );
			result = serviceConvert.convert( request, servicedata );

			if( result == 0 )
			{
				TemplateDataManager manager = new TemplateDataManager( );
				result = manager.contentServiceSave( servicedata );
			}
		}

		return result;
	}

	/**
	 * @Date : May 28, 2013 8:30:22 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @param servicedata
	 * @return
	 * 
	 */
	public int processContentServiceRead( HttpServletRequest request, TemplateFillContentServiceData servicedata )
	{
		int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path

			TemplateDataManager manager = new TemplateDataManager( );
			result = manager.contentServiceRead( servicedata );

		}
		return result;
	}

	/**
	 * @Date : Jun 1, 2013 7:43:08 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public int processContentSolutionsSave( HttpServletRequest request )
	{
		int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path
			TemplateFillContentSolutionsData solutiondata = new TemplateFillContentSolutionsData( );

			ContentSolutionsDataConvertor solutionConvert = new ContentSolutionsDataConvertor( );
			result = solutionConvert.convert( request, solutiondata );

			if( result == 0 )
			{
				TemplateDataManager manager = new TemplateDataManager( );
				result = manager.contentSolutionSave( solutiondata );
			}
		}

		return result;
	}

	/**
	 * @Date : Jun 1, 2013 7:53:34 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @param solutiondata
	 * @return
	 * 
	 */
	public int processContentSoultionsRead( HttpServletRequest request, TemplateFillContentSolutionsData solutiondata )
	{
		int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path

			TemplateDataManager manager = new TemplateDataManager( );
			result = manager.contentSolutionsRead( solutiondata );

		}
		return result;
	}

	/**
	 * @Date	: Jun 1, 2013 9:00:46 AM
	 *
	 * @Return 	: int
	 *
	 * @Purpose	: 
	 *
	 * @param request
	 * @return
	 *
	 */
    public int processContentAboutUsSave( HttpServletRequest request )
    {
		int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path
			TemplateFillContentAboutUsData aboutdata = new TemplateFillContentAboutUsData( );

			ContentAboutUsDataConvertor aboutsConvert = new ContentAboutUsDataConvertor( );
			result = aboutsConvert.convert( request, aboutdata );

			if( result == 0 )
			{
				TemplateDataManager manager = new TemplateDataManager( );
				result = manager.contentAboutUsSave( aboutdata );
			}
		}

		return result;
    }

	/**
	 * @Date	: Jun 1, 2013 9:09:25 AM
	 *
	 * @Return 	: int
	 *
	 * @Purpose	: 
	 *
	 * @param request
	 * @param aboutdata
	 * @return
	 *
	 */
    public int processContentAboutUsRead( HttpServletRequest request, TemplateFillContentAboutUsData aboutdata )
    {
		int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path

			TemplateDataManager manager = new TemplateDataManager( );
			result = manager.contentAboutUsRead( aboutdata );

		}
		return result;
    }

	/**
	 * @Date	: Jun 1, 2013 9:52:33 AM
	 *
	 * @Return 	: int
	 *
	 * @Purpose	: 
	 *
	 * @param request
	 * @return
	 *
	 */
    public int processContentContactUsSave( HttpServletRequest request )
    {
		int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path
			TemplateFillContentContactUsData contactusdata = new TemplateFillContentContactUsData( );

			ContentContactUsDataConvertor contactConvert = new ContentContactUsDataConvertor( );
			result = contactConvert.convert( request, contactusdata );

			if( result == 0 )
			{
				TemplateDataManager manager = new TemplateDataManager( );
				result = manager.contentContactUsSave( contactusdata );
			}
		}

		return result;
    }

	/**
	 * @Date	: Jun 1, 2013 10:09:33 AM
	 *
	 * @Return 	: int
	 *
	 * @Purpose	: 
	 *
	 * @param request
	 * @param contactdata
	 * @return
	 *
	 */
    public int processContentContactUsRead( HttpServletRequest request, TemplateFillContentContactUsData contactdata )
    {
		int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path

			TemplateDataManager manager = new TemplateDataManager( );
			result = manager.contentContactUsRead( contactdata );

		}
		return result;
    }

	/**
	 * @Date	: Jul 20, 2013 8:00:50 PM
	 *
	 * @Return 	: int
	 *
	 * @Purpose	: 
	 *
	 * @param request
	 * @return
	 *
	 */
    public int processContentExternalWebSiteSave( HttpServletRequest request )
    {
    	int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path
			ExternalWebSiteData externalwebdata = new ExternalWebSiteData( );

			ExternalWebSiteDataConvertor webConvert = new ExternalWebSiteDataConvertor( );
			result = webConvert.convert( request, externalwebdata );

			if( result == 0 )
			{
				TemplateDataManager manager = new TemplateDataManager( );
				result = manager.contentContactUsSave( externalwebdata );
			}
		}

		return result;
    }

	/**
	 * @Date	: Jul 20, 2013 8:10:42 PM
	 *
	 * @Return 	: int
	 *
	 * @Purpose	: 
	 *
	 * @param request
	 * @param websitedata
	 * @return
	 *
	 */
    public int processContentExternalWebSiteRead( HttpServletRequest request, ExternalWebSiteData websitedata )
    {
    	int result = 0;
		ExternalPageDataConvertor convertor = new ExternalPageDataConvertor( );
		ExternalPageData basedata = new ExternalPageData( );
		result = convertor.convert( request, basedata );

		if( result == 0 )
		{
			new TemplateCommonData( basedata.companyURLName_ ); // Assigning the
			                                                    // Path

			TemplateDataManager manager = new TemplateDataManager( );
			result = manager.contentExternalWebSiteRead( websitedata );

		}
		return result;
    }
}
