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

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import timers.SMTimers;


/**
 * File:  StartupUtil.java 
 *
 * Created on Feb 8, 2013 11:27:28 AM
 */

/*
 * This class methods call at server start up. All the application 
 * initialization done here.
 */
public class StartupUtil implements ServletContextListener
{
    private static ErrorMaster errorMaster_ = null;
    
        public StartupUtil()
        {
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
        }
	/*
	 * Method : contextInitialized()
	 * 
	 * Input : ServletContextEvent
	 * 
	 * Return : none
	 * 
	 * Purpose: This method is automatically called when server startup. This
	 * method is used to start the timer and initialize the Application config
	 * properties
	 */

	public void contextInitialized( ServletContextEvent evt )
	{
		
                ErrorLogger errorLogger = ErrorLogger.instance( );



	
		String msg = "Info::StartupUtil:contextInitialized-Application started";
		
		ServletContext sc = evt.getServletContext( );

		errorMaster_.insert( "Startup" );
                System.setProperty("catalina.home","");

		AppStartupInitializer appInitializer = new AppStartupInitializer( );

		appInitializer.initAppConfigProperty( );

		appInitializer.initDBConfigProperty( );

		appInitializer.initErrorCodeConfigProperty( );

		appInitializer.initErrorLogger( );
		
		appInitializer.initAccountPolicies( );
		
		appInitializer.initStateMachineMap( );
		
		errorLogger.logMsg( msg );
		
		SMTimers smTimers = new SMTimers( );
		
		smTimers.executeSMTimers( );
		
		smTimers = null;
		
	}

	/*
	 * Method : contextDestroyed()
	 * 
	 * Input : ServletContextEvent
	 * 
	 * Return : none
	 * 
	 * Purpose: This method is automatically called when server shutdown.
	 */

	public void contextDestroyed( ServletContextEvent evt )
	{

		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		String msg = "Info::StartupUtil:contextDestroyed-Application stoped";
		
		errorLogger.logMsg( msg );
		
		
		ServletContext sc = evt.getServletContext( );

		// Place your code here that needs to be executed at application server
		// shutdown

		errorMaster_.insert( "Shutdown" );

	}
}
