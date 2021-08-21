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
package ctrl.myconn;

import javax.servlet.http.HttpServletRequest;

import core.myconn.MyConnStatus;
import core.myconn.MyConnectionData;


/**
 * File:  UserConnController.java 
 *
 * Created on 13-Aug-2013 4:53:31 PM
 */
public class MyConnStatusController
{

	/*
	 * Method : UserConnController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnStatusController()
	{
		
	}
	
	/*
	 * Method : processStatus 
	 * 
	 * Input  : HttpServletRequest obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It get the HttpServletRequest object as parameter. Then parse the request,
	 * from the request data it decide the what operation want to do from the following list
	 * 
	 * 	1.Add New Connection request
	 * 	2.Accept Connection request
	 * 	3.Reject connection request
	 * 
	 */
	
	public int processStatus( HttpServletRequest request )
    {
		 int result = 0;
		 
		 // Converting request object to MyConnectionData object
		 
		MyConnStatusConverter converter = new MyConnStatusConverter( );
		
		MyConnectionData myConnData = new MyConnectionData( );
		
		result = converter.convert( request, myConnData );
	   
		converter = null;
		
		if( result != 0 )
			return 10001;  // Parser error.
		
		String requestType = request.getParameter( "RequestType" );
		
		if( requestType.equals( "AddConnection" ))
		{
			result = addConnection( myConnData );
		}
		else if( requestType.equals( "AcceptConnection" ))
		{
			result = acceptConnection( myConnData );
		}
		else if( requestType.equals( "RejectConnection" ))
		{
			result = rejectConnection( myConnData );
		}
		
		//bala
		
		else if(requestType.equals( "DeleteConnection" ))
		{
			result= deleteConnection(myConnData);
		}
		
		else
		{
			result = 10002;  // can't find appropriate request type
		}
	    
	    return result;
    }
	
	/*
	 * Method : addConnection
	 * 
	 * Input  : MyConnectionData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to add new user connection with Request Sent status.
	 * User send the request connection to other user at the time this method called.
	 * It create the new connection between both the user and put the status as Request Sent.
	 * Once the other user accepted the request the status change as Accepted.
	 */
	
	public int addConnection( MyConnectionData myConnData )
	{
		int result = 0;
		
		MyConnStatus myConnStatus = new MyConnStatus( );
		
		result = myConnStatus.addConnection( myConnData );
		
		myConnStatus = null;
		
		return result;
	}
	
	
	/*
	 * Method : acceptConnection
	 * 
	 * Input  : MyConnectionData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is called when user accept the connection request.
	 * This method change the user connection status as 'Accepted'. 
	 */
	
	public int acceptConnection( MyConnectionData myConnData )
	{
		int result = 0;
		
		MyConnStatus myConnStatus = new MyConnStatus( );
		
		result = myConnStatus.acceptConnection( myConnData );
		
		myConnStatus = null;
		
		return result;
	}
	
	
	/*
	 * Method : rejectConnection
	 * 
	 * Input  : MyConnectionData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is called when user reject the connection request.
	 * It remove the user connection between them.
	 */
	
	public int rejectConnection( MyConnectionData myConnData )
	{
		int result = 0;
		
		MyConnStatus myConnStatus = new MyConnStatus( );
		
		result = myConnStatus.rejectConnection( myConnData );
		
		myConnStatus = null;
		
		return result;
	}
	
	
	//bala
	
	public int deleteConnection(MyConnectionData myConnData)
	{
		int result= 0;
		
		MyConnStatus myConnStatus=new MyConnStatus( );
		
		result=myConnStatus.deleteConnection(myConnData);
		
		myConnStatus = null;
		
		return result;
	}
	
	
}
