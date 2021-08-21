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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import core.trans.TransReject;
import core.trans.TransRejectData;

/**
 * File:  TransRejectController.java 
 *
 * Created on Jul 10, 2013 12:04:41 PM
 */

public class TransRejectController
{
	/*
	 * Method : TransRejectController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransRejectController()
	{
		
	}
	
	/*
	 * Method : processRequest 
	 * 
	 * Input  : HTTPServletRequest and List of TransRejectData
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get the HttpServletRequest object from servlet and 
	 * convert to TransData object using TransRejectDataConverter class. Then do the 
	 * corresponding operation according to the RequestType
	 */
	
	public int processRequest( HttpServletRequest request, List<TransRejectData> rejectDataList )
	{
		
		// Convert the HttpServletRequest object to TransRejectData object 
		
		TransRejectDataConverter converter = new TransRejectDataConverter( );
		
		TransRejectData transRejectData = new TransRejectData( );
		
		converter.convert( request, transRejectData );
		
		converter = null;
		
		int result = 0;
		
		result = reject( transRejectData );
		
		return result;
	}
	
	
	/*
	 * Method : set
	 * 
	 * Input  : TransRejectData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to add the new Reject details.
	 */
	
	private int reject( TransRejectData transRejectData )
	{
		TransReject transReject = new TransReject( );
		
		int result = transReject.reject( transRejectData );
		
		transRejectData = null;
		
		return result;
	}
	
}
