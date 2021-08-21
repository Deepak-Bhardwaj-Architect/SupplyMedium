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

package core.useracctmgmt;


import java.util.ArrayList;
import java.util.List;

import utils.ErrorLogger;
import utils.IntHolder;
import utils.PasswordStore;
import core.login.PasswordPolicyData;
import core.regn.LoginData;
import core.regn.UserProfileKey;
import db.regn.UserLoginTable;
import db.useracctmgmt.PasswordHistoryTable;

/**
 * File:  PasswordHistory.java 
 *
 * Created on Apr 22, 2013 10:36:28 AM
 */

/*
 * Class: PasswordHistory
 * 
 * Purpose: This class is used to 
 * 
 * 1. Add new password when a new user is signed up.
 * 2. Updates the existing password by enforcing password history
 * when a user resets his password
 * 
 */

public class PasswordHistory
{
	
	ErrorLogger errorLogger_;	
	
	/*
	 * Method: PasswordHistory - constructor
	 * 
	 * Input: none
	 * 
	 * Return: none
	 * 
	 * Purpose: To initialize the error logger
	 * 
	 */
	
	public PasswordHistory( )
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method: add( )
	 * 
	 * Input: PasswordResetData object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to add new password from user_login table
	 * to password_history table. This method will be called whenever a new
	 * user is activated to supply medium.
	 * 
	 * 
	 */
	
	public int add( PasswordResetData passResetData )
	{
		UserLoginTable loginTable = new UserLoginTable( );
		
		LoginData loginData =  new LoginData( );
		
		int result	= 0;
		
		result = loginTable.getRecord( passResetData.userKey_.email_, loginData );
		
		if( result == 0 )
		{
			PasswordHistoryTable passHistoryTbl = new PasswordHistoryTable( );
			
			passResetData.newPassword_ = loginData.password_;
			
			result = passHistoryTbl.insert( passResetData );
			
			if( result != 0 )
			{
				errorLogger_.logMsg( "Error:PasswordHistory.add() - Failed to insert" +
									 " new password for user <" + passResetData.userKey_.email_ + ">" );
				
				loginTable = null;
				
				loginData = null;
				
				return 2521; // New Password insertion failed
			}
			
			loginTable = null;
			
			loginData = null;
			
			return 2520; // Insert new password success
		}
		else 
		{
			errorLogger_.logMsg( "Error:PasswordHistory.add() - Failed to fetch" +
					 " login records for user <" + passResetData.userKey_.email_ + ">" );

			loginTable = null;
			
			loginData = null;
			
			return 2512; // Failed to fetch login records
		}
	}
	
	/*
	 * Method: reset( )
	 * 
	 * Input: PasswordResetData object, PasswordPolicyData
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used enforce the password history acount
	 * policy and decide whether to update the password as proposed by
	 * user through reset password option. If the password satisfies
	 * the password history criteria, then this method will reset the
	 * password and returns the response code. Else, the suitable error code
	 * will be returned
	 * 
	 */
	
	public int reset( PasswordResetData passResetData, PasswordPolicyData passwordPolicyData )
	{
		/* To check whether the username and password entered by the user
		  through password reset request is correct */
		 
		int result = 0;
		
		result = validateCredentials( passResetData );
		
		if( result != 0 )
		{
			return 2513; //The entered password is not correct.
		}
		
		/* To check whether the password age min is greater than 
		   the password reset request date */

		UserLoginTable userLoginTbl = new UserLoginTable( );

		LoginData loginData = new LoginData( );
		
		result = userLoginTbl.getPasswordTimestampForEmail( passResetData.userKey_.email_,
		        loginData );

		if( result == 0 )
		{
			//To check whether the password age crosses the password min age
			
			int diffInDays = (int)( ( ( new java.util.Date( ) ).getTime( ) - loginData.createdTimestamp_
			        .getTime( ) ) / ( 1000 * 60 * 60 * 24 ) );
			
			if( diffInDays <= passwordPolicyData.passwordAgeMinDays_ && 
					passwordPolicyData.passwordAgeMinDays_ != 0)
			{
				userLoginTbl = null;
				
				loginData = null;
				
				return 2502; // Cannot reset password before password min age exceeds
			}
			else 
			{
				/*
				 * Here the return value may be
				 * 1. 2504 - Failed to delete last saved password
				 * 2. 2505 - Failed to insert new password for user
				 * 3. 2506 - Failed to fetch password count from password_history table
				 * 4. 2507 - Cannot reset password as password history criteria is not satisfied
				 * 5. 2508 - Failed to update the new password to user_login table
				 * 6. 2511 - Failed to fetch password for removing operation
				 * 7. 2509 - Error occurred while getting passwords
				 * 6. 2500 - Success
				 */
				
				return updatePassword( passResetData, passwordPolicyData );
			}	
		}
		else 
		{
			errorLogger_.logMsg( "Error:PasswordHistory.reset() - Failed to get password " +
								 "created time for user <" + passResetData.userKey_.email_ + ">" );
			
			userLoginTbl = null;
			
			loginData = null;
			
			return 2503; // Failed to get password created time for user
		}
	}
	
