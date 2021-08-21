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
package ctrl.externalpage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import core.login.SessionData;

import utils.ErrorLogger;

/**
 * @FileName	: uploadcontroller.java
 *
 * @author 		: Anbazhagan
 *
 * @Date 		: May 5, 2013
 *
 * @Purpose 		: 
 *
 */
public class UploadController
{
	public int processRequest(HttpServletRequest requset)
	{
		// Get the Folder Structure from Here
		
		String filePath="";
		
		
		
		return 0;
	}
	
	public int uploadtheImage(HttpServletRequest request)
	{
		try
        {
			
			/*
			Object fileObject = request.getAttribute( "ImageUpload" );
			
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
			int dirPathRes = pathBuilder.getUserFeedImagesDirPath( companyname, phoneNumber, dirPath );
			
			if( dirPathRes == 0 ) // User feed directory path fetched successfully
			{
				PictureStore pictureStoreObj = new PictureStore( );
				
				StringHolder storedPath = new StringHolder( );
				
				// Used to store feed image 
				int storeResult = pictureStoreObj.storeImage( fileObject, dirPath.value, Integer.toString( userFeedData.userFeedId_ ),storedPath );
				
				if( storeResult == 0 )  // user feed image stored in folder successfully
				{
					userFeedData.feedImageUrl_ = storedPath.value;
					
					UserFeedManager feedManager = new UserFeedManager( );
					
					feedManager.update( userFeedData );
				}
			}*/
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
