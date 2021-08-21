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

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.myconn.MyConnProfile;
import core.myconn.MyConnProfileData;
import core.regn.UserProfileKey;

/**
 * File:  MyConnProfileController.java 
 *
 * Created on 21-Aug-2013 2:43:21 PM
 */
public class MyConnProfileController
{

	/*
	 * Method : MyConnProfileController.java() -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnProfileController()
	{
	}
	
	/*
	 * Method : processRequest
	 * 
	 * Input  : HttpServletRequest object, MyConnProfileData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the connection profile details. Before that it get the
	 * request data from request object.
	 */
	 
	public int processRequest( HttpServletRequest request, MyConnProfileData myConnProfileData )
	{
		try
        {
	        UserProfileKey userKey = new UserProfileKey( );
	        
	        userKey.email_ = request.getParameter( "UserKey" );
	        
	        int result = get( userKey, myConnProfileData );
	        
	        return result;
	        
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
        	
			String errorMessage = "Exception :: MyConnProfile.convert( userProfileData, myConnProfileData ) - "
			        + e;

			errLogger_.logMsg( errorMessage );

			return -1; 
        }
	}
	
	/*
	 * Method : get
	 * 
	 * Input  : UserProfileKey object, MyConnProfileData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the profile details for given profile key.
	 * Copied to MyConnProfile object then only caller classes can access the 
	 * myconnprofile object.
	 * 
	 */
	
	public int get( UserProfileKey userKey, MyConnProfileData connProfileData )
	{
		MyConnProfile myConnProfile = new MyConnProfile( );
		
		int result = myConnProfile.get( userKey, connProfileData );
		
		myConnProfile = null;
		
		return result;
		
	}

}
