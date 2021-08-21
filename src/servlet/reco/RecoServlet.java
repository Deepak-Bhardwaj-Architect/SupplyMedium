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

package servlet.reco;

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
import utils.PathBuilder;
import utils.StringHolder;

import com.google.gson.Gson;

import core.feed.UserFeedData;
import core.login.SessionData;
import core.regn.MailingAddressData;
import core.regn.RegnData;
import ctrl.feed.UserFeedController;
import ctrl.reco.RecoController;

/**
 * File:  RecoServlet.java 
 *
 * Created on May 11, 2013 11:17:28 PM
 */

@WebServlet("/RecoServlet")
public class RecoServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public RecoServlet()
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
	 * Purpose: It get the request from browser. It is used to get the recommended
	 * company for given company key
	 * 
	 */
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
			String msg = "Info::RecoServlet.doPost() -  Request to get recommended companies" +
					"<" + sessionData.companyRegnKeyStr_ + ">, " +
					"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
												

			errLogger.logMsg( msg );
		
			List<RegnData> regnDataList = new ArrayList<RegnData>( );
			
			RecoController recoCtrl = new RecoController( );
		
			int result = recoCtrl.getVendors( request, regnDataList );
			
			System.out.println( "result="+result );
		
			String jsonStr = composeResponseJSON( result, request, regnDataList );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::RecoServlet.doPost() - "+e+"\r\n\n";

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
	 * Input : int responseCode,request object and user feed list
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the response in to json
	 * string
	 */

	private String composeResponseJSON( int responseCode, HttpServletRequest request,List<RegnData> regnDataList  )
	{
		String jsonString = "";
		
		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );
		
	
			if( responseCode == 1795 )
			{
				
				int compCount = regnDataList.size( );
				
				System.out.println( "List count="+compCount );
				
				
				jsonString = "{ \"result\" : \"success\",  ";
				
				jsonString = jsonString + "\"companies\" : [";
				
				
				
				for( int i=0;i<compCount;i++ )
                {
					RegnData regnData = regnDataList.get( i );
					
					StringHolder webURL = new StringHolder( );
					
					System.out.println( "Regn data.URL ="+ regnData.logo_ );
					
					if( regnData.logo_ == null || regnData.logo_ .equals( "null" ) || 
							regnData.logo_ .equals( "" )  )
						
					{
						
						webURL.value = "";
					}
					else
					{
						PathBuilder pathBuilder = new PathBuilder( );
						
						pathBuilder.getWebURLFromRelativepath( regnData.logo_, webURL );
						
						pathBuilder = null;
					}
					
					
					if( i!= 0 )
						jsonString =jsonString+",";
					
	                jsonString = jsonString + "{ \"companyName\" : \""+regnData.companyName_+"\", ";
	                
	                jsonString = jsonString + " \"companyKey\" : \""+regnData.companyRegnKey_.toString( )+"\", ";
	                
	                jsonString = jsonString + " \"logoURL\" : \""+webURL.value+"\", ";
	                
	                jsonString = jsonString + " \"companyType\" : \""+regnData.companyType_+"\" ";
	                
	                List<MailingAddressData> mailingAddrList = regnData.mailingAddressArr_;
	                
	                if( mailingAddrList.size( )>0)
	                {
	                	MailingAddressData mailingAddressData = mailingAddrList.get( 0 );
	                	
	                	jsonString = jsonString +",";
	                	
	                	jsonString = jsonString + " \"city\" : \""+mailingAddressData.city_+"\", ";
	 	                
	 	                jsonString = jsonString + " \"country\" : \""+mailingAddressData.countryRegion_+"\" ";
	                }
	                
	               
	               
	                jsonString = jsonString + "}";
	                
                }
				
				jsonString = jsonString + "]}";
				
				System.out.println( "feed ="+jsonString );

				return jsonString;
				
			
		}
		else 
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::RecoServlet.doPost() - "
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
