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
package core.externalpage;

import utils.ErrorMaster;
import utils.StringHolder;

/**
 * @FileName : TemplateManager.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 11, 2013
 * 
 * @Purpose :
 * 
 */
public class TemplateDataManager
{

	public int webStructureSave( TemplateWebStructureData webdata )
	{
		int result = 0;
		if( webdata != null )
		{
			result = TemplateDataWR.Serialize( webdata );
			result = result == 0 ? 4400 : 4401;
		}

		return result;
	}

	/**
	 * @Date : May 11, 2013 7:35:22 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param webStructData
	 * @return
	 * 
	 */
	public int webStructureRead( TemplateWebStructureData webStructData )
	{
		int result = 0;
		if( webStructData != null )
		{
			TemplateWebStructureData data = null;
			data = (TemplateWebStructureData)TemplateDataWR.DeSerialize( webStructData );
			if( webStructData != null && data != null )
			{
				data.copy( webStructData );
			}
			result = data != null ? 4500 : 4501;
		}
		else
		{
			result = 4501;
		}

		return result;
	}

	/**
	 * @Date : May 12, 2013 10:20:11 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param webStructData
	 * @return
	 * 
	 */
	public int contentHomeSave( TemplateFillContentHomeData homedata )
	{
		int result = 0;
		if( homedata != null )
		{
			TemplateStoreImage fileUpload = new TemplateStoreImage( );
			StringHolder storedFileName = new StringHolder( );
			storedFileName.value = "";

                        ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );

			errorMaster_.insert( "has image="+homedata.image_1_hasimage_+"image data="+homedata.image_1_data_ );

			if( homedata.image_1_hasimage_ && homedata.image_1_data_ != null )
			{
				fileUpload.storeImage( homedata.image_1_data_, homedata.getImageDirectory( ), homedata.image_1_imgName_, storedFileName );

				homedata.image_1_imgName_ = storedFileName.value;
				storedFileName.value = "";
			}
			
			
			result = TemplateDataWR.Serialize( homedata );
			result = result == 0 ? 4600 : 4601;
		}
		else
			result = 4601;

