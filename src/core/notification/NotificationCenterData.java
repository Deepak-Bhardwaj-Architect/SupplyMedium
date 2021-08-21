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
package core.notification;

import java.sql.Timestamp;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;

/**
 * File:  NotificationCenterData.java 
 *
 * Created on Sep 24, 2013 4:13:36 PM
 */
public class NotificationCenterData
{
	// Id of the notification. This is the auto incremented id
	public long notificationId_;
	
	//receiver key of the notification
	public UserProfileKey receiverKey_;
	
	//receiver Regnkey of the notification
	public CompanyRegnKey receiverRegnKey_;
	
	//senderkey of the notification
	public UserProfileKey senderKey_;	
	
	//sender Regnkey of the notification
	public CompanyRegnKey senderRegnKey_;
	
	//sender company Name of the notification
	public String senderCompName_;
	
	//sender company Name of the notification
	public String senderprofilePicPath_;
	
	//sender Name of the notification
	public String senderName_;
	
	//notification Type of the notification
	public String notificationType_;
	
	//notification TypeId of the notification
	public String notificationTypeId_;
	
	//notification Message of the notification
	public String notificationMessage_;
	
	//notification createdTime
	public Timestamp createdTimestamp_;
		
	

	/*
	 * Method : NotificationCenterData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public NotificationCenterData()
	{
		notificationId_			 = -1;
		receiverKey_			= new UserProfileKey( );
		receiverRegnKey_	= new CompanyRegnKey( );
		senderKey_				= new UserProfileKey( );
		senderRegnKey_		= new CompanyRegnKey( );
		
	}

	/*
	 * Method : show( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "notificationId 		=" + notificationId_ );
		
		System.out.println( "receiverKey     		=" + receiverKey_ );
		
		System.out.println( "receiverRegnKey 		=" + receiverRegnKey_ );
		
		System.out.println( "senderKey		    	=" + senderKey_ );
		
		System.out.println( "senderRegnKey			=" + senderRegnKey_ );
		
		System.out.println( "senderName				=" + senderName_ );
		
		System.out.println( "senderCompName			=" + senderCompName_ );
		
		System.out.println( "senderprofilePicPath_	=" + senderprofilePicPath_ );
		
		System.out.println( "notificationType		=" + notificationType_ );
		
		System.out.println( "notificationTypeId 	=" + notificationTypeId_ );
	
		System.out.println( "notificationMessage	=" + notificationMessage_ );
		
		System.out.println( "CreatedTimestamp 		=" + createdTimestamp_ );
	}
	
	/*
	 * Method : clear( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */
	
	public void clear( )
	{
		notificationId_ 		= -1;
		
		receiverKey_ 			= null;
		
		receiverRegnKey_ 		= null;
		
		senderKey_ 				= null;
		
		senderRegnKey_ 			= null;
		
		senderName_ 			= null;
		
		senderCompName_ 		= null;
		
		senderprofilePicPath_ 	= null;
		
		notificationType_ 		= null;
		
		notificationTypeId_ 	= null;
		
		notificationMessage_	= null;
		
		createdTimestamp_ 		= null;
	}

}
