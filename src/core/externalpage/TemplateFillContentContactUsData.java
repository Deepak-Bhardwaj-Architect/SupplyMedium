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
public class TemplateFillContentContactUsData implements ITemplateData
{

	public String	country_;
	public String	adddress_;
	public String	city_;
	public String	state_;
	public String	zipcode_;
	public String	inquiry_;

	public String getPath( )
	{
		return getDirectory( ) + "/Content_ContactUs.json";
	}

	public String getDirectory( )
	{
		return TemplateCommonData.externalPageDirPath_ + "/Data";
	}

	public void Show( )
	{
		System.out.println( "country_ 		=" + this.country_ );
		System.out.println( "adddress_ 	=" + this.adddress_ );
		System.out.println( "city_ 		=" + this.city_ );
		System.out.println( "state_ 		=" + this.state_ );
		System.out.println( "zipcode_ 		=" + this.zipcode_ );
		System.out.println( "inquiry_ 		=" + this.inquiry_ );
	}

	public void Clear( )
	{
		this.country_ = null;
		this.adddress_ = null;
		this.city_ = null;
		this.state_ = null;
		this.zipcode_ = null;
		this.inquiry_ = null;
	}

	public void copy( ITemplateData data )
	{
		TemplateFillContentContactUsData contactdata = (TemplateFillContentContactUsData)data;

		contactdata.country_ 	= this.country_;
		contactdata.adddress_ 	= this.adddress_;
		contactdata.city_ 		= this.city_;
		contactdata.state_ 		= this.state_;
		contactdata.zipcode_ 	= this.zipcode_;
		contactdata.inquiry_ 	= this.inquiry_;
	}

}
