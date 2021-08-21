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
import core.trans.TransRejectData;

/**
 * File:  TransRejectConverter.java 
 *
 * Created on Jul 10, 2013 12:04:52 PM
 */
public class TransRejectDataConverter
{
	/*
	 * Method : TransRejectConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransRejectDataConverter()
	{
	}
	
	/*
	 * Method : convert 
	 * 
	 * Input  : HTTPServletRequest and TransRejectData object
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to convert the HttpServletRequest object
	 * to TransRejectData object. And assign to transRejectData parameter so it is copied in
	 * caller classes.
	 */
	
	public int convert( HttpServletRequest request, TransRejectData transRejectData )
	{
		ErrorLogger errorLogger_ = ErrorLogger.instance( );
	
		try
        {
			// Setting from company regn key
			
			String fromRegnKeyStr = request.getParameter( "FromRegnKey" );
			
			CompanyRegnKey fromRegnKey = new CompanyRegnKey( );
			
			fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;
			
			transRejectData.from_ = fromRegnKey;
			
			fromRegnKey = null;
			
			
			
			// Setting from user key
			
			String fromUserKeyStr = request.getParameter( "FromUserKey" );
			
			UserProfileKey fromUserKey = new UserProfileKey( );
			
			fromUserKey.email_ = fromUserKeyStr;
			
			transRejectData.userFrom_ = fromUserKey;
			
			fromUserKey = null;
			
			// Setting  to regn key
			
			String toRegnKeyStr = request.getParameter( "ToRegnKey" );
			
			CompanyRegnKey toRegnKey = new CompanyRegnKey( );
			
			toRegnKey.companyPhoneNo_ = toRegnKeyStr;
			
			transRejectData.to_ = toRegnKey;
			
			fromRegnKey = null;
			
			
			
			// Setting to user key
			
			String toUserKeyStr = request.getParameter( "ToUserKey" );
			
			UserProfileKey toUserKey = new UserProfileKey( );
			
			toUserKey.email_ = toUserKeyStr;
			
			transRejectData.userTo_ = toUserKey;
			
			fromUserKey = null;
			
			
			transRejectData.transId_ 		=  Integer.parseInt( request.getParameter( "TransId" ));
			
			transRejectData.transType_ 		=  request.getParameter( "TransType" );
			
			transRejectData.transTypeId_ 	=  Integer.parseInt( request.getParameter( "TransTypeId" ));
			
			transRejectData.rejectReason_	=  request.getParameter( "RejectReason" );
			
			transRejectData.status_			= request.getParameter( "Status" );
			
			transRejectData.action_			= request.getParameter( "Action" );
			
			return 0;
			
			
        }
        catch( Exception e )
        {
        	errorLogger_.logMsg( "Exception::TransRejectDataConverter.convert() - " + e );
        	
        	return -1;
        }
		
		
	}
}
