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
 * @FileName : TemplateManager.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Jun 1, 2013
 * 
 * @Purpose :
 * 
 */
public class PageGeneratorData
{
	public String	                        templateFileBasePath_=System.getProperty( "catalina.home" ) + "/webapps/SupplyMedium/Views/ExternalPage/";

	public enumTemplateMainType	            templateMainType_;

	public enumTemplateSubType	            templateSubType_;

	public TemplateWebStructureData	        webStructureData_;

	public TemplateFillContentHomeData	    homeData_;

	public TemplateFillContentProductsData	productData_;

	public TemplateFillContentServiceData	servicesData_;

	public TemplateFillContentSolutionsData	solutionsData_;

	public TemplateFillContentContactUsData	contactUsData_;

	public TemplateFillContentAboutUsData	aboutUsData_;

	public void Show( )
	{
		
		System.out.println( "templateFileBasePath_ =" + this.templateFileBasePath_ );
		
		
		System.out.println( "templateMainType_ =" + this.templateMainType_ );

		System.out.println( "templateSubType_ =" + this.templateSubType_ );

		System.out.println( "=== Web Structure =====" );
		
		webStructureData_.Show( );

		System.out.println( "=== Home Page =====" );
		homeData_.Show( );

		System.out.println( "=== Product Page =====" );
		productData_.Show( );

		System.out.println( "=== Service Page =====" );
		servicesData_.Show( );

		System.out.println( "=== Solution Page =====" );
		solutionsData_.Show( );

		System.out.println( "=== Contact Us Page =====" );
		contactUsData_.Show( );

		System.out.println( "=== about Us Page =====" );
		aboutUsData_.Show( );
	}

	public void Clear( )
	{
		this.templateMainType_ = null;
		this.templateSubType_ = null;
		this.webStructureData_ = null;
		this.homeData_ = null;
		this.productData_ = null;
		this.servicesData_ = null;
		this.solutionsData_ = null;
		this.contactUsData_ = null;
		this.aboutUsData_ = null;
	}

}
