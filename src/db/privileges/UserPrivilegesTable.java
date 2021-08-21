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

package db.privileges;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.omg.CORBA.LongHolder;

import utils.IntHolder;

import utils.ErrorLogger;

import core.privilege.UserPrivilegesData;
import core.regn.UserProfileKey;
import db.utils.DBConnect;

/*
 * Class  : UserPrivilegesTable
 * 
 * Purpose: It is used to query the user_privileges table.
 * 
 */
public class UserPrivilegesTable
{

	private String tableName_;

	/*
	 * Method : DeptPrivilegesTable( ) - constructor
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public UserPrivilegesTable()
	{
		this.tableName_ = "user_privileges";
	}

	/*
	 * Method : insertPolicies( )
	 * 
	 * Input : UserProfileData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the UserProfileData values into
	 * user_account_policies table
	 */

	public int insert( UserPrivilegesData privilegesData )
	{
		UserPrivilegesRecord userPriRec = new UserPrivilegesRecord( );
		
		userPriRec.userKey_ = privilegesData.userKey_.email_;
		userPriRec.privileges_	= privilegesData.privilegesValue_;

		String query = formInsertQuery( userPriRec );

		Statement stmt = null;
		Connection con = null;

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
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::UserPrivilegesTable:insert"
			        + ex );

			return -2;
		}
		
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::UserPrivilegesTable:insert"
			        + ex );

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
	 * Method : getUserPrivileges( ) 
	 * 
	 * Input : UserProfileKey object and IntHolder object 
	 * 
	 * Return : int
	 * 
	 * Purpose: It get the User privileges for given UserProfileKey and assign
	 * to IntHolder parameter so the caller class use this value. And return
	 * type indicates it fetch the privileges successfully not not.
	 */

	public int getUserPrivileges( UserProfileKey userProfileKey, LongHolder privileges )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errorMessage = "";

		String key = userProfileKey.email_;

		query = "SELECT * FROM " + tableName_ + " WHERE user_rel_key = '" + key + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				privileges.value = rs.getLong( "privileges" );
			}

			return 0;
		}
		catch( SQLException ex ) // SqlException
		{
			errorMessage = "Exception::UserPrivilegesTable:getUserPrivileges-"
			        + ex;

			errLogger.logMsg( errorMessage );

			return -1; 
		}
		catch( Exception ex ) // General Exception
		{
			errorMessage = "Exception::UserPrivilegesTable:getUserPrivileges-"
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
	
	/*
	 * Method : delete( )
	 * 
	 * Input : UserProfileKey userKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the user privilege details from
	 * user_privileges table
	 */

	public int deletePrivileges( UserProfileKey userKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errorMessage = "";
		

		query = "DELETE FROM " + tableName_ + " WHERE user_rel_key = '" + userKey.email_ + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) return 0;

			return 0;

		}
		catch( SQLException ex ) // Sql Exception
		{
			errorMessage = "Exception::UserPrivilegesTable:delete-" + ex;

			errLogger.logMsg( errorMessage );

			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errorMessage = "Exception::UserPrivilegesTable:delete-" + ex;

			errLogger.logMsg( errorMessage );

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
	 * Method : updatePrivileges( )
	 * 
	 * Input : UserProfileKey key
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the user privileges values into
	 * user_privileges table
	 */

	public int updatePrivileges( UserProfileKey key, UserPrivilegesData userPrivilegesData )
	{
		UserPrivilegesRecord record = new UserPrivilegesRecord( );
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		record.userKey_	= key.email_;
		record.privileges_ = userPrivilegesData.privilegesValue_;
		
		String query = "UPDATE " + tableName_;
		query = query + " SET privileges = " + record.privileges_;
		query = query + " WHERE user_rel_key ='" + record.userKey_ + "'";

		// query = query + " Commit ";
		
		System.out.println( "Query = " + query );

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

		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::LoginAttemtpsTable:updateLoginAttempts-"
			        + ex );

			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::LoginAttemtpsTable:updateLoginAttempts-"
			        + ex );

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
	 * Method : updatePrivileges( )
	 * 
	 * Input : UserProfileKey key
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the user privileges values into
	 * user_privileges table
	 */

	public int updatePrivileges( UserProfileKey key, LongHolder privileges )
	{	
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String query = "UPDATE " + tableName_;
		query = query + " SET privileges = " + privileges.value;
		query = query + " WHERE user_rel_key ='" + key.email_ + "'";

		// query = query + " Commit ";
		
		System.out.println( "Query = " + query );

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

		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::UserPrivilegesTable.update( UserProfileKey, LongHolder ) - "
			        + ex );

			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::UserPrivilegesTable.update( UserProfileKey, LongHolder ) - "
			        + ex );

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
	 * Method : updatePrivileges( )
	 * 
	 * Input : UserProfileKey key
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the user privileges values into
	 * user_privileges table
	 */

	public int updatePrivileges( UserProfileKey key )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		UserPrivilegesRecord record = new UserPrivilegesRecord( );

		record.userKey_	= key.email_;
		
		String query = "UPDATE " + tableName_;
		query = query + " SET privileges = " + record.privileges_;
		query = query + " WHERE user_rel_key ='" + record.userKey_ + "'";

		// query = query + " Commit ";
		
		System.out.println( "Query = " + query );

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

		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::LoginAttemtpsTable:updateLoginAttempts-"
			        + ex );

			return -2;
		}
		catch( Exception ex )  // General Exception
		{
			errLogger.logMsg( "Exception::LoginAttemtpsTable:updateLoginAttempts-"
			        + ex );

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
	 * Method : formInsertQuery( )
	 * 
	 * Input : UserAccountPoliciesRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using UserAccountPoliciesRecord and
	 * returns as string
	 */

	private String formInsertQuery( UserPrivilegesRecord userPriRec )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(user_rel_key, privileges)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + userPriRec.userKey_ + "', ";
		insertQuery = insertQuery + userPriRec.privileges_;
		insertQuery = insertQuery + ")";

		return insertQuery;
	}
	
}
