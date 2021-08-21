package servlet.history;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.output.ByteArrayOutputStream;

import utils.ErrorLogger;
import utils.PathBuilder;
import utils.StringHolder;

import com.google.gson.Gson;

import core.login.SessionData;
import utils.ErrorMaster;

/**
 * Servlet implementation class EDIFileDownloadServlet
 */
@WebServlet("/EDIFileDownloadServlet")
public class EDIFileDownloadServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	ServletOutputStream outStream_;
        private static ErrorMaster errorMaster_ = null;



	
	
	public EDIFileDownloadServlet()
	{
		
		super( );
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}

	
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		
	}

	

	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It is used to download transaction EDI files according to request type.
	 * 
	 */
	
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger  = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		outStream_ = response.getOutputStream( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		try
        {		
			String msg = "Info::EDIFileDownloadServlet.doPost() -  Request for "
						  + request.getParameter( "RequestType" ) + 
						  " for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			errorMaster_.insert( msg );
			
			String requestType = request.getParameter( "RequestType" );
			
			long transId = Long.parseLong( request.getParameter( "TransId" ) );
			
			String transTypes = request.getParameter( "TransTypes" );
			
					
			if( requestType.equals( "LatestEDIFiles" ) )
            {
	            dowloadLatestFiles( transId,response );
            }	
			
			
			else if( requestType.equals( "SpecificEDIFiles" ))
			{
				downloadSpecificFiles( transId, transTypes,response );
			}
			
			outStream_.close( );
			
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::EDIFilesDownloadServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			Map<String, String> jsonMap = new HashMap<String, String>( );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			jsonMap = null;
			
			response.getWriter( ).write( jsonStr );
			
        }
	}
	
	// Used to download all the edi files of the given transaction
	private void dowloadLatestFiles( long transId, HttpServletResponse response )
	{
		PathBuilder pathBuilder = new PathBuilder( );
		
		StringHolder localPath = new StringHolder( );
		
		int result = pathBuilder.getEDIFilesDirPath( transId, localPath );
		
		pathBuilder = null;
		
		if( result != 0 )
			return;
		
		File folder = new File( localPath.value );
		
		List<String> filenames = new ArrayList<String>();
		
		for (final File fileEntry : folder.listFiles()) 
		{
	        if (!fileEntry.isDirectory()) 
	        {
	            filenames.add( localPath.value+"/"+fileEntry.getName( ) );
	        } 
	        
	    }
		
		downloadFile( filenames, response );
	}
	
	// Used to download specific edi files of the given transaction
	private void downloadSpecificFiles( long transId, String transTypes,HttpServletResponse response )
	{
		try
        {
			PathBuilder pathBuilder = new PathBuilder( );
			
			StringHolder localPath = new StringHolder( );
			
			int result = pathBuilder.getEDIFilesDirPath( transId, localPath );
			
			pathBuilder = null;
			
			if( result != 0 )
				return;
			
			
			String[] transTypesArr = transTypes.split( "," );
			
			//Create list for file URLs - these are files from all different locations
		    List<String> filenames = new ArrayList<String>();
		    
			
			for( String transType : transTypesArr )
	        {
				errorMaster_.insert("for loop");

		        if( transType.equals( "RFQ" ))
		        {
		        	filenames.add( localPath.value+"/rfq.xml" );
		        	
		        	
		        }
		        else if( transType.equals( "Quote" ))
		        {
		        	filenames.add( localPath.value+"/quote.xml" );
		       
		        	
				}
		        else if( transType.equals( "PO" ))
		        {
		        	filenames.add( localPath.value+"/po.xml" );
		        	 
		        	 
				}
		        else if( transType.equals( "Invoice" ))
		        {
		        	filenames.add( localPath.value+"/invoice.xml" );
		        	
		        	 
				}
		       
	        }
			
			downloadFile( filenames, response );
		    
		   
        }
        catch( Exception e )
        {
        	String msg = "Exception::EDIFilesDownloadServlet.doPost() - "+e+"\r\n\n";

			ErrorLogger.instance( ).logMsg( msg );
			
        }
		
	}
	

/*
 * Method : downloadFile( )
 * 
 * Input : list of filepaths , HttpServletResponse object
 * 
 * Return : none
 * 
 * Purpose: It is used to download the file from given path
 * 
 */

	
	private void downloadFile( List<String> filenames, HttpServletResponse response )
	{
		ErrorLogger errLogger  = ErrorLogger.instance( );
		
		try
		{
			  //..code to add URLs to the list
			byte [ ] buf = new byte[2048];

			// Create the ZIP file
			ByteArrayOutputStream baos = new ByteArrayOutputStream( );
			ZipOutputStream out = new ZipOutputStream( baos );

			// Compress the files
			for( int i = 0; i < filenames.size( ); i++ )
			{
				FileInputStream fis = new FileInputStream( filenames.get( i ).toString( ) );
				BufferedInputStream bis = new BufferedInputStream( fis );

				// Add ZIP entry to output stream.
				File file = new File( filenames.get( i ).toString( ) );
				String entryname = file.getName( );
				out.putNextEntry( new ZipEntry( entryname ) );
				int bytesRead;

				while( ( bytesRead = bis.read( buf ) ) != -1 )
				{
					out.write( buf, 0, bytesRead );
				}

				out.closeEntry( );
				bis.close( );
				fis.close( );
			}

			out.flush( );
			baos.flush( );
			out.close( );
			baos.close( );

			ServletOutputStream sos = response.getOutputStream( );
			response.setContentType( "application/zip" );
			response.setHeader( "Content-Disposition", "attachment; filename=\"EDI.zip\"" );
			sos.write( baos.toByteArray( ) );

			out.flush( );
			out.close( );
			sos.flush( );
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
