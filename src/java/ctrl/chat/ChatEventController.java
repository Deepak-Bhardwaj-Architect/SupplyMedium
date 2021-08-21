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

import javax.servlet.http.HttpServletRequest;

import core.chat.ChatEvent;
import core.chat.ChatEventData;
import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File:  ChatEventController.java 
 *
 * Created on 15-Oct-2013 2:52:45 PM
 */
public class ChatEventController
{
    
//    private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : ChatEventController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public ChatEventController()
	{
//		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method : processEvent
	 * 
	 * Input  : HttpServletRequest object, ChatEventData object
	 * 
	 * Return : int
	 * 
	 * Purpose: Used to parse the request and do the operation depending to request type
	 * 
	 */
	public int processEvent( HttpServletRequest request, ChatEventData chatEventData )
	{
		
		//errorMaster_.insert( "Process Event" );
		
		int result = 0;
		
		//String requestType = request.getParameter( "RequestType" );
		
		UserProfileKey userKey = new UserProfileKey( );
		
		userKey.email_ = request.getParameter( "userId" );
		
		result = get( userKey, chatEventData );
		
		System.gc( );
		
		return result;
		
		
	}
	

	/*
	 * Method : get
	 * 
	 * Input  : userkey and ChatEventData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch user latest event in cache for the requested user . And assign to chatEventData 
	 * parameter it is copied to caller classes.
	 */
	
	public int get ( UserProfileKey userKey, ChatEventData chatEventData )
	{
		
		int result = 0;
		
		ChatEvent chatEvent = new ChatEvent( );
		
		result = chatEvent.get( userKey, chatEventData );
		
		chatEvent = null;
		
		return result;
		
	}

}
