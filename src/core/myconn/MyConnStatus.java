
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
package core.myconn;

import core.notification.NotificationCenterData;
import utils.NotificationType;
import utils.UserConnStatus;
import db.myconn.MyConnectionTable;
import db.notification.NotificationTable;


/**
 * File:  MyConnManager.java 
 *
 * Created on 13-Aug-2013 1:42:15 PM
 */

public class MyConnStatus
{

	/*
	 * Method : MyConnManager -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnStatus()
	{
	}
	
	
	/*
	 * Method : addConnection
	 * 
	 * Input  : MyConnectionData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to add new user connection with Request Sent status.
	 * User send the request connection to other user at the time this method called.
	 * It create the new connection between both the user and put the status as Request Sent.
	 * Once the other user accepted the request the status change as Accepted.
	 */
	
	public int addConnection( MyConnectionData myConnData )
	{
		int result = 0;
		
		MyConnectionTable myConnTbl = new MyConnectionTable( );
		
		result = myConnTbl.isRelationExist( myConnData );
		
		if( result == 1 ) // Connection relationship already exist
		{
			return 10004;
		}
		
		myConnData.status_ = UserConnStatus.status.REQUEST_SENT.getValue( );
		
		result = myConnTbl.insert( myConnData );
		
		myConnTbl = null;
		
		addNotification( myConnData,"New Connection" );
		
		if( result == 0 )
			return 10000;  // Inserted successfully
		else 
			return 10003;  // Insertion failed

	}
	
	
	/*
	 * Method : acceptConnection
	 * 
	 * Input  : MyConnectionData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is called when user accept the connection request.
	 * This method change the user connection status as 'Accepted'. 
	 */
	
	public int acceptConnection( MyConnectionData myConnData )
	{
		int result = 0;
		
		MyConnectionTable myConnTbl = new MyConnectionTable( );
		
		myConnData.status_ = UserConnStatus.status.ACCEPTED.getValue( );
		
		result = myConnTbl.updateStatus( myConnData.fromUserKey_, myConnData.toUserKey_, myConnData.status_ );
		
		myConnTbl = null;
		
		addNotification( myConnData,"Accept Connection" );
		
		if( result == 0 )
			return 10010;  // Status successfully updated
		else 
			return 10011;  // Status update failed
		
	}
	
	
	/*
	 * Method : rejectConnection
	 * 
	 * Input  : MyConnectionData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is called when user reject the connection request.
	 * It remove the user connection between them.
	 */
	
	public int rejectConnection( MyConnectionData myConnData )
	{
		int result = 0;
		
		MyConnectionTable myConnTbl = new MyConnectionTable( );
		
		result = myConnTbl.delete( myConnData.fromUserKey_, myConnData.toUserKey_ );
		
		myConnTbl = null;
		
		addNotification( myConnData,"Reject Connection" );
		
		if( result == 0 )
			return 10020;  // Relation successfully removed 
		else 
			return 10021;  // Relation failed to remove
	}
	
	/*
	 * Method : addNotification
	 * 
	 * Input  : PrivateMessageData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: Used to add the new connection request notification in notification table.
	 */
	
	public int addNotification( MyConnectionData myConnData, String requestType )
    {
		int result = 0;
		
		NotificationCenterData notificationCenterData = new NotificationCenterData( );
		
		if( requestType.equals( "New Connection" ) )
		{
			notificationCenterData.senderKey_ 			= myConnData.fromUserKey_;
			
			notificationCenterData.senderName_ 			= myConnData.fromUserName_;
			
			notificationCenterData.senderRegnKey_ 		= myConnData.fromRegnKey_;
			
			
			notificationCenterData.receiverKey_ 		= myConnData.toUserKey_;
			
			notificationCenterData.receiverRegnKey_		= myConnData.toRegnKey_;
			
			notificationCenterData.notificationMessage_ = myConnData.fromUserName_+" has requested you to join his connection.";
			
			
			notificationCenterData.notificationType_ 	= NotificationType.type.NEWCONNREQ.getValue( );
			
		}
		else if(requestType.equals( "Accept Connection" ))
		{
			notificationCenterData.senderKey_ 			= myConnData.toUserKey_;
			
			notificationCenterData.senderName_ 			= myConnData.toUserName_;
			
			notificationCenterData.senderRegnKey_ 		= myConnData.toRegnKey_;
			
			
			notificationCenterData.receiverKey_ 		= myConnData.fromUserKey_;
			
			notificationCenterData.receiverRegnKey_		= myConnData.fromRegnKey_;
			
			notificationCenterData.notificationMessage_ = myConnData.toUserName_+" accepted your connection request.";
			
			notificationCenterData.notificationType_ 	= NotificationType.type.NEWCONNRES.getValue( );
			
		}
		else 
		{
			notificationCenterData.senderKey_ 			= myConnData.toUserKey_;
			
			notificationCenterData.senderName_ 			= myConnData.toUserName_;
			
			notificationCenterData.senderRegnKey_ 		= myConnData.toRegnKey_;
			
			
			notificationCenterData.receiverKey_ 		= myConnData.fromUserKey_;
			
			notificationCenterData.receiverRegnKey_		= myConnData.fromRegnKey_;
			
			notificationCenterData.notificationMessage_ = myConnData.toUserName_+" rejected your connection request.";
			
			notificationCenterData.notificationType_ 	= NotificationType.type.NEWCONNRES.getValue( );
			
			
		}
		
		NotificationTable notificationTable = new NotificationTable( );
		
		notificationTable.insert( notificationCenterData );
		
		notificationTable = null;
		
		return result;
    }
	
	
	
	public int deleteConnection(MyConnectionData myConnData)
	{
		int result = 0;
		
		MyConnectionTable myConnTbl = new MyConnectionTable( );
			
		result = myConnTbl.deleteConnection( myConnData );
		
		myConnTbl = null;
		
		if( result == 0 )
		{
			
			return 10090;
		}
		
		return 10091;
		
		
		
		
	}
	
	
}
