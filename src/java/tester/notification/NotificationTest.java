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
package tester.notification;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import core.notification.NotificationCenterData;

import core.regn.UserProfileKey;
import ctrl.notification.NotificationController;
import utils.ErrorMaster;

/**
 * File:  NotificationTest.java 
 *
 * Created on Sep 25, 2013 5:10:52 PM
 */
public class NotificationTest
{
	
private static ErrorMaster errorMaster_ = null;




	/*
	 * Method : NotificationTest -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public NotificationTest()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	public void execute()
	{
		addTest( );
		//deleteTest( );
		fetchTest( );
	}
	
	
	/*
	 * Method : addTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to add the new Notification. 
	 */
	
	public void addTest()
	{
		NotificationController controller = new NotificationController( );
		
		NotificationCenterData notificationCenterData = new NotificationCenterData( );
		
		notificationCenterData.receiverKey_.email_ = "";
		
		notificationCenterData.receiverRegnKey_.companyPhoneNo_ = "";
		
		notificationCenterData.senderKey_.email_ = "";
		
		notificationCenterData.senderRegnKey_.companyPhoneNo_ = "";
		
		notificationCenterData.senderName_ = "";
		
		notificationCenterData.notificationType_ = "Privatemessage";
		
		notificationCenterData.notificationTypeId_ = "2";
		
		notificationCenterData.notificationMessage_ = "you have one message from privatemessage";
		
		int result = controller.add( notificationCenterData );
		
		errorMaster_.insert( "Notification result id="+result );
		
		if( result == 10300  )
		{
			errorMaster_.insert( "Notification add test successfully completed" );
			
			errorMaster_.insert( "Notification added successfully" );
		}
		
		else
		{
			errorMaster_.insert( "Notification add test failed" );
		}
			
	}
	
	/*
	 * Method : deleteTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to delete the Notification. 
	 */
	public void deleteTest()
	{
		NotificationController controller = new NotificationController( );
		
		long notificationId = 4;
		
		int result = controller.remove( notificationId );
		
		if( result == 10310  )
		{
			errorMaster_.insert( "Notification delete test successfully completed" );
			
		}
		
		else
		{
			errorMaster_.insert( "Notification delete test failed" );
		}
			
	}
	
	/*
	 * Method : fetchTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to fetch the notifications for given user key. 
	 */
	public void fetchTest()
	{
		NotificationController controller = new NotificationController( );
		
		List<NotificationCenterData> notifications = new ArrayList<NotificationCenterData>( );
	
		
		UserProfileKey receiverKey = new UserProfileKey( );
		
		receiverKey.email_ = "";
		
		Timestamp createdTimestamp =new Timestamp(0);

		String timeStampString="2013-09-25 16:29:53";
		Timestamp ts = Timestamp.valueOf(timeStampString);
		createdTimestamp=ts;
		
		NotificationCenterData nData = new NotificationCenterData( );
		
		
		
		nData.receiverKey_ 	=  receiverKey;
		nData.createdTimestamp_=ts;
		
		int result = controller.get( nData, notifications );
		
		if( result == 10320  )
		{
			errorMaster_.insert( "Notification fetch test successfully completed" );
			
			for( NotificationCenterData notificationCenterData : notifications )
            {
				notificationCenterData.show( );
            }
			
		}
		
		else
		{
			errorMaster_.insert( "Notification fetch test failed" );
		}
			
	}
	

}
