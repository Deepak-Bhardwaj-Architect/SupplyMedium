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
package ctrl.privatemsg;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.privatemsg.PrivateMessageData;

/**
 * File:  PrivateMessageDataConverter.java 
 *
 * Created on Sep 20, 2013 2:34:58 PM
 */
public class PrivateMessageDataConverter
{
	ErrorLogger errorLogger_;
	
	/*
	 * Method : PrivateMessageDataConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	
	public PrivateMessageDataConverter()
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : convert 
	 * 
	 * Input  : HttpServletRequest object, PrivateMessageData object
	 * 
	 * Return : int - success/fail
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to PrivateMessageData object.
	 * And copied to privateMessageData parameter so it available in caller classes.
	 * 
	 */

    public int convert( HttpServletRequest request, PrivateMessageData privateMessageData)
    {
    	int result = 0;
		
		try
        {
			
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			/* PrivateMessage details */
			
			if( reqMap.containsKey( "Message" ) )
			{
				privateMessageData.message = request.getParameter( "Message" );
			}
			
			if( reqMap.containsKey( "MessageId" ) )
			{
				privateMessageData.messageId_ = Integer.parseInt( request.getParameter( "MessageId" ) );
			}
			
			if( reqMap.containsKey( "FromUserKey" ) )
			{
				privateMessageData.fromUserKey_.email_ = request.getParameter( "FromUserKey" );
			}
			
			if( reqMap.containsKey( "ToUserKey" ) )
			{
				privateMessageData.toUserKey_.email_ = request.getParameter( "ToUserKey" );
			}
			
        }
		catch( Exception e )
	    {
			errorLogger_.logMsg( "Exception::PrivateMessageDataConverter.convert() - " + e );
	        	
	        return -1;
	    }
		
		return result;
    }
    }

