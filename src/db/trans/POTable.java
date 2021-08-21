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
import core.trans.POData;
import db.utils.DBConnect;

/**
 * File:  POTable.java 
 *
 * Created on Jul 4, 2013 2:23:28 PM
 */
public class POTable
{
	private String tableName_;
	
	private ErrorLogger errLogger_;

	/*
	 * Method : POTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to in initialize the table name
	 */
	
	public POTable()
	{
		this.tableName_ = "po";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	
	/*
	 * Method : insert( )
	 * 
	 * Input : POData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the POData values into
	 * po table.
	 * 1.Convert the poData to poRecord ( using POTableUtils->dataToRecord method )
	 * 2.Form the insert query ( using POTableUtils->formInsertQuery )
	 * 3.Execute the query
	 */

	public int insert( POData poData )
	{
		POTableUtils utils = new POTableUtils( );

		PORecord record = new PORecord( );
		
		int result = utils.dataToRecord( poData, record );
		
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
					poData.poId_ = keys.getInt( 1 );
				}				
			}
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::POTable.insert( POData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::POTable.insert( POData ) - " + e );
			
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
	 * Input : POData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the POData values into
	 * Quote table using poId.
	 * 1.Convert the poData to poRecord ( using POTableUtils->dataToRecord method )
	 * 2.From the update string ( Using POTableUtils->getUpdateString )
	 * 3.Form the query and execute
	 */

	public int update( POData poData )
	{
		POTableUtils utils = new POTableUtils( );

		PORecord record = new PORecord( );
		
		int result = utils.dataToRecord( poData, record );
		
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
        	errLogger_.logMsg( "Exception::POTable.update( POData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::POTable.update( POData ) - " + e );
			
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
	 * Input : poId,toUserKey,status
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the status value into
	 * po table using poId.
	 * 1.Form the update string 
	 * 2.Form the query and execute
	 */

	public int updateStatus( long poId, UserProfileKey toUserKey, String status )
	{
		String updateQuery = "UPDATE " + this.tableName_ + " SET user_to='"+toUserKey.toString( )+"', " +
				"status='"+status+"' WHERE po_id='" + poId + "'";
		
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
        	errLogger_.logMsg( "Exception::POTable.updateStatus( long, UserProfileKey, String ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::POTable.updateStatus( long, UserProfileKey, String ) - " + e );
			
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
	 * Input : POData obj, list of POList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the PO's and add in POList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the poData to poRecord ( using POTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using POTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( POData poData, List<POData> poList )
	{
		POTableUtils utils = new POTableUtils( );
		
		PORecord record = new PORecord( );

		String selectQuery = "SELECT * FROM " + this.tableName_;
		
		int result = utils.dataToRecord( poData, record );
		
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
			
			result = utils.rsToDataList( rs, poList );
			
			utils = null; record = null; query = null;
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::POTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::POTable.find() - " + e );
			
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
	 * Input : CompanyRegnKey, UserProfileKey, list of POList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the PO's and add in POList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the poData to poRecord ( using POTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using PIOTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( CompanyRegnKey regnKey, UserProfileKey userKey, List<POData> poList )
	{
		POTableUtils utils = new POTableUtils( );
		
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
			int result = utils.rsToDataList( rs, poList );
			
			utils = null;
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::QuoteTable.find( CompanyRegnKey, UserProfileKey, List<POData> ) - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::QuoteTable.find( CompanyRegnKey, UserProfileKey, List<POData> ) - " + e );
			
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
	 * Purpose: It is used to fetch the last created po to generate new po num
	 */

	public int find( CompanyRegnKey regnKey, StringHolder poNum )
	{	
		String selectQuery = "SELECT po_num FROM " + this.tableName_ + " WHERE ";
	
		selectQuery = selectQuery + "from_regn_key = '" + regnKey.companyPhoneNo_ + "'";
		
		selectQuery = selectQuery + " ORDER BY created_timestamp DESC LIMIT 1";
		
		System.out.println( "select query" +selectQuery);
		
		
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
				poNum.value = rs.getString( "po_num" );
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
			errLogger_.logMsg( "Exception::POTable.find( CompanyRegnKey, StringHolder ) - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::POTable.find( CompanyRegnKey, StringHolder ) - " + e );
			
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
	 * Method : setInvoiceCreatedFlag( )
	 * 
	 * Input : transId
	 * 
	 * 
	 * Return : int
	 * 
	 * Purpose: This is method used to change the po created flag to 1 when user
	 * create the invoice for PO
	 */

	public int setInvoiceCreatedFlag( long transId )
	{
		
		String updateQuery = "UPDATE " + this.tableName_ + " SET is_invoice_created = '1' WHERE trans_id='"+ transId +"'";
		 
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
        	errLogger_.logMsg( "Exception::POTable.setPOCreatedFlag( RFQData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::POTable.setPOCreatedFlag( RFQData ) - " + e );
			
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
	 * Input : poId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the PO from supply Medium
	 */

	public int delete( long RFQId )
	{
		return 0;
	}
	
	/************************************* NO MORE METHODS ALLOWED HERE ******************************/
}
