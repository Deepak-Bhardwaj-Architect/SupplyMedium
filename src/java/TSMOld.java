
import utils.ErrorMaster;

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
 * Created on 30-May-2013 3:23:49 PM
 */
public class TSMOld
{
	private static ErrorMaster errorMaster_ = null;
	private enum State{
        
        RFQ_CREATED
        {
            void next(TSMOld entity, String action ) 
            {
            	if( action == "Inquire")
            		entity.state = RFQ_INQUIRE;
            	
            	else if( action == "Accept" )
            		entity.state = RFQ_ACCEPTED;

            }
             
        },
        RFQ_INQUIRE
        {
            void next(TSMOld entity, String action ) 
            {
                entity.state = RFQ_CREATED;
           }
             
        },
        RFQ_ACCEPTED
        {
            void next(TSMOld entity, String action ) 
            {
                entity.state = QUOTE_CREATED;
                
            }
             
        },
        QUOTE_CREATED{
            void next(TSMOld entity, String action ) 
            {
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
                errorMaster_.insert( "Completed" );
            }
        };
         
        abstract void next(TSMOld entity, String action);
    }
	
	private State state;
	
	public TSMOld()
	{
		
	}
	
	public void init()
	{
		 state = State.RFQ_CREATED;
	}
	
	public String getState()
	{
		return state.toString( );
	}
	
	
	public String next( String action )
    {
	   this.state.next( this,action );
	   
	   return state.toString( );
    }
	
	public String close()
	{
		return "";
	}

}
