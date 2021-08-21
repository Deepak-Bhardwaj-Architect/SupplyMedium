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
package tester.myconn;

import java.util.ArrayList;
import java.util.List;

import core.myconn.MyConnectionData;
import core.regn.UserProfileKey;

import ctrl.myconn.MyConnNetworkController;

/**
 * File:  MyConnNetworkTest.java 
 *
 * Created on 20-Aug-2013 11:25:31 AM
 */
public class MyConnNetworkTest
{

	/*
	 * Method : MyConnNetworkTest.java() -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnNetworkTest()
	{
	}
	
	/*
	 * Method : getConnTest() 
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: Used to test the all connection fetch features.
	 */
	
	public void getConnTest()
	{
		UserProfileKey userkey = new UserProfileKey( );
		
		userkey.email_ = "smadmn2@gmail.com";
		
		MyConnNetworkController  controller = new MyConnNetworkController( );
		
		List<MyConnectionData> connections = new ArrayList<MyConnectionData>( );
		
		int result = controller.getConnection( userkey, connections );
		
		if( result == 10030 )
		{
			System.out.println( "Connection fetch test successfully" );
			
			System.out.println( "Connections count="+ connections.size( ));
			
			for( MyConnectionData myConnectionData : connections )
            {
	            myConnectionData.show( );
            }
		}
		else
		{
			System.out.println( "Connection fetch test failed" );
		}
	}
	
	/*
	 * Method : getPendingConnTest() 
	 * 
	 * Input  : None
	 * 
	 * 
	 * 
	 * Return : None
	 * 
	 * Purpose: Used to test the all pending connection fetch features.
	 */
	
	public void getPendingConnTest()
	{
		UserProfileKey userkey = new UserProfileKey( );
		
		userkey.email_ = "smadmn1@gmail.com";
		
		MyConnNetworkController  controller = new MyConnNetworkController( );
		
		List<MyConnectionData> connections = new ArrayList<MyConnectionData>( );
		
		int result = controller.getPendingRequest( userkey, connections );
		
		if( result == 10040 )
		{
			System.out.println( "Connection fetch test successfully" );
			
			System.out.println( "Connections count="+ connections.size( ));
			
			for( MyConnectionData myConnectionData : connections )
            {
	            myConnectionData.show( );
            }
		}
		else
		{
			System.out.println( "Connection fetch test failed" );
		}
	}
	
	/*
	 * Method : searchConnTest() 
	 * 
	 * Input  : None
	 * 
	 * 
	 * Return : None
	 * 
	 * Purpose: Used to test the search connections
	 * 
	 */
	
	public void searchConnTest()
	{
		UserProfileKey userkey = new UserProfileKey( );
		
		userkey.email_ = "smadmn1@gmail.com";
		
		String searchText = "smadmn";
		
		MyConnNetworkController  controller = new MyConnNetworkController( );
		
		List<MyConnectionData> connections = new ArrayList<MyConnectionData>( );
		
		int result = controller.searchConnections( userkey, searchText, connections );
		
		if( result == 10050 )
		{
			System.out.println( "Search Connection fetch test successfully" );
			
			System.out.println( "Connections count="+ connections.size( ));
			
			for( MyConnectionData myConnectionData : connections )
            {
	            myConnectionData.show( );
            }
		}
		else
		{
			System.out.println( "Search connection fetch test failed" );
		}
	}


	
}
