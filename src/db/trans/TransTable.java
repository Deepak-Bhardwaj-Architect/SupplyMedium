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
import core.trans.TransData;
import db.utils.DBConnect;

/**
 * File:  TransTable.java 
 *
 * Created on 21-Jun-2013 10:52:16 AM
 */
public class TransTable
{
	private String tableName_;
	
	ErrorLogger errLogger_;

	/*
	 * Method : TransTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public TransTable()
	{
		this.tableName_ = "transaction";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : TransData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the TransData values into
	 * transaction table.
	 * 1.Convert the transData to transRecord ( using TransTableUtils->dataToRecord method )
	 * 2.Form the insert query ( using TransTableUtils->formInsertQuery )
	 * 3.Execute the query
	 */

	public int insert( TransData transData )
	{
		System.out.println( "db start");
		TransTableUtils utils = new TransTableUtils( );

		TransRecord record = new TransRecord( );
		
		int result = utils.dataToRecord( transData, record );
		
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
	        result	= stmt.executeUpdate( insertQuery );
	        
			return 0;
	        
	       
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::TransTable.insert( TransData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransTable.insert( TransData ) - " + e );
			
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
	 * Input : TransData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the TransData values into
	 * transaction table using transId.
	 * 1.Convert the transData to transRecord ( using TransTableUtils->dataToRecord method )
	 * 2.From the update string ( Using TransTableUtils->getUpdateString )
	 * 3.Form the query and execute
	 */

	public int update( TransData transData )
	{
		TransTableUtils utils = new TransTableUtils( );

		TransRecord record = new TransRecord( );
		
		int result = utils.dataToRecord( transData, record );
		
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
			errLogger_.logMsg( "Exception::TransInquireTable.update( transData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransTable.update( transData ) - " + e );
			
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
	 * Input : TransData obj, list of transList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the transactions and add in RFQList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the transData to transRecord ( using TransTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using TransTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( TransData transData,List<TransData> transList )
	{
		TransTableUtils utils = new TransTableUtils( );
		
		TransRecord record = new TransRecord( );

		String selectQuery = "SELECT * FROM " + this.tableName_;
		
		//Convert the transData to TransRecord ( using TransTableUtils->dataToRecord method )
		int result = utils.dataToRecord( transData, record );
		
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
		
		System.out.println( "Trans fetch="+selectQuery );
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			//convert result to RFQItemList
			result = utils.rsToDataList( rs, transList );
			
			utils = null; record = null; query = null;
			
			return result;
			
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::TransTable.find( ) - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransTable.find( ) - " + e );
			
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
	 * Input : long transId, StringHolder transState
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the latest state of the transaction for given transaction id
	 */

	public int find( long transId, StringHolder transState )
	{
		
		String selectQuery = "SELECT status FROM " + this.tableName_ + " WHERE trans_id='"+transId+"' ORDER BY created_timestamp Desc";
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		System.out.println( "Trans fetch="+selectQuery );
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			if( rs.next( ) )
				transState.value = rs.getString( "status" );
			return 0;
			
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::TransTable.find( transId, transState ) - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransTable.find( transId, transState ) - " + e );
			
			return -3;
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
	 * Method : getMax( )
	 * 
	 * Input : long transId, StringHolder transState
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the maximum value of trans id.
	 */

	public int getMax( )
	{
		String selectQuery = "SELECT MAX(trans_id) AS max from " + tableName_;
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		System.out.println( "Trans fetch="+selectQuery );
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			if( rs.next( ) )
				return rs.getInt( "max" );
			
			return -1;
			
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::TransTable.getMax( ) - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransTable.getMax( ) - " + e );
			
			return -3;
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
	 * Input : from and to company regn keys and transData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the latest transaction between these two companies. And
	 * assign data to transData object so it is copied to caller classes.
	 */

	public int find( CompanyRegnKey from, CompanyRegnKey to, TransData transData )
	{
		
		String selectQuery = "SELECT * FROM " + this.tableName_ + " WHERE ( from_regn_key='"+from.toString( )
							+"' AND to_regn_key ='"+to.toString( )+"') OR (from_regn_key='"+to.toString( )
							+"' AND to_regn_key ='"+from.toString( )+"') ORDER BY created_timestamp Desc LIMIT 1";
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		System.out.println( "Trans fetch="+selectQuery );
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			if( rs.next( ) )
				transData.transId_ = rs.getLong( "trans_id" );
			
			
			return 0;
			
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::TransTable.find( ) - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransTable.find( ) - " + e );
			
			return -3;
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
	 * Input : from and to company regn keys and transData object
	 * 
	 * Return : int
	 * 
	 * Purpose: it is used to fetch all the transaction related record.
	 */

	public int find( long transId, List<TransData> transactions )
	{
		
		String selectQuery = "SELECT * FROM " + this.tableName_ + " WHERE trans_id = '"+transId+"' ORDER BY created_timestamp Desc";
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		System.out.println( "Trans fetch="+selectQuery );
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			while( rs.next( ) )
			{
				TransData transData = new TransData( );
				
				transData.transId_ = rs.getLong( "trans_id" );
				
				transData.status_  = rs.getString( "status" );
				
				transactions.add( transData );
				
				transData = null;
			}
				
			
			
			return 0;
			
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::TransTable.find( ) - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransTable.find( ) - " + e );
			
			return -3;
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
	 * Input : transId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the transaction from supply Medium
	 */

	public int delete( long RFQId )
	{
		return 0;
	}
	
	/************************************* NO MORE METHODS ALLOWED HERE ******************************/

}
