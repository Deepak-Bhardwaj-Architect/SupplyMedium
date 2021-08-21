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

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import core.myconn.MyConnNetwork;
import core.myconn.MyConnectionData;
import core.regn.UserProfileKey;

/**
 * File:  UserConnFetchController.java 
 *
 * Created on 13-Aug-2013 4:55:59 PM
 */
public class MyConnNetworkController
{

	/*
	 * Method : UserConnFetchController
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnNetworkController()
	{
	}
	
	/*
	 * Method : processNetwork 
	 * 
	 * Input  : HttpServletRequest obj, list of UserConnection object
	 * 
	 * Return : int
	 * 
	 * Purpose: It get the HttpServletRequest object as parameter. Then parse the request,
	 * from the request data it decide the what operation want to do from the following list
	 * 
	 * 	1.Get the connection
	 * 	2.Get the pending connection request
	 * 
	 */
	
	public int processNetwork( HttpServletRequest request, List<MyConnectionData> connections )
    {
		String requestType = request.getParameter( "RequestType" );
		
		UserProfileKey userKey = new UserProfileKey( );
		MyConnectionData myData=new MyConnectionData( );
		
		userKey.email_ = request.getParameter( "UserKey" );
		
		String searchText = request.getParameter( "SearchText" );
		
		int result = 0;
		
	    if( requestType.equals( "MyConnections" ))
	    {
	    	result = getConnection( userKey, connections );
	    }
	    else if( requestType.equals( "PendingConnections" ))
	    {
	    	result = getPendingRequest( userKey, connections );
	    }
	    else if( requestType.equals( "SearchConnections" ))
	    {
	    	result = searchConnections( userKey, searchText, connections );
	    }
	    else if( requestType.equals( "SearchMyConnections" ))
	    {
	    	result = searchMyConnections( userKey, searchText, connections );
	    }
	    else if( requestType.equals( "MyMessageConnections" ))
	    {
	    	result = getMessageConnection( userKey, connections );
	    }
	    
	    	    
	    else
	    {
			result = 10031;  // Request type error
		}
	    
	    return result;
    }
	
	/*
	 * Method : getConnection
	 * 
	 * Input  : UserProfileKey obj, list of UserConnectionData objects
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get all the user connections for given userkey.
	 */
	
	public int getConnection( UserProfileKey userKey, List<MyConnectionData> connections )
	{
		int result = 0;
		
		MyConnNetwork myConnNetwork = new MyConnNetwork( );
		
		result = myConnNetwork.getConnection( userKey, connections );
		
		myConnNetwork = null;
		
		return result;
	}
	
	
	/*
	 * Method : searchMyConnections
	 * 
	 * Input  : UserProfileKey obj, search text,list of UserConnectionData objects
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to filter the connection with in the user connection
	 */
	
	public int searchMyConnections( UserProfileKey userKey, String searchText, List<MyConnectionData> connections )
	{
		int result = 0;
		
		MyConnNetwork myConnNetwork = new MyConnNetwork( );
		
		result = myConnNetwork.searchMyConnection( userKey, searchText, connections );
		
		myConnNetwork = null;
		
		return result;
	}
	
	
	

	/*
	 * Method : getPendingRequest
	 * 
	 * Input  : UserProfileKey obj, list of UserConnectionData objects
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get all the pending user connection request
	 *  for given userkey.
	 */
	
	public int getPendingRequest( UserProfileKey userKey, List<MyConnectionData> connections )
	{
		int result = 0;
		
		MyConnNetwork myConnNetwork = new MyConnNetwork( );
		
		result = myConnNetwork.getPendingRequest( userKey, connections );
		
		myConnNetwork = null;
		
		return result;
	}
	
	/*
	 * Method : searchConnections
	 * 
	 * Input  : UserProfileKey obj,search text list of UserConnectionData objects
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to fetch the all the connection for the given search text.
	 */
	
	public int searchConnections( UserProfileKey userKey,String searchText, List<MyConnectionData> connections )
	{
		int result = 0;
		
		MyConnNetwork myConnNetwork = new MyConnNetwork( );
		
		result = myConnNetwork.searchConnection( userKey, searchText, connections );
		
		myConnNetwork = null;
		
		return result;
	}
	
	/*
	 * Method : getMessageConnection
	 * 
	 * Input  : UserProfileKey obj, list of UserConnectionData objects
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get all the user connections who are send/receive messages from/to given user.
	 */
	
	public int getMessageConnection( UserProfileKey userKey, List<MyConnectionData> connections )
	{
		int result = 0;
		
		MyConnNetwork myConnNetwork = new MyConnNetwork( );
		
		result = myConnNetwork.getMessageConnection( userKey, connections );
		
		myConnNetwork = null;
		
		return result;
	}
	
	

}
