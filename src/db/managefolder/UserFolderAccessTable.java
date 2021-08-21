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

package db.managefolder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import core.dept.DeptFolderKey;
import core.dept.DeptKey;
import core.managefolder.UserFolderData;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import db.utils.DBConnect;
import utils.ErrorLogger;

/**
 * File:  UserFolderAccessTable.java 
 *
 * Created on May 10, 2013 4:15:42 PM
 */

/*
 * Class: UserFolderAccessTable
 * 
 *  Purpose: This class is used to perform db operations like
 *  insert, update, delete and find for table user_folder_access table
 *  of supplymedium_db
 */

public class UserFolderAccessTable
{
	private String tableName_;
	
	ErrorLogger errLogger_;
	
	/*Constructor*/
	
	public UserFolderAccessTable( )
	{
		this.tableName_ = "user_folder_access";
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method: insert
	 * 
	 * Input: UserFolderData
	 * 
	 * Return: int
	 * 
	 * Purpose: This method converts the UserFolderData object into 
	 * UserFoldeAccessRecord object and inserts into the user_folder_access
	 * table
	 */
	
	public int insert( UserFolderData userFolderData )
	{
		UserFolderAccessRecord record = createUserFolderAccessRecord( userFolderData );

		String query = formInsertQuery( record );

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
			errLogger_.logMsg( "Exception::UserFolderAccessTable.insert() - " + ex );

			return -2;
		}
		catch( Exception ex )
		{
			errLogger_.logMsg( "Exception::UserFolderAccessTable.insert() - " + ex );

			return -3;
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
	 * Method: update
	 * 
	 * Input: UserFolderData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to update the user_folder_access table
	 * with the values present in the UserFolderData object
	 */
	
	public int update( UserFolderData userFolderData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		UserFolderAccessRecord record = createUserFolderAccessRecord( userFolderData );
		
		query = "UPDATE " + tableName_;
		query = query + " SET read_flag = " + record.readFlag_ + ", ";
		query = query + " read_write_flag = " + record.readWriteFlag_ ;
		
		query = query + " WHERE user_rel_key ='" + record.userKey_ + "' AND ";
		query = query + " folder_rel_key='" + record.folderKey_ + "' AND ";
		query = query + " dept_rel_key='" + record.deptKey_ + "'";

		System.out.println( "Query=" + query );

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
			errLogger_.logMsg( "Exception::UserFolderAccessTable.update() - "+ex );

			return -2; 
		}
		catch( Exception ex ) // General Exception
		{
			errLogger_.logMsg( "Exception::UserFolderAccessTable.update() - "+ex );

			return -3; 
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
	 * Method: delete
	 * 
	 * Input: UserFolderData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to delete the record from user_folder_access
	 * table
	 */
	
	public int delete( UserFolderData userFolderData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		UserFolderAccessRecord record = createUserFolderAccessRecord( userFolderData );

		query = "DELETE FROM " + tableName_ + " WHERE user_rel_key = '" + record.userKey_ + "'";
		query = " AND folder_rel_key = '" + record.folderKey_ + "'";

		System.out.println( "Query=" + query );

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
			errLogger_.logMsg( "Exception::UserFolderAccessTable.delete() - "+ex );

			return -2;
		}
		catch( Exception ex )  // General Exception
		{
			errLogger_.logMsg( "Exception::UserFolderAccessTable.delete() - "+ex );

			return -3;
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
	 * Method: find
	 * 
	 * Input: UserFolderData obj 
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to find the record for given inputs through
	 * UserFolderData obj from user_folder_access table
	 */
	
	public int find( UserFolderData userFolderData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE regn_rel_key = '"
				+ userFolderData.regnKey_.toString( ) + "' AND user_rel_key = '"
		        + userFolderData.userKey_.toString( ) + "' AND folder_rel_key = '"
				+ userFolderData.folderKey_.toString( ) + "' AND dept_rel_key = '"
				+ userFolderData.deptKey_.toString( ) + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				userFolderData.userFolderAccessId_ = rs.getLong( "user_folder_access_id" );
				userFolderData.readFlag_ = rs.getInt( "read_flag" );
				userFolderData.readWriteFlag_ = rs.getInt( "read_write_flag" );
			}

			return 0; // No record for email
		}

		catch( SQLException ex )
		{
			String errorMessage = "Exception::UserFolderAccessTable.find() - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch (Exception e) 
		{
			String errorMessage = "Exception::UserFolderAccessTable.find() - "
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
	 * Method: find
	 * 
	 * Input: UserFolderData obj 
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to find the record for given inputs through
	 * UserFolderData obj from user_folder_access table
	 */
	
	public int find( UserProfileKey userKey, List<DeptKey> deptKeyArr )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT dept_rel_key FROM " + tableName_ + " WHERE user_rel_key = '"
				+ userKey.toString( ) + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				DeptKey key = new DeptKey( );
				
				String deptKeyStr = rs.getString( "dept_rel_key" );
				
				int result = convertToDeptKey( deptKeyStr, key );
				
				if( result == 0)
				{
					deptKeyArr.add( key );
				}
				
				key = null;
				deptKeyArr = null;
			}

			return 0; // No record for email
		}

		catch( SQLException ex )
		{
			String errorMessage = "Exception::UserFolderAccessTable.find( UserProfileKey, List<DeptKey> ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch (Exception e) 
		{
			String errorMessage = "Exception::UserFolderAccessTable.find( UserProfileKey, List<DeptKey> ) - "
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
	 * Method: find
	 * 
	 * Input: UserFolderData obj 
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to find the record for given inputs through
	 * UserFolderData obj from user_folder_access table
	 */
	
	public int find( UserProfileKey userKey, DeptKey deptKey, List<UserFolderData> userFolderDataList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE user_rel_key = '"
		        + userKey.toString( ) + "' AND dept_rel_key = '"
				+ deptKey.toString( ) + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				UserFolderData userFolderData = new UserFolderData( );
				
				String folderKeyStr = rs.getString( "folder_rel_key" );
				
				userFolderData.folderKey_ = convertToFolderKey( folderKeyStr ) ;
				
				userFolderData.readFlag_ = rs.getInt( "read_flag" );
				
				userFolderData.readWriteFlag_ = rs.getInt( "read_write_flag" );
				
				userFolderDataList.add( userFolderData );
				
				userFolderData = null;
			}

			return 0; // No record for email
		}

		catch( SQLException ex )
		{
			String errorMessage = "Exception::UserFolderAccessTable.find( ( UserProfileKey, DeptKey, List<UserFolderData> ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch (Exception e) 
		{
			String errorMessage = "Exception::UserFolderAccessTable.find( ( UserProfileKey, DeptKey, List<UserFolderData> ) - "
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
	 * Method: isExist
	 * 
	 * Input: UserFolderData obj 
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to find whether there exist a record for given
	 * regn_rel_key, folder_rel_key, user_rel_key and dept_rel_key
	 */
	
	public int isExist( UserFolderData userFolderData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE regn_rel_key = '"
				+ userFolderData.regnKey_.toString( ) + "' AND user_rel_key = '"
		        + userFolderData.userKey_.toString( ) + "' AND folder_rel_key = '"
				+ userFolderData.folderKey_.toString( ) + "' AND dept_rel_key = '"
				+ userFolderData.deptKey_.toString( ) + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				return 0;
			}

			return -1; // No record for email
		}

		catch( SQLException ex )
		{
			String errorMessage = "Exception::UserFolderAccessTable.isExist() - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch (Exception e) 
		{
			String errorMessage = "Exception::UserFolderAccessTable.isExist() - "
			        + e;

			errLogger_.logMsg( errorMessage );

			return -3; // error
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
	 * Method: createuserFolderAccessRecord
	 * 
	 * Input: UserFolderData obj
	 * 
	 * Return: UserFolderAccessRecord
	 * 
	 * Purpose: This method converts the UserFolderData to
	 * UserFolderAccessRecord
	 */
	
	private UserFolderAccessRecord createUserFolderAccessRecord( UserFolderData userFolderData )
	{		
		UserFolderAccessRecord record = new UserFolderAccessRecord( );

		// set the values
		
		record.userFolderAccessId_ = userFolderData.userFolderAccessId_;
		record.folderKey_			= userFolderData.folderKey_.toString( );
		record.userKey_ 			= userFolderData.userKey_.toString( );
		record.readFlag_	 		= userFolderData.readFlag_;
		record.readWriteFlag_ 		= userFolderData.readWriteFlag_;
		record.regnKey_				= userFolderData.regnKey_.toString( );
		record.deptKey_				= userFolderData.deptKey_.toString( );

		return record;
	}

	/*
	 * Method: formInsertQuery
	 * 
	 * Input: UserFolderAccessRecord obj
	 * 
	 * Return: String
	 * 
	 * Purpose: This method is used to build insert query using
	 * UserFolderAccessRecord object and returns the query
	 */
	
	private String formInsertQuery( UserFolderAccessRecord record )
	{

		// form the query
		String insertQuery = null;
		String insertValues = null;

		insertValues = "(regn_rel_key, user_rel_key, folder_rel_key, dept_rel_key, ";
		insertValues = insertValues + "read_flag, read_write_flag)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.regnKey_ + "', ";
		insertQuery = insertQuery + "'" + record.userKey_ + "', ";
		insertQuery = insertQuery + "'" + record.folderKey_ + "', ";
		
		insertQuery = insertQuery + "'" + record.deptKey_ + "', ";

		insertQuery = insertQuery + record.readFlag_ + ", ";
		insertQuery = insertQuery + record.readWriteFlag_;
		
		insertQuery = insertQuery + ")";

		return insertQuery;
	}
	
	/*This method converts dept key string into dept key obj*/
	
	private int convertToDeptKey( String deptKeyStr, DeptKey key )
	{
		try
		{
			String[ ] deptKeyStrArr = deptKeyStr.split( ":" );
			
			key.companyRegnKey_ = new CompanyRegnKey( );
			
			key.companyRegnKey_.companyPhoneNo_ = deptKeyStrArr[0];
			
			key.deptName_  = deptKeyStrArr[1];
			
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger.instance( ).logMsg( "Exception::UserFolderAccessTable.convertToDeptKey( ) - " + ex );
			
			return -1;
		}
	}
	
/*This method converts folder key string into folder key obj*/
	
	private DeptFolderKey convertToFolderKey( String folderKeyStr )
	{
		String[ ] deptKeyStrArr = folderKeyStr.split( ":" );
		
		DeptFolderKey key = new DeptFolderKey( );
			
		key.companyRegnKey_ = new CompanyRegnKey( );
			
		key.companyRegnKey_.companyPhoneNo_ = deptKeyStrArr[0];
			
		key.folderName_  = deptKeyStrArr[1];
			
		return key;
	}
}
