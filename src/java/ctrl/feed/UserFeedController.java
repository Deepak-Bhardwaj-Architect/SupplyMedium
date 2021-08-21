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
package ctrl.feed;


import core.common.FileUploadPath;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import utils.DiskSpaceChecker;
import utils.ErrorLogger;
import utils.PathBuilder;
import utils.PictureStore;
import utils.StringHolder;

import core.feed.UserFeedData;
import core.feed.UserFeedManager;
import core.login.SessionData;
import utils.AppConfigReader;
import utils.ErrorMaster;


/**
 * File:  UserFeedController.java 
 *
 * Created on 22-Apr-2013 11:30:24 AM
 */
public class UserFeedController
{

	/*
	 * Method : UserFeedController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public UserFeedController()
	{
		
	}
	
	
	/*
	 * Method : processRequest()
	 * 
	 * Input  : HTTPServletRequest and list of UserFeedData objects 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to process the user feed request. It get the request from servlet
	 * and do the appropriate action according to RequestType, which is available in request.
	 * With the use UserFeedDataCoverter it convert request object to UserFeeddata object.
	 */
	public int processRequest( HttpServletRequest request, List<UserFeedData> userFeedList )
	{
		int result = 0;
                ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		UserFeedDataConverter converter = new UserFeedDataConverter( );
		
		UserFeedData userFeedData = new UserFeedData( );
		
		// Parse the request object to UserFeedData object
		int parseRes = converter.convert( request, userFeedData );
		
		converter = null;
		
		//userFeedData.show( );
		
		if( parseRes != 0 )
		{
			String errMsg = "Error::UserFeedController.processRequest() - Can't parse the " +
					"request ";
			
			errorLogger.logMsg(errMsg);
			
			return 1801;  // Failed request because of invalid data
		}
		
		String requestType = request.getParameter( "RequestType" );
		
		UserFeedManager userFeedManager = new UserFeedManager( );
		
		if( requestType.equals( "NewFeed" ))  // Process the New feed request
		{
			Object fileObject = request.getAttribute( "FeedImage" );
			
			if( fileObject != null )
			{
    			FileItem file = (FileItem)fileObject;
    			
    			double imageSize = file.getSize( );
    			
    			imageSize = imageSize / (1024 * 1024);
    			
    			DiskSpaceChecker diskSpaceChecker = new DiskSpaceChecker( userFeedData.regnKey_ );
    			
    			ErrorLogger.instance( ).logMsg( "SpaceRemaining - " + diskSpaceChecker.spaceRemaining_ + ";" + "ImageSize - " + imageSize );
    			
    			if( diskSpaceChecker.spaceRemaining_ < imageSize && diskSpaceChecker.spaceRemaining_ != -1 )
    			{
    				//This will not allow to post image as image size exceeds the disk quota for that company
    				
    				return 1803; //Disk quota for the company exceeds the limit
    			}
			}
			
			result = userFeedManager.add( userFeedData );
			//System.out.println("result--------------------------->"+result);
			if( result == 1800 )
			{
				storeFeedImage( request, userFeedData );
			}
			
			userFeedList.add( userFeedData );
		}
		else if( requestType.equals( "DeleteFeed" )) // Process the delete feed request
		{
			result = userFeedManager.remove( userFeedData.userFeedId_, userFeedData.userKey_ );
		}
		else if( requestType.equals( "FetchFeed" ) ) // Process the fetch feed request
		{
			errorMaster_.insert( "----------------------------------------------------------------------------------------" );
			
			
			int from = Integer.parseInt( request.getParameter( "StartsFrom" ) );
			
			int count = Integer.parseInt( request.getParameter( "Count" ) );
			
			int lastFeedId = Integer.parseInt( request.getParameter( "LastFeedId" ) );
			
			errorMaster_.insert( "Fetch user feeds from "+from+" to "+from+count+" lastFeedId="+lastFeedId );
					
			result = userFeedManager.find( userFeedData.userKey_, userFeedList, from, count, lastFeedId );
			
			errorMaster_.insert( "----------------------------------------------------------------------------------------" );
			
			
		}
		else if( requestType.equals( "LatestFetchFeed" ) ) // Process the latest fetch feed request
		{
			errorMaster_.insert( "----------------------------------------------------------------------------------------" );
			
			int latestFeedId = Integer.parseInt( request.getParameter( "LatestFeedId" ) );
					
			result = userFeedManager.find( userFeedData.userKey_, userFeedList, latestFeedId );
			
			errorMaster_.insert( "----------------------------------------------------------------------------------------" );
			
			
		}
		
		System.gc( );
		
		return result;
	}
	
	
	/*
	 * Method : storeFeedImage()
	 * 
	 * Input  : HTTPServletRequest and list of UserFeedData objects 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to store the user feed images to user feed directory.
	 * And also it update the user feed image url in user feed record.
	 */
	
