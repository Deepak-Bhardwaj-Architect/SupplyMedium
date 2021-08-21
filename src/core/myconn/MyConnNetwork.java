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
package core.myconn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import utils.ErrorLogger;
import utils.PathBuilder;
import utils.StringHolder;
import utils.UserConnStatus;

import core.privatemsg.PrivateMessageData;
import core.regn.RegnData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.myconn.MyConnectionTable;
import db.privatemsg.PrivateMessageTable;
import db.regn.CompanyRegnTable;
import db.regn.UserProfileTable;

/**
 * File:  MyConnNetwork.java 
 *
 * Created on 14-Aug-2013 5:13:26 PM
 */
public class MyConnNetwork
{

	/*
	 * Method : MyConnNetwork-- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnNetwork()
	{
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
		
		MyConnectionTable myConnTable = new MyConnectionTable( );
		
		result = myConnTable.filter( userKey, UserConnStatus.status.ACCEPTED.getValue( ), connections );
			
		myConnTable = null;
		
		
		
		if( result == 0 )
		{
			addProfileImagePath(  connections );
			
			return 10030;  // Connection fetched successfully
		}
			
		
		else
			return 10032;
		
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
	
		MyConnectionTable myConnTable = new MyConnectionTable( );
		
		result = myConnTable.filter( userKey, UserConnStatus.status.REQUEST_SENT.getValue( ), connections );
		
		myConnTable = null;
		
		if( result == 0 )
		{
			addProfileImagePath(  connections );
			return 10040;  // Connection fetched successfully
		}
			
		
		else
			return 10041;
	}
	
	
	/*
	 * Method : searchConnection
	 * 
	 * Input  : UserProfileKey obj, search text,list of UserConnectionData objects
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to search connections for given search text
	 */
	
	public int searchConnection( UserProfileKey userKey,String searchText, List<MyConnectionData> connections  )
	
	{
		int result = 0;
		
		List<UserProfileData> users = new ArrayList<UserProfileData>( );
		
		// fetch the user details from given search text
		
		UserProfileTable userProfileTbl = new UserProfileTable( );
		
		result = userProfileTbl.find( searchText, users );
		
		userProfileTbl = null;
		
		if( result != 0 )
			return 10051;  // Can't fetch the connections
		
		CompanyRegnTable regnTbl = new CompanyRegnTable( );
		
		for( UserProfileData userProfileData : users )
        {
			// Checking for same user details
			if(userProfileData.userProfileKey_.equals( userKey ))
				continue;
			
			// Fetching company details for given user
			RegnData regnData = new RegnData( );
			
	        result = regnTbl.find( userProfileData.companyRegnKey_, regnData );
	        
	        if( result != 0 )
	        	continue;
	        
	        MyConnectionData myConnData = new MyConnectionData( );
	        
	        myConnData.toUserName_ = userProfileData.firstName_+" "+userProfileData.lastName_;
	        myConnData.toCompName_ = regnData.companyName_;
	        
	        myConnData.toUserKey_ = userProfileData.userProfileKey_;
	        myConnData.toRegnKey_ = userProfileData.companyRegnKey_;
	        
	        // Converting local path to WEB path for user profile image
	        PathBuilder pathBuilder = new PathBuilder( );
	        
	        StringHolder profileImagePath = new StringHolder( );
	        
	        profileImagePath.value = "";
	        
	        pathBuilder.getWebURLFromRelativepath( userProfileData.profilePicture_, profileImagePath );
	        
	        myConnData.userProfileImagePath_ = profileImagePath.value;
	        
	        connections.add( myConnData );
	        
	        pathBuilder = null; profileImagePath = null;
	        
	        myConnData = null;regnData = null;
	        
	        result = filterUserConnection( userKey, connections );
	        
	        if( result != 0 )
	        	return 10051;
	        
        }
		
		
		regnTbl = null;
		
		return 10050;
		
	}
	
	/*
	 * Method : searchMyConnection
	 * 
	 * Input  : UserProfileKey obj, searchtext, list of UserConnectionData objects
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to filter the user connection according to given filter string
	 */
	
	public int searchMyConnection( UserProfileKey userKey, String searchText, List<MyConnectionData> connections )
	{
		int result = 0;
		
		MyConnectionTable myConnTable = new MyConnectionTable( );
		
		result = myConnTable.find( userKey, searchText, connections );
			
		myConnTable = null;
		
		if( result == 0 )
			return 10070;  // Connection fetched successfully
		
		else
			return 10071;
		
	}
	

	/*
	 * Method : filterUserConnection
	 * 
	 * Input  : connections object,UserKey
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to remove the user connections and 
	 * user pending connection from search connections.
	 */
	
