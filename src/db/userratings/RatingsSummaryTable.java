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
package db.userratings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import core.regn.CompanyRegnKey;
import core.userratings.RatingSummaryData;
import db.utils.DBConnect;

import utils.ErrorLogger;

/**
 * @FileName : RattingsSummaryTable.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Aug 28, 2013
 * 
 * @Purpose :
 * 
 */
public class RatingsSummaryTable
{
	private ErrorLogger	      errLogger_;
	private String	          tableName_;
	private Connection	      con_;
	private PreparedStatement	pretStmt	= null;

	/**
	 * Initializing the Object
	 */
	public RatingsSummaryTable()
	{
		this.errLogger_ = ErrorLogger.instance( );
		this.tableName_ = "ratings_summary";
		this.con_ = null;
		this.pretStmt = null;
	}

	/**
	 * @Date : Aug 28, 2013 12:30:33 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Close the DB Connection
	 * 
	 * 
	 */
	private void closeConnection( )
	{
		try
		{
			if( pretStmt != null )
			{
				pretStmt.close( );
			}
			if( con_ != null )
			{
				con_.close( );
			}
		}
		catch( SQLException sQLException )
		{
		}
	}

	/**
	 * @Date : Aug 28, 2013 12:47:25 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Adding the information in the RattingsSummaryData
	 * 
	 * @param data
	 * @return
	 * 
	 */
	public int add( RatingSummaryData data )
	{
		try
		{
			RatingsSummaryRecord record = createRatingsSummaryRecord( data );
			con_ = DBConnect.instance( ).getConnection( );

			String insertQuery = null;

			insertQuery = " INSERT INTO " + this.tableName_
			        + " ( regn_rel_key, first_rating_timestamp, last_rating_timestamp,avg_ratings, no_of_ratings)  ";
			insertQuery += " VALUES (?,?,?,?,?) ";

			pretStmt = con_.prepareStatement( insertQuery );
			pretStmt.setString( 1, record._regn_rel_key );
			pretStmt.setTimestamp( 2, record._first_rating_timestamp );
			pretStmt.setTimestamp( 3, record._last_rating_timestamp );
			pretStmt.setInt( 4, record._avg_ratings );
			pretStmt.setInt( 5, record._no_of_ratings );

			int val = pretStmt.executeUpdate( );

			System.out.println( "----------------------------" );
			System.out.println( pretStmt );

			if( val == 1 ) return 0;

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::RattingsSummaryTable.add( ) " + ex );
			return -1;
		}
		finally
		{
			this.closeConnection( );
		}

		return -1;
	}

	/**
	 * @Date : Aug 28, 2013 12:49:55 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Update this Table information
	 * 
	 * @param data
	 * @return
	 * 
	 */
	public int update( RatingSummaryData data )
	{
		RatingsSummaryRecord record = createRatingsSummaryRecord( data );

		int updateResult = 0;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );

			String updateQuery = null;

			updateQuery = "UPDATE " + this.tableName_ + " SET ";
			updateQuery = updateQuery + " last_rating_timestamp = ?,avg_ratings = ?, no_of_ratings = ?";
			updateQuery = updateQuery + " WHERE regn_rel_key= ?";

			pretStmt = con_.prepareStatement( updateQuery );
			pretStmt.setTimestamp( 1, record._last_rating_timestamp );
			pretStmt.setInt( 2, record._avg_ratings );
			pretStmt.setInt( 3, record._no_of_ratings );
			pretStmt.setString( 4, record._regn_rel_key );

			System.out.println( pretStmt );

			updateResult = pretStmt.executeUpdate( );

			System.out.println( "---------------------------" );
			System.out.println( pretStmt );

			if( updateResult > 0 )
			{
				return 0;
			}
			else
			{
				return -1;
			}

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::RattingsSummaryTable.update( ) " + ex );

