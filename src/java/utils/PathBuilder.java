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
package utils;

import java.io.File;
import javax.servlet.ServletConfig;



/**
 * File:  PathBuilder.java 
 *
 * Created on 24-Apr-2013 11:34:40 AM
 * 
 * Purpose: It is used to give the companies data's path
 */
public class PathBuilder
{
	String projectName = "";
        private static ErrorMaster errorMaster_ = null;




	/*
	 * 
	 * Method : PathBuilder -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public PathBuilder()
	{
		DBConfigReader dbConfigReader = DBConfigReader.instance( );
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		projectName = dbConfigReader.get( "TITLE" );
	}
	
	/*
	 * Method : getRelativePath()
	 * 
	 * Input  : local path,relative path
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the files local path to relative path
	 */
	public int getRelativePath( String localPath, StringHolder relativePath )
	{
		if( localPath == null )
			return -1;
		
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		try
                {
			String replaceStr = "";
			
			String currentStr =System.getProperty( "catalina.home" )+"webapps/";
			
			//currentStr = currentStr.replace( "/", "/" );
			
			relativePath.value =  currentStr;//localPath.replace( currentStr, replaceStr );
			
                }
		catch( NullPointerException e )
		{
        	
			errLogger.logMsg( "Exception::PathBuilder.getRelativePath() - " + e );
			
			return -1;
                }
                catch( Exception e )
                {

                        errLogger.logMsg( "Exception::PathBuilder.getRelativePath() - " + e );

                        return -1;
                }	
		return 0;
	}
	
	/*
	 * Method : getWebURL()
	 * 
	 * Input  : local path,web url
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the files local path to web url for that local path.
	 */
	public int getWebURL( String localPath, StringHolder webURL )
	{
		if( localPath == null )
			return -1;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		try
        {
			String replaceStr = "";
			
			String currentStr =	System.getProperty( "catalina.home" );//+"/webapps/";
			
			//errorMaster_.insert( "Path builder current str - " + currentStr );
			
			//errorMaster_.insert( "Path builder replace str - " + replaceStr );
			
			//currentStr = currentStr.replace( "/", "/" );
			
			webURL.value =  currentStr;//localPath.replace( currentStr, replaceStr );
                        System.out.println("webURL.value......................."+webURL.value);
			
			//errorMaster_.insert( "WebURL at path builder - " + webURL.value );
			
			String basePath = AppConfigReader.instance( ).get( "TEMP_PATH" );
			
			//errorMaster_.insert( "Base path - " + basePath );
			
			webURL.value = basePath +webURL.value;
                        System.out.println("webURL.value......................."+webURL.value);
			
        }
		catch( NullPointerException e )
		{
        	
			errLogger.logMsg( "Exception::PathBuilder.getWebURL() - " + e );
			
			return -1;
        }
        catch( Exception e )
        {
        	
			errLogger.logMsg( "Exception::PathBuilder.getWebURL() - " + e );
			
			return -1;
        }
		
				
		return 0;
	}
	
	
	/*
	 * Method : getWebURLFromRelativepath()
	 * 
	 * Input  : relative path,web url
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the files relative path to web url 
	 */
	public int getWebURLFromRelativepath( String relativePath, StringHolder webURL )
	{
		if( relativePath == null )
			return -1;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		try
        {
			 String path = null ;
             
             // errorMaster_.insert("Path = "+userFeedData.relativePath_);
              
              if(relativePath != null && !relativePath.equals( "null" ))
              	path = //AppConfigReader.instance( ).get( "BASE_SERVER_URL" )+"/"+relativePath;
                        relativePath;
              
              webURL.value = path;
			
        }
		catch( NullPointerException e )
		{
        	
			errLogger.logMsg( "Exception::PathBuilder.getWebURLFromRelativepath() - " + e );
			
			return -1;
        }
        catch( Exception e )
        {
        	
			errLogger.logMsg( "Exception::PathBuilder.getWebURLFromRelativepath() - " + e );
			
			return -1;
        }
		
				
		return 0;
	}
	
	
	/*
	 * Method : getCompanyLogoPath()
	 * 
	 * Input  : Company name,company phone number and path
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to build the path of company's Images folder
	 * using company name and company key. Path added to path parameter
	 * so it is copied to caller classes.
	 */
	public int getCompanyLogoPath( String companyname, String phoneNumber,StringHolder path )
	{
		int responseCode = 0;
		
		try
        {
			path.value = path.value //AppConfigReader.instance( ).get( "JSP_PATH" ) + "/"
                                        //System.getProperty( "catalina.home" ) + "/"
					        + AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" );
					
			String companyFolderName = companyname+"-"+phoneNumber;
			
			path.value = path.value+"/"+companyFolderName+"/Images";
                        
                        
			
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::PathBuilder.getCompanyLogoPath() - " + e );
			
			return -1;
        }
		
		return responseCode;
	}

	
	/*
	 * Method : getUserFeedImagesDirPath()
	 * 
	 * Input  : Company name,company phone number and path
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to build the path of company's Images folder
	 * using company name and company key. Path added to path parameter
	 * so it is copied to caller classes.
	 */
	public int getUserFeedImagesDirPath( String companyname, String phoneNumber,StringHolder path )
	{
		int responseCode = 0;
		
		try
        {
			
			path.value =path.value //System.getProperty( "catalina.home" ) + "/"
                                //AppConfigReader.instance( ).get( "TEMP_PATH" ) +"/"
					        + AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" );
					
			String companyFolderName = companyname+"-"+phoneNumber;
			
			path.value = path.value+"/"+companyFolderName+"/Images/UserFeed";
			
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::PathBuilder.getUserFeedImagesDirPath() - " + e );
			
			return -1;
        }
		
		return responseCode;
	}
	
	
	/*
	 * Method : getAdImagesDirPath()
	 * 
	 * Input  : Company name,company phone number and path
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to build the path of company's Ad Images folder
	 * using company name and company key. Path added to path parameter
	 * so it is copied to caller classes.
	 */
	public int getAdImagesDirPath( String companyname, String phoneNumber,StringHolder path )
	{
		int responseCode = 0;
		
		try
        {
			
			path.value += //System.getProperty( "catalina.home" ) + "/"
					         AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" );
					
			String companyFolderName = companyname+"-"+phoneNumber;
			
			path.value = path.value+"/"+companyFolderName+"/Images/Ad/";
			
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::PathBuilder.getAdImagesDirPath() - " + e );
			
			return -1;
        }
		
		return responseCode;
	}

