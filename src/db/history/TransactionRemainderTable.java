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
package db.history;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import utils.ErrorLogger;
import utils.StringHolder;
import core.history.TransReminderData;
import db.utils.DBConnect;

/**
 * File:  TransactionRemainderTable.java 
 *
 * Created on Sep 26, 2013 3:24:37 PM
 */
public class TransactionRemainderTable
{
	private String tableName_;
	
	private ErrorLogger errLogger_;
	

	/*
	 * Method : TransactionRemainderTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransactionRemainderTable()
    
	{
		
		this.tableName_ = "transaction_remainder";
			
		errLogger_ = ErrorLogger.instance( );
		
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : TransReminderData obj
	 * 
	 * Return : int
	 * 
	 * Purpose:It is used to insert the new remainder to  TransactionRemainder table.
	 */

    public int insert( TransReminderData reminderData )
    {
    	TransactionRemainderTableUtils utils=new TransactionRemainderTableUtils( );
    	
    	TransactionRemainderRecord record=new TransactionRemainderRecord( );
	    int result = utils.dataToRecord( reminderData, record );
		
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
	        result	= stmt.executeUpdate( insertQuery );
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::TransactionRemainderTable.insert( reminderData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransactionRemainderTable.insert( reminderData ) - " + e );
			
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
	 * Method : delete( )
	 * 
	 * Input : transReaminderId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the existing Remainder from
	 * TransactionRemainder table
	 */

	public int delete( long transReaminderId )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE trans_remainder_id = '" + transReaminderId + "'";

		System.out.println( "Query=" + query );

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
			String errorMessage = "Exception::TransactionRemainderTable.delete( ) - " + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::TransactionRemainderTable.delete( ) - " + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		finally
		{
			try
			{
				if( stmt != null )
				{
					stmt.close( );
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

	/*
	 * Method : find( )
	 * 
	 * Input : TransReminderData obj,
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the all the TransReminderData according to
	 * given TransReminderData object. After fetch the TransReminderData it add
	 * into remainterlist. So it is copied to caller classes.
	 */

	public int find( TransReminderData transReminderData, List<TransReminderData> remainders )
	{

		TransactionRemainderTableUtils utils = new TransactionRemainderTableUtils( );

		TransactionRemainderRecord record = new TransactionRemainderRecord( );

		// Convert the TransReminderData to transRemainderRecord
		// ( using TransactionRemainderTableUtils->dataToRecord method )

		int result = utils.dataToRecord( transReminderData, record );

		StringHolder query = new StringHolder( );
		// selectQuery.value = "SELECT * FROM " + this.tableName_ ;
		query.value = "";
		result = utils.getFilterString( record, query );
		String selectQuery = "SELECT * FROM " + this.tableName_;
		selectQuery = selectQuery + query.value;

		if( result != 0 )
		{
			utils = null;
			record = null;

			return result;
		}

		if( result != 0 )
		{
			utils = null;
			record = null;

			return result;
		}

		Statement statement = null;
		Connection con = null;
		ResultSet rs = null;

		System.out.println( "Query=" + selectQuery );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			statement = con.createStatement( );
			rs = statement.executeQuery( selectQuery );

			// convert result to connections
			result = utils.rsToDataList( rs, remainders );

			utils = null;
			record = null;

			return result;
		}
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::TransactionRemainderTable.find() - " + e );

			return -2;
		}
		catch( Exception e )
		{
			errLogger_.logMsg( "Exception::TransactionRemainderTable.find() - " + e );

			return -3;
		}
		finally
		{
			try
			{
				utils = null;
				record = null;

				if( statement != null )
				{
					statement.close( );
				}
				if( con != null )
				{
					con.close( );
				}
				if( rs != null )
				{
					rs.close( );
				}
			}
			catch( SQLException sQLException )
			{
			}
		}
	}

}
   
