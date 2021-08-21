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
 * @FileName : TemplateWebStructureData.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 11, 2013
 * 
 * @Purpose : Store the Web Page Structure information
 * 
 */
public class TemplateWebStructureData implements ITemplateData
{
	public boolean	home_;
	public boolean	products_;
	public boolean	solutions_;
	public boolean	serviceSupport_;
	public boolean	aboutUS_;
	public boolean	contactUS_;

	public String getPath( )
	{
		return getDirectory( ) + "/WebStructure.json";
	}

	public void Show( )
	{
		System.out.println( " home_ 			:" + this.home_ );
		System.out.println( " products_ 		:" + this.products_ );
		System.out.println( " solutions_ 		:" + this.solutions_ );
		System.out.println( " serviceSupport_ 	:" + this.serviceSupport_ );
		System.out.println( " aboutUS_ 			:" + this.aboutUS_ );
		System.out.println( " contactUS_ 		:" + this.contactUS_ );
	}

	public void Clear( )
	{
		this.home_ = false;
		this.products_ = false;
		this.solutions_ = false;
		this.serviceSupport_ = false;
		this.aboutUS_ = false;
		this.contactUS_ = false;
	}

	public String getDirectory( )
	{
		return TemplateCommonData.externalPageDirPath_+"/Data";
	}

	public void copy( ITemplateData data )
	{
		TemplateWebStructureData temp = (TemplateWebStructureData)data;
		if( temp != null )
		{
			temp.aboutUS_ = this.aboutUS_;
			temp.contactUS_ = this.contactUS_;
			temp.home_ = this.home_;
			temp.products_ = this.products_;
			temp.serviceSupport_ = this.serviceSupport_;
			temp.solutions_ = this.solutions_;
		}

	}

}
