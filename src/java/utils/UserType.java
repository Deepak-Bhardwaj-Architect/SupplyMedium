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
 * File:  UserType.java 
 *
 * Created on Feb 8, 2013 11:27:28 AM
 */

/*
 * This class contain the user type enum. It is used to
 * specify the user type. There are
 * 
 * Admin 			- He is the admin of the company, he has all privileges.
 * IntranetUser  	- This user can access only the company intranet pages only.
 * TransactionUser 	- This user can access intranet and transaction features.
 */

public class UserType
{
	public enum type
	{
		ADMIN( "Admin" ), INTRANETUSER( "IntranetUser" ), TRANSACTIONUSER(
		        "TransactionUser" );

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
