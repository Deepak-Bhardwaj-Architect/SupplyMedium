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
package ctrl.reco;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.reco.RecoEngine;
import core.regn.CompanyRegnKey;
import core.regn.RegnData;

/**
 * File: RecoController.java
 * 
 * Created on May 6, 2013 3:24:11 PM
 */

/*
 * Class: RecoController
 * 
 * Purpose: This class is used to control the operations to enlist buyers and
 * suppliers inorder to recommend it for the user.
 * 
 * The following are the Request Types for which the operations are performed
 * 
 * 1. GetVendors - To get buyers (count is 4) if user is supplier or to get
 * suppliers (count is 4) if user is buyer or to get both buyers and suppliers
 * if user is from 'Both' category
 */

public class RecoController
{

	/*
	 * Method: getVendors
	 * 
	 * Input: HttpServletRequest requestObject, List<RegnData> regnDataList (As
	 * ref)
	 * 
	 * Return: int
	 * 
	 * Purpose: This method gets the registered vendors (buyers / suppliers )
	 */

	public int getVendors( HttpServletRequest request,
	        List<RegnData> regnDataList )
	{
		RecoEngine recoEngine = new RecoEngine( );

		CompanyRegnKey regnKey = new CompanyRegnKey( );

		regnKey.companyPhoneNo_ = request.getParameter( "RegnKey" );

		ErrorLogger errorLogger = ErrorLogger.instance( );

		errorLogger
		        .logMsg( "Info::DeptPageController.getAllFeeds() - GetVendors Request in process" );

		return recoEngine.getVendors( regnKey, regnDataList );

	}

}
