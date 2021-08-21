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

import core.trans.TransInquireData;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  TransInquireTable.java 
 *
 * Created on 21-Jun-2013 3:07:19 PM
 */
public class TransInquireTable
{

	private String tableName_;
	
	ErrorLogger errLogger_;
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : TransInquireTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransInquireTable()
	{
		this.tableName_ = "trans_inquire";
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : TransInquireData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the TransInquireData values into
	 * trans_inquire table.
	 * 1.Convert the transInquireData to transInquireRecord ( using TransInquireTableUtils->dataToRecord method )
	 * 2.Form the insert query ( using TransInquireTableUtils->formInsertQuery )
	 * 3.Execute the query
	 */

	public int insert( TransInquireData transInquireData )
	{
		TransInquireTableUtils utils = new TransInquireTableUtils( );

		TransInquireRecord record = new TransInquireRecord( );
		
		int result = utils.dataToRecord( transInquireData, record );
		
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
        	errLogger_.logMsg( "Exception::TransInquireTable.insert( transInquireData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransInquireTable.insert( transInquireData ) - " + e );
			
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
	 * Input :  TransInquireData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the TransData values into
	 * transaction table using transId.
	 * 1.Convert the transInquireData to transInaquireRecord ( using TransInquireTableUtils->dataToRecord method )
	 * 2.From the update string ( Using TransInquireTableUtils->getUpdateString )
	 * 3.Form the query and execute
	 */

	public int update( TransInquireData transInquireData )
	{
		TransInquireTableUtils utils = new TransInquireTableUtils( );

		TransInquireRecord record = new TransInquireRecord( );
		
		int result = utils.dataToRecord( transInquireData, record );
		
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
			errLogger_.logMsg( "Exception::TransInquireTable.update( transInquireData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransInquireTable.update( transInquireData ) - " + e );
			
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
	 * Input : TransInquireData obj, list of transInquireList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the transactions and add in RFQList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the transInquireData to transInquireRecord ( using TransInquireTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using TransInquireTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( TransInquireData transInquireData,List<TransInquireData> transInquireList )
	{
		TransInquireTableUtils utils = new TransInquireTableUtils( );
		
		TransInquireRecord record = new TransInquireRecord( );

		String selectQuery = "SELECT * FROM " + this.tableName_;
		
		//Convert the transInquireData to TransInquireRecord ( using TransInquireTableUtils->dataToRecord method )
		int result = utils.dataToRecord( transInquireData, record );
		
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
		
		errorMaster_.insert( "TransInquire fetch = " + selectQuery );
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery(selectQuery );
			
			//convert result to RFQItemList
			result = utils.rsToDataList( rs, transInquireList );
			
			utils = null; record = null; query = null;
			
			return result;
			
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::TransInquireTable.find( ) - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransInquireTable.find( ) - " + e );
			
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
