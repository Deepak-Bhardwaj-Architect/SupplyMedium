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
import core.feed.CompanyFeedData;
import core.regn.CompanyRegnKey;
import db.utils.DBConnect;
import db.utils.DBEncode;
import utils.ErrorMaster;

/**
 * File:  CompanyFeedTable.java 
 *
 * Created on 22-Apr-2013 4:28:54 PM
 */

/*
 * Class  : CompanyFeedTable
 * 
 * Purpose: It is used to query the Company_feed table.
 * 
 */
public class CompanyFeedTable
{
private static ErrorMaster errorMaster_ = null;


	private String tableName_;
	
	/*
	 * Method : CompanyFeedTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used intialize the table name
	 */
	public CompanyFeedTable()
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "company_feed";
	}
	
	
	
	/*
	 * Method : insert( )
	 * 
	 * Input : CompanyFeedData object
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to insert the company feed data values into
	 * company_feed table
	 */

	public int insert( CompanyFeedData companyFeedData )
	{
		// Convert CompanyFeedData object to CompanyFeedRecord object
		CompanyFeedRecord record = createCompanyFeedRecord( companyFeedData );

		// Form the insert query
		String query = formInsertQuery( record );
		errorMaster_.insert( "Query="+query );

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
					companyFeedData.companyFeedId_ = keys.getInt( 1 );
				
					errorMaster_.insert( "" );
				}

				Date date = new Date( );
				
				companyFeedData.createdTimestamp_ =new Timestamp(date.getTime());
				
				return 0; // Successfully inserted
			}
				

			return -1; // Insert failed

		}

		catch( SQLException ex )
		{
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger.logMsg( "Exception::CompanyFeedTable.insert() - " + ex );

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
	 * Input : CompanyFeedData object
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to update the company feed data values into
	 * company_feed table using user feed id
	 */

	public int update( CompanyFeedData companyFeedData )
	{
		// Convert CompanyFeedData object to CompanyFeedRecord object
		CompanyFeedRecord record = createCompanyFeedRecord( companyFeedData );

		// Form the update query
		String query = "UPDATE " + tableName_;
		query = query + " SET feed = '" + record.feed_+"',";
		
		//query = query + " relative_path = '" +  record.relativePath_+"',";
		//query = query + " local_path = '" +  record.localPath_+"',";
		//query = query + " web_url = '" +  record.webURL_+"',";
		
		query = query + " feed_title = '" + record.feedTitle_+"',";
		query = query + " regn_rel_key = '" + record.regnKey_+"',";
		query = query + " user_rel_key = '" + record.userKey_+"'";
		query = query + " WHERE user_feed_id ='" + record.companyFeedId_ + "'";
		
		errorMaster_.insert( "Query="+query );

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
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger.logMsg( "Exception::CompanyFeedTable.update() - " + ex );

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
	 * Input : int companyFeedId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the company feed from
	 * company_feed table
	 */

	public int delete( long companyFeedId )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		

		query = "DELETE FROM " + tableName_ + " WHERE company_feed_id = '" + companyFeedId + "'";

		errorMaster_.insert( "Query=" + query );

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
			String errorMessage = "Exception::CompanyFeedTable.delete() - "
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
	 * Input : userKey , company feed list, from and no of feeds
	 * 
	 * Return :int
	 * 
	 * Purpose: It is used to get the company feeds. And add to companyFeedList. So this list copied 
	 * to caller classes. from parameters define the start feed and count parameter define
	 * number of feeds from start feed.
	 */

	public int find( CompanyRegnKey regnKey, List<CompanyFeedData> companyFeedList,int from,int count )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE regn_rel_key = '" + regnKey.toString( ) + "' " +
				"ORDER BY created_timestamp DESC LIMIT "+from+", "+count;
		
		errorMaster_.insert( "Query="+query );
		

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				
				CompanyFeedData companyFeedData = new CompanyFeedData( );
				
				companyFeedData.companyFeedId_ = rs.getInt( "company_feed_id" );
				
				companyFeedData.feed_ = rs.getString( "feed" );
				
				companyFeedData.feedTitle_= rs.getString( "feed_title" );
				
				companyFeedData.regnKey_.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				
				companyFeedData.userKey_.email_ = rs.getString( "user_rel_key" );
				
				companyFeedData.createdTimestamp_ = rs.getTimestamp( "created_timestamp" );
				
				companyFeedList.add( companyFeedData );
				
				companyFeedData = null;
				
			}

			return 0;
		}
		catch( SQLException ex )
		{
			errorMaster_.insert( "Exceprion:"+ex );
			
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception::CompanyFeedTable.find() -  " + ex;

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
	 * Method : createCompanyFeedRecord( )
	 * 
	 * Input : CompanyFeedData obj
	 * 
	 * Return : CompanyFeedRecord obj
	 * 
	 * Purpose: It converts CompanyFeedData object to CompanyFeedRecord object
	 */

	private CompanyFeedRecord createCompanyFeedRecord(
	        CompanyFeedData companyFeedData )
	{
		CompanyFeedRecord record 	= new CompanyFeedRecord( );

		record.companyFeedId_ 	= companyFeedData.companyFeedId_;
		record.regnKey_ 		= companyFeedData.regnKey_.toString( );
		record.userKey_ 		= companyFeedData.userKey_.toString( );
		
		record.feed_ 			= companyFeedData.feed_;
		record.feedTitle_ 		= companyFeedData.feedTitle_;

		//record.relativePath_	= companyFeedData.relativePath_;
		//record.localPath_		= companyFeedData.localPath_;
		//record.webURL_		= companyFeedData.webURL_;
		
		return record;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : CompanyFeedRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using CompanyFeedRecord and
	 * returns as string
	 */

	private String formInsertQuery( CompanyFeedRecord record )
	{

		// form the query
		
		DBEncode dbEncode = new DBEncode( );

		String insertQuery = null;
		String insertValues = null;
		
		//insertValues = "(regn_rel_key, user_rel_key, feed, feed_title, relative_path, local_path, web_url)";
		insertValues = "(regn_rel_key, user_rel_key, feed, feed_title)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.regnKey_ + "', ";
		insertQuery = insertQuery + "'" + record.userKey_ + "', ";
		
		insertQuery = insertQuery + "'" + dbEncode.encode( record.feed_ )+ "', ";
		insertQuery = insertQuery + "'" + dbEncode.encode( record.feedTitle_ )+ "'";
		
		//insertQuery = insertQuery + "'" + record.relativePath_ + "',";
		//insertQuery = insertQuery + "'" + record.localPath_ + "',";
		//insertQuery = insertQuery + "'" + record.webURL_ + "'";

		insertQuery = insertQuery + ")";

	
		return insertQuery;
	}
	
	
	
}
