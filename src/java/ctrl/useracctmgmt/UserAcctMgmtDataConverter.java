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

package ctrl.useracctmgmt;

import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.useracctmgmt.UserAcctMgmtData;
import utils.ErrorMaster;

/**
 * File:  UserAcctMgmtDataConverter.java 
 *
 * Created on May 1, 2013 4:41:01 PM
 */

/*
 * Class: UserAcctMgmtDataConverter
 * 
 * Purpose: This class is used to 
 */

public class UserAcctMgmtDataConverter
{
	/*Constructor*/
	
	public UserAcctMgmtDataConverter( )
	{
		
	}
	
	/*
	 * Method: parseRequest
	 * 
	 * Input: HttpServletRequest object, UserAcctMgmtData object (As ref)
	 * 
	 * Return: int
	 * 
	 * Purpose: To parse the request object and 
	 */
	
	public int parseRequest( HttpServletRequest request, UserAcctMgmtData userAcctMgmtData )
	{
		try
        {
                    ErrorMaster errorMaster_ = null;
                    if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
			String requestType = request.getParameter( "RequestType" );
			
			userAcctMgmtData.userProfileKey_.email_ = request.getParameter( "UserKey" );
			
			UserProfileKey profileKey = new UserProfileKey( );
		
			profileKey.email_ = request.getParameter( "UserKey" );
			
			if( requestType.equals( "GetProfilePicture" ) )
			{	
				userAcctMgmtData.userProfileData_.userProfileKey_ = profileKey;	
			}
			
			else if( requestType.equals( "GetNotificationSettings" ) )
			{
				userAcctMgmtData.notifySettingsData_.userProfileKey_.email_ = userAcctMgmtData.userProfileKey_.email_;
			}
			
			else if( requestType.equals( "GetWorkingHours" ) )
			{
				userAcctMgmtData.workingHrsData_.userProfileKey_.email_ = userAcctMgmtData.userProfileKey_.email_;
			}
			
			else if( requestType.equals( "ResetPassword" ) || 
					 requestType.equals( "ChangePassword" ) )
			{
				userAcctMgmtData.passResetData_.newPassword_ = request.getParameter( "NewPassword" );
				
				userAcctMgmtData.passResetData_.oldPassword_ = request.getParameter( "OldPassword" );
				
				userAcctMgmtData.passResetData_.regnKey_.companyPhoneNo_ = request.getParameter( "RegnKey" );
				
				userAcctMgmtData.passResetData_.userKey_.email_ = userAcctMgmtData.userProfileKey_.email_;
			}
			
			else if( requestType.equals( "UpdateNotificationSettings" ) )
			{
				userAcctMgmtData.notifySettingsData_.userProfileKey_.email_ = userAcctMgmtData.userProfileKey_.email_;
				
				userAcctMgmtData.notifySettingsData_.nonWorkingHoursFlag_ = Integer.parseInt( request.getParameter( "NonWorkingHoursFlag" ) );
				
				userAcctMgmtData.notifySettingsData_.notifyEmail_	= request.getParameter( "NotifyEmail" );
				
				userAcctMgmtData.notifySettingsData_.notifyMobile_	= request.getParameter( "NotifyMobile" );
				
				userAcctMgmtData.notifySettingsData_.notifyNonWhMode_ = request.getParameter( "NotifyNonWhMode" );
				
				userAcctMgmtData.notifySettingsData_.notifyStopFlag_ = Integer.parseInt( request.getParameter( "NotifyStopFlag" ) );
				
				String fromTime = request.getParameter( "NotifyStopFromTime" );
				String toTime = request.getParameter( "NotifyStopToTime" );
				
				errorMaster_.insert( "fromtime="+fromTime);
				
				DateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy" ); 
				Date date1 = ( Date )formatter.parse( fromTime );
				Date date2 = ( Date )formatter.parse( toTime );
				
				Timestamp fromTs = new Timestamp( date1.getTime( ) );
				Timestamp toTs	= new Timestamp( date2.getTime( ) );
				
				userAcctMgmtData.notifySettingsData_.notifyStopFromTime_ = fromTs;
				
				userAcctMgmtData.notifySettingsData_.notifyStopToTime_ = toTs;
				
				userAcctMgmtData.notifySettingsData_.notifyWhMode_	= request.getParameter( "NotifyWhMode" );
				
				userAcctMgmtData.notifySettingsData_.workingHoursFlag_ = Integer.parseInt( request.getParameter( "WorkingHoursFlag" ) );
			}
			
			else if( requestType.equals( "UpdateWorkingHours" ) )
			{
				userAcctMgmtData.workingHrsData_.userProfileKey_.email_ = userAcctMgmtData.userProfileKey_.email_;
				userAcctMgmtData.workingHrsData_.workingDaysData_.sunWorkingFlag_ = Boolean.parseBoolean( request.getParameter( "SunWorkingFlag" ) );
				userAcctMgmtData.workingHrsData_.workingDaysData_.monWorkingFlag_ = Boolean.parseBoolean( request.getParameter( "MonWorkingFlag" ) );
				userAcctMgmtData.workingHrsData_.workingDaysData_.tueWorkingFlag_ = Boolean.parseBoolean( request.getParameter( "TueWorkingFlag" ) );
				userAcctMgmtData.workingHrsData_.workingDaysData_.wedWorkingFlag_ = Boolean.parseBoolean( request.getParameter( "WedWorkingFlag" ) );
				userAcctMgmtData.workingHrsData_.workingDaysData_.thuWorkingFlag_ = Boolean.parseBoolean( request.getParameter( "ThuWorkingFlag" ) );
				userAcctMgmtData.workingHrsData_.workingDaysData_.friWorkingFlag_ = Boolean.parseBoolean( request.getParameter( "FriWorkingFlag" ) );
				userAcctMgmtData.workingHrsData_.workingDaysData_.satWorkingFlag_ = Boolean.parseBoolean( request.getParameter( "SatWorkingFlag" ) );
				
				
				userAcctMgmtData.workingHrsData_.sunFromTime_ = Time.valueOf( request.getParameter( "SunFromTime" ) );
				
				userAcctMgmtData.workingHrsData_.sunToTime_ = Time.valueOf( request.getParameter( "SunToTime" ) );
				
				userAcctMgmtData.workingHrsData_.monFromTime_ = Time.valueOf( request.getParameter( "MonFromTime" ) );
				
				userAcctMgmtData.workingHrsData_.monToTime_ = Time.valueOf( request.getParameter( "MonToTime" ) );
				
				userAcctMgmtData.workingHrsData_.tueFromTime_ = Time.valueOf( request.getParameter( "TueFromTime" ) );
				
				userAcctMgmtData.workingHrsData_.tueToTime_ = Time.valueOf( request.getParameter( "TueToTime" ) );
				
				userAcctMgmtData.workingHrsData_.wedFromTime_ = Time.valueOf( request.getParameter( "WedFromTime" ) );
				
				userAcctMgmtData.workingHrsData_.wedToTime_ = Time.valueOf( request.getParameter( "WedToTime" ) );
				
				userAcctMgmtData.workingHrsData_.thuFromTime_ = Time.valueOf( request.getParameter( "ThuFromTime" ) );
				
				userAcctMgmtData.workingHrsData_.thuToTime_ = Time.valueOf( request.getParameter( "ThuToTime" ) );
				
				userAcctMgmtData.workingHrsData_.friFromTime_ = Time.valueOf( request.getParameter( "FriFromTime" ) );
				
				userAcctMgmtData.workingHrsData_.friToTime_ = Time.valueOf( request.getParameter( "FriToTime" ) );
				
				userAcctMgmtData.workingHrsData_.satFromTime_ = Time.valueOf( request.getParameter( "SatFromTime" ) );
				
				userAcctMgmtData.workingHrsData_.satToTime_ = Time.valueOf( request.getParameter( "SatToTime" ) );
			}
			
			else 
			{
				userAcctMgmtData.userProfileData_.userProfileKey_ = profileKey;	
				
				//userAcctMgmtData.userProfileData_.profilePicture_ = request.getParameter( "ProfilePicPath" );
				
				userAcctMgmtData.userProfileData_.companyRegnKey_ = new CompanyRegnKey( );
				
				userAcctMgmtData.userProfileData_.companyRegnKey_.companyPhoneNo_ = request.getParameter( "RegnKey" );
				
				userAcctMgmtData.userProfileData_.profileImage_	  = request.getAttribute( "ProfilePicPath" );
			}
			
			return 0;
			
        }
        catch( Exception e )
        {
	        ErrorLogger errLogger_ = ErrorLogger.instance( );
	        
	        errLogger_.logMsg( "Exception::UserAcctMgmtDataConverter.parseRequest( ) - "+e );
	        
	        return -1;
        }
	}
}
