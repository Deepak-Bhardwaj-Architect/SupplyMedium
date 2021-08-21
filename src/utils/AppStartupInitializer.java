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

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import core.regn.AccountPolicyTblBuffer;
import core.trans.TSMMap;


/*
 * This class is helper class for StartupUtil. This class is used to initialize the 
 * Application start up properties.
 */
public class AppStartupInitializer
{
	/*
	 * Method : initAppConfigProperty( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: To load the appconfig properties from peroperty file to Proeprty
	 * object.
	 */

	public void initAppConfigProperty( )
	{
		AppConfigReader.instance( ).init( );

	}

	/*
	 * Method : initDBConfigProperty( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: To load the dbconfig properties from peroperty file to Proeprty
	 * object.
	 */

	public void initDBConfigProperty( )
	{
		DBConfigReader.instance( ).init( );

	}

	/*
	 * Method : initErrorCodeConfigProperty( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: To load the errorconfig properties from peroperty file to
	 * Proeprty object.
	 */

	public void initErrorCodeConfigProperty( )
	{
		ErrorCodeConfigReader.instance( ).init( );

	}

	/*
	 * Method : initErrorLogger( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: To load the error logger file.
	 */

	public void initErrorLogger(  )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );

		Date date = new Date( );
		
		try
        {
			DateFormat dateFormat = new SimpleDateFormat( "yyyy_MM_dd" );

    		String filePath = System.getProperty( "catalina.home" ) + "/"
    		 + AppConfigReader.instance( ).get( "ERRORLOGGERPATH" );
			
			//String filePath =  AppConfigReader.instance( ).get( "ERRORLOGGERPATH" );


    		File f = new File( filePath );

    		//System.out.println( "File path" + System.getProperty( "catalina.home" ) );

    		if( !f.isDirectory( ) )
    		{
    			 f.mkdirs( );

    			System.out.println( "Creating Directory.." );
    		}

    		String s = filePath + "/log_" + dateFormat.format( date ) + ".log";

    		errLogger.setLogFile( s );
        }
        catch( IllegalArgumentException ex )
        {
        	System.out.println( "Exception::AppStartupInitializer:initErrorLogger-"+ex );
        }
		catch( NullPointerException ex )
        {
			System.out.println( "Exception::AppStartupInitializer:initErrorLogger-"+ex );
        }
		catch( SecurityException ex )
        {
			System.out.println( "Exception::AppStartupInitializer:initErrorLogger-"+ex );
        }
		catch( Exception ex )
        {
			System.out.println( "Exception::AppStartupInitializer:initErrorLogger-"+ex );
        }

	}
	
	/*
	 * Method : initAccountPolicies( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: To load the all companies account policies at startup.
	 */

	public void initAccountPolicies( )
	{
		//The policies will be initialized in the constructor
		
		AccountPolicyTblBuffer.instance( );
	}
	
	/*
	 * Method : initStateMachineMap( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It load the all the state object to map
	 */

	public void initStateMachineMap( )
	{
		
		TSMMap.instance( );
	}


}
