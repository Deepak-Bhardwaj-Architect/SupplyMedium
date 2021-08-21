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

import utils.IntHolder;

import utils.ErrorLogger;

import core.dept.DeptKey;
import core.privilege.DeptPrivilegesData;
import db.utils.DBConnect;

/**
 * File:  DeptPrivilegesTable.java 
 *
 * Created on Mar 4, 2013 3:00:23 PM
 */

/*
 * Class  : DeptPrivilegesTable
 * 
 * Purpose: It is used to query the dept_privileges table.
 * 
 */
public class DeptPrivilegesTable
{

	private String tableName_;

	/*
	 * Method : DeptPrivilegesTable( ) - constructor
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public DeptPrivilegesTable()
	{
		this.tableName_ = "dept_privileges";
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : DeptPrivilegesData obj
	 * 
	 * Return : int - Result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to insert the deptPrivilegesData  values into
	 * dept_privileges table
	 */

	public int insert( DeptPrivilegesData deptPrivilegesData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		// convert deptPrivilegesData object to deptPrivilegesRecord object
		DeptPrivilegesRecord record = createDeptPrivilegesRecord( deptPrivilegesData );

		// form insert query from record object
		String query = formInsertQuery( record );

		Statement stmt = null;
		Connection con = null;

		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			insertResult = stmt.executeUpdate( query );

			if( insertResult > 0 ) 
				return 0; // Successfully inserted 

			return -1; // Insert Failed

		}

		catch( SQLException ex )
		{
			errLogger.logMsg( "Exception::DeptPrivilegesTable:insert-" + ex );

			return -2;
		}
		catch( Exception ex )
		{
			errLogger.logMsg( "Exception::DeptPrivilegesTable:insert-" + ex );

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
	 * Method : update( )
	 * 
	 * Input : DeptPrivilegesData obj
	 * 
	 * Return : int - Result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to udpate the DeptPrivilegesData values into
	 * dept_privileges table
	 */

	public int update( DeptPrivilegesData deptPrivilegesData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		// convert deptPrivilegesData object to deptPrivilegesRecord object
		
		DeptPrivilegesRecord record = createDeptPrivilegesRecord( deptPrivilegesData );

		String query = "UPDATE " + tableName_;
		query = query + " SET privileges = " + record.privileges_;
		query = query + " WHERE dept_rel_key ='" + record.deptKey_ + "'";

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

			if( insertResult > 0 ) 
				return 0;  // Successfully updated

			return -1; // Update failed

		}

		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::DeptPrivilegesTable:update-" + ex );

			return -2;
		}
		catch( Exception ex )  // General Exception
		{
			errLogger.logMsg( "Exception::DeptPrivilegesTable:update-" + ex );

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
	 * Input : DeptKey object
	 * 
	 * Return : int - Result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to delete the deptPrivilegesData values from
	 * dept_privileges table
	 */

	public int delete( DeptKey key )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errorMessage = "";
		
		// Convert deptKey object to string
		String deptKeyStr = convertDeptKeyStr( key );

		query = "DELETE FROM " + tableName_ + " WHERE dept_rel_key = '" + deptKeyStr + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) 
				return 0; // successfully deleted

			return -1;  // delete failed

		}
		catch( SQLException ex ) // Sql Exception
		{
			
			errorMessage = "Exception::DeptPrivilegesTable:delete-"
			        + ex;

			errLogger.logMsg( errorMessage );

			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			
			errorMessage = "Exception::DeptPrivilegesTable:delete-"
			        + ex;

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
	 * Method : getDeptPrivileges( ) Input : DeptKey object and IntHolder object
	 * Return : int
	 * 
	 * Purpose: It get the department privileges for given deptkey and assign to
	 * IntHolder parameter so the caller class use this value. And return type
	 * indicates it fetch the privileges successfully not not.
	 */

	public int getDeptPrivileges( DeptKey deptKey, IntHolder privileges )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errorMessage= "";

		// Convert deptKey object to string
		String key = convertDeptKeyStr( deptKey );

		query = "SELECT * FROM " + tableName_ + " WHERE dept_rel_key = '" + key + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				privileges.value = rs.getInt( "privileges" );
			}

			return 0;
		}
		catch( SQLException ex ) // SqlException
		{
			
			errorMessage = "Exception::DeptPrivilegesTable:getDeptPrivileges-"
			        + ex;

			errLogger.logMsg( errorMessage );

			return -1; 
		}
		catch( Exception ex )  // General Exception
		{
			
			errorMessage = "Exception::DeptPrivilegesTable:getDeptPrivileges-"
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
	 * Method : createDeptPrivilegesRecord( )
	 * 
	 * Input : DeptPrivilegesData object
	 * 
	 * Return : DeptPrivilegesRecord object
	 * 
	 * Purpose: It converts DeptPrivilegesData to DeptPrivilegesRecord
	 */

	private DeptPrivilegesRecord createDeptPrivilegesRecord(
	        DeptPrivilegesData deptPrivilegesData )
	{
		DeptPrivilegesRecord record = new DeptPrivilegesRecord( );

		// set the values
		record.deptKey_ 	= convertDeptKeyStr( deptPrivilegesData.deptData_.deptKey_ );
		record.privileges_	= deptPrivilegesData.privileges_;
		
		return record;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : DeptPrivilegesRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using DeptPrivilegesRecord and
	 * returns as string
	 */

	private String formInsertQuery( DeptPrivilegesRecord record )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(dept_rel_key, privileges)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.deptKey_ + "', ";
		insertQuery = insertQuery + "'" + record.privileges_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}

	/*
	 * Method : convertDeptKeyStr( ) Input : DeptKey object Return : String of
	 * deptkey which is stored in db
	 * 
	 * Purpose: This method is used to convert deptkey object to deptkey string
	 */

	private String convertDeptKeyStr( DeptKey deptKey )
	{
		String deptKeyStr = deptKey.companyRegnKey_.companyPhoneNo_ + ":"
		        + deptKey.deptName_;
		return deptKeyStr;
	}


	/*
	 * Method : updateKey( )
	 * 
	 * Input : DeptKey updatedKey, DeptKey oldKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the DeptKey into
	 * dept_privileges table
	 */

	public int updateKey( DeptKey updatedKey, DeptKey oldKey )
	{
		String updatedKeyStr = convertDeptKeyStr( updatedKey );
		String oldKeyStr = convertDeptKeyStr( oldKey );
				
		String query = "UPDATE " + tableName_;
		query = query + " SET dept_rel_key = " + updatedKeyStr;
		query = query + " WHERE dept_rel_key ='" + oldKeyStr + "'";

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

		catch( SQLException ex )
		{
			System.out.println( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::DetpPrivilegesTable:updateKey " + ex );

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
