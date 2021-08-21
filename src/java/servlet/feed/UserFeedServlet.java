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

import core.feed.UserFeedData;
import core.login.SessionData;
import ctrl.feed.UserFeedController;
import utils.ErrorMaster;

/**
 * File:  UserFeedServlet.java 
 *
 * Created on 22-Apr-2013 11:30:24 AM
 */

@WebServlet("/UserFeedServlet")
public class UserFeedServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
        private static ErrorMaster errorMaster_ = null;




	
	public UserFeedServlet()
	{
		super( );
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
		
		try
        {
		
			String msg = "Info::UserFeedServlet.doPost() -  Request to  " +request.getParameter( "RequestType" )+
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
													

			errLogger.logMsg( msg );
			
			errorMaster_.insert( "Message="+msg );
			
			List<UserFeedData> userFeedList = new ArrayList<UserFeedData>( );
			
			UserFeedController feedCtrl = new UserFeedController( );
			
			int result = feedCtrl.processRequest( request, userFeedList );
			
			//errorMaster_.insert( "userfeedlistcount="+userFeedList.size( ) );
		
			
			String jsonStr = composeResponseJSON( result, request, userFeedList );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	Map<String, String> jsonMap = new HashMap<String, String>( );
        	
        	String msg = "Exception::UserFeedServlet.doPost() - "+e+"\r\n\n";

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
	 * Input : int responseCode,request object and user feed list
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the response in to json
	 * string
	 */

	private String composeResponseJSON( int responseCode, HttpServletRequest request,List<UserFeedData> userFeedList  )
	{
		String jsonString = "";
		
		JSONEncode jsonEncode = new JSONEncode( );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );
		
		if( responseCode == 1800 || responseCode == 1810 || responseCode == 1820 )
		{
			String servletMsg = "Info::UserFeedServlet.doPost() - " +
					"Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );
			
			//errorMaster_.insert( "response code="+responseCode );
			
			if( responseCode == 1810 )
			{
				jsonMap.put( "result", "success" );
				
				String responseString = ErrorCodeConfigReader.instance( ).get( Integer.toString( responseCode ) );
				
				jsonMap.put( "message", responseString );
				
				jsonString = new Gson( ).  toJson( jsonMap );
				
				jsonMap = null;
				
				errorMaster_.insert( "json str="+jsonString );

				return jsonString;
				
			}
			
			else
			{
				//errorMaster_.insert( "response code="+responseCode );
				
				int feedsCount = userFeedList.size( );
				
				if( feedsCount > 10)
					feedsCount = 10;
				
				
				jsonString = "{ \"result\" : \"success\",  ";
				
				jsonString = jsonString + "\"feeds\" : [";
				
				
				
				for( int i=0;i<feedsCount;i++ )
                {
					UserFeedData userFeedData = userFeedList.get( i );
					
					
					
					if( i!= 0 )
						jsonString =jsonString+",";
					
	                jsonString = jsonString + "{ \"userFeedId\" : \""+userFeedData.userFeedId_+"\", ";
	                
	              
	                jsonString = jsonString + " \"feedTitle\" : \""+jsonEncode.encode( userFeedData.feedTitle_)+"\", ";
	                
	                jsonString = jsonString + " \"feedDesc\" : \""+jsonEncode.encode(userFeedData.feed_)+"\", ";
	                
	                
	                // Creating web path from relative path
	               StringHolder webPath = new StringHolder( );
	               
	               PathBuilder pathBuilder = new PathBuilder( );
	               
	                pathBuilder.getWebURLFromRelativepath( userFeedData.relativePath_, webPath );
	                
	                jsonString = jsonString + " \"imageURL\" : \""+webPath.value+"\", ";
	                
	                pathBuilder = null;
	                
	                webPath = null;
	                
	                
	                
	                SimpleDateFormat dateFormat = new SimpleDateFormat( "dd MMM yyyy ',' hh:mm aaa");
	               
	                String dateStr =dateFormat.format( userFeedData.createdTimestamp_ );
	               
	                jsonString = jsonString + " \"createdTime\" : \""+dateStr+"\" ";  
	                
	                jsonString = jsonString + "}";
	                
                }
				
				jsonString = jsonString + "]}";
				
				errorMaster_.insert( "feed ="+jsonString );
				
				jsonEncode = null;

				return jsonString;
				
			}
			
		}
		else 
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::UserFeedServlet.doPost() - "
			        + "Request failed - Error code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );

			jsonMap.put( "message", responseString );
			
			jsonString = new Gson( ).  toJson( jsonMap );
			
			jsonMap = null;
			
			errorMaster_.insert( "json str="+jsonString );

			return jsonString;
			
		}
	
		
	}

}
