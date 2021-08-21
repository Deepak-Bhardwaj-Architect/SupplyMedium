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


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import utils.ErrorLogger;
import core.chat.ChatMessageData;

/**
 * File:  ChatMessageDataConverter.java 
 *
 * Created on 16-Oct-2013 9:55:24 AM
 */
public class ChatMessageDataConverter
{

	/*
	 * Method : ChatMessageDataConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public ChatMessageDataConverter()
	{
	}
	
	
	/*
	 * Method : convert 
	 * 
	 * Input  : HttpServletRequest object, ChatMessageData
	 * 
	 * Return : int - success/fail
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to ChatMessage object.
	 * And copied to chatMessageData parameter so it available in caller classes.
	 * 
	 */
    public int convert( HttpServletRequest request, ChatMessageData chatMessageData )
    {
    	int result = 0;
    	
    	ErrorLogger errorLogger = ErrorLogger.instance( );
		
		try
        {
			
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			/* Chat Message details */
			
			if( reqMap.containsKey( "userId" ) )
			{
				chatMessageData.fromUserKey_.email_ = request.getParameter( "userId" );
			}
			
			if( reqMap.containsKey( "otherUserId" ) )
			{
				chatMessageData.toUserKey_.email_ = request.getParameter( "otherUserId" );
			}
			
			if( reqMap.containsKey( "message" ) )
			{
				chatMessageData.message_ = request.getParameter( "message" );
			}
			
        }
		catch( Exception e )
	    {
			errorLogger.logMsg( "Exception::ChatMessageDataConverter.convert() - " + e );
	        	
	        return -1;
	    }
		
		return result;
    }

}
