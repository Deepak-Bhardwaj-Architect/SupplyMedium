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

import db.utils.DBConnect;

import utils.ErrorLogger;
import utils.IntHolder;

/**
 * File:  PricingOptionTable.java 
 *
 * Created on Feb 18, 2013 11:27:28 AM
 */


public class PricingOptionTable
{
	private String tableName_;
	
	/*
	 * Method : PricingOptionTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used initialize the table name
	 */
	
	public PricingOptionTable()
	{
		this.tableName_ = "sm_config_pricing_option";
	}

	/*
	 * Method : getMaxMembers( )
	 * 
	 * Input : pricingOptionKey
	 * 
	 * Return : long
	 * 
	 * Purpose: It is used to get the max employee for given prcing option key
	 */
	
	public long getMaxMembers( String pricingOptionKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger_ = ErrorLogger.instance( );
		
		String errMsg  = "";

		long membersCount = 0;

		query = "SELECT max_employee FROM " + tableName_ + " WHERE pricing_option_key = '"
		        + pricingOptionKey + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				membersCount = rs.getInt( "max_employee" );
			}

			return membersCount; // Success

		}
		catch( SQLException ex )
		{
			errMsg = "Exception::PricingOption table:getMaxMembers-" + ex;

			errLogger_.logMsg( errMsg );

			return membersCount; // SqlException
		}
		catch (Exception ex)
		{
			errMsg = "Exception::PricingOption table:getMaxMembers-" + ex;

			errLogger_.logMsg( errMsg );

			return membersCount; // general exception
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
	
	/**
	 * 
	 * @author Anbazhagan
	 * 
	 * @Date : Mar 13, 2013 11:18:05 AM
	 * 
	 * @Return : long
	 * 
	 * @Purpose : Get the Max Transaction for the Given Company
	 * 
	 * @param pricingOptionKey
	 * @return
	 * 
	 */
	public int getMaxTransaction( String pricingOptionKey, IntHolder maxTrans )
	{
		Statement preStmt = null;
		Connection con = null;
		String query = null;
		ResultSet rs = null;

		query = "SELECT max_transaction FROM " + tableName_ + " WHERE pricing_option_key = '" + pricingOptionKey + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			preStmt = con.createStatement( );

			rs = preStmt.executeQuery( query );

			while( rs.next( ) )
			{
				maxTrans.value = rs.getInt( "max_transaction" );
			}

			return 0; // Success

		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "PricingOption table : getMaxTransaction :: SQL Error " + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		finally
		{
			try
			{
				if( rs != null )
				{
					rs.close( );
				}

				if( preStmt != null )
				{
					preStmt.close( );
				}

				if( con != null )
				{
					con.close( );
				}

			}
			catch( SQLException sqlExp )
			{
			}
		}

	}

	/*
	 * Method : getMaxSpace( )
	 * 
	 * Input : pricingOptionKey
	 * 
	 * Return : long
	 * 
	 * Purpose: It is used to get the max disk space allocated for given prcing option key
	 */
	
	public double getMaxSpace( String pricingOptionKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger_ = ErrorLogger.instance( );
		
		String errMsg  = "";

		double diskQuota = 0;

		query = "SELECT disk_quota FROM " + tableName_ + " WHERE pricing_option_key = '"
		        + pricingOptionKey + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				diskQuota = rs.getInt( "disk_quota" );
			}

			return diskQuota; // Success

		}
		catch( SQLException ex )
		{
			errMsg = "Exception::PricingOptionTable.getMaxSpace( )  - " + ex;

			errLogger_.logMsg( errMsg );

			return diskQuota; // SqlException
		}
		catch (Exception ex)
		{
			errMsg = "Exception::PricingOption.getMaxSpace( ) - " + ex;

			errLogger_.logMsg( errMsg );

			return diskQuota; // general exception
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
