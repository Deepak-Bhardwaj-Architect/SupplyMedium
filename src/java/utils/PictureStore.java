package utils;


import org.apache.commons.fileupload.FileItem;

import java.io.*;


public class PictureStore
{

	public int storeImage( Object image,String path,String fileName,StringHolder storedPath )
	{

		//System.out.println("path;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;"+path);	
                
                try
		{
                    File uploadDir = new File( path );                

                if(!path.contains("null-null"))
                {
                    System.out.println("folder path....................................."+path);
                    if( !uploadDir.exists( ) )
                    {
                            System.out.println("check exist.....................................");
                            uploadDir.mkdirs( );
                            System.out.println("folder created.....................................");
                    }
                }

			//Object fileObject = request.getAttribute( "logo" );

			// FileItem item = (FileItem)iter.next( );
			FileItem item = (FileItem)image;
			// processes only fields that are not form fields
			if( !item.isFormField( ) )
			{
				String UploadedFileName = new File( item.getName( ) ).getName( );
				
				UploadedFileName = UploadedFileName.replace( ".", ":" );
				
				String[] fileNameStrArr = UploadedFileName.split( ":" );
				
				String extension="";
				
				if( fileNameStrArr.length>1 )
				{
					 extension ="."+ fileNameStrArr[1];
				}
				else
				{
					extension = ".png";
				}
				
				fileName = fileName+extension;				
				
				path = path +"/"+  fileName;
                                
				File storeFile = new File( path );

				// saves the file on disk
				item.write( storeFile );

				storedPath.value = path;

				return 0;

			}

		}
		catch( Exception ex )
		{
                    System.out.println("Exception-----------------"+ex);
			ErrorLogger errLogger = ErrorLogger.instance( );
        	
			errLogger.logMsg( "Exception::PictureStore.storeImage() - " + ex );

			return -1;
		}
		
		return 0;
		
	}
}
