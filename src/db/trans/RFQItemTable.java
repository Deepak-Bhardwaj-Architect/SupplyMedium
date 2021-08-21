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

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ErrorLogger;
import utils.StringHolder;

import core.trans.RFQData;
import core.trans.RFQItemData;
import db.utils.DBConnect;


/**
 * File:  RFQItemTable.java 
 *
 * Created on 21-Jun-2013 12:03:51 PM
 */
public class RFQItemTable
{

	private String tableName_;
	
	private ErrorLogger errLogger_;

	/*
	 * Method : RFQItemTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public RFQItemTable()
	{
		this.tableName_ = "rfq_item";
		
		errLogger_	= ErrorLogger.instance( );
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : RFQItemData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the RFQItemData values into
	 * RFQ_item table.
	 * 1.Convert the RFQItemData to RFQItemRecord ( using RFQItemTableUtils->dataToRecord method )
	 * 2.Form the insert query ( using RFQItemTableUtils->formInsertQuery )
	 * 3.Execute the query
	 */

	public int insert( RFQItemData rfqItemData )
	{
		RFQItemTableUtils utils = new RFQItemTableUtils( );
		
		RFQItemRecord record = new RFQItemRecord( );
		
		int result = utils.dataToRecord( rfqItemData, record );
		
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
        	errLogger_.logMsg( "Exception::RFQItemTable.insert( RFQItemData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::RFQItemTable.insert( RFQItemData ) - " + e );
			
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
	 * Input : RFQItemList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to insert the multiple items in a single query.
	 * 
	 */

	public int insert( List<RFQItemData> RFQItemList )
	{
		RFQItemTableUtils utils = new RFQItemTableUtils( );
		
		List<RFQItemRecord> recordList = new ArrayList<RFQItemRecord>( );
		
		int result = utils.dataListToRecordList( RFQItemList, recordList );
		
		if( result != 0 )
		{
			utils = null; RFQItemList = null;
			
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
        	errLogger_.logMsg( "Exception::RFQItemTable.insert( List<RFQItemData> ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::RFQItemTable.insert( List<RFQItemData> ) - " + e );
			
			return -3;
        }
		finally
		{
			try
            {
				utils = null; RFQItemList = null;
				
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
	 * Input : RFQItemData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the RFQItemData values into
	 * RFQ_item table using RFQItemId.
	 * 1.Convert the RFQItemData to RFQItemRecord ( using RFQItemTableUtils->dataToRecord method )
	 * 2.From the update string ( Using RFQItemTableUtils->getUpdateString )
	 * 3.Form the query and execute
	 */

	public int update( RFQItemData rfqItemData )
	{
		RFQItemTableUtils utils = new RFQItemTableUtils( );

		RFQItemRecord record = new RFQItemRecord( );
		
		int result = utils.dataToRecord( rfqItemData, record );
		
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
        	errLogger_.logMsg( "Exception::RFQItemTable.update( RFQItemData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::RFQItemTable.update( RFQItemData ) - " + e );
			
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
	 * Input : RFQItemData obj, list of RFQItemList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the RFQ items and add in RFQItemList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the RFQItemData to RFQItemRecord ( using RFQItemTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using RFQItemTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( RFQItemData rfqItemData,List<RFQItemData> RFQItemList )
	{
		RFQItemTableUtils utils = new RFQItemTableUtils( );
		
		RFQItemRecord record = new RFQItemRecord( );

		String selectQuery = "SELECT * FROM " + this.tableName_;
		
		//Convert the RFQItemData to RFQItemRecord ( using RFQItemTableUtils->dataToRecord method )
		int result = utils.dataToRecord( rfqItemData, record );
		
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
		
		System.out.println( "item query="+selectQuery );
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			//convert result to RFQItemList
			result = utils.rsToDataList( rs, RFQItemList );
			
			utils = null; record = null; query = null;
			
			return result;
			
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::RFQItemTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::RFQItemTable.find() - " + e );
			
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

	public int delete( long RFQId )
	{
		String query = "DELETE FROM " + this.tableName_ + " WHERE rfq_id = '" + RFQId + "'";
		
		Statement statement = null;
		Connection con		= null;
		
		System.out.println( "Query="+query );
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			statement.execute( query );
			
			return 0;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::RFQItemTable.delete() - " + e );
			
			return -1;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::RFQItemTable.delete() - " + e );
			
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
	
	
	//This is for Search Buyer
	
	/**
	 * This method get rfqdata for matching item data record
	 */

	public int getRfqs( RFQItemData itemData, List<RFQData> rfqDataList )
	{
		String query = null;
		StringHolder whereClause = new StringHolder(  );
		
		whereClause.value ="";
		
		Statement 		stmt_ = null;
		Connection 		con_ = null;
		ResultSet 		rs		= null;
		
		try
		{
			con_ = DBConnect.instance( ).getConnection( );
			stmt_ = con_.createStatement( );
			
			this.formWhereClause( itemData, whereClause );
			
			query =  "SELECT DISTINCT rfq_id FROM " + this.tableName_ + whereClause.value;
			
			System.out.println( "Query: " + query );
			
			rs = stmt_.executeQuery( query );

			while( rs.next( ) )
			{
				RFQData rfqData = new RFQData( );
				
				rfqData.RFQId_ = rs.getLong( "rfq_id" );
				
				rfqDataList.add( rfqData );
				
				rfqData = null;
			}

			return 0;
			// Success
		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "Exception::RFQItemTable.getRfqs( ) - " 
					+ ex );
			return -1;
		}
		catch( Exception ex )
		{
			errLogger_.logMsg( "Exception::RFQItemTable.getRfqs( ) - " 
					+ ex );
			return -1;
		}
		finally
        {
            try
            {
                if (con_ != null)
                {
                	con_.close();
                	con_ = null;
                }
                if( stmt_ != null )
                {
                	stmt_.close();
                	stmt_ = null;
                }
                if( rs != null )
                {
                	rs.close();
                	rs = null;
                }
           }
           catch (SQLException sQLException)
           {
           }
        }
	}
	
	
	/**
	 * The most important method that queries the rfq Table based on the keyword. 
	 * The keyword search is performed on the item_desc field of the table.
	 * 
	 */
	public int getRfqs( String keyword, List<RFQData> rfqDataList )
	{
		
		String query = null;

		Statement 		stmt_ = null;
		Connection 		con_ = null;
		ResultSet 		rs		= null;
		
		try
		{
			con_ = DBConnect.instance( ).getConnection( );
			stmt_ = con_.createStatement( );
			
			query =  "SELECT DISTINCT rfq_id FROM " + this.tableName_;
			//query += " WHERE MATCH (item_name, item_desc) AGAINST ('" + keyword + "' IN boolean MODE)";
			
			query += " WHERE item_desc LIKE '%" + keyword + "%' ";
			
			System.out.println( "RFQItemTable:getCompanyKeys:query: " + query );
			rs = stmt_.executeQuery( query );

			while( rs.next( ) )
			{
				RFQData rfqData = new RFQData( );
				
				rfqData.RFQId_	= rs.getLong( "rfq_id" );
				
				rfqDataList.add( rfqData );
				
				rfqData = null;
			}

			return 0;
			// Success
		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::RFQItemTable : getRfqs " 
					+ ex );
			return -1;
			// SQLException occurred while filtering a Supplier Company
		}
		finally
        {
            try
            {
                if (con_ != null)
                {
                	con_.close();
                	con_ = null;
                }
                if( stmt_ != null )
                {
                	stmt_.close();
                	stmt_ = null;
                }
                if( rs != null )
                {
                	rs.close();
                	rs = null;
                }
           }
           catch (SQLException sQLException)
           {
           }
        }
	}
	
	
	/**
	 * 
	 */
	private void formWhereClause( RFQItemData itemData, StringHolder whereClause )
	{
		if( itemData.itemDesc_ != null && !(itemData.itemDesc_.equalsIgnoreCase( "" )) )
		{
			if( whereClause.value != "" )
			{
				whereClause.value += " OR";
			}
			else
			{
				whereClause.value += " WHERE";
			}
			
			whereClause.value += " item_desc = '" + itemData.itemDesc_ + "'";
		}
		
		if( itemData.partNo_ != null && !(itemData.partNo_.equalsIgnoreCase( "" )) )
		{
			if( whereClause.value != "" )
			{
				whereClause.value += " OR";
			}
			else
			{
				whereClause.value += " WHERE";
			}
			whereClause.value += " part_no = '" + itemData.partNo_ + "'";
		}
	}
	
	
	//////
	
	/************************************* NO MORE METHODS ALLOWED HERE ******************************/
}
