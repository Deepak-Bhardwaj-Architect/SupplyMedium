package core.trans;

import core.trans.states.InvoiceCreated;
import core.trans.states.POCreated;
import core.trans.states.QuoteCreated;
import core.trans.states.RFQCreated;
import utils.StringHolder;
import utils.TransType;
import db.trans.TransTable;
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

/**
 * File:  TSM.java 
 *
 * Created on 26-Jun-2013 7:57:14 PM
 */
public class TSM
{
	public TState curState_;

	/*
	 * Method : TSM -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TSM()
	{
	}
	
	/*
	 * Method : init
	 * 
	 * Input  : transaction id
	 * 
	 * Return : void
	 * 
	 * Purpose: It is intialize the State machine with current state.
	 */
	
	public void init( long transId, String transType )
	{
		if( transId == -1 )
		{
			if( transType.equals( TransType.transType.RFQ.getValue( ) ) )
			{
				curState_ = new RFQCreated( );
			}
			else if( transType.equals( TransType.transType.QUOTE.getValue( ) ) )
			{
				curState_ = new QuoteCreated( );
			}
			else if( transType.equals( TransType.transType.PO.getValue( ) ) ) 
			{
				curState_ = new POCreated( );
			}
			else if( transType.equals( TransType.transType.INVOICE.getValue( ) ) )
			{
				curState_ = new InvoiceCreated( );
			}
		}
		else 
		{
			StringHolder status = new StringHolder( );
			
			TransTable transTbl = new TransTable( );
			
			int result = transTbl.find( transId, status );
			
			if( result == 0 )
			{
				TSMMap tsmMap = TSMMap.instance( );
				
				curState_ = tsmMap.get( status.value );
			}
			else
			{
				curState_ = new RFQCreated( );
			}
		}	
	}
	
	/*
	 * Method : init
	 * 
	 * Input  : none
	 * 
	 * Return : current state object
	 * 
	 * Purpose: It is used to return the current state of the transaction
	 */
	
	public TState state()
	{
		return curState_;
	}
	
	/*
	 * Method : init
	 * 
	 * Input  : action
	 * 
	 * Return : next state object
	 * 
	 * Purpose: It is used to return the next state of the transaction
	 */
	
	public  TState next( String action)
    {
	    return curState_.next( action );
    }

}
