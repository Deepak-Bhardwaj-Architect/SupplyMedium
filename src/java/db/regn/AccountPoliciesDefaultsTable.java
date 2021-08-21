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

package db.regn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import core.login.LockoutPolicyData;
import core.login.PasswordLoginPolicyData;
import core.login.PasswordPolicyData;
import db.utils.DBConnect;

import utils.ErrorLogger;
import utils.ErrorMaster;

/**
 * File:  AccountPoliciesDefaultsTable.java 
 *
 * Created on Mar 5, 2013 3:28:59 PM
 */

/*
 * Class  : AccountPoliciesDefaultsTable
 * 
 * Purpose: It is used to query the account_policies_defaults table.
 * 
 */

public class AccountPoliciesDefaultsTable
{
    private static ErrorMaster errorMaster_ = null;


	private String tableName_;

	/*
	 * Constructor : AccountPoliciesDefaultsTable( ) 
	 * 
	 * Input : None
	 * 
	 * Purpose: it is used to initialize the tableName variable
	 */

	public AccountPoliciesDefaultsTable()
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "sm_config_account_policies";
	}

	/*
	 * Method : getPasswordPoliciesDefaults( ) 
	 * 
	 * Input : PasswordPolicyData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: 
	 */

	public int getPasswordPoliciesDefaults( PasswordPolicyData passPolicyData )
	{
		String query = "SELECT * FROM " + tableName_;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		errorMaster_.insert( "AccountPoliciesDefaultsTable Query = " + query );

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		errorMaster_.insert( "Executing 1" );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			rs = stmt.executeQuery( query );

			errorMaster_.insert( "Executing 2" );

			if( rs.next( ) )
			{
				errorMaster_.insert( "Executing 3" );
				
				passPolicyData.dailyRemainderFlag_ = rs.getInt( "daily_remainder_flag" );
				passPolicyData.notificationRemainderNday_ = rs.getInt( "notification_remainder_nday" );
				
				passPolicyData.passwordAgeMaxDays_	=	rs.getInt( "password_agemax_days" );
				passPolicyData.passwordLength_		= 	rs.getInt( "password_length" );
				
				passPolicyData.passwordAgeMinDays_ = rs.getInt( "password_agemin_days" );
				passPolicyData.passwordHistoryDays_ = rs.getInt( "password_history_days" );
				
				return 0; // Success
			}
			else
			{
				errorMaster_.insert( "Executing 5" );
				return -1;
			}
		}
		
		catch( SQLException ex ) // Sql exception
		{
			errLogger.logMsg( "Exception::AccountPoliciesDefaultsTable:" +
					"getPasswordPoliciesDefaults-"+ ex );
			return -2; 
		}
		catch (Exception ex) // General Exception
		{
			errLogger.logMsg( "Exception::AccountPoliciesDefaultsTable:" +
					"getPasswordPoliciesDefaults-"+ ex );
			return -2; 
		}
		finally
        {
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
                if( con != null )
                {
                    con.close();
                }
           }
           catch (SQLException sQLException)
           {
           }
        }
	}
	

	/*
	 * Method : getPasswordLoginPoliciesDefaults( ) 
	 * 
	 * Input : PasswordLoginPolicyData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: 
	 */

	public int getPasswordLoginPoliciesDefaults( PasswordLoginPolicyData passLoginPolicyData )
	{
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String query = "SELECT * FROM " + tableName_;

		errorMaster_.insert( "AccountPoliciesDefaultsTable Query = " + query );

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		errorMaster_.insert( "Executing 1" );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			rs = stmt.executeQuery( query );

			errorMaster_.insert( "Executing 2" );

			if( rs.next( ) )
			{
				errorMaster_.insert( "Executing 3" );
				
				passLoginPolicyData.numericFlag_ = rs.getInt( "numeric_flag" );
				passLoginPolicyData.passwordComplexityFlag_ = rs.getInt( "password_complexity_flag" );
				//passLoginPolicyData.passwordLength_ = rs.getInt( "password_length" );
				passLoginPolicyData.specialCharactersFlag_ = rs.getInt( "special_characters_flag" );
				passLoginPolicyData.upperLowerFlag_ = rs.getInt( "upper_lower_flag" );
				
				errorMaster_.insert( "Executing 4" );
								
				return 0; // Success				
			}
			else
			{
				errorMaster_.insert( "Executing 5" );
				return -1;
			}
		}
		
		catch( SQLException ex ) // Sql exception
		{
			errLogger.logMsg( "Exception::AccountPoliciesDefaultsTable:" +
					"getPasswordPoliciesDefaults-"+ ex );
			return -2; 
		}
		catch (Exception ex) // General Exception
		{
			errLogger.logMsg( "Exception::AccountPoliciesDefaultsTable:" +
					"getPasswordPoliciesDefaults-"+ ex );
			return -2; 
		}
		finally
        {
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
                if( con != null )
                {
                    con.close();
                }
           }
           catch (SQLException sQLException)
           {
           }
        }
	}
	

	/*
	 * Method : getLockoutPoliciesDefaults( ) 
	 * 
	 * Input : LockoutPolicyData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: 
	 */

	public int getLockoutPoliciesDefaults( LockoutPolicyData lockoutPolicyData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String query = "SELECT * FROM " + tableName_;

		errorMaster_.insert( "AccountPoliciesDefaultsTable Query = " + query );

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		errorMaster_.insert( "Executing 1" );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			rs = stmt.executeQuery( query );

			errorMaster_.insert( "Executing 2" );

			if( rs.next( ) )
			{
				errorMaster_.insert( "Executing 3" );

				lockoutPolicyData.adminUnlockFlag_ = rs.getInt( "admin_unlock_flag" );
				lockoutPolicyData.expireSessionMin_ = rs.getInt( "expire_session_min" );
				lockoutPolicyData.invalidLoginAttempts_ = rs.getInt( "invalid_login_attempts" );
				lockoutPolicyData.lockoutDurationMin_ = rs.getInt( "lockout_duration_min" );
				lockoutPolicyData.resetLockoutDurationMin_ = rs.getInt( "reset_lockout_min" );
				
				return 0; // Success
			}
			else
			{
				errorMaster_.insert( "Executing 5" );
				return -1;
			}
		}
		
		catch( SQLException ex ) // Sql exception
		{
			errLogger.logMsg( "Exception::AccountPoliciesDefaultsTable:" +
					"getPasswordPoliciesDefaults-"+ ex );
			return -2; 
		}
		catch (Exception ex) // General Exception
		{
			errLogger.logMsg( "Exception::AccountPoliciesDefaultsTable:" +
					"getPasswordPoliciesDefaults-"+ ex );
			return -2; 
		}
		finally
        {
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
                if( con != null )
                {
                    con.close();
                }
           }
           catch (SQLException sQLException)
           {
           }
        }
	}
}
