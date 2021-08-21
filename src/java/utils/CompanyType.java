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
 * File:  CompanyType.java 
 *
 * Created on Feb 8, 2013 11:27:28 AM
 */

/*
 * This class contain the company type enum. It is used to
 * specify the company type. There are
 * 
 * Buyer 		- If company buyer, company users get only the buyers features.
 * Supplier  	- If company buyer, company users get only the suppliers features.
 * Both 		- If company both, company users get the buyer & suppliers features.
 */

public class CompanyType
{
	public enum type
	{
		BUYER( "Buyer" ), SUPPLIER( "Supplier" ), BOTH( "Both" );

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
