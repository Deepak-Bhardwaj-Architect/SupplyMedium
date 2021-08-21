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
package core.trans.states;

import core.trans.TState;

/**
 * File:  QuoteAccepted.java 
 *
 * Created on 01-Jul-2013 9:25:16 AM
 */
public class QuoteAccepted implements TState
{
	public String state_ = "QuoteAccepted";
	
	/*
	 * Method : QuoteAccepted -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public QuoteAccepted()
	{
	}
	
	/* (non-Javadoc)
	 * @see TState#next(java.lang.String)
	 */
    @Override
    public TState next( String action )
    {
    	TState tState = null;
    	
 	   if( action.equals( "CreatePO" ))
 	   {
 		   tState = new POCreated( );
 	   }
 	   
 	    
 	    return tState;
    }

    /* (non-Javadoc)
	 * @see TState#toString()
	 */
    @Override
    public String toString( )
    {
	    
	    return state_;
    }

}
