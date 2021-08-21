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
package db.trans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.ErrorLogger;
import utils.StringHolder;
import core.trans.QuoteItemData;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  QuoteItemTable.java 
 *
 * Created on Jul 3, 2013 10:30:29 AM
 */
public class QuoteItemTable
{
	private String tableName_;
	
	private ErrorLogger errLogger_;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : RFQItemTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public QuoteItemTable()
	{
		this.tableName_ = "quote_item";
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errLogger_	= ErrorLogger.instance( );
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : QuoteItemData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the QuoteItemData values into
	 * quote_item table.
	 * 1.Convert the QuoteItemData to QuoteItemRecord ( using QuoteItemTableUtils->dataToRecord method )
	 * 2.Form the insert query ( using QuoteItemTableUtils->formInsertQuery )
	 * 3.Execute the query
	 */

	public int insert( QuoteItemData quoteItemData )
	{
		QuoteItemTableUtils utils = new QuoteItemTableUtils( );
		
		QuoteItemRecord record = new QuoteItemRecord( );
		
		int result = utils.dataToRecord( quoteItemData, record );
		
		if( result != 0 )
		{
			utils = null; record = null;
			
			return result;
		}
		
		String insertQuery = utils.formInsertQuery( record );
		
		 Statement stmt 	= null;
	     Connection con 	= null;
		
		try
        { 
	        con 	= DBConnect.instance( ).getConnection( );
	        stmt 	= con.createStatement( );
	        stmt.executeUpdate( insertQuery );
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::QuoteItemTable.insert( QuoteItemData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::QuoteItemTable.insert( QuoteItemData ) - " + e );
			
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
	 * Method : insert( )
	 * 
	 * Input : QuoteItemList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to insert the multiple items in a single query.
	 * 
	 */

	public int insert( List<QuoteItemData> quoteItemList )
	{
		QuoteItemTableUtils utils = new QuoteItemTableUtils( );
		
		List<QuoteItemRecord> recordList = new ArrayList<QuoteItemRecord>( );
		
		int result = utils.dataListToRecordList( quoteItemList, recordList );
		
		if( result != 0 )
		{
			utils = null; quoteItemList = null;
			
			return result;
		}
		
		String insertQuery = utils.formInsertQuery( recordList );
		
		 Statement stmt 	= null;
	     Connection con 	= null;
		
		try
        { 
	        con 	= DBConnect.instance( ).getConnection( );
	        stmt 	= con.createStatement( );
	        stmt.executeUpdate( insertQuery );
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::QuoteItemTable.insert( List<QuoteItemData> ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::QuoteItemTable.insert( List<QuoteItemData> ) - " + e );
			
			return -3;
        }
		finally
		{
			try
            {
				utils = null; quoteItemList = null;
				
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
	 * Method : update( )
	 * 
	 * Input : QuoteItemData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the QuoteItemData values into
	 * quote_item table using QuoteItemId.
	 * 1.Convert the QuoteItemData to QuoteItemRecord ( using QuoteItemTableUtils->dataToRecord method )
	 * 2.From the update string ( Using QuoteItemTableUtils->getUpdateString )
	 * 3.Form the query and execute
	 */

	public int update( QuoteItemData quoteItemData )
	{
		QuoteItemTableUtils utils = new QuoteItemTableUtils( );

		QuoteItemRecord record = new QuoteItemRecord( );
		
		int result = utils.dataToRecord( quoteItemData, record );
		
		if( result != 0 )
		{
			utils = null; record = null;
			
			return result;
		}
		
		StringHolder query = new StringHolder( );
		
		result = utils.getUpdateString( record, query );
		
		if( result != 0 )
		{
			utils = null; record = null; query = null;
			
			return result;
		}
		
		String updateQuery = "UPDATE " + this.tableName_ + " SET " + query.value;
		 
		 Statement stmt 	= null;
	     Connection con 	= null;
		
		try
        { 
	        con 	= DBConnect.instance( ).getConnection( );
	        stmt 	= con.createStatement( );
	        stmt.executeUpdate( updateQuery );
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::QuoteItemTable.update( QuoteItemData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::QuoteItemTable.update( QuoteItemData ) - " + e );
			
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
	 * Input : QuoteItemData obj, list of QuoteItemList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the Quote items and add in QuoteItemList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the QuoteItemData to QuoteItemRecord ( using QuoteItemTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using QuoteItemTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( QuoteItemData quoteItemData,List<QuoteItemData> quoteItemList )
	{
		QuoteItemTableUtils utils = new QuoteItemTableUtils( );
		
		QuoteItemRecord record = new QuoteItemRecord( );

		String selectQuery = "SELECT * FROM " + this.tableName_;
		
		int result = utils.dataToRecord( quoteItemData, record );
		
		if( result != 0)
		{
			utils = null; record = null;
			
			return result;
		}
		
		StringHolder query = new StringHolder( );
		
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
		
		errorMaster_.insert( "item query="+selectQuery );
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			result = utils.rsToDataList( rs, quoteItemList );
			
			utils = null; record = null; query = null;
			
			return result;
			
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::QuoteItemTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::QuoteItemTable.find() - " + e );
			
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
	 * Method : delete( )
	 * 
	 * Input : RFQId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the RFQ Item from supply Medium
	 */

	public int delete( long quoteId )
	{
		String query = "DELETE FROM " + this.tableName_ + " WHERE quote_id = '" + quoteId + "'";
		
		Statement statement = null;
		Connection con		= null;
		
		errorMaster_.insert( "Query="+query );
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			statement.execute( query );
			
			return 0;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::QuoteItemTable.delete() - " + e );
			
			return -1;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::QuoteItemTable.delete() - " + e );
			
			return -2;
        }
		finally
        {
            try
            {
                if (statement != null)
                {
                	statement.close();
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
	
	/************************************* NO MORE METHODS ALLOWED HERE ******************************/
}
