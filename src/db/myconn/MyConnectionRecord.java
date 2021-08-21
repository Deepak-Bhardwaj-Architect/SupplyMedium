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
package db.myconn;

import java.sql.Timestamp;


/**
 * File:  UserConnectionRecord.java 
 *
 * Created on 13-Aug-2013 5:10:19 PM
 */
public class MyConnectionRecord
{

	// Name of the person who sent the connection request
		public String fromUserName_;
		
		// Name of the user's( who sent the connection ) company
		public String fromCompName_;
		
		// Profile key string of the user who sent the connection request
		public String fromUserKey_;
		
		// Company key string of the user who sent the connection request
		public String fromRegnKey_;
		
		// Name of the person who receive the connection request
		public String toUserName_;
			
		// Name of the user's( who receive the connection ) company
		public String toCompName_;
			
		// Profile key string  of the user who receive the connection request
		public String toUserKey_;
			
		// Company key string of the user who receive the connection request
		public String toRegnKey_;
		
		// Status of the connection it may be "Request Sent" or "Accepted"
		public String status_;
		
		
		public Timestamp createdTimestamp_;
		

		/*
		 * Method : UserConnectionRecord -- constructor
		 * 
		 * Input  : None
		 * 
		 * Return : None
		 * 
		 * Purpose:
		 */
 
        public MyConnectionRecord()
        {
       

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
			
			System.out.println( "fromUserName_ 	="+ fromUserName_ );
			
			System.out.println( "fromCompName_	="+ fromCompName_);
			
			System.out.println( "fromUserKey_	="+ fromUserKey_);
			
			System.out.println( "fromRegnKey_	="+ fromRegnKey_);
			
			
			
			System.out.println( "toUserName_ 	="+ toUserName_ );
			
			System.out.println( "toCompName_	="+ toCompName_);
			
			System.out.println( "toUserKey_		="+ toUserKey_);
			
			System.out.println( "toRegnKey_		="+ toRegnKey_);
			
			
			System.out.println( "status_		="+ status_);
			
			System.out.println( "createdTimestamp		="+ createdTimestamp_);
			
			
			
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
			fromUserName_ 	= null;
			fromCompName_  	= null;
			fromUserKey_   	= null;
			fromRegnKey_   	= null;
			
			toUserName_    	= null;
			toCompName_    	= null;
			toUserKey_     	= null;
			toRegnKey_     	= null;
			
			status_			= null;
			createdTimestamp_ = null;
		}

}
