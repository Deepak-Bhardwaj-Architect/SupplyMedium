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

import org.apache.commons.fileupload.FileItem;

/**
 * File:  FileStore.java 
 *
 * Created on May 8, 2013 12:15:27 PM
 */

/*
 * Class: FileStore
 * 
 * Purpose: This is used to store a file in the folder specified
 * 
 */

public class FileStore
{
	
	/*Constructor*/
	
	public FileStore( )
	{
		
	}
	
	/*
	 * Method: storeFile
	 * 
	 * Input: Object file, String path, String fileName, StringHolder storedPath (As ref)
	 * 
	 * Return: int
	 * 
	 * Purpose: This method stores the file object into the path specified.  The saved path
	 * will be filled in storedPath.
	 * 
	 */
	
	public int storeFile( Object file, String path,String fileName,StringHolder storedPath )
	{

		// creates the directory if it does not exist
		
		File uploadDir = new File( path );

		if( !uploadDir.exists( ) )
		{
			uploadDir.mkdirs( );
		}

		try
		{
			//Object fileObject = request.getAttribute( "logo" );

			// FileItem item = (FileItem)iter.next( );
			FileItem item = (FileItem)file;
			// processes only fields that are not form fields
			if( !item.isFormField( ) )
			{
				String UploadedFileName = new File( item.getName( ) ).getName( );
				
				System.out.println( "Uploaded file name - "+ UploadedFileName );
				
				UploadedFileName = UploadedFileName.replace( ".", ":" );
				
				String[] fileNameStrArr = UploadedFileName.split( ":" );
				System.out.println( "Str Length - "+ fileNameStrArr.length );
				String extension="";
				
				if( fileNameStrArr.length>1 )
				{
					 extension ="."+ fileNameStrArr[1];
					 System.out.println( "Uploaded file name - "+ UploadedFileName );
				}
				else
				{
					extension = ".png";
				}
				
				fileName = fileName+extension;
				
				
				path = path +  fileName;
				File storeFile = new File( path );

				// saves the file on disk
				item.write( storeFile );

				storedPath.value = path;

				return 0;

			}
			else 
			{
				ErrorLogger errLogger = ErrorLogger.instance( );
	        	
				errLogger.logMsg( "Error::PictureStore.storeFile() - The image is not from the form field ");
				
				return -2;
			}

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::PictureStore.storeFile() - " + ex );

			return -1;
		}
	}
	
	/*
	 * Method: deleteFile
	 * 
	 * Input: String path, String fileName
	 * 
	 * Return: int
	 * 
	 * Purpose: This method deletes the file object from the path specified. 
	 * 
	 */
	
	public int deleteFile( String path,String fileName )
	{
		try
		{
			File storeF  = new File( path );

			if(storeF.delete( ))
			{
				return 0;
			}
			else 
			{
				return -2;
			}
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::PictureStore.deletFile( ) - " + ex );

			return -1;
		}
	}
}
