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
package core.common;

import java.util.ArrayList;
import java.util.List;


import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File:  EmailData.java 
 *
 * Created on Oct 2, 2013 4:18:02 PM
 */
public class EmailData
{

	public List<UserProfileKey> customerKeys_;
	
	public String message_;
	
	public String companyName_;
	
	public String senderName_;
	
	public UserProfileKey senderKey_;
        
        private static ErrorMaster errorMaster_ = null;


	
	
	
	/*Constructor - To Initialize the class variables*/
	public EmailData(  )
	{
		
		message_= null;
		
		customerKeys_= new ArrayList<UserProfileKey>( );
		
		senderKey_ = new UserProfileKey( );
		
		companyName_=null;
		
		senderName_=null;
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		
	}
	
	
	
	/* Method: show
	 * 
	 * Input: none
	 *
	 * Return: void
	 *
	 * Purpose: To print the class variables in the console
	 */
	
	public void show()
	{
		errorMaster_.insert( "message_  ="+message_);
		errorMaster_.insert( "customerKey_  ="+customerKeys_.size( ));
		errorMaster_.insert( "companyName_"+companyName_);
		errorMaster_.insert("senderName"+senderName_);
		errorMaster_.insert("senderKey"+senderKey_);
	}
	
	/* Method: clear
	 * 
	 * Input: none
	 *
	 * Return: void
	 *
	 * Purpose: To release the class variables from memory
	 */
	
	public void clear()
	{
		message_=null;
		customerKeys_=null;
		companyName_=null;
		senderName_=null;
		senderKey_ = null;
	}

}
