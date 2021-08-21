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

import java.security.acl.Group;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import utils.ErrorLogger;
import core.dept.DeptData;
import core.regn.CompanyRegnKey;
import core.usermgmt.GroupData;
import core.usermgmt.GroupKey;
import db.dept.DeptRecord;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  GroupsTable.java 
 *
 * Created on Mar 4, 2013 3:00:23 PM
 */
public class GroupsTable
{
    private static ErrorMaster errorMaster_ = null;


	private String tableName_;

	/*
	 * Method : GroupsTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public GroupsTable()
	{
             ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "groups";
	}

	/*
	 * Method : insert( )
	 * 
	 * Input : GroupData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the group data values into
	 * groups table
	 */

	public int insert( GroupData groupData )
	{
		GroupsRecord record = createGroupsRecord( groupData );

		String query = formInsertQuery( record );

		//errorMaster_.insert( "Insert groups query..." + query );
		
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
			errLogger.logMsg( "Exception :: GroupsTable : insert - " + ex );

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
	 * Input : GroupData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the GroupData values into
	 * groups table
	 */

	public int update( GroupData groupData )
	{
		GroupsRecord record = createGroupsRecord( groupData );

		String query = "UPDATE " + tableName_;
		query = query + " SET group_name = '" + record.groupName_ +"' ";
		query = query + " WHERE group_key ='" + record.groupRelKey_ + "'";

		// query = query + " Commit ";

		//errorMaster_.insert( "Query = " + query );

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
			
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger.logMsg( "Exception :: GroupsTable : update - " + ex );

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
	 * Method : getGroup( )
	 * 
	 * Input :  GroupKey, GroupData (As ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve GroupData values from
	 * groups table for given Group key
	 */

	public int getGroup( GroupKey key, GroupData data )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		String groupKeyString = convertGroupKeyStr( key );

		query = "SELECT * FROM " + tableName_ + " WHERE group_key = '" + groupKeyString
		        + "'";

		//errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				GroupKey groupKey = convertGroupKey( rs.getString( "group_key" ) );
				
				data.groupKey_		= groupKey;
				data.groupName_ 	= rs.getString( "group_name" );
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception :: GroupsTable : getGroups - "
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
	 * Method : getAllGroups( )
	 * 
	 * Input : RegnKey, GroupData (As ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve GroupData values from
	 * groups table 
	 */

	public int getAllGroups( CompanyRegnKey key, List<GroupData> dataList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		

		query = "SELECT * FROM " + tableName_ +" WHERE regn_rel_key = '"+key.companyPhoneNo_+"'";

                System.out.println("group query==============================================>"+query); 
		//errorMaster_.insert( "Query=" + query );

		try
		{       System.out.println("check1==============================================>");
			con = DBConnect.instance( ).getConnection( );
                        System.out.println("check2==============================================>");
			stmt = con.createStatement( );
			System.out.println("check3==============================================>");
			ResultSet rs = stmt.executeQuery( query );
                        System.out.println("check4==============================================>");
			while( rs.next( ) )
			{
                               System.out.println("check5==============================================>");
				GroupKey groupKey = convertGroupKey( rs.getString( "group_key" ) );
				System.out.println("check8==============================================>");
				GroupData data = new GroupData( );
				System.out.println("check9==============================================>");
				data.groupKey_		= groupKey;
				System.out.println("check77==============================================>");
                                data.groupName_ 	= rs.getString( "group_name" );
				System.out.println("check6==============================================>");
				dataList.add( data );
			}
                        System.out.println("dataList==============================================>"+dataList); 
			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "GroupsTable : getAllGroups :: SQL Error "
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
	 * groups table
	 */

	public int delete( GroupKey key )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		String groupKeyString = convertGroupKeyStr( key );
		
		query = "DELETE FROM " + tableName_ + " WHERE group_key = '" + groupKeyString + "'";

		//errorMaster_.insert( "Query=" + query );

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
			String errorMessage = "Exception :: GroupsTable : delete - "
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
	 * Method : isExist( ) 
	 * 
	 * Input  : GroupData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check the group already exist 
	 * not. If already exist return 1 otherwise return 0
	 */

	public int isExist( GroupData groupData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		// Convert userDeptMappingData object to UserDeptMappingRecord object
		GroupsRecord record = createGroupsRecord( groupData );

		// Convert compnayregn key object to string
		String key = convertRegnKeyStr( groupData.groupKey_.companyRegnKey_ );

		query = "SELECT * FROM " + tableName_ + " WHERE group_name = '" + record.groupName_+"' AND " +
						"regn_rel_key='"+key +"'";
				
		
		//errorMaster_.insert( "query="+query );

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
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception :: GroupTable : isExist - " + ex;

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
	 * Method : createGroupsRecord( )
	 * 
	 * Input : GroupData obj
	 * 
	 * Return : GroupsRecord
	 * 
	 * Purpose: It converts GroupData to GroupsRecord
	 */

	private GroupsRecord createGroupsRecord(
	        GroupData groupData )
	{
		GroupsRecord record = new GroupsRecord( );

		String groupKeyString = convertGroupKeyStr( groupData.groupKey_ );
		
		// set the values
		record.groupName_ 		= groupData.groupName_;
		record.groupRelKey_		= groupKeyString;
		record.companyRegnKey_ 	= groupData.groupKey_.companyRegnKey_.companyPhoneNo_; 
		
		return record;
	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : GroupsRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using GroupsRecord and
	 * returns as string
	 */

	private String formInsertQuery( GroupsRecord record )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(group_key, group_name, regn_rel_key)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.groupRelKey_ + "', ";
		insertQuery = insertQuery + "'" + record.groupName_ + "', ";
		insertQuery = insertQuery + "'" + record.companyRegnKey_ + "'";

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
//		errorMaster_.insert( "group str="+groupRelKey );
		
		GroupKey groupKey = new GroupKey( );

		String [ ] strArr = groupRelKey.split( ":" );

		if( strArr.length > 1 )
		{
			CompanyRegnKey companyRegnKey = new CompanyRegnKey( );
			companyRegnKey.companyPhoneNo_ = strArr[0];
			groupKey.companyRegnKey_=companyRegnKey;
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
	 * Method : convertRegnKeyStr( ) 
	 * 
	 * Input : RegnKey object 
	 * 
	 * Return : String of regnkey which is stored in db
	 * 
	 * Purpose: This method is used to convert regnkey object to regnkey string
	 */

	private String convertRegnKeyStr( CompanyRegnKey regnKey )
	{
		String regnKeyStr = regnKey.companyPhoneNo_;
		return regnKeyStr;
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
		query = query + " SET group_key = '" + updatedKeyStr + "'";
		query = query + " WHERE group_key ='" + oldKeyStr + "'";

		// query = query + " Commit ";

		//errorMaster_.insert( "Query = " + query );

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
			//errorMaster_.insert( "Exception=" + ex.getMessage( ) );
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
