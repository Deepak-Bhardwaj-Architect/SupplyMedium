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

import core.regn.UserProfileKey;
import core.useracctmgmt.NotificationSettingsData;

import db.utils.DBConnect;
import utils.ErrorLogger;


/**
 * File:  UserNotificationTable.java 
 *
 * Created on Apr 22, 2013 2:17:40 PM
 */

/*
 * Class: UserNotificationTable
 *
 * Purpose: This class is used to insert, update, delete and find
 * the user notification records from user_notification_table
 * 
 */

public class UserNotificationTable
{

	ErrorLogger errLogger_;
	
	private String tableName_;
	
	/*
	 * Method: UserNotificatoinTable( ) - Constructor
	 * 
	 * Purpose: To instantiate errorlogger and table name
	 */
	
	
	public UserNotificationTable( )
    {
	    this.tableName_ = "user_notifications";
	    
	    errLogger_ = ErrorLogger.instance( );
    }
	
	
	/*
	 * Method: insert( )
	 * 
	 * Input: NotificationSettingsData obj
	 * 
	 * Output: int
	 * 
	 * Purpose: To convert the NotificationSettingsData into UserNotificationRecord object
	 * and then insert the values into user_notification_table
	 * 
	 */

	public int insert( NotificationSettingsData notifyData )
    {
		UserNotificationRecord record = createNotificationRecord( notifyData );

		// Form the insertQuery using the UserLoginRecord object.
		String query = formInsertQuery( record );

		Statement stmt = null;
		Connection con = null;

		System.out.println( "Query = " + query );
		
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
			errLogger_.logMsg( "Exception::UserNotificationTable.insert() - " + ex );
			
			return -3;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger_.logMsg( "Exception::UserNotificationTable.insert() - " + ex );
			
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
	 * Input: NotificationSettingsData
	 * 
	 * Output: int
	 * 
	 * Purpose: To update the user_notification table with the
	 * updated values from NotificationSettingsData
	 * 
	 */
	
	public int update( NotificationSettingsData notifyData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		UserNotificationRecord record = createNotificationRecord( notifyData );
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "UPDATE " + tableName_;
		query = query + " SET notify_email = '" + record.notifyEmail_ + "', ";
		query = query + "notify_mobile = '" + record.notifyMobile_ + "', ";
		query = query + "notify_workinghours_flag = " + record.notifyWorkingHoursFlag_ + ", ";
		query = query + "notify_nonworkinghours_flag = " + record.notifyNonWorkingHoursFlag_ + ", ";
		query = query + "notify_workinghours_mode = '" + record.notifyWorkingHoursMode_ + "', ";
		query = query + "notify_nonworkinghours_mode = '" + record.notifyNonWorkingHoursMode_ + "', ";
		query = query + "notify_stop_flag = " + record.notifyStopFlag_ + ", ";
		query = query + "notify_stop_fromtime = '" + record.notifyStopFromTime_ + "', ";
		query = query + "notify_stop_totime = '" + record.notifyStopToTime_ + "'";
		
		query = query + " WHERE user_rel_key ='" + record.userRelKey_ + "'";

		System.out.println( "Query=" + query );

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
			errLogger.logMsg( "Exception::UserNotificationTable.update()-"+ex );

			return -2; 
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::UserNotificationTable.update()-"+ex );

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
	 * Purpose: It is used to delete the notifications settings from
	 * user_notification table for given UserProfileKey object
	 */

	public int delete( UserProfileKey userKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "DELETE FROM " + tableName_ + " WHERE user_rel_key = '" + userKey.email_ + "'";

		System.out.println( "Query=" + query );

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
			errLogger.logMsg( "Exception::UserNotificationTable.delete() - " + ex );

			return -2;
		}
		catch( Exception ex )  // General Exception
		{
			errLogger.logMsg( "Exception::UserNotificationTable.delete() - " + ex );

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
	 * Input: NotificationSettingsData object (As ref)
	 * 
	 * Output: int
	 * 
	 * Purpose: This method fetches the Notification settings data for
	 * given UserProfileKey object and fills the NotificationSettingsData object
	 * which is passed as reference
	 * 
	 */
	
	public int find( NotificationSettingsData notifyData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE user_rel_key = '"
		        + notifyData.userProfileKey_.email_ + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				notifyData.notifyEmail_ = rs.getString( "notify_email" );
				notifyData.notifyMobile_ = rs.getString( "notify_mobile" );
				notifyData.workingHoursFlag_ = rs.getInt( "notify_workinghours_flag" );
				notifyData.nonWorkingHoursFlag_ = rs.getInt( "notify_nonworkinghours_flag" );
				notifyData.notifyWhMode_ = rs.getString( "notify_workinghours_mode" );
				notifyData.notifyNonWhMode_ = rs.getString( "notify_nonworkinghours_mode" );
				notifyData.notifyStopFlag_ = rs.getInt( "notify_stop_flag" );
				notifyData.notifyStopFromTime_ = rs.getTimestamp( "notify_stop_fromtime" );
				
				System.out.println( "from time"+notifyData.notifyStopFromTime_);
				notifyData.notifyStopToTime_ = rs.getTimestamp( "notify_stop_totime" );
				System.out.println( " to time"+notifyData.notifyStopToTime_ );
				return 0; // Success
			}

			return -1; // No record for email
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::UserNotificationTable.find() - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch (Exception e) 
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::UserNotificationTable.find() - "
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
	 * Method : createNotificationRecord( )
	 * 
	 * Input : NotificationSettingsData obj
	 * 
	 * Return : UserNotificationRecord obj
	 * 
	 * Purpose: It converts NotificationSettingsData object to UserNotificationRecord object
	 */
	
	private UserNotificationRecord createNotificationRecord( NotificationSettingsData notifyData )
	{

		UserNotificationRecord record = new UserNotificationRecord( );

		// set the values
		record.notifyEmail_ = notifyData.userProfileKey_.email_;
		record.notifyMobile_ = notifyData.notifyMobile_;
		record.notifyNonWorkingHoursFlag_ = notifyData.nonWorkingHoursFlag_;
		
		record.notifyNonWorkingHoursMode_ = notifyData.notifyNonWhMode_;
		record.notifyStopFlag_ = notifyData.notifyStopFlag_;
		record.notifyStopFromTime_ = notifyData.notifyStopFromTime_;
		
		record.notifyStopToTime_ = notifyData.notifyStopToTime_;
		record.notifyWorkingHoursFlag_ = notifyData.workingHoursFlag_;
		record.notifyWorkingHoursMode_ = notifyData.notifyWhMode_;
		
		record.userRelKey_ = notifyData.userProfileKey_.email_;
		
		return record;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : UserNotificationRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using UserNotificationRecord and
	 * returns as string
	 */
	
	private String formInsertQuery( UserNotificationRecord record )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(user_rel_key, notify_email, notify_mobile, notify_workinghours_flag, ";
		insertValues = insertValues + "notify_nonworkinghours_flag, notify_workinghours_mode, ";
		insertValues = insertValues + "notify_nonworkinghours_mode, ";
		insertValues = insertValues + "notify_stop_flag, notify_stop_fromtime, notify_stop_totime)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "('";

		insertQuery = insertQuery + record.userRelKey_ + "', ";
		insertQuery = insertQuery + "'" + record.notifyEmail_ + "', ";
		insertQuery = insertQuery + "'" + record.notifyMobile_ + "', ";
		insertQuery = insertQuery + record.notifyWorkingHoursFlag_ + ", ";
		insertQuery = insertQuery + record.notifyNonWorkingHoursFlag_ + ", ";
		insertQuery = insertQuery + "'" + record.notifyWorkingHoursMode_ + "', ";
		insertQuery = insertQuery + "'" + record.notifyNonWorkingHoursMode_ + "', ";
		insertQuery = insertQuery + record.notifyStopFlag_ + ", ";
		insertQuery = insertQuery + "'" + record.notifyStopFromTime_ + "', ";
		insertQuery = insertQuery + "'" + record.notifyStopToTime_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
	
}
