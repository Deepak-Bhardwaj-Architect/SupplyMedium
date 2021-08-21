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
 * @FileName : TemplateFillContentServiceData.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 11, 2013
 * 
 * @Purpose : It will handle the Content Fill Data - Services related information
 * 
 */
public class TemplateFillContentServiceData implements ITemplateData
{

	public String	videoURL_1_;
	public String	videoURL_1_content_;
	public String	videoURL_2_;
	public String	videoURL_2_content_;
	public boolean	image_1_hasimage_;
	public String	image_1_imgName_	= "Img_SER_1";
	public String	image_1_content;
	public String	image_1_Title_;
	public Object	image_1_data_;
	public boolean	image_2_hasimage_;
	public String	image_2_imgName_	= "Img_SER_2";
	public String	image_2_content;
	public String	image_2_Title_;
	public Object	image_2_data_;
	public boolean	image_3_hasimage_;
	public String	image_3_imgName_	= "Img_SER_3";
	public String	image_3_content;
	public String	image_3_Title_;
	public Object	image_3_data_;
	public boolean	image_4_hasimage_;
	public String	image_4_imgName_	= "Img_SER_4";
	public String	image_4_content;
	public String	image_4_Title_;
	public Object	image_4_data_;

	public String getPath( )
	{

		return getDirectory( ) + "/Content-Service.json";
	}

	public String getDirectory( )
	{

		return TemplateCommonData.externalPageDirPath_ + "/Data";
	}
	
	public String getImageDirectory()
	{
		return TemplateCommonData.externalPageDirPath_+"/images/";
	}

	public void Show( )
	{
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );

		errorMaster_.insert( "videoURL_1				:" + this.videoURL_1_ );
		errorMaster_.insert( "videoURL_1_content_	:" + this.videoURL_1_content_ );
		errorMaster_.insert( "videoURL_2_			:" + this.videoURL_2_ );
		errorMaster_.insert( "videoURL_2_conten_		:" + this.videoURL_2_content_ );
		errorMaster_.insert( "image_1_data_			:" + this.image_1_data_ );
		errorMaster_.insert( "image_1_hasimage_		:" + this.image_1_hasimage_ );
		errorMaster_.insert( "image_1_imgName_		:" + this.image_1_imgName_ );
		errorMaster_.insert( "image_1_content		:" + this.image_1_content );
		errorMaster_.insert( "image_1_Title_			:" + this.image_1_Title_ );
		errorMaster_.insert( "image_2_data_			:" + this.image_2_data_ );
		errorMaster_.insert( "image_2_hasimage_		:" + this.image_2_hasimage_ );
		errorMaster_.insert( "image_2_imgName_		:" + this.image_2_imgName_ );
		errorMaster_.insert( "image_2_content		:" + this.image_2_content );
		errorMaster_.insert( "image_2_Title_			:" + this.image_2_Title_ );
		errorMaster_.insert( "image_3_data_			:" + this.image_3_data_ );
		errorMaster_.insert( "image_3_hasimage_		:" + this.image_3_hasimage_ );
		errorMaster_.insert( "image_3_imgName_		:" + this.image_3_imgName_ );
		errorMaster_.insert( "image_3_content		:" + this.image_3_content );
		errorMaster_.insert( "image_3_Title_			:" + this.image_3_Title_ );
		errorMaster_.insert( "image_4_data_			:" + this.image_4_data_ );
		errorMaster_.insert( "image_4_hasimage_		:" + this.image_4_hasimage_ );
		errorMaster_.insert( "image_4_imgName_		:" + this.image_4_imgName_ );
		errorMaster_.insert( "image_4_content		:" + this.image_4_content );
		errorMaster_.insert( "image_4_Title_			:" + this.image_4_Title_ );

	}

	public void Clear( )
	{
		this.videoURL_1_ = null;
		this.videoURL_1_content_ = null;
		this.videoURL_2_ = null;
		this.videoURL_2_ = null;
		this.image_1_hasimage_ = false;
		this.image_1_imgName_ = null;
		this.image_1_content = null;
		this.image_1_Title_ = null;
		this.image_1_data_ = null;
		this.image_2_hasimage_ = false;
		this.image_2_imgName_ = null;
		this.image_2_content = null;
		this.image_2_Title_ = null;
		this.image_2_data_ = null;
		this.image_3_hasimage_ = false;
		this.image_3_imgName_ = null;
		this.image_3_content = null;
		this.image_3_Title_ = null;
		this.image_3_data_ = null;
		this.image_4_hasimage_ = false;
		this.image_4_imgName_ = null;
		this.image_4_content = null;
		this.image_4_Title_ = null;
		this.image_4_data_ = null;
	}

	public void copy( ITemplateData data )
	{
		TemplateFillContentServiceData prodata = (TemplateFillContentServiceData)data;

		prodata.videoURL_1_ = this.videoURL_1_;
		prodata.videoURL_1_content_ = this.videoURL_1_content_;
		prodata.videoURL_2_ = this.videoURL_2_;
		prodata.videoURL_2_content_ = this.videoURL_2_content_;
		prodata.image_1_content = this.image_1_content;
		prodata.image_1_hasimage_ = this.image_1_hasimage_;
		prodata.image_1_imgName_ = this.image_1_imgName_;
		prodata.image_1_Title_ = this.image_1_Title_;
		prodata.image_2_content = this.image_2_content;
		prodata.image_2_hasimage_ = this.image_2_hasimage_;
		prodata.image_2_imgName_ = this.image_2_imgName_;
		prodata.image_2_Title_ = this.image_2_Title_;
		prodata.image_3_content = this.image_3_content;
		prodata.image_3_hasimage_ = this.image_3_hasimage_;
		prodata.image_3_imgName_ = this.image_3_imgName_;
		prodata.image_3_Title_ = this.image_3_Title_;
		prodata.image_4_content = this.image_4_content;
		prodata.image_4_hasimage_ = this.image_4_hasimage_;
		prodata.image_4_imgName_ = this.image_4_imgName_;
		prodata.image_4_Title_ = this.image_4_Title_;
	}

}
