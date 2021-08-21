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

import utils.AppConfigReader;

/**
 * @FileName : ExternalPageGeneratorCommonData.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 11, 2013
 * 
 * @Purpose : It has the PageGeneratorCommonData
 * 
 */
public class TemplateCommonData
{
	private String	     companyURLNamePath_;
	private String	     externalPageBasePath_;
	public static String	externalPageDirPath_;
	public static String	externalPageDirImage_;
	public static String	responseCMPPath_;
	public static String exterlWebSite_;

	public TemplateCommonData( String compnayURLName )
	{
		/*this.companyURLNamePath_ = compnayURLName;
		
		AppConfigReader reader = new AppConfigReader( );
		
		reader.init( );

		this.externalPageBasePath_ = System.getProperty( "catalina.home" ) + "/" + reader.get( "EXTERNAL_PAGE_BASE_URL" );
		
		
		externalPageDirPath_ = this.externalPageBasePath_ + '/' + this.companyURLNamePath_;
		
		externalPageDirImage_ = reader.get( "BASE_SERVER_URL" )+"/SupplyMedium/External/" + this.companyURLNamePath_ + "/images/";
		
		responseCMPPath_ = "/External/" + this.companyURLNamePath_ + "/jsp/Home.jsp";
		
		exterlWebSite_ = "/External/" + this.companyURLNamePath_ + "/index.jsp";*/
		
		
		this.companyURLNamePath_ = compnayURLName;
		AppConfigReader reader = new AppConfigReader( );
		reader.init( );

		this.externalPageBasePath_ =System.getProperty( "catalina.home" ) + "/" + reader.get( "EXTERNAL_PAGE_BASE_URL" );
		
		System.out.println( "external page path="+ this.externalPageBasePath_);
		
		externalPageDirPath_ = this.externalPageBasePath_ + '/' + this.companyURLNamePath_;
		externalPageDirImage_ = "External/" + this.companyURLNamePath_ + "/images/";
		responseCMPPath_ = "/External/" + this.companyURLNamePath_ + "/jsp/Home.jsp";
		exterlWebSite_ = "/External/" + this.companyURLNamePath_ + "/index.jsp";
	}

	public void Show( )
	{
		System.out.println( "companyURLNamePath_   =" + this.companyURLNamePath_ );
		System.out.println( "externalPageBasePath_ =" + this.externalPageBasePath_ );
		System.out.println( "externalPageDirPath_  =" + externalPageDirPath_ );
	}

	public void Clear( )
	{
		this.companyURLNamePath_ = null;
		this.externalPageBasePath_ = null;
		externalPageDirPath_ = null;
	}
}
