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
package core.trans;


import java.util.ArrayList;
import java.util.List;

import core.notification.NotificationCenterData;
import core.regn.UserProfileData;
import db.notification.NotificationTable;
import db.regn.UserProfileTable;

/**
 * File:  TransNotification.java 
 *
 * Created on 26-Sep-2013 3:52:24 PM
 */
public class TransNotification
{

	/*
	 * 
	 * Method : TransNotification -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransNotification()
	{
	}
	
	/*
	 * Method : add 
	 * 
	 * Input  : TransData object
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to add the transaction notification. It convert the 
	 * transdata to notificationdata then add the notification
	 */
	public int add( TransData transData )
	{
		int result = 0;
		
		NotificationCenterData notificationCenterData = new NotificationCenterData( );
		
		notificationCenterData.senderKey_ = transData.userFrom_;
		
		notificationCenterData.senderRegnKey_ = transData.from_;
		
		notificationCenterData.receiverRegnKey_ = transData.to_;
		
		notificationCenterData.notificationTypeId_ = ""+transData.transId_;
		
		notificationCenterData.notificationType_ = transData.transType_;
		
		// Setting sender name 
        UserProfileData userProfileData = new UserProfileData( );
        
        UserProfileTable userProfileTable = new UserProfileTable( );
        
        userProfileTable.getUserProfile( notificationCenterData.senderKey_, userProfileData );
        
       
        
        notificationCenterData.senderName_ = userProfileData.firstName_+" "+userProfileData.lastName_;
        
        if( transData.status_.contains( "Created" ))
		{
			notificationCenterData.notificationMessage_ = "Transaction "+transData.transType_+" is received from "+ notificationCenterData.senderName_;
		}
		else if( transData.status_.contains( "Inquire" ))
		{
			notificationCenterData.notificationMessage_ = "Transaction "+transData.transType_+" is inquired by "+ notificationCenterData.senderName_;
		}
		else if( transData.status_.contains( "Accepted" ) )
		{
			notificationCenterData.notificationMessage_ = "Transaction "+transData.transType_+" is accepted by "+ notificationCenterData.senderName_;
		}
		else if( transData.status_.contains( "Rejected" ) )
		{
			notificationCenterData.notificationMessage_ = "Transaction "+transData.transType_+" is rejected by "+ notificationCenterData.senderName_;
		}
        
        NotificationTable notificationTable = new NotificationTable( );
        
                
        if( transData.userTo_.email_  != null  )
        	
        {
        	
        	notificationCenterData.receiverKey_ = transData.userTo_;
        	
        	notificationTable.insert( notificationCenterData );

        }
        
        else
        {
        	
        	List<UserProfileData> profileLists = new ArrayList<UserProfileData>( );
        	
        	userProfileTable.getTransactionUsers( notificationCenterData.receiverRegnKey_, profileLists );
        	
        	for( UserProfileData userProfileData2 : profileLists )
            {
	            notificationCenterData.receiverKey_ = userProfileData2.userProfileKey_;
	            
	            notificationTable.insert( notificationCenterData );
            }
        	
		}
        
		
        userProfileTable = null;
		
		notificationTable = null;
		
		return result;
	}

}
