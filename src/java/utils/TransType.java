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
 * File:  TransType.java 
 *
 * Created on Jul 1, 2013 10:07:58 AM
 */

/*Class: TransType
 * 
 * Purpose: This contains transaction type enum
 * 
 *  1. RFQ 		- Transaction is RFQ;
 *  2. Quote 	- Transaction type is Quote
 *  3. PO		- Transaction type is PO
 *  4. Invoic	- Transaction type is Invoice
 */

public class TransType
{
	public enum transType
	{
		RFQ( "RFQ" ), QUOTE( "Quote" ), PO( "PO" ), INVOICE("Invoice");

		private transType(String value)
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
