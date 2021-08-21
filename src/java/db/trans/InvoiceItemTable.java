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
import core.trans.InvoiceItemData;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  InvoiceItemTable.java 
 *
 * Created on Jul 6, 2013 2:29:03 PM
 */

public class InvoiceItemTable
{
	private String tableName_;
	
	private ErrorLogger errLogger_;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : InvoiceItemTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public InvoiceItemTable()
	{
		this.tableName_ = "invoice_item";
		
		errLogger_	= ErrorLogger.instance( );
                
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : InvoiceItemData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the InvoiceItemData values into
	 * po_item table.
	 * 1.Convert the InvoiceItemData to InvoiceItemRecord ( using InvoiceItemTableUtils->dataToRecord method )
	 * 2.Form the insert query ( using InvoiceItemTableUtils->formInsertQuery )
	 * 3.Execute the query
	 */

	public int insert( InvoiceItemData invoiceItemData )
	{
		InvoiceItemTableUtils utils = new InvoiceItemTableUtils( );
		
		InvoiceItemRecord record = new InvoiceItemRecord( );
		
		int result = utils.dataToRecord( invoiceItemData, record );
		
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
        	errLogger_.logMsg( "Exception::InvoiceItemTable.insert( InvoiceItemData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::InvoiceItemTable.insert( InvoiceItemData ) - " + e );
			
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
	 * Input : InvoiceItemList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to insert the multiple items in a single query.
	 * 
	 */

	public int insert( List<InvoiceItemData> invoiceItemList )
	{
		InvoiceItemTableUtils utils = new InvoiceItemTableUtils( );
		
		List<InvoiceItemRecord> recordList = new ArrayList<InvoiceItemRecord>( );
		
		int result = utils.dataListToRecordList( invoiceItemList, recordList );
		
		if( result != 0 )
		{
			utils = null; invoiceItemList = null;
			
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
        	errLogger_.logMsg( "Exception::InvoiceItemTable.insert( List<InvoiceItemData> ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::InvoiceItemTable.insert( List<InvoiceItemData> ) - " + e );
			
			return -3;
        }
		finally
		{
			try
            {
				utils = null; invoiceItemList = null;
				
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
	 * Input : InvoiceItemData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the InvoiceItemData values into
	 * invoice_item table using InvoiceItemId.
	 * 1.Convert the InvoiceItemData to InvoiceItemRecord ( using InvoiceItemTableUtils->dataToRecord method )
	 * 2.From the update string ( Using InvoiceItemTableUtils->getUpdateString )
	 * 3.Form the query and execute
	 */

	public int update( InvoiceItemData invoiceItemData )
	{
		InvoiceItemTableUtils utils = new InvoiceItemTableUtils( );

		InvoiceItemRecord record = new InvoiceItemRecord( );
		
		int result = utils.dataToRecord( invoiceItemData, record );
		
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
        	errLogger_.logMsg( "Exception::InvoiceItemTable.update( InvoiceItemData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::InvoiceItemTable.update( InvoiceItemData ) - " + e );
			
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
	 * Input : InvoiceItemData obj, list of InvoiceItemList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the Invoice items and add in InvoiceItemList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the InvoiceItemData to InvoiceItemRecord ( using InvoiceItemTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using InvoiceItemTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( InvoiceItemData invoiceItemData,List<InvoiceItemData> invoiceItemList )
	{
		InvoiceItemTableUtils utils = new InvoiceItemTableUtils( );
		
		InvoiceItemRecord record = new InvoiceItemRecord( );

		String selectQuery = "SELECT * FROM " + this.tableName_;
		
		int result = utils.dataToRecord( invoiceItemData, record );
		
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
			
			result = utils.rsToDataList( rs, invoiceItemList );
			
			utils = null; record = null; query = null;
			
			return result;
			
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::InvoiceItemTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::InvoiceItemTable.find() - " + e );
			
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
	 * Input : invoiceId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the Invoice Item from supply Medium
	 */

	public int delete( long invoiceId )
	{
		String query = "DELETE FROM " + this.tableName_ + " WHERE invoice_id = '" + invoiceId + "'";
		
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
			errLogger_.logMsg( "Exception::InvoiceItemTable.delete() - " + e );
			
			return -1;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::InvoiceItemTable.delete() - " + e );
			
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
	
	
	
	public  int getCurrency( long invoiceId,InvoiceItemData invoiceItemData  )
    {    	
    	String selectQuery = "SELECT * FROM " +  this.tableName_  + " WHERE ";
    	
	
		selectQuery = selectQuery + "invoice_id = '" + invoiceId+ "'";
	
		errorMaster_.insert( "select query=" +selectQuery);
		
		
		
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
					
			int result = 0;
			
			if( rs.next( ) )
			{
			
				String currency=rs.getString( "currency" );
			
				invoiceItemData.currency_=currency;
			 
				errorMaster_.insert( "currency:"+invoiceItemData.currency_);
				
				result =0;
			}
			else 
			{
				result=-1;
			}
			
			
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::InvoiceItemTable.getCurrency() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::InvoiceItemTable.getCurrency() - " + e );
			
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

	
	/************************************* NO MORE METHODS ALLOWED HERE ******************************/
}