			return -2;
		}

	}

	/**
	 * @Date : Aug 28, 2013 12:51:48 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Remove the Item form the Table
	 * 
	 * @param key
	 * @return
	 * 
	 */
	public int remove( CompanyRegnKey key )
	{
		int deleteResult = 0;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );

			String deleteQuery = null;
			deleteQuery = "DELETE FROM " + this.tableName_ + " WHERE regn_rel_key= ? ";
			pretStmt = con_.prepareStatement( deleteQuery );
			pretStmt.setString( 1, key.companyPhoneNo_ );
			deleteResult = pretStmt.executeUpdate( );

			System.out.println( pretStmt );

			if( deleteResult > 0 )
			{
				return 0;
			}
			else
			{
				return -1;
			}

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::RattingsSummaryTable.remove( ) for item::" + key.companyPhoneNo_ + " \n" + ex );

			return -2;
		}
		finally
		{
			closeConnection( );
		}

	}

	/**
	 * @Date : Sep 1, 2013 3:46:12 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : get the Summary Table list based on the Company Ren Key
	 * 
	 * @param key
	 * @return
	 * 
	 */
	public int find( CompanyRegnKey key, Map<CompanyRegnKey, RatingSummaryData> ratingMap )
	{

		String query = null;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );

			query = "SELECT * FROM " + this.tableName_ + " where regn_rel_key = ?";

			pretStmt = con_.prepareStatement( query );
			pretStmt.setString( 1, key.companyPhoneNo_ );

			ResultSet rs = pretStmt.executeQuery( );

			int val = rsToRatingsSummaryDataList( rs, ratingMap );
			ratingMap.get( key ).Show( );
			System.out.print( pretStmt );

			return val;
			// Success
		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::RattingsSummaryTable.find( ) " + ex );

			return -1;
		}
		finally
		{
			this.closeConnection( );
		}

	}

	/**
	 * @Date : Aug 28, 2013 12:53:56 PM
	 * 
	 * @Return : boolean
	 * 
	 * @Purpose : Check Whether the Particular Company Has Ratings
	 * 
	 * @param key
	 * @return
	 * 
	 */
	public boolean isItemExists( CompanyRegnKey key )
	{

		String query = null;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );

			query = "SELECT 1 FROM " + this.tableName_ + " WHERE regn_rel_key =? ";

			pretStmt = con_.prepareStatement( query );
			pretStmt.setString( 1, key.companyPhoneNo_ );

			ResultSet rs = pretStmt.executeQuery( );

			System.out.println( pretStmt );

			if( rs.next( ) )
				return true;
			else
				return false;

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::RattingsSummaryTable.isItemExists( ) " + ex );
			return false;
		}
		finally
		{
			this.closeConnection( );
		}

	}

	/**
	 * @Date : Aug 28, 2013 12:57:01 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get All the ratings summary list
	 * 
	 * @param data
	 * @return
	 * 
	 */
	public int getAllRatingsSummaryList( Map<CompanyRegnKey, RatingSummaryData> ratingMap )
	{

		String query = null;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );

			query = "SELECT * FROM " + this.tableName_;

			pretStmt = con_.prepareStatement( query );

			ResultSet rs = pretStmt.executeQuery( );

			int val = rsToRatingsSummaryDataList( rs, ratingMap );
			System.out.print( pretStmt );

			return val;
			// Success
		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::RattingsSummaryTable.getAllRatingsSummaryList( ) " + ex );

			return -1;
		}
		finally
		{
			this.closeConnection( );
		}

	}

	/**
	 * @Date : Aug 28, 2013 12:55:39 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Reading the information from the resultSet
	 * 
	 * @param rs
	 * @param data
	 * @return
	 * 
	 */
	private int rsToRatingsSummaryDataList( ResultSet rs, Map<CompanyRegnKey, RatingSummaryData> ratingMap )
	{
		RatingSummaryData data = null;

		try
		{
			while( rs.next( ) )
			{
				data = new RatingSummaryData( );

				data._regn_rel_key.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				data._first_rating_timestamp = rs.getTimestamp( "first_rating_timestamp" );
				data._last_rating_timestamp = rs.getTimestamp( "last_rating_timestamp" );
				data._avg_ratings = rs.getInt( "avg_ratings" );
				data._no_of_ratings = rs.getInt( "no_of_ratings" );

				ratingMap.put( data._regn_rel_key, data );

			}

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::RattingsSummaryTable.rsToRatingsSummaryDataList( ) " + ex );

			return -1;
		}

		return 0;
	}

	/**
	 * @Date : Aug 28, 2013 12:32:59 PM
	 * 
	 * @Return : RatingsSummaryRecord
	 * 
	 * @Purpose : Creating the Rating Summary Record Oject fromt he
	 *          RatingSummaryData
	 * 
	 * @param data
	 * @return
	 * 
	 */
	private RatingsSummaryRecord createRatingsSummaryRecord( RatingSummaryData data )
	{
		RatingsSummaryRecord record = new RatingsSummaryRecord( );
		record._regn_rel_key = data._regn_rel_key.companyPhoneNo_;
		record._first_rating_timestamp = data._first_rating_timestamp;
		record._last_rating_timestamp = data._last_rating_timestamp;
		record._avg_ratings = data._avg_ratings;
		record._no_of_ratings = data._no_of_ratings;
		return record;
	}

}
