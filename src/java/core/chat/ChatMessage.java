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



/**
 * File:  ChatMessage.java 
 *
 * Created on 15-Oct-2013 3:28:54 PM
 */
public class ChatMessage
{

	/*
	 * Method : ChatMessage
	 *  -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public ChatMessage()
	{
		
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
		
		ChatMessageCache chatMessageCache = ChatMessageCache.instance( );
		
		result = chatMessageCache.put( chatMessageData.toUserKey_, chatMessageData );
		
		if( result == 0)
			return 10620;
		else
			return 10622;
		
	}

}
