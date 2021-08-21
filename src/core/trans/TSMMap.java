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


import java.util.HashMap;
import java.util.Map;

import core.trans.states.InvoiceAccepted;
import core.trans.states.InvoiceCreated;
import core.trans.states.InvoiceInquire;
import core.trans.states.POAccepted;
import core.trans.states.POCreated;
import core.trans.states.POInquire;
import core.trans.states.QuoteAccepted;
import core.trans.states.QuoteCreated;
import core.trans.states.QuoteInquire;
import core.trans.states.RFQAccepted;
import core.trans.states.RFQCreated;
import core.trans.states.RFQInquire;
import core.trans.states.TransRejected;


/**
 * File:  TSMMap.java 
 *
 * Created on 02-Jul-2013 11:33:42 AM
 */
public class TSMMap
{

	// private map contain all state objects
	private Map<String, TState> tsmMap_;

	private static TSMMap tsmMapObj_  = null;
	
	/*
	 * Method : instance( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: This is the singleton class. This class initialize at 
	 * application startup
	 */
	public static TSMMap instance( )
	{
		if( tsmMapObj_ == null )
		{
			tsmMapObj_ = new TSMMap( );
		}

		return tsmMapObj_;
	}

	
	/*
	 * Method : iTSMMap( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: constructor call the private init method to load the all state objects to map
	 */
	private TSMMap( )
	{
		init( );
	}

	
	/*
	 * Method : init( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: this method is used to load the all companies account policies to map
	 */
	private void init( )
	{
		tsmMap_  = new HashMap<String, TState>( );
		
		
		// For RFQ States
		
		tsmMap_.put( "RFQCreated", new RFQCreated( ) );
		
		tsmMap_.put( "RFQInquire", new RFQInquire( ) );
		
		tsmMap_.put( "RFQAccepted", new RFQAccepted( ) );
		
		// For Quote States
		
		tsmMap_.put( "QuoteCreated", new QuoteCreated( ) );
		
		tsmMap_.put( "QuoteInquire", new QuoteInquire( ) );
		
		tsmMap_.put( "QuoteAccepted", new QuoteAccepted( ) );
		
		// For PO States
		
		tsmMap_.put( "POCreated", new POCreated( ) );
		
		tsmMap_.put( "POInquire", new POInquire( ) );
		
		tsmMap_.put( "POAccepted", new POAccepted( ) );
		
		// For Invoice States
		
		tsmMap_.put( "InvoiceCreated", new InvoiceCreated( ) );
		
		tsmMap_.put( "InvoiceInquire", new InvoiceInquire( ) );
		
		tsmMap_.put( "InvoiceAccepted", new InvoiceAccepted( ) );
		
		// For transaction rejection
		
		tsmMap_.put( "Rejected", new TransRejected( ) );
	
	}
	
	/*
	 * Method : get( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to fetch the Transaction state object for given key
	 */
	
	public  TState get( String key )
    {
	    return tsmMap_.get( key );
    }

}
