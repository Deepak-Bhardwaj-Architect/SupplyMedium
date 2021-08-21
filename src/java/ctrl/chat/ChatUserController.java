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
package ctrl.chat;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import core.chat.ChatUser;
import core.chat.ChatUserStatusData;
import core.regn.UserProfileKey;


/**
 * File:  ChatUserController.java 
 *
 * Created on 15-Oct-2013 1:27:13 PM
 */
public class ChatUserController
{

	/*
	 * Method : ChatUserController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public ChatUserController()
	{
	}
	
	/*
	 * Method : processUser
	 * 
	 * Input  : HttpServletRequest object, List of ChatUserStatusData object
	 * 
	 * Return : int
	 * 
	 * Purpose: Used to parse the request and do the operation depending to request type
	 * 
	 */
	public int processUser( HttpServletRequest request, List<ChatUserStatusData> chatUsersStatusList )
	{
		int result = 0;
		
		//String requestType = request.getParameter( "RequestType" );
		
		UserProfileKey userKey = new UserProfileKey( );
		
		userKey.email_ = request.getParameter( "userId" );
		
		result = get( userKey, chatUsersStatusList );
		
		
		return result;
		
		
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
		
		ChatUser chatUser = new ChatUser( );
		
		result = chatUser.get( userKey, chatUsersStatusList );
		
		chatUser = null;
		
		return result;
		
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
		
		ChatUser chatUser = new ChatUser( );
		
		result = chatUser.get( userKey, userStatusData );
		
		chatUser = null;
		
		return result;
		
	}
}

