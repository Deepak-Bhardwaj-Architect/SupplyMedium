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

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import utils.ErrorLogger;

/**
 * @FileName : PageGenerator.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Jun 1, 2013
 * 
 * @Purpose :
 * 
 */
public class PageGenerator
{

	/**
	 * @Date : Jun 1, 2013 1:13:27 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose :
	 * 
	 * @param basicdata
	 * @param pagedata
	 * 
	 */
	public int generator( ExternalPageData basicdata, PageGeneratorData pagedata )
	{
		int result = 0;
		
		result = readCompanyData( basicdata, pagedata );

		result = makeBanner( basicdata, pagedata );

		result = makeCopyrigts( basicdata, pagedata );

		result = copyCSS( pagedata );

		result = copyImages( pagedata );

		result = makeHomePage( pagedata );

		result = makeAboutUs( pagedata );

		result = makeContactUs( basicdata, pagedata );

		if( pagedata.webStructureData_.products_ )
		{
			result = makeProductPage( pagedata );
		}

		if( pagedata.webStructureData_.serviceSupport_ )
		{
			result = makeServicesPage( pagedata );
		}

		if( pagedata.webStructureData_.solutions_ )
		{
			result = makeSolutionsPage( pagedata );
		}

		return result;
	}

	public int generateExternalWebSite( ExternalPageData basicdata )
	{
		try
		{
			int result_ = 0;
			TemplateDataManager manager = new TemplateDataManager( );

			// Assigning the Common Data
			new TemplateCommonData( basicdata.companyURLName_ ); // Assigning
			                                                     // the

			ExternalWebSiteData webSiteData = new ExternalWebSiteData( );
			result_ = manager.contentExternalWebSiteRead( webSiteData );

			if( result_ == 6000 )
			{
				result_ = 0;

				PageGeneratorData data = new PageGeneratorData( );

				String pagePath = data.templateFileBasePath_ + "jsp/ExternalWebSiteTemplate.jsp";
				File templateFile = new File( pagePath );

				StringBuilder JSP_ = new StringBuilder( );
				if( templateFile.exists( ) )
				{
					try
					{
						BufferedReader br = new BufferedReader( new FileReader( templateFile ) );
						String temp;
						while( ( temp = br.readLine( ) ) != null )
						{
							JSP_.append( temp );
						}

						br.close( );

					}
					catch( Exception e )
					{
						result_ = -1;
					}

					if( result_ != -1 )
					{

						replaceString( JSP_, "@#WebURL", webSiteData.webURL_ );

						try
						{
							File filepath = new File( TemplateCommonData.externalPageDirPath_ + "/ExternalWebPage.jsp" );

							if( !filepath.exists( ) )
							{
								filepath.createNewFile( );
							}

							BufferedWriter bw;

							bw = new BufferedWriter( new FileWriter( filepath ) );
							bw.write( JSP_.toString( ) );
							bw.close( );
						}
						catch( Exception e )
						{
							result_ = -1;
						}
					}
				}

				File dirpath = new File( TemplateCommonData.externalPageDirPath_ + "/index.jsp" );
				String tempString = "<script type='text/javascript'>window.location='ExternalWebPage.jsp'</script>";
				BufferedWriter bw;
				bw = new BufferedWriter( new FileWriter( dirpath ) );
				bw.write( tempString );
				bw.close( );
				
				System.out.println(  " ------------- " + result_);

				return result_;
			}
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: PageGenerator :: generateExternalWebSite " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

		return 0;
	}

	private int copyImages( PageGeneratorData pagedata )
	{
		int result_ = 0;
		try
		{

			String pagePath = pagedata.templateFileBasePath_ + pagedata.templateMainType_ + "_" + pagedata.templateSubType_ + "/images";
			File templateFile = new File( pagePath );

			File dirpath = new File( TemplateCommonData.externalPageDirPath_ + "/images" );

			if( !dirpath.isDirectory( ) )
			{
				dirpath.mkdirs( );
			}

			if( templateFile.isDirectory( ) )
			{
				copyDirectory( templateFile, dirpath );
			}

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: PageGenerator :: copyImages " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

		return result_;
	}

	private int copyCSS( PageGeneratorData pagedata )
	{
		int result_ = 0;
		try
		{

			String pagePath = pagedata.templateFileBasePath_ + pagedata.templateMainType_ + "_" + pagedata.templateSubType_ + "/css";
			File templateFile = new File( pagePath );

			File dirpath = new File( TemplateCommonData.externalPageDirPath_ + "/css" );

			if( !dirpath.isDirectory( ) )
			{
				dirpath.mkdirs( );
			}

			if( templateFile.isDirectory( ) )
			{
				copyDirectory( templateFile, dirpath );
			}

			dirpath = new File( TemplateCommonData.externalPageDirPath_ + "/index.jsp" );
			String tempString = "<script type='text/javascript'>window.location='jsp/Home.jsp'</script>";
			BufferedWriter bw;
			bw = new BufferedWriter( new FileWriter( dirpath ) );
			bw.write( tempString );
			bw.close( );

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: PageGenerator :: copyCSS " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

		return result_;
	}

	public void replaceString( StringBuilder sBuilder, String find, String replace )
	{
		int index = -1;

		index = sBuilder.indexOf( find );
		if( index != -1 ) sBuilder.replace( index, index + find.length( ), replace );
	}

	private Dimension getWidthHeight( File FilePath )
	{
		ImageInputStream in = null;

		try
		{
			in = ImageIO.createImageInputStream( FilePath );

			final Iterator readers = ImageIO.getImageReaders( in );
			if( readers.hasNext( ) )
			{
				ImageReader reader = (ImageReader)readers.next( );
				try
				{
					reader.setInput( in );
					return new Dimension( reader.getWidth( 0 ), reader.getHeight( 0 ) );
				}
				finally
				{
					reader.dispose( );
				}
			}
		}
		catch( Exception e )
		{

		}
		finally
		{
			if( in != null ) try
			{
				in.close( );
			}
			catch( IOException e )
			{
			}
		}

		return new Dimension( 0, 0 );
	}

	public void copyDirectory( File sourceLocation, File targetLocation ) throws IOException
	{

		if( sourceLocation.isDirectory( ) )
		{
			if( !targetLocation.exists( ) )
			{
				targetLocation.mkdir( );
			}

			String [ ] children = sourceLocation.list( );
			for( int i = 0; i < children.length; i++ )
			{
				copyDirectory( new File( sourceLocation, children[i] ), new File( targetLocation, children[i] ) );
			}
		}
		else
		{

			InputStream in = new FileInputStream( sourceLocation );
			OutputStream out = new FileOutputStream( targetLocation );

			// Copy the bits from instream to outstream
			byte [ ] buf = new byte[1024];
			int len;
			while( ( len = in.read( buf ) ) > 0 )
			{
				out.write( buf, 0, len );
			}
			in.close( );
			out.close( );
		}
	}

	private int makeCopyrigts( ExternalPageData basicdata, PageGeneratorData pagedata )
	{
		int result_ = 0;

		try
		{
			String pagePath = pagedata.templateFileBasePath_ + pagedata.templateMainType_ + "_" + pagedata.templateSubType_ + "/jsp/Copyrights.jsp";
			File templateFile = new File( pagePath );

			StringBuilder JSP_ = new StringBuilder( );
			if( templateFile.exists( ) )
			{
				try
				{
					BufferedReader br = new BufferedReader( new FileReader( templateFile ) );
					String temp;
					while( ( temp = br.readLine( ) ) != null )
					{
						JSP_.append( temp );
					}

					br.close( );

				}
				catch( Exception e )
				{
					result_ = -1;
				}

				if( result_ != -1 )
				{

					String replace = "@#COPY_RIGHTS";
					int index = JSP_.indexOf( replace );
					if( index != -1 ) JSP_.replace( index, index + replace.length( ), basicdata.companyURLName_ );

					try
					{
						File dirpath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/" );

						if( !dirpath.isDirectory( ) )
						{
							dirpath.mkdirs( );
						}

						File filepath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/Copyrights.jsp" );

						if( !filepath.exists( ) )
						{
							filepath.createNewFile( );
						}

						BufferedWriter bw;

						bw = new BufferedWriter( new FileWriter( filepath ) );
						bw.write( JSP_.toString( ) );
						bw.close( );
					}
					catch( Exception e )
					{
						result_ = -1;
					}
				}
			}
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: PageGenerator :: makeCopyrigts " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

		return result_;
	}

	private int makeBanner( ExternalPageData basicdata, PageGeneratorData pagedata )
	{
		int result_ = 0;

		try
		{
			String pagePath = pagedata.templateFileBasePath_ + pagedata.templateMainType_ + "_" + pagedata.templateSubType_ + "/jsp/Banner.jsp";
			
			
			File templateFile = new File( pagePath );

			StringBuilder JSP_ = new StringBuilder( );
			if( templateFile.exists( ) )
			{
				try
				{
					BufferedReader br = new BufferedReader( new FileReader( templateFile ) );
					String temp;
					while( ( temp = br.readLine( ) ) != null )
					{
						JSP_.append( temp );
					}

					br.close( );

				}
				catch( Exception e )
				{
					ErrorLogger errLogger = ErrorLogger.instance( );
					String errMsg = "Exception :: PageGenerator :: makeBanner " + e;
					errLogger.logMsg( errMsg );
					
					result_ = -1;
				}

				if( result_ != -1 )
				{

					if( pagedata.webStructureData_.products_ )
					{
						replaceString( JSP_, "#@MENU_PRODUCT", "<li id='T_menu_products'><a href='Product.jsp'>Products</a></li>" );
					}
					else
					{
						replaceString( JSP_, "#@MENU_PRODUCT", "" );
					}

					if( pagedata.webStructureData_.solutions_ )
					{
						replaceString( JSP_, "#@MENU_SOLUTIONS", "<li id='T_menu_solutions'><a href='Solutions.jsp'>Solutions</a></li>" );
					}
					else
					{
						replaceString( JSP_, "#@MENU_SOLUTIONS", "" );
					}

					if( pagedata.webStructureData_.serviceSupport_ )
					{
						replaceString( JSP_, "#@MENU_SERVICE", "<li id='T_menu_services'><a href='Services.jsp'>Services</a></li>" );
					}
					else
					{
						replaceString( JSP_, "#@MENU_SERVICE", "" );
					}

					replaceString( JSP_, "@#CMPY_NAME", basicdata.companyURLName_ );

					try
					{
						File dirpath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/" );

						if( !dirpath.isDirectory( ) )
						{
							dirpath.mkdirs( );
						}

						File filepath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/Banner.jsp" );

						if( !filepath.exists( ) )
						{
							filepath.createNewFile( );
						}

						BufferedWriter bw;

						bw = new BufferedWriter( new FileWriter( filepath ) );
						bw.write( JSP_.toString( ) );
						bw.close( );
					}
					catch( Exception e )
					{
						ErrorLogger errLogger = ErrorLogger.instance( );
						String errMsg = "Exception :: PageGenerator :: makeBanner " + e;
						errLogger.logMsg( errMsg );
						
						result_ = -1;
					}
				}
			}
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: PageGenerator :: makeBanner " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

		return result_;
	}

	private int makeAboutUs( PageGeneratorData pagedata )
	{
		int result_ = 0;

		try
		{
			String pagePath = pagedata.templateFileBasePath_ + pagedata.templateMainType_ + "_" + pagedata.templateSubType_ + "/jsp/About.jsp";
			File templateFile = new File( pagePath );

			StringBuilder JSP_ = new StringBuilder( );
			if( templateFile.exists( ) )
			{
				try
				{
					BufferedReader br = new BufferedReader( new FileReader( templateFile ) );
					String temp;
					while( ( temp = br.readLine( ) ) != null )
					{
						JSP_.append( temp );
					}

					br.close( );

				}
				catch( Exception e )
				{
					result_ = -1;
				}

				if( result_ != -1 )
				{
					

					replaceString( JSP_, "@#ABOUT_CONTENT", pagedata.aboutUsData_.image_1_content.replaceAll( "\r\n", "<BR/>" ) );

					replaceString( JSP_, "@#ABOUT_TITLE", pagedata.aboutUsData_.image_1_Title_ );
					
					if( pagedata.aboutUsData_.image_1_hasimage_)
						
					{
						replaceString( JSP_, "@#ABOUT_IMAGE", "block" );
						
						replaceString( JSP_, "@#IMG_NAME_1", pagedata.aboutUsData_.image_1_imgName_ );
	
					}
					else
					{
						replaceString( JSP_, "@#ABOUT_IMAGE", "none" );
					}

					try
					{
						File dirpath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/" );

						if( !dirpath.isDirectory( ) )
						{
							dirpath.mkdirs( );
						}

						File filepath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/About.jsp" );

						if( !filepath.exists( ) )
						{
							filepath.createNewFile( );
						}

						BufferedWriter bw;

						bw = new BufferedWriter( new FileWriter( filepath ) );
						bw.write( JSP_.toString( ) );
						bw.close( );
					}
					catch( Exception e )
					{
						result_ = -1;
					}
				}
			}
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: PageGenerator :: makeAboutUs " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

		return result_;
	}

	private int makeContactUs( ExternalPageData basicdata, PageGeneratorData pagedata )
	{
		int result_ = 0;

		try
		{
			String pagePath = pagedata.templateFileBasePath_ + pagedata.templateMainType_ + "_" + pagedata.templateSubType_ + "/jsp/Contact.jsp";
			File templateFile = new File( pagePath );

			StringBuilder JSP_ = new StringBuilder( );
			if( templateFile.exists( ) )
			{
				try
				{
					BufferedReader br = new BufferedReader( new FileReader( templateFile ) );
					String temp;
					while( ( temp = br.readLine( ) ) != null )
					{
						JSP_.append( temp );
					}

					br.close( );

				}
				catch( Exception e )
				{
					result_ = -1;
				}

				if( result_ != -1 )
				{

					replaceString( JSP_, "@#COMPANYNAME", basicdata.companyURLName_ );

					replaceString( JSP_, "@#C_ADDRESS", pagedata.contactUsData_.adddress_ );

					replaceString( JSP_, "@#C_STATE", pagedata.contactUsData_.state_ );

					replaceString( JSP_, "@#C_ZIPCODE", pagedata.contactUsData_.zipcode_ );

					replaceString( JSP_, "@#C_COUNTRY", pagedata.contactUsData_.country_ );

					replaceString( JSP_, "@#INQUIRY", pagedata.contactUsData_.inquiry_ );
					
					replaceString( JSP_, "@#REGNKEY", basicdata.compnayRegnKey_.toString() );
					
					System.out.print("*************************************************** company Key = "+basicdata.compnayRegnKey_.toString());

					try
					{
						File dirpath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/" );

						if( !dirpath.isDirectory( ) )
						{
							dirpath.mkdirs( );
						}

						File filepath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/Contact.jsp" );

						if( !filepath.exists( ) )
						{
							filepath.createNewFile( );
						}

						BufferedWriter bw;

						bw = new BufferedWriter( new FileWriter( filepath ) );
						bw.write( JSP_.toString( ) );
						bw.close( );
					}
					catch( Exception e )
					{
						result_ = -1;
					}
				}
			}
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: PageGenerator :: makeAboutUs " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

		return result_;
	}

	private int makeHomePage( PageGeneratorData pagedata )
	{
		int result_ = 0;

		try
		{

			String homepagePath = pagedata.templateFileBasePath_ + pagedata.templateMainType_ + "_" + pagedata.templateSubType_ + "/jsp/Home.jsp";
			File templateHomeFile = new File( homepagePath );

			StringBuilder homeJSP = new StringBuilder( );
			if( templateHomeFile.exists( ) )
			{
				try
				{
					BufferedReader br = new BufferedReader( new FileReader( templateHomeFile ) );
					String temp;
					while( ( temp = br.readLine( ) ) != null )
					{
						homeJSP.append( temp );
					}

					br.close( );

				}
				catch( Exception e )
				{
					result_ = -1;
				}
				if( result_ != -1 )
				{
					String replace = "";
					
					int index = 0;

					if( pagedata.homeData_.videoURL_.equalsIgnoreCase( "" )  )
					{
						replaceString( homeJSP, "@#VIDEO_DIS", "None" );
						replaceString( homeJSP, "@#BANNER_DIS", "None" );

					}
					else
					{
						replaceString( homeJSP, "@#VIDEO_DIS", "Block" );
						replaceString( homeJSP, "@#BANNER_DIS", "Block" );
						
						 replace = "@#VIDEOURL_1";
						index = homeJSP.indexOf( replace );
						if( index != -1 ) homeJSP.replace( index, index + replace.length( ), pagedata.homeData_.videoURL_ );
						
					}
					
					replace = "@#TITLE_1";
					index = homeJSP.indexOf( replace );
					if( index != -1 ) homeJSP.replace( index, index + replace.length( ), pagedata.homeData_.videoTitle_ );

					replace = "@#CONTENT_1";
					index = homeJSP.indexOf( replace );
					if( index != -1 )
					    homeJSP.replace( index, index + replace.length( ), pagedata.homeData_.content_.replaceAll( "\r\n", "<BR/>" ) );
					
					
					
					
					Dimension imgewidth;
					
					if( pagedata.homeData_.image_1_hasimage_ )
					{
						replaceString( homeJSP, "@#IMG_1_FULL_D", "block" );

						File imagepath = new File( TemplateCommonData.externalPageDirPath_ + "/images/" + pagedata.homeData_.image_1_imgName_ );

						imgewidth = getWidthHeight( imagepath );

						//if( imgewidth.width < 500 )
							replaceString( homeJSP, "@#IMG_CONTAINER_1_CSS", "Img_Container" );
						//else
							//replaceString( homeJSP, "@#IMG_CONTAINER_1_CSS", "Img_Full_Banner" );

						replaceString( homeJSP, "@#IMG_NAME_1", pagedata.homeData_.image_1_imgName_ );

						replaceString( homeJSP, "@#IMG_TITLE_1", pagedata.homeData_.image_1_Title_ );

						replaceString( homeJSP, "@#IMG_CONTENT_1", pagedata.homeData_.image_1_content.replaceAll( "\r\n", "<BR/>" ) );

					}
					else
					{
						replaceString( homeJSP, "@#IMG_1_FULL_D", "none" );
					}

					try
					{
						File dirpath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/" );

						if( !dirpath.isDirectory( ) )
						{
							dirpath.mkdirs( );
						}

						File filepath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/Home.jsp" );

						if( !filepath.exists( ) )
						{
							filepath.createNewFile( );
						}

						BufferedWriter bw;

						bw = new BufferedWriter( new FileWriter( filepath ) );
						bw.write( homeJSP.toString( ) );
						bw.close( );
					}
					catch( Exception e )
					{
						result_ = -1;
					}

				}
			}
			else
			{
				result_ = -1;
			}
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: PageGenerator :: makeHomePage " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

		return result_;
	}

	private int makeProductPage( PageGeneratorData pagedata )
	{
		int result_ = 0;

		try
		{
			String pagePath = pagedata.templateFileBasePath_ + pagedata.templateMainType_ + "_" + pagedata.templateSubType_ + "/jsp/Product.jsp";
			File templateFile = new File( pagePath );

			System.out.println( templateFile.getAbsolutePath( ) );

			StringBuilder JSP_ = new StringBuilder( );
			if( templateFile.exists( ) )
			{
				try
				{
					BufferedReader br = new BufferedReader( new FileReader( templateFile ) );
					String temp;
					while( ( temp = br.readLine( ) ) != null )
					{
						JSP_.append( temp );
					}

					br.close( );

				}
				catch( Exception e )
				{
					result_ = -1;
				}

				if( result_ != -1 )
				{

					if( pagedata.productData_.videoURL_1_.equalsIgnoreCase( "" ) && pagedata.productData_.videoURL_2_.equalsIgnoreCase( "" ) )
					{
						replaceString( JSP_, "@#VIDEO_FULL_D", "None" );

						replaceString( JSP_, "@#VIDEO_HR_LINE_D", "None" );
						
					}
					else
					{
						replaceString( JSP_, "@#VIDEO_FULL_D", "Block" );
						replaceString( JSP_, "@#VIDEO_HR_LINE_D", "Block" );

						if( !pagedata.productData_.videoURL_1_.equalsIgnoreCase( "" ) && !pagedata.productData_.videoURL_2_.equalsIgnoreCase( "" ) )
						{
							replaceString( JSP_, "@#VIDEO_TWO_ONE_CONT", "T_video_container_Two" );
						}
						else
						{
							replaceString( JSP_, "@#VIDEO_TWO_ONE_CONT", "T_video_container_One" );
						}

						if( !pagedata.productData_.videoURL_1_.equalsIgnoreCase( "" ) )
						{
							replaceString( JSP_, "@#VIDEO_1_V_D", "block" );
							replaceString( JSP_, "@#VIDEO_1_C_D", "block" );
							replaceString( JSP_, "@#VIDEOURL_1", pagedata.productData_.videoURL_1_ );
							replaceString( JSP_, "@#VIDEO_CONTENT_1", pagedata.productData_.videoURL_1_content_.replaceAll( "\r\n", "<BR/>" ) );
						}
						else
						{
							replaceString( JSP_, "@#VIDEO_1_V_D", "None" );
							replaceString( JSP_, "@#VIDEO_1_C_D", "None" );
						}

						if( !pagedata.productData_.videoURL_2_.equalsIgnoreCase( "" ) )
						{
							replaceString( JSP_, "@#VIDEO_2_V_D", "block" );
							replaceString( JSP_, "@#VIDEO_2_C_D", "block" );
							replaceString( JSP_, "@#VIDEOURL_2", pagedata.productData_.videoURL_2_ );
							replaceString( JSP_, "@#VIDEO_CONTENT_2", pagedata.productData_.videoURL_2_content_.replaceAll( "\r\n", "<BR/>" ) );
						}
						else
						{
							replaceString( JSP_, "@#VIDEO_2_V_D", "None" );
							replaceString( JSP_, "@#VIDEO_2_C_D", "None" );
						}
					}

					// IMAGE SECTION

					Dimension imgewidth;

					if( pagedata.productData_.image_1_hasimage_ && !pagedata.productData_.image_1_Title_.equals( "" ) )
					{
							
						replaceString( JSP_, "@#IMG_1_FULL_D", "block" );
						
						replaceString( JSP_, "@#IMG_TITLE_1_D", "block");
						
						replaceString( JSP_, "@#IMG_CONTENT_1_D", "block");

						File imagepath = new File( TemplateCommonData.externalPageDirPath_ + "/images/" + pagedata.productData_.image_1_imgName_ );

						imgewidth = getWidthHeight( imagepath );

						//if( imgewidth.width < 500 )
							replaceString( JSP_, "@#IMG_CONTAINER_1_CSS", "Img_Container" );
						//else
							//replaceString( JSP_, "@#IMG_CONTAINER_1_CSS", "Img_Full_Banner" );

						replaceString( JSP_, "@#IMG_NAME_1", pagedata.productData_.image_1_imgName_ );

						replaceString( JSP_, "@#IMG_TITLE_1", pagedata.productData_.image_1_Title_ );

						replaceString( JSP_, "@#IMG_CONTENT_1", pagedata.productData_.image_1_content.replaceAll( "\r\n", "<BR/>" ) );

					}
					else
					{
						replaceString( JSP_, "@#IMG_1_FULL_D", "none" );
						
						if( pagedata.productData_.image_1_Title_ !=null )
						{
							replaceString( JSP_, "@#IMG_TITLE_1_D", "block");
							
							replaceString( JSP_, "@#IMG_TITLE_1", pagedata.productData_.image_1_Title_ );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_TITLE_1_D", "none");
						}
						
						if( pagedata.productData_.image_1_content !=null )
						{
							replaceString( JSP_, "@#IMG_CONTENT_1_D", "block");
							
							replaceString( JSP_, "@#IMG_CONTENT_1", pagedata.productData_.image_1_content.replaceAll( "\r\n", "<BR/>" ) );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_CONTENT_1_D", "none");
						}
						
					}

					if( pagedata.productData_.image_2_hasimage_ && !pagedata.productData_.image_2_Title_.equals( "" ) )
					{
						replaceString( JSP_, "@#IMG_2_FULL_D", "block" );
						
						replaceString( JSP_, "@#IMG_TITLE_2_D", "block");
						
						replaceString( JSP_, "@#IMG_CONTENT_2_D", "block");

						File imagepath = new File( TemplateCommonData.externalPageDirPath_ + "/images/" + pagedata.productData_.image_2_imgName_ );

						imgewidth = getWidthHeight( imagepath );

						//if( imgewidth.width < 500 )
							replaceString( JSP_, "@#IMG_CONTAINER_2_CSS", "Img_Container" );
						//else
							//replaceString( JSP_, "@#IMG_CONTAINER_2_CSS", "Img_Full_Banner" );

						replaceString( JSP_, "@#IMG_NAME_2", pagedata.productData_.image_2_imgName_ );

						replaceString( JSP_, "@#IMG_TITLE_2", pagedata.productData_.image_2_Title_ );

						replaceString( JSP_, "@#IMG_CONTENT_2", pagedata.productData_.image_2_content.replaceAll( "\r\n", "<BR/>" ) );

					}
					else
					{
						replaceString( JSP_, "@#IMG_2_FULL_D", "none" );
						
						if( pagedata.productData_.image_2_Title_ !=null )
						{
							replaceString( JSP_, "@#IMG_TITLE_2_D", "block");
							
							replaceString( JSP_, "@#IMG_TITLE_2", pagedata.productData_.image_2_Title_ );
							
						}
						else 
						{
							replaceString( JSP_, "@#IMG_TITLE_2_D", "none");
						}
						
						if( pagedata.productData_.image_2_content !=null )
						{
							replaceString( JSP_, "@#IMG_CONTENT_2_D", "block");
							
							replaceString( JSP_, "@#IMG_CONTENT_2", pagedata.productData_.image_2_content.replaceAll( "\r\n", "<BR/>" ) );
							
						}
						else 
						{
							replaceString( JSP_, "@#IMG_CONTENT_2_D", "none");
						}
					}

					if( pagedata.productData_.image_3_hasimage_ && !pagedata.productData_.image_3_Title_.equals( "" ))
					{
						replaceString( JSP_, "@#IMG_3_FULL_D", "block" );
						
						replaceString( JSP_, "@#IMG_CONTENT_3_D", "block");
						
						replaceString( JSP_, "@#IMG_TITLE_3_D", "block");

						File imagepath = new File( TemplateCommonData.externalPageDirPath_ + "/images/" + pagedata.productData_.image_3_imgName_ );

						imgewidth = getWidthHeight( imagepath );

						//if( imgewidth.width < 500 )
							replaceString( JSP_, "@#IMG_CONTAINER_3_CSS", "Img_Container" );
						//else
							//replaceString( JSP_, "@#IMG_CONTAINER_3_CSS", "Img_Full_Banner" );

						replaceString( JSP_, "@#IMG_NAME_3", pagedata.productData_.image_3_imgName_ );

						replaceString( JSP_, "@#IMG_TITLE_3", pagedata.productData_.image_3_Title_ );

						replaceString( JSP_, "@#IMG_CONTENT_3", pagedata.productData_.image_3_content.replaceAll( "\r\n", "<BR/>" ) );

					}
					else
					{
						replaceString( JSP_, "@#IMG_3_FULL_D", "none" );
						
						if( pagedata.productData_.image_3_Title_ !=null )
						{
							replaceString( JSP_, "@#IMG_TITLE_3_D", "block");
							
							replaceString( JSP_, "@#IMG_TITLE_3", pagedata.productData_.image_3_Title_ );
							
						}
						else 
						{
							replaceString( JSP_, "@#IMG_TITLE_3_D", "none");
						}
						
						if( pagedata.productData_.image_3_content !=null )
						{
							replaceString( JSP_, "@#IMG_CONTENT_3_D", "block");
							
							replaceString( JSP_, "@#IMG_CONTENT_3", pagedata.productData_.image_3_content.replaceAll( "\r\n", "<BR/>" ) );
							
						}
						else 
						{
							replaceString( JSP_, "@#IMG_CONTENT_3_D", "none");
						}
					}

					if( pagedata.productData_.image_4_hasimage_ && !pagedata.productData_.image_4_Title_.equals( "" ) )
					{
						replaceString( JSP_, "@#IMG_4_FULL_D", "block" );
						
						replaceString( JSP_, "@#IMG_TITLE_4_D", "block");
						
						replaceString( JSP_, "@#IMG_CONTENT_4_D", "block");
						

						File imagepath = new File( TemplateCommonData.externalPageDirPath_ + "/images/" + pagedata.productData_.image_4_imgName_ );

						imgewidth = getWidthHeight( imagepath );

						//if( imgewidth.width < 500 )
							replaceString( JSP_, "@#IMG_CONTAINER_4_CSS", "Img_Container" );
						//else
							//replaceString( JSP_, "@#IMG_CONTAINER_4_CSS", "Img_Full_Banner" );

						replaceString( JSP_, "@#IMG_NAME_4", pagedata.productData_.image_4_imgName_ );

						replaceString( JSP_, "@#IMG_TITLE_4", pagedata.productData_.image_4_Title_ );

						replaceString( JSP_, "@#IMG_CONTENT_4", pagedata.productData_.image_4_content.replaceAll( "\r\n", "<BR/>" ) );

					}
					else
					{
						replaceString( JSP_, "@#IMG_4_FULL_D", "none" );
						
						if( pagedata.productData_.image_4_Title_ !=null )
						{
							replaceString( JSP_, "@#IMG_TITLE_4_D", "block");
							
							replaceString( JSP_, "@#IMG_TITLE_4", pagedata.productData_.image_4_Title_ );
						
						}
						else 
						{
							replaceString( JSP_, "@#IMG_TITLE_4_D", "none");
						}
						
						if( pagedata.productData_.image_4_content !=null )
						{
							replaceString( JSP_, "@#IMG_CONTENT_4_D", "block");
							
							replaceString( JSP_, "@#IMG_CONTENT_4", pagedata.productData_.image_4_content.replaceAll( "\r\n", "<BR/>" ) );
				
						}
						else 
						{
							replaceString( JSP_, "@#IMG_CONTENT_4_D", "none");
						}
					}

					System.out.println( JSP_ );

					try
					{
						File dirpath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/" );

						if( !dirpath.isDirectory( ) )
						{
							dirpath.mkdirs( );
						}

						File filepath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/Product.jsp" );

						if( !filepath.exists( ) )
						{
							filepath.createNewFile( );
						}

						BufferedWriter bw;

						bw = new BufferedWriter( new FileWriter( filepath ) );
						bw.write( JSP_.toString( ) );
						bw.close( );
					}
					catch( Exception e )
					{
						result_ = -1;
					}
				}
			}
			else
			{
				result_ = -1;
			}
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: PageGenerator :: makeProductPage " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}
		return result_;
	}

	private int makeSolutionsPage( PageGeneratorData pagedata )
	{
		int result_ = 0;

		try
		{
			String pagePath = pagedata.templateFileBasePath_ + pagedata.templateMainType_ + "_" + pagedata.templateSubType_ + "/jsp/Solutions.jsp";
			File templateFile = new File( pagePath );

			System.out.println( templateFile.getAbsolutePath( ) );

			StringBuilder JSP_ = new StringBuilder( );
			if( templateFile.exists( ) )
			{
				try
				{
					BufferedReader br = new BufferedReader( new FileReader( templateFile ) );
					String temp;
					while( ( temp = br.readLine( ) ) != null )
					{
						JSP_.append( temp );
					}

					br.close( );

				}
				catch( Exception e )
				{
					result_ = -1;
				}

				if( result_ != -1 )
				{
					System.out.println( JSP_ );

					if( pagedata.solutionsData_.videoURL_1_.equalsIgnoreCase( "" ) && pagedata.solutionsData_.videoURL_2_.equalsIgnoreCase( "" ) )
					{
						replaceString( JSP_, "@#VIDEO_FULL_D", "None" );

						replaceString( JSP_, "@#VIDEO_HR_LINE_D", "None" );

					}
					else
					{
						replaceString( JSP_, "@#VIDEO_FULL_D", "Block" );
						replaceString( JSP_, "@#VIDEO_HR_LINE_D", "Block" );

						if( !pagedata.solutionsData_.videoURL_1_.equalsIgnoreCase( "" ) && !pagedata.solutionsData_.videoURL_2_.equalsIgnoreCase( "" ) )
						{
							replaceString( JSP_, "@#VIDEO_TWO_ONE_CONT", "T_video_container_Two" );
						}
						else
						{
							replaceString( JSP_, "@#VIDEO_TWO_ONE_CONT", "T_video_container_One" );
						}

						if( !pagedata.solutionsData_.videoURL_1_.equalsIgnoreCase( "" ) )
						{
							replaceString( JSP_, "@#VIDEO_1_V_D", "block" );
							replaceString( JSP_, "@#VIDEO_1_C_D", "block" );
							replaceString( JSP_, "@#VIDEOURL_1", pagedata.solutionsData_.videoURL_1_ );
							replaceString( JSP_, "@#VIDEO_CONTENT_1", pagedata.solutionsData_.videoURL_1_content_.replaceAll( "\r\n", "<BR/>" ) );
						}
						else
						{
							replaceString( JSP_, "@#VIDEO_1_V_D", "None" );
							replaceString( JSP_, "@#VIDEO_1_C_D", "None" );
						}

						if( !pagedata.solutionsData_.videoURL_2_.equalsIgnoreCase( "" ) )
						{
							replaceString( JSP_, "@#VIDEO_2_V_D", "block" );
							replaceString( JSP_, "@#VIDEO_2_C_D", "block" );
							replaceString( JSP_, "@#VIDEOURL_2", pagedata.solutionsData_.videoURL_2_ );
							replaceString( JSP_, "@#VIDEO_CONTENT_2", pagedata.solutionsData_.videoURL_2_content_.replaceAll( "\r\n", "<BR/>" ) );
						}
						else
						{
							replaceString( JSP_, "@#VIDEO_2_V_D", "None" );
							replaceString( JSP_, "@#VIDEO_2_C_D", "None" );
						}
					}

					// IMAGE SECTION

					Dimension imgewidth;

					if( pagedata.solutionsData_.image_1_hasimage_  && !pagedata.solutionsData_.image_1_Title_.equals( "" ))
					{
						replaceString( JSP_, "@#IMG_1_FULL_D", "block" );
						replaceString( JSP_, "@#IMG_TITLE_1_D", "block");
						replaceString( JSP_, "@#IMG_CONTENT_1_D", "block");

						File imagepath = new File( TemplateCommonData.externalPageDirPath_ + "/images/" + pagedata.solutionsData_.image_1_imgName_ );

						imgewidth = getWidthHeight( imagepath );

						//if( imgewidth.width < 500 )
							replaceString( JSP_, "@#IMG_CONTAINER_1_CSS", "Img_Container" );
						//else
							//replaceString( JSP_, "@#IMG_CONTAINER_1_CSS", "Img_Full_Banner" );

						replaceString( JSP_, "@#IMG_NAME_1", pagedata.solutionsData_.image_1_imgName_ );

						replaceString( JSP_, "@#IMG_TITLE_1", pagedata.solutionsData_.image_1_Title_ );

						replaceString( JSP_, "@#IMG_CONTENT_1", pagedata.solutionsData_.image_1_content.replaceAll( "\r\n", "<BR/>" ) );

					}
					else
					{
						replaceString( JSP_, "@#IMG_1_FULL_D", "none" );
						
						if( pagedata.solutionsData_.image_1_Title_ !=null )
						{
							replaceString( JSP_, "@#IMG_TITLE_1_D", "block");
							
							replaceString( JSP_, "@#IMG_TITLE_1", pagedata.solutionsData_.image_1_Title_ );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_TITLE_1_D", "none");
						}
						
						if( pagedata.solutionsData_.image_1_content !=null )
						{
							replaceString( JSP_, "@#IMG_CONTENT_1_D", "block");
							
							replaceString( JSP_, "@#IMG_CONTENT_1", pagedata.solutionsData_.image_1_content.replaceAll( "\r\n", "<BR/>" ) );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_CONTENT_1_D", "none");
						}
					}

					if( pagedata.solutionsData_.image_2_hasimage_ && !pagedata.solutionsData_.image_2_Title_.equals( "" ) )
					{
						replaceString( JSP_, "@#IMG_2_FULL_D", "block" );
						replaceString( JSP_, "@#IMG_TITLE_2_D", "block");
						replaceString( JSP_, "@#IMG_CONTENT_2_D", "block");

						File imagepath = new File( TemplateCommonData.externalPageDirPath_ + "/images/" + pagedata.solutionsData_.image_2_imgName_ );

						imgewidth = getWidthHeight( imagepath );

						//if( imgewidth.width < 500 )
							replaceString( JSP_, "@#IMG_CONTAINER_2_CSS", "Img_Container" );
						//else
							//replaceString( JSP_, "@#IMG_CONTAINER_2_CSS", "Img_Full_Banner" );

						replaceString( JSP_, "@#IMG_NAME_2", pagedata.solutionsData_.image_2_imgName_ );

						replaceString( JSP_, "@#IMG_TITLE_2", pagedata.solutionsData_.image_2_Title_ );

						replaceString( JSP_, "@#IMG_CONTENT_2", pagedata.solutionsData_.image_2_content.replaceAll( "\r\n", "<BR/>" ) );

					}
					else
					{
						replaceString( JSP_, "@#IMG_2_FULL_D", "none" );
						
						if( pagedata.solutionsData_.image_2_Title_ !=null )
						{
							replaceString( JSP_, "@#IMG_TITLE_2_D", "block");
							
							replaceString( JSP_, "@#IMG_TITLE_2", pagedata.solutionsData_.image_2_Title_ );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_TITLE_2_D", "none");
						}
						
						if( pagedata.solutionsData_.image_2_content !=null )
						{
							replaceString( JSP_, "@#IMG_CONTENT_2_D", "block");
							
							replaceString( JSP_, "@#IMG_CONTENT_2", pagedata.solutionsData_.image_1_content.replaceAll( "\r\n", "<BR/>" ) );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_CONTENT_2_D", "none");
						}
					}

					if( pagedata.solutionsData_.image_3_hasimage_ && !pagedata.solutionsData_.image_3_Title_.equals( "" ) )
					{
						replaceString( JSP_, "@#IMG_3_FULL_D", "block" );
						replaceString( JSP_, "@#IMG_TITLE_3_D", "block");
						replaceString( JSP_, "@#IMG_CONTENT_3_D", "block");

						File imagepath = new File( TemplateCommonData.externalPageDirPath_ + "/images/" + pagedata.solutionsData_.image_3_imgName_ );

						imgewidth = getWidthHeight( imagepath );

						//if( imgewidth.width < 500 )
							replaceString( JSP_, "@#IMG_CONTAINER_3_CSS", "Img_Container" );
						//else
							//replaceString( JSP_, "@#IMG_CONTAINER_3_CSS", "Img_Full_Banner" );

						replaceString( JSP_, "@#IMG_NAME_3", pagedata.solutionsData_.image_3_imgName_ );

						replaceString( JSP_, "@#IMG_TITLE_3", pagedata.solutionsData_.image_3_Title_ );

						replaceString( JSP_, "@#IMG_CONTENT_3", pagedata.solutionsData_.image_3_content.replaceAll( "\r\n", "<BR/>" ) );

					}
					else
					{
						replaceString( JSP_, "@#IMG_3_FULL_D", "none" );
						
						if( pagedata.solutionsData_.image_3_Title_ !=null )
						{
							replaceString( JSP_, "@#IMG_TITLE_3_D", "block");
							
							replaceString( JSP_, "@#IMG_TITLE_3", pagedata.solutionsData_.image_3_Title_ );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_TITLE_3_D", "none");
						}
						
						if( pagedata.solutionsData_.image_3_content !=null )
						{
							replaceString( JSP_, "@#IMG_CONTENT_3_D", "block");
							
							replaceString( JSP_, "@#IMG_CONTENT_3", pagedata.solutionsData_.image_1_content.replaceAll( "\r\n", "<BR/>" ) );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_CONTENT_3_D", "none");
						}
					}

					if( pagedata.solutionsData_.image_4_hasimage_ && !pagedata.solutionsData_.image_4_Title_.equals( "" ) )
					{
						replaceString( JSP_, "@#IMG_4_FULL_D", "block" );
						replaceString( JSP_, "@#IMG_TITLE_4_D", "block");
						replaceString( JSP_, "@#IMG_CONTENT_4_D", "block");

						File imagepath = new File( TemplateCommonData.externalPageDirPath_ + "/images/" + pagedata.solutionsData_.image_4_imgName_ );

						imgewidth = getWidthHeight( imagepath );

						//if( imgewidth.width < 500 )
							replaceString( JSP_, "@#IMG_CONTAINER_4_CSS", "Img_Container" );
						//else
							//replaceString( JSP_, "@#IMG_CONTAINER_4_CSS", "Img_Full_Banner" );

						replaceString( JSP_, "@#IMG_NAME_4", pagedata.solutionsData_.image_4_imgName_ );

						replaceString( JSP_, "@#IMG_TITLE_4", pagedata.solutionsData_.image_4_Title_ );

						replaceString( JSP_, "@#IMG_CONTENT_4", pagedata.solutionsData_.image_4_content.replaceAll( "\r\n", "<BR/>" ) );

					}
					else
					{
						replaceString( JSP_, "@#IMG_4_FULL_D", "none" );
						
						if( pagedata.solutionsData_.image_4_Title_ !=null )
						{
							replaceString( JSP_, "@#IMG_TITLE_4_D", "block");
							
							replaceString( JSP_, "@#IMG_TITLE_4", pagedata.solutionsData_.image_1_Title_ );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_TITLE_4_D", "none");
						}
						
						if( pagedata.solutionsData_.image_4_content !=null )
						{
							replaceString( JSP_, "@#IMG_CONTENT_4_D", "block");
							
							replaceString( JSP_, "@#IMG_CONTENT_4", pagedata.solutionsData_.image_4_content.replaceAll( "\r\n", "<BR/>" ) );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_CONTENT_4_D", "none");
						}
					}

					System.out.println( JSP_ );

					try
					{
						File dirpath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/" );

						if( !dirpath.isDirectory( ) )
						{
							dirpath.mkdirs( );
						}

						File filepath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/Solutions.jsp" );

						if( !filepath.exists( ) )
						{
							filepath.createNewFile( );
						}

						BufferedWriter bw;

						bw = new BufferedWriter( new FileWriter( filepath ) );
						bw.write( JSP_.toString( ) );
						bw.close( );
					}
					catch( Exception e )
					{
						result_ = -1;
					}
				}
			}
			else
			{
				result_ = -1;
			}
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: PageGenerator :: makeProductPage " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}
		return result_;
	}

	/**
	 * @Date : Jun 1, 2013 7:24:16 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose :
	 * 
	 * @param pagedata
	 * 
	 */
	private int makeServicesPage( PageGeneratorData pagedata )
	{

		int result_ = 0;

		try
		{
			String pagePath = pagedata.templateFileBasePath_ + pagedata.templateMainType_ + "_" + pagedata.templateSubType_ + "/jsp/Services.jsp";
			File templateFile = new File( pagePath );

			System.out.println( templateFile.getAbsolutePath( ) );

			StringBuilder JSP_ = new StringBuilder( );
			if( templateFile.exists( ) )
			{
				try
				{
					BufferedReader br = new BufferedReader( new FileReader( templateFile ) );
					String temp;
					while( ( temp = br.readLine( ) ) != null )
					{
						JSP_.append( temp );
					}

					br.close( );

				}
				catch( Exception e )
				{
					result_ = -1;
				}

				if( result_ != -1 )
				{
					System.out.println( JSP_ );

					if( pagedata.servicesData_.videoURL_1_.equalsIgnoreCase( "" ) && pagedata.servicesData_.videoURL_2_.equalsIgnoreCase( "" ) )
					{
						replaceString( JSP_, "@#VIDEO_FULL_D", "None" );

						replaceString( JSP_, "@#VIDEO_HR_LINE_D", "None" );

					}
					else
					{
						replaceString( JSP_, "@#VIDEO_FULL_D", "Block" );
						replaceString( JSP_, "@#VIDEO_HR_LINE_D", "Block" );

						if( !pagedata.servicesData_.videoURL_1_.equalsIgnoreCase( "" ) && !pagedata.servicesData_.videoURL_2_.equalsIgnoreCase( "" ) )
						{
							replaceString( JSP_, "@#VIDEO_TWO_ONE_CONT", "T_video_container_Two" );
						}
						else
						{
							replaceString( JSP_, "@#VIDEO_TWO_ONE_CONT", "T_video_container_One" );
						}

						if( !pagedata.servicesData_.videoURL_1_.equalsIgnoreCase( "" ) )
						{
							replaceString( JSP_, "@#VIDEO_1_V_D", "block" );
							replaceString( JSP_, "@#VIDEO_1_C_D", "block" );
							replaceString( JSP_, "@#VIDEOURL_1", pagedata.servicesData_.videoURL_1_ );
							replaceString( JSP_, "@#VIDEO_CONTENT_1", pagedata.servicesData_.videoURL_1_content_.replaceAll( "\r\n", "<BR/>" ) );
						}
						else
						{
							replaceString( JSP_, "@#VIDEO_1_V_D", "None" );
							replaceString( JSP_, "@#VIDEO_1_C_D", "None" );
						}

						if( !pagedata.servicesData_.videoURL_2_.equalsIgnoreCase( "" ) )
						{
							replaceString( JSP_, "@#VIDEO_2_V_D", "block" );
							replaceString( JSP_, "@#VIDEO_2_C_D", "block" );
							replaceString( JSP_, "@#VIDEOURL_2", pagedata.servicesData_.videoURL_2_ );
							replaceString( JSP_, "@#VIDEO_CONTENT_2", pagedata.servicesData_.videoURL_2_content_.replaceAll( "\r\n", "<BR/>" ) );
						}
						else
						{
							replaceString( JSP_, "@#VIDEO_2_V_D", "None" );
							replaceString( JSP_, "@#VIDEO_2_C_D", "None" );
						}
					}

					// IMAGE SECTION

					Dimension imgewidth;

					if( pagedata.servicesData_.image_1_hasimage_ && !pagedata.servicesData_.image_1_Title_.equals( "" ) )
					{
						replaceString( JSP_, "@#IMG_1_FULL_D", "block" );
						replaceString( JSP_, "@#IMG_TITLE_1_D", "block");
						replaceString( JSP_, "@#IMG_CONTENT_1_D", "block");

						File imagepath = new File( TemplateCommonData.externalPageDirPath_ + "/images/" + pagedata.servicesData_.image_1_imgName_ );

						imgewidth = getWidthHeight( imagepath );

						//if( imgewidth.width < 500 )
							replaceString( JSP_, "@#IMG_CONTAINER_1_CSS", "Img_Container" );
						//else
						//	replaceString( JSP_, "@#IMG_CONTAINER_1_CSS", "Img_Full_Banner" );

						replaceString( JSP_, "@#IMG_NAME_1", pagedata.servicesData_.image_1_imgName_ );

						replaceString( JSP_, "@#IMG_TITLE_1", pagedata.servicesData_.image_1_Title_ );

						replaceString( JSP_, "@#IMG_CONTENT_1", pagedata.servicesData_.image_1_content.replaceAll( "\r\n", "<BR/>" ) );

					}
					else
					{
						replaceString( JSP_, "@#IMG_1_FULL_D", "none" );
						
						if( pagedata.servicesData_.image_1_Title_ !=null )
						{
							replaceString( JSP_, "@#IMG_TITLE_1_D", "block");
							
							replaceString( JSP_, "@#IMG_TITLE_1", pagedata.servicesData_.image_1_Title_ );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_TITLE_1_D", "none");
						}
						
						if( pagedata.servicesData_.image_1_content !=null )
						{
							replaceString( JSP_, "@#IMG_CONTENT_1_D", "block");
							
							replaceString( JSP_, "@#IMG_CONTENT_1", pagedata.servicesData_.image_1_content.replaceAll( "\r\n", "<BR/>" ) );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_CONTENT_1_D", "none");
						}
					}

					if( pagedata.servicesData_.image_2_hasimage_ && !pagedata.servicesData_.image_2_Title_.equals( "" ) )
					{
						replaceString( JSP_, "@#IMG_2_FULL_D", "block" );
						replaceString( JSP_, "@#IMG_TITLE_2_D", "block");
						replaceString( JSP_, "@#IMG_CONTENT_2_D", "block");

						File imagepath = new File( TemplateCommonData.externalPageDirPath_ + "/images/" + pagedata.servicesData_.image_2_imgName_ );

						imgewidth = getWidthHeight( imagepath );

						//( imgewidth.width < 500 )
							replaceString( JSP_, "@#IMG_CONTAINER_2_CSS", "Img_Container" );
						//else
							//replaceString( JSP_, "@#IMG_CONTAINER_2_CSS", "Img_Full_Banner" );

						replaceString( JSP_, "@#IMG_NAME_2", pagedata.servicesData_.image_2_imgName_ );

						replaceString( JSP_, "@#IMG_TITLE_2", pagedata.servicesData_.image_2_Title_ );

						replaceString( JSP_, "@#IMG_CONTENT_2", pagedata.servicesData_.image_2_content.replaceAll( "\r\n", "<BR/>" ) );

					}
					else
					{
						replaceString( JSP_, "@#IMG_2_FULL_D", "none" );
						
						if( pagedata.servicesData_.image_2_Title_ !=null )
						{
							replaceString( JSP_, "@#IMG_TITLE_2_D", "block");
							
							replaceString( JSP_, "@#IMG_TITLE_2", pagedata.servicesData_.image_2_Title_ );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_TITLE_2_D", "none");
						}
						
						if( pagedata.servicesData_.image_2_content !=null )
						{
							replaceString( JSP_, "@#IMG_CONTENT_2_D", "block");
							
							replaceString( JSP_, "@#IMG_CONTENT_2", pagedata.servicesData_.image_2_content.replaceAll( "\r\n", "<BR/>" ) );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_CONTENT_2_D", "none");
						}
					}

					if( pagedata.servicesData_.image_3_hasimage_ && !pagedata.servicesData_.image_3_Title_.equals( "" ) )
					{
						replaceString( JSP_, "@#IMG_3_FULL_D", "block" );
						replaceString( JSP_, "@#IMG_TITLE_3_D", "block");
						replaceString( JSP_, "@#IMG_CONTENT_3_D", "block");

						File imagepath = new File( TemplateCommonData.externalPageDirPath_ + "/images/" + pagedata.servicesData_.image_3_imgName_ );

						imgewidth = getWidthHeight( imagepath );

						//if( imgewidth.width < 500 )
							replaceString( JSP_, "@#IMG_CONTAINER_3_CSS", "Img_Container" );
						//else
							//replaceString( JSP_, "@#IMG_CONTAINER_3_CSS", "Img_Full_Banner" );

						replaceString( JSP_, "@#IMG_NAME_3", pagedata.servicesData_.image_3_imgName_ );

						replaceString( JSP_, "@#IMG_TITLE_3", pagedata.servicesData_.image_3_Title_ );

						replaceString( JSP_, "@#IMG_CONTENT_3", pagedata.servicesData_.image_3_content.replaceAll( "\r\n", "<BR/>" ) );

					}
					else
					{
						replaceString( JSP_, "@#IMG_3_FULL_D", "none" );
						
						if( pagedata.servicesData_.image_3_Title_ !=null )
						{
							replaceString( JSP_, "@#IMG_TITLE_3_D", "block");
							
							replaceString( JSP_, "@#IMG_TITLE_3", pagedata.servicesData_.image_3_Title_ );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_TITLE_3_D", "none");
						}
						
						if( pagedata.servicesData_.image_3_content !=null )
						{
							replaceString( JSP_, "@#IMG_CONTENT_3_D", "block");
							
							replaceString( JSP_, "@#IMG_CONTENT_3", pagedata.servicesData_.image_3_content.replaceAll( "\r\n", "<BR/>" ) );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_CONTENT_3_D", "none");
						}
					}

					if( pagedata.servicesData_.image_4_hasimage_ && !pagedata.servicesData_.image_4_Title_.equals( "" ) )
					{
						
						replaceString( JSP_, "@#IMG_4_FULL_D", "block" );
						replaceString( JSP_, "@#IMG_TITLE_4_D", "block");
						replaceString( JSP_, "@#IMG_CONTENT_4_D", "block");

						File imagepath = new File( TemplateCommonData.externalPageDirPath_ + "/images/" + pagedata.servicesData_.image_4_imgName_ );

						imgewidth = getWidthHeight( imagepath );

						//if( imgewidth.width < 500 )
							replaceString( JSP_, "@#IMG_CONTAINER_4_CSS", "Img_Container" );
						//else
							//replaceString( JSP_, "@#IMG_CONTAINER_4_CSS", "Img_Full_Banner" );

						replaceString( JSP_, "@#IMG_NAME_4", pagedata.servicesData_.image_4_imgName_ );

						replaceString( JSP_, "@#IMG_TITLE_4", pagedata.servicesData_.image_4_Title_ );

						replaceString( JSP_, "@#IMG_CONTENT_4", pagedata.servicesData_.image_4_content.replaceAll( "\r\n", "<BR/>" ) );

					}
					else
					{
						replaceString( JSP_, "@#IMG_4_FULL_D", "none" );
						
						if( pagedata.servicesData_.image_4_Title_ !=null )
						{
							replaceString( JSP_, "@#IMG_TITLE_4_D", "block");
							
							replaceString( JSP_, "@#IMG_TITLE_4", pagedata.servicesData_.image_4_Title_ );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_TITLE_4_D", "none");
						}
						
						if( pagedata.servicesData_.image_4_content !=null )
						{
							replaceString( JSP_, "@#IMG_CONTENT_4_D", "block");
							
							replaceString( JSP_, "@#IMG_CONTENT_4", pagedata.servicesData_.image_4_content.replaceAll( "\r\n", "<BR/>" ) );
						}
						else 
						{
							replaceString( JSP_, "@#IMG_CONTENT_4_D", "none");
						}
					}

					System.out.println( JSP_ );

					try
					{
						File dirpath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/" );

						if( !dirpath.isDirectory( ) )
						{
							dirpath.mkdirs( );
						}

						File filepath = new File( TemplateCommonData.externalPageDirPath_ + "/jsp/Services.jsp" );

						if( !filepath.exists( ) )
						{
							filepath.createNewFile( );
						}

						BufferedWriter bw;

						bw = new BufferedWriter( new FileWriter( filepath ) );
						bw.write( JSP_.toString( ) );
						bw.close( );
					}
					catch( Exception e )
					{
						result_ = -1;
					}
				}
			}
			else
			{
				result_ = -1;
			}
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: PageGenerator :: makeProductPage " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}
		return result_;

	}

	private int readCompanyData( ExternalPageData basicdata, PageGeneratorData pagedata )
	{
		TemplateDataManager manager = new TemplateDataManager( );

		// Assigning the Common Data
		new TemplateCommonData( basicdata.companyURLName_ ); // Assigning the

		// Reading the Page Structure
		pagedata.webStructureData_ = new TemplateWebStructureData( );
		manager.webStructureRead( pagedata.webStructureData_ );

		if( pagedata.webStructureData_ != null )
		{
			if( pagedata.webStructureData_.home_ )
			{
				pagedata.homeData_ = new TemplateFillContentHomeData( );
				manager.contentHomeRead( pagedata.homeData_ );
			}

			if( pagedata.webStructureData_.products_ )
			{
				pagedata.productData_ = new TemplateFillContentProductsData( );
				manager.contentProductRead( pagedata.productData_ );
			}

			if( pagedata.webStructureData_.serviceSupport_ )
			{
				pagedata.servicesData_ = new TemplateFillContentServiceData( );
				manager.contentServiceRead( pagedata.servicesData_ );
			}

			if( pagedata.webStructureData_.solutions_ )
			{
				pagedata.solutionsData_ = new TemplateFillContentSolutionsData( );
				manager.contentSolutionsRead( pagedata.solutionsData_ );
			}

			if( pagedata.webStructureData_.contactUS_ )
			{
				pagedata.contactUsData_ = new TemplateFillContentContactUsData( );
				manager.contentContactUsRead( pagedata.contactUsData_ );
			}

			if( pagedata.webStructureData_.aboutUS_ )
			{
				pagedata.aboutUsData_ = new TemplateFillContentAboutUsData( );
				manager.contentAboutUsRead( pagedata.aboutUsData_ );
			}

		}

		return 0;

	}

}
