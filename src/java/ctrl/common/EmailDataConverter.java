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
package ctrl.common;

/**
 * File:  EmailDataConverter.java 
 *
 * Created on Oct 2, 2013 6:36:12 PM
 */

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import core.common.EmailData;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import utils.ErrorMaster;

public class EmailDataConverter
{

    private static ErrorMaster errorMaster_ = null;



    public EmailDataConverter()
    {
        if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
    }
	/* To convert request into EmailData */

	public int getEmailData( HttpServletRequest request, EmailData mailData )
	{
		try
		{
			
			String emailJSON = request.getParameter( "Email" ).toString( );

			JsonParser jsonParser = new JsonParser( );

			JsonObject emailJSONData = (JsonObject)jsonParser.parse( emailJSON );

			// Setting customer regn key

			String cusKeys = emailJSONData.get( "customerKeys" ).getAsString( );

			String [ ] customer = cusKeys.split( "," );

			for( int i = 0; i < customer.length; i++ )
			{
				String cusString = customer[i];

				UserProfileKey profileKey = new UserProfileKey( );

				profileKey.email_ = cusString;

				mailData.customerKeys_.add( profileKey );

				profileKey = null;
			}

			String message = emailJSONData.get( "message" ).getAsString( );

			mailData.message_ = message;
			
			String companyName=emailJSONData.get("companyName").getAsString( );
			
			mailData.companyName_=companyName;
			
			String senderName=emailJSONData.get("senderName").getAsString( );
			
			mailData.senderName_=senderName;
			
			
			String senderKey=emailJSONData.get("senderKey").getAsString( );
			
			UserProfileKey userProfileKey = new UserProfileKey( );
			
			userProfileKey.email_ = senderKey;
			
			mailData.senderKey_=userProfileKey;
			
			userProfileKey = null;



			errorMaster_.insert( "message_" + mailData.message_ );
			
			
			return 0;
		}
		
		catch( Exception ex )
		{
			
			ErrorLogger.instance( ).logMsg(
			        "Exception::EmailDataConverter.getEmailData() "
			                + "- Unable to parse request - " + ex );

			return -1;
		}
	}

}
