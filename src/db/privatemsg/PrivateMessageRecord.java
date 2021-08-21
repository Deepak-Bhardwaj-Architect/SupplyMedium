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
package db.privatemsg;

import java.sql.Timestamp;

/**
 * File:  PrivateMessageRecord.java 
 *
 * Created on Sep 20, 2013 12:04:21 PM
 */
public class PrivateMessageRecord
{
		// Unique id of the privateMessage. It is auto incremented.
		public long messageId_;
		
		// PrivateMessage from user key
		public String fromUserKey_;
		
		//PrivateMessage to userkey
		public String toUserKey_;
		
		// Name of the privateMessage
		public String message;
		
		// created time stamp of the privateMessages
		public Timestamp createdTimestamp_;
		
		/*
		 * Method : PrivateMessageRecord -- constructor
		 * 
		 * Input  : None
		 * 
		 * Return : None
		 * 
		 * Purpose:
		 */

		public PrivateMessageRecord()
		{
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
			System.out.println( "messageId_ 		=" +messageId_);
			
			System.out.println( "fromUserKey_     		    =" +fromUserKey_);
			
			System.out.println("toUserKey_                      ="+toUserKey_);
				
			System.out.println( "message 						=" +message);
				
			System.out.println( "createdTimestamp_ 	=" +createdTimestamp_);
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
			messageId_ = -1;
			
			fromUserKey_ = null;
			
			toUserKey_=null;
			
			message = null;
			
			createdTimestamp_ = null;
		}
	}

		

