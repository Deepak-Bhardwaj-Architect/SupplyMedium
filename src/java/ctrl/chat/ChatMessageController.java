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

import core.chat.ChatMessage;
import core.chat.ChatMessageData;

/**
 * File:  ChatMessageController.java 
 *
 * Created on 15-Oct-2013 2:52:24 PM
 */
public class ChatMessageController
{

	/*
	 * Method : ChatMessageController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public ChatMessageController()
	{
	}
	
	/*
	 * Method : processMessage
	 * 
	 * Input  : HttpServletRequest object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: This method is used to control the chat message. It do the following operation in chat message
	 * 
	 * 1. Create the new Advertisement
	 * 
	 */
	
	public int processMessage( HttpServletRequest request )
	{
		int result = 0 ;
		
		ChatMessageData chatMessageData = new ChatMessageData( );
		
		ChatMessageDataConverter converter = new ChatMessageDataConverter( );
		
		result = converter.convert( request, chatMessageData );
		
		converter = null;
		
		if( result != 0 )
			return 10621;
		
		result = add( chatMessageData );
		
		return result;
	}
	

	/*
	 * Method : add
	 * 
	 * Input  : ChatMessageData
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to add the new message in message cache
	 */
	
	public int add ( ChatMessageData chatMessageData )
	{
		int result = 0;
		
		ChatMessage chatMessage = new ChatMessage( );
		
		result = chatMessage.add( chatMessageData );
		
		return result;
	
	}
	
	

}
