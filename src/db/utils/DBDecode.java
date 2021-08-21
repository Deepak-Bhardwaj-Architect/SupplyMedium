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
package db.utils;

/**
 * File:  DBDecode.java 
 *
 * Created on 23-Oct-2013 5:26:45 PM
 */
public class DBDecode
{

	/*
	 * Method : DBDecode.java() -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public DBDecode()
	{
	}
	
	/*
	 * Method : encode
	 * 
	 * Input  : normal string
	 * 
	 * Return : encoded string
	 * 
	 * Purpose: It is used to convert encoded string to normal string
	 */
	
	public String decode( String str)
	{
		if( str == null )
			return str;
		
		str = str.replace( "\"", "'" );
		
		str = str.replace( "&dlr", "$" );
				
		str = str.replace( "&crt", "^" );
		
		str = str.replace( "&ast", "*" );
		
		str = str.replace( "&tld", "~" );
		
		str = str.replace( "&cln", ":" );
		
		str = str.replace( "&pnd", "#" );

		
		return str;

	}
		
			
			

}
