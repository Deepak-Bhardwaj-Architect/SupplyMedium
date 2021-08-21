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
 * File:  CompanyStatus.java 
 *
 * Created on Feb 8, 2013 11:27:28 AM
 */

/*
 * This class contain the company status enum. It is used to
 * specify the company status. There are
 * 
 * Pending - Company registered, but not activated.
 * Active  - Company in active state.
 * Blocked - Company blocked by SupplyMedium. If company blocked, company
 * 				user cant login into supplymedium.
 */

public class CompanyStatus
{
	public enum status
	{
		PENDING( "Pending" ), ACTIVE( "Active" ), BLOCKED( "Blocked" );

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
