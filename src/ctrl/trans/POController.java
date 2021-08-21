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
import core.trans.POData;
import core.trans.PurchaseOrder;

/**
 * File:  POController.java 
 *
 * Created on 20-Jun-2013 3:01:55 PM
 */
public class POController
{
	ErrorLogger errorLogger_;
	
	/*
	 * Method : POController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public POController()
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : processRequest 
	 * 
	 * Input  : HTTPServletRequest and List of POData
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get the HttpServletRequest object from servlet and 
	 * convert to POData object using PODataConverter class. Then do the 
	 * corresponding operation according to the RequestType
	 */
	
	public int processRequest( HttpServletRequest request, List<POData> poList )
	{	
		int result = 0;
		
		try
        {
			// Convert the HttpServletRequest object to POData object 
			
			POData poData = new POData( );
			
			PODataConverter poDataConverter = new PODataConverter( );
			
			poDataConverter.convert( request, poData );
			
			System.out.println("items count ="+poData.poItems_.size( ));
			
			poDataConverter = null;
			
			String requestType = request.getParameter( "RequestType" );
			
			// if RequestType is equal to 'POCreation'  call 'add' private method
			
			System.out.println("request type"+requestType );
			System.out.println("line 1" );
			if( requestType.equals( "POCreation" ) )
				result = add( poData );
			
			// if RequestType is equal to 'FetchPO'  call 'get' private method
			
			System.out.println( "request type"+requestType);
			System.out.println("line 2" );
			if( requestType.equals( "FetchPO" ) )
				result = get( poData, poList );
			
			
			// if RequestType is equal to 'UpdatePO'  call 'update' private method
			System.out.println( "request type"+requestType);
			System.out.println("line 3" );
			if( requestType.equals( "UpdatePO" ) )
				result = update( poData );
			
			// if RequestType is equal to 'ChangeStatus'  call 'changeStatus' private method
			System.out.println( "request type"+requestType);
			System.out.println("line 4" );
			if( requestType.equals( "ChangeStatus" ) )
				result = changeStatus( poData );
			
        }
        catch( Exception e )
        {
        	errorLogger_.logMsg( "Exception::POController.processRequest() - " + e );
        }
		
		System.gc( );
		
		return result;
	}
	
	/*
	 * Method : add
	 * 
	 * Input  : POData object
	 * 
	 * Return : int value (This indicate whether PO created successful or not)
	 * 
	 * Purpose: This is method is used to created the new PO in supply medium.
	 * 
	 */
	
	private int add( POData poData)
	{
		PurchaseOrder po = new PurchaseOrder( );
		
		int result = po.add( poData );
		
		po = null;
		
		return result;
	}
	
	
	/*
	 * Method : get
	 * 
	 * Input  : regnKey, userKey and list of POData object
	 * 
	 * Return : int value (This indicate whether we get all PO successfully or not)
	 * 
	 * Purpose: This is used to fetch the all the pending PO transaction for the 
	 * particular user of the company. 
	 */
	
	private int get( POData poData, List<POData> poList)
	{
		PurchaseOrder po = new PurchaseOrder( );
		
		int result = po.get( poData.from_, poData.userFrom_, poList );
		
		po = null;
		
		return result;
	}
	
	
	/*
	 * Method : update
	 * 
	 * Input  : POData object
	 * 
	 * Return : int 
	 * 
	 * Purpose: It is used to update the already created PO's
	 */
	private int update( POData poData )
	{
		PurchaseOrder po = new PurchaseOrder( );
		
		int result = po.update( poData );
		
		po = null;
		
		return result;
	}
	
	
	/*
	 * Method : changeStatus
	 * 
	 * Input  : POData
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to change the status of the PO. 
	 * 
	 */
	private int changeStatus( POData poData )
	{
		PurchaseOrder po = new PurchaseOrder( );
		
		int result = po.changeStatus( poData );
		
		po = null;
		
		return result;
	}

}
