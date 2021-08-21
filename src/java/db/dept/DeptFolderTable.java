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
import core.dept.DeptFolderData;
import core.dept.DeptFolderKey;
import core.regn.CompanyRegnKey;
import core.usermgmt.GroupData;
import core.usermgmt.GroupKey;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  DeptFoldersTable.java 
 *
 * Created on Mar 5, 2013 3:28:59 PM
 */

/*
 * Class  : DeptFoldersTable
 * 
 * Purpose: It is used to query the dept_folders table.
 * 
 */

public class DeptFolderTable
{

    private static ErrorMaster errorMaster_ = null;


	private String tableName_;

	/*
	 * Method : DeptFoldersTable( ) - constructor
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public DeptFolderTable()
	{
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "dept_folders";
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : DeptFolderData obj
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to insert the DeptFolder data values into
	 * dept_folders table
	 */

	public int insert( DeptFolderData deptFolderData )
	{
		// Convert DeptFolderData object to DeptFolderRecord object
		DeptFolderRecord record = createDeptFolderRecord( deptFolderData );

		// Form the insert query
		String query = formInsertQuery( record );
		
		errorMaster_.insert( "query="+query );

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
			errLogger.logMsg( "Exception :: DeptFolderTable : insert - " + ex );

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
	 * Input : deptFolderData obj
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to update the DeptFolderData values into
	 * dept_folders table
	 */

	public int update( DeptFolderData deptFolderData )
	{
		// Convert DeptFolderData object to DeptFolderRecord object
		DeptFolderRecord record = createDeptFolderRecord( deptFolderData );

		// Form the update query 
		
		String query = "UPDATE " + tableName_;
		query = query + " SET folder_name = " + record.folderName_+",";
		query = query + " SET folder_path = " + record.folderPath_+",";
		query = query + " WHERE dept_folder_key ='" + record.deptFolderKey_ + "'";

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
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger.logMsg( "Exception :: DeptFolderTable : update - " + ex );

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
	 * Method : getDeptFolder( )
	 * 
	 * Input :  GroupKey, GroupData (As ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve GroupData values from
	 * groups table for given Group key
	 */

	public int getDeptFolder( DeptFolderKey key, DeptFolderData data )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		String groupKeyString = convertDeptFolderKeyStr( key );

		query = "SELECT * FROM " + tableName_ + " WHERE dept_folder_key = '" + groupKeyString
		        + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				DeptFolderKey deptFodlerKey = convertFolderKey( rs.getString( "dept_folder_key" ) );
				
				data.deptFolderKey_		= deptFodlerKey;
				data.folderName_ 		= rs.getString( "folder_name" );
				data.folderPath_  		= rs.getString( "folder_path" );
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception :: DeptFolderTable : getDeptFolder - "
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
	 * Method : isExist( ) 
	 * 
	 * Input  : DeptFolderData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check the folder already exist 
	 * not. If already exist return 1 otherwise return 0
	 */

	public int isExist( DeptFolderData deptFolderData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		
		DeptFolderRecord record = createDeptFolderRecord(  deptFolderData );

		// Convert company regn key object to string
		String key = convertRegnKeyStr( deptFolderData.deptFolderKey_.companyRegnKey_ );

		query = "SELECT * FROM " + tableName_ + " WHERE folder_name = '" + record.folderName_+"' AND " +
								"regn_rel_key='"+key +"'";
		
		errorMaster_.insert( "query="+query );

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

			String errorMessage = "Exception :: DeptFolderTable : isExist - " + ex;

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
	 * Method : getAllFolders( )
	 * 
	 * Input : RegnKey, list of DeptFolderData (As ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve list of DeptFolders objects for given company from
	 * dept_folder table 
	 */

	public int getAllDeptFolders( CompanyRegnKey key, List<DeptFolderData> dataList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		

		query = "SELECT * FROM " + tableName_ +" WHERE regn_rel_key = '"+key.companyPhoneNo_+"'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				DeptFolderKey deptFolderKey = convertFolderKey( rs.getString( "dept_folder_key" ) );
				
				DeptFolderData data = new DeptFolderData( );
				
				data.deptFolderKey_		= deptFolderKey;
				data.folderName_ 		= rs.getString( "folder_name" );
				data.folderPath_		= rs.getString( "folder_path" );
				
				dataList.add( data );
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "DeptFolderTable:getAllDeptFolder::SQL Error "
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
	 * Input : DeptFolderKey object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the deptFolderData values from
	 * dept_folders table
	 */

	public int delete( DeptFolderKey key )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		// Convert DeptFodlerKey object to string
		String deptKeyStr = convertDeptFolderKeyStr( key );

		query = "DELETE FROM " + tableName_ + " WHERE dept_folder_key = '";
		query=query+ deptKeyStr + "'";

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
			String errorMessage = "Exception :: DeptFolderTable : delete - "
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
	 * Method : createDeptFolderRecord( )
	 * 
	 * Input : DeptData obj
	 * 
	 * Return : DeptRecord obj
	 * 
	 * Purpose: It converts DeptData object to DeptRecord object
	 */

	private DeptFolderRecord createDeptFolderRecord(
			DeptFolderData deptFolderData )
	{
		DeptFolderRecord record = new DeptFolderRecord( );

		// set the values
		record.deptFolderKey_ 	= convertDeptFolderKeyStr( deptFolderData.deptFolderKey_ );
		record.folderName_		= deptFolderData.folderName_;
		record.folderPath_		= deptFolderData.folderPath_;
		record.regnKey_         = deptFolderData.deptFolderKey_.companyRegnKey_.companyPhoneNo_;
		
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

	private String formInsertQuery( DeptFolderRecord record )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(dept_folder_key,regn_rel_key,folder_name,folder_path)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.deptFolderKey_ + "', ";
		insertQuery = insertQuery + "'" + record.regnKey_ + "', ";
		insertQuery = insertQuery + "'" + record.folderName_ + "', ";
		insertQuery = insertQuery + "'" + record.folderPath_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
	
	/*
	 * Method : convertDeptFolderKeyStr( ) 
	 * 
	 * Input : DeptFolderKey object 
	 * 
	 * Return : String of deptkey which is stored in db
	 * 
	 * Purpose: This method is used to convert deptkey object to deptkey string
	 * Combination of regn key and folder name is the deptfolderkey.
	 */

	private String convertDeptFolderKeyStr( DeptFolderKey deptFolderKey )
	{
		String deptKeyStr = deptFolderKey.companyRegnKey_.companyPhoneNo_ + ":"
		        +deptFolderKey.folderName_;
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
	

}
