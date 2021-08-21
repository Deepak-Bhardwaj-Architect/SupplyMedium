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
package servlet.history;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import utils.ErrorLogger;

import com.google.gson.Gson;

/**
 * File:  DownloadTask.java 
 *
 * Created on 11-Nov-2013 3:44:00 PM
 */
class DownloadTask implements Runnable 
{

    private String path_;
    
    private HttpServletResponse response_;
    
    private ServletContext context_;
    
    ServletOutputStream outStream_;

    public DownloadTask(  String localPath, HttpServletResponse response, ServletContext context,ServletOutputStream outStream ) 
    {
        this.path_ = localPath;
        
        this.response_ = response;
        
        this.context_ = context;
        
        this.outStream_ = outStream;
    }

    @Override
    public void run() {
        // surround with try-catch if downloadFile() throws something
        downloadFile( path_, response_, context_);
        
        System.out.println( "Run method" );
    }


/*
 * Method : downloadFile( )
 * 
 * Input : String localPath , HttpServletResponse object
 * 
 * Return : none
 * 
 * Purpose: It is used to download the file from given path
 * 
 */

private void downloadFile( String localPath, HttpServletResponse response, ServletContext context )
{
	ErrorLogger errLogger  = ErrorLogger.instance( );
	
	try
	{

		File file = new File( localPath );
		
		//System.out.println( localPath );

		int length = 0;

		String mimetype = context.getMimeType( localPath );

		// sets response content type

		if( mimetype == null )
		{
			mimetype = "application/octet-stream";
		}
		response.setContentType( mimetype );

		response.setContentLength( (int)file.length( ) );

		String fileName = ( new File( localPath ) ).getName( );

		// sets HTTP header
		response.setHeader( "Content-Disposition", "attachment; filename=\"" + fileName
		        + "\"" );

		byte [ ] byteBuffer = new byte[4096];

		DataInputStream in = new DataInputStream( new FileInputStream( file ) );
		

		// reads the file's bytes and writes them to the response stream
		while( ( in != null ) && ( ( length = in.read( byteBuffer ) ) != -1 ) )
		{
			outStream_ .write( byteBuffer, 0, length );
		}

		in.close( );
		

		System.out.println( "Download file request completed"+localPath );
	}

	catch( IOException e )
	{
		String msg = "Exception::EDIFilesDownloadServlet.doPost() - " + e + "\r\n\n";

		errLogger.logMsg( msg );

		Map<String, String> jsonMap = new HashMap<String, String>( );

		jsonMap.put( "result", "failed" );

		jsonMap.put( "message", "Request failed. Try again." );

		String jsonStr = new Gson( ).toJson( jsonMap );

		jsonMap = null;

		try
        {
            response.getWriter( ).write( jsonStr );
        }
        catch( IOException e1 )
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
	}
	catch( Exception e )
	{
		String msg = "Exception::EDIFilesDownloadServlet.doPost() - " + e + "\r\n\n";

		errLogger.logMsg( msg );

		Map<String, String> jsonMap = new HashMap<String, String>( );

		jsonMap.put( "result", "failed" );

		jsonMap.put( "message", "Request failed. Try again." );

		String jsonStr = new Gson( ).toJson( jsonMap );

		jsonMap = null;

		try
        {
            response.getWriter( ).write( jsonStr );
        }
        catch( IOException e1 )
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

	}
	
}
}