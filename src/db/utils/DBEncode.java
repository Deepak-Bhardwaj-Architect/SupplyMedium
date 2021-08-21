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
 * File:  DBEncode.java 
 *
 * Created on 03-Jun-2013 3:01:53 PM
 */
public class DBEncode
{

	/*
	 * Method : DBEncode -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public DBEncode()
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
		
		if( str == null )
			return str;
		
		str = str.trim( );
		
		str = str.replace( "'", "\"" );
		
		str = str.replace( "\\", "/" );
		
		str = str.replace( "$", "&dlr" );
		
		str = str.replace( "^", "&crt" );
		
		str = str.replace( "*", "&ast" );
		
		str = str.replace( "~", "&tld" );
		
		str = str.replace( ":", "&cln" );
		
		str = str.replace( "#", "&pnd" );
		
		
		return str;

	}

}
