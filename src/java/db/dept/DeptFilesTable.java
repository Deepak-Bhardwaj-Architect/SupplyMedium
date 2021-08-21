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
import java.sql.Timestamp;
import java.util.List;

import core.dept.DeptFolderKey;
import core.dept.DeptKey;
import core.dept.FileData;
import core.regn.CompanyRegnKey;
import db.regn.CompanyRegnRecord;
import db.utils.DBConnect;
import utils.ErrorLogger;
import utils.ErrorMaster;
import utils.StringHolder;

/**
 * File:  DeptFilesTable.java 
 *
 * Created on May 7, 2013 10:23:49 AM
 */

/*
 * Class: DeptFilesTable 
 * 
 * Purpose: This class is used to perform operations like inserting,
 * updating, deleting and finding records from dept_files table
 * of supply medium
 */

public class DeptFilesTable
{
	private static ErrorMaster errorMaster_ = null;


	ErrorLogger errLogger_;
	String tableName_;
	
	/*
	 * Constructor
	 */
	
	public DeptFilesTable( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errLogger_ 	= ErrorLogger.instance( );
		tableName_	= "dept_files"; 
	}
	
	/*
	 * Method: insert
	 * 
	 * Input: DeptFilesData object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method converts the DeptFilesData into 
	 * DeptFilesRecord and inserts the dept files data into
	 * the dept_files table.
	 * 
	 */
	
	public int insert( FileData fileData )
	{
		DeptFilesRecord record = createDeptFilesRecord( fileData );
		
		String query = formInsertQuery( record );
		
		errorMaster_.insert( "query="+query );

		Statement stmt = null;
		Connection con = null;

		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			
			stmt = con.createStatement( );
			//insertResult = stmt.executeUpdate( query );
			
			insertResult =stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		   
			if( insertResult > 0 ) 
			{
				ResultSet keys = stmt.getGeneratedKeys( );

				if( keys.next( ) )
				{
					fileData.attrData_.deptFileId_ = keys.getInt( 1 );
				}
				
				fileData.attrData_.createdTs_ = new Timestamp( System.currentTimeMillis( ) );
				
				return 0; // Successfully inserted
			}

			return -1; // Insert failed

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "Exception::DeptFilesTable.insert() - " + ex );

