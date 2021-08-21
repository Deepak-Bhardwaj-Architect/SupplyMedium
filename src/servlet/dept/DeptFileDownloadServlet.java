package servlet.dept;

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

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

import com.google.gson.Gson;

import core.dept.FileData;
import core.login.SessionData;
import ctrl.dept.DeptPageController;

/**
 * Servlet implementation class DeptFileDownloadServlet
 */
@WebServlet("/DeptFileDownloadServlet")
public class DeptFileDownloadServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public DeptFileDownloadServlet()
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
	 * Purpose: It is used to download the file from department page.
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
			String msg = "Info::DeptFilesMgmtServlet.doPost() -  Request for "
						  + request.getParameter( "RequestType" ) + 
						  " for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			DeptPageController deptPageCtrl = new DeptPageController( );
			
			FileData fileData = new FileData( );
			
			int responseCode = deptPageCtrl.manageFiles( request,fileData );
			
			deptPageCtrl = null;
			
			System.out.println( "Download file response code="+responseCode );
			
			if( responseCode == 1730 )
			{
				downloadFile( fileData.attrData_.localPath_, response );
			}
			else
			{
				Map<String, String> jsonMap = new HashMap<String, String>( );
				
				String responseString = ErrorCodeConfigReader.instance( ).get( "" + responseCode );
				
				jsonMap.put( "result", "failed" );
				
				jsonMap.put( "message", responseString );
				
				String jsonStr = new Gson( ).toJson( jsonMap );
				
				jsonMap = null;
				
				response.getWriter( ).write( jsonStr );
			}
			
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::DeptFilesDownloadServlet.doPost() - "+e+"\r\n\n";

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
			
			//System.out.println( localPath );

			int length = 0;

			ServletOutputStream outStream = response.getOutputStream( );

			ServletContext context = getServletConfig( ).getServletContext( );

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
				outStream.write( byteBuffer, 0, length );
			}

			in.close( );
			
			outStream.close( );

			System.out.println( "Download file request completed"+localPath );
		}

		catch( IOException e )
		{
			String msg = "Exception::DeptFilesMgmtServlet.doPost() - " + e + "\r\n\n";

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
			String msg = "Exception::DeptFilesMgmtServlet.doPost() - " + e + "\r\n\n";

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