	/* 
	 * Method: updatePassword()
	 * 
	 * Input: PasswordResetData
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is the helper method for reset() method of
	 * PasswordHistory class.
	 * 
	 */
	
	private int updatePassword( PasswordResetData passResetData, PasswordPolicyData passwordPolicyData )
	{
		PasswordHistoryTable passHistoryTbl = new PasswordHistoryTable( );
		
		//int passResult = passHistoryTbl.isExists( passResetData, passwordPolicyData );
		
		int passResult = isExists( passResetData, passwordPolicyData );
		
		if( passResult == 0 )
		{
			IntHolder max = new IntHolder( );
			
			passResult = passHistoryTbl.getMax( passResetData.userKey_, max );
			
			if( passResult == 0 )
			{
				/* To check whether the max is greater than or equal to pass history value from config 
				 If conditions satisfy, the last stored password will be removed and the new
				 password will be inserted */
	
				System.out.println( "Max Value = "+max.value );
				System.out.println( "Passwordhistory = "+passwordPolicyData.passwordHistoryDays_ );
				if( max.value >= passwordPolicyData.passwordHistoryDays_ )
				{
					/* To remove the last saved password from password table */
		
					//passResult = passHistoryTbl.delete( passResetData );
					
					System.out.println( "Removing function" );
					
					passResult = remove( passResetData.userKey_ );
					
					if( passResult == -2 )
					{
						errorLogger_.logMsg( "Error::PasswordHistory.updatePassword( ) " +
											 "- Failed to delete last saved password for user" +
											 "<" + passResetData.userKey_.email_ + ">" );
						
						passHistoryTbl = null;

						max = null;
						
						return 2504; //Failed to delete last saved password
					}
					
					else if( passResult == -1 )
					{
						errorLogger_.logMsg( "Error::PasswordHistory.remove( ) - Failed to fetch password " +
								"for user <" + passResetData.userKey_.email_ + ">" );
						
						return 2511; //Failed to fetch password for removing operation
					}
					
					/* Deletion successful. Now insert new password and update user_login table*/
					else
					{
						passHistoryTbl = null;
						
						max = null;
						
						return insertPassword( passResetData ); // 2500 or 2505 or 2508
					}
				}
				
				/* To insert new passord into the password table and update user_login table */
				else 
				{
					passHistoryTbl = null;
					
					max = null;
					
					return insertPassword( passResetData ); // 2500 or 2505 or 2508
				}
			}
			
			/* getMax failed to fetch*/
			else 
			{
				errorLogger_.logMsg( "Error::PasswordHistory.updatePassword( ) " +
						 "- Failed to fetch password count from password_history table" +
						 "<" + passResetData.userKey_.email_ + ">" );
				
				passHistoryTbl = null;
				
				max = null;
				
				return 2506; //Failed to fetch password count from password_history table
			}
		}
		else if ( passResult == -1 ) 
		{
			passHistoryTbl = null;
			
			return 2507; // Cannot reset password as password history criteria is not satisfied 	
		}
		else
		{
			passHistoryTbl = null;
			
			return 2509; //Error occurred while getting passwords
		}
	}
	
	
	/*
	 * Method: validateCredentials
	 * 
	 * Input: LoginData object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to check the user's email id and his old password
	 * as entered in password reset request form is correct or not.
	 * 
	 */
	
	private int validateCredentials( PasswordResetData passResetData )
	{
		UserLoginTable loginTable = new UserLoginTable( );
		
		LoginData loginData = new LoginData( );
		
		loginData.emailid_ = passResetData.userKey_.email_;
		
		loginData.password_ = passResetData.oldPassword_ ;
		
		int result = 0;
		
		result = loginTable.checkLoginCredentials( loginData );
		
		if( result == 0 )
		{
			loginData = null;
			
			loginTable = null;
			
			return 0; //Username and password matches
		}
		
		//Username and password not matches
		else 
		{
			errorLogger_.logMsg( "Error::PasswordHistory.validateCredentials( ) " +
					 "- Failed to check login credentials for user " +
					 "<" + loginData.emailid_ + ">" );
			
			loginData = null;
			
			loginTable = null;
			
			return -1;
		}
	}
	
