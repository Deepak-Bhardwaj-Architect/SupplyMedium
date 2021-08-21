package core.trans;
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
 * File:  TState.java 
 *
 * Created on 26-Jun-2013 7:57:28 PM
 */
public interface TState
{
	/* This method is used to find the next state of the transaction
	 * using the action parameter */
	public TState next( String action );
	
	/* It is used to get the state name from state object */
	public String toString();
	
	
}
