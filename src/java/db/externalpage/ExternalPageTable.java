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
package db.externalpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.ErrorLogger;
import utils.IntHolder;
import utils.StringHolder;

import core.externalpage.ExternalPageData;
import core.regn.CompanyRegnKey;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * @FileName : ExternalPageTable.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 5, 2013
 * 
 * @Purpose : External Database Related Function
 * 
 */
public class ExternalPageTable
{
    private static ErrorMaster errorMaster_ = null;


	private String	tableName_;

	public ExternalPageTable()
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "external_page";
	}

	/**
	 * @Date : May 5, 2013 1:44:12 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : get the External Page Record based on the Key value
	 * 
	 * @param externalpageKey
	 * @param data
	 * @return
	 * 
	 */
	public int serachKey( CompanyRegnKey externalpageKey, ExternalPageData data )
	{
		PreparedStatement pretStmt = null;
		Connection con = null;
		String query = null;

		int result = 0;

		query = "SELECT * FROM " + tableName_ + " WHERE compnayRegnKey = ?";

		try
		{
			con = DBConnect.instance( ).getConnection( );
			if( con == null ) return -1;

			pretStmt = con.prepareStatement( query );
			pretStmt.setString( 1, externalpageKey.companyPhoneNo_ );

			errorMaster_.insert( "Query=" + pretStmt );

			ResultSet rs = pretStmt.executeQuery( );

			ExternalPageUtils utils = new ExternalPageUtils( );
			result = utils.getData( rs, data );

			return result;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::ExternalPageTable :: serachKey :: " + ex );
			return -1;
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
			catch( SQLException sQLException )
			{
			}
		}
	}

	/**
	 * @Date : May 5, 2013 1:44:08 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get the External Page Record based on the filter object
	 * 
	 * @param filter
	 * @param data
	 * @return
	 * 
	 */

	public int serachFilter( ExternalPageData filter, ExternalPageData data )
	{
		PreparedStatement pretStmt = null;
		Connection con = null;
		String query = null;

		int result = 0;

		query = "SELECT * FROM " + tableName_;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			if( con == null ) return -1;

			ExternalPageUtils utils = new ExternalPageUtils( );
			ExternalPageRecord filterrecord = new ExternalPageRecord( );
			utils.convert( filter, filterrecord );

			utils.StringHolder searchQuery = new utils.StringHolder( );
			searchQuery.value = "";

			utils.getFilterString( filterrecord, searchQuery );

			query = query + searchQuery.value;

			pretStmt = con.prepareStatement( query );

			utils.prepareStatement( filterrecord, pretStmt );
			
			errorMaster_.insert( "query=" + pretStmt );

			ResultSet rs = pretStmt.executeQuery( );

			result = utils.getData( rs, data );

			return result;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::ExternalPageTable :: serachFilter :: " + ex );
			return -1;
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
			catch( SQLException sQLException )
			{
			}
		}
	}

	/**
	 * @Date : May 5, 2013 5:14:02 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Insert the New Row to the Table
	 * 
	 * @param data
	 * @param ExternalPageID
	 * @return
	 * 
	 */
	public int insert( ExternalPageData data, IntHolder ExternalPageID )
	{
		PreparedStatement pretStmt = null;
		Connection con = null;
		String query = null;
		ResultSet generateKey = null;

		int result = 0;

		query = "INSERT INTO " + tableName_ + " (";

		try
		{
			con = DBConnect.instance( ).getConnection( );
			if( con == null ) return -1;

			ExternalPageUtils utils = new ExternalPageUtils( );
			ExternalPageRecord recordInsert = new ExternalPageRecord( );
			utils.convert( data, recordInsert );

			utils.StringHolder insertvalue = new StringHolder( );
			insertvalue.value = "";
			StringHolder insertcolumn = new StringHolder( );
			insertcolumn.value = "";

			utils.getInsertString( recordInsert, insertvalue, insertcolumn );

			query += insertcolumn.value + ") VALUES ( ";
			query += insertvalue.value + ")";
			errorMaster_.insert( "query =" + query );
			pretStmt = con.prepareStatement( query );

			utils.prepareStatement( recordInsert, pretStmt );

			errorMaster_.insert( "prestmt =" + pretStmt );

			int val = pretStmt.executeUpdate( );

			if( val > 0 )
			{
				generateKey = pretStmt.getGeneratedKeys( );
				while( generateKey.next( ) )
					ExternalPageID.value = generateKey.getInt( 1 );

				result = 0;
			}
			else
				result = -1;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::ExternalPageTable :: insert :: " + ex );
			result = -2;
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
			catch( SQLException sQLException )
			{
			}
		}

		return result;
	}

	/**
	 * @Date : May 5, 2013 5:23:10 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Updating the Existing Data
	 * 
	 * @param key
	 * @param data
	 * @return
	 * 
	 */
	public int update( CompanyRegnKey key, ExternalPageData data )
	{
		PreparedStatement pretStmt = null;
		Connection con = null;
		String query = null;

		int result = 0;

		query = "UPDATE " + tableName_ + " SET ";

		try
		{
			con = DBConnect.instance( ).getConnection( );
			if( con == null ) return -1;

			ExternalPageUtils utils = new ExternalPageUtils( );
			ExternalPageRecord recordUpdate = new ExternalPageRecord( );
			utils.convert( data, recordUpdate );

			utils.StringHolder updateQuery = new utils.StringHolder( );
			updateQuery.value = "";

			utils.getUpdateString( recordUpdate, updateQuery );

			query += updateQuery.value;
			query += " WHERE compnayRegnKey =?";

			pretStmt = con.prepareStatement( query );

			int index = utils.prepareStatement( recordUpdate, pretStmt );
			pretStmt.setString( index, key.companyPhoneNo_ );

			errorMaster_.insert( "prestmt =" + pretStmt );

			result = pretStmt.executeUpdate( );

			if( result == 0 ) return -1;

			return 0;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::ExternalPageTable :: update :: " + ex );
			result = -2;
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
			catch( SQLException sQLException )
			{
			}
		}

		return result;
	}

	/**
	 * @Date : May 5, 2013 5:26:25 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Deleting the record based on the given key
	 * 
	 * @param key
	 * @return
	 * 
	 */
	public int delete( CompanyRegnKey key )
	{
		PreparedStatement preStmt = null;
		Connection con = null;
		String query = null;

		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE compnayRegnKey = ?";

		try
		{
			con = DBConnect.instance( ).getConnection( );
			if( con == null ) return -1;
			preStmt = con.prepareStatement( query );
			preStmt.setString( 1, key.companyPhoneNo_ );
			errorMaster_.insert( "Query=" + preStmt );

			deleteResult = preStmt.executeUpdate( );
			if( deleteResult > 0 ) return 0;

			return -1;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::ExternalPageTable :: update :: " + ex );
			return -2;
		}
		finally
		{
			try
			{
				if( preStmt != null )
				{
					preStmt.close( );
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
