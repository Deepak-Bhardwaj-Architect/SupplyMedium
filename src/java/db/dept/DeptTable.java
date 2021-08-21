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
import core.dept.DeptData;
import core.dept.DeptKey;
import core.regn.CompanyRegnKey;
import core.usermgmt.GroupKey;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  DeptFoldersTable.java 
 *
 * Created on Mar 5, 2013 3:28:59 PM
 */

/*
 * Class  : DeptTable
 * 
 * Purpose: It is used to query the departments table.
 * 
 */

public class DeptTable
{
        private static ErrorMaster errorMaster_ = null;


	private String tableName_;

	/*
	 * Method : DeptTable( ) - constructor
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public DeptTable()
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "departments";
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : DeptData obj
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to insert the department data values into
	 * departments table
	 */

	public int insert( DeptData deptData )
	{
		// Convert DeptData object to DeptRecord object
		DeptRecord record = createDepartmentRecord( deptData );

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

		catch( SQLException ex )
		{
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger.logMsg( "Exception :: DeptTable : insert - " + ex );

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
	 * Input : DeptData obj
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to udpate the DeptData values into
	 * departments table
	 */

	public int update( DeptData deptData )
	{
		// Convert DeptData object to DeptRecord object
		DeptRecord record = createDepartmentRecord( deptData );

		// Form the update query 
		
		String query = "UPDATE " + tableName_;
		query = query + " SET dept_name = '" + record.deptName_+"'";
		query = query + " WHERE dept_key ='" + record.deptKey_ + "'";

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

			if( insertResult > 0 ) 
				return 0;  // Successfully updated

			return -1;  // Update failed

		}

		catch( SQLException ex )
		{
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger.logMsg( "Exception :: DeptTable : update - " + ex );

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
	 * Method : getDepartment( ) 
	 * 
	 * Input : deptkey and deptData object 
	 * 
	 * Return :int
	 * 
	 * Purpose: It is used to get the department details for given deptkey. And
	 * assign to deptData parameter so that caller class can access this values.
	 */

	public int getDepartment( DeptKey deptKey, DeptData deptData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		// Convert DeptKey object to string
		String key = convertDeptKeyStr( deptKey );

		query = "SELECT * FROM " + tableName_ + " WHERE dept_key = '" + key + "'";

		deptData.deptKey_ = deptKey;

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				deptData.deptName_ = rs.getString( "dept_name" );
			}

			return 0;
		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception :: DeptTable : getDepartment - " + ex;

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
	 * Method : getAllDepartments( ) 
	 * 
	 * Input : RegnKey , list of DeptData objects
	 * 
	 * Return :int
	 * 
	 * Purpose: It is used to get all the department details for the given
	 * Company registration key.
	 */

	public int getAllDepartments( CompanyRegnKey regnKey, List<DeptData> deptDataArr )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		// Convert DeptKey object to string
		String key = convertRegnKeyStr( regnKey );

		query = "SELECT * FROM " + tableName_ + " WHERE regn_rel_key = '" + key + "'";
		
		errorMaster_.insert( "Query="+query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				DeptData deptData = new DeptData( );
				
				deptData.deptKey_ = convertDeptKey( rs.getString("dept_key") );
				
				deptData.deptName_ = rs.getString( "dept_name" );
				
				deptDataArr.add( deptData );
				
				deptData = null;
				
			}

			return 0;
		}
		catch( SQLException ex )
		{
			errorMaster_.insert( "Exceprion:"+ex );
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception :: DeptTable : getAllDepartments -  " + ex;

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
	 * Method : isExist( ) 
	 * 
	 * Input  : DeptData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check the department already exist 
	 * not. If already exist return 1 otherwise return 0
	 */

	public int isExist( DeptData deptData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		// Convert userDeptMappingData object to UserDeptMappingRecord object
		DeptRecord record = createDepartmentRecord( deptData );
		
		// Convert company regn key object to string
		String key = convertRegnKeyStr( deptData.deptKey_.companyRegnKey_ );
		
		errorMaster_.insert( "deptname="+deptData.deptName_+"deptKey="+key );

		query = "SELECT * FROM " + tableName_ + " WHERE dept_name = '" + record.deptName_+"' AND " +
				"regn_rel_key='"+key +"'";
		
		errorMaster_.insert( "query="+query );
		
		
		
		errorMaster_.insert( "Dept key="+key );

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

			String errorMessage = "Exception :: DeptTable : isExist - " + ex;

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
	 * Input : DeptKey object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the deptData values from
	 * departments table
	 */

	public int delete( DeptKey key )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		// Convert DeptKey object to string
		String deptKeyStr = convertDeptKeyStr( key );

		query = "DELETE FROM " + tableName_ + " WHERE dept_key = '" + deptKeyStr + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) 
				return 0;  // successfully deleted

			return -1; // Delete failed

		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errorMessage = "Exception :: DeptTable : delete - "
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
	 * Method : createDepartmentRecord( )
	 * 
	 * Input : DeptData obj
	 * 
	 * Return : DeptRecord obj
	 * 
	 * Purpose: It converts DeptData object to DeptRecord object
	 */

	private DeptRecord createDepartmentRecord(
	        DeptData deptData )
	{
		DeptRecord record = new DeptRecord( );

		// set the values
		record.deptKey_ 	= convertDeptKeyStr( deptData.deptKey_ );
		record.deptName_	= deptData.deptName_;
		record.regnKey_		= deptData.deptKey_.companyRegnKey_.companyPhoneNo_;
		
		return record;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : DeptRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using DeptRecord and
	 * returns as string
	 */

	private String formInsertQuery( DeptRecord record )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(dept_key,regn_rel_key, dept_name)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.deptKey_ + "', ";
		insertQuery = insertQuery + "'" + record.regnKey_ + "', ";
		insertQuery = insertQuery + "'" + record.deptName_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}

	/*
	 * Method : convertDeptKeyStr( ) 
	 * 
	 * Input : DeptKey object 
	 * 
	 * Return : String of deptkey which is stored in db
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
	 * Method : convertDeptKey( ) 
	 * 
	 * Input : String of deptrelkey which is stored in db 
	 * 
	 * Return : DeptKey object
	 * 
	 * Purpose: This method is used to convert the string value to DeptKey
	 * object
	 */

	private DeptKey convertDeptKey( String deptKeyStr )
	{
		DeptKey deptKey = new DeptKey( );

		String [ ] strArr = deptKeyStr.split( ":" );

		if( strArr.length > 1 )
		{
			CompanyRegnKey companyRegnKey = new CompanyRegnKey( );
			companyRegnKey.companyPhoneNo_=strArr[0];
			deptKey.companyRegnKey_=companyRegnKey;
			deptKey.deptName_ = strArr[1];
		}

		return deptKey;
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
		query = query + " SET dept_key = '" + updatedKeyStr + "'";
		query = query + " WHERE dept_key ='" + oldKeyStr + "'";

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
			errLogger_.logMsg( "Exception::DetpTable:updateKey " + ex );

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
