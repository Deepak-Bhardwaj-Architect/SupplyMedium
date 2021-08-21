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
package db.newsroom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.newsroom.WLMemberData;
import db.utils.DBConnect;
import utils.ErrorLogger;
import utils.ErrorMaster;
import utils.StringHolder;

/**
 * File:  WLMemberMapperTable.java 
 *
 * Created on 30-Aug-2013 4:04:58 PM
 */
public class WLMemberMapperTable
{
    private static ErrorMaster errorMaster_ = null;


	private String tableName_;
	private ErrorLogger errLogger_;

	/*
	 * Method : WLMemberMapperTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public WLMemberMapperTable()
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "watchlist_member_mapper";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : wlMemberList obj
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to insert the multiple items in a single query.
	 * 
	 */

	public int insert( List<WLMemberData> wlMemberList )
	{
		WLMemberMapperTableUtils utils = new WLMemberMapperTableUtils( );
		
		List<WLMemberMapperRecord> recordList = new ArrayList<WLMemberMapperRecord>( );
		
		int result = utils.dataListToRecordList( wlMemberList, recordList );
		
		if( result != 0 )
		{
			utils = null; wlMemberList = null;
			
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
        	errLogger_.logMsg( "Exception::WLMemberMapperTable.insert( List<WLMemberData> ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::WLMemberMapperTable.insert( List<WLMemberData> ) - " + e );
			
			return -3;
        }
		finally
		{
			try
            {
				utils = null; wlMemberList = null;
				
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
	 * Input : memberData obj, list of memberData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the wlmembers list and add in wlMembersList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the memberData to wlMemberMapperRecord ( using WLMemberMapperTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using WLMemberMapperTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( WLMemberData memberData,List<WLMemberData> wlMembersList )
	{
		WLMemberMapperTableUtils utils = new WLMemberMapperTableUtils( );
		
		WLMemberMapperRecord record = new WLMemberMapperRecord( );

		String selectQuery = "SELECT * FROM " + this.tableName_;
		
		//Convert the WLMemberData to WLMemberMapperRecord ( using WLMemberMapperTableUtils->dataToRecord method )
		int result = utils.dataToRecord( memberData, record );
		
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
			
			//convert result to RFQItemList
			result = utils.rsToDataList( rs, wlMembersList );
			
			utils = null; record = null; query = null;
			
			return result;
			
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::WLMemberMapperTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::WLMemberMapperTable.find() - " + e );
			
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
	 * Input : list of WLMemberData
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete members from watchlist_member_mapper table
	 */

	public int delete( List<WLMemberData> members )
	{
		if( members.size( ) == 0 )
			return 0;
		
		
		String query = "DELETE FROM " + this.tableName_ + " WHERE ";
		
		for( WLMemberData wlMemberData : members )
        {
	        query += "( watchlist_rel_id ='"+wlMemberData.watchListId_+"' AND " +
	        		"user_rel_key='"+wlMemberData.memberKey_.toString( )+"') OR ";
        }
		
		 query = query.substring( 0, query.length( ) - 3 );
		
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
			errLogger_.logMsg( "Exception::WLMemberMapperTable.delete() - " + e );
			
			return -1;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::WLMemberMapperTable.delete() - " + e );
			
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
	

}
