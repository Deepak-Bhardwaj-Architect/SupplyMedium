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
package servlet.vendorregn;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import utils.JSONEncode;

import com.google.gson.Gson;


import core.regn.MailingAddressData;

/**
 * File:  VRMailingComposer.java 
 *
 * Created on Oct 30, 2013 2:24:37 PM
 */
public class VRMailingComposer
{
	
	
	/*
	 * Method : VRMailingComposer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public VRMailingComposer()
	{
		
	}

	
	
	
	/*
	 * Method: composeVRMailingJSON
	 * 
	 * Input: int response, List of MailingAddressData
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the mailingAddressDataList object to json string
	 */
	
	
    public String composeVRMailingJSON( int responseCode,
            List<MailingAddressData> mailingAddressDataList )
    {
    	String jsonString = "";

		JSONEncode jsonEncode = new JSONEncode( );

		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );

		if( responseCode == 17000 || responseCode == 17010 || responseCode == 17020 || responseCode == 17030 )
		{
			String servletMsg = "Info::VRMailingServlet.doPost() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			// System.out.println( "response code="+responseCode );

			if( responseCode == 17000  )
			{
				
				System.out.println( "list count ="+mailingAddressDataList.size( ));
				
				jsonMap.put( "result", "success" );

				jsonMap.put( "insertAddrId", ""+mailingAddressDataList.get( 0 ).addrid_ );

				jsonString = new Gson( ).toJson( jsonMap );

				jsonMap = null;

				return jsonString;

			}
			else if( responseCode == 17010 )
			{
				jsonMap.put( "result", "success" );

				String responseString = ErrorCodeConfigReader.instance( ).get(
				        Integer.toString( responseCode ) );

				jsonMap.put( "message", responseString );

				jsonString = new Gson( ).toJson( jsonMap );

				jsonMap = null;

				return jsonString;

				
			}

			else
			{
				// System.out.println( "response code="+responseCode );

				int mailingAddressCount = mailingAddressDataList.size( );

				jsonString = "{ \"result\" : \"success\",  ";

				jsonString = jsonString + "\"Addresses\" : [";

				for( int i = 0; i < mailingAddressCount; i++ )
				{
					MailingAddressData mailingAddressData = mailingAddressDataList.get( i );

					if( i != 0 ) jsonString = jsonString + ",";

					jsonString = jsonString + "{ \"addrId\" : \""
					        + mailingAddressData.addrid_ + "\", ";

					jsonString = jsonString + " \"address\" : \""
					        + jsonEncode.encode( mailingAddressData.address_ ) + "\", ";
					
					jsonString = jsonString + " \"city\" : \""
					        + jsonEncode.encode( mailingAddressData.city_ ) + "\", ";
					
					jsonString = jsonString + " \"state\" : \""
					        + jsonEncode.encode( mailingAddressData.state_) + "\", ";
				
					
					jsonString = jsonString + " \" zipcode\" : \""
					        + jsonEncode.encode( mailingAddressData.zipcode_ ) + "\", ";
					
					jsonString = jsonString + " \"country\" : \""
					        + jsonEncode.encode( mailingAddressData.countryRegion_) + "\", ";
					
					jsonString = jsonString + " \"companyRegnKey\" : \""
					        +  mailingAddressData.companyRegnKey_ + "\", ";
					
					
					jsonString = jsonString + " \"emailId\" : \""
					        + jsonEncode.encode( mailingAddressData.emailid_) + "\", ";
					
					jsonString = jsonString + " \"addressType\" : \""
					        + jsonEncode.encode( mailingAddressData.addressType_) + "\" ";
					
					jsonString = jsonString + "}";
					
				}

				jsonString = jsonString + "]}";

				jsonEncode = null;
				
				System.out.println( "json str=" + jsonString );

				return jsonString;

			}

		}
		else
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::VRMailingComposer.doPost() - "
			        + "Request failed - Error code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );

			jsonMap.put( "message", responseString );

			jsonString = new Gson( ).toJson( jsonMap );

			jsonMap = null;

			System.out.println( "json str=" + jsonString );

			return jsonString;

		}
    }

}
