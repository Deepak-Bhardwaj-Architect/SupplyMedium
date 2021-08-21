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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import core.regn.CompanyRegnKey;
import core.userratings.UserRatingsData;
import db.utils.DBConnect;

import utils.ErrorLogger;
import utils.IntHolder;

/**
 * @FileName : RattingsTable.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Aug 24, 2013
 * 
 * @Purpose :
 * 
 */
public class UserRatingsTable
{
	private ErrorLogger	      errLogger_;
	private String	          tableName_;
	private Connection	      con_;
	private PreparedStatement	pretStmt	= null;

	/**
	 * Initialize the table Name
	 */
	public UserRatingsTable()
	{
		this.errLogger_ = ErrorLogger.instance( );
		this.tableName_ = "user_ratings";
		this.con_ = null;
		this.pretStmt = null;
	}

	/**
	 * @Date : Aug 28, 2013 11:23:12 AM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Close the Existing Connection
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
	 * @Date : Aug 24, 2013 2:07:24 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Add the Ratings To the Rating Table
	 * 
	 * @param data
	 * @return
	 * 
	 */
	public int add( UserRatingsData data )
	{
		try
		{
			UserRatingsRecord record = createRatingsRecord( data );
			con_ = DBConnect.instance( ).getConnection( );

			String insertQuery = null;

			insertQuery = " INSERT INTO " + this.tableName_
			        + " ( regn_rel_key, user_profile_key, reviewer_name,company_regn_key, review_title,comments,review_date,ratings )  ";
			insertQuery += " VALUES (?,?,?,?,?,?,?,?) ";

			pretStmt = con_.prepareStatement( insertQuery );
			pretStmt.setString( 1, record._regn_rel_key );
			pretStmt.setString( 2, record._user_profile_key );
			pretStmt.setString( 3, record._reviewer_name );
			pretStmt.setString( 4, record._company_regn_key );
			pretStmt.setString( 5, record._review_title );
			pretStmt.setString( 6, record._comments );
			pretStmt.setTimestamp( 7, record._reviewdate );
			pretStmt.setInt( 8, record._ratings );

			int val = pretStmt.executeUpdate( );

			System.out.println( "----------------------------" );
			System.out.println( pretStmt );

			if( val == 1 ) return 0;

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::UserRatingsTable.add( ) " + ex );
			return -1;
		}
		finally
		{
			this.closeConnection( );
		}

		return -1;
	}

	/**
	 * @Date : Aug 24, 2013 2:27:41 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Updating the Rating Table data
	 * 
	 * @param data
	 * @return
	 * 
	 */
	public int update( UserRatingsData data )
	{
		UserRatingsRecord record = null;
		record = createRatingsRecord( data );
		int updateResult = 0;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );

			String updateQuery = null;

			updateQuery = "UPDATE " + this.tableName_ + " SET ";
			updateQuery = updateQuery + " review_title = ?,comments = ?, ratings = ?,review_date = ?";
			updateQuery = updateQuery + "WHERE rattings_key= ?";

			pretStmt = con_.prepareStatement( updateQuery );
			pretStmt.setString( 1, record._review_title );
			pretStmt.setString( 2, record._comments );
			pretStmt.setInt( 3, record._ratings );
			pretStmt.setTimestamp( 4, record._reviewdate );
			pretStmt.setInt( 5, record._rattings_key );

			updateResult = pretStmt.executeUpdate( );
			System.out.println( "---------------------------" );
			System.out.println( updateQuery );

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
			errLogger_.logMsg( "SQLException::UserRatingsTable.update( ) " + ex );

