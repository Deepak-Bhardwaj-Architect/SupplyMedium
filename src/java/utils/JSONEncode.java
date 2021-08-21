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
 * File:  JSONEncode.java 
 *
 * Created on 03-Jun-2013 3:05:57 PM
 */
public class JSONEncode
{

	/*
	 * Method : JSONEncode -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public JSONEncode()
	{
	}
	
	/*
	 * Method : encode
	 * 
	 * Input  : normal string
	 * 
	 * Return : encoded string
	 * 
	 * Purpose: It is used to convert normal string to encoded string
	 */
	
	public String encode( String str)
	{
		str = str.trim( );
		
		str=str.replace("\"","");
		
		// Damn pesky carriage returns...
		str = str.replace("\r\n", "\n");
		str = str.replace("\r", "\n");

	    // JSON requires new line characters be escaped
		str = str.replace("\n", "\\n");
		
		//errorMaster_.insert( "encoded string="+str );
		
		return str;

	}

}
