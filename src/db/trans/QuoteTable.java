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
import java.util.List;

import utils.ErrorLogger;
import utils.StringHolder;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.trans.QuoteData;
import db.utils.DBConnect;

/**
 * File:  QuoteTable.java 
 *
 * Created on Jul 3, 2013 10:06:55 AM
 */
public class QuoteTable
{
	private String tableName_;
	private ErrorLogger errLogger_;

	/*
	 * Method : QuoteTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to in initialize the table name
	 */
	
	public QuoteTable()
	{
		this.tableName_ = "quote";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	
	/*
	 * Method : insert( )
	 * 
	 * Input : QuoteData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the QuoteData values into
	 * RFQ table.
	 * 1.Convert the quoteData to quoteRecord ( using QuoteTableUtils->dataToRecord method )
	 * 2.Form the insert query ( using QuoteTableUtils->formInsertQuery )
	 * 3.Execute the query
	 */

	public int insert( QuoteData quoteData )
	{
		QuoteTableUtils utils = new QuoteTableUtils( );

		QuoteRecord record = new QuoteRecord( );
		
		int result = utils.dataToRecord( quoteData, record );
		
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
	        result	= stmt.executeUpdate( insertQuery, Statement.RETURN_GENERATED_KEYS);
	        
			if( result > 0 ) 
			{
				ResultSet keys = stmt.getGeneratedKeys( );

				if( keys.next( ) )
				{
					quoteData.quoteId_ = keys.getInt( 1 );
				}				
			}
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::QuoteTable.insert( QuoteData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::QuoteTable.insert( QuoteData ) - " + e );
			
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
	 * Method : update( )
	 * 
	 * Input : QuoteData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the QuoteData values into
	 * Quote table using QuoteId.
	 * 1.Convert the quoteData to quoteRecord ( using QuoteTableUtils->dataToRecord method )
	 * 2.From the update string ( Using QuoteTableUtils->getUpdateString )
	 * 3.Form the query and execute
	 */

	public int update( QuoteData quoteData )
	{
		QuoteTableUtils utils = new QuoteTableUtils( );

		QuoteRecord record = new QuoteRecord( );
		
		int result = utils.dataToRecord( quoteData, record );
		
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
		
		System.out.println( "update Query="+updateQuery );
		 
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
        	errLogger_.logMsg( "Exception::QuoteTable.update( QuoteData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::QuoteTable.update( QuoteData ) - " + e );
			
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
	 * Method : updateStatus( )
	 * 
	 * Input : quoteId,toUserKey,status
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the status value into
	 * Quote table using quoteId.
	 * 1.Form the update string 
	 * 2.Form the query and execute
	 */

	public int updateStatus( long quoteId, UserProfileKey toUserKey, String status )
	{
		String updateQuery = "UPDATE " + this.tableName_ + " SET user_to='"+toUserKey.toString( )+"', " +
				"status='"+status+"' WHERE quote_id='"+quoteId+"'";
		
		System.out.println( "update Query="+updateQuery );
		 
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
        	errLogger_.logMsg( "Exception::QuoteTable.updateStatus( long, UserProfileKey, String ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::QuoteTable.updateStatus( long, UserProfileKey, String ) - " + e );
			
			return -3;
        }
		finally
		{
			try
			{
				
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
	 * Input : QuoteData obj, list of QuoteList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the Quote's and add in QuoteList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the quoteData to quoteRecord ( using QuoteTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using QuoteTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( QuoteData quoteData, List<QuoteData> QuoteList )
	{
		QuoteTableUtils utils = new QuoteTableUtils( );
		
		QuoteRecord record = new QuoteRecord( );

		String selectQuery = "SELECT * FROM " + this.tableName_;
		
		int result = utils.dataToRecord( quoteData, record );
		
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
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( query.value );
			
			result = utils.rsToDataList( rs, QuoteList );
			
			utils = null; record = null; query = null;
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::QuoteTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::QuoteTable.find() - " + e );
			
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
	 * Method : find( )
	 * 
	 * Input : CompanyRegnKey, UserProfileKey, list of QuoteList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the Quote's and add in QuoteList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the quoteData to quoteRecord ( using QuoteTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using QuoteTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( CompanyRegnKey regnKey, UserProfileKey userKey, List<QuoteData> QuoteList )
	{
		QuoteTableUtils utils = new QuoteTableUtils( );
		
		String selectQuery = "SELECT * FROM " + this.tableName_ + " WHERE ";
	
		selectQuery = selectQuery + "(from_regn_key = '" + regnKey.companyPhoneNo_ + "' AND ";
		selectQuery = selectQuery + "user_from = '" + userKey.email_ + "') OR ";
		selectQuery = selectQuery + "(to_regn_key = '" + regnKey.companyPhoneNo_ + "' AND ";
		selectQuery = selectQuery + "(user_to = '" + userKey.email_ + "' OR user_to='null')) ORDER BY created_timestamp DESC";
		
		System.out.println( "select query" +selectQuery);
		
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			//convert result to RFQItemList
			int result = utils.rsToDataList( rs, QuoteList );
			
			utils = null;
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::QuoteTable.find( CompanyRegnKey, UserProfileKey, List<QuoteData> ) - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::QuoteTable.find( CompanyRegnKey, UserProfileKey, List<QuoteData> ) - " + e );
			
			return -3;
        }
		finally
        {
            try
            {
            	utils = null;
            	
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
	 * Method : setPOCreatedFlag( )
	 * 
	 * Input : transId
	 * 
	 * 
	 * Return : int
	 * 
	 * Purpose: This is method used to change the po created flag to 1 when user
	 * create the po for Quote
	 */

	public int setPOCreatedFlag( long transId )
	{
		
		String updateQuery = "UPDATE " + this.tableName_ + " SET is_po_created = '1' WHERE trans_id='"+ transId +"'";
		 
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
        	errLogger_.logMsg( "Exception::RFQTable.setPOCreatedFlag( RFQData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::RFQTable.setPOCreatedFlag( RFQData ) - " + e );
			
			return -3;
        }
		finally
		{
			try
            {
				
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
	 * Method : delete( )
	 * 
	 * Input : RFQId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the RFQ from supply Medium
	 */

	public int delete( long RFQId )
	{
		return 0;
	}
	
	/************************************* NO MORE METHODS ALLOWED HERE ******************************/
}
