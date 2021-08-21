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
package core.trans;

import java.util.List;

import core.regn.UserProfileKey;

import db.trans.InvoiceTable;
import db.trans.POTable;
import db.trans.QuoteTable;
import db.trans.RFQTable;
import db.trans.TransInquireTable;
import db.trans.TransTable;

import utils.ErrorLogger;
import utils.ErrorMaster;
import utils.TransType;

/**
 * File:  TransInquire.java 
 *
 * Created on 21-Jun-2013 2:43:29 PM
 */
public class TransInquire
{

	ErrorLogger errLogger_;
        private static ErrorMaster errorMaster_ = null;


	
	/*
	 * Method : TransInquire -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public TransInquire()
	{
		errLogger_ = ErrorLogger.instance( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	
	/*
	 * Method : set
	 * 
	 * Input  : TransInquireData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to add the new Inquire details.
	 * 1. Insert one record in TransInquireTable
	 * 2. Insert one record in TransTable
	 */
	
	public int add( TransInquireData transInquireData )
	{
		TransInquireTable inquireTable = new TransInquireTable( );
		
		int result = inquireTable.insert( transInquireData );
		
		inquireTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::TransInquire.add() - Unable to insert into Transaction inquire table" );
			
			return 8041;
		}
		
		result = addInquire( transInquireData );
		
