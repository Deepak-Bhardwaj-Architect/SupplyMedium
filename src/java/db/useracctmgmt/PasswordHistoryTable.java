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
package db.useracctmgmt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import utils.ErrorLogger;
import utils.IntHolder;

import core.login.PasswordPolicyData;
import core.regn.UserProfileKey;
import core.useracctmgmt.PasswordResetData;
import db.utils.DBConnect;
import utils.ErrorMaster;


/**
 * File:  PasswordHistoryTable.java 
 *
 * Created on Apr 22, 2013 2:15:24 PM
 */

/*
 * Class  : PasswordHistoryTable
 * 
 * Purpose: It is used to query the password_history table.
 * 
 */

public class PasswordHistoryTable
{
	/*
	 * Constructor : PasswordHistoryTable( ) 
	 * 
	 * Input : None
	 * 
	 * Purpose: it is used to initialize the tableName variable
	 */
	private static ErrorMaster errorMaster_ = null;


	private String tableName_;
	
	ErrorLogger errLogger_;
	
	public PasswordHistoryTable( )
    {
        

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	    this.tableName_ = "password_history";
	    
	    errLogger_ = ErrorLogger.instance( );
    }
	
	/*
	 * Method: insert( )
	 * 
	 * Input: PasswordResetData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method gets the values from PasswordResetData object,
	 * converts it into PasswordHistoryRecord object and inserts 
	 * into the password_history table of supply medium db
	 * 
	 */
	
