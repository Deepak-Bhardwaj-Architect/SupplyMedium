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

package db.regn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import utils.ErrorLogger;
import core.regn.CCMapperData;
import core.regn.CompanyRegnKey;
import db.utils.DBConnect;


/**
 * File:  StateTable.java 
 *
 * Created on Jan 18, 2013 11:27:28 AM
 */

/*
 * Class  : CCMapperTable
 * 
 * Purpose: It is used to query the country_company_mapper table.
 * 
 */

public class CCMapperTable
{
	private String tableName_;

	/*
	 * Method : CCMapperTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public CCMapperTable()
	{
		this.tableName_ = "country_company_mapper";
	}

	/*
	 * Method : insertCCMapper( )
	 * 
	 * Input : CCMapperData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the CCMapperData values into
	 * country_company_mapper table
	 */

	public int insertCCMapper( CCMapperData ccMapperData )
	{

		int insertResult = 0;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errMsg = "";

		CCMapperRecord ccMapperRecord = createCCMapperRecord( ccMapperData );

		boolean isRecordExist = isCCMapperExist( ccMapperRecord );

		System.out.println( "isrecordexist=" + isRecordExist );

		if( isRecordExist )
		{
			return insertResult;
		}

		String query = formInsertQuery( ccMapperRecord );

		System.out.println( "CCMapper inser query=" + query );

		Statement stmt = null;
		Connection con = null;

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
			errMsg = "Exception::CCMapperTable:insertCCMapper-"+ex;
			
			errLogger.logMsg( errMsg );
			
			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errMsg = "Exception::CCMapperTable:insertCCMapper-"+ex;
			
			errLogger.logMsg( errMsg );
			
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
	 * Method : isCCMapperExist( )
	 * 
	 * Input : CCMapperRecord obj
	 * 
	 * Return : boolean
	 * 
	 * Purpose: It is used to check if there exists CCMapperRecord for given
	 * regnKey and country name
	 */

	private boolean isCCMapperExist( CCMapperRecord ccMapperRecord )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		boolean result = false;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		String errMsg = "";

		query = "SELECT 1 FROM " + tableName_;
		query = query + " WHERE regn_key = '" + ccMapperRecord.regnKey_;
		query = query + "' AND country_name = '" + ccMapperRecord.countryname_ + "'";
		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			if( !rs.next( ) )
			{
				// ResultSet is empty

				result = false; // CCMapper record no not exists in DB.
			}
			else
			{
				result = true; // CCMapper record already exists in DB
			}

			return result;
		}

		catch( SQLException ex ) // Sql Exception
		{
			errMsg = "Exception::CCMapperTable:insertCCMapper-"+ex;
			
			errLogger.logMsg( errMsg );
			
			return result;
		}
		catch( Exception ex ) // General Exception
		{
			errMsg = "Exception::CCMapperTable:insertCCMapper-"+ex;
			
			errLogger.logMsg( errMsg );
			
			return result;
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
	 * Method : getAllCompanies( )
	 * 
	 * Input : String country, List<CCMapperData> obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the CCMapperData values from
	 * country_company_mapper table
	 */

	public int getAllCompanies( String country,
	        List<CCMapperData> ccMapperDataList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		ErrorLogger errLogger = ErrorLogger.instance( );
		String errMsg = "";
		
		// Need to change this to mapper table based query.
		
		query = "SELECT * from " + tableName_ + " WHERE country_name = '" + country + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			CCMapperData ccMapperData = null;

			while( rs.next( ) )
			{
				ccMapperData = new CCMapperData( );

				ccMapperData.companyname_ = rs.getString( "company_name" );

				CompanyRegnKey companyRegnKey = new CompanyRegnKey( );
				companyRegnKey.companyPhoneNo_ = rs.getString( "regn_key" );
				ccMapperData.companyRegnKey_ = companyRegnKey;

				ccMapperDataList.add( ccMapperData );
			}

			return 0;

		}
		catch( SQLException ex ) // Sql Exception
		{
			errMsg = "Exception::CCMapperTable:insertCCMapper-"+ex;
			
			errLogger.logMsg( errMsg );
			
			return -1;
		}
		catch( Exception ex ) // General Exception
		{
			errMsg = "Exception::CCMapperTable:insertCCMapper-"+ex;
			
			errLogger.logMsg( errMsg );
			
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
	 * Method : update( )
	 * 
	 * Input : CompanyRegnKey obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the ccmapper record
	 */

	public int update( CCMapperData ccMapperData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int result = 0;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		CCMapperRecord ccMapperRecord = createCCMapperRecord( ccMapperData );

		query = "UPDATE " + tableName_ + " SET company_name ='"+ccMapperRecord.companyname_+"', " +
				" country_name ='"+ccMapperRecord.countryname_+"' WHERE regn_key='"
		        + ccMapperRecord.regnKey_ + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			result = stmt.executeUpdate( query );
			 return 0;
		}
		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::CCMapperTabl:update-" + ex );

			return -1;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::CCMapperTabl:update-" + ex );

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
	 * Method : createCCMapperRecord( )
	 * 
	 * Input : CCMapperData obj
	 * 
	 * Return : CCMapperRecord
	 * 
	 * Purpose: It converts CCMapperData to CCMapperRecord
	 */

	private CCMapperRecord createCCMapperRecord( CCMapperData ccMapperData )
	{
		// Form the record using the regnDataObj and return it

		CCMapperRecord ccMapperRecord = new CCMapperRecord( );

		// set the values
		ccMapperRecord.regnKey_ = ccMapperData.companyRegnKey_.companyPhoneNo_;

		ccMapperRecord.companyname_ = ccMapperData.companyname_;

		ccMapperRecord.countryname_ = ccMapperData.countryname_;

		return ccMapperRecord;
	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : CCMapperRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using CCMapperRecord and returns as
	 * string
	 */

	private String formInsertQuery( CCMapperRecord ccMapperRecord )
	{

		// form the query

		System.out.println( "form insert query" );

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(regn_key, company_name, country_name)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + ccMapperRecord.regnKey_ + "', ";
		insertQuery = insertQuery + "'" + ccMapperRecord.companyname_ + "', ";
		insertQuery = insertQuery + "'" + ccMapperRecord.countryname_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
}
