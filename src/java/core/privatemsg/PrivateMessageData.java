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
import utils.ErrorMaster;

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
        
        private static ErrorMaster errorMaster_ = null;



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
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( "message_Id    			= "+ messageId_ );
		
		errorMaster_.insert( "from_user_Key    		= "+ fromUserKey_.toString( ) );
		
		errorMaster_.insert( "to_user_Key    		= "+ toUserKey_.toString( ) );
		
		errorMaster_.insert( "message    			= "+ message );
		
		errorMaster_.insert( "created_timestamp    	= "+ createdTimestamp_ );
		
		errorMaster_.insert( "fromUserProfilePicPath_= "+ fromUserProfilePicPath_);
		
		errorMaster_.insert( "toUserProfilePicPath_	= "+ toUserProfilePicPath_);
		
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