	public int insert( PasswordResetData passResetData )
    {
		
		passResetData.show();
		
		PasswordHistoryRecord historyRecord = createPasswordHistoryRecord( passResetData );
		
		
		
		String query = formInsertQuery( historyRecord );
		
		
		
		Statement stmt = null;
		Connection con = null;

		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			insertResult = stmt.executeUpdate( query );
			
			errorMaster_.insert( "query ="+query );

			if( insertResult > 0 ) return 0;

			return -1;

		}
		catch( SQLException ex ) // Sql Exception
		{
			errLogger_.logMsg( "Exception::PasswordHistoryTable.insert() - " + ex );

			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger_.logMsg( "Exception::PasswordHistoryTable.insert() - " + ex );

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
	 * Method: update( )
	 * 
	 * Input: PasswordResetData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method gets the values from PasswordResetData object,
	 * converts it into PasswordHistoryRecord object and updates the
	 * password_history table of supply medium db
	 *
	 */
	
	public int update( PasswordResetData passResetData )
	{
		return 0;
	}
	
	/*
	 * Method: delete( )
	 * 
	 * Input: PasswordResetData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method deletes the values from password_history table for the
	 * given PasswordResetData object
	 */
	
	public int delete( PasswordResetData passResetData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE password_history_id = '" 
				+ passResetData.passwordHistoryId_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) return 0;

			return -1;
		}
		catch( SQLException ex ) // Sql Exception
		{
			errLogger_.logMsg( "Exception::PasswordHistoryTable.delete( PasswordResetData ) - "+ex );

			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger_.logMsg( "Exception::PasswordHistoryTable.delete( PasswordResetData ) - "+ex );

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
	 * Method: delete( )
	 * 
	 * Input: UserProfileKey obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method deletes the all the values from password_history table for
	 * the user_rel_key specified by the UserProfileKey obj
	 */
	
	public int delete( UserProfileKey userProfileKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE user_rel_key = '" 
				+ userProfileKey.email_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) return 0;

			return -1;
		}
		catch( SQLException ex ) // Sql Exception
		{
			errLogger_.logMsg( "Exception::PasswordHistoryTable.delete( UserProfileKey ) - "+ex );

			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger_.logMsg( "Exception::PasswordHistoryTable.delete( UserProfileKey ) - "+ex );

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
	 * Method: isExists( )
	 * 
	 * Input: UserProfileKey obj, List<PasswordResetData> obj (As ref)
	 *  
	 * Return: int
	 * 
	 * Purpose: This method gets the list of records form password_history
	 * table of the supply medium db where it matches the search criteria
	 * (user_rel_key == key from PasswordResetData object AND password == 
	 * password from passResetData object) and returns integer value.
	 * 
	 * If returns 1, then password not exists
	 * else, the password exists or some exception.
	 * 
	 */
	
	public int isExists( PasswordResetData passResetData, PasswordPolicyData passPolicyData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE ";
		query = query + "user_rel_key = '" + passResetData.userKey_.email_ + "' AND ";
		query = query + "password = '" + passResetData.newPassword_ + "'";
		query = query + " ORDER BY created_timestamp DESC";
		query = query + " LIMIT " + passPolicyData.passwordHistoryDays_;
		
		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if ( rs.next( ) )
			{
				return 1;
			}

			return 0; // Success
		}

		catch( SQLException ex )  // SqlException
		{
			errLogger_.logMsg( "Exception::CompanyRegnTable:getCompany-"+ex );

			return -1; 
		}
		catch( Exception ex )  // General Exception
		{
			errLogger_.logMsg( "Exception::CompanyRegnTable:getCompany-"+ex );

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
	 * Method: find( )
	 * 
	 * Input: UserProfileKey obj, List<PasswordResetData> obj (As ref), PasswordPolicyData object
	 *  
	 * Return: int
	 * 
	 * Purpose: This method gets the list of passwords from password_history
	 * table of the supply medium db and fills the List<PasswordResetData> obj
	 * which is passed as reference object
	 */
	
	public int find( 
						UserProfileKey userProfileKey, 
						List<PasswordResetData> passResetDataList,
						PasswordPolicyData passPolicyData
					)
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE ";
		query = query + "user_rel_key = '" + userProfileKey.email_ + "'";
		query = query + " ORDER BY time_val DESC";
		query = query + " LIMIT " + passPolicyData.passwordHistoryDays_;
		
		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while ( rs.next( ) )
			{
				PasswordResetData data = new PasswordResetData( );
				
				data.oldPassword_ = rs.getString( "password" );
				
				passResetDataList.add( data );
				
				data = null;
			}

			return 0; // Success
		}

		catch( SQLException ex )  // SqlException
		{
			errLogger_.logMsg( "Exception::PasswordHistoryTable.find(UserProfileKey, " +
							   "List<PasswordResetData>, " +
							   "PasswordPolicyData) - "+ex );

			return -1; 
		}
		catch( Exception ex )  // General Exception
		{
			errLogger_.logMsg( "Exception::PasswordHistoryTable.find(UserProfileKey, " +
					   "List<PasswordResetData>, " +
					   "PasswordPolicyData) - "+ex );

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
	 * Method: find( )
	 * 
	 * Input: UserProfileKey obj, List<PasswordResetData> obj (As ref)
	 *  
	 * Return: int
	 * 
	 * Purpose: This method gets the list of passwords from password_history
	 * table of the supply medium db and fills the PasswordResetData obj
	 * which is passed as reference object
	 */
	
	public int find( UserProfileKey userProfileKey, PasswordResetData passResetData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE ";
		query = query + "user_rel_key = '" + userProfileKey.email_ + "'";
		query = query + " ORDER BY time_val LIMIT 1";
		
		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if ( rs.next( ) )
			{				
				passResetData.passwordHistoryId_ = rs.getLong( "password_history_id" );
				passResetData.oldPassword_ = rs.getString( "password" );
				passResetData.userKey_ = userProfileKey;
				
				return 0; // Success
			}
			
			return -1; //no record exists
		}

		catch( SQLException ex )  // SqlException
		{
			errLogger_.logMsg( "Exception::PasswordHistoryTable.find(UserProfileKey, " +
							   "PasswordResetData" +
							   " - "+ex );
			
			return -2; 
		}
		catch( Exception ex )  // General Exception
		{
			errLogger_.logMsg( "Exception::PasswordHistoryTable.find(UserProfileKey, " +
					   "PasswordResetData" +
					   " - "+ex );

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
	 * Method: getMax( )
	 * 
	 * Input: UserProfileKey obj, IntHolder max ( As ref )
	 *  
	 * Return: int
	 * 
	 * Purpose: This method gets the value of max from password_history table
	 * of supplymedium_db, where max refers to the number of passwords stored 
	 * in the table for given UserProfileKey object
	 */
	
	public int getMax( UserProfileKey userProfileKey, IntHolder max )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		query = "SELECT COUNT(password) AS max FROM " + tableName_ + " WHERE ";
		query = query + "user_rel_key = '" + userProfileKey.email_ + "'";
		
		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if ( rs.next( ) )
			{				
				max.value = rs.getInt( "max" );
				
				return 0; // Success
			}
			
			return -1; //no record exists
		}

		catch( SQLException ex )  // SqlException
		{
			errLogger_.logMsg( "Exception::PasswordHistoryTable.getMax() - "+ex );
			
			return -2; 
		}
		catch( Exception ex )  // General Exception
		{
			errLogger_.logMsg( "Exception::PasswordHistoryTable.getMax() - "+ex );

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
	 * Method : createPasswordHistoryRecord( )
	 * 
	 * Input : PasswordResetData obj
	 * 
	 * Return : PasswordHistoryRecord
	 * 
	 * Purpose: It converts PasswordResetData to PasswordHistoryRecord
	 */

	private PasswordHistoryRecord createPasswordHistoryRecord( PasswordResetData passResetData )
	{
		// Form the record using the passResetData and return it
		PasswordHistoryRecord historyRecord = new PasswordHistoryRecord( );

		// set the values
		historyRecord.passwordHistoryId_ = passResetData.passwordHistoryId_;
		historyRecord.regnRelKey_ = passResetData.regnKey_.companyPhoneNo_;
		historyRecord.userRelKey_ = passResetData.userKey_.email_;
		historyRecord.password_ = passResetData.newPassword_;
		
		return historyRecord;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : PasswordHistoryRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using PasswordHistoryRecord and returns as
	 * string
	 */

	private String formInsertQuery( PasswordHistoryRecord historyRecord )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(regn_rel_key, user_rel_key, password)";
		
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";
		
		insertQuery = insertQuery + "'" + historyRecord.regnRelKey_ + "', ";
		insertQuery = insertQuery + "'" + historyRecord.userRelKey_ + "', ";
		insertQuery = insertQuery + "'" + historyRecord.password_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}


}
