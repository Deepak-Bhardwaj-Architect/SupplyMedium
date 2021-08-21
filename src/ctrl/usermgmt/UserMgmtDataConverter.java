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
package ctrl.usermgmt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.dept.DeptKey;
import core.privilege.UsergroupPrivilegesData;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import core.usermgmt.GroupData;
import core.usermgmt.GroupKey;

/**
 * File: UserMgmtDataConverter.java
 * 
 * Created on Mar 6, 2013 5:07:39 PM
 */

/*
 * This class is the helper class for UserMgmtController which is used to
 * Convert HttpServletRequest object into domain object
 */

public class UserMgmtDataConverter
{
	
	/*
	 * Method : getGroupData( )
	 * 
	 * Input : HttpServletRequest obj, group data obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get GroupData  object
	 * from HttpServletRequest object
	 * 
	 */
	
	public int getGroupData( HttpServletRequest request, GroupData groupData )
	{
		try
		{
			GroupKey groupKey = new GroupKey( );

			int responseCode = getGroupkey( request,groupKey );

			if( responseCode == 0 )
			{
				groupData.groupKey_ = groupKey;
				
				if( request.getParameter( "RequestType" ).equals( "UpdateUserGroup" )  )
				{
					groupData.groupName_ =  request.getParameter( "GroupName" );	
				}
				else
				{
					groupData.groupName_ = groupKey.groupName_;
				}	
			}
			
			return responseCode;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errMsg = "Exception::UserMgmtDataConvert:getGroupData - " + ex;

			errLogger.logMsg( errMsg );

			return -1;  // failed
		}
	}
	
	/*
	 * Method : getGroupkey( )
	 * 
	 * Input : HttpServletRequest obj, group key obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get Groupkey  object
	 * from HttpServletRequest object
	 * 
	 */
	
