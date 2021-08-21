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
package tester.newsroom;

import java.util.ArrayList;
import java.util.List;

import core.newsroom.WLMemberData;
import ctrl.newsroom.WLMemberController;
import utils.ErrorMaster;

/**
 * File:  WLMemberTest.java 
 *
 * Created on 03-Sep-2013 12:32:53 PM
 */
public class WLMemberTest
{

    private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : WLMemberTest -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WLMemberTest()
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method : addMembersTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: Used to test add members to watchlist
	 */
	public void addMembersTest()
	{
		List<WLMemberData> members = new ArrayList<WLMemberData>( );
		
		WLMemberData wlMemberData1 = new WLMemberData( );
		
		wlMemberData1.watchListId_ = 1;
		
		wlMemberData1.memberKey_.email_ = "";
		
		members.add( wlMemberData1 );
		
		wlMemberData1 = null;
		
		
		WLMemberData wlMemberData2 = new WLMemberData( );
		
		wlMemberData2.watchListId_ = 1;
		
		wlMemberData2.memberKey_.email_ = "";
		
		members.add( wlMemberData2 );
		
		wlMemberData2 = null;
		
		
		WLMemberController controller = new WLMemberController( );
		
		int result = controller.add( members );
		
		if( result == 10130 )
			errorMaster_.insert( "Add members test completed" );
		else
			errorMaster_.insert( "Add members test failed" );
		
	}
	
	/*
	 * Method : deleteMembersTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: delete members from watchlist
	 */
	public void deleteMembersTest()
	{
		List<WLMemberData> members = new ArrayList<WLMemberData>( );
		
		WLMemberData wlMemberData1 = new WLMemberData( );
		
		wlMemberData1.watchListId_ = 1;
		
		wlMemberData1.memberKey_.email_ = "";
		
		members.add( wlMemberData1 );
		
		wlMemberData1 = null;
		
		
		WLMemberData wlMemberData2 = new WLMemberData( );
		
		wlMemberData2.watchListId_ = 1;
		
		wlMemberData2.memberKey_.email_ = "";
		
		members.add( wlMemberData2 );
		
		wlMemberData2 = null;
		
		
		WLMemberController controller = new WLMemberController( );
		
		int result = controller.remove( members );
		
		if( result == 10140 )
			errorMaster_.insert( "delete members test completed" );
		else
			errorMaster_.insert( "delete members test failed" );
		
	}
	
	/*
	 * Method : getMembersTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: get the members of the watchlist
	 */
	public void getMembersTest()
	{
		WLMemberController controller = new WLMemberController( );
		
		long watchListId = 1;
		
		List<WLMemberData> members = new ArrayList<WLMemberData>( );
		
		int result = controller.get( watchListId, members );
		
		if( result == 10150 )
		{
			errorMaster_.insert( "Members fetch test completed" );
			
			for( WLMemberData wlMemberData : members )
            {
	            wlMemberData.show( );
            }
		}
		else
		{
			errorMaster_.insert( "Members fetch test failed" );
		}
		
	}
	
	

}