	private int filterUserConnection( UserProfileKey userKey,List<MyConnectionData> connections )
	{
		int result = 0;
		
		try
        {
			List<MyConnectionData> myConnections = new ArrayList<MyConnectionData>( );
			
			List<MyConnectionData> pendingConnections = new ArrayList<MyConnectionData>( );
			
			
			// Fetching All connections of the user
			result = getConnection( userKey, myConnections );
			
			if( result != 10030 )
				return -1;
			
			// Fetching All pending connections request of the user
			result = getPendingRequest( userKey, pendingConnections );
			
			if( result != 10040 )
				return -1;
			
			System.out.println( "Connection count="+connections.size( ) );
			
			Iterator<MyConnectionData> iter = connections.iterator();
			
			while(iter.hasNext())
			{
				MyConnectionData connData = iter.next( );
				
				for( MyConnectionData myconn : myConnections )
	            {
					
		            if( connData.toUserKey_.equals( myconn.toUserKey_ ))
		            {
		            	iter.remove( );
		            	
		            	break;
		            }
		            	
	            }
				
			   
			}
			
			System.out.println( "Iterator 1 completed="+connections.size( ) );

			Iterator<MyConnectionData> iter1 = connections.iterator();
			
			while(iter1.hasNext())
			{
				MyConnectionData connData = iter1.next( );
				
				for( MyConnectionData penconn : pendingConnections )
	            {
		            if( connData.toUserKey_.equals( penconn.toUserKey_ ))
		            {
		            	iter1.remove( );
		            	
		            	break;
		            }
		            	
	            }
				
			   
			}
			
			
			
			// Removing connection from search result connection
		/*	for( MyConnectionData searchconn : connections )
	        {
		        for( MyConnectionData myconn : myConnections )
	            {
		            if( searchconn.toUserKey_.equals( myconn.toUserKey_ ))
		            	connections.remove( searchconn );
	            }
	        }
			
			// Removing pending connection from search result connection
			for( MyConnectionData searchconn : connections )
	        {
		        for( MyConnectionData penconn : pendingConnections )
	            {
		            if( searchconn.toUserKey_.equals( penconn.toUserKey_ ))
		            	connections.remove( searchconn );
	            }
	        }*/
        }
		catch( Exception e )
        {
			ErrorLogger errorLogger = ErrorLogger.instance( );
			
			errorLogger.logMsg( "Exception::MyConnectionTable.filterUserConnection - " + e );
			
			return -1;
        }
		
		
		return 0;
		
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
		
		// Get all the connection of the user
		MyConnectionTable myConnTable = new MyConnectionTable( );
		
		result = myConnTable.filter( userKey, UserConnStatus.status.ACCEPTED.getValue( ), connections );
			
		myConnTable = null;
		
		addProfileImagePath(  connections );
		
		PrivateMessageTable privateMsgTbl = new PrivateMessageTable( );
		
		List<PrivateMessageData> messages = new ArrayList<PrivateMessageData>( );
		
		privateMsgTbl.find( userKey, messages );
		
		privateMsgTbl = null;
		
		// Create set it contain all the connection key of the user who sent/receive the message from/to user.
		Set<UserProfileKey> users = new HashSet<UserProfileKey>( );
		
		for( PrivateMessageData privateMessageData : messages )
        {
	        users.add( privateMessageData.toUserKey_ );
	        users.add( privateMessageData.fromUserKey_ );
	        
        }
		
	    // Remove the connections from connection list who are not sent/receive the message from/to user
		Iterator<MyConnectionData> it = connections.iterator( );

		while( it.hasNext( ) )
		{

			if( !users.contains( it.next( ).toUserKey_ ) )
			{
				it.remove( );
			}

		}
        
        
		if( result == 0 )
			return 10080;  // Connection fetched successfully
		
		else
			return 10081;
		
	}
	
	
	// It is used to set the connection profile picture path
	private int addProfileImagePath( List<MyConnectionData> connections )
	{
		int result = 0;
		
		for( MyConnectionData myConnectionData : connections )
        {
			// Get user profile path for the connection
			UserProfileData userProfileData = new UserProfileData( );
			
			UserProfileTable userProfileTable = new UserProfileTable( );
			
			result = userProfileTable.find( myConnectionData.toUserKey_, userProfileData );
			
			userProfileTable = null;
			
			if( result != 0 )
				continue;
			
			 // Converting relative path to WEB path for connection profile image
			
	        PathBuilder pathBuilder = new PathBuilder( );
	        
	        StringHolder profileImagePath = new StringHolder( );
	        
	        profileImagePath.value = "";
	        
	        pathBuilder.getWebURLFromRelativepath( userProfileData.profilePicture_, profileImagePath );
	        
	        myConnectionData.userProfileImagePath_ = profileImagePath.value;
	        
	        pathBuilder = null; profileImagePath = null;
        }
		return result;
	}

}
