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
 * File:  LoginStatus.java 
 *
 * Created on Mar 21, 2013 2:41:40 PM
 */

/*
 * This class contain the login status enum. It is used to
 * specify the user logged in status. There are
 * 
 * connected 		- The user is logged in 
 * notconnected  	- The user is logged out
 */

public class LoginStatus
{
	public enum loginStatus
	{
		CONNECTED( "Connected" ), NOTCONNECTED( "Not Connected" );

		private loginStatus(String value)
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
