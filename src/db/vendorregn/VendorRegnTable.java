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

import core.myconn.MyConnectionData;
import core.regn.CompanyRegnKey;
import core.vendorregn.VendorRegnData;
import db.myconn.MyConnectionRecord;
import db.myconn.MyConnectionTableUtils;
import db.utils.DBConnect;
import utils.ErrorLogger;
import utils.VendorRegnStatus;

/**
 * File:  VendorRegnTable.java 
 *
 * Created on May 21, 2013 3:55:32 PM
 */

/*
 * Class: VendorRegnTable
 * 
 * Purpose: This class is used to perform insert,
 * update, delete and find operations in vendor_registration
 * table of supply medium db
 */

public class VendorRegnTable
{
	private String tableName_;
	
	ErrorLogger errLogger_;
	
	/*Constructor*/
	
	public VendorRegnTable( )
	{
		tableName_ = "vendor_registration";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method: insert
	 * 
	 * Input: VendorRegnData
	 * 
	 * Return: int
	 * 
	 * Purpose: This method converts VendorRegnData,into VendorRegnRecord and
	 * inserts the value into vendor_registration table of supply medium db 
	 */
	
	public int insert( VendorRegnData vendorRegnData )
	{
		VendorRegnRecord record = createVendorRegnRecord( vendorRegnData );

		String query = formInsertQuery( record );

		Statement stmt = null;
		Connection con = null;

		int insertResult = 0;
		
		System.out.println( "Query = " + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			
			stmt = con.createStatement( );
			
			insertResult =stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			   
			if( insertResult > 0 ) 
			{
				ResultSet keys = stmt.getGeneratedKeys( );

				if( keys.next( ) )
				{
					vendorRegnData.vendorRegnId_ = keys.getInt( 1 );
				}
				
				
				return 0; // Successfully inserted
			}

			return -1;

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::VendorRegnTable.insert( ) - "
			        + ex );

			return -2;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::VendorRegnTable.insert( ) - "
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
	 * Method : insert( )
	 * 
	 * Input : vendorRegnData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check relation exist between given two companies. If exist return 1
	 * otherwise return 0. If any exception occur return negative value  
	 */

	public int isRelationExist( VendorRegnData vendorRegnData )
	{
		VendorRegnRecord record = createVendorRegnRecord( vendorRegnData );
		
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		query = "SELECT * FROM " + tableName_ + " WHERE (( regn_rel_key = '" + record.regnKey_ + "'" +
				"AND regn_rel_map = '"+record.regnMapKey_+"' ) OR ( regn_rel_key = '"+record.regnMapKey_+"'"+
				"AND regn_rel_map = '"+record.regnKey_+"')) AND status != '" + VendorRegnStatus.VRStatus.REJECTED.getValue( ) + "'";
		
		

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			
			ResultSet rs = stmt.executeQuery( query );
			
			if( rs.next( ) )
				return 1; // Connection relationship already exist
			else
				return 0; // Relationship not exist
			
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::MyConnectionTable.isRelationExist( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::MyConnectionTable.isRelationExist( ) - "
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
	 * Method: update
	 * 
	 * Input: VendorRegnData
	 * 
	 * Return: int
	 * 
	 * Purpose: This method converts VendorRegnData into
	 * VendorRegnRecord and updates the the vendor registration record
	 */
	
	public int update( VendorRegnData vendorRegnData )
	{
		VendorRegnRecord record = createVendorRegnRecord( vendorRegnData );

		String query = "UPDATE " + tableName_;
		query = query + " SET user_rel_key = '" + record.userKey_ + "', ";
		query = query + "company_level = '" + record.companyLevel_ + "', ";
		query = query + "business_contact_name = '" + record.businessContact_ + "', ";
		
		query = query + "business_tax_id = '" + record.businessTaxId_ + "', ";
		query = query + "naics_code = '" + record.NAICSCode_ + "', ";
		query = query + "w9tax_form_flag = '" + record.w9TaxFormFlag_ + "', ";
		
		query = query + "w9tax_form_path = '" + record.w9TaxFormPath_  + "', ";
		query = query + "business_size = '" + record.businessSize_ +  "', ";
		query = query + "business_classification = '" + record.businessClassification_ + "', ";
		
		query = query + "additional_details = '" + record.additionalDetails_ + "'";
				
		query = query + " WHERE (regn_rel_key ='" + record.regnKey_ + "' AND ";
		query = query + "regn_rel_map = '" + record.regnMapKey_ + "') OR ";
		query = query + "(regn_rel_key ='" + record.regnMapKey_ + "' AND ";
		query = query + "regn_rel_map = '" + record.regnKey_ + "')";

		System.out.println( "Query = " + query );

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
			errLogger_.logMsg( "Exception::VendorRegnTable.update() - " + ex );

			return -2;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::VendorRegnTable.update() - " + ex );

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
	 * Method: updateStatus
	 * 
	 * Input: VendorRegnData
	 * 
	 * Return: int
	 * 
	 * Purpose: This method converts VendorRegnData into
	 * VendorRegnRecord and updates the status of the vendor registration
	 */
	
	public int updateStatus( VendorRegnData vendorRegnData )
	{
		VendorRegnRecord record = createVendorRegnRecord( vendorRegnData );

		String query = "UPDATE " + tableName_;
		query = query + " SET status = '" + record.regnStatus_ + "'";
				
		query = query + " WHERE (regn_rel_key ='" + record.regnKey_ + "' AND ";
		query = query + "regn_rel_map = '" + record.regnMapKey_ + "') OR ";
		query = query + "(regn_rel_key ='" + record.regnMapKey_ + "' AND ";
		query = query + "regn_rel_map = '" + record.regnKey_ + "') ORDER BY created_timestamp DESC ";

		System.out.println( "Query = " + query );

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
			errLogger_.logMsg( "Exception::VendorRegnTable.update() - " + ex );

			return -2;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::VendorRegnTable.update() - " + ex );

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
	 * Method: findMyVendors
	 * 
	 * Input: VendorRegnData obj, List<VendorRegnData> obj (As ref)
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches given company key's 'my request'
	 * records whose registration status are in progress, then
	 * fills the result set in List<VendorRegnData> list.
	 */
	
	public int findMyVendors( VendorRegnData vendorRegnData, List<VendorRegnData> vendorRegnDataList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		query = "SELECT * FROM " + tableName_ + " WHERE " 
				+ "(regn_rel_key = '" + vendorRegnData.regnKey_.companyPhoneNo_ + "') AND "
				+ " (status != '" + VendorRegnStatus.VRStatus.ACCEPTED.getValue( ) + "' AND " 
				+ "status != '" + VendorRegnStatus.VRStatus.REJECTED.getValue( ) + "')";
				
		if( vendorRegnData.requestSenderType_ != null )
		{
			query = query + " AND "	+ "(request_sender_type='" + vendorRegnData.requestSenderType_ + "')";
		}

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				VendorRegnData data = new VendorRegnData( );
				
				data.vendorRegnId_ = rs.getLong( "vendor_regn_id" );
				
				data.regnKey_.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				data.vendorRegnKey_.companyPhoneNo_ = rs.getString( "regn_rel_map" );
				data.companyLevel_	= rs.getString( "company_level" );
				
				data.businessContact_ = rs.getString( "business_contact_name" );
				data.businessTaxId_ = rs.getString( "business_tax_id" );
				data.NAICSCode_ = rs.getString( "naics_code" );
				
				data.w9TaxFormFlag_ = rs.getInt( "w9tax_form_flag" );
				data.w9TaxFormPath_ = rs.getString( "w9tax_form_path" );
				data.businessSize_ = rs.getString( "business_size" );
				
				data.businessClassification_ = rs.getString( "business_classification" );
				data.additionalDetails_ = rs.getString( "additional_details" );
				data.regnStatus_ = rs.getString( "status" );
				
				data.requestSenderType_ = rs.getString( "request_sender_type" );
				data.createdTimestamp_  = rs.getTimestamp( "created_timestamp" );
				
				vendorRegnDataList.add( data );
				
				data = null;
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::VendorRegnTable.findMyVendors( CompanyRegnKey, List<VendorRegnData> ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::VendorRegnTable.findMyVendors( CompanyRegnKey, List<VendorRegnData> ) - "
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
	 * Method: findOthVendors
	 * 
	 * Input: VendorRegnData obj, List<VendorRegnData> obj (As ref)
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches given company key's 'Other vendors request'
	 * records whose registration status are in progress, then
	 * fills the result set in List<VendorRegnData> list.
	 */
	
	public int findOthVendors( VendorRegnData vendorRegnData, List<VendorRegnData> vendorRegnDataList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		

		query = "SELECT * FROM " + tableName_ + " WHERE " 
				+ "(regn_rel_map = '" + vendorRegnData.regnKey_.companyPhoneNo_ + "') AND "
				+ " (status != '" + VendorRegnStatus.VRStatus.ACCEPTED.getValue( ) + "' AND " 
				+ "status != '" + VendorRegnStatus.VRStatus.REJECTED.getValue( ) + "')";
		
		if( vendorRegnData.requestSenderType_ != null )
		{
			query = query + " AND "	+ "(request_sender_type='" + vendorRegnData.requestSenderType_ + "')";
		}
		
		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				VendorRegnData data = new VendorRegnData( );
				
				data.vendorRegnId_ = rs.getLong( "vendor_regn_id" );
				
				data.regnKey_.companyPhoneNo_ = rs.getString( "regn_rel_map" );
				data.vendorRegnKey_.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				data.companyLevel_	= rs.getString( "company_level" );
				
				data.businessContact_ = rs.getString( "business_contact_name" );
				data.businessTaxId_ = rs.getString( "business_tax_id" );
				data.NAICSCode_ = rs.getString( "naics_code" );
				
				data.w9TaxFormFlag_ = rs.getInt( "w9tax_form_flag" );
				data.w9TaxFormPath_ = rs.getString( "w9tax_form_path" );
				data.businessSize_ = rs.getString( "business_size" );
				
				data.businessClassification_ = rs.getString( "business_classification" );
				data.additionalDetails_ = rs.getString( "additional_details" );
				data.regnStatus_ = rs.getString( "status" );
				
				data.requestSenderType_ = rs.getString( "request_sender_type" );
				data.createdTimestamp_  = rs.getTimestamp( "created_timestamp" );
				
				vendorRegnDataList.add( data );
				
				data = null;
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::VendorRegnTable.findOthVendors( CompanyRegnKey, List<VendorRegnData> ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::VendorRegnTable.findOthVendors( CompanyRegnKey, List<VendorRegnData> ) - "
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
	 * Method: filter
	 * 
	 * Input: CompanyRegnKey obj, List<RegnData> obj (As ref)
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches all the vendors 
	 * whose registration status are not a one from 'Accepted' or 'Rejected', then
	 * fills the result set in List<RegnData> list.
	 */
	
	public int filter( CompanyRegnKey regnKey, List<CompanyRegnKey> regnKeyList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		query = "SELECT regn_rel_key, regn_rel_map FROM " + tableName_ + " WHERE (regn_rel_map = '" + regnKey.companyPhoneNo_ 
		        + "' OR regn_rel_key='"+regnKey.companyPhoneNo_ +"') AND "
				+ " ( status != '" + VendorRegnStatus.VRStatus.ACCEPTED.getValue( ) + "' AND status != '" + VendorRegnStatus.VRStatus.REJECTED.getValue( ) + "')";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				CompanyRegnKey key = new CompanyRegnKey( );

				if( regnKey.companyPhoneNo_.equals( rs.getString( "regn_rel_key" ) ))
				{
					key.companyPhoneNo_ = rs.getString( "regn_rel_map" );
				}
				else 
				{
					key.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				}
				
				regnKeyList.add( key );
				
				key = null;
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::VendorRegnTable.filter( CompanyRegnKey, List<RegnData> ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::VendorRegnTable.filter( CompanyRegnKey, List<RegnData> ) - "
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
	 * Method: delete
	 * 
	 * Input: VendorRegnData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method deletes the vendor registration record using 
	 * CompanyRegnKey and Vendor's CompanyRegnKey exist at VendorRegnData object
	 */
	
	public int delete( VendorRegnData vendorRegnData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE vendor_regn_id = '" + vendorRegnData.vendorRegnId_ + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) return 0;

			return -1;

		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::VendorRegnTable.delete( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::VendorRegnTable.delete( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

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
	 * Method : createVendorRegnRecord( )
	 * 
	 * Input : VendorRegnRecord obj
	 * 
	 * Return : VendorRegnRecord
	 * 
	 * Purpose: It converts vendorRegnData to VendorRegnRecord
	 */

	private VendorRegnRecord createVendorRegnRecord( 
	        VendorRegnData vendorRegnData )
	{
		VendorRegnRecord record = new VendorRegnRecord( );
		
		// set the values
		record.additionalDetails_		= vendorRegnData.additionalDetails_;
		record.businessClassification_	= vendorRegnData.businessClassification_;
		record.businessContact_			= vendorRegnData.businessContact_;
		
		record.businessSize_			= vendorRegnData.businessSize_;
		record.businessTaxId_			= vendorRegnData.businessTaxId_;
		record.companyLevel_			= vendorRegnData.companyLevel_;
		
		record.requestSenderType_		= vendorRegnData.requestSenderType_;
		
		record.NAICSCode_				= vendorRegnData.NAICSCode_;
		record.regnKey_					= vendorRegnData.regnKey_.companyPhoneNo_;
		record.regnMapKey_				= vendorRegnData.vendorRegnKey_.companyPhoneNo_;
		
		record.regnStatus_				= vendorRegnData.regnStatus_;
		record.w9TaxFormPath_			= vendorRegnData.w9TaxFormPath_;
		record.w9TaxFormFlag_			= vendorRegnData.w9TaxFormFlag_;
		
		return record;
	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : GroupPrivilegesRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using GroupPrivilegesRecord and
	 * returns as string
	 */

	private String formInsertQuery( VendorRegnRecord record )
	{
		
		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(regn_rel_key, regn_rel_map, user_rel_key, request_sender_type, company_level, ";
		insertValues = insertValues + "business_contact_name, business_tax_id, naics_code, ";
		insertValues = insertValues	+ "w9tax_form_flag, w9tax_form_path, business_size, ";
		insertValues = insertValues + "business_classification, additional_details, status)";
		
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.regnKey_ + "', ";
		insertQuery = insertQuery + "'" + record.regnMapKey_ + "', ";
		insertQuery = insertQuery + "'" + record.userKey_ + "', ";
		
		insertQuery = insertQuery + "'" + record.requestSenderType_ + "', ";
		
		insertQuery = insertQuery + "'" + record.companyLevel_ + "', ";
		insertQuery = insertQuery + "'" + record.businessContact_ + "', ";
		insertQuery = insertQuery + "'" + record.businessTaxId_ + "', ";
		
		insertQuery = insertQuery + "'" + record.NAICSCode_ + "', ";
		insertQuery = insertQuery + "'" + record.w9TaxFormFlag_ + "', ";
		insertQuery = insertQuery + "'" + record.w9TaxFormPath_ + "', ";
		
		insertQuery = insertQuery + "'" + record.businessSize_ + "', ";
		insertQuery = insertQuery + "'" + record.businessClassification_ + "', ";
		insertQuery = insertQuery + "'" + record.additionalDetails_ + "', ";
		
		insertQuery = insertQuery + "'" + record.regnStatus_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
}