			return -2;
		}

	}

	/**
	 * @Date : Aug 24, 2013 2:31:24 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Removing the Ratings from the Table
	 * 
	 * @param rattingsKey
	 * @return
	 * 
	 */
	public int remove( UserRatingsData rattingsKey )
	{
		int deleteResult = 0;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );

			String deleteQuery = null;
			deleteQuery = "DELETE FROM " + this.tableName_ + " WHERE rattings_key= ? ";
			pretStmt = con_.prepareStatement( deleteQuery );
			pretStmt.setInt( 1, rattingsKey._rattings_key );
			deleteResult = pretStmt.executeUpdate( );

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
			errLogger_.logMsg( "SQLException::UserRatingsTable.remove( ) for item::" + rattingsKey._rattings_key + " \n" + ex );

			return -2;
		}
		finally
		{
			closeConnection( );
		}

	}

	/**
	 * @Date : Aug 24, 2013 2:35:38 PM
	 * 
	 * @Return : boolean
	 * 
	 * @Purpose : Check Whether the Ratings by the Company Already Exist
	 * 
	 * @param data
	 * @return
	 * 
	 */
	public boolean isRattingsExists( UserRatingsData data, IntHolder rattingsKey )
	{

		UserRatingsRecord record = createRatingsRecord( data );
		String query = null;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );

			query = "SELECT rattings_key FROM " + this.tableName_ + " WHERE regn_rel_key =? and user_profile_key = ?";

			pretStmt = con_.prepareStatement( query );
			pretStmt.setString( 1, record._regn_rel_key );
			pretStmt.setString( 2, record._user_profile_key );

			ResultSet rs = pretStmt.executeQuery( );

			System.out.println( pretStmt );

			while( rs.next( ) )
			{
				rattingsKey.value = rs.getInt( "rattings_key" );
				return true;
			}

			return false;

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::UserRatingsTable.isRattingsExists( ) " + ex );
			return false;
		}
		finally
		{
			this.closeConnection( );
		}

	}

	/**
	 * @Date : Aug 28, 2013 12:54:41 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get the User Ratting List
	 * 
	 * @param key
	 * @param data
	 * @return
	 * 
	 */
	public int getRattingsList( CompanyRegnKey key, List<UserRatingsData> data )
	{

		String query = null;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );

			query = "SELECT * FROM " + this.tableName_ + " WHERE regn_rel_key = ? ORDER BY review_date DESC ";
			pretStmt = con_.prepareStatement( query );
			pretStmt.setString( 1, key.companyPhoneNo_ );

			ResultSet rs = pretStmt.executeQuery( );
			System.out.print( pretStmt );
			int val = rsToRatingsDataList( rs, data );

			return val;
			// Success
		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::UserRatingsTable.getRattingsList( ) " + ex );

			return -1;
		}
		finally
		{
			this.closeConnection( );
		}

	}

	/**
	 * @Date : Aug 24, 2013 2:49:42 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get the Average Ratings Value
	 * 
	 * @param key
	 * @param rating
	 * @return
	 * 
	 */
	public int getAverageRattings( CompanyRegnKey key, IntHolder averageRating )
	{

		String query = null;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );

			query = "SELECT AVG(ratings) as avgratings FROM " + this.tableName_ + " WHERE regn_rel_key = ?";
			pretStmt = con_.prepareStatement( query );
			pretStmt.setString( 1, key.companyPhoneNo_ );

			System.out.println( pretStmt );
			ResultSet rs = pretStmt.executeQuery( );

			while( rs.next( ) )
			{
				averageRating.value = rs.getInt( "avgratings" );
			}

			return 0;
		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::UserRatingsTable.getAverageRattings( ) " + ex );

			return -1;
		}
		finally
		{
			this.closeConnection( );
		}

	}

	/**
	 * @Date : Aug 28, 2013 6:57:28 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get the Total Number of Ratings
	 * 
	 * @param key
	 * @param noOfRatings
	 * @return
	 * 
	 */
	public int getNoOfRattings( CompanyRegnKey key, IntHolder noOfRatings )
	{

		String query = null;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );

			query = "SELECT COUNT(*) AS NoofRatings FROM " + this.tableName_ + " WHERE regn_rel_key = ?";
			pretStmt = con_.prepareStatement( query );
			pretStmt.setString( 1, key.companyPhoneNo_ );

			ResultSet rs = pretStmt.executeQuery( );

			System.out.println( pretStmt );

			while( rs.next( ) )
			{
				noOfRatings.value = rs.getInt( "NoofRatings" );
			}

			return 0;
		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::UserRatingsTable.getNoOfRattings( ) " + ex );

			return -1;
		}
		finally
		{
			this.closeConnection( );
		}

	}

	/**
	 * @Date : Aug 24, 2013 2:39:33 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Converting the Result set to RastingsData Class
	 * 
	 * @param rs
	 * @param data
	 * @return
	 * 
	 */
	private int rsToRatingsDataList( ResultSet rs, List<UserRatingsData> dataList )
	{
		UserRatingsData data = null;

		try
		{
			while( rs.next( ) )
			{
				data = new UserRatingsData( );

				data._rattings_key = rs.getInt( "rattings_key" );
				data._regn_rel_key.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				data._user_profile_key.email_ = rs.getString( "user_profile_key" );
				data._reviewer_name = rs.getString( "reviewer_name" );
				data._company_regn_key.companyPhoneNo_ = rs.getString( "company_regn_key" );
				data._review_title = rs.getString( "review_title" );
				data._comments = rs.getString( "comments" );
				data._reviewdate = rs.getTimestamp( "review_date" );
				data._ratings = rs.getInt( "ratings" );
				DateFormat target = new SimpleDateFormat( "dd MMM yyyy" );
				target.format( data._reviewdate );
				data._reviewdateFormated = target.format( data._reviewdate );

				dataList.add( data );

			}

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::UserRatingsTable.rsToRatingsDataList( ) " + ex );

			return -1;
		}

		return 0;
	}

	/**
	 * @Date : Aug 24, 2013 2:10:05 PM
	 * 
	 * @Return : RatingsRecord
	 * 
	 * @Purpose : Conver the Ratings Data to RatingsRecord Domain Data
	 * 
	 * @param data
	 * @return
	 * 
	 */
	private UserRatingsRecord createRatingsRecord( UserRatingsData data )
	{
		UserRatingsRecord ratingrec = new UserRatingsRecord( );
		ratingrec._rattings_key = data._rattings_key;
		ratingrec._regn_rel_key = data._regn_rel_key.companyPhoneNo_;
		ratingrec._user_profile_key = data._user_profile_key.email_;
		ratingrec._company_regn_key = data._company_regn_key.companyPhoneNo_;
		ratingrec._reviewer_name = data._reviewer_name;
		ratingrec._review_title = data._review_title;
		ratingrec._comments = data._comments;
		ratingrec._reviewdate = data._reviewdate;
		ratingrec._ratings = data._ratings;

		return ratingrec;
	}

}
