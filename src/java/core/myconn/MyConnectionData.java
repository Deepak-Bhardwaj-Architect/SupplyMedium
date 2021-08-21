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

import java.sql.Timestamp;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File:  MyConnectionData.java 
 *
 * Created on 13-Aug-2013 1:43:19 PM
 */
public class MyConnectionData
{
	// Name of the person who sent the connection request
	public String fromUserName_;
	
	// Name of the user's( who sent the connection ) company
	public String fromCompName_;
	
	// Profile key of the user who sent the connection request
	public UserProfileKey fromUserKey_;
	
	// Company key of the user who sent the connection request
	public CompanyRegnKey fromRegnKey_;
	
	// Name of the person who receive the connection request
	public String toUserName_;
		
	// Name of the user's( who receive the connection ) company
	public String toCompName_;
		
	// Profile key of the user who receive the connection request
	public UserProfileKey toUserKey_;
		
	// Company key of the user who receive the connection request
	public CompanyRegnKey toRegnKey_;
	
	// path of the user profile image who receive the connection request
	public String userProfileImagePath_;
	
	// Status of the connection it may be "Request Sent" or "Accepted"
	public String status_;
        
        public String typ_;
	
	public Timestamp createdTimestamp_;
        
        private static ErrorMaster errorMaster_ = null;


	
	
	

	/*
	 * Method : MyConnection -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnectionData()
	{
		this.fromUserKey_ = new UserProfileKey( );
		
		this.fromRegnKey_ = new CompanyRegnKey( );
		
		this.toUserKey_   = new UserProfileKey( );
		
		this.toRegnKey_   = new CompanyRegnKey( );
                
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
		
		errorMaster_.insert( "fromUserName_ 	="+ fromUserName_ );
		
		errorMaster_.insert( "fromCompName_	="+ fromCompName_);
		
		errorMaster_.insert( "fromUserKey_	="+ fromUserKey_.toString( ));
		
		errorMaster_.insert( "fromRegnKey_	="+ fromRegnKey_.toString( ));
		
		
		
		errorMaster_.insert( "toUserName_ 	="+ toUserName_ );
		
		errorMaster_.insert( "toCompName_	="+ toCompName_);
		
		errorMaster_.insert( "toUserKey_		="+ toUserKey_.toString( ));
		
		errorMaster_.insert( "toRegnKey_		="+ toRegnKey_.toString( ));
		
		errorMaster_.insert( "Image path     ="+ userProfileImagePath_ );
		
		
		errorMaster_.insert( "status_		="+ status_);
		
		errorMaster_.insert( "timeStamp      ="+ createdTimestamp_);
		
		
		
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
		fromUserName_ 	= null;
		fromCompName_  	= null;
		fromUserKey_   	= null;
		fromRegnKey_   	= null;
		
		toUserName_    	= null;
		toCompName_    	= null;
		toUserKey_     	= null;
		toRegnKey_     	= null;
		
		status_= null;
                typ_=null;
                
		createdTimestamp_ = null;
		userProfileImagePath_ = null;
	}

}
