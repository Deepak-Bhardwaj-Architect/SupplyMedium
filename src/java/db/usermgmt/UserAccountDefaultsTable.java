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
package db.usermgmt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import core.regn.UserProfileData;

import utils.ErrorLogger;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  UserAccountDefaultsTable.java 
 *
 * Created on Mar 20, 2013 2:30:15 PM
 */
public class UserAccountDefaultsTable
{
	private String tableName_;

	/*
	 * Method : UserAccountDefaultsTable( ) - constructor
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public UserAccountDefaultsTable()
	{
		this.tableName_ = "sm_config_user_account";
	}

	/*
	 * Method : getDefaults( ) 
	 * 
	 * Input : UserProfileData
	 * 
	 * Return :int
	 * 
	 * Purpose: It gets user's account defaults
	 */

	public int getDefaults( UserProfileData profileData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errorMessage= "";

		query = "SELECT * FROM " + tableName_;
 ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				profileData.changePasswordFlag_ = rs.getInt( "change_password_flag" );
				profileData.disableAccountFlag_	= rs.getInt( "disable_account_flag" );
				profileData.accountExpireDays_	= rs.getInt( "account_expiration_days" );
			}

			return 0;
		}
		catch( SQLException ex ) // SqlException
		{
			
			errorMessage = "Exception::UserAccountDefaultsTable:getDefaults-"
			        + ex;

			errLogger.logMsg( errorMessage );

			return -1; 
		}
		catch( Exception ex )  // General Exception
		{
			
			errorMessage = "Exception::UserAccountDefaultsTable:getDefaults-"
			        + ex;

			errLogger.logMsg( errorMessage );

			return -1;
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
