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
package db.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Map;

import utils.ErrorLogger;
import core.regn.LoginData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  LoginStatusTable.java 
 *
 * Created on Mar 21, 2013 2:39:54 PM
 */
public class LoginStatusTable
{
    private static ErrorMaster errorMaster_ = null;


	private String tableName_;

	public LoginStatusTable()
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "login_status";
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : LoginData obj
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to insert the login data values into
	 * login_status table
	 */

	public int insert( LoginData loginData )
	{
		ErrorLogger errLogger_ = ErrorLogger.instance( );
		
		LoginStatusRecord statusRecord = createLoginStatusRec( loginData );
		
		String query = formInsertQuery( statusRecord );

		Statement stmt = null;
		Connection con = null;

		errorMaster_.insert( "query = "+query );
		
		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			insertResult = stmt.executeUpdate( query );

			if( insertResult > 0 ) return 0;

			return -1;
		}

		catch( SQLException ex )
		{
			errLogger_.logMsg( "Exception::LoginStatusTable:insert" + ex );
			return -2;
		}
		
		catch( Exception ex )
		{
			errLogger_.logMsg( "Exception::LoginStatusTable:insert" + ex );
			return -2;
		}
		
		finally
        {
            try
            {
                if ( stmt != null )
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

	private LoginStatusRecord createLoginStatusRec( LoginData loginData )
	{
		LoginStatusRecord statusRecord = new LoginStatusRecord( );

		// set the values
		statusRecord.userKey_ = loginData.emailid_;
		statusRecord.loginStatus_ = loginData.loginStatus_;
		statusRecord.loggedInTimestamp_	= new Timestamp( System.currentTimeMillis( ) );

		return statusRecord;

	}

	private String formInsertQuery( LoginStatusRecord statusRecord )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(user_rel_key, status, login_timestamp)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";
		
		insertQuery = insertQuery + "'" + statusRecord.userKey_ + "', ";
		insertQuery = insertQuery + "'" + statusRecord.loginStatus_+ "', ";
		insertQuery = insertQuery + "'" + statusRecord.loggedInTimestamp_+ "' ";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
	
	/*
	 * Method : update( )
	 * 
	 * Input : logindata obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the loginData values into
	 * login_status table
	 */

	public int update( LoginData loginData )
	{
		LoginStatusRecord record = createLoginStatusRec( loginData );

		String query = "UPDATE " + tableName_;
		query = query + " SET status = '" + record.loginStatus_ + "', ";
		query = query + "login_timestamp = '" + record.loggedInTimestamp_
		        + "'";
		query = query + " WHERE user_rel_key ='" + record.userKey_ + "'";

		// query = query + " Commit ";

		errorMaster_.insert( "Query = " + query );

		Statement stmt = null;
		Connection con = null;

		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			insertResult = stmt.executeUpdate( query );

			stmt.executeQuery( "Commit" );

			if( insertResult > 0 ) return 0;

			return -1;

		}

		catch( SQLException ex )
		{
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::LoginStatusTable:update"
			        + ex );

			return -2;
		}
		catch( Exception ex )
		{
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::LoginStatusTable:update"
			        + ex );

			return -3;
		}
		
		finally
        {
            try
            {
                if ( stmt != null )
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
	 * Method : getStatus( )
	 * 
	 * Input : UserProfileKey userKey, loginData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get login status of given user key
	 * 
	 */
	
	public int getStatus( UserProfileKey userKey, LoginData loginData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		ResultSet rs = null;

		query = "SELECT user_rel_key, status FROM " + tableName_ + " WHERE user_rel_key = '" + userKey.email_
		        + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				loginData.emailid_ = rs.getString( "user_rel_key" );
				loginData.loginStatus_	= rs.getString( "status" );

				return 0; // Success
			}

			return -1; // No record for user profile key

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::LoginStatusTable:getStatus"
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::LoginStatusTable:getStatus"
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -3;
		}
		
		finally
        {
            try
            {
            	if( rs != null )
            	{
            		rs.close( );
            	}
                if ( stmt != null )
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
	 * Method : get( )
	 * 
	 * Input : UserProfileKey userKey, loginData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get login status of given user key
	 * 
	 */
	
	public int get( Map<UserProfileKey, LoginData> loginStatsMap )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		ResultSet rs = null;

		query = "SELECT user_rel_key, status FROM " + tableName_;

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				
				LoginData data = new LoginData( );
				
				UserProfileKey userProfileKey = new UserProfileKey( );
				userProfileKey.email_ = rs.getString( "user_rel_key" );
				
				data.emailid_ = userProfileKey.email_;
				data.loginStatus_	= rs.getString( "status" );
				
				loginStatsMap.put( userProfileKey, data );
				
				data	= null;
			}

			return 0; // Success

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::LoginStatusTable:getStatus"
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::LoginStatusTable:getStatus"
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -3;
		}
		
		finally
        {
            try
            {
            	if( rs != null )
            	{
            		rs.close( );
            	}
                if ( stmt != null )
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
	 * Method : isEmailExists( )
	 * 
	 * Input : UserProfileKey userKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check whether the record exists for given user key
	 * 
	 */

	public int isEmailExists( UserProfileKey userKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT user_rel_key FROM " + tableName_ + " WHERE user_rel_key = '" + userKey.email_
		        + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				return 0; // Success
			}

			return -1; // No record for user profile key

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::LoginStatusTable:isEmailExists" + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::LoginStatusTable:isEmailExists" + ex;

			errLogger_.logMsg( errorMessage );

			return -3;
		}
		
		finally
        {
            try
            {
                if ( stmt != null )
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
	 * Method : delete( )
	 * 
	 * Input : UserProfileKey userKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the login details from
	 * login_login table
	 */

	public int delete( UserProfileKey userKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE user_rel_key = '" + userKey.email_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) return 0;

			return -1;

		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::LoginStatusTable:delete" + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::LoginStatusTable:delete" + ex;

			errLogger_.logMsg( errorMessage );

			return -3;
		}
		
		finally
        {
            try
            {
                if ( stmt != null )
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
