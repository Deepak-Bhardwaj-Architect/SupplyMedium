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

package core.vendorregn;

import utils.VendorRegnStatus;

/**
 * File: VendorRegnSM.java
 * 
 * Created on May 24, 2013 12:11:09 PM
 */

/*
 * Class: VendorRegnSM
 * 
 * Purpose: This class is used to
 */

public class VendorRegnSM
{

	/* Constructor */
	public VendorRegnSM()
	{

	}

	/*
	 * Method: getNextState
	 * 
	 * Input: String action
	 * 
	 * Return: String state
	 * 
	 * Purpose: This method gives the next state for the given action
	 */

	public String getNextState( String action )
	{
		String nextState = "";

		// If current state is 'Pending'
		if( action.equals( "newrequest" ) )
		{
			nextState = VendorRegnStatus.VRStatus.NEWREQUEST.getValue();
		}
		
		if( action.equals( "formsent" ) )
		{
			nextState = VendorRegnStatus.VRStatus.FORMSENT.getValue( );
		}
		else if( action.equals( "accept" ) )
		{
			nextState = VendorRegnStatus.VRStatus.ACCEPTED.getValue( );
		}
		else if( action.equals( "reject" ) )
		{
			nextState = VendorRegnStatus.VRStatus.REJECTED.getValue( );
		}
		else if( action.equals( "inquire" ) )
		{
			nextState = VendorRegnStatus.VRStatus.INQUIRE.getValue( );
		}
		else if( action.equals( "inquiryanswered" ) )
		{
			nextState = VendorRegnStatus.VRStatus.INQUIRYANSWERED.getValue( );
		}
		
		return nextState;
	}
}