			return -2;
		}	
		catch( Exception ex )
		{
			errLogger_.logMsg( "Exception::DeptFilesTable.insert() - " + ex );
			
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
	 * Method : update( )  - To delete a file from folder and move it to recyle bin
	 * 
	 * Input : FileData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the dept_files table. This function updates
	 * the recycle flag to 1 when a file is deleted and
	 * the recycle flag to 0 when a file is restred from recyle bin to folder
	 * 
	 * By default, the recycle flag is 0
	 */

	public int update( FileData fileData )
	{
		//errorMaster_.insert( "before convertion");
		
		DeptFilesRecord record = createDeptFilesRecord( fileData );
		
		//errorMaster_.insert( "after convertion");
		
		String query = "UPDATE " + tableName_ +" SET ";
		
		StringHolder updateStrHolder = new StringHolder( );
		
		getUpdateString( record, updateStrHolder );
		
		query = query + updateStrHolder.value;
		
		query = query + " WHERE dept_file_id =" + record.deptFileId_ + "";

		errorMaster_.insert( "Query = " + query );

		Statement stmt = null;
		Connection con = null;
		
		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			insertResult = stmt.executeUpdate( query );

			if( insertResult > 0 ) 
				return 0;  // Successfully updated

			return -1;  // Update failed

		}

		catch( SQLException ex )
		{
			errLogger_.logMsg( "Exception::DeptFilesTable.update( ) - " + ex );

			return -2;
		}
		catch( Exception ex )
		{
			errLogger_.logMsg( "Exception::DeptFilesTable.update( ) - " + ex );

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
	 * Method : delete
	 * 
	 * Input : DeptKey object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete all the files that 
	 * are present in recycle bin (Empty Recyle Bin) for a
	 * department
	 * 
	 */

	public int delete( DeptKey deptKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		//String deptKeyStr = convertDeptKeyStr( deptKey );
		
		query = "DELETE FROM " + tableName_ + " WHERE dept_rel_key = '";
		query = query + deptKey.toString( ) + "' AND recycle_flag = 1";

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
			String errorMessage = "Exception::DeptFilesTable.delete( DeptKey deptKey  ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		catch( Exception ex )
		{
			String errorMessage = "Exception::DeptFilesTable.delete( DeptKey deptKey ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

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
	 * Method : delete
	 * 
	 * Input : long deptFileId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete a file from 
	 * dept_files table
	 */

	public int delete( long deptFileId )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		query = "DELETE FROM " + tableName_ + " WHERE dept_file_id = ";
		query = query + deptFileId;

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
			String errorMessage = "Exception::DeptFilesTable.delete( long deptFileId  ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		catch( Exception ex )
		{
			String errorMessage = "Exception::DeptFilesTable.delete( long deptFileId ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

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
	 * Method : delete
	 * 
	 * Input : DeptFolderKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete all the files from a folder
	 */

	public int delete( DeptFolderKey deptFolderKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		String deptFolderKeyStr = convertDeptFolderKeyStr( deptFolderKey );

		query = "DELETE FROM " + tableName_ + " WHERE dept_folder_rel_key = '";
		query = query + deptFolderKeyStr + "'";

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
			String errorMessage = "Exception::DeptFilesTable.delete( DeptFolderKey deptFolderKey ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		catch( Exception ex )
		{
			String errorMessage = "Exception::DeptFilesTable.delete( DeptFolderKey deptFolderKey ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

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
	 * Input:  DeptKey deptKey, Timestamp criticalTs
	 * 
	 * Return: int
	 * 
	 * Purpose: This method deletes all the files record of a department's
	 * recycle bin if the file created Timestamp is less than criticalTs
	 * 
	 * (Used to remove from recyle bin using timers)
	 */
	
	public int delete( DeptKey deptKey, Timestamp criticalTs )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		String deptKeyStr = convertDeptKeyStr( deptKey );

		query = "DELETE FROM " + tableName_ + " WHERE dept_rel_key = ";
		query = query + deptKeyStr + "' AND recycle_flag = 1 AND ";
		query = query + "created_timestamp < '" + criticalTs + "'";

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
			String errorMessage = "Exception::DeptFilesTable.delete( DeptKey deptKey, Timestamp criticalTs ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		catch( Exception ex )
		{
			String errorMessage = "Exception::DeptFilesTable.delete( DeptKey deptKey, Timestamp criticalTs ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

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
	 * Input: DeptKey deptKey, List<FilesData> filesDataArr (As ref)
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches all the files for 
	 * the given dept folder key.(Recyle files are not fetched)
	 * 
	 */
	
	public int find( DeptFolderKey deptFolderKey, List<FileData> fileDataArr )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		String deptFolderKeyStr = convertDeptFolderKeyStr( deptFolderKey );
		
		query = "SELECT * FROM " + tableName_ +" WHERE dept_folder_rel_key = '"
				+ deptFolderKeyStr + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				FileData fileData = new FileData( );
				
				fileData.attrData_.createdTs_ 		= rs.getTimestamp( "created_timestamp" );
				fileData.attrData_.deptFileId_		= rs.getLong( "dept_file_id" );
				
				
				fileData.attrData_.regnKey_ 		= convertRegnKey( rs.getString( "regn_rel_key" ) );
				
				fileData.attrData_.deptKey_ 		= convertDeptKey( rs.getString( "dept_rel_key" ) );
				
				fileData.attrData_.deptFolderKey_ 	= convertFolderKey( rs.getString( "dept_folder_rel_key" ) );
				
				fileData.attrData_.fileName_		= rs.getString( "file_name" );
				fileData.attrData_.fileType_		= rs.getString( "file_type" );
				fileData.attrData_.fileSize_		= rs.getDouble( "file_size" );
				fileData.attrData_.localPath_ 		= rs.getString( "local_path" );
				
				fileData.attrData_.recycleFlag_ 	= rs.getInt( "recycle_flag" );
				fileData.attrData_.relativePath_ 	= rs.getString( "relative_path" );
				fileData.attrData_.webUrl_			= rs.getString( "web_url" );
				
				fileDataArr.add( fileData );
				
				fileData = null;
			}

			//if( fileDataArr.size( ) <= 0 ) return -1; //No record exists
				
			
			return 0; // Success
		}

		catch( SQLException ex )
		{
			String errorMessage = "Exception::DeptFilesTable.find( deptFolderKey, " +
								  "fileDataArr ) - "+ ex;

			errLogger_.logMsg( errorMessage );

			return -2; // SqlException
		}
		catch( Exception ex )
		{
			String errorMessage = "Exception::DeptFilesTable.find( deptFolderKey, " +
								  "fileDataArr ) - "+ ex;

			errLogger_.logMsg( errorMessage );

			return -3; // SqlException
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
	 * Input: DeptKey deptKey, List<FilesData> filesDataArr (As ref)
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches all the files for 
	 * the given dept key where recyle file flag is 1)
	 * 
	 */
	
	public int find( DeptKey deptKey, List<FileData> fileDataArr )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		//String deptFolderKeyStr = convertDeptFolderKeyStr( deptFolderKey );
		
		query = "SELECT * FROM " + tableName_ +" WHERE dept_rel_key = '"
				+ deptKey.toString( ) + "' AND recycle_flag = 1";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				FileData fileData = new FileData( );
				
				fileData.attrData_.createdTs_ 		= rs.getTimestamp( "created_timestamp" );
				fileData.attrData_.deptFileId_		= rs.getLong( "dept_file_id" );
				
				
				fileData.attrData_.regnKey_ 		= convertRegnKey( rs.getString( "regn_rel_key" ) );
				
				fileData.attrData_.deptKey_ 		= convertDeptKey( rs.getString( "dept_rel_key" ) );
				
				fileData.attrData_.deptFolderKey_ 	= convertFolderKey( rs.getString( "dept_folder_rel_key" ) );
				
				fileData.attrData_.fileName_		= rs.getString( "file_name" );
				fileData.attrData_.fileType_		= rs.getString( "file_type" );
				fileData.attrData_.fileSize_		= rs.getDouble( "file_size" );
				fileData.attrData_.localPath_ 		= rs.getString( "local_path" );
				
				fileData.attrData_.recycleFlag_ 	= rs.getInt( "recycle_flag" );
				fileData.attrData_.relativePath_ 	= rs.getString( "relative_path" );
				fileData.attrData_.webUrl_			= rs.getString( "web_url" );
				
				fileDataArr.add( fileData );
				
				fileData = null;
			}

			//if( fileDataArr.size( ) <= 0 ) return -1; //No record exists
				
			
			return 0; // Success
		}

		catch( SQLException ex )
		{
			String errorMessage = "Exception::DeptFilesTable.find( deptKey, " +
								  "fileDataArr ) - "+ ex;

			errLogger_.logMsg( errorMessage );

			return -2; // SqlException
		}
		catch( Exception ex )
		{
			String errorMessage = "Exception::DeptFilesTable.find( deptKey, " +
								  "fileDataArr ) - "+ ex;

			errLogger_.logMsg( errorMessage );

			return -3; // SqlException
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
	 * Input: DeptKey deptKey, List<FilesData> filesDataArr (As ref), int recycleFlag
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches all the files for 
	 * the given dept key where recyle file flag is passed as argument)
	 * 
	 */
	
	public int find( DeptKey deptKey, List<FileData> fileDataArr, int recycleFlag )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		//String deptFolderKeyStr = convertDeptFolderKeyStr( deptFolderKey );
		
		query = "SELECT * FROM " + tableName_ +" WHERE dept_rel_key = '"
				+ deptKey.toString( ) + "' AND recycle_flag = '" + recycleFlag + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				FileData fileData = new FileData( );
				
				fileData.attrData_.createdTs_ 		= rs.getTimestamp( "created_timestamp" );
				fileData.attrData_.deptFileId_		= rs.getLong( "dept_file_id" );
				
				
				fileData.attrData_.regnKey_ 		= convertRegnKey( rs.getString( "regn_rel_key" ) );
				
				fileData.attrData_.deptKey_ 		= convertDeptKey( rs.getString( "dept_rel_key" ) );
				
				fileData.attrData_.deptFolderKey_ 	= convertFolderKey( rs.getString( "dept_folder_rel_key" ) );
				
				fileData.attrData_.fileName_		= rs.getString( "file_name" );
				fileData.attrData_.fileType_		= rs.getString( "file_type" );
				fileData.attrData_.fileSize_		= rs.getDouble( "file_size" );
				fileData.attrData_.localPath_ 		= rs.getString( "local_path" );
				
				fileData.attrData_.recycleFlag_ 	= rs.getInt( "recycle_flag" );
				fileData.attrData_.relativePath_ 	= rs.getString( "relative_path" );
				fileData.attrData_.webUrl_			= rs.getString( "web_url" );
				
				fileDataArr.add( fileData );
				
				fileData = null;
			}

			//if( fileDataArr.size( ) <= 0 ) return -1; //No record exists
				
			
			return 0; // Success
		}

		catch( SQLException ex )
		{
			String errorMessage = "Exception::DeptFilesTable.find( deptKey, " +
								  "fileDataArr, recycleFlag ) - "+ ex;

			errLogger_.logMsg( errorMessage );

			return -2; // SqlException
		}
		catch( Exception ex )
		{
			String errorMessage = "Exception::DeptFilesTable.find( deptKey, " +
								"fileDataArr, recycleFlag ) - "+ ex;

			errLogger_.logMsg( errorMessage );

			return -3; // SqlException
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
	 * Input: DeptKey deptKey, List<FilesData> filesDataArr (As ref)
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches all the files for 
	 * the given dept folder key.(Recyle files are not fetched)
	 * 
	 */
	
	public int find( FileData fileData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		query = "SELECT * FROM " + tableName_ +" WHERE dept_file_id = '"
				+ fileData.attrData_.deptFileId_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			
			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				
				fileData.attrData_.createdTs_ = rs.getTimestamp( "created_timestamp" );
				fileData.attrData_.deptFileId_= rs.getLong( "dept_file_id" );
				
				
				fileData.attrData_.regnKey_ = convertRegnKey( rs.getString( "regn_rel_key" ) );
				
				fileData.attrData_.deptKey_ = convertDeptKey( rs.getString( "dept_rel_key" ) );
				
				fileData.attrData_.deptFolderKey_ = convertFolderKey( rs.getString( "dept_folder_rel_key" ) );
				
				fileData.attrData_.fileName_	= rs.getString( "file_name" );
				fileData.attrData_.fileType_	= rs.getString( "file_type" );
				fileData.attrData_.fileSize_	= rs.getDouble( "file_size" );
				fileData.attrData_.localPath_ = rs.getString( "local_path" );
				
				fileData.attrData_.recycleFlag_ = rs.getInt( "recycle_flag" );
				fileData.attrData_.relativePath_ = rs.getString( "relative_path" );
				fileData.attrData_.webUrl_	= rs.getString( "web_url" );
				
				return 0; // successfully fetched
			}

			else
			{
				return -1; // There is no file available for given file id.
			}
			
		}

		catch( SQLException ex )
		{
			String errorMessage = "Exception::DeptFilesTable.find( deptFolderKey, " +
								  "fileDataArr ) - "+ ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		catch( Exception ex )
		{
			String errorMessage = "Exception::DeptFilesTable.find( deptFolderKey, " +
								  "fileDataArr ) - "+ ex;

			errLogger_.logMsg( errorMessage );

			return -2; // SqlException
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
	 * Method : createDeptFilesRecord( )
	 * 
	 * Input : FilesData obj
	 * 
	 * Return : DeptFilesRecord obj
	 * 
	 * Purpose: It converts FilesData object to DeptFilesRecord object
	 */

	private DeptFilesRecord createDeptFilesRecord(
			FileData fileData )
	{
		DeptFilesRecord record = new DeptFilesRecord( );

		record.deptFileId_		= fileData.attrData_.deptFileId_;
		
		errorMaster_.insert( "dept key parser before");
		
		if(  fileData.attrData_.deptKey_ != null)
			record.deptRelKey_ 		= convertDeptKeyStr( fileData.attrData_.deptKey_ );
		
		errorMaster_.insert( "dept key parser before");
		
		if( fileData.attrData_.deptFolderKey_ != null )
			record.deptFolderRelKey_= convertDeptFolderKeyStr( fileData.attrData_.deptFolderKey_ );
		
		record.relativePath_	= fileData.attrData_.relativePath_;
		record.webUrl_			= fileData.attrData_.webUrl_;
		record.localPath_		= fileData.attrData_.localPath_;
		record.fileName_		= fileData.attrData_.fileName_;
		
		record.fileType_		= fileData.attrData_.fileType_;
		record.recycleFlag_		= fileData.attrData_.recycleFlag_;
		record.fileSize_		= fileData.attrData_.fileSize_;
		
		if( fileData.attrData_.regnKey_!= null )
			record.regnRelKey_		= fileData.attrData_.regnKey_.toString( );
				
		return record;
	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : DeptFilesRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using DeptFilesRecord and
	 * returns as string
	 */

	private String formInsertQuery( DeptFilesRecord record )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(regn_rel_key, dept_rel_key, dept_folder_rel_key, ";
		insertValues += "relative_path, local_path, web_url, file_name, file_type, recycle_flag)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.regnRelKey_ + "', ";
		insertQuery = insertQuery + "'" + record.deptRelKey_ + "', ";
		insertQuery = insertQuery + "'" + record.deptFolderRelKey_ + "', ";
		insertQuery = insertQuery + "'" + record.relativePath_ + "', ";
		insertQuery = insertQuery + "'" + record.localPath_ + "', ";
		insertQuery = insertQuery + "'" + record.webUrl_ + "', ";
		insertQuery = insertQuery + "'" + record.fileName_ + "', ";
		insertQuery = insertQuery + "'" + record.fileType_ + "', ";
		insertQuery = insertQuery + record.recycleFlag_ + "";

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
		String deptFodlerKeyStr = null;
		
		if( deptFolderKey.companyRegnKey_ != null )
			deptFodlerKeyStr = deptFolderKey.companyRegnKey_.companyPhoneNo_ + ":"
		        +deptFolderKey.folderName_;
		
		return deptFodlerKeyStr;
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

		String [] strArr = folderRelKey.split( ":" );

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
	 * Method : convertRegnKey( ) 
	 * 
	 * Input : String of regnkey which is storedin db 
	 * 
	 * Return : RegnKey object
	 * 
	 * Purpose: This method is used to convert the string value to RegnKey
	 * object
	 */

	private CompanyRegnKey convertRegnKey( String regnKeyStr )
	{	
		CompanyRegnKey regnKey = new CompanyRegnKey( );

		regnKey.companyPhoneNo_ = regnKeyStr;

		return regnKey;
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
		String deptKeyStr = null;
	
		if( deptKey.companyRegnKey_ != null)
			deptKeyStr = deptKey.companyRegnKey_.companyPhoneNo_ + ":"
		        + deptKey.deptName_;
		
		return deptKeyStr;
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
	
	
	/**
	 * @Return : int
	 * 
	 * @Purpose : Update String for DeptFilesTable
	 * 
	 * @param filter
	 * @param query
	 * @return
	 * 
	 */
	public int getUpdateString( DeptFilesRecord filter, StringHolder query )
	{
		try
		{
			query.value = "";
			
			query.value += filter.deptFileId_ != 0 ? " dept_file_id='"+filter.deptFileId_ +"' ," :"";

			query.value += filter.regnRelKey_ != null ? " regn_rel_key='"+filter.regnRelKey_+"' ," : "";

			query.value += filter.deptRelKey_ != null ? " dept_rel_key='"+filter.deptRelKey_+"' ," : "";

			query.value += filter.deptFolderRelKey_ != null ? " dept_folder_rel_key='"+filter.deptFolderRelKey_+"' ," : "";

			query.value += filter.relativePath_ != null ? " relative_path='"+filter.relativePath_+"'," : "";

			query.value += filter.localPath_ != null ? " local_path='"+filter.localPath_+"'," : "";

			query.value += filter.webUrl_ != null ? " web_url='"+filter.webUrl_+"'," : "";
			
			query.value += filter.fileName_ != null ? " file_name='"+filter.fileName_+"'," : "";

			query.value += filter.fileType_ != null ? " file_type='"+filter.fileType_+"'," : "";
			
			query.value += filter.fileSize_ != 0.0f ? " file_size='"+filter.fileSize_+"'," : "";

			query.value += " recycle_flag='"+filter.recycleFlag_+"'";

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "CompanyRegnUtils :: getUpdateString :: " + ex );
			return -1;
		}
	}
	
}
