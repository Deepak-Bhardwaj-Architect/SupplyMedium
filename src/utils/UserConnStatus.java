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
 * File:  UserConnStatus.java 
 *
 * Created on 13-Aug-2013 3:07:26 PM
 */

/*
 * This class contain the user connection status enum. It is used to
 * specify the user connection status. There are
 * 
 * Request Sent - User sent the connection request to other user. But waiting for response.
 * Accepted  	- User sent the connection request to other users. They accepted his request.
 */


public class UserConnStatus
{

	public enum status
	{
		REQUEST_SENT( "Request Sent" ), ACCEPTED( "Accepted" );

		private status(String value)
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
