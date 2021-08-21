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
package ctrl.newsroom;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import core.newsroom.WLMember;
import core.newsroom.WLMemberData;

/**
 * File:  WLMemberController.java 
 *
 * Created on 30-Aug-2013 5:55:10 PM
 */
public class WLMemberController
{

	/*
	 * Method : WLMemberController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WLMemberController()
	{
	}
	
	/*
	 * Method : processWLMembers
	 * 
	 * Input  : HttpServletRequest object, list of watchlistData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: This method is used to control the watchlist members. It do the following operation 
	 * 
	 * 1. Add the members to watchlist
	 * 2. Delete the members from watchlist
	 * 3. Get all the members for given watchlist id
	 * 
	 * 
	 */
	public int processWLMembers( HttpServletRequest request, List<WLMemberData> members )
    {
		int result = 0;
		 
		 // Converting request object to list of members object
		 
		WLMemberDataConverter converter	= new WLMemberDataConverter( );
		
		
		result = converter.convert( request, members );
	   
		converter = null;
		
		if( result != 0 )
			return 10301;  // Parser error.
		
		String requestType = request.getParameter( "RequestType" );
		
		if( requestType.equals( "AddMembers" ))
		{
			result = add( members );
		}
		else if( requestType.equals( "DeleteMembers" ))
		{
			result = remove( members );
		}
		else if( requestType.equals( "FetchMembers" ))
		{
			long watchListId  = Integer.parseInt( request.getParameter( "WatchListId" ) );
			
			result = get( watchListId,members );
		}
		else
		{
			result = 10302;  // can't find appropriate request type
		}
	    
	    return result;
    }
	
	/*
	 * Method : add
	 * 
	 * Input  : List of watch list member object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: This method is used to add the members to already created watchlist.
	 */
	
	public int  add( List<WLMemberData> wlMembers )
    {
	    
		WLMember wlMember = new WLMember( );
		
		int result  = wlMember.add( wlMembers );
		
		wlMember = null;
		
		return result;
    }
	
	/*
	 * Method : remove
	 * 
	 * Input  : list of watch list member object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: This method is used to delete the members from already created watchlist.
	 */
	
	public int remove( List<WLMemberData> wlMembers )
    {
	    WLMember wlMember = new WLMember( );
	    
	    int result = wlMember.remove( wlMembers );
	    
	    wlMember = null;
	    
	    return result;
    }

	/*
	 * Method : get
	 * 
	 * Input  : watchlist id,list of watch list member object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: Get all the members details for given watchlist. Members details copied to members list,
	 * so it is available in caller classes
	 */
	
	public int get( long watchListId, List<WLMemberData> members )
    {
	    WLMember wlMember = new WLMember( );
	    
	    int result = wlMember.get( watchListId, members );
	    
	    wlMember = null;
	    
	    return result;
    }

}
