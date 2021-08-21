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
package core.privatemsg;

import java.util.List;

import utils.NotificationType;
import utils.PathBuilder;
import utils.StringHolder;


import core.notification.NotificationCenterData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.notification.NotificationTable;
import db.privatemsg.PrivateMessageTable;
import db.regn.UserProfileTable;

/**
 * File:  PrivateMessage.java 
 *
 * Created on Sep 19, 2013 5:51:17 PM
 */
public class PrivateMessage
{

	/*
	 * Method : PrivateMessage -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public PrivateMessage()
	{
		
	}

	
	/*
	 * Method : add
	 * 
	 * Input  : PrivateMessageData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: Used to add the new PrivateMessage for given user key. 
	 */
	
	public int add( PrivateMessageData privateMessageData)
    {
		PrivateMessageTable privateMessageTable = new PrivateMessageTable( );
		
		int result = privateMessageTable.insert( privateMessageData );
		
		privateMessageTable = null;
		
		addNotification( privateMessageData );
		
		if( result == 0 )
			return 10200;  // PrivateMessage successfully created
		else
			return 10203;  // PrivateMessage creation failed 
    }
	
	
	/*
	 * Method : delete
	 * 
	 * Input  : privateMessageData
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It delete thePrivateMessage using given privateMessageData. Also it remove the 
	 * PrivateMessage member relationship
	 */
	
	public int remove( PrivateMessageData privateMessageData )
    {

		int result = 0;
		
		PrivateMessageTable privateMessageTable = new PrivateMessageTable( );
		
		result = privateMessageTable.updateFromDeleteFlag( privateMessageData );
		
		result = privateMessageTable.updateToDeleteFlag( privateMessageData );
		
		result = privateMessageTable.delete( privateMessageData );
		
		privateMessageTable = null;
		
		if( result == 0 )
		{
			
			return 10210;
		}
		
		return 0;
    }
	
	
	/*
	 * Method : get
	 * 
	 * Input  : PrivateMessageData object, list of PrivateMessageData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It get all the PrivateMessage for given userprofilekey. And copied to messages parameter.
	 * So it is available for caller classes
	 */
	
	public int get( PrivateMessageData pmData, List<PrivateMessageData> messages )
	{

		PrivateMessageTable privateMessageTable = new PrivateMessageTable( );

		int result = privateMessageTable.find( pmData, messages );
		
		
		
		privateMessageTable = null;
		
		
		// Getting from and to user profile picture path
		UserProfileTable userProfileTable = new UserProfileTable( );
		
		UserProfileData fromUserProfile = new UserProfileData( );
		
		UserProfileData toUserProfile   = new UserProfileData( );
		
		userProfileTable.find( pmData.fromUserKey_, fromUserProfile );
		
		userProfileTable.find( pmData.toUserKey_, toUserProfile );
		
		userProfileTable = null;
		
		
		// Convert local path to WEB URL of the profile picture image
		PathBuilder pathBuilder = new PathBuilder( );
		
		StringHolder fromWebURL = new StringHolder( );
		
		StringHolder toWebURL = new StringHolder( );
		fromWebURL.value=fromUserProfile.profilePicture_;
                toWebURL.value=toUserProfile.profilePicture_;        
		//pathBuilder.getWebURL( fromUserProfile.profilePicture_, fromWebURL );
		
		//pathBuilder.getWebURL( toUserProfile.profilePicture_, toWebURL );
		
		pathBuilder = null;
		
		
		
		for( PrivateMessageData privateMessageData : messages )
        {
			if( privateMessageData.fromUserKey_.equals( pmData.fromUserKey_ ))
			{
				privateMessageData.fromUserProfilePicPath_ = fromWebURL.value;
				
				privateMessageData.toUserProfilePicPath_   = toWebURL.value;
			}
			else
			{
				privateMessageData.fromUserProfilePicPath_ = toWebURL.value;
				
				privateMessageData.toUserProfilePicPath_   = fromWebURL.value;
			}
				
	        
	       
        }
		
		if( result == 0 )
			return 10220;
		else 
			return 10221;
		
	}
		
	
	/*
	 * Method : addNotification
	 * 
	 * Input  : PrivateMessageData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: Used to add the new PrivateMessage notification in notification table.
	 * 
	 */
	
	public int addNotification( PrivateMessageData privateMessageData)
    {
		int result = 0;
		
		NotificationCenterData notificationCenterData = new NotificationCenterData( );
		
		notificationCenterData.senderKey_ 			= privateMessageData.fromUserKey_;
		
		notificationCenterData.receiverKey_ 		= privateMessageData.toUserKey_;
	
		notificationCenterData.notificationTypeId_ 	= ""+privateMessageData.messageId_;
		
		notificationCenterData.notificationType_ 	= NotificationType.type.PRIVATEMESSAGE.getValue( );
		
		// Setting sender name 
        UserProfileData userProfileData = new UserProfileData( );
        
        UserProfileTable userProfileTable = new UserProfileTable( );
        
        userProfileTable.getUserProfile( notificationCenterData.senderKey_, userProfileData );
        
        userProfileTable = null;
        
        notificationCenterData.senderName_ = userProfileData.firstName_+" "+userProfileData.lastName_;
		
		
		notificationCenterData.notificationMessage_ = "Private Message is received from "+notificationCenterData.senderName_;
		
		notificationCenterData.senderRegnKey_ = userProfileData.companyRegnKey_;
		
		NotificationTable notificationTable = new NotificationTable( );
		
		notificationTable.insert( notificationCenterData );
		
		notificationTable = null;
		
		return result;
    }
}
