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
 * File:  VRMailType.java 
 *
 * Created on Aug 1, 2013 5:05:56 PM
 */
public class VRMailType
{

	/*Class: TransType
	 * 
	 * Purpose: This contains transaction type enum for Vendor Registration mail types
	 * 
	 */
	
	public enum vrMailType
	{
		NEW_REQ( "VRMail_NewReq" ),
		UPDATE_REQ( "VRMail_UpdateReq" ),
		INQUIRY( "VRMail_Inquiry" ), 
		INQUIRY_RESPONSE("VRMail_InquiryResponse"),
		CONFIRMATION("VRMail_Confirmation"),
		REJECTION("VRMail_Rejection");

		private vrMailType(String value)
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