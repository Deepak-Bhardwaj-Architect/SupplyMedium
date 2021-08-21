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
package core.newsroom;

import java.util.List;

import core.regn.RegnData;
import core.regn.UserProfileData;

import db.newsroom.WLMemberMapperTable;
import db.regn.CompanyRegnTable;
import db.regn.UserProfileTable;

/**
 * File:  WLMember.java 
 *
 * Created on 30-Aug-2013 5:14:19 PM
 */
public class WLMember
{
	

	/*
	 * Method : WLMember -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WLMember()
	{
		
		
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
	    
		WLMemberMapperTable wlMemberMapperTbl = new WLMemberMapperTable( );
		
		int result = wlMemberMapperTbl.insert( wlMembers );
		
		wlMemberMapperTbl = null;
		
		if( result == 0 )
			return 10130; // inserted successfully completed.
		else 
			return 10134; // Insertion failed because of unexpected error
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
		WLMemberMapperTable wlMemberMapperTbl = new WLMemberMapperTable( );
		
		int result = wlMemberMapperTbl.delete( wlMembers );
		
		wlMemberMapperTbl = null;
		
		if( result == 0 )
			return 10140; // deleted successfully completed.
		else 
			return 10141; // deletion failed because of unexpected error
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
		WLMemberData wlMemberData = new WLMemberData( );
		
		wlMemberData.watchListId_ = watchListId;
		
		WLMemberMapperTable wlMemberMapperTbl = new WLMemberMapperTable( );
		
		int result = wlMemberMapperTbl.find( wlMemberData, members );
		
		wlMemberMapperTbl = null;
		
		if( result != 0 )
			return 10151; // fetch all member failed			
		
		for( WLMemberData member : members )
        {
			// Get the user profile details from user_profile table
			
			UserProfileData userProfileData = new UserProfileData( );
			
			UserProfileTable profileTbl = new UserProfileTable( );
			
			result = profileTbl.getUserProfile( member.memberKey_, userProfileData );
			
			if( result != 0 )
				continue;
			
			member.memberName_ = userProfileData.firstName_+" "+userProfileData.lastName_;
			
			member.photoPath_ = userProfileData.profilePicture_;
			
			// Gte the user's company details from company_registration table
			
			RegnData regnData = new RegnData( );
			
			CompanyRegnTable regnTbl = new CompanyRegnTable( );
			
			result = regnTbl.find( userProfileData.companyRegnKey_, regnData );
			 
			regnTbl = null;
			
			if( result != 0 )
				continue;
			
			member.companyName_ = regnData.companyName_;
			
			member.regnKey_ 	= userProfileData.companyRegnKey_;
			
        }
		
		return 10150; // fetch all the members success
		
		
    }
}
