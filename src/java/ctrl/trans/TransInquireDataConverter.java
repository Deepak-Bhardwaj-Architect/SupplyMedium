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
package ctrl.trans;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.trans.TransInquireData;

/**
 * File:  TransInquireDataConverter.java 
 *
 * Created on 21-Jun-2013 3:01:15 PM
 */
public class TransInquireDataConverter
{

	/*
	 * Method : TransInquireDataConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransInquireDataConverter()
	{
	}
	
	/*
	 * Method : convert 
	 * 
	 * Input  : HTTPServletRequest and TransInquireData object
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to convert the HttpServletRequest object
	 * to TransInquireData object. And assign to transInquireData parameter so it is copied in
	 * caller classes.
	 */
	
	public int convert( HttpServletRequest request, TransInquireData transInquireData )
	{
		ErrorLogger errorLogger_ = ErrorLogger.instance( );
	
		try
        {
			// Setting from company regn key
			
			String fromRegnKeyStr = request.getParameter( "FromRegnKey" );
			
			CompanyRegnKey fromRegnKey = new CompanyRegnKey( );
			
			fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;
			
			transInquireData.from_ = fromRegnKey;
			
			fromRegnKey = null;
			
			
			
			// Setting from user key
			
			String fromUserKeyStr = request.getParameter( "FromUserKey" );
			
			UserProfileKey fromUserKey = new UserProfileKey( );
			
			fromUserKey.email_ = fromUserKeyStr;
			
			transInquireData.userFrom_ = fromUserKey;
			
			fromUserKey = null;
			
			// Setting  to regn key
			
			String toRegnKeyStr = request.getParameter( "ToRegnKey" );
			
			CompanyRegnKey toRegnKey = new CompanyRegnKey( );
			
			toRegnKey.companyPhoneNo_ = toRegnKeyStr;
			
			transInquireData.to_ = toRegnKey;
			
			fromRegnKey = null;
			
			
			
			// Setting to user key
			
			String toUserKeyStr = request.getParameter( "ToUserKey" );
			
			UserProfileKey toUserKey = new UserProfileKey( );
			
			toUserKey.email_ = toUserKeyStr;
			
			transInquireData.userTo_ = toUserKey;
			
			fromUserKey = null;
			
			
			transInquireData.transId_ 		=  Integer.parseInt( request.getParameter( "TransId" ));
			
			transInquireData.transType_ 	=  request.getParameter( "TransType" );
			
			transInquireData.transTypeId_ 	=  Integer.parseInt( request.getParameter( "TransTypeId" ));
			
			transInquireData.details_ 		=  request.getParameter( "Details" );
			
			transInquireData.status_		= request.getParameter( "Status" );
			
			transInquireData.action_		= request.getParameter( "Action" );
			
			return 0;
			
			
        }
        catch( Exception e )
        {
        	errorLogger_.logMsg( "Exception::TransInquireDataConverter.convert() - " + e );
        	
        	return -1;
        }
		
		
	}

}
