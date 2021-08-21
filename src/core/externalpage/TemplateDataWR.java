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
package core.externalpage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import utils.ErrorLogger;

import com.google.gson.Gson;

/**
 * @FileName : TemplateDataWR.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 11, 2013
 * 
 * @Purpose : It will Read and Write From the Files as JSON Formate
 * 
 */
public class TemplateDataWR
{
	public static ITemplateData DeSerialize( ITemplateData list )
	{
		try
		{
			File file = new File( list.getPath( ) );

			if( file.exists( ) )
			{

				Gson json_ = null;
				BufferedReader bRader_ = null;
				json_ = new Gson( );

				bRader_ = new BufferedReader( new FileReader( new File( list.getPath( ) ).getAbsolutePath( ) ) );
				list = json_.fromJson( bRader_, list.getClass( ) );
				System.out.println( "File DeSerialized Successfully " + list.getPath( ) );
				
				ErrorLogger.instance( ).logMsg( "File DeSerialized Successfully " + list.getPath( ) );
				
				return list;
			}
			else
			{
				return list = null;
			}
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace( );
			list = null;
			return list;
		}

	}

	public static int Serialize( ITemplateData data )
	{
		PrintWriter _os;
		try
		{
			File file = new File( data.getDirectory( ) );

			if( !file.isDirectory( ) )
			{
				file.mkdirs( );
			}

			file = null;
			file = new File( data.getPath( ) );

			if( !file.exists( ) )
			{
				file.createNewFile( );
			}

			_os = new PrintWriter( new FileWriter( file ) );
			Gson js = new Gson( );
			String value = js.toJson( data, data.getClass( ) );

			//value = value.replace( "{", "\r\n\r\n{\r\t" );
			//value = value.replace( "}", "\r\n}" );
			//value = value.replace( ",", ",\r\n\t" );
			
			_os.write( value );
			_os.close( );

			System.out.println( "File Serialized Successfully " + data.getPath( ) );
			
			ErrorLogger.instance( ).logMsg( "File Serialized Successfully " + data.getPath( )  );

			return 0;
		}
		catch( IOException e )
		{
			e.printStackTrace( );

			return -1;
		}

	}
}