		return result;
	}
	
	
	/*Helper method for add()*/
	private int addInquire( TransInquireData inquireData )
	{
		int result = 0;
		
		String transType = inquireData.transType_;
		
		if( transType.equals( TransType.transType.RFQ.getValue( ) ) )
		{
			result = addRFQInquire( inquireData );
		}
		else if( transType.equals( TransType.transType.QUOTE.getValue( ) ) ) 
		{
			result = addQuoteInquire( inquireData );
		}
		else if( transType.equals( TransType.transType.PO.getValue( ) ) )
		{
			result = addPOInquire( inquireData );
		}
		else if( transType.equals( TransType.transType.INVOICE.getValue( ) ) )
		{
			result = addInvoiceInquire( inquireData );
		}
		
		return result;
	}
	
	/*To add rfq inquire*/
	
	private int addRFQInquire( TransInquireData inquireData )
	{
		errorMaster_.insert( "action 1="+inquireData.action_);
		
		RFQTable rfqTable = new RFQTable( );
		
		RFQData rfqData = new RFQData( );
		
		
		rfqData.RFQId_ = inquireData.transTypeId_;
		
		errorMaster_.insert( "action 2="+inquireData.action_);
		
		TSM tsm = new TSM( );
		
		tsm.init( inquireData.transId_, "" );
		
		TState tState = tsm.next( inquireData.action_ );
		
		rfqData.status_ = tState.toString( );
		
		inquireData.status_ = tState.toString( );
		
		errorMaster_.insert( "action 3="+rfqData.status_);
		
		UserProfileKey userKey =  null;
		
		if( inquireData.action_.equals( "Inquire" ))
		{
			userKey = inquireData.userFrom_;
		}
		else
		{
			userKey = inquireData.userTo_;
		}
		
		int result = rfqTable.updateStatus( rfqData.RFQId_, userKey, rfqData.status_ );
		
		errorMaster_.insert( "action 4="+inquireData.action_);
		
		rfqTable = null; rfqData = null; tsm = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::TransInquire.addRFQInquire() - Unable to update status in rfq table" );
			
			return 8042;
		}
		
		TransTable transTable  = new TransTable( );
		
		TransData transData = new TransData( );
		
		generateTransData( inquireData, transData );
		
		result = transTable.insert( transData );
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
				
		transNotification.add( transData );
				
		transNotification = null;
				
		
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::TransInquire.addRFQInquire() - Unable to insert into transaction table" );
			
			return 8043;
		}
		
		return 8040;
	}
	
	/*To add quote inquire*/
	
	private int addQuoteInquire( TransInquireData inquireData )
	{
		errorMaster_.insert( "action 1="+inquireData.action_);
		
		QuoteTable quoteTable = new QuoteTable( );
		
		QuoteData quoteData = new QuoteData( );
		
		quoteData.quoteId_ = inquireData.transTypeId_;
		
		errorMaster_.insert( "action 2="+inquireData.action_);
		
		TSM tsm = new TSM( );
		
		tsm.init( inquireData.transId_, "" );
		
		TState tState = tsm.next( inquireData.action_ );
		
		quoteData.status_ = tState.toString( );
		
		inquireData.status_ = tState.toString( );
		
		errorMaster_.insert( "action 3="+quoteData.status_);
		
		UserProfileKey userKey =  null;
		
		if( inquireData.action_.equals( "Inquire" ))
		{
			userKey = inquireData.userFrom_;
		}
		else
		{
			userKey = inquireData.userTo_;
		}
		
		int result = quoteTable.updateStatus( quoteData.quoteId_, userKey, quoteData.status_ );
		
		errorMaster_.insert( "action 4="+inquireData.action_);
		
		quoteTable = null; quoteData = null; tsm = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::TransInquire.addQuoteInquire() - Unable to update status in quote table" );
			
			return 8142;
		}
		
		TransTable transTable  = new TransTable( );
		
		TransData transData = new TransData( );
		
		generateTransData( inquireData, transData );
		
		result = transTable.insert( transData );
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
				
		transNotification.add( transData );
				
		transNotification = null;
		
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::TransInquire.addQuoteInquire() - Unable to insert into transaction table" );
			
			return 8143;
		}
		
		return 8140;
	}
	
	/*To add PO inquire*/
	
	private int addPOInquire( TransInquireData inquireData )
	{
		errorMaster_.insert( "action 1="+inquireData.action_);
		
		POTable poTable = new POTable( );
		
		POData poData = new POData( );
		
		poData.poId_ = inquireData.transTypeId_;
		
		errorMaster_.insert( "action 2="+inquireData.action_);
		
		TSM tsm = new TSM( );
		
		tsm.init( inquireData.transId_, "" );
		
		TState tState = tsm.next( inquireData.action_ );
		
		poData.status_ = tState.toString( );
		
		inquireData.status_ = tState.toString( );
		
		errorMaster_.insert( "action 3="+poData.status_);
		
		UserProfileKey userKey =  null;
		
		if( inquireData.action_.equals( "Inquire" ))
		{
			userKey = inquireData.userFrom_;
		}
		else
		{
			userKey = inquireData.userTo_;
		}
		
		int result = poTable.updateStatus( poData.poId_, userKey, poData.status_ );
		
		errorMaster_.insert( "action 4="+inquireData.action_);
		
		poTable = null; poData = null; tsm = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::TransInquire.addPOInquire() - Unable to update status in po table" );
			
			return 8242;
		}
		
		TransTable transTable  = new TransTable( );
		
		TransData transData = new TransData( );
		
		generateTransData( inquireData, transData );
		
		result = transTable.insert( transData );
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
				
		transNotification.add( transData );
				
		transNotification = null;
		
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::TransInquire.addPOInquire() - Unable to insert into transaction table" );
			
			return 8243;
		}
		
		return 8240;
	}
	
	/*To add Invoice inquire */
	
	private int addInvoiceInquire( TransInquireData inquireData )
	{
		errorMaster_.insert( "action 1="+inquireData.action_);
		
		InvoiceTable invoiceTable = new InvoiceTable( );
		
		InvoiceData invoiceData = new InvoiceData( );
		
		invoiceData.invoiceId_ = inquireData.transTypeId_;
		
		errorMaster_.insert( "action 2="+inquireData.action_);
		
		TSM tsm = new TSM( );
		
		tsm.init( inquireData.transId_, "" );
		
		TState tState = tsm.next( inquireData.action_ );
		
		invoiceData.status_ = tState.toString( );

		inquireData.status_ = tState.toString( );
		
		errorMaster_.insert( "action 3="+invoiceData.status_);
		
		UserProfileKey userKey =  null;
		
		if( inquireData.action_.equals( "Inquire" ))
		{
			userKey = inquireData.userFrom_;
		}
		else
		{
			userKey = inquireData.userTo_;
		}
		
		int result = invoiceTable.updateStatus( invoiceData.invoiceId_, userKey, invoiceData.status_ );
		
		errorMaster_.insert( "action 4="+inquireData.action_);
		
		invoiceTable = null; invoiceData = null; tsm = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::TransInquire.addInvoiceInquire() - Unable to update status in invoice table" );
			
			return 8342;
		}
		
		TransTable transTable  = new TransTable( );
		
		TransData transData = new TransData( );
		
		generateTransData( inquireData, transData );
		
		result = transTable.insert( transData );
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
				
		transNotification.add( transData );
				
		transNotification = null;
	
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::TransInquire.addInvoiceInquire() - Unable to insert into transaction table" );
			
			return 8343;
		}
		
		return 8340;
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
	public int get( int transTypeId, List<TransInquireData> inquireDataList  )
	{
		return 0;
	}

	private int generateTransData( TransInquireData transInquireData, TransData transData )
    {
	    transData.transId_ = transInquireData.transId_;
	    
	    transData.transType_ = transInquireData.transType_;
	    
	    transData.transTypeId_ = transInquireData.transTypeId_;
	    
	    transData.from_ = transInquireData.from_;
	    
	    transData.to_ = transInquireData.to_;
	    
	    transData.userFrom_ = transInquireData.userFrom_;
	    
	    transData.userTo_ = transInquireData.userTo_;
	    
	    transData.status_ = transInquireData.status_;
	    
	    return 0;
    }
}
