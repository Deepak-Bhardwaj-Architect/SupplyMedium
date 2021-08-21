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
package db.myconn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import core.myconn.MyConnectionData;
import core.regn.UserProfileKey;
import db.utils.DBConnect;

import utils.ErrorLogger;
import utils.ErrorMaster;
import utils.StringHolder;
import utils.UserConnStatus;


/**
 * File:  UserConnectionTable.java 
 *
 * Created on 13-Aug-2013 5:09:37 PM
 */
public class MyConnectionTable
{
    private static ErrorMaster errorMaster_ = null;


	private String tableName_;
	
	private ErrorLogger errLogger_;

	/*
	 * Method : UserConnectionTable-- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public MyConnectionTable()
	{
           

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "my_connection";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	
	/*
	 * Method : insert( )
	 * 
	 * Input : UserConnectionData obj
	 * 
	 * Return : int
	 * 
	 * Purpose:It is used to insert the new user connection request to user_connection  table.
	 * Initial status of the request is 'Request Sent'
	 */

	public int insert( MyConnectionData userConnectionData )
	{
		MyConnectionTableUtils utils = new MyConnectionTableUtils( );

		MyConnectionRecord record = new MyConnectionRecord( );
		
		int result = utils.dataToRecord( userConnectionData, record );
		
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
        	errLogger_.logMsg( "Exception::MyConnectionTable.insert( RFQItemData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::MyConnectionTable.insert( RFQItemData ) - " + e );
			
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
	 * Input : isRelationExist obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check relation exist between given two connection. If exist return 1
	 * otherwise return 0. If any exception occur return negative value  
	 */

	public int isRelationExist( MyConnectionData userConnectionData )
	{
		MyConnectionTableUtils utils = new MyConnectionTableUtils( );

		MyConnectionRecord record = new MyConnectionRecord( );
		
		int result = utils.dataToRecord( userConnectionData, record );
		
		if( result != 0 )
		{
			utils = null; 
			
			record = null;
			
			return result;
		}
		
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		query = "SELECT * FROM " + tableName_ + " WHERE ( from_user_key = '" + record.fromUserKey_ + "'" +
				"AND to_user_key = '"+record.toUserKey_+"' ) OR ( from_user_key = '"+record.toUserKey_+"'"+
				"AND to_user_key = '"+record.fromUserKey_+"')";
		
		

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			
			ResultSet rs = stmt.executeQuery( query );
			
			if( rs.next( ) )
				return 1; // Connection relationship already exist
			else
				return 0; // Relationship not exist
			
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::MyConnectionTable.isRelationExist( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::MyConnectionTable.isRelationExist( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Exception
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
	 * Method : updateStatus( )
	 * 
	 * Input : fromUserKey,toUserKey,status
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to change the status of the connection using fromusekey and touserkey.
	 *
	 */

	public int updateStatus( UserProfileKey fromUserKey, UserProfileKey toUserKey, String status )
	{
		String updateQuery = "UPDATE " + this.tableName_ + " SET status='"+status+"' WHERE from_user_key='"+
				fromUserKey+"' AND to_user_key='"+toUserKey+"'";
		
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
        	errLogger_.logMsg( "Exception::MyConnectionTable.updateStatus( ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::MyConnectionTable.updateStatus( ) - " + e );
			
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
            	errLogger_.logMsg( "Exception::RFQTable.updateStatus( long rfgId,UserProfileKey toUserKey, String status ) - " + e2 );
    			
    			return -4;
            }
		}
	}
	

	/*
	 * Method : find( )
	 * 
	 * Input : UserConnectionData obj, list of UserConnectionData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the all the connection according to given userConnectionData object.
	 * After fetch the connection it add into connection list. So it is copied to caller classes.
	 */

	public int find( MyConnectionData userConnectionData, List<MyConnectionData> connections )
	{
		MyConnectionTableUtils utils = new MyConnectionTableUtils( );
		
		MyConnectionRecord record = new MyConnectionRecord( );

		String selectQuery = "SELECT * FROM " + this.tableName_;
		
		//Convert the userConnectionData to userConnectionRecord
		//( using MyConnectionTableUtils->dataToRecord method )
		
		int result = utils.dataToRecord( userConnectionData, record );
		
		if( result != 0)
		{
			utils = null; record = null;
			
			return result;
		}
		
		StringHolder query = new StringHolder( );
		
		query.value = "";
		
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
		
		errorMaster_.insert( "Query="+selectQuery );
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			//convert result to connections
			result = utils.rsToDataList( rs, connections,userConnectionData.fromUserKey_ );
			
			utils = null; record = null; query = null;
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::MyConnectionTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::MyConnectionTable.find() - " + e );
			
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
	 * Method: filter
	 * 
	 * Input: userProfileKey object, status,connections list
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches all the pending or connection from table depending upon the status.
	 * Get the result and assign to connection list so it is used to called classes.
	 */
	
	public int filter( UserProfileKey userKey, String status,List<MyConnectionData> connections )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		int result = 0;
		
		query = "SELECT * FROM " + tableName_ + " WHERE ( to_user_key = '" + userKey.email_ + "'" ;
		
		if( status.equals( UserConnStatus.status.ACCEPTED.getValue( ) ))
		{
			query +=" OR from_user_key='"+userKey.email_ +"')";
		}
		else
		{
			query +=")";
		}
		query +=" AND  status = '" +status + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			
			ResultSet rs = stmt.executeQuery( query );
			
			MyConnectionTableUtils utils = new MyConnectionTableUtils( );

			//convert result to connections
			result = utils.rsToDataList( rs, connections,userKey );
			
			utils = null;
			
			return result;
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::MyConnectionTable.filter( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::MyConnectionTable.filter( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Exception
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
	 * Method: find
	 * 
	 * Input: userProfileKey object, search text,connections list
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches the user connection according to given search text
	 */
	
	public int find( UserProfileKey userKey, String searchText,List<MyConnectionData> connections )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		int result = 0;
		
		query = "SELECT * FROM " + tableName_ + " WHERE ( to_user_key = '" + userKey.email_ + "'" ;
		
		
		query +=" OR from_user_key='"+userKey.email_ +"')";
		
			
		query +=" AND  status = '" +UserConnStatus.status.ACCEPTED + "' AND ( to_user_key LIKE '%" ;
		
		query += searchText+"%' OR from_user_key LIKE '%"+searchText+"%' OR from_user_name LIKE '%";
		
		query+= searchText+"%' OR to_user_name LIKE '%"+searchText+"%' )" ;

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			
			ResultSet rs = stmt.executeQuery( query );
			
			MyConnectionTableUtils utils = new MyConnectionTableUtils( );

			//convert result to connections
			result = utils.rsToDataList( rs, connections,userKey );
			
			utils = null;
			
			return result;
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::MyConnectionTable.filter( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::MyConnectionTable.filter( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Exception
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
	 * Method : delete( )
	 * 
	 * Input : fromUserKey, toUserKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the existing connection between fromUserKey and toUserKey.
	 * There is no relation between both of them after deleted the connection.
	 */

	public int delete( UserProfileKey fromUserKey, UserProfileKey toUserKey )
	{
		
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE from_user_key = '" + fromUserKey +
				"' AND to_user_key = '" + toUserKey + "'";

		errorMaster_.insert( "Query=" + query );

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
			String errorMessage = "Exception::MyConnectionTable.delete( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::MyConnectionTable.delete( ) - "
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
	
	
	
	//bala
	public int deleteConnection(MyConnectionData myConnData)
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
	
		query = "DELETE FROM " + tableName_ + " WHERE ( from_user_key = '"+myConnData.fromUserKey_.toString( )
				+"' AND to_user_key = '"+myConnData.toUserKey_.toString( )+"' AND status = 'Accepted' ) OR ( from_user_key = '"+myConnData.toUserKey_.toString( )
				+"' AND to_user_key = '"+myConnData.fromUserKey_.toString( )+"' AND status = 'Accepted' )";

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
			String errorMessage = "Exception::MyConnectionTable.deleteConnection( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::MyConnectionTable.deleteConnection( ) - "
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

}
