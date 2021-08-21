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

package db.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ErrorLogger;
import core.config.SMConfigData;
import db.utils.DBConnect;

/**
 * File:  SMConfigTable.java 
 *
 * Created on Jan 18, 2013 11:27:28 AM
 */

public class SMConfigTable
{
	private String tableName_;

	/*
	 * Method : SMConfigTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */
	
	public SMConfigTable()
	{
		this.tableName_ = "sm_config";
	}

	/*
	 * Method : insert( )
	 * 
	 * Input : SMConfigData obj
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to insert the supply medium config values into
	 * supply_medium_config table
	 */
	
	public int insert( SMConfigData smConfigData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		// Here the smConfigData will come in as the parameter,

		// Create SMConfigRecord using smConfigData
		SMConfigRecord smConfigRec = createSMConfigRecord( smConfigData );

		// Form the insertQuery using the SMConfigRecord object.
		String query = formInsertQuery( smConfigRec );

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

		catch( SQLException ex ) // SQL Exception
		{
			errLogger.logMsg( "Exception::SMConfigTable:insert-" + ex );

			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::SMConfigTable:insert-" + ex );

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
	 * Input : SMConfigData obj
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to update the SMConfigData values into
	 * supply_medium_table table
	 */
	
	public int update( SMConfigData smConfigdata )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		SMConfigRecord smConfigRec = createSMConfigRecord( smConfigdata );

		String query = "UPDATE " + tableName_;
		query = query + " SET reg_link_expire_days = " + smConfigRec.regLinkExpireDays_;

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

		catch( SQLException ex ) // SQL Exception
		{
			errLogger.logMsg( "Exception::SMConfigTable:update-" + ex );

			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::SMConfigTable:update-" + ex );

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
	 * Method : getSMConfig( ) 
	 * 
	 * Input : SMConfigData object 
	 * 
	 * Return :int
	 * 
	 * Purpose: It is used to get the supply medium config values. And
	 * assign to SMConfigData parameter so that caller class can access this values.
	 */

	
	public int getSMConfig( SMConfigData data )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		// List<CompanyRegnRecord> companyRegnRecObjList = new
		// ArrayList<CompanyRegnRecord>();

		query = "SELECT * FROM " + tableName_;

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				data.regLinkExpireDays_ = rs.getInt( "reg_link_expire_days" );
				data.recycleExpireDays_ = rs.getInt( "recycle_files_expire_days" );
				data.taxPercentage_		= rs.getDouble( "tax_percentage" );
			}

			return 0; // Success
		}

		catch( SQLException ex ) // SQL Exception
		{
			errLogger.logMsg( "Exception::SMConfigTable:getSMConfig-" + ex );

			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::SMConfigTable:getSMConfig-" + ex );

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
	 * Method : createSMConfigRecord( )
	 * 
	 * Input : SMConfigData obj
	 * 
	 * Return : SMConfigRecord obj
	 * 
	 * Purpose: It converts SMConfigData object to SMConfigRecord object
	 */
	
	private SMConfigRecord createSMConfigRecord( SMConfigData smConfigData )
	{
		// Form the record using the SMConfigData and return it
		SMConfigRecord smConfigRecord = new SMConfigRecord( );

		// set the values
		smConfigRecord.regLinkExpireDays_ = smConfigData.regLinkExpireDays_;
		smConfigRecord.recycleExpireDays_ = smConfigData.recycleExpireDays_;

		return smConfigRecord;

	}

	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : SMConfigRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using SMConfigRecord and
	 * returns as string
	 */
	
	private String formInsertQuery( SMConfigRecord smConfigRecord )
	{
		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(reg_link_expire_days, recycle_files_expire_days, tax_percentage)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + smConfigRecord.regLinkExpireDays_;
		
		insertQuery = insertQuery + smConfigRecord.recycleExpireDays_;
		
		insertQuery = insertQuery + smConfigRecord.taxPercentage_;

		insertQuery = insertQuery + ")";

		return insertQuery;
	}

}