	/*
	 * Method: insertPassword
	 * 
	 * Input: PasswordResetData object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is the helper method for updatePassword() 
	 */
	
	private int insertPassword( PasswordResetData passResetData )
	{
		PasswordHistoryTable passHistoryTbl = new PasswordHistoryTable( );
		
		/* To encrypt the password before storing it into password history table */ 
		PasswordStore passStore = new PasswordStore( );
		
		String encryptedPass = passStore.getEncryptedValue( passResetData.newPassword_ );
		
		passResetData.newPassword_ = encryptedPass;
		
		passStore = null;
		
		int passResult = passHistoryTbl.insert( passResetData );
		
		if( passResult != 0 )
		{
			errorLogger_.logMsg( "Error::PasswordHistory.updatePassword( ) " +
					 "- Failed to insert new password for user" +
					 "<" + passResetData.userKey_.email_ + ">" );
			
			passHistoryTbl = null;
			
			return 2505; // Failed to insert new password for user
		}
		
		/* Insertion successful */

		passHistoryTbl = null;
		
		/*Update password in user_login table*/
		
		UserLoginTable loginTable = new UserLoginTable( );
		
		LoginData loginData = new LoginData( );
		
		loginData.emailid_ = passResetData.userKey_.email_;
		
		loginData.password_ = passResetData.newPassword_;
		
		passResult = loginTable.resetPassword( loginData );
		
		if( passResult != 0 )
		{
			errorLogger_.logMsg( "Error::PasswordHistory.insertPassword( ) " +
					 "- Failed to update password in user_login tbl for User" +
					 "<" + passResetData.userKey_.email_ + ">" );
			
			loginTable = null;
			
			loginData = null;
			
			return 2508; //Failed to update password in user_login tbl for User
		}
		
		loginTable = null;
		
		loginData = null;
		
		return 2500; //Success
	}
	
	
	/*
	 * Method: isExists
	 * 
	 * Input: PasswordResetData object, PasswordPolicyData object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to check whether the password given
	 * is available to change or not 
	 */
	
	private int isExists( PasswordResetData resetData, PasswordPolicyData passPolicyData )
	{
		PasswordHistoryTable historyTable = new PasswordHistoryTable( );
		
		List<PasswordResetData> passResetDataList = new ArrayList<PasswordResetData>( );
		
		int result = historyTable.find( resetData.userKey_, passResetDataList, passPolicyData );
		
		if( result == 0 )
		{
			for( PasswordResetData passwordResetData : passResetDataList )
            {
	            if( passwordResetData.oldPassword_.equals( resetData.newPassword_ ) )
	            {
	            	historyTable = null;
	            	
	            	passResetDataList = null;
	            	
	            	return -1; //Password already exists within the history limit
	            }
            }
			
			historyTable = null;
        	
        	passResetDataList = null;
			
			return 0; //Password not exist within the history limit
		}
		else 
		{
			errorLogger_.logMsg( "Error::PasswordHistory.isExists() - Failed to fetch passwords" +
								 " for user <" + resetData.userKey_.email_ + ">");
			
			historyTable = null;
        	
        	passResetDataList = null;
        	
			return -2;
		}
	}
	
	/*
	 * Method: remove
	 * 
	 * Input: UserProfileKey key
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used fetch all the passwords for the 
	 * given user key, sorts the passwords using timestamp and removes
	 * the old password
	 */
	
	private int remove( UserProfileKey userProfileKey )
	{
		PasswordHistoryTable historyTable = new PasswordHistoryTable( );
		
		PasswordResetData passResetData = new PasswordResetData( );
		
		int result = historyTable.find( userProfileKey, passResetData );
		System.out.println( "Find result = "+result );

		if( result == 0 )
		{
			result = historyTable.delete( passResetData );
		
			System.out.println( "Delete result = "+result );
			
			if( result != 0 )
			{
				result = -2;
			}
		}
		else 
		{
			result = -1;
		}
		
		historyTable = null;
		
		passResetData = null;
		
		return result;
	}
	
}
