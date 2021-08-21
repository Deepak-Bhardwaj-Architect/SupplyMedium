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
package db.config;

/**
 * File:  TransRejectReasonRecord.java 
 *
 * Created on Jul 10, 2013 12:56:13 PM
 */
public class TransRejectReasonRecord
{
	public String rejectReason_;
	public long rejectReasonId_;

	/*Constructor*/
	
	public TransRejectReasonRecord( )
	{
		
	}
	
	/*
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "rejectReason_ = " + rejectReason_ );
		System.out.println( "rejectReasonId_=" + rejectReasonId_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		this.rejectReason_ = null;
		this.rejectReasonId_ = -1;
	}
}
