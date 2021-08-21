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

/**
 * @FileName : TemplateFillContentHomeData.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 11, 2013
 * 
 * @Purpose : It will handle the Content Home Information
 * 
 */
public class TemplateFillContentHomeData implements ITemplateData
{

	public String	videoURL_;
	public String	videoTitle_;
	public String	content_;
	
	public boolean	image_1_hasimage_;
	public String	image_1_imgName_	= "Img_HOME_1";
	public String	image_1_content;
	public String	image_1_Title_;
	public Object	image_1_data_;

	public String getPath( )
	{
		return getDirectory( ) + "/Content-Home.json";
	}

	public String getDirectory( )
	{
		return TemplateCommonData.externalPageDirPath_+"/Data";
	}
	public String getImageDirectory()
	{
		return TemplateCommonData.externalPageDirPath_+"/images/";
	}

	public void Show( )
	{
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );

		errorMaster_.insert( "videoURL_ 		=" + this.videoURL_ );
		errorMaster_.insert( "videoTitle_ 	=" + this.videoTitle_ );
		errorMaster_.insert( "content_ 		=" + this.content_ );
		
		errorMaster_.insert( "image_1_data_			:" + this.image_1_data_ );
		errorMaster_.insert( "image_1_hasimage_		:" + this.image_1_hasimage_ );
		errorMaster_.insert( "image_1_imgName_		:" + this.image_1_imgName_ );
		errorMaster_.insert( "image_1_content		:" + this.image_1_content );
		errorMaster_.insert( "image_1_Title_			:" + this.image_1_Title_ );
	}

	public void Clear( )
	{
		this.videoURL_ = null;
		this.videoTitle_ = null;
		this.content_ = null;
		
		this.image_1_hasimage_ = false;
		this.image_1_imgName_ = null;
		this.image_1_content = null;
		this.image_1_Title_ = null;
		this.image_1_data_ = null;
	}

	public void copy( ITemplateData data )
	{
		TemplateFillContentHomeData homedata = (TemplateFillContentHomeData)data;

		homedata.videoURL_ = this.videoURL_;
		homedata.videoTitle_ = this.videoTitle_;
		homedata.content_ = this.content_;
		
		homedata.image_1_content = this.image_1_content;
		homedata.image_1_hasimage_ = this.image_1_hasimage_;
		homedata.image_1_imgName_ = this.image_1_imgName_;
		homedata.image_1_Title_ = this.image_1_Title_;
	}

}
