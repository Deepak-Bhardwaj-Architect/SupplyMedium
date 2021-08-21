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
package db.feed;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import utils.ErrorLogger;
import core.feed.UserFeedData;
import core.regn.UserProfileKey;
import db.utils.DBConnect;
import db.utils.DBEncode;

/**
 * File:  UserFeedTable.java 
 *
 * Created on 22-Apr-2013 4:28:54 PM
 */

/*
 * Class  : UserFeedTable
 * 
 * Purpose: It is used to query the user_feed table.
 * 
 */
public class UserFeedTable
{

	private String tableName_;
	
	/*
	 * Method : UserFeedTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used intialize the table name
	 */
	public UserFeedTable()
	{
		this.tableName_ = "user_feed";
	}
	
	
	
	/*
	 * Method : insert( )
	 * 
	 * Input : UserFeedData object
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to insert the user feed data values into
	 * user_feed table
	 */

	public int insert( UserFeedData userFeedData )
	{
		// Convert UserFeedData object to UserFeedRecord object
		UserFeedRecord record = createUserFeedRecord( userFeedData );

		// Form the insert query
		String query = formInsertQuery( record );
		
		
		System.out.println( "Query="+query );

		Statement stmt = null;
		Connection con = null;

		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			//insertResult = stmt.executeUpdate( query );
			
			
			insertResult =stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

		   
			if( insertResult > 0 ) 
			{
				ResultSet keys = stmt.getGeneratedKeys( );

				while( keys.next( ) )
				{
					userFeedData.userFeedId_ = keys.getInt( 1 );
				
					System.out.println( "" );
				}

				Date date = new Date( );
				
				userFeedData.createdTimestamp_ =new Timestamp(date.getTime());
				
				return 0; // Successfully inserted
			}
				

			return -1; // Insert failed

		}

