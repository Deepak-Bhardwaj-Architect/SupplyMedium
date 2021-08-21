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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * File:  ErrorCodeConfigReader.java 
 *
 * Created on Feb 8, 2013 11:27:28 AM
 */

/*
 * It is used to read the errorcodeconfig.properties files and convert 
 * into properties object. 
 */

public class ErrorCodeConfigReader
{
	private static ErrorCodeConfigReader errorCodeConfigReader_ = null;

	private Properties                   properties_            = null;

	/*
	 * Method : instance()
	 * 
	 * Input : none
	 * 
	 * Return : ErrorCodeConfigReader obj
	 * 
	 * Purpose: It is used to implement the single-ton for ErrorCodeConfigReader
	 */

	public static ErrorCodeConfigReader instance( )
	{
		if( errorCodeConfigReader_ == null )
		{
			errorCodeConfigReader_ = new ErrorCodeConfigReader( );
		}
		return errorCodeConfigReader_;

	}

	/*
	 * Method : instance()
	 * 
	 * Input : none
	 * 
	 * Return : none
	 * 
	 * Purpose: It is used to read the values from config file and stored into
	 * properties object
	 */

	public void init( )
	{
		properties_ = new Properties( );
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		try
		{
			InputStream inputStream = getClass( ).getResourceAsStream(
			        "../errorcodeconfig.properties" );

			properties_.load( inputStream );

		}

		catch( IOException ex )
		{
			errLogger.logMsg( "Exception :: ErrorCodeConfigReader : init -" + ex );
		}
		catch( IllegalArgumentException ex )
		{
			errLogger.logMsg( "Exception :: ErrorCodeConfigReader : init -" + ex );
		}
		catch( NullPointerException ex )
		{
			errLogger.logMsg( "Exception :: ErrorCodeConfigReader : init -" + ex );
		}
		catch( Exception ex )
		{
			errLogger.logMsg( "Exception :: ErrorCodeConfigReader : init -" + ex );

		}
	}

	/*
	 * Method : get()
	 * 
	 * Input : key
	 * 
	 * Return : value
	 * 
	 * Purpose: It get the input as the key, it get the key and return
	 * corresponding value using property object which is initialize in init().
	 */

	public String get( String key )
	{
		String value = null;

		try
		{
			value = properties_.getProperty( key );
			
			return value;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger.logMsg( "Exception :: ErrorCodeConfigReader : init -" + ex );
			return value;
		}
	}
	
	/*
	 * Method : get()
	 * 
	 * Input : key
	 * 
	 * Return : value
	 * 
	 * Purpose: This method is used to compose the error message 
	 * with errorcode values and values array as inputs
	 */

	public String get( int errorCode, String [] valueArr )
	{
		String value = ""; 
		
		String msg = get( String.valueOf( errorCode ) );
		
		String [] msgArr = msg.split( "\\$" );
		
		int i = 0;
		
		for( String arrValue : msgArr )
        {
	        value = value + arrValue;
	        
	        if( i < valueArr.length )
	        {
	        	value = value + valueArr[i];
	        }
	        
	        i++;
        }
		
		return value;
	}

}
