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
 * File:  AppConfigReader.java 
 *
 * Created on Feb 8, 2013 11:27:28 AM
 */

//It is used to read the appconfig.properties files and convert into properties object.

public class AppConfigReader
{

	private static AppConfigReader appConfigReader_ = null;

	private Properties             properties_      = null;

	/*
	 * Method : instance()
	 * 
	 * Input : none
	 * 
	 * Return : AppConfigReader obj
	 * 
	 * Purpose: It is used to implement the single-ton for AppConfigReader
	 */

	public static AppConfigReader instance( )
	{
		if( appConfigReader_ == null )
		{
			appConfigReader_ = new AppConfigReader( );
		}
		return appConfigReader_;

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
			        "../appconfig.properties" );

			properties_.load( inputStream );

		}
		catch( IOException ex )
		{
			errLogger.logMsg( "Exception :: AppConfigReader : init -" + ex );
		}
		catch( IllegalArgumentException ex )
		{
			errLogger.logMsg( "Exception :: AppConfigReader : init -" + ex );
		}
		catch( NullPointerException ex )
		{
			errLogger.logMsg( "Exception :: AppConfigReader : init -" + ex );
		}
		catch( Exception ex )
		{
			errLogger.logMsg( "Exception :: AppConfigReader : init -" + ex );

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
			
			errLogger.logMsg( "Exception :: AppConfigReader : get -" + ex );
			
			return value;
		}
	}
}