	/*
	 * Method : getUserLogoDirPath()
	 * 
	 * 
	 * Input  : Company name,company phone number and path
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to build the path of company's user profile folder
	 * using company name and company key. Path added to path parameter
	 * so it is copied to caller classes.
	 */
	public int getUserLogoDirPath( String companyname, String phoneNumber, StringHolder path )
	{
		int responseCode = 0;
		
		try
        {
            //String basePath = AppConfigReader.instance( ).get( "TEMP_PATH" );
			path.value +=  "/"
					        + AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" );
					
			String companyFolderName = companyname+"-"+phoneNumber;
			
			path.value = /*basePath +*/path.value+"/"+companyFolderName+"/Images/UserProfile/";
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::PathBuilder.getUserLogoDirPath() - " + e );
			
			return -1;
        }
		
		return responseCode;
	}
	
	
	/*
	 * Method : getDeptFilesDirPath()
	 * 
	 * Input  : Company name,company phone number and path
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to build the path of company's Images folder
	 * using company name and company key. Path added to path parameter
	 * so it is copied to caller classes.
	 */
	public int getDeptFilesDirPath( String companyname, String phoneNumber,StringHolder path )
	{
		int responseCode = 0;
		
		try
        {
			
			path.value = System.getProperty( "catalina.home" ) + "/"
					        + AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" );
					
			String companyFolderName = companyname+"-"+phoneNumber;
			
			path.value = path.value+"/"+companyFolderName+"/Docs/DeptFiles/";
			
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::PathBuilder.getUserFeedImagesDirPath() - " + e );
			
			return -1;
        }
		
		return responseCode;
	}
	
	
	/*
	 * Method : getEDIFilesDirPath()
	 * 
	 * Input  :  transId and path
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to build the path of transaction EDI folder
	 * , transaction key and company key. Path added to path parameter
	 * so it is copied to caller classes.
	 */
	public int getEDIFilesDirPath( Long transId, StringHolder path )
	{
		int responseCode = 0;
		
		try
        {
			
			path.value = System.getProperty( "catalina.home" ) + "/"
					        + AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" );
			
			path.value = path.value+"/EDI/Trans_"+transId;
			
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::PathBuilder.getEDIFilesDirPath() - " + e );
			
			return -1;
        }
		
		return responseCode;
	}
	
	/*
	 * Method : getW9FormDirPath()
	 * 
	 * Input  : Company name,company phone number and path
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to build the path of w9 forms files
	 * using company name and company key. Path added to path parameter
	 * so it is copied to caller classes.
	 */
	public int getW9FormDirPath( String companyname, String phoneNumber,StringHolder path )
	{
		int responseCode = 0;
		
		try
        {
			
			path.value = System.getProperty( "catalina.home" ) + "/"
					        + AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" );
					
			String companyFolderName = companyname+"-"+phoneNumber;
			
			path.value = path.value+"/"+companyFolderName+"/Docs/W9Forms/";
			
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::PathBuilder.getW9FormDirPath() - " + e );
			
			return -1;
        }
		
		return responseCode;
	}
	
	
	
	
	/*
	 * Method : getCompanyDirPath()
	 * 
	 * Input  : Company name,company phone number and path
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to build the path of company's dir folder
	 * using company name and company key. Path added to path parameter
	 * so it is copied to caller classes.
	 */
	public int getCompanyDirPath( String companyname, String phoneNumber,StringHolder path )
	{
		int responseCode = 0;
		
		try
        {
			
			path.value = System.getProperty( "catalina.base" ) + "/"
					        + AppConfigReader.instance( ).get( "CROP_DATA_DIRECTORY" );
					
			errorMaster_.insert( "Catalina home - " + path.value );
			String companyFolderName = companyname+"-"+phoneNumber;
			
			path.value = path.value+"/"+companyFolderName+"/";
			
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::PathBuilder.getCompanyDirPath() - " + e );
			
			return -1;
        }
		
		return responseCode;
	}

}
