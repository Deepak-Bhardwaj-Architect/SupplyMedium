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
package utils;

/**
 * File:  VendorRegnStatus.java 
 *
 * Created on May 24, 2013 12:42:03 PM
 */

/*
 * This class contain the user status enum. It is used to
 * specify the Vendor Regn status. There are
 * 
 * Pending - New vendor regn request is made
 * Accepted - Vendor regn request is accepted
 * Rejected - Vendor regn request is rejected
 * Inquire - Vendor regn request is in process of wating for additional details
 */
public class VendorRegnStatus
{
	public enum VRStatus
	{
		NEWREQUEST("New Request"), 
		FORMSENT("Form Sent"), 
		ACCEPTED( "Accepted" ), 
		REJECTED( "Rejected" ), 
		INQUIRE( "Inquire" ),
		INQUIRYANSWERED( "Inquiry Answered" );

		private VRStatus(String value)
		{
			this.value = value;
		}

		private final String value;

		public String getValue( )
		{
			return value;
		}

	}
}

