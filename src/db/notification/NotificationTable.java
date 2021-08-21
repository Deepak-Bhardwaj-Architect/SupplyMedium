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
package db.notification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import core.notification.NotificationCenterData;


import db.utils.DBConnect;
import utils.ErrorLogger;
import utils.StringHolder;

/**
 * File:  NotificationTable.java 
 *
 * Created on Sep 24, 2013 2:00:18 PM
 */
public class NotificationTable
{
	private String tableName_;
	
	private ErrorLogger errLogger_;
	
	/*
	 * Method : NotificationTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public NotificationTable()
    
	{
		
		this.tableName_ = "notification";
			
		errLogger_ = ErrorLogger.instance( );
		
	}
	
	
	/*
	 * Method : insert( )
	 * 
	 * Input : NotificationData obj
	 * 
	 * Return : int
	 * 
	 * Purpose:It is used to insert the new Notification to  Notification table.
	 */

	public int insert( NotificationCenterData notificationCenterData )
	{

		NotificationTableUtils utils = new NotificationTableUtils( );

		NotificationRecord record = new NotificationRecord( );
		
		int result = utils.dataToRecord( notificationCenterData, record );
		
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
        	errLogger_.logMsg( "Exception::NotificationTable.insert( NotificationCenterData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::NotificationTable.insert( NotificationCenterData ) - " + e );
			
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
	 * Input : notificationId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the existing Notification from Notification table
	 */

	public int delete( long notificationId )
	{
			Statement stmt = null;
			Connection con = null;
			String query = null;
			int deleteResult = 0;

			query = "DELETE FROM " + tableName_ + " WHERE notification_id = '" + notificationId+"'";

			//System.out.println( "Query=" + query );

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
				String errorMessage = "Exception::NotificationTable.delete( ) - "
				        + ex;

				errLogger_.logMsg( errorMessage );

				return -2;
			}
			catch( Exception ex )
			{
				ErrorLogger errLogger_ = ErrorLogger.instance( );
				String errorMessage = "Exception::NotificationTable.delete( ) - "
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
	 * Input : NotificationData obj, list of NotificationData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the all the NotificationData according to given NotificationData object.
	 * After fetch the NotificationData it add into notifications list. So it is copied to caller classes.
	 */

	public int find( NotificationCenterData notificationCenterData, List<NotificationCenterData> notifications )
	{
		NotificationTableUtils utils = new NotificationTableUtils( );
		
		NotificationRecord record = new NotificationRecord( );

		
		//Convert the notificationCenterData to notificationRecord
		//( using NotificationTableUtils->dataToRecord method )
		
		int result = utils.dataToRecord( notificationCenterData, record );
		

		
		StringHolder query=new StringHolder( );
		
		//selectQuery.value = "SELECT * FROM " + this.tableName_ ;
		
		query.value="";
		
		result=utils.getFilterString( record, query );
		
		String selectQuery = "SELECT * FROM " + this.tableName_ ;
		
		selectQuery=selectQuery+query.value;
		
		//System.out.println( "Select notification query =" +selectQuery);
		
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
		
		
		
		//System.out.println( "Query="+selectQuery );
		
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			
			
			//convert result to connections
			result = utils.rsToDataList( rs, notifications );
			
			utils = null; record = null; 
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::NotificationTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::NotificationTable.find() - " + e );
			
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
	

}
