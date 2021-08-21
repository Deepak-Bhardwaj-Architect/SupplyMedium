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

import core.regn.LoginData;
import core.regn.UserProfileKey;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.utils.DBConnect;

import utils.ErrorLogger;
import utils.ErrorMaster;
import utils.PasswordStore;

/**
 * File:  UserLoginTable.java 
 *
 * Created on Jan 5, 2013 3:28:59 PM
 */

public class UserLoginTable
{
private static ErrorMaster errorMaster_ = null;


	private String tableName_;

	/*
	 * Method : UserLoginTable( ) - constructor
	 * 
	 * Purpose: It is used initialize the table name
	 */
	
	public UserLoginTable()
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "user_login";
	}

	/*
	 * Method : insert( )
	 * 
	 * Input : LoginData obj
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to insert the login data values into
	 * user_login table
	 */
	
	public int insert( LoginData loginData )
	{
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		// Here the loginData will come in as the parameter,

		// Create UserLoginRecord using loginData
		UserLoginRecord userLoginRecObj = createUserLoginRecord( loginData );

		// Form the insertQuery using the UserLoginRecord object.
		String query = formInsertQuery( userLoginRecObj );

		Statement stmt = null;
		Connection con = null;

		errorMaster_.insert( "Insert into login query = "+query );
		
		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			insertResult = stmt.executeUpdate( query );

			if( insertResult > 0 ) return 0;

			return -1;
		}

		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::UserLoginTable:insert-" + ex );
			
			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::UserLoginTable:insert-" + ex );
			
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
	 * Method : checkLoginCredentials( )
	 * 
	 * Input : LoginData obj
	 * 
	 * Return : int - result - if 0 success otherwise invalid credential.
	 * 
	 * Purpose: It is used to check whether given credential are valid or not
	 */
	
	public int checkLoginCredentials( LoginData loginData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		UserLoginRecord userLoginRec = createUserLoginRecord( loginData );

		query = "SELECT * FROM " + tableName_ + " WHERE email = '"
		        + userLoginRec.emailAddress_ + "'";
		query = query + " AND password = '" + userLoginRec.encryptedPassword_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				return 0; // Success
			}

			return -2; // InvalidCredentials
		}

		catch( SQLException ex ) // SqlException
		{
			errLogger.logMsg( "Exception::UserLoginTable:checkLoginCredentials-"+ex );
			return -1; 
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::UserLoginTable:checkLoginCredentials-"+ex );
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


	/*
	 * Method : getPasswordTimestampForEmail( )
	 * 
	 * Input : email and logindata as ref
	 * 
	 * Return : int - result - if 0 success otherwise failed
	 * 
	 * Purpose: It is used to fetch the created time stamp for given email id. 
	 * And assign the created time stamp value in loginData parameter 
	 * so it is copied in caller class
	 */
	
	public int getPasswordTimestampForEmail( String email, LoginData loginData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "SELECT created_timestamp FROM " + tableName_ + " WHERE email = '" + email
		        + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				loginData.createdTimestamp_ = rs.getTimestamp( "created_timestamp" );

				return 0; // Success
			}

			return -1; // No record for user profile key

		}

		catch( SQLException ex )  // Sql Exception
		{
			errLogger.logMsg( "Exception::UserProfileTable:getPasswordTimestampForEmail-"+ex );

			return -2; 
		}
		catch( Exception ex )  // General Exception
		{
			errLogger.logMsg( "Exception::UserProfileTable:getPasswordTimestampForEmail-"+ex );

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
	 * Method : isEmailExists( )
	 * 
	 * Input : UserProfileKey object
	 * 
	 * Return : int - result - if 0 success otherwise failed
	 * 
	 * Purpose: It is used to check email id already exist in user_login table.
	 * If email id already exist it return 0 otherwise failed.
	 */
	
	public int isEmailExists( UserProfileKey userKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "SELECT email FROM " + tableName_ + " WHERE email = '" + userKey.email_
		        + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				return 0; // Email id already exist in user_login_table
			}

			return -1; // Email id not exist in user_login table

		}

		catch( SQLException ex )  // Sql Exception
		{
			errLogger.logMsg( "Exception::UserProfileTable:isEmailExists-"+ex );

			return -2; 
		}
		catch( Exception ex )  // General Exception
		{
			errLogger.logMsg( "Exception::UserProfileTable:isEmailExists-"+ex );

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
	 * Method : resetPassword( )
	 * 
	 * Input : LoginData object
	 * 
	 * Return : int - result - if 0 success otherwise failed
	 * 
	 * Purpose: It is used to update the password the for given email id.
	 * If updated successfully return 0. Otherwise return failed.
	 */
	
	public int resetPassword( LoginData loginData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "UPDATE " + tableName_;
		query = query + " SET password = '" + loginData.password_ + "' ";
		query = query + " WHERE email ='" + loginData.emailid_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			int updateResult = 0;

			updateResult = stmt.executeUpdate( query );

			if( updateResult > 0 )
			{
				return 0; // Success
			}

			else
			{
				return -1; // No record for user profile key
			}

		}

		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::UserProfileTable:resetPassword-"+ex );

			return -2; 
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::UserProfileTable:resetPassword-"+ex );

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
	 * Method : delete( )
	 * 
	 * Input : UserProfileKey userKey
	 * 
	 * Return : int, 0 - success otherwise failed
	 * 
	 * Purpose: It is used to delete the login details from
	 * user_login table
	 */

	public int delete( UserProfileKey userKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "DELETE FROM " + tableName_ + " WHERE email = '" + userKey.email_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			
			if( con == null )
			{
				return -1;
			}
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) return 0;

			return 0;

		}
		catch( SQLException ex )  // Sql Exception
		{
			errLogger.logMsg( "Exception::UserLoginTable:delete-"+ex );

			return -2;
		}
		catch( Exception ex )  // General Exception
		{
			errLogger.logMsg( "Exception::UserLoginTable:delete-"+ex );

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
	 * Method : get)
	 * 
	 * Input : UserProfileKey and logindata as ref
	 * 
	 * Return : int - result - if 0 success otherwise failed
	 * 
	 * Purpose: It is used to fetch the created time stamp for given UserProfileKey. 
	 * And assign the created time stamp value in loginData parameter 
	 * so it is copied in caller class
	 */
	
	public int get( UserProfileKey userKey, LoginData loginData )
	{
		Statement stmt 	= null;
		Connection con 	= null;
		String query 		= null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "SELECT created_timestamp FROM " + tableName_ + " WHERE email = '" + userKey.toString( )
		        + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				loginData.createdTimestamp_ = rs.getTimestamp( "created_timestamp" );

				return 0; // Success
			}

			return -1; // No record for user profile key

		}

		catch( SQLException ex )  // Sql Exception
		{
			errLogger.logMsg( "Exception::UserProfileTable.get( ) - "+ex );

			return -2; 
		}
		catch( Exception ex )  // General Exception
		{
			errLogger.logMsg( "Exception::UserProfileTable.get( ) - "+ex );

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
	 * Method : getRecord( )
	 * 
	 * Input : String email, ref LoginData
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get login record for given email id
	 * table
	 */

	public int getRecord( String email, LoginData loginData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT email, password, created_timestamp FROM " + tableName_ + " WHERE email = '"
		        + email + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				loginData.emailid_	=	rs.getString( "email" );
				loginData.password_	= 	rs.getString( "password" );
				loginData.createdTimestamp_ = rs.getTimestamp( "created_timestamp" );

				return 0; // Success
			}

			return -1; // No record for email
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::UserLoginTable.getPasswordTimestamp() - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch (Exception e) 
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::UserLoginTable.getPasswordTimestamp() - "
			        + e;

			errLogger_.logMsg( errorMessage );

			return -3; // Sql error
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
	 * Method : createUserLoginRecord( )
	 * 
	 * Input : LoginData obj
	 * 
	 * Return : UserLoginRecord obj
	 * 
	 * Purpose: It converts LoginData object to UserLoginRecord object
	 */
	
	private UserLoginRecord createUserLoginRecord( LoginData loginData )
	{
		// Form the record using the loginData and return it

		UserLoginRecord userLoginRecObj = new UserLoginRecord( );

		// set the values
		userLoginRecObj.emailAddress_ = loginData.emailid_;

		PasswordStore passwordStoreObj = new PasswordStore( );
		String encryptedPassword = passwordStoreObj.getEncryptedValue( loginData.password_ );
		userLoginRecObj.encryptedPassword_ = encryptedPassword;

		return userLoginRecObj;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : UserLoginRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using UserLoginRecord and
	 * returns as string
	 */
	
	private String formInsertQuery( UserLoginRecord userLoginRecObj )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(user_id, email, password)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + userLoginRecObj.userId_ + ", ";
		insertQuery = insertQuery + "'" + userLoginRecObj.emailAddress_ + "', ";
		insertQuery = insertQuery + "'" + userLoginRecObj.encryptedPassword_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
}
