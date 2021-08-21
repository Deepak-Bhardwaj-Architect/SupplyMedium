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
import core.useracctmgmt.WorkingHoursData;
import db.utils.DBConnect;

/**
 * File:  UserWorkingHoursDefaultsTable.java 
 *
 * Created on Apr 22, 2013 2:20:03 PM
 */
/*
 * Class  : UserWorkingHoursDefaultsTable
 * 
 * Purpose: It is used to query the user_workinghours_defaults table.
 * 
 */

public class UserWorkingHoursDefaultsTable
{

	ErrorLogger errLogger_;
	
	private String tableName_;
	
	/*
	 * Constructor : UserWorkingHoursDefaultsTable( ) 
	 * 
	 * Input : None
	 * 
	 * Purpose: it is used to initialize the tableName variable
	 */
	
	public UserWorkingHoursDefaultsTable( )
	{
		this.tableName_ = "sm_config_user_workinghours";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	
	/*
	 * Method : getWorkingHoursDefaults( ) 
	 * 
	 * Input : WorkingHoursData object
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: This method is used to 
	 */
	
	public int getWorkingHoursDefaults( WorkingHoursData workingHrsData )
    {
		String query = "SELECT * FROM " + tableName_;

		System.out.println( "Query = " + query );

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
			else
			{
				return -1; // no record exists
			}
		}
		
		catch( SQLException ex ) // Sql exception
		{
			errLogger_.logMsg( "Exception::UserWorkingHoursDefaultsTable." +
					"getWorkingHoursDefaults() - "+ ex );
			return -2; 
		}
		catch (Exception ex) // General Exception
		{
			errLogger_.logMsg( "Exception::UserWorkingHoursDefaultsTable." +
					"getWorkingHoursDefaults() - "+ ex );
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



 