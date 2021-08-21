package servlet.common;

import utils.ErrorLogger;
import com.google.gson.Gson;


import core.login.SessionData;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

/**
 * Servlet implementation class FileReadServlet
 */
@WebServlet("/FileReadServlet")
public class FileReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileReadServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose:  
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			

			ErrorLogger errLogger = ErrorLogger.instance( );

			HttpSession session = request.getSession( );

			SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );

			response.setContentType( "application/json" );

			response.setCharacterEncoding( "UTF-8" );

			try
			{
				String msg = "Info::FileReadServlet.doPost() -  Request : "
				        + request.getParameter( "RequestType" ) + "<"
				        + sessionData.companyRegnKeyStr_ + ">, " + "<"
				        + sessionData.companyName_ + "> by user <" + sessionData.username_
				        + ">";

				errLogger.logMsg( msg );
				

				Object fileObject = request.getAttribute( "file" );
				//errorMaster_.insert( fileObject);
				
				String result = processFile( fileObject );
				//errorMaster_.insert(result );
				
				result = result.replaceAll("[^\\x00-\\x7F]", "");

				Map<String, String> jsonMap = new HashMap<String, String>( );

				jsonMap.put( "result", "success" );

				jsonMap.put( "message", result );

				String jsonStr = new Gson( ).toJson( jsonMap );

				jsonMap = null;

				response.getWriter( ).write( jsonStr );
			}

			catch( Exception e )
			{
				String msg = "Exception::FileReadServlet.doPost() - " + e + "\r\n\n";
				

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
		 * Method : processFile
		 * 
		 * Input  : Object fileObject
		 * 
		 * Return : int success/fail
		 * 
		 * Purpose: This method is used to read the Fileitem obj file 
		 */
		private String processFile( Object fileObject )
		{
			try
			{
				String str = "";

				FileItem file = (FileItem)fileObject;

				InputStream in = file.getInputStream( );

				BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( in ) );
				
				/*String line = bufferedReader.readLine( );

				while( line != null )
				{

					lineString = lineString + "" + line;
						



					line = bufferedReader.readLine( );
						
				}*/
				

		         int value=0;
		         
		         // reads to the end of the stream 
		         while((value = bufferedReader.read()) != -1)
		         {
		            // converts int to character
		            char c = (char)value;
		            
		           str = str+c;
		         }

				

				return str;
				
			}
			catch( Exception e )
			{
				
				return "";
			}

		}	
}
