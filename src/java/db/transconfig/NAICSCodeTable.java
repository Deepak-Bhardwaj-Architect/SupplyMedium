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
package db.transconfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import utils.ErrorLogger;
import core.transconfig.NAICSCodeData;
import db.utils.DBConnect;

/**
 * File: NAICSCodeTable.java
 * 
 * Created on Jul 11, 2013 5:07:57 PM
 */

public class NAICSCodeTable
{
	private String tableName_;

	/*
	 * Method : NAICSCodeTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public NAICSCodeTable( )
	{
		this.tableName_ = "sm_config_NAICS";
	}

	/*
	 * Method : find( )
	 * 
	 * Input : Lit<NAICSCodeData> object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the NAICSCode list
	 */

	public int find( List<NAICSCodeData> NAICSCodeDataList )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );

		String errMsg = "";

		String str = "SELECT * FROM " + tableName_;

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
				NAICSCodeData data = new NAICSCodeData( );

				data.naicsCode_ = rs.getString( "naics_code" );
				data.naicsCodeDesc_ = rs.getString( "naics_description" );

				NAICSCodeDataList.add( data );

				data = null;
			}

			return 0;
		}
		catch( SQLException ex )
		{
			errMsg = "Exception::NAICSCodeTable.find( ) - " + ex;

			errLogger.logMsg( errMsg );

			return -1; // failed
		}
		catch( Exception ex )
		{
			errMsg = "Exception::NAICSCodeTable.find( ) - " + ex;

			errLogger.logMsg( errMsg );

			return -2; // failed
		}
		finally
		{
			try
			{
				if( rs != null )
				{
					rs.close( );
				}
				if( stmt != null )
				{
					stmt.close( );
				}
				if( con != null )
				{
					con.close( );
				}
			}
			catch( SQLException sQLException )
			{
			}
		}
	}

}
