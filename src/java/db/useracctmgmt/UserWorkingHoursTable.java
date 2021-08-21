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

import utils.ErrorLogger;
import core.regn.UserProfileKey;
import core.useracctmgmt.WorkingHoursData;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  UserWorkingHoursTable.java 
 *
 * Created on Apr 22, 2013 2:18:49 PM
 */
/*
 * Class: UserWorkingHoursTable
 *
 * Purpose: This class is used to insert, update, delete and find
 * the user working hours records from user_working_hours
 * 
 */

public class UserWorkingHoursTable
{
private static ErrorMaster errorMaster_ = null;


	ErrorLogger errLogger_;
	
	private String tableName_;
	
	/*
	 * Method: UserWorkingHoursTable( ) - Constructor
	 * 
	 * Purpose: To instantiate errorlogger and table name
	 */
	
	
	public UserWorkingHoursTable( )
    {
        

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	    this.tableName_ = "user_working_hours";
	    
	    errLogger_ = ErrorLogger.instance( );
    }
	
	
	/*
	 * Method: insert( )
	 * 
	 * Input: WorkingHoursData obj
	 * 
	 * Output: int
	 * 
	 * Purpose: To convert the WorkingHoursData into UserWorkingHoursRecord object
	 * and then insert the values into user_working_hours
	 * 
	 */

	public int insert( WorkingHoursData workingHrsData )
    {
		UserWorkingHoursRecord record = createWorkingHoursRec( workingHrsData );

		String query = formInsertQuery( record );

		Statement stmt = null;
		Connection con = null;

		errorMaster_.insert( "Query = " + query );
		
		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			insertResult = stmt.executeUpdate( query );

			if( insertResult > 0 ) return 0;

			return -1;
		}

