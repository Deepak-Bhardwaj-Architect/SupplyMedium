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
package db.newsroom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import core.newsroom.WatchListData;
import db.dept.DeptRecord;
import db.utils.DBConnect;
import utils.ErrorLogger;
import utils.ErrorMaster;
import utils.StringHolder;

/**
 * File:  WatchListTable.java 
 *
 * Created on 30-Aug-2013 4:03:49 PM
 */
public class WatchListTable
{

	private String tableName_;
	
	private ErrorLogger errLogger_;
	
	/*
	 * Method : WatchListTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WatchListTable()
	{
		
		this.tableName_ = "watchlist";
			
		errLogger_ = ErrorLogger.instance( );
		
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : watchListData obj
	 * 
	 * Return : int
	 * 
	 * Purpose:It is used to insert the new watchlist to watchlist table.
	 */

	public int insert( WatchListData watchListData )
	{
		WatchListTableUtils utils = new WatchListTableUtils( );

		WatchListRecord record = new WatchListRecord( );
		
		int result = utils.dataToRecord( watchListData, record );
		
		if( result != 0 )
		{
			utils = null; 
			
			record = null;
			
			return result;
		}
		
		String insertQuery = utils.formInsertQuery( record );
		
		 Statement stmt 	= null;
	     Connection con 	= null;
		
		try
        { 
	        con 	= DBConnect.instance( ).getConnection( );
	        stmt 	= con.createStatement( );
	        result	= stmt.executeUpdate( insertQuery);
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::WatchListTable.insert( WatchListData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::WatchListTable.insert( WatchListData ) - " + e );
			
			return -3;
        }
		finally
		{
			try
            {
				utils = null; record = null;
				
	            if( con != null )
	            {
	            	con.close();
	            }
	            if( stmt != null )
	            {
	            	stmt.close( );
	            }
            }
            catch( Exception e2 )
            {
	            
            }
		}
	}
	
	/*
	 * Method : find( )
	 * 
	 * Input : WatchListData obj, list of WatchListData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the all the watchListData according to given watchListData object.
	 * After fetch the watchListData it add into watchLists list. So it is copied to caller classes.
	 */

	public int find( WatchListData watchListData, List<WatchListData> watchLists )
	{
		WatchListTableUtils utils = new WatchListTableUtils( );
		
		WatchListRecord record = new WatchListRecord( );

		String selectQuery = "SELECT * FROM " + this.tableName_;
		
		//Convert the WatchListData to watchListRecord
		//( using watchListTableUtils->dataToRecord method )
		
		int result = utils.dataToRecord( watchListData, record );
		
		if( result != 0)
		{
			utils = null; record = null;
			
			return result;
		}
		
		StringHolder query = new StringHolder( );
		
		query.value = "";
		
		//Form Filter string
		result = utils.getFilterString( record, query );
		
		if( result != 0 )
		{
			utils = null; record = null; query = null;
			
			return result;
		}
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		selectQuery = selectQuery + query.value;
		 ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "Query="+selectQuery );
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			//convert result to connections
			result = utils.rsToDataList( rs, watchLists );
			
			utils = null; record = null; query = null;
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::WacthListTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::WacthListTable.find() - " + e );
			
			return -3;
        }
		finally
        {
            try
            {
            	utils = null; record = null; query = null;
            	
                if (statement != null)
                {
                	statement.close();
                }
                if( con != null )
                {
                    con.close();
                }
                if( rs != null )
                {
                	rs.close( );
                }
           }
           catch (SQLException sQLException)
           {
           }
        }
	}
	
	/*
	 * Method : isWatchListExist( )
	 * 
	 * Input : watchListData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is check the given watchlist is available or not
	 */

	public int isWatchListExist( WatchListData watchListData )
	{
		
		Statement stmt = null;
		Connection con = null;
		String query = null;

		WatchListTableUtils utils = new WatchListTableUtils( );
		
		WatchListRecord record = new WatchListRecord( );
		
		//Convert the watchListData to watchListRecord
		//( using WatchListTableUtils->dataToRecord method )
		
		int result = utils.dataToRecord( watchListData, record );
		
		if( result != 0)
		{
			utils = null; record = null;
			
			return result;
		}
		

		query = "SELECT * FROM " + tableName_ + " WHERE watchlist_name = '" + record.watchListName_+"' AND " +
				"user_rel_key='"+record.userKey_ +"'";
		
		//errorMaster_.insert( "query="+query );
		
		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				return 1;  // Record already exist
			}

			return 0;  // Record not exist
			
		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception :: WatchListTable : isExist - " + ex;

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
	 * Method : delete( )
	 * 
	 * Input : watchListId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the existing watchlist from watchlist table
	 */

	public int delete( long watchList )
	{
		
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE watchlist_id = '" + watchList+"'";

		//errorMaster_.insert( "Query=" + query );

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
			String errorMessage = "Exception::WatchListTable.delete( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::WatchListTable.delete( ) - "
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

}
