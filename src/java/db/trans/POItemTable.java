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
import core.trans.POItemData;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  POItemTable.java 
 *
 * Created on Jul 4, 2013 2:22:43 PM
 */
public class POItemTable
{
	private String tableName_;
	
	private ErrorLogger errLogger_;
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : POItemTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public POItemTable()
	{
		this.tableName_ = "po_item";
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errLogger_	= ErrorLogger.instance( );
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : POItemData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the POItemData values into
	 * po_item table.
	 * 1.Convert the POItemData to POItemRecord ( using POItemTableUtils->dataToRecord method )
	 * 2.Form the insert query ( using POItemTableUtils->formInsertQuery )
	 * 3.Execute the query
	 */

	public int insert( POItemData poItemData )
	{
		POItemTableUtils utils = new POItemTableUtils( );
		
		POItemRecord record = new POItemRecord( );
		
		int result = utils.dataToRecord( poItemData, record );
		
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
        	errLogger_.logMsg( "Exception::POItemTable.insert( POItemData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::POItemTable.insert( POItemData ) - " + e );
			
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
	 * Input : POItemList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to insert the multiple items in a single query.
	 * 
	 */

	public int insert( List<POItemData> poItemList )
	{
		POItemTableUtils utils = new POItemTableUtils( );
		
		List<POItemRecord> recordList = new ArrayList<POItemRecord>( );
		
		int result = utils.dataListToRecordList( poItemList, recordList );
		
		if( result != 0 )
		{
			utils = null; poItemList = null;
			
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
        	errLogger_.logMsg( "Exception::POItemTable.insert( List<POItemData> ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::POItemTable.insert( List<POItemData> ) - " + e );
			
			return -3;
        }
		finally
		{
			try
            {
				utils = null; poItemList = null;
				
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
	 * Input : POItemData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the POItemData values into
	 * po_item table using POItemId.
	 * 1.Convert the POItemData to POItemRecord ( using POItemTableUtils->dataToRecord method )
	 * 2.From the update string ( Using POItemTableUtils->getUpdateString )
	 * 3.Form the query and execute
	 */

	public int update( POItemData poItemData )
	{
		POItemTableUtils utils = new POItemTableUtils( );

		POItemRecord record = new POItemRecord( );
		
		int result = utils.dataToRecord( poItemData, record );
		
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
        	errLogger_.logMsg( "Exception::POItemTable.update( POItemData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::POItemTable.update( POItemData ) - " + e );
			
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
	 * Input : POItemData obj, list of POItemList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the PO items and add in POItemList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the POItemData to POItemRecord ( using POItemTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using POItemTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( POItemData poItemData,List<POItemData> poItemList )
	{
		POItemTableUtils utils = new POItemTableUtils( );
		
		POItemRecord record = new POItemRecord( );

		String selectQuery = "SELECT * FROM " + this.tableName_;
		
		int result = utils.dataToRecord( poItemData, record );
		
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
			
			result = utils.rsToDataList( rs, poItemList );
			
			utils = null; record = null; query = null;
			
			return result;
			
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::POItemTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::POItemTable.find() - " + e );
			
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
	 * Input : poId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the PO Item from supply Medium
	 */

	public int delete( long quoteId )
	{
		String query = "DELETE FROM " + this.tableName_ + " WHERE po_id = '" + quoteId + "'";
		
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
			errLogger_.logMsg( "Exception::POItemTable.delete() - " + e );
			
			return -1;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::POItemTable.delete() - " + e );
			
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