		return result;
	}

	/**
	 * @Date : May 12, 2013 10:31:03 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param homeData
	 * @return
	 * 
	 */
	public int contentHomeRead( TemplateFillContentHomeData homeData )
	{
		int result = 0;
		if( homeData != null )
		{
			TemplateFillContentHomeData data = null;
			data = (TemplateFillContentHomeData)TemplateDataWR.DeSerialize( homeData );
			if( homeData != null && data != null )
			{
				data.copy( homeData );
			}
			result = data != null ? 4700 : 4701;
		}
		else
		{
			result = 4701;
		}

		return result;
	}

	/**
	 * @Date : May 12, 2013 9:57:03 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param productdata
	 * @return
	 * 
	 */
	public int contentProductSave( TemplateFillContentProductsData productdata )
	{
		int result = 0;
		if( productdata != null )
		{

			TemplateStoreImage fileUpload = new TemplateStoreImage( );
			StringHolder storedFileName = new StringHolder( );
			storedFileName.value = "";

			if( productdata.image_1_hasimage_ && productdata.image_1_data_ != null )
			{
				fileUpload.storeImage( productdata.image_1_data_, productdata.getImageDirectory( ), productdata.image_1_imgName_, storedFileName );

				productdata.image_1_imgName_ = storedFileName.value;
				storedFileName.value = "";
			}

			if( productdata.image_2_hasimage_ && productdata.image_2_data_ != null )
			{
				fileUpload.storeImage( productdata.image_2_data_, productdata.getImageDirectory( ), productdata.image_2_imgName_, storedFileName );

				productdata.image_2_imgName_ = storedFileName.value;
				storedFileName.value = "";
			}

			if( productdata.image_3_hasimage_ && productdata.image_3_data_ != null )
			{
				fileUpload.storeImage( productdata.image_3_data_, productdata.getImageDirectory( ), productdata.image_3_imgName_, storedFileName );

				productdata.image_3_imgName_ = storedFileName.value;
				storedFileName.value = "";
			}

			if( productdata.image_4_hasimage_ && productdata.image_4_data_ != null )
			{
				fileUpload.storeImage( productdata.image_4_data_, productdata.getImageDirectory( ), productdata.image_4_imgName_, storedFileName );

				productdata.image_4_imgName_ = storedFileName.value;
				storedFileName.value = "";
			}

			productdata.Show( );

			result = TemplateDataWR.Serialize( productdata );
			result = result == 0 ? 4800 : 4801;

		}
		else
			result = 4801;

		return result;
	}

	/**
	 * @Date : May 12, 2013 10:01:29 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param productdata
	 * @return
	 * 
	 */
	public int contentProductRead( TemplateFillContentProductsData productdata )
	{
		int result = 0;
		if( productdata != null )
		{
			TemplateFillContentProductsData data = null;
			data = (TemplateFillContentProductsData)TemplateDataWR.DeSerialize( productdata );
			if( productdata != null && data != null )
			{
				data.copy( productdata );
			}
			result = data != null ? 4900 : 4901;
		}
		else
		{
			result = 4901;
		}

		return result;
	}

	/**
	 * @Date : May 28, 2013 8:22:59 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param servicedata
	 * @return
	 * 
	 */
	public int contentServiceSave( TemplateFillContentServiceData servicedata )
	{
		int result = 0;
		if( servicedata != null )
		{

			TemplateStoreImage fileUpload = new TemplateStoreImage( );
			StringHolder storedFileName = new StringHolder( );
			storedFileName.value = "";

			if( servicedata.image_1_hasimage_ && servicedata.image_1_data_ != null )
			{
				fileUpload.storeImage( servicedata.image_1_data_, servicedata.getImageDirectory( ), servicedata.image_1_imgName_, storedFileName );

				servicedata.image_1_imgName_ = storedFileName.value;
				storedFileName.value = "";
			}

			if( servicedata.image_2_hasimage_ && servicedata.image_2_data_ != null )
			{
				fileUpload.storeImage( servicedata.image_2_data_, servicedata.getImageDirectory( ), servicedata.image_2_imgName_, storedFileName );

				servicedata.image_2_imgName_ = storedFileName.value;
				storedFileName.value = "";
			}

			if( servicedata.image_3_hasimage_ && servicedata.image_3_data_ != null )
			{
				fileUpload.storeImage( servicedata.image_3_data_, servicedata.getImageDirectory( ), servicedata.image_3_imgName_, storedFileName );

				servicedata.image_3_imgName_ = storedFileName.value;
				storedFileName.value = "";
			}

			if( servicedata.image_4_hasimage_ && servicedata.image_4_data_ != null )
			{
				fileUpload.storeImage( servicedata.image_4_data_, servicedata.getImageDirectory( ), servicedata.image_4_imgName_, storedFileName );

				servicedata.image_4_imgName_ = storedFileName.value;
				storedFileName.value = "";
			}

			result = TemplateDataWR.Serialize( servicedata );
			result = result == 0 ? 5000 : 5001;

		}
		else
			result = 5001;

		return result;
	}

	/**
	 * @Date : May 28, 2013 8:31:03 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param servicedata
	 * @return
	 * 
	 */
	public int contentServiceRead( TemplateFillContentServiceData servicedata )
	{
		int result = 0;
		if( servicedata != null )
		{
			TemplateFillContentServiceData data = null;
			data = (TemplateFillContentServiceData)TemplateDataWR.DeSerialize( servicedata );
			if( servicedata != null && data != null )
			{
				data.copy( servicedata );
			}
			result = data != null ? 5100 : 5101;
		}
		else
		{
			result = 5101;
		}

		return result;
	}

	/**
	 * @Date : Jun 1, 2013 7:49:38 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param solutiondata
	 * @return
	 * 
	 */
	public int contentSolutionSave( TemplateFillContentSolutionsData solutiondata )
	{
		int result = 0;
		if( solutiondata != null )
		{

			TemplateStoreImage fileUpload = new TemplateStoreImage( );
			StringHolder storedFileName = new StringHolder( );
			storedFileName.value = "";

			if( solutiondata.image_1_hasimage_ && solutiondata.image_1_data_ != null )
			{
				fileUpload.storeImage( solutiondata.image_1_data_, solutiondata.getImageDirectory( ), solutiondata.image_1_imgName_, storedFileName );

				solutiondata.image_1_imgName_ = storedFileName.value;
				storedFileName.value = "";
			}

			if( solutiondata.image_2_hasimage_ && solutiondata.image_2_data_ != null )
			{
				fileUpload.storeImage( solutiondata.image_2_data_, solutiondata.getImageDirectory( ), solutiondata.image_2_imgName_, storedFileName );

				solutiondata.image_2_imgName_ = storedFileName.value;
				storedFileName.value = "";
			}

			if( solutiondata.image_3_hasimage_ && solutiondata.image_3_data_ != null )
			{
				fileUpload.storeImage( solutiondata.image_3_data_, solutiondata.getImageDirectory( ), solutiondata.image_3_imgName_, storedFileName );

				solutiondata.image_3_imgName_ = storedFileName.value;
				storedFileName.value = "";
			}

			if( solutiondata.image_4_hasimage_ && solutiondata.image_4_data_ != null )
			{
				fileUpload.storeImage( solutiondata.image_4_data_, solutiondata.getImageDirectory( ), solutiondata.image_4_imgName_, storedFileName );

				solutiondata.image_4_imgName_ = storedFileName.value;
				storedFileName.value = "";
			}

			result = TemplateDataWR.Serialize( solutiondata );
			result = result == 0 ? 5200 : 5201;

		}
		else
			result = 5201;

		return result;
	}

	/**
	 * @Date : Jun 1, 2013 7:54:19 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param solutiondata
	 * @return
	 * 
	 */
	public int contentSolutionsRead( TemplateFillContentSolutionsData solutiondata )
	{
		int result = 0;
		if( solutiondata != null )
		{
			TemplateFillContentSolutionsData data = null;
			data = (TemplateFillContentSolutionsData)TemplateDataWR.DeSerialize( solutiondata );
			if( solutiondata != null && data != null )
			{
				data.copy( solutiondata );
			}
			result = data != null ? 5300 : 5301;
		}
		else
		{
			result = 5301;
		}

		return result;
	}

	/**
	 * @Date : Jun 1, 2013 9:06:38 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param aboutdata
	 * @return
	 * 
	 */
	public int contentAboutUsSave( TemplateFillContentAboutUsData aboutdata )
	{
		int result = 0;
		if( aboutdata != null )
		{

			TemplateStoreImage fileUpload = new TemplateStoreImage( );
			StringHolder storedFileName = new StringHolder( );
			storedFileName.value = "";

			if( aboutdata.image_1_hasimage_ && aboutdata.image_1_data_ != null )
			{
				fileUpload.storeImage( aboutdata.image_1_data_, aboutdata.getImageDirectory( ), aboutdata.image_1_imgName_, storedFileName );

				aboutdata.image_1_imgName_ = storedFileName.value;
				storedFileName.value = "";
			}

			result = TemplateDataWR.Serialize( aboutdata );
			result = result == 0 ? 5400 : 5401;

		}
		else
			result = 5401;

		return result;
	}

	/**
	 * @Date : Jun 1, 2013 9:09:55 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param aboutdata
	 * @return
	 * 
	 */
	public int contentAboutUsRead( TemplateFillContentAboutUsData aboutdata )
	{
		int result = 0;
		if( aboutdata != null )
		{
			TemplateFillContentAboutUsData data = null;
			data = (TemplateFillContentAboutUsData)TemplateDataWR.DeSerialize( aboutdata );
			if( aboutdata != null && data != null )
			{
				data.copy( aboutdata );
			}
			result = data != null ? 5500 : 5501;
		}
		else
		{
			result = 5501;
		}

		return result;
	}

	/**
	 * @Date : Jun 1, 2013 10:02:03 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param aboutdata
	 * @return
	 * 
	 */
	public int contentContactUsSave( TemplateFillContentContactUsData contactusdata )
	{
		int result = 0;
		if( contactusdata != null )
		{

			result = TemplateDataWR.Serialize( contactusdata );
			result = result == 0 ? 5600 : 5601;

		}
		else
			result = 5601;

		return result;
	}

	/**
	 * @Date : Jun 1, 2013 10:10:20 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param contactdata
	 * @return
	 * 
	 */
	public int contentContactUsRead( TemplateFillContentContactUsData contactdata )
	{
		int result = 0;
		if( contactdata != null )
		{
			TemplateFillContentContactUsData data = null;
			data = (TemplateFillContentContactUsData)TemplateDataWR.DeSerialize( contactdata );
			if( contactdata != null && data != null )
			{
				data.copy( contactdata );
			}
			result = data != null ? 5700 : 5701;
		}
		else
		{
			result = 5701;
		}

		return result;
	}

	/**
	 * @Date : Jul 20, 2013 8:07:05 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param externalwebdata
	 * @return
	 * 
	 */
	public int contentContactUsSave( ExternalWebSiteData externalwebdata )
	{
		int result = 0;
		if( externalwebdata != null )
		{

			result = TemplateDataWR.Serialize( externalwebdata );
			result = result == 0 ? 5900 : 5901;

		}
		else
			result = 5901;

		return result;
	}

	/**
	 * @Date : Jul 20, 2013 8:11:22 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param websitedata
	 * @return
	 * 
	 */
	public int contentExternalWebSiteRead( ExternalWebSiteData websitedata )
	{
		int result = 0;
		if( websitedata != null )
		{
			ExternalWebSiteData data = null;
			data = (ExternalWebSiteData)TemplateDataWR.DeSerialize( websitedata );
			if( websitedata != null && data != null )
			{
				data.copy( websitedata );
			}
			result = data != null ? 6000 : 6001;
		}
		else
		{
			result = 6001;
		}

		return result;
	}

}
