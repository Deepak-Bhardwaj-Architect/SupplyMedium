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

package servlet.dept;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.dept.DeptFolderData;
import core.dept.FileData;

import ctrl.dept.DeptPageController;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

/**
 * File:  DeptFetchServlet.java 
 *
 * Created on May 11, 2013 11:17:28 PM
 */

@WebServlet("/DeptFolderFetchServlet")
public class DeptFolderFetchServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public DeptFolderFetchServlet()
	{
		super( );

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
	 * Purpose: It get the request from browser. Fetch the all the folders for
	 *given department key. Then send the result to jsp.
	 * 
	 */
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger  = ErrorLogger.instance( );
		
		try
        {
			String servletMsg = "Info::DeptFolderServlet.doPost() "
			        + "Fetch folders request ";
			
			errLogger.logMsg( servletMsg );
			
	        DeptPageController deptPageCtrl = new DeptPageController( );
	        
	        List<DeptFolderData> folderDataList = new ArrayList<DeptFolderData>( );
	        
	        int result = deptPageCtrl.getAllFiles( request, folderDataList );
	        
	        String jsonStr = composeResponseJSON( result, request, folderDataList );
	        
	        response.setContentType( "application/json" );

			response.setCharacterEncoding( "UTF-8" );

			response.getWriter( ).write( jsonStr );
	        
        }
        catch( Exception e )
        {
        	String msg = "Exception::DeptFolderFetchServlet.doPost() - "+e+"\r\n\n";

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
	 * Method : composeResponseJSON( ) 
	 * 
	 * Input : int responseCode,request object and folderDataList
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the response in to json
	 * string
	 */

	private String composeResponseJSON( int responseCode, HttpServletRequest request,List<DeptFolderData> folderDataList  )
	{
		String jsonString = "";
		
		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );
		
		
		if( responseCode == 1715  )
		{
			String servletMsg = "Info::SearchSupplierServlet.composeResponseJSON() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			jsonString = "{ \"result\" : \"success\",  ";

			jsonString = jsonString + "\"folders\" : [";
			
			int i=0,j=0;

			for( DeptFolderData deptFolderData : folderDataList )
			{
				
				j = 0;

				if( i != 0 ) jsonString = jsonString + ",";
				
				jsonString = jsonString + "{ \"folderKey\" : \""
				        + deptFolderData.deptFolderKey_.toString( ) + "\", ";

				jsonString = jsonString + " \"folderName\" : \""
				        + deptFolderData.folderName_+ "\", ";
				
				jsonString = jsonString + " \"recycleFlag\" : \""
				        + deptFolderData.recycleFlag_+ "\", ";
				
				jsonString = jsonString + " \"files\" : [";
				        
				for( FileData fileData : deptFolderData.filesArr_ )
				{
					if( j != 0 ) jsonString = jsonString + ",";
					
					jsonString = jsonString + "{ \"fileId\" : \""
					        + fileData.attrData_.deptFileId_ + "\", ";

					jsonString = jsonString + " \"fileName\" : \""
					        + fileData.attrData_.fileName_ + "\", ";
					
					jsonString = jsonString + " \"fileType\" : \""
					        + fileData.attrData_.fileType_ + "\", ";
					
					jsonString = jsonString + " \"recycleFlag\" : \""
					        + fileData.attrData_.recycleFlag_ + "\", ";
					
					jsonString = jsonString + " \"fileSize\" : \""
					        + fileData.attrData_.fileSize_ + "\", ";
					
					SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy");
		               
	                String dateStr =dateFormat.format( fileData.attrData_.createdTs_ );
	                
					jsonString = jsonString + " \"createdDate\" : \""
					        + dateStr+ "\", ";
					
					jsonString = jsonString + " \"webURL\" : \""
					        + fileData.attrData_.webUrl_ + "\" ";
					
					jsonString = jsonString + "}";
					
					j++;
				}
				
				jsonString = jsonString + "]";
				

				jsonString = jsonString + "}";
				
				i++;

			}

			jsonString = jsonString + "]}";
			
			
			System.out.println( "json="+jsonString );
			

			return jsonString;
			
			
		}
		else 
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::DeptFolderFetchServlet.composeResponseJSON() - "
			        + "Request failed - Error code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );

			jsonMap.put( "message", responseString );
			
			jsonString = new Gson( ).  toJson( jsonMap );
			
			jsonMap = null;
			
			System.out.println( "json str="+jsonString );

			return jsonString;
			
		}
	
		
	}


}
