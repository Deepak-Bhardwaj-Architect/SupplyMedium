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
import core.common.CountryCodeData;
import db.utils.DBConnect;

/**
 * File: CountryCodeTable.java
 * 
 * Created on Mar 28, 2013 11:27:28 AM
 */

/*
 * Purpose: It is used to query the country_code table.
 */

public class CountryCodeTable
{

	private String tableName_;

	/*
	 * Method : BusinessCategoryTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public CountryCodeTable()
	{
		this.tableName_ = "sm_config_country_code";
	}

	/*
	 * Method : find( )
	 * 
	 * Input : countryName,CompanyEmailDomainData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the country code for given country name
	 */

	public int find( String countryName, CountryCodeData countryCodeData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );

		String errMsg = "";

		String str = "SELECT * FROM " + tableName_ + " WHERE country_name='" + countryName
		        + "'";

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
				countryCodeData.countryCode_ = rs.getString( "country_code" );

				countryCodeData.countryName_ = rs.getString( "country_name" );

				return 0;
			}
		}
		catch( SQLException ex )
		{
			errMsg = "Exception::CountryCodeTable:find-" + ex;

			errLogger.logMsg( errMsg );

			return -1; // failed
		}
		catch( Exception ex )
		{
			errMsg = "Exception::CountryCodeTable:find-" + ex;

			errLogger.logMsg( errMsg );

			return -1; // failed
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

		return 0; // success
	}

}
