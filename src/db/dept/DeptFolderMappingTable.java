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
import core.dept.DeptFolderKey;
import core.dept.DeptFolderMappingData;
import core.dept.DeptKey;
import core.regn.CompanyRegnKey;
import db.utils.DBConnect;

/**
 * File:  DeptFolderMappingTable.java 
 *
 * Created on Mar 5, 2013 3:30:06 PM
 */

/*
 * Class  : DeptFolderMappingTable
 * 
 * Purpose: It is used to query the dept_folder_mapping table
 * 
 */

public class DeptFolderMappingTable
{
	private String tableName_;
	

	/*
	 * Method : DeptTable( ) - constructor
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public DeptFolderMappingTable()
	{
		this.tableName_ = "dept_folder_mapping";
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : DeptFolderMappingData obj
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to insert the DeptFolderMappingData data values into
	 * dept_folder_mapping table
	 */

	public int insert( DeptFolderMappingData deptFolderMappingData )
	{
		// Convert DeptFolderMappingData object to DeptRecord object
		DeptFolderMappingRecord record = createDeptFolderMappingRecord( deptFolderMappingData );

		// Form the insert query
		String query = formInsertQuery( record );
		
		System.out.println( "Sql="+query );

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
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger.logMsg( "Exception :: DeptFolderMappingTable : insert - " + ex );

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
	 * Input  : DeptFolderMappingData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check the Department - folder relation already
	 * exist or not. If already exist return 1 otherwise return 0
	 */

	public int isExist( DeptFolderMappingData deptFolderMappingData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		// Convert DeptFolderMappingData object to DeptFolderMappingRecord object
		DeptFolderMappingRecord record = createDeptFolderMappingRecord( deptFolderMappingData );

		query = "SELECT * FROM " + tableName_ + " WHERE dept_rel_key = '" + record.deptKey_ + "'";
		query=query+" AND dept_folder_rel_key = '"+record.deptFolderKey_+"'";


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

			String errorMessage = "Exception :: DeptFolderMappingTable : isExist - " + ex;

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
	 * Method : getFoldersKey( )
	 * 
	 * Input :  DeptKey, Array of GroupDeptMappingData (As ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve list of DeptFolderMappingData values from
	 * groups_dept_mapping table for given dept key
	 */

	public int getFoldersKey( DeptKey key, 
									List<DeptFolderMappingData> deptFolderMappingDataArr )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		
		
		String deptKeyString = convertDeptKeyStr( key );
		
		query = "SELECT * FROM " + tableName_ + " WHERE dept_rel_key = '" + deptKeyString
		        + "' AND recycle_flag=0";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				DeptFolderMappingData deptFolderMappingData = new DeptFolderMappingData( );
				
				DeptFolderKey folKey = convertFolderKey( rs.getString( "dept_folder_rel_key" ) );
				DeptKey deptKey	= convertDeptKey( rs.getString( "dept_rel_key" ) );
				
				deptFolderMappingData.deptFolderKey_	= folKey;
				deptFolderMappingData.deptKey_	= deptKey;
				
				deptFolderMappingDataArr.add( deptFolderMappingData );
				
				deptFolderMappingData = null;
			}
			
			

			return 0; // Success
		}

		catch( SQLException ex )
		{
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception :: DeptFolderMappingTable : getFoldersKey - "
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
	 * Method : getFolders( )
	 * 
	 * Input :  DeptKey, Array of GroupDeptMappingData (As ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve list of DeptFolderMappingData values from
	 * groups_dept_mapping table for given dept key ( contains records with recycle_flag = 0 or 1 )
	 */

	public int getFolders( DeptKey key, 
									List<DeptFolderMappingData> deptFolderMappingDataArr )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		//String deptKeyString = convertDeptKeyStr( key );
		
		query = "SELECT * FROM " + tableName_ + " WHERE dept_rel_key = '" + key.toString( )
		        + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				DeptFolderMappingData deptFolderMappingData = new DeptFolderMappingData( );
				
				DeptFolderKey folKey = convertFolderKey( rs.getString( "dept_folder_rel_key" ) );
				DeptKey deptKey	= convertDeptKey( rs.getString( "dept_rel_key" ) );
				
				deptFolderMappingData.deptFolderKey_	= folKey;
				deptFolderMappingData.deptKey_	= deptKey;
				deptFolderMappingData.recyleFlag_ = rs.getInt( "recycle_flag" );
				
				deptFolderMappingDataArr.add( deptFolderMappingData );
				
				deptFolderMappingData = null;
			}
			
			

			return 0; // Success
		}

		catch( SQLException ex )
		{
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception :: DeptFolderMappingTable : getFoldersKey - "
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
	 * Method : find( )
	 * 
	 * Input :  DeptKey, Array of FolderDeptMappingData (As ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve list of DeptFolderMappingData values from
	 * groups_dept_mapping table for given dept key whose recycle flage is 1
	 */

	public int find( DeptKey key, List<DeptFolderMappingData> deptFolderMappingDataArr )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		//String deptKeyString = convertDeptKeyStr( key );
		
		query = "SELECT * FROM " + tableName_ + " WHERE dept_rel_key = '" + key.toString( )
		        + "' AND recycle_flag = 1";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				DeptFolderMappingData deptFolderMappingData = new DeptFolderMappingData( );
				
				DeptFolderKey folKey = convertFolderKey( rs.getString( "dept_folder_rel_key" ) );
				DeptKey deptKey	= convertDeptKey( rs.getString( "dept_rel_key" ) );
				
				deptFolderMappingData.deptFolderKey_	= folKey;
				deptFolderMappingData.deptKey_	= deptKey;
				
				deptFolderMappingDataArr.add( deptFolderMappingData );
				
				deptFolderMappingData = null;
			}
			
			

			return 0; // Success
		}

		catch( SQLException ex )
		{
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception :: DeptFolderMappingTable : find - "
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
	 * Input : DeptFolderMappingData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the deptFolderMappingData values from
	 * dept_folder_mapping table
	 */

	public int delete( DeptFolderMappingData deptFolderMappingData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		// Convert DeptFolderMappingData object to DeptFolderMappingRecord object
		DeptFolderMappingRecord record = createDeptFolderMappingRecord( deptFolderMappingData );

		query = "DELETE FROM " + tableName_ + " WHERE dept_rel_key = '";
		query =query + record.deptKey_ + "'";
		query = query+ " AND dept_folder_rel_key='"+record.deptFolderKey_+"'";

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
		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			
			String errorMessage = "Exception :: DeptFolderMappingTable : delete - "
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
	 * Input : deptKey object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the deptFolderMappingData values from
	 * dept_folder_mapping table
	 */

	public int delete( DeptKey deptKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
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
		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			
			String errorMessage = "Exception :: DeptFolderMappingTable : delete - "
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
	 * Input : deptFolderKey object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the deptFolderMappingData values from
	 * dept_folder_mapping table
	 */

	public int delete( DeptFolderKey deptFolderKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		String deptFolderKeyStr = convertDeptFolderKeyStr( deptFolderKey );

		query = "DELETE FROM " + tableName_ + " WHERE dept_folder_rel_key = '";
		query =query + deptFolderKeyStr + "'";
		

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
		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			
			String errorMessage = "Exception :: DeptFolderMappingTable : delete - "
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
	 * Input : deptKey object, folderkey obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete a record that matches
	 * deptkey and folderkey
	 */

	public int delete( DeptKey deptKey, DeptFolderKey folderKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		String deptKeyStr = convertDeptKeyStr( deptKey );

		query = "DELETE FROM " + tableName_ + " WHERE dept_rel_key = '";
		query =query + deptKeyStr + "' AND folder_rel_key = '" + folderKey.toString( ) + "'";
		

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
		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			
			String errorMessage = "Exception :: DeptFolderMappingTable : delete - "
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
	 * Method : createDeptFolderMappingRecord( )
	 * 
	 * Input : DeptFolderMappingData obj
	 * 
	 * Return : DeptFolderMappingRecord obj
	 * 
	 * Purpose: It converts DeptFolderMappingData object to 
	 * DeptFolderMappingRecord object
	 */

	private DeptFolderMappingRecord createDeptFolderMappingRecord(
			DeptFolderMappingData deptFolderMappingData )
	{
		DeptFolderMappingRecord record = new DeptFolderMappingRecord( );

		// set the values
		record.deptKey_ 		= convertDeptKeyStr( deptFolderMappingData.deptKey_ );
		record.deptFolderKey_	= convertDeptFolderKeyStr( deptFolderMappingData.deptFolderKey_ );
		
		return record;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : DeptFolderMappingRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using DeptFolderMappingRecord and
	 * returns as string
	 */

	private String formInsertQuery( DeptFolderMappingRecord record )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(dept_rel_key, dept_folder_rel_key)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.deptKey_ + "', ";
		insertQuery = insertQuery + "'" + record.deptFolderKey_ + "'";

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
			deptKey.companyRegnKey_=companyRegnKey;
			deptKey.deptName_ = strArr[1];
		}

		return deptKey;
	}
	
	/*
	 * Method : convertDeptFolderKeyStr( ) 
	 * 
	 * Input : DeptFolderKey object 
	 * 
	 * Return : String of deptkey which is stored in db
	 * 
	 * Purpose: This method is used to convert deptkey object to deptkey string
	 * Combination of regn  key and folder name is the deptfolderkey.
	 */

	private String convertDeptFolderKeyStr( DeptFolderKey deptFolderKey )
	{
		String deptKeyStr = deptFolderKey.companyRegnKey_.companyPhoneNo_ + ":"
		        +deptFolderKey.folderName_;
		return deptKeyStr;
	}
	
	/*
	 * Method : convertFolderKey( ) 
	 * 
	 * Input : String of folderRelKey which is storedin db 
	 * 
	 * Return : DeptFolderKey object
	 * 
	 * Purpose: This method is used to convert the string value to DeptFolderKey
	 * object
	 */

	private DeptFolderKey convertFolderKey( String folderRelKey )
	{	
		DeptFolderKey deptFodlerKey = new DeptFolderKey( );

		String [ ] strArr = folderRelKey.split( ":" );

		if( strArr.length > 1 )
		{
			CompanyRegnKey companyRegnKey = new CompanyRegnKey( );
			companyRegnKey.companyPhoneNo_ = strArr[0];
			deptFodlerKey.companyRegnKey_ = companyRegnKey;
			companyRegnKey = null;
			deptFodlerKey.folderName_ 	= strArr[1];
		}

		return deptFodlerKey;
	}
	

	/*
	 * Method : updateKey( )
	 * 
	 * Input : DeptKey updatedKey, DeptKey oldKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the DeptKey into
	 * dept_folder_mapping table
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
			errLogger_.logMsg( "Exception::DetpFolderMappingTable:updateKey " + ex );

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
	 * Input : DeptFolderMappingData folderMapData, DeptKey deptKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update recycle flag
	 */

	public int update( DeptFolderMappingData folderMapData )
	{
		String query = "UPDATE " + tableName_;
		query = query + " SET recycle_flag = '" + folderMapData.recyleFlag_ + "'";
		query = query + " WHERE dept_rel_key = '" + folderMapData.deptKey_.toString( ) + "' AND " +
						" dept_folder_rel_key = '" + folderMapData.deptFolderKey_.toString( ) + "'";

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

			if( insertResult > 0 ) return 0;

			return -1;

		}

		catch( SQLException ex )
		{
			System.out.println( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::DetpFolderMappingTable:update " + ex );

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