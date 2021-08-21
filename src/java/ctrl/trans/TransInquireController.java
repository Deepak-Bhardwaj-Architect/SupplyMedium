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

import core.trans.TransInquire;
import core.trans.TransInquireData;

/**
 * File:  TransInquireController.java 
 *
 * Created on 20-Jun-2013 3:02:35 PM
 */
public class TransInquireController
{

	/*
	 * Method : TransInquireController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransInquireController()
	{
		
	}
	
	/*
	 * Method : processRequest 
	 * 
	 * Input  : HTTPServletRequest and List of TransInquireData
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get the HttpServletRequest object from servlet and 
	 * convert to TransData object using RFQDataConverter class. Then do the 
	 * corresponding operation according to the RequestType
	 */
	
	public int processRequest( HttpServletRequest request, List<TransInquireData> inquireDataList )
	{
		
		// Convert the HttpServletRequest object to TransInquireData object 
		
		TransInquireDataConverter converter = new TransInquireDataConverter( );
		
		TransInquireData transInquireData = new TransInquireData( );
		
		converter.convert( request, transInquireData );
		
		converter = null;
		
		String requestType = request.getParameter( "RequestType" );
		
		int result = 0;
		
		// if RequestType is equal to 'AddInquire'  call 'add' private method
		
		if( requestType.equals( "AddInquire" ))
			result = add( transInquireData );
		
		// if RequestType is equal to 'FetchInquire'  call 'get' private method
		
		else if( requestType.equals( "FetchInquire" )  )
		{
			long transTypeId = Long.parseLong( request.getParameter( "TransTypeId" ) );
			
			result = get( transTypeId, inquireDataList );
		}
			
		System.gc( );
		
		return result;
	}
	
	
	/*
	 * Method : set
	 * 
	 * Input  : TransInquireData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to add the new Inquire details.
	 */
	
	private int add( TransInquireData transInquireData )
	{
		TransInquire transInquire = new TransInquire( );
		
		int result = transInquire.add( transInquireData );
		
		transInquire = null;
		
		return result;
	}
	
	
	/*
	 * Method : get
	 * 
	 * Input  : transTypeId and inquireDataList( TransTypeId should be
	 * 			RFQId or QuoteId or POId or InvoiceId )
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get all the Inquire details for the given
	 * transTypeId and assign to inquireDataList parameter so it is copied to caller
	 * classes.
	 */
	private int get( long transTypeId, List<TransInquireData> inquireDataList  )
	{
		return 0;
	}

}
