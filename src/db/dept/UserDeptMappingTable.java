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

package db.dept;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import utils.ErrorLogger;
import core.dept.DeptKey;
import core.dept.UserDeptMappingData;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import db.utils.DBConnect;

/**
 * File:  UserDeptMappingTable.java 
 *
 * Created on Mar 5, 2013 3:28:59 PM
 */

/*
 * Class  : UserPrivilegesTable
 * 
 * Purpose: It is used to query the user_dept_mapping table.
 * 
 */

public class UserDeptMappingTable
{
	private String tableName_;

	/*
	 * Method : UserDeptMappingTable( ) - constructor
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public UserDeptMappingTable()
	{
		this.tableName_ = "user_dept_mapping";
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input :  UserDeptMappingData object
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to insert the UserDeptMappingData data values into
	 * user_dept_mapping table
	 */

	public int insert( UserDeptMappingData userDeptMappingData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		// Convert userDeptMappingData object to UserDeptMappingRecord object
		UserDeptMappingRecord record = createUserDeptMappingRecord( userDeptMappingData );

		// Form the insert query
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

			return -1; // Insert failed

		}

		catch( SQLException ex ) // SQL Exception
		{
			errLogger.logMsg( "Exception::UserDeptMappingTable:insert-" + ex );

			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::UserDeptMappingTable:insert-" + ex );

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
	 * Input  : UserDeptMappingData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check the user - department relation already
	 * exist or not. If already exist return 1 otherwise return 0
	 */

	public int isExist( UserDeptMappingData userDeptMappingData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errorMessage = "";
		
		Statement stmt = null;
		Connection con = null;
		String query = null;

		// Convert userDeptMappingData object to UserDeptMappingRecord object
		UserDeptMappingRecord record = createUserDeptMappingRecord( userDeptMappingData );

		query = "SELECT * FROM " + tableName_ + " WHERE dept_rel_key = '" + record.deptKey_ + "'";
		query=query+" AND user_rel_key = '"+record.userKey_+"'";

		System.out.println( "query="+query );

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
		catch( SQLException ex ) // SqlException
		{
			errorMessage = "Exception::UserDeptMappingTable isExist-" + ex;

			errLogger.logMsg( errorMessage );

			return -1; 
		}
		catch( Exception ex ) // General Exception
		{
			errorMessage = "Exception::UserDeptMappingTable:isExist-" + ex;

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
	 * Method : getUsersKey( )
	 * 
	 * Input :  DeptKey, Array of UserDeptMappingData (As ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve list of UserDeptMappingData values from
	 * user_dept_mapping table for given dept key
	 */

	public int getUsersKey( DeptKey key, 
									List<UserDeptMappingData> userDeptMappingDataArr )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errorMessage = "";
		
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		String deptKeyString = convertDeptKeyStr( key );
		
		query = "SELECT * FROM " + tableName_ + " WHERE dept_rel_key = '" + deptKeyString
		        + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				UserDeptMappingData userDeptMappingdata = new UserDeptMappingData( );
				
				UserProfileKey userKey = convertUserKey( rs.getString( "user_rel_key" ) );
				
				DeptKey deptKey	= convertDeptKey( rs.getString( "dept_rel_key" ) );
				
				userDeptMappingdata.userKey_ = userKey;
				
				userDeptMappingdata.deptKey_ = deptKey;
			
				
				userDeptMappingDataArr.add( userDeptMappingdata );
				
				userDeptMappingdata = null;
			}
			
			return 0; // Success
		}
 
		catch( SQLException ex ) // SqlException
		{
			errorMessage = "Exception::UserDeptMappingTable:getUsersKey - "
			        + ex;

			errLogger.logMsg( errorMessage );

			return -1; 
		}
		catch( Exception ex ) // General Exception
		{
			errorMessage = "Exception::UserDeptMappingTable:getUsersKey - "
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
	 * Method : getDepartmentKeys( ) 
	 * 
	 * Input : UserProfileKey object and list of deptkey object 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the all the departments keys for given user
	 * profile key. Set the deptKeyArr parameter.So caller class can used this
	 * values.
	 */

	public int getDepartmentKeys( UserProfileKey userProfileKey, List<DeptKey> deptKeyArr )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errorMessage = "";

		String key = userProfileKey.email_;

		query = "SELECT dept_rel_key FROM " + tableName_ + " WHERE user_rel_key = '" + key
		        + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				String deptRelKey = rs.getString( "dept_rel_key" );

				DeptKey deptKey = convertDeptKey( deptRelKey );

				deptKeyArr.add( deptKey );
			}

			return 0;
		}
		catch( SQLException ex )  // SqlException
		{
			errorMessage= "Exception::UserDeptMappingTable:getDepartmentKeys-"
			        + ex;

			errLogger.logMsg( errorMessage );

			return -1; 
		}
		catch( Exception ex )  // General Exception
		{
			errorMessage= "Exception::UserDeptMappingTable:getDepartmentKeys-"
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
	 * Input : UserDeptMappingData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the UserDeptMappingData values from
	 * user_dept_mapping table
	 */

	public int delete( UserDeptMappingData userDeptMappingData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errorMessage = "";
		
		// Convert UserDeptMappingRecord object to UserDeptMappingData object
		UserDeptMappingRecord record = createUserDeptMappingRecord( userDeptMappingData );

		query = "DELETE FROM " + tableName_ + " WHERE dept_rel_key = '";
		query =query + record.deptKey_ + "'";
		query = query+ " AND user_rel_key='"+record.userKey_+"'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) 
				return 0;  // successfully deleted

			return -1; // Delete failed

		}
		catch( SQLException ex )  // SqlException
		{
			errorMessage= "Exception::UserDeptMappingTable:delete-"
			        + ex;

			errLogger.logMsg( errorMessage );

			return -2; 
		}
		catch( Exception ex )  // General Exception
		{
			errorMessage= "Exception::UserDeptMappingTable:delete-"
			        + ex;

			errLogger.logMsg( errorMessage );

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
	 * Method : delete( )
	 * 
	 * Input : UserDeptMappingData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the UserDeptMappingData values from
	 * user_dept_mapping table
	 */

	public int delete( DeptKey deptKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errorMessage = "";
		
		String deptKeyStr = convertDeptKeyStr( deptKey );
		
		query = "DELETE FROM " + tableName_ + " WHERE dept_rel_key = '";
		query =query + deptKeyStr + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) 
				return 0;  // successfully deleted

			return -1; // Delete failed

		}
		catch( SQLException ex )  // SqlException
		{
			errorMessage= "Exception::UserDeptMappingTable:delete-"
			        + ex;

			errLogger.logMsg( errorMessage );

			return -1; 
		}
		catch( Exception ex )  // General Exception
		{
			errorMessage= "Exception::UserDeptMappingTable:delete-"
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
	 * Method : createUserDeptMappingRecord( )
	 * 
	 * Input : UserDeptMappingData obj
	 * 
	 * Return : UserDeptMappingRecord obj
	 * 
	 * Purpose: It converts UserDeptMappingData object to 
	 * UserDeptMappingRecord object
	 */

	private UserDeptMappingRecord createUserDeptMappingRecord(
			UserDeptMappingData userDeptMappingData )
	{
		UserDeptMappingRecord record = new UserDeptMappingRecord( );

		// set the values
		record.deptKey_ 		= convertDeptKeyStr( userDeptMappingData.deptKey_ );
		record.userKey_			= userDeptMappingData.userKey_.email_;
		
		return record;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : UserDeptMappingRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using UserDeptMappingRecord and
	 * returns as string
	 */

	private String formInsertQuery( UserDeptMappingRecord record )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(user_rel_key, dept_rel_key)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.userKey_ + "', ";
		insertQuery = insertQuery + "'" + record.deptKey_ + "'";

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
			companyRegnKey.companyPhoneNo_ = strArr[ 0 ];
			deptKey.companyRegnKey_ = companyRegnKey;
			deptKey.deptName_ = strArr[1];
		}

		return deptKey;
	}
	
	/*
	 * Method : convertUserKey( ) 
	 * 
	 * Input : String of userkey which is storedin db 
	 * 
	 * Return : UserProfileKey object
	 * 
	 * Purpose: This method is used to convert the string value to UserProfileKey
	 * object
	 */

	private UserProfileKey convertUserKey( String userKeyStr )
	{	
		UserProfileKey userProfileKey = new UserProfileKey( );

		userProfileKey.email_ = userKeyStr;

		return userProfileKey;
	}
	

	/*
	 * Method : updateKey( )
	 * 
	 * Input : DeptKey updatedKey, DeptKey oldKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the DeptKey into
	 * user_detp_mapping table
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
			errLogger_.logMsg( "Exception::UserDeptMappingTable:updateKey " + ex );

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