	public int storeFeedImage( HttpServletRequest request,UserFeedData userFeedData )
	{
		try
        {
			Object fileObject = request.getAttribute( "FeedImage" );
			//System.out.println("fileObject==========================>"+fileObject);
			if( fileObject == null )
			{
				return -1;
			}
			
			// Getting company name and company phone number from session
			HttpSession session = request.getSession( );
			
			SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
			
			String companyname = sessionData.companyName_;
			
			String phoneNumber = sessionData.companyRegnKeyStr_;
			
			StringHolder dirPath = new StringHolder( );
			
			PathBuilder pathBuilder = new PathBuilder( );
			
			// Getting the user feed image directory path
                        dirPath.value=FileUploadPath.getFileUploadPath(request);
			int dirPathRes = pathBuilder.getUserFeedImagesDirPath( companyname, phoneNumber, dirPath );
			
			if( dirPathRes == 0 ) // User feed directory path fetched successfully
			{
				PictureStore pictureStoreObj = new PictureStore( );
				
				StringHolder storedPath = new StringHolder( );
				
				// Used to store feed image 
				int storeResult = pictureStoreObj.storeImage( fileObject, dirPath.value, Integer.toString( userFeedData.userFeedId_ ),storedPath );
				//System.out.println("storeResult==========================>"+storeResult);
				if( storeResult == 0 )  // user feed image stored in folder successfully
				{
                                        
					userFeedData.localPath_ = storedPath.value.replace(FileUploadPath.getFileUploadPath(request), AppConfigReader.instance( ).get( "TEMP_PATH" )+"/");
					
					StringHolder relativePath = new StringHolder( );
					
					StringHolder webURL = new StringHolder( );
					
					// Converting local path to relative path
					
					int result = pathBuilder.getRelativePath( userFeedData.localPath_, relativePath );
					
					if( result == 0 )
					{
						userFeedData.relativePath_ = relativePath.value;
					}
					
					
					// Converting local path to web url
					
					result = pathBuilder.getWebURL( userFeedData.localPath_, webURL );
					
					if( result == 0 )
					{
						userFeedData.webURL_ = userFeedData.localPath_;
                                                userFeedData.localPath_=userFeedData.webURL_;
                                                userFeedData.relativePath_=userFeedData.webURL_;
                                                userFeedData.webURL_=userFeedData.webURL_.replace(AppConfigReader.instance( ).get( "JSP_PATH" ),AppConfigReader.instance( ).get( "TEMP_PATH" ));
                                                userFeedData.localPath_=userFeedData.webURL_;
                                                userFeedData.relativePath_=userFeedData.webURL_;
					}
					
					UserFeedManager feedManager = new UserFeedManager( );
					
					feedManager.update( userFeedData );
				}
			}
        }
        catch( Exception ex )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::UserFeedController.storeFeedImage() - " + ex );
			
			return -1;
        }
		
		
		return 0;
	}
}
