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
import java.util.List;

import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File:  ChatEventData.java 
 *
 * Created on 15-Oct-2013 3:00:40 PM
 */

public class ChatEventData
{
	public UserProfileKey userKey_;
	
	public List<ChatUserStatusData> chatUsersStatus_;
	
	public List<ChatMessageData> chatMessages_;

	/*
	 * Method : ChatEventData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
        private static ErrorMaster errorMaster_ = null;


	public ChatEventData()
	{
		userKey_ 			= new UserProfileKey( );
		
		chatUsersStatus_ 	= new ArrayList<ChatUserStatusData>( );
		
		chatMessages_   	= new ArrayList<ChatMessageData>( );
                
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method : clear( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		userKey_ 			= null;
		
		chatUsersStatus_ 	= null;
		
		chatMessages_ 		= null;
	}

	/*
	 * Method : show( ) 
	 * 
	 * Input  : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		errorMaster_.insert( "userKey_			= " + userKey_ );
		
		errorMaster_.insert( "chatUsersStatus_	= " + chatUsersStatus_ );
		
		errorMaster_.insert( "chatMessages_		= " + chatMessages_ );
	}

}
