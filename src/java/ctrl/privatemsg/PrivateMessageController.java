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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import core.privatemsg.PrivateMessage;
import core.privatemsg.PrivateMessageData;


/**
 * File:  PrivateMessageController.java 
 *
 * Created on Sep 19, 2013 5:45:37 PM
 */
public class PrivateMessageController
{
	
	
	/*
	 * Method : PrivateMessageController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public PrivateMessageController()
	{
		
		
	}

	/*
	 * Method : processPM
	 * 
	 * Input  : HttpServletRequest object, list of PrivateMessageData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: This method is used to control the PrivateMessage. It do the following operation in PrivateMessage
	 * 
	 * 1. Create the new PrivateMessage
	 * 2. Delete the PrivateMessage
	 * 3. Get all the PrivateMessage for given user key
	 * 
	 * 
	 */

public int processPM( HttpServletRequest request, List<PrivateMessageData>messages )
{
	int result = 0;
	 // Converting request object to PrivateMessageData object
	
	PrivateMessageDataConverter converter = new PrivateMessageDataConverter( );
	
	PrivateMessageData privateMessageData = new PrivateMessageData( );
	
	result = converter.convert( request, privateMessageData );
   
	converter = null;
	
	if( result != 0 )
		return 10201;  // Parser error.
	
	String requestType = request.getParameter( "RequestType" );
	
	if( requestType.equals( "AddMessage" ))
	{
		result = add( privateMessageData );
	}
	else if( requestType.equals( "DeleteMessage" ))
	{
		result = remove( privateMessageData );
	}
	else if( requestType.equals( "FetchMessages" ))
	{
		result = get( privateMessageData, messages);
	}
	else
	{
		result = 10202;  // can't find appropriate request type
	}
	
	return result;
}



/*
 * Method : add
 * 
 * Input  : PrivateMessageData object
 * 
 * Return : int success/fail
 * 
 * Purpose: Used to add the new PrivateMessage for given user key. 
 */
public int add(PrivateMessageData privateMessageData)
{
	int result = 0;
	
	PrivateMessage privateMessage = new PrivateMessage( );
	
	result = privateMessage.add( privateMessageData );
	
	privateMessage = null;
	
	return result;
}

/*
 * Method : delete
 * 
 * Input  : privateMessageData
 * 
 * Return : int success/fail
 * 
 * Purpose: It delete the PrivateMessage using given privateMessageData. Also it remove the 
 * PrivateMessage member relationship
 */

public int remove( PrivateMessageData privateMessageData )
{
	int result = 0;
	
	PrivateMessage privateMessage = new PrivateMessage( );
	
	result = privateMessage.remove( privateMessageData );
	
	return result;
}



/*
 * Method : get
 * 
 * Input  : PrivateMessageData object, list of PrivateMessageData object
 * 
 * Return : int success/fail
 * 
 * Purpose: It get all the PrivateMessage for given userprofilekey. And copied to messages parameter.
 * So it is available for caller classes
 */

public int get( PrivateMessageData pmData, List<PrivateMessageData> messages )
{

	int result = 0;
	
	PrivateMessage privateMessage = new PrivateMessage( );
	
	result = privateMessage.get( pmData, messages );
	
	return result;
}
}