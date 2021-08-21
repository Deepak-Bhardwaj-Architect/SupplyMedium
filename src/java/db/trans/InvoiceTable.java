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
import core.trans.InvoiceData;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  InvoiceTable.java 
 *
 * Created on Jul 6, 2013 2:28:34 PM
 */
public class InvoiceTable
{
	private String tableName_;
	private ErrorLogger errLogger_;
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : InvoiceTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to in initialize the table name
	 */
	
	public InvoiceTable()
	{
		this.tableName_ = "invoice";
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errLogger_ = ErrorLogger.instance( );
	}
	
	
	/*
	 * Method : insert( )
	 * 
	 * Input : POData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the InvoiceData values into
	 * invoice table.
	 * 1.Convert the invoiceData to invoiceRecord ( using InvoiceTableUtils->dataToRecord method )
	 * 2.Form the insert query ( using InvoiceTableUtils->formInsertQuery )
	 * 3.Execute the query
	 */

	public int insert( InvoiceData invoiceData )
	{
		InvoiceTableUtils utils = new InvoiceTableUtils( );

		InvoiceRecord record = new InvoiceRecord( );
		
		int result = utils.dataToRecord( invoiceData, record );
		
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
					invoiceData.invoiceId_ = keys.getInt( 1 );
				}				
			}
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::InvoiceTable.insert( InvoiceData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::InvoiceTable.insert( InvoiceData ) - " + e );
			
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
	 * Input : InvoiceData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the InvoiceData values into
	 * Invoice table using invoiceId.
	 * 1.Convert the invoiceData to invoiceRecord ( using InvoiceTableUtils->dataToRecord method )
	 * 2.From the update string ( Using InvoiceTableUtils->getUpdateString )
	 * 3.Form the query and execute
	 */

	public int update( InvoiceData invoiceData )
	{
		InvoiceTableUtils utils = new InvoiceTableUtils( );

		InvoiceRecord record = new InvoiceRecord( );
		
		int result = utils.dataToRecord( invoiceData, record );
		
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
		
		errorMaster_.insert( "update Query="+updateQuery );
		 
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
        	errLogger_.logMsg( "Exception::InvoiceTable.update( InvoiceData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::InvoiceTable.update( InvoiceData ) - " + e );
			
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
	 * Input : invoiceId,toUserKey,status
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the status value into
	 * po table using invoiceId.
	 * 1.Form the update string 
	 * 2.Form the query and execute
	 */

	public int updateStatus( long invoiceId, UserProfileKey toUserKey, String status )
	{
		String updateQuery = "UPDATE " + this.tableName_ + " SET user_to='"+toUserKey.toString( )+"', " +
				"status='"+status+"' WHERE invoice_id='" + invoiceId + "'";
		
		errorMaster_.insert( "update Query="+updateQuery );
		 
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
        	errLogger_.logMsg( "Exception::InvoiceTable.updateStatus( long, UserProfileKey, String ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::InvoiceTable.updateStatus( long, UserProfileKey, String ) - " + e );
			
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
	 * Input : InvoiceData obj, list of InvoiceList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the Invoice's and add in InvoiceList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the invoiceData to poRecord ( using InvoiceTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using InvoiceTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( InvoiceData invoiceData, List<InvoiceData> invoiceList )
	{
		InvoiceTableUtils utils = new InvoiceTableUtils( );
		
		InvoiceRecord record = new InvoiceRecord( );

		String selectQuery = "SELECT * FROM " + this.tableName_;
		
		int result = utils.dataToRecord( invoiceData, record );
		
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
			
			result = utils.rsToDataList( rs, invoiceList );
			
			utils = null; record = null; query = null;
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::InvoiceTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::InvoiceTable.find() - " + e );
			
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
	 * Input : CompanyRegnKey, UserProfileKey, list of InvoiceList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the Invoice's and add in InvoiceList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the poData to invoiceRecord ( using InvoiceTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using InvoiceTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( CompanyRegnKey regnKey, UserProfileKey userKey, List<InvoiceData> invoiceList )
	{
		InvoiceTableUtils utils = new InvoiceTableUtils( );
		
		String selectQuery = "SELECT * FROM " + this.tableName_ + " WHERE ";
	
		selectQuery = selectQuery + "(from_regn_key = '" + regnKey.companyPhoneNo_ + "' AND ";
		selectQuery = selectQuery + "user_from = '" + userKey.email_ + "') OR ";
		selectQuery = selectQuery + "(to_regn_key = '" + regnKey.companyPhoneNo_ + "' AND ";
		
		selectQuery = selectQuery + "(user_to = '" + userKey.email_ + "' OR user_to='null')) ORDER BY created_timestamp DESC";
		
		errorMaster_.insert( "select query" +selectQuery);
		
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			//convert result to invoiceList
			int result = utils.rsToDataList( rs, invoiceList );
			
			utils = null;
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::InvoiceTable.find( CompanyRegnKey, UserProfileKey, List<InvoiceData> ) - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::InvoiceTable.find( CompanyRegnKey, UserProfileKey, List<InvoiceData> ) - " + e );
			
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
	 * Method : find( )
	 * 
	 * Input : CompanyRegnKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the last created invoice to generate new invoice num
	 */

	public int find( CompanyRegnKey regnKey, StringHolder invoiceNum )
	{	
		String selectQuery = "SELECT invoice_no FROM " + this.tableName_ + " WHERE ";
	
		selectQuery = selectQuery + "from_regn_key = '" + regnKey.companyPhoneNo_ + "'";
		
		selectQuery = selectQuery + " ORDER BY created_timestamp DESC LIMIT 1";
		
		errorMaster_.insert( "select query" +selectQuery);
		
		
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
				invoiceNum.value = rs.getString( "invoice_no" );
				result = 0;
			}
			else
			{
				result = -1;
			}
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::InvoiceTable.find( CompanyRegnKey, StringHolder ) - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::InvoiceTable.find( CompanyRegnKey, StringHolder ) - " + e );
			
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
	
	
	public  int findAmount( long invoiceid, InvoiceData invoiceData )
    {
   

    
    	String selectQuery = "SELECT * FROM " + this.tableName_  + " WHERE ";
    	
		selectQuery = selectQuery + "trans_id = '" + invoiceData.transId_ + "'";
	
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
			
			double price=rs.getDouble( "total_price" );
			
			invoiceData.totalPrice_=price;
			 
			 errorMaster_.insert( "totalprice:"+invoiceData.totalPrice_ );
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
			errLogger_.logMsg( "Exception::InvoiceTable.findAmount() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::InvoiceTable.findAmount() - " + e );
			
			return -3;
        }
		finally
        {
            try
            {
            	selectQuery = null;
            	
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
	 * Purpose: It is used to delete the Invoice from supply Medium
	 */

	public int delete( long invoiceId )
	{
		return 0;
	}
	
	/************************************* NO MORE METHODS ALLOWED HERE ******************************/
}
