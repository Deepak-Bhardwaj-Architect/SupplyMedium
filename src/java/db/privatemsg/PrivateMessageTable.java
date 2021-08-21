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
package db.privatemsg;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import core.privatemsg.PrivateMessageData;
import core.regn.UserProfileKey;

import utils.ErrorLogger;


import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  PrivateMessageTable.java 
 *
 * Created on Sep 19, 2013 5:52:20 PM
 */
public class PrivateMessageTable
{
private static ErrorMaster errorMaster_ = null;


	private String tableName_;
	
	private ErrorLogger errLogger_;
	
	/*
	 * Method : PrivateMessageTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public PrivateMessageTable()
    
	{
	

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );	
		this.tableName_ = "private_message";
			
		errLogger_ = ErrorLogger.instance( );
		
	}
	
	
	/*
	 * Method : insert( )
	 * 
	 * Input : PrivateMessageData obj
	 * 
	 * Return : int
	 * 
	 * Purpose:It is used to insert the new PrivateMessage to PrivateMessage table.
	 */

	public int insert( PrivateMessageData privateMessageData )
	{

		PrivateMessageTableUtils utils = new PrivateMessageTableUtils( );

		PrivateMessageRecord record = new PrivateMessageRecord( );
		
		int result = utils.dataToRecord( privateMessageData, record );
		
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
	        result	= stmt.executeUpdate( insertQuery);
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::PrivateMessageTable.insert( PrivateMessageData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::PrivateMessageTable.insert( PrivateMessageData ) - " + e );
			
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
	 * Input : privateMessageData
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the existing PrivateMessage from PrivateMessage table
	 */

	public int delete( PrivateMessageData privateMessageData )
	{
			Statement stmt = null;
			Connection con = null;
			String query = null;
		
			query = "DELETE FROM " + tableName_ + " WHERE ( from_user_key = '"+privateMessageData.fromUserKey_.toString( )
					+"' AND to_user_key = '"+privateMessageData.toUserKey_.toString( )+"' AND from_delete_flag = 1 AND " +
					" to_delete_flag =1 ) OR ( from_user_key = '"+privateMessageData.toUserKey_.toString( )
					+"' AND to_user_key = '"+privateMessageData.fromUserKey_.toString( )+"' AND to_delete_flag = 1 AND from_delete_flag = 1)";

			errorMaster_.insert( "Query=" + query );

			try
			{
				con = DBConnect.instance( ).getConnection( );
				
				stmt = con.createStatement( );
				
				stmt.executeUpdate( query );

				return 0;

				

			}
			catch( SQLException ex )
			{
				ErrorLogger errLogger_ = ErrorLogger.instance( );
				String errorMessage = "Exception::PrivateMessageTable.delete( ) - "
				        + ex;

				errLogger_.logMsg( errorMessage );

				return -2;
			}
			catch( Exception ex )
			{
				ErrorLogger errLogger_ = ErrorLogger.instance( );
				String errorMessage = "Exception::PrivateMessageTable.delete( ) - "
				        + ex;

				errLogger_.logMsg( errorMessage );

				return -2;
			}
			finally
	        {
	            try
	            {
	                if (stmt != null)
	                {
	                    stmt.close();
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
	
	/*
	 * Method : find( )
	 * 
	 * Input : PrivateMessageData obj, list of PrivateMessageData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the all the PrivateMessageData according to given PrivateMessageData object.
	 * After fetch the PrivateMessageData it add into messages list. So it is copied to caller classes.
	 */

	public int find( PrivateMessageData privateMessageData, List<PrivateMessageData> messages )
	{
		PrivateMessageTableUtils utils = new PrivateMessageTableUtils( );
		
		PrivateMessageRecord record = new PrivateMessageRecord( );

		
		//Convert the PrivateMessageData to privateMessageRecord
		
		//( using PrivateMessageTableUtils->dataToRecord method )
		
		int result = utils.dataToRecord( privateMessageData, record );
		

		String selectQuery = "SELECT * FROM " + this.tableName_ +" WHERE ( from_user_key = '" +
				record.fromUserKey_+"' AND to_user_key='"+record.toUserKey_+"' AND from_delete_flag = 0) OR " +
						"(from_user_key = '" +record.toUserKey_+"' " +
								"AND to_user_key='"+record.fromUserKey_+"' AND to_delete_flag = 0) ORDER BY created_timestamp ASC";
		
		if( result != 0)
		{
			utils = null; record = null;
			
			return result;
		}
		
		
		if( result != 0 )
		{
			utils = null; record = null;
			
			return result;
		}
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		
		
		errorMaster_.insert( "Query="+selectQuery );
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			//convert result to connections
			result = utils.rsToDataList( rs, messages );
			
			utils = null; record = null; 
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::PrivateMessageTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::PrivateMessageTable.find() - " + e );
			
			return -3;
        }
		finally
        {
            try
            {
            	utils = null; record = null; 
            	
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
	 * Input : PrivateMessageData obj, list of PrivateMessageData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the all the PrivateMessageData according to given PrivateMessageData object.
	 * After fetch the PrivateMessageData it add into messages list. So it is copied to caller classes.
	 */

	public int find( UserProfileKey userKey, List<PrivateMessageData> messages )
	{
		PrivateMessageTableUtils utils = new PrivateMessageTableUtils( );

		int result = 0;

		String selectQuery = "SELECT * FROM " + this.tableName_ +" WHERE ( from_user_key = '" +
				userKey.toString( )+"' AND from_delete_flag = 0 ) OR " +"(to_user_key = '" +userKey.toString( )+"' AND " +
						"to_delete_flag = 0 )";
		
		errorMaster_.insert( "Query="+selectQuery );
		
		if( result != 0)
		{
			utils = null; 
			
			return result;
		}
		
		
		if( result != 0 )
		{
			utils = null; 		
			return result;
		}
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
	
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			//convert result to connections
			result = utils.rsToDataList( rs, messages );
			
			utils = null; 
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::PrivateMessageTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::PrivateMessageTable.find() - " + e );
			
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
	 * Method : updateFromDeleteFlag( )
	 * 
	 * Input : PrivateMessageData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the from_delete_flag to 1. So this message is not display for
	 * the from user
	 */

	public int updateFromDeleteFlag( PrivateMessageData privateMessageData )
	{
		String updateQuery = "UPDATE " + this.tableName_ + " SET from_delete_flag=1 " +
				"WHERE from_user_key='"+privateMessageData.fromUserKey_.toString( )+"' AND to_user_key='" +
						privateMessageData.toUserKey_.toString( )+"'";
		
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
        	errLogger_.logMsg( "Exception::PrivateMessageTable.updateFromDeleteFlag( PrivateMessageData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::PrivateMessageTable.updateFromDeleteFlag( PrivateMessageData ) - " + e );
			
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
	 * Method : updateToDeleteFlag( )
	 * 
	 * Input : PrivateMessageData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the from_delete_flag to 1. So this message is not display for
	 * the from user
	 */

	public int updateToDeleteFlag( PrivateMessageData privateMessageData )
	{
		String updateQuery = "UPDATE " + this.tableName_ + " SET to_delete_flag = 1 " +
				"WHERE from_user_key = '"+privateMessageData.toUserKey_.toString( )+"' AND to_user_key = '" +
						privateMessageData.fromUserKey_.toString( )+"'";
		
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
        	errLogger_.logMsg( "Exception::PrivateMessageTable.updateToDeleteFlag( PrivateMessageData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::PrivateMessageTable.updateToDeleteFlag( PrivateMessageData ) - " + e );
			
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
}
