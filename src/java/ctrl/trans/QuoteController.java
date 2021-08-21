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
import core.trans.Quote;
import core.trans.QuoteData;

/**
 * File:  QuoteController.java 
 *
 * Created on 20-Jun-2013 3:01:39 PM
 */
public class QuoteController
{

	ErrorLogger errorLogger_;
	
	/*
	 * Method : QuoteController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public QuoteController()
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : processRequest 
	 * 
	 * Input  : HTTPServletRequest and List of QuoteData
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get the HttpServletRequest object from servlet and 
	 * convert to QuoteData object using QuoteDataConverter class. Then do the 
	 * corresponding operation according to the RequestType
	 */
	
	public int processRequest( HttpServletRequest request, List<QuoteData> quoteList )
	{	
		int result = 0;
		
		try
        {
			
			// Convert the HttpServletRequest object to QuoteData object 
			
			QuoteData quoteData = new QuoteData( );
			
			QuoteDataConverter quoteDataConverter = new QuoteDataConverter( );
			
			quoteDataConverter.convert( request, quoteData );
			
			quoteDataConverter = null;
			
			String requestType = request.getParameter( "RequestType" );
			
			// if RequestType is equal to 'QuoteCreation'  call 'add' private method
			
			if( requestType.equals( "QuoteCreation" ) )
				result = add( quoteData );
			
			// if RequestType is equal to 'FetchQuote'  call 'get' private method
			
			if( requestType.equals( "FetchQuote" ) )
				result = get( quoteData, quoteList );
			
			
			// if RequestType is equal to 'UpdateQuote'  call 'update' private method
			
			if( requestType.equals( "UpdateQuote" ) )
				result = update( quoteData );
			
			// if RequestType is equal to 'ChangeStatus'  call 'changeStatus' private method
			
			if( requestType.equals( "ChangeStatus" ))
				result = changeStatus( quoteData );
			
        }
        catch( Exception e )
        {
        	errorLogger_.logMsg( "Exception::QuoteController.processRequest() - " + e );
        }
		
		System.gc( );
		
		return result;
	}
	
	/*
	 * Method : add
	 * 
	 * Input  : QuoteData object
	 * 
	 * Return : int value (This indicate whether Quote created successful or not)
	 * 
	 * Purpose: This is method is used to created the new Quote in supply medium.
	 * 
	 */
	
	private int add( QuoteData quoteData)
	{
		Quote quote = new Quote( );
		
		int result = quote.add( quoteData );
		
		quote = null;
		
		return result;
	}
	
	
	/*
	 * Method : get
	 * 
	 * Input  : regnKey, userKey and list of QuoteData object
	 * 
	 * Return : int value (This indicate whether we get all Quote successfully or not)
	 * 
	 * Purpose: This is used to fetch the all the pending Quote transaction for the 
	 * particular user of the company. 
	 */
	
	private int get( QuoteData quoteData, List<QuoteData> quoteList)
	{
		Quote quote = new Quote( );
		
		int result = quote.get( quoteData.from_, quoteData.userFrom_, quoteList );
		
		quote = null;
		
		return result;
	}
	
	
	/*
	 * Method : update
	 * 
	 * Input  : QuoteData object
	 * 
	 * Return : int 
	 * 
	 * Purpose: It is used to update the already created Quote's
	 */
	private int update( QuoteData quoteData )
	{
		Quote quote = new Quote( );
		
		int result = quote.update( quoteData );
		
		quote = null;
		
		return result;
	}
	
	
	/*
	 * Method : changeStatus
	 * 
	 * Input  : QuoteData
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to change the status of the Quote. 
	 * 
	 */
	private int changeStatus( QuoteData quoteData )
	{
		Quote quote = new Quote( );
		
		int result = quote.changeStatus( quoteData );
		
		quote = null;
		
		return result;
	}
}
