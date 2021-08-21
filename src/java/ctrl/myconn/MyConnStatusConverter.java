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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.myconn.MyConnectionData;
import utils.ErrorMaster;


/**
 * File:  MyConnectionStatusConverter.java 
 *
 * Created on 19-Aug-2013 4:50:59 PM
 */
public class MyConnStatusConverter
{

	ErrorLogger errorLogger_;
	
	/*
	 * Method : MyConnStatusConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnStatusConverter()
	{
		errorLogger_ = ErrorLogger.instance( );
	
	}
	
	/*
	 * Method : convert 
	 * 
	 * Input  : HTTPServletRequest and RFQData object
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to convert the HttpServletRequest object
	 * to RFQData object. And assign to rfqData parameter so it is copied in
	 * caller classes.
	 */
	
	public int convert( HttpServletRequest request, MyConnectionData myConnData )
	{
		int result = 0;
		
		try
        {
            ErrorMaster errorMaster_ = null;
	if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
			Map<String, String [ ]> reqMap = request.getParameterMap( );

			/* User connection sender details */
			
			if( reqMap.containsKey( "FromUserName" ) )
			{
				myConnData.fromUserName_ = request.getParameter( "FromUserName" );
			}
			
			if( reqMap.containsKey( "FromUserKey" ) )
			{
				myConnData.fromUserKey_.email_ = request.getParameter( "FromUserKey" );
				errorMaster_.insert("from user Key ="+myConnData.fromUserKey_.email_ );
			}
			
			if( reqMap.containsKey( "FromCompName" ) )
			{
				myConnData.fromCompName_ = request.getParameter( "FromCompName" );
			}
			if( reqMap.containsKey( "FromRegnKey" ) )
			{
				myConnData.fromRegnKey_.companyPhoneNo_ = request.getParameter( "FromRegnKey" );
			}
			
			/* User connection receiver details */
			
			if( reqMap.containsKey( "ToUserName" ) )
			{
				myConnData.toUserName_ = request.getParameter( "ToUserName" );
			}
			
			if( reqMap.containsKey( "ToUserKey" ) )
			{
				myConnData.toUserKey_.email_ = request.getParameter( "ToUserKey" );
				errorMaster_.insert( "To userKey="+myConnData.toUserKey_.email_);
			}
			
			if( reqMap.containsKey( "ToCompName" ) )
			{
				myConnData.toCompName_ = request.getParameter( "ToCompName" );
			}
			if( reqMap.containsKey( "ToRegnKey" ) )
			{
				myConnData.toRegnKey_.companyPhoneNo_ = request.getParameter( "ToRegnKey" );
			}
		
        }
		catch( Exception e )
	    {
			errorLogger_.logMsg( "Exception::MyConnStatusConverter.convert() - " + e );
	        	
	        return -1;
	    }
		
		return result;
	}

}
