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
package db.privileges;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.omg.CORBA.LongHolder;

import utils.ErrorLogger;
import utils.IntHolder;
import db.utils.DBConnect;

/**
 * File:  PrivilegesDefaultsTable.java 
 *
 * Created on Mar 20, 2013 2:29:06 PM
 */


/*
 * Class  : PrivilegesDefaultsTable
 * 
 * Purpose: It is used to query the privileges_defaults table.
 * 
 */

public class PrivilegesDefaultsTable
{
	private String tableName_;

	/*
	 * Method : PrivilegesDefaultsTable( ) - constructor
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public PrivilegesDefaultsTable()
	{
		this.tableName_ = "sm_config_privileges";
	}

	/*
	 * Method : getDeptDefaults( ) 
	 * 
	 * Input : IntHolder object
	 * 
	 * Return :int
	 * 
	 * Purpose: It get the default department privileges value.
	 */

	public int getDeptDefaults( IntHolder deptPrivileges )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errorMessage= "";

		query = "SELECT dept_privileges FROM " + tableName_;

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				deptPrivileges.value = rs.getInt( "dept_privileges" );
			}

			return 0;
		}
		catch( SQLException ex ) // SqlException
		{
			
			errorMessage = "Exception::PrivilegesDefaultsTable:getDeptDefaults-"
			        + ex;

			errLogger.logMsg( errorMessage );

			return -1; 
		}
		catch( Exception ex )  // General Exception
		{
			
			errorMessage = "Exception::PrivilegesDefaultsTable:getDeptDefaults-"
			        + ex;

			errLogger.logMsg( errorMessage );

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
	 * Method : getGroupDefaults( ) 
	 * 
	 * Input : IntHolder object
	 * 
	 * Return :int
	 * 
	 * Purpose: It get the default group privileges value.
	 */

	public int getGroupDefaults( LongHolder groupPrivileges )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errorMessage= "";

		query = "SELECT group_privileges FROM " + tableName_;

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				groupPrivileges.value = rs.getLong( "group_privileges" );
			}

			return 0;
		}
		catch( SQLException ex ) // SqlException
		{
			
			errorMessage = "Exception::PrivilegesDefaultsTable:getGroupDefaults-"
			        + ex;

			errLogger.logMsg( errorMessage );

			return -1; 
		}
		catch( Exception ex )  // General Exception
		{
			
			errorMessage = "Exception::PrivilegesDefaultsTable:getGroupDefaults-"
			        + ex;

			errLogger.logMsg( errorMessage );

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
	 * Method : getUserDefaults( ) 
	 * 
	 * Input : IntHolder object
	 * 
	 * Return :int
	 * 
	 * Purpose: It get the default user privileges value.
	 */

	public int getUserDefaults( LongHolder userPrivileges )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errorMessage= "";

		query = "SELECT user_privileges FROM " + tableName_;

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				userPrivileges.value = rs.getLong( "user_privileges" );
			}

			return 0;
		}
		catch( SQLException ex ) // SqlException
		{
			
			errorMessage = "Exception::PrivilegesDefaultsTable:getUserDefaults-"
			        + ex;

			errLogger.logMsg( errorMessage );

			return -1; 
		}
		catch( Exception ex )  // General Exception
		{
			
			errorMessage = "Exception::PrivilegesDefaultsTable:getUserDefaults-"
			        + ex;

			errLogger.logMsg( errorMessage );

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
}