		catch( SQLException ex )
		{
			System.out.println( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger.logMsg( "Exception::UserFeedTable.insert() - " + ex );

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
	 * Method : update( )
	 * 
	 * Input : UserFeedData object
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to update the user feed data values into
	 * user_feed table using user feed id
	 */

	public int update( UserFeedData userFeedData )
	{
		// Convert UserFeedData object to UserFeedRecord object
		UserFeedRecord record = createUserFeedRecord( userFeedData );

		// Form the update query
		String query = "UPDATE " + tableName_;
		query = query + " SET feed = '" + record.feed_+"',";
		
		query = query + " relative_path = '" +  record.relativePath_+"',";
		query = query + " local_path = '" +  record.localPath_+"',";
		query = query + " web_url = '" +  record.webURL_+"',";
		
		query = query + " feed_title = '" + record.feedTitle_+"',";
		query = query + " regn_rel_key = '" + record.regnKey_+"',";
		query = query + " user_rel_key = '" + record.userKey_+"'";
		query = query + " WHERE user_feed_id ='" + record.userFeedId_ + "'";
		
		System.out.println( "Query="+query );

		Statement stmt = null;
		Connection con = null;

		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			insertResult = stmt.executeUpdate( query );

			if( insertResult > 0 ) 
				return 0; // Successfully inserted

			return -1; // Insert failed

		}

		catch( SQLException ex )
		{
			System.out.println( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger.logMsg( "Exception::UserFeedTable.update() - " + ex );

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
	 * Method : delete( )
	 * 
	 * Input : String userFeedId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the user feed from
	 * user_feed table
	 */

	public int delete( int userFeedId )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		

		query = "DELETE FROM " + tableName_ + " WHERE user_feed_id = '" + userFeedId + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) 
				return 0;  // successfully deleted

			return -1; // Delete failed

		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errorMessage = "Exception::UserFeedTable.delete() - "
			        + ex;

			errLogger.logMsg( errorMessage );

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
	 * Method : find( ) 
	 * 
	 * Input : userKey , user feed list, from and no of feeds
	 * 
	 * Return :int
	 * 
	 * Purpose: It is used to get the user feeds. And add to userFeedList. So this list copied 
	 * to caller classes. from parameters define the start feed and count parameter define
	 * number of feeds from start feed.
	 */

	public int find( UserProfileKey userKey, List<UserFeedData> userFeedList,int from,int count )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE user_rel_key = '" + userKey.toString( ) + "' " +
				"ORDER BY created_timestamp DESC LIMIT "+from+", "+count;
		
		System.out.println( "Query="+query );
		

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				
				UserFeedData userFeedData = new UserFeedData( );
				
				userFeedData.userFeedId_ = rs.getInt( "user_feed_id" );
				
				userFeedData.feed_ = rs.getString( "feed" );
				
				userFeedData.feedTitle_= rs.getString( "feed_title" );
				
				userFeedData.relativePath_ = rs.getString( "relative_path" );
				
				userFeedData.localPath_ = rs.getString( "local_path" );
				
				userFeedData.webURL_ = rs.getString( "web_url" );
				
				userFeedData.regnKey_.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				
				userFeedData.userKey_.email_ = rs.getString( "user_rel_key" );
				
				userFeedData.createdTimestamp_ = rs.getTimestamp( "created_timestamp" );
				
				userFeedList.add( userFeedData );
				
				userFeedData = null;
				
			}

			return 0;
		}
		catch( SQLException ex )
		{
			System.out.println( "Exceprion:"+ex );
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception::UserFeedTable.find() -  " + ex;

			errLogger.logMsg( errorMessage );

			return -1; // SqlException
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
	 * Method : find( ) 
	 * 
	 * Input : userKey , user feed list, from and no of feeds
	 * 
	 * Return :int
	 * 
	 * Purpose: It is used to get the user's feeds. And add to userFeedList. So this list copied 
	 * to caller classes. from parameters define the start feed and count parameter define
	 * number of feeds from start feed.
	 */

	public int find( List<UserProfileKey> userKeys, List<UserFeedData> userFeedList,int from,int count )
	{
		Statement stmt = null;
		Connection con = null;
		String query = "";
		
		
		int i=0;
		
		
		
		for( UserProfileKey userProfileKey : userKeys )
        {
	        if( i!= 0 )
	        	query += " UNION ALL ";
	        
	        query += "( Select * FROM "+ tableName_ + " WHERE user_rel_key ='"+userProfileKey.toString( )+"' ";
	        query +=  " ORDER BY created_timestamp DESC LIMIT "+from+", "+count+" )";
	        
	        i++;
        }
		
		query += " ORDER BY created_timestamp DESC";
		
		System.out.println( "Query="+query );
		

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				
				UserFeedData userFeedData = new UserFeedData( );
				
				userFeedData.userFeedId_ = rs.getInt( "user_feed_id" );
				
				userFeedData.feed_ = rs.getString( "feed" );
				
				userFeedData.feedTitle_= rs.getString( "feed_title" );
				
				userFeedData.relativePath_ = rs.getString( "relative_path" );
				
				userFeedData.localPath_ = rs.getString( "local_path" );
				
				userFeedData.webURL_ = rs.getString( "web_url" );
				
				userFeedData.regnKey_.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				
				userFeedData.userKey_.email_ = rs.getString( "user_rel_key" );
				
				userFeedData.createdTimestamp_ = rs.getTimestamp( "created_timestamp" );
				
				userFeedList.add( userFeedData );
				
				userFeedData = null;
				
			}

			return 0;
		}
		catch( SQLException ex )
		{
			System.out.println( "Exceprion:"+ex );
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception::UserFeedTable.find() -  " + ex;

			errLogger.logMsg( errorMessage );

			return -1; // SqlException
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
	 * Method : find( ) 
	 * 
	 * Input : userKey , user feed list, lastFeedId
	 * 
	 * Return :int
	 * 
	 * Purpose: It is used to get the latest user's feeds. And add to userFeedList. So this list copied 
	 * to caller classes. from parameters define the start feed and count parameter define
	 * number of feeds from start feed.
	 */

	public int find( List<UserProfileKey> userKeys, List<UserFeedData> userFeedList,int lastFeedId )
	{
		Statement stmt = null;
		Connection con = null;
		String query = "";
		
		
		int i=0;
		
		
		
		for( UserProfileKey userProfileKey : userKeys )
        {
	        if( i!= 0 )
	        	query += " UNION ALL ";
	        
	        query += "( Select * FROM "+ tableName_ + " WHERE user_rel_key ='"+userProfileKey.toString( )+"' ";
	        query +=  " AND user_feed_id > "+lastFeedId+" ORDER BY created_timestamp DESC  )";
	        
	        i++;
        }
		
		query += " ORDER BY created_timestamp DESC";
		
		System.out.println( "Query="+query );
		

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				
				UserFeedData userFeedData = new UserFeedData( );
				
				userFeedData.userFeedId_ = rs.getInt( "user_feed_id" );
				
				userFeedData.feed_ = rs.getString( "feed" );
				
				userFeedData.feedTitle_= rs.getString( "feed_title" );
				
				userFeedData.relativePath_ = rs.getString( "relative_path" );
				
				userFeedData.localPath_ = rs.getString( "local_path" );
				
				userFeedData.webURL_ = rs.getString( "web_url" );
				
				userFeedData.regnKey_.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				
				userFeedData.userKey_.email_ = rs.getString( "user_rel_key" );
				
				userFeedData.createdTimestamp_ = rs.getTimestamp( "created_timestamp" );
				
				userFeedList.add( userFeedData );
				
				userFeedData = null;
				
			}

			return 0;
		}
		catch( SQLException ex )
		{
			System.out.println( "Exceprion:"+ex );
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception::UserFeedTable.find() -  " + ex;

			errLogger.logMsg( errorMessage );

			return -1; // SqlException
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
	 * Method : createUserFeedRecord( )
	 * 
	 * Input : UserFeedData obj
	 * 
	 * Return : UserFeedRecord obj
	 * 
	 * Purpose: It converts UserFeedData object to UserFeedRecord object
	 */

	private UserFeedRecord createUserFeedRecord(
	        UserFeedData userFeedData )
	{
		UserFeedRecord record 	= new UserFeedRecord( );

		record.userFeedId_ 		= userFeedData.userFeedId_;
		record.regnKey_ 		= userFeedData.regnKey_.toString( );
		record.userKey_ 		= userFeedData.userKey_.toString( );
		
		record.feed_ 			= userFeedData.feed_;
		record.feedTitle_ 		= userFeedData.feedTitle_;

		record.relativePath_	= userFeedData.relativePath_;
		record.localPath_		= userFeedData.localPath_;
		record.webURL_			= userFeedData.webURL_;
		
		return record;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : UserFeedRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using UserFeedRecord and
	 * returns as string
	 */

	private String formInsertQuery( UserFeedRecord record )
	{

		// form the query
		
		DBEncode dbEncode = new DBEncode( );

		String insertQuery = null;
		String insertValues = null;
		
		insertValues = "(regn_rel_key, user_rel_key, feed, feed_title, relative_path, local_path, web_url)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.regnKey_ + "', ";
		insertQuery = insertQuery + "'" + record.userKey_ + "', ";
		
		insertQuery = insertQuery + "'" + dbEncode.encode( record.feed_ )+ "', ";
		insertQuery = insertQuery + "'" + dbEncode.encode(record.feedTitle_ ) + "', ";
		
		insertQuery = insertQuery + "'" + record.relativePath_ + "',";
		insertQuery = insertQuery + "'" + record.localPath_ + "',";
		insertQuery = insertQuery + "'" + record.webURL_ + "'";

		insertQuery = insertQuery + ")";
		
		dbEncode = null;

	
		return insertQuery;
	}
	
	
	
}
