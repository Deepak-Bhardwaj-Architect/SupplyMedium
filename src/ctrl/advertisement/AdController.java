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
package ctrl.advertisement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import utils.DiskSpaceChecker;
import utils.ErrorLogger;
import utils.PathBuilder;
import utils.PictureStore;
import utils.StringHolder;

import core.advertisement.Advertisement;
import core.advertisement.AdData;
import core.login.SessionData;

/**
 * File:  AdController.java 
 *
 * Created on Oct 3, 2013 11:26:41 AM
 */
public class AdController
{
	/*
	 * Method : AdController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public AdController()
	{		
		
	}

	/*
	 * Method : processAD
	 * 
	 * Input  : HttpServletRequest object, list of AdvertisementData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: This method is used to control the Advertisement. It do the following operation in Advertisement
	 * 
	 * 1. Create the new Advertisement
	 * 2. Delete the Advertisement
	 * 3. Get all the Advertisement for given user key
	 * 
	 */

    public int processAD( HttpServletRequest request, List<AdData> adlists )
    {
    	int result = 0;
    	
	 // Converting request object to AdvertisementData object
	
	AdDataConverter converter = new AdDataConverter( );
	
	AdData adData = new AdData( );
	
	result = converter.convert( request, adData );
  
	converter = null;
	
	if( result != 0 )
		return 10701;  // Parser error.
	
	adData.show( );
	
	String requestType = request.getParameter( "RequestType" );
	
	if( requestType.equals( "AddAdvertisement" ))
	{
		Object fileObject = request.getAttribute( "AdImage" );
		
		if( fileObject != null )
		{
			FileItem file = (FileItem)fileObject;
			
			double imageSize = file.getSize( );
			
			imageSize = imageSize / (1024 * 1024);
			
			DiskSpaceChecker diskSpaceChecker = new DiskSpaceChecker( adData.regnKey_ );
			
			ErrorLogger.instance( ).logMsg( "SpaceRemaining - " + diskSpaceChecker.spaceRemaining_ + ";" + "ImageSize - " + imageSize );
			
			if( diskSpaceChecker.spaceRemaining_ < imageSize && diskSpaceChecker.spaceRemaining_ != -1 )
			{
				// This will not allow to post image as image size exceeds the disk quota for that company
				
				return 1803; //Disk quota for the company exceeds the limit
			}
		}
		
		result = add( adData );
		
		if( result == 10700 )
		{
			storeAdImage( request, adData );
		}
		
		
	}
	else if( requestType.equals( "DeleteAdvertisement" ))
	{
		result = remove( adData.adId_ );
	}
	else if( requestType.equals( "FetchAdvertisement" ))
	{
		result = get( adData, adlists);
	}
	
	
	else
	{
		result = 10702;  // can't find appropriate request type
	}
	
	return result;
    }
  
    /*
     * Method : add
     * 
     * Input  : AdvertisementData object
     * 
     * Return : int success/fail
     * 
     * Purpose: Used to add the new Advertisement  
     */	
    
    public int add( AdData adData )
    {
    	int result = 0;
    	
    	Advertisement advertisement = new Advertisement( );
    	
    	result = advertisement.add( adData );
    	
    	advertisement = null;
    	
    	return result;
    }
       
    /*
     * Method : delete
     * 
     * Input  : adId
     * 
     * Return : int success/fail
     * 
     * Purpose: It delete the Advertisement using given adId. Also it remove the 
     * Advertisement member relationship
     */
    
    public int remove( long adId )
    {
    	int result = 0;
    	
    	Advertisement advertisement = new Advertisement( );
    	
    	result = advertisement.remove( adId );
    	
    	advertisement = null;
    	
    	return result;
	    
    }
    
    /*
     * Method : get
     * 
     * Input  : AdvertisementData object, list of AdvertisementData object
     * 
     * Return : int success/fail
     * 
     * Purpose: It get all the Advertisement for given userprofilekey. And copied to adlists parameter.
     * So it is available for caller classes
     */
    
    public int get( AdData nData, List<AdData> adlists )
    {
	  
    	int result = 0;
    	
    	Advertisement advertisement = new Advertisement( );
    	
    	result = advertisement.get( nData, adlists );
    	
    	advertisement = null;
    	
    	return result;
    }
    
    

	/*
	 * Method : storeAdImage()
	 * 
	 * Input  : HTTPServletRequest and list of UserFeedData objects 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to store the user feed images to user feed directory.
	 * And also it update the user feed image url in user feed record.
	 */
	
	public int storeAdImage( HttpServletRequest request,AdData adData )
	{
		System.out.println( "StoreImage");
		
		try
        {
			Object fileObject = request.getAttribute( "AdImage" );
			
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
			
			// Getting the user Ad directory path
			int dirPathRes = pathBuilder.getAdImagesDirPath( companyname, phoneNumber, dirPath );
			
			if( dirPathRes == 0 ) // Ad image directory path fetched successfully
			{
				PictureStore pictureStoreObj = new PictureStore( );
				
				StringHolder storedPath = new StringHolder( );
				
				// Used to store ad image 
				int storeResult = pictureStoreObj.storeImage( fileObject, dirPath.value, Long.toString( adData.adId_ ),storedPath );
				
				if( storeResult == 0 )  // user ad image stored in folder successfully
				{
					int result = 0;
					
					StringHolder webURL = new StringHolder( );
					
					// Converting local path to web url
					
					result = pathBuilder.getWebURL( storedPath.value, webURL );
					
					if( result == 0 )
					{
						adData.adImagePath_ = webURL.value;
					}
					
					Advertisement advertisement = new Advertisement( );
					
					advertisement.update( adData );
				}
			}
        }
        catch( Exception ex )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::AdController.storeFeedImage() - " + ex );
			
			return -1;
        }
		
		
		return 0;
	}
   	
 }