	public int getGroupkey( HttpServletRequest request, GroupKey groupKey )
	{	
		try
        {
			int responseCode = 0;
			
	        CompanyRegnKey regnKey = new CompanyRegnKey( );
	        
	        responseCode = getRegnKey( request, regnKey );
	        
	        if( responseCode == 0 )
	        {
	        	String requestType = request.getParameter( "RequestType" );
	        	
	        	groupKey.companyRegnKey_ = regnKey;
	        	 //For new group creation
	        	if( requestType.equals( "NewUserGroup" ) ) 
	        	{
	        		groupKey.groupName_= request.getParameter( "GroupName" );
	        	}
	        	else  // Other than all operation having the groupkey
	        	{
	        		String groupKeyStr = request.getParameter( "GroupKey" );
	        		
	        		System.out.println( "Group Key At Convertor : "+groupKeyStr );
	        		
	        		if( groupKeyStr != null )
	        		{
	        			 String [] strArr = groupKeyStr.split( ":" );
	 	 	            
	 	 	            if( strArr.length > 1)
	 	 	            {
	 	 	            	String groupName = strArr[1];
	 	 	            	
	 	 	            	groupKey.groupName_ = groupName;
	 	 	            	
	 	 	            	System.out.println( "Group name= "+groupKey.groupName_ );
	 	 	            }
	        		}
				}
	        }
	        
	        return responseCode;
        }
		
        catch( Exception ex )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
        	String errMsg = "Exception::UserMgmtDataConvert:getGroupkey - "+ex;
			
			errLogger.logMsg( errMsg );
			
	        return -1;  // failed
        }
	}
	
	
	/*
	 * Method : getUsersData( ) 
	 * 
	 * Input  : HttpServletRequest object, list of UserProfileData objects.
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to list 
	 * of UserProfileData objects.
	 */
	
	public int getUsersData( HttpServletRequest request,
									List<UserProfileData> userProfileDataArr )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		try
        {
			int responseCode = 0;
			
			if (!request.getParameterMap().containsKey("UserKeys[]")) 
			{
				return responseCode;
			}
			
			String [] usersKeyStrArr = request.getParameterValues( "UserKeys[]" );
			
			if( usersKeyStrArr == null )
			{
				
	        	String errMsg = "Exception::UserMgmtDataConvert:getUsersData - " +
	        			"Parser error ";
				
				errLogger.logMsg( errMsg );
				
				return -1;
			}
			
			for( String userKeyStr : usersKeyStrArr )
            {
				
				UserProfileData userProfileData = new UserProfileData( );

				UserProfileKey userProfileKey = new UserProfileKey( );

				userProfileKey.email_ = userKeyStr;

				userProfileData.userProfileKey_ = userProfileKey;

				userProfileKey = null;

				userProfileData.emailId_ = userKeyStr;

				userProfileDataArr.add( userProfileData );

				userProfileData = null;

            }
			
	        return responseCode;
        }
        catch( Exception ex )
        {
        	String errMsg = "Exception::UserMgmtDataConvert:getUsersData - "+ex;
			
			errLogger.logMsg( errMsg );
			
	        return -1;  // failed
        }
	}
	
	
	/*
	 * Method : getGroupPrivilegesData( )
	 * 
	 * Input : HttpServletRequest obj
	 * 
	 * Return : UsergroupPrivilegesData
	 * 
	 * Purpose: It is used to get UsergroupPrivilegesData  object
	 * from HttpServletRequest object
	 * 
	 */
	
	public UsergroupPrivilegesData getGroupPrivilegesData( HttpServletRequest request )
	{
		UsergroupPrivilegesData data	= new UsergroupPrivilegesData( );
		
		
		if( request.getParameter( "accessusermgmt" ).equals( "1" ) )
		{
			data.accessUserMgmt_	= true;
		}
		else
		{
			data.accessUserMgmt_	= false;
		}
		
		if( request.getParameter( "addbuyers" ).equals( "1" ) )
		{
			data.addBuySupplier_	= true;
		}
		else
		{
			data.addBuySupplier_	= false;
		}
		
		if( request.getParameter( "addusers" ).equals( "1" ) )
		{
			data.addNewUser_		= true;
		}
		else
		{
			data.addNewUser_		= false;
		}
		
		if( request.getParameter( "creategroup" ).equals( "1" ) )
		{
			data.createUserGroup_	= true;
		}
		else
		{
			data.createUserGroup_	= false;
		}
		
		if( request.getParameter( "delanncemnt" ).equals( "1" ) )
		{
			data.deleteAnnouncement_	= true;
		}
		else
		{
			data.deleteAnnouncement_	= false;
		}
		
		if( request.getParameter( "delbuyers" ).equals( "1" ) )
		{
			data.deleteBuySupplier_	= true;
		}
		else
		{
			data.deleteBuySupplier_	= false;
		}
		
		if( request.getParameter( "deldoc" ).equals( "1" ) )
		{
			data.deleteDoc_		 	= true;
		}
		else
		{
			data.deleteDoc_		 	= false;
		}
		
		if( request.getParameter( "delusers" ).equals( "1" ) )
		{
			data.deleteUser_		= true;
		}
		else
		{
			data.deleteUser_		= false;
		}
		
		if( request.getParameter( "delgroup" ).equals( "1" ) )
		{
			data.deleteUserGroup_	= true;
		}
		else
		{
			data.deleteUserGroup_	= false;
		}
		
		if( request.getParameter( "managefolder" ).equals( "1" ) )
		{
			data.manageFolder_	= true;
		}
		else
		{
			data.manageFolder_	= false;
		}

		if( request.getParameter( "postanncemnt" ).equals( "1" ) )
		{
			data.postAnnouncement_	= true;
		}
		else
		{
			data.postAnnouncement_	= false;
		}
		
		if( request.getParameter( "rate" ).equals( "1" ) )
		{
			data.rateBuySupplier_	= true;
		}
		else
		{
			data.rateBuySupplier_	= false;
		}
		
		if( request.getParameter( "uploaddoc" ).equals( "1" ) )
		{
			data.uploadDoc_		 	= true;
		}
		else
		{
			data.uploadDoc_		 	= false;
		}
		
		if( request.getParameter( "applythemes" ).equals(  "1" ) )
		{
			data.applyThemes_		= true;
		}
		else
		{
			data.applyThemes_		= false;
		}
		
		return data;
	}
	
	/*
	 * Method : getRegnKey( ) 
	 * 
	 * Input  : HttpServletRequest object, CompanyRegnKey object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to CompanyRegnKey
	 * object.
	 */
	
	public int getRegnKey( HttpServletRequest request,CompanyRegnKey regnKey )
	{
		try
        {
	       regnKey.companyPhoneNo_ = request.getParameter( "RegnKey" );
	       
	       return 0;
	        
        }
        catch( Exception ex )
        {
        	ErrorLogger errLogger = ErrorLogger.instance( );
        	
        	String errMsg = "Exception::UserMgmtDataConvert:getRegnKey - "+ex;
			
			errLogger.logMsg( errMsg );
			
	        return -1; // failed
        }
	}
	
}
