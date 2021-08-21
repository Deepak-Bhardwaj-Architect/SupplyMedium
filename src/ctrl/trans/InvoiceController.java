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
import core.trans.Invoice;
import core.trans.InvoiceData;

/**
 * File:  InvoiceController.java 
 *
 * Created on 20-Jun-2013 3:02:09 PM
 */
public class InvoiceController
{

	ErrorLogger errorLogger_;
	
	/*
	 * Method : InvoiceController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public InvoiceController()
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : processRequest 
	 * 
	 * Input  : HTTPServletRequest and List of InvoiceData
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get the HttpServletRequest object from servlet and 
	 * convert to InvoiceData object using InvoiceDataConverter class. Then do the 
	 * corresponding operation according to the RequestType
	 */
	
	public int processRequest( HttpServletRequest request, List<InvoiceData> invoiceList )
	{	
		int result = 0;
		
		try
        {
			// Convert the HttpServletRequest object to InvoiceData object 
			
			InvoiceData invoiceData = new InvoiceData( );
			
			InvoiceDataConverter invoiceDataConverter = new InvoiceDataConverter( );
			
			invoiceDataConverter.convert( request, invoiceData );
			
			invoiceDataConverter = null;
			
			String requestType = request.getParameter( "RequestType" );
			
			// if RequestType is equal to 'InvoiceCreation'  call 'add' private method
			
			if( requestType.equals( "InvoiceCreation" ) )
				result = add( invoiceData );
			
			// if RequestType is equal to 'FetchInvoice'  call 'get' private method
			
			if( requestType.equals( "FetchInvoice" ) )
				result = get( invoiceData, invoiceList );
			
			
			// if RequestType is equal to 'UpdateInvoice'  call 'update' private method
			
			if( requestType.equals( "UpdateInvoice" ) )
				result = update( invoiceData );
			
			// if RequestType is equal to 'ChangeStatus'  call 'changeStatus' private method
			
			if( requestType.equals( "ChangeStatus" ) )
				result = changeStatus( invoiceData );
			
        }
        catch( Exception e )
        {
        	errorLogger_.logMsg( "Exception::InvoiceController.processRequest() - " + e );
        }
		
		System.gc( );
		
		return result;
	}
	
	/*
	 * Method : add
	 * 
	 * Input  : InvoiceData object
	 * 
	 * Return : int value (This indicate whether Invoice created successful or not)
	 * 
	 * Purpose: This is method is used to created the new Invoice in supply medium.
	 * 
	 */
	
	private int add( InvoiceData invoiceData)
	{
		Invoice invoice = new Invoice( );
		
		int result = invoice.add( invoiceData );
		
		invoice = null;
		
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
	
	private int get( InvoiceData invoiceData, List<InvoiceData> invoiceList)
	{
		Invoice invoice = new Invoice( );
		
		int result = invoice.get( invoiceData.from_, invoiceData.userFrom_, invoiceList );
		
		invoice = null;
		
		return result;
	}
	
	
	/*
	 * Method : update
	 * 
	 * Input  : InvoiceData object
	 * 
	 * Return : int 
	 * 
	 * Purpose: It is used to update the already created Invoice's
	 */
	private int update( InvoiceData invoiceData )
	{
		Invoice invoice = new Invoice( );
		
		int result = invoice.update( invoiceData );
		
		invoice = null;
		
		return result;
	}
	
	
	/*
	 * Method : changeStatus
	 * 
	 * Input  : InvoiceData
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to change the status of the Invoice. 
	 * 
	 */
	private int changeStatus( InvoiceData invoiceData )
	{
		Invoice invoice = new Invoice( );
		
		int result = invoice.changeStatus( invoiceData );
		
		invoice = null;
		
		return result;
	}

}
