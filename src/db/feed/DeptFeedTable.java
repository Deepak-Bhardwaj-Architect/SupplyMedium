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
import java.util.List;

import utils.ErrorLogger;
import core.feed.DeptFeedData;
import core.dept.DeptKey;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import db.utils.DBConnect;

/**
 * File:  DeptFeedTable.java 
 *
 * Created on May 7, 2013 10:23:40 AM
 */
public class DeptFeedTable
{
	ErrorLogger errLogger_ ;
	
	private String tableName_;
	
	/*Constructor*/
	
	public DeptFeedTable( )
	{
		errLogger_ = ErrorLogger.instance( );
		tableName_ = "dept_feed";
	}

	
	/*
	 * Method : insert( )
	 * 
	 * Input : DeptFeedData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the DeptFeedData values
	 * into the dept_feed table
	 */

	public int insert( DeptFeedData deptFeedData )
	{
		DeptFeedRecord record = createDeptFeedRecord( deptFeedData );
		
		String query = formInsertQuery( record );
		
		System.out.println( "query="+query );

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

				if( keys.next( ) )
				{
					deptFeedData.deptFeedId_ = keys.getInt( 1 );
				
					System.out.println( "feedId_"+deptFeedData.deptFeedId_ );
				}
				
				deptFeedData.createdTs_ = new Timestamp( System.currentTimeMillis( ) );
				
				return 0; // Successfully inserted
			}

			return -1; // Insert failed

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "Exception::DeptFeedTable.insert() - " + ex );

			return -2;
		}	
		catch( Exception ex )
		{
			errLogger_.logMsg( "Exception::DeptFeedTable.insert() - " + ex );
			
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
	 * Method : delete( )
	 * 
	 * Input : Long deptFeedId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the dept feed from
	 * dept_feed table
	 */

	public int delete( long deptFeedId )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
	
		query = "DELETE FROM " + tableName_ + " WHERE dept_feed_id = '" + deptFeedId + "'";

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
			String errorMessage = "Exception::DeptFeedTable.delete() - " + ex;
			
			errLogger.logMsg( errorMessage );

			return -2;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errorMessage = "Exception::DeptFeedTable.delete() - " + ex;
			
			errLogger.logMsg( errorMessage );

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
	 * Method : find( ) 
	 * 
	 * Input : deptKey , deptFeedList, from and no of feeds
	 * 
	 * Return :int
	 * 
	 * Purpose: It is used to get the dept feeds. And add to deptFeedList. So this list copied 
	 * to caller classes. from parameters define the start feed and count parameter define
	 * number of feeds from start feed.
	 */

	public int find( DeptKey deptKey, List<DeptFeedData> deptFeedList,int from,int count )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		String deptRelKeyStr = convertDeptKeyStr( deptKey );
		
		query = "SELECT * FROM " + tableName_ + " WHERE dept_rel_key = '" + deptRelKeyStr + "' " +
				"ORDER BY created_timestamp DESC LIMIT "+from+", "+count;
		
		System.out.println( "Query="+query );
		

		try
		
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				DeptFeedData deptFeedData = new DeptFeedData( );
				
				deptFeedData.deptFeedId_ = rs.getLong( "dept_feed_id" );
				
				
				CompanyRegnKey regnKey = new CompanyRegnKey( );
				
				regnKey.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				
				deptFeedData.regnKey_ = regnKey;
				
				regnKey = null;
				
				
				UserProfileKey userKey = new UserProfileKey( );
				
				userKey.email_ = rs.getString( "user_rel_key" );
				
				deptFeedData.userKey_ = userKey;
				
				userKey = null;
				
				
				
				DeptKey key = convertDeptKey( rs.getString( "dept_rel_key" ) );
				
				deptFeedData.deptKey_ = key;
				
				key = null;
				
				deptFeedData.feedTitle_ = rs.getString( "feed_title" );
				
				deptFeedData.deptFeed_	= rs.getString( "feed" );
				
				deptFeedData.companyFeedFlag_	= rs.getInt( "company_feed_flag" );
				
				deptFeedData.createdTs_	=	rs.getTimestamp( "created_timestamp" );
				
				deptFeedList.add( deptFeedData );
				
				deptFeedData = null;
			}

			return 0;
		}
		catch( SQLException ex )
		{
			String errorMessage = "Exception::DeptFeedTable.find() -  " + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		catch( Exception ex )
		{
			String errorMessage = "Exception::DeptFeedTable.find() -  " + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // SqlException
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
	 * Method : createDeptFeedRecord( )
	 * 
	 * Input : DeptFeedData obj
	 * 
	 * Return : DeptFeedRecord obj
	 * 
	 * Purpose: It converts DeptFeedData object to DeptFeedRecord object
	 */

	private DeptFeedRecord createDeptFeedRecord(
			DeptFeedData deptFeedData )
	{
		DeptFeedRecord record = new DeptFeedRecord( );

		record.deptRelKey_ 		= convertDeptKeyStr( deptFeedData.deptKey_ );
		record.companyFeedFlag_ = deptFeedData.companyFeedFlag_;
		// record.createdTs_ = deptFeedData.createdTs_;
		
		record.feed_ 			= deptFeedData.deptFeed_;
		record.feedTitle_ 		= deptFeedData.feedTitle_;
		record.regnRelKey_ 		= deptFeedData.regnKey_.toString( );

		record.userRelKey_ = deptFeedData.userKey_.email_;
		
		return record;
	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : DeptRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using DeptRecord and
	 * returns as string
	 */

	private String formInsertQuery( DeptFeedRecord record )
	{

		// form the query

		String insertQuery = null;
		String insertValues = "";

		insertValues += "(regn_rel_key, user_rel_key, dept_rel_key, ";
		insertValues += "feed_title, feed, company_feed_flag)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + record.regnRelKey_ + "', ";
		insertQuery = insertQuery + "'" + record.userRelKey_ + "', ";
		insertQuery = insertQuery + "'" + record.deptRelKey_ + "', ";
		insertQuery = insertQuery + "'" + record.feedTitle_ + "', ";
		insertQuery = insertQuery + "'" + record.feed_ + "', ";
		insertQuery = insertQuery + record.companyFeedFlag_ + "";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
	
	/*
	 * Method : convertDeptKeyStr( ) 
	 * 
	 * Input : DeptKey object 
	 * 
	 * Return : String of deptkey which is stored in db
	 * 
	 * Purpose: This method is used to convert deptkey object to deptkey string
	 */

	private String convertDeptKeyStr( DeptKey deptKey )
	{
		String deptKeyStr = deptKey.companyRegnKey_.companyPhoneNo_ + ":"
		        + deptKey.deptName_;
		return deptKeyStr;
	}

	/*
	 * Method : convertDeptKey( ) 
	 * 
	 * Input : String of deptrelkey which is stored in db 
	 * 
	 * Return : DeptKey object
	 * 
	 * Purpose: This method is used to convert the string value to DeptKey
	 * object
	 */

	private DeptKey convertDeptKey( String deptKeyStr )
	{
		DeptKey deptKey = new DeptKey( );

		String [ ] strArr = deptKeyStr.split( ":" );

		if( strArr.length > 1 )
		{
			CompanyRegnKey companyRegnKey = new CompanyRegnKey( );
			companyRegnKey.companyPhoneNo_=strArr[0];
			deptKey.companyRegnKey_=companyRegnKey;
			deptKey.deptName_ = strArr[1];
		}

		return deptKey;
	}
	
}
