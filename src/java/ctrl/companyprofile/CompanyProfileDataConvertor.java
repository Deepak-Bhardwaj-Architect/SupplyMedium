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
package ctrl.companyprofile;

import com.google.gson.Gson;
import core.common.FileUploadPath;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import utils.ErrorLogger;
import core.companyprofile.CompanyProfileData;
import core.login.SessionData;
import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;
import java.io.ByteArrayInputStream;
import java.io.File;
import utils.AppConfigReader;
import utils.ErrorMaster;
import utils.PathBuilder;
import utils.PictureStore;
import utils.StringHolder;

/**
 * @FileName	: CompanyProfileDataConvertor.java
 *
 * @author 		: Anbazhagan
 *
 * @Date 		: Mar 14, 2013
 *
 * @Purpose 		: Convert the HTTPServlet Request to Company Profile Data
 *
 */
public class CompanyProfileDataConvertor
{
	public int convert(HttpServletRequest request,CompanyProfileData profiledata)
	{
		try
		{
                        
			Map<String,String[]> reqMap=request.getParameterMap( );
			
			//ErrorMaster errorMaster_ = new ErrorMaster( );
                        ErrorLogger errLogger_ = ErrorLogger.instance( );
                        //if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
			
                        //errLogger_.logMsg( "53 enter");
			if(reqMap.containsKey( "companyname" ))
			{
				profiledata.companyName_=request.getParameter("companyname");
			}
			//errLogger_.logMsg( "59"+profiledata.companyName_);
			if(reqMap.containsKey( "signupas" ))
			{
				profiledata.signupas_=request.getParameter( "signupas" );
				
			}
			//errLogger_.logMsg( "65"+profiledata.signupas_);
			if(reqMap.containsKey( "businesscategory" ))
			{
				profiledata.businessCategory_=request.getParameter("businesscategory");                                
			}			
			//errLogger_.logMsg( "70"+profiledata.businessCategory_);
			if(reqMap.containsKey( "companyid" ))
			{
				profiledata.companyID_=request.getParameter("companyid");
			}
			//errLogger_.logMsg( "75"+profiledata.companyID_);
			//CompanyRegnKey regnKey = new CompanyRegnKey( );
			
			HttpSession session = request.getSession( );
			
			SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
			
			//errLogger_.logMsg( "session data="+sessionData );
			
			//regnKey.companyPhoneNo_ = sessionData.companyRegnKeyStr_;
			
			profiledata.companyRegnKey_=sessionData.companyRegnKeyStr_;
			
			if(reqMap.containsKey( "plan" ))
			{
				profiledata.pricingOption_=request.getParameter("plan");
			}
			//errLogger_.logMsg( "92"+profiledata.pricingOption_);
			if(reqMap.containsKey( "themeSelect" ))
			{
				profiledata.theme_=request.getParameter("themeSelect");
			}			
			//errLogger_.logMsg( "97"+profiledata.theme_);
                        /* - */
			PathBuilder pathBuilder = new PathBuilder( );
			
			StringHolder logoPath = new StringHolder( );
                        
                        //logoPath.value=request.getServletContext().getRealPath("/").replace("\\", "/");
                        logoPath.value=FileUploadPath.getFileUploadPath(request);
                        //logoPath.value=AppConfigReader.instance( ).get( "JSP_PATH" ) + "/";
                        //errLogger_.logMsg( "107"+logoPath.value);
                        int pathResult = pathBuilder.getCompanyLogoPath( profiledata.companyName_, profiledata.companyRegnKey_, logoPath );
                        //errLogger_.logMsg( "109"+pathResult);
                        if( pathResult == 0 )  // Logo path fetch successfully
                        {
                                PictureStore pictureStoreObj = new PictureStore( );

                                Object fileObject = request.getAttribute("logo" );
                                
                                StringHolder storedPath = new StringHolder( );

                                int storeResult = pictureStoreObj.storeImage( fileObject, logoPath.value, "logo", storedPath );
                                //errLogger_.logMsg( "119"+storeResult+"nd"+storedPath);
                                //regnDataObj.logo_ = storedPath.value;
                                if( storeResult == 0 )  // Logo stored in folder successfully
                                {
                                    System.out.println(storedPath.value);
                                    profiledata.logoPath_ = storedPath.value.replace(FileUploadPath.getFileUploadPath(request), AppConfigReader.instance( ).get( "TEMP_PATH" )+"/");
                                    //profiledata.logoPath_ = storedPath.value.replace(AppConfigReader.instance( ).get( "JSP_PATH" ),AppConfigReader.instance( ).get( "TEMP_PATH" ));
                                    System.out.println("profiledata.logoPath_==============>"+profiledata.logoPath_);
                                }


                        }
                        /* - */
                        //errLogger_.logMsg( "129"+profiledata.logoPath_);
                        if(  request.getAttribute( "logo" )!= null )
                        {
                                profiledata.logoImage_ = request.getAttribute( "logo" );
                        }
			//errLogger_.logMsg( "134"+profiledata.logoImage_);
			// Mail Address Converter
			int Result;
			MailingAddressData maildata=new MailingAddressData( );
			MailAddressDataConvertor convert=new MailAddressDataConvertor();
			
			Result=convert.convert(request, maildata );
			
			maildata.show( );
			//errLogger_.logMsg(Result );
			if(Result==0)
			{
				profiledata.mailingAddressArr_=new ArrayList<MailingAddressData>();
				profiledata.mailingAddressArr_.add( maildata );
			}
			//errLogger_.logMsg( "149"+profiledata.mailingAddressArr_);
			return 0;
		}
		catch(Exception ex)
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::CompanyProfileDataConvertor::Convert:: " + ex.getMessage( ) );			
			return -1;
		}
	}
}
