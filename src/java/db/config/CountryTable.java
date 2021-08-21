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

import core.common.CountryData;

import db.utils.DBConnect;
import java.sql.DriverManager;

import utils.ErrorLogger;


/**
 * File:  CountryTable.java 
 *
 * Created on Jan 18, 2013 11:27:28 AM
 */

/*
 * Purpose: It is used to query the country table.
 */

public class CountryTable
{
	private String tableName_;

	/*
	 * Method : CountryTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public CountryTable()
	{
		this.tableName_ = "sm_config_countries";
	}

	/*
	 * Method : getAllCountries( )
	 * 
	 * Input : List<CountryData> countryDataArr
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the countries from supplymedium and
	 * assign to countryDataArr parameter so it is copied to caller method. 
	 */


	public int getAllCountries( List<CountryData> countryDataArr )
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
//                        Class.forName("com.mysql.jdbc.Driver");
//                        con=DriverManager.getConnection("jdbc:mysql://dbinstanceidentifier.cv4hkcizmqtn.us-west-1.rds.amazonaws.com:3306/databasename","masteruser","masterpass");
			stmt = con.createStatement( );
			rs = stmt.executeQuery( str );

			while( rs.next( ) )
			{
				CountryData countryObj = new CountryData( );

				countryObj.country_ = rs.getString( "country_name" );

				countryDataArr.add( countryObj );
				
				countryObj = null;
			}
		}
		catch( SQLException ex )
		{
			errMsg = "Exception::CountryTable:getAllCountries-"+ex;
			
			errLogger.logMsg(errMsg);
			
			return -1;
		}
		catch (Exception ex) 
		{
			errMsg = "Exception::CountryTable:getAllCountries-"+ex;
			
			errLogger.logMsg(errMsg);
			
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

		
		return 0;
	}
}
