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

package servlet.managefolder;

//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

//import com.google.gson.Gson;

import core.dept.DeptData;
import core.dept.DeptFolderData;
import core.managefolder.GroupFolderData;
import core.managefolder.UserFolderData;
import core.regn.UserProfileData;
import core.usermgmt.GroupData;

/**
 * File:  MFJSONComposer.java 
 *
 * Created on May 15, 2013 11:33:21 AM
 */

/*
 * Class: MFJSONComposer
 * 
 * Purpose: This class is used to parse the response object
 * and composes the JSON String
 */

public class MFJSONComposer
{
	
	/*Constructor*/
	
	public MFJSONComposer( )
	{
		
	}
	
	/*
	 * Method: composeGroupListJSON
	 * 
	 * Input: int response, List<GroupData> groupDataList
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the groupDataList and compose JSON string
	 */
	
	public String composeGroupListJSON( int responseCode, List<GroupData> groupDataList )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		System.out.println( "Response Code="+responseCode );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 7800 )
		{
			//jsonMap.put( "result", "success" );
			
			String msg = "Info::ManageFolderServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );
			
			jsonStr = "{ \"result\" : \"success\",  ";
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\", ";
			jsonStr = jsonStr + "\"groups\" : [";
			
			int iterator = 0;
			
			for( GroupData groupData : groupDataList )
            {
				jsonStr = jsonStr + "{ \"groupkey\" : \"" + groupData.groupKey_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"groupname\" : \"" + groupData.groupName_ + "\", ";
				jsonStr = jsonStr + " \"userdata\" : [";
                
				int profileIter = 0;
				
				for( UserProfileData profileData: groupData.profileDataList_ )
                {
					jsonStr = jsonStr + "{ \"firstname\" : \"" + profileData.firstName_ + "\", ";
					jsonStr = jsonStr + " \"lastname\" : \"" + profileData.lastName_ + "\", ";
					jsonStr = jsonStr + " \"userkey\" : \"" + profileData.emailId_ + "\" ";
					
					jsonStr = jsonStr + "}";
				    profileIter = profileIter + 1;
	                
	                System.out.println( "Profile Arr size: "+ groupData.profileDataList_.size( )+", profileIter: "+profileIter );
	                
	                if( groupData.profileDataList_.size( ) > profileIter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "]}";
				//jsonStr = jsonStr + "}";
				iterator = iterator + 1;

				System.out.println( "group data list size: " + groupDataList.size( )
				        + ", iterator: " + iterator );

				if( groupDataList.size( ) > iterator )
				{
					jsonStr = jsonStr + ",";
				}
			}
				
			jsonStr = jsonStr + "]}";
		}
		else 
		{
			//jsonMap.put( "result", "failed" );
			
			jsonStr = "{ \"result\" : \"failed\",  ";
			
			String msg = "Info::ManageFolderServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
			
			//jsonMap.put( "message", responseString );	
		
		}
		
		//jsonStr = new Gson( ).toJson( jsonMap );
		
		System.out.println( "json str="+jsonStr );

		return jsonStr;
	}
	
	/*
	 * Method: composeUserListJSON
	 * 
	 * Input: int response, List<UserProfileData> profileDataList
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the userDataList and compose JSON string
	 */
	
	public String composeUserListJSON( int responseCode, List<UserProfileData> profileDataList )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		System.out.println( "Response Code="+responseCode );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 7810 )
		{
		//	jsonMap.put( "result", "success" );
			
			String msg = "Info::ManageFolderServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );
			
			jsonStr = "{ \"result\" : \"success\",  ";
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\", ";
			jsonStr = jsonStr + "\"users\" : [";

			int profileIter = 0;

			for( UserProfileData profileData : profileDataList )
			{
				jsonStr = jsonStr + "{ \"firstname\" : \"" + profileData.firstName_ + "\", ";
				jsonStr = jsonStr + " \"lastname\" : \"" + profileData.lastName_ + "\", ";
				jsonStr = jsonStr + " \"userkey\" : \"" + profileData.emailId_ + "\" ";

				jsonStr = jsonStr + "}";
				profileIter = profileIter + 1;

				System.out.println( "Profile Arr size: " + profileDataList.size( )
				        + ", profileIter: " + profileIter );

				if( profileDataList.size( ) > profileIter )
				{
					jsonStr = jsonStr + ",";
				}
			}

			jsonStr = jsonStr + "]}";

		}
		else 
		{
			//jsonMap.put( "result", "failed" );
			jsonStr = "{ \"result\" : \"failed\",  ";
			
			
			String msg = "Info::ManageFolderServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
			
			//jsonMap.put( "message", responseString );	
		
		}
		
		//jsonStr = new Gson( ).toJson( jsonMap );
		
		System.out.println( "json str="+jsonStr );

		return jsonStr;
	}
	
	/*
	 * Method: composeDeptListJSON
	 * 
	 * Input: int response, List<DeptData> deptDataList
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the deptDataList and compose JSON string
	 */
	
	public String composeDeptListJSON( int responseCode, List<DeptData> deptDataList )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		System.out.println( "Response Code="+responseCode );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 7820 || responseCode == 7830 )
		{
		//	jsonMap.put( "result", "success" );
			
			String msg = "Info::ManageFolderServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );
			
			jsonStr = "{ \"result\" : \"success\",  ";
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\", ";
			jsonStr = jsonStr + "\"depts\" : [";
			
			int iterator = 0;
			
			for( DeptData deptData : deptDataList )
            {
				jsonStr = jsonStr + "{ \"deptkey\" : \"" + deptData.deptKey_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"deptname\" : \"" + deptData.deptName_ + "\", ";
				jsonStr = jsonStr + " \"folderdata\" : [";
                
				int profileIter = 0;
				
				for( DeptFolderData folderData: deptData.deptFolderDataList_ )
                {
					jsonStr = jsonStr + "{ \"folderkey\" : \"" + folderData.deptFolderKey_.toString( ) + "\", ";
					jsonStr = jsonStr + " \"foldername\" : \"" + folderData.folderName_ + "\"";
					
					jsonStr = jsonStr + "}";
				    profileIter = profileIter + 1;
	                
	                System.out.println( "folderArr size: "+ deptData.deptFolderDataList_.size( )+", profileIter: "+profileIter );
	                
	                if( deptData.deptFolderDataList_.size( ) > profileIter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "]";
				jsonStr = jsonStr + "}";
				iterator = iterator + 1;

				System.out.println( "Dept data list size: " + deptDataList.size( )
				        + ", iterator: " + iterator );

				if( deptDataList.size( ) > iterator )
				{
					jsonStr = jsonStr + ",";
				}
			}
				
			jsonStr = jsonStr + "]}";
		}
		else 
		{
			//jsonMap.put( "result", "failed" );
			jsonStr = "{ \"result\" : \"failed\",  ";
			
			
			
			String msg = "Info::ManageFolderServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
		
			//jsonMap.put( "message", responseString );	
		
		}
		
		//jsonStr = new Gson( ).toJson( jsonMap );
		
		System.out.println( "json str="+jsonStr );

		return jsonStr;
	}
	
	/*
	 * Method: composeGFDataJSON
	 * 
	 * Input: int response, GroupFolderData groupFolderData
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the groupFolderData and compose JSON string
	 */
	
	public String composeGFDataJSON( int responseCode, GroupFolderData groupFolderData )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		System.out.println( "Response Code="+responseCode );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 7840 )
		{
			//jsonMap.put( "result", "success" );
			
			String msg = "Info::ManageFolderServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );
			
			jsonStr = "{ \"result\" : \"success\",  ";
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\", ";
			//jsonStr = jsonStr + "\"groupfolderdata\" : [";

			jsonStr = jsonStr + " \"readflag\" : \"" + groupFolderData.readFlag_ + "\", ";
			jsonStr = jsonStr + " \"readwriteflag\" : \"" + groupFolderData.readWriteFlag_ + "\", ";
			jsonStr = jsonStr + " \"folderkey\" : \"" + groupFolderData.folderKey_.toString( ) + "\", ";
			
			jsonStr = jsonStr + " \"groupkey\" : \"" + groupFolderData.groupKey_.toString( ) + "\", ";
			jsonStr = jsonStr + " \"regnkey\" : \"" + groupFolderData.regnKey_.toString( ) + "\", ";
			jsonStr = jsonStr + " \"deptkey\" : \"" + groupFolderData.deptKey_.toString( ) + "\"";
			
			//jsonStr = jsonStr + "}";

			//jsonStr = jsonStr + "]}";
			jsonStr = jsonStr + "}";
		}
		else 
		{
			//jsonMap.put( "result", "failed" );
			
			jsonStr = "{ \"result\" : \"failed\",  ";
			
			
			
			String msg = "Info::ManageFolderServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
			
			//jsonMap.put( "message", responseString );	
		
		}
		
		//jsonStr = new Gson( ).toJson( jsonMap );
		
		System.out.println( "json str="+jsonStr );

		return jsonStr;
	}
	
	/*
	 * Method: composeUFDataJSON
	 * 
	 * Input: int response, UserFolderData userFolderData
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the userFolderData and compose JSON string
	 */
	
	public String composeUFDataJSON( int responseCode, UserFolderData userFolderData )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		System.out.println( "Response Code="+responseCode );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 7850 )
		{
			//jsonMap.put( "result", "success" );
			
			String msg = "Info::ManageFolderServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );
			
			jsonStr = "{ \"result\" : \"success\",  ";
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\", ";
			//jsonStr = jsonStr + "\"groupfolderdata\" : [";

			jsonStr = jsonStr + " \"readflag\" : \"" + userFolderData.readFlag_ + "\", ";
			jsonStr = jsonStr + " \"readwriteflag\" : \"" + userFolderData.readWriteFlag_ + "\", ";
			jsonStr = jsonStr + " \"folderkey\" : \"" + userFolderData.folderKey_.toString( ) + "\", ";
			
			jsonStr = jsonStr + " \"userkey\" : \"" + userFolderData.userKey_.email_ + "\", ";
			jsonStr = jsonStr + " \"regnkey\" : \"" + userFolderData.regnKey_.toString( ) + "\", ";
			jsonStr = jsonStr + " \"deptkey\" : \"" + userFolderData.deptKey_.toString( ) + "\"";
			
			//jsonStr = jsonStr + "}";

			//jsonStr = jsonStr + "]}";
			jsonStr = jsonStr + "}";
		}
		else 
		{
			//jsonMap.put( "result", "failed" );
			
			jsonStr = "{ \"result\" : \"failed\",  ";
			
			String msg = "Info::ManageFolderServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
			
			//jsonMap.put( "message", responseString );	
		
		}
		
		//jsonStr = new Gson( ).toJson( jsonMap );
		
		System.out.println( "json str="+jsonStr );

		return jsonStr;
	}
	
	/*
	 * Method: composeUpdateDataJSON
	 * 
	 * Input: int response, UserFolderData userFolderData
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the userFolderData and compose JSON string
	 */
	
	public String composeUpdateDataJSON( int responseCode )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		System.out.println( "Response Code="+responseCode );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 7860 || responseCode == 7870 )
		{
			//jsonMap.put( "result", "success" );
			jsonStr = "{ \"result\" : \"success\",  ";
			
			String msg = "Info::ManageFolderServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
			
			//jsonMap.put( "message", responseString );	
		}
		else 
		{
			//jsonMap.put( "result", "failed" );
			
			jsonStr = "{ \"result\" : \"failed\",  ";		
			
			String msg = "Info::ManageFolderServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
			
			//jsonMap.put( "message", responseString );	
		
		}
		//jsonStr = new Gson( ).toJson( jsonMap );
		
		System.out.println( "json str="+jsonStr );

		return jsonStr;
	}
}
