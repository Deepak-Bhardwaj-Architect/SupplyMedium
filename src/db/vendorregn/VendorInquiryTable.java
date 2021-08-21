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

import core.vendorregn.VendorInquireData;
import db.utils.DBConnect;
import db.utils.DBDecode;
import db.utils.DBEncode;
import utils.ErrorLogger;

/**
 * File:  VendorInquiryTable.java 
 *
 * Created on May 25, 2013 3:16:58 PM
 */

/*
 * Class: VendorInquiryTable
 * 
 * Purpose: This method is used to add inquire details into 
 * vendor_inquire_details table for vendor registration
 */

public class VendorInquiryTable
{
	ErrorLogger errLogger_;
	private String tableName_;
	
	/*Constructor*/
	
	public VendorInquiryTable( )
	{
		errLogger_ = ErrorLogger.instance( );
		tableName_ = "vendor_inquire_details";
	}
	
	/*
	 * Method: Insert
	 * 
	 * Input: VendorInquireData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to insert vendor inquiry details
	 * into vendor_inquire_details table
	 */
	
	public int insert( VendorInquireData inquireData )
	{
		VendorInquiryRecord record = createVendorInquiryRecord( inquireData );

		String query = formInsertQuery( record );

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
			errLogger_.logMsg( "Exception::VendorInquiryTable.insert( ) - "
			        + ex );

			return -2;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::VendorInquiryTable.insert( ) - "
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
	 * Method: Insert
	 * 
	 * Input: Long vendorRegnId, List<VendorInquireData> inquireDataList
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to fetch VendorInquireData list for 
	 * given vendorRegnId
	 */
	
	public int find( long vendorRegnId, List<VendorInquireData> inquireDataList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		query = "SELECT * FROM " + tableName_ + " WHERE vendor_regn_id = '" + vendorRegnId
		        + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			boolean flag = false;
			
			DBDecode dbDecode = new DBDecode();
			
			while( rs.next( ) )
			{
				VendorInquireData data = new VendorInquireData( );
				
				if( flag == false )
				{
					data.regnKey_.companyPhoneNo_ = rs.getString( "inquire_from" ); 
					data.vendorRegnKey_.companyPhoneNo_ = rs.getString( "inquire_to" );
					flag = true;
				}
				else 
				{
					data.regnKey_.companyPhoneNo_ = rs.getString( "inquire_to" );
					data.vendorRegnKey_.companyPhoneNo_ = rs.getString( "inquire_from" );
					flag = false;
				}
				
				data.vendorRegnId_ = rs.getLong( "vendor_regn_id" );
				
				data.createdTs_ = rs.getTimestamp( "created_timestamp" );
				data.inquireDetails_ = dbDecode.decode( rs.getString( "inquire_details" ) );
				
				inquireDataList.add( data );
				
				data = null;
			}
			
			 dbDecode = null;

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::VendorInquiryTable.find( long, List<VendorInquireData> ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::VendorInquiryTable.find( long, List<VendorInquireData> ) - "
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
	 * Method : createVendorInquiryRecord( )
	 * 
	 * Input : VendorInquireData obj
	 * 
	 * Return : VendorInquiryRecord
	 * 
	 * Purpose: It converts VendorInquireData to VendorInquiryRecord
	 */

	private VendorInquiryRecord createVendorInquiryRecord( 
			VendorInquireData vendorInquireData )
	{
		VendorInquiryRecord record = new VendorInquiryRecord( );
		
		DBEncode dbEncode = new DBEncode();
		
		// set the values
		record.inquireDetails_ = dbEncode.encode( vendorInquireData.inquireDetails_ );
		record.regnRelKey_	= vendorInquireData.regnKey_.companyPhoneNo_;
		record.regnRelKeyMap_ = vendorInquireData.vendorRegnKey_.companyPhoneNo_;
		record.vendorRegnId_ = vendorInquireData.vendorRegnId_;
		
		dbEncode = null;
		
		return record;
	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : VendorInquiryRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using VendorInquiryRecord and
	 * returns as string
	 */

	private String formInsertQuery( VendorInquiryRecord record )
	{
		
		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(vendor_regn_id, inquire_from, inquire_to, inquire_details)";
		
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + record.vendorRegnId_ + ", ";
		insertQuery = insertQuery + "'" + record.regnRelKey_ + "', ";
		insertQuery = insertQuery + "'" + record.regnRelKeyMap_ + "', ";
		
		insertQuery = insertQuery + "'" + record.inquireDetails_+ "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
}

