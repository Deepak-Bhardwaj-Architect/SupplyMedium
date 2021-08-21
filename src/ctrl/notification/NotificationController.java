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
package ctrl.notification;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import core.notification.NotificationCenter;
import core.notification.NotificationCenterData;


/**
 * File:  NotificationController.java 
 *
 * Created on Sep 24, 2013 1:05:50 PM
 */
public class NotificationController
{

	/*
	 * Method : NotificationController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public NotificationController()
	{
		
		
	}

	/*
	 * Method : processNM
	 * 
	 * Input  : HttpServletRequest object, list of NotificationCenterData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: This method is used to control the NotificationCenter. It do the following operation in NotificationCenter
	 * 
	 * 1. Create the new Notification
	 * 2. Delete the Notification
	 * 3. Get all the Notification for given user key
	 * 4.Get the Latest Notification
	 * 
	 */

public int processNM( HttpServletRequest request, List<NotificationCenterData>notifications )
{
	int result = 0;
	 // Converting request object to NotificationData object
	
	NotificationDataConverter converter = new NotificationDataConverter( );
	
	NotificationCenterData notificationCenterData = new NotificationCenterData( );
	
	result = converter.convert( request, notificationCenterData );
   
	converter = null;
	
	if( result != 0 )
		return 10301;  // Parser error.
	
	String requestType = request.getParameter( "RequestType" );
	
	if( requestType.equals( "AddNotification" ))
	{
		result = add( notificationCenterData );
	}
	else if( requestType.equals( "DeleteNotification" ))
	{
		result = remove( notificationCenterData.notificationId_ );
	}
	else if( requestType.equals( "FetchNotification" ))
	{
		result = get( notificationCenterData, notifications);
	}
	
	else if (requestType.equals( "FetchRecentNotification" )) 
	{
		result =getLatestNotification(notificationCenterData,notifications);
	}
	else
	{
		result = 10302;  // can't find appropriate request type
	}
	
	System.gc( );
	
	return result;
}







/*
 * Method : add
 * 
 * Input  : NotificationCenterData object
 * 
 * Return : int success/fail
 * 
 * Purpose: Used to add the new Notification for given user key. 
 */
public int add(NotificationCenterData notificationCenterData)
{
	int result = 0;
	
	NotificationCenter notificationCenter = new NotificationCenter( );
	
	result = notificationCenter.add( notificationCenterData );
	
	notificationCenter = null;
	
	return result;
}

/*
 * Method : delete
 * 
 * Input  : notificationid
 * 
 * Return : int success/fail
 * 
 * Purpose: It delete the Notification using given notificationid. Also it remove the 
 * Notification member relationship
 */

public int remove( long notificationId)
{
	int result = 0;
	
	NotificationCenter notificationCenter = new NotificationCenter( );
	
	result = notificationCenter.remove( notificationId );
	
	notificationCenter = null;
	
	return result;
}



/*
 * Method : get
 * 
 * Input  : NotificationCenterData object, list of NotificationCenterData object
 * 
 * Return : int success/fail
 * 
 * Purpose: It get all the Notification for given userprofilekey. And copied to notifications parameter.
 * So it is available for caller classes
 */

public int get( NotificationCenterData ncData, List<NotificationCenterData> notifications )
{

	int result = 0;
	
	NotificationCenter notificationCenter = new NotificationCenter( );
	
	result = notificationCenter.get( ncData, notifications );
	
	notificationCenter = null;
	
	return result;
}


/*
 * Method : getLatestNotification
 * 
 * Input  : NotificationCenterData, list of NotificationCenterData object
 * 
 * Return : int success/fail
 * 
 * Purpose: It get all the LatestNotification for given userprofilekey. And copied to notifications parameter.
 * So it is available for caller classes
 */

public int getLatestNotification(  NotificationCenterData ncData, List<NotificationCenterData> notifications )
{
	int result=0;
	
	NotificationCenter notificationCenter=new NotificationCenter( );
	
	result=notificationCenter.get( ncData, notifications );
	
	notificationCenter = null;
	
	return result;
}



}
