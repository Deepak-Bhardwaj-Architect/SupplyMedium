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
import java.util.List;

import core.common.StateData;

import db.utils.DBConnect;

import utils.ErrorLogger;

/**
 * File:  StateTable.java 
 *
 * Created on Jan 18, 2013 11:27:28 AM
 */

/*
 * Purpose: It is used to query the states table.
 */


public class StateTable
{
	private String tableName_;
	
	/*
	 * Method : StateTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public StateTable()
	{
		this.tableName_ = "sm_config_states";
	}
	
	/*
	 * Method : getAllStates( )
	 * 
	 * Input : List<StateData> stateDataArr , country name
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the states for given country and
	 * assign to stateDataArr parameter so it is copied to caller method. 
	 */

	public int getAllStates( String country , List<StateData> stateDataArr )
	{
		StateData stateObj = null;

		String query = "SELECT * FROM " + tableName_ + " WHERE country_name='" + country
		        + "'";

		System.out.println( "State Query=" + query );

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try
		{

			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				stateObj = new StateData( );

				stateObj.state_ = rs.getString( "state_name" );

				stateDataArr.add( stateObj );
			}
			
			return 0;
		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			
			String msg = "Exception :: StateTable : getAllStatesForCountry - "+ex;
			
			errLogger.logMsg(msg);
			
			return -1;
		}
		finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
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
	 * Method : getStateIdForState( )
	 * 
	 * Input : statename and country id
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the state for given country id and state name
	 */
	
	public long getStateIdForState( String stateName, long countryId )
	{
		String str = "SELECT state_id FROM " + tableName_;
		str = str + " WHERE state_name = '" + stateName + "' AND ";
		str = str + "country_id = '" + countryId + "'";

		long stateId = 0;

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try
		{

			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			rs = stmt.executeQuery( str );

			while( rs.next( ) )
			{
				stateId = rs.getLong( "state_id" );
			}
			return stateId;
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "StateTable : getStateIdForState :: SQL Error " + ex );
			return stateId;
		}
		finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
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
