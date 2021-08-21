package servlet.vendorregn;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorLogger;

import com.google.gson.Gson;

import core.login.SessionData;

/**
 * Servlet implementation class W9FormDownloadServlet
 */
@WebServlet("/W9FormDownloadServlet")
public class W9FormDownloadServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public W9FormDownloadServlet()
	{
		super( );
		// TODO Auto-generated constructor stub
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
	 * Purpose: It is used to download the w9form 
	 * 
	 */
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger  = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		try
        {		
			String msg = "Info::W9FormDownloadServlet.doPost() -  Request for download w9 tax form " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			downloadFile( request.getParameter( "w9FormPath" ),response);
			
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::W9FormDownloadServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			Map<String, String> jsonMap = new HashMap<String, String>( );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			jsonMap = null;
			
			response.getWriter( ).write( jsonStr );
			
        }
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
	
	private void downloadFile( String localPath, HttpServletResponse response )
	{
		ErrorLogger errLogger  = ErrorLogger.instance( );
		
		try
		{

			File file = new File( localPath );
			
			//errorMaster_.insert("file path="+localPath);

			int length = 0;

			ServletOutputStream outStream = response.getOutputStream( );
			
			//errorMaster_.insert("output stream object created");

			ServletContext context = getServletConfig( ).getServletContext( );
			
			//errorMaster_.insert("Servlet context created");

			String mimetype = context.getMimeType( localPath );

			//errorMaster_.insert("Mime type created");
			
			// sets response content type

			if( mimetype == null )
			{
				mimetype = "application/octet-stream";
				
				//errorMaster_.insert("mime type hardcoded");
			}
			response.setContentType( mimetype );
			
			//errorMaster_.insert("Content type has been set to the response as mime type");

			response.setContentLength( (int)file.length( ) );
			
			//errorMaster_.insert("content length has been set to response");

			String fileName = ( new File( localPath ) ).getName( );
			
		//	errorMaster_.insert("File Name = "+fileName);

			// sets HTTP header
			response.setHeader( "Content-Disposition", "attachment; filename=\"" + fileName
			        + "\"" );
			
			//errorMaster_.insert("Header has been set to response");

			byte [ ] byteBuffer = new byte[4096];

			DataInputStream in = new DataInputStream( new FileInputStream( file ) );
			
			//errorMaster_.insert("DataInputStream created");

			// reads the file's bytes and writes them to the response stream
			while( ( in != null ) && ( ( length = in.read( byteBuffer ) ) != -1 ) )
			{
				outStream.write( byteBuffer, 0, length );
			}
			
			//errorMaster_.insert("File's byte has been read and written to response stream");

			in.close( );
			
			//errorMaster_.insert("DataInputStream closed");
			
			outStream.close( );

			//errorMaster_.insert( "Download file request completed"+localPath );
		}

		
		catch( Exception e )
		{
			String msg = "Exception::DownloadFileServlet.doPost() - " + e + "\r\n\n";
			
			e.printStackTrace( );

			errLogger.logMsg( msg );
		}
		
	}

}
