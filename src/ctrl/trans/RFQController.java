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

import utils.ErrorLogger;


import core.trans.RFQ;
import core.trans.RFQData;

/**
 * File:  RFQController.java 
 *
 * Created on 20-Jun-2013 3:00:19 PM
 */
public class RFQController
{
	ErrorLogger errorLogger_;

	/*
	 * Method : RFQController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public RFQController()
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : processRequest 
	 * 
	 * Input  : HTTPServletRequest and List of RFQData
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get the HttpServletRequest object from servlet and 
	 * convert to RFQData object using RFQDataConverter class. Then do the 
	 * corresponding operation according to the RequestType
	 */
	
	public int processRequest( HttpServletRequest request, List<RFQData> RFQList )
	{	
		int result = 0;
		
		try
        {
			
			// Convert the HttpServletRequest object to RFQData object 
			
			RFQData rfqData = new RFQData( );
			
			RFQDataConverter rfqDataConverter = new RFQDataConverter( );
			
			rfqDataConverter.convert( request, rfqData );
			
			rfqDataConverter = null;
			
			String requestType = request.getParameter( "RequestType" );
						
			// if RequestType is equal to 'RFQCreation'  call 'add' private method
				
				if( requestType.equals( "RFQCreation" ))
				result = add( rfqData );
			
			// if RequestType is equal to 'FetchRFQ'  call 'get' private method
			
				if( requestType.equals( "FetchRFQ" ))
				result = get( rfqData, RFQList );
			
			
			// if RequestType is equal to 'UpdateRFQ'  call 'update' private method
			
				if( requestType.equals( "UpdateRFQ" ))
				result = update( rfqData );
			
			// if RequestType is equal to 'ChangeStatus'  call 'changeStatus' private method
			
				if( requestType.equals( "ChangeStatus" ))
				result = changeStatus( rfqData );
			
        }
        catch( Exception e )
        {
        	errorLogger_.logMsg( "Exception::RFQController.processRequest() - " + e );
        }
		
		System.gc( );
		
		return result;
	}
	
	/*
	 * Method : add
	 * 
	 * Input  : RFQData object
	 * 
	 * Return : int value (This indicate whether RFQ created successful or not)
	 * 
	 * Purpose: This is method is used to created the new RFQ in supply medium.
	 * 
	 */
	
	private int add( RFQData rfqData)
	{
		RFQ rfq = new RFQ( );
		
		int result = rfq.add( rfqData );
		
		rfq = null;
		
		return result;
	}
	
	
	/*
	 * Method : get
	 * 
	 * Input  : regnKey, userKey and list of RFQData object
	 * 
	 * Return : int value (This indicate whether we get all RFQ successfully or not)
	 * 
	 * Purpose: This is used to fetch the all the pending RFQ transaction for the 
	 * particular user of the company. 
	 */
	
	private int get( RFQData rfqData, List<RFQData> RFQList)
	{
		RFQ rfq = new RFQ( );
		
		int result = rfq.get( rfqData.from_, rfqData.userFrom_, RFQList );
		
		rfq = null;
		
		return result;
	}
	
	
	/*
	 * Method : update
	 * 
	 * Input  : RFQData object
	 * 
	 * Return : int 
	 * 
	 * Purpose: It is used to update the already created RFQ's
	 */
	private int update( RFQData rfqData )
	{
		RFQ rfq = new RFQ( );
		
		int result = rfq.update( rfqData );
		
		rfq = null;
		
		return result;
	}
	
	
	/*
	 * Method : changeStatus
	 * 
	 * Input  : RFQData
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to change the status of the RFQ. 
	 * 
	 */
	private int changeStatus( RFQData rfqData )
	{
		System.out.println( "rfq change status controller start");
		RFQ rfq = new RFQ( );
		
		int result = rfq.changeStatus( rfqData );
		
		rfq = null;
		System.out.println("rfq change status controller End" );
		return result;
	}

}
