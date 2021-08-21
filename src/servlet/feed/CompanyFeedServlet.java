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

package servlet.feed;

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
import javax.servlet.http.HttpSession;


import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import utils.JSONEncode;
import utils.PathBuilder;
import utils.StringHolder;

import com.google.gson.Gson;

import core.feed.CompanyFeedData;
import core.login.SessionData;
import ctrl.feed.CompanyFeedController;

/**
 * File:  CompanyFeedServlet.java 
 *
 * Created on 22-Apr-2013 11:30:24 AM
 */

@WebServlet("/CompanyFeedServlet")
public class CompanyFeedServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	
	public CompanyFeedServlet()
	{
		super( );
		
	}

	
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
	
	}

	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
		
			String msg = "Info::CompanyFeedServlet.doPost() -  Request to  " +request.getParameter( "RequestType" )+
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
													

			errLogger.logMsg( msg );
			
			System.out.println( "Message="+msg );
			
			List<CompanyFeedData> companyFeedList = new ArrayList<CompanyFeedData>( );
			
			CompanyFeedController feedCtrl = new CompanyFeedController( );
			
			int result = feedCtrl.processRequest( request, companyFeedList );
			
			//System.out.println( "companyfeedlistcount="+userFeedList.size( ) );
		
			
			String jsonStr = composeResponseJSON( result, request, companyFeedList );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::CompanyFeedServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }
	}
	
	

	/*
	 * Method : composeResponseJSON( ) 
	 * 
	 * Input : int responseCode,request object and company feed list
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the response in to json
	 * string
	 */

	private String composeResponseJSON( int responseCode, HttpServletRequest request,List<CompanyFeedData> companyFeedList  )
	{
		String jsonString = "";
		
		JSONEncode jsonEncode = new JSONEncode( );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );
		
		if( responseCode == 1600 || responseCode == 1610 || responseCode == 1620 || responseCode == 1625 )
		{
			String servletMsg = "Info::CompanyFeedServlet.doPost() - " +
					"Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );
			
			//System.out.println( "response code="+responseCode );
			
			if( responseCode == 1610 )
			{
				jsonMap.put( "result", "success" );
				
				String responseString = ErrorCodeConfigReader.instance( ).get( Integer.toString( responseCode ) );
				
				jsonMap.put( "message", responseString );
				
				jsonString = new Gson( ).  toJson( jsonMap );
				
				jsonMap = null;
				
				System.out.println( "json str="+jsonString );

				return jsonString;
				
			}
			
			else
			{
			
				int feedsCount = companyFeedList.size( );
				
				if( feedsCount > 10)
					feedsCount = 10;
				
				
				jsonString = "{ \"result\" : \"success\",  ";
				
				jsonString = jsonString + "\"feeds\" : [";
				
				
				
				for( int i=0;i<feedsCount;i++ )
                {
					CompanyFeedData companyFeedData = companyFeedList.get( i );
					
					StringHolder webURL = new StringHolder( );
					
					System.out.println( "Regn data.URL ="+ companyFeedData.userPictureUrl_ );
					
					System.out.println( "desc="+companyFeedData.feed_ );
					
					if( companyFeedData.userPictureUrl_ == null || companyFeedData.userPictureUrl_ .equals( "null" ) || 
							companyFeedData.userPictureUrl_ .equals( "" ) )
					{
						System.out.println( "if block" );
						
						webURL.value = "";
					}
					else
					{
						System.out.println( "else block" );
						
						PathBuilder pathBuilder = new PathBuilder( );
						
						pathBuilder.getWebURL( companyFeedData.userPictureUrl_, webURL );
						
						pathBuilder = null;
					}
					
					System.out.println( "After if else" );
					
					if( i!= 0 )
						jsonString =jsonString+",";
					
	                jsonString = jsonString + "{ \"companyFeedId\" : \""+companyFeedData.companyFeedId_+"\", "; 
	              
	                jsonString = jsonString + " \"feedTitle\" : \""+jsonEncode.encode( companyFeedData.feedTitle_ )+"\", ";
	                
	                jsonString = jsonString + " \"feedDesc\" : \""+jsonEncode.encode( companyFeedData.feed_ )+"\", ";
	                
	                jsonString = jsonString + "\"email\":\""+companyFeedData.userKey_.toString( )+"\",";
	                
	                jsonString = jsonString + "\"userName\":\""+companyFeedData.userName_+"\",";
	                
	                jsonString = jsonString + "\"userPictureURL\":\""+webURL.value+"\",";
	                
	                SimpleDateFormat dateFormat = new SimpleDateFormat( "dd MMM yyyy ',' hh:mm aaa");
	               
	                String dateStr =dateFormat.format( companyFeedData.createdTimestamp_ );
	              
	                jsonString = jsonString + " \"createdTime\" : \""+dateStr+"\" ";
	                
	                jsonString = jsonString + "}";
	                
	               
	             
                }
				
				jsonString = jsonString + "]}";
				
				System.out.println( "feed ="+jsonString );
				
				jsonEncode = null;

				return jsonString;
				
			}
			
		}
		else 
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::CompanyFeedServlet.doPost() - "
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
