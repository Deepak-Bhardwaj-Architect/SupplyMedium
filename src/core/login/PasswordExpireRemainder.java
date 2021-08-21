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

package core.login;

import java.util.ArrayList;
import java.util.List;

import core.regn.AccountPolicyTblBuffer;
import core.regn.CompanyRegnKey;
import core.regn.LoginData;
import core.regn.UserProfileKey;

import db.regn.CompanyRegnTable;
import db.regn.UserLoginTable;
import db.regn.UserProfileTable;
import utils.ErrorLogger;
import utils.Mailer;

/**
 * File:  PasswordExpireRemainder.java 
 *
 * Created on Jul 31, 2013 6:15:04 PM
 * 
 */

/* Class: PasswordExpireRemainder
 * 
 * Purpose: This class is used to send password expire mail to 
 * all the users of all the companies.
 * 
 * 1. Each company's password policies will be get from AcctPolicyTblBuffer Map
 * 
 * 2. For each company's user's, the account expire time will be checked
 * 
 * 3. If these users password time are expired, then notification will be mailed
 * to the users
 * 
 * a. Notification will be daily or only once as specified in the account policy
 * settings
 */

public class PasswordExpireRemainder
{
	/*Constructor*/
	
	private ErrorLogger errLogger_;
	
	private AccountPolicyTblBuffer policyTblBuffer_;
	
	public PasswordExpireRemainder( )
	{
		errLogger_ = ErrorLogger.instance( );
		
		policyTblBuffer_ = AccountPolicyTblBuffer.instance( );
	}
	
	/*
	 * Method: remaind
	 * 
	 *  Input:
	 *  
	 *  Return:
	 *  
	 *  Purpose:
	 */
	
	public int remaind( )
	{
		System.out.println( "Task Started" );

		CompanyRegnTable regnTable = new CompanyRegnTable( );
		
		List<CompanyRegnKey> companyRegnKeyList = new ArrayList<CompanyRegnKey>( );
		
		int result = regnTable.get( companyRegnKeyList );
		
		regnTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::PasswordExpireRemainder.remaind( ) - Failed to get company key list from company regn table" );
			
			companyRegnKeyList = null;
			
			return -1;
		}
		
		for( CompanyRegnKey companyRegnKey : companyRegnKeyList )
        {
			AccountPoliciesEnforcer enforcer = new AccountPoliciesEnforcer( );
			
	        enforcer = policyTblBuffer_.policyMap_.get( companyRegnKey );
	        
	        int notifyNday = enforcer.accPolicyData_.passPolicyData_.notificationRemainderNday_;
	        
	        int dailyNotifyFlag = enforcer.accPolicyData_.passPolicyData_.dailyRemainderFlag_;
	        
	        int maxPassAge = enforcer.accPolicyData_.passPolicyData_.passwordAgeMaxDays_;
	        
	        UserProfileTable profileTable = new UserProfileTable( );
	        
	        List<UserProfileKey> profileKeyList = new ArrayList<UserProfileKey>( );
	        
	        result = profileTable.getActiveUsers( companyRegnKey, profileKeyList );
	        
	        profileTable = null;
	        
	        if( result != 0 )
	        {
	        	errLogger_.logMsg( "Error::PasswordExpireRemainder.remaind( ) - Failed to get user key list from user profile table" );
				
	        	enforcer = null; profileKeyList = null;
	        	
				continue;
	        }
	        
	        for( UserProfileKey userProfileKey : profileKeyList )
            {
	            UserLoginTable loginTable = new UserLoginTable( );
	            
	            LoginData loginData = new LoginData( );
	            
	            result = loginTable.get( userProfileKey, loginData );
	            
	            loginTable = null;
	            
	            if( result != 0 )
	            {
	            	errLogger_.logMsg( "Error::PasswordExpireRemainder.remaind( ) - Failed to get login data from user login table" );
					
	            	loginTable = null; loginData = null;
	            	
					continue;
	            }
	            
	        	int diffInDays = (int)( ( ( new java.util.Date( ) ).getTime( ) - loginData.createdTimestamp_
				        .getTime( ) ) / ( 1000 * 60 * 60 * 24) );
	        	
	        	if( dailyNotifyFlag == 1 )
	        	{
	        		if( diffInDays >= ( maxPassAge - notifyNday ) )
	        		{
	        			int nthDay = notifyNday - ( diffInDays - ( maxPassAge - notifyNday ) );
	        			
	        			if( nthDay > 0 )
	        			{
	        				sendMail( userProfileKey, nthDay );
	        			}
	        		}
	        	}
	        	else
	        	{
	        		if( diffInDays == ( maxPassAge - notifyNday ) )
	        		{
	        			sendMail( userProfileKey, notifyNday );
	        		}
	        	}
	        	loginTable = null; loginData = null;
            }
	        enforcer = null; profileKeyList = null;
        }
		
		companyRegnKeyList = null;
		
		System.out.println( "Task Complete" );
		
		return 0;
	}
	
	/*Helper method for remaind method*/
	
	private void sendMail( UserProfileKey userKey, int nDay )
	{
		String [ ] to = { userKey.email_ };

		String sub = "SupplyMedium - Password Expiry Remainder";
				
		String message = "";
		
		message = "Your password is about to expire in " + nDay + " days. Kindly reset your password to stay secured";
		
//		composer = null;
				  
		Mailer mailerObj = new Mailer( );
		
		mailerObj.composeAndSendMail( to, sub, message.toString( ) );
	}
}
