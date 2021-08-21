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
 * File:  NotificationType.java 
 *
 * Created on 26-Sep-2013 11:58:22 AM
 */
public class NotificationType
{

	public enum type
	{
		RFQ( "RFQ" ), QUOTE( "Quote" ), PO( "PO" ), INVOICE( "Invoice" ),
		VENREG( "VendorRegistration" ), NEWSFEED( "NewsFeed" ),
		PRIVATEMESSAGE( "PrivateMessage" ), NEWCONNREQ( "NewConnectionRequest" ), 
		NEWCONNRES( "NewConnectionResponse" );
		

		private type(String value)
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
