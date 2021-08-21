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
package tester.privatemsg;

import java.util.ArrayList;
import java.util.List;

import core.privatemsg.PrivateMessageData;
import core.regn.UserProfileKey;
import ctrl.privatemsg.PrivateMessageController;
import utils.ErrorMaster;

/**
 * File:  PrivateMessageTester.java 
 *
 * Created on Sep 23, 2013 11:15:27 AM
 */
public class PrivateMessageTest
{

    private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : PrivateMessageTest -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public PrivateMessageTest()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	public void execute()
	{
		//addTest( );
		//deleteTest( );
		fetchTest( );
	}
	
	
	/*
	 * Method : addTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to add the new privatemessage. 
	 */
	
	public void addTest()
	{
		PrivateMessageController controller = new PrivateMessageController( );
		
		PrivateMessageData privateMessageData = new PrivateMessageData( );
		
		privateMessageData.message = "message1";
		
		privateMessageData.fromUserKey_.email_ = "";
		
		privateMessageData.toUserKey_.email_ = "";
		
		int result = controller.add( privateMessageData );
		
		errorMaster_.insert( "privatemessage result id="+result );
		
		if( result == 10100  )
		{
			errorMaster_.insert( "Privatemessage add test successfully completed" );
			
			errorMaster_.insert( "Privatemessage added successfully" );
		}
		
		else
		{
			errorMaster_.insert( "Privatemessage add test failed" );
		}
			
	}
	
	/*
	 * Method : deleteTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to delete the privatemessage. 
	 */
	public void deleteTest()
	{
		PrivateMessageController controller = new PrivateMessageController( );
		
		PrivateMessageData privateMessageData = new PrivateMessageData( );
		
		privateMessageData.message = "message1";
		
		privateMessageData.fromUserKey_.email_ = "";
		
		privateMessageData.toUserKey_.email_ = "";
		
		int result = controller.remove( privateMessageData );
		
		if( result == 10110  )
		{
			errorMaster_.insert( "Privatemessage delete test successfully completed" );
			
		}
		
		else
		{
			errorMaster_.insert( "Privatemessage delete test failed" );
		}
			
	}
	
	/*
	 * Method : fetchTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to fetch the messages for given user key. 
	 */
	public void fetchTest()
	{
		PrivateMessageController controller = new PrivateMessageController( );
		
		List<PrivateMessageData> messages = new ArrayList<PrivateMessageData>( );
	
		UserProfileKey fromUserKey = new UserProfileKey( );
		
		fromUserKey.email_ = "";
		
		UserProfileKey toUserKey = new UserProfileKey( );
		
		toUserKey.email_ = "";
		
		
		PrivateMessageData pmData = new PrivateMessageData( );
		
		pmData.fromUserKey_ = fromUserKey;
		
		pmData.toUserKey_ 	=  toUserKey;
		
		int result = controller.get( pmData, messages );
		
		if( result == 10120  )
		{
			errorMaster_.insert( "Privatemessage fetch test successfully completed" );
			
			for( PrivateMessageData privateMessageData : messages )
            {
				privateMessageData.show( );
            }
			
		}
		
		else
		{
			errorMaster_.insert( "Privatemessage fetch test failed" );
		}
			
	}
	

}
