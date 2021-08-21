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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import utils.CompanyStatus;
import utils.CompanyType;
import utils.ErrorLogger;
import utils.StringHolder;

import db.utils.DBConnect;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


import core.common.RVendorSearchData;
import core.regn.*;
import core.vendorregn.NRVendorSearchData;
import core.vendorregn.VendorRegnData;
import utils.ErrorMaster;

/**
 * File: CompanyRegnTable.java
 * 
 * Created on Jan 5, 2013 3:29:51 PM
 */

/*
 * Class  : CompanyRegnTable
 * 
 * Purpose: It is used to query the company_registration table.
 * 
 */

public class CompanyRegnTable
{
private static ErrorMaster errorMaster_ = null;


	private String tableName_;
	
	private CompanyRegnTableUtils	regUtils_;

	/*
	 * Method : CompanyRegnTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public CompanyRegnTable()
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = " company_registration ";
		
		regUtils_ = new CompanyRegnTableUtils( );
		regUtils_.tableName_ = tableName_;

	}

	/*
	 * Method : insert( )
	 * 
	 * Input : RegnData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the RegnData values into
	 * company_registration table
	 */

	public int insert( RegnData regnDataObj )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		// Here the regnDataObj will come in as the parameter,

		// Create CompanyRegnRecord using regnDataObj
		CompanyRegnRecord companyRecObj = createCompanyRegnRecord( regnDataObj );

		// Form the insertQuery using the CompanyRegnRecord object.
		String query = formInsertQuery( companyRecObj );

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
		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:insertCompany-" + ex );

			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:insertCompany-" + ex );

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
	 * Method : isPhoneNoExist( )
	 * 
	 * Input : RegnData obj
	 * 
	 * Return : boolean
	 * 
	 * Purpose: It is used to check whether the given phoneno exists on
	 * company_registration table
	 */

	public boolean isPhoneNoExist( String phoneNo )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		boolean result = false;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "SELECT 1 FROM " + tableName_;
		query = query + " WHERE company_phoneno = '" + phoneNo + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			if( !rs.next( ) )
			{
				// ResultSet is empty

				result = false; // Phone no not exists in DB. So it is valid
				                // phone number
			}
			else
			{
				result = true; // Phone already exists in DB
			}

