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
package core.privatemsg;

import java.sql.Timestamp;


import core.regn.UserProfileKey;

/**
 * File:  PrivateMessageData.java 
 *
 * Created on Sep 20, 2013 5:57:35 PM
 */
public class PrivateMessageData
{
	// Id of the privateMessage. This is the auto incremented id
	public long messageId_;
	
	// fromuser key of the privateMessage. 
	public UserProfileKey fromUserKey_;
	
	//touser key of the privateMessage
	public UserProfileKey toUserKey_;
	
	public String fromUserProfilePicPath_;
	
	public String toUserProfilePicPath_;
	
	// Name of the privatemessage
	public String message;
	
	// PrivateMessage created time
	public Timestamp createdTimestamp_;

	/*
	 * Method : PrivateMessageData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public PrivateMessageData()
	{
		fromUserKey_ = new UserProfileKey( );
		
		toUserKey_ = new UserProfileKey( );
		
		messageId_ = -1;
		
	}
	
	/*
	 * Method : show( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "message_Id    			= "+ messageId_ );
		
		System.out.println( "from_user_Key    		= "+ fromUserKey_.toString( ) );
		
		System.out.println( "to_user_Key    		= "+ toUserKey_.toString( ) );
		
		System.out.println( "message    			= "+ message );
		
		System.out.println( "created_timestamp    	= "+ createdTimestamp_ );
		
		System.out.println( "fromUserProfilePicPath_= "+ fromUserProfilePicPath_);
		
		System.out.println( "toUserProfilePicPath_	= "+ toUserProfilePicPath_);
		
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
		messageId_ 				= -1;
		
		fromUserKey_ 			= null;
		
		toUserKey_ 				= null;
		
		message 				= null;
		
		createdTimestamp_ 		= null;
		
		fromUserProfilePicPath_ = null;
		
		toUserProfilePicPath_   = null;
		
	}

}



