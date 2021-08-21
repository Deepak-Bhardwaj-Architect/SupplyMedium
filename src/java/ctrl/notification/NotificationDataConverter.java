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


import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.notification.NotificationCenterData;

/**
 * File:  NotificationDataConverter.java 
 *
 * Created on Sep 24, 2013 5:36:43 PM
 */
public class NotificationDataConverter
{
	
	ErrorLogger errorLogger_;
	
	/*
	 * Method : NotificationDataConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public NotificationDataConverter()
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : convert 
	 * 
	 * Input  : HttpServletRequest object, NotificationCenterData object
	 * 
	 * Return : int - success/fail
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to NotificationCenterData object.
	 * And copied to notificationCenterData parameter so it available in caller classes.
	 * 
	 */

    public int convert( HttpServletRequest request,
            NotificationCenterData notificationCenterData )
    {
	   
    	int result = 0;
		
		try
        {
			
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			/* Notification details */
	
			if( reqMap.containsKey( "NotificationId" ) )
			{
				notificationCenterData.notificationId_ = Integer.parseInt( request.getParameter( "NotificationId" ) );
			}
			
			if( reqMap.containsKey( "ReceiverKey" ) )
			{
				notificationCenterData.receiverKey_.email_ = request.getParameter( "ReceiverKey" );
			}
			
			
			if( reqMap.containsKey( "ReceiverRegnKey" ) )
			{
				notificationCenterData.receiverRegnKey_ .companyPhoneNo_= request.getParameter( "ReceiverRegnKey" );
			}
			
			if( reqMap.containsKey( "SenderKey" ) )
			{
				notificationCenterData.senderKey_.email_ = request.getParameter( "SenderKey" );
			}
			
			if( reqMap.containsKey( "senderRegnKey" ) )
			{
				notificationCenterData.senderRegnKey_.companyPhoneNo_ = request.getParameter( "senderRegnKey" );
			}
			
			if( reqMap.containsKey( "SenderName" ) )
			{
				notificationCenterData.senderName_ = request.getParameter( "SenderName" );
			}
			
			if( reqMap.containsKey( "NotificationType" ) )
			{
				notificationCenterData.notificationType_ = request.getParameter( "NotificationType" );
			}
			
			if( reqMap.containsKey( "NotificationTypeId" ) )
			{
				notificationCenterData.notificationTypeId_ = request.getParameter( "NotificationTypeId" );
			}
			
			if( reqMap.containsKey( "NotificationMessage" ) )
			{
				notificationCenterData.notificationMessage_ = request.getParameter( "NotificationMessage" );
			}
			if( reqMap.containsKey( "Timestamp" ) )
			{
				if( ! request.getParameter( "Timestamp" ).equals( "" ))
				{
					SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy ',' HH:mm:ss");
					java.util.Date date = sdf.parse(request.getParameter( "Timestamp" ));
					java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
					
					notificationCenterData.createdTimestamp_ = timestamp;
				}
				
			}
			

			
			
        }
		catch( Exception e )
	    {
			errorLogger_.logMsg( "Exception::NotificationDataConverter.convert() - " + e );
	        	
	        return -1;
	    }
		
		return result;
    }
}
