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

package db.vendorregn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import core.regn.CompanyRegnKey;
import core.vendorregn.VendorRegnData;
import db.utils.DBConnect;
import utils.ErrorLogger;
import utils.ErrorMaster;

/**
 * File:  RegnVendorMapTable.java 
 *
 * Created on May 24, 2013 6:28:14 PM
 */

/*
 * Class: RegnVendorMapTable
 * 
 * Purpose: This class performs insert, delete operations of regn_vendor_mapper table
 */

public class RegnVendorMapTable{
    private static ErrorMaster errorMaster_ = null;


	ErrorLogger errLogger_;
	
	private String tableName_;
	
	/*Constructor*/
	
	public RegnVendorMapTable( )
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errLogger_ = ErrorLogger.instance( );
		
		tableName_ = "regn_vendor_mapper";
	}
	
	/*
	 * Method: insert
	 * 
	 * Input: VendorRegnData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method inserts a record into regn_vendor_mapper table
	 */
	
	public int insert( VendorRegnData vendorRegnData )
	{
		String insertValues = "(regn_rel_key, regn_rel_key_map)";
		
		String query = "INSERT INTO " + this.tableName_ + insertValues + " VALUES (";
		
		query = query + "'" + vendorRegnData.regnKey_ + "', ";
		query = query + "'" + vendorRegnData.vendorRegnKey_ + "')";
		
		errorMaster_.insert( "query="+query);

		Statement stmt = null;
		Connection con = null;

		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			insertResult = stmt.executeUpdate( query );

			if( insertResult > 0 ) return 0;

			return -1;

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::RegnVendorMap.insert( ) - "
			        + ex );

			return -2;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::RegnVendorMap.insert( ) - "
			        + ex );

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
	 * Method: delete
	 * 
	 * Input: VendorRegnData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method removes the record from regn_vendor_mapper table
	 */
	
	public int delete( VendorRegnData vendorRegnData )
	{
		return 0;
	}
	
	/*
	 * Method: find
	 * 
	 * Input: CompanyRegnKey obj, List<CompanyRegnKey> vendorRegnKeyList
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches list of vendor company list for given company
	 */
	
	public int find( CompanyRegnKey key, List<CompanyRegnKey> vendorRegnKeyList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		query = "SELECT * FROM " + tableName_ + " WHERE regn_rel_key = '" + key.companyPhoneNo_
		        + "' OR regn_rel_key_map = '" + key.companyPhoneNo_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				CompanyRegnKey regnKey = new CompanyRegnKey( );

				if( key.companyPhoneNo_.equals( rs.getString( "regn_rel_key" ) ))
				{
					regnKey.companyPhoneNo_ = rs.getString( "regn_rel_key_map" );
				}
				else 
				{
					regnKey.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				}
				
				vendorRegnKeyList.add( regnKey );
				
				errorMaster_.insert( " one record added ") ;
				
				regnKey = null;
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::RegnVendorMapTable.find( CompanyRegnKey, List<CompanyRegnKey> ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::RegnVendorMapTable.find( CompanyRegnKey, List<CompanyRegnKey> ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Exception
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
	 * Method: isExist
	 * 
	 * Input: CompanyRegnKey key, CompanyRegnKey vendorRegnKey
	 * 
	 * Return: int
	 * 
	 * Purpose: This method checks whether the given company and vendor company are
	 * already vendors
	 */
	
	public int isExist( CompanyRegnKey key, CompanyRegnKey vendorRegnKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		query = "SELECT * FROM " + tableName_ + " WHERE " +
				"(regn_rel_key = '" + key.companyPhoneNo_ + "' AND regn_rel_key_map = '" + vendorRegnKey.companyPhoneNo_ + "') OR "  
		        + "(regn_rel_key = '" + vendorRegnKey.companyPhoneNo_ + "' AND regn_rel_key_map = '" + key.companyPhoneNo_ + "')";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				return 0;
			}

			return -1; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::RegnVendorMapTable.isExist( CompanyRegnKey, CompanyRegnKey ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::RegnVendorMapTable.isExist( CompanyRegnKey, CompanyRegnKey ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Exception
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
	 * Input: CompanyRegnKey obj, List<CompanyRegnKey> vendorRegnKeyList
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches list of vendor company list for given company
	 */
	
	public int find( CompanyRegnKey key, Set<CompanyRegnKey> companyKeyList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		query = "SELECT * FROM " + tableName_ + " WHERE regn_rel_key = '" + key.companyPhoneNo_
		        + "' OR regn_rel_key_map = '" + key.companyPhoneNo_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				CompanyRegnKey regnKey = new CompanyRegnKey( );

				if( key.companyPhoneNo_.equals( rs.getString( "regn_rel_key" ) ))
				{
					regnKey.companyPhoneNo_ = rs.getString( "regn_rel_key_map" );
				}
				else 
				{
					regnKey.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				}
				
				companyKeyList.add( regnKey );
				
				errorMaster_.insert( " one record added ") ;
				
				regnKey = null;
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::RegnVendorMapTable.find( CompanyRegnKey, List<CompanyRegnKey> ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::RegnVendorMapTable.find( CompanyRegnKey, List<CompanyRegnKey> ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Exception
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
