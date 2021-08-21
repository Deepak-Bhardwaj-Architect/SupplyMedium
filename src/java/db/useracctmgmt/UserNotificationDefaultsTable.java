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

import core.useracctmgmt.NotificationSettingsData;
import db.utils.DBConnect;
import utils.ErrorLogger;
import utils.ErrorMaster;

/**
 * File:  UserNotificationDefaultsTable.java 
 *
 * Created on Apr 22, 2013 2:19:22 PM
 */


/*
 * Class  : UserNotificationDefaultsTable
 * 
 * Purpose: It is used to query the user_notifications_defaults table.
 * 
 */

public class UserNotificationDefaultsTable
{

	ErrorLogger errLogger_;
	
	private String tableName_;
	
	/*
	 * Constructor : UserNotificationDefaultsTable( ) 
	 * 
	 * Input : None
	 * 
	 * Purpose: it is used to initialize the tableName variable
	 */
	
	public UserNotificationDefaultsTable( )
	{
		this.tableName_ = "sm_config_user_notifications";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	
	/*
	 * Method : getNotificationDefaults( ) 
	 * 
	 * Input : NotificationSettingsData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: 
	 */
	
	public int getNotificationDefaults( NotificationSettingsData notifyData )
    {
		String query = "SELECT * FROM " + tableName_;
 ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "Query = " + query );

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				notifyData.workingHoursFlag_ 	= rs.getInt( "notify_workinghours_flag" );
				notifyData.nonWorkingHoursFlag_ = rs.getInt( "notify_nonworkinghours_flag" );
				notifyData.notifyWhMode_ 		= rs.getString( "notify_workinghours_mode" );
				notifyData.notifyNonWhMode_ 	= rs.getString( "notify_nonworkinghours_mode" );
				notifyData.notifyStopFlag_ 		= rs.getInt( "notify_stop_flag" );
				notifyData.notifyStopFromTime_ 	= rs.getTimestamp( "notify_stop_fromtime" );
				notifyData.notifyStopToTime_ 	= rs.getTimestamp( "notify_stop_totime" );
				
				return 0; // Success
			}
			else
			{
				return -1; // no record exists
			}
		}
		
		catch( SQLException ex ) // Sql exception
		{
			errLogger_.logMsg( "Exception::UserNotificationDefaultsTable." +
					"getNotificationDefaults() - "+ ex );
			return -2; 
		}
		catch (Exception ex) // General Exception
		{
			errLogger_.logMsg( "Exception::UserNotificationDefaultsTable." +
					"getNotificationDefaults() - "+ ex );
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
	
}
