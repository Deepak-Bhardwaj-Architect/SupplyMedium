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
public class ExternalWebSiteData implements ITemplateData
{

	public String	webURL_;
        private static ErrorMaster errorMaster_ = null;



	public String getPath( )
	{
		return getDirectory( ) + "/ExternalWebSite.json";
	}

	public String getDirectory( )
	{
		return TemplateCommonData.externalPageDirPath_ + "/Data";
	}

	public void Show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );           
            
		errorMaster_.insert( "webURL_ 		=" + this.webURL_ );
	}

	public void Clear( )
	{
		this.webURL_ = null;
	}

	public void copy( ITemplateData data )
	{
		ExternalWebSiteData contactdata = (ExternalWebSiteData)data;

		contactdata.webURL_ = this.webURL_;
	}

}
