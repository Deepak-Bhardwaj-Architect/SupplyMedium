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

import utils.ErrorLogger;
import utils.TransType;
import core.regn.UserProfileKey;
import db.trans.InvoiceTable;
import db.trans.POTable;
import db.trans.TransRejectTable;
import db.trans.TransTable;
import utils.ErrorMaster;

/**
 * File:  TransReject.java 
 *
 * Created on Jul 10, 2013 12:05:26 PM
 */
public class TransReject
{
	ErrorLogger errLogger_;
        
        private static ErrorMaster errorMaster_ = null;


	
	/*
	 * Method : TransReject -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public TransReject()
	{
		errLogger_ = ErrorLogger.instance( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	
	/*
	 * Method : reject
	 * 
	 * Input  : TransRejectData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to reject transaction and
	 * 1. Insert one record in TransRejectTable
	 * 2. Insert one record in TransTable
	 */
	
	public int reject( TransRejectData transRejectData )
	{
		TransRejectTable rejectTable = new TransRejectTable( );
		
		int result = rejectTable.insert( transRejectData );
		
		rejectTable = null;
		
		if( result != 0)
		{
			errLogger_.logMsg( "Error::TransReject.add() - Unable to insert into Transaction Reject table" );
			
			return 8051;
		}
		
		result = rejectTrans( transRejectData );
		
		return result;
	}
	
	
	/*Helper method for rejectTrans()*/
	private int rejectTrans( TransRejectData rejectData )
	{
		int result = 0;
		
		String transType = rejectData.transType_;
		
		if( transType.equals( TransType.transType.RFQ.getValue( ) ) )
		{
			result = rejectFromRFQ( rejectData );
		}
		else if( transType.equals( TransType.transType.QUOTE.getValue( ) ) ) 
		{
			result = rejectFromQuote( rejectData );
		}
		else if( transType.equals( TransType.transType.PO.getValue( ) ) )
		{
			result = rejectFromPO( rejectData );
		}
		else if( transType.equals( TransType.transType.INVOICE.getValue( ) ) )
		{
			result = rejectFromInvoice( rejectData );
		}
		
		return result;
	}
	
	/*To reject from rfq */
	
	private int rejectFromRFQ( TransRejectData rejectData )
	{
		return 8050;
	}
	
	/*To reject from quote*/
	
	private int rejectFromQuote( TransRejectData rejectData )
	{
		return 8150;
	}
	
	/*To reject from PO*/
	
	private int rejectFromPO( TransRejectData rejectData )
	{
		errorMaster_.insert( "action 1="+rejectData.action_);
		
		POTable poTable = new POTable( );
		
		POData poData = new POData( );
		
		poData.poId_ = rejectData.transTypeId_;
		
		errorMaster_.insert( "action 2="+rejectData.action_);
		
		TSM tsm = new TSM( );
		
		tsm.init( rejectData.transId_, "" );
		
		TState tState = tsm.next( rejectData.action_ );
		
		poData.status_ = tState.toString( );
		
		rejectData.status_ = tState.toString( );
		
		errorMaster_.insert( "action 3="+poData.status_);
		
		UserProfileKey userKey =  null;
		
		if( rejectData.action_.equals( "Reject" ))
		{
			userKey = rejectData.userFrom_;
		}
		else
		{
			userKey = rejectData.userTo_;
		}
		
		int result = poTable.updateStatus( poData.poId_, userKey, poData.status_ );
		
		errorMaster_.insert( "action 4="+rejectData.action_);
		
		poTable = null; poData = null; tsm = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::TransReject.rejectFromPO() - Unable to update status in po table" );
			
			return 8252;
		}
		
		TransTable transTable  = new TransTable( );
		
		TransData transData = new TransData( );
		
		generateTransData( rejectData, transData );
		
		result = transTable.insert( transData );
		
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::TransReject.rejectFromPO() - Unable to insert into transaction table" );
			
			return 8253;
		}
		
		return 8250;
	}
	
	/*To reject from Invoice */
	
	private int rejectFromInvoice( TransRejectData rejectData )
	{
		errorMaster_.insert( "action 1="+rejectData.action_);
		
		InvoiceTable invoiceTable = new InvoiceTable( );
		
		InvoiceData invoiceData = new InvoiceData( );
		
		invoiceData.invoiceId_ = rejectData.transTypeId_;
		
		errorMaster_.insert( "action 2="+rejectData.action_);
		
		TSM tsm = new TSM( );
		
		tsm.init( rejectData.transId_, "" );
		
		TState tState = tsm.next( rejectData.action_ );
		
		invoiceData.status_ = tState.toString( );

		rejectData.status_ = tState.toString( );
		
		errorMaster_.insert( "action 3="+invoiceData.status_);
		
		UserProfileKey userKey =  null;
		
		if( rejectData.action_.equals( "Reject" ))
		{
			userKey = rejectData.userFrom_;
		}
		else
		{
			userKey = rejectData.userTo_;
		}
		
		int result = invoiceTable.updateStatus( invoiceData.invoiceId_, userKey, invoiceData.status_ );
		
		errorMaster_.insert( "action 4="+rejectData.action_);
		
		invoiceTable = null; invoiceData = null; tsm = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::TransReject.rejectFromInvoice() - Unable to update status in invoice table" );
			
			return 8352;
		}
		
		TransTable transTable  = new TransTable( );
		
		TransData transData = new TransData( );
		
		generateTransData( rejectData, transData );
		
		result = transTable.insert( transData );
		
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::TransReject.rejectFromInvoice() - Unable to insert into transaction table" );
			
			return 8343;
		}
		
		return 8350;
	}
	
	/*
	 * Method : get
	 * 
	 * Input  : transTypeId and rejectDataList( TransTypeId should be
	 * 			RFQId or QuoteId or POId or InvoiceId )
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to get all the Inquire details for the given
	 * transTypeId and assign to rejectDataList parameter so it is copied to caller
	 * classes.
	 */
	public int get( int transTypeId, List<TransRejectData> rejectDataList  )
	{
		return 0;
	}

	private int generateTransData( TransRejectData transRejectData, TransData transData )
    {
	    transData.transId_ = transRejectData.transId_;
	    
	    transData.transType_ = transRejectData.transType_;
	    
	    transData.transTypeId_ = transRejectData.transTypeId_;
	    
	    transData.from_ = transRejectData.from_;
	    
	    transData.to_ = transRejectData.to_;
	    
	    transData.userFrom_ = transRejectData.userFrom_;
	    
	    transData.userTo_ = transRejectData.userTo_;
	    
	    transData.status_ = transRejectData.status_;
	    
	    return 0;
    }
}
