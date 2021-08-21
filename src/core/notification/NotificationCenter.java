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

import java.util.List;

import core.regn.RegnData;
import core.regn.UserProfileData;



import db.notification.NotificationTable;
import db.regn.CompanyRegnTable;
import db.regn.UserProfileTable;



/**
 * File:  NotificationCenter.java 
 *
 * Created on Sep 24, 2013 4:06:32 PM
 */
public class NotificationCenter
{

	/*
	 * Method : NotificationCenter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public NotificationCenter( )
	{
		
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
	
	public int add( NotificationCenterData notificationCenterData )
    {
		NotificationTable notificationTable = new NotificationTable( );
		
		
		int result = notificationTable.insert( notificationCenterData );
		
		notificationTable = null;
		
		if( result == 0 )
			return 10300;  // Notification successfully created
		else
			return 10304;  // Notification creation failed 
		
    }

	/*
	 * Method : delete
	 * 
	 * Input  : notification id
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It delete the Notification using given notificationId. Also it remove the 
	 * Notification member relationship
	 */
	
	public int remove( long notificationId )
    {
		NotificationTable notificationTable = new NotificationTable( );
		
		int result = notificationTable.delete( notificationId );
		
		notificationTable = null;
		
		if( result == 0 )
		{
			// delete the Notification members from Notification table
			
			return 10310;
		}
		else 
		{
			return 10311;
		}
		
		
	    
    }
	
	/*
	 * Method : get
	 * 
	 * Input  : NotificationCenterData, list of NotificationCenterData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It get all the Notification for given userprofilekey. And copied to notifications parameter.
	 * So it is available for caller classes
	 */
	
	
	public int get( NotificationCenterData ncData,
	        List<NotificationCenterData> notifications )
	{

		NotificationTable notificationTable = new NotificationTable( );

		int result = notificationTable.find( ncData, notifications );
	
		notificationTable = null;
		
		setSenderInfo( notifications  );

		if( result == 0 )
			return 10320;
		else
			return 10321;

	}

	/*
	 * Method : getLatestNotification
	 * 
	 * Input : NotificationCenterData, list of NotificationCenterData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It get all the LatestNotification for given userprofilekey. And
	 * copied to notifications parameter. So it is available for caller classes
	 */

	public int getLatestNotification( NotificationCenterData ncData,
	        List<NotificationCenterData> notifications )
	{
		NotificationTable notificationTable = new NotificationTable( );

		int result = notificationTable.find( ncData, notifications );

		notificationTable = null;
		
		setSenderInfo( notifications  );

		if( result == 0 )
			return 10330;
		else
			return 10331;

	}
	
	/*
	 * Method : setSenderInfo
	 * 
	 * Input : list of NotificationCenterData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It set the sender name, sender profile picture path and sender company name details.
	 * 
	 */
	
	public int setSenderInfo( List<NotificationCenterData> notifications  )
    {
	    for( NotificationCenterData notificationCenterData : notifications )
        {
	    	// Setting sender name and profile picture details
	        UserProfileData userProfileData = new UserProfileData( );
	        
	        UserProfileTable userProfileTable = new UserProfileTable( );
	        
	        userProfileTable.getUserProfile( notificationCenterData.senderKey_, userProfileData );
	        
	        userProfileTable = null;
	        
	        notificationCenterData.senderName_ = userProfileData.firstName_+" "+userProfileData.lastName_;
	        
	        notificationCenterData.senderprofilePicPath_ = userProfileData.profilePicture_;
	        
	        // Setting sender company name details
	        
	        RegnData regnData = new RegnData( );
	        
	        CompanyRegnTable regnTable = new CompanyRegnTable( );
	        
	        regnTable.getCompany( notificationCenterData.senderRegnKey_, regnData );
	        
	        notificationCenterData.senderCompName_ = regnData.companyName_;
	        
        }
	    
	    return 0;
    }
}



