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

import java.io.File;

import org.apache.commons.fileupload.FileItem;

import utils.ErrorLogger;
import utils.StringHolder;

/**
 * @FileName : TemplateStoreImage.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 19, 2013
 * 
 * @Purpose :
 * 
 */
public class TemplateStoreImage
{
	public int storeImage( Object image, String path, String fileName, StringHolder storedFileName )
	{

		// creates the directory if it does not exist
		File uploadDir = new File( path );

		String [ ] fileNamesplit = fileName.split( "\\." );
		fileName = fileNamesplit[0];

		if( !uploadDir.exists( ) )
		{
			uploadDir.mkdirs( );
		}

		try
		{

			// FileItem item = (FileItem)iter.next( );
			FileItem item = (FileItem)image;
			// processes only fields that are not form fields
			if( !item.isFormField( ) )
			{
				String UploadedFileName = new File( item.getName( ) ).getName( );

				String [ ] fileNameStrArr = UploadedFileName.split( "\\." );

				String extension = "";

				if( fileNameStrArr.length > 1 )
				{
					extension = "." + fileNameStrArr[1];
				}
				else
				{
					extension = ".png";
				}

				fileName = fileName.toUpperCase( ) + extension.toUpperCase( );

				path = path + fileName;
				File storeFile = new File( path );

				// saves the file on disk
				item.write( storeFile );

				storedFileName.value = fileName.toUpperCase( );

				return 0;

			}

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );

			errLogger.logMsg( "Exception::TemplateStoreImage.storeImage() - " + ex );

			return -1;
		}

		return 0;

	}

}
