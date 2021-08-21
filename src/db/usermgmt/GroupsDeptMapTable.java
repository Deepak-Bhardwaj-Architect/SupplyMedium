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
import core.dept.DeptKey;
import core.regn.CompanyRegnKey;
import core.usermgmt.GroupDeptMappingData;
import core.usermgmt.GroupKey;
import db.utils.DBConnect;

/**
 * File:  GroupsDeptMapTable.java 
 *
 * Created on Mar 4, 2013 11:41:02 AM
 */
public class GroupsDeptMapTable
{
	private String tableName_;

	/*
	 * Method : GroupsDeptMapTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public GroupsDeptMapTable()
	{
		this.tableName_ = "group_dept_mapping";
	}

	/*
	 * Method : insert( )
	 * 
	 * Input : GroupDeptMappingData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the group data values into
	 * group_dept_mapping table
	 */

	public int insert( GroupDeptMappingData data )
	{
		GroupsDeptMapRecord record = createGroupDeptMapRecord( data );

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
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger.logMsg( "Exception :: GroupsDeptMapTable : insert - "
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
	 * Method : isExist( ) 
	 * 
	 * Input  : GroupDeptMappingData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check the Group - Department relation already
	 * exist or not. If already exist return 1 otherwise return 0
	 */

	public int isExist( GroupDeptMappingData groupDeptMappingData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		// Convert groupDeptMappingData object to GroupsDeptMapRecord object
		GroupsDeptMapRecord record = createGroupDeptMapRecord( groupDeptMappingData );

		query = "SELECT * FROM " + tableName_ + " WHERE dept_rel_key = '" + record.deptRelKey_ + "'";
		query=query+" AND group_rel_key = '"+record.groupRelKey_+"'";


		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				return 1;  // Record already exist
			}

			return 0;  // Record not exist
			
		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception :: DeptFolderMappingTable : isExist - " + ex;

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
	 * Method : update( )
	 * 
	 * Input : GroupDeptMappingData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the GroupData values into
	 * group_dept_mapping table
	 */

	public int update( GroupDeptMappingData data )
	{
		GroupsDeptMapRecord record = createGroupDeptMapRecord( data );

		String query = "UPDATE " + tableName_;
		query = query + " SET dept_rel_key = " + record.deptRelKey_;
		query = query + " WHERE group_rel_key ='" + record.groupRelKey_ + "'";

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
			ErrorLogger errLogger= ErrorLogger.instance( );
			
			errLogger.logMsg( "Exception :: GroupsDeptMapTable : update - " + ex );

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
	 * Method : getGroupsKey( )
	 * 
	 * Input :  DeptKey, Array of GroupDeptMappingData (As ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve list of GroupData values from
	 * groups_dept_mapping table for given dept key
	 */

	public int getGroupsKey( DeptKey key, 
									List<GroupDeptMappingData> groupDeptMappingDataArr )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		String groupKeyString = convertDeptKeyStr( key );
		
		query = "SELECT * FROM " + tableName_ + " WHERE dept_rel_key = '" + groupKeyString
		        + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				
				GroupKey groupKey = convertGroupKey( rs.getString( "group_rel_key" ) );
				
				DeptKey deptKey	= convertDeptKey( rs.getString( "dept_rel_key" ) );
				
				GroupDeptMappingData groupDeptMappingData = new GroupDeptMappingData( );
				
				groupDeptMappingData.groupRelKey_	= groupKey;
				groupDeptMappingData.deptRelKey_	= deptKey;
				
				groupDeptMappingDataArr.add( groupDeptMappingData );
				
				groupDeptMappingData = null;
			}
			

			return 0; // Success
		}

		catch( SQLException ex )
		{
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception :: GroupsDeptMapTable : getGroupsDeptMap - "
			        + ex;

			errLogger.logMsg( errorMessage );

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
	 * Method : get( )
	 * 
	 * Input :  GroupKey, Array of GroupDeptMappingData (As ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve list of GroupData values from
	 * groups_dept_mapping table for given group key
	 */

	public int get( GroupKey key, List<GroupDeptMappingData> groupDeptMappingDataArr )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		String groupKeyString = convertGroupKeyStr( key );
		
		query = "SELECT * FROM " + tableName_ + " WHERE group_rel_key = '" + groupKeyString
		        + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				
				GroupKey groupKey = convertGroupKey( rs.getString( "group_rel_key" ) );
				
				DeptKey deptKey	= convertDeptKey( rs.getString( "dept_rel_key" ) );
				
				GroupDeptMappingData groupDeptMappingData = new GroupDeptMappingData( );
				
				groupDeptMappingData.groupRelKey_	= groupKey;
				groupDeptMappingData.deptRelKey_	= deptKey;
				
				groupDeptMappingDataArr.add( groupDeptMappingData );
				
				groupDeptMappingData = null;
			}
			

			return 0; // Success
		}

		catch( SQLException ex )
		{
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception::GroupsDeptMapTable:get - "
			        + ex;

			errLogger.logMsg( errorMessage );

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
	 * Input : GroupDeptMappingData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the GroupDeptMappingData values from
	 * groups_dept_mapping table
	 */

	public int delete( GroupDeptMappingData groupDeptMappingKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		String groupKeyString = convertGroupKeyStr( groupDeptMappingKey.groupRelKey_ );
		
		String deptKeyString  = convertDeptKeyStr( groupDeptMappingKey.deptRelKey_ );
		
		query = "DELETE FROM " + tableName_ + " WHERE group_rel_key = '" + groupKeyString + "'"
				+" AND dept_rel_key='"+deptKeyString+"'";

		System.out.println( "Query=" + query );
		

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
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errorMessage = "Exception :: GroupsDeptMapTable : delete - "
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
	 * Method : delete( )
	 * 
	 * Input : deptKey obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the GroupDeptMappingData values from
	 * groups_dept_mapping table
	 */

	public int delete( DeptKey deptKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		String deptKeyString  = convertDeptKeyStr( deptKey );
		
		query = "DELETE FROM " + tableName_ + " WHERE dept_rel_key='"+deptKeyString+"'";

		System.out.println( "Query=" + query );

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
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errorMessage = "Exception :: GroupsDeptMapTable : delete - "
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
	 * Method : createGroupDeptMapRecord( )
	 * 
	 * Input : GroupDeptMappingData obj
	 * 
	 * Return : GroupsDeptMapRecord
	 * 
	 * Purpose: It converts GroupData to GroupsDeptMapRecord
	 */

	private GroupsDeptMapRecord createGroupDeptMapRecord(
	        GroupDeptMappingData data )
	{
		GroupsDeptMapRecord record = new GroupsDeptMapRecord( );

		String groupKeyString = convertGroupKeyStr( data.groupRelKey_ );
		String deptKeyString = convertDeptKeyStr( data.deptRelKey_ );
		
		// set the values
		record.groupRelKey_ = groupKeyString;
		record.deptRelKey_	= deptKeyString;
		
		return record;
	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : GroupsDeptMapRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using GroupsDeptMapRecord and
	 * returns as string
	 */

	private String formInsertQuery( GroupsDeptMapRecord record )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(group_rel_key, dept_rel_key)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.groupRelKey_ + "', ";
		insertQuery = insertQuery + "'" + record.deptRelKey_ + "'";

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
			CompanyRegnKey companyRegnKey = new CompanyRegnKey( );
			companyRegnKey.companyPhoneNo_ = strArr[0];
			groupKey.companyRegnKey_ = companyRegnKey;
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
	 * Method : convertDeptKey( ) Input : String of deptrelkey which is stored
	 * in db Return : DeptKey object
	 * 
	 * Purpose: This method is used to convert the string value to DeptKey
	 * object
	 */

	private DeptKey convertDeptKey( String deptRelKey )
	{
		DeptKey deptKey = new DeptKey( );

		String [ ] strArr = deptRelKey.split( ":" );

		if( strArr.length > 1 )
		{
			CompanyRegnKey companyRegnKey = new CompanyRegnKey( );
			companyRegnKey.companyPhoneNo_ =  strArr[0];
			deptKey.companyRegnKey_ = companyRegnKey;
			deptKey.deptName_ = strArr[1];
		}

		return deptKey;
	}
	
	/*
	 * Method : convertDeptKeyStr( ) Input : DeptKey object Return : String of
	 * DeptKey which is stored in db
	 * 
	 * Purpose: This method is used to convert DeptKey object to DeptKey string
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
	 * Input : GroupKey updatedKey, GroupKey oldKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the groupkey into
	 * group_dept_mapping table
	 */

	public int updateKey( GroupKey updatedKey, GroupKey oldKey )
	{
		String updatedKeyStr = convertGroupKeyStr( updatedKey );
		String oldKeyStr = convertGroupKeyStr( oldKey );
				
		String query = "UPDATE " + tableName_;
		query = query + " SET group_rel_key = " + updatedKeyStr;
		query = query + " WHERE group_rel_key ='" + oldKeyStr + "'";

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
	 * Method : updateKey( )
	 * 
	 * Input : DeptKey updatedKey, DeptKey oldKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the DeptKey into
	 * departments table
	 */

	public int updateKey( DeptKey updatedKey, DeptKey oldKey )
	{
		String updatedKeyStr = convertDeptKeyStr( updatedKey );
		String oldKeyStr = convertDeptKeyStr( oldKey );
				
		String query = "UPDATE " + tableName_;
		query = query + " SET dept_rel_key = '" + updatedKeyStr + "'";
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
			errLogger_.logMsg( "Exception::GroupsDeptMapTable:updateKey " + ex );

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
