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
import core.trans.TransRejectData;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File: TransRejectTable.java 
 *
 * Created on Jul 10, 2013 12:05:54 PM
 */

public class TransRejectTable
{
	private String tableName_;
	
	ErrorLogger errLogger_;
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : TransRejectTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransRejectTable()
	{
		this.tableName_ = "trans_reject";
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : TransRejectData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the TransRejectData values into
	 * trans_reject table.
	 * 1.Convert the TransRejectData to TransRejectRecord ( using TransRejectTableUtils->dataToRecord method )
	 * 2.Form the insert query ( using TransRejectTableUtils->formInsertQuery )
	 * 3.Execute the query
	 */

	public int insert( TransRejectData transRejectData )
	{
		TransRejectTableUtils utils = new TransRejectTableUtils( );

		TransRejectRecord record = new TransRejectRecord( );
		
		int result = utils.dataToRecord( transRejectData, record );
		
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
        	errLogger_.logMsg( "Exception::TransRejectTable.insert( transRejectData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransRejectTable.insert( transRejectData ) - " + e );
			
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
	 * Input :  TransRejectData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the TransData values into
	 * transaction table using transId.
	 * 1.Convert the TransRejectData to transRejectRecord ( using TransRejectTableUtils->dataToRecord method )
	 * 2.From the update string ( Using TransRejectTableUtils->getUpdateString )
	 * 3.Form the query and execute
	 */

	public int update( TransRejectData transRejectData )
	{
		TransRejectTableUtils utils = new TransRejectTableUtils( );

		TransRejectRecord record = new TransRejectRecord( );
		
		int result = utils.dataToRecord( transRejectData, record );
		
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
			errLogger_.logMsg( "Exception::TransRejectTable.update( transRejectData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransRejectTable.update( transRejectData ) - " + e );
			
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
	 * Input : TransRejectData obj, list of transRejectList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the transactions and add in transRejectList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the TransRejectData to transRejectRecord ( using TransRejectTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using TransRejectTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( TransRejectData transRejectData,List<TransRejectData> transRejectList )
	{
		TransRejectTableUtils utils = new TransRejectTableUtils( );
		
		TransRejectRecord record = new TransRejectRecord( );

		String selectQuery = "SELECT * FROM " + this.tableName_;
		
		//Convert the TransRejectData to TransRejectRecord ( using TransRejectTableUtils->dataToRecord method )
		int result = utils.dataToRecord( transRejectData, record );
		
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
		
		errorMaster_.insert( "TransReject fetch = " + selectQuery );
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery(selectQuery );
			
			result = utils.rsToDataList( rs, transRejectList );
			
			utils = null; record = null; query = null;
			
			return result;
			
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::TransRejectTable.find( ) - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransRejectTable.find( ) - " + e );
			
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
	 * Input : transId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the transaction inquire details from supply Medium
	 */

	public int delete( long transId )
	{
		return 0;
	}
	
	/************************************* NO MORE METHODS ALLOWED HERE ******************************/
}
