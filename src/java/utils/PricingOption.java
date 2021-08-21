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
 * File: PricingOption.java
 * 
 * Created on Feb 8, 2013 11:27:28 AM
 */

/*
 * This class contain the PricingOption enum. It is used to specify the company
 * pricing type. There are
 * 
 * Basic - 25 Employee License and $250K/M transactions Plus - 100 Employee
 * License and $1Million/M transactions Premium - Unlimited Employee License and
 * Unlimited transactions
 */

public class PricingOption
{
	public enum option
	{
		BASIC( "Basic" ), PLUS( "Plus" ), PREMIUM( "Premium" );

		private option(String value)
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
