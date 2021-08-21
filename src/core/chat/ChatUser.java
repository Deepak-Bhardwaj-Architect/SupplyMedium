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
package core.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.LoginStatus;
import utils.UserConnStatus;

import core.myconn.MyConnectionData;
import core.regn.LoginData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.login.LoginStatusTable;
import db.myconn.MyConnectionTable;
import db.regn.UserProfileTable;

/**
 * File:  ChatUser.java 
 *
 * Created on 15-Oct-2013 3:28:13 PM
 */
public class ChatUser
{

	/*
	 * Method : ChatUser -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public ChatUser()
	{
		
	}
	
	/*
	 * Method : get
	 * 
	 * Input  : userkey and List of ChatUserStatusData
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch all the connection details including connection online status
	 *  of the user. And assign to chatUsers parameter it is copied to caller classes.
	 */
	public int get ( UserProfileKey userKey, List<ChatUserStatusData> chatUsersStatusList )
	{
		int result = 0;
		
		
		// Used to fetch all the connections of the user
		List< MyConnectionData > connections = new ArrayList< MyConnectionData >( );
		
		MyConnectionTable myConnTable = new MyConnectionTable( );
		
		result = myConnTable.filter( userKey, UserConnStatus.status.ACCEPTED.getValue( ), connections );
			
		myConnTable = null;
		
		if( result != 0 )
		{
			connections = null;
			
			return 10603; // can't fetch connections of the user
		}
			
		
		// Used to fetch all the user profile details 
		Map< UserProfileKey, UserProfileData > profileMap = new HashMap< UserProfileKey, UserProfileData >( );

		UserProfileTable userProfileTable = new UserProfileTable( );

		result = userProfileTable.get( profileMap );

		userProfileTable = null;

		if( result != 0 )
		{
			profileMap = null;

			return 10604; // can't fetch users profile details
		}
				
		
		// Used to fetch all the user login status ( connected or not connected)
		Map< UserProfileKey, LoginData > loginDataMap = new HashMap< UserProfileKey, LoginData >( );
		
		LoginStatusTable loginStatusTable = new LoginStatusTable( );
		
		result = loginStatusTable.get( loginDataMap );
		
		loginStatusTable = null;
		
		if( result != 0 )
		{
			loginDataMap = null;
			
			return 10604; // can't fetch login status of the connections
		}
			
		
		for( MyConnectionData myConnectionData : connections )
        {
	        ChatUserStatusData chatUserStatusData = new ChatUserStatusData( );
	        
	        chatUserStatusData.userKey_ = myConnectionData.toUserKey_;
	       
	        // Setting user profile details
	        
	        UserProfileData userProfileData = profileMap.get( myConnectionData.toUserKey_ );
	        
	        if( userProfileData == null )
	        	continue;
	        
	        chatUserStatusData.name_ = userProfileData.firstName_ +" "+userProfileData.lastName_;
	        
	        chatUserStatusData.profilePicturePath_ = userProfileData.profilePicture_;
	        
	        chatUserStatusData.email_ = userProfileData.emailId_;
	        
	        
	        // Setting login status for the connections
	        LoginData loginData = loginDataMap.get( myConnectionData.toUserKey_ );
	        
	        if( loginData == null )  // Userkey not in login status table
	        {
	        	chatUserStatusData.status_ = 0;
	        }
	        else
	        {
				if( loginData.loginStatus_.equals( LoginStatus.loginStatus.CONNECTED.getValue( ) ) )
                {
					chatUserStatusData.status_ = 1;
                }
				else
				{
					chatUserStatusData.status_ = 0;
				}
			}
	        
	        chatUsersStatusList.add( chatUserStatusData );
	        
	        chatUserStatusData = null;
        }
		
		connections  = null;
		
		loginDataMap = null;
		
		
		// Add user list to chatusercache
		ChatUserCache chatUserCache = ChatUserCache.instance( );
		
		chatUserCache.put( userKey, chatUsersStatusList );
		
		return 10600;
		
	}
	
	/*
	 * Method : get
	 * 
	 * Input  : userkey and ChatUserStatusData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch user chat information . And assign to userStatusData 
	 * parameter it is copied to caller classes.
	 */
	
	public int get ( UserProfileKey userKey, ChatUserStatusData userStatusData )
	{
		
		int result = 0;
		
		return result;
		
	}

}
