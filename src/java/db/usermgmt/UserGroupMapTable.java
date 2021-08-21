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
import java.util.List;

import utils.ErrorLogger;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.usermgmt.GroupData;
import core.usermgmt.GroupKey;
import core.usermgmt.UserGroupMappingData;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  UserGroupMapTable.java 
 *
 * Created on Mar 4, 2013 5:22:19 PM
 */

public class UserGroupMapTable
{
    private static ErrorMaster errorMaster_ = null;


	private String tableName_;

	/*
	 * Method : UserGroupMapTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public UserGroupMapTable()
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "user_group_mapping";
	}

	/*
	 * Method : insert( )
	 * 
	 * Input : UserGroupMappingData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the group data values into
	 * user_group_mapping table
	 */

	public int insert( UserGroupMappingData data )
	{
		UserGroupMapRecord record = createUserGroupMapRecord( data );

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
			errLogger_.logMsg( "UserGroupMapTable : insert :: SQL Error " + ex );

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
	 * Input : UserGroupMappingData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the GroupData values into
	 * user_group_mapping table
	 */

	public int update( UserGroupMappingData data )
	{
		UserGroupMapRecord record = createUserGroupMapRecord( data );

		String query = "UPDATE " + tableName_;
		
		query = query + " SET user_rel_key = '" + record.userRelKey_ + "' ";
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
			errLogger_.logMsg( "UserGroupMapTable : update :: SQL Error " + ex );

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
	 * Method : getUserGroupMap( )
	 * 
	 * Input :  GroupKey, UserGroupMappingData (As ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve users fo values from
	 * user_group_mapping table for given Group key
	 */

	public int getGroupUsers( GroupKey key, List<UserGroupMappingData> dataList )
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

			while( rs.next( ) )
			{
				UserProfileKey userProfileKey = new UserProfileKey( );
				userProfileKey.email_	= rs.getString( "user_rel_key" );
				
				GroupKey groupKey	= convertGroupKey( rs.getString( "group_rel_key" ) );
				
				UserGroupMappingData data = new UserGroupMappingData( );
				
				data.userRelKey_ = userProfileKey;
				data.groupRelKey_ = groupKey;
				
				dataList.add( data );
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "UserGroupMapTable : getUserGroupMap :: SQL Error "
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
	 * Method : getUserGroupMap( )
	 * 
	 * Input :  GroupKey, UserGroupMappingData (As ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve users fo values from
	 * user_group_mapping table for given Group key
	 */

	public int getGroups( UserProfileKey key, List<UserGroupMappingData> dataList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		query = "SELECT * FROM " + tableName_ + " WHERE user_rel_key = '" + key.email_
		        + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{	
				GroupKey groupKey	= convertGroupKey( rs.getString( "group_rel_key" ) );
				
				UserGroupMappingData data = new UserGroupMappingData( );
				
				data.userRelKey_ = key;
				data.groupRelKey_ = groupKey;
				
				dataList.add( data );
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "UserGroupMapTable : getUserGroupMap :: SQL Error "
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
	 * Input : UserGroupMappingData key
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the GroupData values from
	 * user_group_mapping table
	 */

	public int delete( UserGroupMappingData userGroupMap )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		String groupKeyString = convertGroupKeyStr( userGroupMap.groupRelKey_ );
		
		
		String userEmail = userGroupMap.userRelKey_.email_;
		
		query = "DELETE FROM " + tableName_ + " WHERE group_rel_key = '" + groupKeyString + "'"
				+" AND user_rel_key='"+userEmail+"'";

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
			String errorMessage = "UserGroupMapTable : delete :: SQL Error "
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
	 * Method : delete( )
	 * 
	 * Input : GroupKey key
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the GroupData values from
	 * user_group_mapping table
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
			String errorMessage = "UserGroupMapTable : delete :: SQL Error "
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
	 * Method : createUserGroupMapRecord( )
	 * 
	 * Input : UserGroupMappingData obj
	 * 
	 * Return : UserGroupMapRecord
	 * 
	 * Purpose: It converts GroupData to UserGroupMapRecord
	 */

	private UserGroupMapRecord createUserGroupMapRecord(
	        UserGroupMappingData data )
	{
		UserGroupMapRecord record = new UserGroupMapRecord( );

		String groupKeyString	= convertGroupKeyStr( data.groupRelKey_ );
		
		// set the values
		record.groupRelKey_ 		= groupKeyString;
		record.userRelKey_			= data.userRelKey_.email_;
		
		return record;
	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : UserGroupMapRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using UserGroupMapRecord and
	 * returns as string
	 */

	private String formInsertQuery( UserGroupMapRecord record )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(user_rel_key, group_rel_key)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.userRelKey_ + "', ";
		insertQuery = insertQuery + "'" + record.groupRelKey_ + "'";
		//insertQuery = insertQuery + "'" + record.userGroupMappingKey_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
	
	/*
	 * Method : convertGroupKey( ) Input : String of deptrelkey which is stored
	 * in db Return : GroupKey object
	 * 
	 * Purpose: This method is used to convert the string value to GroupKey
	 * object
	 */

	private GroupKey convertGroupKey( String groupRelKey )
	{	
		GroupKey groupKey = new GroupKey( );

		String [ ] strArr = groupRelKey.split( ":" );

		if( strArr.length > 1 )
		{
			CompanyRegnKey key = new CompanyRegnKey( );
			key.companyPhoneNo_ = strArr[0];
			
			groupKey.companyRegnKey_ = key;
			groupKey.groupName_ = strArr[1];
		}

		return groupKey;
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
	 * user_group_mapping table
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
	
	/*
	 * Method : isExists( )
	 * 
	 * Input :  UseProfileKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to find whether the given user key is related
	 * to any one of the user groups
	 */

	public int isExists( UserProfileKey key )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		query = "SELECT 1 FROM " + tableName_ + " WHERE user_rel_key = '" + key.email_
		        + "'";

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

			return -1; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::UserGroupMapTable.isExists() - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // SqlException
		}
		
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::UserGroupMapTable.isExists() - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -3; // Exception
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
