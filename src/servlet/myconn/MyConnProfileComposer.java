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
package servlet.myconn;



import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import utils.PathBuilder;
import utils.StringHolder;
import core.myconn.MyConnProfileData;


/**
 * File:  MyConnProfileComposer.java 
 *
 * Created on 21-Aug-2013 2:42:58 PM
 */
public class MyConnProfileComposer
{

	/*
	 * Method : MyConnProfileComposer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnProfileComposer()
	{
	}
	

	/*
	 * Method: composeProfileJSON
	 * 
	 * Input: int response, MyConnProfileData myConnProfileData
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the myConnProfileData and compose JSON string
	 */
	
	public String composeProfileJSON( int responseCode, MyConnProfileData myConnProfileData )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		System.out.println( "Response Code="+responseCode );
		
		if( responseCode == 10060 )
		{
			

			jsonStr = jsonStr + "{ \"result\" : \"success\",  ";

			String msg = "Info::MyConnProfileServletServlet.doPost() - Request successful - Response code - "
			        + responseCode + "\r\n\n\n";

			errLogger.logMsg( msg );

			jsonStr = jsonStr + " \"name\" : \"" + myConnProfileData.name_+ "\", ";
			
			jsonStr = jsonStr + " \"email\" : \"" + myConnProfileData.email_+ "\", ";
			
			jsonStr = jsonStr + " \"department\" : \"" + myConnProfileData.department_+ "\", ";
			
			jsonStr = jsonStr + " \"role\" : \"" + myConnProfileData.role_+ "\", ";
			
			jsonStr = jsonStr + " \"companyName\" : \"" + myConnProfileData.companyName_+ "\", ";
			
			jsonStr = jsonStr + " \"companyType\" : \"" + myConnProfileData.companyType_+ "\", ";
			
			jsonStr = jsonStr + " \"businessCategory\" : \"" + myConnProfileData.businessCategory_+ "\", ";
			
			jsonStr = jsonStr + " \"address\" : \"" + myConnProfileData.address_+ "\", ";
			
			jsonStr = jsonStr + " \"phoneNo\" : \"" + myConnProfileData.phoneNo_+ "\", ";
			
			jsonStr = jsonStr + " \"cell\" : \"" + myConnProfileData.cell_+ "\", ";
			
			String profileImagPath = myConnProfileData.profileImagePath_;
			
			if( profileImagPath != null )
			{
				profileImagPath = profileImagPath.replace( "\\", "\\\\" );
				
				PathBuilder pathBuilder = new PathBuilder( );
				
				StringHolder webURL = new StringHolder( );
				
				pathBuilder.getWebURLFromRelativepath( profileImagPath, webURL );
				
				profileImagPath = webURL.value;
				
				pathBuilder = null;
				
				webURL = null;
			}
			
			
			
			jsonStr = jsonStr + " \"imagePath\" : \"" +profileImagPath+ "\", ";
			
			jsonStr = jsonStr + " \"fax\" : \"" + myConnProfileData.fax_+ "\" ";
			
			jsonStr = jsonStr + "}";
		}
		else 
		{
			
			jsonStr = "{ \"result\" : \"failed\",  ";
			
			String msg = "Info::MyConnNetworkServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
		
		}
		

		return jsonStr;
	}


}
