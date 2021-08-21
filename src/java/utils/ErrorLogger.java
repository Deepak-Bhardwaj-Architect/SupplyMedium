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
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author 
 * 
 */

// FILEPATH=./logs/

public class ErrorLogger
{

	/**
     *
     */

	private static ErrorLogger logger_     = null;

//	private String             logFile_    = "";
//
//	private File               outFile_    = null;
//
//	private FileWriter         out_        = null;
//
//	private Date               date_       = null;
//
//	private DateFormat         dateFormat_ = null;
        
        private static ErrorMaster errorMaster_ = null;

	private ErrorLogger()
	{

            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		// Private constructor for Singleton
	}

	private static String logLevel_ = "ERROR";

	public static ErrorLogger instance( )
	{
		if( logger_ == null ) logger_ = new ErrorLogger( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );

		return logger_;
                
	}

//	public void setLogFile( String logFileIn )
//	{
//
//		if( !this.logFile_.equalsIgnoreCase( logFileIn ) )
//		{
//			// here, close the original log file if any and open the new file.
//			this.logFile_ = logFileIn;
//
//			if( out_ != null )
//			{
//				try
//				{
//					out_.close( );
//					
//					System.out.println( "In SetLogFile - log file name - "+this.logFile_ );
//				}
//				catch( IOException ex )
//				{
//					System.out.println( "In SetLogFile - Exception log file name - "+this.logFile_ );
//					ex.printStackTrace( );
//				}
//			}
//
//			try
//			{
//				outFile_ = new File( this.logFile_ );
//
//				System.out.println( "Outfile - "+outFile_ );
//				
//				out_ = new FileWriter( outFile_, true );
//				
//				//System.
//			}
//			catch( IOException ex )
//			{
//				ex.printStackTrace( );
//			}
//		}
//
//	}

	public void logMsg( String msgIn )
	{
		// Will always log the messages into the logFile that is set.

//		date_ = new Date( );
//
//		dateFormat_ = new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss:SSS" );
//
//		String s = dateFormat_.format( date_ );
//
//		try
//		{
//			out_.write( s + "\t" + msgIn + "\r\n" );
//
//			System.out.print( "Log =" + s + "\t" + msgIn + "\r\n" );
//
//			out_.flush( );
//		}
//		catch( IOException ex )
//		{
//			ex.printStackTrace( );
//
//			System.out.println( "file log" + ex );
//		}
//		//date_ = null;
//		//dateFormat_ = null;
            errorMaster_.insertError(msgIn);

	}

	// Below methods are not used right now.
//	public void setLevel( String logLevelIn )
//	{
//		logLevel_ = logLevelIn;
//
//	}
//
//	public String getLevel( )
//	{
//		return logLevel_;
//	}
//
//	public void logMsg( String level, String msgIn )
//	{
//
//	}

}
