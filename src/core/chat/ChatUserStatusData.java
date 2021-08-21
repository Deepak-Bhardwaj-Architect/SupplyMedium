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


import core.regn.UserProfileKey;

/**
 * File:  ChatUserStatusData.java 
 *
 * Created on 15-Oct-2013 2:59:57 PM
 */
public class ChatUserStatusData
{
	public UserProfileKey userKey_;
	
	public String name_;
	
	public String profilePicturePath_;
	
	public int status_;
	
	public String email_;
	

	/*
	 * Method : ChatUserStatusData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public ChatUserStatusData()
	{
		userKey_ = new UserProfileKey( );
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
		
		name_ 				= null;
		
		profilePicturePath_ = null;
		
		status_ 			= 0;
		
		email_ 				= null;
		
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
		System.out.println( "userKey_      			= " + userKey_ );
		System.out.println( "name_     				= " + name_ );
		System.out.println( "profilePicturePath_    = " + profilePicturePath_ );
		System.out.println( "status_      			= " + status_ );
		System.out.println( "email_      			= " + email_ );
	}

}
