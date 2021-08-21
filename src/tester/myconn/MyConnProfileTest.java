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

import core.myconn.MyConnProfile;
import core.myconn.MyConnProfileData;
import core.myconn.MyConnectionData;
import core.regn.UserProfileKey;
import ctrl.myconn.MyConnNetworkController;
import ctrl.myconn.MyConnProfileController;

/**
 * File:  MyConnProfileTest.java 
 *
 * Created on 21-Aug-2013 3:48:22 PM
 */
public class MyConnProfileTest
{

	/*
	 * Method : MyConnProfileTest -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnProfileTest()
	{
	}
	
	/*
	 * Method : getConnProfileTest() 
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: Used to test the fetch the given connection profile deatails.
	 */
	
	public void getConnProfileTest()
	{
		UserProfileKey userkey = new UserProfileKey( );
		
		userkey.email_ = "smadmn2@gmail.com";
		
		MyConnProfileController controller = new MyConnProfileController( );
		
		MyConnProfileData myConnProfileData = new MyConnProfileData( );
		
		int result = controller.get( userkey, myConnProfileData );
		
		if( result == 10060 )
		{
			System.out.println( "Connection profile fetch test successfully" );
			
			myConnProfileData.show( );
			
		}
		else
		{
			System.out.println( "Connection fetch test failed" );
		}
	}

}
