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

import org.omg.CORBA.LongHolder;

import utils.ErrorLogger;
import utils.IntHolder;
import core.dept.DeptKey;
import core.privilege.UsergroupPrivilegesData;
import core.usermgmt.GroupData;
import core.usermgmt.GroupKey;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  GroupPrivilegesTable.java 
 *
 * Created on Mar 4, 2013 10:56:20 AM
 */

/*
 * Class  : GroupPrivilegesTable
 * 
 * Purpose: It is used to query the group_privileges table.
 * 
 */

public class GroupPrivilegesTable
{
    private static ErrorMaster errorMaster_ = null;


	private String tableName_;

	/*
	 * Method : GroupPrivilegesTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public GroupPrivilegesTable()
	{
           

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "group_privileges";
	}

	/*
	 * Method : insert( )
	 * 
	 * Input : UsergroupPrivilegesData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the group data values into
	 * group_privileges table
	 */

	public int insert( UsergroupPrivilegesData privilegesData )
	{
		GroupPrivilegesRecord record = createGroupPriRecord( privilegesData );

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
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "GroupPrivilegeTable : insert :: SQL Error "
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
	 * Method : update( )
	 * 
	 * Input : UsergroupPrivilegesData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the GroupData values into
	 * group_privileges table
	 */

	public int update( UsergroupPrivilegesData privilegesData )
	{
		GroupPrivilegesRecord record = createGroupPriRecord( privilegesData );

		String query = "UPDATE " + tableName_;
		query = query + " SET privileges = " + record.privileges_;
		query = query + " WHERE group_rel_key ='" + record.groupRelKey_ + "'";

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
			errLogger_.logMsg( "GroupPrivilegesTable : update :: SQL Error " + ex );

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
	 * Method : getGroupPrivileges( )
	 * 
	 * Input :  GroupKey, UsergroupPrivilegesData (As ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve GroupData values from
	 * group_privileges table for given Group key
	 */

	public int getGroupPrivileges( GroupKey key, LongHolder privileges )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		String groupKeyString = convertGroupKeyStr( key );
		
		query = "SELECT * FROM " + tableName_ + " WHERE group_rel_key = '" + groupKeyString
		        + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				privileges.value	= rs.getLong( "privileges" );
				errorMaster_.insert( "Privileges --- "+privileges.value );
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "GroupPrivilegesTable : getGroupPrivileges :: SQL Error "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
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
	 * Input : GroupKey key
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the GroupData values from
	 * group_privileges table
	 */

	public int delete( GroupKey key )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		String groupKeyString = convertGroupKeyStr( key );
		
		query = "DELETE FROM " + tableName_ + " WHERE group_rel_key = '" + groupKeyString + "'";

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
			String errorMessage = "GroupPrivilegesTable : delete :: SQL Error "
			        + ex;

			errLogger_.logMsg( errorMessage );

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
	 * Method : createGroupPriRecord( )
	 * 
	 * Input : UsergroupPrivilegesData obj
	 * 
	 * Return : GroupPrivilegesRecord
	 * 
	 * Purpose: It converts GroupData to GroupPrivilegesRecord
	 */

	private GroupPrivilegesRecord createGroupPriRecord(
	        UsergroupPrivilegesData privilegesData )
	{
		GroupPrivilegesRecord record = new GroupPrivilegesRecord( );

		GroupKey key = privilegesData.groupKey_;
		
		String groupKeyString = convertGroupKeyStr( key );
		
		// set the values
		record.groupRelKey_ = groupKeyString;
		record.privileges_	= privilegesData.privilegesValue_;
		
		return record;
	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : GroupPrivilegesRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using GroupPrivilegesRecord and
	 * returns as string
	 */

	private String formInsertQuery( GroupPrivilegesRecord record )
	{
		
		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(group_rel_key, privileges)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.groupRelKey_ + "', ";
		insertQuery = insertQuery + record.privileges_;

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
	
	/*
	 * Method : convertGroupKeyStr( ) Input : groupKey object Return : String of
	 * groupKey which is stored in db
	 * 
	 * Purpose: This method is used to convert groupKey object to groupKey string
	 */

	private String convertGroupKeyStr( GroupKey groupKey )
	{
		String groupKeyStr = groupKey.companyRegnKey_.companyPhoneNo_ + ":"
		        + groupKey.groupName_;
		return groupKeyStr;
	}
	
	/*
	 * Method : updateKey( )
	 * 
	 * Input : GroupKey updatedKey, GroupKey oldKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the groupkey into
	 * group_privileges table
	 */

	public int updateKey( GroupKey updatedKey, GroupKey oldKey )
	{
		String updatedKeyStr = convertGroupKeyStr( updatedKey );
		String oldKeyStr = convertGroupKeyStr( oldKey );
				
		String query = "UPDATE " + tableName_;
		query = query + " SET group_rel_key = '" + updatedKeyStr + "'";
		query = query + " WHERE group_rel_key ='" + oldKeyStr + "'";

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
			errLogger_.logMsg( "GroupPrivilegesTable : update :: SQL Error " + ex );

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
