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

import core.managefolder.GroupFolderData;
import db.utils.DBConnect;
import utils.ErrorLogger;
import utils.ErrorMaster;

/**
 * File:  GroupFolderAccessTable.java 
 *
 * Created on May 10, 2013 4:15:53 PM
 */

/*
 * Class: GroupFolderAccessTable
 * 
 *  Purpose: This class is used to perform db operations like
 *  insert, update, delete and find for table group_folder_access table
 *  of supplymedium_db
 */

public class GroupFolderAccessTable
{
private static ErrorMaster errorMaster_ = null;


	private String tableName_;
	
	ErrorLogger errLogger_;
	
	/*Constructor*/
	
	public GroupFolderAccessTable( )
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "group_folder_access";
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method: insert
	 * 
	 * Input: GroupFolderData
	 * 
	 * Return: int
	 * 
	 * Purpose: This method converts the GroupFolderData object into 
	 * GroupFoldeAccessRecord object and inserts into the group_folder_access
	 * table
	 */
	
	public int insert( GroupFolderData groupFolderData )
	{
		GroupFolderAccessRecord record = createGroupFolderAccessRecord( groupFolderData );

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
			errLogger_.logMsg( "Exception::GroupFolderAccessTable.insert() - " + ex );

			return -2;
		}
		catch( Exception ex )
		{
			errLogger_.logMsg( "Exception::GroupFolderAccessTable.insert() - " + ex );

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
	 * Input: GroupFolderData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to update the group_folder_access table
	 * with the values present in the GroupFolderData object
	 */
	
	public int update( GroupFolderData groupFolderData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		GroupFolderAccessRecord record = createGroupFolderAccessRecord( groupFolderData );
		
		query = "UPDATE " + tableName_;
		query = query + " SET read_flag = " + record.readFlag_ + ", ";
		query = query + " read_write_flag = " + record.readWriteFlag_ ;
		
		query = query + " WHERE group_rel_key = '" + record.groupKey_+ "' AND ";
		query = query + " folder_rel_key = '" + record.folderKey_ + "' AND ";
		query = query + " dept_rel_key = '"	+ record.deptKey_ + "'";
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
			errLogger_.logMsg( "Exception::GroupFolderAccessTable.update() - "+ex );

			return -2; 
		}
		catch( Exception ex ) // General Exception
		{
			errLogger_.logMsg( "Exception::GroupFolderAccessTable.update() - "+ex );

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
	 * Input: GroupFolderData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to delete the record from group_folder_access
	 * table
	 */
	
	public int delete( GroupFolderData groupFolderData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		GroupFolderAccessRecord record = createGroupFolderAccessRecord( groupFolderData );

		query = "DELETE FROM " + tableName_ + " WHERE group_rel_key = '" + record.groupKey_ + "'";
		query = " AND folder_rel_key = '" + record.folderKey_ + "'";

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
			errLogger_.logMsg( "Exception::GroupFolderAccessTable.delete() - "+ex );

			return -2;
		}
		catch( Exception ex )  // General Exception
		{
			errLogger_.logMsg( "Exception::GroupFolderAccessTable.delete() - "+ex );

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
	 * Input: GroupFolderData obj 
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to find the record for given inputs through
	 * GroupFolderData obj from group_folder_access table
	 */
	
	public int find( GroupFolderData groupFolderData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE regn_rel_key = '"
				+ groupFolderData.regnKey_.toString( ) + "' AND group_rel_key = '"
		        + groupFolderData.groupKey_.toString( ) + "' AND folder_rel_key = '"
				+ groupFolderData.folderKey_.toString( ) + "' AND dept_rel_key = '"
				+ groupFolderData.deptKey_.toString( ) + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				groupFolderData.groupFolderAccessId_ = rs.getLong( "group_folder_access_id" );
				groupFolderData.readFlag_ = rs.getInt( "read_flag" );
				groupFolderData.readWriteFlag_ = rs.getInt( "read_write_flag" );
			}

			return 0; // No record for email
		}

		catch( SQLException ex )
		{
			String errorMessage = "Exception::GroupFolderAccessTable.find() - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch (Exception e) 
		{
			String errorMessage = "Exception::GroupFolderAccessTable.find() - "
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
	 * regn_rel_key, folder_rel_key, group_rel_key and dept_rel_key
	 */
	
	public int isExist( GroupFolderData groupFolderData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE regn_rel_key = '"
				+ groupFolderData.regnKey_.toString( ) + "' AND group_rel_key = '"
		        + groupFolderData.groupKey_.toString( ) + "' AND folder_rel_key = '"
				+ groupFolderData.folderKey_.toString( ) + "' AND dept_rel_key = '"
				+ groupFolderData.deptKey_.toString( ) + "'";

		errorMaster_.insert( "Query=" + query );

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
			String errorMessage = "Exception::GroupFolderAccessTable.isExist() - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch (Exception e) 
		{
			String errorMessage = "Exception::GroupFolderAccessTable.isExist() - "
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
	 * Method: createGroupFolderAccessRecord
	 * 
	 * Input: GroupFolderData obj
	 * 
	 * Return: GroupFolderAccessRecord
	 * 
	 * Purpose: This method converts the GroupFolderData to
	 * GroupFolderAccessRecord
	 */
	
	private GroupFolderAccessRecord createGroupFolderAccessRecord( GroupFolderData groupFolderData )
	{		
		GroupFolderAccessRecord record = new GroupFolderAccessRecord( );

		// set the values
		
		record.groupFolderAccessId_ = groupFolderData.groupFolderAccessId_;
		record.folderKey_			= groupFolderData.folderKey_.toString( );
		record.groupKey_ 			= groupFolderData.groupKey_.toString( );
		record.deptKey_				= groupFolderData.deptKey_.toString( );
		record.readFlag_	 		= groupFolderData.readFlag_;
		record.readWriteFlag_ 		= groupFolderData.readWriteFlag_;
		record.regnKey_				= groupFolderData.regnKey_.toString( );

		return record;
	}

	/*
	 * Method: formInsertQuery
	 * 
	 * Input: GroupFolderAccessRecord obj
	 * 
	 * Return: String
	 * 
	 * Purpose: This method is used to build insert query using
	 * GroupFolderAccessRecord object and returns the query
	 */
	
	private String formInsertQuery( GroupFolderAccessRecord record )
	{

		// form the query
		String insertQuery = null;
		String insertValues = null;

		insertValues = "(regn_rel_key, group_rel_key, folder_rel_key, dept_rel_key, ";
		insertValues = insertValues + "read_flag, read_write_flag)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.regnKey_ + "', ";
		insertQuery = insertQuery + "'" + record.groupKey_ + "', ";
		insertQuery = insertQuery + "'" + record.folderKey_ + "', ";
		
		insertQuery = insertQuery + "'" + record.deptKey_ + "', ";

		insertQuery = insertQuery + record.readFlag_ + ", ";
		insertQuery = insertQuery + record.readWriteFlag_;
		
		insertQuery = insertQuery + ")";

		return insertQuery;
	}
	
}
