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

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File:  WLMemberData.java 
 *
 * Created on 30-Aug-2013 5:13:01 PM
 */
public class WLMemberData
{

	// Id of the watchlist
		public long watchListId_;
		
		// Member user profile key
		public UserProfileKey memberKey_;
		
		// Member company name
		public String companyName_;
		
		// Member company regn key
		public CompanyRegnKey regnKey_;
		
		// Name of the member. Contain first and last name
		public String memberName_;
		
		// Member profile picture path
		public String photoPath_;
                
                private static ErrorMaster errorMaster_ = null;



		/*
		 * Method : WLMember -- constructor
		 * 
		 * Input  : None
		 * 
		 * Return : None
		 * 
		 * Purpose:
		 */
		public WLMemberData()
		{
			
			watchListId_  = -1;
			
			memberKey_ = new UserProfileKey( );
			
			regnKey_   = new CompanyRegnKey( );
                        
                        if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		}
		
		/*
		 * Method : show( ) 
		 * 
		 * Input : None 
		 * 
		 * Return : None
		 * 
		 * Purpose: It is used to print the all class variable values in console
		 */

		public void show( )
		{
			errorMaster_.insert( "watchListId   	= "+ watchListId_ );
			
			errorMaster_.insert( "memberKey_   	= "+ memberKey_.toString( ) );
			
			errorMaster_.insert( "companyName_  	= "+ companyName_ );
			
			errorMaster_.insert( "regnKey_  		= "+ regnKey_ );
			
			errorMaster_.insert( "memberName_   	= "+ memberName_ );
			
			errorMaster_.insert( "photoPath_     = "+photoPath_);
		}
		
		/*
		 * Method : clear( ) 
		 * 
		 * Input : None 
		 * 
		 * Return : None
		 * 
		 * Purpose: To reset the class variables.
		 */
		
		public void clear( )
		{
			watchListId_	= -1;
			
			memberKey_   	= null;
			
			companyName_ 	= null;
			
			regnKey_ 		= null;
			
			memberName_ 	= null;
			
			photoPath_		= null;
		}
}
