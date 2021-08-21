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

import utils.UserConnStatus;
import core.myconn.MyConnectionData;
import ctrl.myconn.MyConnStatusController;

/**
 * File:  MyConnStatusTest.java 
 *
 * Created on 20-Aug-2013 11:25:16 AM
 */
public class MyConnStatusTest
{

	/*
	 * Method : MyConnStatusTest.java() -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnStatusTest()
	{
	}
	

	/*
	 * Method : addConnTest 
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: Used to test the add connection 
	 */
	
	public void addConnTest()
	{
		MyConnectionData myConnData = new MyConnectionData( );
		
		myConnData.fromUserName_ = "George Clooney";
		myConnData.fromUserKey_.email_ = "smadmn2@gmail.com";
		myConnData.fromCompName_ = "Afton Chemicals";
		myConnData.fromRegnKey_.companyPhoneNo_ = "4044080000";
		
		myConnData.toUserName_ = "Denise Richards";
		myConnData.toUserKey_.email_ = "smadmn1@gmail.com";
		myConnData.toCompName_ = "Zynx Health";
		myConnData.toRegnKey_.companyPhoneNo_ = "4044082222";
		
		myConnData.status_ = UserConnStatus.status.REQUEST_SENT.getValue( );
		
		MyConnStatusController  controller = new MyConnStatusController( );
		
		int result = controller.addConnection( myConnData );
		
		if( result == 10000 )
		{
			System.out.println( "Add connection test successfully completed" );
		}
		else
		{
			System.out.println( "Add connection text failed" );
		}
		
		
	}
	

	/*
	 * Method : acceptConnTest 
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: Used to test the accept connection flow
	 */
	
	public void acceptConnTest()
	{
		MyConnectionData myConnData = new MyConnectionData( );
		
		
		myConnData.fromUserKey_.email_ = "smadmn2@gmail.com";
		
		myConnData.toUserKey_.email_ = "smadmn1@gmail.com";
		
		
		MyConnStatusController  controller = new MyConnStatusController( );
		
		int result = controller.acceptConnection( myConnData );
		
		if( result == 10010 )
		{
			System.out.println( "Accept connection test successfully completed" );
		}
		else
		{
			System.out.println( "Accept connection test failed" );
		}
	}


	/*
	 * Method : rejectConnTest 
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: Used to test the reject connection usecase
	 */
	
	public void rejectConnTest()
	{
		MyConnectionData myConnData = new MyConnectionData( );
		
		
		myConnData.fromUserKey_.email_ = "smadmn2@gmail.com";
		
		myConnData.toUserKey_.email_ = "smadmn1@gmail.com";
		
		
		MyConnStatusController  controller = new MyConnStatusController( );
		
		int result = controller.rejectConnection( myConnData );
		
		if( result == 10020 )
		{
			System.out.println( "Reject connection test successfully completed" );
		}
		else
		{
			System.out.println( "Reject connection test failed" );
		}
	}
}