			return result;
		}

		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:isPhoneNoExist-" + ex );
			
			return result;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:isPhoneNoExist-" + ex );
			
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
	 * Method : isPhoneNoExist( )
	 * 
	 * Input : RegnData obj
	 * 
	 * Return : boolean
	 * 
	 * Purpose: It is used to check whether the given phoneno exists on
	 * company_registration table
	 */

	public boolean isCompanyIdExist( String companyId )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		boolean result = false;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "SELECT 1 FROM " + tableName_;
		query = query + " WHERE company_id = '" + companyId + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			if( !rs.next( ) )
			{
				// ResultSet is empty

				result = false; // Phone no not exists in DB. So it is valid
				                // phone number
			}
			else
			{
				result = true; // Phone already exists in DB
			}

			return result;
		}

		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:isCompanyIdExist-" + ex );
			
			return result;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:isCompanyIdExist-" + ex );
			
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
	 * Method : getCompany( )
	 * 
	 * Input : String uuid
	 * 
	 * Return : List<RegnData>
	 * 
	 * Purpose: It is used to get list of company RegnData for given uuid
	 */

	public List<RegnData> getCompany( String uuid )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		List<RegnData> regnDataList = new ArrayList<RegnData>( );

		query = "SELECT * FROM " + tableName_ + " WHERE uuid = '" + uuid + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			
			ResultSet rs = stmt.executeQuery( query );

			List<CompanyRegnRecord> companyRegnRecordList = getCompanyRecordList( rs );

			regnDataList = getRegnDataList( companyRegnRecordList );

			return regnDataList;
		}

		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getCompany-" + ex );
			
			return regnDataList;
		}
		catch( Exception ex )  // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getCompany-" + ex );
			
			return regnDataList;
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
	 * Method : activateCompany( )
	 * 
	 * Input : CompanyRegnKey obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to activate the company status from pending to active
	 */

	public int activateCompany( CompanyRegnKey companyRegnKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int result = 0;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "UPDATE " + tableName_ + " SET company_status='Active' WHERE regn_key='"
		        + companyRegnKey.companyPhoneNo_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			result = stmt.executeUpdate( query );
			if( result > 0 ) return 0;

			return -1;

		}
		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:activateCompany-" + ex );

			return -1;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:activateCompany-" + ex );

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
	 * Method : deleteCompanyRegistration( )
	 * 
	 * Input : String companyRegnKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete a record from company_registration table
	 * using CompanyRegnKey
	 */

	public int delete( String companyRegnKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "DELETE FROM " + tableName_ + " WHERE company_phoneno = '" + companyRegnKey
		        + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) return 0;

			return -1;

		}
		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:delete-"+ex );

			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:delete-"+ex );

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
	 * Method : getCompany( )
	 * 
	 * Input : String key, List<RegnData>
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get List<RegnData> from
	 * company_registration table using CompanyRegnKey
	 */

	public int getCompany( String key, List<RegnData> companyRegnDataList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		// List<CompanyRegnRecord> companyRegnRecObjList = new
		// ArrayList<CompanyRegnRecord>();

		query = "SELECT * FROM " + tableName_ + " WHERE company_phoneno = '" + key + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			List<CompanyRegnRecord>companyRegnRecList = getCompanyRecordList( rs );
			
			companyRegnDataList = getRegnDataList( companyRegnRecList );

			return 0; // Success
		}

		catch( SQLException ex )  // SqlException
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getCompany-"+ex );

			return -1; 
		}
		catch( Exception ex )  // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getCompany-"+ex );

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
	 * Method : getCompany( )
	 * 
	 * Input : String key, RegnData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get List<RegnData> from
	 * company_registration table using CompanyRegnKey
	 */

	public int getCompany( CompanyRegnKey regnkey, RegnData regnData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		// List<CompanyRegnRecord> companyRegnRecObjList = new
		// ArrayList<CompanyRegnRecord>();
		
		String key = regnkey.companyPhoneNo_;

		query = "SELECT * FROM " + tableName_ + " WHERE company_phoneno = '" + key + "' LIMIT 1";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				regnData.companyId_ 	= rs.getString( "company_id" );
				regnData.companyName_ 	= rs.getString( "company_name" );
				regnData.logo_ 			= rs.getString( "company_logo_path" );

				regnData.businessCategory_ = rs.getString( "business_category_name" );
				regnData.theme_ = rs.getString( "company_theme" );
				regnData.companyType_ = rs.getString( "company_type" );

				regnData.pricingOption_ = rs.getString( "pricing_option" );
				regnData.phoneNo_ = rs.getString( "company_phoneno" );
				regnData.uuid_ = rs.getString( "uuid" );

				regnData.createdDate_ = rs.getTimestamp( "created_date" );
				regnData.companyStatus_ = rs.getString( "company_status" );
				regnData.segmentDivisionName_ = rs
				        .getString( "segment_division_name" );

				regnData.businessUnitName_ = rs.getString( "business_unit_name" );
				regnData.emailId_ = rs.getString( "email" );
			}

			return 0; // Success
		}

		catch( SQLException ex )  // SqlException
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getCompany-"+ex );

			return -1; 
		}
		catch( Exception ex )  // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getCompany-"+ex );

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
	 * Method : getCompanyPricingOption( )
	 * 
	 * Input : CompanyRegnKey key
	 * 
	 * Return : String
	 * 
	 * Purpose: It is used to get CompanyPricingOption for given CompanyRegnKey
	 */

	public String getCompanyPricingOption( CompanyRegnKey companyRegnKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		String pricingOption = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		// List<CompanyRegnRecord> companyRegnRecObjList = new
		// ArrayList<CompanyRegnRecord>();

		query = "SELECT pricing_option FROM " + tableName_ + " WHERE company_phoneno = '"
		        + companyRegnKey.companyPhoneNo_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				pricingOption = rs.getString( "pricing_option" );
			}

			return pricingOption; // Success
		}

		catch( SQLException ex )  // SqlException
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getCompanyPricingOption-"+ex );

			return pricingOption;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getCompanyPricingOption-"+ex );

			return pricingOption; 
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
	 * Method : getCompanyRecordList( )
	 * 
	 * Input : ResultSet
	 * 
	 * Return : List<CompanyRegnRecord>
	 * 
	 * Purpose: It is used to retrieve List<CompanyRegnRecord> from
	 * company_registration table for given result set
	 */

	public List<CompanyRegnRecord> getCompanyRecordList( ResultSet rs )
	{

		CompanyRegnRecord companyRegnRecObj = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		List<CompanyRegnRecord> companyRegnRecObjList = new ArrayList<CompanyRegnRecord>( );

		try
		{
			while( rs.next( ) )
			{
				companyRegnRecObj = new CompanyRegnRecord( );

				companyRegnRecObj.companyId_ = rs.getString( "company_id" );
				companyRegnRecObj.companyName_ = rs.getString( "company_name" );
				companyRegnRecObj.companyLogoPath_ = rs.getString( "company_logo_path" );

				companyRegnRecObj.businessCategoryName_ = rs
				        .getString( "business_category_name" );
				companyRegnRecObj.companyType_ = rs.getString( "company_type" );
				companyRegnRecObj.companyTheme_ = rs.getString( "company_theme" );

				companyRegnRecObj.pricingOption_ = rs.getString( "pricing_option" );
				companyRegnRecObj.companyPhoneNo_ = rs.getString( "company_phoneno" );
				companyRegnRecObj.uuid_ = rs.getString( "uuid" );

				companyRegnRecObj.createdDate_ = rs.getTimestamp( "created_date" );
				companyRegnRecObj.companyStatus_ = rs.getString( "company_status" );
				companyRegnRecObj.segmentDivisionName_ = rs
				        .getString( "segment_division_name" );

				companyRegnRecObj.businessUnitName_ = rs.getString( "business_unit_name" );
				companyRegnRecObj.regnKey_ = rs.getString( "regn_key" );
				companyRegnRecObj.email_ = rs.getString( "email" );

				companyRegnRecObjList.add( companyRegnRecObj );

			}

		}
		catch( SQLException ex ) // Sql Exception 
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getCompanyRecordList-" + ex );
			
			return companyRegnRecObjList;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getCompanyRecordList-" + ex );
			
			return companyRegnRecObjList;
		}
		

		return companyRegnRecObjList;

	}

	/*
	 * Method : getRegnDataList( )
	 * 
	 * Input : List<CompanyRegnRecord>
	 * 
	 * Return : List<RegnData>
	 * 
	 * Purpose: It is used to retrieve List<RegnData> from company_registration
	 * table for given rList<CompanyRegnRecord>
	 */

	private List<RegnData> getRegnDataList( List<CompanyRegnRecord> companyRegnRecordList )
	{
		List<RegnData> regnDataList = new ArrayList<RegnData>( );

		for( CompanyRegnRecord regnRecord : companyRegnRecordList )
		{
			RegnData regndata = new RegnData( );

			// set the values
			regndata.companyName_ = regnRecord.companyName_;
			regndata.logo_ = regnRecord.companyLogoPath_;
			regndata.businessCategory_ = regnRecord.businessCategoryName_;
			regndata.companyId_ = regnRecord.companyId_;

			regndata.signUpAs_ = regnRecord.companyType_;
			regndata.theme_ = regnRecord.companyTheme_;
			regndata.pricingOption_ = regnRecord.pricingOption_;

			regndata.phoneNo_ = regnRecord.companyPhoneNo_;
			regndata.uuid_ = regnRecord.uuid_;
			regndata.companyStatus_ = regnRecord.companyStatus_;

			regndata.segmentDivisionName_ = regnRecord.segmentDivisionName_;
			regndata.businessUnitName_ = regnRecord.businessUnitName_;
			regndata.emailId_ = regnRecord.email_;

			regndata.createdDate_ = regnRecord.createdDate_;

			CompanyRegnKey companyRegnKey = new CompanyRegnKey( );
			companyRegnKey.companyPhoneNo_ = regnRecord.companyPhoneNo_;
			regndata.companyRegnKey_ = companyRegnKey;

			regnDataList.add( regndata );

		}

		return regnDataList;
	}
	
	
	/*
	 * Method : getRegnDataList( )
	 * 
	 * Input : List<CompanyRegnRecord>
	 * 
	 * Return : List<RegnData>
	 * 
	 * Purpose: It is used to retrieve List<RegnData> from company_registration
	 * table for given rList<CompanyRegnRecord>
	 */

	private void getRegnDataList( List<CompanyRegnRecord> companyRegnRecordList,List<RegnData> regnDataList  )
	{

		for( CompanyRegnRecord regnRecord : companyRegnRecordList )
		{
			RegnData regndata = new RegnData( );

			// set the values
			regndata.companyName_ = regnRecord.companyName_;
			regndata.logo_ = regnRecord.companyLogoPath_;
			regndata.businessCategory_ = regnRecord.businessCategoryName_;
			regndata.companyId_ = regnRecord.companyId_;

			regndata.signUpAs_ = regnRecord.companyType_;
			regndata.theme_ = regnRecord.companyTheme_;
			regndata.pricingOption_ = regnRecord.pricingOption_;

			regndata.phoneNo_ = regnRecord.companyPhoneNo_;
			regndata.uuid_ = regnRecord.uuid_;
			regndata.companyStatus_ = regnRecord.companyStatus_;

			regndata.segmentDivisionName_ = regnRecord.segmentDivisionName_;
			regndata.businessUnitName_ = regnRecord.businessUnitName_;
			regndata.emailId_ = regnRecord.email_;

			regndata.createdDate_ = regnRecord.createdDate_;

			CompanyRegnKey companyRegnKey = new CompanyRegnKey( );
			companyRegnKey.companyPhoneNo_ = regnRecord.companyPhoneNo_;
			regndata.companyRegnKey_ = companyRegnKey;

			regnDataList.add( regndata );

		}

		
	}


	/*
	 * Method : createCompanyRegnRecord( )
	 * 
	 * Input : RegnData obj
	 * 
	 * Return : CompanyRegnRecord
	 * 
	 * Purpose: It converts RegnData to CompanyRegnRecord
	 */

	private CompanyRegnRecord createCompanyRegnRecord( RegnData regnDataObj )
	{
		// Form the record using the regnDataObj and return it
		CompanyRegnRecord regnRecord = new CompanyRegnRecord( );

		// set the values
		regnRecord.companyName_ = regnDataObj.companyName_;
		regnRecord.companyLogoPath_ = regnDataObj.logo_;
                System.out.println(regnDataObj.logo_);
		regnRecord.businessCategoryName_ = regnDataObj.businessCategory_;
		regnRecord.companyId_ = regnDataObj.companyId_;

		regnRecord.companyType_ = regnDataObj.signUpAs_;
		regnRecord.companyTheme_ = regnDataObj.theme_;
		regnRecord.pricingOption_ = regnDataObj.pricingOption_;

		regnRecord.companyPhoneNo_ = regnDataObj.phoneNo_;
		regnRecord.uuid_ = regnDataObj.uuid_;
		regnRecord.companyStatus_ = regnDataObj.companyStatus_;

		regnRecord.segmentDivisionName_ = regnDataObj.segmentDivisionName_;
		regnRecord.businessUnitName_ = regnDataObj.businessUnitName_;

		regnRecord.email_ = regnDataObj.emailId_;
		regnRecord.regnKey_ = regnDataObj.companyRegnKey_.companyPhoneNo_;

		return regnRecord;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : CompanyRegnRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using CompanyRegnRecord and returns as
	 * string
	 */

	private String formInsertQuery( CompanyRegnRecord companyRegnRecObj )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(company_id,company_name, company_logo_path, business_category_name, company_type, ";
		insertValues = insertValues
		        + "company_theme, pricing_option, company_phoneno, uuid, company_status,  ";
		insertValues = insertValues
		        + "segment_division_name, business_unit_name,regn_key,email)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";
		
		insertQuery = insertQuery + "'" + companyRegnRecObj.companyId_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.companyName_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.companyLogoPath_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.businessCategoryName_ + "', ";

		insertQuery = insertQuery + "'" + companyRegnRecObj.companyType_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.companyTheme_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.pricingOption_ + "', ";

		insertQuery = insertQuery + "'" + companyRegnRecObj.companyPhoneNo_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.uuid_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.companyStatus_ + "', ";

		insertQuery = insertQuery + "'" + companyRegnRecObj.segmentDivisionName_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.businessUnitName_ + "',";
		insertQuery = insertQuery + "'" + companyRegnRecObj.regnKey_ + "',";

		insertQuery = insertQuery + "'" + companyRegnRecObj.email_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}

	/*
	 * Method : getAllCompanyRegnKey( )
	 * 
	 * Input : List<CompanyRegnKey> obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all company regn key and fills
	 * List<CompanyRegnKey> through reference
	 */

	public int getAllCompanyRegnKey( List<CompanyRegnKey> companyRegnKeyList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "SELECT regn_key FROM " + tableName_;

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			CompanyRegnKey regnKey = null;

			while( rs.next( ) )
			{
				regnKey = new CompanyRegnKey( );

				regnKey.companyPhoneNo_ = rs.getString( "regn_key" );

				companyRegnKeyList.add( regnKey );
			}

			regnKey = null;

			return 0; // Success
		}

		catch( SQLException ex )  // Sql Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getAllCompanyRegnKey-"+ex );

			return -1;
		}
		catch( Exception ex )  // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getAllCompanyRegnKey-"+ex );

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
	 * Method : getCompanyStatusForCompanyKey( )
	 * 
	 * Input : CompanyRegnKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all company registration status for given
	 * CompanyRegnKey
	 */

	public int getCompanyStatus( CompanyRegnKey companyKey, StringBuilder userStatus )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT company_status FROM " + tableName_;
		query = query + " WHERE regn_key = '" + companyKey.companyPhoneNo_ + "'";

		errorMaster_.insert( "Query=" + query );
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				String status = rs.getString( "company_status" );
				userStatus = userStatus.append( status );
				return 0; // Success
			}
			else
			{
				return -1; // Company does not exist for company key
			}

		}

		catch( SQLException ex )  // SqlException
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getAllCompanyRegnKey-"+ex );

			return -1; 
		}
		catch( Exception ex )  // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getAllCompanyRegnKey-"+ex );

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
	 * Method : getCompanyType( )
	 * 
	 * Input : CompanyRegnKey
	 * 
	 * Return : String
	 * 
	 * Purpose: It is used to get company type for given CompanyRegnKey
	 */

	public String getCompanyType( CompanyRegnKey companyKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "SELECT company_type FROM " + tableName_;
		query = query + " WHERE regn_key = '" + companyKey.companyPhoneNo_ + "'";

		errorMaster_.insert( "Query=" + query );

		String companyType = null;

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				companyType = rs.getString( "company_type" );
				// Success

				return companyType;
			}
			else
			{
				return companyType; // Company does not exist for company key
			}

		}

		catch( SQLException ex ) // SqlException
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getCompanyType-"+ex );

			return companyType; 
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getCompanyType-"+ex );

			return companyType; 
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
	 * @Date : Mar 23, 2013 7:27:20 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get the RegnData based on the CompanRegnKey
	 * 
	 * @param key
	 * @param regndata
	 * @return
	 * 
	 */
	public int searchKey( CompanyRegnKey key, RegnData regndata )
	{
		PreparedStatement pretStmt = null;
		Connection con = null;
		String query = null;
		ResultSet rs = null;

		int result = 0;

		query = "SELECT * FROM " + tableName_ + " WHERE regn_key = ?";

		try
		{
			con = DBConnect.instance( ).getConnection( );
			if( con == null ) return -1;

			pretStmt = con.prepareStatement( query );
			pretStmt.setString( 1, key.companyPhoneNo_ );

			errorMaster_.insert( "Query=" + pretStmt );

			rs = pretStmt.executeQuery( );

			CompanyRegnUtils regnutils = new CompanyRegnUtils( );

			result = regnutils.getData( rs, regndata );

			return result;
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "CompanyRegnTable :: searchKey :: SQL Error " + ex;

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

				if( pretStmt != null )
				{
					pretStmt.close( );
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

	/**
	 * @Date : Mar 23, 2013 7:27:57 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : get the RegnData based on the Filter
	 * 
	 * @param Filter
	 * @param regndataList
	 * @return
	 * 
	 */
	public int searchFilter( RegnData Filter, List<RegnData> regndataList )
	{
		PreparedStatement pretStmt = null;
		Connection con = null;
		String query = null;
		ResultSet rs = null;

		int result = 0;

		query = "SELECT * FROM " + tableName_;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			if( con == null ) return -1;

			CompanyRegnUtils regnutils = new CompanyRegnUtils( );

			CompanyRegnRecord regnrecord = new CompanyRegnRecord( );
			regnutils.convert( Filter, regnrecord );

			StringHolder updaterQuery = new StringHolder( );
			regnutils.getFilterString( regnrecord, updaterQuery );
			query = query + updaterQuery.value;

			pretStmt = con.prepareStatement( query );

			regnutils.prepareStatement( regnrecord, pretStmt );

			errorMaster_.insert( "Query=" + pretStmt );

			rs = pretStmt.executeQuery( );

			result = regnutils.getDataList( rs, regndataList );

			return result;
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "CompanyRegnTable : getCompanyForKey :: SQL Error " + ex;

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

				if( pretStmt != null )
				{
					pretStmt.close( );
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

	/**
	 * @author Anbazhagan
	 * 
	 * @Date : Mar 23, 2013 2:31:46 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Update the CompanyRegnTable
	 * 
	 * @param regndata
	 * @return
	 * 
	 */
	public int update( CompanyRegnKey regnKey, RegnData regndata )
	{
		PreparedStatement pretStmt = null;
		Connection con = null;
		String query = null;
		int updateResult;

		query = "UPDATE " + tableName_ + " SET ";

		try
		{
			con = DBConnect.instance( ).getConnection( );
			if( con == null ) return -1;

			CompanyRegnUtils regnutils = new CompanyRegnUtils( );
			CompanyRegnRecord regnrecord = new CompanyRegnRecord( );
			regnutils.convert( regndata, regnrecord );

			StringHolder updaterQuery = new StringHolder( );
			updaterQuery.value = "";
			regnutils.getUpdateString( regnrecord, updaterQuery );
			query += updaterQuery.value;
			query += " WHERE regn_key =?";

			pretStmt = con.prepareStatement( query );

			int index = regnutils.prepareStatement( regnrecord, pretStmt );
			pretStmt.setString( index, regnKey.companyPhoneNo_ ); // for Adding
																  // the Company
																  // Regn Key
																  // Key

			errorMaster_.insert( "prestmt =" + pretStmt );

			updateResult = pretStmt.executeUpdate( );

			if( updateResult == 0 ) return -1;

			return 0;
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "CompanyRegnTable :: update :: SQL Error " + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // SqlException
		}

		finally
		{
			try
			{
				if( pretStmt != null )
				{
					pretStmt.close( );
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
	
	// Search Supplier and Buyer -- start
		/**
		 * This is a search method that offers the result as a set of regn keys of
		 * the company. It is returned as a set, since the keys are anyway unique
		 * and does not have duplicates. Also , Set data structure is needed for the
		 * API of one of the modules related to search supplier.
		 * 
		 * Input: RegnData comes as a queryFilter, with values set to null string,
		 * for those fields not included in the search clause. For other fields the
		 * values will be set. Hence this RegnData input acts as a queryFilter.
		 * 
		 * Output: Is a Set<> of company keys formed as a result of the search
		 * query.
		 * 
		 */
		
		public int searchKeys( RegnData queryFilter, Set<CompanyRegnKey> companyKeys )
		{
			Statement stmt = null;
			
			Connection con = null;
			
			String query = new String( );
			
			StringHolder whereClause = new StringHolder(  );
			
			whereClause.value = "";

			ErrorLogger errLogger = ErrorLogger.instance( );

			try
			{
				con = DBConnect.instance( ).getConnection( );
				
				stmt = con.createStatement( );

				int result = regUtils_.formWhereClause( queryFilter, whereClause );
				
				if( result == -1 )
					return result;

				query = "SELECT DISTINCT regn_key FROM " + this.tableName_ + whereClause.value;

				errorMaster_.insert( "searchKeys::Query: " + query );
                                System.out.println("QUERY::==>>"+query);

				ResultSet rs = stmt.executeQuery( query );

				// Need to return int and check for error also. 
				regUtils_.rsToKeySet( rs, companyKeys );

				return 0;

				// Success

			}
			catch( SQLException ex )
			{
				errLogger.logMsg( "SQLException::CompanyRegnTable:searchKeys " + ex );
				return -1;

				// SQLException occurred while filtering a Company
			}
			catch( Exception ex ) // General Exception
			{
				errLogger.logMsg( "Exception::CompanyRegnTable:searchKeys" + ex );
				return -2;
			}
			finally
			{
				try
				{
					
					if( stmt != null )
					{
						stmt.close( );
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

		/**
		 * 
		 * This method returns the List of RegnData of the companies for the given
		 * set of Company Keys as a Set<>.
		 * 
		 * Input: Set<> of CompanyRegnKey objects.
		 * 
		 * Output: List<> of RegnData related to the set of input company keys.
		 * 
		 */
		public int getCompanyList( Set<CompanyRegnKey> regnKeys, List<RegnData> companyList )
		{
			
			Statement stmt = null;
			Connection con = null;
			String query = null;

			ErrorLogger errLogger = ErrorLogger.instance( );

			String whereClause = "";

			Iterator<CompanyRegnKey> it = regnKeys.iterator( );

			while( it.hasNext( ) )
			{
				whereClause += "'" + it.next( ).companyPhoneNo_ + "',";
			}
			
			whereClause = whereClause.substring( 0, whereClause.length( ) - 1 );

			try
			{
				con = DBConnect.instance( ).getConnection( );
				stmt = con.createStatement( );

				// assumed company_type value is Supplier
				//query = "SELECT DISTINCT regn_key, company_name FROM " + this.tableName_;
				query = "SELECT * FROM " + tableName_ ;
				query += " WHERE regn_key IN (" + whereClause + ")";

				errorMaster_.insert( "getCompanyForKeys::Query: " + query );

				ResultSet rs = stmt.executeQuery( query );

				// Need to return int and check for error also. 

				return regUtils_.rsToDataList( rs, companyList );

				// Success

			}
			catch( SQLException ex )
			{
				errLogger.logMsg( "SQLException::CompanyRegnTable:getCompanyForKeys" + ex );

				return -1;

				// SQLException occurred while filling list for key set
			}
			catch( Exception ex ) // General Exception
			{
				errLogger.logMsg( "Exception::CompanyRegnTable:getCompanyForKeys" + ex );

				return -2;
			}
			finally
			{
				try
				{
					
					if( stmt != null )
					{
						stmt.close( );
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
		 * Method: find
		 * 
		 * Input: CompanyRegnKey object, RegnData obj (As ref)
		 * 
		 * Return: int
		 * 
		 * Purpose:  This method is used to get the company type and company business category
		 * name for the given company regn key object
		 * 
		 */
		
		public int find( CompanyRegnKey regnKey, RegnData regnData )
		{
			Statement stmt = null;
			Connection con = null;
			String query = null;
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			query = "SELECT company_name, company_type, business_category_name, email FROM " + tableName_;
			query = query + " WHERE regn_key = '" + regnKey.companyPhoneNo_ + "'";

			errorMaster_.insert( "Query=" + query );

			try
			{
				con = DBConnect.instance( ).getConnection( );

				stmt = con.createStatement( );

				ResultSet rs = stmt.executeQuery( query );

				if( rs.next( ) )
				{
					regnData.companyName_ = rs.getString( "company_name" );
					regnData.companyType_ = rs.getString( "company_type" );
					regnData.businessCategory_ = rs.getString( "business_category_name" );
					regnData.emailId_ 	= rs.getString( "email" );
					
					return 0;
				}
				else
				{
					return -1; // Company does not exist for company key
				}

			}

			catch( SQLException ex ) // SqlException
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.find() - "+ex );

				return -2; 
			}
			catch( Exception ex ) // General Exception
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.find() - "+ex );

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
		 * Method: find
		 * 
		 * Input: RegnData regnDatao, List<RegnData> regnDataList (As ref)
		 * 
		 * Return: int
		 * 
		 * Purpose: To get the list of regnDataList based on company key, business
		 * category and company type
		 */
		
		public int find( RegnData regnData, List<RegnData> regnDataList )
		{
			Statement stmt = null;
			Connection con = null;
			String query = null;
			
			String searchStr = getRecoVendorStr( regnData );
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			query = "SELECT regn_key, company_name, company_logo_path,company_type,email FROM " + tableName_;
			query = query + " WHERE " + searchStr + " AND regn_key != '"+ regnData.companyRegnKey_.companyPhoneNo_ + "' LIMIT 50";
			
			
			errorMaster_.insert( "Query=" + query );

			try
			{
				con = DBConnect.instance( ).getConnection( );

				stmt = con.createStatement( );

				ResultSet rs = stmt.executeQuery( query );

				while( rs.next( ) )
				{
					RegnData data = new RegnData( );
					
					data.phoneNo_ = rs.getString( "regn_key" );
					
					data.companyName_ = rs.getString( "company_name" );
					
					data.logo_	= rs.getString( "company_logo_path" );
					
					data.companyType_ = rs.getString( "company_type" );
					
					data.emailId_ = rs.getString( "email" );
					
					data.logo_  = rs.getString( "company_logo_path" );
					
					CompanyRegnKey regnKey = new CompanyRegnKey( );
					
					regnKey.companyPhoneNo_ =  rs.getString( "regn_key" );
					
					data.companyRegnKey_ = regnKey;
					
					List<MailingAddressData> mailAddressList = new ArrayList<MailingAddressData>( );
					
					data.mailingAddressArr_ = mailAddressList;
					
					regnDataList.add( data );
					
					data = null;
					
				}
				return 0; 

			}

			catch( SQLException ex ) // SqlException
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.find() - "+ex );

				
				return -2; 
			}
			catch( Exception ex ) // General Exception
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.find() - "+ex );

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
		
		private String getRecoVendorStr( RegnData regnData )
		{
			String searchStr = "";
			
			if( regnData.companyType_.equals( CompanyType.type.SUPPLIER.getValue( ) ) )
			{
				searchStr = "company_type = '" + CompanyType.type.BUYER.getValue( ) + "'" +
							" AND business_category_name = '" + regnData.businessCategory_ + "'";
			}
			else if( regnData.companyType_.equals( CompanyType.type.BUYER.getValue( ) ) )
			{
				searchStr = "company_type = '" + CompanyType.type.SUPPLIER.getValue( ) + "'" +
							" AND business_category_name = '" + regnData.businessCategory_ + "'";
			}
			else 
			{
				searchStr = "business_category_name = '" + regnData.businessCategory_ + "'";
			}
			
			return searchStr;
		}
		
		/*
		 * Method: find
		 * 
		 * Inut: CompanyRegnKey obj, UserProfileKey obj(As ref)
		 * 
		 * Return: int
		 * 
		 * Purpose: This method gives fetches the UserProfileKey for given
		 * CompanyRegnKey to find the UserProfileKey of the company admin
		 */
		public int find( CompanyRegnKey regnKey, UserProfileKey profileKey )
		{
			Statement stmt = null;
			Connection con = null;
			String query = null;
		
			ErrorLogger errLogger = ErrorLogger.instance( );

			
			
			query = "SELECT email FROM " + tableName_;
			query = query + " WHERE regn_key = '" + regnKey.companyPhoneNo_ + "'";
			
			
			errorMaster_.insert( "Query=" + query );

			try
			{
				con = DBConnect.instance( ).getConnection( );

				stmt = con.createStatement( );

				ResultSet rs = stmt.executeQuery( query );

				if( rs.next( ) )
				{
					profileKey.email_ = rs.getString( "email" );
					
					return 0;
				}
				return -1; // email not exist for given company key

			}

			catch( SQLException ex ) // SqlException
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.find( CompanyRegnKey, UserProfileKey ) - "+ex );

				return -2; 
			}
			catch( Exception ex ) // General Exception
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.find( CompanyRegnKey, UserProfileKey ) - "+ex );

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
		 * Method: find
		 * 
		 * Input: list<CompanyRegnKey> regCompKeyList, List<CompanyRegnKey> nonRegComList (As ref)
		 * 
		 * Return: int
		 * 
		 * Purpose: This method fetches the list of companies for given String
		 * and filters the list with list of companyregnkey list inorder to get the
		 * top ten companies that matches the string who are not registered vendors
		 */
		
		public int find( 
    						List<CompanyRegnKey> regCompKeyList,
    						List<RegnData> nonRegCompList,
    						NRVendorSearchData nrVendorSearchData 
						) 
		{
			Statement stmt = null;
			Connection con = null;
			String query = null;
		
			ErrorLogger errLogger = ErrorLogger.instance( );

			query = "SELECT regn_key, company_name, company_type FROM " + tableName_;
			query = query + " WHERE company_name LIKE '%"+ nrVendorSearchData.searchStr_ + "%'";
			
			if( regCompKeyList.size( ) > 0 )
			{
				query = query + "AND regn_key NOT IN (";
				
    			int i=0;
    			
    			for( CompanyRegnKey regnKey : regCompKeyList )
                {
    	            query = query + "'" + regnKey.companyPhoneNo_ + "'";
    	            
    	            i++;
    	            
    	            if( regCompKeyList.size( ) > i )
    	            {
    	            	query = query + ",";
    	            }
                }
    			query = query + ")";
			}
			
			query = query + " AND (company_type = '" + nrVendorSearchData.requestSendorType_ + "' OR ";
			query = query + "company_type = 'Buyers/Suppliers')";
			
			query = query + " LIMIT 10";
			
			errorMaster_.insert( "Query=" + query );
			
			try
			{
				con = DBConnect.instance( ).getConnection( );

				stmt = con.createStatement( );

				ResultSet rs = stmt.executeQuery( query );

				while( rs.next( ) )
				{
					RegnData regnData = new RegnData( );
					
					regnData.companyRegnKey_ = new CompanyRegnKey( );
					
					regnData.companyRegnKey_.companyPhoneNo_ = rs.getString( "regn_key" );
					regnData.companyName_	= rs.getString( "company_name" );
					regnData.companyType_	= rs.getString( "company_type" );
					
					nonRegCompList.add( regnData );
					
					regnData  = null;
				}
				
				return 0; // email not exist for given company key
			}

			catch( SQLException ex ) // SqlException
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.find( List<CompanyRegnKey>, List<RegnData> ) - "+ex );

				return -2; 
			}
			catch( Exception ex ) // General Exception
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.find( List<CompanyRegnKey>, List<RegnData> ) - "+ex );

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
		 * Method: find
		 * 
		 * Input: list<CompanyRegnKey> regCompKeyList, List<CompanyRegnKey> nonRegComList (As ref)
		 * 
		 * Return: int
		 * 
		 * Purpose: This method fetches the list of companies for given String
		 * and filters the list with list of companyregnkey list inorder to get the
		 * top ten companies that matches the string who are not registered vendors
		 */
		
		public int find( 
    						List<CompanyRegnKey> regCompKeyList,
    						List<RegnData> nonRegCompList,
    						RVendorSearchData rVendorSearchData
						) 
		{
			Statement stmt = null;
			Connection con = null;
			String query = null;
		
			ErrorLogger errLogger = ErrorLogger.instance( );

			query = "SELECT regn_key, company_name, company_type,email FROM " + tableName_;
			query = query + " WHERE company_name LIKE '%"+ rVendorSearchData.searchStr_ + "%'";
			
			if( regCompKeyList.size( ) > 0 )
			{
				query = query + "AND regn_key IN (";
				
    			int i=0;
    			
    			for( CompanyRegnKey regnKey : regCompKeyList )
                {
    	            query = query + "'" + regnKey.companyPhoneNo_ + "'";
    	            
    	            i++;
    	            
    	            if( regCompKeyList.size( ) > i )
    	            {
    	            	query = query + ",";
    	            }
                }
    			query = query + ")";
			}
			
			
			query = query + " LIMIT 10";
			
			errorMaster_.insert( "Query=" + query );
			
			try
			{
				con = DBConnect.instance( ).getConnection( );

				stmt = con.createStatement( );

				ResultSet rs = stmt.executeQuery( query );

				while( rs.next( ) )
				{
					RegnData regnData = new RegnData( );
					
					regnData.companyRegnKey_ = new CompanyRegnKey( );
					
					regnData.companyRegnKey_.companyPhoneNo_ = rs.getString( "regn_key" );
					regnData.companyName_	= rs.getString( "company_name" );
					regnData.companyType_	= rs.getString( "company_type" );
                                        regnData.companyId_=rs.getString( "email" );
					
					nonRegCompList.add( regnData );
					
					regnData  = null;
				}
				
				return 0; // email not exist for given company key
			}

			catch( SQLException ex ) // SqlException
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.find( List<CompanyRegnKey>, List<RegnData> ) - "+ex );

				return -2; 
			}
			catch( Exception ex ) // General Exception
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.find( List<CompanyRegnKey>, List<RegnData> ) - "+ex );

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
		 * Method : get( )
		 * 
		 * Input : List<CompanyRegnKey> obj
		 * 
		 * Return : int
		 * 
		 * Purpose: It is used to get all company regn key and fills
		 * List<CompanyRegnKey> through reference where company status is active
		 */

		public int get( List<CompanyRegnKey> companyRegnKeyList )
		{
			Statement stmt = null;
			Connection con = null;
			String query = null;
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			query = "SELECT regn_key FROM " + tableName_ + " WHERE company_status = '" + 
							CompanyStatus.status.ACTIVE.getValue( ) + "'";
			
			errorMaster_.insert( "Query=" + query );

			try
			{
				con = DBConnect.instance( ).getConnection( );

				stmt = con.createStatement( );

				ResultSet rs = stmt.executeQuery( query );

				CompanyRegnKey regnKey = null;

				while( rs.next( ) )
				{
					regnKey = new CompanyRegnKey( );

					regnKey.companyPhoneNo_ = rs.getString( "regn_key" );

					companyRegnKeyList.add( regnKey );
				}

				regnKey = null;

				return 0; // Success
			}

			catch( SQLException ex )  // Sql Exception
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.get( List<CompanyRegnKey> ) - "+ex );

				return -1;
			}
			catch( Exception ex )  // General Exception
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.get( List<CompanyRegnKey> ) - "+ex );

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
		 * Method: find
		 * 
		 * Input: CompanyRegnKey obj
		 * 
		 * Return: string
		 * 
		 * Purpose: This method fetches the company name for given company key
		 */
		
		public String find( CompanyRegnKey regnKey ) 
		{
			Statement stmt = null;
			Connection con = null;
			String query = null;
			
			String result = "";
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			query = "SELECT company_name FROM " + tableName_;
			query = query + " WHERE regn_key = '" + regnKey.companyPhoneNo_ + "'" ;
			
			errorMaster_.insert( "Query=" + query );
			
			try
			{
				con = DBConnect.instance( ).getConnection( );

				stmt = con.createStatement( );

				ResultSet rs = stmt.executeQuery( query );
				
				if( rs.next( ) )
				{
					result = rs.getString( "company_name" );
				}
				
				return result; // email not exist for given company key
			}

			catch( SQLException ex ) // SqlException
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.find( CompanyRegnKey ) - "+ex );

				return ""; 
			}
			catch( Exception ex ) // General Exception
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.find( CompanyRegnKey ) - "+ex );

				return ""; 
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
		 * Input: CompanyRegnKey obj
		 * 
		 * Return: string
		 * 
		 * Purpose: This method fetches the company uuid for given company key
		 */
		
		public String getUuid( CompanyRegnKey regnKey ) 
		{
			Statement stmt = null;
			Connection con = null;
			String query = null;
			
			String result = "";
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			query = "SELECT uuid FROM " + tableName_;
			query = query + " WHERE regn_key = '" + regnKey.companyPhoneNo_ + "'" ;
			
			errorMaster_.insert( "Query=" + query );
			
			try
			{
				con = DBConnect.instance( ).getConnection( );

				stmt = con.createStatement( );

				ResultSet rs = stmt.executeQuery( query );
				
				if( rs.next( ) )
				{
					result = rs.getString( "uuid" );
				}
				
				return result; // email not exist for given company key
			}

			catch( SQLException ex ) // SqlException
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.getUuid( CompanyRegnKey ) - "+ex );

				return ""; 
			}
			catch( Exception ex ) // General Exception
			{
				errLogger.logMsg( "Exception::CompanyRegnTable.getUuid( CompanyRegnKey ) - "+ex );

				return ""; 
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
		 * @Date	: Aug 28, 2013 7:27:52 PM
		 *
		 * @Return 	: int
		 *
		 * @Purpose	: Getting the All the registered Company Information
		 *
		 * @param companyDetailsMap
		 * @return
		 *
		 */
		public int getAllCompanyData( Map<CompanyRegnKey, RegnData> companyDetailsMap )
		{
			Statement stmt = null;
			Connection con = null;
			String query = null;

			ErrorLogger errLogger = ErrorLogger.instance( );

			// List<CompanyRegnRecord> companyRegnRecObjList = new
			// ArrayList<CompanyRegnRecord>();

			query = "SELECT * FROM " + tableName_;

			errorMaster_.insert( "Query=" + query );

			try
			{
				con = DBConnect.instance( ).getConnection( );

				stmt = con.createStatement( );

				ResultSet rs = stmt.executeQuery( query );

				List<CompanyRegnRecord> companyRegnRecList = getCompanyRecordList( rs );
				
				List<RegnData> regnDataList = getRegnDataList( companyRegnRecList );

				for( RegnData item : regnDataList )
				{
					companyDetailsMap.put( item.companyRegnKey_, item );
				}

				return 0; // Success
			}

			catch( SQLException ex ) // SqlException
			{
				errLogger.logMsg( "Exception::CompanyRegnTable:getAllCompanyData-" + ex );

				return -1;
			}
			catch( Exception ex ) // General Exception
			{
				errLogger.logMsg( "Exception::CompanyRegnTable:getAllCompanyData-" + ex );

				return -1;
			}
			finally
			{
				try
				{
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