		catch( SQLException ex ) // Sql Exception
		{
			errLogger_.logMsg( "Exception::UserWorkingHoursTable.insert() - " + ex );
			
			return -3;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger_.logMsg( "Exception::UserWorkingHoursTable.insert() - " + ex );
			
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
	 * Method: update
	 * 
	 * Input: WorkingHoursData
	 * 
	 * Output: int
	 * 
	 * Purpose: To update the user_working_hours table with the
	 * updated values from WorkingHoursData
	 * 
	 */
	
	public int update( WorkingHoursData workingHrsData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		UserWorkingHoursRecord record = createWorkingHoursRec( workingHrsData );
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		query = "UPDATE " + tableName_;
		query = query + " SET working_days = " + record.workingDays_ + ", ";
		
		query = query + "sun_fromtime = '" + record.sunFromTime_ + "', ";
		query = query + "sun_totime = '" + record.sunToTime_ + "', ";
		
		query = query + "mon_fromtime = '" + record.monFromTime_ + "', ";
		query = query + "mon_totime = '" + record.monToTime_ + "', ";
		
		query = query + "tue_fromtime = '" + record.tueFromTime_ + "', ";
		query = query + "tue_totime = '" + record.tueToTime_ + "', ";
		
		query = query + "wed_fromtime = '" + record.wedFromTime_ + "', ";
		query = query + "wed_totime = '" + record.wedToTime_ + "', ";
		
		query = query + "thu_fromtime = '" + record.thuFromTime_ + "', ";
		query = query + "thu_totime = '" + record.thuToTime_ + "', ";
		
		query = query + "fri_fromtime = '" + record.friFromTime_ + "', ";
		query = query + "fri_totime = '" + record.friToTime_ + "', ";
		
		query = query + "sat_fromtime = '" + record.satFromTime_ + "', ";
		query = query + "sat_totime = '" + record.satToTime_ + "'";
		
		query = query + " WHERE user_rel_key ='" + record.userRelKey_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			int updateResult = 0;

			updateResult = stmt.executeUpdate( query );

			if( updateResult > 0 )
			{
				return 0; // Success
			}

			else
			{
				return -1; // No record for user profile key
			}

		}

		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::UserWorkingHoursTable.update()-"+ex );

			return -2; 
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::UserWorkingHoursTable.update()-"+ex );

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
	 * Input : UserProfileKey userKey
	 * 
	 * Return : int, 0 - success otherwise failed
	 * 
	 * Purpose: It is used to delete the working hours data from
	 * user_working_hours table for given UserProfileKey object
	 */

	public int delete( UserProfileKey userKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "DELETE FROM " + tableName_ + " WHERE user_rel_key = '" + userKey.email_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			
			if( con == null )
			{
				return -1;
			}
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) return 0;

			return -1;

		}
		catch( SQLException ex )  // Sql Exception
		{
			errLogger.logMsg( "Exception::UserWorkingHoursTable.delete() - " + ex );

			return -2;
		}
		catch( Exception ex )  // General Exception
		{
			errLogger.logMsg( "Exception::UserWorkingHoursTable.delete() - " + ex );

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
	 * Input: WorkingHoursData object (As ref)
	 * 
	 * Output: int
	 * 
	 * Purpose: This method fetches the WorkingHoursData for
	 * given UserProfileKey object and fills the WorkingHoursData object
	 * which is passed as reference
	 * 
	 */
	
	public int find( WorkingHoursData workingHrsData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE user_rel_key = '"
		        + workingHrsData.userProfileKey_.email_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				workingHrsData.workingDays_ = rs.getInt( "working_days" );
				workingHrsData.sunFromTime_ = rs.getTime( "sun_fromtime" );
				workingHrsData.sunToTime_ 	= rs.getTime( "sun_totime" );
				
				workingHrsData.monFromTime_ = rs.getTime( "mon_fromtime" );
				workingHrsData.monToTime_	= rs.getTime( "mon_totime" );
				
				workingHrsData.tueFromTime_ = rs.getTime( "tue_fromtime" );
				workingHrsData.tueToTime_ 	= rs.getTime( "tue_totime" );
				
				workingHrsData.wedFromTime_ = rs.getTime( "wed_fromtime" );
				workingHrsData.wedToTime_	= rs.getTime( "wed_totime" );
				
				workingHrsData.thuFromTime_ = rs.getTime( "thu_fromtime" );
				workingHrsData.thuToTime_	= rs.getTime( "thu_totime" );
				
				workingHrsData.friFromTime_	= rs.getTime( "fri_fromtime" );
				workingHrsData.friToTime_	= rs.getTime( "fri_totime" );
				
				workingHrsData.satFromTime_	= rs.getTime( "sat_fromtime" );
				workingHrsData.satToTime_	= rs.getTime( "sat_totime" );

				return 0; // Success
			}

			return -1; // No record for email
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::UserWorkingHoursTable.find() - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch (Exception e) 
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::UserWorkingHoursTable.find() - "
			        + e;

			errLogger_.logMsg( errorMessage );

			return -3; // Sql error
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
	 * Method : createWorkingHoursRec( )
	 * 
	 * Input : WorkingHoursData obj
	 * 
	 * Return : UserWorkingHoursRecord obj
	 * 
	 * Purpose: It converts WorkingHoursData object to UserWorkingHoursRecord object
	 */
	
	private UserWorkingHoursRecord createWorkingHoursRec( WorkingHoursData workingHrsData )
	{

		UserWorkingHoursRecord record = new UserWorkingHoursRecord( );

		// set the values
		record.userRelKey_		= workingHrsData.userProfileKey_.email_;
		record.workingDays_		= workingHrsData.workingDays_;
		
		record.sunFromTime_		= workingHrsData.sunFromTime_;
		record.sunToTime_		= workingHrsData.sunToTime_; 	
		
		record.monFromTime_		= workingHrsData.monFromTime_; 
		record.monToTime_		= workingHrsData.monToTime_;
		
		record.tueFromTime_		= workingHrsData.tueFromTime_; 
		record.tueToTime_		= workingHrsData.tueToTime_; 	
		
		record.wedFromTime_		= workingHrsData.wedFromTime_;
		record.wedToTime_		= workingHrsData.wedToTime_;	
		
		record.thuFromTime_		= workingHrsData.thuFromTime_; 
		record.thuToTime_		= workingHrsData.thuToTime_;	
		
		record.friFromTime_		= workingHrsData.friFromTime_;	
		record.friToTime_		= workingHrsData.friToTime_;	
		
		record.satFromTime_		= workingHrsData.satFromTime_;
		record.satToTime_	 	= workingHrsData.satToTime_;
		
		return record;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : UserWorkingHoursRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using UserWorkingHoursRecord and
	 * returns as string
	 */
	
	private String formInsertQuery( UserWorkingHoursRecord record )
	{

		// form the query
		
		String insertQuery = null;
		String insertValues = null;

		insertValues = "(user_rel_key, working_days, sun_fromtime, sun_totime, ";
		insertValues = insertValues + "mon_fromtime, mon_totime, ";
		insertValues = insertValues + "tue_fromtime, tue_totime, ";
		insertValues = insertValues + "wed_fromtime, wed_totime, ";
		
		insertValues = insertValues + "thu_fromtime, thu_totime, ";
		insertValues = insertValues + "fri_fromtime, fri_totime, ";
		insertValues = insertValues + "sat_fromtime, sat_totime)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "('";

		insertQuery = insertQuery + record.userRelKey_ + "', ";
		insertQuery = insertQuery + record.workingDays_ + ", ";
		insertQuery = insertQuery + "'" + record.sunFromTime_ + "', ";
		insertQuery = insertQuery + "'" + record.sunToTime_ + "', ";
		
		insertQuery = insertQuery + "'" + record.monFromTime_ + "', ";
		insertQuery = insertQuery + "'" + record.monToTime_ + "', ";
		
		insertQuery = insertQuery + "'" + record.tueFromTime_ + "', ";
		insertQuery = insertQuery + "'" + record.tueToTime_ + "', ";
		
		insertQuery = insertQuery + "'" + record.wedFromTime_ + "', ";
		insertQuery = insertQuery + "'" + record.wedToTime_ + "', ";
		
		insertQuery = insertQuery + "'" + record.thuFromTime_ + "', ";
		insertQuery = insertQuery + "'" + record.thuToTime_ + "', ";
		
		insertQuery = insertQuery + "'" + record.friFromTime_ + "', ";
		insertQuery = insertQuery + "'" + record.friToTime_ + "', ";
		
		insertQuery = insertQuery + "'" + record.satFromTime_ + "', ";
		insertQuery = insertQuery + "'" + record.satToTime_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
	
}
